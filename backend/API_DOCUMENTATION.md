# Transaction Engine REST API

## Overview
RESTful API for managing banking transactions built with Spring Boot 3.2.2 and Java 21 LTS.

## Base URL
- **Production**: `https://transactionengine-shravan.railway.app/api`
- **Local Development**: `http://localhost:8080/api`

## API Endpoints

### Transactions CRUD Operations

#### Get All Transactions
```
GET /api/transactions
```
Returns a list of all transactions.

**Response (200 OK):**
```json
[
  {
    "id": "TXN001",
    "amount": 5000.00,
    "type": "Deposit",
    "timestamp": "2026-02-09T12:00:00",
    "status": "COMPLETED"
  }
]
```

#### Get Transaction by ID
```
GET /api/transactions/{id}
```
Get a specific transaction by its ID.

**Response (200 OK):**
```json
{
  "id": "TXN001",
  "amount": 5000.00,
  "type": "Deposit",
  "timestamp": "2026-02-09T12:00:00",
  "status": "COMPLETED"
}
```

#### Create New Transaction
```
POST /api/transactions
Content-Type: application/json
```

**Request Body:**
```json
{
  "id": "TXN001",
  "amount": 5000.00,
  "type": "Deposit"
}
```

**Response (201 Created):**
```json
{
  "id": "TXN001",
  "amount": 5000.00,
  "type": "Deposit",
  "timestamp": "2026-02-09T12:00:00",
  "status": "COMPLETED"
}
```

#### Update Transaction
```
PUT /api/transactions/{id}
Content-Type: application/json
```

**Request Body:**
```json
{
  "amount": 6000.00,
  "type": "Deposit",
  "status": "COMPLETED"
}
```

**Response (200 OK):**
```json
{
  "id": "TXN001",
  "amount": 6000.00,
  "type": "Deposit",
  "timestamp": "2026-02-09T12:00:00",
  "status": "COMPLETED"
}
```

#### Delete Transaction
```
DELETE /api/transactions/{id}
```

**Response (204 No Content)**

#### Get Transactions by Type
```
GET /api/transactions/type/{type}
```

**Response (200 OK):**
```json
[
  {
    "id": "TXN001",
    "amount": 5000.00,
    "type": "Deposit",
    "timestamp": "2026-02-09T12:00:00",
    "status": "COMPLETED"
  }
]
```

### Statistics Endpoints

#### Get Statistics
```
GET /api/transactions/stats/all
```

**Response (200 OK):**
```json
{
  "totalCount": 4,
  "totalAmount": 8551.25,
  "depositCount": 1,
  "withdrawalCount": 1,
  "transferCount": 1,
  "paymentCount": 1
}
```

### Management Endpoints

#### Clear All Transactions
```
DELETE /api/transactions
```

**Response (204 No Content)**

#### Health Check
```
GET /api/transactions/health
```

**Response (200 OK):**
```json
{
  "status": "UP",
  "service": "Transaction Engine API",
  "version": "1.0.0"
}
```

## Transaction Types
- `Deposit` - Money coming in
- `Withdrawal` - Money going out
- `Transfer` - Transfer between accounts
- `Payment` - Payment made

## CORS Support
All endpoints support Cross-Origin Resource Sharing (CORS) for frontend integration.

## Error Responses

### 400 Bad Request
```json
{
  "message": "Invalid transaction data"
}
```

### 404 Not Found
```json
{
  "message": "Transaction not found"
}
```

### 500 Internal Server Error
```json
{
  "message": "Internal server error"
}
```

## Testing with cURL

### Add a transaction
```bash
curl -X POST https://transactionengine-shravan.railway.app/api/transactions \
  -H "Content-Type: application/json" \
  -d '{
    "id": "TXN005",
    "amount": 1500.00,
    "type": "Deposit"
  }'
```

### Get all transactions
```bash
curl https://transactionengine-shravan.railway.app/api/transactions
```

### Get statistics
```bash
curl https://transactionengine-shravan.railway.app/api/transactions/stats/all
```

### Delete a transaction
```bash
curl -X DELETE https://transactionengine-shravan.railway.app/api/transactions/TXN001
```

## Technology Stack
- **Framework**: Spring Boot 3.2.2
- **Language**: Java 21 LTS
- **Build Tool**: Maven 3.9.4
- **Server**: Embedded Tomcat
- **Logging**: SLF4J + Logback
- **Deployment**: Railway.app with Docker

## Development

### Prerequisites
- Java 21 or higher
- Maven 3.6 or higher

### Building Locally
```bash
cd backend
mvn clean package
mvn spring-boot:run
```

### Running JAR
```bash
java -jar target/TransactionEngine-1.0-SNAPSHOT.jar
```

The API will be available at `http://localhost:8080/api`

## Deployment
Deployed automatically on Railway.app using Docker and the Dockerfile configuration.
