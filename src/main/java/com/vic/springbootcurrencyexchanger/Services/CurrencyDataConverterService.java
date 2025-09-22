package com.vic.springbootcurrencyexchanger.Services;

import com.vic.springbootcurrencyexchanger.Interfaces.CurrencyDataConverter;
import com.vic.springbootcurrencyexchanger.models.Currency;
import com.vic.springbootcurrencyexchanger.models.CurrencyRateHistory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CurrencyDataConverterService implements CurrencyDataConverter {

    private final ApiProviderSwitch apiProvider;
    private final CurrencyDataConverterService self; //proxy reference to self to ensure cached data is used

    public CurrencyDataConverterService(ApiProviderSwitch apiProvider, @Lazy CurrencyDataConverterService self) {
        this.apiProvider = apiProvider;
        this.self = self;
    }


    @Override
    @Cacheable(value = "conversion", key = "#fromCurrency + '_' + #toCurrency + '_' + #value" )
    public Double convert(String fromCurrency, String toCurrency, Double value) {
        try{
            StringBuilder response = apiProvider.getRate(fromCurrency, toCurrency, value);
            return apiProvider.getActiveParser().parseConversionRate(response);
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException("conversion failed: " + e.getMessage());
        }
    }

    @Override
    @Cacheable(value = "allCurrencies")
    public List<Currency> getAllCurrencies() {
        try{
            StringBuilder response = apiProvider.getSymbolsWithSignification();
            return apiProvider.getActiveParser().parseCurrencies(response);
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException("Failed to fetch currencies: " + e.getMessage());
        }
    }

    @Override
    @Cacheable(value = "allCurrenciesAndSignifications")
    public List<String> getAllCurrencyNamesAndSignifications(List<Currency> currencies) {
        return currencies.stream()
                .sorted(Comparator.comparing(Currency::getSignification)) // sort by signification
                .map(currency -> currency.getSignification() + " " + currency.getSymbol())
                .toList();
    }

    @Override
    @Cacheable(value = "allCurrenciesAndSignifications")
    public List<String> getAllCurrencyNamesAndSignifications() {
        // call via self proxy ensures caching applies
        return self.getAllCurrencies().stream()
                .sorted(Comparator.comparing(Currency::getSignification)) // sort by signification
                .map(currency -> currency.getSignification() + " " + currency.getSymbol())
                .toList();
    }



    @Override
    @Cacheable(value = "history", key = "#baseCurrency + '_' + #duration + '_' + #toCurrency ")
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

    @Override
    public List<Currency> findCurrency(List<Currency> currencies, String keyword) {
        if (keyword == null || keyword.isBlank()) {

            return currencies;
        }
        String search = keyword.toUpperCase();
        return currencies.stream()
                .filter(currency -> currency.getSymbol().contains(search) ||
                        currency.getSignification().toUpperCase().contains(search))
                .collect(Collectors.toList());
    }
}
