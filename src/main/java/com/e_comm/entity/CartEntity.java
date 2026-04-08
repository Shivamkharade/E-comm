package com.e_comm.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "cart")
public class CartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 
    
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<CartItemEntity> items = new ArrayList<>();
    
    private BigDecimal totalPrice = BigDecimal.ZERO;
    
    public void calculateTotalPrice() {
        this.totalPrice = items.stream()
                .map(CartItemEntity::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}