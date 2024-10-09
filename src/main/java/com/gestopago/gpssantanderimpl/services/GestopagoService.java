package com.gestopago.gpssantanderimpl.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class GestopagoService {
    private final RestTemplate restTemplate;
    private final TokenService tokenService;
    
    @Value("${app.X_API_KEY}")
    private String xApiKey;
    @Value("${app.GESTOPAGO_CONTEXT}")
    private String context;
    
    public String getProductList() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-API-Key", xApiKey);
        headers.add("Authorization", "Bearer " + tokenService.getToken());
        
        String productListEndpoint = context + "/getProductList.do";
        
        log.warn("Token: {}", tokenService.getToken());
        log.warn("Endpoint: {}", productListEndpoint);
        
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(productListEndpoint, HttpMethod.GET, entity, String.class);
        
        return responseEntity.getBody();
    }
    
    public String sendTx() {
        return null;
    }
    
    public String confirmTx() {
        return null;
    }
    
    public String verifyReference() {
        return null;
    }
    
    public String getReferenceImage() {
        return null;
    }
}
