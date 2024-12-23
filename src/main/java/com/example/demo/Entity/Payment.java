//package com.example.demo.Entity;
//
//
//import java.time.LocalDateTime;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.Table;
//
//@Entity
//@Table(name = "payments")
//public class Payment {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(name = "payment_intent_id", nullable = false, unique = true)
//    private String paymentIntentId;
//
//    @Column(nullable = false)
//    private Long amount;
//
//    @Column(nullable = false)
//    private String currency;
//
//    @Column(name = "status", nullable = false)
//    private String status;
//
//    @Column(name = "client_secret", nullable = false)
//    private String clientSecret;
//
//    @Column(name = "created_at", nullable = false)
//    private LocalDateTime createdAt;
//
//    // Optional: Add relationships to User or Order entities
//    @Column(name = "user_id", nullable = false)
//    private Long userId;
//
//    // Constructors, Getters, and Setters
//
//    public Payment() {}
//
//    public Payment(String paymentIntentId, Long amount, String currency, String status, String clientSecret, Long userId) {
//        this.paymentIntentId = paymentIntentId;
//        this.amount = amount;
//        this.currency = currency;
//        this.status = status;
//        this.clientSecret = clientSecret;
//        this.createdAt = LocalDateTime.now();
//        this.userId = userId;
//    }
//
//    // Getters and Setters
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getPaymentIntentId() {
//        return paymentIntentId;
//    }
//
//    public void setPaymentIntentId(String paymentIntentId) {
//        this.paymentIntentId = paymentIntentId;
//    }
//
//    public Long getAmount() {
//        return amount;
//    }
//
//    public void setAmount(Long amount) {
//        this.amount = amount;
//    }
//
//    public String getCurrency() {
//        return currency;
//    }
//
//    public void setCurrency(String currency) {
//        this.currency = currency;
//    }
//
//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
//
//    public String getClientSecret() {
//        return clientSecret;
//    }
//
//    public void setClientSecret(String clientSecret) {
//        this.clientSecret = clientSecret;
//    }
//
//    public LocalDateTime getCreatedAt() {
//        return createdAt;
//    }
//
//    public void setCreatedAt(LocalDateTime createdAt) {
//        this.createdAt = createdAt;
//    }
//
//    public Long getUserId() {
//        return userId;
//    }
//
//    public void setUserId(Long userId) {
//        this.userId = userId;
//    }
//}
//
