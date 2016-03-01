<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="container container_24" >
	<form action="" method="post" id="maintainForm">
		<pop:token />
		<input type="hidden" value="${mode }" id="mode"/>
		<input type="hidden" value="${issueStandardForFunOrg.id }" name="issueStandardForFunOrg.id" id="issueStandardForFunOrgId"/>
		<div class="grid_6 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">职能部门：</label>
		</div>
		<div class="grid_18">
			<select name="issueStandardForFunOrg.organization.id" class='form-txt' id="organizationId">
				<c:forEach items="${funOrg}" var="fo">
					<option value="${fo.key }">${fo.value }</option>
				</c:forEach>
			</select>
		</div>
		<div class="grid_6 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">项目名称：</label>
		</div>
		<div class="grid_18">
			<select name="issueStandardForFunOrg.itemName.id" class='form-txt' id="itemName">
		   		<pop:OptionTag notNull="true" name="@com.tianque.domain.property.PropertyTypes@ITEM_NAME" defaultValue="${issueStandardForFunOrg.itemName.id}" />
			</select>
		</div>
    	<div class='clearLine'></div>
    	<div class="grid_2"></div>
    	<div class="grid_22">
    		<em class="form-req">*</em>
			<label class="form-lbl" style="color: red;">办理时限（至少填写一项）</label>
		</div>
		<table>
			<tr>
				<td colspan="2">
					<div style="padding: 15px 0px 5px 50px;color: #C9C9C9;">——<span style="font-weight: bolder;color:#353535;font-size:14px;padding:0px 10px 0px 10px">受理</span>———————————————————————————————————</div>		
				</td>
			</tr>
			<tr>
				<td style="padding: 0px 0px 0px 60px;">
			    	<div class='clearLine'></div>
					<div class="grid_6 lable-right">
						<label class="form-lbl">黄牌时限：</label>
					</div>
					<div class="grid_4">
						<input type="text" name="issueStandardForFunOrg.yellowLimitAccept" id="yellowLimitAccept" 
						class='form-txt' value="${issueStandardForFunOrg.yellowLimitAccept }" />
					</div>
					<div class="grid_7">
						&nbsp;个工作日
					</div>
					<div class="grid_7 lable-right" style="color:red;">
					</div>
				</td>
				<td>
					<div class='clearLine'></div>
					<div class="grid_6 lable-right">
						<label class="form-lbl">红牌时限：</label>
					</div>
					<div class="grid_4">
						<input type="text" name="issueStandardForFunOrg.redLimitAccept" id="redLimitAccept" 
						class='form-txt' value="${issueStandardForFunOrg.redLimitAccept }" maxlength="50"/>
					</div>
					<div class="grid_7">
						&nbsp;个工作日
					</div>
					<div class="grid_7 lable-right" style="color:red;">
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="2" style="color: red;padding: 15px 0px 0px 60px;">
					注:受理的时限标准从事件交办之日起计算，红牌时限需大于黄牌时限
				</td>
			</tr>
			
			<tr>
				<td colspan="2">
					<div style="padding: 15px 0px 5px 50px;color: #C9C9C9;">——<span style="font-weight: bolder;color:#353535;font-size:14px;padding:0px 10px 0px 10px">办理</span>———————————————————————————————————</div>		
				</td>
			</tr>
			<tr>
				<td style="padding: 0px 0px 0px 60px;">
			       <div class='clearLine'></div>
					<div class="grid_6 lable-right">
						<label class="form-lbl">黄牌时限：</label>
					</div>
					<div class="grid_4">
						<input type="text" name="issueStandardForFunOrg.yellowLimitHandle" id="yellowLimitHandle" 
						class='form-txt' value="${issueStandardForFunOrg.yellowLimitHandle }"/>
					</div>
					<div class="grid_7">
						&nbsp;个工作日
					</div>
					<div class="grid_7 lable-right" style="color:red;">
					</div>				
				</td>
				<td>
					<div class='clearLine'></div>
					<div class="grid_6 lable-right">
						<label class="form-lbl">红牌时限：</label>
					</div>
					<div class="grid_4">
						<input type="text" name="issueStandardForFunOrg.redLimitHandle" id="redLimitHandle" 
						class='form-txt' value="${issueStandardForFunOrg.redLimitHandle }" maxlength="50"/>
					</div>
					<div class="grid_7">
						&nbsp;个工作日
					</div>
					<div class="grid_7 lable-right" style="color:red;">
					</div>				
				</td>
			</tr>
			<tr>
				<td colspan="2" style="color: red;padding: 15px 0px 0px 60px;">
					注:办理的时限标准从事件受理之日起计算，红牌时限需大于黄牌时限
				</td>
			</tr>
			
			<tr>
				<td colspan="2">
					<div style="padding: 15px 0px 5px 50px;color: #C9C9C9;">——<span style="font-weight: bolder;color:#353535;font-size:14px;padding:0px 10px 0px 10px">验证</span>———————————————————————————————————</div>		
				</td>
			</tr>
			<tr>
				<td style="padding: 0px 0px 0px 60px;">
			       <div class='clearLine'></div>
					<div class="grid_6 lable-right">
						<label class="form-lbl">黄牌时限：</label>
					</div>
					<div class="grid_4">
						<input type="text" name="issueStandardForFunOrg.yellowLimitVerify" id="yellowLimitVerify" 
						class='form-txt' value="${issueStandardForFunOrg.yellowLimitVerify }"/>
					</div>
					<div class="grid_7">
						&nbsp;个工作日
					</div>
					<div class="grid_7 lable-right" style="color:red;">
					</div>
				</td>
				<td>
					<div class='clearLine'></div>
					<div class="grid_6 lable-right">
						<label class="form-lbl">红牌时限：</label>
					</div>
					<div class="grid_4">
						<input type="text" name="issueStandardForFunOrg.redLimitVerify" id="redLimitVerify" 
						class='form-txt' value="${issueStandardForFunOrg.redLimitVerify }" maxlength="50"/>
					</div>
					<div class="grid_7">
						&nbsp;个工作日
					</div>
					<div class="grid_7 lable-right" style="color:red;">
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="2" style="color: red;padding: 15px 0px 0px 60px;">
					注:验证的时限标准从事件结案之日起计算，红牌时限需大于黄牌时限
				</td>
			</tr>			
		</table>
		
		<div class="grid_6 lable-right">
		   <label class="form-lbl">备注：</label>
		</div>
		<div class="grid_18">
			<textarea rows="2" cols="" id="remark" name="issueStandardForFunOrg.remark" class="form-txt" maxlength="200">${issueStandardForFunOrg.remark }</textarea>
		</div>
		<input name="isSubmit" id="isSubmit" type="hidden" value="">
		<input type="hidden" id="itemNameIds" name="issueStandardForFunOrg.itemNameIds">
	</form>
</div>
<script>

function callback(){
    var dfop = {
    	organizationId:'<s:property value="issueStandardForFunOrg.organization.id"/>',
    	ifAdd:'${mode=="add"}',
    	ifEdit:'${mode=="edit"}'
    
    }
    TQ.maintainFunctionOrgTimeLimitStandardDlg(dfop)
}
$.cacheScript({
    url:'/resource/getScript/issue/issueAccessConfig/maintainFunctionOrgTimeLimitStandardDlg.js',
    callback: callback
})

</script>
