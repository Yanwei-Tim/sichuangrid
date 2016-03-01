<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div class="content" >
	<input type="hidden" id="regionalBuildInfoId" name="regionalBuildInfoId" value="${regionalBuildInfoId }">
	<div style="clear: both;"></div>
	<div style="width: 100%;">
		<table id="claimRegionalBuildInfoList"> </table>
		<div id="claimRegionalBuildInfoListPager"></div>
	</div>
	<div id="claimRegionalBuildInfoDialog"></div>
</div>
<script type="text/javascript">
var regionalBuildInfoId="${regionalBuildInfoId }";

//Formatter
function operatorClaimRegionalBuildInfoFormatter(el,options,rowData){
	return "<pop:JugePermissionTag ename='updateRegionalBuildInfo'><a href='javascript:updateClaimRegionalBuildInfo("+rowData.id+")'><span>修改</span></a> | </pop:JugePermissionTag><pop:JugePermissionTag ename='deleteRegionalBuildInfo'><a href='javascript:deleteClaimRegionalBuildInfo("+rowData.id+")'><span>删除</span></a></pop:JugePermissionTag>";
}

function updateClaimRegionalBuildInfo(claimId){
	if(claimId==null){return;}
 $("#claimRegionalBuildInfoDialog").createDialog({
		width:550,
		height:200,
		title:'修改区域联建情况认领记录',
		url:'${path}/partyBuilding/regionalBuildInfoManage/dispatchOperate.action?mode=claim&claimMode=edit&id='+claimId,
		buttons: {
	   		"保存" : function(event){
	   			$("#claimRegionalBuildInfoForm").submit();
   			},
	   		"关闭" : function(){
	        	$(this).dialog("close");
	   		}
		}
	});
}

function deleteClaimRegionalBuildInfo(claimId){
	
	var str="确定要删除吗?一经删除将无法恢复";
	$.confirm({
		title:"确认删除",
		message:str,
		okFunc: function() {
			$.ajax({
				url:"${path}/partyBuilding/regionalBuildInfoManage/deleteClaimRegionalBuildInfo.action?id="+claimId,
				success:function(data){
					$.messageBox({message:"已经成功删除该区域联建情况认领信息!"});
						$("#claimRegionalBuildInfoList").trigger("reloadGrid");
				}
			});
		}
	});
}

function toggleClaimRegionalBuildInfoButtonState(){
  	var selectedIds=$("#regionalBuildInfoList").jqGrid("getGridParam", "selarrrow");
  	var selectedRowCount=selectedIds.length;
}
$(document).ready(function() {
	
	 //生成组织活动列表
	$("#claimRegionalBuildInfoList").jqGridFunction({
		mtype:'post',
		datatype: "local",
		colModel:[
	    	{name:"id", index:"id", hidden:true,frozen : true},
	    	{name:"regionalBuildInfoId", index:"regionalBuildInfoId", hidden:true,frozen : true},
	    	{name:"operator", index:'id',label:'操作',formatter:operatorClaimRegionalBuildInfoFormatter,width:65,frozen:true,align:'center' },
	    	{name:"claimDepartment", index:"claimDepartment",label:"认领单位",width:200, frozen : true},
  			{name:"claimDate", index:"claimDate",label:"认领时间",formatter:'date',formatoptions:{newformat: 'Y-m-d'},width:200, frozen : true}
	  	],
	  	multiselect:true,
	  	onSelectAll:toggleClaimRegionalBuildInfoButtonState,
    	loadComplete: function(){afterLoad();},
		onSelectRow:toggleClaimRegionalBuildInfoButtonState,
		height:$(".ui-layout-center").height()-320
	});
	jQuery("#claimRegionalBuildInfoList").jqGrid('setFrozenColumns');
	
	if(regionalBuildInfoId!=null&&regionalBuildInfoId!=""){
		getAllClaimRegionalBuildInfoByRegionalBuildInfoId(regionalBuildInfoId);
	}
});



function getAllClaimRegionalBuildInfoByRegionalBuildInfoId(regionalBuildInfoId){
	$("#claimRegionalBuildInfoList").setGridParam({
		url:"${path}/partyBuilding/regionalBuildInfoManage/claimRegionalBuildInfoList.action",
		datatype: "json",
		page:1
	});
	$("#claimRegionalBuildInfoList").setPostData({
		"regionalBuildInfoId":regionalBuildInfoId
   	});
	$("#claimRegionalBuildInfoList").trigger("reloadGrid");
}
</script>