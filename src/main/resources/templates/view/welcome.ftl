
<#include "../publicPart/layout.ftl">

<#import "../publicPart/publicJs.ftl" as puj>

<@layout ; section>
    <#if section = "head">

    <#elseif section = "content" >

    <div class="page-content">

        <div class="container-fluid">

            <div class="row-fluid profile">

                <div class="span12">

                    <div class="tabbable tabbable-custom tabbable-full-width">

                    </div>

                </div>

            </div>

        </div>

    </div>

    <#elseif section = "footer">

    <#elseif section = "publicJs">

    <#elseif section = "privateJs">

        <@puj.publicJs></@puj.publicJs>

    </#if>

</@layout>