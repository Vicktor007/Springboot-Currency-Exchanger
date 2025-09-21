package com.vic.springbootcurrencyexchanger.Utility;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigLoader {

    private String fixerApiUrl;
    private String fixerApiKey;
    private String openApiUrl;
    private String openApiKey;


    // Getters and setters
    public String getFixerApiUrl() { return fixerApiUrl; }
    public void setFixerApiUrl(String fixerApiUrl) { this.fixerApiUrl = fixerApiUrl; }

    public String getFixerApiKey() { return fixerApiKey; }
    public void setFixerApiKey(String fixerApiKey) { this.fixerApiKey = fixerApiKey; }

    public String getOpenApiUrl() { return openApiUrl; }
    public void setOpenApiUrl(String openApiUrl) { this.openApiUrl = openApiUrl; }

    public String getOpenApiKey() { return openApiKey; }
    public void setOpenApiKey(String openApiKey) { this.openApiKey = openApiKey; }

}