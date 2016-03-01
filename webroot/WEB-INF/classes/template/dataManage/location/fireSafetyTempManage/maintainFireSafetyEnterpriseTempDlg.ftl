<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
		<div id="enterpriseInfo" class="container container_24">
			<div id="perUuid"></div>
			<input id="mode" type="hidden"	 name="mode" value="${mode}" />
			<input id="organizationId"	type="hidden" name="location.organization.id" value="${(location.organization.id)!}" />
			<input id="locationId"	type="hidden" name="location.id" value="${(location.id)!}" />
			<input id="ajaxUrl" type="hidden" name="ajaxUrl" value="${(ajaxUrl)!}">
			<input name="isSubmit" id="isSubmit" type="hidden" value="">
			<div class="grid_4 lable-right">
			<@s.if test='!"view".equals(mode)'><em class="form-req">*</em></@s.if>
				<label class="form-lbl">所属网格：</label>
			</div>
			<div class="grid_19">
				<input type="text" name="location.organization.orgName" id="locationOrgName" value="${(location.organization.orgName) ! }" readonly class="form-txt"   style="width: 99%"/>
			</div>
			<div class="grid_1"></div>
			<div class='clearLine'>&nbsp;</div>

			<div class="grid_4 lable-right">
			<@s.if test='!"view".equals(mode)'>
					<em class="form-req">*</em>
				</@s.if>
				<label class="form-lbl">场所名称：</label>
			</div>
			<div class="grid_19">
			<input type="text" name="location.name" id="name" value="${(location.name)!}" maxlength="50"
			<@s.if test='"view".equals(mode)'>disabled='true'</@s.if> class="form-txt {required:true,exculdeParticalChar:true,exsistedName:true,messages:{required:'请输入场所名称',exculdeParticalChar:'不能输入非法字符',exsistedName:'场所名称在该网格下已经存在，请重新输入'}}" style="width: 99%" />
			</div>
			<div class="grid_1"></div>
			<div class='clearLine'>&nbsp;</div>
			
			<div class="grid_4 lable-right">
				<@s.if test='!"view".equals(mode)'>
					<em class="form-req">*</em>
				</@s.if>
				<label class="form-lbl">场所地址： </label>
			</div>
			<div class="grid_19">
				<input type="text" name="location.address" id="enterprise-address" value="${(location.address)! }" maxlength="50"
					<@s.if test='"view".equals(mode)'>disabled='true'</@s.if> class="form-txt" style="width: 99%" />
			</div>
			<div class="grid_1"></div>
			<div class='clearLine'>&nbsp;</div>

			<div class="grid_4 lable-right">
			<@s.if test='!"view".equals(mode)'>
					<em class="form-req">*</em>
				</@s.if>
				<label class="form-lbl">场所类型： </label>
			</div>
			<div class="grid_7">
				<select name="location.type.id" class="form-txt dialogtip" id="enterprise-type-id"
					<@s.if test='"view".equals(mode)'>disabled='true'</@s.if>>
					<@pop.OptionTag name="@com.tianque.domain.property.PropertyTypes@FIRESAFETY_TYPE" defaultValue="${(location.type.id)!}"/>
				</select>
			</div>
			<div class="grid_5 lable-right">
			<@s.if test='!"view".equals(mode)'>
					<em class="form-req">*</em>
				</@s.if>
				<label class="form-lbl">负责人：</label>
			</div>
			<div class="grid_7">
				<input type="text" name="location.manager" class="form-txt" id="location-legalPerson" value="${(location.manager)!}" maxlength="20"
					<@s.if test='"view".equals(mode)'>disabled='true'</@s.if> />
			</div>
			<div class='clearLine'>&nbsp;</div>
			<div class="grid_4 lable-right">
				<label class="form-lbl">关注程度：</label>
			</div>
			<div class="grid_7">
				<select name="location.attentionExtent.id" id="location-attentionExtent" class="form-txt"
					<@s.if test='"view".equals(mode)'>disabled='true'</@s.if> >
					<@pop.OptionTag name="@com.tianque.domain.property.PropertyTypes@ATTENTION_EXTENT" defaultValue="${(location.attentionExtent.id)!}" />
				</select>
			</div>
			<div class="grid_5 lable-right">
				<label class="form-lbl">手机号码：</label>
			</div>
			<div class="grid_7">
				<input type="text" name="location.mobileNumber" id="location-mobileNumber" value="${(location.mobileNumber)! }" maxlength="11"
					<@s.if test='"view".equals(mode)'>disabled='true'</@s.if>
					class="form-txt  dialogtip"  title="请输入11位以1开头的手机号码!例如:13988888888" />
			</div>
			<div class='clearLine'>&nbsp;</div>
			<div class="grid_4"></div>
			<div class="grid_7">
				<input id="isRiskEnterprise" type="checkbox" name="location.riskEnterprise" value="true" class="dialogtip"
					<@s.if test="location.riskEnterprise" >checked="checked"</@s.if>
					<@s.if test='"view".equals(mode)'>disabled='true'</@s.if> title="指依法设立且取得企业法人营业执照的，从事危险化学品生产的企业，包括最终产品或中间产品列入《危险化学品名录》的危险化学品的生产企业。" />
				<label class="form-check-text" for="isRiskEnterprise">是否存在隐患</label>
			</div>
			<div class="grid_5 lable-right">
				<label class="form-lbl">联系电话：</label>
			</div>
			<div class="grid_7">
				<input type="text" name="location.telephone" maxlength="15" id="location-telephone" value="${(location.telephone)! }" class="form-txt  dialogtip"
					<@s.if test='"view".equals(mode)'>disabled='true'</@s.if> title="请输入由数字和-组成的联系电话!例如：0577-88888888" />
			</div>
			<div class='clearLine'>&nbsp;</div>

			<div class="grid_4 lable-right">
				<label class="form-lbl">隐患情况：</label>
			</div>
			<div class="grid_19">
				<input type="text" name="location.hiddenCases" id="location-hiddenCases" 
				<@s.if test='"view".equals(mode)'>disabled='true'</@s.if>
				class="form-txt" value="${(location.hiddenCases)! }" style="width: 99%" maxlength="100" />
			</div>
			<div class='clearLine'>&nbsp;</div>

			<div class="grid_4 lable-right">
				<label class="form-lbl">隐患整改情况：</label>
			</div>
			<div class="grid_19">
				<input type="text" name="location.hiddenRectification" id="location-hiddenRectification"
					<@s.if test='"view".equals(mode)'>disabled='true'</@s.if>
					class="form-txt" value="${(location.hiddenRectification)! }" style="width: 99%" maxlength="100" />
			</div>
			<div class='clearLine'>&nbsp;</div>

			<div class="grid_4 lable-right">
				<label class="form-lbl">备注：</label>
			</div>
			<div class="grid_19">
				<textarea name="location.remark" id="location-remark"
					<@s.if test='"view".equals(mode)'>disabled='true'</@s.if>
					class="form-txt" style="height: 3em; width: 99%">${(location.remark)! }</textarea>
			</div>
			<div class='clearLine'>&nbsp;</div>
			<br />
	</div>
<script type="text/javascript">
	$(document).ready(function(){

		jQuery.validator.addMethod("partyMemberAmountLessThanEmployeeAmount", function(value, element){
			if($("#partyMemberAmount").val() != null
					&& $("#employeeAmount").val() != null
					&& $("#partyMemberAmount").val() != ""
					&& $("#employeeAmount").val() != ""
					&& parseInt($("#partyMemberAmount").val()) > parseInt($("#employeeAmount").val())){
				return false;
			}
			return true;
		});

	$("#maintainForm").formValidate({
			promptPosition: "bottomLeft",
			submitHandler: function(form){
				$(form).ajaxSubmit({
					async : false,
					success:function(data){
						if(!data.id){
							$.messageBox({
								message: data,
								level: "error"
							});
							return;
						}
					}
				})
			},
			rules:{
				"location.name":{
					required:true,
					exculdeParticalChar:true,
					maxlength:50
				},
				"location.manager":{
					required:true,
					exculdeParticalChar:true,
					minlength:2,
					maxlength:20
				},
				"location.businessLicense":{
					minlength:6,
					maxlength:20
				},
				"location.registeredCapital":{
					number:true,
					min:0,
					max:999999999
				},
				"location.area":{
					number:true,
					min:0,
					max:999999999
				},
				"location.employeeAmount":{
					digits:true,
					min:0,
					max:999999999
				},
				"location.partyMemberAmount":{
					digits:true,
					min:0,
					max:999999999,
					partyMemberAmountLessThanEmployeeAmount:true
				},
				"location.enterpriseTelephone":{
					telephone:true
				},
				"location.fax":{
					telephone:true
				},
				"location.telephone":{
					telephone:true
				},
				"location.mobileNumber":{
					mobile:true
				},
				"location.address":{
					required:true,
					minlength:2,
					maxlength:50
				},
				"location.type.id":{
					required:true
				},
				"location.hiddenCases":{
					maxlength:200
				},
				"location.hiddenRectification":{
					maxlength:200
				},
				"location.remark":{
					maxlength:200
				}
			},
			messages:{
				"location.name":{
					required:"请输入场所名称",
					exculdeParticalChar:"不能输入非法字符",
					minlength:$.format("场所名称至少需要输入{0}个字符"),
					maxlength:$.format("场所名称最多需要输入{0}个字符")
				},
				"location.manager":{
					required:"请输入法人代表",
					exculdeParticalChar:"不能输入非法字符",
					minlength:$.format("法人代表至少需要输入{0}个字符"),
					maxlength:$.format("法人代表最多需要输入{0}个字符")
				},
				"location.businessLicense":{
					minlength:$.format("工商执照至少需要输入{0}个字符"),
					maxlength:$.format("工商执照最多需要输入{0}个字符")
				},
				"location.registeredCapital":{
					number: "注册资金需要输入正数",
					min: "注册资金需要输入正数",
					max: "注册资金最大输入999999999"
				},
				"location.area":{
					number: "面积需要输入正数",
					min: "面积需要输入正数",
					max: "面积最大输入999999999"
				},
				"location.employeeAmount":{
					digits: "员工数量需要输入正整数",
					min: "员工数量需要输入正整数",
					max: "员工数量最大输入999999999"
				},
				"location.partyMemberAmount":{
					digits: "党员数量需要输入正整数",
					min: "党员数量需要输入正整数",
					max: "员工数量最大输入999999999",
					partyMemberAmountLessThanEmployeeAmount: "党员数不能大于员工数"
				},
				"location.enterpriseTelephone":{
					telephone:$.format("场所联系电话不合法，只能输数字和横杠(-)")
				},
				"location.fax":{
					telephone:$.format("场所传真不合法，只能输数字和横杠(-)")
				},
				"location.telephone":{
					telephone:$.format("法人联系电话不合法，只能输数字和横杠(-)")
				},
				"location.mobileNumber":{
					mobile:"手机号码输入只能是以1开头的11位数字"
				},
				"location.address":{
					required:"请输入场所地址",
					minlength:$.format("场所地址至少需要输入{0}个字符"),
					maxlength:$.format("场所地址最多需要输入{0}个字符")
				},
				"location.type.id":{
					required:"请选择场所类型"
				},
				"location.hiddenCases":{
					maxlength: "隐患情况最多输入200个字符"
				},
				"location.hiddenRectification":{
					maxlength: "隐患整改情况最多输入200个字符"
				},
				"location.remark":{
					maxlength: "备注最多输入200个字符"
				}
			}
		});



});


</script>