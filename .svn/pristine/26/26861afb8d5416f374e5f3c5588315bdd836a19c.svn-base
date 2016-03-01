<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<div id="dialog-form" title="医院信息" class="container container_24">
		<input type="hidden" name="hospital.orgInternalCode" value="${hospital.orgInternalCode}" id="hospital.orgInternalCode">
		<div class="grid_4 lable-right">
			<label class="form-lbl">医院名称：</label>
		</div>
		<div class="grid_12">
			<input id="hospital.hospitalName" type="text" name="hospital.hospitalName" class="form-txt"
			value='${hospital.hospitalName}' maxlength="30" />
		</div>
		
		<div class="grid_3 lable-right"><label class="form-lbl">医院等级：</label></div>
		<div class="grid_5">
			<select name="hospital.level.id" id="hospital.level.id" class="form-txt">
				<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@HOSPITAL_LEVEL" defaultValue="${hospital.level.id}" />
			</select>
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		<div class="grid_4 lable-right">
			<label class="form-lbl">医院别名：</label>
		</div>
		<div class="grid_12">
			<input id="hospital.anotherName" type="text" name="hospital.anotherName" class="form-txt"
			value='${hospital.anotherName}' maxlength="30" />
		</div>
		<div class="grid_3 lable-right">
			<label class="form-lbl">医院性质：</label>
		</div>
		<div class="grid_5">
			<select name="hospital.kind.id" id="hospital.kind.id" class="form-txt">
				<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@HOSPITALS_KIND" defaultValue="${hospital.kind.id}" />
			</select>
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		<div class="grid_4 lable-right">
			<label class="form-lbl">医院地址： </label>
		</div>
		<div class="grid_12">
			<input id="hospital.address" type="text" name="hospital.address" class="form-txt"
			value='${hospital.address}' maxlength="50" />
		</div>
		
		<div class="grid_3 lable-right">
			<label class="form-lbl">医院类型：</label>
		</div>
		<div class="grid_5">
			<select name="hospital.type.id" id="hospital.type.id" class="form-txt">
				<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@HOSPITALS_TYPE" defaultValue="${hospital.type.id}" />
			</select>
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		<div class="grid_4 lable-right">
			<label class="form-lbl">所属单位：</label>
		</div>
		<div class="grid_12">
			<input id="hospital.affiliatedUnit" type="text" name="hospital.affiliatedUnit" class="form-txt" maxlength="50" 
			value='${hospital.affiliatedUnit}' />
		</div>
		
		<div class="grid_3 lable-right">
			<label class="form-lbl">医院院长：</label>
		</div>
		<div class="grid_5">
			<input id="hospital.director" type="text" name="hospital.director" class="form-txt" maxlength="20" value='${hospital.director}' />
		</div>
		
		
		
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4"></div>
		<div class="grid_7">
			是否医保
			<select id="hospital.medicalInsurance" name="hospital.medicalInsurance">
				<option value=""></option>
				<option value="true">是</option>
				<option value="false">否</option>
			</select>
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		<div class="grid_4 lable-right">
			<label class="form-lbl">联系人：</label>
		</div>
		<div class="grid_4">
			<input id="hospital.contactName" type="text" name="hospital.contactName" class="form-txt"
			maxlength="20" value='${hospital.contactName}' />
		</div>
		
		<div class="grid_3 lable-right">
			<label class="form-lbl">联系电话：</label>
		</div>
		<div class="grid_5" title="电话格式只支持数字和横线如：010-88888888">
			<input id="hospital.telephone" type="text" name="hospital.telephone" class="form-txt"
				maxlength="15" value='${hospital.telephone}' />
		</div>
		
		<div class="grid_3 lable-right">
			<label class="form-lbl">联系手机：</label>
		</div>
		<div class="grid_5">
			<input id="hospital.mobileNumber" type="text" name="hospital.mobileNumber" class="form-txt"
			maxlength="11" value='${hospital.mobileNumber}' />
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		<div class="grid_4 lable-right">
			<label class="form-lbl">电子邮箱：</label>
		</div>
		<div class="grid_12">
			<input id="hospital.email" type="text" name="hospital.email" class="form-txt"
			maxlength="30" value='${hospital.email}' />
		</div>
		
		<div class="grid_3 lable-right">
			<label class="form-lbl">联系传真：</label>
		</div>
		<div class="grid_5">
			<input id="hospital.fax" type="text" name="hospital.fax" class="form-txt"
			maxlength="15" value='${hospital.fax}' />
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		<div class="grid_4 lable-right">
			<label class="form-lbl">综治负责人：</label>
		</div>
		<div class="grid_4">
			<input id="hospital.personLiable" type="text" name="hospital.personLiable" class="form-txt"
			maxlength="21" value='${hospital.personLiable}' />
		</div>
		<div class="grid_3 lable-right">
			<label class="form-lbl">联系电话：</label>
		</div>
		<div class="grid_5" title="电话格式只支持数字和横线如：010-88888888">
			<input id="hospital.personLiableTelephone" type="text" name="hospital.personLiableTelephone" class="form-txt" 
				maxlength="15" value='${hospital.personLiableTelephone}' />
			</div>
		<div class="grid_3 lable-right">
			<label class="form-lbl">联系手机：</label>
		</div>
		<div class="grid_5">
			<input id="hospital.personLiableMobileNumber" type="text" name="hospital.personLiableMobileNumber" 
				class="form-txt" 
				maxlength="11" value='${hospital.personLiableMobileNumber}' />
		</div>
		<div class='clearLine'>&nbsp;</div>
</div>
