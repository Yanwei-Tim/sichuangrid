<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ include file="/baseinfo/commonPopulation/jsFunctionInclude.jsp"%>
<input type="hidden" id="orgIdForStat" value="<s:property value="#parameters.orgIdForStat"/>"/>
<div class="content">
	
	<div style="clear: both;"></div>
	<div style="width: 100%;">
		<table id="scenicEquipmentList">
		</table>
		<div id="scenicEquipmentListPager"></div>
	</div>
	<div id="scenicEquipmentDialog"></div>
</div>
<script type="text/javascript">
var dialogWidth=850;
var dialogHeight=600;
var currentOrgId=getOrgIdForStat();
<pop:formatterProperty name="equipType" domainName="@com.tianque.domain.property.PropertyTypes@SCENICEQUIP_TYPES" />
function operatorFormatter(el,options,rowData){
	return "<a href='javascript:;' onclick='viewScenicEquipment("+rowData.id+");'><span>查看</span></a> ";
}

function nameFont(el,options,rowData){
	if(rowData.isEmphasis==true||rowData.isEmphasis=='true'){
		return "<a href='javascript:;' <pop:JugePermissionTag ename='viewScenicEquipment'>  onclick='viewScenicEquipment("+rowData.id+")' </pop:JugePermissionTag> ><font color='#778899'>"+rowData.equipName+"</font></a>";
	}
	return "<a href='javascript:;' <pop:JugePermissionTag ename='viewScenicEquipment'> onclick='viewScenicEquipment("+rowData.id+")' </pop:JugePermissionTag> >"+rowData.equipName+"</a>";
}

function onOrgChanged(orgId, isgrid){
    var	initParam = {
		"organizationId":orgId,
		"location.isEmphasis":false
	}

	$("#scenicEquipmentList").setGridParam({
		url:'${path}/baseinfo/scenicEquipmentManage/scenicEquipmentList.action',
		datatype:'json',
		page:1
	});
	$("#scenicEquipmentList").setPostData(initParam);
	$("#scenicEquipmentList").trigger("reloadGrid");
}
	
	
$(function(){
	$("#scenicEquipmentList").jqGridFunction({
		datatype: "local",
		mytype:"post",
	    colModel:[
				{name:"id", index:"id",hidden:true,sortable:false, frozen :true},
				{name:"operator", index:'id',label:'操作',formatter:operatorFormatter,width:80,frozen:true,sortable:false,align:'center' },
				{name:"equipName",index:"equipName",label:"名称",width:200,sortable:true,frozen:true,formatter:nameFont},
				{name:"equipAddress",index:"equipAddress",label:"地址",width:200,sortable:true,frozen:true},
				{name:"equipType",index:"equipType",label:"类型",sortable:true,width:100,hidden:false,formatter:equipTypeFormatter},
				{name:"mobile",index:"mobile",label:"联系电话",sortable:true,width:200},
				{name:"manager",index:"manager",label:"负责人",sortable:true,width:80},	          
				{name:"managerMobile",index:"managerMobile", sortable:true, label:'负责人联系电话', width:140,align:'center'},
				{name:"aroundScenic",index:"aroundScenic", sortable:true, label:'周边景点',width:300},
				{name:"remark",index:"remark",label:"备注",sortable:true,width:200,hidden:true},
				{name:"isEmphasis",label:'是否关注',sortable:false,hidden:true,hidedlg:true,width:100,align:'center'}
		],
		multiselect:true,
	  	onSelectAll:function(aRowids,status){},
		<pop:JugePermissionTag ename="viewScenicEquipment">
		ondblClickRow:viewScenicEquipment,
		</pop:JugePermissionTag>
		onSelectRow:selectRow
	});
	jQuery("#scenicEquipmentList").jqGrid('setFrozenColumns');
	$("#scenicEquipmentList").closest(".ui-jqgrid-bdiv").css({ "height" : "435" });
	if (getOrgIdForStat()!=null && getOrgIdForStat()!=""){
		onOrgChanged(getOrgIdForStat(),isGrid());
	}
});

function selectRow(){
	var count = $("#scenicEquipmentList").jqGrid("getGridParam","records");
	var selectedCounts = getActualjqGridMultiSelectCount("scenicEquipmentList");
	if(selectedCounts == count){
		jqGridMultiSelectState("scenicEquipmentList", true);
	}else{
		jqGridMultiSelectState("scenicEquipmentList", false);
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

function viewScenicEquipment(rowid){
	if(rowid==null){
 		return;
	}
	$("#scenicEquipmentDialog").createDialog({
		width: dialogWidth,
		height: 600,
		title:'查看旅游景点信息',
		modal : true,
		url:'${path}/baseinfo/scenicEquipmentManage/dispatchOperate.action?mode=view&id='+rowid,
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
