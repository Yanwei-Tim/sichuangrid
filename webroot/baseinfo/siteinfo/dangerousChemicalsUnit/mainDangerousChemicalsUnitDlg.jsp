<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
	<div id="dangerousChemicalsUnit" class="container container_24">
		<form action="${path}/baseinfo/dangerousChemicalsUnitManage/addDangerousChemicalsUnit.action" method="post" id="maintainForm">
		<div id="perUuid"></div>
		<s:if test='"add".equals(mode)'>
			<pop:token/>
		</s:if>
		<input id="_imgUrl" type="hidden" name="dangerousChemicalsUnit.imgUrl" value="${dangerousChemicalsUnit.imgUrl}" />
		<input type="hidden" name="mode" id="mode" value="${mode}" />
		<input type="hidden" name="modeType" id="modeType" value="${modeType}" />
		<input type="hidden" name="dangerousChemicalsUnit.id" id="dangerousChemicalsUnit-id" value="${dangerousChemicalsUnit.id}" />
		<input type="hidden" id="houseInfoBuildingId" name="dangerousChemicalsUnit.gisInfo.buildingId" value="${dangerousChemicalsUnit.gisInfo.buildingId}" />
		<input type="hidden" id="houseInfoCenterX" name="dangerousChemicalsUnit.gisInfo.centerX" value="${dangerousChemicalsUnit.gisInfo.centerX}" />
		<input type="hidden" id="houseInfoCenterY" name="dangerousChemicalsUnit.gisInfo.centerY" value="${dangerousChemicalsUnit.gisInfo.centerY}" />
		<input type="hidden" name="organizationId" id="organizationId" value="${orgId}" />
		<input type="hidden" name="dangerousChemicalsUnit.organization.id" id="dangerChemUnitOrganizationId" value="${dangerousChemicalsUnit.organization.id}" />
		<input id="keyType" type="hidden" name="" value="${keyType}" />
		<input id="personTypeName" type="hidden" name="personTypeName" value="${personTypeName}" />
		<div class="grid_4 lable-right">
		<em class="form-req">*</em>
			<label class="form-lbl">所属网格：</label>
		</div>
		<div class="grid_18">
			<input type="text"  name="dangerousChemicalsUnit.organization.orgName" id="orgName"  readonly class="form-txt" value="${ dangerousChemicalsUnit.organization.orgName}"/>
		</div>
		<div class='clearLine'></div>
		<div class="grid_4 lable-right">
		<em class="form-req">*</em>
			<label class="form-lbl">单位名称：</label>
		</div>
		<div class="grid_18">
			<input type="text" id="unitName" name="dangerousChemicalsUnit.unitName" class="form-txt" value="${dangerousChemicalsUnit.unitName}" maxlength="20"
			/>
		</div>
		<div class='clearLine'></div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">单位地址：</label>
		</div>
		<div class="grid_18">
			<input type="text" name="dangerousChemicalsUnit.unitAddress" class="form-txt" value="${dangerousChemicalsUnit.unitAddress}" maxlength="50"
			/>
		</div>
		<div class="clearLine"></div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">关注程度：</label>
		</div>
		<div class="grid_7">
			<select name="dangerousChemicalsUnit.attentionExtent.id" id="dangerousChemicalsUnit-attentionExtent" class="form-txt"
				<s:if test='"view".equals(mode)'>disabled='true'</s:if> >
				<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@ATTENTION_EXTENT" defaultValue="${dangerousChemicalsUnit.attentionExtent.id}" />
			</select>
		</div>
		<div class="grid_4 lable-right" style="width:93px;">
            <label class="form-lbl">负责人：</label>
        </div>
        <div class="grid_7">
            <input type="text" name="dangerousChemicalsUnit.superintendent" id="superintendent" class="form-txt" value="${dangerousChemicalsUnit.superintendent}"
             maxlength="20"/>
        </div>
        <div class="clearLine">&nbsp;</div>
        <div class="grid_4 lable-right">
            <label class="form-lbl">联系电话：</label>
        </div>
        <div class="grid_7">
            <input type="text" name="dangerousChemicalsUnit.telephone" id="telephone" class="form-txt" value="${dangerousChemicalsUnit.telephone}" title="请输入由数字和-组成的联系电话 例如：0577-88888888"
            maxlength="20"/>
        </div>
		<div class="grid_4 lable-right" style="width:93px;">
            <label class="form-lbl">单位类别：</label>
        </div>
        <div class="grid_7">
            <input type="text" name="dangerousChemicalsUnit.unitType" id="unitType" class="form-txt" value="${dangerousChemicalsUnit.unitType}"
             maxlength="50"/>
        </div>
        <div class="grid_4 lable-right">
            <label class="form-lbl">货物类别：</label>
        </div>
        <div class="grid_18">
            <input type="text" name="dangerousChemicalsUnit.commodityType" id="commodityType" class="form-txt" value="${dangerousChemicalsUnit.commodityType}"
            maxlength="50"/>
        </div>
        <div class="clearLine">&nbsp;</div>
        <div class="grid_4 lable-right">
			<label class="form-lbl">副本许可范围：</label>
		</div>
		<div class="grid_18">
			<input type="text" name="dangerousChemicalsUnit.copyScope" class="form-txt" value="${dangerousChemicalsUnit.copyScope}" maxlength="50"
			/>
		</div>
         <div class="clearLine">&nbsp;</div>
        <div class="grid_4 lable-right">
			<label class="form-lbl">存储设备：</label>
		</div>
		<div class="grid_18">
			<input type="text" name="dangerousChemicalsUnit.storageDevice" class="form-txt" value="${dangerousChemicalsUnit.storageDevice}" maxlength="50"
			/>
		</div>
        <div class="clearLine"></div>
		<div class='clearLine'></div>
		<div class="grid_4 lable-right">
		   <label class="form-lbl">备注：</label>
		</div>
		<div class="grid_18">
			<textarea rows="3" cols="" id="dangerousChemicalsUnit-remark" name="dangerousChemicalsUnit.remark" class="form-txt" >${dangerousChemicalsUnit.remark}</textarea>
		</div>

		<div class='clearLine'>&nbsp;</div>
		<input name="isSubmit" id="isSubmit" type="hidden" value="" />
			</form>
	 </div>
<script>
if(null!=$("#_imgUrl").val() && $("#_imgUrl").val()!=""){
	$("#headerImg").attr("src",$("#_imgUrl").val()+"?r="+Math.random());
	$(".shadow").show();
}
$(document).ready(function(){
	if(""!=$("#_imgUrl").val()){
		$("#img").attr("src",$("#_imgUrl").val());
	}
	$('#logOutTime').datePicker({
		yearRange:'1900:2030',
		dateFormat:'yy-mm-dd',
        maxDate:'+0d'
	});
function changeOrgId(){
	$("#organizationId").val($("#dangerChemUnitOrganizationId").val());
}
var dangerousChemicalsUnitOrgTree="";
function isGridForTreeSelect(){
	if(dangerousChemicalsUnitOrgTree != ""){
		return $.getSelectedNode(dangerousChemicalsUnitOrgTree).attributes.orgLevelInternalId == <s:property value="@com.tianque.domain.property.OrganizationLevel@GRID"/>;
	}else{
		return true;
	}
}
var existed = true;
var bol = true;

	$("#tabs").tabFunction({ cache: true });
	<s:if test='"add".equals(mode)'>
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
	</s:if>

	$(".dialogtip").inputtip();
	$("#maintainForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form){
		$("#_imgUrl").attr("src",$("#_imgUrl").val());
			$(form).ajaxSubmit({
				async : false,
				success:function(data){
					if(!data.id){
           	 			$.messageBox({
							message:data,
							evel: "error"
			 			});
            			return;
					}
					 $("#<s:property value='#parameters.dailogName[0]'/>").proccessSuccess(data.id,data);	
				},
				error:function(XMLHttpRequest, textStatus, errorThrown){
  	      			alert("提交数据时发生错误");
	   		    }
			});
		},
		rules:{
		"dangerousChemicalsUnit.unitName":{
			required:true,
			exculdeParticalChar:true,
			minlength:1,
			maxlength:20
		},
		"dangerousChemicalsUnit.unitAddress":{
			minlength:1,
			maxlength:50
		},
		"dangerousChemicalsUnit.superintendent":{
			exculdeParticalChar:true,
			minlength:1,
			maxlength:50
		},
		"dangerousChemicalsUnit.telephone":{
			telephone:true
		},
		"dangerousChemicalsUnit.unitType":{
			exculdeParticalChar:true,
			maxlength:50
		},
		"dangerousChemicalsUnit.commodityType":{
			exculdeParticalChar:true,
			maxlength:50
		},
		"dangerousChemicalsUnit.copyScope":{
			maxlength:50
		},
		"dangerousChemicalsUnit.storageDevice":{
			maxlength:50
		},
		"dangerousChemicalsUnit.logOutReason":{
			maxlength:20
		},
		"dangerousChemicalsUnit.remark":{
			maxlength:300
	   }
	},
		messages:{
		"dangerousChemicalsUnit.unitName":{
			required:"请输入单位名称",
			exculdeParticalChar:"不能输入非法字符",
			minlength:$.format("单位名称至少需要输入{0}个字符"),
			maxlength:$.format("单位名称最多需要输入{0}个字符")
		},

		"dangerousChemicalsUnit.unitAddress":{
			minlength:$.format("单位地址至少需要输入{0}个字符"),
			maxlength:$.format("单位地址最多需要输入{0}个字符")
		},
		"dangerousChemicalsUnit.superintendent":{
			exculdeParticalChar:"不能输入非法字符",
			minlength:$.format("负责人至少需要输入{0}个字符"),
			maxlength:$.format("负责人最多需要输入{0}个字符")
		},
		"dangerousChemicalsUnit.telephone":{
			telephone:$.format("固定电话不合法，只能输数字和横杠(-)")
		},
		"dangerousChemicalsUnit.unitType":{
			exculdeParticalChar:"不能输入非法字符",
			maxlength:$.format("单位类别最多需要输入{0}个字符")
		},
		"dangerousChemicalsUnit.commodityType":{
			exculdeParticalChar:"不能输入非法字符",
			maxlength:$.format("货物类别最多需要输入{0}个字符")
		},
		"dangerousChemicalsUnit.copyScope":{
			maxlength:$.format("副本许可范围最多需要输入{0}个字符")
		},
		"dangerousChemicalsUnit.storageDevice":{
			maxlength:$.format("存储设备最多需要输入{0}个字符")
		},
		"dangerousChemicalsUnit.logOutReason":{
			maxlength:$.format("注销原因最多需要输入{0}个字符")
		},
		"dangerousChemicalsUnit.remark":{
			maxlength:$.format("备注最多需要输入{0}个字符")
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
		dangerousChemicalsUnitOrgTree=$("#orgName").treeSelect({
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
				$.searchChild(dangerousChemicalsUnitOrgTree,data);
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
