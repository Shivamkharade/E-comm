package com.e_comm.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "Orders") 
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToMany
    @JoinTable(
        name = "order_items",
        joinColumns = @JoinColumn(name = "order_id"),
        inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<ProductEntity> items = new ArrayList<>();

    private BigDecimal totalPrice;

    private String status;

    public OrderEntity() {
    	
    }

    public OrderEntity(String id, List<ProductEntity> items, BigDecimal totalPrice, String status) {
        this.id = id;
        this.items = items;
        this.totalPrice = totalPrice;
        this.status = status;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public List<ProductEntity> getItems() { return items; }
    public void setItems(List<ProductEntity> items) { this.items = items; }

    public BigDecimal getTotalPrice() { return totalPrice; }
    public void setTotalPrice(BigDecimal totalPrice) { this.totalPrice = totalPrice; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "OrderEntity [id=" + id + ", items=" + items + ", totalPrice=" + totalPrice + ", status=" + status + "]";
    }
}