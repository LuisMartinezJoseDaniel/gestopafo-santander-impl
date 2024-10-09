package com.gestopago.gpssantanderimpl.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "request_logs")
public class RequestLog {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Target target;
    private Integer idDistribuidor;
    private Integer idServicio;
    private Integer idProducto;
    private String referencia;
    @Column(columnDefinition = "json")
    private String request;
    @Column(columnDefinition = "json")
    private String response;
    private Integer idTransaccion;
    private Integer responseTime;
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
