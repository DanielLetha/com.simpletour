package com.simpletour.dao.sale;

import com.simpletour.domain.sale.Agreement;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by zt on 2016/4/29.
 */
public interface SaleAgreementRepository extends PagingAndSortingRepository<Agreement, Long>, JpaSpecificationExecutor<Agreement> {

}
