package model;

import lombok.Data;

import java.util.List;

@Data
public class PaymentMessage {

    String transactionId;
    String transactionStatus;
    List transactionTracking;
    String transactionTimestamp;
}
