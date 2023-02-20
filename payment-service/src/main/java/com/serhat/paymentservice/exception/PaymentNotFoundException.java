package com.serhat.paymentservice.exception;

public class PaymentNotFoundException extends RuntimeException{
    public PaymentNotFoundException() {
        super("Payment not found");
    }
}
