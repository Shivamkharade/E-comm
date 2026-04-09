package com.e_comm.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.e_comm.Enum.OrderEntityEnum;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;


@Entity
@Table(name = "orders") 
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    @OneToMany(mappedBy = "order",
    		   cascade = CascadeType.ALL,
    		   orphanRemoval = true,
    		   fetch = FetchType.LAZY)
    private List<OrderItemEntity> items = new ArrayList<>();
    
    private BigDecimal totalPrice;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",nullable = false)
    private UserEntity user;

    @Enumerated(EnumType.STRING)
    private OrderEntityEnum status = OrderEntityEnum.CREATED;
    
    @PrePersist
    @PreUpdate
    public void calculateTotalPrice() {
        this.totalPrice = items.stream()
                .map(item -> item.getSubtotal() != null ? item.getSubtotal() : BigDecimal.ZERO)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<OrderItemEntity> getItems() {
		return items;
	}

	public void setItems(List<OrderItemEntity> items) {
		this.items = items;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public OrderEntityEnum getStatus() {
		return status;
	}

	public void setStatus(OrderEntityEnum status) {
		this.status = status;
	}

	public OrderEntity(String id, List<OrderItemEntity> items, BigDecimal totalPrice, UserEntity user,
			OrderEntityEnum status) {
		super();
		this.id = id;
		this.items = items;
		this.totalPrice = totalPrice;
		this.user = user;
		this.status = status;
	}
	
	public void addItem(OrderItemEntity item) {
	    items.add(item);
	    item.setOrder(this);
	}
    
    public OrderEntity() {
    	
    }
    
}