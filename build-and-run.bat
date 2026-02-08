@echo off
REM Transaction Engine Build and Run Script for Windows

echo.
echo ==========================================
echo.  ^(banker^) Transaction Engine - Build Script
echo ==========================================
echo.

REM Check if Maven is installed
where mvn >nul 2>nul
if %errorlevel% neq 0 (
    echo.
    echo [ERROR] Maven is not installed or not in PATH
    echo Please install Maven to continue
    echo.
    pause
    exit /b 1
)

REM Check if Java is installed
where java >nul 2>nul
if %errorlevel% neq 0 (
    echo.
    echo [ERROR] Java is not installed or not in PATH
    echo Please install Java 8 or higher to continue
    echo.
    pause
    exit /b 1
)

echo [OK] Java Version:
java -version
echo.

echo [OK] Maven Version:
mvn -version
echo.

REM Navigate to backend directory
cd backend

echo [INFO] Building Transaction Engine...
echo.

REM Clean and compile
call mvn clean compile

if %errorlevel% equ 0 (
    echo.
    echo [SUCCESS] Build successful!
    echo.
    
    REM Package the application
    echo [INFO] Packaging application...
    call mvn package -DskipTests
    
    if %errorlevel% equ 0 (
        echo.
        echo [SUCCESS] Package created successfully!
        echo.
        
        REM Run the application
        echo [INFO] Running Transaction Engine...
        echo.
        java -jar target\TransactionEngine-1.0-SNAPSHOT.jar
    ) else (
        echo.
        echo [ERROR] Packaging failed!
        echo.
        pause
        exit /b 1
    )
) else (
    echo.
    echo [ERROR] Build failed!
    echo.
    pause
    exit /b 1
)

pause
