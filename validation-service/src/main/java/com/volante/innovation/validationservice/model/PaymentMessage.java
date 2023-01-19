package com.volante.innovation.validationservice.model;

import lombok.Data;

@Data
public class PaymentMessage {

    String transactionId;
    String transactionStatus;
    String transactionTracking;
    String transactionTimestamp;
}
