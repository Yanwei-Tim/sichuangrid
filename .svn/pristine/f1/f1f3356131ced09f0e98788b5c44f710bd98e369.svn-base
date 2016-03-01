<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>

<div class="container container_24" style="width:99.8%;height: 520px;text-align:center;">
	<form id="issueAccessConfigForm" action="${path}/issueAccessConfigManage/saveSet.action">
		<br><br>
		<div class="grid_3 lable-right" >
			<label>黄牌扣分：</label>
		</div>
		<div class="grid_5">
			<input name="issueAccessConfig.yellowCardScores" type="text" maxlength="6" value="${issueAccessConfig.yellowCardScores}"
				<s:if test="@com.tianque.core.util.ThreadVariable@getUser().isAdmin() != true"> readonly </s:if> class="form-txt">	
		</div>
		<div class="grid_13" style="text-align:left">
			<label class="form-req">注：黄牌督办将导致处理部门被扣分。 </label>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<!-- 
		<div class="grid_3 lable-right" >
			<label>黄牌受理期限：</label>
		</div>
		<div class="grid_5">
			<input name="issueAccessConfig.yellowCardAccepted"type="text" maxlength="2" value="${issueAccessConfig.yellowCardAccepted}" class="form-txt">	
		</div>
		<div class="grid_13" style="text-align:left">
			<label class="form-req">(单位工作日)&nbsp;&nbsp;&nbsp;&nbsp;注：某事件超过1个工作日未受理的，系统自动黄牌督办。 </label>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_3 lable-right" >
			<label>黄牌处理期限：</label>
		</div>
		<div class="grid_5">
			<input name="issueAccessConfig.yellowCardHandle"type="text" maxlength="2" value="${issueAccessConfig.yellowCardHandle}" class="form-txt">	
		</div>
		<div class="grid_13" style="text-align:left">
			<label class="form-req">(单位工作日)&nbsp;&nbsp;&nbsp;&nbsp;注：某事件超过5个工作日未办理（即没有反馈意见）的，系统自动黄牌督办 。</label>
		</div>
		<div class='clearLine'>&nbsp;</div>
		 -->
		<div class="grid_3 lable-right" >
			<label>红牌扣分：</label>
		</div>
		<div class="grid_5">
			<input name="issueAccessConfig.redCardScores"type="text" maxlength="6" value="${issueAccessConfig.redCardScores}"
				<s:if test="@com.tianque.core.util.ThreadVariable@getUser().isAdmin() != true"> readonly </s:if> class="form-txt">	
		</div>
		<div class="grid_13" style="text-align:left">
			<label class="form-req">注：红牌督办将导致处理部门被扣分。 </label>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<!-- 
		<div class="grid_3 lable-right" >
			<label>红牌受理期限：</label>
		</div>
		<div class="grid_5">
			<input name="issueAccessConfig.redCardAccepted"type="text" maxlength="2" value="${issueAccessConfig.redCardAccepted}" class="form-txt">	
		</div>
		<div class="grid_13" style="text-align:left">
			<label class="form-req">(单位工作日)&nbsp;&nbsp;&nbsp;&nbsp;注：某事件超过2个工作日未受理的，系统自动红牌督办 。</label>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_3 lable-right" >
			<label>红牌处理期限：</label>
		</div>
		<div class="grid_5">
			<input name="issueAccessConfig.redCardHandle" type="text" maxlength="2" value="${issueAccessConfig.redCardHandle}" class="form-txt">	
		</div>
		<div class="grid_13" style="text-align:left">
			<label class="form-req">(单位工作日)&nbsp;&nbsp;&nbsp;&nbsp;注：某事件超过10个工作日未办理（即没有反馈意见）的，系统自动红牌督办 。</label>
		</div>
		<div class='clearLine'>&nbsp;</div>
		 -->
		<div class="grid_3 lable-right" >
			<label>正常办理加分：</label>
		</div>
		<div class="grid_5">
			<input name="issueAccessConfig.normalScores" type="text" maxlength="6" value="${issueAccessConfig.normalScores}"
				<s:if test="@com.tianque.core.util.ThreadVariable@getUser().isAdmin() != true"> readonly </s:if> class="form-txt">	
		</div>
		<div class="grid_13" style="text-align:left">
			<label class="form-req">注：正常办理加分 。</label>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<br>
		<s:if test="@com.tianque.core.util.ThreadVariable@getUser().isAdmin() == true">
			<div class="grid_10" style="text-align:center;">
				<button type="submit" style="height:30px;">保存</button>
				<button type="reset" style="height:30px;">重置</button>
			</div>
		</s:if>
	</form>
</div>
<script type="text/javascript">
//精确到3位的小数
jQuery.validator.addMethod("positiveDouble", function(value, element) {
	var positiveInteger = /^\d+(\.[0-9]{1,3})?$/;
	return this.optional(element) || (positiveInteger.test(value));
});
$(function(){
	 $("#issueAccessConfigForm").formValidate({
			promptPosition: "bottomLeft",
			submitHandler: function(form) {
	         $(form).ajaxSubmit({
	             success: function(data){
	        	     if(!data.redCardScores){
             	     	$.messageBox({ message:data, level: "error"});
             	 		return ;
                	 }
			    	 $.messageBox({message:"成功保存设置信息!"});
			    	 return ;
		      	  },
		      	  error: function(XMLHttpRequest, textStatus, errorThrown){
		      	      alert("提交错误");
		      	  }
	      	  });
		},
		rules:{
			"issueAccessConfig.yellowCardScores":{
				required:true,
				positiveDouble:true,
				max:99.999
			},
			"issueAccessConfig.redCardScores":{
				required:true,
				positiveDouble:true,
				max:99.999
			},
			"issueAccessConfig.yellowCardAccepted":{
				required:true,
				nonNegativeInteger:true
			},
			"issueAccessConfig.redCardAccepted":{
				required:true,
				nonNegativeInteger:true
			},
			"issueAccessConfig.yellowCardHandle":{
				required:true,
				nonNegativeInteger:true
			},
			"issueAccessConfig.redCardHandle":{
				required:true,
				nonNegativeInteger:true
			},
			"issueAccessConfig.normalScores":{
				required:true,
				positiveDouble:true,
				max:99.999
			}
		},
		messages:{
			"issueAccessConfig.yellowCardScores":{
				required:"请输入黄牌扣分数",
				positiveDouble:"只能输入小数位数不大于3位的非负数",
				max:"最大不能超过99.999"
			},
			"issueAccessConfig.redCardScores":{
				required:"请输入红牌扣分数",
				positiveDouble:"只能输入小数位数不大于3位的非负数",
				max:"最大不能超过99.999"
			},
			"issueAccessConfig.yellowCardAccepted":{
				required:"请输入黄牌受理期限",
				nonNegativeInteger:"只能输入非负整数"
			},
			"issueAccessConfig.redCardAccepted":{
				required:"请输入红牌受理期限",
				nonNegativeInteger:"只能输入非负整数"
			},
			"issueAccessConfig.yellowCardHandle":{
				required:"请输入黄牌处理期限",
				nonNegativeInteger:"只能输入非负整数"
			},
			"issueAccessConfig.redCardHandle":{
				required:"请输入红牌处理期限",
				nonNegativeInteger:"只能输入非负整数"
			},
			"issueAccessConfig.normalScores":{
				required:"请输入正常办理加分数",
				positiveDouble:"只能输入小数位数不大于3位的非负数",
				max:"最大不能超过99.999"
			}
		}
	});
	
});
</script>