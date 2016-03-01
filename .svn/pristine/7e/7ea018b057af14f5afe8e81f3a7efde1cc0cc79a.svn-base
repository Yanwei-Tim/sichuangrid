<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%> 
<style>
	.incident-container{position:relative;}
	.incident-left{position:absolute;top:0;left:0;width:180px;}
	.incident-right{overflow: auto;overflow-x: hidden;position: relative;}
</style>
<div class="incident-container" id="incidentCaseLibrary">
	<div class="incident-left" id="incidentCaseLibraryLeft">
		<jsp:include page="/incident/emergencyDisposal/left/incidentDegree.jsp">
			 <jsp:param value="caseLibrary" name="tabType"/>
		</jsp:include>
	</div>
	<div class="incident-right" id="incidentCaseLibraryRight">
		 <jsp:include page="/incident/emergencyDisposal/right/caselibraryList.jsp"/>
	</div>
</div>

<script type="text/javascript">
	$(function(){
		accidentAddClass();
		loadCaseLibraryDate(getSelectId());
	});

	function onCaseLibraryIncidentTypeChanged(){
		loadCaseLibraryList();
	}
	function accidentAddClass(){
		
		var $DDall=$("#incdList_caseLibrary>dl>dd");
		var $firstDd=$("#incdList_caseLibrary>dl:eq(0)").find("dd");
		
		$("#incdList_caseLibrary>dl").each(function(){
			var $dd=$(this).find("dd");
			$dd.removeClass("current");
			$firstDd.addClass("current");
			$(this).click(function(){
				$DDall.removeClass("current");
				$dd.addClass("current");
			})
		})
	}
	$("#incdList_caseLibrary dl[id^='incident_']").click(function(){
		var firstIdStr=this.id;
		var firstId =firstIdStr.substring(firstIdStr.indexOf("_")+1);
		loadCaseLibraryDate(firstId);
    });

	/*左侧案例库列表 dataType:"json",*/
	function loadCaseLibraryList(){
		var url = selectUrl();
		$.ajax({
			async:false,
			datatype:"json",
			url: url,
			success:function(result){
				$("#incidentCaseLibraryRight").html(result);
			}
		});
	}
	
	function GetCategoryIdifNotNull(){
		var categoryId;
		if(getPropertyDictId()){
			categoryId = getPropertyDictId();
		}else{
			categoryId = "";
		}
		return categoryId;
	}
	function selectUrl(){
		var url;
		if(getcurrentLevel()==3){
			url='${path}/incident/emergencyDisposal/loadCaseLibraryList.action?incidents.id='+getParentNodeIncidentTypeId()+'&incidents.category.id='+getParentNodePropertyDictId()+'&incidents.degree.id='+GetCategoryIdifNotNull()+'&incidents.status='+<s:property value="@com.tianque.incident.vo.IncidentConstants@CASE_LIBRARY"/> ;
		}else{
			url='${path}/incident/emergencyDisposal/loadCaseLibraryList.action?incidents.id='+getCurrentIncidentId()+'&incidents.category.id='+GetCategoryIdifNotNull()+'&incidents.status='+<s:property value="@com.tianque.incident.vo.IncidentConstants@CASE_LIBRARY"/> ;
		}
		return url;
	}
	function getSelectId(){
		   if($("#incdList_caseLibrary dl[id^='incident_']").html()==null)return;
		   var firstIdStr=$("#incdList_caseLibrary dl[id^='incident_']")[0].id;
		   var firstId=firstIdStr.substring(firstIdStr.indexOf("_")+1);
		   return firstId;
	}
	
</script>