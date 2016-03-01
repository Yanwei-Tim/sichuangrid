<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>

<div id="search-condition-form" title="建筑施工单位信息查询" class="container container_24">

		<input type="hidden" id="organizationId" name="organizationId" value="${organization.id}" />
	
		<div class="grid_6 lable-right">
			<label class="form-lbl">工程名称：</label>
		</div>
		<div class="grid_5 form-left">
			<input type="text" id="conditionProjectName" name="conditionProjectName" class="form-txt" />
		</div>
  		
		<div class="grid_6 lable-right">
			<label class="form-lbl">工程地址：</label>
		</div>
		<div class="grid_5 form-left">
			<input type="text" id="conditionProjectAddress" name="conditionProjectAddress" class="form-txt" />
		</div>
		
		
 		<div class='clearLine'></div>
		
		<div class="grid_6 lable-right">
			<label class="form-lbl">开发单位：</label>
		</div>
		<div class="grid_5 form-left">
			<input type="text" id="conditionDevelopmentUnit" 
			name="conditionDevelopmentUnit" class="form-txt" />
		</div>
			
		<div class="grid_6 lable-right">
			<label class="form-lbl">开发单位联系人：</label>
		</div>
		<div class="grid_5 form-left">
			<input type="text" id="conditionDevelopmentUnitContactPerson" 
			name="conditionDevelopmentUnitContactPerson" class="form-txt" maxlength="15" />
		</div>
					
		<div class="grid_6 lable-right">
			<label class="form-lbl">开发单位联系人手机：</label>
		</div>
		<div class="grid_5 form-left">
			<input type="text" id="conditionDevelopmentUnitContactPersonPhone" 
			name="conditionDevelopmentUnitContactPersonPhone" class="form-txt" maxlength="11" />
		</div>
		<div class='clearLine'></div>
		
		<hr>
		<div id="bs-accordion" style="padding-bottom:10px;">
			<div>
				<h1 class="" style="text-align:right;">更多查询条件</h1>
				 <div class="accdContent">
				 	<div class="grid_6 lable-right">
						<label class="form-lbl">总承包方：</label>
					</div>
					<div class="grid_5 form-left">
						<input type="text" id="conditionContractor" 
						name="conditionContractor" class="form-txt" />
					</div>
			
					<div class="grid_6 lable-right">
						<label class="form-lbl">总承包方联系人：</label>
					</div>
					<div class="grid_5 form-left">
						<input type="text" id="conditionContractorContactPerson" 
						name="conditionContractorContactPerson" class="form-txt" maxlength="15" />
					</div>
						
					<div class='clearLine'></div>
						
					<div class="grid_6 lable-right">
						<label class="form-lbl">总承包方联系人手机：</label>
					</div>
					<div class="grid_5 form-left">
						<input type="text" id="conditionContractorContactPersonPhone" 
						name="conditionContractorContactPersonPhone" class="form-txt" maxlength="11" />
					</div>
					
					<div class='clearLine'></div>	
											
					<div class="grid_6 lable-right">
						<label class="form-lbl">劳务方：</label>
					</div>
					<div class="grid_5 form-left">
						<input type="text" id="conditionLaborer" name="conditionLaborer" class="form-txt" />
					</div>			
						
					<div class="grid_6 lable-right">
						<label class="form-lbl">劳务方联系人：</label>
					</div>
					<div class="grid_5 form-left">
						<input type="text" id="conditionLaborerContractorContactPerson" name="conditionLaborerContractorContactPerson" class="form-txt" maxlength="15" />
					</div>
						
					<div class='clearLine'></div>
						
					<div class="grid_6 lable-right">
						<label class="form-lbl">劳务方联系人手机：</label>
					</div>
					<div class="grid_5 form-left">
						<input type="text" id="conditionLaborerContractorContactPersonPhone" name="conditionLaborerContractorContactPersonPhone" class="form-txt" maxlength="11" />
					</div>
					<div class='clearLine'></div>
				 </div>
			</div>
		</div>
		
		
</div>

<script type="text/javascript">
$(document).ready(function(){
	var _accordion=$("#bs-accordion div>h1");
	_accordion.toggle(
		function(){
		$("#constructionUnitDialog").dialog( "option" , {height:360});
	    buttom:($(window).height() - 350) * 0.5;   
		$(this).next("div").show();
		$(this).text("隐藏更多条件");
		},
		function(){
		$("#constructionUnitDialog").dialog( "option" , {height:250} );
		$(this).next("div").hide();
		$(this).text("显示更多条件");
	});
	
	var placeName = $("#searchByPlaceName").val();
	if(placeName=="请输入场所名称"){
		$("#conditionProjectName").val("");
	}else{
		$("#conditionProjectName").val(placeName);
	}
	$.ajax({
		async: false,   
		url: "${path}/sysadmin/orgManage/getOrgRelativePath.action",
		data:{
			"organization.id":$("#organizationId").val()
		},
		success:function(responseData){
			$("#orgName").val(responseData);
		}
	});
	
});
</script>