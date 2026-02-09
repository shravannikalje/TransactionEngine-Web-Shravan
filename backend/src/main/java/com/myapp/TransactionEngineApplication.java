package com.myapp;

import com.myapp.model.Transaction;
import com.myapp.service.TransactionService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TransactionEngineApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransactionEngineApplication.class, args);
    }

    /**
     * Initialize sample transactions on startup
     */
    @Bean
    public CommandLineRunner initializeData(TransactionService transactionService) {
        return args -> {
            try {
                System.out.println("===================================");
                System.out.println("[TRANSACTION ENGINE] Initializing sample data...");
                System.out.println("===================================\n");

                transactionService.addTransaction(new Transaction("TXN001", 5000.00, "Deposit"));
                System.out.println("[OK] Added: TXN001 - $5000.00 (Deposit)");

                transactionService.addTransaction(new Transaction("TXN002", 1200.50, "Withdrawal"));
                System.out.println("[OK] Added: TXN002 - $1200.50 (Withdrawal)");

                transactionService.addTransaction(new Transaction("TXN003", 3500.75, "Transfer"));
                System.out.println("[OK] Added: TXN003 - $3500.75 (Transfer)");

                transactionService.addTransaction(new Transaction("TXN004", 250.00, "Payment"));
                System.out.println("[OK] Added: TXN004 - $250.00 (Payment)");

                System.out.println("\n===================================");
                System.out.println("[SUCCESS] Transaction Engine Running!");
                System.out.println("===================================");
                System.out.println("[INFO] API available at: http://localhost:8080/api/transactions");
                System.out.println("[INFO] Health check: http://localhost:8080/api/transactions/health");
                System.out.println("===================================\n");

            } catch (Exception e) {
                System.err.println("[ERROR] " + e.getMessage());
                e.printStackTrace();
            }
        };
    }
}
