<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ include file="/baseinfo/commonPopulation/jsFunctionInclude.jsp"%>

<script type="text/javascript">
<pop:formatterProperty name="state" domainName="@com.tianque.domain.property.PropertyTypes@STATE"/>

var gridOption = {
	colModel:[
		{name:"id", index:"id", hidden:true},
		{name:"objNum", index:"objNum",label:"部件编号",width:100},
		{name:"objName", index:"objName",label:"部件名称",width:100},
		{name:"street", label:"所在街道",width:140,hidden:true,sortable:false},
		{name:"community", label:"所在社区",width:140,hidden:true,sortable:false},		
		{name:"deptCode", index:"deptCode",label:"主管部门代码",width:100},
		{name:"deptName", index:"deptName",label:"主管部门名称",width:100},
		{name:"ownershipUnitCode",label:"权属单位代码",width:100,hidden:true,sortable:false},
		{name:"ownershipUnitName",label:"权属单位名称",width:100,hidden:true,sortable:false},
		{name:"maintenanceUnitCode",label:"养护单位代码",width:100,hidden:true,sortable:false},
		{name:"maintenanceUnitName",label:"养护单位名称",width:100,hidden:true,sortable:false},
		{name:"state",label:"状态",formatter:stateFormatter,width:100,hidden:true,sortable:false},
		{name:"startDate",label:"初始时间",width:100,hidden:true,sortable:false},
		{name:"changeDate",label:"更改时间",width:100,hidden:true,sortable:false},
		{name:"dateSource",label:"数据来源",width:100,sortable:false},
		{name:"researchOwnership", label:"现场调查权属 ",index:"researchOwnership",width:140,hidden:true},
		{name:"verifyOwnership", label:"专业部门确认权属",index:"verifyOwnership",width:140,hidden:true},
		{name:"caseMaterialOwnership", label:"案件资料确认权属",index:"caseMaterialOwnership",width:140,hidden:true},
		{name:"otherSourceOwnership", label:"其他来源权属",index:"otherSourceOwnership",width:140,hidden:true},
		{name:"objPosition", label:"位置描述",index:"objPosition",width:140,hidden:true},		
		{name:"organization.orgName", index:"orgInternalCode", width:230,label:"所属网格"},
		{name:"remark",label:'备注', width:180,hidden:true,sortable:false}		
	]
};
var dialogWidth=800;
var dialogHeight=640;
</script>
<jsp:include page="/digitalCity/otherFacilitiesManagement/otherFacilities.jsp">
	<jsp:param value="OtherFacilities" name="moduleName"/>
	<jsp:param value="其他设施" name="moduleCName"/>
</jsp:include>