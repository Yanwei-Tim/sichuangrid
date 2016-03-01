<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="dialog-form" title="机关党组织表" class="container container_24">
	
	<form id="maintainForm" method="post" action="">
		<pop:token />
		<input type="hidden" name="mode" id="mode" value="${mode}" />
		<input type="hidden" name="organsParty.id" value="${organsParty.id }">
		<c:if test='${mode=="edit"}'>
			<input type="hidden" name="organsParty.type.id" value="${organsParty.type.id }">
		</c:if>
		<input type="hidden" name="oldPartyOrg" value="${organsParty.name}">
		<div class="grid_4 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">党组织类型：</label>
	 	</div>
		<div class="grid_7">
			<select name="organsParty.type.id" id="organsPartyType"  <c:if test='${mode=="edit"}'>disabled="disabled"</c:if> class='form-txt {required:true,messages:{required:"请选择党组织类型"}}'>
		   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@PARTYORGTYPE"  defaultValue="${organsParty.type.id}" />
			</select>
		</div>
		
		<div class="grid_4 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">所属部门：</label>
	 	</div>
		<div class="grid_7">
			<select name="organsParty.department.id" id="department" class='form-txt {required:true,messages:{required:"请选择所属部门"}}'>
		   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@BELONG_ORG"  defaultValue="${organsParty.department.id}" />
			</select>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">党组织名称：</label>
	 	</div>
		<div class="grid_18">
			<input type="text" name="organsParty.name" id="name" style="width: 99%" maxlength="30" class='form-txt {required:true,maxlength:30,<c:if test='${mode=="add"}'>ishasSameName:true,</c:if>messages:{required:"请输入组织名称",maxlength:$.format("姓名不能多于{0}个字符")<c:if test='${mode=="add"}'>,ishasSameName:"已有相同名称的机关党组织存在"</c:if>}}' value="${organsParty.name}"/>
		</div>
		<div class='clearLine'>&nbsp;</div>
	 	
	 	<div id="superiorDiv" style="display: none;">
		 	<div class="grid_4 lable-right">
				<label class="form-lbl">上级党组织：</label>
		 	</div>
			<div class="grid_7">
				<input type="hidden"  name="organsParty.superior.id" id="superior_id"  value="${organsParty.superior.id}"/>
				<input type="text" readonly="readonly" name="organsParty.superior.name" id="superior_name"  maxlength="" class="form-txt" value="${organsParty.superior.name}"/>
			</div>
			<div class="ui-corner-all" id="nav">
				<a id="searchSuperior" href="javascript:void(0)"><span>查询</span></a>
			</div>
		</div>
		<div class='clearLine'>&nbsp;</div>
		
	 	<div class="grid_4 lable-right">
			<label class="form-lbl">联系人：</label>
	 	</div>
		<div class="grid_7">
			<input type="text" name="organsParty.contact" id="contact"  maxlength="20" class='form-txt {maxlength:20,minlength:2,exculdeParticalChar:true,messages:{maxlength:$.format("联系人姓名不能多于{0}个字符"),minlength:$.format("联系人姓名不能少于{0}个字符"),exculdeParticalChar:"不能输入非法字符"}}' value="${organsParty.contact}"/>
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">联系电话：</label>
	 	</div>
		<div class="grid_7">
			<input type="text" name="organsParty.telephone" id="telephone"  maxlength="15" class='form-txt {maxlength:15,telephone:true,messages:{maxlength:$.format("联系电话不能多于{0}个字符"),telephone:"请填写正确的联系电话"}}' value="${organsParty.telephone}"/>
		</div>
		<div class='clearLine'>&nbsp;</div>
	 	<div class="grid_4 lable-right">
			<label class="form-lbl">备注：</label>
	 	</div>
		<div class="grid_18">
			<textarea id="remark" style="width: 99%" name="organsParty.remark" class='form-txt {maxlength:200,messages:{maxlength:$.format("备注不能多于{0}个字符")}}' rows="4">${organsParty.remark}</textarea>
		</div>
	</form>
	<div id="searchOrgansPartyDialog"></div>
	
</div>
<script type="text/javascript">

$(document).ready(function(){

	jQuery.validator.addMethod("ishasSameName", function(value, element){
		var flag=true;
		$.ajax({
			async:false,
			url:"${path}/organsPartyManage/checkOrgansPartyHas.action",
			data:{
				"organsParty.name": function(){ return $('#name').val()}
			},
			success:function(responseData){	
				flag = responseData;
			}
		});
		return flag;
	});
	
	$("#maintainForm").formValidate({
		submitHandler: function(form) {
	        $(form).ajaxSubmit({
				success: function(data){
		        	if(!data.id){
						$.messageBox({
							message:data,
							level: "error"
						});
						return;
					}
		        	<c:if test='${mode=="edit"}'>
						//$("#organsPartyList").setRowData(data.id,data);
						searchByBelongOrgAndPartyOrgType();
				    	$.messageBox({message:"机关党组织表修改成功!"});
				    	$("#organsPartyDialog").dialog("close");
					</c:if>
					<c:if test='${mode=="add"}'>
						//$("#organsPartyList").addRowData(data.id,data,"first");
						searchByBelongOrgAndPartyOrgType();
						$.messageBox({message:"机关党组织表新增成功!"});
						$("#organsPartyDialog").dialog("close");
					</c:if>
	      	   	},
	      	   	error: function(XMLHttpRequest, textStatus, errorThrown){
	      	    	alert("提交数据时发生错误");
	      	   	}
	      	});
		},
		rules:{
		},
		messages:{
		},
		ignore:':hidden'
	});
	<c:if test='${mode=="add"}'>
	    $("#maintainForm").attr("action","${path}/organsPartyManage/addOrgansParty.action");
	</c:if>
	<c:if test='${mode=="edit"}'>
	    $("#maintainForm").attr("action","${path}/organsPartyManage/updateOrgansParty.action");
	</c:if>
	<c:if test='${mode=="edit"}'>
		if($('#organsPartyType option:selected').text()=="党委"){
			$("#superiorDiv").hide();
		}else{
			$("#superiorDiv").show();
		}
	</c:if>
	$("#organsPartyType").change(function(){
		$("#superior_id").val("");
		$("#superior_name").val("");
		if($('#organsPartyType option:selected').text()=="党委"){
			$("#superiorDiv").hide();
		}else{
			$("#superiorDiv").show();
		}
	});
	$("#searchSuperior").click(function(){
		$("#searchOrgansPartyDialog").createDialog({
			title:"查询上级党组织",
			width: 550,
			height: 450,
			url:"${path}/partyBuilding/organsParty/organsPartyManage/SuperiorOrgansPartyList.jsp",
			buttons: {
	    		   "保存" : function(){
	    			   setSuperior();
	    		   },
	    		   "关闭" : function(){
	    		        $(this).dialog("close"); 
	    		   }
	    		}
		});
	});
});

function setSuperior(){
	var selectedId=$("#superiorOrgansPartyList").jqGrid("getGridParam", "selrow");
	if(selectedId==null){
		$.messageBox({level:'warn',message:"请选择上级党组织！"});
		return;
	}
	var rowData = $("#superiorOrgansPartyList").getRowData(selectedId);
	$("#superior_id").val(rowData.id);
	$("#superior_name").val(rowData.name);
	$("#searchOrgansPartyDialog").dialog("close");
}

</script>


