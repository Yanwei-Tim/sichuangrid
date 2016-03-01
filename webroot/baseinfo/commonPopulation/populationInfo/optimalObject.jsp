<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<jsp:include page="/includes/baseInclude.jsp" />

	<span style="font-weight: bold; margin-left:20px; ">优抚人员</span>
	<div class='clearLine'>&nbsp;</div>

<div class="grid_5 lable-right">
	<label class="form-lbl">关注程度：</label>
</div>
<div class="grid_7">
	<select name="population.attentionExtent.id" id="attentionExtent" class="form-txt" >
		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@ATTENTION_EXTENT" defaultValue="${population.attentionExtent.id}" />
	</select>
</div>
<div class="grid_5 lable-right">
	<label class="form-lbl">优待证号：</label>
</div>
<div class="grid_7">
	<input type="text" name="population.optimalCardNo" id="optimalCardNo" class='form-txt {exculdeParticalChar:true,messages:{exculdeParticalChar:"不能输入非法字符"}}' value="${population.optimalCardNo}" maxlength="30"
	<s:if test='"view".equals(mode)'>disabled</s:if>/>
</div>
<div class='clearLine'>&nbsp;</div>
<div class="grid_5 lable-right">
	<label class="form-lbl">优抚类型：</label>
</div>
<div class="grid_7">
	<select id="population.optimalCardType.id" name="population.optimalCardType.id"  class="form-select" <s:if test='"view".equals(mode)'>disabled</s:if>>
    	<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@SPECIAL_CARE_TYPE"  defaultValue="${population.optimalCardType.id }"  />
    </select>
</div>

<div class="grid_5 lable-right">
	<label class="form-lbl">劳动能力：</label>
</div>
<div class="grid_7">
    <select id="population.laborAbility.id" name="population.laborAbility.id"  class="form-select" <s:if test='"view".equals(mode)'>disabled</s:if>>
    	<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@LABOUR_CAPACITY"  defaultValue="${population.laborAbility.id }"  />
    </select>
</div>
<div class='clearLine'>&nbsp;</div>

<div class="grid_5 lable-right">
	<label class="form-lbl">生活能力：</label>
</div>
<div class="grid_7">
	<select id="population.abilityLiving.id" name="population.abilityLiving.id"  class="form-select" <s:if test='"view".equals(mode)'>disabled</s:if>>
    	<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@VIABILITY"  defaultValue="${population.abilityLiving.id }"  />
    </select>
</div>

<div class="grid_5 lable-right">
	<label class="form-lbl">就业情况：</label>
</div>
<div class="grid_7">
    <select id="population.employmentSituation.id" name="population.employmentSituation.id"  class="form-select" <s:if test='"view".equals(mode)'>disabled</s:if>>
    	<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@EMPLOYMENT_STATUS"  defaultValue="${population.employmentSituation.id }"  />
    </select>
</div>
<div class='clearLine'>&nbsp;</div>

<div class="grid_5 lable-right">
	<label class="form-lbl">供养情况：</label>
</div>
<div class="grid_7">
	<select id="population.supportSituation.id" name="population.supportSituation.id"  class="form-select" <s:if test='"view".equals(mode)'>disabled</s:if>>
    	<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@SUPPORT_STATUS"  defaultValue="${population.supportSituation.id }"  />
    </select>
</div>
<div class="grid_5 lable-right">
	<label class="form-lbl">月生活费：</label>
</div>
<div class="grid_6">
	<input type="text" name="population.monthLivingExpenses" id="monthLivingExpenses"  class="form-txt {decimal:true,range:[0,999999.99],messages:{decimal:'请输入非负数 ，保留两位位小数点',range: '请输入0至999999.99之间的非负数 '}}" value="${population.monthLivingExpenses}" maxlength="8"
	<s:if test='"view".equals(mode)'>disabled</s:if>/>
</div><div class="grid_1">(元)</div>
<div class='clearLine'>&nbsp;</div>
<script type="text/javascript">
jQuery.validator.addMethod("decimal", function(value, element) {
    var decimal = /^-?\d+(\.\d{1,2})?$/;
    if (value==""){
	     return true;
	 }
    if(value>=0){
    	return this.optional(element) || (decimal.test(value));
    }
    return false;
});
</script>
