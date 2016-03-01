<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div class="container_24">
	<div class='clearLine'></div>
	<div class='clearLine'></div>
	<div class='grid_24'>
		<b>数据合并为：</b>
	</div>
	<form id="combineForm" action="${path}/primaryOrg/primaryMembersManage/combinePrimaryMembers.action" method="post">
		<input type="hidden" name="ids" id="removeIds" value="0">
		<input type="hidden"  name="primaryMembers.id" id="id" value="${primaryMembers.id}">
		<input type="hidden" name="primaryMembers.org.id" id="orgId" value="${primaryMembers.org.id}">
		<input type="hidden" name="primaryMembers.orgCode" id="orgCode" value="${primaryMembers.orgCode}">
		<div class="grid_3 lable-right">
			<em class="form-req">*</em> <label class="form-lb1">姓名：</label>
		</div>
		<div class="grid_5">
			<input type="text" name="primaryMembers.name" readonly="readonly" id="name" maxlength="20" value='${primaryMembers.name}'
			class='form-txt {required:true,exculdeParticalChar:true,minlength:2,maxlength:20,messages:{required:"请输入姓名",exculdeParticalChar:"不能输入非法字符",minlength:$.format("姓名至少需要输入{0}个字符"),maxlength:$.format("姓名最多需要输入{0}个字符")}}'/>
		</div>
		<div class="grid_3 lable-right">
			<em class="form-req">*</em> <label class="form-lb1">性别：</label>
		</div>
		<div class="grid_4">
			<select name="primaryMembers.gender.id"  style="width: 98%" id="gender" class='form-txt {required:true,messages:{required:"请选择性别"}}'>
				<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@GENDER" defaultValue="${primaryMembers.gender.id}" />
			</select>
		</div>
		<div class="grid_3 lable-right" >
			<em class="form-req"></em> <label class="form-lb1">身份证号：</label>
		</div>
		<div class="grid_6">
			<input type="text" name="primaryMembers.idcardNo" id="idcardNo" maxlength="18" value="${primaryMembers.idcardNo}" style="width: 80%" class="form-txt" />
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		<div class="grid_3 lable-right">
			<label class="form-lb1">出生年月 ：</label>
		</div>
		<div class="grid_5" id="birthdayDiv">
			<input type="text" name="primaryMembers.birthday" id="birthday" maxlength="20" value="<fmt:formatDate value="${primaryMembers.birthday}" type="date" pattern="yyyy-MM-dd" />" class="form-txt" readonly="readonly"/>
		</div>
		<div class="grid_3 lable-right">
			<em class="form-req">*</em> <label class="form-lb1">民族：</label>
		</div>
		<div class="grid_4">
			<select name="primaryMembers.nation.id" id="nation" class='form-txt'  style="width: 98%" >
				<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@NATION" defaultValue="${primaryMembers.nation.id}" />
			</select>
		</div>
		<div class="grid_3 lable-right">
			<em class="form-req">*</em> <label class="form-lb1">政治面貌：</label>
		</div>
		<div class="grid_5">
			<select name="primaryMembers.politicalBackground.id" id="politicalBackground" class='form-txt'  style="width: 98%" >
				<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@POLITICAL_BACKGROUND" defaultValue="${primaryMembers.politicalBackground.id}" />
			</select>
		</div>
		
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_3 lable-right">
			<em class="form-req">*</em> <label class="form-lb1">文化程度：</label>
		</div>
		<div class="grid_5">
			<select name="primaryMembers.schooling.id" id="schooling" class='form-txt'  style="width: 99%" >
				<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@SCHOOLING" defaultValue="${primaryMembers.schooling.id}" />
			</select>
		</div>
		<div class="grid_3 lable-right">
			<label class="form-lb1">联系手机：</label>
		</div>
		<div class="grid_4">
			<input type="text" name="primaryMembers.mobile" maxlength="11" style="width: 95.5%" value="${primaryMembers.mobile}" id='mobile'
			title="请输入11位以1开头的联系手机  例如：13988888888" class='form-txt {mobile:true,messages:{mobile:"手机号码输入只能是以1开头的11位数字"}}' />
		</div>
		<div class="grid_3 lable-right">
			<label class="form-lb1">联系电话：</label>
		</div>
		<div class="grid_5">
			<input type="text" name="primaryMembers.telephone" id="telephone" maxlength="15" style="width: 95.5%" value="${primaryMembers.telephone}" title="请输入由数字和-组成的联系电话,例如：0577-88888888"
			class='form-txt {telephone:true,messages:{telephone:$.format("固定电话不合法，只能输数字和横杠(-)")}}' />
		</div>
	</form>
	<div class='clearLine'></div>
	<div class='grid_24'>
		<b>已选择合并的数据</b>
	</div>
	<div class="content">
		<div style="clear: both;"></div>
		<div style="width: 100%;">
			<table id="combinePrimaryMembersList"></table>
			<div id="combinePrimaryMembersListPager"></div>
		</div>
		<div id="combinePrimaryMembersDialog"></div>
	</div>
	
</div>
<script type="text/javascript">
$(document).ready(function(){
	$("#combinePrimaryMembersList").jqGridFunction({
		datatype: "local",
		colModel:[
			{name:"id",index:"id",sortable:true,hidden:true},
			{name:"org.id",index:"org.id",sortable:false,hidden:true},
			{name:'name',index:"name",label:"姓名",width:100,frozen:true,sortable:true},
			{name:'gender',index:'gender',label:"性别",width:80,formatter:genderFormatter,sortable:true,align:"center"},
			{name:'mobile',index:'mobile',label:"联系手机",sortable:true,width:110,align:"center"},
			{name:'telephone',index:'telephone',label:"固定电话",sortable:true,width:110},
			{name:"birthday",index:"birthday",label:"出生年月",sortable:true,width:80,align:"center",formatter: function (arg0){return arg0 == null ? '' :arg0.toString().replace('T00:00:00', '')}},
			{name:"org.orgName",index:"org.orgName",label:"所属区域(网格)",sortable:false,width:350}
		],
		multiselect:true,
		height:290,
	    showColModelButton:false
	});
	
	getPrimaryMembersList();
		
	
	//获取年份的选择下拉框
	$.ajax({
		async: false,
		url: "${path }/plugin/serviceTeam/serviceTeamMember/getCurrentTimeForIntegrativeQueryYear.action",
		success:function(responseData){
			var year='${primaryMembers.year}';
			for(var i = 0;i<responseData.length;i++){
				if(responseData[i]==year){
					$("#year").append("<option value='"+responseData[i]+"' selected >"+responseData[i]+"</option>");
				}else{
					$("#year").append("<option value='"+responseData[i]+"'>"+responseData[i]+"</option>");}
			}
		}
	});
	
	$("#idcardNo").blur(function(){
		var text=$('#idcardNo').val();
		text=getBirthDayTextFromIdCard(text);
		resetBirthdayField(text);
	});
	function resetBirthdayField(text){
		if (text!="" && $('#idcardNo').val()!=""){
			$("#birthdayDiv").html("<input type='text' style='width: 95%' name='primaryMembers.birthday' id='birthday' class='form-txt' value='"+text+"' readonly/>");
		}else{
			$('#birthday').datePicker({
				yearRange: '1900:2030',
	    		dateFormat: 'yy-mm-dd',
	            maxDate:'+0d'});
		}
	}
	function getBirthDayTextFromIdCard(idCard){
		if(idCard!=null&&idCard.length==18){
			idCard=idCard.substring(6,14);
			if(idCard.substring(4,6)<=0||idCard.substring(4,6)>12){
				return "";
			}else if(idCard.substring(6,8)<=0||idCard.substring(6,8)>31){
				return "";
			}else{
				return idCard.substring(0,4)+"-"+idCard.substring(4,6)+"-"+idCard.substring(6,8);
			}
		}else if(idCard!=null&&idCard.length==15){
			idCard=idCard.substring(6,12);
			if(idCard.substring(2,4)<=0||idCard.substring(2,4)>12){
				return "";
			}else if(idCard.substring(4,6)<=0||idCard.substring(4,6)>31){
				return "";
			}else{
				return "19"+idCard.substring(0,2)+"-"+idCard.substring(2,4)+"-"+idCard.substring(4,6);
			}
		}
		return "";
	}
	
});
//列表显示
function getPrimaryMembersList(){
	var selectedIds = $("#combinePrimaryMembersList").jqGrid("getGridParam", "selarrrow");
	$("#removeIds").val(selectedIds);
	$("#combinePrimaryMembersList").setGridParam({
		url:'${path}/primaryOrg/primaryMembersManage/findPrimaryMembersByNameOrMobile.action?primaryMembers.name='+encodeURIComponent($("#name").val())+'&primaryMembers.mobile='+$("#mobile").val()+'&primaryMembers.id='+$("#id").val()+'&primaryMembers.orgCode='+$("#orgCode").val()+'&ids='+$("#removeIds").val()+'&primaryMembers.idcardNo='+$("#idcardNo").val(),
		datatype: "json",
		page:1,
		mytype:"post"
	});
	$("#combinePrimaryMembersList").trigger("reloadGrid"); 
}

//表单验证
$("#combineForm").formValidate({
	submitHandler: function(form) {
		var selectedIds = $("#combinePrimaryMembersList").jqGrid("getGridParam", "selarrrow");
		$("#removeIds").val(selectedIds);
		$(form).ajaxSubmit({
	         success: function(data){
	         	if(!data.id){
	           		$.messageBox({
						message:data,
						level: "error"
				 	});
	            	return;
				}
				$.messageBox({message:"成功合并!"});
				$("#combinePrimaryMembersList").trigger("reloadGrid");
				$("#primaryMembersDialog${primaryMembers.isHaveJob}").dialog("close");
				$("#primaryMembers${primaryMembers.isHaveJob}List").trigger("reloadGrid");
			},
		    error: function(XMLHttpRequest, textStatus, errorThrown){
	         	$.messageBox({message:"提交错误",level: "error"});
		    }
		});
	},
	rules:{
		"primaryMembers.name":{
			required:true,
			exculdeParticalChar:true,
			minlength:2,
			maxlength:20
		},
		"primaryMembers.idcardNo":{
			idCard:true
		},
		"primaryMembers.gender.id":{
			required:true
		},
		"primaryMembers.nation.id":{
			required:true
		},
		"primaryMembers.politicalBackground.id":{
			required:true
		},
		"primaryMembers.schooling.id":{
			required:true
		},
		"primaryMembers.mobile":{
			mobile:true
		},
		"primaryMembers.telephone":{
			telephone:true
		}
	},
	messages:{
		"primaryMembers.name":{
			required:"请输入姓名",
			exculdeParticalChar:"不能输入非法字符",
			minlength:$.format("姓名至少需要输入{0}个字符"),
			maxlength:$.format("姓名最多需要输入{0}个字符")
		},
		"primaryMembers.idcardNo":{
			idCard:"请输入一个合法的身份证号码"
		},
		"primaryMembers.gender.id":{
			required:"请选择性别"
		},
		"primaryMembers.nation.id":{
			required:"请选择民族"
		},
		"primaryMembers.politicalBackground.id":{
			required:"请选择政治面貌"
		},
		"primaryMembers.schooling.id":{
			required:"请选文化程度"
		},
		"primaryMembers.mobile":{
			mobile:"手机号码输入只能是以1开头的11位数字"
		},
		"primaryMembers.telephone":{
			telephone:"固定电话不合法，只能输数字和横杠(-)"
		}
	}
});

</script>