<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>

<div class="container container_24">
	<form id="maintainForm" method="post"	action="${path}/baseinfo/actualCompanyManage/maintainBaseInfo.action" >
		<input type="hidden" name="mode" id="mode" value="${mode}" />
		<s:if test='"add".equals(mode)'>
			<pop:token/>
		</s:if>
		<input id="organizationId"	type="hidden" name="organizationId" value="${location.organization.id }" />
		<input id="houseInfoBuildingId" type="hidden" name="location.gisInfo.buildingId" value="${location.gisInfo.buildingId}" />
		<input id="houseInfoCenterX" type="hidden" name="location.gisInfo.centerX" value="${location.gisInfo.centerX}" />
		<input id="houseInfoCenterY" type="hidden" name="location.gisInfo.centerY" value="${location.gisInfo.centerY}" />
		<input type="hidden" name="location.id" value="${location.id }" id="actualCompanyId"/>
		<input id="_imgUrl" name="location.imgUrl" type="hidden" value="${location.imgUrl}"/>
		<s:if test="#parameters.fromPopulationType[0]">
			<span style="font-weight: bold; margin-left:20px; ">实有单位</span>
			<div class='clearLine'>&nbsp;</div>
		</s:if>

		<div class="grid_4 lable-right">
		   	<em class="form-req">*</em>
			<label class="form-lb1">所属网格：</label>
		</div>
		<div class="grid_20">
			<input type="text"  id="orgName"  name="location.organization.orgName"  style="width: 99%"  readonly value="${location.organization.orgName}" class="form-txt" />
		</div>
	<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em>
			<label class="form-lb1">单位名称：</label>
		</div>
		<div class="grid_20">
			<input type="text" name="location.companyName" id="companyName" maxlength="50" style="width: 99%" value='${location.companyName }' class="form-txt {required:true,exculdeParticalChar:true,exsistedCompanyName:true,messages:{required:'请输入单位名称',exculdeParticalChar:'不能输入非法字符',exsistedCompanyName:'单位名称已经被使用'}}" />
		</div>
	<div class='clearLine'>&nbsp;</div>

		<div class="grid_4 lable-right">
			<em class="form-req">*</em><label class="form-lb1">单位地址：</label>
		</div>
		<div class="grid_20">
			<input type="text" name="location.companyAddress" id="companyAddress" maxlength="50" style="width: 99%" value='${location.companyAddress }' class="form-txt {required:true,messages:{required:'请输入单位地址'}}"  />
		</div>
	<div class='clearLine'>&nbsp;</div>
	<div class="grid_4 lable-right">
			<em class="form-req">*</em><label class="form-lb1">营业执照号码：</label>
		</div>
		<div class="grid_8">
			<input type="text" name="location.businessLicenseNo" id="businessLicenseNo" maxlength="50" value='${location.businessLicenseNo}' class="form-txt {required:true,exculdeParticalChar:true,messages:{required:'请输入营业执照号码',exculdeParticalChar:'不能输入非法字符'}}" />
		</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em><label class="form-lb1">组织机构代码：</label>
		</div>
		<div class="grid_8">
			<input type="text" name="location.orgCode" id="orgCode"  maxlength="50" value="${location.orgCode}"  class="form-txt {required:true,messages:{required:'请输入组织机构代码'}}"/>
		</div>
	<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<label class="form-lb1">单位类别：</label>
		</div>
		<div class="grid_8">
		<select name="location.companyType.id" id="companyType" class="form-txt" >
		   	<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@ACTUALCOMPANY_COMPANYTYPE" defaultValue="${location.companyType.id}" />
		</select>
		</div>
		<div class="grid_4 lable-right">
		</div>
		<div class="grid_8">
			<input type="checkbox" name="location.isKey" id="isKey" value="true" <s:if test='true==location.isKey'>checked="checked"</s:if>></input>
			<label class="form-check-text">是否重点单位</label>
		</div>
	<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<label class="form-lb1">法人代表：</label>
		</div>
		<div class="grid_8">
			<input type="text" name="location.corporateRepresentative" id="corporateRepresentative" maxlength="50" value="${location.corporateRepresentative}" class='form-txt {exculdeParticalChar:true,minlength:2,maxlength:20,messages:{exculdeParticalChar:"不能输入非法字符",minlength:$.format("法人代表至少需要输入{0}个字符"),maxlength:$.format("法人代表最多需要输入{0}个字符")}}' />
		</div>
		<div  class="grid_4 lable-right">
			<label class="form-lb1">身份证号码：</label>
		</div>
		<div class="grid_8">
			<input type="text" name="location.idCardNo" id="idCardNo"  maxlength="18" value="${location.idCardNo}"  class="form-txt {idCard:true,messages:{idCard:'请输入一个合法的身份证号码'}}" />
		</div>
	<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<label class="form-lb1">单位电话：</label>
		</div>
		<div class="grid_8">
			<input type="text" name="location.telephone" id="telephone" maxlength="20"  value='${location.telephone}'class="form-txt {telephone:true,messages:{telephone:'固定电话不合法，只能输数字和横杠(-)'}}" />
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lb1">单位传真：</label>
		</div>
		<div class="grid_8">
			<input type="text" name="location.facsimile" id="facsimile"  maxlength="50" value="${location.facsimile}"  class="form-txt {telephone:true,messages:{telephone:'单位传真不合法，只能输数字和横杠(-)'}}"/>
		</div>
	<div class='clearLine'>&nbsp;</div>

		<div class="grid_4 lable-right">
			<label class="form-lb1">注册资本：</label>
		</div>
		<div class="grid_6">
			<input type="text" name="location.registeredCapital" id="registeredCapital" maxlength="11" value="${location.registeredCapital}" class="form-txt {fund:true,range:[1,999999.9999],messages:{fund:'请输入非负数 ，保留四位位小数点',range:'注册资本只能输入1到999999.9999之间的数'}}"/>
		</div>
		<div class="grid_2 lable-right">
 		  <label class="form-lbl">万元</label>
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lb1">经济性质：</label>
		</div>
		<div class="grid_8">
		<select name="location.economicNature.id" id="economicNature" class="form-txt" >
		   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@ACTUALCOMPANY_ECONOMICNATURE" defaultValue="${location.economicNature.id}" />
			</select>
		</div>
	<div class='clearLine'>&nbsp;</div>
	
		<div class="grid_4 lable-right">
			<label class="form-lb1">注册日期：</label>
		</div>
		<div class="grid_8">
			<input type="text" name="location.registrationDate" id="registrationDate" maxlength="32" readonly value='<s:date name="location.registrationDate" format="yyyy-MM-dd" />'class="form-txt " />
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lb1">有效期至：</label>
		</div>
		<div class="grid_8">
			<input type="text" name="location.expiryDate" id="expiryDate" maxlength="32" readonly value='<s:date name="location.expiryDate" format="yyyy-MM-dd" />'class="form-txt " />
		</div>
		
	<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<label class="form-lb1">经营范围：</label>
		</div>
		<div class="grid_20">
			<input type="text" name="location.businessScope" id="businessScope" maxlength="50" style="width: 99%" value="${location.businessScope}"   class="form-txt "/>
		</div>
	<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<label class="form-lb1">注册地址：</label>
		</div>
		<div class="grid_20">
			<input type="text" name="location.registrationAddress" id="registrationAddress" style="width: 99%" maxlength="50" value="${location.registrationAddress}"  class="form-txt " />
		</div>
	<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<label class="form-lb1">从业人数：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="location.employeesNum" id="employeesNum" maxlength="9"  value="${location.employeesNum}"   class="form-txt {digits:true,messages:{digits:'请输入不小于0的阿拉伯数字整数'} }" />
		</div>
		<div class="grid_1">
 		  <label class="form-lbl">&nbsp;人</label>
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lb1">主管部门：</label>
		</div>
		<div class="grid_8">
			<input type="text" name="location.competentDepartment" id="competentDepartment" maxlength="30"  value="${location.competentDepartment }"   class="form-txt " />
		</div>
	<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<label class="form-lb1">管理级别：</label>
		</div>
		<div class="grid_8">

		<select name="location.supervisoryLevel.id" id="supervisoryLevel" class="form-txt" >
		   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@ACTUALCOMPANY_SUPERVISORYLEVEL" defaultValue="${location.supervisoryLevel.id}" />
			</select>
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lb1">管理部门：</label>
		</div>
		<div class="grid_8">
			<input type="text" name="location.supervisoryDepartment" id="supervisoryDepartment" maxlength="30"  value="${location.supervisoryDepartment }"   class="form-txt " />
		</div>
	<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<label class="form-lb1">消防等级：</label>
		</div>
		<div class="grid_8">
		<select name="location.fireFightingLevel.id" id="fireFightingLevel" class="form-txt" >
		   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@ACTUALCOMPANY_FIREFIGHTINGLEVEL" defaultValue="${location.fireFightingLevel.id}" />
			</select>
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lb1">治安负责人：</label>
		</div>
		<div class="grid_8">
			<input type="text" name="location.securityChief" id="securityChief" maxlength="50"  value="${location.securityChief }"   class="form-txt {exculdeParticalChar:true,messages:{exculdeParticalChar:'不能输入非法字符'}}" />
		</div>
	<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<label class="form-lb1">备注：</label>
		</div>
		<div class="grid_20">
			<textarea rows="3" name="location.remark" id="remark" class="form-txt" maxlength="300" style="width: 99%" class="form-txt {maxlength:true,messages:{maxlength:'备注最多只能输入300字符'}}">${location.remark }</textarea>
		</div>
	<div class='clearLine'>&nbsp;</div>
	</form>
</div>
<script>
//是否添加标签页
function procActualCompanyTab(id){
	
	var dialogName = "<s:property value='#parameters.dailogName[0]'/>";
	$("#"+dialogName).removeTabFromDialog($("#"+dialogName).getTabIndexByTitle("从业人员"));
	$("#"+dialogName).addTabToDialog({title:"从业人员", url:'${path}/baseinfo/actualCompany/practitionersList.jsp?id='+id, index:1});
	if("${id}" == null){
		$("#"+dialogName).tabDialog('disable');
	}
}
procActualCompanyTab('${id}');

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


		jQuery.validator.addMethod("exsistedCompanyName", function(value, element){
			var flag =true;
			if(value==null||value==undefined||value==""){return true}
			$.ajax({
				async:false,
				url:'/baseinfo/actualCompanyManage/hasDuplicateLocation.action',
			   	data:{
			   		"location.id":$('#actualCompanyId').val(),
					"location.companyName":$('#companyName').val(),
					"organizationId":$('#organizationId').val()
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
					async : false,
					success:function(data){
						if(!data.id){
	           	 			$.messageBox({
								evel: "error"
				 			});
	            			return;
						}procActualCompanyTab(data.id);
		                $("#<s:property value='#parameters.dailogName[0]'/>").proccessSuccess(data.id,data);
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