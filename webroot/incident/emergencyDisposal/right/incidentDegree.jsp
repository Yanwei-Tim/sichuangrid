<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>	
<%
	request.setAttribute("tabType",request.getParameter("tabType"));
%>
<div id="incdList_${tabType}" class="warning_content clearfix">
   <s:iterator  value="incidentsList" var="incidentObj" status="st" >
     <s:set name="incidentObjId" value="#incidentObj.id" scope="request"/>
     <s:if test="#incidentObj.degree!=null">
       <s:set name="degreeId" value="#incidentObj.degree.id" scope="request"/>
     </s:if>
      <dl id="incident_${incidentObjId}" class="Structure clearfix" >
       <s:if test="#incidentObj.degree==null">
         <dt id="incidentDegreeId_${incidentObjId}"  class="warnGrade5"> 
                              无分级
           <span class="gradeStar gradeStar5"></span> 
           </dt>                   
       </s:if> 
      <s:elseif test="@com.tianque.domain.property.IncidentDegreeSituation@FIRET_DEGREE==#incidentObj.degree.internalId">
       <dt id="incidentDegreeId_${incidentObjId}"  class="warnGrade1"> 
         <pop:PropertyDictViewTag defaultValue="${degreeId}" name="@com.tianque.domain.property.PropertyTypes@INCIDENT_DEGREE"/> 
         <span class="gradeStar gradeStar1"></span>
        </dt>
       </s:elseif> 
        <s:elseif test="@com.tianque.domain.property.IncidentDegreeSituation@SECOND_DEGREE==#incidentObj.degree.internalId">
            <dt id="incidentDegreeId_${incidentObjId}"  class="warnGrade2"> 
                <pop:PropertyDictViewTag defaultValue="${degreeId}" name="@com.tianque.domain.property.PropertyTypes@INCIDENT_DEGREE"/> 
                 <span class="gradeStar gradeStar2"></span>
             </dt>
        </s:elseif> 
        <s:elseif test="@com.tianque.domain.property.IncidentDegreeSituation@THRID_DEGREE==#incidentObj.degree.internalId">
            <dt id="incidentDegreeId_${incidentObjId}"  class="warnGrade3"> 
                <pop:PropertyDictViewTag defaultValue="${degreeId}" name="@com.tianque.domain.property.PropertyTypes@INCIDENT_DEGREE"/> 
                 <span class="gradeStar gradeStar3"></span>
             </dt>
        </s:elseif> 
        <s:elseif test="@com.tianque.domain.property.IncidentDegreeSituation@FOURTH_DEGREE==#incidentObj.degree.internalId">
            <dt id="incidentDegreeId_${incidentObjId}"  class="warnGrade4"> 
                 <pop:PropertyDictViewTag defaultValue="${degreeId}" name="@com.tianque.domain.property.PropertyTypes@INCIDENT_DEGREE"/> 
                 <span class="gradeStar gradeStar4"></span>
             </dt>
        </s:elseif> 
       <dd id="incidentTitle_${incidentObjId}"><a href="javascript:;"><s:property value="#incidentObj.title"/></a></dd>
    </dl>
   </s:iterator>
</div>
<script>
$(function(){
	function accidentAddClass(){
		var $DDall=$("#incdList_warning>dl>dd");
		var $firstDd=$("#incdList_warning>dl:eq(0)").find("dd");
		$("#incdList_warning>dl").each(function(){
			var $dd=$(this).find("dd");
			$dd.removeClass("current");
			$firstDd.addClass("current");
			$(this).click(function(){
				$DDall.removeClass("current");
				$dd.addClass("current");
			})
		})
	}
	accidentAddClass();

	function processListHeight(){
		var timer;
		function Height(){
			var $height=document.documentElement.clientHeight||document.body.clientHeight;
			$(".warning_content").height($height-160);
		}
		Height()
		$(window).resize(function(){
			clearTimeout(timer);
			timer=setInterval(function(){Height()},10);
		})
	}
	processListHeight();
})
</script>