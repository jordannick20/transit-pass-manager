import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

public class View extends JFrame {
    private JLabel txtName;


    public View() {
        setTitle("Transit Pass Manager");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new MigLayout(""));
        setContentPane(mainPanel);


        mainPanel.add(new JLabel("Transit Pass Management System v1.0 >"));
        // JButton
        // JButton
        // JButton
        // JButton
        // JButton

    }


    
    public static void main(String[] args) throws Exception {
        View view = new View();
        view.setVisible(true);
    }
}
