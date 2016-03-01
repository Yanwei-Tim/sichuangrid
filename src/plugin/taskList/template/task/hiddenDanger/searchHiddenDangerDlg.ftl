<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp" />
<div id="search-condition-form" title="治安隐患查询" class="container container_24">

    <div class="grid_8 lable-right">
		<label class="form-lbl">异常类型：</label>
	</div>
	<div class="grid_8">
    <select  id="conditionExceptionType" onchange="setExceptionType(value)" class="form-txt" <@s.if test="!'all'.equals(exceptionType)">disabled="true"</@s.if> >
		   <@pop.OptionTag name="@com.tianque.domain.property.PropertyTypes@DANGER_EXCEPTION_TYPE" />
		</select>
	</div>
	<div class='clearLine'></div>
	<div class="grid_8 lable-right">
		<label class="form-lbl">异常情况：</label>
	</div>
	<div class="grid_8">
			<input type="text" id="conditionSituation" maxlength="18" class="form-txt" />
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
  		<label class="form-lbl">地点：</label>
  	</div>
	<div class="grid_8">
		<input type="text" id="conditionAddress" class="form-txt" maxlength="50"/>
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
   	<div class="grid_8 lable-right">
		<label class="form-lbl">是否回复：</label>
	</div>
	<div class="grid_5">
		<select id="hasReplay"  class="form-select" >
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

	var $exceptionType = "<@s.property value='@com.tianque.plugin.taskList.constants.Constants@getHiddenDangerMap(exceptionType)' />";
	var $option = $("#conditionExceptionType option");
	for(var i=0;i<$option.length;i++){
		if($($option[i]).text() === $exceptionType){
			$("#conditionExceptionType option").eq(i).attr("selected",true);
		}
	}
});

function setExceptionType(value){
	$("input[name='hiddenDanger.exceptionType.id']").val(value);
} 
</script>
