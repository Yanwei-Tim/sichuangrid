<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>

<div class="content">
	<div class="ui-corner-all" id="nav">
		<div class="btnbanner btnbannerData">
			<div class="ui-widget autosearch">
			    <input class="basic-input" type="text" value="请输入发生地点" id="_searchcontent" maxlength="24" class="searchtxt" onblur="value=(this.value=='')?'请输入发生地点':this.value;" onfocus="value=(this.value=='请输入发生地点')?'':this.value;"/>
				<button id="_refreshSearchKey" class="ui-icon ui-icon-refresh searchbtnico" style="background-color:transparent; background:url(/resource/system/js/jqueryui/default/images/ui-icons_0078ae_256x240.png) no-repeat -64px -80px;"></button>
			</div>
			<a href="javascript:;" id="_fastSearch"><span>搜索</span></a>
		</div>
		<pop:JugePermissionTag ename="serviceRecordManagement">
			<pop:JugePermissionTag ename="searchServiceRecord">
				<a id="searchSuperviseRecord" href="javascript:void(0)"><span>高级搜索</span></a>
			</pop:JugePermissionTag>
			<pop:JugePermissionTag ename="addServiceRecord">
				<a id="addSuperviseRecord" href="javascript:;"><span>新增</span></a>
			</pop:JugePermissionTag>
			<pop:JugePermissionTag ename="updateServiceRecord">
				<a id="editSuperviseRecord" href="javascript:;"><span>修改</span></a>
			</pop:JugePermissionTag>
			<pop:JugePermissionTag ename="deleteServiceRecord">
				<a id="deleteSuperviseRecord"" href="javascript:void(0)"><span>批量删除</span></a>
			</pop:JugePermissionTag>
			<pop:JugePermissionTag ename="viewServiceRecord">
				<a id="viewSuperviseRecord" href="javascript:void(0)"><span>查看</span></a>
			</pop:JugePermissionTag>
			<pop:JugePermissionTag ename="exportServiceRecord">
				<a id="exportSuperviseRecord" href="javascript:void(0)"><span>导出</span></a>
			</pop:JugePermissionTag>
		</pop:JugePermissionTag>
   		<a id="reloadSuperviseRecord"  href="javascript:void(0)"><span>刷新</span></a>
	</div>
	<div style="clear: both;"></div>
	<div style="width: 100%;">
		<table id="superviseRecordList"> </table>
		<div id="superviseRecordListPager"></div>
	</div>

	<div id="superviseRecordDialog"></div>
	<input type="hidden" id="_population_id_" value="${population.id}"/>
	<input type="hidden" id="_population_attentionPopulationType_" value="${population.attentionPopulationType}"/>
	<input type="hidden" id="_location_id_" value="<s:property value='#parameters["location.id"]'/>"/>
	<input type="hidden" id="_location_type_" value="${locationType}"/>
	<input type="hidden" id="_location_name_" value="<s:property value='#parameters["location.name"]'/>"/>

</div>

<script type="text/javascript">
function formatterAttachFile(el,options,rowData){
	if(rowData.attachments.length>0){
	$("#superviseRecordList").data( "'"+rowData.id+"'", rowData.attachments);
		return "<div style='clear:both' align='center'><a href='javascript:;' id='serviceRecord_"+rowData.id+"' style='color:#666666' class='popUpMore' serviceRecordId='"+rowData.id+"' >附件>></a></div>";
	}
	return "";
}
function operateFormatter(el,options,rowData){
	return "<a href='javascript:updateRecord("+rowData.id+")'><span>修改</span></a> | <a href='javascript:deleteOperator("+rowData.id+")'><span>删除</span></a>";
}
function updateRecord(selectedId){
	var locationType=$("#_location_type_").val();
	var objectTitle="";
	if(locationType==null || locationType==""){
		objectTitle="修改服务记录";
	}else{
		objectTitle="修改巡场情况";
	}
	$("#superviseRecordDialog").createDialog({
		width: 600,
		height: 400,
		title: objectTitle,
		url:'${path}/baseinfo/serviceRecordManage/dispatch.action?mode=edit&serviceRecord.id='+selectedId+'&locationId='+$("#_location_id_").val()+'&locationType='+$("#_location_type_").val(),
		postData:{locationName:$("#_location_name_").val()},
		buttons: {
	   		"保存" : function(event){
				$("#serviceRecord_form").submit();
   			},
	   		"关闭" : function(){
	        	$(this).dialog("close");
	   		}
		}
	});
}
function deleteOperator(selectedIds){
	var locationType=$("#_location_type_").val();
	var mess="";
	str='';
	if(locationType==null || locationType==""){
		mess="该服务记录信息确定要删除吗?";
		str='服务记录';
	}else{
		mess="该巡场情况信息确定要删除吗?";
		str='巡场情况';
	}
	$.confirm({
		title:"确认删除",
		message:mess,
		okFunc: function() {
			$.ajax({
				url:"${path}/baseinfo/serviceRecordManage/deleteServiceRecordByIds.action?recordIds="+selectedIds,
				success:function(data){
					$("#superviseRecordList").trigger("reloadGrid");
				    $.messageBox({message:"已经成功删除"+str+"信息!"});
				    afterLoad();
				}
			});
		}
	});
}

$(document).ready(function(){

	$("#_refreshSearchKey").click(function(){
		$("#_searchcontent").attr("value",'请输入发生地点');
	});

	$("#superviseRecordList").jqGridFunction({
		datatype: "local",
		colNames:['id','操作','发生时间','地点','服务人员','内容','附件','创建时间'],
	   	colModel:[
	        {name:'id',index:'id',hidden:true},
	        {name:"operation",index:"id",sortable:false,formatter:operateFormatter,width:80,align:"center"},
	        {name:'occurDate',sortable:true,width:90,align:"center"},
	        {name:'occurPlace',sortable:false, width:150},
	        {name:'serviceMembers',sortable:true, width:150},
	   		{name:'serviceContent',sortable:false, width:210},
      		{name:'attachments',sortable:false, width:150, formatter:formatterAttachFile},
      		{name:'createDate',sortable:false, width:150, hidden:true}
		],
		height:355,
	    loadComplete: afterLoad,
	    multiselect:true,
	    onSelectRow:selectRow,
	    ondblClickRow: viewServiceRecord
	});

	loadRecords();

	function afterLoad() {
		checkExport();
		loadFiles();
	}

	function selectRow(){
		var selectedCounts = getActualjqGridMultiSelectCount("superviseRecordList");
		var count = $("#superviseRecordList").jqGrid("getGridParam","records");
		if(selectedCounts == count){
			jqGridMultiSelectState("superviseRecordList", true);
		}else{
			jqGridMultiSelectState("superviseRecordList", false);
		}
	}

	function checkExport(){
		if($("#superviseRecordList").getGridParam("records") > 0
				|| $("#superviseRecordList").getGridParam("selrow")!=null){
			$("#exportSuperviseRecord").buttonEnable();
		}
	}

	function viewServiceRecord() {
		var selectedId = $("#superviseRecordList").jqGrid("getGridParam", "selarrrow");
		if(null == selectedId || selectedId.length == 0){
			$.messageBox({level:'warn',message:"请选择一条数据进行查看！"});
			return;
		}
		if(selectedId.length>1){
			$.messageBox({level:'warn',message:"只能选择一条数据进行查看！"});
			return;
		}
		var locationType=$("#_location_type_").val();
		if(locationType==null||locationType==""){
			$("#superviseRecordDialog").createDialog({
				width: 600,
				height: 400,
				title: '查看服务记录信息',
				url:'${path}/baseinfo/serviceRecordManage/dispatch.action?mode=view&serviceRecord.id='+selectedId,
				buttons: {
			   		"关闭" : function(){
			        	$(this).dialog("close");
			   		}
				}
			});
		}else{
			$("#superviseRecordDialog").createDialog({
				width: 600,
				height: 400,
				title: '查看巡场情况',
				url:'${path}/baseinfo/serviceRecordManage/dispatch.action?mode=view&serviceRecord.id='+selectedId+'&locationId='+$("#_location_id_").val(),
				buttons: {
			   		"关闭" : function(){
			        	$(this).dialog("close");
			   		}
				}
			});
		}
	}

	$("#addSuperviseRecord").click(function(){
		var locationType=$("#_location_type_").val();
		if(locationType==null||locationType==""){
			$("#superviseRecordDialog").createDialog({
				width: 600,
				height: 430,
				title: '新增服务记录',
				url:'${path}/baseinfo/serviceRecordManage/dispatch.action?mode=add&population.id='+$("#_population_id_").val()+"&population.attentionPopulationType="+$("#_population_attentionPopulationType_").val(),
				buttons: {
			   		"保存" : function(event){
						$("#serviceRecord_form").submit();
		   			},
			   		"关闭" : function(){
			        	$(this).dialog("close");
			   		}
				}
			});
		}else{
			
			$("#superviseRecordDialog").createDialog({
				width: 600,
				height: 400,
				title: '新增巡场情况',
				url:'${path}/baseinfo/serviceRecordManage/dispatch.action?mode=add&locationId='+$("#_location_id_").val()+"&locationType="+$("#_location_type_").val(),
				postData:{locationName:$("#_location_name_").val()},
				buttons: {
			   		"保存" : function(event){
						$("#serviceRecord_form").submit();
		   			},
			   		"关闭" : function(){
			        	$(this).dialog("close");
			   		}
				}
			});
		}

	});

	$("#editSuperviseRecord").click(function(){
		var selectedId = $("#superviseRecordList").jqGrid("getGridParam", "selarrrow");
		if(null == selectedId || selectedId.length == 0) {
			$.messageBox({level:'warn',message:"请选择一条或多条数据进行修改！"});
			return;
			}
		updateOperator(selectedId);
	});

	$("#deleteSuperviseRecord").click(function(){
		var selectedIds = $("#superviseRecordList").jqGrid("getGridParam", "selarrrow");
		if(null == selectedIds || selectedIds.length == 0){
			$.messageBox({level:'warn',message:"请选择一条或多条数据进行删除！"});
			return;
		}
		deleteOperator(selectedIds);
	});

	$("#_fastSearch").click(function(){
		var searchContent = $("#_searchcontent").val();
		if("请输入发生地点" == searchContent) {
			searchContent = "";
		}
		loadData(searchContent);
	});

	$("#exportSuperviseRecord").click(function(){
		var str='';
		if($("#_location_type_").val()==null || $("#_location_type_").val()==''){
			str='导出服务记录信息';
		}else{
			str='导出巡场情况';
		}
		if ($("#superviseRecordList").getGridParam("records")>0){
			$("#superviseRecordDialog").createDialog({
				width: 250,
				height: 200,
				title:str,
				url:'${path}/common/exportExcel.jsp',
				postData:{
					gridName:'superviseRecordList',
					downloadUrl:'${path}/baseinfo/searchServiceRecordManage/downloadServiceRecords.action'
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
		}else{
			$.messageBox({level:'warn',message:"没有可导出的数据！"});
			return;
		}
	});

	$("#searchSuperviseRecord").click(function(){
		var locationType=$("#_location_type_").val();
		if(locationType==null||locationType==""){
			$("#superviseRecordDialog").createDialog({
				width: 650,
				height: 320,
				title:'服务记录查询-请输入查询条件',
				url:'${path}/baseinfo/searchServiceRecordManage/prepareSearchServiceRecord.action?fromSource=population&searchServiceRecordVo.populationId='+$("#_population_id_").val()+"&searchServiceRecordVo.populationType="+$("#_population_attentionPopulationType_").val(),
				buttons: {
					"查询" : function(event){
						searchSuperviseRecords();
					   	$(this).dialog("close");
				   },
				   "关闭" : function(){
				        $(this).dialog("close");
				   }
				}
			});
		}else{
			$("#superviseRecordDialog").createDialog({
				width: 650,
				height: 320,
				title:'巡场情况查询-请输入查询条件',
				url:'${path}/baseinfo/searchServiceRecordManage/prepareSearchServiceRecord.action?fromSource=population&searchServiceRecordVo.locationId='+$("#_location_id_").val()+"&searchServiceRecordVo.locationType="+$("#_location_type_").val(),
				buttons: {
					"查询" : function(event){
						searchSuperviseRecords();
					   	$(this).dialog("close");
				   },
				   "关闭" : function(){
				        $(this).dialog("close");
				   }
				}
			});
		}

	});

	function loadData(searchContent) {
		var listParam = {
			"searchServiceRecordVo.occurPlace":searchContent,
			'searchServiceRecordVo.populationId':$("#_population_id_").val(),
			'searchServiceRecordVo.populationType':$("#_population_attentionPopulationType_").val(),
			'searchServiceRecordVo.locationId':$("#_location_id_").val(),
			'searchServiceRecordVo.locationType':$("#_location_type_").val(),
			'searchServiceRecordVo.displayLevel':'all',
			'searchServiceRecordVo.organization.id':'<s:property value="@com.tianque.core.util.ThreadVariable@getUser().organization.id"/>'
		};
		$("#superviseRecordList").setGridParam({
			url:'${path}/baseinfo/searchServiceRecordManage/searchServiceRecords.action',
			datatype: "json",
			page:1
		});
		$("#superviseRecordList").setPostData(listParam);
		$("#superviseRecordList").trigger("reloadGrid");
	}

	function searchSuperviseRecords() {
		var data = $("#serviceRecord_form").serializeArray();
		$("#superviseRecordList").setGridParam({
			url:"${path}/baseinfo/searchServiceRecordManage/searchServiceRecords.action",
			datatype: "json",
			page:1,
			mtype:"post"
		});
		$("#superviseRecordList").setPostData(data);
	    $("#superviseRecordList").trigger("reloadGrid");
	}


	$("#viewSuperviseRecord").click(function(){
		viewServiceRecord();
	});

	$("#reloadSuperviseRecord").click(function(){
		$("#_searchcontent").val('请输入发生地点');
		loadRecords();
	});

	function loadRecords() {
		var searchContent = $("#_searchcontent").val();
		if("请输入发生地点" == searchContent) {
			searchContent = "";
		}
		loadData(searchContent);
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
			})
		});
	}

	function updateOperator(event,selectedId){
		if(!checkedOrgId(getCurrentOrgId())){
			return;
		}
		$("#serviceRecordDialog").createDialog({
			width: 600,
			height: 400,
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
		var evt = event || window.event;
		if (window.event) { 
		evt.cancelBubble=true; 
		} else { 
		evt.stopPropagation(); 
		} 
	}

});

</script>

