package com.vic.springbootcurrencyexchanger.Interfaces;

import com.vic.springbootcurrencyexchanger.models.Currency;
import com.vic.springbootcurrencyexchanger.models.CurrencyRateHistory;

import java.util.List;
import java.util.Map;

public interface JsonParser {
    Double parseConversionRate(StringBuilder response);
    List<CurrencyRateHistory> parseConversionRateHistory(StringBuilder response, String base, String symbol);
    Map<String, String> parseSymbols(StringBuilder response);
    List<Currency> parseCurrencies(StringBuilder response);
}
