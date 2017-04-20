package org.qydata.dst;

import org.qydata.entity.ApiType;
import org.qydata.entity.MobileOperator;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jonhn on 2017/1/18.
 */
public class ApiFinance implements Serializable {

    private Integer apiId;
    private Integer apiTypeId;
    private String apiTypeName;
    private Integer vendorId;
    private String vendorName;
    private String apiName;
    private Integer status;
    private Long balance;
    private Long weekTotleCost;
    private Long monthTotleCost;
    private Long consumeTotleAmount;
    private Long currMonthCost;
    private Long currDayCost;
    private Long usageAmount;
    private Integer partnerId;
    private String partnerName;
    private List<MobileOperator> mobileOperatorList;
    private List<ApiType> apiTypeList;
    private List<ApiTypeConsume> apiTypeConsumeList;

    public Integer getApiId() {
        return apiId;
    }

    public void setApiId(Integer apiId) {
        this.apiId = apiId;
    }

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

    public Integer getVendorId() {
        return vendorId;
    }

    public void setVendorId(Integer vendorId) {
        this.vendorId = vendorId;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public Long getWeekTotleCost() {
        return weekTotleCost;
    }

    public void setWeekTotleCost(Long weekTotleCost) {
        this.weekTotleCost = weekTotleCost;
    }

    public Long getMonthTotleCost() {
        return monthTotleCost;
    }

    public void setMonthTotleCost(Long monthTotleCost) {
        this.monthTotleCost = monthTotleCost;
    }

    public Long getConsumeTotleAmount() {
        return consumeTotleAmount;
    }

    public void setConsumeTotleAmount(Long consumeTotleAmount) {
        this.consumeTotleAmount = consumeTotleAmount;
    }

    public List<MobileOperator> getMobileOperatorList() {
        return mobileOperatorList;
    }

    public void setMobileOperatorList(List<MobileOperator> mobileOperatorList) {
        this.mobileOperatorList = mobileOperatorList;
    }

    public Integer getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(Integer partnerId) {
        this.partnerId = partnerId;
    }

    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

    public List<ApiType> getApiTypeList() {
        return apiTypeList;
    }

    public void setApiTypeList(List<ApiType> apiTypeList) {
        this.apiTypeList = apiTypeList;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getCurrMonthCost() {
        return currMonthCost;
    }

    public void setCurrMonthCost(Long currMonthCost) {
        this.currMonthCost = currMonthCost;
    }

    public Long getCurrDayCost() {
        return currDayCost;
    }

    public void setCurrDayCost(Long currDayCost) {
        this.currDayCost = currDayCost;
    }

    public List<ApiTypeConsume> getApiTypeConsumeList() {
        return apiTypeConsumeList;
    }

    public void setApiTypeConsumeList(List<ApiTypeConsume> apiTypeConsumeList) {
        this.apiTypeConsumeList = apiTypeConsumeList;
    }

    public Long getUsageAmount() {
        return usageAmount;
    }

    public void setUsageAmount(Long usageAmount) {
        this.usageAmount = usageAmount;
    }
}
