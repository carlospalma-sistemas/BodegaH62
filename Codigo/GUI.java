import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import java.util.List;
public class GUI extends JFrame
{
    JTextField txtCodigo, txtNombre, txtMarca, txtPresent, txtCant, txtPrecio, txtBuscar;
    JComboBox comboTipo;
    JButton btnAgregar, btnActualizar, btnLimpiar, btnEliminar, btnFiltrar, btnQuitarFiltro;
    JTable tablaProductos;
    DefaultTableModel dtm;
    
    public GUI()
    {
        iniciarComponentes();
        cargarProductos();
        implementarListeners();
    }
    
    public void iniciarComponentes()
    {
        setBounds(100, 100, 1200, 550);
        setTitle("Bodega H62 - V2.0");
        setLayout(null);
        
        //Creación de páneles
        JPanel panelBanner = new JPanel();
        panelBanner.setBounds(0, 0, 1200, 100);
        panelBanner.setBackground(new Color(64, 0, 196));   
        panelBanner.setLayout(null);
        getContentPane().add(panelBanner);
        
        JPanel panelFormulario = new JPanel();
        panelFormulario.setBounds(5, 105, 455, 402);
        panelFormulario.setBorder(BorderFactory.createEtchedBorder());
        panelFormulario.setLayout(null);
        getContentPane().add(panelFormulario);
        
        JPanel panelTabla = new JPanel();
        panelTabla.setBounds(465, 105, 715, 402);
        panelTabla.setBorder(BorderFactory.createEtchedBorder());
        panelTabla.setLayout(null);
        getContentPane().add(panelTabla);
        
        //Componentes del panel del banner
        JLabel labelTitulo = new JLabel("Sistema de Inventario y POS");
        labelTitulo.setBounds(20, 20, 500, 60);
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 28));
        labelTitulo.setForeground(Color.WHITE);
        panelBanner.add(labelTitulo);
        
        Image imagen = new ImageIcon("imgs\\logo_shop.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        JLabel labelLogo = new JLabel(new ImageIcon(imagen));
        labelLogo.setBounds(1050, 10, 84, 80);
        panelBanner.add(labelLogo);
        
        //Componentes del panel del formulario
        JLabel labelCodigo = new JLabel("Codigo");
        labelCodigo.setBounds(10, 20, 100, 30);
        panelFormulario.add(labelCodigo);
        
        txtCodigo = new JTextField();
        txtCodigo.setBounds(140, 20, 300, 30);
        txtCodigo.setEditable(false);
        panelFormulario.add(txtCodigo);
        
        JLabel labelNombre = new JLabel("Nombre");
        labelNombre.setBounds(10, 60, 100, 30);
        panelFormulario.add(labelNombre);
        
        txtNombre = new JTextField();
        txtNombre.setBounds(140, 60, 300, 30);
        panelFormulario.add(txtNombre);
        
        JLabel labelMarca = new JLabel("Marca");
        labelMarca.setBounds(10, 100, 100, 30);
        panelFormulario.add(labelMarca);
        
        txtMarca = new JTextField();
        txtMarca.setBounds(140, 100, 300, 30);
        panelFormulario.add(txtMarca);
        
        JLabel labelPresent = new JLabel("Presentacion");
        labelPresent.setBounds(10, 140, 100, 30);
        panelFormulario.add(labelPresent);
        
        txtPresent = new JTextField();
        txtPresent.setBounds(140, 140, 300, 30);
        panelFormulario.add(txtPresent);
        
        JLabel labelTipo = new JLabel("Tipo");
        labelTipo.setBounds(10, 180, 100, 30);
        panelFormulario.add(labelTipo);
        
        comboTipo = new JComboBox();
        comboTipo.addItem("");
        comboTipo.addItem("Aseo");
        comboTipo.addItem("Alimento");
        comboTipo.setBounds(140, 180, 300, 30);
        UIManager.put("ComboBox.disabledForeground", Color.BLACK);
        panelFormulario.add(comboTipo);
        
        JLabel labelCant = new JLabel("Cantidad");
        labelCant.setBounds(10, 220, 100, 30);
        panelFormulario.add(labelCant);
        
        txtCant = new JTextField();
        txtCant.setBounds(140, 220, 300, 30);
        panelFormulario.add(txtCant);

        JLabel labelPrecio = new JLabel("Precio");
        labelPrecio.setBounds(10, 260, 100, 30);
        panelFormulario.add(labelPrecio);
        
        txtPrecio = new JTextField();
        txtPrecio.setBounds(140, 260, 300, 30);
        panelFormulario.add(txtPrecio);
        
        btnAgregar = new JButton("Agregar");
        btnAgregar.setBounds(10, 350, 100, 30);
        panelFormulario.add(btnAgregar);
        
        btnActualizar = new JButton("Actualizar");
        btnActualizar.setBounds(120, 350, 100, 30);
        panelFormulario.add(btnActualizar);
        
        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBounds(230, 350, 100, 30);
        panelFormulario.add(btnLimpiar);
        
        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(340, 350, 100, 30);
        panelFormulario.add(btnEliminar);
        
        //Componentes del panel de la tabla
        Object[][] datos = null;
        String[] columnas = {"Codigo", "Nombre", "Marca", "Presentación", "Tipo", "Cant", "Precio"};     
        dtm= new DefaultTableModel(datos, columnas);
        
        tablaProductos = new JTable(dtm);
        tablaProductos.setPreferredScrollableViewportSize(new Dimension(715, 290));
        tablaProductos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaProductos.setFillsViewportHeight(true);
        JScrollPane scroll = new JScrollPane(tablaProductos);
        
        JPanel contenidoTabla = new JPanel();
        contenidoTabla.setBounds(0, 0, 715, 290);
        contenidoTabla.setLayout(new GridLayout(1,0));
        contenidoTabla.add(scroll);
        panelTabla.add(contenidoTabla);
        
        JLabel labelBuscar = new JLabel("Buscar por: ");
        labelBuscar.setBounds(10, 350, 100, 30);
        panelTabla.add(labelBuscar);
        
        txtBuscar = new JTextField();
        txtBuscar.setBounds(140, 350, 300, 30);
        panelTabla.add(txtBuscar);
        
        btnFiltrar = new JButton("Filtrar");
        btnFiltrar.setBounds(460, 350, 100, 30);
        panelTabla.add(btnFiltrar);
        
        btnQuitarFiltro = new JButton("Quitar filtro");
        btnQuitarFiltro.setBounds(570, 350, 120, 30);
        panelTabla.add(btnQuitarFiltro);
        
        setVisible(true);
    }

    public void cargarProductos()
    {
        dtm.setRowCount(0);
        Bodega bodega = new Bodega();
        List<Producto> productos = bodega.getListaProductos();
        for (Producto p : productos)
        {
            Object[] row = { p.getCodigo(), p.getNombre(), p.getMarca(), p.getPresentacion(), p.getTipo(), p.getCantidad(), p.getPrecio() };
            dtm.addRow(row);
        }
    }
    
    public void implementarListeners()
    {
        btnFiltrar.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                cargarProductosFiltrados();
            }
        });
        
        btnQuitarFiltro.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                limpiarFiltro();
            }
        });
        
        btnAgregar.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                agregarProducto();
            }
        });
        
        btnLimpiar.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                limpiarFormulario();
            }
        });
        
        tablaProductos.getSelectionModel().addListSelectionListener(new ListSelectionListener() 
        {
            public void valueChanged(ListSelectionEvent e) 
            {
                if (tablaProductos.getSelectedRowCount() > 0)
                {
                    int row = tablaProductos.getSelectedRow();
                    String codigo = dtm.getValueAt(row, 0).toString();
                    cargarProductoEnFormulario(codigo);
                }
            }
        });
        
        btnActualizar.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                actualizarProducto();
            }
        });
        
        btnEliminar.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                eliminarProducto();
            }
        });
        
        txtCant.addKeyListener(new KeyAdapter() 
        {
            public void keyTyped(KeyEvent e)
            {
                char tecla = e.getKeyChar();
                if (tecla < '0' || tecla > '9' && tecla != '\b') 
                {
                    e.consume();
                }
            }
        });
        
        txtPrecio.addKeyListener(new KeyAdapter() 
        {
            public void keyTyped(KeyEvent e)
            {
                char tecla = e.getKeyChar();
                if (tecla < '0' || tecla > '9' && tecla != '\b') 
                {
                    e.consume();
                }
            }
        });
    }
    
    public void cargarProductosFiltrados()
    {
        dtm.setRowCount(0);
        Bodega bodega = new Bodega();
        String criterio = txtBuscar.getText();
        List<Producto> productos = bodega.buscarProductos(criterio);
        for (Producto p : productos)
        {
            Object[] row = { p.getCodigo(), p.getNombre(), p.getMarca(), p.getPresentacion(), p.getTipo(), p.getCantidad(), p.getPrecio() };
            dtm.addRow(row);
        }
    }
    
    public void limpiarFiltro()
    {
        txtBuscar.setText("");
        cargarProductos();   
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
    
    public void agregarProducto()
    {
        if (!txtCodigo.getText().equals(""))
        {
            JOptionPane.showMessageDialog(this, "Debe ser un producto nuevo");
            return;
        }
        
        if (txtNombre.getText().equals("") || txtMarca.getText().equals("") || txtPresent.getText().equals("") || comboTipo.getSelectedItem().equals("") || txtCant.getText().equals("") || txtPrecio.getText().equals(""))
        {
            JOptionPane.showMessageDialog(this, "Debe ingresar la información completa");
            return;
        }
        String codigo = generarCodigoAleatorio();
        String nombre = txtNombre.getText();
        String marca = txtMarca.getText();
        String presentacion = txtPresent.getText();
        String tipo = comboTipo.getSelectedItem().toString();
        int cantidad = Integer.parseInt(txtCant.getText());
        int precio = Integer.parseInt(txtPrecio.getText());
        
        Producto p = new Producto(tipo, nombre, marca, presentacion, codigo, precio, cantidad);
        Bodega bodega = new Bodega();
        bodega.agregarProducto(p);
        limpiarFormulario();
        cargarProductos();
    }
    
    public void limpiarFormulario()
    {
        txtCodigo.setText("");
        txtNombre.setText("");
        txtMarca.setText("");
        txtPresent.setText("");
        comboTipo.setSelectedItem("");
        txtCant.setText("");
        txtPrecio.setText("");
        desbloquearFormulario();
    }
    
    public void cargarProductoEnFormulario(String codigo)
    {
        Bodega bodega = new Bodega();
        Producto p = bodega.buscarProducto(codigo);
        txtCodigo.setText(p.getCodigo());
        txtNombre.setText(p.getNombre());
        txtMarca.setText(p.getMarca());
        txtPresent.setText(p.getPresentacion());
        comboTipo.setSelectedItem(p.getTipo());
        txtCant.setText(p.getCantidad() + "");
        txtPrecio.setText(p.getPrecio() + "");
        bloquearFormulario();
    }
    
    public void actualizarProducto()
    {
        if (txtCodigo.getText().equals("") || txtCant.getText().equals("") || txtPrecio.getText().equals(""))
        {
            JOptionPane.showMessageDialog(this, "Debe haber información completa para el producto a actualizar");
            return;
        }
        String codigo = txtCodigo.getText();
        int cantidad = Integer.parseInt(txtCant.getText());
        int precio = Integer.parseInt(txtPrecio.getText());
        Bodega bodega = new Bodega();
        bodega.surtirProducto(codigo, cantidad, precio);
        limpiarFormulario();
        cargarProductos();
    }
    
    public void bloquearFormulario()
    {
        txtNombre.setEditable(false);
        txtMarca.setEditable(false);
        txtPresent.setEditable(false);
        comboTipo.setEnabled(false);
    }
    
    public void desbloquearFormulario()
    {
        txtNombre.setEditable(true);
        txtMarca.setEditable(true);
        txtPresent.setEditable(true);
        comboTipo.setEnabled(true);
    }
    
    public void eliminarProducto()
    {
        if (txtCodigo.getText().equals(""))
        {
            JOptionPane.showMessageDialog(this, "Seleccione un producto para eliminar");
            return;
        }
        int opcion = JOptionPane.showConfirmDialog(this, "Desea realmente eliminar este producto?", "Advertencia", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (opcion == JOptionPane.YES_OPTION)
        {
            Bodega bodega= new Bodega();
            bodega.eliminarProducto(txtCodigo.getText());
        }
        limpiarFormulario();
        cargarProductos();
    }
}













