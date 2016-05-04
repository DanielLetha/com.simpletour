package com.simpletour.domain.sale;

import javax.persistence.*;
import java.util.List;


/**
 * Author:  wangLin
 * Mail  :  wl@simpletour.com
 * Date  :  2016/4/19.
 * Remark:  产品退改协议的实体类
 */
@Entity
@Table(name = "SALE_AGREEMENT_PRODUCT")
public class AgreementProduct{

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    /**
     * 业务系统产品ID
     */
    @Column(name = "product_id")
    private Long productId;

    /**
     * 销售协议
     */
    @ManyToOne
    @JoinColumn(name = "agreement_id")
    private Agreement agreement;

    /**
     * 产品退款细则
     */
    @OneToMany(mappedBy = "agreementProduct", cascade = {CascadeType.MERGE,CascadeType.REFRESH,CascadeType.REMOVE})
    @OrderBy("timing asc")
    private List<AgreementProductRefundRule> productRefundRules;

    @OneToMany(mappedBy = "agreementProduct", cascade = {CascadeType.REMOVE})
    @OrderBy("id asc")
    private List<AgreementProductPrice> productPrices;

    /**
     * 退款规则文字说明
     */
    @Column(length=1024)
    private String refund;

    /**
     * 截止下单时间，相对于出行那天的 0:00点，正数表示0点以后，负数表示0点之前
     */
    @Column
    private Integer deadline;

    /**
     * 备注
     */
    @Column(columnDefinition = "text")
    private String remark;

    /**
     * 公司ID
     */
    @Column(name = "tenant_id")
    private Long tenantId;

    /**
     * 版本号（乐观锁）
     */
    @Version
    private Integer version;

    public AgreementProduct() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Agreement getAgreement() {
        return agreement;
    }

    public void setAgreement(Agreement agreement) {
        this.agreement = agreement;
    }

    public List<AgreementProductRefundRule> getProductRefundRules() {
        return productRefundRules;
    }

    public void setProductRefundRules(List<AgreementProductRefundRule> productRefundRules) {
        this.productRefundRules = productRefundRules;
    }

    public String getRefund() {
        return refund;
    }

    public void setRefund(String refund) {
        this.refund = refund;
    }

    public Integer getDeadline() {
        return deadline;
    }

    public void setDeadline(Integer deadline) {
        this.deadline = deadline;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }
    public List<AgreementProductPrice> getProductPrices() {
        return productPrices;
    }

    public void setProductPrices(List<AgreementProductPrice> productPrices) {
        this.productPrices = productPrices;
    }
}
