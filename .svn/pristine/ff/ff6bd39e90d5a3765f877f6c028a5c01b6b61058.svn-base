<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp" %>
<form id="searchLowerPartyActivityForm">
<div class="container container_24">
	<div class="grid_4 lable-right">
			<label class="form-lbl">所属网格：</label>
		</div>
		<div class="grid_18">
	    	<input type="text" name="orgInternalCode" id="orgInternalCode" class="form-txt" value="${population.organization.orgName}" readonly/>
		</div>
		
		<div class="grid_4 lable-right">
            <label class="form-lbl">活动时间: 从：</label>
        </div>
        <div class="grid_7">
            <input type="text" id="lowerActivityDate" name="searchPartyOrgActivityVo.activityDate" readonly="readonly" class="form-txt" />
        </div>
        <div class="grid_4" align="center">
            <label class="form-lbl">至&nbsp;&nbsp;</label>
        </div>
        <div class="grid_7">
            <input type="text" id="lowerEndActivityDate" name="searchPartyOrgActivityVo.endActivityDate" readonly="readonly" class="form-txt" />
        </div>

        <div class="grid_4 lable-right">
			<label class="form-lbl">活动主题：</label>
		</div>
		<div class="grid_7">
	    	<input type="text" name="searchPartyOrgActivityVo.activitySubject" id="activitySubject" class="form-txt" />
		</div>

		<div class="grid_4 lable-right">
			<label class="form-lbl">活动地点：</label>
		</div>
		<div class="grid_7">
			<input type="text" id="activityAddr" name="searchPartyOrgActivityVo.activityAddr" class="form-txt" />
		</div>

		<div class="grid_4 lable-right">
			<label class="form-lbl">参与者：</label>
		</div>
		<div class="grid_7">
			<input type="text" id="participants" name="searchPartyOrgActivityVo.participants" class="form-txt" />
		</div>

		<div class="grid_4 lable-right">
			<label class="form-lbl">组织者：</label>
		</div>
		<div class="grid_7">
			<input type="text" id="organizers" name="searchPartyOrgActivityVo.organizers" class="form-txt" />
		</div>

		<div class="grid_4 lable-right">
			<label class="form-lbl">活动内容：</label>
		</div>
		<div class="grid_18">
			<input type="text" id="activeContent" name="searchPartyOrgActivityVo.activeContent" class="form-txt" />
		</div>
</div>
</form>
<script type="text/javascript">
$(function(){
	$('#lowerActivityDate').datePicker({
		yearRange:'1900:2030',
		dateFormat:'yy-mm-dd',
    	maxDate:'+0d',
       	onSelect:function(dateText, inst) {
			if(dateText!=null&&dateText!=''){
				var dateUnit=dateText.split('-');
				var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
				$("#lowerActivityDate").datepicker("option", "minDate",date);
			}
		}
	});

	$('#lowerEndActivityDate').datePicker({
		yearRange:'1900:2030',
		dateFormat:'yy-mm-dd',
        maxDate:'+0d',
        onSelect:function(dateText, inst) {
			if(dateText!=null&&dateText!=''){
				var dateUnit=dateText.split('-');
				var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
				$("#lowerEndActivityDate").datepicker("option", "maxDate",date);
			}
		}
	});

});
</script>
