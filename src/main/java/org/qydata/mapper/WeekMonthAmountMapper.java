package org.qydata.mapper;

import org.qydata.entity.WeekMonthAmount;

import java.util.List;

/**
 * Created by Administrator on 2017/1/5.
 */
public interface WeekMonthAmountMapper {

    /**
     * 统计客户每周的充值数据
     * @return
     * @throws Exception
     */
    public List<WeekMonthAmount> getAllCustomerWeekRechargeRecord()throws Exception;

    /**
     * 统计客户每月的充值数据
     * @return
     * @throws Exception
     */
    public List<WeekMonthAmount> getAllCustomerMonthRechargeRecord()throws Exception;

    /**
     * 插入客户每周的数据
     * @param weekAmountList
     * @return
     * @throws Exception
     */
    public boolean addWeekRecord(List<WeekMonthAmount> weekAmountList)throws Exception;

    /**
     * 插入客户每月的数据
     * @param monthAmountList
     * @return
     * @throws Exception
     */
    public boolean addMonthRecord(List<WeekMonthAmount> monthAmountList)throws Exception;

    /**
     * 统计客户每周的Api消费数据
     * @return
     * @throws Exception
     */
    public List<WeekMonthAmount> getAllCustomerApiWeekConsumeRecord()throws Exception;

    /**
     * 统计客户每月的Api消费数据
     * @return
     * @throws Exception
     */
    public List<WeekMonthAmount> getAllCustomerApiMonthConsumeRecord()throws Exception;

}