import java.awt.event.*;
public class Controller {
    // References to the model and view
    private Model model;
    private View view;

    // Constructor
    public Controller(Model mymodel, View myview) {
        model = mymodel;
        view = myview;

        lambdaExpressions();
    }

    // Connect all button actions to controller methods
    private void lambdaExpressions() {
        // Delete panel events
        view.addDeleteListener((ActionEvent e) -> onOpenDeleteScreen(e));
        view.addDeleteOkListener((ActionEvent e) -> onConfirmDelete(e));
        view.addDeleteCancelListener((ActionEvent e) -> onCancelDelete(e));

        // Select panel events
        view.addSelectListener((ActionEvent e) -> onOpenSelectScreen(e));
        view.addSelectOkListener((ActionEvent e) -> onConfirmSelectPass(e));
        view.addSelectCancelListener((ActionEvent e) -> onCancelSelectPass(e));

        // Create panel events
        view.addCreateListener((ActionEvent e) -> onOpenCreateScreen(e));
        view.addCreateBackListener((ActionEvent e) -> onBackFromCreate(e));
        view.addCreatePassListener((ActionEvent e) -> onCreatePass(e));

        // View panel events
        view.addViewListener((ActionEvent e) -> onViewPass(e));
        view.addViewBackListener((ActionEvent e) -> onBackFromView(e));

        // Scan panel events
        view.addScanListener((ActionEvent e) -> onOpenScanScreen(e));
        view.addScanOkListener((ActionEvent e) -> onConfirmScan(e));
        view.addScanCancelListener((ActionEvent e) -> onCancelScan(e));

        // Top-up panel events
        view.addTopUpListener((ActionEvent e) -> onOpenTopUpScreen(e));
        view.addTopUpOkListener((ActionEvent e) -> onTopUpPass(e));
        view.addTopUpCancelListener((ActionEvent e) -> onCancelTopUp(e));

        // Quit event
        view.addQuitListener((ActionEvent e) -> onQuit(e));
    }
}
