<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<jsp:include page="/includes/baseInclude.jsp" />

	<span style="font-weight: bold; margin-left:20px; ">艾滋病人员</span>
	<div class='clearLine'>&nbsp;</div>
	<div class="grid_6 lable-right">
		<label class="form-lbl">关注程度：</label>
	</div>
	<div class="grid_6">
		<select name="population.attentionExtent.id" id="druggy-attentionExtent" class="form-txt"
			<s:if test='"view".equals(mode)'>disabled='true'</s:if> >
			<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@ATTENTION_EXTENT" defaultValue="${population.attentionExtent.id}" />
		</select>
	</div>
	<div class="grid_6 lable-right">
		<em class="form-req">*&nbsp;</em>
		<label class="form-lbl">感染途径：</label>
	</div>
	<div class="grid_6">
	    <select id="population.infectway.id" name="population.infectway.id"  class="form-txt {required:true,messages:{required:'请选择感染途径'}}" <s:if test='"view".equals(mode)'>disabled</s:if>>
	    	<pop:OptionTag name="@com.tianque.constant.PropertyTypes@INFECT_WAY" defaultValue="${population.infectway.id}" ></pop:OptionTag>
	    </select>
	</div>
	
	<div class="grid_6 lable-right">
		<em class="form-req">*&nbsp;</em>
		<label class="form-lbl">违法情况：</label>
	</div>
	<div class="grid_6">
		<select id="population.violationsofthelaw.id" name="population.violationsofthelaw.id"  class="form-txt {required:true,messages:{required:'请选择违法情况'}}" <s:if test='"view".equals(mode)'>disabled</s:if>>
	    	<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@VIOLATIONSOFTHELAW"  defaultValue="${population.violationsofthelaw.id}"></pop:OptionTag>
	    </select>
	</div>
	
	<div class="grid_6 lable-right">
		<em class="form-req">*&nbsp;</em>
		<label class="form-lbl">犯罪类型：</label>
	</div>
	<div class="grid_6">
		<select id="population.crimetype.id" name="population.crimetype.id"  class="form-txt {required:true,messages:{required:'请选择犯罪类型'}}">
	    	<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@CRIMETYPE"  ></pop:OptionTag>
	    </select>
	</div>
	<div class='clearLine'>&nbsp;</div>
	<div class="grid_6 lable-right">
		<em class="form-req">*&nbsp;</em>
		<label class="form-lbl">收治机构：</label>
	</div>
	<div class="grid_6">
		<input type="text" name="population.receivedorganization" id="receivedorganization" value="${population.receivedorganization}"  class="form-txt dialogtip {required:true,exculdeParticalChar:true,messages:{required:'请输入收治机构',exculdeParticalChar:'不能输入非法字符'}}" value="<s:property value="population.receivedorganization"/>" maxlength="30"
		<s:if test='"view".equals(mode)'>disabled</s:if>/>
	</div>
	
	<div class="grid_6 lable-right">
		<em class="form-req">*&nbsp;</em>
		<label class="form-lbl">收治机构所属层级： </label>
	</div>
	<div class="grid_6">
		<select name="population.receivedlevel.id" class="form-txt dialogtip{required:true,messages:{required:'请选择收治机构所属层级'}}"
	    <s:if test='"view".equals(mode)'>disabled='true'</s:if>id="receivedlevel">
	   		<pop:OptionTag name="@com.tianque.constant.PropertyTypes@RECEIVED_LEVEL"  defaultValue="${population.receivedlevel.id}" ></pop:OptionTag>
		</select>
	</div>
<div class='clearLine'>&nbsp;</div>
