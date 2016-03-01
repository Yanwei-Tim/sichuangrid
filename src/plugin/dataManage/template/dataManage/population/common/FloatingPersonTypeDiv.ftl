<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>

<div id="floatTypeInfo">
		<div  class="grid_4 lable-right">
			<em class="form-req">*</em>
  			<label class="form-lbl">政治面貌：</label>
  		</div>
		<div class="grid_8">
		   <select name="population.politicalBackground.id" id="politicalBackground2" class='form-txt {required:true,messages:{required:"请选择政治面貌"}}'
   				<@pop.GlobalSettingTag key="@com.tianque.core.globalSetting.util.GlobalSetting@BUSINESS_DEPENDENT_ACTUAL_POPULATION" value="@com.tianque.core.globalSetting.util.GlobalSetting@NOT_ADD_POPULATION">disabled</@pop.GlobalSettingTag>
		   >
		   		<@pop.OptionTag name="@com.tianque.domain.property.PropertyTypes@POLITICAL_BACKGROUND" defaultValue="${(population.politicalBackground.id)!}" />
			</select>
   		</div>
   		<div  class="grid_4 lable-right">
   			<em class="form-req">*</em>
  			<label class="form-lbl">文化程度：</label>
  		</div>
		<div class="grid_8">
		   <select name="population.schooling.id" id="schooling2" class='form-txt {required:true,messages:{required:"请选择文化程度"}}'
   				<@pop.GlobalSettingTag key="@com.tianque.core.globalSetting.util.GlobalSetting@BUSINESS_DEPENDENT_ACTUAL_POPULATION" value="@com.tianque.core.globalSetting.util.GlobalSetting@NOT_ADD_POPULATION">disabled</@pop.GlobalSettingTag>
		   >
		   		<@pop.OptionTag name="@com.tianque.domain.property.PropertyTypes@SCHOOLING" defaultValue="${(population.schooling.id)!}" />
			</select>
   		</div>
   		 <div class="clearLine">&nbsp;</div>
   		<div  class="grid_4 lable-right">
   			<em class="form-req">*</em>
  			<label class="form-lbl">民族：</label>
  		</div>
		<div class="grid_8">
		   <select name="population.nation.id" id="nation2" class='form-txt {required:true,messages:{required:"请选择民族"}}'
   				<@pop.GlobalSettingTag key="@com.tianque.core.globalSetting.util.GlobalSetting@BUSINESS_DEPENDENT_ACTUAL_POPULATION" value="@com.tianque.core.globalSetting.util.GlobalSetting@NOT_ADD_POPULATION">disabled</@pop.GlobalSettingTag>
		   >
		   		<@pop.OptionTag name="@com.tianque.domain.property.PropertyTypes@NATION" defaultValue="${(population.nation.id)!}" />
			</select>
   		</div>
   			<div class="grid_4 lable-right">
   			<em class="form-req">*</em>
   			<label class="form-lb1">婚姻状况：</label>
   		</div>
   		<div class="grid_8">
   			 <select name="population.maritalState.id" id="maritalState2" class='form-txt {required:true,messages:{required:"请选择婚姻状况"}}'
   				<@pop.GlobalSettingTag key="@com.tianque.core.globalSetting.util.GlobalSetting@BUSINESS_DEPENDENT_ACTUAL_POPULATION" value="@com.tianque.core.globalSetting.util.GlobalSetting@NOT_ADD_POPULATION">disabled</@pop.GlobalSettingTag>
   			 >
		   		<@pop.OptionTag name="@com.tianque.domain.property.PropertyTypes@MARITAL_STATUS" defaultValue="${(population.maritalState.id)!}" />
			</select>
   		</div>
   		
   		<div class="clearLine">&nbsp;</div>
</div>