package com.simpletour.domain.sale;

import javax.persistence.*;
import java.util.List;

/**
 * 销售协议实体类
 * Created by YuanYuan/yuanyuan@simpletour.com on 2016/4/19.
 *
 * @since 2.0-SNAPSHOT
 */
@Entity
@Table(name = "SALE_AGREEMENT")
public class Agreement {
    /**
     * 销售协议主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    /**
     * 销售端
     */
    @ManyToOne
    @JoinColumn(name = "app_id")
    private SaleApp saleApp;
    /**
     * 公司ID
     */
    @Column(name = "tenant_id")
    private Long tenantId;
    /**
     * 状态
     */
    @Column
    private Boolean enabled = true;
    /**
     * 备注
     */
    @Column(columnDefinition = "text")
    private String remark;

    @OneToMany(mappedBy = "agreement")
    @OrderBy("id asc")
    private List<AgreementProduct> agreementProducts;
    /**
     * 乐观锁
     */
    @Version
    private Integer version;

    public Agreement() {
    }

    public Agreement(Long id) {
        this.id = id;
    }

    public Agreement(Long id,SaleApp saleApp) {
        this.id = id;
        this.saleApp = saleApp;
    }

    public Agreement(Long id,List<AgreementProduct> agreementProducts) {
        this.id = id;
        this.agreementProducts = agreementProducts;
    }

    public Agreement(SaleApp saleApp, Boolean enabled, String remark) {
        this.saleApp = saleApp;
        this.enabled = enabled;
        this.remark = remark;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SaleApp getSaleApp() {
        return saleApp;
    }

    public void setSaleApp(SaleApp saleApp) {
        this.saleApp = saleApp;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public Boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
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

    public List<AgreementProduct> getAgreementProducts() {
        return agreementProducts;
    }

    public void setAgreementProducts(List<AgreementProduct> agreementProducts) {
        this.agreementProducts = agreementProducts;
    }
}
