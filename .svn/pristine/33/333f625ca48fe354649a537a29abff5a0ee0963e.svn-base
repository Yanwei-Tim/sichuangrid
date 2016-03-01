<%@page import="java.util.Properties"%>
<%@page import="java.io.FileInputStream"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<jsp:include page="/includes/baseInclude.jsp" />

<%
	String Url = System.getProperty("user.dir");
	String str = Url + "\\webroot\\interaction\\newSMS\\sms.properties";
	FileInputStream inputFile = new FileInputStream(str);
	
	Properties propertie = new Properties();
	propertie.load(inputFile);
	inputFile.close();
	
	String cmccSocketUrl= propertie.getProperty("cmcc.url");
	String cmccSocketPort = propertie.getProperty("cmcc.port");
	String cmccUrl = "http://" + cmccSocketUrl + ":" + cmccSocketPort
	+ "/countReceive.action?type=cmcc";
	
	
	String telecomSocketUrl= propertie.getProperty("telecom.url");
	String telecomSocketPort = propertie.getProperty("telecom.port");
	
	String telecomUrl = "http://" + telecomSocketUrl + ":"
		+ telecomSocketPort + "/countReceiveMessageForJsonp.action?type=telecom";
	
	String unicomSocketUrl= propertie.getProperty("unicom.url");
	String unicomSocketPort = propertie.getProperty("unicom.port");
	String unicomUrl = "http://" + unicomSocketUrl + ":"
		+ unicomSocketPort + "/countReceiveMessageForJsonp.action?type=unicom";

%>

<div class="content">
	<div style="clear: both;"></div>
	<div style="width: 100%;">
		<table id="receiveMessageList">
		</table>
		<div id="receiveMessageListPager"></div>
	</div>
	<div id="receiveMessageDialog"></div>
	<div id="processChoseDialog"></div>
	<div id="noticeDialog"></div>
</div>
<script type="text/javascript">
var dialogWidth = 880;
var dialogHeight = 600;


$(function(){
	
	var url =  "<%=cmccUrl%>";
	
	getReceive(url);
	
<%-- 	url = "<%=telecomUrl %>"; --%>
	
// 	getReceive(url);
	
<%-- 	url = "<%=unicomUrl %>"; --%>
	
// 	getReceive(url);
	
	function toggleButtonState(){
	}
	function afterLoad(){
	}
	
	// 生成列表
	$("#receiveMessageList").jqGridFunction({
		mtype:'post',
		datatype: "local",
	   	colModel:[
			{name:"id",index:'id',hidden:true},
	    	{name:"gateway", index:'gateway',label:'网关',width:280,frozen:true,sortable:false,align:'center' },
	    	{name:'totalUpload', index:'totalUpload',label:'总接收数', width:170, sortable:true, align:'center', hidden:false}, 	
	    	{name:'yesterdayUpload', index:'yesterdayUpload',label:'昨天接收成功数', width:300, sortable:true, align:'center', hidden:false}, 	
			{name:'todayUpload', index:'todayUpload',label:'今天接收成功数', width:300, sortable:true, align:'center', hidden:false}
	   	]
	});
	jQuery("#receiveMessageList").jqGrid('setFrozenColumns');
	
	
});


function getReceive(url){
	$.ajax({
		type : "get",
		url : url,
		dataType: "jsonp",
		jsonp:'callback',  
		success : function(data) {
			$.each(data,function(entryIndex,entry){
				
				var id = entry['id'];
				var result={
					"id":id,
					"gateway":getName(id)+"(从"+entry['startDate']+"开始统计)",
					"totalUpload":entry['totalUpload'],
					"yesterdayUpload":entry['yesterdayUpload'],
					"todayUpload":entry['todayUpload']
				};
				
				
				$("#receiveMessageList").addRowData(result.id,result,"first");
				
		   });
		}
	});
}


function getName(id){
	if(id ==1){
		return "移动";
	}else if(id ==2){
		return "联通";
	}else if(id ==3){
		return "小灵通";
	}else if(id ==4){
		return "电信";
	}
	return "";
}

function getSelectedIdLast(){
	var selectedId;
	var selectedIds = $("#receiveMessageList").jqGrid("getGridParam", "selarrrow");
	for(var i=0;i<selectedIds.length;i++){
		selectedId = selectedIds[i];
	}
	return selectedId;
}
function getSelectedIds(){
	var selectedIds = $("#receiveMessageList").jqGrid("getGridParam", "selarrrow");
	return selectedIds;
}


</script>