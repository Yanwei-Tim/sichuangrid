<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<jsp:include page="/includes/baseInclude.jsp" />
<s:action name="getLoginInfo" var="loginAction" executeResult="false" namespace="/sessionManage" />
<div class="content">
<div class="ui-corner-all" id="nav">
	<a id="refreshLog" href="javascript:void(0)"><span>刷新</span></a>
	<s:if test="@com.tianque.core.util.ThreadVariable@getUser().getUserName().equals('longzhendong')">
	<a id="addLog" href="javascript:void(0)"><span>新增日志</span></a>
	<a id="executionJob" href="javascript:void(0)"><span>执行</span></a>
	<a id="deleteLog" href="javascript:void(0)"><span>删除日志</span></a>
	</s:if>
	</div>
	<div style="width: 100%;">
		<table id="permanentAddressLogList"></table>
		<div id="permanentAddressLogListPager"></div>
	</div>
	<div id="addAddressCleanDlg" style="overflow: hidden"></div>
</div>

<script type="text/javascript">
$(document).ready(function () {
	$("#permanentAddressLogList").jqGridFunction({
		url: '${path}/baseinfo/permanentAddressManage/findPermanentAddressCleanLog.action',
		sortname:"createDate",
		colModel: [
			{name:"id",index:"id",hidden:true,key:true},
			{name:'province', index: 'province',label:'原户籍地省',width:100,sortable:true},
			{name:'city',index:'city',label:'原户籍地市',width:100,sortable:true},
			{name:'district',index:'district',label:'原户籍地区县',width:100,sortable:true},
			{name:'addressNo',index:'addressNo',label:'户籍地编号',width:100,sortable:true},
			{name:'changedName',index:'changedName',label:'新户籍地名称',width:100,sortable:true},
			{name:'addslevel',index:'addslevel',label:'操作层级',width:80,formatter:addslevelFormatter,sortable:true},
			{name:'optionType',index:'optionType',label:'操作类别',width:80,formatter:optionTypeFormatter,sortable:true},
			{name:'jobState',index:'jobState',label:'job执行状态',width:80,formatter:jobStateFormatter,sortable:true},
			{name:'createUser',index:'createUser',label:'创建者',width:50,sortable:true},
			{name:'createDate',index:'createDate',label:'创建时间',width:150,sortable:true}
		],
		multiselect:true,
		showColModelButton:true
		
	}); 
	
	function addslevelFormatter(el,options,rowData){
		var addressLevel = rowData.addslevel;
		if(addressLevel == 1  || addressLevel=='1'){
			return "省级";
		}else if(addressLevel==2 || addressLevel=='2'){
			return "市级";
		}else if(addressLevel==3 || addressLevel=='3'){
			return "区县级";
		}else{
			return "";
		}
	}	
	
	function optionTypeFormatter(el,options,rowData){
		var optionType = rowData.optionType;
		if(optionType==1 || optionType =='1'){
			return "新增";
		}else if(optionType==2 || optionType =='2'){
			return "修改";
		}else if(optionType==3 || optionType =='3'){
			return "删除";
		}else{
			return "";
		}
	}
	
	function jobStateFormatter(el,options,rowData){
		var jobState = rowData.jobState;
		if(jobState!=2 &&　jobState!='2'){
			return "未处理";
		}else{
			return "已处理";
		}
	}
	
});



$("#addLog").click(function(){
	$("#addAddressCleanDlg").createDialog({
		width:680,
		height:250,
		title:'新增户籍地清洗日志',
		url:'${path}/baseinfo/permanentAddressManage/doPermanentAddress.action?mode=addressClean',
		buttons :{
			"添加" : function(){
				$("#cleanForm").submit();
				$("#permanentAddressLogList").trigger("reloadGrid");
			},
			"关闭" : function(){
				$(this).dialog("close");
			}
		}
	});
});

$("#deleteLog").click(function(){
	var rowIds = $("#permanentAddressLogList").jqGrid("getGridParam", "selarrrow");
	if(rowIds.length ==0){
		 $.messageBox({level:"warn",message:"请选择一条或多条记录，再进行删除！"});
		 return;
	}
	$.confirm({
		title:"确认删除",
		message:"确定要删除吗?一经删除，日志信息无法恢复，确认?",
		okFunc: function(){
			$.ajax({
				url:'${path}/baseinfo/permanentAddressManage/deleteAddressLogByIds.action?ids='+rowIds,
				type:"post",
				data:{
				},
				success:function(data){
					$("#permanentAddressLogList").trigger("reloadGrid");
				    $.messageBox({message:"已经成功删除日志信息!"});
			    }
		    });
	   }
 	});
});

$("#executionJob").click(function(){
	var rowIds = $("#permanentAddressLogList").jqGrid("getGridParam", "selarrrow");
	if(rowIds==null || rowIds.length ==0){
		 $.messageBox({level:"warn",message:"请选择一条或多条记录，再进行执行操作！"});
		 return;
	}
	$.ajax({
		url:'${path}/baseinfo/permanentAddressManage/executionJobByIds.action?ids='+rowIds,
		type:"post",
		data:{
		}
    });
	$.messageBox({message:"清洗正在执行，请稍后查看执行记录"});
});

$("#refreshLog").click(function(){
	$("#permanentAddressLogList").trigger("reloadGrid");
});

</script>