<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<jsp:include page="/includes/baseInclude.jsp" />
	<div id="otherFacilities" class="container container_24">
		<form action="${path}/baseinfo/otherFacilitiesManage/addOtherFacilities.action" method="post" id="maintainForm">
		<div id="perUuid"></div>
		<input id="_imgUrl" type="hidden" name="otherFacilities.imgUrl" value="">
		<input type="hidden" name="mode" id="mode" value="${mode}" />
		<input type="hidden" name="modeType" id="modeType" value="${modeType}" />
		<input type="hidden" name="otherFacilities.id" id="otherFacilities-id" value="${otherFacilities.id}" />
		<input type="hidden" name="otherFacilities.organization.id" id="otherFacilitiesOrganizationId" value="${organizationId}" />
		<div class="grid_5 lable-right">
		<em class="form-req">*</em>
			<label class="form-lbl">所属网格：</label>
		</div>
		<div class="grid_18">
			<input type="text"  name="otherFacilities.organization.orgName" id="orgName"  readonly class="form-txt" />
		</div>
		<div class="grid_5 lable-right">
		<em class="form-req">*</em>
			<label class="form-lbl">部件编号：</label>
		</div>
		<div class="grid_7">
			<input type="text" id="objNum" name="otherFacilities.objNum" class="form-txt" value="${otherFacilities.objNum}" maxlength="16"
			maxlength="50"/>
		</div>
		<div class="grid_5 lable-right">
		<em class="form-req">*</em>
			<label class="form-lbl">部件名称：</label>
		</div>
		<div class="grid_7">
			<input type="text" id="name" name="otherFacilities.objName" class="form-txt" value="${otherFacilities.objName}" maxlength="30"
			maxlength="50"/>
		</div>
		
		<div class='clearLine'></div>
		

        <div class="grid_5 lable-right">
            <label class="form-lbl">状态：</label>
        </div>
        <div class="grid_7">
             <select name="otherFacilities.state.id" class="form-select" id="otherFacilities-state">
		   <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@STATE" defaultValue="${otherFacilities.state.id}" />
			</select>
        </div>
        
		<div class='clearLine'></div>
		
		<div class="grid_5 lable-right">
			<label class="form-lbl">所在街道：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="otherFacilities.street" class="form-txt" value="${otherFacilities.street}" maxlength="50"
			/>
		</div>
		<div class="grid_5 lable-right">
			<label class="form-lbl">所在社区：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="otherFacilities.community" class="form-txt" value="${otherFacilities.community}" maxlength="50"
			/>
		</div>
		
		<div class='clearLine'></div>
		
		<div class="grid_5 lable-right">
		<em class="form-req">*</em>
			<label class="form-lbl">主管部门代码：</label>
		</div>
		<div class="grid_7">
			<input type="text" id="deptCode" name="otherFacilities.deptCode" class="form-txt" value="${otherFacilities.deptCode}" maxlength="10"
			maxlength="50"/>
		</div>
		<div class="grid_5 lable-right">
		<em class="form-req">*</em>
			<label class="form-lbl">主管部门名称：</label>
		</div>
		
		<div class="grid_7">
			<input type="text" id="deptName" name="otherFacilities.deptName" class="form-txt" value="${otherFacilities.deptName}" maxlength="60"
			maxlength="50"/>
		</div>
		<div class="grid_5 lable-right">
			<label class="form-lbl">权属单位代码：</label>
		</div>
		<div class="grid_7">
			<input type="text" id="ownershipUnitCode" name="otherFacilities.ownershipUnitCode" class="form-txt" value="${otherFacilities.ownershipUnitCode}" maxlength="10"
			maxlength="50"/>
		</div>
		<div class="grid_5 lable-right">
			<label class="form-lbl">权属单位名称：</label>
		</div>
		<div class="grid_7">
			<input type="text" id="ownershipUnitName" name="otherFacilities.ownershipUnitName" class="form-txt" value="${otherFacilities.ownershipUnitName}" maxlength="60"
			maxlength="50"/>
		</div>
		<div class="grid_5 lable-right">
			<label class="form-lbl">养护单位代码：</label>
		</div>
		<div class="grid_7">
			<input type="text" id="maintenanceUnitCode" name="otherFacilities.maintenanceUnitCode" class="form-txt" value="${otherFacilities.maintenanceUnitCode}" maxlength="10"
			maxlength="50"/>
		</div>
		<div class="grid_5 lable-right">
			<label class="form-lbl">养护单位名称：</label>
		</div>
		<div class="grid_7">
			<input type="text" id="maintenanceUnitName" name="otherFacilities.maintenanceUnitName" class="form-txt" value="${otherFacilities.maintenanceUnitName}" maxlength="60"
			maxlength="50"/>
		</div>
        
		<div class="grid_5 lable-right">
			<label class="form-lbl">初始时间：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="otherFacilities.startDate" id="startDate" class="form-txt"
			value="<s:date name="otherFacilities.startDate" format="yyyy-MM-dd"/>" readonly/>
		</div>
		<div class="grid_5 lable-right">
			<label class="form-lbl">变更时间：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="otherFacilities.changeDate" id="changeDate" class="form-txt"
			value="<s:date name="otherFacilities.changeDate" format="yyyy-MM-dd"/>" readonly/>
		</div>
		
		<div class="grid_5 lable-right">
			<label class="form-lbl">数据来源：</label>
		</div>
		<div class="grid_7">
			<input type="text" id="dateSource" name="otherFacilities.dateSource" class="form-txt" value="${otherFacilities.dateSource}" maxlength="30"
			maxlength="50"/>
		</div>
		
		<div class="grid_5 lable-right">
			<label class="form-lbl">现场调查权属 ：</label>
		</div>
		<div class="grid_7">
			<input type="text" id="dateSource" name="otherFacilities.researchOwnership" class="form-txt" value="${otherFacilities.researchOwnership}" maxlength="30"
			maxlength="50"/>
		</div>
        <div class='clearLine'></div>
        
        <div class="grid_5 lable-right">
			<label class="form-lbl">专业部门确认权属：</label>
		</div>
		<div class="grid_7">
			<input type="text" id="dateSource" name="otherFacilities.verifyOwnership" class="form-txt" value="${otherFacilities.verifyOwnership}" maxlength="30"
			maxlength="50"/>
		</div>
       <div class="grid_5 lable-right">
			<label class="form-lbl">案件资料确认权属：</label>
		</div>
		<div class="grid_7">
			<input type="text" id="dateSource" name="otherFacilities.caseMaterialOwnership" class="form-txt" value="${otherFacilities.caseMaterialOwnership}" maxlength="30"
			maxlength="50"/>
		</div>
        <div class='clearLine'></div>
        
         <div class="grid_5 lable-right">
			<label class="form-lbl">其他来源权属：</label>
		</div>
		<div class="grid_7">
			<input type="text" id="dateSource" name="otherFacilities.otherSourceOwnership" class="form-txt" value="${otherFacilities.otherSourceOwnership}" maxlength="30"
			maxlength="50"/>
		</div>
       <div class="grid_5 lable-right">
			<label class="form-lbl">位置描述：</label>
		</div>
		<div class="grid_7">
			<input type="text" id="dateSource" name="otherFacilities.objPosition" class="form-txt" value="${otherFacilities.objPosition}" maxlength="30"
			maxlength="50"/>
		</div>
        <div class='clearLine'></div>

        <div class="grid_5 lable-right">
            <label class="form-lbl">备注：</label>
        </div>
        <div class="grid_18">
            <textarea rows="5" name="otherFacilities.remark" cols="" class="form-txt">${otherFacilities.remark}</textarea>
        </div>
		
		<div class='clearLine'>&nbsp;</div>
		<input name="isSubmit" id="isSubmit" type="hidden" value="">
			</form>
	 </div>	
<script>
$("#otherFacilitiesOrganizationId").val($("#currentOrgId").val());
if(null!=$("#_imgUrl").val() && $("#_imgUrl").val()!=""){
	$("#headerImg").attr("src",$("#_imgUrl").val()+"?r="+Math.random());
	$(".shadow").show();
}
var existed = true;
$(document).ready(function(){
	if(""!=$("#_imgUrl").val()){
		$("#img").attr("src",$("#_imgUrl").val());
	}

	//ajax异步验证是否有重复记录存在
	jQuery.validator.addMethod("countExsistedOtherFacilities", function(value, element){
		if(value==null||value==undefined||value==""){return true}
		$.ajax({
			async:false,
			url:"${path}/baseinfo/otherFacilitiesManage/hasDuplicateOtherFacilitiesObjNum.action",
			data:{
				"otherFacilities.organization.id":$("#otherFacilitiesOrganizationId").val(),
				"otherFacilities.objNum":$('#objNum').val(),
				"otherFacilities.id":function(){if($("#mode").val() == "add" ){return -1;}else{ return $("#otherFacilities-id").val();}}
			},
			success:function(responseData){
				
				existed = responseData;
			}
		});
		return existed;
	});
		
	
		jQuery.validator.addMethod("isStartDate", function(value, element){
			var value = $('#startDate').val();
			if(value==null||value==undefined||value==""){return true}
			if(value < $('#changeDate').val()){
	           return true;
			}else {
				return false;
			}

		});

		jQuery.validator.addMethod("isChangeDate", function(value, element){
			var value = $('#changeDate').val();
			if(value==null||value==undefined||value==""){return true}
			if(value > $('#startDate').val()){
	           return true;
			}else {
				return false;
			}

		});

	$('#startDate').datePicker({
		yearRange:'1900:2030',
		dateFormat:'yy-mm-dd',
        maxDate:'+0d'
	});
	$('#changeDate').datePicker({
		yearRange:'1900:2030',
		dateFormat:'yy-mm-dd',
        maxDate:'+0d'
	});
function changeOrgId(){
	$("#otherFacilitiesOrganizationId").val($("#currentOrgId").val());
}
var otherFacilitiesOrgTree="";
function isGridForTreeSelect(){
	if(otherFacilitiesOrgTree != ""){
		return $.getSelectedNode(otherFacilitiesOrgTree).attributes.orgLevelInternalId == <s:property value="@com.tianque.domain.property.OrganizationLevel@GRID"/>;
	}else{
		return true;
	}
}

var bol = true;

	$("#tabs").tabFunction({ cache: true });
	$.ajax({
		async: false,
		url: "${path}/sysadmin/orgManage/getOrgRelativePath.action",
		data:{
			"organization.id":$("#currentOrgId").val()
		},
		success:function(responseData){
			$("#orgName").val(responseData);
		}
	});

	
	$(".dialogtip").inputtip();
	$("#maintainForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form){
		$("#_imgUrl").attr("src",$("#_imgUrl").val());
			$(form).ajaxSubmit({
				success:function(data){
					if(!data.id){
           	 			$.messageBox({
							message:data,
							evel: "error"
			 			});
            			return;
					}
					if("add"==$("#mode").val()){
						 $.messageBox({message:"其他设施新增成功"});
						 $("#otherFacilitiesList").addRowData(data.id,data,"first");
				         doAction("otherFacilitiesMaintanceDialog",data.id);
						 $("#otherFacilitiesList").setSelection(data.id);
					 }
					 if("edit"==$("#mode").val()){
						 $("#otherFacilitiesList").setRowData(data.id,data);
						 $.messageBox({message:"其他设施修改成功"});
					 } 
					 doAction("<s:property value='#parameters.dailogName[0]'/>",data.id);
				},
				error:function(XMLHttpRequest, textStatus, errorThrown){
  	      			alert("提交数据时发生错误");
	   		    }
			});
		},
		rules:{
		"otherFacilities.objNum":{
			required:true,
			countExsistedOtherFacilities:true
		},
		"otherFacilities.objName":{
			required:true
		},
		"otherFacilities.deptCode":{
			required:true,
			minlength:1,
			maxlength:10
		},
		"otherFacilities.deptName":{
			required:true,
			minlength:1,
			maxlength:60
		},

		"otherFacilities.ownershipUnitCode":{
			minlength:1,
			maxlength:10
		},
		"otherFacilities.ownershipUnitName":{
			minlength:1,
			maxlength:60
		},
		"otherFacilities.maintenanceUnitCode":{
			minlength:1,
			maxlength:10
		},
		"otherFacilities.maintenanceUnitName":{
			minlength:1,
			maxlength:60
		},
		"otherFacilities.startDate":{
			isStartDate:true
		},
		"otherFacilities.changeDate":{
			isChangeDate:true
		},
		"otherFacilities.remark":{
			maxlength:300
		}
	},
		messages:{
		"otherFacilities.objNum":{
			required:"请输入部件编号",
			countExsistedOtherFacilities:"该网格下已经存在该编号的部件"
			
		},

		"otherFacilities.objName":{
			required:"请输入部件名称"
		},
		"otherFacilities.deptCode":{
			required:"请输入主管部门代码",
			minlength:$.format("主管部门代码至少需要输入{0}个字符"),
			maxlength:$.format("主管部门代码最多需要输入{0}个字符")
		},
		"otherFacilities.deptName":{
			required:"请输入主管部门名称",
			minlength:$.format("主管部门名称至少需要输入{0}个字符"),
			maxlength:$.format("主管部门名称最多需要输入{0}个字符")
		},
		"otherFacilities.ownershipUnitCode":{
			minlength:$.format("权属单位代码至少需要输入{0}个字符"),
			maxlength:$.format("权属单位代码最多需要输入{0}个字符")
		},
		"otherFacilities.ownershipUnitName":{
			minlength:$.format("权属单位名称至少需要输入{0}个字符"),
			maxlength:$.format("权属单位名称最多需要输入{0}个字符")
		},
		"otherFacilities.maintenanceUnitCode":{
			minlength:$.format("养护单位代码至少需要输入{0}个字符"),
			maxlength:$.format("养护单位代码最多需要输入{0}个字符")
		},
		"otherFacilities.maintenanceUnitName":{
			minlength:$.format("养护单位名称至少需要输入{0}个字符"),
			maxlength:$.format("养护单位名称最多需要输入{0}个字符")
		},
		"otherFacilities.startDate":{
			isStartDate:"初始时间应小于变更时间"
		},
		"otherFacilities.changeDate":{
			isChangeDate:"变更时间应大于初始时间"
		},
		"otherFacilities.dateSource":{
			minlength:$.format("数据来源至少需要输入{0}个字符"),
			maxlength:$.format("数据来源最多需要输入{0}个字符")
		},
		"otherFacilities.description":{
			maxlength:$.format("描述最多需要输入{0}个字符")
		}
	}
	});
	


	$(".dialogtip").inputtip();
	<s:if test='"add_path".equals(modeType)'>
	$("#orgName").click(function(event){
		$("#noticeDialog").createDialog({
			width: 300,
			height: 350,
			title:'请选择一个部门',
			url:'${path}/common/organizationSelector.jsp',
			buttons: {
				"确定" : function(){
					closeDialog();
				},
				"取消" : function(){
					$("#noticeDialog").dialog("close");
				}
			}
		});
	});
	</s:if>

	<s:if test='#parameters.dialog != null'>
		otherFacilitiesOrgTree=$("#orgName").treeSelect({
			inputName:"ownerOrg.id",
			url:"/sysadmin/orgManage/loadTownForExtTree.action",
			onSelect:changeOrgId
		});
		var bol = false;
		$.ajax({
			async:false,
			url:"${path}/issue/issueManage/checkOccurOrgId.action",
			data:{
				"issueNew.occurOrg.id":${USER_ORG_ID}
			},
			success:function(responseData){
				bol = responseData;
			}
		});
		var url = "";
		if(bol){
			url = PATH+"/sysadmin/orgManage/ajaxSearchParentNodeIdsWhenRootIsTown.action?organization.id="+$("#organizationId").val()
		}else{
			url = PATH+"/sysadmin/orgManage/ajaxSearchParentNodeIds.action?organization.id="+$("#organizationId").val()
		}
		$.ajax({
			url:url,
			success:function(data){
				$.searchChild(otherFacilitiesOrgTree,data);
			}
		});
	</s:if>
});

<s:if test='"add_path".equals(modeType)'>
function setZone(selectOrgId,selectOrgName){
		$("#organizationId").val(selectOrgId);
		$("#orgName").val(selectOrgName);
	}
</s:if>





</script>
