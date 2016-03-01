<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<jsp:include page="/includes/baseInclude.jsp" />
<form id="searchPublicComplexPlacesForm">
	<div class="container container_24">
		<div class="grid_5 lable-right">
			<label class="form-lbl">场所名称：</label>
		</div>
		<div class="grid_19">
	    	<input type="text" name="searchPublicComplexPlacesVo.unitName" id="conditionPlaceName" class="form-txt" style="width:99%" maxlength="50"/>
		</div>

		<div class="grid_5 lable-right">
			<label class="form-lbl">场所地址：</label>
		</div>
		<div class="grid_19">
	    	<input type="text" name="searchPublicComplexPlacesVo.placeAddress" id="conditionPlaceAddress" class="form-txt" style="width:99%" maxlength="50"/>
		</div>

       <div class="grid_5 lable-right">
  			<label class="form-lb1">场所类型：</label>
   		</div>
   		<div class="grid_7">
		  <select name="searchPublicComplexPlacesVo.typeId" id="type" class="form-select form-txt" >
		   		<pop:PropertyDictReleationSelectTag name="@com.tianque.domain.property.PropertyTypes@PUBLICCOMPLEXPLACES_TYPE"  reletionId="detailedType"
	   		reletionName="@com.tianque.domain.property.PropertyTypes@PUBLICCOMPLEXPLACES_DETAILEDTYPE" id="type" />
			</select>
		</div>
		<div class="grid_5 lable-right">
  				<label class="form-lb1">场所细类：</label>
   		</div>
   		<div class="grid_7">
		   <select name="searchPublicComplexPlacesVo.detailedTypeId" id="detailedType" class="form-txt" >
		   		<%-- <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@PUBLICPLACE_CLOAKROOM"  /> --%>
			</select>
		</div>
        
        <jsp:include page="/baseinfo/common/commonHasOrHavntForSearch.jsp">
			<jsp:param value="治安负责人：" name="memberLabelName"/>
			<jsp:param value="searchPublicComplexPlacesVo.hasServiceTeamMember" name="memberSelectName"/>
			<jsp:param value="巡场记录：" name="recordLabelName"/>
			<jsp:param value="searchPublicComplexPlacesVo.hasServiceRecord" name="recordSelectName"/>
		</jsp:include>
		
	    <div class="grid_5 lable-right">
  			<label class="form-lb1">关注状态：</label>
	   	</div>
	   	<div class="grid_7">
			<select id="isLock" class="form-txt" >
				<option value="" selected="selected">全部</option>
				<option value="false">现在关注</option>
				<option value="true">曾经关注</option>
			</select>
		</div>
	<div class='clearLine'></div>
    <div class="showMoreBtnBox"><h1 class="searchArraw" id="showMoreBtn">显示更多条件</h1></div>
	<div id="showMoreCtt" style="display: none">
		<div class="grid_5 lable-right">
			<label class="form-lbl">负责人：</label>
		</div>
		<div class="grid_7">
			<input type="text" id="conditionManager" name="searchPublicComplexPlacesVo.manager" class="form-txt" />
		</div>    
		<div class="grid_5 lable-right">
			<label class="form-lbl">负责人电话：</label>
		</div>
		<div class="grid_7">
			<input type="text" id="conditionManagerTelephone" name="searchPublicComplexPlacesVo.managerTelephone" class="form-txt" />
		</div>
		       
	    <div class="grid_5 lable-right">
			<label class="form-lbl">负责人电话手机：</label>
		</div>
		<div class="grid_7">
	    	<input type="text" name="searchPublicComplexPlacesVo.managerMobile" id="conditionManagerMobile"  class="form-txt" maxlength="18"/>
		</div>
	   </div>
	</div>
</form>
<script type="text/javascript">
$(document).ready(function(){

	$("#showMoreBtn").toggle(
		function(){
			$("#publicComplexPlacesDialog").dialog( "option" , {height:450});
			$("#showMoreCtt").show();
			$(this).addClass("cur").text("隐藏更多条件");
		},
		function(){
			$("#publicComplexPlacesDialog").dialog( "option" , {height:320} );
			$("#showMoreCtt").hide();
			$(this).removeClass("cur").text("显示更多条件");
		}
	);
	
});

</script>
