<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>

<div id="dialog-form" title="其他场所信息维护" class="container container_24">

	<s:if test='!"view".equals(mode)'>
	<form id="maintainForm" method="post" action="">
	</s:if>
	<s:if test='"add".equals(mode)'>
		<pop:token/>
	</s:if>
	<input id="mode" name="mode" type="hidden" value="${mode}" />
	<input id="_imgUrl" type="hidden" name="otherLocale.imgUrl" value="${otherLocale.imgUrl}"/>
	<input id="organizationId" name="organization.id" type="hidden" value="${organization.id}" />
	<input id="otherLocaleId" name="otherLocale.id" type="hidden" value="${otherLocale.id}" />
	<input name="isSubmit" id="isSubmit" type="hidden" value="">
		<div>

			<div class="grid_4 lable-right">
			<s:if test='!"view".equals(mode)'><em class="form-req">*</em></s:if>
	  			<label class="form-lbl">所属网格：</label>
	  		</div>
    		<div class="grid_19 form-left">
    			<input type="text" name="orgName" id="orgName" value="" readonly <s:if test='"view".equals(mode)'>disabled</s:if> class="form-txt" style="width: 99%"/>
    		</div>    		
			<div class='clearLine'></div>

			<div class="grid_4 lable-right">
			<s:if test='!"view".equals(mode)'><em class="form-req">*</em></s:if>
				<label class="form-lbl">场所名称：</label>
			</div>
			<div class="grid_19 form-left">
		    	<input type="text" id="otherLocale-name" name="otherLocale.name" maxlength="20"
		    	<s:if test='"view".equals(mode)'>disabled='true'</s:if> value="${otherLocale.name}" class="form-txt" style="width: 99%"/>
			</div>
  			<div class='clearLine'></div>

			<div class="grid_4 lable-right">
				<label class="form-lbl">场所地址：</label>
			</div>
			<div class="grid_19 form-left">
				<input type="text" id="otherLocale.address" name="otherLocale.address" maxlength="50"
				<s:if test='"view".equals(mode)'>disabled='true'</s:if> value="${otherLocale.address}" class="form-txt" style="width: 99%"/>
			</div>
 			<div class='clearLine'></div>

			<div class="grid_4 lable-right">
			<s:if test='!"view".equals(mode)'><em class="form-req">*</em></s:if>
	  			<label class="form-lbl">场所类型：</label>
	  		</div>
			<div class="grid_7 form-left">
				<select id="otherLocale.type.id" name="otherLocale.type.id" class="form-select"
				<s:if test='"view".equals(mode)'>disabled="disabled"</s:if>>
			   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@OTHER_LOCALE_TYPE" defaultValue="${otherLocale.type.id}" />
				</select>
    		</div>
    		
    		<div class="grid_5 lable-right">
				<label class="form-lbl">关注程度：</label>
			</div>
			<div class="grid_7">
				<select name="otherLocale.attentionExtent.id" id="otherLocale-attentionExtent" class="form-txt"
					<s:if test='"view".equals(mode)'>disabled='true'</s:if> >
					<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@ATTENTION_EXTENT" defaultValue="${otherLocale.attentionExtent.id}" />
				</select>
			</div>
			
			<div class="grid_4 lable-right">
				<label class="form-lbl">从业人员数：</label>
			</div>
			<div class="grid_7 form-left">
				<input type="text" id="otherLocale.practitionerNumber" name="otherLocale.practitionerNumber" maxlength="9"
				<s:if test='"view".equals(mode)'>disabled='true'</s:if> value="${otherLocale.practitionerNumber}" class="dialogtip form-txt" />
			</div>
			<div class='clearLine'></div>

			<div class='clearLine'></div>
 			<div class="grid_4 lable-right">
				<label class="form-lbl">联系人：</label>
			</div>
			<div class="grid_7 form-left">
				<input type="text" id="otherLocale.contactPerson" name="otherLocale.contactPerson" maxlength="20"
				<s:if test='"view".equals(mode)'>disabled='true'</s:if> value="${otherLocale.contactPerson}" class="form-txt" />
			</div>
			<div class='clearLine'></div>

			<div class="grid_4 lable-right">
				<label class="form-lbl">联系电话：</label>
			</div>
			<div class="grid_7 form-left">
				<input type="text" id="otherLocale.telephone" name="otherLocale.telephone" maxlength="15"
				<s:if test='"view".equals(mode)'>disabled='true'</s:if> value="${otherLocale.telephone}" class="dialogtip form-txt" title="请输入由数字和-组成的联系电话 例如：0577-88888888" />
			</div>

			<div class="grid_5 lable-right">
				<label class="form-lbl">联系手机：</label>
			</div>
			<div class="grid_7 form-left">
				<input type="text" id="otherLocale.mobileNumber" name="otherLocale.mobileNumber" maxlength="11"
				<s:if test='"view".equals(mode)'>disabled='true'</s:if> value="${otherLocale.mobileNumber}" class="dialogtip form-txt" title="请输入11位以1开头的联系手机 例如：13988888888" />
			</div>
			<div class='clearLine'></div>

			<div class="grid_4 lable-right">
				<label class="form-lbl">备注：</label>
			</div>
			<div class="grid_20 form-left">
    			<textarea id="otherLocale.remark" name="otherLocale.remark"
    		 	<s:if test='"view".equals(mode)'>disabled='true'</s:if> class="form-txt" style="height: 3em;width:94.5%">${otherLocale.remark}</textarea>
			</div>
		</div>
	<s:if test='!"view".equals(mode)'>
	</form>
	</s:if>
</div>
<script type="text/javascript">
var otherLocaleOrgTree="";
function isGridForTreeSelect(){
	if(otherLocaleOrgTree != ""){
		return $.getSelectedNode(otherLocaleOrgTree).attributes.orgLevelInternalId == <s:property value="@com.tianque.domain.property.OrganizationLevel@GRID"/>;
	}else{
		return true;
	}
}
if(null!=$("#_imgUrl").val() && $("#_imgUrl").val()!=""){
	$("#headerImg").attr("src",$("#_imgUrl").val()+"?r="+Math.random());
	$(".shadow").show();
}

var existed = true;
$(document).ready(function(){
	jQuery.validator.addMethod("countExsistedOtherLocale", function(value, element){
		if(value==null||value==undefined||value==""){return true}
		$.ajax({
			async:false,
			url:"${path}/baseinfo/otherLocaleManage/hasDuplicateOtherLocaleName.action",
			data:{
				"otherLocale.organization.id":$("#organizationId").val(),
				"otherLocale.name":$('#otherLocale-name').val(),
				"otherLocale.id":function(){if($("#mode").val() == "add" ){return -1;}else{ return $("#otherLocaleId").val();}}
			},
			success:function(responseData){
				existed = responseData;
			}
		});
		return existed;
	});

	jQuery.validator.addMethod("isGridOrganization", function(value, element){
		if(value==null||value==undefined||value==""){return true}
		if(isGridForTreeSelect()){
			return true;
		}else{
			return false;
		}
	});

	if(""!=$("#_imgUrl").val() && typeof($("#_imgUrl").val())!="undefined"){
		$("#img").attr("src","${path }/"+$("#_imgUrl").val());
	};

<s:if test='!"view".equals(mode)'>
	$("#maintainForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form){
			$("#_imgUrl").val($("#imgUrl").val());
			$(form).ajaxSubmit({
				async : false,
				success:function(data){
				    if(!data.id){
               	 		$.messageBox({
							level: "error"
				 		});
                		return;
               		}  
					//data["organization.orgName"]=data.organization.orgName;
					<s:if test='"add".equals(mode)'>
						<s:if test='#parameters.dialog != null'>
							//提示
							$("#tagSerch").val($('#otherLocale-name').val());
        					$("#searchPositiveinfo").click();
        					$("#otherLocaleList").addRowData(data.id,data,"first");
							$.messageBox({message:"已经成功保存该其他场所信息!"});
							$("#<s:property value='#parameters.dialog'/>").dialog("close");
						</s:if>
						<s:else>
							$("#otherLocaleList").addRowData(data.id,data,"first");
	    					$.messageBox({message:"已经成功保存该其他场所对象信息!"});
							if($("#isSubmit").val()=="true"){
								$("#otherLocaleDialog").dialog("close");
							}else{
								emptyObject();
							}
	    					$("#otherLocaleList").setSelection(data.id);
	    					$("#otherLocaleList").trigger("reloadGrid");
						</s:else>
		     		</s:if>
		     		<s:if test='"edit".equals(mode)'>
				    	$("#otherLocaleList").setRowData(data.id,data);
				    		if(data.isEmphasis==1){
								 $("#"+data.id+"").css('color','#778899');
							}
						$.messageBox({message:"已经成功保存该其他场所信息!"});
						$("#otherLocaleList").setSelection(data.id);
						$("#otherLocaleList").trigger("reloadGrid");
				    </s:if>
					 doLocationAction("<s:property value='#parameters.dailogName[0]'/>",data.id,null,data.name);
				},
				error:function(XMLHttpRequest, textStatus, errorThrown){
      	      		alert("提交数据时发生错误");
   	   		    }
			});
		},
		rules:{
			"otherLocale.name":{
				required:true,
				exculdeParticalChar:true,
				minlength:2,
				countExsistedOtherLocale:true
			},
			"otherLocale.contactPerson":{
				minlength:2,
				exculdeParticalChar:true
			},
			"otherLocale.practitionerNumber":{
				positiveInteger:true,
				maxlength:9
			},
			"otherLocale.telephone":{
				telephone:true
			},
			"otherLocale.mobileNumber":{
				mobile:true
			},
			"otherLocale.remark":{
				maxlength:200
			},
			"orgName":{
				isGridOrganization:true
			},
			"otherLocale.type.id":{
				required:true
			}
		},
		messages:{
			"otherLocale.name":{
				required:"请输入名称",
				exculdeParticalChar:"名称只能输入字母，数字和中文字符",
				minlength:$.format("名称至少需要输入{0}个字符"),
				countExsistedOtherLocale:"该网格下已存在该名称场所"
			},
			"otherLocale.contactPerson":{
				minlength:$.format("联系人至少需要输入{0}个字符"),
				exculdeParticalChar:"联系人只能输入字母，数字和中文字符"
			},
			"otherLocale.practitionerNumber":{
				positiveInteger:"请输入正整数",
				maxlength:$.format("从业人员数不能大于{0}")
			},
			"otherLocale.telephone":{
				telephone:$.format("联系电话只能输数字和横杠(-)")
			},
			"otherLocale.mobileNumber":{
				mobile:$.format("手机号码输入只能是以1开头的11位数字")
			},
			"otherLocale.remark":{
				maxlength:$.format("备注最多只能输入{0}个字符")
			},
			"orgName":{
				isGridOrganization:"网格只能属于片组片格"
			},
			"otherLocale.type.id":{
				required:"请选择场所类型"
			}
		}
	});

	<s:if test='"add".equals(mode)'>
		$("#maintainForm").attr("action","${path}/baseinfo/otherLocaleManage/addOtherLocale.action");
	</s:if>
	<s:else>
		$("#maintainForm").attr("action","${path}/baseinfo/otherLocaleManage/updateOtherLocale.action");
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
	$("#otherLocaleId").val("");
	$("#otherLocale-name").val("");
	$("#otherLocale\\.address").val("");
	$("#otherLocale\\.type\\.id").val("");
	$("#otherLocale\\.contactPerson").val("");
	$("#otherLocale\\.telephone").val("");
	$("#otherLocale\\.mobileNumber").val("");
	$("#otherLocale\\.remark").val("");
}
</script>