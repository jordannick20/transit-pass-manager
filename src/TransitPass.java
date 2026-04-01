import javax.swing.JFrame;
import java.util.ArrayList;

public class TransitPass extends JFrame {
    private String holderName;
    private double balance;
    private ArrayList<Transaction> transactions;


    public TrasitPass(String holderName, double initialTopUp) {
        this.holderName = holderName;
        this.balance = initialTopUp;
        this.transactions = new ArrayList<>();
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
    
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
    }
}
