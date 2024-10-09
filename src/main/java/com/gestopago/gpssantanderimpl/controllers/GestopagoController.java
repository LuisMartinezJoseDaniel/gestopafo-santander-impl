package com.gestopago.gpssantanderimpl.controllers;

import com.gestopago.gpssantanderimpl.services.GestopagoService;
import com.gestopago.gpssantanderimpl.services.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/gestopago")
public class GestopagoController {
    private final TokenService tokenService;
    private final GestopagoService gestopagoService;
    
    @GetMapping("/token")
    public String getToken() {
        return tokenService.getToken();
    }
    
    @GetMapping("/productList")
    public ResponseEntity<?> getProductList() {
        var productList = gestopagoService.getProductList();
        return ResponseEntity.ok(productList);
    }
}
