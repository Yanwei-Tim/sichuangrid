<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<div class="pop1"> 
    <table>
        <col width="72" />
        <col width="125" />
        <col width="50" />
        <col width="150" />
        <tr><td colspan="4" class="popRed">${msgBean.organization.orgName}发生警情</td></tr>
        <tr><td class="popGray">姓名：</td><td>${msgBean.user.name}</td><td class="popGray">电话：</td><td>${msgBean.user.helpLine}</td></tr>
        <tr><td class="popGray">地址：</td><td colspan="3">${msgBean.user.familyAddress}</td></tr>
        <tr><td class="popGray">求助内容：</td><td colspan="3">${msgBean.message.text}</td></tr>
    </table>
    <div class="popBtn"><div class="centerBtn">
    		<@s.if test="@com.tianque.core.util.ThreadVariable@getUser().getOrganization().getOrgLevel().getInternalId() == @com.tianque.domain.property.OrganizationLevel@COUNTRY">
    		</@s.if>
    		<@s.elseif test="msgBean.isDeal==false">
    			<a href="javascript:;" class="ignoreBtn" onclick='dealReceiveMsgInfo(${msgBean.id});'>忽略</a>
    			<a href="javascript:;" class="dealBtn" onclick='dealTenHouseholdsJointMsg(${msgBean.id})'>处理</a>
    		</@s.elseif>
    		<@s.else>
    			<a href="javascript:;" class="ignoreBtn" onclick='$("#chicken_close").trigger("click");' >关闭</a>
    			<a href="javascript:;" class="dealBtn" onclick='void();' style="background-color: #9E9E9E;">已处理</a>
    		</@s.else>
    </div></div>
</div>
