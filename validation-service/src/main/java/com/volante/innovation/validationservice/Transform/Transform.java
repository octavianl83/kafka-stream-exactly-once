package com.volante.innovation.validationservice.Transform;


import model.PaymentMessage;

public class Transform {

    public static PaymentMessage process(PaymentMessage paymentMessage) {
        paymentMessage.setTransactionStatus("FINALIZED");
        return paymentMessage;
    }
}
