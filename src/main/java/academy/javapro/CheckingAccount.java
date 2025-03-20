package academy.javapro;

/**
 * CheckingAccount class extending the abstract Account class.
 * Features overdraft protection and transaction fees.
 */
public class CheckingAccount extends Account {
    private double overdraftLimit;
    private static final double TRANSACTION_FEE = 1.5; // Fee per withdrawal

    /**
     * Constructor for creating a new checking account.
     *
     * @param accountNumber The account number
     * @param customerName The name of the account holder
     * @param initialBalance The initial balance
     * @param overdraftLimit The maximum allowed overdraft
     */
    public CheckingAccount(String accountNumber, String customerName, double initialBalance, double overdraftLimit) {
        super(accountNumber, customerName, initialBalance); // Call to the parent constructor
        this.overdraftLimit = overdraftLimit;
    }

    /**
     * Getter for overdraft limit.
     *
     * @return The overdraft limit
     */
    public double getOverdraftLimit() {
    	 return overdraftLimit;
    }

    /**
     * Sets a new overdraft limit.
     *
     * @param overdraftLimit The new overdraft limit
     */
    public void setOverdraftLimit(double newLimit) {
        overdraftLimit = newLimit;
        System.out.println("Overdraft limit updated to $" + newLimit);
    }

    /**
     * Overrides the withdraw method with checking account-specific rules.
     * Implements overdraft protection and applies transaction fees.
     * @return 
     */
    @Override
    public boolean withdraw(double amount) {
        double newBalance = getBalance() - amount - TRANSACTION_FEE;
        if (newBalance >= -overdraftLimit) {
            setBalance(newBalance);
            getTransactionHistory().add("WITHDRAWAL | $" + amount + " | Balance: $" + getBalance());
            getTransactionHistory().add("FEE | $" + TRANSACTION_FEE + " | Balance: $" + getBalance());
            System.out.println("Withdrew $" + amount + " from checking account");
            System.out.println("Transaction fee: $" + TRANSACTION_FEE);
            if (getBalance() < 0) {
                System.out.println("Account is in overdraft. Current balance: $" + getBalance());
            }
            return true;
        } else {
            System.out.println("Withdrawal failed: Overdraft limit exceeded.");
            return false;
        }
    }


    /**
     * Overrides the displayInfo method to include checking account-specific information.
     */
    @Override
    public void displayInfo() {
        super.displayInfo(); // Call to the parent method
        System.out.println("Account Type: Checking Account");
        System.out.println("Overdraft Limit: $" + String.format("%.2f", overdraftLimit));
        System.out.println("Transaction Fee: $" + String.format("%.2f", TRANSACTION_FEE));
    }
}
