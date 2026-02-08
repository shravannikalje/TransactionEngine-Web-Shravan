# 💳 Transaction Engine - Banking System

A full-stack banking transaction management system with a modern web frontend and robust Java backend.

## 📋 Project Structure

```
TransactionEngine-Web/
├── backend/
│   ├── src/
│   │   └── main/
│   │       └── java/
│   │           └── com/
│   │               └── myapp/
│   │                   ├── model/
│   │                   │   └── Transaction.java
│   │                   ├── service/
│   │                   │   └── TransactionService.java
│   │                   └── TransactionEngine.java
│   └── pom.xml
├── index.html
├── styles.css
├── script.js
└── README.md
```

## ✨ Features

### Frontend (HTML/CSS/JavaScript)

- **Modern UI** with gradient backgrounds and responsive design
- **Transaction Management**: Add, view, edit, and delete transactions
- **Real-time Statistics**: Track total transactions, deposits, withdrawals, and amounts
- **Local Storage**: Persist data in browser's local storage
- **Notification System**: Visual feedback for user actions
- **Mobile Responsive**: Works seamlessly on all devices
- **Form Validation**: Client-side validation for data integrity

### Backend (Java)

- **Transaction Model**: Complete POJO with validation
- **Transaction Service**: Business logic layer with comprehensive operations
- **Error Handling**: Proper exception handling and validation
- **Statistics**: Calculate totals, counts by type, and other metrics
- **Main Application**: Sample data and console output

## 🚀 Getting Started

### Prerequisites

- Java 8 or higher
- Maven 3.6 or higher
- Modern web browser

### Building the Backend

1. Navigate to the backend directory:

```bash
cd backend
```

2. Compile and build with Maven:

```bash
mvn clean compile
```

3. Run the application:

```bash
mvn exec:java -Dexec.mainClass="com.myapp.TransactionEngine"
```

Or compile to JAR:

```bash
mvn clean package
java -jar target/TransactionEngine-1.0-SNAPSHOT.jar
```

### Running the Frontend

1. Open `index.html` in your web browser
2. Or use a local server:

```bash
# Python 3
python -m http.server 8000

# Python 2
python -m SimpleHTTPServer 8000

# Node.js (with http-server)
npx http-server
```

3. Navigate to `http://localhost:8000` in your browser

## 📝 How to Use

### Adding a Transaction

1. Fill in the **Add New Transaction** form:
   - **Transaction ID**: Unique identifier (e.g., TXN001)
   - **Amount**: Transaction amount in dollars
   - **Transaction Type**: Choose from Deposit, Withdrawal, Transfer, or Payment

2. Click **"Add Transaction"** button

3. Transaction appears in the list and statistics update automatically

### Managing Transactions

- **View**: All transactions appear in the list with details
- **Delete**: Click the "Delete" button next to any transaction
- **Refresh**: Use the refresh button to reload data
- **Clear All**: Delete all transactions at once (confirmation required)

### Transaction Types

- **Deposit**: Money coming in (positive)
- **Withdrawal**: Money going out (negative)
- **Transfer**: Moving money between accounts (positive)
- **Payment**: Payment made (positive)

## 🛠️ Java Classes

### Transaction.java

```java
public class Transaction {
    - id: String          // Unique transaction ID
    - amount: double      // Transaction amount
    - type: String        // Type of transaction
    - timestamp: LocalDateTime // When transaction occurred
    - status: String      // Transaction status
}
```

**Methods:**

- Getters/Setters for all properties
- `isValid()`: Validates transaction data
- `toString()`: String representation

### TransactionService.java

```java
public class TransactionService {
    - addTransaction(Transaction)
    - getAllTransactions()
    - getTransactionById(String id)
    - getTransactionsByType(String type)
    - updateTransaction(String id, Transaction)
    - deleteTransaction(String id)
    - getTotalAmount()
    - getCountByType(String type)
    - getTotalCount()
    - clearAll()
}
```

### TransactionEngine.java

Main application class with:

- Sample transaction creation
- Console output and statistics
- Integration with TransactionService

## 🎨 UI Components

### Form Section

- Responsive form with validation
- Input fields for transaction details
- Drop-down for transaction types
- Submit button with hover effects

### Transactions List

- Card-based transaction display
- Shows ID, type, amount, and timestamp
- Color-coded by transaction type
- Delete button for each transaction
- Empty state message when no transactions

### Statistics Dashboard

- Total transaction count
- Deposits count
- Withdrawals count
- Total amount (net of all transactions)

## 💾 Data Storage

- **Frontend**: Transactions stored in browser's localStorage
- **Backend**: In-memory storage in ArrayList

## 🔒 Validation

### Frontend

- Required fields validation
- Amount must be positive
- Duplicate transaction ID check
- Form submission feedback

### Backend

- Null checks
- Amount validation (> 0)
- ID uniqueness validation
- Status validation

## 📊 Statistics Calculation

- **Total Count**: Number of all transactions
- **Deposit Count**: Transactions of type "Deposit"
- **Withdrawal Count**: Transactions of type "Withdrawal"
- **Total Amount**: Sum of all transaction amounts (considering type)

## 🎯 Sample Data

The application loads with sample transactions:

- TXN001: $5000.00 Deposit
- TXN002: $1200.50 Withdrawal
- TXN003: $3500.75 Transfer
- TXN004: $250.00 Payment

## 🐛 Troubleshooting

### Backend won't compile

- Ensure Java 8+ is installed: `java -version`
- Ensure Maven is installed: `mvn -version`
- Clean and rebuild: `mvn clean compile`

### Frontend not loading data

- Check browser console for JavaScript errors (F12)
- Ensure JavaScript is enabled
- Clear browser cache if needed

### Local Storage not working

- Check browser privacy settings
- Try in a private/incognito window
- Use local server instead of file:// protocol

## 📖 API (Future REST Endpoints)

Planned REST API endpoints:

- `GET /api/transactions` - Get all transactions
- `POST /api/transactions` - Add new transaction
- `GET /api/transactions/{id}` - Get transaction by ID
- `PUT /api/transactions/{id}` - Update transaction
- `DELETE /api/transactions/{id}` - Delete transaction
- `GET /api/transactions/type/{type}` - Get by type
- `GET /api/statistics` - Get statistics

## 🔄 Integration Steps

To connect frontend to backend:

1. Add Spring Boot dependency to pom.xml
2. Create REST Controller with @RestController
3. Create @RestMapping endpoints
4. Enable CORS for cross-origin requests
5. Update apiBaseUrl in script.js
6. Test API endpoints

## 📝 Future Enhancements

- [ ] REST API endpoints
- [ ] Database integration (JPA/Hibernate)
- [ ] User authentication
- [ ] Transaction filtering and sorting
- [ ] Export to CSV/PDF
- [ ] Charts and graphs
- [ ] Transaction search
- [ ] Audit logging
- [ ] Unit tests
- [ ] API documentation (Swagger)

## 👨‍💻 Development

### Code Standards

- Follow Java naming conventions
- Use meaningful variable names
- Add comments for complex logic
- Keep functions small and focused
- Handle exceptions appropriately

### Testing

```bash
# Run tests
mvn test

# Generate test reports
mvn site
```

## 📄 License

This project is open source and available under the MIT License.

## 👥 Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Write/update tests
5. Submit a pull request

## 📞 Support

For issues or questions:

- Check the README first
- Review the code comments
- Check the troubleshooting section
- Open an issue in the repository

---

**Happy Banking! 💰**
