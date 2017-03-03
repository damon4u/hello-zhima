package com.damon4u.zhima.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Description:
 *
 * @author damon4u
 * @version 2017-03-01 14:53
 */
public class ZhimaCustomerCertificationInitializeBizContent {
    @JsonProperty("transaction_id")
    private String transactionId;

    @JsonProperty("product_code")
    private String productCode;

    @JsonProperty("biz_code")
    private String bizCode;

    @JsonProperty("identity_param")
    private IdentityParam identityParam;

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getBizCode() {
        return bizCode;
    }

    public void setBizCode(String bizCode) {
        this.bizCode = bizCode;
    }

    public IdentityParam getIdentityParam() {
        return identityParam;
    }

    public void setIdentityParam(IdentityParam identityParam) {
        this.identityParam = identityParam;
    }
}
