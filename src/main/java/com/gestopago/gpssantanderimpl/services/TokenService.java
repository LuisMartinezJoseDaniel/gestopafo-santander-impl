package com.gestopago.gpssantanderimpl.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class TokenService {
    private final RestTemplate restTemplate;
    private final CacheManager cacheManager;
    
    @Value("${app.GESTOPAGO_AUTH_ENDPOINT}")
    private String authEndpoint;
    @Value("${app.GESTOPAGO_ID_DISTRIBUTOR}")
    private String idDistribuidor;
    @Value("${app.GESTOPAGO_CODE_DISPOSITIVE}")
    private String codigoDispositivo;
    @Value("${app.GESTOPAGO_PASSWORD}")
    private String password;
    @Value("${app.X_API_KEY}")
    private String xApiKey;
    
    public String getToken() {
        String token = cacheManager.getCache("gestopago_token_cache").get("gestopago_token", String.class);
        
        if ( token != null ) {
            return token;
        }
        try {
            String uri = UriComponentsBuilder.fromUriString(authEndpoint)
                    .queryParam("idDistribuidor", idDistribuidor)
                    .queryParam("codigoDispositivo", codigoDispositivo)
                    .queryParam("password", password)
                    .toUriString();
            
            log.warn("URI: {}", uri);
            
            HttpHeaders header = new HttpHeaders();
            header.add("X-API-Key", xApiKey);
            
            HttpEntity<Map<String, String>> request = new HttpEntity<>(header);
            
            var response = restTemplate.postForObject(uri, request, Map.class);
            
            if ( response.get("success") != null && !Boolean.parseBoolean(response.get("success").toString()) ) {
                log.error("Error getting token: {}", response.get("message"));
                throw new RuntimeException("Error getting token");
            }
            
            var tokenResponse = response.get("token").toString();
            if ( tokenResponse != null ) {
                cacheManager.getCache("gestopago_token_cache").put("gestopago_token", tokenResponse);
            }
            return tokenResponse;
        } catch ( RestClientException e ) {
            log.error("Error getting token", e);
            throw new RuntimeException("Error getting token");
        }
    }
    
    //TODO implementation
    public String validateMe() {
        return "I'm here";
    }
}
