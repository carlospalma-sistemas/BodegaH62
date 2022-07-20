import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;

public class Bodega 
{
    private List<Producto> listaProductos;
    
    public Bodega()
    {
        this.listaProductos = new ArrayList<Producto>();
    }
    
    public List<Producto> getListaProductos()
    {
        this.listaProductos.clear();
        BaseDatos bd = new BaseDatos();
        bd.crearConexion();
        ResultSet rs = bd.consultar("SELECT codigo, nombre, marca, presentacion, tipo, precio, cantidad "+
                                    "FROM TProducto");
        try
        {
            while(rs.next())
            {
                String codigo = rs.getString(1);
                String nombre = rs.getString(2);
                String marca = rs.getString(3);
                String presentacion = rs.getString(4);
                String tipo = rs.getString(5);
                int precio = rs.getInt(6);
                int cantidad = rs.getInt(7);
                Producto p = new Producto(tipo, nombre, marca, presentacion, codigo, precio, cantidad);
                this.listaProductos.add(p);
            }
        }
        catch(Exception e)
        {
            return null;
        }
        bd.cerrarConexion();
        return this.listaProductos;
    }

    public void agregarProducto(Producto p) 
    {
        BaseDatos bd = new BaseDatos();
        String sql = "INSERT INTO TProducto(tipo, nombre, marca, presentacion, codigo, precio, cantidad) "+
                     "VALUES (\""+p.getTipo()+"\", \""+p.getNombre()+"\", \""+p.getMarca()+"\", \""+p.getPresentacion()+"\", \""+p.getCodigo()+"\", "+p.getPrecio()+", "+p.getCantidad()+") ";
        bd.crearConexion();
        bd.insertar(sql);
        bd.cerrarConexion();
    }

    public ArrayList<Producto> buscarProductos(String criterio)
    {
        ArrayList<Producto> listaEncontrados = new ArrayList<Producto>();
        BaseDatos bd = new BaseDatos();
        bd.crearConexion();
        String sql = "SELECT codigo, nombre, marca, presentacion, tipo, precio, cantidad "+
                     "FROM TProducto "+
                     "WHERE nombre LIKE \"%"+criterio+"%\" "+
                     "OR marca LIKE \"%"+criterio+"%\" "+
                     "OR tipo LIKE \"%"+criterio+"%\" "+
                     "OR presentacion LIKE \"%"+criterio+"%\" ";
        ResultSet rs = bd.consultar(sql);
        try
        {
            while(rs.next())
            {
                String codigo = rs.getString(1);
                String nombre = rs.getString(2);
                String marca = rs.getString(3);
                String presentacion = rs.getString(4);
                String tipo = rs.getString(5);
                int precio = rs.getInt(6);
                int cantidad = rs.getInt(7);
                Producto p = new Producto(tipo, nombre, marca, presentacion, codigo, precio, cantidad);
                listaEncontrados.add(p);
            }
        }
        catch(Exception e)
        {
            return null;
        }
        bd.cerrarConexion();
        return listaEncontrados;
    }
    
    public void eliminarProducto(String codigo) 
    {
        BaseDatos bd = new BaseDatos();
        bd.crearConexion();
        String sql = "DELETE FROM TProducto "+
                     "WHERE codigo = \""+codigo+"\" ";
        bd.borrar(sql);
        bd.cerrarConexion();
        
    }

    public void surtirProducto(String codigo, int cant, int precio) 
    {
        BaseDatos bd = new BaseDatos();
        bd.crearConexion();
        String sql = "UPDATE TProducto "+
                     "SET cantidad = cantidad + "+cant+", precio = "+precio+" "+
                     "WHERE codigo = \""+codigo+"\"";
        bd.actualizar(sql);
        bd.cerrarConexion();
    }

    public void disminuirCantProducto(String codigo, int cant) 
    {
        BaseDatos bd = new BaseDatos();
        bd.crearConexion();
        String sql = "UPDATE TProducto "+
                     "SET cantidad = cantidad - "+cant+" "+
                     "WHERE codigo = \""+codigo+"\"";
        bd.actualizar(sql);
        bd.cerrarConexion();
    }
    
}






