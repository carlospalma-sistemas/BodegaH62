import javax.swing.JOptionPane;
import java.util.Random;
import java.util.List;

public class Interfaz
{
    private Bodega b;
    
    public Interfaz()
    {
        b = new Bodega();
    }
    
    public void presentarMenu()
    {
        int opcion = 0;
        do 
        {
            String menu = "MENU PRINCIPAL \n" +
                          "1. Ingresar producto nuevo \n" +
                          "2. Mostrar lista de productos \n" +
                          "3. Buscar productos \n" +
                          "0. Salir";
            opcion = Integer.parseInt(JOptionPane.showInputDialog(null, menu, "Seleccione una opción", JOptionPane.QUESTION_MESSAGE));
            switch(opcion)
            {
                case 1:
                    this.ingresarProducto();
                    break;
                case 2:
                    this.mostrarProductos();
                    break;
                case 3:
                    this.buscarProductos();
                    break;
                case 0:
                    JOptionPane.showMessageDialog(null, "Gracias", "Salir del programa", JOptionPane.INFORMATION_MESSAGE);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción incorrecta", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        }
        while(opcion != 0);
    }
    
    public void ingresarProducto()
    {
        String [] tipos = {"Aseo", "Alimento"};
        int tipo = JOptionPane.showOptionDialog(null, "Ingrese tipo de producto", "Producto nuevo", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, tipos, tipos[0]);
        String nombre = JOptionPane.showInputDialog(null, "Ingrese nombre de producto", "Producto nuevo", JOptionPane.QUESTION_MESSAGE);
        String marca = JOptionPane.showInputDialog(null, "Ingrese marca de producto", "Producto nuevo", JOptionPane.QUESTION_MESSAGE);
        String presentacion = JOptionPane.showInputDialog(null, "Ingrese presentacion de producto", "Producto nuevo", JOptionPane.QUESTION_MESSAGE);
        String codigo = this.generarCodigoAleatorio();
        int precio = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese precio del producto", "Producto nuevo", JOptionPane.QUESTION_MESSAGE));
        int cantidad = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese cantidad del producto", "Producto nuevo", JOptionPane.QUESTION_MESSAGE));
        
        Producto p = new Producto(tipos[tipo], nombre, marca, presentacion, codigo, precio, cantidad);
        
        b.agregarProducto(p);
        JOptionPane.showMessageDialog(null, "Producto agregado con éxito:\n"+p.toString(), "Producto agregado", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public String generarCodigoAleatorio()
    {
        Random random = new Random();
        String caracteres = "0123456789abcdefghijklmnopqrstuvwxyz";
        String codigo = "";
        for (int i=0; i<10; i++)
        {
            int index = random.nextInt(caracteres.length());
            codigo = codigo + caracteres.charAt(index);
        }
        return codigo;
    }
    
    public void mostrarProductos()
    {
        String listado = "";
        List<Producto> lista = b.getListaProductos();
        for (Producto p: lista)
        {
            listado = listado + p.toString() + "\n";
        }
        JOptionPane.showMessageDialog(null, listado, "Productos registrados", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void buscarProductos()
    {
        String busqueda = JOptionPane.showInputDialog(null, "Ingrese dato del producto a buscar (nombre, tipo, marca, present)", "Buscar producto", JOptionPane.QUESTION_MESSAGE);
        List<Producto> lista = b.buscarProductos(busqueda);
        String listado = "";
        for (Producto p: lista)
        {
            listado = listado + p.toString() + "\n";
        }
        JOptionPane.showMessageDialog(null, listado, "Productos encontrados", JOptionPane.INFORMATION_MESSAGE);
    }
}












