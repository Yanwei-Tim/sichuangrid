<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/includes/baseInclude.jsp"%>

<div  title="请选择部门" id="beforeMove">
		<div id="organizationTree" style="clear: both;"></div>
	</div>
<%--<form id="maintainShiftForm" method="post" action="${path}/baseinfo/${currentClassName}Manage/shiftPerson.action">--%>
<form id="maintainShiftForm" method="post" action="${path}/publicSecurity/transferManage/transfer.action">
	<input name="orgId" id="orgId" value="${organization.id}" type="hidden"/>
	<input id="orgPangtId" value="${organization.parentOrg.id}" type="hidden"/>
	<input name="toOrgId" id="shiftOrgId" value="" type="hidden" style="width:0px;border:0px;height:0px;overflow:hidden;position:absolute;top:25px;left:70px;"/>
	<input name="ids"  value="${ids}" type="hidden"/>
	<input name="type" type="hidden" id="type" value="${type}"/>
	<input name="modeType" type="hidden" id="modeType" value="${modeType}"/>
	<input type="hidden" id="bigType" value="${bigType}"/>
</form>
<div id="errorList"></div>

<script type="text/javascript" >
var orgPangtId = $("#orgPangtId").val();
var orgId = $("#orgId").val();
var orgSelector;
var downLoadList;
function closeDialog(){
	var selectOrgId=$.getSelectedNode(orgSelector).attributes.id;
	if (selectOrgId!=null){
		$("#shiftOrgId").val(selectOrgId);
	}
}
$(document).ready(function(){
	$("#type").val($("#lowerCaseModuleName").val());
	orgSelector=$("#organizationTree").initTree({rootId:orgPangtId,
		loadCom:function(){
			$.disableNode(orgSelector,orgId);
			$.disableNode(orgSelector,orgPangtId);
	}});
	$.addClick(orgSelector,closeDialog);
	jQuery.validator.addMethod("isGridOrganization", function(value, element){
		if(value==null||value==undefined||value==""){return true}
		$.ajax({
			async:false,
			url:"${path}/sysadmin/orgManage/isGridOrganization.action",
			data:{
				"organization.id":$("#shiftOrgId").val()
			},
			success:function(responseData){
				bol = responseData;
			}
		});
		return bol;
	});

	jQuery.validator.addMethod("isModernOrg", function(value, element){
		if($("#orgId").val()==$("#shiftOrgId").val()){
			return false;
		}
		return true;
	});

	//test.dialog("option","buttons",{"关闭":close});
	$("#maintainShiftForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form) {
			$(form).ajaxSubmit({
				success: function(data){
					if(null==data||data.length<1){
						$.messageBox({message:"数据转移成功!"});
						$("#moveDataDialog").dialog("close");
					}else if(null!=data&&data.length>0){
						$("#beforeMove").hide();
						$("#moveDataDialog").dialog("option","buttons",{"关闭":function(){$(this).dialog("close"); }}).dialog('open');
						//downLoadList=data;
						var code="<p>数据转移出错，目标网格：<font color='red'>"+data[0]['orgName']+"</font>，详细信息：</p>";
						code+="<table><tr><th style='text-align:center;width:60px;'>编号</th>";
						code+="<th style='text-align:center;'>错误信息</th></tr>";
						for(var i=0;i<data.length;i++){
							code=code+"<tr><td style='text-align:center;'>"+data[i]['name']+"</td><td>"+data[i]['message']+"</td></tr>";
						}
						code+="</table>";
						$("#errorList").append(code);
						//$("#errorList").append("	请下载无法转移的数据信息       <a onclick='downLoad()' href='javascript:;'>下载数据信息</a>");
					}else {
						$.messageBox({message:data.errorMessage});
						$("#moveDataDialog").dialog("close");
					}
					onOrgChanged(getCurrentOrgId(),isGrid());
				},
				error: function(data){
					$.messageBox({
						message:data,
						level: "error"
					});
				}
			});
		},
		rules:{"toOrgId":{isGridOrganization:true,isModernOrg:true}},
		messages:{"toOrgId":{isGridOrganization:"数据只能转移到网格",isModernOrg:"数据不能转移到当前网格"}}
	});
});

function downLoad(){
	$.ajax({
		type:'post',
		//url:'${path}/transferManage/downLoad.action?ids='+$("#ids").val()+'&type='+$("#type").val()+'&toOrgId='+orgId,
		//url:'${path}/transferManage/downLoad.action?type='+$("#type").val()+'&toOrgId='+orgId,
		url:'${path}/transferManage/downLoad.action',
		data:{list:downLoadList},
		success:function(data){
		 
		}
	});
}
</script>