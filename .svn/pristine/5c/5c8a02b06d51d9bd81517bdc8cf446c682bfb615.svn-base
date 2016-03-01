<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<jsp:include page="/includes/baseInclude.jsp" />
<div class="container container_24">
<form id="searchActivityRecordForm" method="post" action="">
	<div class="grid_4 lable-right">
			<label class="form-lbl">所属网格：</label>
		</div>
		<div class="grid_20">
			<input type="hidden" name="orgId" id="orgId" class="form-txt" value="${orgId}"/>
			<input type="hidden" name="activityRecordsVo.organizationType" id="organizationType" class="form-txt" value="${activityRecordsVo.organizationType}"/>
	    	<input type="hidden" name="activityRecordsVo.organizationId" id="" class="form-txt" value="${activityRecordsVo.organizationId}"/>
	    	<input type="text" name="orgInternalCode" id="orgInternalCode" class="form-txt" value="${activityRecordsVo.organization.orgName}" readonly/>
		</div>
		<div class="grid_4 lable-right">
            <label class="form-lbl">活动时间 从：</label>
        </div>
        <div class="grid_8">
            <input type="text" id="activityDateStart" name="activityRecordsVo.activityDateStart" readonly="readonly" class="form-txt" />
        </div>
        <div class="grid_4" align="center">
            <label class="form-lbl">至&nbsp;&nbsp;</label>
        </div>
        <div class="grid_8">
            <input type="text" id="activityDateEnd" name="activityRecordsVo.activityDateEnd" readonly="readonly" class="form-txt" />
        </div>

        <div class="grid_4 lable-right">
			<label class="form-lbl">活动主题：</label>
		</div>
		<div class="grid_8">
	    	<input type="text" name="activityRecordsVo.activityTheme" id="activityTheme" class="form-txt" />
		</div>

		<div class="grid_4 lable-right">
			<label class="form-lbl">活动地点：</label>
		</div>
		<div class="grid_8">
			<input type="text" id="activityPlace" name="activityRecordsVo.activityPlace" class="form-txt" />
		</div>

		<div class="grid_4 lable-right">
			<label class="form-lbl">参与者：</label>
		</div>
		<div class="grid_8">
			<input type="text" id="participant" name="activityRecordsVo.participant" class="form-txt" />
		</div>

		<div class="grid_4 lable-right">
			<label class="form-lbl">组织者：</label>
		</div>
		<div class="grid_8">
			<input type="text" id="organizers" name="activityRecordsVo.organizer" class="form-txt" />
		</div>

		<div class="grid_4 lable-right">
			<label class="form-lbl">活动内容：</label>
		</div>
		<div class="grid_8">
			<input type="text" id="details" name="activityRecordsVo.details" class="form-txt" />
		</div>
</form>
</div>
<script type="text/javascript">

$("#activityDateEnd,#activityDateStart").datepickers();

</script>
