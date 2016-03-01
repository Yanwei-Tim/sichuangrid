<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<div class="container container_24">
	<form id="serviceTeamGuarderForm" method="post" action="${path}/plugin/serviceTeam/router/routerManage/${mode!}ObjectAndGuardersRelation.action">
	<@pop.token />
 		<input name="serviceTeamGuarders.id" type="hidden" id="serviceTeamGuardersId" value="${(serviceTeamGuarders.id)!}"/>
 		
		<input type="hidden" name="isSubmit" id="isSubmit"/>
	<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em> <label class="form-lb1">姓名：</label>
		</div>
		<div class="grid_8">
			<input type="text" style="width: 95%" name="serviceTeamGuarders.guarderName" id="guarderName" value='${(serviceTeamGuardersVo.guarderName)!}'
			class='form-txt {required:true,exculdeParticalChar:true,minlength:2,maxlength:20,messages:{required:"请输入姓名",exculdeParticalChar:"不能输入非法字符",minlength:$.format("姓名至少需要输入{0}个字符"),maxlength:$.format("姓名最多需要输入{0}个字符")}}' style="width:96%"/>
		</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em> <label class="form-lb1">性别：</label>
		</div>
		<div class="grid_8">
			<select name="serviceTeamGuarders.gender.id" id="guarderGenderId" class='form-txt {required:true,messages:{required:"请选择性别"}}'>
				<@pop.OptionTag name="@com.tianque.domain.property.PropertyTypes@GENDER" defaultValue="${(serviceTeamGuardersVo.gender.id)!}" />
			</select>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em><label class="form-lb1">身份： </label>
		</div>
		<div class="grid_8">
			<input type="text" style="width: 95%" name="serviceTeamGuarders.relation" id="guarderPositipn" value='${(serviceTeamGuardersVo.relation)!}' 
			class='form-txt {required:true,exculdeParticalChar:true,minlength:2,maxlength:20,messages:{required:"请输入身份",exculdeParticalChar:"不能输入非法字符",minlength:$.format("身份至少需要输入{0}个字符"),maxlength:$.format("身份最多需要输入{0}个字符")}}' />
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lb1">身份证号码： </label>
		</div>
		<div class="grid_8">
			<input type="text" name="serviceTeamGuarders.idCardNo" id="guarderIdCardNo" value="${(serviceTeamGuardersVo.idCardNo)!}" maxlength="18"	class="form-txt {idCard:true,messages:{idCard:'请输入一个合法的身份证号码'}}" style="width:96%"/>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<label class="form-lb1">手机：</label>
		</div>
		<div class="grid_8">
			<input type="text" name="serviceTeamGuarders.mobile" id="guarderMobile" maxlength="11" value="${(serviceTeamGuardersVo.mobile)!}"
			title="请输入11位以1开头的联系手机  例如：13988888888" class='form-txt {mobile:true,messages:{mobile:"手机号码输入只能是以1开头的11位数字"}}' />
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lb1">电话：</label>
		</div>
		<div class="grid_8">
			<input type="text" style="width: 95%" name="serviceTeamGuarders.phone" id="guarderPhone" maxlength="20" value="${(serviceTeamGuardersVo.phone)!}" title="请输入由数字和-组成的联系电话,例如：0577-88888888"
			class='form-txt {telephone:true,messages:{telephone:$.format("固定电话不合法，只能输数字和横杠(-)")}}' />
		</div>
		<div class='clearLine'>&nbsp;</div>
	
		<div class="grid_4 lable-right">
			<label class="form-lb1">备注：</label>
		</div>
		<div class="grid_20 heightAuto">
		    <textarea rows="2" name="serviceTeamGuarders.remark" id="guarderRemark" class='form-txt {maxlength:300,messages:{maxlength:"备注最多需要输入300个字符"}}'
			style="width: 97.8%">${(serviceTeamGuardersVo.remark)!}</textarea>
		</div>
	</form>
	<div class='clearLine'></div>
</div>

<script>
$(document).ready(function(){


//表单验证
	$("#serviceTeamGuarderForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form){
		 $(form).ajaxSubmit({
			success : function(data) {
				if (!data.guardersId) {
					$.messageBox({message : data,level : "error"});
					return;
				}
				if("add"=="${mode}"){
				  //$("#serviceTeamMemberList").addRowData(data.guardersId,data,"first");
				  $("#serviceTeamMemberList").trigger("reloadGrid");
					$.messageBox({message:"监护人员新增成功！"});
				}else if("edit"=="${mode}"){
				  //$("#serviceTeamMemberList").setRowData(data.guardersId,data);
				  $("#serviceTeamMemberList").trigger("reloadGrid");
					 $.messageBox({message:"监护人员修改成功！"});
				}
				if($("#isSubmit").val()=="false"){
					document.getElementById("serviceTeamGuarderForm").reset();
				}else if($("#isSubmit").val()=="true"){
					$("#_serviceTeamGuardersDialog").dialog("close");
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert("提交错误");
			}
			});
		},
		rules:{},
		messages:{}
	});	

});


</script>