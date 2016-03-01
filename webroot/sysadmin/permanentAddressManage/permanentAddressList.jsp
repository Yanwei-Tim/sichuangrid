<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<jsp:include page="/includes/baseInclude.jsp" />
<s:action name="getLoginInfo" var="loginAction" executeResult="false" namespace="/sessionManage" />
<div class="content">
<div class="ui-corner-all" id="nav">
	<pop:JugePermissionTag ename="searchPermanentAddr">	
	<div class="btnbanner btnbannerData">
		<select class="basic-input" id="levelSelect" name="levelSelect">
					<option value="1">省级</option>
					<option value="2">市级</option>
					<option value="3">区县级</option>
					<option value="" selected="selected">全部</option>
		</select>
		<div class="ui-widget autosearch">
		<input class="basic-input" type="text" value="请输入地区名称或编号" name="searchText" id="searchText" maxlength="18" class="searchtxt"
			style="width:180px;" onblur="value=(this.value=='')?'请输入地区名称或编号':this.value;" onfocus="value=(this.value=='请输入地区名称或编号')?'':this.value;" />
		<button id="refreshSearchKey" class="ui-icon ui-icon-refresh searchbtnico"></button>
		</div>
	</div>
	<a href="javascript:;" id="fastSearchButton"><span>搜索</span></a>
	</pop:JugePermissionTag>
	<span class="lineBetween "></span>
	<s:if test="#loginAction.user.userName=='admin'">
		<pop:JugePermissionTag ename="addPermanentAddr">
			<a id="add" href="javascript:;"><span>新增</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="updatePermanentAddr">
			<a id="update"  href="javascript:void(0)"><span>修改</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="deletePermanentAddr">
			<a id="delete" href="javascript:void(0)"><span>删除</span></a>
		</pop:JugePermissionTag>
	</s:if>
	<s:if test="@com.tianque.core.util.ThreadVariable@getUser().getUserName().equals('longzhendong')">
			<a id="addressClean" href="javascript:void(0)"><span>户籍地清洗日志</span></a>
	</s:if>
	<a id="refresh" href="javascript:void(0)"><span>刷新</span></a>
	</div>
	<div style="width: 100%;">
		<table id="permanentAddressList"></table>
		<div id="permanentAddressListPager"></div>
	</div>
	<div id="permanentAddresslog" style="overflow: hidden"></div>
	<div id="addressCleanDlg" style="overflow: hidden"></div>
</div>

<script type="text/javascript">
$(document).ready(function () {
	$("#permanentAddressList").jqGridFunction({
		url: '${path}/baseinfo/permanentAddressManage/findPermanentAddress.action',
		sortname:"addressNo",
		colModel: [
			{name:"addressNo",index:"addressNo",hidden:true,key:true},
			{name:'addressNo', index: 'addressNo',label:'地区编号',width:200,sortable:true},
			{name:'addressName',index:'addressName',label:'地区名称',width:200,sortable:true},
			{name:'parentid',index:'parentid',label:'上级编号',width:200,sortable:true},
			{name:'addressLevel',index:'addressLevel',label:'地区级别',formatter:addressLevelFormatter,width:200,sortable:true}	
		],
		multiselect:true,
		loadComplete: afterLoad,
		showColModelButton:false,
	}); 
	$("#permanentAddressList").trigger("reloadGrid");
	$("#refreshSearchKey").click(function(){$("#searchText").attr("value","请输入地区名称或编号");});
	function afterLoad(){
		setpermanentAddressOperateButton();
	}
	function setpermanentAddressOperateButton(){
		var selectedCounts = getActualjqGridMultiSelectCount("permanentAddressList");
		var count = $("#permanentAddressList").jqGrid("getGridParam","records");
		var selectedIds = getActualjqGridMultiSelectIds("permanentAddressList");
		if(selectedCounts == count && selectedCounts!=0){
			jqGridMultiSelectState("permanentAddressList", true);
		}else{
			jqGridMultiSelectState("permanentAddressList", false);
		}
		usedOrStopUsedButtom(selectedIds);
	}
	
	function usedOrStopUsedButtom(strs){
		var used = false;
		var stopused = false;
		if(strs.length==0){
			jqGridMultiSelectState("permanentAddressList", false);
		}
	}

	$("#refresh").click(function(){
		refresh();
	});

	function refresh(){
		$("#levelSelect").val("");
		$("#permanentAddressList").setGridParam({
			url:'${path}/baseinfo/permanentAddressManage/findPermanentAddress.action',
			datatype: "json",
			sortname:"addressNo",
			page:1
		});
		$("#permanentAddressList").trigger("reloadGrid");
	}
	$("#fastSearchButton").click(function(){
		var conditions = $("#searchText").val();
		if(conditions == '请输入地区名称或编号'){
			return;
		}
		$("#permanentAddressList").setGridParam({
			url:'${path}/baseinfo/permanentAddressManage/searchPermanentAddress.action',
			ortname:"addressNo",
			datatype: "json",
			page:1
		});
		$("#permanentAddressList").setPostData({
			"searchText":$("#searchText").val()
	   	});
		$("#permanentAddressList").trigger("reloadGrid");
	});

	$("#add").click(function(){
		$("#permanentAddresslog").createDialog({
			width:680,
			height:250,
			title:'添加户籍地',
			url:'${path}/baseinfo/permanentAddressManage/doPermanentAddress.action?mode=add',
			buttons :{
				"添加" : function(){
					$("#addform").submit();
					$("#permanentAddressList").trigger("reloadGrid");
				},
				"关闭" : function(){
					$(this).dialog("close");
				}
			}
		});
	});

	$("#update").click(function(){
		var selectedIds = $("#permanentAddressList").jqGrid("getGridParam", "selarrrow");
		var selectedId = getSelectedIdLast();
		if(selectedId==null || selectedIds==null || selectedIds.length>1){
			$.messageBox({level:'warn',message:"请选择一条数据，再进行修改！"});
	 		return;
		}
		var permanentAddress = $("#permanentAddressList").getRowData(selectedId);
		if(permanentAddress==""||permanentAddress==null||permanentAddress.addressNo==""){
			return;
		}
		var addressNo = permanentAddress.addressNo;
		$("#permanentAddresslog").createDialog({
			width:680,
			height:250,
			title:'修改户籍地',
			url:'${path}/baseinfo/permanentAddressManage/doPermanentAddress.action?mode=edit&addressNo='+addressNo,
			buttons :{
				"修改" : function(){
					$("#updateform").submit();
					$("#permanentAddressList").trigger("reloadGrid");
				},
				"关闭" : function(){
					$(this).dialog("close");
				}
			}
		});
	});
	
	$("#delete").click(function(){
		var selectedIds = $("#permanentAddressList").jqGrid("getGridParam", "selarrrow");
		var selectedId = getSelectedIdLast();
		if(selectedId==null || selectedIds==null || selectedIds.length>1){
			$.messageBox({level:'warn',message:"请选择一条记录，再进行删除！"});
	 		return;
		}
		var permanentAddress = $("#permanentAddressList").getRowData(selectedId);
		$.confirm({
			title:"确认删除",
			message:"该删除后就不能还原，确认删除？",
			width: 300,
			okFunc: function(){
				$.ajax({
					url:'${path}/baseinfo/permanentAddressManage/deletePermanentAddress.action?permanentAddress.addressNo='+permanentAddress.addressNo,
					success:function(data){
						if(data!=true && data!="true" ){
							$.messageBox({message:data,level:"error"});
							return ;
						}
						$("#permanentAddressList").trigger("reloadGrid");
						$.messageBox({message:"删除成功！"});
					}
				});
			}
		});
	});
	
	function getSelectedIdLast(){
		var selectedId;
		var selectedIds = $("#permanentAddressList").jqGrid("getGridParam", "selarrrow");
		for(var i=0;i<selectedIds.length;i++){
			selectedId = selectedIds[i];
		}
		return selectedId;
	}

	$("#levelSelect").change(function(event){
		var selectTypeId=$("#levelSelect").val();
		if(selectTypeId=='' || selectTypeId==null){
			refresh();
		}else if(selectTypeId!=''){
			refreshByThingsType();
		}		
	});
	
	function refreshByThingsType(){
		$("#permanentAddressList").setGridParam({
			url:'${path}/baseinfo/permanentAddressManage/getPermanentAddressByLevel.action',
			datatype:"json",
			page:1
		});
		$("#permanentAddressList").setPostData({
			"permanentAddress.addressLevel":$("#levelSelect").val(), 
	        searchChildOrg : false
	   	});
		$("#permanentAddressList").trigger("reloadGrid");
	}
	
});

function addressLevelFormatter(el,options,rowData){
	if(rowData==""||rowData.addressLevel==""){
		return "";
	}
    if(rowData.addressLevel==1){
    	return "省级";
    }else if(rowData.addressLevel==2){
   		return "市级";
   	}else if(rowData.addressLevel==3){
   		return "区县级";
   	}
	return "";
}

$("#addressClean").click(function(){
	$("#addressCleanDlg").createDialog({
		width:900,
		height:650,
		title:'新增户籍地清洗日志',
		url:PATH+'/baseinfo/permanentAddressManage/doPermanentAddress.action?mode=addressCleanLog',
		buttons :{
			"关闭" : function(){
				$(this).dialog("close");
			}
		}
	});
});
</script>

