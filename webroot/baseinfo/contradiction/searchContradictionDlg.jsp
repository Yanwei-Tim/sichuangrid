<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>

<div id="search-condition-form" title="矛盾纠纷信息查询" class="container container_24">
	<input id="sourceOrganization" name="sourceOrganization" type="hidden" value="${sourceOrganization}" /> 
    <input id="issueTypeDomainName" name="issueTypeDomainName" type="hidden" value="${issueTypeDomainName }"/>
	<div class="grid_5 lable-right">
		<label class="form-lbl">所属网格：</label>
	</div>
	<div class="grid_4 form-left">${sourceOrganization.orgName}</div>
    
    <div class="grid_3 lable-right"></div>
    
	<div class="grid_5 lable-right">
		<label class="form-lbl">来源人 ：</label>
	</div>
	<div class="grid_7 form-left">
		<input type="text" id="conditionFromPerson" name="conditionFromPerson" maxlength="20" value="" class="form-txt" />
	</div>
   	<div class='clearLine'></div>	
    	    		
	<div class="grid_5 lable-right">
		<label class="form-lbl">状态：</label>
	</div>
	<div class="grid_7 form-left">
	   <select id="conditionStatus" name="conditionStatus" class="form-select">
            <option value="">请选择</option>
	        <option value='1'>未受理</option>
	        <option value='2'>处理中</option>
	        <option value='3'>已完成</option>
	   </select>
	</div>	
	<!--  
	<div class="grid_5 lable-right">
		<label class="form-lbl">服务单号：</label>
	</div>
	<div class="grid_7 form-left">
		<input type="text" id="conditionIssueNo" name="conditionIssueNo" maxlength="" value="" class="form-txt" />
    </div>
    -->
	<div class='clearLine'></div>		
	
	<div class="grid_5 lable-right">
		<label class="form-lbl">录入时间  从：</label>
	</div>
	<div class="grid_7 form-left">
		<input type="text" id="conditionInputFrom" name="conditionInputFrom" value="" readonly="readonly" class="form-txt" />
	</div>
	
	<div class="grid_5 lable-right">
		<label class="form-lbl">到 ：</label>
	</div>
	<div class="grid_7 form-left">
		<input type="text" id="conditionInputEnd" name="conditionInputEnd" value="" readonly="readonly" class="form-txt" />
	</div>	
	
	<div class="grid_5 lable-right">
		<label class="form-lbl">时间名称：</label>
	</div>
	<div class="grid_19 form-left">
		<input type="text" id="conditionName" name="conditionName" maxlength="50" value="" class="form-txt" />
	</div>
	<div class='clearLine'></div>	
	
</div>

<script type="text/javascript">
$(document).ready(function(){
	$("#conditionInputFrom").datePicker({
		yearRange:"1900:2030",
		dateFormat:"yy-mm-dd",
   		maxDate:"+0d",
       	onSelect: function(dateText, inst) {
			if(dateText!=null&&dateText!=""){
				var dateUnit=dateText.split("-");
				var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
				$("#conditionInputEnd").datepicker("option","minDate",date);
			}
		}
    }); 

	$("#conditionInputEnd").datePicker({
		yearRange:"1900:2030",
		dateFormat:"yy-mm-dd",
    	maxDate:"+0d",
   		onSelect: function(dateText, inst) {
			if(dateText!=null&&dateText!=""){
				var dateUnit=dateText.split("-");
				var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
				$("#conditionInputFrom").datepicker("option", "maxDate",date);
			}
		}
	});

});
</script>