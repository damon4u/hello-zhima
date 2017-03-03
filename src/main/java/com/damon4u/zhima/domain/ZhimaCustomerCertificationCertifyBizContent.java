package com.damon4u.zhima.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Description:
 *
 * @author damon4u
 * @version 2017-03-01 14:53
 */
public class ZhimaCustomerCertificationCertifyBizContent {

    @JsonProperty("biz_no")
    private String bizNo;

    public String getBizNo() {
        return bizNo;
    }

    public void setBizNo(String bizNo) {
        this.bizNo = bizNo;
    }
}
