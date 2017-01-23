
<#include "../customer/layout.ftl">

<#import "../publicPart/headNavigationBars.ftl" as c>

<#import "../publicPart/tools.ftl" as d>

<@layout ; section>

    <#if section = "head">

    <#elseif section = "content" >

    <div class="page-content" xmlns="http://www.w3.org/1999/html">

        <div class="container-fluid">

            <@c.navigationBars></@c.navigationBars>

            <div class="row-fluid">

                <div class="span12">

                <#--搜索框-->

                    <form action="/api/find-all-api-vendor-consume" method="get">

                        <div class="clearfix margin-bottom-20" style="margin-top: -18px;">


                            <div class="control-group pull-left" style="margin-bottom: -20px;">

                                <label class="control-label">产品供应商</label>

                                <div class="controls">

                                    <select id="vendorId" name="vendorId" class="medium m-wrap1" tabindex="1" style="width: 105px;">
                                        <option value="">请选择...</option>
                                        <#if apiVendorList??>
                                            <#list apiVendorList as apiVendor>
                                                <option <#if vendorId?? && vendorId==apiVendor.id>selected="selected"</#if> value="${apiVendor.id}">${apiVendor.name}</option>
                                            </#list>
                                        </#if>
                                    </select>
                                </div>

                            </div>

                            <div class="control-group pull-left" style="margin-bottom: -20px;">

                                <label class="control-label">&nbsp;&nbsp;</label>

                                <div class="controls" >

                                    <div class="input-append">

                                        <button class="btn black" type="submit">搜索</button>

                                    </div>

                                </div>

                            </div>

                        </div>

                    </form>

                <#--表格-->
                    <div class="portlet box grey">

                        <div class="portlet-title">

                            <div class="caption"><i class="icon-user"></i></div>

                            <@d.tools idName="exportExcel"></@d.tools>

                            <div class="actions">

                                <div class="btn-group">

                                    <a class="btn" href="#" data-toggle="dropdown">

                                        表格显示列

                                        <i class="icon-angle-down"></i>

                                    </a>

                                    <div id="sample_11_column_toggler" class="dropdown-menu hold-on-click dropdown-checkboxes pull-right">

                                        <label><input type="checkbox" checked data-column="0">产品供应商</label>

                                        <label><input type="checkbox" checked data-column="1">消费总额</label>

                                        <label><input type="checkbox" checked data-column="2">所剩余额</label>

                                        <label><input type="checkbox" data-column="3">上周消费</label>

                                        <label><input type="checkbox" checked data-column="4">上月消费</label>

                                    </div>

                                </div>

                            </div>

                        </div>

                        <div class="portlet-body no-more-tables">

                            <div class="clearfix margin-bottom-20">

                                <div class="control-group pull-left" style="margin-bottom: -10px;">

                                    <label class="control-label">周消费总额&yen;：<#if weekTotleAmount??><span>${(weekTotleAmount/100.0)?c}元</span><#else ><span>0元</span></#if>&nbsp;&nbsp;&nbsp;</label>

                                </div>

                                <div class="control-group pull-left" style="margin-bottom: -10px;">

                                    <label class="control-label">月消费总额&yen;：<#if monthTotleAmount??><span>${(monthTotleAmount/100.0)?c}元</span><#else ><span>0元</span></#if>&nbsp;&nbsp;&nbsp;</label>

                                </div>

                                <div class="control-group pull-left" style="margin-bottom: -10px;">

                                    <label class="control-label">消费总额&yen;：<#if consumeTotleAmount??><span>${(consumeTotleAmount/100.0)?c}元</span><#else ><span>0元</span></#if>&nbsp;&nbsp;&nbsp;</label>

                                </div>

                                <div class="control-group pull-left" style="margin-bottom: -10px;">

                                    <label class="control-label">所剩余额&yen;：<#if totleBalance??><span>${(totleBalance/100.0)?c}元</span><#else ><span>0元</span></#if>&nbsp;&nbsp;&nbsp;</label>

                                </div>

                            <#--表字段总额-->
                                <div class="control-group pull-right" style="margin-bottom: -10px;">

                                    <label class="control-label">

                                        <a id="columnVendorHistogram" href="#form_modal7" data-toggle="modal">

                                            <i class="icon-bar-chart"></i>总消费

                                        </a>

                                    </label>

                                    <div id="form_modal7" class="modal hide fade myModalChart" tabindex="-1" role="dialog" aria-labelledby="myModalLabel7" aria-hidden="true">

                                        <div class="modal-header">

                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>

                                            <h3 id="myModalLabel7">&nbsp;</h3>

                                        </div>

                                        <div class="modal-body">
                                            <div id="columnVendorHistogramContainer">

                                            </div>

                                        </div>

                                    </div>

                                </div>

                            </div>

                            <table class="table table-striped table-bordered table-hover table-condensed" id="sample_11">
                                <thead>
                                <tr>
                                    <th style="text-align: center;">产品供应商</th>
                                    <th>消费总额（单位：元）</th>
                                    <th>所剩余额（单位：元）</th>
                                    <th>上周消费（单位：元）</th>
                                    <th>上月消费（单位：元）</th>
                                    <th style="text-align: center; width: 13%;">操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                    <#if apiFinanceList??>
                                        <#list apiFinanceList as apiFinance>
                                        <tr class="odd gradeX">
                                            <td data-title="产品供应商">${apiFinance.vendorName}</td>
                                            <td data-title="消费总额"><#if apiFinance.consumeTotleAmount??>${(apiFinance.consumeTotleAmount/100.0)?c}<#else >0</#if></td>
                                            <td data-title="所剩余额"><#if apiFinance.balance??>${(apiFinance.balance/100.0)?c}<#else >0</#if></td>
                                            <td data-title="上周消费"><#if apiFinance.weekTotleCost??>${(apiFinance.weekTotleCost/100.0)?c}<#else >0</#if></td>
                                            <td data-title="上月消费"><#if apiFinance.monthTotleCost??>${(apiFinance.monthTotleCost/100.0)?c}<#else >0</#if></td>
                                            <td data-title="操作" style="text-align: center;" >
                                                <a href="#form_modal4" onclick="charge(${apiFinance.vendorId})" data-toggle="modal">充值</a>
                                            </td>
                                        </tr>
                                        </#list>
                                    </#if>
                                </tbody>
                            </table>

                            <div id="form_modal4" class="modal hide fade myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel4" aria-hidden="true">

                                <div class="modal-header">

                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>

                                    <h3 id="myModalLabel4">请填写信息</h3>

                                </div>

                                <div class="modal-body">

                                    <form action="#" class="form-horizontal">

                                        <div class="control-group"></div>

                                        <div class="control-group"></div>

                                        <div id="error-alert"></div>

                                        <div id="apiId-controls" class="controls" style="display: none;"></div>

                                        <div class="control-group">

                                            <label class="control-label">金&nbsp;额<span class="required">*</span></label>

                                            <div id="amount-controls" class="controls"></div>

                                        </div>

                                        <div class="control-group">

                                            <label class="control-label">充值日期<span class="required">*</span></label>

                                            <div class="controls">

                                                <div class="input-append date date-picker" data-date="102/2012" data-date-format="mm/yyyy" data-date-viewmode="years" data-date-minviewmode="months">

                                                    <input id="chargeDate" name="chargeDate" class="m-wrap m-ctrl-medium date-picker" size="16" type="text"><span class="add-on"><i class="icon-calendar"></i></span>

                                                </div>

                                            </div>

                                        </div>

                                        <div class="control-group">

                                            <label class="control-label">备&nbsp;注<span class="required">*</span></label>

                                            <div id="remark-controls" class="controls"></div>

                                        </div>

                                    </form>

                                </div>

                                <div class="modal-footer">

                                    <button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>

                                    <button class="btn black btn-primary" id="btn-black-btn-primary" type="button">提交</button>

                                </div>

                            </div>

                        </div>

                    </div>

                </div>

            </div>

        </div>

    </div>

    <#elseif section = "footer">

    <#elseif section = "publicJs">

    <#elseif section = "privateJs">

    <script src="https://code.highcharts.com/highcharts.js"></script>

    <script src="https://code.highcharts.com/modules/exporting.js"></script>

    <script type="text/javascript" src="/js/jquery.dataTables.js"></script>

    <script type="text/javascript" src="/js/DT_bootstrap.js"></script>

    <script src="/js/table-managed.js"></script>

    <script type="text/javascript">

        jQuery(document).ready(function() {
            TableManaged.init();
        });

    </script>

    <#--导出Excel-->
    <script type="text/javascript">

        $(document).ready(function() {
            $('#exportExcel').on('click', function () {
                var vendorId = $('#vendorId').val();
                fetch('/excel-api-finance/find-all-api-vendor-consume?vendorId='+vendorId).then(res => res.blob().then(blob => {
                    var a = document.createElement('a');
                var url = window.URL.createObjectURL(blob);
                var filename ='产品供应商消费账单.xls';
                a.href = url;
                a.download = filename;
                a.click();
                window.URL.revokeObjectURL(url);
            }))
            });
        });
    </script>

    <#--总消费柱状图-->
    <script type="text/javascript">
        $(document).ready(function () {

            $("#columnVendorHistogram").on("click",function () {
                $.ajax({
                    type: 'post',
                    url: '/api/find-all-api-vendor-consume/bar-chart',
                    dataType: 'json',
                    success: function (result) {
                        var json = result;
                        var chart = new Highcharts.Chart({
                            chart: {
                                renderTo: 'columnVendorHistogramContainer',
                                type: 'column'
                            },
                            title: {
                                text: ''
                            },
                            exporting: {
                                enabled: false
                            },
                            credits: {
                                enabled: false
                            },
                            xAxis: {
                                categories: json.xList
                            },
                            yAxis: {
                                min: 0,
                                title: {
                                    text: '消费总额（单位：元）'
                                }
                            },
                            tooltip: {
                                headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                                pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                                '<td style="padding:0"><b>{point.y:.1f} 元</b></td></tr>',
                                footerFormat: '</table>',
                                shared: true,
                                useHTML: true
                            },
                            plotOptions: {
                                column: {
                                    pointPadding: 0.2,
                                    borderWidth: 0
                                }
                            },
                            series: json.yList
                        });
                    }
                });
            });
        });
    </script>

    <#--充值-->
    <script type="text/javascript">

        function charge(vendorId) {
            $("#apiId-controls").empty();
            $("#amount-controls").empty();
            $("#remark-controls").empty();
            $("#error-alert").empty();
            var op=document.createElement("input");
            op.value=vendorId;
            op.type="text";
            op.id="vendorIdCharge";
            op.name="vendorIdCharge";
            $("#apiId-controls").append(op);
            $("#amount-controls").append('<input type="text" id="amount" name="amount"  placeholder="（单位/元）" class="m-wrap medium"><span id="amount-message"></span><span class="help-block">说明：只能输入数字类型并且金额大于0</span>');
            $("#remark-controls").append('<textarea class="medium m-wrap" id="remark" name="remark" rows="3"></textarea><span class="help-block" style="font-size: 12px;">说明：只能输入255个字符</span>');

        }

        $("#btn-black-btn-primary").on("click",function () {
            var vendorIdCharge=$("#vendorIdCharge").val();
            var amount=$("#amount").val();
            var chargeDate=$("#chargeDate").val();
            var remark=$("#remark").attr("value");
            $.ajax({
                type: "post",
                url: "/api/find-all-vendor-record/charge",
                data: {"vendorIdCharge":vendorIdCharge,"amount":amount,"chargeDate":chargeDate,"remark":remark},
                dataType: "json",
                success: function (result) {
                    if(result.amountMessage != null){
                        $("#amount-message").empty();
                        $("#amount-message").append('<span class="help-line"><font color="red">'+result.amountMessage+'</font></span>');
                    }
                    if(result.successMessage != null){
                        window.location.href="/api/find-all-api-vendor-consume"
                    }
                    if(result.errorMessage != null) {
                        $("#error-alert").empty();
                        $("#error-alert").append('<div class="alert alert-error show"><button class="close" data-dismiss="alert"></button><span>'+result.errorMessage+'</span></div>')
                    }
                }
            });
        });
    </script>

    <#--左侧导航-->
    <script type="text/javascript">
        $(document).ready(function() {
            $('#customerBalance').addClass('active');

            $('#apiVendorRecordLog').addClass('active');

            $('#customerBalanceSelect').addClass('selected');

            $('#customerBalanceArrow').addClass('arrow open');

        });
    </script>

    </#if>

</@layout>