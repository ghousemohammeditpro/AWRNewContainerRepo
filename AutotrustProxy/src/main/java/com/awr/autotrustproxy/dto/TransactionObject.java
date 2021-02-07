package com.awr.autotrustproxy.dto;

public class TransactionObject {
    public TransactionObject() {
        super();
    }
    private String txnId;
    private String txnSource;
    private String lpCustomerId;
    private String customerId;
    private String custAccountId;
    private String partnerCode;
    //    private String partnerId;
    private String trxDate;
    private String sourceTxnType;
    private String txnAmount;
    private String paidAmount;
    private String sourceDocNumber;
    private String sourceDocId;
    private String trxNumber;
    private String totalPoints;
    //    private String awardStatus;
    //    private String subLedgerStatus;
    //    private String attributeCategory;
    //    private String attribute1;
    //    private String attribute2;
    //    private String attribute3;
    //    private String attribute4;
    //    private String attribute5;
    //    private String trxId;
    //    private String currencyCode;
    //    private String exchangeRate;
    private String batchId;
    private String recordStatusFlag;
    private String batchNumber;
    //    private String remarks;
    //    private String customerCode;
    //    private String fcAmount;
    //    private String glTransferFlag;
    //    private String glTransferDate;
    //    private String mobileNumber;
    private String lob;
    private String brand;
    private String branch;
    //    private String storeId;
    //    private String posId;
    private String organizationId;
    //    private String organizationCode;
    //    private String accountCategoryType;
    private String pointsValue;
    private String blooomTxnId;
    //    private String awardingStatus;

    public void setTxnId(String txnId) {
        this.txnId = txnId;
    }

    public String getTxnId() {
        return txnId;
    }

    public void setTxnSource(String txnSource) {
        this.txnSource = txnSource;
    }

    public String getTxnSource() {
        return txnSource;
    }

    public void setLpCustomerId(String lpCustomerId) {
        this.lpCustomerId = lpCustomerId;
    }

    public String getLpCustomerId() {
        return lpCustomerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustAccountId(String custAccountId) {
        this.custAccountId = custAccountId;
    }

    public String getCustAccountId() {
        return custAccountId;
    }

    public void setPartnerCode(String partnerCode) {
        this.partnerCode = partnerCode;
    }

    public String getPartnerCode() {
        return partnerCode;
    }

    public void setTrxDate(String trxDate) {
        this.trxDate = trxDate;
    }

    public String getTrxDate() {
        return trxDate;
    }

    public void setSourceTxnType(String sourceTxnType) {
        this.sourceTxnType = sourceTxnType;
    }

    public String getSourceTxnType() {
        return sourceTxnType;
    }

    public void setTxnAmount(String txnAmount) {
        this.txnAmount = txnAmount;
    }

    public String getTxnAmount() {
        return txnAmount;
    }

    public void setPaidAmount(String paidAmount) {
        this.paidAmount = paidAmount;
    }

    public String getPaidAmount() {
        return paidAmount;
    }

    public void setSourceDocNumber(String sourceDocNumber) {
        this.sourceDocNumber = sourceDocNumber;
    }

    public String getSourceDocNumber() {
        return sourceDocNumber;
    }

    public void setSourceDocId(String sourceDocId) {
        this.sourceDocId = sourceDocId;
    }

    public String getSourceDocId() {
        return sourceDocId;
    }

    public void setTrxNumber(String trxNumber) {
        this.trxNumber = trxNumber;
    }

    public String getTrxNumber() {
        return trxNumber;
    }

    public void setTotalPoints(String totalPoints) {
        this.totalPoints = totalPoints;
    }

    public String getTotalPoints() {
        return totalPoints;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setRecordStatusFlag(String recordStatusFlag) {
        this.recordStatusFlag = recordStatusFlag;
    }

    public String getRecordStatusFlag() {
        return recordStatusFlag;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public String getLob() {
        return lob;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getBrand() {
        return brand;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getBranch() {
        return branch;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setPointsValue(String pointsValue) {
        this.pointsValue = pointsValue;
    }

    public String getPointsValue() {
        return pointsValue;
    }

    public void setBlooomTxnId(String blooomTxnId) {
        this.blooomTxnId = blooomTxnId;
    }

    public String getBlooomTxnId() {
        return blooomTxnId;
    }

}
