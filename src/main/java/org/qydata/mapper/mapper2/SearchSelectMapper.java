package org.qydata.mapper.mapper2;

import org.qydata.dst.customer.CustomerReqLog;

import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2017/11/16.
 */
public interface SearchSelectMapper {

    /**
     * 查询客户请求log
     * @param map
     * @return
     */
    public List<CustomerReqLog> queryCustomerReqLog(Map<String, Object> map);



}
