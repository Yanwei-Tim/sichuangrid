<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<jsp:include page="/includes/baseInclude.jsp" />
<form id="searchUndergroundSpaceForm">
	<div class="container container_24">
		<div class="grid_4 lable-right">
			<label class="form-lbl">单位名称:
			</label>
		</div>
		<div class="grid_18">
	    	<input type="text" name="searchUndergroundSpaceVo.unitName" id="conditionUnitName" class="form-txt" maxlength="50"/>
		</div>

		<div class="grid_4 lable-right">
			<label class="form-lbl">单位地址：</label>
		</div>
		<div class="grid_18">
			<input type="text" id="conditionUnitAddress" name="searchUndergroundSpaceVo.unitAddress" class="form-txt"  maxlength="50"/>
		</div>
		<div class="grid_4 lable-right">
            <label class="form-lbl">负责人：</label>
        </div>
        <div class="grid_7">
        <input type="text" id="conditionSuperintendent" name="searchUndergroundSpaceVo.superintendent" class="form-txt"  maxlength="50"/>
        </div>
		<div class="grid_4 lable-right">
            <label class="form-lbl">联系电话：</label>
        </div>
        <div class="grid_7">
            <input type="text" id="conditionTelephone" name="searchUndergroundSpaceVo.telephone" class="form-txt" maxlength="11" />
        </div>
        <div class='clearLine'></div>
    	<hr>
    	
    	<div id="bs-accordion" style="padding-bottom:10px;">
	  		 <div>
		        <h1 class="" style="text-align:right;">更多查询条件</h1>
		        <div class="accdContent">
					 <div class="grid_4 lable-right">
			            <label class="form-lbl">单位类别： </label>
			        </div>
			        <div class="grid_7">
			            <input type="text" id="conditionUnitType" name="searchUndergroundSpaceVo.unitType" class="form-txt" maxlength="16" />
			        </div>
			        
					<div class="grid_4 lable-right">
						<label class="form-lbl">货物类别：</label>
					</div>
					<div class="grid_7">
			            <input type="text" id="conditionCommodityType" name="searchUndergroundSpaceVo.commodityType" class="form-txt" maxlength="16" />
			        </div>
			<div class="grid_4 lable-right">
			            <label class="form-lbl">注销时间 从：</label>
			        </div>
			        <div class="grid_7">
			            <input type="text" id="startConditionLogOut" name="searchUndergroundSpaceVo.startLogOutTime" readonly="readonly" class="form-txt" />
			        </div>
			     <div class="grid_4 lable-right">
						<label class="form-lbl">至：</label>
					</div>
			        <div class="grid_7">
			            <input type="text" id="endConditionLogOut" name="searchUndergroundSpaceVo.endLogOutTime" readonly="readonly" class="form-txt" />
			        </div>
					<div class="grid_4 lable-right">
						<label class="form-lbl">副本许可范围：</label>
					</div>
					<div class="grid_18">
						<input type="text" id="conditionCopyScope" name="searchUndergroundSpaceVo.copyScope" class="form-txt" />
					</div>
			       
			        <div class="clearLine">&nbsp;</div>
					<div class="grid_4 lable-right">
						<label class="form-lbl">存储设备：</label>
					</div>
					<div class="grid_18">
						<input type="text" id="conditionStorageDevice" name="searchUndergroundSpaceVo.storageDevice" class="form-txt" />
					</div>
			 		
			      <div class='clearLine'></div>  
			       <div class="grid_4 lable-right">
						<label class="form-lbl">注销原因：</label>
					</div>
					<div class="grid_18">
						<input type="text" id="conditionLogOutReason" name="searchUndergroundSpaceVo.logOutReason" class="form-txt" />
					</div>
			</div>
			</div>		
		</div>
	</div>
</form>
<script type="text/javascript">
$(document).ready(function(){
	var _accordion=$("#bs-accordion div>h1");
	_accordion.toggle(
		function(){
		$("#undergroundSpaceDlg").dialog( "option" , {height:510});
	    buttom:($(window).height() - 350) * 0.5;   
		$(this).next("div").show();
		$(this).text("隐藏更多条件");
		},
		function(){
		$("#undergroundSpaceDlg").dialog( "option" , {height:280} );
		$(this).next("div").hide();
		$(this).text("显示更多条件");
	});
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
