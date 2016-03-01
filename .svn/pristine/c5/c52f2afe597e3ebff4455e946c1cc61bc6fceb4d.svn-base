<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div  class="container container_24">
	<form id="maintainForm" method="post" action="">
		<div class="grid_4 lable-right">
			<em class="form-req"></em><label class="form-lbl">群组名称：</label>
		</div>
		<div class="grid_8 ">
			<input type="text" name="contacterVo.name" class="form-txt"/>
		</div>
		<div class="grid_4 lable-right">
			<em class="form-req"></em><label class="form-lbl">群组描述：</label>
		</div>
		<div class="grid_8" id="titleDiv">
			<input type="text" name="contacterVo.remark"  class="form-txt" />
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		<div class="grid_4 lable-right">
			<em class="form-req"></em><label class="form-lbl">群组人数：</label>
		</div>
		<div class="grid_8 heightAuto">
			 <input type="text" id="minNums" name="contacterVo.minContacterNums" class="form-txt {number:true,messages:{number:'只能输入整数'}}" style="width:83px;"/>-
			  <input type="text" id="maxNums" name="contacterVo.maxContacterNums" class="form-txt {number:true,messages:{number:'只能输入整数'}}" style="width:83px;"/>
		</div>
	</form>
</div>
<script type="text/javascript">

$(document).ready(function(){
	//提交
	$("#maintainForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form){
			var params = $(form).serialize();
			jQuery("#myGroupList").setPostData({});
			var url = jQuery("#myGroupList").getGridParam("url");
			jQuery("#myGroupList").setGridParam({
				"url":"${path}/contact/myGroupManage/searchMyGroupByCondition.action?belongClass=myGroup&"+params
						});
			$("#myGroupList").trigger("reloadGrid");
			$("#searchMyGroupDlg").dialog("close");
		},
		rules:{
			"contacterVo.minContacterNums":{
					number:true
				},
			"contacterVo.maxContacterNums":{
					number:true,
					minNumsMustMinMaxNums:true
			}
		},
		messages:{
			"contacterVo.minContacterNums":{
				maxlength:$.format("群组人数只能输入整数")
				},
			"contacterVo.maxContacterNums":{
					maxlength:$.format("群组人数只能输入整数"),
					minNumsMustMinMaxNums:$.format("群组人数最大值不能小于初始值")
			}
		}
	});
	
});

jQuery.validator.addMethod("minNumsMustMinMaxNums", function(){
	 var minNums = $("#minNums").val();
	 var maxNums = $("#maxNums").val();
	 if(minNums!=null && maxNums!='' && maxNums!=null && maxNums!=''){
		 if(minNums>maxNums){
			 return false;
		 }
	 }
	 return true;
});

</script>


