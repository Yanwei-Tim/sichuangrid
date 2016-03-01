<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ include file="/baseinfo/commonPopulation/jsFunctionInclude.jsp"%>

<script type="text/javascript">
var gridOption = {
	colModel:[
		{name:"id", index:"id", hidden:true},
		{name:"unitName", index:"unitName",label:"单位名称",width:100},
		{name:"unitType",label:"单位类别", index:"unitType", width:100,sortable:false},
		{name:"superintendent", label:"负责人",index:"superintendent",width:40},
		{name:"unitAddress",label:'单位地址', width:80},
		{name:"organization.orgName", index:"orgInternalCode", width:200,label:"所属网格",hidden:true},
		{name:"telephone",label:"联系电话", width:100,sortable:false},
		{name:"commodityType",label:"货物类别", width:100,sortable:false},
		{name:"copyScope",label:"副本许可范围", width:100,sortable:false},
		{name:"storageDevice",label:"存储设备", width:100,sortable:false},
		{name:"isEmphasis",sortable:false,label:"是否注销",hidden:true,hidedlg:true,width:100},
		{name:"logOutReason",label:"注销原因", width:100,sortable:false},
		{name:"logOutTime",label:"注销时间", width:100,sortable:false},
		{name:"remark",sortable:false,label:"备注",hidden:true,width:100}
	]
};
var dialogWidth=800;
var dialogHeight=640;
</script>
<jsp:include page="/baseinfo/siteinfo/undergroundSpace/undergroundSpace.jsp">
	<jsp:param value="UndergroundSpace" name="moduleName"/>
	<jsp:param value="地下空间" name="moduleCName"/>
</jsp:include>