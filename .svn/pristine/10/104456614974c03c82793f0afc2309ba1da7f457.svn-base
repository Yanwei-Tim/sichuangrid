<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<jsp:include page="/includes/baseInclude.jsp" />
<div id="dialog-form" class="container container_24">
	<form id="searchDangerousChemicalsUnitForm">
		<div class="grid_5 lable-right">
			<label class="form-lbl">单位名称:
			</label>
		</div>
		<div class="grid_19">
	    	<input type="text" name="searchDangerousChemicalsUnitVo.unitName" id="conditionUnitName" class="form-txt" maxlength="50" style="width:99%;" />
		</div>

		<div class="grid_5 lable-right">
			<label class="form-lbl">单位地址：</label>
		</div>
		<div class="grid_19">
			<input type="text" id="conditionUnitAddress" name="searchDangerousChemicalsUnitVo.unitAddress" class="form-txt"  maxlength="50" style="width:99%;"/>
		</div>
		<div class="grid_5 lable-right">
            <label class="form-lbl">负责人：</label>
        </div>
        <div class="grid_7">
        <input type="text" id="conditionSuperintendent" name="searchDangerousChemicalsUnitVo.superintendent" class="form-txt"  maxlength="50"/>
        </div>
		<div class="grid_5 lable-right">
            <label class="form-lbl">联系电话：</label>
        </div>
        <div class="grid_7">
            <input type="text" id="conditionTelephone" name="searchDangerousChemicalsUnitVo.telephone" class="form-txt" maxlength="11" />
        </div>
        
       	<div class="grid_5 lable-right">
  			<label class="form-lb1">关注状态：</label>
	   	</div>
	   	<div class="grid_7">
			<select id="isLock" class="form-txt" >
				<option value=""  selected="selected">全部</option>
				<option value="false">现在关注</option>
				<option value="true">曾经关注</option>
			</select>
		</div>
        <div class='clearLine'></div>
		<div class="grid_5 lable-right">
		     <label class="form-lbl">单位类别： </label>
		</div>
		<div class="grid_7">
		     <input type="text" id="conditionUnitType" name="searchDangerousChemicalsUnitVo.unitType" class="form-txt" maxlength="16" />
		</div>
		        
		<div class="grid_5 lable-right">
			<label class="form-lbl">货物类别：</label>
		</div>
		<div class="grid_7">
			<input type="text" id="conditionCommodityType" name="searchDangerousChemicalsUnitVo.commodityType" class="form-txt" maxlength="16" />
		</div>
		<div class="grid_5 lable-right">
			<label class="form-lbl">副本许可范围：</label>
		</div>
		<div class="grid_19">
			<input type="text" id="conditionCopyScope" name="searchDangerousChemicalsUnitVo.copyScope" class="form-txt" style="width:99%;"/>
		</div>
		       
		<div class="clearLine">&nbsp;</div>
		<div class="grid_5 lable-right">
			<label class="form-lbl">存储设备：</label>
		</div>
		<div class="grid_19">
			<input type="text" id="conditionStorageDevice" name="searchDangerousChemicalsUnitVo.storageDevice" class="form-txt" style="width:99%;"/>
		</div>
		<div class='clearLine'>&nbsp;</div> 
		<jsp:include page="/baseinfo/common/commonHasOrHavntForSearch.jsp">
			<jsp:param value="治安负责人：" name="memberLabelName"/>
			<jsp:param value="searchDangerousChemicalsUnitVo.hasServiceTeamMember" name="memberSelectName"/>
			<jsp:param value="巡场记录：" name="recordLabelName"/>
			<jsp:param value="searchDangerousChemicalsUnitVo.hasServiceRecord" name="recordSelectName"/>
		</jsp:include>
	</form>
</div>
<script type="text/javascript">
$(document).ready(function(){

	$("#showMoreBtn").toggle(
		function(){
			$("#dangerousChemicalsUnitDialog").dialog( "option" , {height:450});
			$("#showMoreCtt").show();
			$(this).addClass("cur").text("隐藏更多条件");
		},
		function(){
			$("#dangerousChemicalsUnitDialog").dialog( "option" , {height:320} );
			$("#showMoreCtt").hide();
			$(this).removeClass("cur").text("显示更多条件");
		}
	);
	$('#startConditionLogOut').datePicker({
		yearRange: '1900:2030',
		dateFormat:'yy-mm-dd',
        maxDate:'+0d',
        onSelect: function(dateText, inst) {
		if(dateText!=null&&dateText!=''){
			var dateUnit=dateText.split('-');
			var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
			$("#endConditionLogOut").datepicker("option", "minDate",date);
			}
		}
	});

	$('#endConditionLogOut').datePicker({
		yearRange: '1900:2030',
		dateFormat:'yy-mm-dd',
        maxDate:'+0d',
        onSelect: function(dateText, inst) {
		if(dateText!=null&&dateText!=''){
			var dateUnit=dateText.split('-');
			var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
			$("#startConditionLogOut").datepicker("option", "minDate",date);
			}
		}
	});

	 
});
</script>
