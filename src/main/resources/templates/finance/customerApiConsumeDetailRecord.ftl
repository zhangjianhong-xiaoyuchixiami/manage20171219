
<#include "../customer/layout.ftl">

<#import "../publicPart/headNavigationBars.ftl" as c>

<@layout ; section>
    <#if section = "head">

    <#elseif section = "content" >

    <div class="page-content">

        <div class="container-fluid">

            <@c.navigationBars></@c.navigationBars>

            <div class="row-fluid">

                <div class="span12">

                <#--搜索框-->

                    <form action="/finance/findAllCustomerBalanceLogByCustomerId" method="get">

                        <div class="clearfix margin-bottom-20">

                            <div class="control-group pull-left" style="margin-bottom: -20px;">

                                <label class="control-label">状态</label>

                                <div class="controls">

                                    <label class="checkbox">

                                        <input type="checkbox" <#if reasonIdArray??><#list reasonIdArray as reasonId><#if reasonId=="-2">checked="checked"</#if></#list></#if> id="reasonId" name="reasonId" value="-2">成功

                                    </label>

                                    <label class="checkbox">

                                        <input type="checkbox" <#if reasonIdArray??><#list reasonIdArray as reasonId><#if reasonId=="-1">checked="checked"</#if></#list></#if> id="reasonId" name="reasonId" value="-1">失败

                                    </label>

                                </div>

                            </div>

                            <div class="control-group pull-left" style="margin-bottom: -20px;">

                                <label class="control-label">Api类型</label>

                                <div class="controls">

                                    <select id="apiId" name="apiId" class="medium m-wrap1" tabindex="1">
                                    <#--<#if apiList??>-->
                                    <#--<#list apiList as api>-->

                                        <option value="">二要素</option>
                                        <option value="">三要素</option>
                                        <option value="">四要素</option>

                                    <#--</#list>-->
                                    <#--</#if>-->
                                    </select>

                                </div>

                            </div>

                            <div class="control-group pull-left" style="margin-bottom: -20px;">

                                <label class="control-label">Api供应商</label>

                                <div class="controls">

                                    <select id="apiId" name="apiId" class="medium m-wrap1" tabindex="1">
                                    <#--<#if apiList??>-->
                                    <#--<#list apiList as api>-->

                                        <option value="">移动</option>
                                        <option value="">联通</option>
                                        <option value="">电信</option>

                                    <#--</#list>-->
                                    <#--</#if>-->
                                    </select>

                                </div>

                            </div>

                            <div class="control-group pull-left" style="margin-bottom: -20px;">

                                <label class="control-label">Api</label>

                                <div class="controls">

                                    <select id="apiId" name="apiId" class="medium m-wrap1" tabindex="1">
                                    <#--<#if apiList??>-->
                                    <#--<#list apiList as api>-->

                                        <option value="">二要素</option>
                                        <option value="">三要素</option>
                                        <option value="">四要素</option>

                                    <#--</#list>-->
                                    <#--</#if>-->
                                    </select>

                                </div>

                            </div>

                            <div class="control-group pull-left margin-right-20" style="margin-bottom: -20px;">

                                <label class="control-label">起始日期</label>

                                <div class="controls">

                                    <div class="input-append date date-picker" data-date="12-02-2012" data-date-format="dd-mm-yyyy" data-date-viewmode="years">

                                        <input <#if beginDate??>value="${beginDate}" </#if> id="beginDate" name="beginDate" class="m-wrap m-ctrl-medium date-picker" size="16" type="text" style="width: 150px;"><span class="add-on"><i class="icon-calendar"></i></span>

                                    </div>

                                </div>

                            </div>

                            <div class="control-group pull-left" style="margin-bottom: -20px;">

                                <label class="control-label">结束日期</label>

                                <div class="controls">

                                    <div class="input-append date date-picker" data-date="102/2012" data-date-format="mm/yyyy" data-date-viewmode="years" data-date-minviewmode="months">

                                        <input <#if endDate??>value="${endDate}" </#if> id="endDate" name="endDate" class="m-wrap m-ctrl-medium date-picker" size="16" type="text" style="width: 150px;"><span class="add-on"><i class="icon-calendar"></i></span>

                                    </div>

                                </div>

                            </div>

                            <div class="control-group pull-left" style="margin-bottom: -20px;">

                                <label class="control-label">&nbsp;&nbsp;</label>

                                <div class="input-append" >

                                    <button class="btn black" type="submit">搜索</button>

                                </div>

                            </div>

                        </div>

                    </form>


                <#--表单-->
                    <div class="portlet box grey">

                        <div class="portlet-title">

                            <div class="caption"><i class="icon-cogs"></i>北风分期分期付</div>

                            <div class="tools">

                            <#--<a href="javascript:;" class="collapse"></a>-->

                                <#--<a href="#portlet-config" data-toggle="modal" class="config"></a>-->

                                <#--<a href="javascript:;" class="reload"></a>-->

                                <#--<a href="javascript:;" class="remove"></a>-->

                            </div>

                        </div>

                        <div class="portlet-body no-more-tables">

                            <div class="clearfix margin-bottom-20">

                                <div class="control-group pull-left" style="margin-bottom: -10px;">

                                    <label class="control-label">共计&yen;：<#if totleAmount??><span>${totleAmount}元&nbsp;&nbsp;&nbsp;</span><#else ><span>0元&nbsp;&nbsp;&nbsp;</span></#if></label>

                                </div>


                                <div class="control-group pull-left" style="margin-bottom: -10px;">

                                    <label class="control-label">成功次数：<#if totleAmount??><span>${totleAmount}元&nbsp;&nbsp;&nbsp;</span><#else ><span>23次&nbsp;&nbsp;&nbsp;</span></#if></label>

                                </div>


                                <div class="control-group pull-left" style="margin-bottom: -10px;">

                                    <label class="control-label">失败次数：<#if totleAmount??><span>${totleAmount}元&nbsp;&nbsp;&nbsp;</span><#else ><span>0次&nbsp;&nbsp;&nbsp;</span></#if></label>

                                </div>

                            </div>

                            <table class="table table-striped table-hover table-bordered table-condensed" id="sample_7">
                                <thead>
                                <tr>
                                    <th>Api</th>
                                    <th>金额（单位/元）</th>
                                    <th>创建时间</th>
                                    <th>状态</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr class="danger">
                                    <td>三要素</td>
                                    <td>10</td>
                                    <td>2016.12.12</td>
                                    <td>失败</td>
                                </tr>
                                <tr>
                                    <td>二要素</td>
                                    <td>10</td>
                                    <td>2016.12.12</td>
                                    <td>成功</td>
                                </tr>
                                <tr>
                                    <td>二要素</td>
                                    <td>10</td>
                                    <td>2016.12.12</td>
                                    <td>成功</td>
                                </tr>
                                <tr>
                                    <td>二要素</td>
                                    <td>10</td>
                                    <td>2016.12.12</td>
                                    <td>成功</td>
                                </tr>
                                <tr>
                                    <td>二要素</td>
                                    <td>10</td>
                                    <td>2016.12.12</td>
                                    <td>成功</td>
                                </tr>
                                </tbody>
                            </table>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <#elseif section = "footer">

    <#elseif section = "publicJs">

    <#elseif section = "privateJs">

    <script type="text/javascript" src="/js/jquery.dataTables.js"></script>

    <script type="text/javascript" src="/js/DT_bootstrap.js"></script>

    <script src="/js/table-managed.js"></script>

    <script type="text/javascript">

        jQuery(document).ready(function() {
            TableManaged.init();
        });
    </script>
    <script>
        $(document).ready(function() {
            $('#customerBalance').addClass('active');

            $('#customerListBalanceLog').addClass('active');

            $('#customerBalanceSelect').addClass('selected');

            $('#customerBalanceArrow').addClass('arrow open');
        });
    </script>
    </#if>

</@layout>