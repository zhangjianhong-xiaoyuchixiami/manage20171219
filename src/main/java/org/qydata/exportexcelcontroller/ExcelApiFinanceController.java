package org.qydata.exportexcelcontroller;

import org.apache.commons.collections.map.HashedMap;
import org.qydata.dst.ApiFinance;
import org.qydata.entity.ApiRequestLog;
import org.qydata.entity.ApiVendor;
import org.qydata.service.ApiFinanceService;
import org.qydata.tools.ExportIoOperate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2017/1/20.
 */
@Controller
@RequestMapping("excel-api-finance")
public class ExcelApiFinanceController {

    @Autowired
    private ApiFinanceService apiFinanceService;

    //Api消费账单
    @RequestMapping("/find-all-api-record")
    public void findAllApiRecord(Integer vendorId, Integer apiTypeId, HttpServletResponse response) throws IOException {
        Map<String,Object> map = new HashedMap();
        map.put("vendorId",vendorId);
        map.put("apiTypeId",apiTypeId);
        List<ApiFinance> apiFinanceList = apiFinanceService.queryApiOverAllFinance(map);
        List<Map<String,Object>> list = new ArrayList<>();
        Map<String, Object> mapA = new HashMap<String, Object>();
        mapA.put("sheetName", "sheet1");
        list.add(mapA);
        ApiFinance apiFinance=null;
        for (int j = 0; j < apiFinanceList.size(); j++) {
            apiFinance = apiFinanceList.get(j);
            Map<String, Object> mapValue = new HashMap<String, Object>();
            //mapValue.put("apiName", apiFinance.getApiName());
            mapValue.put("apiTypeName", apiFinance.getApiTypeName());
            mapValue.put("vendorName", apiFinance.getVendorName());
            if(apiFinance.getWeekTotleCost() != null){
                mapValue.put("wekTotleCost", apiFinance.getWeekTotleCost()/100.0);
            }else {
                mapValue.put("wekTotleCost", 0.0);
            }
            if(apiFinance.getMonthTotleCost() != null){
                mapValue.put("monthTotleCost", apiFinance.getMonthTotleCost()/100.0);
            }else {
                mapValue.put("monthTotleCost", 0.0);
            }
            if(apiFinance.getConsumeTotleAmount() != null){
                mapValue.put("consumeTotleAmount", apiFinance.getConsumeTotleAmount()/100.0);
            }else {
                mapValue.put("consumeTotleAmount", 0.0);
            }
            list.add(mapValue);
        }
        String fileName = "产品消费账单";
        String columnNames[]= {"产品类型","产品供应商","上周消费（单位：元）","上月消费（单位：元）","消费总额（单位：元）"};//列名
        String keys[] = {"apiTypeName","vendorName","wekTotleCost","monthTotleCost","consumeTotleAmount"};//map中的key
        ExportIoOperate.excelEndOperator(list,keys,columnNames,fileName,response);
    }

    //Api消费账单-消费明细
    @RequestMapping("/find-all-api-record/detail")
    public void findAllApiDetailRecord(Integer apiId,String beginDate,String endDate,HttpServletResponse response) throws IOException {
        Map<String,Object> map = new HashedMap();
        map.put("apiId",apiId);
        if (beginDate != null && beginDate != "" ) {
            map.put("beginDate", beginDate+" "+"00:00:00");
        }
        if(endDate != null && endDate != ""){
            map.put("endDate", endDate+" "+"23:59:59");
        }
        List<ApiRequestLog> apiRequestLogList = apiFinanceService.queryApiDetailById(map);
        List<Map<String,Object>> list = new ArrayList<>();
        Map<String, Object> mapA = new HashMap<String, Object>();
        mapA.put("sheetName", "sheet1");
        list.add(mapA);
        ApiRequestLog apiRequestLog=null;
        for (int j = 0; j < apiRequestLogList.size(); j++) {
            apiRequestLog = apiRequestLogList.get(j);
            Map<String, Object> mapValue = new HashMap<String, Object>();
            if(apiRequestLog.getCompany() != null){
                mapValue.put("companyName", apiRequestLog.getCompany().getName());
            }else{
                mapValue.put("companyName", "");
            }
            if(apiRequestLog.getApiResponseLog() != null && apiRequestLog.getApiResponseLog().getCost() != null){
                mapValue.put("cost", apiRequestLog.getApiResponseLog().getCost()/100.0);
            }else {
                mapValue.put("cost", "");
            }
            if(apiRequestLog.getApiResponseLog() != null && apiRequestLog.getApiResponseLog().getResTime() != null){
                mapValue.put("resTime", apiRequestLog.getApiResponseLog().getResTime()/1000.0);
            }else {
                mapValue.put("resTime", "");
            }
            mapValue.put("createTime", apiRequestLog.getCreateTime());
            list.add(mapValue);
        }
        String fileName = "产品消费明细账单";
        String columnNames[]= {"公司名称","消费金额（单位：元）","响应时间（单位：秒）","创建时间"};//列名
        String keys[] = {"companyName","cost","resTime","createTime"};//map中的key
        ExportIoOperate.excelEndOperator(list,keys,columnNames,fileName,response);
    }

    /**
     * 以APIVendor统计消费信息
     * @return
     * @throws Exception
     */
    @RequestMapping("/find-all-api-vendor-consume")
    public String findAllApiVendorConsume(Integer vendorId,Integer partnerId ,HttpServletResponse response) throws IOException {
        Map<String,Object> map = new HashMap<>();
        List<ApiVendor> apiVendorList  = apiFinanceService.queryApiVendorName(map);
        System.out.println(apiVendorList);
        if(vendorId != null){
            map.put("vendorId",vendorId);
        }
        if(partnerId != null){
            map.put("partnerId",partnerId);
        }
        List<ApiFinance> apiFinanceList = apiFinanceService.queryApiVendor(map);
        List<ApiFinance> apiFinances = new ArrayList<>();
        if (apiFinanceList != null){
            for (int i=0; i<apiFinanceList.size(); i++){
                ApiFinance apiFinance = apiFinanceList.get(i);
                ApiFinance apiFinanceOne = new ApiFinance();
                apiFinanceOne.setVendorId(apiFinance.getVendorId());
                apiFinanceOne.setVendorName(apiFinance.getVendorName());
                apiFinanceOne.setApiTypeList(apiFinance.getApiTypeList());
                if(apiFinance.getPartnerName() != null){
                    apiFinanceOne.setPartnerName(apiFinance.getPartnerName());
                }
                if(apiFinance.getPartnerId() != null){
                    apiFinanceOne.setPartnerId(apiFinance.getPartnerId());
                }
                if(apiFinance.getWeekTotleCost() != null){
                    apiFinanceOne.setWeekTotleCost(apiFinance.getWeekTotleCost());
                }else{
                    apiFinanceOne.setWeekTotleCost(0L);
                }
                if(apiFinance.getMonthTotleCost() != null){
                    apiFinanceOne.setMonthTotleCost(apiFinance.getMonthTotleCost());
                }else {
                    apiFinanceOne.setMonthTotleCost(0L);
                }

                if(apiFinance.getConsumeTotleAmount() != null){
                    apiFinanceOne.setConsumeTotleAmount(apiFinance.getConsumeTotleAmount());
                }else {
                    apiFinanceOne.setConsumeTotleAmount(0L);
                }
                if(apiFinance.getBalance() != null && apiFinance.getConsumeTotleAmount() != null){
                    apiFinanceOne.setBalance(apiFinance.getBalance()-apiFinance.getConsumeTotleAmount());
                }else if (apiFinance.getBalance() != null && apiFinance.getConsumeTotleAmount() == null){
                    apiFinanceOne.setBalance(apiFinance.getBalance());
                }else if (apiFinance.getBalance() == null && apiFinance.getConsumeTotleAmount() == null){
                    apiFinanceOne.setBalance(0L);
                }else {
                    apiFinanceOne.setBalance(-apiFinance.getConsumeTotleAmount());
                }

                apiFinances.add(apiFinanceOne);
            }
        }

        List<Map<String,Object>> list = new ArrayList<>();
        Map<String, Object> mapA = new HashMap<String, Object>();
        mapA.put("sheetName", "sheet1");
        list.add(mapA);
        ApiFinance apiFinance=null;
        for (int j = 0; j < apiFinances.size(); j++) {
            apiFinance = apiFinances.get(j);
            Map<String, Object> mapValue = new HashMap<String, Object>();
            if(apiFinance.getVendorName() != null){
                mapValue.put("vendorName", apiFinance.getVendorName());
            }else{
                mapValue.put("vendorName", "");
            }
            if(apiFinance.getPartnerName() != null){
                mapValue.put("partnerName", apiFinance.getPartnerName());
            }else{
                mapValue.put("partnerName", "");
            }
            if(apiFinance.getConsumeTotleAmount() != null){
                mapValue.put("consumeTotleAmount", apiFinance.getConsumeTotleAmount()/100.0);
            }else {
                mapValue.put("consumeTotleAmount", 0.0);
            }
            if(apiFinance.getBalance() != null){
                mapValue.put("balance", apiFinance.getBalance()/100.0);
            }else {
                mapValue.put("balance", 0.0);
            }
            if(apiFinance.getWeekTotleCost() != null){
                mapValue.put("weekTotleCost", apiFinance.getWeekTotleCost()/100.0);
            }else {
                mapValue.put("weekTotleCost", 0.0);
            }
            if(apiFinance.getMonthTotleCost() != null){
                mapValue.put("monthTotleAmount", apiFinance.getMonthTotleCost()/100.0);
            }else {
                mapValue.put("monthTotleAmount", 0.0);
            }
            list.add(mapValue);
        }
        String fileName = "供应商财务账单";
        String columnNames[]= {"供应商","合作公司","消费金额（单位：元）","所剩余额（单位：元）","上周消费（单位：元）","上月消费（单位：元）"};//列名
        String keys[] = {"vendorName","partnerName","consumeTotleAmount","balance","weekTotleCost","monthTotleAmount"};//map中的key
        ExportIoOperate.excelEndOperator(list,keys,columnNames,fileName,response);

        return "/finance/apiVendorRecord";
    }

}
