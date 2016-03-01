<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="${path}/baseinfo/businessPopulation.jsp"/>
    <div id="houseStaff" class="container container_24">
		<div id=tabs>
			<ul>
				<li><a href="${path}/baseinfo/householdStaff/viewCommonPopulation.action?population.id=${population.encryptId}">基本信息</a> </li>
<%-- 				<c:if test='${false!=population.isHaveHouse && null!=population.isHaveHouse}'> --%>
<%-- 					<li id="houseInfo"><a href="${path}/baseinfo/houseInfoForPopulation/viewHouseInfoForHouseholdStaff.action?population.id=${population.encryptId}">住房信息</a> </li> --%>
<%-- 				</c:if> --%>
<%-- 				<li id="householdInfo"><a href="${path}/baseinfo/householdStaff/viewHouseholdStaff.action?population.id=${population.encryptId}">户籍信息</a> </li> --%>
				<pop:JugePermissionTag ename="groupFamilyInfo">
				<li id="groupFamily"><a href="${path}/baseinfo/familyInfo/detailGroupFamily.action?groupFamilyHasPopulation.populationId=${population.encryptId}&groupFamilyHasPopulation.population.orgInternalCode=${population.orgInternalCode}&groupFamilyHasPopulation.populationType=<s:property value='@com.tianque.service.util.PopulationType@HOUSEHOLD_STAFF'/>">家庭信息</a></li>
				</pop:JugePermissionTag>
				<li><a id="personnelTrackInfos">轨迹信息</a></li>
			</ul>
   		</div>
  </div>
<script>
$("#personnelTrackInfos").attr("href","${path }/baseinfo/tracks/personnelTrackInfos.jsp?idCardNo=${population.idCardNo}&width=750&height=370&populationType=&populationName="+encodeURI(encodeURI("${population.name}")));
$(function() {
	<pop:GlobalSettingTag key="@com.tianque.core.globalSetting.util.GlobalSetting@VIEW_BUSINESS_POPULATION" value="true">
		var info = $("#houseStaff li:last");
		$.ajax({
			url:'${path}/baseinfo/populationTypeManage/getPopulationTypeByActualIdAndType.action?populationId=${population.id}&populationType=<s:property value="@com.tianque.service.util.PopulationType@HOUSEHOLD_STAFF"/>&orgInternalCode=${population.orgInternalCode}',
			async:false,
			success:function(datas){
				viewBusinessPopulation(datas,info);
		}
	});
	</pop:GlobalSettingTag>
	$( "#houseStaff #tabs" ).tabs();
});
</script>