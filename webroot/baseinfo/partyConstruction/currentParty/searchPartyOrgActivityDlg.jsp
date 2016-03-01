<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<jsp:include page="/includes/baseInclude.jsp" />
<form id="searchPartyOrgActivityForm">
<div class="container container_24">
	<div class="grid_4 lable-right">
			<label class="form-lbl">所属网格：</label>
		</div>
		<div class="grid_20">
			<input type="hidden" name="orgId" id="orgId" class="form-txt" value="${orgId}"/>
	    	<input type="text" name="orgInternalCode" id="orgInternalCode" class="form-txt" value="${population.organization.orgName}" readonly/>
		</div>
		<div class="grid_4 lable-right">
            <label class="form-lbl">活动时间 从：</label>
        </div>
        <div class="grid_8">
            <input type="text" id="activityDate" name="searchPartyOrgActivityVo.activityDate" readonly="readonly" class="form-txt" />
        </div>
        <div class="grid_4" align="center">
            <label class="form-lbl">至&nbsp;&nbsp;</label>
        </div>
        <div class="grid_8">
            <input type="text" id="endActivityDate" name="searchPartyOrgActivityVo.endActivityDate" readonly="readonly" class="form-txt" />
        </div>

        <div class="grid_4 lable-right">
			<label class="form-lbl">活动主题：</label>
		</div>
		<div class="grid_8">
	    	<input type="text" name="searchPartyOrgActivityVo.activitySubject" id="activitySubject" class="form-txt" />
		</div>

		<div class="grid_4 lable-right">
			<label class="form-lbl">活动地点：</label>
		</div>
		<div class="grid_8">
			<input type="text" id="activityAddr" name="searchPartyOrgActivityVo.activityAddr" class="form-txt" />
		</div>

		<div class="grid_4 lable-right">
			<label class="form-lbl">参与者：</label>
		</div>
		<div class="grid_8">
			<input type="text" id="participants" name="searchPartyOrgActivityVo.participants" class="form-txt" />
		</div>

		<div class="grid_4 lable-right">
			<label class="form-lbl">组织者：</label>
		</div>
		<div class="grid_8">
			<input type="text" id="organizers" name="searchPartyOrgActivityVo.organizers" class="form-txt" />
		</div>

		<div class="grid_4 lable-right">
			<label class="form-lbl">活动内容：</label>
		</div>
		<div class="grid_8">
			<input type="text" id="activeContent" name="searchPartyOrgActivityVo.activeContent" class="form-txt" />
		</div>
</div>
</form>
<script type="text/javascript">

$("#activityDate,#endActivityDate").datepickers();

</script>
