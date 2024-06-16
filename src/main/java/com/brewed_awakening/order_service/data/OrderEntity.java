package com.brewed_awakening.order_service.data;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "orders")
public class OrderEntity {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false, unique = true)
    private String orderNumber;

    private Date orderDate;

    private float pricePaid;

    private String product;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public float getPricePaid() {
        return pricePaid;
    }

    public void setPricePaid(float pricePaid) {
        this.pricePaid = pricePaid;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }
}
