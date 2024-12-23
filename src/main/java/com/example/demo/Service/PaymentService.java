package com.example.demo.Service;

import com.example.demo.DTO.PaymentResponse;
import com.example.demo.Entity.Order;
import com.stripe.exception.StripeException;

public interface PaymentService{
	
	public PaymentResponse createPaymentLink(Order order) throws StripeException;
	
}