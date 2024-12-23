package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.PaymentResponse;
import com.example.demo.Entity.Order;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;

@Service
public class PaymentServiceImpl implements PaymentService{

	@Value("${stripe.api.secret}")
	private String stripeSeceretKey;
	
	@Override
	public PaymentResponse createPaymentLink(Order order) throws StripeException {
		Stripe.apiKey=stripeSeceretKey;
		long totalAmountInCents = Math.round(order.getTotalAmount() * 100);
		SessionCreateParams params=SessionCreateParams.builder().addPaymentMethodType(
				SessionCreateParams.
				PaymentMethodType.CARD)
				.setMode(SessionCreateParams.Mode.PAYMENT)
				.setSuccessUrl("http://localhost:5173/payment/success"+order.getId())
				.setCancelUrl("http://localhost:5173/payment/failed")
				.addLineItem(SessionCreateParams.LineItem.builder()
						.setQuantity(1L).setPriceData(SessionCreateParams.LineItem.PriceData.builder()
								.setCurrency("usd")
								.setUnitAmount(totalAmountInCents)
								.setProductData(SessionCreateParams
										.LineItem.PriceData.ProductData.builder()
										.setName("sirajShoe").build())
									.build()
								
								).build()
						).build();
		
		Session session=Session.create(params);
		
		System.out.println("Stripe Session: " + session);

		
		PaymentResponse res=new PaymentResponse();
		res.setPayment_url(session.getUrl());
		
				
		return res;
	}

}
