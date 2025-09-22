**CurrencyExchanger** Java project, complete with setup instructions for IntelliJ IDEA and links to necessary libraries.

---

```markdown
# 💱 CurrencyExchanger

A JavaFX desktop application that enables users to convert currencies using live exchange rate APIs. This project supports multiple data sources for currency exchange rates.

## 🌟 Features

- Integration with two external currency exchange APIs

- Error handling for API failures and connectivity issues

## 🧰 Technologies Used

- Java 21
- Springboot 3.5.6
- IntelliJ IDEA
## 📦 Project Structure

+---src
|   +---main
|   |   +---java
|   |   |   \---com
|   |   |       \---vic
|   |   |           \---springbootcurrencyexchanger
|   |   |               |   CurrencyDataConverterService.java
|   |   |               |   SpringbootCurrencyExchangerApplication.java
|   |   |               |
|   |   |               +---ApiBundles
|   |   |               |       FixerBundle.java
|   |   |               |       OpenExchangeBundle.java
|   |   |               |
|   |   |               +---ApiConnections
|   |   |               |       FixerApiConnection.java
|   |   |               |       OpenExchangeApiConnection.java
|   |   |               |
|   |   |               +---ApiResponseParsers
|   |   |               |       FixerJsonParser.java
|   |   |               |       OpenExchangeJsonParser.java
|   |   |               |
|   |   |               +---Configuratons
|   |   |               |       RestTemplateConfig.java
|   |   |               |
|   |   |               +---Controllers
|   |   |               |       CurrencyController.java
|   |   |               |
|   |   |               +---Interfaces
|   |   |               |       CurrencyApiProvider.java
|   |   |               |       CurrencyDataConverter.java
|   |   |               |       CurrencyProviderBundle.java
|   |   |               |       JsonParser.java
|   |   |               |
|   |   |               +---models
|   |   |               |       Currency.java
|   |   |               |       CurrencyRateHistory.java
|   |   |               |
|   |   |               +---Services
|   |   |               |       ApiProviderSwitch.java
|   |   |               |
|   |   |
|   |   \---resources
|   |       |   application.config.properties



## 🧪 Troubleshooting

- **API Requests Failing**: Check your internet connection or the API endpoints.

## 📌 Todo

- Add  spring documentation
- Database integration


---

