package org.qydata.dst;

import org.qydata.entity.MobileOperator;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jonhn on 2017/4/17.
 */
public class ApiTypeConsume implements Serializable {
    private Integer apiTypeId;
    private String apiTypeName;
    private Integer apiStatus;
    private Long apiTypeConsumeTotleAmount;
    private Integer apiTypeUsageAmount;
    private Integer cost;
    private List<MobileOperator> mobileOperatorList;

    public Integer getApiTypeId() {
        return apiTypeId;
    }

    public void setApiTypeId(Integer apiTypeId) {
        this.apiTypeId = apiTypeId;
    }

    public String getApiTypeName() {
        return apiTypeName;
    }

    public void setApiTypeName(String apiTypeName) {
        this.apiTypeName = apiTypeName;
    }

    public Integer getApiStatus() {
        return apiStatus;
    }

    public void setApiStatus(Integer apiStatus) {
        this.apiStatus = apiStatus;
    }

    public Long getApiTypeConsumeTotleAmount() {
        return apiTypeConsumeTotleAmount;
    }

    public void setApiTypeConsumeTotleAmount(Long apiTypeConsumeTotleAmount) {
        this.apiTypeConsumeTotleAmount = apiTypeConsumeTotleAmount;
    }

    public Integer getApiTypeUsageAmount() {
        return apiTypeUsageAmount;
    }

    public void setApiTypeUsageAmount(Integer apiTypeUsageAmount) {
        this.apiTypeUsageAmount = apiTypeUsageAmount;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public List<MobileOperator> getMobileOperatorList() {
        return mobileOperatorList;
    }

    public void setMobileOperatorList(List<MobileOperator> mobileOperatorList) {
        this.mobileOperatorList = mobileOperatorList;
    }
}