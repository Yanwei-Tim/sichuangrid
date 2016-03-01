<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div id="dialog-form" title="数据导出" class="container container_24">

<style type="text/css">
.loadmask {z-index: 9999;position: absolute;top:0;left:0;-moz-opacity: 0.5;opacity: .50;filter: alpha(opacity=50);background-color: #CCC;width: 100%;height: 100%;zoom: 1;}
.loadmask-msg {z-index: 20001;position: absolute;top: 0;left: 0;background:#fff;}
.masked {overflow: hidden !important;}
.masked-relative {position: relative !important;}
.masked-hidden {visibility: hidden !important;}
#buttons{width:300px;height:100px;background:#fff;}
#load {position:relative;}
#load .ui-button{width:80px;height:25px;}
#load .ui-dialog-buttonset{position:absolute;bottom:20px;right:15px;}
</style>
<form action="" method="post" id="exceldownload">
	<input id="baseDownloadUrl" type="hidden" value=<s:property value='#parameters.downloadUrl'/> />
	<div id="download-div" class="container container_24">
		<div class="grid_20 lable-left"  >
			<label class="form-lbl">请选择要导出的数据:</label>
		</div>
   		<div class='clearLine'>&nbsp;</div>
		<div class="grid_2 lable-left"  >&nbsp;
		</div>
		<div class="grid_20 lable-left"  >
			<input type="radio" name="pageOnly" value="false" checked="checked"/>导出全部数据
		</div>
   		<div class='clearLine'>&nbsp;</div>
		<div class="grid_2 lable-left"  >&nbsp;
		</div>
		<div class="grid_20 lable-left"  >
			<input type="radio" name="pageOnly" value="true"/>导出本页数据
		</div>
	</div>
</form>
<div id="message"></div>
</div>
<script>
var intarcalId;
var ticketId='';
var threadId;
var errorMessageExcelName;
var uploadFileName;
var actionUrl;
var actionUrlTemp;
var urlTemp = "";
$(document).ready(function(){
	actionUrl =$("#baseDownloadUrl").val();
	actionUrlTemp = actionUrl.substring(0,actionUrl.length-7)+"All.action";
	var params= "";
	if("" != "<s:property value='#parameters.gridName'/>"){
		params=$("#<s:property value='#parameters.gridName'/>").getPostData();
		urlTemp = "?"+$.param(params);
	}
});

function exceldownloadSubmitForm(){
	var pageOnly= $('input:radio[name="pageOnly"]:checked').val();
	if(pageOnly==true||pageOnly=="true"){
		$("#exceldownload").attr("action",actionUrl+urlTemp);
		if ($("#exceldownload").attr("action")==""){
			 return false;
		};
		$("#exceldownload").submit();
		return true;
	}else{
		$("#exceldownload").attr("action",actionUrlTemp+urlTemp);
		ajaxSubmitForm();
	}
}

function ajaxSubmitForm(){
	$("#exceldownload").ajaxSubmit({
		async:false,
		success: function(data){
			var dataJson=eval("("+data+")");
			errorMessageExcelName = dataJson.errorMessageExcelName;
			uploadFileName = dataJson.uploadFileName;
			$("#<s:property value='#parameters.dialog'/>").dialog("close");
			var str = "<div id='load' class='ui-widget-border'><div style='text-align:left;margin-left:30px;margin-top:20px;margin-bottom:-70px;margin-right:30px;' id='templateVersionLabel'></div><p class='filename'></p><p id='progressbar-msg'>文件正在下载...</p><div id='progressbar'></div><span id='progressbar-value'>0%</span><div class='clear'>&nbsp;</div>";
			
		    str+="<div class='promptMsgDiv'><p></p></div> "
			str+="<div id='errorMsgDiv' style='margin-top:25px;margin-left:20px;'><table id='importErrorMsg'></table></div>";
			
			str += "<div class='clear'></div>";
			str += "<div class='ui-dialog-buttonset' id='errorMsgButton'><button id='cancel' type='button' onclick='showErrorBtn()' class='ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only'>关闭</button></div>";
			str +="</div>";
			$("body").mask(str);
			$("#importErrorMsg").jqGridFunction({
                datatype: "local",
                autowidth:false,
                width:420,
                height:140,
                shrinkToFit:true,
                colModel:[
                    {name:'errorMsg',label:'错误信息',width:'350',sortable:false}
                ]
            });
			
			$("#promptMsgDiv").hide();
			$("#errorMsgDiv").hide();
			$("#errorMsgButton").hide();
			$("#progressbar").progressbar();
			ticketId = dataJson.ticketId;
			threadId = dataJson.threadId;
			intarcalId = setInterval(getTicketByIds,2000);
		}
	});
	return false;
}
function getTicketByIds(){
	 $.ajax({
		url: '${path}/ticket/getDataExportTicketByTicketId.action',
		type: 'post',
		dataType:'json',
		data:{
			"ticketId":ticketId,
			"currentErrorRow": function(){return $("#importErrorMsg").getGridParam("records");}
		},
  	 	success: function(data){
	   	 	if(data == null){
               clearInterval(intarcalId);
	   	 	}
			totalRowCount=data.totalRowCount;
			currentRowCount=data.currentRowCount;
			errorCount = data.errorCount;
			if(totalRowCount==currentRowCount&&totalRowCount!=0){
				var dataType = '<s:property value="#parameters.dataType"/>';
				var st="<div id='importInfo' class='importInfo' style='margin-left:5px'><p style='text-align:center'>(请点击 <a href='${path}/dataTransfer/downloadDataZipTemplate.action?dataTemplatesName="+errorMessageExcelName+"&templates="+uploadFileName+"&dataType="+dataType+"'><font color='red'> 下载  </font>)</a>"+"</p></div>";
				$("div.promptMsgDiv p").html(st);
				$("#errorMsgButton").show();
				$("#reloadExcel").hide();
				$('#cancel').unbind('click');
			    $('#cancel').bind('click', function () {
			    	clearInterval(intarcalId);
			    	$('body').unmask();
			    	threadStop();
			    	<s:if test="!''.equals(#attr.listName)">
					$("#<s:property value='#parameters.listName'/>").trigger("reloadGrid");
					</s:if>
			    });
			    clearInterval(intarcalId);
			    threadStop();
			}
			$("#progressbar").progressbar("option", "value", currentRowCount/totalRowCount*100);
			if(currentRowCount==0){
				$("#progressbar-value").text("0%");
			}else{
				$("#progressbar-value").text(parseInt(currentRowCount/totalRowCount*100)+"%");
			}
			var jsonMsg = eval("("+data.description+")");
			var dataJson = data.errorMsgs;
			var state = data.state;
			$("#progressbar-msg").text(jsonMsg.msg);
			if(dataJson && dataJson.length>0){
				showErrorDiv();
				$("#importInfo").hide();
				$("#progressbar-value").text("0%");
				$("#progressbar").progressbar("option", "value", 0);
	            for(var i=0;i<dataJson.length;i++){
		            var str = {errorMsg:dataJson[i]};
		            $("#importErrorMsg").addRowData($("#importErrorMsg").getGridParam("records")+i,str,"last");
	            }
	            if(state == '<s:property value="@com.tianque.state.TicketState@DOING"/>'){
	            	intarcalId = setInterval(getTicketByIds,1000);
	            }
	        }
			
			if(state=='<s:property value="@com.tianque.state.TicketState@TIMEOUT"/>' || state=='<s:property value="@com.tianque.state.TicketState@EXCEPTIONSTOP"/>'){
				clearInterval(intarcalId);
			}
	 	}
 	}); 
}
function showErrorDiv(){
	clearInterval(intarcalId);
    $("#load").attr("style","height:400px;");
    $("#errorMsgDiv").show();
}
function showErrorBtn(){
	$("#errorMsgButton").show();
	clearInterval(intarcalId);
	$('body').unmask(); 
	threadStop();
}

function threadStop(){
	$.ajax({
		url: '${path}/dataTransfer/threadStop.action',
		data:{"threadId":threadId},
  	 	success: function(data){
	 	}
 	});
}

</script>