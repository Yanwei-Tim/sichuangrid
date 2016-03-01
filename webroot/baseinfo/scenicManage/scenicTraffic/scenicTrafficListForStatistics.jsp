<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ include file="/baseinfo/commonPopulation/jsFunctionInclude.jsp"%>
<input type="hidden" id="orgIdForStat" value="<s:property value="#parameters.orgIdForStat"/>"/>
<div class="content">
	
	<div style="clear: both;"></div>
	<div style="width: 100%;">
		<table id="scenicTrafficList">
		</table>
		<div id="scenicTrafficListPager"></div>
	</div>
	<div id="scenicTrafficDialog"></div>
</div>
<script type="text/javascript">
var dialogWidth=850;
var dialogHeight=600;
var currentOrgId=getOrgIdForStat();
<pop:formatterProperty name="trafficType" domainName="@com.tianque.domain.property.PropertyTypes@SCENICTRAFFIC_TYPES" />
function operatorFormatter(el,options,rowData){
	return "<a href='javascript:;' onclick='viewScenicTraffic("+rowData.id+");'><span>查看</span></a> ";
}

function onOrgChanged(orgId, isgrid){
    var	initParam = {
		"organizationId":orgId,
		"location.isEmphasis":false
	}

	$("#scenicTrafficList").setGridParam({
		url:'${path}/baseinfo/scenicTrafficManage/scenicTrafficList.action',
		datatype:'json',
		page:1
	});
	$("#scenicTrafficList").setPostData(initParam);
	$("#scenicTrafficList").trigger("reloadGrid");
}
	
	
$(function(){
	$("#scenicTrafficList").jqGridFunction({
		datatype: "local",
		mytype:"post",
	    colModel:[
				{name:"id", index:"id",hidden:true,sortable:false, frozen :true},
				{name:"operator", index:'id',label:'操作',formatter:operatorFormatter,width:80,align:'center'},
				{name:"trafficType.id",label:"交通类型",width:100,formatter:trafficTypeFormatter,align:'center'},
				{name:"trafficName",label:"线路名称",width:300},
				{name:"managerUnit",label:"负责单位",width:260},
				{name:"managerPeople",label:"负责人",width:100,align:'center'},
		        {name:"isEmphasis",label:"是否关注",hidden:true,width:100},
				{name:"managerPeoplePhone",label:"负责人电话",width:80,align:'center'},
				{name:"updateDate",label:"数据更新时间",width:120,align:'center'}
		],
		multiselect:true,
	  	onSelectAll:function(aRowids,status){},
		<pop:JugePermissionTag ename="viewScenicTraffic">
		ondblClickRow:viewScenicTraffic,
		</pop:JugePermissionTag>
		onSelectRow:selectRow
	});
	jQuery("#scenicTrafficList").jqGrid('setFrozenColumns');
	$("#scenicTrafficList").closest(".ui-jqgrid-bdiv").css({ "height" : "435" });
	if (getOrgIdForStat()!=null && getOrgIdForStat()!=""){
		onOrgChanged(getOrgIdForStat(),isGrid());
	}
});

function selectRow(){
	var count = $("#scenicTrafficList").jqGrid("getGridParam","records");
	var selectedCounts = getActualjqGridMultiSelectCount("scenicTrafficList");
	if(selectedCounts == count){
		jqGridMultiSelectState("scenicTrafficList", true);
	}else{
		jqGridMultiSelectState("scenicTrafficList", false);
	}
}

	
	function addressColorFormatter(el,options,rowData){
		if(rowData.address !=null && rowData.address !="null"){
			if(rowData.isEmphasis==true||rowData.isEmphasis=='true'){
				return "<font color='#778899'>"+rowData.address+"</font>";
			}
			return "<font color='#000'>"+rowData.address+"</font>";
		}else{
			return "";
		}
	}
	
	function getLocationId(rowData){
		return rowData.id;
	}

function viewScenicTraffic(rowid){
	if(rowid==null){
 		return;
	}
	$("#scenicTrafficDialog").createDialog({
		width: dialogWidth,
		height: 600,
		title:'查看旅游景点信息',
		modal : true,
		url:'${path}/baseinfo/scenicTrafficManage/dispatchOperate.action?mode=view&id='+rowid,
		buttons: {
		   "关闭" : function(){
		        $(this).dialog("close");
		   }
		}
	});
}

function getOrgIdForStat(){
	var orgIdForStat = $("#orgIdForStat").val();
	if(orgIdForStat == null || orgIdForStat == '' || orgIdForStat == 'undefined'){
		return getCurrentOrgId();
	}else{
		return orgIdForStat;
	}
}
	
</script>
