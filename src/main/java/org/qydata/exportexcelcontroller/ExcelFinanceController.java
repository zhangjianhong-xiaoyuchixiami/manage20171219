package org.qydata.exportexcelcontroller;

import net.sf.json.JSONArray;
import org.apache.commons.collections.map.HashedMap;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.qydata.dst.CustomerApiType;
import org.qydata.dst.CustomerApiVendor;
import org.qydata.dst.CustomerFinance;
import org.qydata.entity.CustomerBalanceLog;
import org.qydata.entity.Dept;
import org.qydata.entity.User;
import org.qydata.entity.WeekMonthAmount;
import org.qydata.service.CustomerFinanceService;
import org.qydata.service.CustomerService;
import org.qydata.tools.ExcelUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

import static java.lang.Integer.parseInt;

/**
 * Created by Administrator on 2017/1/10.
 */
@Controller
@RequestMapping("/excel-finance")
public class ExcelFinanceController {

    private final Logger log = Logger.getLogger(this.getClass());
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerFinanceService customerFinanceService;
    /**
     * 查找公司财务账单
     * @param request

     * @return
     */
    @RequestMapping(value = "/find-all-customer")
    public void findAllCustomer(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String companyName = request.getParameter("content");
        Map<String,Object> map = new HashMap<String,Object>();
        List customerTypeIdList = new ArrayList();
        if(companyName!=null){
            map.put("content",companyName);
        }
        customerTypeIdList.add(1);
        map.put("customerTypeIdList",customerTypeIdList);

        List<CustomerFinance> customerFinances = null;
        try {
            customerFinances  =customerFinanceService.queryCompanyCustomerOverAllFinance(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Map<String,Object>> list=createExcelRecord(customerFinances);
        String fileName = "客户财务报表文件";
        String columnNames[]= {"公司名称","周充值（单位：元）","周消费（单位：元）","月充值（单位：元）","月消费（单位：元）","充值总额（单位：元）","消费总额（单位：元）","余额（单位：元）"};//列名
        String keys[] = {"companyName","chargeWeekTotleAmount","consumeWeekTotleAmount","chargeMonthTotleAmount","consumeMonthTotleAmount","chargeTotleAmount","consumeMonthTotleAmount","balance"};//map中的key
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            ExcelUtil.createWorkBook(list,keys,columnNames).write(os);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] content = os.toByteArray();
        InputStream is = new ByteArrayInputStream(content);
        // 设置response参数，可以打开下载页面
        response.reset();
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename="+ new String((fileName + ".xls").getBytes(), "iso-8859-1"));
        ServletOutputStream out = null;
        try {
            out = response.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            bis = new BufferedInputStream(is);
            bos = new BufferedOutputStream(out);
            byte[] buff = new byte[2048];
            int bytesRead;
            // Simple read/write loop.
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        } catch (final IOException e) {
            throw e;
        } finally {
            if (bis != null)
                bis.close();
            if (bos != null)
                bos.close();
        }
    }

    private List<Map<String, Object>> createExcelRecord(List<CustomerFinance> customerFinances) {
        List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("sheetName", "sheet1");
        listmap.add(map);
        CustomerFinance customerFinance=null;
        for (int j = 0; j < customerFinances.size(); j++) {
            customerFinance=customerFinances.get(j);
            Map<String, Object> mapValue = new HashMap<String, Object>();
            mapValue.put("companyName", customerFinance.getCompanyName());
            mapValue.put("chargeWeekTotleAmount", 100.0);
            mapValue.put("consumeWeekTotleAmount", 100.0);
            mapValue.put("chargeMonthTotleAmount", 100.0);
            mapValue.put("consumeMonthTotleAmount", 100.0);
            mapValue.put("chargeTotleAmount", 100.0);
            mapValue.put("consumeMonthTotleAmount", 100.0);
            mapValue.put("balance", 100.0);
            listmap.add(mapValue);
        }
        return listmap;
    }



    /**
     * 通过部门编号查找公司财务账单
     * @param request
     * @param model
     * @param content
     * @return
     */
    @RequestMapping(value = ("/find-all-customer-by-dept-id"))
    public String findAllCustomerByDeptId(HttpServletRequest request,Model model,String content){
        User user = (User)request.getSession().getAttribute("userInfo");
        List<Dept> deptList = user.getDept();
        List deptIdList = new ArrayList();
        if (deptList.size() > 0) {
            for (int i = 0; i < deptList.size(); i++) {
                deptIdList.add(deptList.get(i).getId());
            }
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("deptIdList", deptIdList);
            if (content != null) {
                map.put("content", content);
            }
            List customerTypeIdList = new ArrayList();
            customerTypeIdList.add(1);
            map.put("customerTypeIdList", customerTypeIdList);
            List<CustomerFinance> customerFinanceList = null;
            try {
                customerFinanceList = customerFinanceService.queryCompanyCustomerOverAllFinance(map);
            } catch (Exception e) {
                e.printStackTrace();
            }
            model.addAttribute("customerFinanceList",customerFinanceList);
            model.addAttribute("content",content);
            return "/finance/customerFinancialAccount";
        }else {
            model.addAttribute("deptIdList", deptIdList);
            model.addAttribute("customerFinanceList",null);
            return "/finance/customerFinancialAccount";
        }
    }
    /**
     * 指定账号充值记录
     * @param customerId
     * @param companyName
     * @param beginDate
     * @param endDate
     * @param reasonId
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/find-all-customer/find-all-customer-recharge-log-by-customer-id")
    public String findAllCustomerRechargeLogByCustomerId(Integer customerId,String companyName, String beginDate, String endDate, String [] reasonId, HttpServletRequest request, Model model){
        Subject subject = SecurityUtils.getSubject();
        if (subject.hasRole("sell")){
            User user = (User)request.getSession().getAttribute("userInfo");
            List<Dept> deptList = user.getDept();
            List deptIdList = new ArrayList();
            for(int i =0;i<deptList.size();i++){
                deptIdList.add(deptList.get(i).getId());
            }
            List customerIdList = null;
            try {
                customerIdList = customerService.findAllCustomerIdByDeptId(deptIdList);
                if (!customerIdList.contains(customerId)){
                    return "../view/unauthUrl";
                }
            } catch (Exception e) {
                log.error("findAllRechargeCustomerBalanceLogByCustomerId:授权失败");
                e.printStackTrace();
            }
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("customerId", customerId);
        List<Integer> reasonIdList = new ArrayList<>();
        if (reasonId != null && reasonId.length >0) {
            for(int i=0;i<reasonId.length;i++){
                reasonIdList.add(parseInt(reasonId[i]));
            }
        }else {
            reasonIdList.add(1);
            reasonIdList.add(2);
            reasonIdList.add(3);
        }
        map.put("reasonIdList", reasonIdList);
        if (beginDate != null && beginDate != "" ) {
            map.put("beginDate", beginDate+" "+"00:00:00");
        }
        if(endDate != null && endDate != ""){
            map.put("endDate", endDate+" "+"23:59:59");
        }
        List<CustomerBalanceLog> customerBalanceLogList = null;
        long totleAmount = 0;
        try {
            customerBalanceLogList = customerFinanceService.queryCompanyCustomerRechargeRecordByCustomerId(map);
            if(customerBalanceLogList != null && customerBalanceLogList.size()>0) {
                Iterator<CustomerBalanceLog> iterator = customerBalanceLogList.iterator();
                while (iterator.hasNext()) {
                    CustomerBalanceLog customerBalanceLog = iterator.next();
                    totleAmount = totleAmount + customerBalanceLog.getAmount();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("customerBalanceLogList",customerBalanceLogList);
        model.addAttribute("customerId",customerId);
        model.addAttribute("reasonIdArray",reasonId);
        model.addAttribute("beginDate",beginDate);
        model.addAttribute("endDate",endDate);
        model.addAttribute("companyName",companyName);
        model.addAttribute("totleAmount",totleAmount);
        return "/finance/customerBalanceLogRecord";
    }

    /**
     * 指定账号Api消费记录
     * @return
     */
    @RequestMapping("/find-all-customer/find-all-customer-api-consume-record-by-customer-id")
    public String findAllApiConsumeRecordByCustomerId(Integer customerId,Integer apiTypeId,Integer apiVendorId,String companyName,Model model){
        Map<String,Object> map = new HashedMap();
        map.put("customerId",customerId);
        List<CustomerApiType> customerApiTypes = null;
        try {
            customerApiTypes = customerFinanceService.queryCompanyCustomerApiConsumeRecordByCustomerId(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<CustomerApiType> customerApiTypes1 = null;
        List<CustomerApiVendor> customerApiVendors = null;
        if(apiTypeId != null){
            map.put("apiTypeId",apiTypeId);
            try {
                customerApiTypes1 = customerFinanceService.queryCompanyCustomerApiConsumeRecordByCustomerId(map);
                if (customerApiTypes1 != null) {
                    for (int i = 0; i < customerApiTypes1.size(); i++) {
                        CustomerApiType customerApiType = customerApiTypes1.get(i);
                        customerApiVendors = customerApiType.getCustomerApiVendors();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(apiVendorId != null){
            map.put("apiVendorId",apiVendorId);
        }
        List<CustomerApiType> customerApiTypeList = null;
        long totleAmount =0;
        try {
            customerApiTypeList = customerFinanceService.queryCompanyCustomerApiConsumeRecordByCustomerId(map);
            if (customerApiTypeList != null) {
                for (int i = 0; i < customerApiTypeList.size(); i++) {
                    CustomerApiType customerApiType = customerApiTypeList.get(i);
                    totleAmount = totleAmount + customerApiType.getTotlePrice();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("customerApiTypeList", customerApiTypeList);
        model.addAttribute("customerApiTypes",customerApiTypes);
        model.addAttribute("customerApiVendors",customerApiVendors);
        model.addAttribute("customerId",customerId);
        model.addAttribute("apiTypeId",apiTypeId);
        model.addAttribute("apiVendorId",apiVendorId);
        model.addAttribute("companyName",companyName);
        model.addAttribute("totleAmount",totleAmount);
        return "/finance/customerApiConsumeRecord";
    }

    /**
     * 二级级联
     * @param request
     * @return
     */
    @RequestMapping("find-api-vendor-by-api-type-id")
    @ResponseBody
    public String findApiVendorByApiTypeId(HttpServletRequest request){
        Integer apiTypeId = parseInt(request.getParameter("apiTypeId"));
        Integer customerId = parseInt(request.getParameter("customerId"));
        Map<String, Object> map = new HashedMap();
        map.put("customerId", customerId);
        map.put("apiTypeId", apiTypeId);
        List<CustomerApiType> customerApiTypeList = null;
        CustomerApiType customerApiType = null;
        List<CustomerApiVendor> customerApiVendorList = null;
        try {
            customerApiTypeList = customerFinanceService.queryCompanyCustomerApiConsumeRecordByCustomerId(map);
            if(customerApiTypeList != null) {
                for (int i = 0; i < customerApiTypeList.size(); i++) {
                    customerApiType = customerApiTypeList.get(i);
                    customerApiVendorList = customerApiType.getCustomerApiVendors();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        JSONArray jsonArray = JSONArray.fromObject(customerApiVendorList);
        return jsonArray.toString();
    }

    /**
     * 指定账号Api消费明细记录
     * @param customerId
     * @param apiTypeId
     * @param companyName
     * @param apiTypeName
     * @param model
     * @return
     */
    @RequestMapping("/find-all-customer/find-all-customer-api-consume-record-by-customer-id/detail/{customerId}")
    public String findAllApiConsumeDetailRecordByCustomerId(@PathVariable Integer customerId, Integer apiTypeId, String companyName, String apiTypeName, Model model){
        Map<String,Object> map = new HashedMap();
        map.put("customerId",customerId);
        map.put("apiTypeId",apiTypeId);
        List<CustomerApiVendor> customerApiVendorList = null;
        long totleAmount = 0;
        try {
            customerApiVendorList = customerFinanceService.queryCompanyCustomerApiDetailConsumeRecordByCustomerId(map);
            if(customerApiVendorList != null) {
                for (int i = 0; i < customerApiVendorList.size(); i++) {
                    CustomerApiVendor customerApiVendor = customerApiVendorList.get(i);
                    totleAmount = totleAmount + customerApiVendor.getTotlePrice();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("customerApiVendorList",customerApiVendorList);
        model.addAttribute("companyName",companyName);
        model.addAttribute("apiTypeName",apiTypeName);
        model.addAttribute("totleAmount",totleAmount);
        return "/finance/customerApiConsumeDetailRecord";
    }

    /**
     * 周历史记录
     * @param customerId
     * @param years
     * @param months
     * @param weeks
     * @param typeId
     * @param companyName
     * @param model
     * @return
     */
    @RequestMapping("/find-all-customer/find-week-record-by-customer-id")
    public String findWeekRecordByCustomerId(Integer customerId,Integer years,Integer months,Integer weeks,Integer [] typeId,String companyName,Model model){
        Map<String,Object> map = new HashedMap();
        map.put("customerId",customerId);
        map.put("weekMonthTypeId",1);
        List<Integer> tableIdList = new ArrayList();
        if(typeId != null && typeId.length>0){
            for(int i=0; i<typeId.length; i++){
                tableIdList.add(typeId[i]);
            }
        }else {
            tableIdList.add(1);
            tableIdList.add(2);
        }
        map.put("tableIdList",tableIdList);
        List<Integer> monthList = null;
        if(years != null){
            map.put("years",years);
            try {
                monthList = customerFinanceService.queryCompanyCustomerMonthsByCustomerId(map);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        List<Integer> weekList = null;
        if(months != null){
            map.put("months",months);
            try {
                weekList = customerFinanceService.queryCompanyCustomerWeeksByCustomerId(map);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(weeks != null){
            map.put("weeks",weeks);
        }
        List<WeekMonthAmount> weekMonthAmountList = null;
        List<Integer> yearList = null;
        long totleAmount = 0;
        try {
            yearList = customerFinanceService.queryCompanyCustomerYearsByCustomerId(map);
            weekMonthAmountList = customerFinanceService.queryCompanyCustomerWeekMonthRecordByCustomerId(map);
            if(weekMonthAmountList != null){
                for (int i=0; i<weekMonthAmountList.size(); i++){
                    WeekMonthAmount weekMonthAmount = weekMonthAmountList.get(i);
                    totleAmount = totleAmount + weekMonthAmount.getTotleAmount();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("weekMonthAmountList",weekMonthAmountList);
        model.addAttribute("yearList",yearList);
        model.addAttribute("monthList",monthList);
        model.addAttribute("weekList",weekList);
        model.addAttribute("totleAmount",totleAmount);
        model.addAttribute("companyName",companyName);
        model.addAttribute("customerId",customerId);
        model.addAttribute("typeIdArray",typeId);
        model.addAttribute("years",years);
        model.addAttribute("months",months);
        model.addAttribute("weeks",weeks);
        return "/finance/weekRecord";
    }

    /**
     * 月历史记录
     * @param customerId
     * @param years
     * @param months
     * @param typeId
     * @param companyName
     * @param model
     * @return
     */
    @RequestMapping("/find-all-customer/find-month-record-by-customer-id")
    public String findMonthRecordByCustomerId(Integer customerId,Integer years,Integer months, Integer [] typeId,String companyName,Model model){
        Map<String,Object> map = new HashedMap();
        map.put("customerId",customerId);
        map.put("weekMonthTypeId",2);
        List<Integer> tableIdList = new ArrayList();
        if(typeId != null && typeId.length>0){
            for(int i=0; i<typeId.length; i++){
                tableIdList.add(typeId[i]);
            }
        }else {
            tableIdList.add(1);
            tableIdList.add(2);
        }
        map.put("tableIdList",tableIdList);
        List<Integer> monthList = null;
        if(years != null){
            map.put("years",years);
            try {
                monthList = customerFinanceService.queryCompanyCustomerMonthsByCustomerId(map);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(months != null){
            map.put("months",months);

        }
        List<WeekMonthAmount> weekMonthAmountList = null;
        List<Integer> yearList = null;
        long totleAmount = 0;
        try {
            yearList = customerFinanceService.queryCompanyCustomerYearsByCustomerId(map);
            weekMonthAmountList = customerFinanceService.queryCompanyCustomerWeekMonthRecordByCustomerId(map);
            if(weekMonthAmountList != null){
                for (int i=0; i<weekMonthAmountList.size(); i++){
                    WeekMonthAmount weekMonthAmount = weekMonthAmountList.get(i);
                    totleAmount = totleAmount + weekMonthAmount.getTotleAmount();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("weekMonthAmountList",weekMonthAmountList);
        model.addAttribute("yearList",yearList);
        model.addAttribute("monthList",monthList);
        model.addAttribute("totleAmount",totleAmount);
        model.addAttribute("companyName",companyName);
        model.addAttribute("customerId",customerId);
        model.addAttribute("typeIdArray",typeId);
        model.addAttribute("years",years);
        model.addAttribute("months",months);
        return "/finance/monthRecord";
    }





}