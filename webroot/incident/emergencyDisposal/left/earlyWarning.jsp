<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>	
<script type="text/javascript">
$(function(){
	loadDataUrl(getSelectId());
	$("#incdList_warning dl").die().live('click',function(){
	    var firstIdStr=this.id;
	    var firstId =firstIdStr.substring(firstIdStr.indexOf("_")+1);
	    loadDataUrl(firstId);
    });
});
function getSelectId(){
	
   if($("#incdList_warning dl[id^='incident_']").html()==null)return;
   var firstIdStr=$("#incdList_warning dl[id^='incident_']")[0].id;
   var firstId=firstIdStr.substring(firstIdStr.indexOf("_")+1);
   return firstId;
}
function loadDataUrl(firstId){
	if(firstId==null){
			$.ajax({
				url : PATH+'/incident/emergencyDisposal/right/earlyWarningContnet.jsp',
				cache: false,
				success : function(result) {
					proccessLoginResult(result,function(){$(".center-right").html(result);});
				}
			});
	}else{
			$.ajax({
				url : PATH+'/incident/emergencyDisposal/findEarlyWarningContnet.action?incidents.status=<s:property value="@com.tianque.incident.vo.IncidentConstants@EARLY_WARNING"/>&incidents.id='+firstId,
				cache: false,
				success : function(result) {
					proccessLoginResult(result,function(){$(".center-right").html(result);});
				}
			});
		}
		
}
</script>
<jsp:include page="/incident/emergencyDisposal/left/incidentDegree.jsp">
   <jsp:param value="warning" name="tabType"/>
 </jsp:include>