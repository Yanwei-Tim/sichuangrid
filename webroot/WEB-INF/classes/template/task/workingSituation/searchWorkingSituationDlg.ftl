<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp" />
<div id="search-condition-form" title="民警领带下开展工作情况查询" class="container container_24">

	<div class="grid_8 lable-right">
  		<label class="form-lbl">民警姓名：</label>
  	</div>
	<div class="grid_8">
		<input type="text" id="conditionPoliceName" class="form-txt" maxlength="100"/>
   	</div>
   	<div class='clearLine'></div>
   	<div class="grid_8 lable-right">
  		<label class="form-lbl">身份证号码：</label>
  	</div>
	<div class="grid_8">
		<input type="text" id="idCard" class="form-txt" maxlength="100"/>
   	</div>
   	<div class='clearLine'></div>
   	<div class="grid_8 lable-right">
  		<label class="form-lbl">电话号码：</label>
  	</div>
	<div class="grid_8">
		<input type="text" id="phone" class="form-txt" maxlength="100"/>
   	</div>
   	<div class='clearLine'></div>
    <div class="grid_8 lable-right">
		<label class="form-lbl">工作内容：</label>
	</div>
	<div class="grid_8">
    <select  id="conditionWorkcontent" class="form-txt" >
		   <@pop.OptionTag name="@com.tianque.domain.property.PropertyTypes@WORKING_CONTENT_TYPE" />
		</select>
	</div>
	<div class='clearLine'></div>
	<div  class="grid_8 lable-right">
  		<label class="form-lbl">发生起始时间：</label>
  	</div>
	<div class="grid_8">
		<input type="text" id="conditionStartDate" class="form-txt" readonly="readonly"/>
   	</div>
   	 	<div class='clearLine'></div>
   		<div class="grid_8 lable-right">
		<label class="form-lbl">发生截止时间：</label>
	</div>
	<div class="grid_8">
			<input type="text" id="conditionEndDate" class="form-txt" readonly="readonly"/>
   	</div>
   	<div class='clearLine'></div>
   	<div  class="grid_8 lable-right">
  		<label class="form-lbl">签收人姓名：</label>
  	</div>
	<div class="grid_8">
		<input type="text" id="conditionSignName" class="form-txt" maxlength="100"/>
   	</div>
   	<div class='clearLine'></div>
	<div  class="grid_8 lable-right">
  		<label class="form-lbl">签收起始时间：</label>
  	</div>
	<div class="grid_8">
		<input type="text" id="conditionStartSign" class="form-txt" readonly="readonly"/>
   	</div>
   	 	<div class='clearLine'></div>
   		<div class="grid_8 lable-right">
		<label class="form-lbl">签收截止时间：</label>
	</div>
	<div class="grid_8">
			<input type="text" id="conditionEndSign" class="form-txt" readonly="readonly"/>
   	</div>

	
	<div class='clearLine'></div>
	
	<div class="grid_8 lable-right">
				<label class="form-lbl">是否签收：</label>	
	</div>
	<div class="grid_8">
			<select id="conditionSign" name="conditionSign" class="form-select" >
			        <option value=""></option>
					<option value="0">否</option>
					<option value="1">是</option>
				</select>
     </div>

	<div class='clearLine'></div>
</div>
<script type="text/javascript">
$(document).ready(function(){

	$('#conditionStartDate').datePicker({
		yearRange: '1900:2030',
		dateFormat:'yy-mm-dd',
        maxDate:'+0d',
        onSelect: function(dateText, inst) {
		if(dateText!=null&&dateText!=''){
			var dateUnit=dateText.split('-');
			var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
			$("#conditionStartDate").datepicker("option", "minDate",date);
			}
		}
	});
	
	$('#conditionEndDate').datePicker({
		yearRange: '1900:2030',
		dateFormat:'yy-mm-dd',
        maxDate:'+0d',
        onSelect: function(dateText, inst) {
		if(dateText!=null&&dateText!=''){
			var dateUnit=dateText.split('-');
			var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
			$("#conditionEndDate").datepicker("option", "minDate",date);
			}
		}
	});
	
	$('#conditionStartSign').datePicker({
		yearRange: '1900:2030',
		dateFormat:'yy-mm-dd',
        maxDate:'+0d',
        onSelect: function(dateText, inst) {
		if(dateText!=null&&dateText!=''){
			var dateUnit=dateText.split('-');
			var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
			$("#conditionStartSign").datepicker("option", "minDate",date);
			}
		}
	});
	
	$('#conditionEndSign').datePicker({
		yearRange: '1900:2030',
		dateFormat:'yy-mm-dd',
        maxDate:'+0d',
        onSelect: function(dateText, inst) {
		if(dateText!=null&&dateText!=''){
			var dateUnit=dateText.split('-');
			var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
			$("#conditionEndSign").datepicker("option", "minDate",date);
			}
		}
	});

	
});
</script>
