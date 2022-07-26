import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

public class BaseDatos
{
    private String conectorInstalado = "jdbc:sqlite:";
    private String rutaBaseDatos = "..\\Basedatos\\basedatos.db";
    private Connection conexion;
    private Statement ejecutor;
    
    public boolean crearConexion()
    {
        try
        {
            conexion = DriverManager.getConnection(conectorInstalado + rutaBaseDatos);
            ejecutor = conexion.createStatement();
            ejecutor.setQueryTimeout(30);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }
    
    public void cerrarConexion()
    {
        try
        {
            conexion.close();
        }
        catch (Exception e)
        {
            
        }
    }
    
    public int insertar(String sql)
    {
        try
        {
            int cant = ejecutor.executeUpdate(sql);
            return cant;
        }
        catch (Exception e)
        {
            return 0;
        }
    }
    
    public ResultSet consultar(String sql)
    {
        try
        {
            ResultSet rs = ejecutor.executeQuery(sql);
            return rs;
        }
        catch (Exception e)
        {
            return null;
        }
    }
    
    public int actualizar(String sql)
    {
        try
        {
            int cant = ejecutor.executeUpdate(sql);
            return cant;
        }
        catch (Exception e)
        {
            return 0;
        }
    }
    
    public int borrar(String sql)
    {
        try
        {
            int cant = ejecutor.executeUpdate(sql);
            return cant;
        }
        catch (Exception e)
        {
            return 0;
        }
    }
}
