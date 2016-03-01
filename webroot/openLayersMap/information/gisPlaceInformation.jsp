<%@ page import="java.util.*"  language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>

<div class="gisPop">
	<ul class="type_menu">
		<li onclick="window.parent.getHouseProperty(${locationVo.id})">建筑物信息</li>
		<li class="curDetails" onclick="window.parent.getHouseProperty(${locationVo.id})">
			<s:if test="locationVo.type == @com.tianque.openLayersMap.util.GisGlobalValueMap@HOUSE_MODE">
				<a href="javascript:;" onclick="window.parent.getHouseProperty(${locationVo.id})">详细信息&gt;&gt;</a>
			</s:if>
			<s:else>
				<a href="javascript:;" onclick="window.parent.getKeyPlace(${locationVo.id},'${locationVo.type}','${locationVo.viewUrl }')">详细信息</a>
			</s:else>
		</li>
	</ul>
	<div class="person_info_type typePanel">
		<div class="map-ctt-top">
			<div class="small_text">
				<div class="title">
					<div>
						<strong>${locationVo.locationNameValue}</strong>
					</div>
				</div>
				<div class="title">
					<strong>地址：${locationVo.locationAddress}</strong>
				</div>
			</div>
		</div>
	</div>
	<div class="gis_search_type">在附近找</div>
	<div class="person_info_type">
		<div class="person_info_list">
			<ul class="typelistTabs clearfix">
				<li class="current"><a href="javascript:;"  onclick="window.parent.tablist1(this)">重点场所</a></li>
				<li><a href="javascript:;"  onclick="window.parent.tablist2(this)">特殊人群</a></li>
			</ul>
			<div class="typelistManage typelistManage1 clearfix">
	    		<ul>
					<s:iterator value="gisTypeManagePlaceList">
						<li><label><input id="keyPlacelements" type="checkbox" name="elements" value='<s:property value='key'/>' <s:if test="(peripheryVo.elements).indexOf(key)>=0">checked="checked"</s:if>> <s:property value="name"/></label></li>
					</s:iterator>
				</ul>
			</div>
			<div class="typelistManage typelistManage2 hidden clearfix">
				<ul id="keyPersonsUL">
	    		</ul>
	    		<ul>
					<s:iterator value="gisTypeManagePersonList" >
						<li><label><input id="keyPersonElements" type="checkbox" name="elements" value='<s:property value='key'/>' <s:if test="(peripheryVo.elements).indexOf(key)>=0">checked="checked"</s:if>> <s:property value="name"/></label></li>
					</s:iterator>
				</ul>
			</div>
		</div>
		<div class="person_info_list">
			<label class="lab">范围：</label>
			<ul class="typelist">
				<s:iterator value="@com.tianque.openLayersMap.util.GisGlobalValueMap@KEY_MODULE_MAP.get(@com.tianque.openLayersMap.util.GisGlobalValueMap@KEY_RANGE)"  id='rangeValue'>
				 	<li><label><input id="range" type="radio" name="range"  value='<s:property value="rangeValue"/>'  <s:if test="#rangeValue==peripheryVo.range">checked="checked"</s:if> ><s:property value="rangeValue"/>米</label></li>
				</s:iterator>
			</ul>
		</div>
		<div class="search_btnlist"><a href="javascript:;" class="search_btn" onclick="window.parent.searchPeriphery(document.all.elements,document.all.range,<s:property value='#parameters.lon'/>,<s:property value='#parameters.lat'/>)"><span>搜索</span></a></div>
	</div>
</div>