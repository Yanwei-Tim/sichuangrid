<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>

<div class="container container_24">
		<input type="hidden" name="mode" id="mode" value="${mode}" />
		<input id="organizationId"	type="hidden" name="location.organization.id" value="${location.organization.id }" />
		<input type="hidden" name="location.id" value="${location.id }" id="locationId"/>
		<input id="ajaxUrl" type="hidden" name="ajaxUrl" value="${(ajaxUrl)!}">
		<@s.if test="#parameters.fromPopulationType[0]">
			<span style="font-weight: bold; margin-left:20px; ">实有单位</span>
			<div class='clearLine'>&nbsp;</div>
		</@s.if>
		<div class="grid_4 lable-right">
		   	<em class="form-req">*</em>
			<label class="form-lb1">所属网格：</label>
		</div>
		<div class="grid_20">
			<input type="text"  id="orgName"  name="location.organization.orgName"  style="width: 99%"  readonly value="${(location.organization.orgName)!}" class="form-txt" />
		</div>
	<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em>
			<label class="form-lb1">单位名称：</label>
		</div>
		<div class="grid_20">
			<input type="text" name="location.name" id="name" maxlength="50" style="width: 99%" value='${(location.name)! }' class="form-txt {required:true,exculdeParticalChar:true,exsistedName:true,messages:{required:'请输入单位名称',exculdeParticalChar:'不能输入非法字符',exsistedName:'单位名称已经被使用'}}" />
		</div>
	<div class='clearLine'>&nbsp;</div>

		<div class="grid_4 lable-right">
			<em class="form-req">*</em><label class="form-lb1">单位地址：</label>
		</div>
		<div class="grid_20">
			<input type="text" name="location.address" id="address" maxlength="50" style="width: 99%" value='${(location.address)! }' class="form-txt {required:true,messages:{required:'请输入单位地址'}}"  />
		</div>
	<div class='clearLine'>&nbsp;</div>
	<div class="grid_4 lable-right">
			<em class="form-req">*</em><label class="form-lb1">营业执照号码：</label>
		</div>
		<div class="grid_8">
			<input type="text" name="location.businessLicenseNo" id="businessLicenseNo" maxlength="50" value='${(location.businessLicenseNo)!}' class="form-txt {required:true,exculdeParticalChar:true,messages:{required:'请输入营业执照号码',exculdeParticalChar:'不能输入非法字符'}}" />
		</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em><label class="form-lb1">组织机构代码：</label>
		</div>
		<div class="grid_8">
			<input type="text" name="location.orgCode" id="orgCode"  maxlength="50" value="${(location.orgCode)!}"  class="form-txt {required:true,messages:{required:'请输入组织机构代码'}}"/>
		</div>
	<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<label class="form-lb1">单位类别：</label>
		</div>
		<div class="grid_8">
		<select name="location.companyType.id" id="companyType" class="form-txt" >
		   	<@pop.OptionTag name="@com.tianque.domain.property.PropertyTypes@ACTUALCOMPANY_COMPANYTYPE" defaultValue="${(location.companyType.id)!}" />
		</select>
		</div>
		<div class="grid_4 lable-right">
		</div>
		<div class="grid_8">
			<input type="checkbox" name="location.isKey" id="isKey" value="true" <@s.if test='true==location.isKey'>checked="checked"</@s.if>></input>
			<label class="form-check-text">是否重点单位</label>
		</div>
	<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<label class="form-lb1">法人代表：</label>
		</div>
		<div class="grid_8">
			<input type="text" name="location.manager" id="manager" maxlength="50" value="${(location.manager)!}" class='form-txt {exculdeParticalChar:true,minlength:2,maxlength:20,messages:{exculdeParticalChar:"不能输入非法字符",minlength:$.format("法人代表至少需要输入{0}个字符"),maxlength:$.format("法人代表最多需要输入{0}个字符")}}' />
		</div>
		<div  class="grid_4 lable-right">
			<label class="form-lb1">身份证号码：</label>
		</div>
		<div class="grid_8">
			<input type="text" name="location.idCardNo" id="idCardNo"  maxlength="18" value="${(location.idCardNo)!}"  class="form-txt {idCard:true,messages:{idCard:'请输入一个合法的身份证号码'}}" />
		</div>
	<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<label class="form-lb1">单位电话：</label>
		</div>
		<div class="grid_8">
			<input type="text" name="location.telephone" id="telephone" maxlength="20"  value='${(location.telephone)!}'class="form-txt {telephone:true,messages:{telephone:'固定电话不合法，只能输数字和横杠(-)'}}" />
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lb1">单位传真：</label>
		</div>
		<div class="grid_8">
			<input type="text" name="location.fax" id="fax"  maxlength="50" value="${(location.fax)!}"  class="form-txt {telephone:true,messages:{telephone:'单位传真不合法，只能输数字和横杠(-)'}}"/>
		</div>
	<div class='clearLine'>&nbsp;</div>

		<div class="grid_4 lable-right">
			<label class="form-lb1">注册资本：</label>
		</div>
		<div class="grid_6">
			<input type="text" name="location.registeredCapital" id="registeredCapital" maxlength="11" value="${(location.registeredCapital)!}" class="form-txt {fund:true,range:[1,999999.9999],messages:{fund:'请输入非负数 ，保留四位位小数点',range:'注册资本只能输入1到999999.9999之间的数'}}"/>
		</div>
		<div class="grid_2 lable-right">
 		  <label class="form-lbl">万元</label>
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lb1">经济性质：</label>
		</div>
		<div class="grid_8">
		<select name="location.economicNature.id" id="economicNature" class="form-txt" >
		   		<@pop.OptionTag name="@com.tianque.domain.property.PropertyTypes@ACTUALCOMPANY_ECONOMICNATURE" defaultValue="${(location.economicNature.id)!}" />
			</select>
		</div>
	<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<label class="form-lb1">有效期至：</label>
		</div>
		<div class="grid_8">
			<input type="text" name="location.expiryDate" id="expiryDate" maxlength="32" readonly value='<@s.date name="location.expiryDate" format="yyyy-MM-dd" />'class="form-txt " />
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lb1">注册日期：</label>
		</div>
		<div class="grid_8">
			<input type="text" name="location.registrationDate" id="registrationDate" maxlength="32" readonly value='<@s.date name="location.registrationDate" format="yyyy-MM-dd" />'class="form-txt " />
		</div>
	<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<label class="form-lb1">经营范围：</label>
		</div>
		<div class="grid_20">
			<input type="text" name="location.businessScope" id="businessScope" maxlength="50" style="width: 99%" value="${(location.businessScope)!}"   class="form-txt "/>
		</div>
	<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<label class="form-lb1">注册地址：</label>
		</div>
		<div class="grid_20">
			<input type="text" name="location.registrationAddress" id="registrationAddress" style="width: 99%" maxlength="50" value="${(location.registrationAddress)!}"  class="form-txt " />
		</div>
	<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<label class="form-lb1">从业人数：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="location.employeesNum" id="employeesNum" maxlength="9"  value="${(location.employeesNum)!}"   class="form-txt {digits:true,messages:{digits:'请输入不小于0的阿拉伯数字整数'} }" />
		</div>
		<div class="grid_1">
 		  <label class="form-lbl">&nbsp;人</label>
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lb1">主管部门：</label>
		</div>
		<div class="grid_8">
			<input type="text" name="location.competentDepartment" id="competentDepartment" maxlength="30"  value="${(location.competentDepartment)! }"   class="form-txt " />
		</div>
	<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<label class="form-lb1">管理级别：</label>
		</div>
		<div class="grid_8">

		<select name="location.supervisoryLevel.id" id="supervisoryLevel" class="form-txt" >
		   		<@pop.OptionTag name="@com.tianque.domain.property.PropertyTypes@ACTUALCOMPANY_SUPERVISORYLEVEL" defaultValue="${(location.supervisoryLevel.id)!}" />
			</select>
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lb1">管理部门：</label>
		</div>
		<div class="grid_8">
			<input type="text" name="location.supervisoryDepartment" id="supervisoryDepartment" maxlength="30"  value="${(location.supervisoryDepartment)! }"   class="form-txt " />
		</div>
	<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<label class="form-lb1">消防等级：</label>
		</div>
		<div class="grid_8">
		<select name="location.fireFightingLevel.id" id="fireFightingLevel" class="form-txt" >
		   		<@pop.OptionTag name="@com.tianque.domain.property.PropertyTypes@ACTUALCOMPANY_FIREFIGHTINGLEVEL" defaultValue="${(location.fireFightingLevel.id)!}" />
			</select>
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lb1">治安负责人：</label>
		</div>
		<div class="grid_8">
			<input type="text" name="location.securityChief" id="securityChief" maxlength="50"  value="${(location.securityChief)! }"   class="form-txt {exculdeParticalChar:true,messages:{exculdeParticalChar:'不能输入非法字符'}}" />
		</div>
	<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<label class="form-lb1">备注：</label>
		</div>
		<div class="grid_20">
			<textarea rows="3" name="location.remark" id="remark" class="form-txt" maxlength="300" style="width: 99%" class="form-txt {maxlength:true,messages:{maxlength:'备注最多只能输入300字符'}}">${(location.remark)! }</textarea>
		</div>
	<div class='clearLine'>&nbsp;</div>
</div>
<script>
	$(document).ready(function(){
		$('#registrationDate').datePicker({
			yearRange: '1900:2030',
			dateFormat: 'yy-mm-dd',
		    maxDate:'+0d'});
		$('#expiryDate').datePicker({
			yearRange : '1900:2060',
			dateFormat : 'yy-mm-dd',
			minDate : '+1d'
		});
		if($("#organizationId").val()==null || $("#organizationId").val()==""){
			$("#organizationId").val($("#currentOrgId").val());
		}

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