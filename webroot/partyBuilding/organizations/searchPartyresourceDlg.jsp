<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>

<div id="dialog-form" title="区域内主要党组织资源" class="container container_24">
	
	<form id="searchPartyresourceForm" method="post" action="">
		<div class="grid_4 lable-right">
			<label class="form-lbl">组织名称：</label>
	 	</div>
		<div class="grid_20">
			<input type="text" name="searchPartyresourceVo.name" id="name"  maxlength="50" class='form-txt '/>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">负责人：</label>
	 	</div>
		<div class="grid_8">
			<input type="text" name="searchPartyresourceVo.manager" id="manager"  maxlength="20" class='form-txt '/>
		</div>
		
	 	<div class="grid_4 lable-right">
			<label class="form-lbl">联系电话：</label>
	 	</div>
		<div class="grid_8">
			<input type="text" name="searchPartyresourceVo.telephone" id="telephone"  maxlength="15" style="width: 93%" class='form-txt'/>
		</div>
		<div class='clearLine'>&nbsp;</div>
	 	<div class="grid_4 lable-right">
			<label class="form-lbl">地点：</label>
	 	</div>
		<div class="grid_20">
			<input type="text" name="searchPartyresourceVo.address" id="address"  maxlength="50" class="form-txt"/>
		</div>
	</form>
	
</div>
<script type="text/javascript">
</script>


