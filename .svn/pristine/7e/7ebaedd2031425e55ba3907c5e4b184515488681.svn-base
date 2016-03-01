<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>

<div id="newSocietyFederationInfo" class="container container_24">
		<input id="mode" name="mode" type="hidden" value="${(mode)!}" />
		<input id="_imgUrl" name="location.imgUrl" type="hidden" value="${(location.imgUrl)!}"/>
		<input id="organizationId" name="location.organization.id" type="hidden" value="${(location.organization.id)!}" />
		<input id="locationId" name="location.id" type="hidden" value="${(location.id)!}" />
		<input name="isSubmit" id="isSubmit" type="hidden" value="">
		<input id="ajaxUrl" type="hidden" name="ajaxUrl" value="${(ajaxUrl)!}">

		<div class="grid_4 lable-right">
  			<em class="form-req">*</em>
  			<label class="form-lbl">所属网格：</label>
  		</div>
   		<div class="grid_18">
			<input type="text" id="orgName" name="location.organization.orgName" value="${(location.organization.orgName)!}" style="width: 99%" class="form-txt" readonly  />
		</div>
   		<div class="clearLine">&nbsp;</div>

		<div class="grid_4 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">组织名称：</label>
		</div>
		<div class="grid_18 form-left">
	    	<input type="text" id="name" name="location.name" maxlength="20"
	    	  value="${(location.name)!}" class="form-txt  {required:true,exculdeParticalChar:true,exsistedCompanyName:true,minlength:2,messages:{required:'请输入社会组织名称',exculdeParticalChar:'不能输入非法字符',exsistedCompanyName:'社会组织名称在该网格下已经存在，请重新输入',minlength:'名称至少需要输入2个字符'}}" />
		</div>
   		<div class="clearLine">&nbsp;</div>

		<div class="grid_4 lable-right">
  			<em class="form-req">*</em>
			<label class="form-lbl">住所：</label>
		</div>
		<div class="grid_18 form-left">
			<input type="text" id="address" name="location.address" maxlength="50"
			  value="${(location.address)!}" 
			class="form-txt" />
		</div>
   		<div class="clearLine">&nbsp;</div>

		<div class="grid_4 lable-right">
  			<em class="form-req">*</em>
  			<label class="form-lbl">组织类别：</label>
  		</div>
		<div class="grid_7">
			<select id="type" name="location.type.id" class="form-select form-txt">
		   		<@pop.PropertyDictReleationSelectTag name="@com.tianque.domain.property.PropertyTypes@SOCIETY_GROUP" defaultValue="${(location.type.id)!}" reletionId="subTypeId"
		   		reletionName="@com.tianque.domain.property.PropertyTypes@NEW_SOCIETY_TYPE" id="type" />
			</select>
   		</div>
<!-- 				            新字段		    		 -->
		<!--<div class="grid_4 lable-right">-->
  			<!--<em class="form-req">*</em>-->
  			<!--<label class="form-lbl">组织子类别：</label>-->
  		<!--</div>-->
					<!--<div class="grid_7">-->
						<!--<select id="subTypeId" name="location.subType.id" class="form-select form-txt">-->
<!--  					   		<@pop.OptionTag name="@com.tianque.domain.property.PropertyTypes@NEW_SOCIETY_TYPE" defaultValue="${(location.subType.id)!}" />  -->
  						<!--</select>-->
		    		<!--</div>-->
   		<div class="clearLine">&nbsp;</div>

		<div class="grid_4 lable-right">
  			<em class="form-req">*</em>
<!-- 			  			<label class="form-lbl">法定代表人（负责人）：</label> -->
  			<label class="form-lbl">法定代表人：</label>
  		</div>
		<div class="grid_7 form-left">
			<input type="text" id="manager" name="location.manager" maxlength="20"
			  value="${(location.manager)!}" 
			class="form-txt" />
		</div>
		<div class="grid_4 lable-right">
  			<label class="form-lbl">固定电话：</label>
  		</div>
		<div class="grid_7 form-left">
			<input type="text" id="telephone" name="location.telephone" maxlength="15"
			  value="${(location.telephone)!}" class="form-select form-txt" />
		</div>
   		<div class="clearLine">&nbsp;</div>

		<div class="grid_4 lable-right">
  			<label class="form-lbl">联系手机：</label>
  		</div>
		<div class="grid_7 form-left">
			<input type="text" id="mobileNumber" name="location.mobileNumber" maxlength="11"
			 value="${(location.mobileNumber)!}" class="form-select form-txt" />
		</div>
		<div class="grid_4 lable-right">
  			<label class="form-lbl">业务主管单位：</label>
  		</div>
		<div class="grid_7 form-left">
			<input type="text" id="chargeUnit" name="location.chargeUnit" maxlength="50"
			 value="${(location.chargeUnit)!}" class="form-txt" />
		</div>
   		<div class="clearLine">&nbsp;</div>

		<div class="grid_4 lable-right">
  			<label class="form-lbl">登记证号码：</label>
  		</div>
		<div class="grid_7 form-left">
			<input type="text" id="registrationNumber" name="location.registrationNumber" maxlength="50"
			 value="${(location.registrationNumber)!}" class="form-txt" />
		</div>
		<div class="grid_4 lable-right">
  			<label class="form-lbl">有效期：从</label>
  		</div>
		<div class="grid_3 form-left">
			<input type="text" id="validityStartDate" name="location.validityStartDate" readonly
			value="<@s.date name="location.validityStartDate" format="yyyy-MM-dd"/>" class="form-txt" />
		</div>
		<div class="grid_1 lable-right">
  			<label class="form-lbl">至&nbsp;</label>
  		</div>
  		<div class="grid_3 form-left">
			<input type="text" id="validityEndDate" name="location.validityEndDate" readonly
			value='<@s.date name="location.validityEndDate" format="yyyy-MM-dd" />' class="form-txt" />
		</div>
   		<div class="clearLine">&nbsp;</div>

		<div class="grid_4 lable-right">
  			<label class="form-lbl">成员数：</label>
  		</div>
		<div class="grid_6 form-left">
			<input type="text" id="personNum" name="location.personNum" maxlength="50"
			value="${(location.personNum)!}" class="form-txt" />
		</div>
		<div class="grid_1 lable-right">
  			<label class="form-lbl">个</label>
  		</div>
		<div class="grid_4 lable-right">
  			<label class="form-lbl">党员人数：</label>
  		</div>
		<div class="grid_6 form-left">
			<input type="text" id="partyNum" name="location.partyNum" maxlength="50"
			value="${(location.partyNum)!}" class="form-txt" />
		</div>
		<div class="grid_1 lable-right">
  			<label class="form-lbl">个</label>
  		</div>
        <div class="clearLine"></div>

		<div class="grid_4 lable-right">
			<label class="form-lbl">简 介：</label>
		</div>
		<div class="grid_18">
   			<textarea id="introduction"  name="location.introduction"
   		 	class="form-txt" style="height: 3em;">${(location.introduction)!}</textarea>
		</div>
        <div class="clearLine"></div>
        <br/>

		<div class="grid_4 lable-right">
			<label class="form-lbl">获得荣誉：</label>
		</div>
		<div class="grid_18">
   			<textarea id="honor"  name="location.honor"
   		 	 class="form-txt" style="height: 3em;">${(location.honor)!}</textarea>
		</div>
        <div class="clearLine"></div>
        <br/>

		<div class="grid_4 lable-right">
			<label class="form-lbl">备注：</label>
		</div>
		<div class="grid_18">
   			<textarea id="remark" rows="3" name="location.remark"
   		 	 class="form-txt" >${(location.remark)!}</textarea>
		</div>	
</div>
<script type="text/javascript">

$('#validityStartDate').datePicker({
	yearRange : '1900:2030',
	dateFormat : 'yy-mm-dd',
	maxDate : '+0d'

	});
$('#validityEndDate').datePicker({
	minDate:'+1d'

	});

$(document).ready(function(){
	if(null!=$("#_imgUrl").val() && $("#_imgUrl").val()!=""){//添加随机数保证IE下的图片查看没问题
		$("#headerImg").attr("src",$("#_imgUrl").val()+"?r="+Math.random());
		$(".shadow").show();
	}
	
	
	jQuery.validator.addMethod("partyNum", function(value, element){
		if(value==null||value==undefined||value==""){return true}
		var personNum = $("#personNum").val();
		if(personNum==null||personNum==undefined||personNum==""){return false;}
		if(value>eval(personNum)){
			return false;
		}else{
			return true;
		}
	});

	//验证名称唯一性
	jQuery.validator.addMethod("exsistedCompanyName", function(value, element){
		var flag =true;
		if(value==null||value==undefined||value==""){return true}
		$.ajax({
			async:false,
			url:'${path}/baseinfo/newSocietyOrganizationsManage/hasDuplicateNewSocietyOrganizationsName.action',
		   	data:{
				"newSocietyOrganizations.name": $("#name").val(),
				"newSocietyOrganizations.organization.id" :  $("#currentOrgId").val(),
				"mode": $("#mode").val(),
				"newSocietyOrganizations.id":$("#newSocietyOrganizationsId").val()
	        },
			success:function(responseData){
				flag = !eval(responseData);
			}
		});
		return flag;
	});
	
	$("#maintainForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form) {
			$(form).ajaxSubmit({
				async : false,
				success:function(data){
	                if(!data.id){
	               	 $.messageBox({
							message:data,
							level: "error"
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
			"location.name":{
				required:true,
				exculdeParticalChar:true,
				minlength:2,
				maxlength:50,
				exsistedName:true
			},
			"location.address":{
				required:true,
				minlength:2,
				maxlength:50
			},
			"location.type.id":{
				required:true
			},
			"location.manager":{
				required:true,
				exculdeParticalChar:true,
				minlength:2
			},
			"location.telephone":{
				telephone:true
			},
			"location.mobileNumber":{
				mobile:true
			},
			"location.registrationNumber":{
				number:true
			},
			"location.introduction":{
				maxlength:200
			},
			"location.honor":{
				maxlength:200
			},
			"location.remark":{
				maxlength:200
			},
			"location.personNum":{
				number:true,
				min:1,
				max:999999999
			},
			"location.partyNum":{
				number:true,
				partyNum:true,
				min:1,
				max:999999999
			}
		},
		messages:{
			"location.name":{
				required:"请输入社会组织名称",
				exculdeParticalChar:"名称只能输入字母，数字和中文字符",
				minlength:$.format("名称至少需要输入{0}个字符")
			},
			"location.address":{
				required:"请输入住所",
				minlength:$.format("住所至少需要输入{0}个字符"),
				maxlength:$.format("住所最多只能输入{0}个字符")
			},
			"location.type.id":{
				required:"请选择社会组织类型"
			},
			"location.manager":{
				required:"请输入法定代表人",
				exculdeParticalChar:"法定代表人只能输入字母，数字和中文字符",
				minlength:$.format("法定代表人至少需要输入{0}个字符")
			},
			"location.mobileNumber":{
				mobile:$.format("手机号码输入只能是以1开头的11位数字")
			},
			"location.telephone":{
				telephone:$.format("固定电话只能输数字和横杠(-)")
			},
			"location.registrationNumber":{
				number:'登记证号码需要输入数字'
			},
			"location.introduction":{
				maxlength:$.format("简 介最多只能输入{0}个字符")
			},
			"location.honor":{
				maxlength:$.format("获得荣誉最多只能输入{0}个字符")
			},
			"location.remark":{
				maxlength:$.format("备注最多只能输入{0}个字符")
			},
			"location.personNum":{
				number: '成员数需要输入正数',
				min: '成员数需要输入正数',
				max: '成员数输入999999999'
			},
			"location.partyNum":{
				number: '党员人数需要输入数字',
				partyNum:"党员人数不能大于成员数",
				min: '党员人数需要输入正数',
				max: '党员人数输入999999999'
			}
		}
	});
	
});

</script>