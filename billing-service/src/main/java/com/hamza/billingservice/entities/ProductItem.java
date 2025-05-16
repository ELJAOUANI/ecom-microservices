package com.hamza.billingservice.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hamza.billingservice.model.Product;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class ProductItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productId;
    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private  Bill bill;
    private int quantity;
    private double unitPrice;
    @Transient
    private Product product;
}
