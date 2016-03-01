<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<div id="dialog-form" title="" class="container container_24">
		<div id="perUuid"></div>
		<input id="mode" type="hidden" name="mode" value="${(mode)!}" />
		<input id="_imgUrl" name="location.imgUrl" type="hidden" value="${(location.imgUrl)!}"/>
		<input id="newEconomicOrganizationsID" type="hidden" name="location.id" value="${(location.id)! }">
		<input id="orgId" type="hidden" name="location.organization.id" value="${(organizationId)!}" />
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
		<em class="form-req">*</em>
			<label class="form-lb1">所属网格：</label>
		</div>
		<div class="grid_18">
			<input type="text" id="commonOrgName" name="location.organization.orgName" value="${(location.organization.orgName)!}" style="width: 99%" class="form-txt" readonly  />
		</div>
		<div class='clearLine'>&nbsp;</div>

		<div class="grid_4 lable-right">
			<em class="form-req">*</em> <label class="form-lb1">名称：</label>
		</div>
		<div class="grid_18">                  
			<input type="text" id="name" name="location.name" value="${(location.name)!}" style="width: 99%" class="form-txt" maxlength="50"/>
		</div>
		<div class='clearLine'>&nbsp;</div>

		<div class="grid_4 lable-right">
			<em class="form-req">*</em> <label class="form-lb1">住所：</label>
		</div>
		<div class="grid_18">
			<input type="text" id="residence" name="location.address" value="${(location.address)!}" style="width: 99%" class="form-txt" maxlength="50"/>
		</div>
		<div class='clearLine'>&nbsp;</div>

		<div class="grid_4 lable-right">
			<em class="form-req">*</em><label class="form-lb1">营业执照号码：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="location.licenseNumber" id="licenseNumber" value="${(location.licenseNumber)!}" class='form-txt' maxlength="20"/>
		</div>

		<div class="grid_4 lable-right">
			<em class="form-req">*&nbsp;</em><label class="form-lb1">有效期：</label>
		</div>
		<div class="grid_3">
			<input type="text" name="location.validityStartDate" id="validityStartDate" readonly value='<@s.date name="location.validityStartDate" format="yyyy-MM-dd" />' class="form-txt" />
		</div>
		<div class="grid_1 lable-right">&nbsp;至：</div>
		<div class="grid_3">
			<input type="text" name="location.validityEndDate" id="validityEndDate" readonly value='<@s.date name="location.validityEndDate" format="yyyy-MM-dd" />' class="form-txt" />
		</div>


		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*&nbsp;</em><label class="form-lb1">类别：</label>
		</div>
		<div class="grid_7">
			<select name="location.type.id" id="style" class="form-txt">
				<@pop.OptionTag name="@com.tianque.domain.property.PropertyTypes@NEWECONOMICORGANIZATIONS_STYLE" defaultValue="${(location.type.id)!}" />
			</select>
		</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*&nbsp;</em><label class="form-lb1">负责人：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="location.manager" id="chief" maxlength="20" value="${(location.manager)!}" class="form-txt" />
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<label class="form-lb1">联系手机：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="location.mobileNumber" id="mobileNumber" maxlength="11" value="${(location.mobileNumber)!}" class="form-txt" />
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lb1">固定电话：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="location.telephone" id="telephone" maxlength="20" value="${(location.telephone)! }" class="form-txt" />
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		<div class="grid_4 lable-right">
			<label class="form-lb1">传真号码：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="location.foxNumber" id="foxNumber" maxlength="20" value="${(location.foxNumber)!}" class="form-txt" />
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lb1">面积：</label>
		</div>
		<div class="grid_6">
			<input type="text" name="location.area" id="area" value="${(location.area)!}"  class="form-txt" maxlength="9"/>
		</div>
		<div class="grid_1"><font size="1">m</font><font size="1"><sup>2</sup></font> </div>
		<div class='clearLine'>&nbsp;</div>
		
		<div class="grid_4 lable-right">
			<label class="form-lb1">从业人数：</label>
		</div>
		<div class="grid_6">
			<input type="text" name="location.employeeNumber" id="employeeNumber" value="${(location.employeeNumber)!}" class="form-txt" maxlength="9"/>
		</div>
		<div class="grid_1 lable-right">
  			<label class="form-lbl">个</label>
  		</div>
		<div class="grid_4 lable-right">
			<label class="form-lb1">党员人数：</label>
		</div>
		<div class="grid_6">
			<input type="text" name="location.partyNum" id="partyMemberNumber" value="${(location.partyNum)!}" class="form-txt" style="text-align:right;" maxlength="9"/>
		</div>
		<div class="grid_1 lable-right">
  			<label class="form-lbl">个</label>
  		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
   			<label class="form-lb1">简介：</label>
   		</div>
   		<div class="grid_18">
   			<textarea  name="location.introduction" id="introduction" class="form-txt">${(location.introduction)! }</textarea>
   		</div>
   		<div class="clearLine"></div>
        <br/>
   		<div class="grid_4 lable-right">
   			<label class="form-lb1">荣誉：</label>
   		</div>
   		<div class="grid_18">
   			<textarea name="location.honor" id="honor" class="form-txt" >${(location.honor)! }</textarea>
   		</div>
		<div class="clearLine"></div>
        <br/>
   		<div class="grid_4 lable-right">
   			<label class="form-lb1">备注：</label>
   		</div>
   		<div class="grid_18">
   			<textarea rows="3" name="location.remark" id="remark" class="form-txt">${(location.remark)!}</textarea>
   		</div>
   		<input name="isSubmit" id="isSubmit" type="hidden" value="" />
</div>

<script type="text/javascript">
$('#validityStartDate').datePicker({
		yearRange : '1900:2030',
		dateFormat : 'yy-mm-dd',
		onClose:function(dateText, inst) {
			if(dateText!=null&&dateText!=''){
				var dateUnit=dateText.split('-');
				var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
				$("#validityEndDate").datepicker("option", "minDate",date);
				}else{
					$("#validityEndDate").datepicker("option", "minDate", null);
				}
			}
		
		});
$('#validityEndDate').datePicker({
	yearRange : '1900:2030',
	dateFormat : 'yy-mm-dd',
	onClose:function(dateText, inst) {
		if(dateText!=null&&dateText!=''){
			var dateUnit=dateText.split('-');
			var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
			$("#validityStartDate").datepicker("option", "maxDate",date);
		}else{
			$("#validityStartDate").datepicker("option", "maxDate", null);
		}
		}
	
	});

$(document).ready(function(){
	if(null!=$("#_imgUrl").val() && $("#_imgUrl").val()!=""){//添加随机数保证IE下的图片查看没问题
		$("#headerImg").attr("src",$("#_imgUrl").val()+"?r="+Math.random());
		$(".shadow").show();
	}
	
	jQuery.validator.addMethod("peopleNumber", function(value, element) {   
		var stature = /^[0-9]*[1-9][0-9]*$/;
		return this.optional(element) || (value <= 999999999 && stature.test(value));       
	});
	
	jQuery.validator.addMethod("partyMemberNumber", function(value, element){
		if(value==null||value==undefined||value==""){return true}
		var employeeNumber = $("#employeeNumber").val();
		if(employeeNumber==null||employeeNumber==undefined||employeeNumber==""){return false;}
		if(value>eval(employeeNumber)){
			return false;
		}else{
			return true;
		}
	});
	jQuery.validator.addMethod("exsistedName", function(value, element){
		var flag =true;
		if(value==null||value==undefined||value==""){return true}
		$.ajax({
			async: false ,
			url:"${path}/baseinfo/newEconomicOrganizationsManage/hasDuplicateNewEconomicOrganizations.action",
		   	data:{
				"newEconomicOrganizations.name":$('#name').val(),
				"organizationId":$('#orgId').val(),
				"mode":$('#mode').val(),
				"newEconomicOrganizations.id":$('#newEconomicOrganizationsID').val()
	        },
			success:function(responseData){
				flag = !eval(responseData);
			}
		});
		return flag;
	});
	jQuery.validator.addMethod("exsistedLicenseNumber", function(value, element){
		var flag =true;
		if(value==null||value==undefined||value==""){return true}
		$.ajax({
			async: false ,
			url:"${path}/baseinfo/newEconomicOrganizationsManage/hasDuplicateNewEconomicOrganizations.action",
		   	data:{
				"newEconomicOrganizations.licenseNumber":$("#licenseNumber").val(),
				"organizationId":$('#orgId').val(),
				"mode":$('#mode').val(),
				"newEconomicOrganizations.id":$('#newEconomicOrganizationsID').val()
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
			 success : function(data) {
				if (!data.id) {
					$.messageBox({
						message : data,
						level : "error"
					});
					return;
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert("提交错误");
			}
			});
		},
		rules:{
			"location.name":{
				required:true,
				exculdeParticalChar:true,
				minlength:2,
				maxlength:50,
				exsistedName:true
			},
			"location.address":{
				required:true
			},
			"location.licenseNumber":{
				required:true,
				exculdeParticalChar:true,
				exsistedLicenseNumber:true
			},
			"location.validityStartDate":{
				required:true
			},
			"location.validityEndDate":{
				required:true
			},
			"location.style.id":{
				required:true
			},
			"location.manager":{
				required:true,
				exculdeParticalChar:true,
				minlength:2,
				maxlength:20
			},
			"location.telephone":{
				telephone:true
			},
			"location.foxNumber":{
				telephone:true
			},
			"location.mobileNumber":{
				mobile:true
			},
			"location.area":{
				number:true,
				min:0,
				max:999999999
			},
			"location.employeeNumber":{
				peopleNumber:true,
				number:true,
				min:1,
				max:999999999
			},
			"location.partyMemberNumber":{
				peopleNumber:true,
				partyMemberNumber:true,
				number:true,
				min:1,
				max:999999999
			},
			"location.introduction":{
				maxlength:300
			},
			"location.honor":{
				maxlength:300
			},
			"location.remark":{
				maxlength:300
			}
		},
		messages:{
			"location.name":{
				required:"名称不能为空",
				exculdeParticalChar:"名称不能存在非法字符",
				minlength:$.format("名称至少需要输入{0}个字符"),
				maxlength:$.format("名称最多需要输入{0}个字符"),
				exsistedName:"名称已存在"
			},
			"location.address":{
				required:"住所不能为空"
			},
			"location.licenseNumber":{
				required:"营业执照号码不能为空",
				exculdeParticalChar:"营业执照号码不能存在非法字符",
				exsistedLicenseNumber:"营业执照号码已存在"
			},
			"location.validityStartDate":{
				required:"有效期开始日期不能为空"
			},
			"location.validityEndDate":{
				required:"有效期结束日期不能为空"
			},
			"location.style.id":{
				required:"类别不能为空"
			},
			"location.manager":{
				required:"负责人不能为空",
				exculdeParticalChar:"负责人不能存在非法字符",
				minlength:$.format("负责人至少需要输入{0}个字符"),
				maxlength:$.format("负责人最多需要输入{0}个字符")
			},
			"location.telephone":{
				telephone:$.format("固定电话不合法，只能输数字和横杠(-)")
			},
			"location.foxNumber":{
				telephone:$.format("传真输入不合法，只能输数字和横杠(-)")
			},
			"location.mobileNumber":{
				mobile:"手机号码输入只能是以1开头的11位数字"
			},
			"location.area":{
				number: '面积需要输入非负数',
				min: '面积需要输入非负数',
				max: '面积最大输入999999999'
			},
			"location.employeeNumber":{
				peopleNumber:'从业人数需要输入正整数',
				number: '从业人数需要输入正数',
				min: '从业人数需要输入正数',
				max: '从业人数最大输入999999999'
			},
			"location.partyMemberNumber":{
				peopleNumber:'党员人数需要输入正整数',
				partyMemberNumber:"党员人数不能大于从业人数",
				number: '党员人数需要输入正数',
				min: '党员人数需要输入正数',
				max: '党员人数输入999999999'
			},
			"location.introduction":{
				maxlength:"简介最多需要输入300个字符"
			},
			"location.honor":{
				maxlength:"荣誉最多需要输入300个字符"
			},
			"location.remark":{
				maxlength:"备注最多需要输入300个字符"
			}
		}
	
	});

});

</script>