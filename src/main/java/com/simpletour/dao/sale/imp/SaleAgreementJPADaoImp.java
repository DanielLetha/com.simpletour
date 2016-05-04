package com.simpletour.dao.sale.imp;


import com.simpletour.dao.sale.ISaleAgreementDao;
import com.simpletour.domain.sale.Agreement;
import com.simpletour.domain.sale.SaleApp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class SaleAgreementJPADaoImp implements ISaleAgreementDao {

    private final static Logger logger = LoggerFactory.getLogger(SaleAgreementJPADaoImp.class);
    @PersistenceContext
    private EntityManager em;

    public List<Agreement> findByConditions(Map<String, Object> conditions) {
        List<Predicate> predicates = new ArrayList();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq=cb.createQuery(Agreement.class);
        Root<Agreement> agreementRoot = cq.from(Agreement.class);
        EntityType<Agreement> agreementInfo=agreementRoot.getModel();
        if (conditions.containsKey("tenantId")) {
            predicates.add(cb.equal(agreementRoot.get("tenantId").as(Long.class), conditions.get("tenantId")));
        }

        // 关联查询SaleApp
        CriteriaQuery cqa=cb.createQuery(SaleApp.class);
        if (conditions.containsKey("sale_name")) {
            Join<Agreement,SaleApp> join = agreementRoot.join(agreementInfo.getSingularAttribute("saleApp", SaleApp.class), JoinType.INNER);
//            Join<Agreement,SaleApp> join = agreementRoot.join("saleApp");
            predicates.add(cb.like(join.get("name").as(String.class), "%" + conditions.get("sale_name").toString() + "%"));
        }
        cq.where(predicates.toArray(new Predicate[predicates.size()]));
        List<Agreement> agreements = em.createQuery(cq).getResultList();
        return agreements;
    }
}
