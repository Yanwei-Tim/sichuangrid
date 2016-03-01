<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
<div id="commonPopulation" class="container container_24">
	<form id="mainNewsForm" method="post" action="/baseinfo/newsManage/saveNews.action" >
		<input id="mode" type="hidden" name="mode" value="${mode}" />
		
			<div class="grid_4 lable-right">
				<label class="form-lbl">创建者：</label>
			</div>
			<div class="grid_7">
		    	<input type="text" name="news.createUserRealName" id="createUser" value="${news.createUserRealName}"
	  				readonly="readonly"/>
			</div>
			<div class="grid_4 lable-right">
				<label class="form-lbl">创建时间：</label>
			</div>
			<div class="grid_7">
				<input type="text" value='<s:date name="news.createDate"/>'/>
		    	
			</div>
			<div class='clearLine'>&nbsp;</div>
			<div class="grid_4 lable-right">
				<s:if test='!"view".equals(mode)'><em class="form-req">*</em></s:if>
				<label class="form-lbl">标题：</label>
			</div>
			<div class="grid_15">
		    	<input type="text" name="news.title" id="title" value="${news.title}"
	  				<s:if test='"view".equals(mode)'>disabled="disabled"</s:if> class="form-txt" style="width: 99%"/>
			</div>
			<div class='clearLine'>&nbsp;</div>
			<div class="grid_4 lable-right">
				<s:if test='!"view".equals(mode)'><em class="form-req">*</em></s:if>
				<label class="form-lbl">内容：</label>
			</div>
			
			<div class="grid_18">
				<textarea name="news.content" id="newsContent" rows="18" cols="78">${news.content}</textarea>
			</div>
			<input type="hidden" name="news.orgId" value="${orgId}"/>
			<input type="hidden" name="news.id" value="${news.id}"/>
			<input type="hidden" name="news.newsType" value="00"/>
	</form>
</div>
<script type="text/javascript">

$(document).ready(function(){
	$('#newsContent').noBarRichText();
	$('#newsContent').focus();
	
	$("#mainNewsForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form){
			$(form).ajaxSubmit({
				success:function(data){
					if (!data.id) {
						$.messageBox({
							message : data,
							level : "error"
						});
						return;
					}
					document.location.reload();	
					$.messageBox({message:"操作成功!"});
					$("#newsDialog").dialog("close");
				}
			});
		},
		rules:{
			"news.title":{
				required:true,
				exculdeParticalChar:true,
				minlength:2,
				maxlength:100
			},
			"news.content":{
				required:true,
			}
			
		},
		messages:{
			"news.title":{
				required:"请输入标题",
				exculdeParticalChar:"不能输入非法字符",
				minlength:$.format("标题至少需要输入{0}个字符"),
				maxlength:$.format("标题最多需要输入{0}个字符")
			},
		"news.content":{
			required:"请输入内容"
		}
		}
	});

});
</script>
