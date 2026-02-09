package com.myapp.service;

import com.myapp.model.Transaction;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransactionService {
    private List<Transaction> transactions = new ArrayList<>();

    // Add a new transaction
    public Transaction addTransaction(Transaction transaction) throws IllegalArgumentException {
        if (transaction == null) {
            throw new IllegalArgumentException("Transaction cannot be null");
        }
        if (!transaction.isValid()) {
            throw new IllegalArgumentException("Transaction is invalid");
        }
        if (transactions.stream().anyMatch(t -> t.getId().equals(transaction.getId()))) {
            throw new IllegalArgumentException("Transaction with ID " + transaction.getId() + " already exists");
        }
        transactions.add(transaction);
        return transaction;
    }

    // Get all transactions
    public List<Transaction> getAllTransactions() {
        return new ArrayList<>(transactions);
    }

    // Get transaction by ID
    public Optional<Transaction> getTransactionById(String id) {
        return transactions.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst();
    }

    // Get transactions by type
    public List<Transaction> getTransactionsByType(String type) {
        return transactions.stream()
                .filter(t -> t.getType().equalsIgnoreCase(type))
                .collect(Collectors.toList());
    }

    // Update transaction
    public Transaction updateTransaction(String id, Transaction updatedTransaction) throws IllegalArgumentException {
        Optional<Transaction> existing = getTransactionById(id);
        if (!existing.isPresent()) {
            throw new IllegalArgumentException("Transaction with ID " + id + " not found");
        }
        Transaction transaction = existing.get();
        if (updatedTransaction.getAmount() > 0) {
            transaction.setAmount(updatedTransaction.getAmount());
        }
        if (updatedTransaction.getType() != null) {
            transaction.setType(updatedTransaction.getType());
        }
        if (updatedTransaction.getStatus() != null) {
            transaction.setStatus(updatedTransaction.getStatus());
        }
        return transaction;
    }

    // Delete transaction
    public boolean deleteTransaction(String id) {
        return transactions.removeIf(t -> t.getId().equals(id));
    }

    // Get total amount
    public double getTotalAmount() {
        return transactions.stream()
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    // Get count by type
    public long getCountByType(String type) {
        return transactions.stream()
                .filter(t -> t.getType().equalsIgnoreCase(type))
                .count();
    }

    // Clear all transactions
    public void clearAll() {
        transactions.clear();
    }

    // Get total transaction count
    public int getTotalCount() {
        return transactions.size();
    }
}
