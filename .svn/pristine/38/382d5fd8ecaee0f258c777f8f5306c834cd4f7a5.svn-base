<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>	
<%@ include file="/includes/baseInclude.jsp"%>

<div class="gisPop clearfix featurePop">
	<h3 class="title">
		<s:if test="buildDataInfoVo!=null && buildDataInfoVo.name!=null">	
			<span>${buildDataInfoVo.name}</span>
		</s:if>
		<s:elseif test="buildDataInfoVo!=null && buildDataInfoVo.address!=null" >
			<span>${buildDataInfoVo.address}</span>
		</s:elseif>
		<s:else>
			<span>房屋信息</span>
		</s:else>
	</h3>
	<div class="noFeature">
		<s:if test="buildDataInfoVo==null || buildDataInfoVo.name==null">
			<span><span class="noneb"></span>该建筑物还未绑定楼宇！</span>
		</s:if>
	</div>
	<div class="basicData basicDataContent">
		<div class="houseMsg" id="houseMsg">
			<s:if test="buildDataInfoVo!=null && buildDataInfoVo.address!=null">	
				<div>地址：<div class="houseName">${buildDataInfoVo.address}</div></div>
			</s:if>
			<s:if test="buildDataInfoVo!=null && buildDataInfoVo.type!=null&& buildDataInfoVo.type.id!=null">	
				<div>类型：<div class="houseName">${buildDataInfoVo.type.displayName}</div></div>
			</s:if>
		</div>
		<div class="houseMsg">
			<span>共有住房：</span><a href="javascript:void(0);" id="houseSum" onclick="window.parent.findHourseListByBuildDataId('${buildDataInfoVo.id}',${houseNum})">【${houseNum}】</a>间
		</div>
		<div class="gisArea">
			<div class="personMsg">
			<span>人口总数：</span><a href="javascript:void(0);" id="personSum" onclick="window.parent.findPersonList('${buildDataInfoVo.id}',${buildDataInfoVo.lon},${buildDataInfoVo.lat},${personSum},'<s:property value='@com.tianque.openLayersMap.util.GisGlobalValueMap@PERSONAL_MODE'/>')">【${personSum}】</a>人
			</div>
			<ul class="personMsg"  style="display:inline;">
				<s:if test="householdStaffCount!=0">
				<li><span>户籍人口：</span><a href="javascript:void(0);" id="householdStaffCount" onclick="window.parent.findPersonList('${buildDataInfoVo.id}',${buildDataInfoVo.lon},${buildDataInfoVo.lat},${houseNum},'<s:property value='@com.tianque.openLayersMap.util.GisGlobalValueMap@HOUSEHOLDSTAFF'/>')">【${householdStaffCount}】</a>人</li>
				</s:if>
				<s:if test="floatingPopulationCount!=0">
				<li><span>流动人口：</span><a href="javascript:void(0);" id="floatingPopulationCount" onclick="window.parent.findPersonList('${buildDataInfoVo.id}',${buildDataInfoVo.lon},${buildDataInfoVo.lat},${houseNum},'<s:property value='@com.tianque.openLayersMap.util.GisGlobalValueMap@FLOATINGPOPULATION'/>')">【${floatingPopulationCount}】</a>人</li>
				</s:if>
				<s:if test="unsettledPopulationCount!=0">
				<li><span>未落户人口：</span><a href="javascript:void(0);" id="unsettledPopulationCount" onclick="window.parent.findPersonList('${buildDataInfoVo.id}',${buildDataInfoVo.lon},${buildDataInfoVo.lat},${houseNum},'<s:property value='@com.tianque.openLayersMap.util.GisGlobalValueMap@UNSETTLEDPOPULATION'/>')">【${unsettledPopulationCount}】</a>人</li>
				</s:if>
				<s:if test="overseaStaffCount!=0">
				<li><span>境外人员：</span><a href="javascript:void(0);" id="overseaStaffCount" onclick="window.parent.findPersonList('${buildDataInfoVo.id}',${buildDataInfoVo.lon},${buildDataInfoVo.lat},${houseNum},'<s:property value='@com.tianque.openLayersMap.util.GisGlobalValueMap@OVERSEASTAFF'/>')">【${overseaStaffCount}】</a>人</li>
				</s:if>
			</ul>
		</div>
			<ul class="houseList" style="display:inline;">
				<s:if test="keyPlace.size() > 0 ">
					<s:iterator value="keyPlace" id="mp">
					<li>
						<label class="" for="<s:property value="key"/>" title='<s:property value="@com.tianque.openLayersMap.util.ModuleMap@getModuleName(key)"/>'>
							<div class='houseTotel' style="margin-left: 10px;">
								<s:property value="@com.tianque.openLayersMap.util.ModuleMap@getModuleName(key)"/>
							</div><span>：</span><a href="javascript:void(0);" onclick="javascript:window.parent.getKeyPlaceList(${buildDataId},${buildDataInfoVo.lon},${buildDataInfoVo.lat},'${key}','<s:property value='@com.tianque.openLayersMap.util.ModuleMap@getEntityName(key)'/>')" >【<s:property value="value"/>】</a>个
						</label>	
					</li>
					</s:iterator>
				</s:if>
			</ul>
	</div>
	<div class="bindFeature">
		<div class="bindFeatureP">
			<s:if test="buildDataInfoVo!=null && buildDataInfoVo.isLayout==1">	
			<div class="bindFeatureContent"  id="buildLayout">
				<a href="javascript:void(0);" id="viewBuildLayout" onclick="window.parent.viewBuildLayout(${buildDataInfoVo.id},'${buildDataInfoVo.name }');"><span class="viewlayouticon"></span>查看布局</a>
			</div>
			</s:if>
			
			<div class="bindFeatureContent">
				<div class="placeCtt">
					<a href="javascript:void(0);" onclick="boundKeyPlaceing(${buildDataInfoVo.lon},${buildDataInfoVo.lat})"><span class="bindicon"></span>绑定场所</a>
					<span id="placeModules" style="display: none">
					 	<select name="modules" onchange="javascript:window.parent.showUnbindingPlaceDialog(this.value,'${buildDataId}',${buildDataInfoVo.lon},${buildDataInfoVo.lat},this);">
					 		<option value="" selected="selected">&lt;==请选择==&gt;</option>
				 			<s:iterator value="gisTypeManagePlaceList">
				 				<option innerKey='<s:property value="innerKey"/>'  value="<s:property value="key"/>"><s:property value="name"/></option>
				 			</s:iterator>
					 	</select>
					</span>
				</div>
			</div>
			<div class="bindFeatureContent" id="bindingBuliding">
			   	<s:if test="buildDataInfoVo!=null&&buildDataInfoVo.id!=null">
			   		<a href="javascript:void(0);"  onclick='window.parent.cancelBindingBuilding(${buildDataId },${buildDataInfoVo.id},${buildDataInfoVo.lon},${buildDataInfoVo.lat})'><span class="unbindicon"></span>解除绑定</a>
				</s:if>
				<s:else>
					<a href="javascript:void(0);"  onclick='window.parent.bindingBuilding(${buildDataId },${buildDataInfoVo.lon},${buildDataInfoVo.lat})'><span class="bindicon"></span>绑定楼宇</a>
				</s:else>
			</div>
		</div>
	</div>
</div>
