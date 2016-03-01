<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<div class="btnbanner btnbannerData">
	<div class="ui-widget autosearch">
	     <label>发生时间：&nbsp;从&nbsp;</label><input type="text" id="_startOccurDate"  readonly="readonly" class="form-txt" style="width:15%;" />
		 <label>&nbsp;至&nbsp;&nbsp;</label><input type="text" id="_endOccurDate" readonly="readonly" class="form-txt"  style="width:15%;" />
	     <label>&nbsp;&nbsp;发生地点：&nbsp;</label>
	    <input type="text" value="请输入<s:text name="list.search.button.name"/>条件" id="_searchcontent" maxlength="24" class="searchtxt" onblur="value=(this.value=='')?'请输入<s:text name="list.search.button.name"/>条件':this.value;" onfocus="value=(this.value=='请输入<s:text name="list.search.button.name"/>条件')?'':this.value;" style="width:275px;height:22px;"/>
		<button id="_refreshSearchKey" class="ui-icon ui-icon-refresh searchbtnico"></button>
	</div>
	<a href="javascript:;" id="_fastSearch"><span><s:text name="list.search.button.name"/></span></a>
</div>


<div class="content">
	<div class="ui-corner-all" id="nav">
		<a id="addServiceRecord" href="javascript:;"><span>新增</span></a>
		<a id="editServiceRecord" href="javascript:void(0)">修改</span></a>
		<a id="deleteServiceRecord" href="javascript:void(0)"><span>删除</span></a>
		<a id="viewServiceRecord" href="javascript:void(0)"><span>查看</span></a>
		<a id="searchServiceRecord" href="javascript:void(0)"><span><s:text name="list.query.button.name"/></span></a>
   		<a id="reloadServiceRecord"  href="javascript:void(0)"><span>刷新</span></a>
		<a id="exportServiceRecord" href="javascript:void(0)"><span>导出</span></a>
	</div>
	<div style="clear: both;"></div>
	<div style="width: 100%;">
		<table id="serviceRecordList"> </table>
		<div id="serviceRecordListPager"></div>
	</div>
	
	<div id="serviceRecordDialog"></div>
	<input type="hidden" id="_teamId_" value="${serviceRecord.manageTeam.id}"/>
</div>

<script type="text/javascript">
function formatterAttachFile(el,options,rowData){
	if(rowData.attachments.length>0){
	$("#serviceRecordList").data( "'"+rowData.id+"'", rowData.attachments);
		return "<div style='clear:both' align='center'><a href='javascript:;' id='serviceRecord_"+rowData.id+"' style='color:#666666' class='popUpMore' serviceRecordId='"+rowData.id+"' >附件>></a></div>";
	}
	return "";
}
$(document).ready(function(){
	$("#_startOccurDate, #_endOccurDate").datePicker({
		yearRange: '2000:2030',
		defaultDate: "+1d",
		onSelect: function( selectedDate ) {
			var option = this.id == "_startOccurDate" ? "minDate" : "maxDate",
				instance = $( this ).data( "datepicker" ),
				date = $.datepicker.parseDate(
						instance.settings.dateFormat ||
						$.datepicker._defaults.dateFormat,
						selectedDate, instance.settings );
			$("#_startOccurDate, #_endOccurDate").not( this ).datepicker( "option", option, date);
		}
	});
	
	$("#_refreshSearchKey").click(function(){$("#_searchcontent").attr("value","");});
	
	$("#serviceRecordList").jqGridFunction({
		datatype: "local",
		colNames:['id','发生时间','地点','参与人员','服务对象','内容','附件','创建时间'],
	   	colModel:[
	        {name:'id',index:'id',hidden:true},
	        {name:'occurDate',sortable:true,width:60},
	        {name:'occurPlace',sortable:false, width:120},
	        {name:'serviceMembers',sortable:true, width:150},
	        {name:'serviceObjects',sortable:true, width:210},
	   		{name:'serviceContent',sortable:false, width:150},
      		{name:'attachments',sortable:false, width:90, formatter:formatterAttachFile},
      		{name:'createDate',sortable:false,hidden:true,width:80}
		],
		height:355,  
	    loadComplete: afterLoad,
	    multiselect:true,
	    onSelectRow:selectRow,
	    ondblClickRow: viewServiceRecord
	});
	
	loadRecords();
	
	function afterLoad() {
		disableButtons();
		checkExport();
		loadFiles();
	}
	
	function selectRow(){
		var selectedCounts = getActualjqGridMultiSelectCount("serviceRecordList");
		var count = $("#serviceRecordList").jqGrid("getGridParam","records");
		if(selectedCounts == count){
			jqGridMultiSelectState("serviceRecordList", true);
		}else{
			jqGridMultiSelectState("serviceRecordList", false);
		}
		if(selectedCounts==0) {
			disableButtons();
			
		}else if(selectedCounts==1){
			$("#editServiceRecord").buttonEnable();
			$("#deleteServiceRecord").buttonEnable();
			$("#viewServiceRecord").buttonEnable();
		}else{
			$("#editServiceRecord").buttonDisable();
			$("#deleteServiceRecord").buttonEnable();
			$("#viewServiceRecord").buttonDisable();
		}
	}
	
	function disableButtons(){
		$("#editServiceRecord").buttonDisable();
		$("#deleteServiceRecord").buttonDisable();
		$("#viewServiceRecord").buttonDisable();
	}
	
	function checkExport(){
		if($("#serviceRecordList").getGridParam("records") > 0
				|| $("#serviceRecordList").getGridParam("selrow")!=null){
			$("#exportServiceRecord").buttonEnable();
		}else{
			$("#exportServiceRecord").buttonDisable();
		}
	}
	
	function viewServiceRecord() {
		var selectedId = $("#serviceRecordList").jqGrid("getGridParam", "selarrrow");
		if(null == selectedId || selectedId.length == 0) return;
		$("#serviceRecordDialog").createDialog({
			width: 600,
			height: 400,
			title: '查看服务记录信息',
			url:'${path}/baseinfo/serviceRecordManage/dispatchForTeam.action?mode=view&serviceRecord.id='+selectedId,
			buttons: {
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
	}
	
	$("#addServiceRecord").click(function(){
		$("#serviceRecordDialog").createDialog({
			width: 600,
			height: 450,
			title: '新增服务记录',
			url:'${path}/baseinfo/serviceRecordManage/dispatchForTeam.action?mode=add&serviceRecord.manageTeam.id='+$("#_teamId_").val(),
			buttons: {
		   		"保存" : function(event){
					$("#serviceRecord_form").submit();
	   			},
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
	});
	
	$("#editServiceRecord").click(function(){
		var selectedId = $("#serviceRecordList").jqGrid("getGridParam", "selarrrow");
		if(null == selectedId || selectedId.length == 0) return;
		$("#serviceRecordDialog").createDialog({
			width: 600,
			height: 450,
			title: '修改服务记录',
			url:'${path}/baseinfo/serviceRecordManage/dispatchForTeam.action?mode=edit&serviceRecord.id='+selectedId,
			buttons: {
		   		"保存" : function(event){
					$("#serviceRecord_form").submit();
	   			},
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
	});
	
	$("#deleteServiceRecord").click(function(){
		var selectedIds = $("#serviceRecordList").jqGrid("getGridParam", "selarrrow");
		if(null == selectedIds || selectedIds.length == 0) return null;
		$.confirm({
			title:"确认删除",
			message:"该服务记录信息确定要删除吗?",
			okFunc: function() {
				$.ajax({
					url:"${path}/baseinfo/serviceRecordManage/deleteServiceRecordByIds.action?recordIds="+selectedIds,
					success:function(data){
						$("#serviceRecordList").trigger("reloadGrid");
					    $.messageBox({message:"已经成功删除服务记录信息!"});
					    afterLoad();
					}
				});
			}
		});
	});
	
	$("#_fastSearch").click(function(){
		var startOccurDate = $("#_startOccurDate").val();
		var endOccurDate = $("#_endOccurDate").val();
		var searchContent = $("#_searchcontent").val();
		if('' == startOccurDate && '' == endOccurDate && "请输入<s:text name="list.search.button.name"/>条件" == searchContent) {
			return;
		}
		if("请输入<s:text name="list.search.button.name"/>条件" == searchContent) {
			searchContent = "";
		}
		loadData(startOccurDate,endOccurDate,searchContent);
	});
	
	$("#exportServiceRecord").click(function(){
		if ($("#serviceRecordList").getGridParam("records")>0){
			$("#serviceRecordDialog").createDialog({
				width: 250,
				height: 200,
				title:'导出服务记录信息',
				url:'${path}/common/exportExcel.jsp',
				postData:{
					gridName:'serviceRecordList',
					downloadUrl:'${path}/baseinfo/searchServiceRecordManage/downloadServiceRecordsForTeam.action'
					},
				buttons: {
		   			"导出" : function(event){
						$("#exceldownload").submit();
		        		$(this).dialog("close");
	   				},
		   			"关闭" : function(){
		        		$(this).dialog("close");
		   			}
				},
				shouldEmptyHtml:false
			});
		}
	});
	
	$("#searchServiceRecord").click(function(){
		$("#serviceRecordDialog").createDialog({
			width: 650,
			height: 320,
			title:'服务记录查询-请输入查询条件',
			url:'${path}/baseinfo/searchServiceRecordManage/prepareSearchServiceRecord.action?fromSource=team&searchServiceRecordVo.teamId='+$("#_teamId_").val(),
			buttons: {
				"查询" : function(event){
					searchServiceRecords();
				   	$(this).dialog("close");
			   },
			   "关闭" : function(){
			        $(this).dialog("close");
			   }
			}
		});
	});
	
	function loadData(startOccurDate,endOccurDate,searchContent) {
		var listParam = {
			"searchServiceRecordVo.startOccurDate":startOccurDate,
			"searchServiceRecordVo.endOccurDate":endOccurDate,
			"searchServiceRecordVo.occurPlace":searchContent,
			'searchServiceRecordVo.teamId':$("#_teamId_").val()
		};
		$("#serviceRecordList").setGridParam({
			url:'${path}/baseinfo/searchServiceRecordManage/searchServiceRecordsForTeam.action',
			datatype: "json",
			page:1
		});
		$("#serviceRecordList").setPostData(listParam);
		$("#serviceRecordList").trigger("reloadGrid");
	}
	
	function searchServiceRecords() {
		var data = $("#serviceRecord_form").serializeArray();
		$("#serviceRecordList").setGridParam({
			url:"${path}/baseinfo/searchServiceRecordManage/searchServiceRecords.action",
			datatype: "json",
			page:1,
			mtype:"post"
		});
		$("#serviceRecordList").setPostData(data);
	    $("#serviceRecordList").trigger("reloadGrid");
	}
	
	
	$("#viewServiceRecord").click(function(){
		viewServiceRecord();
	});
	
	$("#reloadServiceRecord").click(function(){
		$("#_startOccurDate").val('');
		$("#_endOccurDate").val('');
		$("#_searchcontent").val('请输入<s:text name="list.search.button.name"/>条件');
		loadRecords();
	});
	
	function loadRecords() {
		var startOccurDate = $("#_startOccurDate").val();
		var endOccurDate = $("#_endOccurDate").val();
		var searchContent = $("#_searchcontent").val();
		if("请输入<s:text name="list.search.button.name"/>条件" == searchContent) {
			searchContent = "";
		}
		loadData(startOccurDate,endOccurDate,searchContent);
	}
	
	function loadFiles() {
		$.each($(".popUpMore"), function(i, n){
			$.ajax({
				url:"${path}/baseinfo/serviceRecordManage/listServiceRecordAttachments.action?serviceRecord.id="+$(n).attr("serviceRecordId"),
				success:function(attachFileList){
					if(null != attachFileList){
						var popMoreData = [];
						for(var j = 0; j < attachFileList.length; j++){
							popMoreData[j]={id:attachFileList[j].id, url:'${path}/baseinfo/searchServiceRecordManage/downloadServiceRecordAttachment.action?attachmentId='+attachFileList[j].id, text:attachFileList[j].fileName,clickFun:function(){}};
						}
						$(n).popUpMore({data: popMoreData});
					}
				}
			}
			$("#serviceRecord_"+rowData.id).popUpMore({data: popMoreData});
		}
	});
}
</script>

