<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>

<div class="container container_24" id="viewPrimaryOrgMemer${primaryOrgMemberVo.isHaveJob}">
	<input id="viewPrimaryOrgId${primaryOrgMemberVo.isHaveJob}" name="viewPrimaryOrgId${primaryOrgMemberVo.isHaveJob}" type="hidden" value="${primaryOrgMemberVo.primaryOrgId}"/>
	<input id="viewIsHaveJob${primaryOrgMemberVo.isHaveJob}" name="viewIsHaveJob${primaryOrgMemberVo.isHaveJob}" type="hidden" value="${primaryOrgMemberVo.isHaveJob}"/>
	<div class='clearLine'></div>
	<div style="width: 100%;margin-top:6px">
		<table id="viewPrimaryOrgMember${primaryOrgMemberVo.isHaveJob}List"> </table>
		<div id="viewPrimaryOrgMember${primaryOrgMemberVo.isHaveJob}ListPager"></div>
	</div>
</div>

<script type="text/javascript">
<pop:formatterProperty name="gender" domainName="@com.tianque.domain.property.PropertyTypes@GENDER" />

<s:if     
	test="internalId==@com.tianque.domain.property.BasicOrgType@PERMARY_ORGANIZATION">
	<pop:formatterProperty name="dutyId" domainName="@com.tianque.domain.property.PropertyTypes@COMPOSITEDUTY" />
</s:if>
<s:elseif
	test="internalId==@com.tianque.domain.property.BasicOrgType@PERMARY_PARTY">
	<pop:formatterProperty name="dutyId" domainName="@com.tianque.domain.property.PropertyTypes@PARTYDUTY" />
</s:elseif>
<s:elseif
	test="internalId==@com.tianque.domain.property.BasicOrgType@AUTONOMY_ORG">
	<pop:formatterProperty name="dutyId" domainName="@com.tianque.domain.property.PropertyTypes@UTONOMYDUTY" />
</s:elseif>
<s:elseif
	test="internalId==@com.tianque.domain.property.BasicOrgType@MASS_TREAT_TEAM">
	<pop:formatterProperty name="dutyId" domainName="@com.tianque.domain.property.PropertyTypes@MASSESDUTY" />
</s:elseif>
<s:elseif
	test="internalId==@com.tianque.domain.property.BasicOrgType@VOLUNTARY_TEAM">
	<pop:formatterProperty name="dutyId" domainName="@com.tianque.domain.property.PropertyTypes@POSTULANTDUTY" />
</s:elseif>
<s:elseif
	test="internalId==@com.tianque.domain.property.BasicOrgType@LEADER_GROUP">
	<pop:formatterProperty name="dutyId" domainName="@com.tianque.domain.property.PropertyTypes@LEADERGROUPDUTY" />
</s:elseif>
<s:elseif
	test="internalId==@com.tianque.domain.property.BasicOrgType@OTHER">
	<pop:formatterProperty name="dutyId" domainName="@com.tianque.domain.property.PropertyTypes@LEADERGROUPDUTY" />
</s:elseif>
<s:elseif
	test="internalId==@com.tianque.domain.property.BasicOrgType@BASICLEVEL_PARTY">
	<pop:formatterProperty name="dutyId" domainName="@com.tianque.domain.property.PropertyTypes@BASICLEVELPARTYDUTY" />
</s:elseif>
<s:elseif
	test="internalId==@com.tianque.domain.property.BasicOrgType@DEPARTMENT_PARTY">
	<pop:formatterProperty name="dutyId" domainName="@com.tianque.domain.property.PropertyTypes@DEPARTMENTPARTYDUTY" />
</s:elseif>
<s:elseif
	test="internalId==@com.tianque.domain.property.BasicOrgType@GOVERNMENT_DEPARTMENT">
	<pop:formatterProperty name="dutyId" domainName="@com.tianque.domain.property.PropertyTypes@GOVERNMENTDEPARTMENTDUTY" />
</s:elseif>
<s:elseif
	test="internalId==@com.tianque.domain.property.BasicOrgType@MASS_ORGANIZATION">
	<pop:formatterProperty name="dutyId" domainName="@com.tianque.domain.property.PropertyTypes@MASSORGMANAGEMENTDUTY" />
</s:elseif>
<s:elseif
test="internalId==null">
<pop:formatterProperty name="dutyId" domainName="@com.tianque.domain.property.PropertyTypes@PLATFORMDUTY" />
</s:elseif>

$(document).ready(function(){

	$("#viewPrimaryOrgMember${primaryOrgMemberVo.isHaveJob}List").jqGridFunction({
		datatype: "local",
	   	colModel:[
	        {name:"id",index:"id",sortable:false,hidden:true},
	        {name:"org.id",index:"org.id",sortable:false,hidden:true},
	        {name:'name',index:"name",label:'姓名',sortable:false,width:80,align:"center"},
	        {name:'gender',index:'gender',label:'性别',sortable:true,formatter:genderFormatter,width:40,align:"center"},
	        {name:'dutyId',index:'dutyId',label:'职务',sortable:true,formatter:dutyIdFormatter,width:80,align:"center"},
	        //{name:'position',index:'position',label:'职位',sortable:true,width:100,align:"center"},
	        //{name:'year',index:'year',label:'出生年份',sortable:true,width:60,align:"center"},
	        {name:'idcardNo',index:'idcardNo',label:'身份证号码',sortable:true,width:140,align:"center"},
	        {name:'mobile',index:'mobile',label:'手机',sortable:false,width:90,align:"center"},
	        {name:'telephone',index:'telephone',label:'电话',sortable:false,width:100,align:"center"},
		    {name:'remark',index:'remark',label:'备注',sortable:false,width:195}
		],
		height:300,
		showColModelButton:false
	});
	
	$("#viewPrimaryOrgMember${primaryOrgMemberVo.isHaveJob}List").setGridParam({
		url:"${path}/baseinfo/primaryOrgMemberManage/findPrimaryOrgMembers.action",
		sortname:"dutyId",
		sortorder:"asc",
		datatype: "json",
		page:1
	});
	if("${internalId}"==null || "${internalId}"==""){
		$("#viewPrimaryOrgMember${primaryOrgMemberVo.isHaveJob}List").setPostData({
			"primaryOrgMemberVo.primaryOrgId":$("#viewPrimaryOrgId${primaryOrgMemberVo.isHaveJob}").val(),
			"primaryOrgMemberVo.isHaveJob":$("#viewIsHaveJob${primaryOrgMemberVo.isHaveJob}").val(),
			"primaryOrgMemberVo.isFourLevelPlatform":1	
	   	});
		
	}else{
		$("#viewPrimaryOrgMember${primaryOrgMemberVo.isHaveJob}List").setPostData({
			"primaryOrgMemberVo.primaryOrgId":$("#viewPrimaryOrgId${primaryOrgMemberVo.isHaveJob}").val(),
			"primaryOrgMemberVo.isHaveJob":$("#viewIsHaveJob${primaryOrgMemberVo.isHaveJob}").val()
			
	   	});
	}
	
	$("#viewPrimaryOrgMember${primaryOrgMemberVo.isHaveJob}List").trigger("reloadGrid");
	$("#viewPrimaryOrgMember${primaryOrgMemberVo.isHaveJob}List").jqGrid('setFrozenColumns');
});
</script>