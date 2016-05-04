package com.simpletour.domain.order;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Mario on 2016/4/20.
 */
@Entity
@Table(name = "ORD_ORDER")
public class Order {

    public enum Status {
        PENDING("等待确认", "pending", new String[]{"finished", "canceled", "closed"}),
        CANCELED("已取消", "canceled", true),
        CLOSED("已关闭", "closed", true),
        FINISHED("已完成", "finished", new String[]{"canceled"});

        private String remark, value;

        /**
         * 是否更新库存
         */
        private boolean flushStock = false;
        /**
         * 可以转变的状态
         */
        private List<String> transitions;

        Status(String remark, String value, boolean flushStock) {
            this.remark = remark;
            this.value = value;
            this.flushStock = flushStock;
        }

        Status(String remark, String value, String[] transitions) {
            this.remark = remark;
            this.value = value;
            this.transitions = Arrays.asList(transitions);
        }

        public String getRemark() {
            return remark;
        }

        public String getValue() {
            return value;
        }

        public boolean isFlushStock() {
            return flushStock;
        }

        public List<String> getTransitions() {
            return transitions;
        }
    }

    /**
     * 主键id
     */
    @Id()
    @Column(name = "ID")
    private Long id;

    /**
     * 关联的销售端
     */
    @Column(name = "app_id")
    private Long saleAppId;

    /**
     * 交易金额
     */
    @Column
    private Integer amount;

    /**
     * 关联的订单状态
     */
    @Column
    @Enumerated(EnumType.STRING)
    private Status status;

    /**
     * 订单预留时间
     */
    @Column(name = "reserve_time")
    private Long reserveTime;

    /**
     * 关联的订单项
     */
    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    private List<Item> items;

    @Version
    private Integer version;

    public Long getId() {
        return this.id;
    }

    public void setId(Long aLong) {
        this.id = aLong;
    }

    public Long getSaleAppId() {
        return saleAppId;
    }

    public void setSaleAppId(Long saleAppId) {
        this.saleAppId = saleAppId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getReserveTime() {
        return reserveTime;
    }

    public void setReserveTime(Long reserveTime) {
        this.reserveTime = reserveTime;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

}
