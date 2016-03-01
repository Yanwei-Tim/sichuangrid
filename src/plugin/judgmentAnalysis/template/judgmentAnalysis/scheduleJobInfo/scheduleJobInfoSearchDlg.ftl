<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>


<form id="searchPrimaryOrgForm">
<pop:token />

  <div id="search-condition-form" title="日志查询" class="container container_24">
   
		<div class='clearLine'></div>
		
	  	<div class="grid_4 lable-right">
			<label class="form-lbl">执行类型：</label>
		</div>
		<div class="grid_8">
	       <select id="jobInfo_type" name="scheduleJobInfo.type"  class="form-txt" >
			   <option value=""></option>
			   <option value="0">SQL</option>
			   <option value="1">JAVA</option>				 
			</select>			 
		</div>
   
	  	<div class="grid_4 lable-right">
			<label class="form-lbl">SQL类型：</label>
		</div>
		<div class="grid_8">			
            <select id="jobInfo_sqlType" name="scheduleJobInfo.sqlType" class="form-txt"  >
			   <option value=""></option>
			   <option value="1">新增</option>
			   <option value="2">查询</option>
			   <option value="3">修改</option>
			   <option value="4">删除</option>				 
			</select>
		</div>
		
		<div class='clearLine'></div>
 		

  </div>
 
<script type="text/javascript">
	 
  $(document).ready(function(){
	 
  
   
   
   });



</script>











</form>



















