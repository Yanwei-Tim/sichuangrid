<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>

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

<form id="mForm" method="post" enctype="multipart/form-data" action="${path}/system/gis2DLayerManage/importGis2DLayer.action">
	<pop:token />
	<div id="upload-div" class="container container_24">
		<div class="grid_24 heightAuto">
		    <label class="form-lbl heightAuto" id="templateVersion">
		            <font color="red"><s:property value="@com.tianque.core.util.BaseInfoTables@getTypeDisplayNames(#parameters.templates)"/></font>&nbsp;导入功能支持的数据模板为（.xml,.gml）文件
            </label>
		</div>
		<div id="importBox">
			<div style="clear: both;">&nbsp;</div>
			<div class="grid_6 lable-right"  >
				<label class="form-lbl">选择文件：</label>
			</div>
			<div class="grid_18" style="">
				<input id="fileToUpload" type="file" value="" name="upload">
			</div>
			<div style="clear: both;">&nbsp;</div>
			<div class="grid_12 lable-right"  >
				<label class="form-lbl">上传文件的坐标数据类型：</label>
			</div>
			<div class="grid_12">
				<select name="gisType" style="width:75px;">
					<option value="2D" selected>2D</option>
					<option value="3D">3D</option>
				</select>
			</div>
			<div id="message"></div>
		</div>
	</div>
</form>
<script>
var intarcalId = null;
$(document).ready(function(){
	$("#mForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form){
			$(form).ajaxSubmit({
				type:"json",
				success: function(data){
					/* if(data.indexOf("true")>=0){
						$.messageBox({level: "info",message: "导入成功！"});
						$("#mForm").parent().dialog("close");
					}else{
						$.messageBox({level: "error",message: "导入失败！"});
					} */
					if(!$(data).text()){
						$.messageBox({level: "error",message: data});
						return;
					}
					var isclosed = false;
					intarcalId = setInterval(function(){
						if(!isclosed){
							$("#mForm").parent().dialog("close");
							isclosed = true;
						}
						getTicketById($(data).text());
					},2000);
				}
			});
			return false;
		},
		rules:{
			"upload":{
				isXML: true
			}
		},
		messages:{
			"upload":{
				isXML: "只能选择后缀为.xml格式的文件"
			}
		}
	});

	//excel
	jQuery.validator.addMethod("isXML", function(fileName, element){
		var suffix = fileName.substring(fileName.lastIndexOf(".")+1).toLowerCase();
		if(suffix!="xml" && suffix!="gml" ){
			return false;
		}
		return true ;
	});
});

function getTicketById(id){
	 $.ajax({
		url: '${path}/baseinfo/excelImportLogManage/getExcelimportlogById.action',
		type: 'post',
		data:{"id":id},
  	 	success: function(data){
	   	 	if(!data || data.importDataNum == data.currentDealNum){
               clearInterval(intarcalId);
	   	 	}
			totalRowCount=data.importDataNum;
			currentRowCount=data.currentDealNum;
			errorCount = data.errorCountNum;
			var str = "<div id='load' class='ui-widget-border'><div style='text-align:left;margin-left:30px;margin-top:20px;margin-bottom:-70px;margin-right:30px;' id='templateVersionLabel'>"+$("#templateVersion").html()+"</div><p class='filename'>"+data.fileName+"</p><p id='progressbar-msg'>文件正在上传...</p><p id='currentText'>您选择导入<b id='totalCount' style='color:red;'>"+totalRowCount+"</b>条数据，正在处理第<b id='currentNumber' style='color:red;'>"+currentRowCount+"</b>条数据</p><div id='progressbar'></div><span id='progressbar-value'>0%</span><div class='clear'>&nbsp;</div>";
		    str+="<div class='promptMsgDiv'><p></p></div> "
			str+="<div id='errorMsgDiv' style='margin-top:25px;margin-left:20px;'><table id='importErrorMsg'></table></div>";
			str += "<div class='clear'></div>";
			str += "<div class='ui-dialog-buttonset'><div id='errorMsgButton' style='float:left'></div><div style='float:left' id='closeImportDiv'><button id='closeImportDialog' type='button' class='ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only'>关闭</button></div>";
			str +="</div>";
			$("body").mask(str);
			$('#closeImportDialog').bind('click', function () {
				$('body').unmask();
				clearInterval(intarcalId);
			});
			if($("#progressbar").attr("role")!="progressbar"){
				$("#progressbar").progressbar();
			}
			$("#progressbar").progressbar("option", "value", currentRowCount/totalRowCount*100);
			$("#progressbar-value").text(parseInt(currentRowCount/totalRowCount*100)+"%");
			if(totalRowCount == currentRowCount){
				clearInterval(intarcalId);
			}
	 	}
 	}); 
}

</script>