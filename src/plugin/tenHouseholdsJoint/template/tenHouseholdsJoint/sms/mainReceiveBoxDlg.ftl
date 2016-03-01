<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<div class="container container_24">
	<form action="" method="post" id="maintainForm">
		<div class="grid_5 lable-right">
			<em class="form-req">*</em>
			<label>发送手机：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="messageInfoVo.telephone" id="name" class="form-txt {required:true,messages:{required:'发送手机必填'}}" maxlength="11"/>
		</div>
		<div class="grid_5 lable-right">
			<em class="form-req">*</em>
			<label>数字标记：</label>
		</div>
		<div class="grid_7">
			<select name="messageInfoVo.messageTypeId" id="alarmCenter" class="form-txt {required:true,messages:{required:'数字标记必选'}}" style="height: 22px;width:137px;">
				<@pop.OptionTag name="@com.tianque.domain.property.PropertyTypes@MESSAGETYPE"/>
			</select>
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		<div class="grid_5 lable-right">
			<label>短信内容：</label>
		</div>
		<div class="grid_19">
			<input type="text" name="messageInfoVo.text" id="name" class="form-txt"/>
		</div>
		<div class='clearLine'>&nbsp;</div>
	</form>
</div>
<script type="text/javascript">
	$("#maintainForm").attr("action","${path}/tenHouseholdsJoint/receiveMsgInfo/dispatch.action?mode=add");
	$("#maintainForm").formValidate({
		submitHandler: function(form){
			 $(form).ajaxSubmit({
					success : function(data) {
						if(data==true || data=='true'){
							$.messageBox({message:"模拟预警成功！"});
						}else{
							$.messageBox({message:"模拟预警失败,手机号未加入联防",level:"warn"});
						}
						$("#receiveBoxleList").trigger("reloadGrid");
						$("#receiveBoxDialog").dialog("close");
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						alert("提交错误");
					}
			 });
		},
		rules:{
		},
		messages:{
		}
	});
</script>