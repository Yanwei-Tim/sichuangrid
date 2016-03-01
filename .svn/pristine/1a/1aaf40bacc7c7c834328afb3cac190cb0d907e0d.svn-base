<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ include file="/baseinfo/commonPopulation/jsFunctionInclude.jsp"%>
<script type="text/javascript">
<pop:formatterProperty name="trafficType" domainName="@com.tianque.domain.property.PropertyTypes@SCENICTRAFFIC_TYPES" />
	function operatorFormatter(el,options,rowData){
		return "<pop:JugePermissionTag ename='updateScenicTraffic'><a href='javascript:;' onclick='updateOperator(event,"+rowData.id+");'><span>修改</span></a> | </pop:JugePermissionTag><pop:JugePermissionTag ename='deleteScenicTraffic'><a href='javascript:;' onclick='deleteOperator(event,"+rowData.id+");'><span>删除</span></a></pop:JugePermissionTag>";
	}
	function emphasisFormatter(el,options,rowData){
		if(rowData.isEmphasis == 'false' || rowData.isEmphasis == false || rowData.isEmphasis ==''|| rowData.isEmphasis ==undefined ){
			return "是";
		}else{
			return "否";
		}
	}
	var gridOption={
		colModel:[
        	{name:"id", index:"id",hidden:true,sortable:false, frozen :true},
        	{name:"encryptId", index:"id",hidden:true,sortable:false, frozen :true},
			{name:"operator", index:'id',label:'操作',formatter:operatorFormatter,width:80,align:'center'},
			{name:"trafficType.id",index:'trafficType',label:"交通类型",sortable:true,width:100,formatter:trafficTypeFormatter,align:'center'},
			{name:"trafficName",label:"线路名称",sortable:true,width:300},
			{name:"managerUnit",label:"负责单位",sortable:true,width:260},
			{name:"managerPeople",label:"负责人",sortable:true,width:100,align:'center'},
	        {name:"isEmphasis_title",index:'isEmphasis',label:'是否关注',sortable:true,formatter:emphasisFormatter,width:70,align:'center'},
	        {name:"isEmphasis",label:'是否关注',sortable:false,hidden:true,frozen:false,hidedlg:true},
	        {name:"logOutTime",index:"logOutTime",label:"注销时间",sortable:true,width:100,hidden:true,align:'center'},
		    {name:"logOutReason",index:"logOutReason",label:"注销原因",sortable:true,width:100,hidden:true},
	        {name:"managerPeoplePhone",label:"负责人电话",sortable:true,width:80,align:'center'},
			{name:"updateDate",label:"数据更新时间",sortable:true,width:120,align:'center'}
			]
		};
	var dialogWidth=850;
	var dialogHeight=600;
	function getLocationName(rowData){
		return rowData["trafficType.id"];
	}
</script>
<jsp:include page="/baseinfo/scenicManage/common/commonLocationList.jsp">
	<jsp:param value="ScenicTraffic" name="moduleName"/>
	<jsp:param value="线路" name="moduleCName"/>
	<jsp:param value="线路" name="moduleSearchCName"/>
	<jsp:param value="1" name="isNew"/>
</jsp:include>