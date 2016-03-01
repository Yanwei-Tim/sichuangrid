<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>

	<span style="font-weight: bold; margin-left:20px; ">其他人员</span>
	<div class='clearLine'>&nbsp;</div>
<div class="grid_4 lable-right">
	<label class="form-lbl">关注程度：</label>
</div>
<div class="grid_6">
	<select name="population.attentionExtent.id" id="otherAttentionPersonnelAttentionExtent" class="form-txt"
		<s:if test='"view".equals(mode)'>disabled='true'</s:if> >
		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@ATTENTION_EXTENT" defaultValue="${population.attentionExtent.id}" />
	</select>
</div>
<div class="grid_6 lable-right"><label class="form-lbl">关注原因：</label></div>
	<div class="grid_6">
		 <input type="text" name="population.attentionReason" maxlength="20"
		   		class="form-txt dialogtip{exculdeParticalChar:true,messages:{exculdeParticalChar:'不能输入非法字符'}}" title="关注原因"
	  			value="${population.attentionReason}" />
	</div>
<div class='clearLine'>&nbsp;</div>
 <div class="grid_4 lable-right">
	<label class="form-lbl">工作情况：</label>
</div>
<div class="grid_6 lable-right">
	<input type="text" name="population.workCondition" id="workCondition"  maxlength="50"
		value="${population.workCondition}" class='form-txt' />
</div>

<script>
$(function(){
})
</script>