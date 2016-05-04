package com.simpletour.dao.imp;

import com.simpletour.dao.IOrderDao;
import com.simpletour.domain.order.Item;
import com.simpletour.domain.order.Order;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import javax.persistence.metamodel.EntityType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by zt on 2016/4/28.
 */
@Repository
public class OrderJPADaoImp implements IOrderDao {

    @PersistenceContext
    private EntityManager em;

    public List<Order> findByConditions(Map<String, Object> conditions) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq=cb.createQuery(Order.class);
        Root<Order> orderRoot = cq.from(Order.class);
        cq.select(orderRoot);
        EntityType<Order> orderInfo=orderRoot.getModel();
        if (conditions.containsKey("status")) {
            cb.equal(orderRoot.get("status"), conditions.get("status"));
        }

        // 关联查询Item
        CriteriaBuilder cbi = em.getCriteriaBuilder();
        CriteriaQuery cqi=cbi.createQuery(Item.class);
        Root<Order> itemRoot = cqi.from(Item.class);
        List<Predicate> list = new ArrayList<Predicate>();
        if (conditions.containsKey("saleAppId")) {
            list.add(cbi.equal(itemRoot.get("saleAppId").as(Long.class), conditions.get("saleAppId")));
        }
        if (conditions.containsKey("name")) {
            list.add(cbi.equal(itemRoot.get("name").as(String.class), conditions.get("name")));
        }
        if (list != null && !list.isEmpty()) {
            Join<Order, Item> join = orderRoot.join(orderInfo.getList("items",Item.class), JoinType.INNER);
            Predicate[] p = new Predicate[list.size()];
            cqi.where(p);
        }
        List<Order> orders = em.createQuery(cq).getResultList();
        return orders;
    }
}
