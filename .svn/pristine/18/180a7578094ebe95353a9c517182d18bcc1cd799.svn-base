<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp"%>

<div  title="请选择部门" id="beforeMove">
		<div id="organizationTree" style="clear: both;"></div>
	</div>
<%--<form id="maintainShiftForm" method="post" action="${path}/baseinfo/${currentClassName}Manage/shiftPerson.action">--%>
<form id="maintainShiftForm" method="post" action="${path}/baseinfo/companyPlaceTransfer/transfer.action">
	<pop:token />
	<input name="orgId" id="orgId" value="${organization.id}" type="hidden"/>
	<input id="orgPangtId" value="${organization.parentOrg.id}" type="hidden"/>
	<input name="toOrgId" id="shiftOrgId" value="" type="hidden" style="display:none;width: 0px;" class='form-txt {validateToOrgId:true,messages:{validateToOrgId:"请重新选择目标网格"}}'/>
	<input name="ids"  value="${ids}" type="hidden"/>
	<input name="type" type="hidden" id="type" value="${type}"/>
	<input name="modeType" type="hidden" id="modeType" value="${modeType}"/>
	<input type="hidden" id="bigType" value="${bigType}"/>
	<input type="hidden" id="modulKey" value="${modulKey}"/>
	<input type="hidden" id="orgIds" value="${orgIds}"/>
</form>
<div id="errorList"></div>

<script type="text/javascript" >
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
	orgSelector=$("#organizationTree").initAdministrativeTree({rootId:USER_ORG_ID,
		loadCom:function(){
			$.disableNode(orgSelector,orgId);
			$.disableNode(orgSelector,USER_ORG_ID);
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
				if(bol == false || bol =='false'){
					$.messageBox({message:"数据只能转移到网格",level:"warn"});
				}
			}
		});
		return bol;
	});

	jQuery.validator.addMethod("isModernOrg", function(value, element){
		if($("#orgId").val()==$("#shiftOrgId").val()){
			$.messageBox({message:"数据不能转移到当前网格",level:"warn"});
			return false;
		}
		return true;
	});
	
	
	jQuery.validator.addMethod("validateToOrgId", validateToOrgId);
	
	function validateToOrgId(value,element){
		var orgIds = $("#orgIds").val();
		var toOrgId = $("#shiftOrgId").val();
		if(orgIds!=null){
			var orgIdss = orgIds.split(",");
			for(var i=0;i<orgIdss.length;i++){
				if(toOrgId == orgIdss[i]){
					$.messageBox({level:'warn',message:"所选数据中，有数据已经存在该网格，请重新选择"});
					return false;
				}
			}
		}
		return true;
	}
	
	

	//test.dialog("option","buttons",{"关闭":close});
	$("#maintainShiftForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form) {
			$(form).ajaxSubmit({
				success: function(data){
					if(data == "请不要重复提交！"){
						$.messageBox({level:"error",message:data});
						return;
					}
					if(null==data||data.length<1){
						$.messageBox({message:"数据转移成功!"});
						$("#moveDataDialog").dialog("close");
					}else if(null!=data&&data.length>0){
						$("#beforeMove").hide();
						$("#moveDataDialog").dialog("option","buttons",{"关闭":function(){$(this).dialog("close"); }}).dialog('open');
						//downLoadList=data;
						var code="<p>数据转移出错，目标网格：<font color='red'>"+data[0]['orgName']+"</font>，详细信息：</p>";
						code+="<table><tr><th style='text-align:center;width:160px;'>名称</th>";
						code+="<th style='text-align:center;'>错误信息</th></tr>";
						for(var i=0;i<data.length;i++){
							code=code+"<tr><td style='text-align:left;'>"+data[i]['name']+"</td><td>"+data[i]['message']+"</td></tr>";
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