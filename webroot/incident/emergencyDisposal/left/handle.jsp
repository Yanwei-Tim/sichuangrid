<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>	
<%@ include file="/includes/baseInclude.jsp"%>
<script type="text/javascript">
$(function(){
	accidentAddClass();
	loadRightData(getSelectId());

	$("#incdList_handle dl").die().live('click',function(){
	    var firstIdStr=this.id;
	    var firstId =firstIdStr.substring(firstIdStr.indexOf("_")+1);
	    loadRightData(firstId);
    });
	
	
});

function getSelectId(){
  if($("#incdList_handle dl[id^='incident_']").html()==null)return ;
  var firstIdStr=$("#incdList_handle dl[id^='incident_']")[0].id;
  var firstId=firstIdStr.substring(firstIdStr.indexOf("_")+1);
  return firstId;
}
function loadRightData(firstId){
	if(firstId==null){
		$.ajax({
			url: '${path}/incident/emergencyDisposal/right/handleContent.jsp',
			cache: false,
			success:function(result){
					$(".center-right").html(result);
			}
		});
	}else{
		var status='<s:property value="@com.tianque.incident.vo.IncidentConstants@HANDLING"/>';
		$.ajax({
			url :'${path}/incident/emergencyDisposal/findHandleContent.action',
			cache: false,
			data:{"incidents.id":firstId,"incidents.status":status},
			success : function(result) {
					$(".center-right").html(result);
			}
		});
	}
}
function accidentAddClass(){
	
	var $DDall=$("#incdList_handle>dl>dd");
	var $firstDd=$("#incdList_handle>dl:eq(0)").find("dd");
	
	$("#incdList_handle>dl").each(function(){
		var $dd=$(this).find("dd");
		$dd.removeClass("current");
		$firstDd.addClass("current");
		$(this).click(function(){
			$DDall.removeClass("current");
			$dd.addClass("current");
		})
	})
}

</script>

<jsp:include page="/incident/emergencyDisposal/left/incidentDegree.jsp">
  <jsp:param value="handle" name="tabType"/>
</jsp:include>