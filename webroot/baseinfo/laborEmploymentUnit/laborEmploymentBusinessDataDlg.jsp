<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
<div class="container container_24">
	<form id="maintainForm" method="post"	action="${path}/baseinfo/laborEmploymentUnitManage/maintainBusinessInfo.action" >
	<pop:token />
		<input type="hidden" name="mode" id="mode" value="${mode}" />
		<input id="organizationId"	type="hidden" name="organizationId" value="${location.organization.id }" />
		<input type="hidden" name="location.id" value="${location.id }" id="laborEmploymentUnitId"/>
		
		<div class="grid_5 lable-right">
		   	<em class="form-req">*</em>
			<label class="form-lb1">所属网格：</label>
		</div>
		<div class="grid_19">
			<input type="text"  id="orgName"  name="location.organization.orgName"  readonly value="${location.organization.orgName}" class="form-txt" />
		</div>
	<div class='clearLine'>&nbsp;</div>  
		<div class="grid_5 lable-right">
			<label class="form-lb1">从业人数：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="location.employeesNum" id="employeesNum" maxlength="9"  value='${location.employeesNum }' class="form-txt {number:true,min:0,max:999999999,messages:{number: '从业人数需要输入正数',	min: '从业人数需要输入正数',max: '从业人数最大输入999999999'}}" />
		</div>
	    <div class="grid_5 lable-right">
			<label class="form-lb1">女职工数：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="location.femaleWorkerNumber" id="femaleWorkerNumber" maxlength="9" value='${location.femaleWorkerNumber}' class="form-txt {number:true,min:0,max:999999999,messages:{number: '女职工数需要输入正数',	min: '女职工数需要输入正数',max: '女职工数最大输入999999999'}}" />
		</div>
	<div class='clearLine'>&nbsp;</div> 
		<div class="grid_5 lable-right">
			<label class="form-lb1">本市城镇职工数：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="location.localTownNumber" id="localTownNumber"  maxlength="50" value="${location.localTownNumber}"  class="form-txt {number:true,min:0,max:999999999,messages:{number: '本市城镇职工数需要输入正数',	min: '本市城镇职工数要输入正数',max: '本市城镇职工数最大输入999999999'}}"/>
		</div>	
		<div class="grid_5 lable-right">
			<label class="form-lb1">本市农村职工数：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="location.localCountryNumber" id="localCountryNumber" maxlength="30"  value='${location.localCountryNumber }' class="form-txt {number:true,min:0,max:999999999,messages:{number: '本市农村职工数需要输入正数',	min: '本市农村职工数需要输入正数',max: '本市农村职工数最大输入999999999'}}"/>
		</div>	
	<div class='clearLine'>&nbsp;</div> 
		<div class="grid_5 lable-right">
			<label class="form-lb1">外省市职工数：</label>
		</div>	
		<div class="grid_7">
			<input type="text" name="location.otherTownNumber" id="otherTownNumber" maxlength="9" value="${location.otherTownNumber }"  class="form-txt {number:true,min:0,max:999999999,messages:{number: '外省市职工数需要输入正数',	min: '外省市职工数需要输入正数',max: '外省市职工数最大输入999999999'}}"></input>
		</div>
		<div class="grid_5 lable-right">
			<label class="form-lb1">其它职工数：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="location.otherNumber" id="otherNumber" maxlength="11" value="${location.otherNumber}" class="form-txt {fund:true,range:[1,999999.9999],messages:{fund:'请输入非负数 ，保留四位位小数点',range:'技防设施投入资金只能输入1到999999.9999之间的数'}}" />
		</div>
	<div class='clearLine'>&nbsp;</div> 	
		<div class="grid_5 lable-right">
			<label class="form-lb1">保证上岗人数：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="location.workNumber" id="workNumber"  maxlength="9" value="${location.workNumber}"  class="form-txt {number:true,min:0,max:999999999,messages:{number: '保证上岗人数需要输入正数',	min: '保证上岗人数需要输入正数',max: '保证上岗人数最大输入999999999'}}"/>
		</div>	
		<div class="grid_5 lable-right">
			<label class="form-lb1">标准工作时间人数：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="location.standardWorkNumber" id="standardWorkNumber" maxlength="11" value="${location.standardWorkNumber}" class="form-txt {number:true,min:0,max:999999999,messages:{number: '标准工作时间人数需要输入正数',	min: '标准工作时间人数需要输入正数',max: '标准工作时间人数最大输入999999999'}}" />
		</div>	
	<div class='clearLine'>&nbsp;</div> 
		<div class="grid_5 lable-right">
			<label class="form-lb1">不定时工作人数：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="location.unLimitWorkNumber" id="unLimitWorkNumber" maxlength="50" value="${location.unLimitWorkNumber}" class="form-txt {number:true,min:0,max:999999999,messages:{number: '不定时工作人数需要输入正数',	min: '不定时工作人数需要输入正数',max: '不定时工作人数最大输入999999999'}}"/>
		</div>
		<div class="grid_5 lable-right">
			<label class="form-lb1">综合工作时间人数：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="location.compositeWorkNumber" id="compositeWorkNumber" maxlength="50"  value="${location.compositeWorkNumber }" class="form-txt {number:true,min:0,max:999999999,messages:{number: '综合工作时间人数需要输入正数',	min: '综合工作时间人数需要输入正数',max: '综合工作时间人数最大输入999999999'}}" />
		</div>
	<div class='clearLine'>&nbsp;</div> 
		<div class="grid_5 lable-right">
			<label class="form-lb1">加班工资支付情况：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="location.overtimePayCondition" id="overtimePayCondition" maxlength="18" value="${location.overtimePayCondition }" class="form-txt" />
		</div>
		<div class="grid_5 lable-right">
			<label class="form-lb1">工资支付日期：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="location.payMoneyDate" id="payMoneyDate" maxlength="32" readonly value='<s:date name="location.payMoneyDate" format="yyyy-MM-dd" />' class="form-txt "/>
		</div>
	<div class='clearLine'>&nbsp;</div> 
		<div class="grid_5 lable-right">
			<label class="form-lb1">实际发放工资总额：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="location.payTotalMoney" id="payTotalMoney" maxlength="30" value="${location.payTotalMoney}"  class="form-txt " />
		</div>
		<div class="grid_5 lable-right">
			<label class="form-lb1">领取生活费人数：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="location.getLivingMoneyNum" id="getLivingMoneyNum" maxlength="30"  value="${location.getLivingMoneyNum}"   class="form-txt {number:true,min:0,max:999999999,messages:{number: '女职工数需要输入正数',	min: '女职工数需要输入正数',max: '女职工数最大输入999999999'}}" />
		</div>
	<div class='clearLine'>&nbsp;</div> 
		<div class="grid_5 lable-right">
			<label class="form-lb1">应签劳动合同人数：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="location.shouldEmployeeNum" id="shouldEmployeeNum" maxlength="30"  value="${location.shouldEmployeeNum }"   class="form-txt {number:true,min:0,max:999999999,messages:{number: '女职工数需要输入正数',	min: '女职工数需要输入正数',max: '女职工数最大输入999999999'}}" />
		</div>
		<div class="grid_5 lable-right">
			<label class="form-lb1">未签劳动合同人数：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="location.unEmployeeNum" id="unEmployeeNum" maxlength="30"  value="${location.unEmployeeNum}"   class="form-txt {number:true,min:0,max:999999999,messages:{number: '女职工数需要输入正数',	min: '女职工数需要输入正数',max: '女职工数最大输入999999999'}}" />
		</div>
	<div class='clearLine'>&nbsp;</div> 
		<div class="grid_5 lable-right">
			<label class="form-lb1">未签劳动合同原因：</label>
		</div>
		<div class="grid_19">
			<input type="text" name="location.unEmployeeReason" id="unEmployeeReason" maxlength="30"  value="${location.unEmployeeReason }"   class="form-txt " />
		</div>
	<div class='clearLine'>&nbsp;</div> 
		<div class="grid_5 lable-right">
		</div>
		<div class="grid_7">
			<input type="checkbox" name="location.isAcceptRewardCondition" id="isAcceptRewardCondition" value="true" <s:if test='true==location.isAcceptRewardCondition'>checked="checked"</s:if>></input>
			<label class="form-check-text">是否有接受奖励情况</label>
		</div>
		<div class="grid_5 lable-right">
		</div>
		<div class="grid_7">
			<input type="checkbox" name="location.isBreakLaw" id="isBreakLaw" value="true" <s:if test='true==location.isBreakLaw'>checked="checked"</s:if>></input>
			<label class="form-check-text">是否有违法情况</label>
		</div>
	<div class='clearLine'>&nbsp;</div> 
		<div class="grid_5 lable-right">
		</div>
		<div class="grid_7">
			<input type="checkbox" name="location.isAcceptPunish" id="isAcceptPunish" value="true" <s:if test='true==location.isAcceptPunish'>checked="checked"</s:if>></input>
			<label class="form-check-text">是否有接受处罚情况</label>
		</div>
		<div  class="grid_5 lable-right">
		</div>
		<div class="grid_7">
			<input type="checkbox" name="location.isUserdChild" id="isUserdChild" value="true" <s:if test='true==location.isUserdChild'>checked="checked"</s:if>></input>
			<label class="form-check-text">是否使用童工</label>
		</div>
	<div class='clearLine'>&nbsp;</div>  
		<div class="grid_5 lable-right">
		</div>
		<div class="grid_10">
			<input type="checkbox" name="location.isObservedOrder" id="isObservedOrder" value="true" <s:if test='true==location.isObservedOrder'>checked="checked"</s:if>></input>
			<label class="form-check-text">是否遵守女职工未成年保护规定</label>
		</div>
	<div class='clearLine'>&nbsp;</div>
	</form>
</div>
<script>
	$(document).ready(function(){

		$('#payMoneyDate').datePicker({
			yearRange : '1900:2060',
			dateFormat : 'yy-mm-dd',
			maxDate : '+1d'
		});
		
		if($("#organizationId").val()==null || $("#organizationId").val()==""){
			$("#organizationId").val($("#currentOrgId").val());
		}
		
		$("#maintainForm").formValidate({
			promptPosition: "bottomLeft",
			submitHandler: function(form){
				$(form).ajaxSubmit({
					success:function(data){
						if(!data.id){
	           	 			$.messageBox({ 
								evel: "error"
				 			});
	            			return;
						}
		                if("addBusinessData"==$("#mode").val()){
		                	 $.messageBox({message:"劳动用工单位新增成功！"});
		    				 $("#laborEmploymentUnitList").setRowData(data.id,data,"first");
		    				 doLocationAction("<s:property value='#parameters.dailogName[0]'/>",data.id);
		                }
		                if("editBusinessData"==$("#mode").val()){
		                	 $("#laborEmploymentUnitList").setRowData(data.id,data);
		                	 if(data.death){
	                			if($("#isLock").val()=='0') {
	                				$("#laborEmploymentUnitList").delRowData(data.id);
								}else {
									$("td[aria-describedby='druggyList_name']").css('color','red');
									$("td[aria-describedby='druggyList_idCardNo']").css('color','#778899');
									$("#"+data.id+"").css('color','#778899');
								}
							 }
		                	 $.messageBox({message:"劳动用工单位修改成功！"});
		                	 doLocationAction("<s:property value='#parameters.dailogName[0]'/>",data.id);	
		                }
					},
					error:function(XMLHttpRequest, textStatus, errorThrown){
	  	      			alert("提交数据时发生错误");
		   		    }
				});
			},
			rules:{
			},
			messages:{
			}
		});
	});
</script>