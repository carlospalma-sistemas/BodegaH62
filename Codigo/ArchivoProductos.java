import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.List;
import java.util.ArrayList;

public class ArchivoProductos
{
    private File archivo;
    
    public ArchivoProductos()
    {
        this.archivo = new File("listaproductos.txt");
    }
    
    public void guardarProducto(String texto)
    {
        try
        {
            FileWriter writer = new FileWriter(this.archivo, true);  
            PrintWriter cursor = new PrintWriter(writer);
            cursor.println(texto);
            cursor.flush();
            cursor.close();
            writer.close();
        }
        catch(Exception e)
        {
            
        }
    }
    
    public List<Producto> leerProductos()
    {
        List<Producto> lista = new ArrayList<Producto>();
        try
        {
            FileReader reader = new FileReader(this.archivo);
            BufferedReader cursor = new BufferedReader(reader);
            while(cursor.ready())
            {
                String linea = cursor.readLine();
                String [] datos = linea.split(";");
                String codigo = datos[0];
                String nombre = datos[1];
                String marca = datos[2];
                String presentacion = datos[3];
                String tipo = datos[4];
                int precio = Integer.parseInt(datos[5]);
                int cantidad = Integer.parseInt(datos[6]);
                Producto p = new Producto(tipo, nombre, marca, presentacion, codigo, precio, cantidad);
                lista.add(p);
            }
        }
        catch(Exception e)
        {
            
        }
        return lista;
    }
    
}
