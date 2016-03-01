<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<script>
$(document).ready(function(){
	 $('#startCloseCaseDate').dateTimePicker({
		yearRange:'1930:2060',
		timeFormat: 'HH:mm:ss',
		maxDate:'+0y',
		onClose: function (selectedDate) {
        	$("#endCloseCaseDate").datepicker("option", "minDate", selectedDate);
        }
	})
	$('#endCloseCaseDate').dateTimePicker({
		yearRange:'1930:2060',
		timeFormat: 'HH:mm:ss',
		maxDate:'+0y',
		onClose:function(selectedDate) {
       		$("#startCloseCaseDate").datepicker("option", "maxDate",selectedDate);
    	}
	})
});

</script>
<div id="dialog-form" class="container container_24">
        <div class='clearLine'>&nbsp;</div>
        <div class="grid_5 lable-right">
	  		<label class="form-lbl">服务单号：</label> 
	  	</div>
	    <div class="grid_19">
	      <input type="text" id="serialNumber" name="evaluationIssueHandle.serialNumber"  maxlength="64"  class='form-txt {maxlength:64,messages:{maxlength:$.format("服务单号最多可以输入{0}个字符")}}' />
	   </div>
	   <div class='clearLine'>&nbsp;</div>
	   <div class="grid_5 lable-right">
	     	<label class="form-lbl">事件名称：</label>
	   </div>
	  	<div class="grid_7">
	    	<input type="text" id="issueName" name="evaluationIssueHandle.issueName"  maxlength="20"  class="form-txt" />
	  	</div>
       <div class="grid_5 lable-right">
	     	<label class="form-lbl">评分人/粉丝昵称：</label>
	   </div>
	  	<div class="grid_7">
	    	<input type="text" id="scorePerson" name="evaluationIssueHandle.scorePerson"  maxlength="20"  class="form-txt" />
	  	</div>
	   <div class='clearLine'>&nbsp;</div>
	   	<div class="grid_5 lable-right">
		  	<label class="form-lbl">接收时间从：</label> 
		</div>
		<div class="grid_7"> 
			<input type="text" id="startCloseCaseDate" value="<s:date name="evaluationIssueHandle.startCloseCaseDate" format="yyyy-MM-dd HH:mm:ss"/>" readonly="readonly" class="form-txt"/>
		</div>
	  	 <div class="grid_5 lable-right">
	     	<label class="form-lbl">到：</label>
	  	</div>
	  	<div class="grid_7">
  	    	<input type="text" id="endCloseCaseDate" value="<s:date  name="evaluationIssueHandle.endCloseCaseDate" format="yyyy-MM-dd HH:mm:ss"/>" readonly="readonly" class="form-txt"/>
  	   </div>
</div>



