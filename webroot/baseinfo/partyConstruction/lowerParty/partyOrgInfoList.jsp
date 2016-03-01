<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<div class="btnbanner btnbannerData">
	<div class="ui-widget autosearch">
	    <input type="text" value="请输入身份证号码或党支部名称" name="searchText" id="searchText" maxlength="18" class="searchtxt" onblur="value=(this.value=='')?'请输入身份证号码或党支部名称':this.value;" onfocus="value=(this.value=='请输入身份证号码或党支部名称')?'':this.value;"/>
		<button id="refreshOrgTree1" class="ui-icon ui-icon-refresh searchbtnico"></button>
	</div>
	<a href="javascript:;" id="searchByConditionButton"><span>搜索</span></a>
</div>
<div class="content" >
	<div class="ui-corner-all" id="nav">
		<pop:JugePermissionTag ename="getPartyOrgInfos">
			<a id="search" href="javascript:void(0)"><span>查询</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="viewLowerPartyOrgInfos">
			<a id="view" href="javascript:void(0)"><span>查看</span></a>
		</pop:JugePermissionTag>
		<a id="reload" href="javascript:void(0)"><span>刷新</span></a>
		<pop:JugePermissionTag ename="downloadLowerPartyOrgInfos">
            <a id="export" href="javascript:void(0)"><span>导出</span></a>
        </pop:JugePermissionTag>
	</div>
	<div style="clear: both;"></div>
	<div style="width:100%;" >
		<table id="partyOrgInfoList"></table>
		<div id="partyOrgInfoListPager"></div>
	</div>
	<div id="partyOrgInfoDialog"></div>
	<div id="noticeDialog"></div>
	<div id="searchDialog"></div>
	<div id="personInChargeDialog"></div>
	<div id="floorpersonDialog"></div>
	<div id="appendPopulationDialog"></div>
</div>
<div style="display: none;"><pop:JugePermissionTag
	ename="lowerPartyOrgInfoManagement">
	<span id="title"><s:property value="#request.name"/></span>
</pop:JugePermissionTag>
</div>

<script type="text/javascript">
var dialogWidth=760;
var dialogHeight=510;
var isgridBol = false;
var currentOrgId=getCurrentOrgId();

function onOrgChangedPartyOrgInfoShow(orgId,isgrid){
	$.ajax({
		url:"${path}/baseinfo/partyOrgInfoManage/searchPartyOrgInfoById.action?orgId="+orgId,
		success:function(data){
			if(data){
				$.each(data, function(key, value) {
					if(null != value) {
						$("label[name='partyOrgInfos."+key+"']").text(value);
					} else {
						$("label[name='partyOrgInfos."+key+"']").text("");
					}
					//$("input[name='partyOrgInfos."+key+"']").val(value);
				});
			} else {
				//$("input[name^='partyOrgInfos.']:visible",$("#partyOrganizationform")).val("");
				$("label[name^='partyOrgInfos.']:visible",$("#partyOrganizationform")).text("");
			}
			$("#orgName_label").text(data.organization.orgName);
			if(window.tree !=undefined){
				$("#orgIdForParty").val($.getSelectedNode(tree).attributes.id);
				$("#orgIds").val($.getSelectedNode(tree).attributes.id);
			}
		}
	})
	if(getCurrentOrgId() != USER_ORG_ID) {
		$("#editOrganization").hide();
		return;
	} else {
		$("#editOrganization").show();
	}
}

function onOrgChanged(orgId,isgrid){
	currentOrgId=orgId;
	isgridBol = isgrid;
	onOrgChangedPartyOrgInfoShow(orgId,isgrid);
	$("#partyOrgInfoList").setGridParam({
		url:"${path}/baseinfo/partyOrgInfoManage/partyOrgInfoList.action",
		datatype: "json",
		page:1
	});
	$("#partyOrgInfoList").setPostData({
			"orgId":function(){return currentOrgId;}
	});
	$("#partyOrgInfoList").trigger("reloadGrid");
}
function idCardNoFontOrg(el,options,rowData){
	if(rowData.isEmphasis==1) {
		return "<font color='#778899'>"+rowData.idCardNo+"</font>";
	}
	return "<font color='#000'>"+rowData.idCardNo+"</font>";
}

$(document).ready(function(){
	$("#partyOrgInfoList").jqSubGrid({
		subGridPostData:function(data){return {partyOrgId:data.id}},
        subGridUrl:"${path}/baseinfo/partyOrgInfoManage/findPartyOrgFoMemberInfos.action",
		datatype: "local",
		height:$(".ui-layout-center").height()-$(".submenu").height()-$("#villageProfileBaseInfo").height()-$(".btnbanner").height()-$("#nav").height()-150,
		colNames:['id','党支部名称','党支部书记','党员人数','党支部成立时间','地址','组织活动记录','所属区域(网格)','身份证号码','联系电话','联系手机'],
	   	colModel:[
	        {name:'id',index:'id', hidden:true, frozen:true, sortable:false},
	        {name:'partyBranchName', index:'partyBranchName', width:100, frozen:true, sortable:true},
	        {name:'partyBranchSecretary', index:'partyBranchSecretary', width:150, align:'center', frozen:true, sortable:true},
      		{name:'partyNumbers', index:'partyNumbers', width:100, sortable:true},
      		{name:'establishedDate', index:'establishedDate', width:100, hidden:true, sortable:true},
            {name:'partyBranchAddr', index:'partyBranchAddr', width:110, sortable:true},
	        {name:'orgActivity', index:'orgActivity', width:90, formatter:orgActivityFormatter, align:'center', sortable:false},
	        {name:'organization.orgName', index:'orgInternalCode', width:90, sortable:true},
	        {name:"idCardNo", index:"idCardNo", width:150,  formatter:idCardNoFontOrg, hidden:true, sortable:true},
	        {name:"telephone",index:"telephone", width:100, sortable:true, hidden:true},
			{name:"mobileNumber",index:"mobileNumber", width:100,sortable:true,hidden:true}
		],
		<pop:JugePermissionTag ename="viewOrgInfo">
		ondblClickRow: viewOrgInfo,
		</pop:JugePermissionTag>
		loadComplete: afterLoad,
		onSelectAll:function(aRowids,status){ if(status) {
	   		var selectedIds = $("#partyOrgInfoList").jqGrid("getGridParam", "selarrrow");
	   		for(var i = 0;i<=selectedIds.length;i++){
	   			var row=$("#partyOrgInfoList").getRowData(selectedIds[i]);
	   			if(selectedIds.length ==1){
			   		$("#view").buttonEnable();
				}
	   			if(selectedIds.length >2){
   					$("#view").buttonDisable();
   				}
	    	}
	   	}else{
	   		$("#view").buttonDisable();
	   	}},
		multiselect:true,
		onSelectRow:function(){setOrgInfoOperateButton();}
	});
	jQuery("#partyOrgInfoList").jqGrid('setFrozenColumns');
	if(getCurrentOrgId()!="" && getCurrentOrgId()!=null){
		onOrgChanged(getCurrentOrgId(),isGrid());
	}

	$("#search").click(function(event){
		$("#searchDialog").createDialog({
			width: 760,
			height: 400,
			datatype: "json",
			title:'党员组织信息-请输入查询条件',
			url:'${path}/baseinfo/partyOrgInfoManage/dispatchOperate.action?mode=search&orgId='+getCurrentOrgId(),
			buttons: {
				"查询" : function(event){
					searchPartyOrgInfo();
	   				$(this).dialog("close");
	   			},
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
	});
	function searchPartyOrgInfo() {
		$("#partyOrgInfoList").setGridParam({
			url:"${path}/baseinfo/partyOrgInfoManage/searchPartyOrgInfo.action",
			datatype: "json",
			page:1
		});
		$("#partyOrgInfoList").setPostData($.extend({orgId:getCurrentOrgId()},$("#searchLowerPartyOrgInfoForm").serializeObject()));
		$("#partyOrgInfoList").trigger("reloadGrid");
	}

	$("#view").click(function(event){
		var selectedId =  getSelectedIdLast();
		if(selectedId==null){
	 		return;
		}
		viewOrgInfo(selectedId);
	});

	$("#reload").click(function(event){
		$("#conditionMark").val("partyBranchName");
		$("#searchByCondition").attr("value","");
		onOrgChanged(getCurrentOrgId(),isGrid());
	});

	$("#searchByConditionButton").click(function() {
		search(getCurrentOrgId());
	});
	function search(orgId) {
		var conditions = $("#searchText").val();
		if(conditions == '请输入身份证号码或党支部名称') return;
		$("#partyOrgInfoList").setPostData({
			"orgId":orgId,
			"searchPartyOrgInfoVo.fastSearchKeyWords":conditions
		});
		$("#partyOrgInfoList").setGridParam({
			url:"${path}/baseinfo/partyOrgInfoManage/searchPartyOrgInfo.action",
			datatype: "json",
			page:1
		});
		
		$("#partyOrgInfoList").trigger("reloadGrid");
	 }

	$("#export").click(function(event){
        if ($("#partyOrgInfoList").getGridParam("records")>0){
            $("#partyOrgInfoDialog").createDialog({
                width: 250,
                height: 200,
                title:'导出党员组织信息',
                url:'${path}/common/exportExcel.jsp',
                postData:{
                    gridName:'partyOrgInfoList',
					orgId:getCurrentOrgId(),
                    downloadUrl:'${path}/baseinfo/partyOrgInfoManage/downloadPartyOrgInfo.action'
                },
                buttons: {
                    "导出" : function(event){
                    	exceldownloadSubmitForm();
                        $(this).dialog("close");
                    },
                    "关闭" : function(){
                        $(this).dialog("close");
                    }
                },
                shouldEmptyHtml:false
            });
        }else{
        }
    });

});
function afterLoad(){
	disableButtons();
	setOrgInfoOperateButton();
	checkExport();
	isEmphasisFormatter();
}

function disableButtons(){
	$("#view").buttonDisable();
	$("#emphasis").buttonDisable();
}
function checkExport(){
	if($("#partyOrgInfoList").getGridParam("records")>0
			|| $("#partyOrgInfoList").getGridParam("selrow")!=null){
		$("#export").buttonEnable();
	}else{
		$("#export").buttonDisable();

	}
}
function setOrgInfoOperateButton(){
	var selectedCounts = getActualjqGridMultiSelectCount("partyOrgInfoList");
	var count = $("#partyOrgInfoList").jqGrid("getGridParam","records");
	if(selectedCounts == count){
		jqGridMultiSelectState("partyOrgInfoList", true);
	}else{
		jqGridMultiSelectState("partyOrgInfoList", false);
	}
		if(selectedCounts==1){
			var id = getSelectedIdLast();
			var orgInfoNew = $("#partyOrgInfoList").getRowData(id);
			$("#view").buttonEnable();
		}else{
			$("#view").buttonDisable();
		}
}
function viewOrgInfo(rowid){
	if(rowid==null){
 		return;
	}
	var partyOrgInfo =  $("#partyOrgInfoList").getRowData(rowid);
	$("#partyOrgInfoDialog").createDialog({
		width: dialogWidth,
		height: dialogHeight,
		title:'查看党员组织信息',
		modal : true,
		url:'${path}/baseinfo/partyOrgInfoManage/dispatchOperate.action?mode=view&partyOrgInfo.id='+rowid+'&orgId='+getCurrentOrgId(),
		buttons: {
		   "关闭" : function(){
		        $(this).dialog("close");
		   }
		}
	});
}


function setCompVal(){
	var selectedIds = $("#partyOrgInfoList").jqGrid("getGridParam", "selarrrow");
	return selectedIds;
}
function isEmphasisFormatter(){
	var idCollection = new Array();
	idCollection=$("#partyOrgInfoList").getDataIDs();
		for(var i=0;i<idCollection.length;i++){
			var ent =  $("#partyOrgInfoList").getRowData(idCollection[i]);
			if(ent.isEmphasis==1){
			$("#"+idCollection[i]+"").css('color','#778899');
		}
	}
}

function orgActivityFormatter(el, options, rowData){
	return "<a href='javascript:void(0)' onclick=viewOrgActivityInfo("+rowData.id+") style='color:#666666' class='popUpMore'><span><strong class='ui-ico-cakan'></strong>详细</span></a>";
}

function viewOrgActivityInfo(selectedId){
	var partyOrgInfo=$("#partyOrgInfoList").getRowData(selectedId);
	$("#partyOrgInfoDialog").createDialog({
		width:900,
		height:500,
		title:"查看下级党建党组织活动记录信息",
		url:"${path}/baseinfo/partyOrgInfoManage/partyOrgActivityList.action?partyOrgId="+selectedId,
		buttons: {
		   "关闭" : function() {
		        $(this).dialog("close");
		   }
		}
	});
}

function getSelectedIdLast(){
	var selectedId;
	var selectedIds = $("#partyOrgInfoList").jqGrid("getGridParam", "selarrrow");
	for(var i=0;i<selectedIds.length;i++){
		selectedId = selectedIds[i];
	}
	return selectedId;
}

$("#refreshOrgTree1").click(function(){
	$("#searchText").attr("value","");
});

</script>