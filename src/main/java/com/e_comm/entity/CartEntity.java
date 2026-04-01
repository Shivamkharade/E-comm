package com.e_comm.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "cart")
public class CartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 
    
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
        name = "cart_products",
        joinColumns = @JoinColumn(name = "cart_id"),
        inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<ProductEntity> items = new ArrayList<>();

    private BigDecimal totalPrice = BigDecimal.ZERO;


    public CartEntity() {
    	
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ProductEntity> getItems() {
        return items;
    }

    public void setItems(List<ProductEntity> items) {
        this.items = items;
        calculateTotalPrice();
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void calculateTotalPrice() {
        this.totalPrice = items.stream()
                               .map(ProductEntity::getPrice)
                               .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

	public CartEntity(Long id, List<ProductEntity> items, BigDecimal totalPrice) {
		super();
		this.id = id;
		this.items = items;
		this.totalPrice = totalPrice;
	}
    
    
}