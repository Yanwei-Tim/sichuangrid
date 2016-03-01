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
			
			<label class="form-lb1">设施名称：</label>
		</div>
		<div class="grid_19">
			<input type="text" name="location.facilitiesName" id="facilitiesName" maxlength="50"  value='${location.facilitiesName }' class="form-txt " />
		</div>
	
	
		<div class="grid_5 lable-right">
			<label class="form-lb1">设施安装时间：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="location.installationTime" id="installationTime"  maxlength="50" readonly value='<s:date name="location.installationTime" format="yyyy-MM-dd" />'  class="form-txt "/>
		</div>	
		<div class="grid_5  lable-right">
		<label class="form-lb1">设施安装位置：</label>
		</div>	
		<div class="grid_7">
			<input type="text" name="location.installationPosition" id="installationPosition" maxlength="30" value="${location.installationPosition }" ></input>
		</div> 	
	<div class='clearLine'>&nbsp;</div> 
		<div class="grid_5 lable-right">
			<label class="form-lb1">运行投入金额：</label>
		</div>
		<div class="grid_5">
			<input type="text" name="location.investmentAmount" id="investmentAmount" maxlength="11"  value='${location.investmentAmount }' class="form-txt  {fund:true,range:[1,999999.9999],messages:{fund:'请输入非负数 ，保留四位位小数点',range:'运行投入金额只能输入1到999999.9999之间的数'}}"/>
		</div>	
		<div class="grid_2">
 		  <label class="form-lbl">万元</label>
		</div>
		
		<div  class="grid_5 lable-right">
			
		</div>
		<div class="grid_7">
			<input type="checkbox" name="location.canLose" id="canLose"   value="true" <s:if test='true==location.canLose'>checked="checked"</s:if> />
		<label class="form-check-text">能否输至公安图像中心</label>
		</div>
	<div class='clearLine'>&nbsp;</div>
		<div class="grid_5 lable-right">
			<label class="form-lb1">报警设施连接部门：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="location.connectedDepartment" id="connectedDepartment" maxlength="30"  value='${location.connectedDepartment}'class="form-txt " />
		</div>	   
		<div class="grid_5 lable-right">
			<label class="form-lb1">覆盖区域：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="location.coverageArea" id="coverageArea"  maxlength="15" value="${location.coverageArea}"  class="form-txt"/>
		</div>	
	<div class='clearLine'>&nbsp;</div> 
		
		<div class="grid_5 lable-right">
			<label class="form-lb1">设施保存天数：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="location.keepDays" id="keepDays" maxlength="9" value="${location.keepDays}" class="form-txt {number:true,min:0,max:999999999,messages:{number: '设施保存天数需要输入正数',	min: '设施保存天数需要输入正数',max: '设施保存天数最大输入999999999'}}"/>
		</div>	
	
		<div class="grid_5 lable-right">
			<label class="form-lb1">存储方式：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="location.storageWays" id="storageWays" maxlength="50" value="${location.storageWays}" class="form-txt "/>
		</div>
	<div class='clearLine'>&nbsp;</div> 
		<div class="grid_5 lable-right">
			<label class="form-lb1">其中含摄像机数量：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="location.camerasNumber" id="camerasNumber" maxlength="9"  value="${location.camerasNumber }" class="form-txt {number:true,min:0,max:999999999,messages:{number: '摄像机数量需要输入正数',	min: '摄像机数量需要输入正数',max: '摄像机数量最大输入999999999'}}" />
		</div>
		<div class="grid_5 lable-right">
			<label class="form-lb1">其中含入侵探测器数量：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="location.intrusionDetectorNumber" id="intrusionDetectorNumber" maxlength="9" value="${location.intrusionDetectorNumber }" class="form-txt {number:true,min:0,max:999999999,messages:{number: '入侵探测器数量需要输入正数',	min: '入侵探测器数量需要输入正数',max: '入侵探测器数量最大输入999999999'}}" />
		</div>
	
	
	<div class='clearLine'>&nbsp;</div> 
		<div class="grid_5 lable-right">
			<label class="form-lb1">配置描述：</label>
		</div>
		<div class="grid_19">
			<textarea rows="5" name="location.configurationDescribe" id="configurationDescribe" class="form-txt" maxlength="300" style="width: 99%" class="form-txt {maxlength:true,messages:{maxlength:'配置描述最多只能输入300字符'}}">${location.configurationDescribe}</textarea>
		</div>
	<div class='clearLine'>&nbsp;</div>
	</form>
</div>
<script>
	$(document).ready(function(){
		$('#installationTime').datePicker({
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
		                if("addPreventionFacilities"==$("#mode").val()){
		                	 $.messageBox({message:"实有单位新增成功！"});
		    				 $("#actualCompanyList").setRowData(data.id,data,"first");
		    				// $("#actualCompanyList").trigger("reloadGrid");
		    		         doLocationAction("<s:property value='#parameters.dailogName[0]'/>",data.id);
//			                 $("#actualCompanyList").setSelection(data.id);
		                }
		                if("editPreventionFacilities"==$("#mode").val()){
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