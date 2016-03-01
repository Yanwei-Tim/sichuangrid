<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div class="container container_24">
	<form id="maintainForm" method="post" action="/smsResponseManage/addSmsResponse.action">
	<pop:token/>
	<input type="hidden" id="sendToPhoneNumber" name="sendToPhoneNumber" value="${sendToPhoneNumber}"/>
	<input type="hidden" name="isSendByPhoneNumber" value="${isSendByPhoneNumber}"/>
	<c:if test="${!isSendByPhoneNumber}">
	<div class="grid_9 lable-right">
		<label>短信接收者(红袖套队伍)：</label>
	</div>
	<div class="grid_13">
		<pop:PropertyDictMultiCheckbox  name="smsSendGroup.receiverRedCuffTeamType" column="5" domainName="@com.tianque.domain.property.PropertyTypes@RED_CUFF_TEAM_TYPE"/>
	</div>
	<div class='clearLine'>&nbsp;</div>
	</c:if>
	<c:if test="${'byRedCuffTeamType'==sendType}">
	<div class="grid_9 lable-right">
		<label>红袖套人员选择：</label>
	</div>
	<div class="grid_15">
		<select  class='form-txt' id="redCuffTeamSelect" style="width: 200px" >
			<s:iterator value="redCuffTeamList">
					<option value="${phoneNumber}" >${memeberName}</option>
			</s:iterator>
		</select>
	</div>
	<div class='clearLine'>&nbsp;</div>
	</c:if>
	<div class="grid_9 lable-right">
		<label>短信账号：</label>
	</div>
	<div class="grid_15">
		<select  class='form-txt' id="senderAccountId" name="smsSendGroup.senderAccountId" style="width: 200px" >
			<option value="">请选择短信账号</option>
			<s:iterator value="smsAccountList">
					<option value="${id }" data-content-suffix="${contentSuffix}">${name}</option>
			</s:iterator>
		</select> 账户余额 <span id="smsAccountBalance" style="color:red">0</span> 条
	</div>
	<div class='clearLine'>&nbsp;</div>
	<div class="grid_9 lable-right">
		<label></label>
	</div>
	<div class="grid_14">
		需要使用 <span id="needSmsAccountBalance" style="color:red" data-countPhone="0">0</span> 条，剩余 <span id="afterSmsAccountBalance" style="color:red">0</span> 条
	</div>
	<div class='clearLine'>&nbsp;</div>
	<div class="grid_9 lable-right">
		<label>短信内容：</label>
	</div>
	<div class="grid_13" style="height: 250px">
		<textarea name="smsSendGroup.smsContent"  id="smsContent" onkeyup="charlength(value)" style='height:250px;width: 200px;' maxlength="500" class='form-txt {required:true,maxlength:500,messages:{required:"请输入短信内容",maxlength:$.format("短信内容最多可以输入{0}个字")}}' ></textarea>
	</div>
	<div class='clearLine'>&nbsp;</div>
	<div class="grid_9 lable-right">
		<label>短信标签：</label>
	</div>
	<div class="grid_13">
		<span id="smsSuffix"></span>
	</div>
	<div class='clearLine'>&nbsp;</div>
	<div class="grid_9 lable-right">
		<label class="form-lbl">已输入：</label>
	</div>
	<div class="grid_14 heightAuto">
		<span id="counter" style="line-height: 30px; padding-left: 5px;font-style:normal;">-</span>个字(含短信标签)（注意：内容不要超过500字。)
	</div>
  </form>
</div>
<script type="text/javascript">
function countNeedSms(){
	var contentNeed = 1;
	var content = $("#smsContent").val();
	if(content){
		contentNeed = content.length%70==0?content.length%70:(parseInt(content.length/70)+1);
	}
	var countPhone = $("#needSmsAccountBalance").data("countPhone");
	if(!countPhone)countPhone=0;
	var sendToPhoneNumber = $("#sendToPhoneNumber").val();
	if(sendToPhoneNumber)countPhone+=sendToPhoneNumber.split(",").length;
	$("#needSmsAccountBalance").html(countPhone*contentNeed);
	afterSmsAccountBalance();
}
function afterSmsAccountBalance(){
	$("#afterSmsAccountBalance").html('-');
	var smsAccountBalance = $("#smsAccountBalance").text();
	var needSmsAccountBalance = $("#needSmsAccountBalance").text();
	if(!isNaN(smsAccountBalance) && !isNaN(needSmsAccountBalance)){
		$("#afterSmsAccountBalance").html(smsAccountBalance - needSmsAccountBalance);
	}
}
//计算文字长度
function charlength(value){
	var a=value.length + $("#smsSuffix").text().length;
	document.getElementById("counter").innerText=a;
	countNeedSms();
 }
$(document).ready(function() {
	var $redCuffTeamSelect = $("#redCuffTeamSelect");
	if($redCuffTeamSelect) {
		$redCuffTeamSelect.uiMultiselect({
			selectedList: 5,
			click: function (event, ui) {
				prepareValues(ui.checked, ui.value);
			},
			checkAllText: '',
			header: false,
			uncheckAllText: ''
		});
		$redCuffTeamSelect.multiselect("uncheckAll");

		var checkedValues = new Array();

		function prepareValues(ifCheck, cheValue) {
			if (ifCheck == 'true' || ifCheck == true) {
				if (cheValue != "") {
					checkedValues.push(cheValue);
				}
			}
			if (ifCheck == 'false' || ifCheck == false) {
				for (var i in checkedValues) {
					if (checkedValues[i] == cheValue) {
						checkedValues.splice(i, 1);
					}
				}
			}
			$("#sendToPhoneNumber").val(checkedValues);
			countNeedSms();
		}
	}
	var sendToPhoneNumber = $("#sendToPhoneNumber").val();
	if(sendToPhoneNumber){
		$("#needSmsAccountBalance").data("countPhone",sendToPhoneNumber.split(",").length);
		countNeedSms();
	}
	
	$("#senderAccountId").change(function(){
		$("smsAccountBalance").html(0);
		var id = $("#senderAccountId").val();
		var contentSuffix = $("#senderAccountId option:selected").data("contentSuffix");
		$("#smsSuffix").text(contentSuffix);
		if(!id)return false;
		$.get(PATH+"/smsResponseManage/querySmsAccountBalance.action",{"id":id},function(result){
			if(result.errorCode=='0'){
				$("#smsAccountBalance").html(result.data);
			}else{
				$("#smsAccountBalance").html('error');
				$.messageBox({level: "warn",message : result.message});
			}
			afterSmsAccountBalance();
		});
	});
	$("input[name='smsSendGroup.receiverRedCuffTeamType']").change(function(){
		var type = '';
		$("input[name='smsSendGroup.receiverRedCuffTeamType']:checked").each(function(){
			if(type!='')type+=",";
			type+=$(this).val();
		});
		if(!type){
			$("#needSmsAccountBalance").data("countPhone",0);
			countNeedSms();
			afterSmsAccountBalance();
			return false;
		}
		$.get(PATH+"/smsResponseManage/countRedCuffTeamListByTeamType.action",{"smsSendGroup.receiverRedCuffTeamType":type},function(result){
			if(result.errorCode=='0'){
				$("#needSmsAccountBalance").data("countPhone",result.data);
				countNeedSms();
			}else{
				$("#needSmsAccountBalance").html('error');
				$.messageBox({level: "warn",message : result.message});
			}
			afterSmsAccountBalance();
		});
	});
	//提交
	$("#maintainForm").formValidate({
		promptPosition : "bottomLeft",
		submitHandler : function(form) {
			<c:if test="${!isSendByPhoneNumber}">
			var sendType = $("checkbox[name='smsSendGroup.receiverRedCuffTeamType']:checked");
			if(!sendType||sendType.size<1){
				$.messageBox({level: "warn",message : "请选择接收消息的红袖套队伍类别"});
				return false;
			}
			</c:if>
			if(!($("#senderAccountId").val())||!$("#senderAccountId").val()){
				$.messageBox({level: "warn",message : "请选择要群发使用的短信账号"});
				return false;
			}
			if($("#afterSmsAccountBalance").text()<0){
				$.messageBox({level: "warn",message : "短信账户余额不足"});
				return false;
			}
			var contentSuffix = $("#smsSuffix").text();
			var reg=new RegExp(contentSuffix+"$");
			var smsContent = $("#smsContent").val();
			if(!reg.test($("#smsContent").val())){
				$("#smsContent").val(smsContent+contentSuffix);
			}
			$(form).ajaxSubmit({

				success : function(data) {
					if (data ==true||data=="true") {
						$("#smsResponseMaintanceDialog").dialog("close");
						$.messageBox({message : "群发成功!"});
						$("#smsResponseList").trigger("reloadGrid");
					} else {
						$.messageBox({
							message : data,
							level : "error"
						});
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert("提交错误");
				}
			});
		}
	});
});
</script>