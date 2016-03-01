<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<div id="floatTypeInfo">
	<div  class="grid_4 lable-right">
  			<label class="form-lbl">政治面貌：</label>
  		</div>
		<div class="grid_7">
		   <select name="population.politicalBackground.id" id="politicalBackground" class="form-txt"
   				<pop:GlobalSettingTag key="@com.tianque.core.globalSetting.util.GlobalSetting@BUSINESS_DEPENDENT_ACTUAL_POPULATION" value="@com.tianque.core.globalSetting.util.GlobalSetting@NOT_ADD_POPULATION">disabled</pop:GlobalSettingTag>
		   >
		   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@POLITICAL_BACKGROUND" defaultValue="${population.politicalBackground.id}" />
			</select>
   		</div>
   		<div  class="grid_4 lable-right">
  			<label class="form-lbl">文化程度：</label>
  		</div>
		<div class="grid_7">
		   <select name="population.schooling.id" id="schooling" class="form-txt"
   				<pop:GlobalSettingTag key="@com.tianque.core.globalSetting.util.GlobalSetting@BUSINESS_DEPENDENT_ACTUAL_POPULATION" value="@com.tianque.core.globalSetting.util.GlobalSetting@NOT_ADD_POPULATION">disabled</pop:GlobalSettingTag>
		   >
		   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@SCHOOLING" defaultValue="${population.schooling.id}" />
			</select>
   		</div>
   		 <div class="clearLine">&nbsp;</div>
		<div  class="grid_4 lable-right">
  			<label class="form-lbl">民族：</label>
  		</div>
		<div class="grid_7">
		   <select name="population.nation.id" id="nation" class="form-txt"
   				<pop:GlobalSettingTag key="@com.tianque.core.globalSetting.util.GlobalSetting@BUSINESS_DEPENDENT_ACTUAL_POPULATION" value="@com.tianque.core.globalSetting.util.GlobalSetting@NOT_ADD_POPULATION">disabled</pop:GlobalSettingTag>
		   >
		   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@NATION" defaultValue="${population.nation.id}" />
			</select>
   		</div>
		<div class="grid_4 lable-right">
   			<label class="form-lb1">婚姻状况：</label>
   		</div>
   		<div class="grid_7">
   			 <select name="population.maritalState.id" id="maritalState" class="form-txt"
   				<pop:GlobalSettingTag key="@com.tianque.core.globalSetting.util.GlobalSetting@BUSINESS_DEPENDENT_ACTUAL_POPULATION" value="@com.tianque.core.globalSetting.util.GlobalSetting@NOT_ADD_POPULATION">disabled</pop:GlobalSettingTag>
   			 >
		   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@MARITAL_STATUS" defaultValue="${population.maritalState.id}" />
			</select>
   		</div>
</div>