import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import java.awt.Font;
import net.miginfocom.swing.MigLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.awt.event.*;

public class View extends JFrame {

    // Layout and Main Containers
    private CardLayout cardLayout;
    private JPanel mainContainer;

    // Screen Panels
    private JPanel viewPanel;
    private JPanel scanPanel;
    private JPanel topupPanel;
    private JPanel deletePanel;
    private JPanel selectPanel;

    // Top-Up Screen stuff
    private JButton btnTopUpOk;
    private JButton btnTopUpCancel;
    private JTextField txtTopUpAmount;

    // Delete Screen stuff
    private JLabel lblDeletePassName;
    private JButton btnDeleteOk;
    private JButton btnDeleteCancel;

    // Select Screen stuff
    private JList<String> lstPasses;
    private DefaultListModel<String> listModel;
    private JButton btnSelectOk;
    private JButton btnSelectCancel;

    // Scan Screen stuff
    private JLabel lblScanPassName;
    private JButton btnScanOk;
    private JButton btnScanCancel;

    // View Screen stuff
    private JTextArea txtViewOutput;

    // Main Menu Buttons
    private JButton btnSelect;
    private JButton btnTopup;
    private JButton btnScan;
    private JButton btnCreate;
    private JButton btnDelete;
    private JButton btnView;
    private JButton btnQuit;

    // Other Buttons 
    private JButton btnViewBack;
    private JButton btnCreatePass;
    private JButton btnBack;

    private JComboBox<String> cmbPassType;
    private JTextField txtHolderName;
    private JTextField txtInitialBalance;
    

    // Constructor
    public View() {
        setTitle("Transit Pass");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        // setup for card layout 
        cardLayout = new CardLayout();
        mainContainer = new JPanel(cardLayout);
        setContentPane(mainContainer);

        buildMainMenuPanel();
        buildCreatePanel();
        buildViewPanel();
        buildScanPanel();
        buildTopupPanel();
        buildSelectPanel();
        buildDeletePanel();
        
    }

    // using cardlayout to build Panels 

    private void buildMainMenuPanel() {
        JPanel mainMenuPanel = new JPanel(new MigLayout("insets 45", "[]25[]", "[]25[][][][][][][]"));

        JLabel lblTitle = new JLabel("Transit Pass Management System v1.0 >");
        lblTitle.setFont(new Font(Font.MONOSPACED, Font.BOLD, 24));
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
        setMainButtonsEnabled(false);

        mainContainer.add(mainMenuPanel, "MAIN");
    }

    private void buildCreatePanel() {
        JPanel createPanel = new JPanel(new MigLayout("insets 25", "[][grow]", "[]20[]15[]15[]25[]"));

        JLabel lblCreateTitle = new JLabel("Transit Pass Management System v1.0 >");
        lblCreateTitle.setFont(new Font(Font.MONOSPACED, Font.BOLD, 24));
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

        mainContainer.add(createPanel, "CREATE");
    }

    private void buildViewPanel() {
        viewPanel = new JPanel(new MigLayout("insets 25", "[grow]", "[]20[grow]20[]"));

        JLabel lblTitle = new JLabel("Transit Pass Management System v1.0 >");
        lblTitle.setFont(new Font(Font.MONOSPACED, Font.BOLD, 24));
        viewPanel.add(lblTitle, "wrap");

        txtViewOutput = new JTextArea(15, 40);
        txtViewOutput.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(txtViewOutput);
        viewPanel.add(scrollPane, "grow, wrap");

        btnViewBack = new JButton("Back");
        viewPanel.add(btnViewBack, "right");

        mainContainer.add(viewPanel, "VIEW");
    }

    private void buildScanPanel() {
        scanPanel = new JPanel(new MigLayout("insets 35", "[]20[]", "[]30[]30[]"));

        JLabel lblTitle = new JLabel("Transit Pass Management System v1.0 >");
        lblTitle.setFont(new Font(Font.MONOSPACED, Font.BOLD, 24));
        scanPanel.add(lblTitle, "span 2, wrap");

        JLabel lblQuestion = new JLabel("Scan Current Pass?");
        lblQuestion.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
        scanPanel.add(lblQuestion);

        lblScanPassName = new JLabel("");
        lblScanPassName.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
        scanPanel.add(lblScanPassName, "wrap");

        btnScanOk = new JButton("Ok");
        btnScanCancel = new JButton("Cancel");

        scanPanel.add(btnScanOk, "split 2, gapleft 130");
        scanPanel.add(btnScanCancel);

        mainContainer.add(scanPanel, "SCAN");
    }

    private void buildTopupPanel() {
        topupPanel = new JPanel(new MigLayout("insets 40", "[]10[]", "[]40[]30[]"));

        JLabel lblTitle = new JLabel("Transit Pass Management System v1.0 >");
        lblTitle.setFont(new Font(Font.MONOSPACED, Font.BOLD, 24));
        topupPanel.add(lblTitle, "span 2, wrap");

        JLabel lblPrompt = new JLabel("Enter Amount to top-up: $");
        lblPrompt.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        topupPanel.add(lblPrompt);

        txtTopUpAmount = new JTextField(10);
        topupPanel.add(txtTopUpAmount, "wrap");

        btnTopUpOk = new JButton("Ok");
        btnTopUpCancel = new JButton("Cancel");

        topupPanel.add(btnTopUpOk, "split 2, gapleft 150");
        topupPanel.add(btnTopUpCancel);

        mainContainer.add(topupPanel, "TOPUP");
    }

    private void buildSelectPanel() {
        selectPanel = new JPanel(new MigLayout("insets 35", "[]20[grow]", "[]30[grow]30[]"));

        JLabel lblTitle = new JLabel("Transit Pass Management System v1.0 >");
        lblTitle.setFont(new Font(Font.MONOSPACED, Font.BOLD, 24));
        selectPanel.add(lblTitle, "span 2, wrap");

        JLabel lblSelect = new JLabel("Select Pass:");
        lblSelect.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
        selectPanel.add(lblSelect, "top");

        listModel = new DefaultListModel<>();
        lstPasses = new JList<>(listModel);

        JScrollPane scrollPane = new JScrollPane(lstPasses);
        scrollPane.setPreferredSize(new Dimension(380, 270));
        selectPanel.add(scrollPane, "grow, wrap");

        btnSelectOk = new JButton("Ok");
        btnSelectCancel = new JButton("Cancel");

        selectPanel.add(btnSelectOk, "split 2, gapleft 10");
        selectPanel.add(btnSelectCancel);

        mainContainer.add(selectPanel, "SELECT");
    }

    private void buildDeletePanel() {
        deletePanel = new JPanel(new MigLayout("insets 35", "[]20[]", "[]35[]35[]"));

        JLabel lblTitle = new JLabel("Transit Pass Management System v1.0 >");
        lblTitle.setFont(new Font(Font.MONOSPACED, Font.BOLD, 24));
        deletePanel.add(lblTitle, "span 2, wrap");

        JLabel lblQuestion = new JLabel("Delete Current Pass?");
        lblQuestion.setFont(new Font(Font.MONOSPACED, Font.BOLD, 18));
        deletePanel.add(lblQuestion);

        lblDeletePassName = new JLabel("");
        lblDeletePassName.setFont(new Font(Font.MONOSPACED, Font.BOLD, 18));
        deletePanel.add(lblDeletePassName, "wrap");

        btnDeleteOk = new JButton("Ok");
        btnDeleteCancel = new JButton("Cancel");

        deletePanel.add(btnDeleteOk, "split 2, gapleft 250");
        deletePanel.add(btnDeleteCancel);

        mainContainer.add(deletePanel, "DELETE");
    }

    // Screen Display Methods
    

    public void showMainMenu() {
        cardLayout.show(mainContainer, "MAIN");
    }

    public void showTopUpScreen() {
        txtTopUpAmount.setText("");
        cardLayout.show(mainContainer, "TOPUP");
    }

    public void showCreateScreen() {
        cardLayout.show(mainContainer, "CREATE");
    }

    public void showViewScreen(String text) {
        txtViewOutput.setText(text);
        cardLayout.show(mainContainer, "VIEW");
    }

    public void showScanScreen(String passName) {
        lblScanPassName.setText(passName);
        cardLayout.show(mainContainer, "SCAN");
    }

    public void showDeleteScreen(String passName) {
        lblDeletePassName.setText(passName);
        cardLayout.show(mainContainer, "DELETE");
    }

    public void showSelectScreen() {
        cardLayout.show(mainContainer, "SELECT");
    }

    // Dialog Methods
    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Input Error", JOptionPane.ERROR_MESSAGE);
    }

    public void showInfoMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Message", JOptionPane.INFORMATION_MESSAGE);
    }

    // Button listeners

    public void addQuitListener(ActionListener listener) {
        btnQuit.addActionListener(listener);
    }

    public void addCreateListener(ActionListener listener) {
        btnCreate.addActionListener(listener);
    }

    public void addCreatePassListener(ActionListener listener) {
        btnCreatePass.addActionListener(listener);
    }

    public void addCreateBackListener(ActionListener listener) {
        btnBack.addActionListener(listener);
    }

    public void addViewListener(ActionListener listener) {
        btnView.addActionListener(listener);
    }

    public void addViewBackListener(ActionListener listener) {
        btnViewBack.addActionListener(listener);
    }
    
    public void addScanListener(ActionListener listener) {
        btnScan.addActionListener(listener);
    }

    public void addScanOkListener(ActionListener listener) {
        btnScanOk.addActionListener(listener);
    }

    public void addScanCancelListener(ActionListener listener) {
        btnScanCancel.addActionListener(listener);
    }

    public void addTopUpListener(ActionListener listener) {
        btnTopup.addActionListener(listener);
    }

    public void addTopUpOkListener(ActionListener listener) {
        btnTopUpOk.addActionListener(listener);
    }

    public void addTopUpCancelListener(ActionListener listener) {
        btnTopUpCancel.addActionListener(listener);
    }

    public void addSelectListener(ActionListener listener) {
        btnSelect.addActionListener(listener);
    }

    public void addSelectOkListener(ActionListener listener) {
        btnSelectOk.addActionListener(listener);
    }

    public void addSelectCancelListener(ActionListener listener) {
        btnSelectCancel.addActionListener(listener);
    }

    public void addDeleteListener(ActionListener listener) {
        btnDelete.addActionListener(listener);
    }

    public void addDeleteOkListener(ActionListener listener) {
        btnDeleteOk.addActionListener(listener);
    }

    public void addDeleteCancelListener(ActionListener listener) {
        btnDeleteCancel.addActionListener(listener);
    }
    public static void main(String[] args) {
        Model model = new Model();
        View view = new View();
        new Controller(model, view);
        view.setVisible(true);
        
    }
}