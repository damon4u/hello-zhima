package com.damon4u.zhima.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Description:
 *
 * @author damon4u
 * @version 2017-03-01 14:54
 */
public class IdentityParam {

    @JsonProperty("identity_type")
    private String identityType;

    @JsonProperty("cert_type")
    private String certType;

    @JsonProperty("cert_name")
    private String certName;

    @JsonProperty("cert_no")
    private String certNo;

    public String getIdentityType() {
        return identityType;
    }

    public void setIdentityType(String identityType) {
        this.identityType = identityType;
    }

    public String getCertType() {
        return certType;
    }

    public void setCertType(String certType) {
        this.certType = certType;
    }

    public String getCertName() {
        return certName;
    }

    public void setCertName(String certName) {
        this.certName = certName;
    }

    public String getCertNo() {
        return certNo;
    }

    public void setCertNo(String certNo) {
        this.certNo = certNo;
    }
}
