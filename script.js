// Transaction Engine Frontend Application
class TransactionManager {
    constructor() {
        this.transactions = [];
        this.apiBaseUrl = 'http://localhost:8080/api'; // Update with your backend URL
        this.initializeEventListeners();
        this.loadTransactions();
    }

    initializeEventListeners() {
        const form = document.getElementById('transactionForm');
        const refreshBtn = document.getElementById('refreshBtn');
        const clearAllBtn = document.getElementById('clearAllBtn');

        if (form) {
            form.addEventListener('submit', (e) => this.handleAddTransaction(e));
        }
        if (refreshBtn) {
            refreshBtn.addEventListener('click', () => this.loadTransactions());
        }
        if (clearAllBtn) {
            clearAllBtn.addEventListener('click', () => this.handleClearAll());
        }
    }

    // Add new transaction
    handleAddTransaction(e) {
        e.preventDefault();

        const transactionId = document.getElementById('transactionId').value.trim();
        const amount = parseFloat(document.getElementById('amount').value);
        const type = document.getElementById('type').value;

        // Validation
        if (!transactionId || !amount || !type) {
            this.showNotification('Please fill in all fields', 'error');
            return;
        }

        if (amount <= 0) {
            this.showNotification('Amount must be greater than 0', 'error');
            return;
        }

        // Check for duplicate ID
        if (this.transactions.some(t => t.id === transactionId)) {
            this.showNotification('Transaction ID already exists', 'error');
            return;
        }

        const transaction = {
            id: transactionId,
            amount: amount,
            type: type,
            timestamp: new Date().toISOString()
        };

        this.transactions.push(transaction);
        this.saveToLocalStorage();
        this.displayTransactions();
        this.updateStatistics();
        this.showNotification(`Transaction ${transactionId} added successfully!`, 'success');

        // Reset form
        document.getElementById('transactionForm').reset();
    }

    // Load transactions from storage
    loadTransactions() {
        const stored = localStorage.getItem('transactions');
        if (stored) {
            this.transactions = JSON.parse(stored);
        } else {
            this.transactions = [];
        }
        this.displayTransactions();
        this.updateStatistics();
    }

    // Display all transactions
    displayTransactions() {
        const transactionsList = document.getElementById('transactionsList');
        if (!transactionsList) return;

        if (this.transactions.length === 0) {
            transactionsList.innerHTML = '<p class="empty-state">No transactions yet. Add one to get started!</p>';
            return;
        }

        transactionsList.innerHTML = this.transactions
            .sort((a, b) => new Date(b.timestamp) - new Date(a.timestamp))
            .map((transaction, index) => this.createTransactionElement(transaction, index))
            .join('');
    }

    // Create transaction element HTML
    createTransactionElement(transaction, index) {
        const amount = parseFloat(transaction.amount);
        const amountClass = this.isPositiveTransaction(transaction.type) 
            ? 'amount-positive' 
            : 'amount-negative';
        const amountSymbol = this.isPositiveTransaction(transaction.type) ? '+' : '-';
        const typeClass = `type-${transaction.type.toLowerCase()}`;
        const date = new Date(transaction.timestamp).toLocaleString();

        return `
            <div class="transaction-item">
                <div class="transaction-info">
                    <div class="transaction-id">${this.escapeHtml(transaction.id)}</div>
                    <div class="transaction-details">
                        <span class="transaction-type ${typeClass}">${transaction.type}</span>
                        <span>📅 ${date}</span>
                    </div>
                </div>
                <div class="transaction-amount ${amountClass}">
                    ${amountSymbol}$${amount.toFixed(2)}
                </div>
                <button class="btn btn-small btn-danger" onclick="transactionManager.deleteTransaction(${index})">
                    Delete
                </button>
            </div>
        `;
    }

    // Delete transaction
    deleteTransaction(index) {
        const transaction = this.transactions[index];
        if (confirm(`Are you sure you want to delete transaction ${transaction.id}?`)) {
            this.transactions.splice(index, 1);
            this.saveToLocalStorage();
            this.displayTransactions();
            this.updateStatistics();
            this.showNotification('Transaction deleted successfully', 'success');
        }
    }

    // Clear all transactions
    handleClearAll() {
        if (this.transactions.length === 0) {
            this.showNotification('No transactions to clear', 'warning');
            return;
        }

        if (confirm('Are you sure you want to clear ALL transactions? This action cannot be undone.')) {
            this.transactions = [];
            this.saveToLocalStorage();
            this.displayTransactions();
            this.updateStatistics();
            this.showNotification('All transactions cleared', 'success');
        }
    }

    // Update statistics
    updateStatistics() {
        const totalCount = this.transactions.length;
        const depositCount = this.transactions.filter(t => t.type === 'Deposit').length;
        const withdrawalCount = this.transactions.filter(t => t.type === 'Withdrawal').length;
        
        let totalAmount = 0;
        this.transactions.forEach(t => {
            const amount = parseFloat(t.amount);
            if (this.isPositiveTransaction(t.type)) {
                totalAmount += amount;
            } else {
                totalAmount -= amount;
            }
        });

        document.getElementById('totalCount').textContent = totalCount;
        document.getElementById('depositCount').textContent = depositCount;
        document.getElementById('withdrawalCount').textContent = withdrawalCount;
        document.getElementById('totalAmount').textContent = `$${totalAmount.toFixed(2)}`;
    }

    // Check if transaction is positive
    isPositiveTransaction(type) {
        return type === 'Deposit' || type === 'Transfer' || type === 'Payment';
    }

    // Save to local storage
    saveToLocalStorage() {
        localStorage.setItem('transactions', JSON.stringify(this.transactions));
    }

    // Show notification
    showNotification(message, type) {
        const notification = document.createElement('div');
        notification.className = `notification ${type}`;
        notification.textContent = message;

        const container = document.querySelector('main');
        if (container) {
            container.insertBefore(notification, container.firstChild);
            setTimeout(() => {
                notification.remove();
            }, 3000);
        }
    }

    // Escape HTML to prevent XSS
    escapeHtml(text) {
        const map = {
            '&': '&amp;',
            '<': '&lt;',
            '>': '&gt;',
            '"': '&quot;',
            "'": '&#039;'
        };
        return text.replace(/[&<>"']/g, m => map[m]);
    }
}

// Initialize the application when DOM is loaded
document.addEventListener('DOMContentLoaded', () => {
    window.transactionManager = new TransactionManager();
    console.log('Transaction Engine loaded successfully!');
});

// Add some sample transactions for testing
function addSampleTransactions() {
    const samples = [
        { id: 'TXN001', amount: 5000, type: 'Deposit', timestamp: new Date(Date.now() - 3600000).toISOString() },
        { id: 'TXN002', amount: 1200, type: 'Withdrawal', timestamp: new Date(Date.now() - 1800000).toISOString() },
        { id: 'TXN003', amount: 3500, type: 'Transfer', timestamp: new Date(Date.now() - 900000).toISOString() }
    ];

    samples.forEach(sample => {
        if (!window.transactionManager.transactions.some(t => t.id === sample.id)) {
            window.transactionManager.transactions.push(sample);
        }
    });

    window.transactionManager.saveToLocalStorage();
    window.transactionManager.displayTransactions();
    window.transactionManager.updateStatistics();
    console.log('Sample transactions added!');
}
