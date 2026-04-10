import java.util.ArrayList;

public class Model {
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