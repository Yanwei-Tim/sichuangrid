<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<style type="text/css">
</style>
<div  class="container container_24">
	<form id="messageTemplateForm" method="post" action="">
      <@pop.token />
	    <input type="hidden" id="currentOrgIdForMessageTemplate" name="templateMessageRecord.org.id" value="${(templateMessageRecord.org.id)!''}">
	    <input type="hidden" name="templateMessageRecord.id" value="${(templateMessageRecord.id)!''}">
		<div class="grid_4 lable-right">
	    	<em class="form-req">*</em>
			<label >所属层级：</label>
		</div>
		<div class="grid_20">
			<input type="text" id="templateMessageRecord_orgName_id" class="form-txt" value="${(templateMessageRecord.org.orgName)!''}" readonly/>
		</div>
		<div style="clear:both"></div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em><label>模板ID：</label>
		</div>	
		<div class="grid_20">
			<input type="text" name="templateMessageRecord.templateId"  maxlength="100" value="${(templateMessageRecord.templateId)!''}" class='form-txt {required:true,messages:{required:"请输入模板ID"}}'  />
		</div>		
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em><label>编号：</label>
		</div>	
		<div class="grid_20">
			<input type="text" name="templateMessageRecord.templateNum"  maxlength="50" value="${(templateMessageRecord.templateNum)!''}" class='form-txt {required:true,messages:{required:"请输入模板编号"}}'  />
		</div>		
		<div class='clearLine'>&nbsp;</div>
		<div id="title">
			<div class="grid_4 lable-right">
				<em class="form-req">*</em><label class="form-lbl">标题：</label>
			</div>
			<div class="grid_20" id="titleDiv">
				<input type="text" name="templateMessageRecord.title"  maxlength="30" value="${(templateMessageRecord.title)!''}" class='form-txt {required:true,messages:{required:"请输入标题"}}'  />
			</div>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<label>一级行业：</label>
		</div>	
		<div class="grid_20">
			<input type="text" name="templateMessageRecord.primaryIndustry"  maxlength="30" value="${(templateMessageRecord.primaryIndustry)!''}"  class='form-txt {}'  />
		</div>		
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<label>二级行业：</label>
		</div>	
		<div class="grid_20">
			<input type="text" name="templateMessageRecord.twoStageIndustry" maxlength="30"  value="${(templateMessageRecord.twoStageIndustry)!''}" class='form-txt {}'  />
		</div>		
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em>
			<label>微信连接号：</label>
		</div>
		<div class="grid_6">
			<input type="text" id="weChatUserId_Id" readonly  name="templateMessageRecord.weChatUserId" value="${(templateMessageRecord.weChatUserId)!''}" class='form-txt {required:true,messages:{required:"请选择微信连接号"}}' />
		</div>
		<div class="grid_2">
			<input type="button" class="defaultBtn" value="选择" id="choiceWxappid_Id" />
		</div>
		<div style="clear:both"></div>
		<div class="grid_4 lable-right">
			<label><em class="form-req">注:</em></label>
		</div>
		<div class="grid_20">
			<em class="form-req">以上内容请严格按照对应的微信公众平号台中设置的内容录入(否则微信模板消息将无法正常使用)</em>
		</div>
		<div style="clear:both"></div>
	</form>
</div>
<div id="messageTemplateFormDialog"></div>
<script type="text/javascript">	
$(document).ready(function(){
	//获取当前orgId
	//var currentOrgIdForMessageTemplate = getCurrentOrgId();
	//$("#currentOrgIdForMessageTemplate").val(currentOrgIdForMessageTemplate);
	//为部门赋值
	//$("#templateMessageRecord_orgName_id").val($("#globalOrgBtnMod").text());
	<@s.if test="'add'.equals(mode)">
		$("#messageTemplateForm").attr("action","/messageTemplateManage/addTemplateMessageRecord.action");
		var success_message = "新增成功";
	</@s.if>
	<@s.else>
		$("#messageTemplateForm").attr("action","/messageTemplateManage/updateTemplateMessageRecord.action");
		var success_message = "修改成功";	
	</@s.else>
	//提交
	$("#messageTemplateForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form) {			
	    $(form).ajaxSubmit({
	        success: function(data){
	          if(data != null && data.id){
					$.messageBox({message:success_message});			
					$("#messageTemplateDialog").dialog("close");	
					$("#messageTemplateList").trigger("reloadGrid");
              }else{
         			$.messageBox({message:data,level: "error"});
         	  }
	  	   },
	  	   error: function(XMLHttpRequest, textStatus, errorThrown){
	  	      alert("提交错误");
	  	   }
	  	  });
		}
	});
});
//选择微信连接号(公众账号)
$("#choiceWxappid_Id").click(function(){ 
	$("#messageTemplateFormDialog").createDialog({
		width: 820,
		height: 550,
		title:'选择微信公众平台appID',
		url:'${path}/hotModuel/redEnvelopeManagement/searchTencentUserDlg.ftl',
		buttons: {			
		   "确定" : function(){
				fillWeChatUserId();
		   },
		   "关闭" : function(){
				$(this).dialog("close");
		   }
		}
	});
});
</script>


