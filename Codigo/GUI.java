import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

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
import java.util.List;


public class GUI extends JFrame
{
    JTextField txtCodigo, txtNombre, txtMarca, txtPresentacion, txtCantidad, txtPrecio, txtFiltro;
    JComboBox comboTipo;
    JButton btnEliminar, btnGuardar, btnCancelar, btnFiltrar, btnQuitarFiltro;
    DefaultTableModel dtm;
    
    public GUI()
    {
        setBounds(100, 50, 1200, 600);
        setTitle("Bodega H62 - V.3.0");
        setLayout(null);
        
        JPanel panelFormulario = new JPanel();
        panelFormulario.setBounds(5, 5, 400, 550);
        panelFormulario.setBorder(BorderFactory.createEtchedBorder());
        panelFormulario.setLayout(null);
        getContentPane().add(panelFormulario);
        
        JPanel panelTabla = new JPanel();
        panelTabla.setBounds(410, 5, 770, 550);
        panelTabla.setBorder(BorderFactory.createEtchedBorder());
        panelTabla.setLayout(null);
        getContentPane().add(panelTabla);
        
        JLabel labelCodigo = new JLabel("Codigo: ");
        labelCodigo.setBounds(20, 20, 100, 30);
        panelFormulario.add(labelCodigo);
        
        txtCodigo = new JTextField();
        txtCodigo.setBounds(140, 20, 250, 30);
        panelFormulario.add(txtCodigo);
        
        JLabel labelNombre = new JLabel("Nombre: ");
        labelNombre.setBounds(20, 70, 100, 30);
        panelFormulario.add(labelNombre);
        
        txtNombre = new JTextField();
        txtNombre.setBounds(140, 70, 250, 30);
        panelFormulario.add(txtNombre);
        
        JLabel labelMarca = new JLabel("Marca: ");
        labelMarca.setBounds(20, 120, 100, 30);
        panelFormulario.add(labelMarca);
        
        txtMarca = new JTextField();
        txtMarca.setBounds(140, 120, 250, 30);
        panelFormulario.add(txtMarca);
        
        JLabel labelPresent = new JLabel("Presentación: ");
        labelPresent.setBounds(20, 170, 100, 30);
        panelFormulario.add(labelPresent);
        
        txtPresentacion = new JTextField();
        txtPresentacion.setBounds(140, 170, 250, 30);
        panelFormulario.add(txtPresentacion);
        
        JLabel labelTipo = new JLabel("Tipo: ");
        labelTipo.setBounds(20, 220, 100, 30);
        panelFormulario.add(labelTipo);
        
        comboTipo = new JComboBox();
        comboTipo.setBounds(140, 220, 250, 30);
        comboTipo.addItem("");
        comboTipo.addItem("Aseo");
        comboTipo.addItem("Alimento");
        panelFormulario.add(comboTipo);
        
        JLabel labelCant = new JLabel("Cantidad: ");
        labelCant.setBounds(20, 270, 100, 30);
        panelFormulario.add(labelCant);
        
        txtCantidad = new JTextField();
        txtCantidad.setBounds(140, 270, 250, 30);
        panelFormulario.add(txtCantidad);
        
        JLabel labelPrecio = new JLabel("Precio: ");
        labelPrecio.setBounds(20, 320, 100, 30);
        panelFormulario.add(labelPrecio);
        
        txtPrecio = new JTextField();
        txtPrecio.setBounds(140, 320, 250, 30);
        panelFormulario.add(txtPrecio);
        
        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(20, 500, 90, 30);
        panelFormulario.add(btnEliminar);
        
        btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(140, 500, 90, 30);
        panelFormulario.add(btnGuardar);
        
        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(250, 500, 90, 30);
        panelFormulario.add(btnCancelar);
        
        JLabel labelFiltro = new JLabel("Filtrar por: ");
        labelFiltro.setBounds(20, 500, 100, 30);
        panelTabla.add(labelFiltro);
        
        txtFiltro = new JTextField();
        txtFiltro.setBounds(140, 500, 250, 30);
        panelTabla.add(txtFiltro);
        
        btnFiltrar = new JButton("Filtrar");
        btnFiltrar.setBounds(400, 500, 90, 30);
        panelTabla.add(btnFiltrar);
        
        btnQuitarFiltro = new JButton("Quitar filtro");
        btnQuitarFiltro.setBounds(510, 500, 120, 30);
        panelTabla.add(btnQuitarFiltro);
        
        Object[][] datos = null;
        String[] columnas = {"Codigo", "Nombre", "Marca", "Presentación", "Tipo", "Cant", "Precio"};     
        dtm= new DefaultTableModel(datos, columnas);
        
        JTable tablaProductos = new JTable(dtm);
        tablaProductos.setPreferredScrollableViewportSize(new Dimension(745, 350));
        tablaProductos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaProductos.setFillsViewportHeight(true);
        JScrollPane scroll = new JScrollPane(tablaProductos);
        
        JPanel contenidoTabla = new JPanel();
        contenidoTabla.setBounds(10, 10, 745, 350);
        contenidoTabla.setLayout(new GridLayout(1,0));
        contenidoTabla.add(scroll);
        panelTabla.add(contenidoTabla);
        
        cargarProductos();
        vincularListeners();
        
        setVisible(true);
    }
    
    public void vincularListeners()
    {
        btnFiltrar.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                cargarProductosFiltrados();
            }
        });
    }
    
    public void cargarProductos()
    {
        Bodega bodega = new Bodega();
        List<Producto> productos = bodega.getListaProductos();
        dtm.setRowCount(0);
        for(Producto p : productos)
        {
            Object[] row = {p.getCodigo(), p.getNombre(), p.getMarca(), p.getPresentacion(), p.getTipo(), p.getCantidad(), p.getPrecio()};
            dtm.addRow(row);
        }
    }
    
    public void cargarProductosFiltrados()
    {
        Bodega bodega = new Bodega();
        String criterio = txtFiltro.getText();
        if (criterio.equals(""))
        {
            JOptionPane.showMessageDialog(null, "Debe proporcionar un criterio para filtrar", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
        else
        {
            List<Producto> productos = bodega.buscarProductos(criterio);
            dtm.setRowCount(0);
            for(Producto p : productos)
            {
                Object[] row = {p.getCodigo(), p.getNombre(), p.getMarca(), p.getPresentacion(), p.getTipo(), p.getCantidad(), p.getPrecio()};
                dtm.addRow(row);
            }
        }
    }
}









