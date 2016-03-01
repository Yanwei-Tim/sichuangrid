<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>


<div class="container container_24">
	<form id="searchServiceRecordForm" method="post" action="" >
	<input id="serviceRecordOrgId" type="hidden" name="serviceRecord.organization.id" />
	<@pop.token />
		<div class="grid_4 lable-right">
   			<label class="form-lb1">记录新增时间：</label>
   		</div>
   		<div class="grid_1"><label>&nbsp;&nbsp;从</label></div>
   		<div class="grid_8">
   			<input type="text" id="recordAddDateStart" name="serviceRecordVo.recordAddDateStart" readonly="readonly" class="form-txt"/>
   		</div>
   		<div class="grid_1"><label>&nbsp;&nbsp;至</label></div>
   		<div class="grid_8">
   			<input type="text" id="recordAddDateEnd" name="serviceRecordVo.recordAddDateEnd" readonly="readonly" class="form-txt"/>
   		</div>
   		<div class='clearLine'>&nbsp;</div>
	
		<div class="grid_4 lable-right">
   			<label class="form-lb1">服务时间：</label>
   		</div>
   		<div class="grid_1"><label>&nbsp;&nbsp;从</label></div>
   		<div class="grid_8">
   			<input type="text" id="occurDateStart" name="serviceRecordVo.occurDateStart" readonly="readonly" class="form-txt"/>
   		</div>
   		<div class="grid_1"><label>&nbsp;&nbsp;至</label></div>
   		<div class="grid_8">
   			<input type="text" id="occurDateEnd" name="serviceRecordVo.occurDateEnd" readonly="readonly" class="form-txt"/>
   		</div>
   		<div class='clearLine'>&nbsp;</div>

		<div class="grid_4 lable-right">
   			<label class="form-lb1">服务地点：</label>
   		</div>
   		<div class="grid_18">
   			<input type="text" name="serviceRecordVo.occurPlace" class="form-txt" style="width: 99%" />
   		</div>
   		<div class='clearLine'>&nbsp;</div>
   		
		<div class="grid_4 lable-right">
			<label class="form-lb1">记录所属人：</label>
   		</div>
   		<div class="grid_18">
			<input type="text" name="serviceRecordVo.serviceMembers" class="form-txt" style="width: 99%" />
    	</div>
		<div class='clearLine'>&nbsp;</div>
		
		<div class="grid_4 lable-right">
			<label class="form-lb1">服务参与人：</label>
   		</div>
   		<div class="grid_18">
			<input type="text" name="serviceRecordVo.serviceJoiners" class="form-txt" style="width: 99%" />
    	</div>
		<div class='clearLine'>&nbsp;</div>
		<@s.if test="objectIds==null">
	    	<div class="grid_4 lable-right">
	    		<label class="form-lb1">服务对象：</label>
	   		</div>
	   		<div class="grid_18">
				<input type="text" name="serviceRecordVo.serviceObjects" class="form-txt" style="width: 99%" />
	    	</div>    	
	    	<div class='clearLine'>&nbsp;</div>
    	</@s.if>
		<@s.if test="showRecordType==1">
			<div class="grid_4 lable-right">
	    		<label class="form-lb1">记录类型：</label>
	    	</div>
	    	<div class="grid_18">
		    	<input type="radio" class="form-btn" name="serviceRecordVo.recordType" value='0' />&nbsp;&nbsp;排查类&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" class="form-btn" name="serviceRecordVo.recordType" value='1' />&nbsp;&nbsp;整改类			
			</div> 
		</@s.if>
		<div class="grid_4 lable-right">
   			<label class="form-lb1">服务内容：</label>
   		</div>
   		<div class="grid_18">
			<input type="text" name="serviceRecordVo.serviceContent" class="form-txt" style="width: 99%" />
    	</div>
		<div class='clearLine'>&nbsp;</div>
   	</form>
</div>
<script type="text/javascript">
$(document).ready(function(){
	//$("#serviceRecordOrgId").val(getCurrentOrgId());
	$("#occurDateStart, #occurDateEnd").datePicker({
		yearRange: '2000:2030',
		defaultDate: "+1d",
		onSelect: function( selectedDate ) {
			var option = this.id == "occurDateStart" ? "minDate" : "maxDate",
				instance = $( this ).data( "datepicker" ),
				date = $.datepicker.parseDate(
						instance.settings.dateFormat ||
						$.datepicker._defaults.dateFormat,
						selectedDate, instance.settings );
			$("#occurDateStart, #occurDateEnd").not( this ).datepicker( "option", option, date);
		}
	});
	$("#recordAddDateStart, #recordAddDateEnd").datePicker({
		yearRange: '2000:2030',
		defaultDate: "+1d",
		onSelect: function( selectedDate ) {
			var option = this.id == "recordAddDateStart" ? "minDate" : "maxDate",
				instance = $( this ).data( "datepicker" ),
				date = $.datepicker.parseDate(
						instance.settings.dateFormat ||
						$.datepicker._defaults.dateFormat,
						selectedDate, instance.settings );
			$("#recordAddDateStart, #recordAddDateEnd").not( this ).datepicker( "option", option, date);
		}
	});
})
</script>