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

    // Create Screen Methods
    private void onOpenCreateScreen(ActionEvent e) {
        view.showCreateScreen();
    }

    private void onBackFromCreate(ActionEvent e) { 
        view.clearCreateFields();
        view.showMainMenu();
    }

    private void onCreatePass(ActionEvent e) {
        String holderName = view.getHolderNameInput();
        String balanceText = view.getInitialBalanceInput();
        String passType = view.getSelectedPassType();

        if (holderName.isEmpty()) {
            view.showErrorMessage("Holder name is required.");
            return;
        }

        double balance;

        try {
            balance = Double.parseDouble(balanceText);
        } catch (NumberFormatException nfex) {
            view.showErrorMessage("Initial balance must be a valid number.");
            return;
        }

        if (balance < 0) {
            view.showErrorMessage("Initial balance cannot be negative.");
            return;
        }

        model.createPass(holderName, balance, passType);
        //
        view.setMainButtonsEnabled();

        view.showInfoMessage("Pass created successfully.");
        view.clearCreateFields();
        view.showMainMenu();
    }

    // Top-Up Screen Methods
    private void onOpenTopUpScreen(ActionEvent e) {
        view.showTopUpScreen();
    }

    private void onTopUpPass(ActionEvent e) {
        String amountText = view.getTopUpAmountInput();
        double amount;

        try {
            amount = Double.parseDouble(amountText);
        } catch (NumberFormatException nfex) {
            view.showErrorMessage("Top-up amount must be a valid number.");
            return;
        }

        if (amount <= 0) {
            view.showErrorMessage("Top-up amount must be greater than 0.");
            return;
        }

        boolean success = model.topUpSelectedPass(amount);

        if (success) {
            view.showInfoMessage("Top-up successful.");
        }
        view.showMainMenu();
    }

    private void onCancelTopUp(ActionEvent e) {
        view.showMainMenu();
    }

    // Scan Screen Methods
    private void onOpenScanScreen(ActionEvent e) {
        String passName = model.getSelectedPassName();
        view.showScanScreen(passName);
    }

    private void onConfirmScan(ActionEvent e) {
        boolean success = model.scanSelectedPass();

        if (success) {
            view.showInfoMessage("Trip scanned successfully.");
        } else {
            view.showErrorMessage("Insufficient balance.");
        }

        view.showMainMenu();
    }

    private void onCancelScan(ActionEvent e) {
        view.showMainMenu();
    }

    // Select Screen Methods
    private void onOpenSelectScreen(ActionEvent e) {
        view.showSelectScreen(model.getPasses());
    }

    private void onConfirmSelectPass(ActionEvent e) {
        int selectedIndex = view.getSelectedPassIndex();
        model.setSelectedPass(model.getPasses().get(selectedIndex));
        view.showInfoMessage("Pass selected successfully.");
        view.showMainMenu();
    }

    private void onCancelSelectPass(ActionEvent e) {
        view.showMainMenu();
    }

    // Delete Screen Methods
    private void onOpenDeleteScreen(ActionEvent e) {
        String passName = model.getSelectedPassName();
        view.showDeleteScreen(passName);
    }

    private void onConfirmDelete(ActionEvent e) {
        boolean success = model.deleteSelectedPass();

        if (success) {
            view.showInfoMessage("Pass deleted successfully.");
        }
        if (!model.hasPasses()) {
            view.setMainButtonsDisabled();
}
        view.showMainMenu();
    }

    private void onCancelDelete(ActionEvent e) {
        view.showMainMenu();
    }

    // View Screen Methods
    private void onViewPass(ActionEvent e) {
        String output = model.getSelectedPassDetails();
        view.showViewScreen(output);
    }

    private void onBackFromView(ActionEvent e) {
        view.showMainMenu();
    }

    // App Exit
    private void onQuit(ActionEvent e) {
        System.exit(0);
    }
}