package com.vic.springbootcurrencyexchanger;

import com.vic.springbootcurrencyexchanger.Interfaces.CurrencyDataConverter;
import com.vic.springbootcurrencyexchanger.Services.ApiProviderSwitch;
import com.vic.springbootcurrencyexchanger.models.Currency;
import com.vic.springbootcurrencyexchanger.models.CurrencyRateHistory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@Service
public class CurrencyDataConverterService implements CurrencyDataConverter {

    private final ApiProviderSwitch apiProvider;

    public CurrencyDataConverterService( ApiProviderSwitch apiProvider) {
        this.apiProvider = apiProvider;
    }


    @Override
    public Double convert(String fromCurrency, String toCurrency, Double value) {
        try{
            StringBuilder response = apiProvider.getRate(fromCurrency, toCurrency, value);
            return apiProvider.getActiveParser().parseConversionRate(response);
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException("conversion failed: " + e.getMessage());
        }
    }

    @Override
    public List<Currency> getAllCurrencies() {
        try{
            StringBuilder response = apiProvider.getSymbolsWithSignification();
            return apiProvider.getActiveParser().parseCurrencies(response);
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException("Failed to fetch currencies: " + e.getMessage());
        }
    }

    @Override
    public List<String> getAllCurrencyNamesAndSignifications(List<Currency> currencies) {
        return currencies.stream()
                .sorted(Comparator.comparing(Currency::getSignification)) // sort by signification
                .map(currency -> currency.getSignification() + " " + currency.getSymbol())
                .toList();
    }

    @Override
    public List<CurrencyRateHistory> getCurrencyRateHistory(String baseCurrency, Integer duration, String toCurrency) {
        try {
            LocalDate today = LocalDate.now();
            LocalDate startDate = today.minusDays(duration);
            StringBuilder response = apiProvider.getCurrencyHistory(baseCurrency, startDate, today, toCurrency );
            return apiProvider.getActiveParser().parseConversionRateHistory(response, baseCurrency, toCurrency);
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
