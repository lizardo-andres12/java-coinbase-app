# CryptoHelper - Java Cryptocurrency Conversion Application

A JavaFX-based desktop application that provides real-time cryptocurrency price conversion to various international currencies. This application integrates with external APIs to fetch current cryptocurrency prices and perform currency conversions.

## Features

- **Real-time Cryptocurrency Prices**: Get current USD prices for popular cryptocurrencies
- **Currency Conversion**: Convert cryptocurrency values to multiple international currencies
- **Intuitive GUI**: Easy-to-use JavaFX interface with dropdown menus and conversion button
- **Market Data**: Display global market cap rankings for cryptocurrencies
- **Error Handling**: Comprehensive error handling with user-friendly alerts

### Supported Cryptocurrencies
- Bitcoin (BTC)
- Ethereum (ETH) 
- Tether (USDT)
- XRP (XRP)
- Solana (SOL)
- Dogecoin (DOGE)

### Supported Currencies
- British Pound (GBP)
- Euro (EUR)
- Australian Dollar (AUD)
- Brazilian Real (BRL)
- Chinese Yuan (CNY)

## Prerequisites

Before running the application, ensure you have the following installed:

### Required Software
- **Java Development Kit (JDK) 17 or higher**
  - Download from [Oracle](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) or [OpenJDK](https://openjdk.java.net/projects/jdk/17/)
  - Verify installation: `java --version`
- **Apache Maven 3.9.0 or higher**
  - Download from [Maven Official Site](https://maven.apache.org/download.cgi)
  - Verify installation: `mvn --version`

### API Key Requirements
This application requires an API key from CurrencyLayer for currency conversion functionality.

1. **Get CurrencyLayer API Key**:
   - Visit [CurrencyLayer](https://currencylayer.com/)
   - Sign up for a free account
   - Navigate to your dashboard to get your API key

2. **Create Configuration File**:
   ```bash
   # Create the resources directory (if it doesn't exist)
   mkdir -p resources
   
   # Create the config.properties file
   touch resources/config.properties
   ```

3. **Add API Key to Configuration**:
   Edit `resources/config.properties` and add:
   ```properties
   conversionapi.key=YOUR_CURRENCYLAYER_API_KEY_HERE
   ```
   Replace `YOUR_CURRENCYLAYER_API_KEY_HERE` with your actual API key.

## Installation & Setup

### 1. Clone or Download the Project
```bash
# If using git
git clone <repository-url>
cd java-coinbase-app

# Or download and extract the project files
```

### 2. Verify Project Structure
Ensure your project directory contains:
```
java-coinbase-app/
├── src/
│   ├── main/
│   │   └── java/
│   │       ├── cs1302/
│   │       │   └── api/
│   │       │       ├── ApiDriver.java
│   │       │       ├── AppGraphicalUserInterface.java
│   │       │       ├── APIService.java
│   │       │       ├── AbstractController.java
│   │       │       ├── CoinInterfaceComponent.java
│   │       │       ├── CryptoController.java
│   │       │       ├── CurrencyInterfaceComponent.java
│   │       │       └── ForexController.java
│   │       └── module-info.java
│   └── site/
├── resources/
│   └── config.properties  (you need to create this)
├── pom.xml
├── run.sh
└── README.md
```

### 3. Set Up Configuration
Create and configure the `resources/config.properties` file as described in the API Key Requirements section above.

## Running the Application

### Method 1: Using the Provided Shell Script (Recommended for Unix/Linux/macOS)

1. **Make the script executable**:
   ```bash
   chmod +x run.sh
   ```

2. **Run the application**:
   ```bash
   ./run.sh
   ```

### Method 2: Using Maven Commands (Cross-platform)

1. **Clean previous builds**:
   ```bash
   mvn clean
   ```

2. **Compile the project**:
   ```bash
   mvn compile
   ```

3. **Run the application**:
   ```bash
   mvn exec:exec
   ```

### Method 3: Step-by-step Manual Execution

1. **Clean and compile**:
   ```bash
   mvn clean compile
   ```

2. **Run with full module path**:
   ```bash
   mvn exec:exec -Dexec.mainClass=cs1302uga.api/cs1302.api.ApiDriver
   ```

## Using the Application

### Basic Usage Flow

1. **Launch the Application**: The CryptoHelper window will open with three main sections:
   - Left: Cryptocurrency selection and price display
   - Center: Amount input field and Convert button
   - Right: Currency selection and conversion results

2. **Select a Cryptocurrency**:
   - Use the dropdown menu on the left to choose a cryptocurrency (BTC, ETH, USDT, XRP, SOL, or DOGE)

3. **Enter Amount**:
   - Type the amount of cryptocurrency you want to convert in the text field

4. **Select Target Currency**:
   - Use the dropdown menu on the right to choose the target currency (GBP, EUR, AUD, BRL, or CNY)

5. **Convert**:
   - Click the "Convert" button to perform the conversion
   - The application will display:
     - Current USD price of the selected cryptocurrency
     - Global market cap rank
     - Converted amount in the selected currency
     - Exchange rate information

### Example Conversion
1. Select "Bitcoin (BTC)" from the left dropdown
2. Enter "0.5" in the amount field
3. Select "Euro (EUR)" from the right dropdown  
4. Click "Convert"
5. View results showing current BTC price, 0.5 BTC value in EUR, and exchange rate

## Troubleshooting

### Common Issues and Solutions

**Issue**: `UnsupportedOperationException` with DISPLAY error
- **Solution**: This typically occurs on remote servers without X11 forwarding. If using SSH, connect with X11 forwarding: `ssh -X username@server`
- **Alternative**: Run on a local machine with a desktop environment

**Issue**: `IOException` during API calls
- **Solution**: 
  - Verify your internet connection
  - Check that your CurrencyLayer API key is correct in `config.properties`
  - Ensure the API key hasn't exceeded its usage limits

**Issue**: `RuntimeException: No coin selected!`
- **Solution**: Make sure to select a cryptocurrency from the dropdown before clicking Convert

**Issue**: `RuntimeException: No amount selected!`
- **Solution**: Enter a numeric value in the amount field before converting

**Issue**: `RuntimeException: No currency selected!`
- **Solution**: Select a target currency from the right dropdown menu

**Issue**: Maven compilation errors
- **Solution**: 
  - Verify Java 17+ is installed: `java --version`
  - Verify Maven 3.9.0+ is installed: `mvn --version`
  - Check that JAVA_HOME environment variable points to JDK 17+

**Issue**: Module path errors
- **Solution**: Ensure you're using JDK 17 or higher and all required JavaFX modules are included in the Maven dependencies

### Debug Mode
To run with additional debug information:
```bash
mvn exec:exec -Dexec.args="-Djavafx.verbose=true -Dprism.verbose=true"
```

## API Information

### Cryptocurrency API
- **Service**: CoinLore API
- **Endpoint**: `https://api.coinlore.net/api/ticker/`
- **Authentication**: None required
- **Rate Limits**: Check CoinLore documentation for current limits

### Currency Conversion API  
- **Service**: CurrencyLayer
- **Endpoint**: `https://api.currencylayer.com/convert`
- **Authentication**: API key required
- **Rate Limits**: Depends on your CurrencyLayer plan (free tier typically allows 1,000 requests/month)

## Project Structure Details

- **`src/main/java/cs1302/api/`**: Main application package
  - `ApiDriver.java`: Application entry point
  - `AppGraphicalUserInterface.java`: Main JavaFX application class and UI logic
  - `APIService.java`: Service layer for API interactions, contains data models
  - `AbstractController.java`: Base class for API controllers
  - `CryptoController.java`: Handles cryptocurrency API requests
  - `ForexController.java`: Handles currency conversion API requests
  - `CoinInterfaceComponent.java`: Custom JavaFX component for cryptocurrency selection
  - `CurrencyInterfaceComponent.java`: Custom JavaFX component for currency selection

- **`resources/config.properties`**: Configuration file for API keys (you create this)
- **`pom.xml`**: Maven project configuration
- **`run.sh`**: Convenience script for running the application

## Technical Requirements

- **Java Version**: 17+
- **Maven Version**: 3.9.0+
- **JavaFX Version**: 17.0.10 (automatically managed by Maven)
- **Dependencies**: 
  - Google Gson 2.10.1 (JSON parsing)
  - JavaFX Controls, FXML, and Web modules
- **Internet Connection**: Required for API calls