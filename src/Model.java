import java.util.ArrayList;

public class Model {
        // Stores all created transit passes array list of TransitPass objects
    private ArrayList<TransitPass> passes;

    // Stores the currently selected pass
    private TransitPass selectedPass;

    // Constructor
    public Model() {
        passes = new ArrayList<>();
    }

    // Return all passes
    public ArrayList<TransitPass> getPasses() {
        return passes;
    }

    // Return the currently selected pass
    public TransitPass getSelectedPass() {
        return selectedPass;
    }

    // Set the currently selected pass
    public void setSelectedPass(TransitPass myselectedPass) {
        selectedPass = myselectedPass;
    }

    // Create a new pass and automatically select it
    public boolean createPass(String holderName, double balance, String passType) {
        TransitPass newPass;

        if (passType.equals("Green Pass")) {
            newPass = new GreenPass(holderName, balance);
        } else {
            newPass = new StandardPass(holderName, balance);
        }

        passes.add(newPass);
        selectedPass = newPass;

        return true;
    }

    // Return a description of the selected pass in the view
    public String getSelectedPassDetails() {
        StringBuilder output = new StringBuilder();
        
        output.append(String.format("Pass Holder: %s\nPass type: %s\nCurrent balance: $ %.2f\n%s",selectedPass.getHolderName(),selectedPass.getPassType(),selectedPass.getBalance(),selectedPass.addCarbonCredits()));

        ArrayList<Transaction> transactions = selectedPass.getTransactions();

        for (int i = 0; i < transactions.size(); i++) {
                Transaction transaction = transactions.get(i);
                output.append("Type: " + transaction.getType() + "\n");
                output.append("Amount: $" + transaction.getAmount() + "\n");
        }
        return output.toString();
    }

    // Add money to the selected pass
    public boolean topUpSelectedPass(double amount) {
        if (selectedPass == null) {
            return false;
        }
        // <= Means no zeros and only positive numbers allowed
        if (amount <= 0) {
            return false;
        }

        selectedPass.topUp(amount);
        return true;
    }

    // Scan the selected pass for a trip
    public boolean scanSelectedPass() {
        if (selectedPass == null) {
            return false;
        }

        return selectedPass.scanTrip();
    }

    // Return the selected pass holders name
    public String getSelectedPassName() {
        return selectedPass.getHolderName();
    }

    // Delete the currently selected pass
    public boolean deleteSelectedPass() {
        if (selectedPass == null) {
            return false;
        }

        passes.remove(selectedPass);
        selectedPass = null;
        return true;
    }

    public boolean hasPasses() {
        return (!passes.isEmpty());
    }

    // the nested classes 
    public abstract class TransitPass {
        private String holderName;
        private double balance;
        private ArrayList<Transaction> transactions;

        public TransitPass(String myholderName, double mybalance) {
            holderName = myholderName;
            balance = mybalance;
            transactions = new ArrayList<>();
        }

        public String getHolderName() {
            return holderName;
        }

        public double getBalance() {
            return balance;
        }

        public String addCarbonCredits() {
            return "";
        }
  
        public ArrayList<Transaction> getTransactions() {
            return transactions;
        }

        public void addTransaction(Transaction transaction) {
            transactions.add(transaction);
        }

        public void topUp(double amount) {
            if (amount > 0) {
                balance += amount;
                addTransaction(new Transaction("Top Up", amount));
            }
        }

        public boolean scanTrip() {
            double fee = getTripFee();

            if (balance >= fee) {
                balance = balance - fee;
                addTransaction(new Transaction("Trip Scan", -fee));
                return true;
            }

            return false;
        }

        public abstract double getTripFee();

        public abstract String getPassType();
    }

    public class StandardPass extends TransitPass {
        public StandardPass(String holderName, double balance) {
            // super calls parent constructor
            super(holderName, balance);
        }

        @Override
        public double getTripFee() {
            return 2.00;
        }

        @Override
        public String getPassType() {
            return "Standard Pass";
        }
    }

    public class GreenPass extends TransitPass {
        private int carbonCredits;

        public GreenPass(String holderName, double balance) {
            // super calls parent constructor
            super(holderName, balance);
            // activation bonus and credits from initial balance
            carbonCredits = 10; 
            // credits from initial balance
            carbonCredits = carbonCredits + (int) (balance / 10); 
        }

        public int getCarbonCredits() {
            return carbonCredits;
        }

        @Override
        public void topUp(double amount) {
            if (amount > 0) {
                super.topUp(amount);
                carbonCredits = carbonCredits + (int) (amount / 10);
            }
        }

        @Override
        public double getTripFee() {
            return 2.10;
        }

        @Override
        public String getPassType() {
            return "Green Pass";
        }
        @Override
        public String addCarbonCredits() {
            return "\n" + "Carbon Credits: " + carbonCredits + "\n";
        }
    }

    public static class Transaction {
        private String type;
        private double amount;

        public Transaction(String mytype, double myamount) {
            type = mytype;
            amount = myamount;
        }

        public String getType() {
            return type;
        }

        public double getAmount() {
            return amount;
        }
    }
}