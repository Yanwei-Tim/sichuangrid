<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<div id =caseLibraryView>	
<table class="tablelist">
	  <tr>
	    <td class="title"><label>事件大类</label></td>
	    <td class="content"><span id="caseCategory" ><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@INCIDENT_SUBJECTION" defaultValue="${incidents.category.id}" /></span></td>
	    <td class="title"><label>事件小类</label></td>
	    <td class="content"><span id="incident_subdivision">${incidents.incidentType.name}</span></td>
	  </tr>
	  <tr>
	    <td class="title"><label>事件等级</label></td>
	    <td class="content"><span id="caseDegree"><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@INCIDENT_DEGREE" defaultValue="${incidents.degree.id}" /></span></td>
	    <td class="title"><label>事件来源</label></td>
	    <td class="content"><span>${incidents.sourceType}</span></td>
	  </tr>
	   <tr>
	    <td class="title"><label>事发地点</label></td>
	    <td class="content"><span>${incidents.occurAddress}</span></td>
	    <td class="title"><label>影响范围</label></td>
	    <td class="content"><span>${incidents.range}</span></td>
	  </tr>
	  <tr>
	    <td class="title"><label>预案提出人</label></td>
	    <td class="content"><span>${incidents.applicant}</span></td>
	    <td class="title"><label>预案提出时间</label></td>
	    <td class="content"><span><s:date name="incidents.applyDate" format="yyyy-MM-dd" /></span></td>
	  </tr>
	   <tr>
	    <td class="title"><label>预案详细内容</label></td>
	     <td colspan="3" class="content"><span id="">${incidents.content}</span></td>	
	  </tr>
  </table>
	<jsp:include page="/incident/emergencyDisposal/right/caseLibraryStepfeedBacksList.jsp"></jsp:include>
</div>
<script>
</script>
