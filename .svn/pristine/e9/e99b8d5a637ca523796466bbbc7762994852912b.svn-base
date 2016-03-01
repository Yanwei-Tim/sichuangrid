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
	
	String cmccSendUrl = "http://"+cmccSocketUrl+":"+cmccSocketPort+"/countSend.action?type=cmcc";
	String cmccRetryUrl =  "http://"+cmccSocketUrl+":"+cmccSocketPort+"/retrySend.action?id=1";
	
	String telecomSocketUrl= propertie.getProperty("telecom.url");
	String telecomSocketPort = propertie.getProperty("telecom.port");
	
	String telecomSendUrl = "http://"+telecomSocketUrl+":"+telecomSocketPort+"/countSend.action?type=telecom";
	String telecomRetryUrl =  "http://"+telecomSocketUrl+":"+telecomSocketPort+"/retrySend.action?id=4";
	
	String unicomSocketUrl= propertie.getProperty("unicom.url");
	String unicomSocketPort = propertie.getProperty("unicom.port");
	String unicomSendUrl = "http://"+unicomSocketUrl+":"+unicomSocketPort+"/countSend.action?type=unicom";
	String unicomRetryUrl =  "http://"+unicomSocketUrl+":"+unicomSocketPort+"/retrySend.action?id=2";

%>

<div class="content">
	<div style="clear: both;"></div>
	<div style="width: 100%;">
		<table id="sendMessageList">
		</table>
		<div id="sendMessageListPager"></div>
	</div>
	<div id="receiveMessageDialog"></div>
	<div id="processChoseDialog"></div>
	<div id="noticeDialog"></div>
</div>
<script type="text/javascript">

//Formatter
function operatorFormatter(el,options,rowData){
	return "<a href='javascript:retry("+rowData.id+",0)'><span>重发(当天)</span></a>";
}

$(function(){
	
	var url =  "<%=cmccSendUrl%>";
	getSend(url);
	
	url = "<%=telecomSendUrl %>";
	getSend(url);
	
	url = "<%=unicomSendUrl %>";
	getSend(url);
	
	function toggleButtonState(){
	}
	function afterLoad(){
	}
	
	// 生成列表
	$("#sendMessageList").jqGridFunction({
		mtype:'post',
		datatype: "local",
	   	colModel:[
			{name:"id",index:'id',hidden:true},
			{name:"operator", index:'sendTheDay',label:'操作',formatter:operatorFormatter,width:65,frozen:true,sortable:false,align:'center' },
	    	{name:"gateway", index:'gateway',label:'网关',width:200,frozen:true,sortable:false,align:'center' },
	    	{name:'Total', index:'Total',label:'总发送数', width:130, sortable:true, align:'center', hidden:false}, 	
	    	{name:'YesterdaySend', index:'YesterdaySend',label:'昨天发送成功数', width:130, sortable:true, align:'center', hidden:false}, 	
			{name:'TotalSend', index:'TotalSend',label:'今天发送成功数', width:130, sortable:true, align:'center', hidden:false},
			{name:'WaitSend', index:'WaitSend',label:'等待数', width:130, sortable:true, align:'center', hidden:false},
			{name:'NoSend', index:'NoSend',label:'未发送数', width:130, sortable:true, align:'center', hidden:false},
			{name:'FailSend', index:'FailSend',label:'失败数', width:130, sortable:true, align:'center', hidden:false}
	   	]
	});
	jQuery("#sendMessageList").jqGrid('setFrozenColumns');
	
	
});

function retry(id,timelimit){
	var retryurl="";
	
	if(id==1){
		retryurl = "<%=cmccRetryUrl%>";
	}else if(id == 4){
		retryurl = "<%=telecomRetryUrl%>";
	}else if(id == 2){
		retryurl = "<%=unicomRetryUrl%>";
	}

	var url = retryurl+"&timelimit="+timelimit;
	
	$.ajax({
		type : "post",
		url : url,
		dataType : "jsonp",
		jsonp:'callback',  
		success : function(data) {
			if(data== true){
				alert("操作成功!");
				
			}else{
				alert("操作失败!");
			}
		}
		
	});
}

function getSend(url){
	$.ajax({
		type : "get",
		url : url,
		dataType : "jsonp",
		jsonp:'callback',  
		success : function(data) {
			$.each(data,function(entryIndex,entry){
				
				var id = entry['id'];
				var result={
					"id":id,
					"gateway":getName(id)+"(从"+entry['startDate']+"开始统计)",
					"Total":entry['Total'],
					"YesterdaySend":entry['YesterdaySend'],
					"TotalSend":entry['TotalSend'],
					"WaitSend":entry['WaitSend'],
					"NoSend":entry['NoSend'],
					"FailSend":entry['FailSend'],
					"sendTheDay":entry['id']
				};
				
				$("#sendMessageList").addRowData(result.id,result,"first");				
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
	var selectedIds = $("#sendMessageList").jqGrid("getGridParam", "selarrrow");
	for(var i=0;i<selectedIds.length;i++){
		selectedId = selectedIds[i];
	}
	return selectedId;
}
function getSelectedIds(){
	var selectedIds = $("#sendMessageList").jqGrid("getGridParam", "selarrrow");
	return selectedIds;
}


</script>