package com.vic.springbootcurrencyexchanger.models;

public class CurrencyRateHistory {
    private String base;
    private String symbol;
    private String day;
    private double value;

    public CurrencyRateHistory(String base, String symbol, String day, double value) {
        this.base = base;
        this.symbol = symbol;
        this.day = day;
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public String getDay() {
        return day;
    }
}
