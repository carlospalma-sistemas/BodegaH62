import java.util.List;
import java.util.ArrayList;

public class Bodega 
{
    private List<Producto> listaProductos;
    
    public Bodega()
    {
        this.listaProductos = new ArrayList<Producto>();
    }
    
    public List<Producto> getListaProductos()
    {
        return this.listaProductos;
    }

    public void agregarProducto(Producto p) 
    {
        this.listaProductos.add(p);
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






