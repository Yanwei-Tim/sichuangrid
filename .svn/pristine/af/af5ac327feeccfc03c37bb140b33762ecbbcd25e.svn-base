<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
<script>
function notFindPopulationByIdCardNoAndOrgId(){
	$("#actualPersonTypeDiv").show();
}

function clearSearchActualData() {
	if(null != $("input[name='actualPersonType']:checked",$("#maintainForm")).val()) {
		$("input[name='actualPersonType']",$("#maintainForm")).removeAttr('checked');
// 		$("#<s:property value='#parameters.dailogName[0]'/>").createActualPopulationDialog("remove","流入信息");
// 		$("#<s:property value='#parameters.dailogName[0]'/>").createActualPopulationDialog("remove","户籍信息");
	}
}

function afterSearchActualData(data){
	var dialogName = "<s:property value='#parameters.dailogName[0]'/>";
	$("#attentionPopulationType").val(dialogName.substring(0,dialogName.length-6));
	if(data.actualPopulationType == '<s:property value="@com.tianque.service.util.PopulationType@HOUSEHOLD_STAFF"/>') {
		<%--
		$("#"+dialogName).removeTabFromDialog($("#"+dialogName).getTabIndexByTitle("流入信息"));
		$("#"+dialogName).addTabToDialog(
			{ 
				title:"户籍信息",
				url:"/baseinfo/householdStaff/dispathHouseholdInfoForBusinessPopulation.action?mode=edit&dailogName="+dialogName+"&population.id="+data.id,
				index:1
			}
		)
		--%>
		$("input[name='actualPersonType']:first").click();
	}else if(data.actualPopulationType == '<s:property value="@com.tianque.service.util.PopulationType@FLOATING_POPULATION"/>'){
		<%--
		$("#"+dialogName).removeTabFromDialog($("#"+dialogName).getTabIndexByTitle("户籍信息"));
		$("#"+dialogName).addTabToDialog(
			{ 
				title:"流入信息",
				url:"/baseinfo/floatingPopulationManage/dispathInflowingInfomationForBusinessPopulation.action?mode=edit&dailogName="+dialogName+"&population.id="+data.id,
				index:1
			}
		)
		--%>
		$("input[name='actualPersonType']:last").click();
	}
}

$(function(){
	
	$("input[name='actualPersonType']").click(function(){
		var dialogName = "<s:property value='#parameters.dailogName[0]'/>";
		$("#attentionPopulationType").val(dialogName.substring(0,dialogName.length-6));
		if($(this).val() == '<s:property value="@com.tianque.service.util.PopulationType@HOUSEHOLD_STAFF"/>') {
			$("#actualPopulationType").val('<s:property value="@com.tianque.service.util.PopulationType@HOUSEHOLD_STAFF"/>');
			<%--
			$("#"+dialogName).removeTabFromDialog($("#"+dialogName).getTabIndexByTitle("流入信息"));
			$("#"+dialogName).addTabToDialog(
				{
					title:"户籍信息",
					url:"/baseinfo/householdStaff/dispathHouseholdInfoForBusinessPopulation.action",
					index:1
				}
			)
			$("#"+dialogName).tabDialog('disable');
			--%>
		}else {
			$("#actualPopulationType").val('<s:property value="@com.tianque.service.util.PopulationType@FLOATING_POPULATION"/>');
			<%--
			$("#"+dialogName).removeTabFromDialog($("#"+dialogName).getTabIndexByTitle("户籍信息"));
			$("#"+dialogName).addTabToDialog(
				{
					title:"流入信息",
					url:"/baseinfo/floatingPopulationManage/dispathInflowingInfomationForBusinessPopulation.action",
					index:1
				}
			)
			$("#"+dialogName).tabDialog('disable');
			--%>
		}
	});
	<s:if test='null!=population&&null!=population.id'>
	var dialogName = "<s:property value='#parameters.dailogName[0]'/>";
	$("#attentionPopulationType").val(dialogName.substring(0,dialogName.length-6));
	<%--
	$.ajax({
		url:"${path}/commonPopulation/commonPopulationManage/getActualPopulationByOrgIdAndIdCardNo.action",
		data:{
			"orgId":'${population.organization.id }',
			"idCardNo":'${population.idCardNo }'
        },
        success:function(data){
			if(data.actualPopulationType == 'householdStaff') {
				$("#"+dialogName).addTabToDialog(
					{ 
						title:"户籍信息",
						url:"/baseinfo/householdStaff/dispathHouseholdInfoForBusinessPopulation.action?dailogName="+dialogName,
						index:1
					}
				)
			}else if(data.actualPopulationType == 'floatingPopulation'){
				$("#"+dialogName).addTabToDialog(
					{ 
						title:"流入信息",
						url:"/baseinfo/floatingPopulationManage/dispathInflowingInfomationForBusinessPopulation.action?dailogName="+dialogName,
						index:1
					}
				)
			}
        }
    });
    --%>
	</s:if>
})
</script>