package com.thesis.sofen.request.booking;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;

import java.beans.ConstructorProperties;

//http://localhost:8080/vnpay_jsp/vnpay_return.jsp?
// vnp_Amount=10000000
// &vnp_BankCode=NCB
// &vnp_BankTranNo=VNP14361218
// &vnp_CardType=ATM
// &vnp_OrderInfo=Thanh+toan+don+hang%3A37628251
// &vnp_PayDate=20240330230243
// &vnp_ResponseCode=00
// &vnp_TmnCode=QGXV5PWZ&vnp_TransactionNo=14361218
// &vnp_TransactionStatus=00
// &vnp_TxnRef=37628251
// &vnp_SecureHash=745fd88992ea850b976b07dcc412b81014ee886eb8b39511ce3bf43f80c98736fc9c44d482b8fe035ec85e1bf5abe40340028551aafc66b5ac2171b065b73e5f
@Getter
@JsonPropertyOrder({"vnp_Amount", "vnp_BankCode", "vnp_BankTranNo", "vnp_CardType", "vnp_OrderInfo", "vnp_PayDate",
        "vnp_ResponseCode", "vnp_TmnCode", "vnp_TransactionNo", "vnp_TransactionStatus", "vnp_TxnRef", "vnp_SecureHash"})
public class BookingPaymentRequest {
    @JsonProperty("vnp_Amount")
    private Long amount;
    @JsonProperty("vnp_BankCode")
    private String bankCode;
    @JsonProperty("vnp_BankTranNo")
    private String bankTranNo;
    @JsonProperty("vnp_CardType")
    private String cardType;
    @JsonProperty("vnp_OrderInfo")
    private String orderInfo;
    @JsonProperty("vnp_PayDate")
    private String payDate;
    @JsonProperty("vnp_ResponseCode")
    private String responseCode;
    @JsonProperty("vnp_TmnCode")
    private String tmnCode;
    @JsonProperty("vnp_TransactionNo")
    private String transactionNo;
    @JsonProperty("vnp_TransactionStatus")
    private String transactionStatus;
    @JsonProperty("vnp_TxnRef")
    private String txnRef;
    @JsonProperty("vnp_SecureHash")
    private String secureHash;

    @ConstructorProperties({"vnp_Amount", "vnp_BankCode", "vnp_BankTranNo", "vnp_CardType", "vnp_OrderInfo",
            "vnp_PayDate", "vnp_ResponseCode", "vnp_TmnCode", "vnp_TransactionNo", "vnp_TransactionStatus",
            "vnp_TxnRef", "vnp_SecureHash"})
    public BookingPaymentRequest(Long amount, String bankCode, String bankTranNo, String cardType, String orderInfo,
                                 String payDate, String responseCode, String tmnCode, String transactionNo,
                                 String transactionStatus, String txnRef, String secureHash) {
        this.amount = amount;
        this.bankCode = bankCode;
        this.bankTranNo = bankTranNo;
        this.cardType = cardType;
        this.orderInfo = orderInfo;
        this.payDate = payDate;
        this.responseCode = responseCode;
        this.tmnCode = tmnCode;
        this.transactionNo = transactionNo;
        this.transactionStatus = transactionStatus;
        this.txnRef = txnRef;
        this.secureHash = secureHash;
    }
}
