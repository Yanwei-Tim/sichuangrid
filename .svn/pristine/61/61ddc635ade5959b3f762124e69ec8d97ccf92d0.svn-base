<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
<div class="container container_24">
	<form id="maintainForm" method="post"	action="${path}/baseinfo/emergencyShelterManage/maintainBaseInfo.action" >
		<input type="hidden" name="mode" id="mode" value="${mode}" />
		<input id="organizationId"	type="hidden" name="organizationId" value="${location.organization.id }" />
		<input type="hidden" name="location.id" value="${location.id }" id="emergencyShelterId"/>
		<input id="_imgUrl" name="location.imgUrl" type="hidden" value="${location.imgUrl}"/>
		
		<div class="grid_5 lable-right">
		   	<em class="form-req">*</em>
			<label class="form-lb1">所属网格：</label>
		</div>
		<div class="grid_19">
			<input type="text"  id="orgName"  name="location.organization.orgName"  readonly value="${location.organization.orgName}" class="form-txt" />
		</div>
	<div class='clearLine'>&nbsp;</div>  
		<div class="grid_5 lable-right">
			<em class="form-req">*</em>
			<label class="form-lb1">场所名称：</label>
		</div>
		<div class="grid_19">
			<input type="text" name="location.name" id="companyName" maxlength="20"  value='${location.name }' class="form-txt {required:true,exsistedCompanyName:true,messages:{required:'请输入场所名称',exsistedCompanyName:'场所名称已经被使用'}}" />
		</div>
	<div class='clearLine'>&nbsp;</div>  
		<div class="grid_5 lable-right">
			<em class="form-req">*</em><label class="form-lb1">场所位置：</label>
		</div>
		<div class="grid_19">
			<input type="text" name="location.address" id="companyAddress" maxlength="50"   value='${location.address }' class="form-txt {required:true,messages:{required:'请输入单位名称'}}"  />
		</div>
	<div class='clearLine'>&nbsp;</div>
		<div class="grid_5 lable-right">
			<label class="form-lb1">经纬度：</label>
		</div>
		<div class="grid_19">
			<input type="text" name="location.longAititude" id="longAititude" maxlength="30" value='${location.longAititude}' class="form-txt" />
		</div>
	<div class='clearLine'>&nbsp;</div>
		<div class="grid_5 lable-right">
			<label class="form-lb1">场地类型：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="location.siteType" id="siteType"  maxlength="20" value="${location.siteType}"  class="form-txt"/>
		</div>	
		<div class="grid_5 lable-right">
			<label class="form-lb1">面积：</label>
		</div>
		<div class="grid_6">
			<input type="text" name="location.area" id="area" maxlength="10" value="${location.area}" class="form-txt {fund:true,range:[1,9999999.99],messages:{fund:'请输入非负数 ，保留2位小数点',range:'面积只能输入1到9999999.99之间的数'}}" />
		</div>
		<div class="grid_1">㎡</div>
	<div class='clearLine'>&nbsp;</div>
		<div  class="grid_5 lable-right">
			<label class="form-lb1">可容纳人数：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="location.fullPersonNum" id="fullPersonNum"  maxlength="10" value="${location.fullPersonNum}"  class="form-txt {number:true,min:0,max:999999999,messages:{number: '可容纳人数需要输入正数',	min: '可容纳人数需要输入正数',max: '可容纳人数最大输入999999999'}}" />
		</div>
		<div class="grid_5 lable-right">
			<label class="form-lb1">周边社区/村数量：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="location.aroundVillageNum" id="aroundVillageNum" maxlength="10" value="${location.aroundVillageNum}" class="form-txt {number:true,min:0,max:999999999,messages:{number: '周边社区/村数量需要输入正数',	min: '周边社区/村数量需要输入正数',max: '周边社区/村数量最大输入999999999'}}" />
		</div>
	<div class='clearLine'>&nbsp;</div>
		<div  class="grid_5 lable-right">
			<label class="form-lb1">周边人口：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="location.aroundPersonNum" id="aroundPersonNum"  maxlength="10" value="${location.aroundPersonNum}"  class="form-txt {number:true,min:0,max:999999999,messages:{number: '周边人口数需要输入正数',	min: '周边人口数需要输入正数',max: '周边人口数最大输入999999999'}}" />
		</div>
		<div class="grid_5 lable-right">
			<label class="form-lb1">标识：</label>
		</div>
		<div class="grid_5">
			<input type="text" name="location.identificationNum" id="identificationNum" maxlength="10" value="${location.identificationNum}" class="form-txt {number:true,min:0,max:999999999,messages:{number: '标识数需要输入正数',	min: '标识数需要输入正数',max: '标识数最大输入999999999'}}"/>
		</div>	
	<div class='clearLine'>&nbsp;</div> 
		<div class="grid_5 lable-right">
			<label class="form-lb1">图形符号：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="location.imageSymbolNum" id="imageSymbolNum" maxlength="10" value="${location.imageSymbolNum}"   class="form-txt {number:true,min:0,max:999999999,messages:{number: '图形符号需要输入正数',	min: '图形符号数需要输入正数',max: '图形符号数最大输入999999999'}}"/>
		</div>
		<div class="grid_5 lable-right">
			<label class="form-lb1">指示：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="location.pointNum" id="pointNum" maxlength="10" value="${location.pointNum}"  class="form-txt {number:true,min:0,max:999999999,messages:{number: '指示牌数需要输入正数',	min: '指示牌数需要输入正数',max: '指示牌数最大输入999999999'}}" />
		</div>
	<div class='clearLine'>&nbsp;</div> 
		<div class="grid_5 lable-right">
			<label class="form-lb1">周边道路情况：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="location.aroundRoadCondition" id="aroundRoadCondition" maxlength="50"  value='${location.aroundRoadCondition}'class="form-txt" />
		</div>	   
		<div class="grid_5 lable-right">
			<label class="form-lb1">功能完善：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="location.functionComplete" id="functionComplete"  maxlength="50" value="${location.functionComplete}"  class="form-txt"/>
		</div>	
	<div class='clearLine'>&nbsp;</div>
		<div class="grid_5 lable-right">
			<label class="form-lb1">备注：</label>
		</div>
		<div class="grid_19">
			<textarea rows="5" name="location.remark" id="remark" class="form-txt" maxlength="300" style="width: 99%" class="form-txt {maxlength:true,messages:{maxlength:'备注最多只能输入300字符'}}">${location.remark }</textarea>
		</div>
	<div class='clearLine'>&nbsp;</div>
	</form>
</div>
<script>
	$(document).ready(function(){
		if(null!=$("#_imgUrl").val() && $("#_imgUrl").val()!=""){
			$("#headerImg").attr("src",$("#_imgUrl").val()+"?r="+Math.random());
			$(".shadow").show();
		}
		if(""!=$("#_imgUrl").val() && typeof($("#_imgUrl").val())!="undefined"){
			$("#img").attr("src","${path }/"+$("#_imgUrl").val());
		};
		
		<s:if test='"add".equals(mode)'>
			$.ajax({
				async: false,
				url: "${path }/sysadmin/orgManage/getOrgRelativePath.action",
				data:{
					"organization.id" : $("#currentOrgId").val()
				},
				success:function(responseData){
					$("#orgName").val(responseData);
				}
			});
		</s:if>
		
		if($("#organizationId").val()==null || $("#organizationId").val()==""){
			$("#organizationId").val($("#currentOrgId").val());
		}

		jQuery.validator.addMethod("exsistedCompanyName", function(value, element){
			var flag =true;
			if(value==null||value==undefined||value==""){return true}
			$.ajax({
				async: false ,
				url:'/baseinfo/emergencyShelterManage/hasDuplicatePopulation.action',
			   	data:{
			   		"location.id":$('#emergencyShelterId').val(),
					"location.companyName":$('#companyName').val(),
					"organizationId":$('#organizationId').val(),
		        },
				success:function(responseData){
					flag = !eval(responseData);
				}
			});
			return flag;
		});

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
		                if("add"==$("#mode").val()){
		                	 $.messageBox({message:"应急避难场所新增成功！"});
		    				 $("#emergencyShelterList").addRowData(data.id,data,"first");
		    		         doAction("<s:property value='#parameters.dailogName[0]'/>",data.id);
		    		         checkExport();
		                }
		                if("edit"==$("#mode").val()){
		                	 $("#emergencyShelterList").setRowData(data.id,data);
		                	 if(data.death){
	                			if($("#isLock").val()=='0') {
	                				$("#emergencyShelterList").delRowData(data.id);
								}else {
									$("td[aria-describedby='druggyList_name']").css('color','red');
									$("td[aria-describedby='druggyList_idCardNo']").css('color','#778899');
									$("#"+data.id+"").css('color','#778899');
								}
							}
		                	 $.messageBox({message:"应急避难场所修改成功！"});
		    				 doAction("<s:property value='#parameters.dailogName[0]'/>",data.id);	
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