<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>

<style type="text/css">
	div.terms {
		width:552px;
		height:50px;
		border:1px solid #7F9DB9;
		background:#ffffff;
		padding:6px;
		overflow:auto;
	}
</style>

<div id="search-condition-form" title="事件转台账信息查询" class="container container_24">
<form id="searchForm" method="post" action="">
        <input id="conditionOccurOrgId" name="searchVo.occurOrg.id" type="hidden" />
   		<div>
   			
			<div class="grid_4 lable-right">
				<label class="form-lbl">事件编号：</label>
			</div>
			<div class="grid_7 form-left">
		    	<input type="text" id="serialNumber" name="searchVo.serialNumber" maxlength="30" value="" class="form-txt" />
			</div>
			<div class="grid_4 lable-right">
	  			<label class="form-lbl">发生网格：</label>
	  		</div>
    		<div class="grid_7 form-left">
				 <input type="text" id="occurOrgSelector" name="selectOrgName"  value="请点击此处选择" readonly="readonly" class="form-txt"  style="color: grey;" /> 
    		</div>
    		<div class='clearLine'></div>
    			
			<div class="grid_4 lable-right">
				<label class="form-lbl">发生时间：</label>
			</div>
			<div class="grid_7 form-left">
			    <input type="text" id="conditionOccurFrom" name="searchVo.occurFrom" value="" readonly="readonly" class="form-txt" />
			</div>
			<div class="grid_4 lable-right">
				<label class="form-lbl">到：</label>
			</div>
			<div class="grid_7 form-left">
				<input type="text" id="conditionOccurEnd" name="searchVo.occurEnd" value="" readonly="readonly" class="form-txt" />
			</div>
			<div class='clearLine'></div>
			<div class="grid_11 lable-left">
				<input type="checkbox" id="conditionStatus" name="searchVo.status" value="0" />
				<label class="form-check-text" for="conditionStatus">是否转换</label>
			</div>
			
	</div>
  </form>
</div>

<script type="text/javascript">

$(document).ready(function(){

	$("#conditionStatus").change(function(event){
		if($("#conditionStatus").attr("checked")=="checked"){
			$("#conditionStatus").val(1);
		}else{
			$("#conditionStatus").val(0);
		}
	});

	
	$("ul.zc-sf li label").click(function(){
		$(this).parent().find("input").click();
	})
	
	$("#conditionOccurFrom").datePicker({
		yearRange:"1900:2030",
		dateFormat:"yy-mm-dd",
   		maxDate:"+0d",
       	onSelect: function(dateText, inst) {
			if(dateText!=null&&dateText!=""){
				var dateUnit=dateText.split("-");
				var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
				$("#conditionOccurEnd").datepicker("option","minDate",date);
			}
		}
    }); 
	
	$("#conditionOccurEnd").datePicker({
		yearRange:"1900:2030",
		dateFormat:"yy-mm-dd",
    	maxDate:"+1d",
    	timeFormat: 'HH:mm:ss',
   		onSelect: function(dateText, inst) {
			if(dateText!=null&&dateText!=""){
				var dateUnit=dateText.split("-");
				var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
				$("#conditionOccurFrom").datepicker("option", "maxDate",date);
			}
		}
	});
	
	
	
	
	$("#orgName").click(function(event){
		$("#noticeDialog").createDialog({
			width: 300,
			height: 350,
			title:'请选择一个部门',
			url:'${path}/common/organizationSelector.jsp',
			buttons: {
				"确定" : function(){
					closeDialog();
				},
				"取消" : function(){
					$("#noticeDialog").dialog("close");
				}
			}
		});
	});
	$("#occurOrgSelector").one("click", function(){
		initOccurOrgSelector();
	});
});



function setZone(selectOrgId,selectOrgName){
	$("#conditionOccurOrgId").val(selectOrgId);
	$("#orgName").val(selectOrgName);
}

function initOccurOrgSelector(){
	var tree=$("#occurOrgSelector").treeSelect({
		inputName:"searchVo.occurOrg.id"
	});
}

</script>