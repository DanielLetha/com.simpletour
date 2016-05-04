package com.simpletour.dao;

import com.simpletour.dao.sale.SaleAgreementRepository;
import com.simpletour.domain.sale.Agreement;
import com.simpletour.domain.sale.SaleApp;
import org.hibernate.jpa.criteria.path.SingularAttributeJoin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zt on 2016/4/29.
 */
@ContextConfiguration({"classpath:applicationContext.xml"})
public class SaleRepositoryTest extends AbstractTransactionalTestNGSpringContextTests {
    private final static Logger logger = LoggerFactory.getLogger(SaleRepositoryTest.class);
    @Autowired
    private SaleAgreementRepository saleRepository;

    private Map<String, Object> conditions;

    private Specification<Agreement> specification;

    @BeforeClass
    public void before() {
        logger.debug("before");
        conditions = new HashMap<String, Object>();
        conditions.put("sale_name", "OTA");
        conditions.put("tenantId", 1L);
        specification = doSpecification(conditions);
    }

    /** 根据销售端名称和产品ID等条件查询销售协议 */
    @Test
    public void testFindAll() {
        List<Agreement> agreements = saleRepository.findAll(specification);
        logger.debug("数量:" + agreements.size());
    }

    private Specification<Agreement> doSpecification(final Map<String, Object> conditions){
        return new Specification<Agreement>() {
            public Predicate toPredicate(Root<Agreement> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                if (conditions != null && conditions.size() > 0) {
                    List<Predicate> predicates = new ArrayList();
                    if (conditions.containsKey("tenantId")) {
                        predicates.add(cb.equal(root.get("tenantId").as(Long.class), conditions.get("tenantId")));
                    }

                    // 查询SaleApp
                    if (conditions.containsKey("sale_name")) {
                        Join<Agreement, SaleApp> join = root.join(
                                root.getModel().getSingularAttribute("saleApp", SaleApp.class), JoinType.INNER);
//                        Join<Agreement, SaleApp> join = root.join("saleApp", JoinType.INNER);
                        predicates.add(cb.like(join.get("name").as(String.class),"%" +  conditions.get("sale_name").toString() + "%"));
                    }
                    if (!predicates.isEmpty()) {
                        return cb.and(predicates.toArray(new Predicate[predicates.size()]));
//                        return cq.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
                    }
                }
                return cb.conjunction();
            }
        };
    }
}
