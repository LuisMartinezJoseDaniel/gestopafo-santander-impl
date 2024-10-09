package com.gestopago.gpssantanderimpl.models;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;
    
    private Integer idServicio;
    
    private Integer idProducto;
    
    private Integer idCatTipoServicio;
    
    private Integer tipoFront;
    
    private Boolean hasDigitoVerificador;
    
    private String tipoReferencia;
    
    private String precio;
    
    private Boolean showAyuda;
    
    private Boolean disponible;
    
    private Timestamp createdAt;
    
    private Timestamp updatedAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = new Timestamp(System.currentTimeMillis());
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Timestamp(System.currentTimeMillis());
    }

}
