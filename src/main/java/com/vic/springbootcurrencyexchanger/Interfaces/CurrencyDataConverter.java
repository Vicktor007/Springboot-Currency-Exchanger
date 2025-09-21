package com.vic.springbootcurrencyexchanger.Interfaces;

import com.vic.springbootcurrencyexchanger.models.Currency;
import com.vic.springbootcurrencyexchanger.models.CurrencyRateHistory;

import java.util.List;

public interface CurrencyDataConverter {
    Double convert(String fromCurrency, String toCurrency, Double value);
    List<Currency> getAllCurrencies();
    List<String> getAllCurrencyNamesAndSignifications(List<Currency> currencies);
    List<CurrencyRateHistory> getCurrencyRateHistory(String baseCurrency, Integer duration, String toCurrency);
}
