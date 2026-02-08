# ⚙️ Configuration & Setup Guide

## System Requirements

### Minimum Requirements

- **OS**: Windows 7+, macOS 10.12+, or Linux (any modern distro)
- **RAM**: 2GB minimum (4GB recommended)
- **Disk Space**: 500MB for Java + Maven + project
- **Browser**: Chrome 90+, Firefox 88+, Safari 14+, Edge 90+

### Required Software

#### Java JDK

- **Version**: 8 or higher
- **Download**: https://www.oracle.com/java/technologies/downloads/
- **Alternative**: OpenJDK at https://openjdk.java.net/
- **Verify**: `java -version`

#### Maven

- **Version**: 3.6.0 or higher
- **Download**: https://maven.apache.org/download.cgi
- **Guide**: https://maven.apache.org/install.html
- **Verify**: `mvn -version`

---

## Installation Steps

### Step 1: Install Java

#### Windows

```
1. Download Java from Oracle website
2. Run the installer
3. Follow the installation wizard
4. Accept license agreement
5. Choose installation folder (default is fine)
6. Add to PATH (important!)
7. Restart computer
8. Verify: Open Command Prompt and type: java -version
```

#### macOS

```
1. Open Terminal
2. Use Homebrew (recommended):
   brew install java
3. Verify: java -version
```

#### Linux

```
# Ubuntu/Debian
sudo apt-get update
sudo apt-get install openjdk-8-jdk

# Fedora/RHEL
sudo yum install java-1.8.0-openjdk

# Verify
java -version
```

### Step 2: Install Maven

#### Windows

```
1. Download Maven from apache.org
2. Extract to a folder (e.g., C:\Maven)
3. Add to Environment Variables:
   - Variable: MAVEN_HOME = C:\Maven\apache-maven-3.8.1
   - Update PATH: C:\Maven\apache-maven-3.8.1\bin
4. Restart Command Prompt
5. Verify: mvn -version
```

#### macOS

```
brew install maven
# Verify
mvn -version
```

#### Linux

```
# Ubuntu/Debian
sudo apt-get install maven

# Fedora/RHEL
sudo yum install maven

# Verify
mvn -version
```

### Step 3: Clone/Extract Project

```
# Navigate to your projects folder
cd C:\Users\YourName\Documents

# Extract the TransactionEngine-Web folder
# Or clone from Git if available
```

---

## Configuration Files

### pom.xml Configuration

Located at: `backend/pom.xml`

```xml
<!-- Main configuration sections -->

<!-- Properties -->
<maven.compiler.source>1.8</maven.compiler.source>
<maven.compiler.target>1.8</maven.compiler.target>

<!-- Dependencies -->
<dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <version>4.13.2</version>
</dependency>

<!-- Plugins -->
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-compiler-plugin</artifactId>
</plugin>
```

### Application Configuration

#### Frontend Configuration (script.js)

```javascript
// Line 4-5: Configure API URL
const apiBaseUrl = "http://localhost:8080/api";

// Modify as needed for your backend server
```

---

## Environment Variables

### Windows

```batch
Set up in System Properties > Environment Variables > New

Variable Name: JAVA_HOME
Variable Value: C:\Program Files\Java\jdk1.8.0_281

Variable Name: MAVEN_HOME
Variable Value: C:\Maven\apache-maven-3.8.1

Variable Name: PATH
Add: %JAVA_HOME%\bin;%MAVEN_HOME%\bin

Then restart Command Prompt
```

### Linux/macOS

```bash
# Edit ~/.bash_profile or ~/.bashrc

export JAVA_HOME=$(dirname $(dirname $(which javac)))
export MAVEN_HOME=/usr/local/maven
export PATH=$JAVA_HOME/bin:$MAVEN_HOME/bin:$PATH

# Save and reload
source ~/.bash_profile
```

---

## Maven Configuration

### settings.xml Location

**Windows**: `C:\Users\[Username]\.m2\settings.xml`  
**Linux/Mac**: `~/.m2/settings.xml`

### Create settings.xml if needed

```xml
<?xml version="1.0" encoding="UTF-8"?>
<settings>
    <localRepository>${user.home}/.m2/repository</localRepository>

    <mirrors>
        <mirror>
            <id>central</id>
            <name>Central Repository</name>
            <url>https://repo.maven.apache.org/maven2</url>
            <mirrorOf>central</mirrorOf>
        </mirror>
    </mirrors>
</settings>
```

---

## Firewall & Port Configuration

### Ports Used

- **8000**: Python HTTP server (frontend)
- **8001-8005**: Alternative HTTP ports
- **8080**: Spring Boot default (future)
- **5432**: PostgreSQL (future)
- **3306**: MySQL (future)

### Windows Firewall

If port access is blocked:

```
1. Open Windows Defender Firewall
2. Click "Allow an app through firewall"
3. Click "Change settings"
4. Click "Allow another app"
5. Browse to Java installation folder
6. Select java.exe or javaw.exe
7. Click Add
8. Click OK
```

### Linux Firewall

```bash
# Check firewall status
sudo ufw status

# Allow port 8000
sudo ufw allow 8000

# Allow port 8080
sudo ufw allow 8080

# Reload firewall
sudo ufw reload
```

---

## Browser Configuration

### LocalStorage Settings

LocalStorage is required for data persistence.

#### Chrome

```
1. Settings > Privacy and security > Site settings
2. Cookies and data stored
3. Allow all sites to store data (or add exceptions)
```

#### Firefox

```
1. Preferences > Privacy & Security
2. Cookies and Site Data
3. Allow sites to save data (or add exceptions)
```

#### Safari

```
1. Preferences > Privacy
2. Cookies and website data: "Always allow"
```

#### Edge

```
1. Settings > Privacy > Cookies and other site data
2. Allow sites to save and read cookie data
```

---

## IDE Configuration (Optional)

### Eclipse

```
1. File > Import > Maven > Existing Maven Projects
2. Select backend folder
3. Click Finish
4. Eclipse will import and build automatically
```

### IntelliJ IDEA

```
1. File > Open
2. Select backend folder
3. Choose "Open as Project"
4. IntelliJ will detect Maven and import
```

### Visual Studio Code

```
1. Install Extension Pack for Java
2. File > Open Folder
3. Select TransactionEngine-Web folder
4. VS Code will detect Maven project
5. Run via terminal: mvn clean compile
```

---

## Build Configuration

### Maven Build Profiles

Add to pom.xml for different environments:

```xml
<profiles>
    <profile>
        <id>dev</id>
        <properties>
            <maven.test.skip>false</maven.test.skip>
        </properties>
    </profile>

    <profile>
        <id>prod</id>
        <properties>
            <maven.test.skip>true</maven.test.skip>
        </properties>
    </profile>
</profiles>
```

Build with profile:

```bash
mvn clean install -Pprod
```

---

## Logging Configuration

### Logback Configuration

Create `backend/src/main/resources/logback.xml`:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<configuration>
    <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>
        </encoder>
    </appender>

    <root level="INFO">
        <appender-ref ref="STDOUT" />
    </root>
</configuration>
```

---

## Network Configuration

### Proxy Settings (if behind corporate proxy)

#### Maven Proxy

Edit `~/.m2/settings.xml`:

```xml
<proxy>
    <id>corporate-proxy</id>
    <active>true</active>
    <protocol>http</protocol>
    <host>proxy.company.com</host>
    <port>8080</port>
    <username>proxyuser</username>
    <password>proxypass</password>
    <nonProxyHosts>localhost|127.0.0.1</nonProxyHosts>
</proxy>
```

#### Git Proxy (if using)

```bash
git config --global http.proxy http://proxy.company.com:8080
git config --global https.proxy https://proxy.company.com:8080
```

---

## Performance Tuning

### JVM Memory Settings

For backend application:

```bash
# Increase heap memory
java -Xmx1024m -Xms512m -jar target/TransactionEngine-1.0-SNAPSHOT.jar

# Xms = Initial memory (512MB)
# Xmx = Maximum memory (1024MB)
```

### Maven Memory

```bash
# Set MAVEN_OPTS environment variable
set MAVEN_OPTS=-Xmx1024m -XX:MaxPermSize=256m
mvn clean install
```

---

## Security Configuration

### Java Security

Update `security.policy`:

```
permission java.io.FilePermission "backend/target/-", "read,write,delete";
permission java.net.SocketPermission "localhost:8000", "connect,listen";
```

---

## Backup & Restore

### Backup Project

```bash
# Create backup
tar -czf TransactionEngine-backup.tar.gz TransactionEngine-Web/

# Or on Windows
7z a TransactionEngine-backup.7z TransactionEngine-Web/
```

### Restore Project

```bash
# Extract backup
tar -xzf TransactionEngine-backup.tar.gz

# Or on Windows
7z x TransactionEngine-backup.7z
```

---

## Update & Maintenance

### Update Maven Dependencies

```bash
# Check for updates
mvn versions:display-dependency-updates

# Check for plugin updates
mvn versions:display-plugin-updates

# Update to new versions
mvn versions:use-latest-versions
mvn versions:commit
```

### Update Java

- Check: `java -version`
- Download latest from oracle.com or openjdk.java.net
- Install newer version
- Update PATH if needed
- Rebuild project with new Java

---

## Troubleshooting Configuration

### Port Already in Use

```bash
# Windows - Find and kill process on port 8000
netstat -ano | findstr :8000
taskkill /PID [PID] /F

# Linux/Mac - Find and kill process on port 8000
lsof -i :8000
kill -9 [PID]
```

### OutOfMemory Error

```bash
# Increase JVM memory
set MAVEN_OPTS=-Xmx2048m
mvn clean package
```

### Connection Refused

- Check if backend is running
- Verify correct port and URL
- Check firewall settings
- Verify localhost is correctly configured

---

## Verification Checklist

After setup, verify:

- [ ] Java installed: `java -version` shows 1.8+
- [ ] Maven installed: `mvn -version` shows 3.6+
- [ ] Project folder structure correct
- [ ] Backend builds: `mvn clean compile`
- [ ] Frontend loads in browser
- [ ] No console errors (F12)
- [ ] Can add transactions
- [ ] Statistics work
- [ ] Data persists

---

## Advanced Configuration

### CI/CD Integration

For Jenkins/GitHub Actions, create:

```yaml
# .github/workflows/build.yml
name: Build
on: [push]
jobs:
  build:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v2
      - uses: actions/setup-java@v2
        with:
          java-version: "8"
      - run: mvn clean package
```

---

## Support & Help

- Check README.md for features
- Check QUICKSTART.md for setup
- Review code comments
- Check browser console (F12)
- Verify prerequisites installed

---

**For more help, see README.md and QUICKSTART.md**
