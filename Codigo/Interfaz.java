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
    
    public void presentarMenuPrincipal()
    {
        int opcion = 0;
        do
        {
            String menu = "MENU PRINCIPAL \n" +
                          "1. Productos \n" +
                          "2. Ventas \n" +
                          "0. Salir";
            opcion = Integer.parseInt(JOptionPane.showInputDialog(null, menu, "Seleccione una opci�n", JOptionPane.QUESTION_MESSAGE));
            switch(opcion)
            {
                case 1:
                    this.presentarMenuProductos();
                    break;
                case 2:
                    this.generarVenta();
                    break;
                case 0:
                    JOptionPane.showMessageDialog(null, "Gracias", "Salir del programa", JOptionPane.INFORMATION_MESSAGE);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opci�n incorrecta", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        }
        while(opcion != 0);
    }
    
    public void presentarMenuProductos()
    {
        int opcion = 0;
        do 
        {
            String menu = "MENU PRODUCTOS \n" +
                          "1. Ingresar producto nuevo \n" +
                          "2. Mostrar lista de productos \n" +
                          "3. Buscar productos \n" +
                          "4. Surtir producto \n" +
                          "5. Eliminar producto \n" +
                          "0. Regresar al men� principal";
            opcion = Integer.parseInt(JOptionPane.showInputDialog(null, menu, "Seleccione una opci�n", JOptionPane.QUESTION_MESSAGE));
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
                case 4:
                    this.surtirProducto();
                    break;
                case 5:
                    this.eliminarProducto();
                    break;
                case 0:
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opci�n incorrecta", "Advertencia", JOptionPane.WARNING_MESSAGE);
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
        JOptionPane.showMessageDialog(null, "Producto agregado con �xito:\n"+p.toString(), "Producto agregado", JOptionPane.INFORMATION_MESSAGE);
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
    
    public List<Producto> buscarProductos()
    {
        String busqueda = JOptionPane.showInputDialog(null, "Ingrese dato del producto a buscar (nombre, tipo, marca, present)", "Buscar producto", JOptionPane.QUESTION_MESSAGE);
        List<Producto> lista = b.buscarProductos(busqueda);
        String listado = "";
        for (int i=0; i<lista.size(); i++)
        {
            listado = listado + "Posici�n ["+(i+1)+"] "+ lista.get(i).toString() + "\n";
        }
        JOptionPane.showMessageDialog(null, listado, "Productos encontrados", JOptionPane.INFORMATION_MESSAGE);
        return lista;
    }
    
    public void surtirProducto()
    {
        List<Producto> lista = this.buscarProductos();
        int posicion = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese posici�n del producto a surtir", "Surtir producto", JOptionPane.QUESTION_MESSAGE)) - 1;
        int cantidad = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese cantidad del producto a surtir", "Surtir producto", JOptionPane.QUESTION_MESSAGE));
        int precio = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese nuevo precio del producto a surtir", "Surtir producto", JOptionPane.QUESTION_MESSAGE));
        String codigo = lista.get(posicion).getCodigo();
        b.surtirProducto(codigo, cantidad, precio);
        JOptionPane.showMessageDialog(null, "Producto surtido exitosamente", "Producto surtido", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void eliminarProducto()
    {
        List<Producto> lista = this.buscarProductos();
        int posicion = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese posici�n del producto a eliminar", "Eliminar producto", JOptionPane.QUESTION_MESSAGE)) - 1;
        String codigo = lista.get(posicion).getCodigo();
        Producto p = b.buscarProducto(codigo);
        int opcion = JOptionPane.showConfirmDialog(null, "Desea eliminar el producto "+p.getNombre()+" "+p.getMarca()+"?", "Vender producto", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (opcion == JOptionPane.YES_OPTION)
        {
            b.eliminarProducto(codigo);
            JOptionPane.showMessageDialog(null, "Producto eliminado exitosamente", "Producto eliminado", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public void generarVenta()
    {
        Venta v = new Venta();
        int opcion;
        do
        {
            List<Producto> lista = this.buscarProductos();
            int posicion = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese posici�n del producto a vender", "Vender producto", JOptionPane.QUESTION_MESSAGE)) - 1;
            int cantidad = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese cantidad del producto a vender", "Vender producto", JOptionPane.QUESTION_MESSAGE));
            Producto p = lista.get(posicion);
            if (cantidad <= p.getCantidad()) {
                v.agregarACarrito(p, cantidad);    
            }
            else 
            {
                JOptionPane.showMessageDialog(null, "Cantidad insuficiente", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
            opcion = JOptionPane.showConfirmDialog(null, "Desea continuar agregando productos?", "Vender producto", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        } 
        while(opcion == JOptionPane.YES_OPTION);
        int valorTotal = v.calcularVenta();
        
        opcion = JOptionPane.showConfirmDialog(null, "El valor a pagar es: $"+valorTotal+"\nDesea finalizar la venta?", "Vender producto", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (opcion == JOptionPane.YES_OPTION)
        {
            v.finalizarVenta();
            JOptionPane.showMessageDialog(null, "Gracias por su compra", "Venta realizada", JOptionPane.INFORMATION_MESSAGE);
        }
        else 
        {
            JOptionPane.showMessageDialog(null, "Venta no realizada", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }
}





