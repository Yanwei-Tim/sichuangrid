<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>	
<%@ include file="/includes/baseInclude.jsp"%>
<div class="gisPop clearfix">
	<ul class="type_menu type_menu_statistics">
		<li class="">所选区域数据统计</li>
	</ul>
	<div class="basicData">
		<h3 class="title"><span>注：【已绑定/总数】</span></h3>
<%-- 		<div class="data">建筑物数量：(<s:property value="drawAreaStatisticVo.hourseBoundMapNum"/>/<s:property value="drawAreaStatisticVo.hourseNum"/>) 幢</div> --%>
		<div class="data">楼宇数量：(${drawAreaStatisticVo.buildDataBoundMapNum}/${drawAreaStatisticVo.buildDataSum }) 幢</div>
		<div class="data">事件数量：(<s:property value="drawAreaStatisticVo.issueBoundMapNum"/>/<s:property value="drawAreaStatisticVo.issueSum"/>) 件</div>
		<div class="data">住房数量：(${drawAreaStatisticVo.housePropertyBoundMapNum}/${drawAreaStatisticVo.housePropertySum }) 幢</div>
		<div class="data">特殊人群数量：(<s:property value="drawAreaStatisticVo.keyPersonBoundMapNum"/>/<s:property value="drawAreaStatisticVo.keyPersonSum"/>) 人</div>
		<div class="data">组织场所数量：(<s:property value="drawAreaStatisticVo.keyPlaceBoundMapNum"/>/<s:property value="drawAreaStatisticVo.keyPlaceSum"/>) 个</div>
	</div>
</div>