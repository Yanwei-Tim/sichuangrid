<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<jsp:include page="/includes/baseInclude.jsp" />
	<div id="roadTraffic" class="container container_24">
		<form action="${path}/baseinfo/roadTrafficManage/addRoadTraffic.action" method="post" id="maintainForm">
		<div id="perUuid"></div>
		<input id="_imgUrl" type="hidden" name="roadTraffic.imgUrl" value="">
		<input type="hidden" name="mode" id="mode" value="${mode}" />
		<input type="hidden" name="modeType" id="modeType" value="${modeType}" />
		<input type="hidden" name="roadTraffic.id" id="roadTraffic-id" value="${roadTraffic.id}" />
		<input type="hidden" name="roadTraffic.organization.id" id="roadTrafficOrganizationId" value="${organizationId}" />
		<div class="grid_5 lable-right">
		<em class="form-req">*</em>
			<label class="form-lbl">所属网格：</label>
		</div>
		<div class="grid_18">
			<input type="text"  name="roadTraffic.organization.orgName" id="orgName"  readonly class="form-txt" />
		</div>
		<div class="grid_5 lable-right">
		<em class="form-req">*</em>
			<label class="form-lbl">部件编号：</label>
		</div>
		<div class="grid_7">
			<input type="text" id="objNum" name="roadTraffic.objNum" class="form-txt" value="${roadTraffic.objNum}" maxlength="16"
			maxlength="50"/>
		</div>
		<div class="grid_5 lable-right">
		<em class="form-req">*</em>
			<label class="form-lbl">部件名称：</label>
		</div>
		<div class="grid_7">
			<input type="text" id="name" name="roadTraffic.objName" class="form-txt" value="${roadTraffic.objName}" maxlength="30"
			maxlength="50"/>
		</div>
		
		<div class='clearLine'></div>
		

        <div class="grid_5 lable-right">
            <label class="form-lbl">状态：</label>
        </div>
        <div class="grid_7">
             <select name="roadTraffic.state.id" class="form-select" id="roadTraffic-state">
		   <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@STATE" defaultValue="${roadTraffic.state.id}" />
			</select>
        </div>
        
		<div class='clearLine'></div>
		
		<div class="grid_5 lable-right">
			<label class="form-lbl">所在街道：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="roadTraffic.street" class="form-txt" value="${roadTraffic.street}" maxlength="50"
			/>
		</div>
		<div class="grid_5 lable-right">
			<label class="form-lbl">所在社区：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="roadTraffic.community" class="form-txt" value="${roadTraffic.community}" maxlength="50"
			/>
		</div>
		
		<div class='clearLine'></div>
		
		<div class="grid_5 lable-right">
		<em class="form-req">*</em>
			<label class="form-lbl">主管部门代码：</label>
		</div>
		<div class="grid_7">
			<input type="text" id="deptCode" name="roadTraffic.deptCode" class="form-txt" value="${roadTraffic.deptCode}" maxlength="10"
			maxlength="50"/>
		</div>
		<div class="grid_5 lable-right">
		<em class="form-req">*</em>
			<label class="form-lbl">主管部门名称：</label>
		</div>
		
		<div class="grid_7">
			<input type="text" id="deptName" name="roadTraffic.deptName" class="form-txt" value="${roadTraffic.deptName}" maxlength="60"
			maxlength="50"/>
		</div>
		<div class="grid_5 lable-right">
			<label class="form-lbl">权属单位代码：</label>
		</div>
		<div class="grid_7">
			<input type="text" id="ownershipUnitCode" name="roadTraffic.ownershipUnitCode" class="form-txt" value="${roadTraffic.ownershipUnitCode}" maxlength="10"
			maxlength="50"/>
		</div>
		<div class="grid_5 lable-right">
			<label class="form-lbl">权属单位名称：</label>
		</div>
		<div class="grid_7">
			<input type="text" id="ownershipUnitName" name="roadTraffic.ownershipUnitName" class="form-txt" value="${roadTraffic.ownershipUnitName}" maxlength="60"
			maxlength="50"/>
		</div>
		<div class="grid_5 lable-right">
			<label class="form-lbl">养护单位代码：</label>
		</div>
		<div class="grid_7">
			<input type="text" id="maintenanceUnitCode" name="roadTraffic.maintenanceUnitCode" class="form-txt" value="${roadTraffic.maintenanceUnitCode}" maxlength="10"
			maxlength="50"/>
		</div>
		<div class="grid_5 lable-right">
			<label class="form-lbl">养护单位名称：</label>
		</div>
		<div class="grid_7">
			<input type="text" id="maintenanceUnitName" name="roadTraffic.maintenanceUnitName" class="form-txt" value="${roadTraffic.maintenanceUnitName}" maxlength="60"
			maxlength="50"/>
		</div>
        
		<div class="grid_5 lable-right">
			<label class="form-lbl">初始时间：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="roadTraffic.startDate" id="startDate" class="form-txt"
			value="<s:date name="roadTraffic.startDate" format="yyyy-MM-dd"/>" readonly/>
		</div>
		<div class="grid_5 lable-right">
			<label class="form-lbl">变更时间：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="roadTraffic.changeDate" id="changeDate" class="form-txt"
			value="<s:date name="roadTraffic.changeDate" format="yyyy-MM-dd"/>" readonly/>
		</div>
		
		<div class="grid_5 lable-right">
			<label class="form-lbl">数据来源：</label>
		</div>
		<div class="grid_7">
			<input type="text" id="dateSource" name="roadTraffic.dateSource" class="form-txt" value="${roadTraffic.dateSource}" maxlength="30"
			maxlength="50"/>
		</div>
		
		<div class="grid_5 lable-right">
			<label class="form-lbl">现场调查权属 ：</label>
		</div>
		<div class="grid_7">
			<input type="text" id="dateSource" name="roadTraffic.researchOwnership" class="form-txt" value="${roadTraffic.researchOwnership}" maxlength="30"
			maxlength="50"/>
		</div>
        <div class='clearLine'></div>
        
        <div class="grid_5 lable-right">
			<label class="form-lbl">专业部门确认权属：</label>
		</div>
		<div class="grid_7">
			<input type="text" id="dateSource" name="roadTraffic.verifyOwnership" class="form-txt" value="${roadTraffic.verifyOwnership}" maxlength="30"
			maxlength="50"/>
		</div>
       <div class="grid_5 lable-right">
			<label class="form-lbl">案件资料确认权属：</label>
		</div>
		<div class="grid_7">
			<input type="text" id="dateSource" name="roadTraffic.caseMaterialOwnership" class="form-txt" value="${roadTraffic.caseMaterialOwnership}" maxlength="30"
			maxlength="50"/>
		</div>
        <div class='clearLine'></div>
        
         <div class="grid_5 lable-right">
			<label class="form-lbl">其他来源权属：</label>
		</div>
		<div class="grid_7">
			<input type="text" id="dateSource" name="roadTraffic.otherSourceOwnership" class="form-txt" value="${roadTraffic.otherSourceOwnership}" maxlength="30"
			maxlength="50"/>
		</div>
       <div class="grid_5 lable-right">
			<label class="form-lbl">位置描述：</label>
		</div>
		<div class="grid_7">
			<input type="text" id="dateSource" name="roadTraffic.objPosition" class="form-txt" value="${roadTraffic.objPosition}" maxlength="30"
			maxlength="50"/>
		</div>
        <div class='clearLine'></div>

        <div class="grid_5 lable-right">
            <label class="form-lbl">备注：</label>
        </div>
        <div class="grid_18">
            <textarea rows="5" name="roadTraffic.remark" cols="" class="form-txt">${roadTraffic.remark}</textarea>
        </div>
		
		<div class='clearLine'>&nbsp;</div>
		<input name="isSubmit" id="isSubmit" type="hidden" value="">
			</form>
	 </div>	
<script>
$("#roadTrafficOrganizationId").val($("#currentOrgId").val());
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
	jQuery.validator.addMethod("countExsistedRoadTraffic", function(value, element){
		if(value==null||value==undefined||value==""){return true}
		$.ajax({
			async:false,
			url:"${path}/baseinfo/roadTrafficManage/hasDuplicateRoadTrafficObjNum.action",
			data:{
				"roadTraffic.organization.id":$("#roadTrafficOrganizationId").val(),
				"roadTraffic.objNum":$('#objNum').val(),
				"roadTraffic.id":function(){if($("#mode").val() == "add" ){return -1;}else{ return $("#roadTraffic-id").val();}}
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
	$("#roadTrafficOrganizationId").val($("#currentOrgId").val());
}
var roadTrafficOrgTree="";
function isGridForTreeSelect(){
	if(roadTrafficOrgTree != ""){
		return $.getSelectedNode(roadTrafficOrgTree).attributes.orgLevelInternalId == <s:property value="@com.tianque.domain.property.OrganizationLevel@GRID"/>;
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
						 $.messageBox({message:"道路交通新增成功"});
						 $("#roadTrafficList").addRowData(data.id,data,"first");
				         doAction("roadTrafficMaintanceDialog",data.id);
						 $("#roadTrafficList").setSelection(data.id);
					 }
					 if("edit"==$("#mode").val()){
						 $("#roadTrafficList").setRowData(data.id,data);
						 $.messageBox({message:"道路交通修改成功"});
					 } 
					 doAction("<s:property value='#parameters.dailogName[0]'/>",data.id);
				},
				error:function(XMLHttpRequest, textStatus, errorThrown){
  	      			alert("提交数据时发生错误");
	   		    }
			});
		},
		rules:{
		"roadTraffic.objNum":{
			required:true,
			countExsistedRoadTraffic:true
		},
		"roadTraffic.objName":{
			required:true
		},
		"roadTraffic.deptCode":{
			required:true,
			minlength:1,
			maxlength:10
		},
		"roadTraffic.deptName":{
			required:true,
			minlength:1,
			maxlength:60
		},

		"roadTraffic.ownershipUnitCode":{
			minlength:1,
			maxlength:10
		},
		"roadTraffic.ownershipUnitName":{
			minlength:1,
			maxlength:60
		},
		"roadTraffic.maintenanceUnitCode":{
			minlength:1,
			maxlength:10
		},
		"roadTraffic.maintenanceUnitName":{
			minlength:1,
			maxlength:60
		},
		"roadTraffic.startDate":{
			isStartDate:true
		},
		"roadTraffic.changeDate":{
			isChangeDate:true
		},
		"roadTraffic.remark":{
			maxlength:300
		}
	},
		messages:{
		"roadTraffic.objNum":{
			required:"请输入部件编号",
			countExsistedRoadTraffic:"该网格下已经存在该编号的部件"
			
		},

		"roadTraffic.objName":{
			required:"请输入部件名称"
		},
		"roadTraffic.deptCode":{
			required:"请输入主管部门代码",
			minlength:$.format("主管部门代码至少需要输入{0}个字符"),
			maxlength:$.format("主管部门代码最多需要输入{0}个字符")
		},
		"roadTraffic.deptName":{
			required:"请输入主管部门名称",
			minlength:$.format("主管部门名称至少需要输入{0}个字符"),
			maxlength:$.format("主管部门名称最多需要输入{0}个字符")
		},
		"roadTraffic.ownershipUnitCode":{
			minlength:$.format("权属单位代码至少需要输入{0}个字符"),
			maxlength:$.format("权属单位代码最多需要输入{0}个字符")
		},
		"roadTraffic.ownershipUnitName":{
			minlength:$.format("权属单位名称至少需要输入{0}个字符"),
			maxlength:$.format("权属单位名称最多需要输入{0}个字符")
		},
		"roadTraffic.maintenanceUnitCode":{
			minlength:$.format("养护单位代码至少需要输入{0}个字符"),
			maxlength:$.format("养护单位代码最多需要输入{0}个字符")
		},
		"roadTraffic.maintenanceUnitName":{
			minlength:$.format("养护单位名称至少需要输入{0}个字符"),
			maxlength:$.format("养护单位名称最多需要输入{0}个字符")
		},
		"roadTraffic.startDate":{
			isStartDate:"初始时间应小于变更时间"
		},
		"roadTraffic.changeDate":{
			isChangeDate:"变更时间应大于初始时间"
		},
		"roadTraffic.dateSource":{
			minlength:$.format("数据来源至少需要输入{0}个字符"),
			maxlength:$.format("数据来源最多需要输入{0}个字符")
		},
		"roadTraffic.description":{
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
		roadTrafficOrgTree=$("#orgName").treeSelect({
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
				$.searchChild(roadTrafficOrgTree,data);
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
