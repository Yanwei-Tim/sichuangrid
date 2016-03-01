<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>

<div id="dialog-form" title="提供的服务项目" class="container container_24">
	<form id="searchServiceProjectForm" method="post" action="">
	
		<input type="hidden" id="organizationId" name="organization.id" value="${param.orgId}"/>
		<input type="hidden" id="organization" name="searchServiceProjectVo.orgId" value="${param.orgId}"/>
		
	 	<div class="grid_4 lable-right">
			<label class="form-lbl">项目名称：</label>
	 	</div>
		<div class="grid_7">
			<input type="text" name="searchServiceProjectVo.projectName" id="projectName"  maxlength="30" class='form-txt'/>
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">拟认领数：</label>
	 	</div>
		<div class="grid_7">
			<input type="text" name="searchServiceProjectVo.claimPlans" id="claimPlans"  maxlength="30" class='form-txt'/>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">项目内容：</label>
	 	</div>
		<div class="grid_18 heightAuto" >
			<textarea  rows="5" name="searchServiceProjectVo.projectContent" style="width: 99%" class='form-txt' ></textarea>
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		<div class="grid_4 lable-right">
			<label class="form-lbl">联系人：</label>
	 	</div>
		<div class="grid_7">
			<input type="text" name="searchServiceProjectVo.contractor" id="contractor"  maxlength="30" class='form-txt'/>
		</div>
			<div class="grid_4 lable-right">
			<label class="form-lbl">联系电话：</label>
	 	</div>
		<div class="grid_7">
			<input type="text" name="searchServiceProjectVo.telephone" id="telephone"  maxlength="11" class='form-txt'/>
		</div>
		<div class='clearLine'>&nbsp;</div>
		
	 	<div class="grid_4 lable-right">
			<label class="form-lbl">备注：</label>
	 	</div>
		<div class="grid_18 heightAuto" >
			<textarea rows="5" name="searchServiceProjectVo.remark" style="width: 99%" class='form-txt' ></textarea>
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		
	</form>
	
</div>
<script type="text/javascript">

</script>
