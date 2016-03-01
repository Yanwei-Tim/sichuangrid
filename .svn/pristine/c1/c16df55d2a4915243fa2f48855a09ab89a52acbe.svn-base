<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ include file="/baseinfo/commonPopulation/jsFunctionInclude.jsp"%>
<script type="text/javascript">
<pop:formatterProperty name="partType" domainName="@com.tianque.domain.property.PropertyTypesYinchuan@PART_TYPE" />
<pop:formatterProperty name="partName" domainName="@com.tianque.domain.property.PropertyTypesYinchuan@PART_NAME" />

function operatorFormatter(el,options,rowData){
	return "<pop:JugePermissionTag ename='updateDustbin'><a href='javascript:updateOperator("+rowData.id+")'><span>修改</span></a> | </pop:JugePermissionTag><pop:JugePermissionTag ename='deleteDustbin'><a href='javascript:deleteOperator("+rowData.id+")'><span>删除</span></a></pop:JugePermissionTag>";
}

function nameFont(el,options,rowData){
	if(rowData.isEmphasis==true||rowData.isEmphasis=='true'){
		return "<pop:JugePermissionTag ename='viewDustbin'><a href='javascript:viewDustbin("+rowData.id+")'></pop:JugePermissionTag><font color='#778899'>"+rowData.dustbinCode+"</font><pop:JugePermissionTag ename='viewDustbin'></a></pop:JugePermissionTag>";
	}
		return "<pop:JugePermissionTag ename='viewDustbin'><a href='javascript:viewDustbin("+rowData.id+")'></pop:JugePermissionTag>"+rowData.dustbinCode+"<pop:JugePermissionTag ename='viewDustbin'></a></pop:JugePermissionTag>";
}
function hasVideo(hasVideo){
	if(true==hasVideo || "true" == hasVideo){
		return "是";
	}else{
		return "否";
	}
}

var gridOption = {
	colModel:[
		{name:"id", index:"id", hidden:true,frozen:true},
		{name:"encryptId", index:"id", hidden:true,frozen:true},
		{name:"operator", index:'id',label:'操作',formatter:operatorFormatter,width:80,frozen:true,sortable:false,align:'center' },
		{name:"dustbinCode", index:"dustbinCode",label:"部件标识码",width:100,formatter:nameFont,frozen:true,sortable:true},
		{name:"partType", index:"partType",label:"部件类型",formatter:partTypeFormatter,width:80,sortable:true},
		{name:"partName", index:"partName",label:"部件名称",formatter:partNameFormatter,width:100,sortable:true},
		{name:"organization.orgName", index:"orgInternalCode", width:220,label:"所属网格",sortable:true},
		{name:"address", label:"地址",index:"address",width:100,sortable:true},
		{name:"deptName", index:"deptName",label:"主管部门名称",width:100,sortable:true},
		{name:"ownershipUnitName",label:"权属单位名称",width:100,sortable:true},
		{name:"maintenanceUnitName",label:"养护单位名称",width:100,sortable:true},
		{name:"hasVideo", label:"是否有视频流",index:"hasVideo", formatter:hasVideo,width:80,sortable:true},
		{name:"remark",label:'备注', width:180,hidden:true},
		{name:'createDate', label:'创建时间',hidden:true,align:"center", width:150},
		{name:'createUser', label:'创建用户',hidden:true,align:"center", width:110},
		{name:'updateDate', label:'更新时间',hidden:true,align:"center", width:150},
		{name:'updateUser', label:'更新用户',hidden:true,align:"center", width:110},
		{name:"isEmphasis",label:"是否关注",hidden:true,hidedlg:true,width:100}
	]
};
var dialogWidth=800;
var dialogHeight=640;

</script>

<jsp:include page="/baseinfo/commonPopulation/commonPart.jsp">
	<jsp:param value="Dustbin" name="moduleName"/>
	<jsp:param value="部件信息" name="moduleCName"/>
</jsp:include>