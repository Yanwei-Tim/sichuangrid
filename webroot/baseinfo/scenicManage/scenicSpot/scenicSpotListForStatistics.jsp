<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ include file="/baseinfo/commonPopulation/jsFunctionInclude.jsp"%>
<input type="hidden" id="orgIdForStat" value="<s:property value="#parameters.orgIdForStat"/>"/>
<div class="content">
	
	<div style="clear: both;"></div>
	<div style="width: 100%;">
		<table id="scenicSpotList">
		</table>
		<div id="scenicSpotListPager"></div>
	</div>
	<div id="scenicSpotDialog"></div>
</div>
<script type="text/javascript">
var dialogWidth=850;
var dialogHeight=600;
var currentOrgId=getOrgIdForStat();
function operatorFormatter(el,options,rowData){
	return "<a href='javascript:;' onclick='viewScenicSpot("+rowData.id+");'><span>查看</span></a> ";
}

function nameFont(el,options,rowData){
	if(rowData.isEmphasis==true||rowData.isEmphasis=='true'){
		return "<a href='javascript:;' <pop:JugePermissionTag ename='viewScenicSpot'>  onclick='viewScenicSpot("+rowData.id+")' </pop:JugePermissionTag> ><font color='#778899'>"+rowData.name+"</font></a>";
	}
	return "<a href='javascript:;' <pop:JugePermissionTag ename='viewScenicSpot'> onclick='viewScenicSpot("+rowData.id+")' </pop:JugePermissionTag> >"+rowData.name+"</a>";
}

function onOrgChanged(orgId, isgrid){
    var	initParam = {
		"organizationId":orgId,
		"location.isEmphasis":false
	}

	$("#scenicSpotList").setGridParam({
		url:'${path}/baseinfo/scenicSpotManage/scenicSpotList.action',
		datatype:'json',
		page:1
	});
	$("#scenicSpotList").setPostData(initParam);
	$("#scenicSpotList").trigger("reloadGrid");
}
	
	
$(function(){
	$("#scenicSpotList").jqGridFunction({
		datatype: "local",
		mytype:"post",
	    colModel:[
		      {name:"id", index:"id",hidden:true,sortable:false, frozen:true},
		  	  {name:"operator", index:'id',label:'操作',formatter:operatorFormatter,width:80,frozen:true,sortable:false,align:'center' },
		      {name:"name",index:"name",label:"景点名称",width:100,sortable:true,formatter:nameFont},
		      {name:"address",index:"address",label:"景点地址",width:200,sortable:false,formatter:addressColorFormatter},
		      {name:"grade",index:"grade",label:"景点等级",width:200,sortable:false},
		      {name:"telephone",index:"telephone",label:"景点电话",width:200,sortable:false},
		      {name:"personLiable",index:"personLiable",label:"景点负责人",sortable:true,width:100,hidden:false},
		      {name:"personLiableTelephone",index:"personLiableTelephone",label:"景点负责人电话",sortable:true,width:100,hidden:false},
			  {name:'sourcesState',index:'sourcesState',label:"数据来源",sortable:false,hidden:true,formatter:sourceStateFormatter,width:80,align:'center'},
		      {name:"isEmphasis",sortable:false,label:"是否关注",hidden:true,hidedlg:true,width:100},
		      {name:"logOutTime",index:"logOutTime",label:"注销时间",sortable:true,width:100,hidden:true,align:'center'},
		      {name:"logOutReason",index:"logOutReason",label:"注销原因",sortable:true,width:100,hidden:true},
		      {name:"remark",index:"remark",label:"备注",sortable:true,width:200,hidden:true}
		],
		multiselect:true,
	  	onSelectAll:function(aRowids,status){},
		<pop:JugePermissionTag ename="viewScenicSpot">
		ondblClickRow:viewScenicSpot,
		</pop:JugePermissionTag>
		onSelectRow:selectRow
	});
	jQuery("#scenicSpotList").jqGrid('setFrozenColumns');
	$("#scenicSpotList").closest(".ui-jqgrid-bdiv").css({ "height" : "435" });
	if (getOrgIdForStat()!=null && getOrgIdForStat()!=""){
		onOrgChanged(getOrgIdForStat(),isGrid());
	}
});

function selectRow(){
	var count = $("#scenicSpotList").jqGrid("getGridParam","records");
	var selectedCounts = getActualjqGridMultiSelectCount("scenicSpotList");
	if(selectedCounts == count){
		jqGridMultiSelectState("scenicSpotList", true);
	}else{
		jqGridMultiSelectState("scenicSpotList", false);
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

function viewScenicSpot(rowid){
	if(rowid==null){
 		return;
	}
	$("#scenicSpotDialog").createDialog({
		width: dialogWidth,
		height: 600,
		title:'查看旅游景点信息',
		modal : true,
		url:'${path}/baseinfo/scenicSpotManage/dispatchOperate.action?mode=view&id='+rowid,
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
