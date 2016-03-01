<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<style type="text/css">
#textContent a.search{display:inline-block; *display:inline; *zoom:1; cursor:pointer; background:url(/resource/system/images/button-bg.png) left bottom no-repeat; padding-left:15px; height:24px; line-height:24px; margin:0 5px 10px 0; position:relative; color:#333;}
#textContent a.search span{display:inline-block; *display:inline; *zoom:1; background:url(/resource/system/images/button-bg.png) right bottom no-repeat; padding-right:15px; vertical-align:top; *vertical-align:middle;}
#textContent a.search:hover{background-position:left top; color:#fff;}
#textContent a.search:hover span{background-position:right top;}

</style>
<div id="newServiceManageTeam" class="container container_24">
	<form action="${path}/plugin/serviceTeam/serviceTeamMember/${mode!}ServiceTeamMemberBase.action" method="post" id="serviceTeamMemberForm">
		<@pop.token />
		<input type="hidden" id="serviceTeamMemberBaseID" name="serviceTeamMemberBase.id" value="${(serviceTeamMemberVo.baseId)!}">
		<input type="hidden" id="serviceTeamMemberOrgId" name="serviceTeamMemberBase.org.id" value="${(serviceTeamMemberVo.org.id)!}" />
		<input type="hidden" id="birthday" name="" value="${(serviceTeamMemberVo.birthday)!}">
		<input name="addTeam" id="addTeam" type="hidden"/>
		
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em> <label class="form-lb1">姓名：</label>
		</div>
		<div class="grid_8">
			<input type="text" style="width: 95%" name="serviceTeamMemberBase.name" id="name" maxlength="20" value='${(serviceTeamMemberVo.name)!}'
			class='form-txt {required:true,exculdeParticalChar:true,minlength:2,maxlength:20,messages:{required:"请输入姓名",exculdeParticalChar:"不能输入非法字符",minlength:$.format("姓名至少需要输入{0}个字符"),maxlength:$.format("姓名最多需要输入{0}个字符")}}' style="width:96%"/>
		</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em> <label class="form-lb1">性别：</label>
		</div>
		<div class="grid_8">
			<select name="serviceTeamMemberBase.gender.id" id="gender" class='form-txt {required:true,messages:{required:"请选择性别"}}'>
				<@pop.OptionTag name="@com.tianque.domain.property.PropertyTypes@GENDER" defaultValue="${(serviceTeamMemberVo.gender.id)!}" />
			</select>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<label class="form-lb1">职位： </label>
		</div>
		<div class="grid_8">
			<input type="text" style="width: 95%" name="serviceTeamMemberBase.job" id="job" maxlength="20" value='${(serviceTeamMemberVo.job)!}' class='form-txt {exculdeParticalChar:true,minlength:2,maxlength:20,messages:{exculdeParticalChar:"不能输入非法字符",minlength:$.format("职位至少需要输入{0}个字符"),maxlength:$.format("职位最多需要输入{0}个字符")}}' />
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lb1">出生年份 ：</label>
		</div>
		<div class="grid_8">
			<select id="year" class="basic-input" name="serviceTeamMemberBase.birthday">
				<option value="" selected>-请选择-</option>
			</select>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<label class="form-lb1">联系手机：</label>
		</div>
		<div class="grid_8">
			<input type="text" name="serviceTeamMemberBase.mobile" id="mobileNumber" maxlength="11" value="${(serviceTeamMemberVo.mobile)!}"
			title="请输入11位以1开头的联系手机  例如：13988888888" class='form-txt {mobile:true,messages:{mobile:"手机号码输入只能是以1开头的11位数字"}}' />
		</div>
	
		<div class="grid_4 lable-right">
			<label class="form-lb1">联系电话：</label>
		</div>
		<div class="grid_8">
			<input type="text" style="width: 95%" name="serviceTeamMemberBase.homePhone" id="homePhone" maxlength="15" value="${(serviceTeamMemberVo.homePhone)!}" title="请输入由数字和-组成的联系电话,例如：0577-88888888"
			class='form-txt {telephone:true,messages:{telephone:$.format("固定电话不合法，只能输数字和横杠(-)")}}' />
		</div>
		<div class='clearLine'>&nbsp;</div>
	
		<div class="grid_4 lable-right">
			<label class="form-lb1">备注：</label>
		</div>
		<div class="grid_20 heightAuto">
		    <textarea rows="2" name="serviceTeamMemberBase.remark" id="remark" class='form-txt {maxlength:300,messages:{maxlength:"备注最多需要输入300个字符"}}'
			style="width: 97.8%">${(serviceTeamMemberVo.remark)!}</textarea>
		</div>
	
		<input type="hidden" name="positionInTeam" id="position_in_team"/>
		<input type="hidden" name="isSubmit" id="isSubmit"/>
	</form>
</div>

<script type="text/javascript">

$(document).ready(function() {
	//获取年份的选择下拉框
	$.ajax({
		async: false,
		url: "${path }/plugin/serviceTeam/serviceTeamMember/getCurrentTimeForIntegrativeQueryYear.action",
		success:function(responseData){
			for(var i = 0;i<responseData.length;i++){
				$("#year").append("<option value='"+responseData[i]+"'>"+responseData[i]+"</option>");
			}
		}
	});

	//表单验证
	$("#serviceTeamMemberForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form){
		 $(form).ajaxSubmit({
			success : function(data) {
				if (!data.baseId) {
					$.messageBox({message : data,level : "error"});
					return;
				}
				if("add"=="${mode}"){
				//$("#serviceTeamMemberList").addRowData(data.baseId,data,"first");
					$.messageBox({message:"服务团队成员新增成功！"});
					$("#serviceTeamMemberList").trigger("reloadGrid");
				}else if("edit"=="${mode}"){
				  //$("#serviceTeamMemberList").setRowData(data.baseId,data);
					 $.messageBox({message:"服务团队成员修改成功！"});
					 $("#serviceTeamMemberList").trigger("reloadGrid");
					 $("#_serviceTeamMemberDialog").dialog("close");
				}
				if($("#isSubmit").val()=="false"){
					document.getElementById("serviceTeamMemberForm").reset();
				}else if($("#isSubmit").val()=="true"){
					$("#_serviceTeamMemberDialog").dialog("close");
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
	if($("#birthday").val()){
		$("#year").val($("#birthday").val());
	}
</script>