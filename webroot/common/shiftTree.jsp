<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp"%>

<div  title="请选择部门" id="beforeMove">
		<div id="organizationTree" style="clear: both;"></div>
	</div>
<%--<form id="maintainShiftForm" method="post" action="${path}/baseinfo/${currentClassName}Manage/shiftPerson.action"> --%>
<form id="maintainShiftForm" method="post" action="${path}/transferManage/transfer.action" >
	<pop:token />
	<input name="orgId" id="orgId" value="${organization.id}" type="hidden"/>
	<input name="oldOrgId" id="oldOrgId" value="${orgId}" type="hidden"/>
	<input id="orgPangtId" value="${organization.parentOrg.id}" type="hidden"/>
	<input name="toOrgId" id="shiftOrgId" value="" type="hidden" class='form-txt {validateToOrgId:true,messages:{validateToOrgId:"请重新选择目标网格"}}' style="width:0px;border:0px;height:0px;overflow:hidden;position:absolute;top:25px;left:70px;"/>
	<input name="ids" id="ids" value="${ids}" type="hidden"/>
	<input name="type" type="hidden" id="type" value="${type}"/>
	<input type="hidden" id="bigType" value="${bigType}"/>
	<input type="hidden" id="orgIds" value="${orgIds}"/>
</form>
<div id="errorList"></div>

<script type="text/javascript" >
var orgPangtId;
var newSocietyOrganizationsType=$("#type").val();
var orgId = $("#orgId").val();
var orgSelector;
var downLoadList;


function closeDialog(){
	var selectOrgId=$.getSelectedNode(orgSelector).attributes.id;
	if (selectOrgId!=null){
		$("#shiftOrgId").val(selectOrgId);
	}
}
function initRootOrgId(){
	//if(newSocietyOrganizationsType=="newSocietyOrganizations"){
		orgPangtId=USER_ORG_ID;
	//}else{
		//orgPangtId = $("#orgPangtId").val();
	//}
	
}
$(document).ready(function(){
	$("#type").val($("#lowerCaseModuleName").val());
	initRootOrgId();
	orgSelector=$("#organizationTree").initAdministrativeTree({
		rootId:orgPangtId,
		loadCom:function(){
		if(newSocietyOrganizationsType!="newSocietyOrganizations"){
			$.disableNode(orgSelector,orgPangtId);
			$.ajax({
				url:PATH+"/sysadmin/orgManage/ajaxSearchParentNodeIds.action?organization.id="+orgId,
				success:function(data){
					$.searchChild(orgSelector,data,function(){
						$.disableNode(orgSelector,orgId);
					});
				}
			});
		}
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
		if(!bol){
			$.messageBox({level:'warn',message:"数据只能转移到网格"});
		}
		return bol;
	});

	jQuery.validator.addMethod("isSameCity", function(value, element){
		if(value==null||value==undefined||value==""){return true}
		$.ajax({
			async:false,
			url:"${path}/transferManage/isSameCity.action",
			data:{
				"toOrgId":$("#shiftOrgId").val(),
				"orgId":getCurrentOrgId()
			},
			success:function(responseData){
				bol = responseData;
			}
		});
		if(!bol){
			$.messageBox({level:'warn',message:"数据只能同一市内进行转移"});
		}
		return bol;
	});
	
	jQuery.validator.addMethod("isModernOrg", function(value, element){
		if($("#orgId").val()==$("#shiftOrgId").val()){
			return false;
		}
		return true;
	});

	jQuery.validator.addMethod("validateToOrgId", validateToOrgId);
	
	function validateToOrgId(value,element){
		var orgIds = $("#orgIds").val();
		var toOrgId = $("#shiftOrgId").val();
		var orgId = $("#oldOrgId").val();
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
						if($("#bigType").val()=="location"){
							code+="<table><tr><th style='text-align:center;width:160px;'>场所名称</th>";
						}else{
							if(newSocietyOrganizationsType=="newSocietyOrganizations")
								code+="<table><tr><th style='text-align:center;width:160px;'>组织名称</th>";
							else
							code+="<table><tr><th style='text-align:center;width:60px;'>姓名</th><th style='text-align:center;width:180px;'>证件号码</th>";
						}
						code+="<th style='text-align:center;'>错误信息</th></tr>";
						if($("#bigType").val()=="location"){
							for(var i=0;i<data.length;i++){
								code=code+"<tr><td style='text-align:center;'>"+data[i]['name']+"</td><td>"+data[i]['message']+"</td></tr>";
							}
						}else{
							if(newSocietyOrganizationsType=="newSocietyOrganizations"){
								for(var i=0;i<data.length;i++){
									code=code+"<tr><td style='text-align:center;'>"+data[i]['name']+"</td><td>"+data[i]['message']+"</td></tr>";
								}
							}else{
								for(var i=0;i<data.length;i++){
									code=code+"<tr><td style='text-align:center;'>"+data[i]['name']+"</td><td style='text-align:center;'>"+data[i]['idCardNo']+"</td><td>"+data[i]['message']+"</td></tr>";
								}
							}
						}
						code+="</table>";
						$("#errorList").append(code);
						//$("#errorList").append("	请下载无法转移的数据信息       <a onclick='downLoad()' href='javascript:;'>下载数据信息</a>");
					}else {
						$.messageBox({message:data});
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
		rules:{
			"toOrgId":{
				<s:if test='!"newSocietyOrganizations".equals(#parameters.type[0])'>
				isGridOrganization:true,
				isSameCity:true,
				isModernOrg:true
				</s:if>
			}
		},
		messages:{
			"toOrgId":{
				<s:if test='!"newSocietyOrganizations".equals(#parameters.type[0])'>
				isGridOrganization:"数据只能转移到网格",
				isSameCity:"数据只能同一市内进行转移",
				isModernOrg:"数据不能转移到当前网格"
				</s:if>
				}
		}
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