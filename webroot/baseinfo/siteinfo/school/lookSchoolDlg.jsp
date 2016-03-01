<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<div id="dialog-form" title="查看学校信息" class="container container_24">
<input id="orgId" type="hidden" name="orgId" value="${orgId}"> 
<input type="hidden" name="mode" value="${mode}" /> <input type="hidden"
	name="school.organization.orgInternalCode"
	value="<s:property value='school.organization.orgInternalCode'/>" /> <input
	type="hidden" name="school.orgInternalCode"
	value="<s:property value='school.orgInternalCode'/>" /> <input
	type="hidden" name="school.organization.id"
	value="<s:property value='school.organization.id'/>" /> <input
	type="hidden" name="school.id" value="${school.id}" /> <input
	type="hidden" name="school.createUser" value="${school.createUser}" />
<input type="hidden" name="school.createDate"
	value='<s:date name="school.createDate" format="yyyy-MM-dd HH:mm:ss"/>' />
<div class='clearLine'>&nbsp;</div>
<div class="grid_4 lable-right"><label class="form-lbl">学校名称：</label></div>
<div class="grid_10"><input type="text" id="schoolChineseName" disabled maxlength="30" name="school.chineseName" <s:if test='"view".equals(mode)'>readonly</s:if> value="${school.chineseName}" class="form-txt" /></div>
<div class="grid_1">
		<em class="form-req">*</em></div>
<div class="grid_4 lable-right"><label class="form-lbl">所属网格：</label></div>
<div class="grid_4">
<input type="text" name="school.organization.orgName" value="${school.organization.orgName}" disabled="disabled" class="form-txt" /></div>
<div class='clearLine'>&nbsp;</div>
<div class="grid_4 lable-right"><label class="form-lbl">英文名称：</label></div>
<div class="grid_10"><input type="text" id="schoolEnglishName" maxlength="30"
	name="school.englishName"
	disabled value="${school.englishName}" class="form-txt" /></div>

<div class="grid_5 lable-right"><label class="form-lbl">学校性质：</label></div>
<div class="grid_4">
			<select name="school.kind.id" class="form-select"
		    <s:if test='"view".equals(mode)'>disabled='true'</s:if>>
		   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@SCHOOL_PROPERTY" defaultValue="${school.kind.id}" />
			</select>
</div>
<div class="grid_1">
		<em class="form-req">*</em></div>
<div class='clearLine'>&nbsp;</div>
<div class="grid_4 lable-right"><label class="form-lbl">学校地址：</label></div>
<div class="grid_10"><input type="text" name="school.address" maxlength="50"
	id="schoolAddress" disabled	value="${school.address}" class="form-txt" /></div>
<div class="grid_1">
		<em class="form-req">*</em></div>
<div class="grid_4 lable-right"><label class="form-lbl">学校类型：</label></div>
<div class="grid_4">
			<select name="school.type.id" class="form-select" title="托管机构:在正常8小时以外从事学生托管的机构"
		    <s:if test='"view".equals(mode)'>disabled='true'</s:if>>
		   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@SCHOOL_TYPE" defaultValue="${school.type.id}" />
			</select>
</div>
<div class="grid_1">
		<em class="form-req">*</em></div>
<div class='clearLine'>&nbsp;</div>
<div class="grid_4 lable-right"><label class="form-lbl">学校网址：</label></div>
<div class="grid_10"><input type="text" name="school.webSite" maxlength="30"
	id="schoolWebSite" disabled	value="${school.webSite}" class="form-txt"></div>
<div class="grid_5 lable-right"><label class="form-lbl">在校人数：</label></div>
<div class="grid_4"><input type="text" name="school.atSchoolHeadcount" maxlength="5"
	disabled value="${school.atSchoolHeadcount}" class="form-txt" /></div>
<div class="grid_1"></div>
<div class='clearLine'>&nbsp;</div>
<div class="grid_4 lable-right"><label class="form-lbl">校长：</label></div>
<div class="grid_3"><input type="text" name="school.president" maxlength="20"
	disabled value="${school.president}" class="form-txt" /></div>
<div class="grid_1"><em class="form-req">*</em></div>
<div class="grid_3 lable-right"><label class="form-lbl">传真：</label></div>
<div class="grid_3"><input type="text" id="schoolFax" maxlength="15"
	name="school.fax" 
	disabled value='${school.fax}'
	class="form-txt" /></div>
<div class="grid_5 lable-right"><label class="form-lbl">电子邮箱：</label></div>
<div class="grid_4"><input type="text" name="school.email" maxlength="30"
	disabled value="${school.email}" class="form-txt" /></div>
<div class="grid_1"></div>
<div class='clearLine'>&nbsp;</div>
<div class="grid_4 lable-right"><label class="form-lbl">综治负责人：</label></div>
<div class="grid_3"><input type="text" name="school.personLiable" maxlength="20"
	disabled	value="${school.personLiable}" class="form-txt"></div>
<div class="grid_1"><em class="form-req">*</em></div>
<div class="grid_3 lable-right"><label class="form-lbl">联系电话：</label></div>
<div class="grid_3"><input type="text" name="school.personLiableTelephone"
	maxlength="15" disabled
	value="${school.personLiableTelephone}" class="form-txt"></div>
<div class="grid_1"><em class="form-req">*</em></div>
<div class="grid_4 lable-right"><label class="form-lbl">联系手机：</label></div>
<div class="grid_4"><input type="text" name="school.personLiableMobileNumber"
	maxlength="11" disabled value="${school.personLiableMobileNumber}" class="form-txt" /></div>
<div class="grid_1"><em class="form-req">*</em></div>
<div class='clearLine'>&nbsp;</div>
<div class='clearLine'>&nbsp;</div>
<div class="grid_4 lable-right"><label class="form-lbl">周边情况：</label></div>
<div class="grid_19"><textarea name="school.remark"
	class="form-txt" disabled>${school.remark}</textarea>
</div>
<div class='clearLine'>&nbsp;</div>
</div>
