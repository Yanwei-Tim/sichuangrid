<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
<div class="container container_24">
	<form id="maintainForm" method="post"	action="${path}/baseinfo/laborEmploymentUnitManage/maintainBaseInfo.action" >
	<pop:token />
		<input type="hidden" name="mode" id="mode" value="${mode}" />
		<input id="organizationId"	type="hidden" name="organizationId" value="${location.organization.id }" />
		<input type="hidden" name="location.id" value="${location.id }" id="laborEmploymentUnitId"/>
		<input id="_imgUrl" name="location.imgUrl" type="hidden" value="${location.imgUrl}"/>
		<s:if test="#parameters.fromPopulationType[0]">
			<span style="font-weight: bold; margin-left:20px; ">实有单位</span>
			<div class='clearLine'>&nbsp;</div>
		</s:if>
		
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
			<label class="form-lb1">单位名称：</label>
		</div>
		<div class="grid_19">
			<input type="text" name="location.companyName" id="companyName" maxlength="50"  value='${location.companyName }' class="form-txt {required:true,exsistedCompanyName:true,messages:{required:'请输入单位名称',exsistedCompanyName:'单位名称已经被使用'}}" />
		</div>
	<div class='clearLine'>&nbsp;</div>  
	<input type="hidden" name="location.addressId" id="houseId" value="${location.addressId }"/>
	<div class="grid_5 lable-right">
		 			<label class="form-lb1">房屋编号：</label>
		 		</div>
		 		<div class="grid_19">
		 			<input type="text" id="houseCode"  name="houseCode"   maxlength="50" style="width: 99%"
		 				class="form-txt {isCodeValidate:true,exsistedHouseCode:true,messages:{isCodeValidate:'不能输入非法字符',exsistedHouseCode:'该房屋编号已经存在，请重新输入'}}" />
		 		</div>
		<div class="grid_5 lable-right">
			<em class="form-req">*</em><label class="form-lb1">单位地址：</label>
		</div>
		<div class="grid_19">
			<input type="text" name="location.companyAddress" id="companyAddress" maxlength="50"   value='${location.companyAddress }' class="form-txt {required:true,messages:{required:'请输入单位名称'}}"  />
		</div>
	<div class='clearLine'>&nbsp;</div>
	<div class="grid_5 lable-right">
			<em class="form-req">*</em><label class="form-lb1">营业执照号码：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="location.businessLicenseNo" id="businessLicenseNo" maxlength="50" value='${location.businessLicenseNo}' class="form-txt {required:true,messages:{required:'请输入单位名称'}}" />
		</div>
		<div class="grid_5 lable-right">
			<em class="form-req">*</em><label class="form-lb1">组织机构代码：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="location.orgCode" id="orgCode"  maxlength="50" value="${location.orgCode}"  class="form-txt {required:true,messages:{required:'请输入单位名称'}}"/>
		</div>	
	<div class='clearLine'>&nbsp;</div> 
		<div class="grid_5 lable-right">
			<label class="form-lb1">单位类别：</label>
		</div>
		<div class="grid_7">
		<select name="location.companyType.id" id="companyType" class="form-txt" >
		   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@ACTUALCOMPANY_COMPANYTYPE" defaultValue="${location.companyType.id}" />
			</select>
		</div>	
		<div class="grid_5  lable-right">
		</div>	
		<div class="grid_7">
			<input type="checkbox" name="location.isKey" id="isKey" value="true" <s:if test='true==location.isKey'>checked="checked"</s:if>></input>
			<label class="form-check-text">是否重点单位</label>
		</div>
	<div class='clearLine'>&nbsp;</div> 	
		<div class="grid_5 lable-right">
			<label class="form-lb1">法人代表：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="location.corporateRepresentative" id="corporateRepresentative" maxlength="50" value="${location.corporateRepresentative}" class="form-txt" />
		</div>
		<div  class="grid_5 lable-right">
			<label class="form-lb1">联系方式：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="location.postTelephone" id="postTelephone"  maxlength="15" value="${location.postTelephone}"  class="form-txt {mobile:true,messages:{mobile:'请输入一个正确的手机号码'}}" />
		</div>
	<div class='clearLine'>&nbsp;</div> 	
		<div class="grid_5 lable-right">
			<label class="form-lb1">职务：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="location.post" id="post" maxlength="50" value="${location.post}" class="form-txt" />
		</div>
		<div  class="grid_5 lable-right">
			<label class="form-lb1">身份证号码：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="location.idCardNo" id="idCardNo"  maxlength="18" value="${location.idCardNo}"  class="form-txt {idCard:true,messages:{idCard:'请输入一个合法的身份证号码'}}" />
		</div>
	<div class='clearLine'>&nbsp;</div> 
		<div class="grid_5 lable-right">
			<label class="form-lb1">单位电话：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="location.telephone" id="telephone" maxlength="20"  value='${location.telephone}'class="form-txt {telephone:true,messages:{telephone:'固定电话不合法，只能输数字和横杠(-)'}}" />
		</div>	   
		<div class="grid_5 lable-right">
			<label class="form-lb1">单位传真：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="location.facsimile" id="facsimile"  maxlength="50" value="${location.facsimile}"  class="form-txt"/>
		</div>	
	<div class='clearLine'>&nbsp;</div> 
		
		<div class="grid_5 lable-right">
			<label class="form-lb1">注册资本：</label>
		</div>
		<div class="grid_5">
			<input type="text" name="location.registeredCapital" id="registeredCapital" maxlength="11" value="${location.registeredCapital}" class="form-txt {fund:true,range:[1,999999.9999],messages:{fund:'请输入非负数 ，保留四位位小数点',range:'注册资本只能输入1到999999.9999之间的数'}}"/>
		</div>	
		<div class="grid_2">
 		  <label class="form-lbl">万元</label>
		</div>
		<div class="grid_5 lable-right">
			<label class="form-lb1">经济性质：</label>
		</div>
		<div class="grid_7">
		<select name="location.economicNature.id" id="economicNature" class="form-txt" >
		   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@ACTUALCOMPANY_ECONOMICNATURE" defaultValue="${location.economicNature.id}" />
			</select>
		</div>
	<div class='clearLine'>&nbsp;</div> 
		<div class="grid_5 lable-right">
			<label class="form-lb1">有效期至：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="location.expiryDate" id="expiryDate" maxlength="32" readonly value='<s:date name="location.expiryDate" format="yyyy-MM-dd" />'class="form-txt " />
		</div>
		<div class="grid_5 lable-right">
			<label class="form-lb1">注册日期：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="location.registrationDate" id="registrationDate" maxlength="32" readonly value='<s:date name="location.registrationDate" format="yyyy-MM-dd" />'class="form-txt " />
		</div>
	<div class='clearLine'>&nbsp;</div> 	
		<div class="grid_5 lable-right">
			<label class="form-lb1">经营范围：</label>
		</div>
		<div class="grid_19">
			<input type="text" name="location.businessScope" id="businessScope" maxlength="50" value="${location.businessScope}"   class="form-txt "/>
		</div>
	<div class='clearLine'>&nbsp;</div> 
		<div class="grid_5 lable-right">
			<label class="form-lb1">注册地址：</label>
		</div>
		<div class="grid_19">
			<input type="text" name="location.registrationAddress" id="registrationAddress" maxlength="50" value="${location.registrationAddress}"  class="form-txt " />
		</div>
	<div class='clearLine'>&nbsp;</div>
		<div class="grid_5 lable-right">
			<label class="form-lb1">劳资负责人：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="location.principal" id="principal" maxlength="20"  value="${location.principal}"   class="form-txt" />
		</div>
		<div class="grid_5 lable-right">
			<label class="form-lb1">联系方式：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="location.principalTelephone" id="principalTelephone" maxlength="30"  value="${location.principalTelephone }"   class="form-txt {mobile:true,messages:{mobile:'请输入一个正确的手机号码'}}" />
		</div>
	<div class='clearLine'>&nbsp;</div>
		<div class="grid_5 lable-right">
			<label class="form-lb1">社会保险登记号：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="location.insureNumber" id="insureNumber" maxlength="9"  value="${location.insureNumber}"   class="form-txt" />
		</div>
		<div class="grid_5 lable-right">
			<label class="form-lb1">主管部门：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="location.competentDepartment" id="competentDepartment" maxlength="30"  value="${location.competentDepartment }"   class="form-txt " />
		</div>
	<div class='clearLine'>&nbsp;</div> 	
		<div class="grid_5 lable-right">
			<label class="form-lb1">管理级别：</label>
		</div>
		<div class="grid_7">
		
		<select name="location.supervisoryLevel.id" id="supervisoryLevel" class="form-txt" >
		   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@ACTUALCOMPANY_SUPERVISORYLEVEL" defaultValue="${location.supervisoryLevel.id}" />
			</select>
		</div>
		<div class="grid_5 lable-right">
			<label class="form-lb1">管理部门：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="location.supervisoryDepartment" id="supervisoryDepartment" maxlength="30"  value="${location.supervisoryDepartment }"   class="form-txt " />
		</div>
	<div class='clearLine'>&nbsp;</div> 
		<div class="grid_5 lable-right">
			<label class="form-lb1">消防等级：</label>
		</div>
		<div class="grid_7">
		<select name="location.fireFightingLevel.id" id="fireFightingLevel" class="form-txt" >
		   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@ACTUALCOMPANY_FIREFIGHTINGLEVEL" defaultValue="${location.fireFightingLevel.id}" />
			</select>
		</div>
		<div class="grid_5 lable-right">
			<label class="form-lb1">治安负责人：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="location.securityChief" id="securityChief" maxlength="50"  value="${location.securityChief }"   class="form-txt " />
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
		$('#registrationDate').datePicker({
			yearRange: '1900:2030',
			dateFormat: 'yy-mm-dd',
		    maxDate:'+0d'});
			
		
		
		$('#expiryDate').datePicker({
			yearRange : '1900:2060',
			dateFormat : 'yy-mm-dd',
			minDate : '+1d'
		});
		

		
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

		// 根据房屋编号自动获取实有房屋信息
		function searchHoseInfoByCode(){
			$.ajax({
				async: false ,
				url:"${path}/baseinfo/actualHouseManage/getHouseInfoByHouseCode.action",
			   	data:{
					"searchHouseInfoVo.houseCode":$('#houseCode').val()
		        },
				success:function(data){
					if(null != data) {
						procCurrentAddressType(data);
					} else {
						$("#companyAddress").val("");
					}       
				}
			});
		}
		
		function procCurrentAddressType(data) {
			$("#houseId").val(data.id);
			if(data.addressType.id==741) {
				$("#companyAddress").val(data.community+"小区"+data.block+"幢"+data.unit+"单元"+data.room+"室");
			} else {
				$("#companyAddress").val(data.address);
			}
		}
		$("#houseCode").change(searchHoseInfoByCode);
		
		$("#houseCode").houseAutoComplete({
			searchLevel:"houseCode",
			select:function(event,ui){
				renderHouseInfoForHouseCode(ui.item);
			}});

		function renderHouseInfoForHouseCode(house){
			$("#houseId").val(house.houseId);
			procCurrentAddressType(house);
			$("#houseCode").val(house.houseCode);
		}
		
		jQuery.validator.addMethod("exsistedHouseCode", function(value, element){
			var flag =true;
			if(value==null||value==undefined||value==""){return true}
			$.ajax({
				async: false ,
				url:"${path}/baseinfo/rentalHouseManage/hasDuplicateHouseInfo.action",
			   	data:{
				"houseInfo.houseCode":$('#houseCode').val(),
				"orgId":$('#currentOrgId').val(),
				"mode":$('#mode').val(),
				"houseInfo.houseType.id":$('#houseTypeId').val(),
				"houseInfo.id":$("#mode").val() == "add"?"-1":$('#houseInfoId').val()
		        },
				success:function(responseData){
					flag = !eval(responseData);
				}
			});
			return flag;
		});
		jQuery.validator.addMethod("exsistedCompanyName", function(value, element){
			var flag =true;
			if(value==null||value==undefined||value==""){return true}
			$.ajax({
				async: false ,
				url:'/baseinfo/laborEmploymentUnitManage/hasDuplicatePopulation.action',
			   	data:{
			   		"location.id":$('#laborEmploymentUnitId').val(),
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
		                	 $.messageBox({message:"实有单位新增成功！"});
		    				 $("#laborEmploymentUnitList").addRowData(data.id,data,"first");
		    				// $("#laborEmploymentUnitList").trigger("reloadGrid");
		    		         doLocationAction("<s:property value='#parameters.dailogName[0]'/>",data.id);
		    		         checkExport();
//			                 $("#laborEmploymentUnitList").setSelection(data.id);
		                }
		                if("edit"==$("#mode").val()){
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