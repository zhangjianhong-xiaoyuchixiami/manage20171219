package org.qydata.mapper;

import org.qydata.dst.ApiFinance;

import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2017/1/12.
 */
public interface ApiFinanceMapper {

    /**
     *Api财务总览
     * @param map
     * @return
     * @throws Exception
     */
   public List<ApiFinance> queryApiOverAllFinance(Map<String,Object> map)throws Exception;

    /**
     * 查询供应商和api
     * @param map
     * @return
     * @throws Exception
     */
   public List<ApiFinance> queryApiVendorAndApi(Map<String,Object> map)throws Exception;
}
