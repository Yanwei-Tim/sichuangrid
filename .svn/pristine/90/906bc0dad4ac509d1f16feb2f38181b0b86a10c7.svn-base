<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<style type="text/css">
#smsClear{color:red;}
#smsClear:hover{color:blue;}
</style>
<div id="dialog-form" title="短信收件箱" class="container container_24">
		<form id="maintainForm" method="post" action="javascript:void(0);">
			<input type="hidden" name="map.sendType" id="sendObjectType" value="1"/>
			<input type="hidden" name="map.smsSendId" id="selectAddresseeHide" value="1"/>
		 	<div class="grid_4 lable-right">
				<em class="form-req">*</em>
				<label class="form-lbl">收信人：</label>
		 	</div>
		 	<div class="grid_16" style="height:60px;" >
				<textarea class="form-txt" name="selectAddressee" id="selectAddressee" readonly ></textarea>
			</div>
			<div class="grid_4" style="height:60px;" >
				<label id="sendNumberCounts" class="form-req">发送人数(0)</label>
				<div class='clearLine'>&nbsp;</div>
				<label class="form-lbl" ><a href="javascript:;" id="smsClear" >&nbsp;清空</a></label>
			</div>
			<div class='clearLine'>&nbsp;</div>
			<div class="grid_4 lable-right">
				<em class="form-req">*</em>
				<label class="form-lbl">优先级：</label>
	 		</div>
	 		<div class="grid_16">
	 			<select name="map.smsLevel" class="form-select">
					<option value="2">普通</option>
					<option value="1">紧急</option>
				</select>
	 		</div>
	 		<div class='clearLine'>&nbsp;</div>
	 		
			<div id="showSearch" style="display:none;">
		 		<div id="loadSearch"></div>
			</div>
			<div class='clearLine'>&nbsp;</div>
			<div class="grid_4 lable-right">
				<label class="form-lbl">已输入字符：</label>
		 	</div>
		 	<div class="grid_4">
		 		<input type="text" id="numberCounts" readonly class="form-txt" style="text-align: center;color: #ff6600;" >
		 	</div>
		 	<div class="grid_14">
		 		<label>&nbsp;&nbsp;(每条短信70字符，超过70字符将拆分成多条发送。)</label>
		 	</div>
			<div class='clearLine'>&nbsp;</div>
		 	<div class="grid_4 lable-right">
				<em class="form-req">*</em>
				<label class="form-lbl">发送内容：</label>
		 	</div>
			<div class="grid_20" style="height:20px;" >
				<textarea rows="6" class="form-txt" id="smsContent" name="map.smsSendContent" ></textarea>
			</div>
		</form>
		
</div>
<script type="text/javascript">

$(document).ready(function(){
	
	$("#smsContent").keyup(function(){
		var counts = $(this).val().length;
		$("#numberCounts").val(counts+"/"+(parseInt(counts/70)+1));
	});
	
	$("#smsClear").click(function(){
		$("#selectAddressee").val("");
		$("#sendNumberCounts").text("发送人数(0)");
		$("#selectAddresseeHide").val("");
		$("#sendObjectType").val("");
	});
	
	var jsonData={};
	$("#maintainForm").formValidate({
		submitHandler: function(form) {
			if($("#searchSubmit").val()){
				$.messageBox({
					message:$("#smsSendObject").find("option:selected").text()+"查询条件不存在，请联系管理员！",
					level: "error"
				});
				return ;
			}
			var data=$(form).serializeArray();
			for(i=0;i<data.length;i++){
				jsonData[data[i].name]=data[i].value;
			}
			$.ajax({
				async:false,
				url:'${path}/smsUplinkManage/validateSmsNumber.action',
				data:jsonData,
				success:function(data){
					if(true==data || 'true'==data){
						formSbmit(form);
						return ;
					}else if(false==data || 'false'==data){
						$.confirm({
							title:"确认发送",
							message:"短信数超过每天的发送总数，超额部分明天发送",
							okFunc: function() {
								formSbmit(jsonData)
							}
						});
						return ;
					}
				}
			});
		},
		rules:{
			"map.smsSendContent":{
				required:true,
				minlength:2,
				maxlength:200
			},
			"selectAddressee":{
				required:true
			},
			"map.smsLevel":{
				required:true
			}
		},
		messages:{
			"map.smsSendContent":{
				required:"发送内容不能为空！",
					minlength:"发送内容不能小于2个字符！",
					maxlength:"发送内容不能大于200个字符！"
			},
			"selectAddressee":{
				required:"收信人不能为空！"
			},
			"map.smsLevel":{
				required:"请选择优先级！"
			}
		},
		ignore:':hidden'
	});
	
	function formSbmit(form){
		$.ajax({
			async:false,
			url:'${path}/smsUplinkManage/addSendSMSJobSql.action',
			data:jsonData,
			success:function(data){
				if(true == data || "true" == data){
					jsonData = null;
					$.messageBox({message:"短信新增成功!"});
					$("#smsUplinkList").trigger("reloadGrid");
					$("#smsUplinkDialog").dialog("close");
					if($("#${dialogName}")!=null){
						$("#${dialogName}").dialog("close");
					}
				}else{
					$.messageBox({
						message:"短信新增失败，请联系管理员！",
						level: "error"
					});
				}
			}
		});
	}
	
	$("#selectAddressee").click(function(){
		$("#addresseeDialog").createDialog({
			width: 800,
			height: 500,
			title:'选择收信人',
			url:'${path}/interaction/newSMS/smsUplink/selectAddresseeDlg.jsp',
			buttons: {
				"确定" : function(event){
			    	var type = $("#selectType").val();
			    	if(type == "1"){
			    		saveAddressee(this);
			    	}else if(type == "2"){
			    		saveContacts(this);
			    	}else if(type == "3"){
			    		saveSendObject(this);
			    	}
			    },
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
	});
	
	function saveSendObject(obj){
		var ids=$("#selectSendObjectList").getDataIDs();
		if(!ids){
			return ;
		}
		var number = $("#selectSendObjectList").getGridParam("records");
		var value = "";
		for(var i=0;i<ids.length;i++){
			var rowData = $("#selectSendObjectList").getRowData(ids[i]);
			if(i==2){
				value += ",\n"+rowData.name+"("+rowData.mobile+")";
				continue ;
			}else if(i==3){
				value += ","+rowData.name+"("+rowData.mobile+")。。。。。。";
				break ;
			}
			value += ","+rowData.name+"("+rowData.mobile+")";
		}
		if(value){
			value = value.substring(1,value.length);
		}
		var data=$("#selectSendObjectForm").serializeArray();
		for(i=0;i<data.length;i++){
			jsonData[data[i].name]=data[i].value;
		}
		$("#selectAddressee").val(value);
		$("#selectAddresseeHide").val($("#selectSendObjectId").val());
		$("#sendNumberCounts").text("发送人数("+number+")");
		$("#sendObjectType").val(3);
		$(obj).dialog("close");
	}
	
	function saveContacts(obj){
		var ids=$("#selectContactsList").jqGrid("getGridParam", "selarrrow");
		if(!ids){
			return ;
		}
		var jsonData={};
		var tdData = $("#contactsTableDiv").find(".td1");
// 		for(var i=0;i<ids.length;i++){
// 			var rowData = $("#selectContactsList").getRowData(ids[i]);
// 			jsonData[rowData.name+"("+rowData.mobileNumber+")"]=rowData.name+"("+rowData.mobileNumber+")";
// 		}
		jsonData = addJsonDataByTdData(tdData,jsonData);
		var value = jsonValueToString(jsonData);
		if(value){
			$("#sendNumberCounts").text("发送人数("+value.split(",").length+")");
		}else{
			$("#sendNumberCounts").text("发送人数(0)");
		}
		$("#selectAddressee").val(value);
		$("#selectAddresseeHide").val(value);
		$("#sendObjectType").val(2);
		$(obj).dialog("close");
	}
	
	function saveAddressee(obj){
		var inputData = $("#inputDiv").find(".inputMobile");
		var tdData = $("#tableDiv").find(".td1");
		var value = "";
		var jsonData={};
		jsonData = addJsonDataByInputData(inputData,jsonData);
		jsonData = addJsonDataByTdData(tdData,jsonData);
		var value = jsonValueToString(jsonData);
		if(value){
			$("#sendNumberCounts").text("发送人数("+value.split(",").length+")");
		}else{
			$("#sendNumberCounts").text("发送人数(0)");
		}
		$("#selectAddressee").val(value);
		$("#selectAddresseeHide").val(value);
		$("#sendObjectType").val(1);
		$(obj).dialog("close");
	}
	
	function addJsonDataByInputData(objData,jsonData){
		$.each(objData,function(a,b){
			var mobile = $(b).val();
			if(!mobile || !$.trim(mobile)){
				return ;
			}
			jsonData[mobile]=mobile;
		});
		return jsonData;
	}
	
	function addJsonDataByTdData(objData,jsonData){
		$.each(objData,function(a,b){
			var mobile = $(b).text();
			if(!mobile || !$.trim(mobile)){
				return ;
			}
			jsonData[mobile]=mobile;
		});
		return jsonData;
	}
	
	function jsonValueToString(data){
		var str = "";
		$.each(data,function(a,b){
			str += ","+a;
		});
		return str.substring(1);
	}

});

</script>


