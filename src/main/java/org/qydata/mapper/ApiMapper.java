package org.qydata.mapper;

import org.qydata.dst.CustomerApiPartner;
import org.qydata.entity.*;

import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2017/2/28.
 */
public interface ApiMapper {

    /**
     * 查询产品
     * @return
     * @throws Exception
     */
    public List<Api> queryApi(Map<String,Object> map)throws Exception;

    /**
     * 查询产品类型
     * @return
     * @throws Exception
     */
    public List<ApiType> queryApiType()throws Exception;

    /**
     * 查询产品供应商
     * @return
     * @throws Exception
     */
    public List<ApiVendor> queryApiVendor()throws Exception;

    /**
     * 根据产品类型查询产品供应商
     * @return
     * @throws Exception
     */
    public List<ApiVendor> queryApiVendorByApiTypeId(Integer id)throws Exception;

    /**
     * 以客户纬度查询产品
     * @param map
     * @return
     * @throws Exception
     */
    public List<CustomerApiPartner> queryApiByCompanyId(Map<String,Object> map)throws Exception;

    /**
     * 查询所有公司
     * @return
     * @throws Exception
     */
    public List<Company> queryCompany()throws Exception;

    /**
     * 查询api最近请求的失败次数
     * @return
     */
    public List<ApiBan> queryApiMonitor(Map<String,Object> map);

    /**
     * 查询产品类型名称、子类型名称、供应商名称
     * @param apiId
     * @return
     */
    public Api queryApiTypeNameStidNameVendorNameByApiId(Integer apiId);

    /**
     * 查看产品改价记录
     * @return
     */
    public List<ApiPriceChanceLog> queryApiPriceChangeLog(Map<String,Object> map);

    /**
     * 新增产品价格记录
     * @param tid
     * @param vid
     * @param pic
     * @param date
     * @return
     */
    public boolean addApiPriceChangeLog(Integer tid,Integer vid,Double pic,String date) throws Exception;


}
