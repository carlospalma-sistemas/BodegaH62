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
        
    }

    public int buscarProducto(String codigo) 
    {
        return -1;
    }
    
    public ArrayList<Producto> buscarProductos(String criterio)
    {
        ArrayList<Producto> listaEncontrados = new ArrayList<Producto>();
        return listaEncontrados;
    }
    
    public void eliminarProducto(String codigo) 
    {
        
    }

   
    public void modificarPrecio(String codigo, int precio) 
    {
        
    }

    public void incrementarCantProducto(String codigo, int cant) 
    {

    }

    public void disminuirCantProducto(String codigo, int cant) 
    {

    }
    
    public void actualizarArchivo()
    {
    }
}






