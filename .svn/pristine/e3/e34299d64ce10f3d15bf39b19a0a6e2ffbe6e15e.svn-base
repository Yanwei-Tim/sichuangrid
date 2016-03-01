<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>

<div class="container container_24">
<form id="businessModelForm" method="post" action="/judgmentAnalysis/businessModelManage/${mode!}BusinessModel.action">
  <@pop.token />
        <input id="mode" type="hidden" name="mode" value="${mode!}" />
        <input id="id" type="hidden" name="businessModel.id" value="${(businessModel.id )!}"/>		 
		<input name="isSubmit" id="isSubmit" type="hidden" value="" />
 
	<div class="grid_4 lable-right">
	 <em class="form-req">*</em>
		<label class="form-lbl">名称：</label>
	</div>
	<div class="grid_7">
		 <input  type="text" id="businessModelname" maxlength="50" name="businessModel.name" value="${(businessModel.name)!}" title="请输入模型名称"
			class='form-txt {required:true,maxlength:20,minlength:2,exculdeParticalChar:true,messages:{required:"请输入模型名称",maxlength:$.format("业务模型名称不能多于{0}个字符"),minlength:$.format("业务模型名称不能少于{0}个字符"),exculdeParticalChar:"不能输入非法字符"}}' 
			<@s.if test='"view".equals(mode)'> readonly </@s.if>
		 />
  	</div>
  	
	<div class='clearLine'>&nbsp;</div>
	<div class="grid_4 lable-right" >
	    <em class="form-req">*</em>
		<label class="form-lb1">关键字：</label>
	</div>
	<div class="grid_7">
		<input  type="text" id="businessModelkeyName" maxlength="20" name="businessModel.keyName" value="${(businessModel.keyName)!}" title="请输入关键字"
			class='form-txt {required:true,maxlength:20,minlength:2,exculdeParticalChar:true,messages:{required:"请输入关键字",maxlength:$.format("业务模型关键字不能多于{0}个字符"),minlength:$.format("业务模型关键字不能少于{0}个字符"),exculdeParticalChar:"不能输入非法字符"}}' 
			<@s.if test='"view".equals(mode)'> readonly </@s.if>
		 />
	</div>
	
	<div class='clearLine'>&nbsp;</div>
	<div class="grid_4 lable-right" >	     
		<label class="form-lbl">类型：</label>
	</div>
	<div class="grid_7 ">
		 <input  type="text" id="businessModeltype" maxlength="20" name="businessModel.type" value="${(businessModel.type)!}" title="请输入类型"
			class='form-txt' 
			<@s.if test='"view".equals(mode)'> readonly </@s.if>
			/>	
	</div>
	
	<div class="clear"></div>
	 
</form>

</div>
<script type="text/javascript">
$(document).ready(function(){
	//表单验证
	$("#businessModelForm").formValidate({
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
						if($("#isSubmit").val()=="true"){
	                	 	$("#businessModelDialog").dialog("close");
	                	}else{
	                		emptyObject();
		                }
						$("#businessModelList").trigger("reloadGrid");
         			}
         			if("edit" == $("#mode").val()){
         				$("#businessModelDialog").dialog("close");
						$.messageBox({message:"成功修改!"});
						$("#businessModelList").trigger("reloadGrid");
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
		$("#businessModelForm").resetForm();	 
	}

 
	
});
</script>