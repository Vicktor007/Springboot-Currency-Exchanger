package com.vic.springbootcurrencyexchanger.Dtos;

import java.util.Map;

public record HistoryResponse(Map<String, Map<String, Double>> rates) {
}
