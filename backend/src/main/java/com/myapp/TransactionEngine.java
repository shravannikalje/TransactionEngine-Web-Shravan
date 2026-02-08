package com.myapp;

import com.myapp.model.Transaction;
import com.myapp.service.TransactionService;

public class TransactionEngine {
    private static TransactionService transactionService = new TransactionService();

    public static void main(String[] args) {
        System.out.println("===================================");
        System.out.println("[TRANSACTION ENGINE] Started...");
        System.out.println("===================================\n");

        try {
            // Add sample transactions
            addSampleTransactions();

            // Display all transactions
            displayAllTransactions();

            // Display statistics
            displayStatistics();

            System.out.println("\n===================================");
            System.out.println("[SUCCESS] Transaction Engine Running!");
            System.out.println("===================================");

        } catch (Exception e) {
            System.err.println("[ERROR] " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void addSampleTransactions() {
        System.out.println("Adding sample transactions...\n");
        
        try {
            transactionService.addTransaction(new Transaction("TXN001", 5000.00, "Deposit"));
            System.out.println("[OK] Added: TXN001 - $5000.00 (Deposit)");

            transactionService.addTransaction(new Transaction("TXN002", 1200.50, "Withdrawal"));
            System.out.println("[OK] Added: TXN002 - $1200.50 (Withdrawal)");

            transactionService.addTransaction(new Transaction("TXN003", 3500.75, "Transfer"));
            System.out.println("[OK] Added: TXN003 - $3500.75 (Transfer)");

            transactionService.addTransaction(new Transaction("TXN004", 250.00, "Payment"));
            System.out.println("[OK] Added: TXN004 - $250.00 (Payment)");

            System.out.println();
        } catch (IllegalArgumentException e) {
            System.err.println("Error adding transaction: " + e.getMessage());
        }
    }

    private static void displayAllTransactions() {
        System.out.println("[INFO] All Transactions:");
        System.out.println(createDashes(80));

        if (transactionService.getAllTransactions().isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            for (Transaction t : transactionService.getAllTransactions()) {
                System.out.printf("ID: %-10s | Amount: $%-10.2f | Type: %-12s | Status: %s%n",
                        t.getId(), t.getAmount(), t.getType(), t.getStatus());
            }
        }
        System.out.println(createDashes(80) + "\n");
    }

    private static void displayStatistics() {
        System.out.println("[INFO] Transaction Statistics:");
        System.out.println(createDashes(80));
        System.out.printf("Total Transactions: %d%n", transactionService.getTotalCount());
        System.out.printf("Total Amount: $%.2f%n", transactionService.getTotalAmount());
        System.out.printf("Deposits: %d%n", transactionService.getCountByType("Deposit"));
        System.out.printf("Withdrawals: %d%n", transactionService.getCountByType("Withdrawal"));
        System.out.printf("Transfers: %d%n", transactionService.getCountByType("Transfer"));
        System.out.printf("Payments: %d%n", transactionService.getCountByType("Payment"));
        System.out.println(createDashes(80));
    }

    // Helper method for Java 8 compatibility
    private static String createDashes(int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append("-");
        }
        return sb.toString();
    }

    // Public methods for external use
    public static TransactionService getTransactionService() {
        return transactionService;
    }
}
