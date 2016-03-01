<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>

<div id="dialog-form" title="建筑施工单位信息维护" class="container container_24">

<s:if test='!"view".equals(mode)'>
	<form id="maintainForm" method="post" action="">
</s:if> 
	<input id="mode" name="mode" type="hidden" value="${mode}" /> 
	<input	id="organizationId" name="organization.id" type="hidden"	value="${organization.id}" /> 
	<input id="constructionUnitId"	name="constructionUnit.id" type="hidden" value="${constructionUnit.id}" />
	<input name="isSubmit" id="isSubmit" type="hidden" value="">
	
	<div>

		<div class="grid_6 lable-right"><label class="form-lbl">所属网格：</label>
		</div>
		<div class="grid_16 form-left"><input type="text" name="orgName"
			id="orgName" value="" readonly
			<s:if test='"view".equals(mode)'>disabled</s:if> class="form-txt" /></div>
		<div class="grid_1"><s:if test='!"view".equals(mode)'>
			<em class="form-req">*</em>
		</s:if></div>
		<div class='clearLine'></div>
		
		<div class="grid_6 lable-right"><label class="form-lbl">工程名称：</label>
		</div>
		<div class="grid_5 form-left"><input type="text"
			id="constructionUnit-projectName" name="constructionUnit.projectName"
			maxlength="30" <s:if test='"view".equals(mode)'>disabled='true'</s:if>
			value="${constructionUnit.projectName}" class="form-txt" /></div>
		<div class="grid_1"><s:if test='!"view".equals(mode)'>
			<em class="form-req">*</em>
		</s:if></div>
		
		<div class="grid_5 lable-right"><label class="form-lbl">工程地点：</label>
		</div>
		<div class="grid_5 form-left"><input type="text"
			id="constructionUnit.projectAddress"
			name="constructionUnit.projectAddress" maxlength="30"
			<s:if test='"view".equals(mode)'>disabled='true'</s:if>
			value="${constructionUnit.projectAddress}" class="form-txt" /></div>
		<div class="grid_1"><s:if test='!"view".equals(mode)'>
			<em class="form-req">*</em>
		</s:if></div>
		
		<div class='clearLine'></div>
		
		<div class="grid_6 lable-right"><label class="form-lbl">开发单位：</label>
		</div>
		<div class="grid_5 form-left"><input type="text"
			id="constructionUnit.developmentUnit"
			name="constructionUnit.developmentUnit" maxlength="30"
			<s:if test='"view".equals(mode)'>disabled='true'</s:if>
			value="${constructionUnit.developmentUnit}" class="form-txt" /></div>
		<div class="grid_1"><s:if test='!"view".equals(mode)'>
			<em class="form-req">*</em>
		</s:if></div>
		
		
		<div class="grid_5 lable-right"><label class="form-lbl">开发单位联系人：</label>
		</div>
		<div class="grid_5 form-left"><input type="text"
			id="constructionUnit.developmentContactPerson"
			name="constructionUnit.developmentContactPerson" maxlength="15"
			<s:if test='"view".equals(mode)'>disabled='true'</s:if>
			value="${constructionUnit.developmentContactPerson}" class="form-txt" />
		</div>
		<div class="grid_1"><s:if test='!"view".equals(mode)'>
			<em class="form-req">*</em>
		</s:if></div>
		
		<div class='clearLine'></div>
		
		<div class="grid_6 lable-right"><label class="form-lbl">开发单位联系人手机：</label>
		</div>
		
		<div class="grid_5 form-left"><input type="text"
			id="constructionUnit.developmentContactPersonPhone"
			name="constructionUnit.developmentContactPersonPhone" maxlength="11"
			<s:if test='"view".equals(mode)'>disabled='true'</s:if>
			value="${constructionUnit.developmentContactPersonPhone}"
			class="dialogtip form-txt" title="请输入11位以1开头的联系手机 例如：13988888888" /></div>
		<div class="grid_1"><s:if test='!"view".equals(mode)'>
			<em class="form-req">*</em>
		</s:if></div>
		
		<div class='clearLine'></div>
		
		<div class="grid_6 lable-right"><label class="form-lbl">总承包方：</label>
		</div>
		<div class="grid_5 form-left"><input type="text"
			id="constructionUnit.contractor" name="constructionUnit.contractor"
			maxlength="30" <s:if test='"view".equals(mode)'>disabled='true'</s:if>
			value="${constructionUnit.contractor}" class="form-txt" /></div>
		
		<div class="grid_6 lable-right"><label class="form-lbl">总承包方联系人：</label>
		</div>
		<div class="grid_5 form-left"><input type="text"
			id="constructionUnit.contractorContactPerson"
			name="constructionUnit.contractorContactPerson" maxlength="15"
			<s:if test='"view".equals(mode)'>disabled='true'</s:if>
			value="${constructionUnit.contractorContactPerson}" class="form-txt" />
		</div>
		
		<div class='clearLine'></div>
		
		<div class="grid_6 lable-right"><label class="form-lbl">总承包方联系人手机：</label>
		</div>
		
		<div class="grid_5 form-left"><input type="text"
			id="constructionUnit.contractorContactPersonPhone"
			name="constructionUnit.contractorContactPersonPhone" maxlength="11"
			<s:if test='"view".equals(mode)'>disabled='true'</s:if>
			value="${constructionUnit.contractorContactPersonPhone}"
			class="dialogtip form-txt" title="请输入11位以1开头的联系手机 例如：13988888888" /></div>
		
		<div class='clearLine'></div>
		
		<div class="grid_6 lable-right"><label class="form-lbl">劳务方：</label>
		</div>
		<div class="grid_5 form-left"><input type="text"
			id="constructionUnit.laborer" name="constructionUnit.laborer"
			maxlength="30" <s:if test='"view".equals(mode)'>disabled='true'</s:if>
			value="${constructionUnit.laborer}" class="form-txt" /></div>
		
		
		<div class="grid_6 lable-right"><label class="form-lbl">劳务方联系人：</label>
		</div>
		<div class="grid_5 form-left"><input type="text"
			id="constructionUnit.laborerContactPerson"
			name="constructionUnit.laborerContactPerson" maxlength="15"
			<s:if test='"view".equals(mode)'>disabled='true'</s:if>
			value="${constructionUnit.laborerContactPerson}" class="form-txt" /></div>
		
		<div class='clearLine'></div>
		
		<div class="grid_6 lable-right"><label class="form-lbl">劳务方联系人手机：</label>
		</div>
		
		<div class="grid_5 form-left"><input type="text"
			id="constructionUnit.laborerContactPersonPhone"
			name="constructionUnit.laborerContactPersonPhone" maxlength="11"
			<s:if test='"view".equals(mode)'>disabled='true'</s:if>
			value="${constructionUnit.laborerContactPersonPhone}"
			class="dialogtip form-txt" title="请输入11位以1开头的联系手机 例如：13988888888" /></div>
		
		<div class='clearLine'></div>
		
		<div class="grid_6 lable-right"><label class="form-lbl">
		工资支付日期：</label></div>
		
		<div class="grid_5 form-left"><input type="text"
			id="constructionUnit.salaryPayDate"
			name="constructionUnit.salaryPayDate" maxlength="15"
			<s:if test='"view".equals(mode)'>disabled='true'</s:if>
			value='<s:date name="constructionUnit.salaryPayDate" format="yyyy-MM-dd" />'
			class="form-txt" /></div>
		
		<div class="grid_6 lable-right"><label class="form-lbl">
		实际发放工资总额：</label></div>
		
		<div class="grid_5 form-left"><input type="text"
			id="constructionUnit.actualTotalSalary"
			name="constructionUnit.actualTotalSalary" maxlength="15"
			<s:if test='"view".equals(mode)'>disabled='true'</s:if>
			value="${constructionUnit.actualTotalSalary}" class="form-txt" /></div>
		
		<div class='clearLine'></div>
		
		<div class="grid_6 lable-right"><label class="form-lbl">
		应签订劳动合同人数：</label></div>
		
		<div class="grid_5 form-left"><input type="text"
			id="constructionUnit.shouldSignContractNumber"
			name="constructionUnit.shouldSignContractNumber" maxlength="15"
			<s:if test='"view".equals(mode)'>disabled='true'</s:if>
			value="${constructionUnit.shouldSignContractNumber}" class="form-txt" />
		</div>
		
		<div class="grid_6 lable-right"><label class="form-lbl">
		未签订劳动合同人数：</label></div>
		<div class="grid_5 form-left"><input type="text"
			id="constructionUnit.notSignContractNumber"
			name="constructionUnit.notSignContractNumber" maxlength="15"
			<s:if test='"view".equals(mode)'>disabled='true'</s:if>
			value="${constructionUnit.notSignContractNumber}" class="form-txt" />
		</div>
		
		
		<div class='clearLine'></div>
		
		
		<div class="grid_6 lable-right"><label class="form-lbl">
		从业人数：</label></div>
		
		<div class="grid_5 form-left"><input type="text"
			id="constructionUnit.workersNumber"
			name="constructionUnit.workersNumber" maxlength="15"
			<s:if test='"view".equals(mode)'>disabled='true'</s:if>
			value="${constructionUnit.workersNumber}" class="form-txt" /></div>
		
		
		
		<div class="grid_6 lable-right"><label class="form-lbl">
		开工时间：</label></div>
		
		<div class="grid_5 form-left"><input type="text"
			id="constructionUnit.startTime" name="constructionUnit.startTime"
			maxlength="15" <s:if test='"view".equals(mode)'>disabled='true'</s:if>
			value='<s:date name="constructionUnit.startTime" format="yyyy-MM-dd" />'
			class="form-txt" /></div>
		
		<div class='clearLine'></div>
		
		<div class="grid_6 lable-right"><label class="form-lbl">备注：</label>
		</div>
		<div class="grid_11 form-left"><textarea
			id="constructionUnit.remark" name="constructionUnit.remark"
			<s:if test='"view".equals(mode)'>disabled='true'</s:if>
			class="form-txt" style="height: 3em; width: 30em">${constructionUnit.remark}</textarea>
		</div>
		
	</div>
	
	<s:if test='!"view".equals(mode)'>
		</form>
	</s:if>
</div>
<script type="text/javascript">
var constructionUnitOrgTree="";
function isGridForTreeSelect(){
	if(constructionUnitOrgTree != ""){
		return $.getSelectedNode(constructionUnitOrgTree).attributes.orgLevelInternalId == <s:property value="@com.tianque.domain.property.OrganizationLevel@GRID"/>;
	}else{
		return true;
	}
}
var existed = false;
$(document).ready(function(){
	//验证是否有同名重复对象
	jQuery.validator.addMethod("countExsistedConstructionUnitProjectName", function(value, element){
		if(value==null||value==undefined||value==""){return true}
		$.ajax({
			async:false,
			url:"${path}/baseinfo/constructionUnitManage/hasDuplicateProjectName.action",
			data:{
				"constructionUnit.organization.id":$("#organizationId").val(),
				"constructionUnit.projectName":$('#constructionUnit-projectName').val(),
				"constructionUnit.id":function(){if($("#mode").val() == "add" ){return -1;}else{ return $("#constructionUnitId").val();}}
			},
			success:function(responseData){
				//alert(responseData);
				existed = responseData;
			}
		});
		return existed;
	});
	//验证当前所选择的组织是否为网格
	jQuery.validator.addMethod("isGridOrganization", function(value, element){
		if(value==null||value==undefined||value==""){return true}
		if(isGridForTreeSelect()){
			return true;
		}else{
			return false;
		}
	});

	//提交操作开始
<s:if test='!"view".equals(mode)'>
	
	$("#maintainForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form){
			$(form).ajaxSubmit({
				success:function(data){
					if(!data.id){
               	 		$.messageBox({
							message:data,
							level: "error"
				 		});
                		return;
               		}
					data["organization.orgName"]=data.organization.orgName;
					<s:if test='"add".equals(mode)'>
						<s:if test='#parameters.dialog != null'>
							//提示
							$("#tagSerch").val($('#constructionUnit-projectName').val());
        					$("#searchPositiveinfo").click();
							$.messageBox({message:"已经成功保存该建筑施工单位信息!"});
							$("#<s:property value='#parameters.dialog'/>").dialog("close");
						</s:if>
						<s:else>
							$("#constructionUnitList").addRowData(data.id,data,"first");
	    					$.messageBox({message:"已经成功保存该建筑施工单位信息!"});
							if($("#isSubmit").val()=="true"){
								$("#constructionUnitDialog").dialog("close");
							}else{
								emptyObject();
							}	
	    					$("#constructionUnitList").setSelection(data.id);
	    					checkExport();
						</s:else>
		     		</s:if>
		     		<s:if test='"edit".equals(mode)'>
				    	$("#constructionUnitList").setRowData(data.id,data);
				    		if(data.isEmphasis==1){
								 $("#"+data.id+"").css('color','#778899');
							}
						$.messageBox({message:"已经成功保存该建筑施工单位信息!"});
						$("#constructionUnitDialog").dialog("close");
						$("#constructionUnitList").setSelection(data.id);
						checkExport();
				    </s:if>
				},
				error:function(XMLHttpRequest, textStatus, errorThrown){
      	      		alert("提交数据时发生错误");
   	   		    }
			});
		},
		rules:{
			"constructionUnit.projectName":{
				required:true,
				minlength:2,
				countExsistedConstructionUnitProjectName:true
			},
			"constructionUnit.projectAddress":{
				required:true,
				minlength:2
			
			},
			"orgName":{
				isGridOrganization:true
			},
			"constructionUnit.developmentUnit":{
				required:true,
				minlength:2
			},
			"constructionUnit.developmentContactPerson":{
				required:true,
				minlength:2
			},
			
			"constructionUnit.developmentContactPersonPhone":{
				required:true,
				mobile:true
			},

			"constructionUnit.remark":{
				maxlength:300
			}		
		},
		messages:{
			"constructionUnit.projectName":{
				required:"请输入工程名称",
				minlength:$.format("工程名称至少需要输入{0}个字符"),
				countExsistedConstructionUnitProjectName:"该网格下已存在该工程名称的建筑施工单位"
			},
			"constructionUnit.projectAddress":{
				required:"请输入工程地点",
				minlength:$.format("工程地点至少需要输入{0}个字符")
			},
			"orgName":{
				isGridOrganization:"网格只能属于片组片格"
			},
			"constructionUnit.developmentUnit":{
				required:"请输入开发单位"
				
			},

			"constructionUnit.developmentContactPerson":{
				required:"请输入开发单位联系人"
				
			},
			
			"constructionUnit.developmentContactPersonPhone":{
				required:"请输入开发单位联系人手机",
				mobile:$.format("手机号码输入只能是以1开头的11位数字")
			},

			"constructionUnit.remark":{
				maxlength:$.format("备注最多只能输入{0}个字符")
			}
		}
	});
	//提交操作结束
	
	<s:if test='"add".equals(mode)'>
		$("#maintainForm").attr("action","${path}/baseinfo/constructionUnitManage/addConstructionUnit.action");
	</s:if>
	<s:else>
		$("#maintainForm").attr("action","${path}/baseinfo/constructionUnitManage/updateConstructionUnit.action");
	</s:else>
	</s:if>
	
	$.ajax({
		async: false,
		url: "${path}/sysadmin/orgManage/getOrgRelativePath.action",
		data:{
			"organization.id":$("#organizationId").val()
		},
		success:function(responseData){
			
			$("#orgName").val(responseData);
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
	otherLocaleOrgTree = $("#orgName").treeSelect({
		url:"/sysadmin/orgManage/loadTownForExtTree.action",
		inputName:"organization.id"
	});
	//$.setTreeValue($("#orgId").val(),tree); 
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
			$.searchChild(otherLocaleOrgTree,data);
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

function emptyObject(){
	$("#constructionUnitId").val("");
	$("#constructionUnit-projectName").val("");
	$("#constructionUnit\\.projectAddress").val("");
	$("#constructionUnit\\.developmentUnit").val("");
	$("#constructionUnit\\.developmentContactPerson").val("");
	$("#constructionUnit\\.developmentContactPersonPhone").val("");
	$("#constructionUnit\\.contractor").val("");
	$("#constructionUnit\\.contractorContactPerson").val("");
	$("#constructionUnit\\.contractorContactPersonPhone").val("");
	$("#constructionUnit\\.laborer").val("");
	$("#constructionUnit\\.laborerContactPerson").val("");
	$("#constructionUnit\\.laborerContactPersonPhone").val("");
	$("#constructionUnit\\.salaryPayDate").val("");
	$("#constructionUnit\\.actualTotalSalary").val("");
	$("#constructionUnit\\.").val("");
	$("#constructionUnit\\.shouldSignContractNumber").val("");
	$("#constructionUnit\\.notSignContractNumber").val("");
	$("#constructionUnit\\.workersNumber").val("");
	$("#constructionUnit\\.startTime").val("");
	$("#constructionUnit\\.remark").val("");
}

$('#constructionUnit\\.startTime').datePicker({
	yearRange: '1900:2060',
	dateFormat:'yy-mm-dd',
	maxDate:'+0y'
});

$('#constructionUnit\\.salaryPayDate').datePicker({
	yearRange: '1900:2060',
	dateFormat:'yy-mm-dd',
	maxDate:'+0y'
});


</script>