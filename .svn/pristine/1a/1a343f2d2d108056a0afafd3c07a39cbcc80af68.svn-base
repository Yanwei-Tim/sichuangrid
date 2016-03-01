<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>

<div id="dialog-form" title="机关党组织表" class="container container_24">
	
	<form id="searchMaintainForm" method="post" action="">
		<div class="grid_4 lable-right">
			<em class="form-req"></em>
			<label class="form-lbl">党组织类型：</label>
	 	</div>
		<div class="grid_7">
			<select name="searchOrgansPartyVo.type" id="organsPartyType"  class='form-txt'>
		   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@PARTYORGTYPE"  defaultValue="${organsParty.type.id}" />
			</select>
		</div>
		
		<div class="grid_4 lable-right">
			<em class="form-req"></em>
			<label class="form-lbl">所属部门：</label>
	 	</div>
		<div class="grid_7">
			<select name="searchOrgansPartyVo.department" id="department" class='form-txt'>
		   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@BELONG_ORG"  defaultValue="${organsParty.department.id}" />
			</select>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<em class="form-req"></em>
			<label class="form-lbl">党组织名称：</label>
	 	</div>
		<div class="grid_18">
			<input type="text" name="searchOrgansPartyVo.name" id="name" style="width: 99%" maxlength="20" class='form-txt' />
		</div>
		<div class='clearLine'>&nbsp;</div>
		
	 	<div class="grid_4 lable-right">
			<label class="form-lbl">联系人：</label>
	 	</div>
		<div class="grid_7">
			<input type="text" name="searchOrgansPartyVo.contact" id="contact"  maxlength="20" class='form-txt'/>
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">联系电话：</label>
	 	</div>
		<div class="grid_7">
			<input type="text" name="searchOrgansPartyVo.telephone" id="telephone"  maxlength="15" class='form-txt'/>
		</div>
		<div class='clearLine'>&nbsp;</div>
	 	<div class="grid_4 lable-right">
			<label class="form-lbl">备注：</label>
	 	</div>
		<div class="grid_18">
			<textarea id="remark" style="width: 99%" name="searchOrgansPartyVo.remark" class='form-txt' rows="4"></textarea>
		</div>
	</form>
	
</div>
