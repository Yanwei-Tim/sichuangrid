<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
<script>
function notFindPopulationByIdCardNoAndOrgId(){
	$.messageBox({level:"error",message:"在实口中未能找到该身份证号码的人员信息，请先在实口中添加"});
}
function afterSearchActualData(data){
	return;
}
$(function(){
	$("#idCardNo").blur(function(){
		if($(this).attr("createMsg")=="false") {
			searchAndSynData($(this).val(), getCurrentOrgId());
		}
	});
	
	<s:if test='"edit".equals(mode)'>
	var dialogName = "<s:property value='#parameters.dailogName[0]'/>";
	$.ajax({
		url:"${path}/commonPopulation/commonPopulationManage/getActualPopulationByOrgIdAndIdCardNo.action",
		data:{
			"orgId":$("#populationOrgId").val(),
			"idCardNo":'<s:property value="population.idCardNo"/>'
        },
        success:function(data){
        	$("#houseCode").val(data.houseCode);
        	$("#houseId").val(data.houseId);
        }
    });
	</s:if>
	
})
</script>