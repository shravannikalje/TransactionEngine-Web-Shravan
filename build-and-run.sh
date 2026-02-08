#!/bin/bash
# Transaction Engine Build and Run Script

echo "=========================================="
echo "💳 Transaction Engine - Build Script"
echo "=========================================="
echo ""

# Colors for output
GREEN='\033[0;32m'
BLUE='\033[0;34m'
RED='\033[0;31m'
NC='\033[0m' # No Color

# Check if Maven is installed
if ! command -v mvn &> /dev/null
then
    echo -e "${RED}❌ Maven is not installed${NC}"
    echo "Please install Maven to continue"
    exit 1
fi

# Check if Java is installed
if ! command -v java &> /dev/null
then
    echo -e "${RED}❌ Java is not installed${NC}"
    echo "Please install Java 8 or higher to continue"
    exit 1
fi

echo -e "${BLUE}✓ Java Version:${NC}"
java -version
echo ""

echo -e "${BLUE}✓ Maven Version:${NC}"
mvn -version
echo ""

# Navigate to backend directory
cd backend

echo -e "${BLUE}🔨 Building Transaction Engine...${NC}"
echo ""

# Clean and compile
mvn clean compile

if [ $? -eq 0 ]; then
    echo -e "${GREEN}✅ Build successful!${NC}"
    echo ""
    
    # Package the application
    echo -e "${BLUE}📦 Packaging application...${NC}"
    mvn package -DskipTests
    
    if [ $? -eq 0 ]; then
        echo -e "${GREEN}✅ Package created successfully!${NC}"
        echo ""
        
        # Run the application
        echo -e "${BLUE}🚀 Running Transaction Engine...${NC}"
        echo ""
        java -jar target/TransactionEngine-1.0-SNAPSHOT.jar
    else
        echo -e "${RED}❌ Packaging failed!${NC}"
        exit 1
    fi
else
    echo -e "${RED}❌ Build failed!${NC}"
    exit 1
fi
