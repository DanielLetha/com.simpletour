package com.simpletour.dao;

import com.simpletour.domain.order.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ContextConfiguration({"classpath:applicationContext.xml"})
public class OrderJPATest extends AbstractTransactionalTestNGSpringContextTests {

    private final static Logger logger = LoggerFactory.getLogger(OrderJPATest.class);
    @Autowired
    private IOrderDao orderDao;

    private Map conditions;

    @BeforeClass
    public void before() {
        logger.debug("before");
        conditions = new HashMap();
        conditions.put("status" ,Order.Status.PENDING);
        conditions.put("name" ,"ttt");
    }
    @Test
    public void testFindAll() {
        List<Order> list = orderDao.findByConditions(conditions);
        logger.debug("test");
    }

}
