<%@ page import="java.util.*"  language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>

<div class="gisPop clearfix">
	<ul class="type_menu">
		<li class="" >${objectList[0].moduleName}</li>
		<li class="curDetails" onclick="window.parent.getDetailInformation('${objectList[0].encryptId}','${objectList[0].detailsUrl}','${objectList[0].issueLogId}','${objectList[0].tableName}','${objectList[0].detailFieldA}','${objectList[0].dustbinType}')">详细信息</li>
	</ul>
	<div class="person_info_type typePanel">
		<div class="map-ctt-top">
			<div class="small_text" style="width:350px;">
				<span class="title">
					<s:if test="objectList[0].detailFieldNameA!=null && objectList[0].detailFieldNameA!='' ">
						<span>
							<strong>${objectList[0].detailFieldNameA}：${objectList[0].detailFieldA}</strong>
						</span>
					</s:if>
					<span>&nbsp;&nbsp;</span>
					<s:if test="objectList[0].detailFieldNameB!=null  && objectList[0].detailFieldNameB!='' ">
						<span>
							<strong>${objectList[0].detailFieldNameB}：${objectList[0].detailFieldB}&nbsp;&nbsp;&nbsp;</strong>
						</span>
					</s:if>	
					<br/>
					<s:if test="objectList[0].detailFieldNameC!=null && objectList[0].detailFieldNameC!='' ">
						<span>
							<strong>${objectList[0].detailFieldNameC}：${objectList[0].detailFieldC}</strong>
						</span>
					</s:if>
					<span>&nbsp;&nbsp;</span>
					<s:if test="objectList[0].detailFieldNameD!=null && objectList[0].detailFieldNameD!='' ">
						<span>
							<strong>${objectList[0].detailFieldNameD}：${objectList[0].detailFieldD}&nbsp;&nbsp;&nbsp;</strong>
						</span>
					</s:if>	
					<br/>
					<s:if test="objectList[0].detailFieldNameE!=null && objectList[0].detailFieldNameE!=''">
						<span>
							<strong>${objectList[0].detailFieldNameE}：${objectList[0].detailFieldE}</strong>
						</span>
					</s:if>
				</span>
			</div>
		</div>
	</div>
	<div class="gis_search_content" style="margin-top:-20px;">
		<div class="gis_search_find">
			<div class="gis_search_type">在附近找</div>
		</div>
		<div class="person_info_type">
			<div class="person_info_list" id="search-List" >
				<ul class="typelistTabs clearfix">
					<li style="width: 100px;" class="current"><a href="javascript:;" class="keyPlaceTwoDimensionMap" onclick="window.parent.tablist1(this)">单位场所</a></li>
					<li style="width: 100px;"><a href="javascript:;" class="consideringObjectTwoDimensionMap"  onclick="window.parent.tablist2(this)">关注对象</a></li>
					<li style="width: 100px;"><a href="javascript:;" class="cityComponentsTwoDimensionMap"  onclick="window.parent.tablist3(this)">城市部件</a></li>
				</ul>
				<div class="typelistManage typelistManage1 clearfix">
		    		<ul class="poptypelist">
		    			<li>
		    				<select class="multiselect" id="unitPlaceBaseTwoDimensionMap" multiple="multiple" name="elements" title="单位">
							</select>
						</li>
		    			<li>
		    				<select class="multiselect" id="companyPlaceBaseTwoDimensionMap" multiple="multiple" name="elements" title="场所">
							</select>
						</li>
		    			<li>
		    				<select class="multiselect" id="keyCompanyPlaceBaseTwoDimensionMap" multiple="multiple" name="elements" title="重点单位场所">
							</select>
						</li>
		    			<li>
		    				<select class="multiselect" id="sizedEnterprisePlaceBaseTwoDimensionMap" multiple="multiple" name="elements" title="规模企业">
							</select>
						</li>
						<li>
							<select class="multiselect" id="twoNewGroupTwoDimensionMap" multiple="multiple" name="elements" title="两新组织">
							</select>
						</li>
						<li>
							<select class="multiselect" id="scenicsManageTwoDimensionMap" multiple="multiple" name="elements" title="景区管理">
							</select>
						</li>
					</ul>
				</div>
				<div class="typelistManage typelistManage2 hidden clearfix">
		    		<ul class="poptypelist">
		    			<li>
			    			<select class="multiselect" id="personTwoDimensionMap"  multiple="multiple" name="elements" title="特殊人群">
							</select>
						</li>
		    			<li>
		    			<select class="multiselect" id="consideringObjectTwoDimensionMap" multiple="multiple" name="elements" size="5" title="关怀对象">
						</select>
						</li>
						<li>
		    			<select class="multiselect" id="otherConsideringObjectTwoDimensionMap" multiple="multiple" name="elements" title="其他关注对象">
							<option id="gpsements" value='deviceInformation'>网格员</option>
						</select>
						</li>
					</ul>
				</div>
				<div class="typelistManage typelistManage3 hidden clearfix">
					<ul class="poptypelist">
					<s:iterator value="objectList[1]" status="st">
						<li>
							<select class="multiselect" id="cityComponentsTwoDimensionMap" internalId="<s:property value="internalId"/>"  multiple="multiple" name="elements" size="5" title="<s:property value='displayName'/>">
							</select>
						</li>
					</s:iterator>
						<li>
							<select class="multiselect" id="publicSecurityTwoDimensionMap" multiple="multiple" name="elements" title="公安部件">
							</select>
						</li>
					</ul>
				</div>
			</div>
			<div class="person_info_list person_info_range">
				<label class="lab">范围：</label>
				<ul class="typelist">
					<s:iterator value="@com.tianque.openLayersMap.util.GisGlobalValueMap@KEY_MODULE_MAP.get(@com.tianque.openLayersMap.util.GisGlobalValueMap@KEY_RANGE)"  id='rangeValue'>
					 	<li><label><input id="range" type="radio" name="range" value='<s:property value="rangeValue"/>'  <s:if test="#rangeValue==peripheryVo.range">checked="checked"</s:if> ><s:property value="rangeValue"/>米</label></li>
					</s:iterator>
				</ul>
			</div>
			
			<div class="person_info_list person_info_range">
				<label class="searchStr">请输入(名称/地址)：</label>
				<ul>
					<li><input id="searchValue" type="text"/></li>
				</ul>
				<div class="search_btnlist"><a href="javascript:;" class="search_btn" onclick="window.parent.searchPeriphery(document.all.elements,document.all.range,<s:property value='#parameters.lon'/>,<s:property value='#parameters.lat'/>)"><span id="search">单位场所搜索</span></a></div>
			</div>
		</div>
	</div>
</div>