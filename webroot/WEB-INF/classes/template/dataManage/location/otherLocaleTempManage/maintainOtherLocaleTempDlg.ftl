<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>

<div id="dialog-form" title="其他场所信息维护" class="container container_24">
	<input id="mode" name="mode" type="hidden" value="${(mode)!}" />
	<input id="_imgUrl" type="hidden" name="location.imgUrl" value="${(location.imgUrl)!}"/>
	<input id="organizationId" name="location.organization.id" type="hidden" value="${(location.organization.id)!}" />
	<input id="otherLocaleId" name="location.id" type="hidden" value="${(location.id)!}" />
	<input name="isSubmit" id="isSubmit" type="hidden" value="">
		<div>

			<div class="grid_4 lable-right">
	  			<label class="form-lbl">所属网格：</label>
	  		</div>
    		<div class="grid_19 form-left">
    			<input type="text" name="orgName" id="orgName" value="" readonly <@s.if test='"view".equals(mode)'>disabled</@s.if>  class="form-txt" style="width: 99%"/>
    		</div>    		
			<div class='clearLine'></div>

			<div class="grid_4 lable-right">
			<@s.if test='!"view".equals(mode)'><em class="form-req">*</em></@s.if>
				<label class="form-lbl">场所名称：</label>
			</div>
			<div class="grid_19 form-left">
		    	<input type="text" id="location.name" name="location.name" maxlength="20"
		    	<@s.if test='"view".equals(mode)'>disabled='true'</@s.if> value="${(location.name)!}" class="form-txt" style="width: 99%"/>
			</div>
  			<div class='clearLine'></div>

			<div class="grid_4 lable-right">
				<label class="form-lbl">场所地址：</label>
			</div>
			<div class="grid_19 form-left">
				<input type="text" id="location.address" name="location.address" maxlength="50"
				<@s.if test='"view".equals(mode)'>disabled='true'</@s.if> value="${(location.address)!}" class="form-txt" style="width: 99%"/>
			</div>
 			<div class='clearLine'></div>

			<div class="grid_4 lable-right">
			<@s.if test='!"view".equals(mode)'><em class="form-req">*</em></@s.if>
	  			<label class="form-lbl">场所类型：</label>
	  		</div>
			<div class="grid_7 form-left">
				<select id="location.type.id" name="location.type.id" class="form-select" class="form-txt"
				<@s.if test='"view".equals(mode)'>disabled="disabled"</@s.if>>
			   		<@pop.OptionTag name="@com.tianque.domain.property.PropertyTypes@OTHER_LOCALE_TYPE" defaultValue="${(location.type.id)!}" />
				</select>
    		</div>
    		
    		<div class="grid_5 lable-right">
				<label class="form-lbl">关注程度：</label>
			</div>
			<div class="grid_7">
				<select name="location.attentionExtent.id" id="otherLocale-attentionExtent" class="form-txt"
					<@s.if test='"view".equals(mode)'>disabled='true'</@s.if> >
					<@pop.OptionTag name="@com.tianque.domain.property.PropertyTypes@ATTENTION_EXTENT" defaultValue="${(location.attentionExtent.id)!}" />
				</select>
			</div>
			<div class='clearLine'></div>
			<div class="grid_4 lable-right">
				<label class="form-lbl">从业人员数：</label>
			</div>
			<div class="grid_7 form-left">
				<input type="text" id="location.practitionerNumber" name="location.practitionerNumber" maxlength="9"
				<@s.if test='"view".equals(mode)'>disabled='true'</@s.if> value="${(location.practitionerNumber)!}" 
				class="form-txt" />
			</div>
 			<div class="grid_5 lable-right">
				<label class="form-lbl">联系人：</label>
			</div>
			<div class="grid_7 form-left">
				<input type="text" id="location.manager" name="location.manager" maxlength="20"
				<@s.if test='"view".equals(mode)'>disabled='true'</@s.if> value="${(location.manager)!}" 
				class="form-txt" />
			</div>
			<div class='clearLine'></div>

			<div class="grid_4 lable-right">
				<label class="form-lbl">联系电话：</label>
			</div>
			<div class="grid_7 form-left">
				<input type="text" id="location.telephone" name="location.telephone" maxlength="15"
				<@s.if test='"view".equals(mode)'>disabled='true'</@s.if> value="${(location.telephone)!}" class="dialogtip form-txt" title="请输入由数字和-组成的联系电话 例如：0577-88888888" />
			</div>

			<div class="grid_5 lable-right">
				<label class="form-lbl">联系手机：</label>
			</div>
			<div class="grid_7 form-left">
				<input type="text" id="location.mobileNumber" name="location.mobileNumber" maxlength="11"
				<@s.if test='"view".equals(mode)'>disabled='true'</@s.if> value="${(location.mobileNumber)!}" class="form-txt" title="请输入11位以1开头的联系手机 例如：13988888888" />
			</div>
			<div class='clearLine'></div>

			<div class="grid_4 lable-right">
				<label class="form-lbl">备注：</label>
			</div>
			<div class="grid_20 form-left">
    			<textarea id="location.remark" name="location.remark"
    		 	<@s.if test='"view".equals(mode)'>disabled='true'</@s.if> maxlength="200" class="form-txt" style="height: 3em;width:94.5%">${(location.remark)!}</textarea>
			</div>
		</div>
	
</div>
<script type="text/javascript">
var otherLocaleOrgTree="";
function isGridForTreeSelect(){
	if(otherLocaleOrgTree != ""){
		return $.getSelectedNode(otherLocaleOrgTree).attributes.orgLevelInternalId == <@s.property value="@com.tianque.domain.property.OrganizationLevel@GRID"/>;
	}else{
		return true;
	}
}
if(null!=$("#_imgUrl").val() && $("#_imgUrl").val()!=""){
	$("#headerImg").attr("src",$("#_imgUrl").val());
	$(".shadow").show();
}

var existed = true;
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
$(document).ready(function(){
	jQuery.validator.addMethod("countExsistedOtherLocale", function(value, element){
		return true;
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




	$(".dialogtip").inputtip();
	<@s.if test='"add_path".equals(modeType)'>
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
</@s.if>

<@s.if test='#parameters.dialog != null'>
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
</@s.if>

	$("#maintainForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form){
			$("#_imgUrl").val($("#imgUrl").val());
			$(form).ajaxSubmit({
				success:function(data){
					if(!data.id){
               	 		$.messageBox({
							message:data,
							level: "error"
				 		});
                		return;
               		}
				},
				error:function(XMLHttpRequest, textStatus, errorThrown){
      	      		alert("提交数据时发生错误");
   	   		    }
			});
		},
		rules:{
			"location.name":{
				required:true,
				exculdeParticalChar:true,
				minlength:2,
				countExsistedOtherLocale:true
			},
			"location.practitionerNumber":{
				positiveInteger:true,
				maxlength:9
			},
			"location.manager":{
				minlength:2,
				exculdeParticalChar:true
			},
			"location.telephone":{
				telephone:true
			},
			"location.mobileNumber":{
				mobile:true
			},
			"location.remark":{
				maxlength:200
			},
			"location.type.id":{
				required:true
			}
		},
		messages:{
			"location.name":{
				required:"请输入名称",
				exculdeParticalChar:"名称只能输入字母，数字和中文字符",
				minlength:$.format("名称至少需要输入{0}个字符"),
				countExsistedOtherLocale:"该网格下已存在该名称场所"
			},
			"location.practitionerNumber":{
				positiveInteger:"请输入正整数",
				maxlength:$.format("从业人员数不能大于{0}")
			},
			"location.manager":{
				minlength:$.format("联系人至少需要输入{0}个字符"),
				exculdeParticalChar:"联系人只能输入字母，数字和中文字符"
			},
			"location.telephone":{
				telephone:$.format("联系电话只能输数字和横杠(-)")
			},
			"location.mobileNumber":{
				mobile:$.format("手机号码输入只能是以1开头的11位数字")
			},
			"location.remark":{
				maxlength:$.format("备注最多只能输入{0}个字符")
			},
			"location.type.id":{
				required:"请选择场所类型"
			}
		}
	});


});

<@s.if test='"add_path".equals(modeType)'>
function setZone(selectOrgId,selectOrgName){
		$("#organizationId").val(selectOrgId);
		$("#orgName").val(selectOrgName);
	}
</@s.if>

function emptyObject(){
	$("#otherLocaleId").val("");
	$("#otherLocale-name").val("");
	$("#otherLocale\\.address").val("");
	$("#otherLocale\\.type\\.id").val("");
	$("#otherLocale\\.manager").val("");
	$("#otherLocale\\.telephone").val("");
	$("#otherLocale\\.mobileNumber").val("");
	$("#otherLocale\\.remark").val("");
}




</script>