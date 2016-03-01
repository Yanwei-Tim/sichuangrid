<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<div id="dialog-form" title="队伍信息查看" class="container container_24">
        
		<div class="grid_8 lable-right">
   			<em class="form-req">*</em>
   			<label class="form-lb1">队伍所在网格：</label>
   		</div>
		<div class="grid_12">
		    	<div  title="请选择部门">
					<div class="nav" >
						<input type="text" disabled value="${fourTeams.organization.orgName}" class="form-txt" />
					 </div>
				</div>
  		</div>
  		
  		<div class='clearLine'>&nbsp;</div>
  		<div class="grid_8 lable-right">
			<label class="form-lbl">主管部门： </label>
		</div>
		<div class="grid_12" >
<!-- 		<input type="text" name="fourTeams.competentDepartment.id" id="competentDepartment"/> -->
		   <select id="competentDepartment" name="fourTeams.competentDepartment.id" class="form-txt" disabled>
		   <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@FOURTEAMS_COMPETENT_DEPARTMENT"  defaultValue="${fourTeams.competentDepartment.id }"/>
		   </select>
  		</div>
  		
  		<div class='clearLine'>&nbsp;</div>
		<div class="grid_8 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">队伍名称： </label>
		</div>
		<div class="grid_12">
		   <input type="text"  name="fourTeams.names" id="names" maxlength="32" value="${fourTeams.names}" class="form-txt" disabled/>
  		</div>
  		
  		<div class='clearLine'>&nbsp;</div>
		<div class="grid_8 lable-right">
			<label class="form-lbl">组建部门： </label>
		</div>
		<div class="grid_12" >
				<input id="transferTo"  name="fourTeams.departementName" value="${fourTeams.departementName }"  class="form-txt" disabled/>
		</div> 
				
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_8 lable-right">
			<label class="form-lbl">服务范围： </label>
		</div>
		<div class="grid_12 heightAuto">
		    <textarea rows="5" cols="100" name="fourTeams.serviceArea" id="serviceArea" class="form-txt" style="float:left;overflow:auto"  readonly="readonly">${fourTeams.serviceArea }</textarea>
  		</div>
  		
  		<div class="grid_8 lable-right">
			<label class="form-lbl">备注： </label>
		</div>
		<div class="grid_12 heightAuto">
   			<textarea rows="5" cols="100" name="fourTeams.comments" id="comments" class="form-txt" style="overflow:auto" readonly="readonly">${fourTeams.comments }</textarea>
   		</div>
</div>	

