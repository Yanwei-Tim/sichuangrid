<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>

<div class="container container_24">
<@s.if test='"edit".equals(mode)'>
	<form action="${path}/judgmentAnalysis/businessDescriptionManage/updateBusinessDescription.action" method="post" id="businessDescriptionForm">
</@s.if>  
<@s.if test='"add".equals(mode)'>
	<form action="${path}/judgmentAnalysis/businessDescriptionManage/addBusinessDescription.action" method="post" id="businessDescriptionForm">
</@s.if>
  <@pop.token />
        <input id="mode" type="hidden" name="mode" value="${mode!}" />
        <input id="id" type="hidden" name="businessDescription.id" value="${(businessDescription.id )!}"/>		 
 
	<div class="grid_4 lable-right">
	 <em class="form-req">*</em>
		<label class="form-lbl">名称：</label>
	</div>
	<div class="grid_8">
		 <input  type="text" id="businessDescriptionName" maxlength="50" name="businessDescription.name" value="${(businessDescription.name)!}" 
			class='form-txt {required:true,maxlength:10,minlength:2,exculdeParticalChar:true,messages:{required:"请输入名称",maxlength:$.format("名称不能多于{0}个字符"),minlength:$.format("名称不能少于{0}个字符"),exculdeParticalChar:"不能输入非法字符"}}' 
			<@s.if test='"view".equals(mode)'> readonly </@s.if>
		 />
  	</div>
  	
	<div class="grid_4 lable-right" >
	    <em class="form-req">*</em>
		<label class="form-lb1">关键字：</label>
	</div>
	<div class="grid_8">
		<input  type="text" id="businessDescriptionKeyName" maxlength="20" name="businessDescription.keyName" value="${(businessDescription.keyName)!}" 
			class='form-txt {required:true,maxlength:10,messages:{required:"请输入关键字",maxlength:$.format("关键字不能多于{0}个字符")}}' 
			<@s.if test='"view".equals(mode)'> readonly </@s.if>
		 />
	</div>
	
	
	<div class="clear"></div>
	
	<div class="grid_4 lable-right heightAuto">
		<label class="form-lbl">内容：</label>
	</div>
	<div class="grid_20 heightAuto">
            <textarea rows="4" name="businessDescription.content" id="content" class='form-txt'
			style="width: 97.8%">${(businessDescription.content)!}</textarea>
  	</div>
	
  	<div class="clear"></div>
  	
  	<div class="grid_4 lable-right heightAuto" >
	    <em class="form-req">*</em>
		<label class="form-lb1">动态参数：</label>
	</div>
	<div class="grid_20 heightAuto">
		<@s.iterator value="@com.tianque.plugin.judgmentAnalysis.constants.BusinessDescriptionConfiguration@values()" var="config">
			<input type="button" class="defaultBtn" onclick="setContent('<@s.property value="value" escape=false/>')" value="<@s.property value="name" escape=false/>" />
		</@s.iterator>
	</div>
	
	
	<div class="clear"></div>
	
	
	
</form>

</div>
<div id="combinationDialog"></div>
<div id="businessModelDialog"></div>
<div id="idxDialog"></div>
<script type="text/javascript">
$(document).ready(function(){
	
	//表单验证
	$("#businessDescriptionForm").formValidate({
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
						$.messageBox({message:"成功添加!"});
	                	$("#businessDescriptionMaintanceDialog").dialog("close");
						$("#businessDescriptionList").trigger("reloadGrid");
         			}
         			if("edit" == $("#mode").val()){
         				$("#businessDescriptionMaintanceDialog").dialog("close");
						$.messageBox({message:"成功修改!"});
						$("#businessDescriptionList").trigger("reloadGrid");
         			}
	  	   		},
	      	   	error: function(XMLHttpRequest, textStatus, errorThrown){
         				$.messageBox({message:"提交错误",level: "error"});
	      	   	}
      	  	});
		}
	});

 
	
});

function setContent(txt){
	$("#content").val($("#content").val()+txt);
}


</script>