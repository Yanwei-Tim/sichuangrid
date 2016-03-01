<%@ page import="java.util.*" language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/includes/baseInclude.jsp"%>

<div class="gisPop">
	<div class="person_info_top">
		<a href="javascript:;" onclick="window.parent.getHouseProperty(${populationVo.houseId},'${populationVo.viewUrl }')">建筑物信息>></a>
	</div>
	<ul class="type_menu">
		<li onclick="window.parent.getPermanentResident(${populationVo.personId})" class="cur">人员信息</li>
	</ul>
	<div class="person_info_type typePanel">
		<div class="map-ctt-top">
			<div class="imgbox">
				<s:if test='populationVo.imgUrl!=null'>
					<img src="${path }${populationVo.imgUrl}" width="80" height="70" id='image' />
				</s:if>
				<s:else>
					<img src="${resource_path}/resource/images/nopic.jpg" width="80" height="70" id='image' />
				</s:else>
			</div>
			<div class="title">
				<div class="double">
					<strong>姓名：<a href="javascript:;" onclick="window.parent.getPermanentResident(${populationVo.personId},'$(personType)')">${populationVo.name}</a></strong>
				</div>
				<div class="double">
					<strong class="">性别：${populationVo.gender.displayName}</strong>
				</div>
			</div>
			<div class="title">
				<strong>身份证号码：${populationVo.idCardNo}</strong>
			</div>
			<div class="title">
				<strong>住址：<a href="javascript:;" onclick="window.parent.getHouseProperty(${populationVo.houseId})">${populationVo.address}</a></strong>
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


