package com.vic.springbootcurrencyexchanger.Dtos;

public record RateRequest(
        String from,
        String to,
        Double Amount
) {
}
