package com.vic.springbootcurrencyexchanger.Controllers;


import com.vic.springbootcurrencyexchanger.Services.CurrencyDataConverterService;
import com.vic.springbootcurrencyexchanger.models.Currency;
import com.vic.springbootcurrencyexchanger.models.CurrencyRateHistory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/currency")
public class CurrencyController {

    private final CurrencyDataConverterService converterService;

    public CurrencyController(CurrencyDataConverterService converterService) {
        this.converterService = converterService;
    }

    /**
     * Convert an amount from one currency to another
     * Example: GET /api/currency/convert?from=USD&to=EUR&amount=100
     */
    @GetMapping("/convert")
    public Double convert(
            @RequestParam String from,
            @RequestParam String to,
            @RequestParam Double amount
    ) {
        return converterService.convert(from, to, amount);
    }

    /**
     * Get all supported currencies
     * Example: GET /api/currency/all
     */
    @GetMapping("/all")
    public List<Currency> getAllCurrencies() {
        return converterService.getAllCurrencies();
    }

    /**
     * Get all currency names + significations (sorted)
     * Example: GET /api/currency/names
     */
    @GetMapping("/names")
    public List<String> getAllCurrencyNames() {
        List<Currency> currencies = converterService.getAllCurrencies();
        return converterService.getAllCurrencyNamesAndSignifications(currencies);
    }

    /**
     * Get historical rates for the last X days
     * Example: GET /api/currency/history?base=USD&from=USD&to=EUR&days=1
     */
    @GetMapping("/history")
    public List<CurrencyRateHistory> getHistory(
            @RequestParam String base,
            @RequestParam String to,
            @RequestParam(defaultValue = "1") Integer days
    ) {
        return converterService.getCurrencyRateHistory(base, days, to);
    }
}
