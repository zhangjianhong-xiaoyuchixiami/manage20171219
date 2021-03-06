package org.qydata.mapper.mapper2;

import org.qydata.dst.ApiWeb;
import org.qydata.dst.VendorHistoryBill;
import org.qydata.dst.VendorHistoryBillDetail;
import org.qydata.entity.ApiVendor;
import org.qydata.entity.Partner;
import org.qydata.entity.VendorHistoryBillUpdateLog;

import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2017/8/30.
 */
public interface VendorHistoryBillSelectMapper {

    /**
     * 查询供应商历史财务账单
     * @param map
     * @return
     */
    public List<VendorHistoryBill> queryVendorHistoryBill(Map<String, Object> map);

    /**
     * 查询供应商截止上月静态消费
     * @return
     */
    public List<VendorHistoryBill> queryVendorStaticConsumeAmount();

    /**
     * 查询供应商当月实时消费（不包括当天）
     * @param map
     * @return
     */
    public List<VendorHistoryBill> queryVendorCurrMonthRealTimeConsume(Map<String, Object> map);

    /**
     * 查询供应商当天实时消费
     * @param map
     * @return
     */
    public List<VendorHistoryBill> queryVendorCurrDayRealTimeConsume(Map<String, Object> map);

    /**
     * 查询供应商截至上月末充值总额
     * @param map
     * @return
     */
    public List<VendorHistoryBill> queryVendorChargeTot(Map<String,Object> map);

    /**
     * 查询所有供应商
     * @return
     */
    public List<ApiVendor> queryAllVendor();

    /**
     * 查询所有合作伙伴
     * @return
     */
    public List<Partner> queryAllPartner();

    /**
     * 查询消费的月份
     * @return
     */
    public List<String> queryAllConsumeTime();

    /**
     * 查询供应商历史财务账单明细
     * @param map
     * @return
     */
    public List<VendorHistoryBillDetail> queryVendorHistoryBillDetail(Map<String, Object> map);

    /**
     * 根据供应商Id查询产品
     * @param vid
     * @return
     */
    public List<ApiWeb> queryApiByVendorId(Integer vid);

    /**
     * 根据Id查看锁定状态
     * @param id
     * @return
     */
    public Integer queryVendorHistoryBillLockById(Integer id);

    /**
     * 查看修改日志
     * @param map
     * @return
     */
    public List<VendorHistoryBillUpdateLog> queryVendorHistoryBillDetailUpdateLog(Map<String, Object> map);


}
