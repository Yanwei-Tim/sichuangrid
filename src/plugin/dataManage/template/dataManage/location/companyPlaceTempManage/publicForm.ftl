<!-- 头部 公共字段 （除党政机关）-->
<div id="baseFromDiv" >
	<div class="grid_4 lable-right">
		<label class="form-lb1">负责人：</label>
	</div>
	<div class="grid_8">
		<input type="text" name="companyPlace.userName"  maxlength="20" style="width: 99%" value='${population.userName! }' class="form-txt {isLawful:true,exculdeParticalChar:true,minlength:2,maxlength:20, messages:{isLawful:'您输入了非法脚本，请重新输入！',exculdeParticalChar:'不能输入非法字符',minlength:$.format('负责人至少需要输入{0}个字符'),maxlength:$.format('负责人最多需要输入{0}个字符')}}" />
	</div>
	<div class="grid_4 lable-right">
		<label class="form-lb1">联系手机：</label>
	</div>
	<div class="grid_8">
		<input type="text" name="companyPlace.mobileNumber"  maxlength="11" style="width: 99%" value='${population.mobileNumber! }' class="form-txt {mobile:true,messages:{mobile:'手机号码输入只能是以1开头的11位数字'}}" title="请输入11位以1开头的联系手机  例如：13988888888" />
	</div>
	<div id="base_form_pf_id_div">
	<div class="grid_4 lable-right">
		<label class="form-lb1">联系固话：</label>
	</div>
	<div class="grid_8">
		<input type="text" name="companyPlace.telePhone"  maxlength="13" style="width: 99%" value='${population.telePhone! }' class="form-txt {telephone:true,messages:{telephone:'固定电话不合法，只能输数字和横杠(-)'}}" title="请输入由数字和-组成的联系电话,例如：0577-88888888" />
	</div>
	<div class="grid_4 lable-right">
		<label class="form-lb1">传真号码：</label>
	</div>
	<div class="grid_8">
		<input type="text" name="companyPlace.faxNumber"   maxlength="13" style="width: 99%" value='${population.faxNumber! }' class="form-txt {telephone:true,messages:{telephone:'传真号码不合法，只能输数字和横杠(-)'}}" title="请输入由数字和-组成的传真号码,例如：0577-88888888" />
	</div>
	</div>
	<div class='clearLine'>&nbsp;</div>
</div>

<!-- 主管单位，面积 （公共场所，交通场所，娱乐场所,上网服务场所[labe修改]） -->
<div id="executiveAreaFromDiv" >
	<div class="grid_4 lable-right">
		<label class="form-lb1" id="executive_from_id">主管单位：</label>
	</div>
	<div class="grid_12">
		<input type="text" name="companyPlace.area" maxlength="100"  style="width: 99%" value='${population.area! }' class="form-txt {isLawful:true,exculdeParticalChar:true,minlength:2,maxlength:100, messages:{isLawful:'您输入了非法脚本，请重新输入！',exculdeParticalChar:'不能输入非法字符',minlength:$.format('主管单位至少需要输入{0}个字符'),maxlength:$.format('主管单位最多需要输入{0}个字符')}}" />
	</div>
	<div class="grid_4 lable-right">
		<label class="form-lb1">面积：</label>
	</div>
	<div class="grid_4">
		<input type="text" name="companyPlace.coveredArea"   maxlength="11" style="width: 80%" value='${population.coveredArea! }' class="form-txt {number:true,min:0,max:999999999,messages:{number:'面积需要输入正数',min:'面积需要输入正数',max:'面积最大输入999999999'}}" />㎡
	</div>
	<div class='clearLine'>&nbsp;</div>
</div>
<!-- 执照（娱乐场所，商贸场所，上网服务场所，住宿场所，餐饮场所，其他场所，教育） -->
<div id="licenseFromDiv" >
	<div class="grid_4 lable-right">
		<label class="form-lb1">
		<input type="hidden" id="hasLicense_Realy" value="${population.hasLicense! }" name="companyPlace.hasLicense">
		<input type="checkbox" id="hasLicense_checkBox">是否有证照</label>
	</div>
	<div class='clearLine'>&nbsp;</div>		
	<div class="grid_4 lable-right">
		<label class="form-lb1">营业执照号：</label>
	</div>
	<div class="grid_12">
		<input type="text" name="companyPlace.businessLicenseNo" readonly="readonly" id="businessLicenseNo"  maxlength="20" style="width: 99%" value='${population.businessLicenseNo! }' class="form-txt {required:function(){var isChecked=$('#hasLicense_checkBox').is(':checked');return isChecked;},isLawful:true,minlength:6,maxlength:20, messages:{required:'请输入营业执照号',isLawful:'您输入了非法脚本，请重新输入！',minlength:$.format('营业执照号至少需要输入{0}个字符'),maxlength:$.format('营业执照号最多需要输入{0}个字符')}}" />
	</div>
	<div class="grid_4 lable-right">
		<label class="form-lb1">组织机构代码：</label>
	</div>
	<div class="grid_4">
		<input type="text" name="companyPlace.orgCode"   maxlength="50" style="width: 99%" value='${population.orgCode! }' class="form-txt {isLawful:true,isDigitStrAndUnderline:true,minlength:2,maxlength:50, messages:{isLawful:'您输入了非法脚本，请重新输入！',isDigitStrAndUnderline:'组织机构代码只能由数字、字母、下划线组成',minlength:$.format('组织机构代码至少需要输入{0}个字符'),maxlength:$.format('组织机构代码最多需要输入{0}个字符')}}" />
	</div>
	<div class='clearLine'>&nbsp;</div>
</div>
<!-- 规模企业（商贸场所，上网服务场所，餐饮服务场所，其他场所） -->
<div id="scaleFromDiv" >
	<div class="grid_4 lable-right">
		<label class="form-lb1">规模：</label>
	</div>
	<div class="grid_4">
		<select class="form-txt"  name="companyPlace.scaleType.id">
			<@pop.OptionTag name="@com.tianque.domain.property.PropertyTypes@ENTERPRISE_TYPE" defaultValue="${(population.scaleType.id)!}"/>
		</select>
	</div>
	<div class="grid_4 lable-right">
		<label class="form-lb1">党员数：</label>
	</div>
	<div class="grid_4">
		<input type="text"  name="companyPlace.partyCountNumber"  maxlength="9" style="width: 99%" value='${population.partyCountNumber! }' class="form-txt {digits:true,min:0,max:999999999,messages:{digits:'党员数量需要输入正整数',min:'党员数量需要输入正整数',max:'党员数量最大输入999999999'}}" />
	</div>
	<div class="grid_4 lable-right">
		<label class="form-lb1">注册资金：</label>
	</div>
	<div class="grid_4">
		<input type="text"  name="companyPlace.registeredCapital"  maxlength="11" style="width: 65%" value='${population.registeredCapital! }' class="form-txt {number:true,min:0,max:999999999,messages:{number:'注册资金需要输入正数',min:'注册资金需要输入正数',max:'注册资金最大输入999999999'}}" />万元
	</div>
	<div class='clearLine'>&nbsp;</div>
</div>