
<#include "../publicPart/layout.ftl">

<#import "../publicPart/headNavigationBars.ftl" as c>

<#import "../publicPart/tools.ftl" as d>

<#import "../publicPart/publicJs.ftl" as puj>

<@layout ; section>

    <#if section = "head">

    <#elseif section = "content" >

    <div class="page-content">

        <div class="container-fluid">

            <@c.navigationBars></@c.navigationBars>

            <div id="dashboard">

                <div class="clearfix"></div>

                <div class="row-fluid">

                    <div class="span12">

                        <div class="portlet solid bordered light-grey">

                            <div class="portlet-title">

                                <div class="caption"><i class="icon-bar-chart"></i>${name!'无'}</div>

                            </div>

                            <div class="portlet-body">

                                <div id="site_statistics_loading">

                                    <div id="container" style="min-width:400px;height:400px"></div>

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

        <@puj.publicJs></@puj.publicJs>

    <script src="/js/highcharts/highcharts.js"></script>

    <script src="/js/highcharts/exporting.js"></script>

    <script src="/js/myjs/customerleftbar.js?v=${ver}"></script>

    <script>

        jQuery(document).ready(function () {
            CustomerLeftBar.init();
        });

        (function($){
            $.getUrlParam = function(name)
            {
                var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
                var r = window.location.search.substr(1).match(reg);
                return r?decodeURIComponent(r[2]):'';  //含有中文请注意此处的编码和解码
            }
        })(jQuery);

        $(function () {
            var cid = $.getUrlParam('cid');
            $.ajax({
                type: "post",
                url: "/finance/customer-nearly-week-thread/data",
                data: {"cid": cid},
                dataType: "json",
                success: function (data) {
                    if (data != null) {

                        new Highcharts.Chart({
                            chart: {
                                renderTo: 'container',
                                type: 'line',
                                reflow: true
                            },
                            title: {
                                text: '近一周消费走势'
                            },
                            exporting: {
                                enabled: false
                            },
                            credits: {
                                enabled: false
                            },
                            xAxis: {
                                categories: data.xList
                            },
                            yAxis: {
                                title: {
                                    text: '消费金额 (元)'
                                }
                            },
                            plotOptions: {
                                line: {
                                    dataLabels: {
                                        enabled: true          // 开启数据标签
                                    },
                                    enableMouseTracking: false // 关闭鼠标跟踪，对应的提示框、点击事件会失效
                                }
                            },
                            series: data.seriesData
                        });

                    }
                }
            });

        });

    </script>

    </#if>

</@layout>
