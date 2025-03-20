package academy.javapro;

/**
 * Main class demonstrating the banking system functionality.
 * Creates and operates on different types of accounts using polymorphism.
 */
public class BankingSystem {

    public static void main(String[] args) {
        System.out.println("======= Banking System Demonstration =======\n");

        
        SavingsAccount savings = new SavingsAccount("SA001", "John Doe", 1000.0, 2.5);
        CheckingAccount checking = new CheckingAccount("CA001", "Jane Smith", 2000.0, 500.0);

        Account[] accounts = { savings, checking };

        // Display initial information for all accounts
        System.out.println("--- Initial Account Information ---");
        for (Account account : accounts) {
            account.displayInfo();
            System.out.println();
        }

        // Demonstrate account operations
        System.out.println("--- Performing Account Operations ---");

        // Test deposit operations
        System.out.println("\n1. Testing deposits:");
        savings.deposit(500.0);
        checking.deposit(300.0);

        // Test withdrawal operations
        System.out.println("\n2. Testing withdrawals:");
        if (!savings.withdraw(1300.0)) {
            System.out.println("Cannot withdraw $200.0. Minimum balance of $100.0 must be maintained.");
        }
        if (savings.withdraw(200.0)) {
            System.out.println("Withdrew $2500.00 from checking account");
            System.out.println("Transaction fee: $1.50");
        }
        if (checking.withdraw(2500.0)) {
            System.out.println("Checking Account withdrawal of 2500.0 succeeded and account is in overdraft.");
        }

        // Test account-specific operations
        System.out.println("\n3. Testing account-specific operations:");

        // Savings account - apply interest
        System.out.println("\nSavings Account - Applying interest:");
        savings.applyInterest();

        // Checking account - modify overdraft limit
        System.out.println("\nChecking Account - Modifying overdraft limit:");
        checking.setOverdraftLimit(1000.0);

        // Display updated information for all accounts
        System.out.println("\n--- Updated Account Information ---");
        for (Account account : accounts) {
            account.displayInfo();
            System.out.println();
        }

        // Display transaction history
        System.out.println("--- Transaction History ---");
        System.out.println("\nSavings Account Transactions:");
        for (String transaction : savings.getTransactionHistory()) {
            System.out.println(transaction);
        }
        System.out.println("\nChecking Account Transactions:");
        for (String transaction : checking.getTransactionHistory()) {
            System.out.println(transaction);
        }

        System.out.println("\n======= End of Banking System Demonstration =======");
    }
}
