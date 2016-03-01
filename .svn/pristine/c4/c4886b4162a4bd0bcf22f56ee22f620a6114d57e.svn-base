<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
<fieldset>
	<div class="grid_4 lable-right">
		<em class="form-req">*</em>
		<label class="form-lb1">所属网格：</label>
	</div>
	<div class="grid_18">
		<input type="text" readonly  value="${population.organization.orgName}" class="form-txt" />
	</div>
	<div class='clearLine'>&nbsp;</div>
	<div class="grid_4 lable-right">
		<em class="form-req">*</em>
		<label class="form-lb1">身份证号码：</label>
	</div>
	<div class="grid_7">
		<input type="text" readonly value="${population.idCardNo}" class="form-txt"/>
	</div>
	<div class="grid_4 lable-right">
		<em class="form-req">*</em>
		<label class="form-lb1">姓名：</label>
	</div>
	<div class="grid_7">
		<input type="text" readonly value="${population.name}" class="form-txt"
		/>
	</div>
	<div class='clearLine'>&nbsp;</div>
	<div class="grid_4 lable-right">
		<em class="form-req">*</em>
		<label class="form-lb1">性别：</label>
	</div>
	<div class="grid_7">
	    <select class="form-txt" disabled="disabled">
			<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@GENDER" defaultValue="${population.gender.id}" />
		</select>
	</div>
	<div  class="grid_4 lable-right">
		<label class="form-lbl">民族：</label>
	</div>
	<div class="grid_7">
		<select class="form-txt" disabled="disabled">
			<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@NATION" defaultValue="${population.nation.id}" />
		</select>
	</div>
   	<div class="clearLine">&nbsp;</div>
	<div  class="grid_4 lable-right">
		<label class="form-lbl">联系手机：</label>
	</div>
	<div class="grid_7">
		<input type="text" value="${population.mobileNumber }" readonly class="form-txt" />
	</div>
	<div class="grid_4 lable-right">
		<label class="form-lb1">固定电话：</label>
	</div>
	<div class="grid_7">
		<input type="text" value="${population.telephone }" readonly class="form-txt" />
	</div>
	<div class="clearLine">&nbsp;</div>
</fieldset>