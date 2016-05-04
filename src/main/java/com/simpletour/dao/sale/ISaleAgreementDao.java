package com.simpletour.dao.sale;

import com.simpletour.domain.sale.Agreement;

import java.util.List;
import java.util.Map;

/**
 * Created by zt on 2016/5/3.
 */
public interface ISaleAgreementDao {
    /** order条件查询 */
    List<Agreement> findByConditions(Map<String, Object> conditions);
}
