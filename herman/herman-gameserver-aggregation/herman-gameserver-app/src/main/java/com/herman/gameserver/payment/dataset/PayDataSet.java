package com.herman.gameserver.payment.dataset;

import java.io.Serializable;

public class PayDataSet implements Serializable {

    private static final long serialVersionUID = -1889540522534079994L;

    private String transactionId;

    private String paymentUrl;

    private String payStatus;

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getPaymentUrl() {
        return paymentUrl;
    }

    public void setPaymentUrl(String paymentUrl) {
        this.paymentUrl = paymentUrl;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }
}
