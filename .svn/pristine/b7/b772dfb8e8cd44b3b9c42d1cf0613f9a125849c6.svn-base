<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<div id="dialog-form" title="岗位维护" class="container container_24">
    <div class="grid_4 lable-right">
	     <label class="form-lbl">岗位名称：</label>
    </div>
	<div class="grid_7">
	    <input type="text" name="role.roleName" id="roleName" maxlength="20"
		  <s:if test='"view".equals(mode)'>readonly</s:if> value=""  class="form-txt" />
	</div>
	<div class="grid_4 lable-right">
		<label class="form-lbl">应用层级：</label>
	</div>
	<div class="grid_7">
		<select name="role.useInLevel.id" class="form-select" id="useInl"
	    <s:if test='"view".equals(mode)'>disabled='true'</s:if>>
	   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@ORGANIZATION_LEVEL" defaultValue="" />
		</select>
	</div>
    <div class='clearLine'>&nbsp;</div>

    <div class="grid_4 lable-right">
  	    <label class="form-lbl">岗位描述： </label>
    </div>
    <div class="grid_19">
	    <textarea name="role.description" id="description"
		  class="form-txt" style="height: 3em;"></textarea>
    </div>
</div>
