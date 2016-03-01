<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
<div class="container container_24">
	<form id="maintainForm" method="post"	action="${path}/baseinfo/actualCompanyManage/maintainBusinessInfo.action" >
		<input type="hidden" name="mode" id="mode" value="${mode}" />
		<input id="organizationId"	type="hidden" name="organizationId" value="${location.organization.id }" />
		<input type="hidden" name="location.id" value="${location.id }" id="actualCompanyId"/>
		
		<div class="grid_5 lable-right">
		   	<em class="form-req">*</em>
			<label class="form-lb1">所属网格：</label>
		</div>
		<div class="grid_19">
			<input type="text"  id="orgName"  name="location.organization.orgName"  readonly value="${location.organization.orgName}" class="form-txt" />
		</div>
	<div class='clearLine'>&nbsp;</div>  
		<div class="grid_5 lable-right">
			
			<label class="form-lb1">名称：</label>
		</div>
		<div class="grid_19">
			<input type="text" name="location.keyName" id="keyName" maxlength="50"  value='${location.keyName }' class="form-txt " />
		</div>
	
	<div class="grid_5 lable-right">
			<label class="form-lb1">武警人数：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="location.policeNumber" id="policeNumber" maxlength="9" value='${location.policeNumber}' class="form-txt {number:true,min:0,max:999999999,messages:{number: '武警人数需要输入正数',	min: '武警人数需要输入正数',max: '武警人数最大输入999999999'}}" />
		</div>
		<div class="grid_5 lable-right">
			<label class="form-lb1">方位：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="location.position" id="position"  maxlength="50" value="${location.position}"  class="form-txt "/>
		</div>	
	<div class='clearLine'>&nbsp;</div> 
		<div class="grid_5 lable-right">
			<label class="form-lb1">重要设施：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="location.importantFacilities" id="importantFacilities" maxlength="30"  value='${location.importantFacilities }' class="form-txt "/>
		</div>	
		<div class="grid_5  lable-right">
		<label class="form-lb1">护卫队人数：</label>
		</div>	
		<div class="grid_7">
			<input type="text" name="location.convoyNumber" id="convoyNumber" maxlength="9" value="${location.convoyNumber }"  class="form_txt {number:true,min:0,max:999999999,messages:{number: '护卫队人数需要输入正数',	min: '护卫队人数需要输入正数',max: '护卫队人数最大输入999999999'}}"></input>
		</div>
	<div class='clearLine'>&nbsp;</div> 	
		<div class="grid_5 lable-right">
			<label class="form-lb1">经济价值：</label>
		</div>
		<div class="grid_5">
			<input type="text" name="location.economicValue" id="economicValue" maxlength="11" value="${location.economicValue}" class="form-txt {fund:true,range:[1,999999.9999],messages:{fund:'请输入非负数 ，保留四位位小数点',range:'技防设施投入资金只能输入1到999999.9999之间的数'}}" />
		</div>
		<div class="grid_2">
 		  <label class="form-lbl">万元</label>
		</div>
		<div  class="grid_5 lable-right">
			<label class="form-lb1">保安人数：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="location.securityNumber" id="securityNumber"  maxlength="9" value="${location.securityNumber}"  class="form-txt {number:true,min:0,max:999999999,messages:{number: '保安人数需要输入正数',	min: '保安人数需要输入正数',max: '保安人数最大输入999999999'}}" />
		</div>
	<div class='clearLine'>&nbsp;</div> 
		<div class="grid_5 lable-right">
			<label class="form-lb1">工作人员数：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="location.staffNumber" id="staffNumber" maxlength="9"  value='${location.staffNumber}'class="form-txt {number:true,min:0,max:999999999,messages:{number: '工作人员数需要输入正数',	min: '工作人员数需要输入正数',max: '工作人员数最大输入999999999'}}" />
		</div>	   
		<div class="grid_5 lable-right">
			<label class="form-lb1">经警人数：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="location.classicsAlarmNumber" id="classicsAlarmNumber"  maxlength="9"  value="${location.classicsAlarmNumber}"  class="form-txt {number:true,min:0,max:999999999,messages:{number: '经警人数需要输入正数',	min: '经警人数需要输入正数',max: '经警人数最大输入999999999'}}"/>
		</div>	
	<div class='clearLine'>&nbsp;</div> 
		
		<div class="grid_5 lable-right">
			<label class="form-lb1">电话：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="location.keyPhone" id="keyPhone" maxlength="20" value="${location.keyPhone}" title="请输入由数字和-组成的联系电话,例如：0577-88888888"  class='form-txt {telephone:true,messages:{telephone:$.format("电话不合法，只能输数字和横杠(-)")}}'/>
		</div>	
	
		<div class="grid_5 lable-right">
			<label class="form-lb1">具体位置：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="location.location" id="location" maxlength="50" value="${location.location}" class="form-txt "/>
		</div>
	<div class='clearLine'>&nbsp;</div> 
		<div class="grid_5 lable-right">
			<label class="form-lb1">负责人：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="location.chief" id="chief" maxlength="50"  value="${location.chief }" class="form-txt " />
		</div>
		<div class="grid_5 lable-right">
			<label class="form-lb1">负责人身份证号码：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="location.chiefIdNumber" id="chiefIdNumber" maxlength="18" value="${location.chiefIdNumber }" class="form-txt {idCard:true,messages:{idCard:'请输入一个合法的身份证号码'}}" />
		</div>
	<div class='clearLine'>&nbsp;</div> 	
		<div class="grid_5 lable-right">
			<label class="form-lb1">有关安全防范标准：</label>
		</div>
		<div class="grid_19">
			<input type="text" name="location.relevantSafety" id="relevantSafety" maxlength="50" value="${location.relevantSafety}"   class="form-txt "/>
		</div>
	<div class='clearLine'>&nbsp;</div> 
		<div class="grid_5 lable-right">
			<label class="form-lb1">技防措施：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="location.safetyMeasures" id="safetyMeasures" maxlength="30" value="${location.safetyMeasures}"  class="form-txt " />
		</div>
		<div class="grid_5 lable-right">
			<label class="form-lb1">人防措施：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="location.civilMeasures" id="civilMeasures" maxlength="30"  value="${location.civilMeasures}"   class="form-txt " />
		</div>
			<div class='clearLine'>&nbsp;</div> 
		<div class="grid_5 lable-right">
			<label class="form-lb1">物防措施：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="location.thingMeasures" id="thingMeasures" maxlength="30"  value="${location.thingMeasures }"   class="form-txt " />
		</div>
		<div class="grid_5 lable-right">
			<label class="form-lb1">犬防措施：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="location.dogMeasures" id="dogMeasures" maxlength="30"  value="${location.dogMeasures}"   class="form-txt " />
		</div>
		<div class="grid_5 lable-right">
			<label class="form-lb1">判定依据：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="location.judgementBasis" id="judgementBasis" maxlength="30"  value="${location.judgementBasis }"   class="form-txt " />
		</div>
	
		<div class="grid_5 lable-right">
			<label class="form-lb1">技防设施投入资金：</label>
		</div>
		<div class="grid_5">
			<input type="text" name="location.safetyMeasuresFunds" id="safetyMeasuresFunds" maxlength="11"  value="${location.safetyMeasuresFunds }"   class="form-txt {fund:true,range:[1,999999.9999],messages:{fund:'请输入非负数 ，保留四位位小数点',range:'技防设施投入资金只能输入1到999999.9999之间的数'}}" />
		</div>
		<div class="grid_2">
 		  <label class="form-lbl">万元</label>
		</div>
	<div class='clearLine'>&nbsp;</div> 
		<div class="grid_5 lable-right">
			<label class="form-lb1">保卫制度建立情况：</label>
		</div>
		<div class="grid_19">
			<textarea rows="5" name="location.defendEstablishment" id="defendEstablishment" class="form-txt" maxlength="300" style="width: 99%" class="form-txt {maxlength:true,messages:{maxlength:'保卫制度建立情况最多只能输入300字符'}}">${location.defendEstablishment}</textarea>
		</div>
	<div class='clearLine'>&nbsp;</div>
	</form>
</div>
<script>
	$(document).ready(function(){
		$('#expiryDate').datePicker({
			yearRange: '1900:2030',
			dateFormat: 'yy-mm-dd'
			});
		$('#registrationDate').datePicker({
			yearRange: '1900:2030',
			dateFormat: 'yy-mm-dd',
		    maxDate:'+0d'});
			
		
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
		                if("addKeyCrucialPosition"==$("#mode").val()){
		                	 $.messageBox({message:"实有单位新增成功！"});
		    				 $("#actualCompanyList").setRowData(data.id,data,"first");
		    				// $("#actualCompanyList").trigger("reloadGrid");
		    		         doLocationAction("<s:property value='#parameters.dailogName[0]'/>",data.id);
//			                 $("#actualCompanyList").setSelection(data.id);
		                }
		                if("editKeyCrucialPosition"==$("#mode").val()){
		                	 $("#actualCompanyList").setRowData(data.id,data);
		                	 if(data.death){
	                			if($("#isLock").val()=='0') {
	                				$("#actualCompanyList").delRowData(data.id);
								}else {
									$("td[aria-describedby='druggyList_name']").css('color','red');
									$("td[aria-describedby='druggyList_idCardNo']").css('color','#778899');
									$("#"+data.id+"").css('color','#778899');
								}
							}
		                	 $.messageBox({message:"实有单位修改成功！"});
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