<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>

<div class="container container_24">
<form id="serviceTeamForm" method="post" action="/plugin/serviceTeam/serviceTeamManage/${mode!}ServiceTeam.action">
<@pop.token />
        <input id="mode" type="hidden" name="mode" value="${mode!}" />
        <input id="serviceTeamId" type="hidden" name="serviceTeam.id" value="${(serviceTeamVo.id )!}"/>
		<input id="serviceTeamOrgId" type="hidden"	name="serviceTeam.org.id" value="${(serviceTeamVo.org.id )!}" />
		<input name="isSubmit" id="isSubmit" type="hidden" value="" />
	<div class="grid_4 lable-right">
	    <em class="form-req">*</em>
		<label class="form-lb1">所属区域：</label> 
	</div>
	<div class="grid_7">
		<input  type="text" id="orgName" name="serviceTeam.org.orgName" readonly style="width:440px;"
			value="${(serviceTeamVo.org.orgName)! }" class="form-txt" />
	</div>
	<div class='clearLine'>&nbsp;</div>
	<div class="grid_4 lable-right">
		<label class="form-lbl">团队类别：</label>
	</div>
	<div class="grid_8">
		<select id="teamTypeId" name="serviceTeam.teamType.id" class="form-select" >                                                
			<@pop.OptionTag name="@com.tianque.plugin.serviceTeam.util.Constants$ServiceTeamPropertyTypes@TEAM" defaultValue="${(serviceTeamVo.teamType.id)!}"/>
		</select>
  	</div>
	<div class='clearLine'>&nbsp;</div>
	<div class="grid_4 lable-right" >
	    <em class="form-req">*</em>
		<label class="form-lb1">团队名称：</label>
	</div>
	<div class="grid_7">
		<input  type="text" id="serviceTeamTitle" maxlength="50" name="serviceTeam.teamName" value="${(serviceTeamVo.teamName)!}" title="请输入团队名称"
			class='form-txt {required:true,maxlength:150,minlength:2,exculdeParticalChar:true,messages:{required:"请输入团队名称",maxlength:$.format("团队名称不能多于{0}个字符"),minlength:$.format("团队名称不能少于{0}个字符"),exculdeParticalChar:"不能输入非法字符"}}' />
	</div>
	<div class="grid_4 lable-right" >
		<label class="form-lbl">成立时间：</label>
	</div>
	<div class="grid_8 ">
		<input type="text" name="serviceTeam.buildDate" id="buildDate" maxlength="32"
			readonly="readonly"  class="form-txt" value='<@s.date name="serviceTeamVo.buildDate" format="yyyy-MM-dd" />' />
	</div>
	<div class="clear"></div>
	<div class="grid_4 lable-right" >
		<label class="form-lb1">简介：</label>
	</div>
	<div class="grid_20 heightAuto">
		<textarea id="serviceTeamRemark" style="width:440px;height:100px" name="serviceTeam.remark" maxlength="300" title="请输入简介"
		class="form-txt {maxlength:600,messages:{maxlength:$.format('简介最多允许输入{0}个字符')}}">${(serviceTeamVo.remark)!}</textarea>
	</div>
</form>

</div>
<script type="text/javascript">
$(document).ready(function(){
	//表单验证
	$("#serviceTeamForm").formValidate({
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
         			if("add" == $("#mode").val()){
						$.messageBox({message:"成功添加团队!"});
						if($("#isSubmit").val()=="true"){
	                	 	$("#serviceTeamDialog").dialog("close");
	                	}else{
	                		emptyObject();
		                }
						$("#serviceTeamList").trigger("reloadGrid");
         			}
         			if("edit" == $("#mode").val()){
         				$("#serviceTeamDialog").dialog("close");
						$.messageBox({message:"成功修改团队!"});
						$("#serviceTeamList").trigger("reloadGrid");
         			}
	  	   		},
	      	   	error: function(XMLHttpRequest, textStatus, errorThrown){
         				$.messageBox({message:"提交错误",level: "error"});
	      	   	}
      	  	});
		}
	});
	//继续新增时清空内容
	function emptyObject() {
		$("#serviceTeamForm").resetForm();
		$("#orgName").val($("#currentOrgId").attr("text"));
		$("#serviceTeamOrgId").val(getCurrentOrgId());
	}

	//成立时间
	$('#buildDate').datePicker({
		yearRange : '1900:2030',
		dateFormat : 'yy-mm-dd',
		maxDate : '+0d'
	});
	<@s.if test='"add".equals(mode)'>
			$("#serviceTeamOrgId").val(getCurrentOrgId());
			$.ajax({
				async: false,
				url: "${path}/sysadmin/orgManage/getOrgRelativePath.action",
				data:{
					"organization.id" : $("#currentOrgId").val()
				},
				success:function(responseData){
					$("#orgName").val(responseData);
				}
			});
	</@s.if>
	
});
</script>