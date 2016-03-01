<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
    <div id="nurturesWomen" class="container container_24">

		<form action="${path}/baseinfo/nurturesWomenManage/addNurturesWomen.action" method="post" id="maintainForm">
		<pop:token />
		<div id="perUuid"></div>
		<input type="hidden" name="mode" id="mode" value="${mode}" />
			<input type="hidden" name="modeType" id="modeType" value="${modeType}" />
		<input type="hidden" name="contextId"  value="${contextId}" />
		<input type="hidden" name="cacheId.id"  value="${cacheId.id}" />
		<input id="populationId" type="hidden" name="population.id" value="${population.id}" />
		<input type="hidden" name="cacheId.id" value="${cacheId.id}" />
		<input id="_imgUrl" name="population.imgUrl" type="hidden" value="${population.imgUrl}"/>
		<input id="populationOrganizationId" type="hidden" name="population.organization.id" value="${population.organization.id }" />
		<input id="populationGenderId" type="hidden" name="population.gender.id" value="${population.gender.id }" />
		<input id="populationIdCardNo" type="hidden" name="population.idCardNo" value="${population.idCardNo }" />
		<input type="hidden" id="birthday" name="" value="<fmt:formatDate value="${population.birthday}" type="date" pattern="yyyy-MM-dd" />"></input>
		<input type="hidden" id="maritalStateValue" name="maritalState" value="${population.maritalState.id}"/>
		<input id="isEmphasis"	type="hidden" name="population.isEmphasis" value="${population.isEmphasis}" />
		<jsp:include page="/baseinfo/commonPopulation/populationInfo/nurturesWomen.jsp"></jsp:include>
	  </form>
   </div>

<script type="text/javascript">
$(document).ready(function(){
	
	$("#maintainForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form){
			$("#_imgUrl").val($("#imgUrl").val());
			businessHouseToCurrentAddress();
			$(form).ajaxSubmit({
				async:false,
				 success : function(data) {
		
					if (!data.id) {
						$.messageBox({
							message : data,
							level : "error"
						});
						return;
					}
					 if("add"==$("#mode").val()){
						 if($("#isLock").val()!='1'){
							 $("#nurturesWomenList").addRowData(data.id,data,"first");
							 $("#nurturesWomenList").setSelection(data.id);
						 }
						 $("#nurturesWomenList").trigger("reloadGrid");
						 $.messageBox({message:"育妇新增成功！"});
				         doAction("nurturesWomenDialog",data.id);
					 }
					 if("edit"==$("#mode").val()){	
						 if(data.death==true||data.death=="true"){
							 if($("#isLock").val()=="0"){
								 $("#nurturesWomenList").delRowData(data.id+"");
							 }else{
								 $("#nurturesWomenList").setRowData(data.id,data);
								 $("#"+data.id+"").css('color','#778899');
								// $("#nurturesWomenList").setSelection(data.id);
							 }
						 }else{
							 $("#nurturesWomenList").setRowData(data.id,data);
							// $("#nurturesWomenList").setSelection(data.id);
						 }		
						 $("#nurturesWomenList").trigger("reloadGrid");			 
						 $.messageBox({message:"育妇修改成功！"});
						 doAction("nurturesWomenDialog",data.id);
					 }
					 
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert("提交错误");
				}
				
				
			});
		},
		rules:{
		},
		messages:{
		}
	});

	function businessHouseToCurrentAddress(){
		var businessHouse = $("#businessHouse").val();
		var other = $("#other").val();
		var manCurrentAddressType = $("#manCurrentAddressType").val();
		if(manCurrentAddressType==businessHouse.split("-")[0]){
			var manCommunity = $("#manCommunity").val();
			var manBlock = $("#manBlock").val();
			var manUnit = $("#manUnit").val();
			var manRoom = $("#manRoom").val();
			var manCurrentAddress ="";
									
			if(manCommunity!=null&&manCommunity!=undefined&&manCommunity!=""){
				manCurrentAddress += manCommunity+"小区";
			}
			if(manBlock!=null&&manBlock!=undefined&&manBlock!=""){
				manCurrentAddress += manBlock+"幢";
			}
			if(manUnit!=null&&manUnit!=undefined&&manUnit!=""){
				manCurrentAddress += manUnit+"单元";
			}
			if(manRoom!=null&&manRoom!=undefined&&manRoom!=""){
				manCurrentAddress += manRoom+"室";
			}
			 $("#manCurrentAddress").val(manCurrentAddress);
		}else{
			$("#manCommunity").val("");
			$("#manBlock").val("");
			$("#manUnit").val("");
			$("#manRoom").val("");
		}
	}
	

});
</script>

