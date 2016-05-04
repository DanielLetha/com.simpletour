package com.simpletour.dao;

import com.simpletour.dao.sale.ISaleAgreementDao;
import com.simpletour.domain.order.Order;
import com.simpletour.domain.sale.Agreement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ContextConfiguration({"classpath:applicationContext.xml"})
public class AgreementJPATest extends AbstractTransactionalTestNGSpringContextTests {

    private final static Logger logger = LoggerFactory.getLogger(AgreementJPATest.class);
    @Autowired
    private ISaleAgreementDao agreementDao;

    private Map conditions;

    @BeforeTest
    public void before() {
        logger.debug("before");
        conditions = new HashMap();
        conditions.put("tenantId", 1L);
        conditions.put("sale_name", "%OTA%");
    }
    @Test
    public void testFindAll() {
        List<Agreement> list = agreementDao.findByConditions(conditions);
        logger.debug("数量:"+ list.size());
    }
}
