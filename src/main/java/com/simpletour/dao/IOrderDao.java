package com.simpletour.dao;

        import com.simpletour.domain.order.Order;

        import java.util.List;
        import java.util.Map;

/**
 * Created by zt on 2016/4/28.
 */
public interface IOrderDao {

    /** order条件查询 */
    List<Order> findByConditions(Map<String, Object> conditions);
}
