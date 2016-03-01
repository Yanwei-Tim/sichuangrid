<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<div class="container container_24">
	<input id="mode"	type="hidden" name="mode" value="${(mode)!}" />
	 <div  class="grid_4 lable-right" >
		<em class="form-req"></em>
			<label class="form-lbl">服务器名称： </label>
		</div>
		<div class="grid_7" >
		    	<input type="text" name="scheduleJobException.appName" id="appName" 
		    	<@s.if test='"view".equals(mode)'> readonly="readonly"</@s.if>
		    	class="form-txt"  value='${(scheduleJobException.appName)!}'/>
  		</div>
  		<div class="grid_1">
	  </div>
	   <div  class="grid_4 lable-right" >
		<em class="form-req"></em>
			<label class="form-lbl">调度名称： </label>
		</div>
		<div class="grid_7" >
		    	<input type="text" name="scheduleJobException.jobName" id="jobName" 
		    	<@s.if test='"view".equals(mode)'> readonly="readonly"</@s.if>
  				 value="${(scheduleJobException.jobName)!}"
  				class="form-txt" />
  		</div>
  		<div class="grid_1">
	  </div>
	   <div class='clearLine'>&nbsp;</div>
	      <div  class="grid_4 lable-right" >
		<em class="form-req"></em>
			<label class="form-lbl">维度表名： </label>
		</div>
		<div class="grid_7" >
		    	<input type="text" name="scheduleJobException.tableName" id="tableName"
		    	 <@s.if test='"view".equals(mode)'> readonly="readonly"</@s.if>
  				 value="${(scheduleJobException.tableName)!}"
  				class="form-txt"/>
  		</div>
  		<div class="grid_1">
	  </div>
	   
	    <div  class="grid_4 lable-right" >
		<em class="form-req"></em>
			<label class="form-lbl">业务模型名称： </label>
		</div>
		<div class="grid_7">
	    	<input type="text" value="${(scheduleJobException.modelName)!}" readonly="readonly"value="自定义" class="form-txt" />
  		</div>
  		<div class="grid_1">
	  </div>
	   
	 
	    <div class='clearLine'>&nbsp;</div>
	  <div  class="grid_4 lable-right" >
		<em class="form-req"></em>
			<label class="form-lbl">异常时间： </label>
		</div>
		<div class="grid_7">
			<input type="text" name="scheduleJobException.errorTime" id="errorTime" maxlength="20" 
		    	 <@s.if test='"view".equals(mode)'> readonly="readonly"</@s.if>
  				 value='<@s.date name="scheduleJobException.errorTime" format="yyyy-MM-dd" />'
  				class="form-txt"/>
	 	</div> 
	 	 <div class='clearLine'>&nbsp;</div>
	 	
	    <div  class="grid_4 lable-right" >
		<em class="form-req"></em>
			<label class="form-lbl">异常描述： </label>
		</div>
		<div class="grid_19 heightAuto" >
			<textarea  rows="6" class="form-txt"  readonly="readonly" id="description"   style="width:98.5%">${(scheduleJobException.description)! }</textarea>
  		</div>
</div>