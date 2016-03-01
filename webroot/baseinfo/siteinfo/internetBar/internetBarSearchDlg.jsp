<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<jsp:include page="/includes/baseInclude.jsp" />
<form id="searchInternetBarForm">
	<div class="container container_24">
		<div class="grid_5 lable-right">
			<label class="form-lbl">场所名称：</label>
		</div>
		<div class="grid_19">
	    	<input type="text" name="searchInternetBarVo.placeName" id="placeName" class="form-txt" style="width:99%;" maxlength="50"/>
		</div>

		<div class="grid_5 lable-right">
			<label class="form-lbl">场所地址：</label>
		</div>
		<div class="grid_19">
	    	<input type="text" name="searchInternetBarVo.placeAddress" id="conditionPlaceAddress" class="form-txt" style="width:99%;" maxlength="50"/>
		</div>
		<div class="grid_5 lable-right">
			<label class="form-lbl">负责人：</label>
		</div>
		<div class="grid_7">
			<input type="text" id="conditionManager" name="searchInternetBarVo.manager" class="form-txt" />
		</div>    
		<div class="grid_5 lable-right">
			<label class="form-lbl">联系方式：</label>
		</div>
		<div class="grid_7">
			<input type="text" id="conditionMobile" name="searchInternetBarVo.mobile" class="form-txt" />
		</div>    
		<div class="grid_5 lable-right">
			<label class="form-lbl">网吧编号：</label>
		</div>
		<div class="grid_7">
			<input type="text" id="conditionBarCode" name="searchInternetBarVo.barCode" class="form-txt" />
		</div>    
		<div class="grid_5 lable-right">
			<label class="form-lbl">所在派出所名称：</label>
		</div>
		<div class="grid_7">
			<input type="text" id="conditionStationPolice" name="searchInternetBarVo.stationPolice" class="form-txt" />
		</div>    
		
		<jsp:include page="/baseinfo/common/commonHasOrHavntForSearch.jsp">
			<jsp:param value="治安负责人：" name="memberLabelName"/>
			<jsp:param value="searchInternetBarVo.hasServiceTeamMember" name="memberSelectName"/>
			<jsp:param value="巡场记录：" name="recordLabelName"/>
			<jsp:param value="searchInternetBarVo.hasServiceRecord" name="recordSelectName"/>
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
		<div class="grid_6 lable-right">
			<label class="form-lbl">营业面积：从</label>
		</div>
		<div class="grid_7">
			<input type="text" id="conditionOperationArea" name="searchInternetBarVo.operationAreaStart" class="form-txt" />
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">到&nbsp;&nbsp;</label>
		</div>
		<div class="grid_7">
			<input type="text" id="endConditionOperationArea" name="searchInternetBarVo.operationAreaEnd" class="form-txt" />
		</div>
		<div class="grid_6 lable-right">
            <label class="form-lbl">临时上网卡数：从</label>
        </div>
        <div class="grid_7">
        	<input	type="text" id="conditionTempNetCardNumStart" name="searchInternetBarVo.tempNetCardNumStart"class="form-txt">
        </div>
        
        <div class="grid_4 lable-right">
            <label class="form-lbl">到&nbsp;&nbsp;</label>
        </div>
        <div class="grid_7">
        	<input	type="text" id="tempNetCardNumEnd" name="searchInternetBarVo.tempNetCardNumEnd"class="form-txt">
        </div>
        <div class="grid_6 lable-right">
            <label class="form-lbl">电脑总数：从</label>
        </div>
        <div class="grid_7">
        	<input	type="text" id="totalComputerNumStart" name="searchInternetBarVo.totalComputerNumStart"class="form-txt">
        </div>
        
        <div class="grid_4 lable-right">
            <label class="form-lbl">到&nbsp;&nbsp;</label>
        </div>
        <div class="grid_7">
        	<input	type="text" id="totalComputerNumEnd" name="searchInternetBarVo.totalComputerNumEnd"class="form-txt">
        </div>
         <div class="grid_6 lable-right">
            <label class="form-lbl">网络文化经营许可证号：</label>
        </div>
        <div class="grid_18">
        	<input	type="text" id="netCultureLicenceNo" name="searchInternetBarVo.netCultureLicenceNo" style="width:99%;" class="form-txt">
        </div>
         <div class="grid_6 lable-right">
            <label class="form-lbl">网络安全审核意见证号：</label>
        </div>
        <div class="grid_18">
        	<input	type="text" id="netSecurityAuditNo" name="searchInternetBarVo.netSecurityAuditNo" style="width:99%;" class="form-txt">
        </div>
         <div class="grid_6 lable-right">
            <label class="form-lbl">消防审核意见证书号：</label>
        </div>
        <div class="grid_18">
        	<input	type="text" id="fireAuditDocumentNo" name="searchInternetBarVo.fireAuditDocumentNo" style="width:99%;" class="form-txt">
        </div>
         <div class="grid_6 lable-right">
            <label class="form-lbl">网络接入服务商：</label>
        </div>
        <div class="grid_18">
        	<input	type="text" id="netAccessProviders" name="searchInternetBarVo.netAccessProviders" style="width:99%;" class="form-txt">
        </div>
        <div class="grid_6 lable-right">
            <label class="form-lbl">现使用IP地址：</label>
        </div>
        <div class="grid_18">
        	<input	type="text" id="useIP" name="searchInternetBarVo.useIP" style="width:99%;" class="form-txt">
        </div>
         <div class="grid_6 lable-right">
            <label class="form-lbl">宽带接入方式：</label>
        </div>
        <div class="grid_18">
        	<input	type="text" id="internetAccessType" name="searchInternetBarVo.internetAccessType" style="width:99%;" class="form-txt">
        </div>
	</div>
	</div>
</form>
<script type="text/javascript">
$(document).ready(function(){

	$("#showMoreBtn").toggle(
		function(){
			$("#internetBarDialog").dialog( "option" , {height:450});
			$("#showMoreCtt").show();
			$(this).addClass("cur").text("隐藏更多条件");
		},
		function(){
			$("#internetBarDialog").dialog( "option" , {height:320} );
			$("#showMoreCtt").hide();
			$(this).removeClass("cur").text("显示更多条件");
		}
	);
	
	$('#conditionOpeningDate').datePicker({
		yearRange:'1900:2030',
		dateFormat:'yy-mm-dd',
    	maxDate:'+0d',
       	onSelect:function(dateText, inst) {
			if(dateText!=null&&dateText!=''){
				var dateUnit=dateText.split('-');
				var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
				$("#endConditionOpeningDate").datepicker("option", "minDate",date);
			}
		}
	});
	$('#conditionLicenseDate').datePicker({
		yearRange:'1900:2030',
		dateFormat:'yy-mm-dd',
    	maxDate:'+0d',
       	onSelect:function(dateText, inst) {
			if(dateText!=null&&dateText!=''){
				var dateUnit=dateText.split('-');
				var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
				$("#endConditionLicenseDate").datepicker("option", "minDate",date);
			}
		}
	});
	$('#endConditionOpeningDate').datePicker({
		yearRange:'1900:2030',
		dateFormat:'yy-mm-dd',
    	maxDate:'+0d',
       	onSelect:function(dateText, inst) {
			if(dateText!=null&&dateText!=''){
				var dateUnit=dateText.split('-');
				var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
				$("#conditionOpeningDate").datepicker("option", "maxDate",date);
			}
		}
	});
	$('#endConditionLicenseDate').datePicker({
		yearRange:'1900:2030',
		dateFormat:'yy-mm-dd',
    	maxDate:'+0d',
       	onSelect:function(dateText, inst) {
			if(dateText!=null&&dateText!=''){
				var dateUnit=dateText.split('-');
				var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
				$("#conditionLicenseDate").datepicker("option", "maxDate",date);
			}
		}
	});

	$('#logOutTimeStart').datePicker({
		yearRange: '1900:2030',
		dateFormat:'yy-mm-dd',
        maxDate:'+0d',
        onSelect: function(dateText, inst) {
		if(dateText!=null&&dateText!=''){
			var dateUnit=dateText.split('-');
			var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
			$("#logOutTimeStart").datepicker("option", "minDate",date);
			}
		}
	});

	$('#logOutTimeEnd').datePicker({
        onSelect: function(dateText, inst) {
		if(dateText!=null&&dateText!=''){
			var dateUnit=dateText.split('-');
			var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
			$("#logOutTimeEnd").datepicker("option", "maxDate",date);
			}
		}
	});
	
});

</script>
