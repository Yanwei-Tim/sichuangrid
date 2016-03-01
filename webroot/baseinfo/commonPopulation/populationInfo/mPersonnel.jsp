<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>

	<span style="font-weight: bold; margin-left:20px; ">M人员</span>
	<div class='clearLine'>&nbsp;</div>
<div class="grid_4 lable-right">
	<label class="form-lbl">关注程度：</label>
</div>
<div class="grid_6">
	<select name="population.attentionExtent.id" id="mPersonnelAttentionExtent" class="form-txt"
		<s:if test='"view".equals(mode)'>disabled='true'</s:if> >
		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@ATTENTION_EXTENT" defaultValue="${population.attentionExtent.id}" />
	</select>
</div>
<div class='clearLine'>&nbsp;</div>
<div class="grid_4 lable-right"><label class="form-lbl">备注：</label></div>
	<div class="grid_20" style="height: 120px">
		 <textarea rows="4" name="population.mbusinessRemark" maxlength="100"
		   class="form-txt dialogtip{exculdeParticalChar:true,maxlength:300,messages:{exculdeParticalChar:'不能输入非法字符',maxlength:'备注最多需要输入100个字符'}}" title="备注">${population.mbusinessRemark}</textarea>
	</div>
<div class='clearLine'>&nbsp;</div>
<script>
$(function(){
})
</script>