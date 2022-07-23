import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;

public class GUI extends JFrame
{
    public GUI()
    {
        setBounds(100, 100, 800, 600);
        setTitle("Bodega H62 - V.3.0");
        setVisible(true);
        setLayout(null);
        
        JPanel panelFormulario = new JPanel();
        panelFormulario.setBounds(0, 0, 400, 600);
        panelFormulario.setBackground(new Color(0, 0, 255));
        getContentPane().add(panelFormulario);
    }
}
