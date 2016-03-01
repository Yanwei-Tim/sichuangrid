<%@page import="java.util.Properties"%>
<%@page import="java.io.FileInputStream"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<jsp:include page="/includes/baseInclude.jsp" />

<%

	String dir = System.getProperty("user.dir");
	String str = dir + "\\webroot\\interaction\\newSMS\\sms.properties";
	FileInputStream inputFile = new FileInputStream(str);
	
	Properties propertie = new Properties();
	propertie.load(inputFile);
	inputFile.close();
	
	String realmName= propertie.getProperty("sms.url");
	String port = propertie.getProperty("sms.port");
	
	String url="http://"+realmName+":"+port+"/globalListServletForJsonp.action";
%>

<div class="content">
	<div style="clear: both;"></div>
	<div style="width: 100%;">
		<table id="smsManagementList">
		</table>
		<div id="smsManagementListPager"></div>
	</div>
	<div id="smsManagementDialog"></div>
	<div id="processChoseDialog"></div>
	<div id="noticeDialog"></div>
</div>
<script type="text/javascript">

//Formatter
function operatorFormatter(el,options,rowData){
	var globalshowname=rowData.globalshowname;
	if(globalshowname.trim()==""){
		globalshowname="";
	}
	var globalvalue=rowData.globalvalue;
	if(globalvalue.trim()==""){
		globalvalue="";
	}
	
	return "<a href=javascript:updateOperator('"+rowData.id+"','"+rowData.globalname+"','"+globalshowname+"','"+globalvalue+"','"+rowData.describtion+"')><span>修改</span></a>";
}

$(function(){
	var url="<%=url%>";
	
	getSend(url);
	
	function toggleButtonState(){
	}
	function afterLoad(){
	}
	
	// 生成列表
	$("#smsManagementList").jqGridFunction({
		mtype:'post',
		datatype: "local",
	   	colModel:[
			{name:"id",index:'id',hidden:true},
			{name:"operator", index:'sendTheDay',label:'操作',formatter:operatorFormatter,width:100,frozen:true,sortable:false,align:'center' },
	    	{name:"globalname", index:'globalname',label:'globalname',width:220,frozen:true,sortable:false,align:'center' },
	    	{name:'globalshowname', index:'globalshowname',label:'globalshowname', width:220, sortable:true, align:'center', hidden:false}, 	
	    	{name:'globalvalue', index:'globalvalue',label:'globalvalue', width:140, sortable:true, align:'center', hidden:false}, 	
			{name:'describtion', index:'describtion',label:'说明', width:380, sortable:true, align:'center', hidden:false}
	   	]
	});
	jQuery("#smsManagementList").jqGrid('setFrozenColumns');
	
	
});


function getSend(url){
	$.ajax({
		type : "get",
		url : url,
		dataType : "jsonp",
		jsonp:'callback',  
		success : function(data) {
			$.each(data,function(entryIndex,entry){
				
				var result={
					"id":entryIndex,
					"globalname":entry['globalName'],
					"globalshowname":entry['globalShowName'],
					"globalvalue":entry['globalValue'],
					"describtion":entry['describtion']
				};
				
				$("#smsManagementList").addRowData(result.id,result,"first");				
           });
		}
	});
	
}


function updateOperator(smsId,globalName,globalShowName,globalValue,describtion){
	$("#smsManagementDialog").createDialog({
		title:"修改短信信息",
		width: 370,
		height: 200,
		url:'${path}/interaction/newSMS/smstrafficmanageManage/smsManagementViewDlg.jsp?port=<%=port %>&realmName=<%=realmName %>&smsId='+smsId+'&globalName='+globalName+'&globalShowName='+globalShowName+'&globalValue='+globalValue+'&describtion='+describtion,
		buttons: {
			"保存" : function(){
				$("#maintainForm").submit();  
			},
			"关闭" : function(){
				$(this).dialog("close");
			}
		}
			
	});
}

function getSelectedIdLast(){
	var selectedId;
	var selectedIds = $("#smsManagementList").jqGrid("getGridParam", "selarrrow");
	for(var i=0;i<selectedIds.length;i++){
		selectedId = selectedIds[i];
	}
	return selectedId;
}
function getSelectedIds(){
	var selectedIds = $("#smsManagementList").jqGrid("getGridParam", "selarrrow");
	return selectedIds;
}


</script>