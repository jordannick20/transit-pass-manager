import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Font;
import net.miginfocom.swing.MigLayout;
import java.awt.CardLayout;
import java.awt.Dimension;

public class View extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainContainer;
    private JPanel creatPanel;

    private JButton btnSelect;
    private JButton btnTopup;
    private JButton btnScan;
    private JButton btnCreate;
    private JButton btnDelete;
    private JButton btnView;
    private JButton btnQuit;
    
    private JComboBox<String> cmbPassType;
    private JTextField txtHolderName;
    private JTextField txtInitialBalance;
    private JButton btnCreatePass;
    private JButton btnBack;

    public View() {
        setTitle("Transit Pass");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainContainer = new JPanel(cardLayout);
        setContentPane(mainContainer);

        buildMainMenuPanel();
        buildCreatePanel();
    }
    
    private void buildMainMenuPanel() {
        JPanel mainMenuPanel = new JPanel(new MigLayout("insets 45", "[]25[]", "[]25[][][][][][][]"));

        JLabel lblTitle = new JLabel("Transit Pass Management System v1.0 >");
        lblTitle.setFont(new Font(Font.MONOSPACED,Font.BOLD, 24));
        mainMenuPanel.add(lblTitle, "span 2, wrap");

        btnSelect = new JButton("select");
        mainMenuPanel.add(btnSelect);
        mainMenuPanel.add(new JLabel("Select Pass"), "wrap");

        btnTopup = new JButton("top-up");
        mainMenuPanel.add(btnTopup);
        mainMenuPanel.add(new JLabel("Top-up pass balance"), "wrap");

        btnScan = new JButton("scan");
        mainMenuPanel.add(btnScan);
        mainMenuPanel.add(new JLabel("Scan pass (trip deduction)"), "wrap");

        btnCreate = new JButton("create");
        mainMenuPanel.add(btnCreate);
        mainMenuPanel.add(new JLabel("Create new pass"), "wrap");

        btnDelete = new JButton("delete");
        mainMenuPanel.add(btnDelete);
        mainMenuPanel.add(new JLabel("Delete pass"), "wrap");

        btnView = new JButton("view");
        mainMenuPanel.add(btnView);
        mainMenuPanel.add(new JLabel("View pass transactions"), "wrap");

        btnQuit = new JButton("quit");
        mainMenuPanel.add(btnQuit);
        mainMenuPanel.add(new JLabel("Save and Quit"), "wrap");

        mainContainer.add(mainMenuPanel, "MAIN");

        btnQuit.addActionListener(e -> {
            System.exit(0);
        });

        btnCreate.addActionListener(e -> {
            cardLayout.show(mainContainer, "CREATE");
        });
    }
    
    private void buildCreatePanel() {
        JPanel createPanel = new JPanel(new MigLayout("insets 25", "[][grow]", "[]20[]15[]15[]25[]"));

        JLabel lblCreateTitle = new JLabel("Create New Pass");
        lblCreateTitle.setFont(new Font(Font.MONOSPACED,Font.BOLD, 24));
        createPanel.add(lblCreateTitle, "span 2, wrap");

        createPanel.add(new JLabel("Pass Type:"));
        cmbPassType = new JComboBox<>(new String[]{"Standard Pass", "Green Pass"});
        createPanel.add(cmbPassType, "growx, wrap");

        createPanel.add(new JLabel("Holder Name:"));
        txtHolderName = new JTextField();
        createPanel.add(txtHolderName, "growx, wrap");

        createPanel.add(new JLabel("Initial Balance:"));
        txtInitialBalance = new JTextField();
        txtInitialBalance.setPreferredSize(new Dimension(80, 20));
        createPanel.add(txtInitialBalance, "wrap");

        btnCreatePass = new JButton("Create Pass");
        btnBack = new JButton("Back");

        createPanel.add(btnCreatePass);
        createPanel.add(btnBack);

        // btnCreatePass.addActionListener(e -> onCreatePass());
        btnBack.addActionListener(e -> {
            clearCreateFields();
            cardLayout.show(mainContainer, "MAIN");
        });

        mainContainer.add(createPanel, "CREATE");
    }

    private void clearCreateFields() {
        txtHolderName.setText("");
        txtInitialBalance.setText("");
        cmbPassType.setSelectedIndex(0);
    }  

    public static void main(String[] args) {
        View view = new View();
        view.setVisible(true);
    }
}