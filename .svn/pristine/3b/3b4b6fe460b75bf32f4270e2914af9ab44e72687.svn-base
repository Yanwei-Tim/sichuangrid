<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<jsp:include page="/includes/baseInclude.jsp" />

<div class="container container_24" >
	<div id="scenicSpot" class="container container_24">
		<form id="praiseForm" method="post" action="${path}/baseinfo/scenicSpotManage/addPraiseScenicSpot.action">
			<div id="perUuid"></div>
			<s:if test='"add".equals(mode)'>
				<pop:token/>
			</s:if>
			<input type="hidden" name="mode" id="mode" value="${mode}" />
			<input type="hidden" name="praiseScenicSpot.scenicSpotId" id="scenicSpotId" value="${location.id}" />
			<div class="grid_4 lable-right">
	   			<em class="form-req">*</em><label class="form-lb1">类型：</label>
   			</div>
   			<div class="grid_8">
   				<select name="praiseScenicSpot.feedbackType.id" id="feedbackType" class="form-txt" >
		   			<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@PRAISESCENICSPOT_TYPES"/>
				</select>
   			</div>
   			<div class="grid_4 lable-right">
	   			<label class="form-lb1">反馈时间：</label>
   			</div>
   			<div class="grid_8">
   				<input type="text" id="feedbackTime" name="praiseScenicSpot.feedbackTime" style="width: 99%"  maxlength="32" readonly class="form-txt " />
   			</div>
   			<div class='clearLine'>&nbsp;</div>
   			<div class="grid_4 lable-right">
	   			<label class="form-lb1">反馈人：</label>
   			</div>
   			<div class="grid_8">
   				<input type="text" id="feedbackPerson" name="praiseScenicSpot.feedbackPerson" style="width: 99%"  maxlength="15" class="form-txt " />
   			</div>
   			<div class="grid_4 lable-right">
   				<label class="form-lb1">反馈人电话：</label>
	   		</div>
	   		<div class="grid_8">
	   			<input type="text" id="feedbackPersonTelephone" name="praiseScenicSpot.feedbackPersonTelephone" maxlength="20" class="form-txt"/>
	   		</div>
	   		<div class='clearLine'>&nbsp;</div>
   			<div class="grid_4 lable-right">
   				<label class="form-lb1">情况描述：</label>
	   		</div>
	   		<div class="grid_20" style="height:145px">
   				<textarea rows="6" id="introduction" name="praiseScenicSpot.introduction" class="form-txt" style="width: 99%"></textarea>
   			</div>
	   		<div class='clearLine'>&nbsp;</div>
   		    <input name="isSubmit" id="isSubmit" type="hidden" value="" />
		</form>
  	</div>
</div>
<script type="text/javascript">
	/* 
	<s:if test='"edit".equals(mode)'>
		$("#maintainForm").attr("action","${path}/baseinfo/scenicSpotManage/updateScenicSpot.action");
	</s:if>
	 */

$(document).ready(function(){
	
	$('#feedbackTime').datePicker({
		yearRange: '1900:2030',
		dateFormat: 'yy-mm-dd',
	    maxDate:'+0d'
	});
	
	$("#praiseForm").formValidate({
		submitHandler: function(form) {
			$.confirm({
				title:"投诉表扬确认",
				message:"一经添加将不能修改和删除，确认添加吗？",
				okFunc: function() {
					$(form).ajaxSubmit({
		         		async : false,
						success: function(data){
							if(!data.id){
		                    	 $.messageBox({
									message:data,
									level: "error"
								 });
								return;
							}
							$($("#<s:property value='#parameters.dailogName[0]'/>")).dialog("close");
							$.messageBox({message:"已经成功添加该反馈信息!"});
						},
						error: function(XMLHttpRequest, textStatus, errorThrown){
							alert("提交错误");
						}
					});
				}
			});
         	
		},
		rules:{
			"praiseScenicSpot.feedbackType.id":{
				required:true
			},
			"praiseScenicSpot.feedbackPerson":{
				exculdeParticalChar:true,
				maxlength:20
			},
			"praiseScenicSpot.feedbackPersonTelephone":{
				telephone:true
			},
			"praiseScenicSpot.introduction":{
				maxlength:300
			}
		},
		messages:{
			"praiseScenicSpot.feedbackType.id":{
				required:"类型必须选择"
			},
			"praiseScenicSpot.feedbackPerson":{
				exculdeParticalChar:"反馈人不能输入特殊字符",
				maxlength:$.format("反馈人最多只能输入{0}个字符")
			},
			"praiseScenicSpot.feedbackPersonTelephone":{
				telephone:$.format("反馈人电话只能输数字和横杠(-)")
			},
			"praiseScenicSpot.introduction":{
				maxlength:$.format("情况描述最多只能输入{0}个字符")
			}
		}
	});
});

</script>