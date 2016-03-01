<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<jsp:include page="/includes/baseInclude.jsp" />
	<div id="landscaping" class="container container_24">
		<form action="${path}/baseinfo/landscapingManage/addLandscaping.action" method="post" id="maintainForm">
		<div id="perUuid"></div>
		<input id="_imgUrl" type="hidden" name="landscaping.imgUrl" value="">
		<input type="hidden" name="mode" id="mode" value="${mode}" />
		<input type="hidden" name="modeType" id="modeType" value="${modeType}" />
		<input type="hidden" name="landscaping.id" id="landscaping-id" value="${landscaping.id}" />
		<input type="hidden" name="landscaping.organization.id" id="landscapingOrganizationId" value="${organizationId}" />
		<div class="grid_5 lable-right">
		<em class="form-req">*</em>
			<label class="form-lbl">所属网格：</label>
		</div>
		<div class="grid_18">
			<input type="text"  name="landscaping.organization.orgName" id="orgName"  readonly class="form-txt" />
		</div>
		<div class="grid_5 lable-right">
		<em class="form-req">*</em>
			<label class="form-lbl">部件编号：</label>
		</div>
		<div class="grid_7">
			<input type="text" id="objNum" name="landscaping.objNum" class="form-txt" value="${landscaping.objNum}" maxlength="16"
			maxlength="50"/>
		</div>
		<div class="grid_5 lable-right">
		<em class="form-req">*</em>
			<label class="form-lbl">部件名称：</label>
		</div>
		<div class="grid_7">
			<input type="text" id="name" name="landscaping.objName" class="form-txt" value="${landscaping.objName}" maxlength="30"
			maxlength="50"/>
		</div>
		
		<div class='clearLine'></div>
		

        <div class="grid_5 lable-right">
            <label class="form-lbl">状态：</label>
        </div>
        <div class="grid_7">
             <select name="landscaping.state.id" class="form-select" id="landscaping-state">
		   <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@STATE" defaultValue="${landscaping.state.id}" />
			</select>
        </div>
        
		<div class='clearLine'></div>
		
		<div class="grid_5 lable-right">
			<label class="form-lbl">所在街道：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="landscaping.street" class="form-txt" value="${landscaping.street}" maxlength="50"
			/>
		</div>
		<div class="grid_5 lable-right">
			<label class="form-lbl">所在社区：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="landscaping.community" class="form-txt" value="${landscaping.community}" maxlength="50"
			/>
		</div>
		
		<div class='clearLine'></div>
		
		<div class="grid_5 lable-right">
		<em class="form-req">*</em>
			<label class="form-lbl">主管部门代码：</label>
		</div>
		<div class="grid_7">
			<input type="text" id="deptCode" name="landscaping.deptCode" class="form-txt" value="${landscaping.deptCode}" maxlength="10"
			maxlength="50"/>
		</div>
		<div class="grid_5 lable-right">
		<em class="form-req">*</em>
			<label class="form-lbl">主管部门名称：</label>
		</div>
		
		<div class="grid_7">
			<input type="text" id="deptName" name="landscaping.deptName" class="form-txt" value="${landscaping.deptName}" maxlength="60"
			maxlength="50"/>
		</div>
		<div class="grid_5 lable-right">
			<label class="form-lbl">权属单位代码：</label>
		</div>
		<div class="grid_7">
			<input type="text" id="ownershipUnitCode" name="landscaping.ownershipUnitCode" class="form-txt" value="${landscaping.ownershipUnitCode}" maxlength="10"
			maxlength="50"/>
		</div>
		<div class="grid_5 lable-right">
			<label class="form-lbl">权属单位名称：</label>
		</div>
		<div class="grid_7">
			<input type="text" id="ownershipUnitName" name="landscaping.ownershipUnitName" class="form-txt" value="${landscaping.ownershipUnitName}" maxlength="60"
			maxlength="50"/>
		</div>
		<div class="grid_5 lable-right">
			<label class="form-lbl">养护单位代码：</label>
		</div>
		<div class="grid_7">
			<input type="text" id="maintenanceUnitCode" name="landscaping.maintenanceUnitCode" class="form-txt" value="${landscaping.maintenanceUnitCode}" maxlength="10"
			maxlength="50"/>
		</div>
		<div class="grid_5 lable-right">
			<label class="form-lbl">养护单位名称：</label>
		</div>
		<div class="grid_7">
			<input type="text" id="maintenanceUnitName" name="landscaping.maintenanceUnitName" class="form-txt" value="${landscaping.maintenanceUnitName}" maxlength="60"
			maxlength="50"/>
		</div>
        
		<div class="grid_5 lable-right">
			<label class="form-lbl">初始时间：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="landscaping.startDate" id="startDate" class="form-txt"
			value="<s:date name="landscaping.startDate" format="yyyy-MM-dd"/>" readonly/>
		</div>
		<div class="grid_5 lable-right">
			<label class="form-lbl">变更时间：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="landscaping.changeDate" id="changeDate" class="form-txt"
			value="<s:date name="landscaping.changeDate" format="yyyy-MM-dd"/>" readonly/>
		</div>
		
		<div class="grid_5 lable-right">
			<label class="form-lbl">数据来源：</label>
		</div>
		<div class="grid_7">
			<input type="text" id="dateSource" name="landscaping.dateSource" class="form-txt" value="${landscaping.dateSource}" maxlength="30"
			maxlength="50"/>
		</div>
		
		<div class="grid_5 lable-right">
			<label class="form-lbl">现场调查权属 ：</label>
		</div>
		<div class="grid_7">
			<input type="text" id="dateSource" name="landscaping.researchOwnership" class="form-txt" value="${landscaping.researchOwnership}" maxlength="30"
			maxlength="50"/>
		</div>
        <div class='clearLine'></div>
        
        <div class="grid_5 lable-right">
			<label class="form-lbl">专业部门确认权属：</label>
		</div>
		<div class="grid_7">
			<input type="text" id="dateSource" name="landscaping.verifyOwnership" class="form-txt" value="${landscaping.verifyOwnership}" maxlength="30"
			maxlength="50"/>
		</div>
       <div class="grid_5 lable-right">
			<label class="form-lbl">案件资料确认权属：</label>
		</div>
		<div class="grid_7">
			<input type="text" id="dateSource" name="landscaping.caseMaterialOwnership" class="form-txt" value="${landscaping.caseMaterialOwnership}" maxlength="30"
			maxlength="50"/>
		</div>
        <div class='clearLine'></div>
        
         <div class="grid_5 lable-right">
			<label class="form-lbl">其他来源权属：</label>
		</div>
		<div class="grid_7">
			<input type="text" id="dateSource" name="landscaping.otherSourceOwnership" class="form-txt" value="${landscaping.otherSourceOwnership}" maxlength="30"
			maxlength="50"/>
		</div>
       <div class="grid_5 lable-right">
			<label class="form-lbl">位置描述：</label>
		</div>
		<div class="grid_7">
			<input type="text" id="dateSource" name="landscaping.objPosition" class="form-txt" value="${landscaping.objPosition}" maxlength="30"
			maxlength="50"/>
		</div>
        <div class='clearLine'></div>

        <div class="grid_5 lable-right">
            <label class="form-lbl">备注：</label>
        </div>
        <div class="grid_18">
            <textarea rows="5" name="landscaping.remark" cols="" class="form-txt">${landscaping.remark}</textarea>
        </div>
		
		<div class='clearLine'>&nbsp;</div>
		<input name="isSubmit" id="isSubmit" type="hidden" value="">
			</form>
	 </div>	
<script>
$("#landscapingOrganizationId").val($("#currentOrgId").val());
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
	jQuery.validator.addMethod("countExsistedLandscaping", function(value, element){
		if(value==null||value==undefined||value==""){return true}
		$.ajax({
			async:false,
			url:"${path}/baseinfo/landscapingManage/hasDuplicateLandscapingObjNum.action",
			data:{
				"landscaping.organization.id":$("#landscapingOrganizationId").val(),
				"landscaping.objNum":$('#objNum').val(),
				"landscaping.id":function(){if($("#mode").val() == "add" ){return -1;}else{ return $("#landscaping-id").val();}}
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
	$("#landscapingOrganizationId").val($("#currentOrgId").val());
}
var landscapingOrgTree="";
function isGridForTreeSelect(){
	if(landscapingOrgTree != ""){
		return $.getSelectedNode(landscapingOrgTree).attributes.orgLevelInternalId == <s:property value="@com.tianque.domain.property.OrganizationLevel@GRID"/>;
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
						 $.messageBox({message:"园林绿化新增成功"});
						 $("#landscapingList").addRowData(data.id,data,"first");
				         doAction("landscapingMaintanceDialog",data.id);
						 $("#landscapingList").setSelection(data.id);
					 }
					 if("edit"==$("#mode").val()){
						 $("#landscapingList").setRowData(data.id,data);
						 $.messageBox({message:"园林绿化修改成功"});
					 } 
					 doAction("<s:property value='#parameters.dailogName[0]'/>",data.id);
				},
				error:function(XMLHttpRequest, textStatus, errorThrown){
  	      			alert("提交数据时发生错误");
	   		    }
			});
		},
		rules:{
		"landscaping.objNum":{
			required:true,
			countExsistedLandscaping:true
		},
		"landscaping.objName":{
			required:true
		},
		"landscaping.deptCode":{
			required:true,
			minlength:1,
			maxlength:10
		},
		"landscaping.deptName":{
			required:true,
			minlength:1,
			maxlength:60
		},

		"landscaping.ownershipUnitCode":{
			minlength:1,
			maxlength:10
		},
		"landscaping.ownershipUnitName":{
			minlength:1,
			maxlength:60
		},
		"landscaping.maintenanceUnitCode":{
			minlength:1,
			maxlength:10
		},
		"landscaping.maintenanceUnitName":{
			minlength:1,
			maxlength:60
		},
		"landscaping.startDate":{
			isStartDate:true
		},
		"landscaping.changeDate":{
			isChangeDate:true
		},
		"landscaping.remark":{
			maxlength:300
		}
	},
		messages:{
		"landscaping.objNum":{
			required:"请输入部件编号",
			countExsistedLandscaping:"该网格下已经存在该编号的部件"
			
		},

		"landscaping.objName":{
			required:"请输入部件名称"
		},
		"landscaping.deptCode":{
			required:"请输入主管部门代码",
			minlength:$.format("主管部门代码至少需要输入{0}个字符"),
			maxlength:$.format("主管部门代码最多需要输入{0}个字符")
		},
		"landscaping.deptName":{
			required:"请输入主管部门名称",
			minlength:$.format("主管部门名称至少需要输入{0}个字符"),
			maxlength:$.format("主管部门名称最多需要输入{0}个字符")
		},
		"landscaping.ownershipUnitCode":{
			minlength:$.format("权属单位代码至少需要输入{0}个字符"),
			maxlength:$.format("权属单位代码最多需要输入{0}个字符")
		},
		"landscaping.ownershipUnitName":{
			minlength:$.format("权属单位名称至少需要输入{0}个字符"),
			maxlength:$.format("权属单位名称最多需要输入{0}个字符")
		},
		"landscaping.maintenanceUnitCode":{
			minlength:$.format("养护单位代码至少需要输入{0}个字符"),
			maxlength:$.format("养护单位代码最多需要输入{0}个字符")
		},
		"landscaping.maintenanceUnitName":{
			minlength:$.format("养护单位名称至少需要输入{0}个字符"),
			maxlength:$.format("养护单位名称最多需要输入{0}个字符")
		},
		"landscaping.startDate":{
			isStartDate:"初始时间应小于变更时间"
		},
		"landscaping.changeDate":{
			isChangeDate:"变更时间应大于初始时间"
		},
		"landscaping.dateSource":{
			minlength:$.format("数据来源至少需要输入{0}个字符"),
			maxlength:$.format("数据来源最多需要输入{0}个字符")
		},
		"landscaping.description":{
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
		landscapingOrgTree=$("#orgName").treeSelect({
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
				$.searchChild(landscapingOrgTree,data);
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
