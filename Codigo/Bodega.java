import java.util.List;
import java.util.ArrayList;

public class Bodega 
{
    private List<Producto> listaProductos;
    
    public Bodega()
    {
        ArchivoProductos a = new ArchivoProductos();
        this.listaProductos = a.leerProductos();
    }
    
    public List<Producto> getListaProductos()
    {
        return this.listaProductos;
    }

    public void agregarProducto(Producto p) 
    {
        this.listaProductos.add(p);
        ArchivoProductos a = new ArchivoProductos();
        a.guardarProducto(p.toCSV());
    }

    public int buscarProducto(String codigo) 
    {
        for (int i = 0; i < this.listaProductos.size(); i++)
        {
            if (this.listaProductos.get(i).getCodigo().equals(codigo))
            {
                return i;
            }
        }
        return -1;
    }
    
    public ArrayList<Producto> buscarProductos(String criterio)
    {
        ArrayList<Producto> listaEncontrados = new ArrayList<Producto>();
        for (Producto p : this.listaProductos)
        {
            if (p.getNombre().equals(criterio) || p.getMarca().equals(criterio) || p.getPresentacion().equals(criterio) || p.getTipo().equals(criterio))
            {
                listaEncontrados.add(p);
            }
        }
        return listaEncontrados;
    }
    
    public void eliminarProducto(String codigo) 
    {
        int indexAEliminar = this.buscarProducto(codigo);
        if (indexAEliminar != -1)
        {
            this.listaProductos.remove(indexAEliminar);    
        }
    }

   
    public void modificarPrecio(String codigo, int precio) 
    {
        int indexAModificar = this.buscarProducto(codigo);
        if (indexAModificar != -1)
        {
            this.listaProductos.get(indexAModificar).setPrecio(precio);
        }
    }

    public void incrementarCantProducto(String codigo, int cant) 
    {
        int indexAModificar = this.buscarProducto(codigo);
        if (indexAModificar != -1)
        {
            int cantActual = this.listaProductos.get(indexAModificar).getCantidad();
            this.listaProductos.get(indexAModificar).setCantidad(cantActual + cant);
        }
    }

    public void disminuirCantProducto(String codigo, int cant) 
    {
        int indexAModificar = this.buscarProducto(codigo);
        if (indexAModificar != -1)
        {
            int cantActual = this.listaProductos.get(indexAModificar).getCantidad();
            if (cantActual >= cant)
            {
                this.listaProductos.get(indexAModificar).setCantidad(cantActual - cant);    
            }
        }
    }

}






