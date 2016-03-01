<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div class="container container_24">
	<div class="grid_4 lable-right">
		<label>微信号：</label>
	</div>
	<div class="grid_19">
		<select class='form-txt' id="search_weChatUserId"
			style="width: 475px" readonly>
			<option value="请选择微信号">请选择微信号</option>
			<s:iterator value="listTencentUser">
				<option value="${weChatUserId }">${weChatUserId}&nbsp&nbsp&nbsp&nbsp&nbsp[昵称：
					${name}]</option>
			</s:iterator>
		</select>
	</div>
	<div class='clearLine'>&nbsp;</div>
	<div class="grid_4 lable-right">
		<label>菜单类型：</label>
	</div>
	<div class="grid_19">
		<select id="search_menuType"  class="form-txt"
			style="width: 475px" readonly>
		<option value="null" selected>请选择菜单类型</option>
		<option value="1">单击</option>
		<option value="2">网页</option>
		</select>
	</div>
	<div class='clearLine'>&nbsp;</div>
	<div class="grid_4 lable-right">
		<label>菜单名称：</label>
	</div>
	<div class="grid_19">
		<input type="text" name="weChatMenu.menuName" id="search_menuName" class='form-txt' />
	</div>
	<div class='clearLine'>&nbsp;</div>
	<div class="grid_4 lable-right">
		<label>素材类型：</label>
	</div>
	<div class="grid_19">
		<select id="search_sourceType"  class="form-txt"
			style="width: 475px" readonly>
		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@WECHAT_TYPE"></pop:OptionTag>
		</select>
	</div>
	
	
</div>



