package com.myapp.model;

import java.time.LocalDateTime;

public class Transaction {
    private String id;
    private double amount;
    private String type;
    private LocalDateTime timestamp;
    private String status;

    // Default constructor
    public Transaction() {
        this.timestamp = LocalDateTime.now();
        this.status = "COMPLETED";
    }

    // Parameterized constructor
    public Transaction(String id, double amount, String type) {
        this.id = id;
        this.amount = amount;
        this.type = type;
        this.timestamp = LocalDateTime.now();
        this.status = "COMPLETED";
    }

    public Transaction(String id, double amount, String type, LocalDateTime timestamp, String status) {
        this.id = id;
        this.amount = amount;
        this.type = type;
        this.timestamp = timestamp;
        this.status = status;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id='" + id + '\'' +
                ", amount=" + amount +
                ", type='" + type + '\'' +
                ", timestamp=" + timestamp +
                ", status='" + status + '\'' +
                '}';
    }

    // Validate transaction
    public boolean isValid() {
        return id != null && !id.trim().isEmpty() &&
               amount > 0 &&
               type != null && !type.trim().isEmpty() &&
               timestamp != null &&
               status != null && !status.trim().isEmpty();
    }
}
