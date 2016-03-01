<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>

<div id="dialog-form" title="党组织报到" class="container container_24">
	<form id="searchPartyOrgReportForm" method="post" action="">
	
		<input type="hidden" id="type" name="searchPartyOrgReportVo.type" value="${param.type}"/>
		<input type="hidden" id="organizationId" name="organization.id" value="${param.orgId}"/>
		<input type="hidden" id="organization" name="searchPartyOrgReportVo.orgId" value="${param.orgId}"/>
		
	 	<div class="grid_4 lable-right">
			<label class="form-lbl">党组织类别：</label>
	 	</div>
		<div class="grid_7">
			<select name="searchPartyOrgReportVo.partyOrgType" id="partyOrgType" class='form-txt'>
		   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@PARTYORGTYPE"/>
			</select>
		</div>
	 	<div class="grid_4 lable-right">
			<label class="form-lbl">单位名称：</label>
	 	</div>
		<div class="grid_7">
			<input type="text" name="searchPartyOrgReportVo.name" id="name"  maxlength="30" class='form-txt'/>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">联系人：</label>
	 	</div>
		<div class="grid_7">
			<input type="text" name="searchPartyOrgReportVo.contractor" id="contractor"  maxlength="30" class='form-txt'/>
		</div>
			<div class="grid_4 lable-right">
			<label class="form-lbl">联系电话：</label>
	 	</div>
		<div class="grid_7">
			<input type="text" name="searchPartyOrgReportVo.telephone" id="telephone"  maxlength="11" class='form-txt'/>
		</div>
		<div class='clearLine'>&nbsp;</div>
		
	 	<div class="grid_4 lable-right">
			<label class="form-lbl">地址：</label>
	 	</div>
		<div class="grid_18">
			<input type="text" name="searchPartyOrgReportVo.address" id="address"  maxlength="30" class="form-txt"/>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
  			<label class="form-lb1">注销状态：</label>
	   	</div>
	   	<div class="grid_8">
			<select id="isEmphasis" name="searchPartyOrgReportVo.isEmphasis" class="form-txt">
				<option value="-1" selected="selected">全部</option>
				<option value="0">现在关注</option>
				<option value="1">曾经关注</option>
			</select>
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		<div class="grid_4 lable-right">
			<label class="form-lb1">认领服务项目：</label>
		</div>
		<div  class="grid_7" style="width: 450px;height: 100px;">
			<input type="hidden"  id="claimServiceProjectIds" name="searchPartyOrgReportVo.claimServiceProjectIds" maxlength="" class="form-txt"/> 
			<textarea rows="5" cols="30" id="claimProjectName"  class="form-txt" style="width: 450px;" readonly></textarea>
		</div>
		<input type="button" id="change" value="选择" style="margin-left: 10px;margin-top: 13px;width: 50px;height: 25px;text-align: center;"/>
		<div style="margin-top: 10px;" class='clearLine'>&nbsp;</div>
		<div class="content" style="width: 100%;height: 10px;padding-top: 10px;">
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		
	 	<div class="grid_4 lable-right">
			<label class="form-lbl">备注：</label>
	 	</div>
		<div class="grid_18">
			<textarea rows="5" name="searchPartyOrgReportVo.remark" style="width: 99%" class='form-txt' ></textarea>
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		
	</form>
	
</div>
<script type="text/javascript">


//添加认领服务项目
$("#change").click(function(){
	var orgid = $("#organizationId").val();
	$("#claimProjectNameDialog").createDialog({
		title:"添加认领服务项目",
		width: 600,
		height: 300,
		url:'${path}/partyBuilding/doubleRegActivities/partyorgReportManage/addClaimProjectDlg.jsp?mode=addClaimProject&organization.id='+orgid+'&dailogName=claimProjectNameDialog',
		buttons: {
			"确定" : function(){
				  $(this).dialog("close");
			}
		}
	});
});


</script>
