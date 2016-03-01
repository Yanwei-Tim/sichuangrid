<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>


<form id="searchPrimaryOrgForm">
<pop:token />

  <div id="search-condition-form" title="日志查询" class="container container_24">
  
        <div class="grid_4 lable-right">
			<label class="form-lbl">开始日期：</label>
		</div>
		<div class="grid_8">
		  <input type="text" id="conditionOccurFrom" name="scheduleLog.startTime" value="" readonly="readonly" class="form-txt" />			 
		</div>

        <div class="grid_4 lable-right">
			<label class="form-lbl">结束日期：</label>
		</div>
		<div class="grid_8">
			<input type="text" id="conditionOccurEnd" name="scheduleLog.endTime" value="" readonly="readonly" class="form-txt" />		
		</div>
		
		<div class='clearLine'></div>
		
		<div class='clearLine'></div>
	  	<div class="grid_4 lable-right">
			<label class="form-lbl">任务名称：</label>
		</div>
		<div class="grid_8">
			<input type="text" id="log_name" name="scheduleLog.name" class="form-txt" maxlength="30"/>
		</div>
   
	  	<div class="grid_4 lable-right">
			<label class="form-lbl">业务模型名称:</label>
		</div>
		<div class="grid_8">
			<input type="text" id="log_modelName" name="scheduleLog.modelName" class="form-txt" maxlength="30"/>
		</div>
		<div class='clearLine'></div>
 		

  </div>
 
<script type="text/javascript">
	 
  $(document).ready(function(){
	 
   $("#conditionOccurFrom").datePicker({
		yearRange:"1900:2030",
		dateFormat:"yy-mm-dd",
   		maxDate:"+0d",
       	onSelect: function(dateText, inst) {
			if(dateText!=null&&dateText!=""){
				var dateUnit=dateText.split("-");
				var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
				$("#conditionOccurEnd").datepicker("option","minDate",date);
			}
		}
    }); 
	
	$("#conditionOccurEnd").datePicker({
		yearRange:"1900:2030",
		dateFormat:"yy-mm-dd",
    	maxDate:"+0d",
    	timeFormat: 'HH:mm:ss',
   		onSelect: function(dateText, inst) {
			if(dateText!=null&&dateText!=""){
				var dateUnit=dateText.split("-");
				var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
				$("#conditionOccurFrom").datepicker("option", "maxDate",date);
			}
		}
	});
   
   
   
   
   });



</script>











</form>



















