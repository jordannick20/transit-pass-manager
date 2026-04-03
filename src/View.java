import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Font;
import net.miginfocom.swing.MigLayout;

public class View extends JFrame {
    private JButton btnSelect;
    private JButton btnTopup;
    private JButton btnScan;
    private JButton btnCreate;
    private JButton btnDelete;
    private JButton btnView;
    private JButton btnQuit;

    public View() {
        setTitle("Transit Pass");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new MigLayout("insets 45", "[]25[]", "[]25[][][][][][][]"));
        setContentPane(mainPanel);

        JLabel lblTitle = new JLabel("Transit Pass Management System v1.0 >");
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 24));
        mainPanel.add(lblTitle, "span 2, wrap");

        btnSelect = new JButton("select");
        mainPanel.add(btnSelect);
        mainPanel.add(new JLabel("Select Pass"), "wrap");

        btnTopup = new JButton("top-up");
        mainPanel.add(btnTopup);
        mainPanel.add(new JLabel("Top-up pass balance"), "wrap");

        btnScan = new JButton("scan");
        mainPanel.add(btnScan);
        mainPanel.add(new JLabel("Scan pass (trip deduction)"), "wrap");

        btnCreate = new JButton("create");
        mainPanel.add(btnCreate);
        mainPanel.add(new JLabel("Create new pass"), "wrap");

        btnDelete = new JButton("delete");
        mainPanel.add(btnDelete);
        mainPanel.add(new JLabel("Delete pass"), "wrap");

        btnView = new JButton("view");
        mainPanel.add(btnView);
        mainPanel.add(new JLabel("View pass transactions"), "wrap");

        btnQuit = new JButton("quit");
        mainPanel.add(btnQuit);
        mainPanel.add(new JLabel("Save and Quit"), "wrap");
    }

    public static void main(String[] args) {
        View view = new View();
        view.setVisible(true);
    }
}