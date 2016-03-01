<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>

<div id="logout-form" class="container container_24">
   	<form id="logoutForm" method="post" action="${path}/plugin/serviceTeam/serviceTeamManage/logOutServiceTeam.action">
   	<@pop.token />
   		<input type="hidden" name="serviceTeam.id" id="selectedIds" value="${(serviceTeam.id)!}" />
   		<input type="hidden" name="serviceTeam.logOutTime" id="logOutTimeValue" value="" />
	    <div class="grid_7 lable-right">
	     	<em class="form-req">*</em>
	        <label class="form-lbl">解散时间：</label>
	    </div>
	    <div class="grid_16">
	        <input type="text" id="logOutTime"  disabled="disabled" class="form-txt" maxlength="20" />
	    </div>
	    <div class='clearLine'></div>
	    <div class="grid_7 lable-right">
	    	<em class="form-req">*</em>
	        <label class="form-lbl">解散原因：</label>
	    </div>
	    <div class="grid_16">
			<input type="text" name="serviceTeam.logOutReason" id="logOutReason" maxlength="300"
			class="form-txt {required:true,maxlength:300,messages:{required:'请输入解散原因',maxlength:$.format('备注最多只能输入{0}个字符')}}"  />
	    </div>
	</form>
</div>
<script type="text/javascript">
$(function(){
	var t=document.getElementById("logOutTime");
	d=new Date();
	t.value=d.getFullYear()+"-"+(d.getMonth()+1)+"-"+d.getDate();
	t.onfocus=function(){
		var d=new Date();
		t.value=d.getHours()+":"+d.getMinutes();
	}
	$("#logOutTimeValue").val($("#logOutTime").val());
	$("#logoutForm").formValidate({
		showErrors:showErrorsForTab,
		promptPosition: "bottomLeft",
		submitHandler: function(form){
			$(form).ajaxSubmit({
				success:function(data){
					if(data !=true){
	       	 			$.messageBox({
							message:data,
							level: "error"
			 			});
	        			return;
					}else{
						$.messageBox({message:document.title+"解散成功"});
					}
					$("#serviceTeamList").trigger("reloadGrid");
					$("#serviceTeamDialog").dialog("close");
				},
				error:function(XMLHttpRequest, textStatus, errorThrown){
					$.messageBox({level:'warn',message:"提交数据时发生错误"});
	   		    }
			});
		},
		rules:{},
		messages:{}
	});
});
</script>