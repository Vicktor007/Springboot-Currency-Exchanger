package com.vic.springbootcurrencyexchanger.Dtos;

import java.time.LocalDate;

public record CurrencyHistoryRequest(
        String base,
        LocalDate start,
        LocalDate end,
        String symbol
) {
}
