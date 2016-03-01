<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div id="dialog-form" class="container container_24">
	<input type="hidden" id="address"  value="<s:property value='#parameters.address'/>" />
	<input type="hidden" id="ip"  value="<s:property value='#parameters.ip'/>" />
	<input type="hidden" id="portNum"  value="<s:property value='#parameters.portNum'/>" />
	<input type="hidden" id="account"  value="<s:property value='#parameters.account'/>" />
	<input type="hidden" id="password"  value="<s:property value='#parameters.password'/>" />
	<input type="hidden" id="guid"  value="<s:property value='#parameters.guid'/>" />
	<div style="text-align: center;">
		<embed id="Embedded" type="application/ZTE_MOCPlayer_NPAPI_Plugin" width=760 height=480>
		<br>
	</div>
	
</div>



<script>
var arrayObj = new Array();
var objForRecord=new Array();
var guidCode;

var addresForReal=$("#address").val();
var ip=$("#ip").val();
var portNum=parseInt($("#portNum").val());
var account=$("#account").val();
var password=$("#password").val();
var guid=$("#guid").val();
var guidflag = false;//是否存在改guid
var embed1;

function login(){
	var lVal = embed1.LoginEx2(ip,portNum,account,password,0);
  	if(lVal==200){
		alert("登录成功！");
		embed1.GetDeviceListEx();
	}else if(lVal == 414){
		alert("连接服务器失败！");
	}else if(lVal == 500){
		alert("该监控用户已登陆！");
	}else if(lVal == 404){
		alert("该监控用户不存在！");
	}else if(lVal == 401){
		alert("监控密码错误！");
	}else{
		alert("登陆失败！");
	}
}

function DeviceListReturnEx(statusCode, CurIndex, TotalCount, sDeviceList){
       var xml = sDeviceList;
       var xmlDoc = $.parseXML(xml);
       var elements = xmlDoc.getElementsByTagName("Camera");		
       for (var i = 0; i < elements.length; i++) {
       var obj = new Object();

            obj.name = elements[i].getAttribute("guname");
            obj.guid = elements[i].getAttribute("guid");    
            arrayObj.push(obj);	

	   }	
       var str = "";
       guidflag = false;
       for(var i = 0; i < arrayObj.length; i++){
         if(guid==arrayObj[i].guid){
    		 guidCode=arrayObj[i].guid;
    		 str = "<li style='margin-top: 4px'><a style='font-size: 14px;letter-spacing: 3px;color:OrangeRed' href='javascript:void(0)' onclick='OpenGuStream("+ JSON.stringify(arrayObj[i].guid) +")'>"+arrayObj[i].name+"</a></li>" + str;
    		 guidflag = true;
    		 OpenGuStream(arrayObj[i].guid);
    	 }
    	}
       if(!guidflag){
    	   alert("监控通道编号有误：" + guid);
       }
      //$("#guname").html(str);
}

function OpenGuStream(guid){

    var lVar=0;
	lVal = embed1.OpenGUStreamEx2(guid, 0, 0);
}

function playRecordTq(taskId, startTime, endTime)
{
  
   embed1.OpenRecordTaskEx(taskId,guidCode,startTime,endTime);
   
 
   
}  

function QueryRecordReturn(nQueryType, sGUID, sQueryResult)
{
	   
	   var str = "";
	   var xmlForRecord = sQueryResult;
       var xmlRecordDoc = $.parseXML(xmlForRecord);
       
       var elements = xmlRecordDoc.getElementsByTagName("rec");	
     
       for (var i = 0; i < elements.length; i++) {
    	   
       var obj = new Object();

        obj.taskID =  elements[i].getElementsByTagName("TaskID")[0].firstChild.nodeValue;
        
       obj.startTime = elements[i].getElementsByTagName("BeginTime")[0].firstChild.nodeValue;
    	  
       obj.endTime =  elements[i].getElementsByTagName("EndTime")[0].firstChild.nodeValue;
 	  
       
 	  str = "<li style='margin-top: 4px'><a style='font-size: 14px; text-decoration: underline;letter-spacing: 3px;color:blue' href='javascript:void(0)' onclick='playRecordTq("+ JSON.stringify(obj.taskID) +","+JSON.stringify(obj.startTime)+","+JSON.stringify(obj.endTime)+")'>"+obj.startTime+"</a></li>" + str;

	}	
       
   	$("#searchList").html(str);
      
	
	
	
	
}
//录像文件播放到最后,结束播放通知
function RecordFileEnd(sTaskID)
{
  alert("录像文件播放到最后,结束播放!");
}

$(document).ready(function(){
	
	
	 
	 embed1 = document.getElementById('Embedded');
	login();
});
</script>