<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
<div class="container container_24">
	<form id="maintainForm" method="post" action="${path }/baseinfo/floatingPopulationManage/maintainHouseInfo.action" >
	<pop:token />
		<input type="hidden" id="organizationId" name="ownerOrg.id" value="${houseInfo.organization.id}"/>
		<input type="hidden" name="houseInfo.organization.id" id="houseInfoOrganizationId" value="${houseInfo.organization.id}" />
		
		<jsp:include page="/baseinfo/commonPopulation/commonActualHouseDlg.jsp"></jsp:include>
 	</form>
 </div>
 <script>
	$(function(){
		$("#maintainForm").formValidate({
			submitHandler: function(form) {
				businessHouseToCurrentAddress();
				$(form).ajaxSubmit({
					success: function(data){
		        		if("<s:property value='#parameters.dailogName[0]'/>"!=""){
							doActualHouseAction("<s:property value='#parameters.dailogName[0]'/>",data.id);
						}
 	        		}
 	        	})
 			},
 			rules:{},
 			messages:{}
 		});
 	});
 </script>