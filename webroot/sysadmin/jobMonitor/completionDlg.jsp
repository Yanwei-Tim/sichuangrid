<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<div id="dialog-form" title="数据补全" class="container container_24">
	 <form id="maintainForm" method="post"	action="${path}/baseinfo/leaderViewManage/complentionJob.action" >
	 	 <pop:token/>
	 	 <input type="hidden" name="completionType" id="completionType"  value="<s:property value='#parameters.type[0]'/>"  />
	 	<div class="grid_8 lable-right">
		<em class="form-req">*</em>
		<label class="form-lbl">补全类型： </label>
		</div>
	 	 <div class="grid_12" >
		<input type="text" disabled value="<s:property value='#parameters.type[0]'/>" class='form-txt' />
  		</div>
  		
		<div class="grid_8 lable-right">
		<em class="form-req">*</em>
		<label class="form-lbl">补全年份： </label>
		</div>
		<div class="grid_12" >
		 <input type="text"  name="year" id="year" class='form-txt {required:true,messages:{required:"请输入需要补全年份"}}'/>
  		</div>
  		
  		<div class='clearLine'>&nbsp;</div>
		<div class="grid_8 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">补全月份： </label>
		</div>
		<div class="grid_12">
		   <input type="text"  name="month" id="month" class='form-txt {required:true,messages:{required:"请输入需要补全月份"}}'/>
  		</div>
	</form>
</div>	
<script type="text/javascript">
$("#maintainForm").formValidate({
	promptPosition: "bottomLeft",
	submitHandler: function(form) {
     $(form).ajaxSubmit({
         success: function(data){
	        	 if(data!=true){
                	 $.messageBox({
						message:data,
						level: "error"
					 });
                 	return;
                 }
				 $.messageBox({message:"数据补全成功!"});
  	   },
  	   error: function(XMLHttpRequest, textStatus, errorThrown){
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