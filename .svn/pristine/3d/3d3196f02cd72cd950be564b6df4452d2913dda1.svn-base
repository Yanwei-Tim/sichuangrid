<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>

<div id="locationInfo" class="container container_24">
		<input id="modeType" type="hidden" name="modeType" value="${mode}">
		<input id="organizationId"	type="hidden" name="location.organization.id" value="${(location.organization.id )!}" />
			<input type="hidden" id="locationId" name="location.id" value="${(location.id)!}" /> 
			<input name="isSubmit" id="isSubmit" type="hidden" value=""> 
			<input id="_imgUrl" type="hidden" name="location.imgUrl"value="${(location.imgUrl)!}" />

		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*&nbsp;</em> <label class="form-lbl">所属网格：</label>
		</div>
		<div class="grid_19">
			<input type="text" name="orgName" id="locationOrgName" value="${(location.organization.orgName) ! }"
				readonly class="form-txt" style="width: 99%" />
		</div>
		<div class="grid_1"></div>
		<div class='clearLine'>&nbsp;</div>

		<div class="grid_4 lable-right">
			<em class="form-req">*&nbsp;</em> <label class="form-lbl">医院名称：</label>
		</div>
		<div class="grid_19">
			<input type="text" id="name" maxlength="30"
				name="location.name" value="${(location.name)!}"
				class="form-txt {required:true,exculdeParticalChar:true,messages:{required:'请输入医院名称',exculdeParticalChar:'不能输入非法字符'}}"
				style="width: 99%" />
		</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*&nbsp;</em> <label class="form-lbl">医院地址：</label>
		</div>
		<div class="grid_19">
			<input type="text" name="location.address" maxlength="50"
				id="locationAddress" value="${(location.address)!}" class="form-txt"
				style="width: 99%" />
		</div>
		<div class='clearLine'>&nbsp;</div>

		<div class="grid_4 lable-right">
			<em class="form-req">*&nbsp;</em> <label class="form-lbl">所属单位：</label>
		</div>
		<div class="grid_19">
			<input type="text" name="location.affiliatedUnit"
				id="location-affiliatedUnit" maxlength="20"
				value="${(location.affiliatedUnit)!}" class="form-txt" />
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">医院性质：</label>
		</div>
		<div class="grid_7">
			<select name="location.kind.id" id="location-kind-id"
				class="form-select">
				<@pop.OptionTag
					name="@com.tianque.domain.property.PropertyTypes@HOSPITALS_KIND"
					defaultValue="${(location.kind.id)!}" />
			</select>
		</div>
		<div class="grid_5 lable-right">
			<label class="form-lbl">医院类型：</label>
		</div>
		<div class="grid_7">
			<select name="location.type.id" id="location-type-id"
				class="form-select">
				<@pop.OptionTag
					name="@com.tianque.domain.property.PropertyTypes@HOSPITALS_TYPE"
					defaultValue="${(location.type.id)!}" />
			</select>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">关注程度：</label>
		</div>
		<div class="grid_7">
			<select name="location.attentionExtent.id"
				id="location-attentionExtent" class="form-txt"
				<@s.if test='"view".equals(mode)'>disabled='true'</@s.if>>
				<@pop.OptionTag
					name="@com.tianque.domain.property.PropertyTypes@ATTENTION_EXTENT"
					defaultValue="${(location.attentionExtent.id)!}" />
			</select>
		</div>
		<div class="grid_5 lable-right">
			<label class="fsorm-lbl">医院院长：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="location.director" id="location-director"
				maxlength="20" value="${(location.director)!}" class="form-txt">
		</div>

		<div class='clearLine'>&nbsp;</div>

		<div class="grid_4 lable-right">
			<label class="form-lbl">联系电话：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="location.telephone" maxlength="15"
				id="location-telephone" value="${(location.telephone)!}"
				class="form-txt  dialogtip" title="请输入由数字和-组成的联系电话!例如：0577-88888888" />
		</div>
		<div class="grid_5 lable-right">
			<label class="form-lbl">联系手机：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="location.mobileNumber" maxlength="11"
				id="location-mobileNumber" value="${(location.mobileNumber)!}"
				class="form-txt  dialogtip" title="请输入11位以1开头的手机号码!例如：13988888888" />
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">传真：</label>
		</div>
		<div class="grid_7">
			<input type="text" id="locationFax" maxlength="15"
				name="location.fax" value='${(location.fax)!}'
				class="form-txt  dialogtip" title="请输入由数字和-组成的传真号码!例如：0577-88888888" />
		</div>
		<div class="grid_5 lable-right">
			<label class="form-lbl">电子邮箱：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="location.email" id="location-email"
				maxlength="30" style="ime-mode: disabled" value="${(location.email)!}"
				class="form-txt  dialogtip"
				title="电子邮箱是通过网络电子邮局为网络客户提供的网络交流电子信息空间。电子邮箱具有存储和收发电子信息的功能，是因特网中最重要的信息交流工具 例如：youname@youcompany.com" />
		</div>
		<div class="grid_1"></div>
		<div class='clearLine'>&nbsp;</div>

		<div class="grid_4 lable-right">
			<label class="form-lbl">备注：</label>
		</div>
		<div class="grid_19">
			<textarea name="location.remark" id="location-remark"
				class="form-txt" rows="5" style="width: 99%">${(location.remark)!}</textarea>
		</div>
		<div class='clearLine'>&nbsp;</div>
</div>

<script type="text/javascript">
if(null!=$("#_imgUrl").val() && $("#_imgUrl").val()!=""){
	$("#headerImg").attr("src",$("#_imgUrl").val()+"?r="+Math.random());
	$(".shadow").show();
}

$(document).ready(function(){

	$("#tabs").tabFunction({ cache: true });
	$(".dialogtip").inputtip();
		$.ajax({
			async: false,
			url: "${path }/sysadmin/orgManage/getOrgRelativePath.action",
			data:{
				"organization.id":$("#organizationId").val()
			},
			success:function(responseData){
				$("#locationOrgName").val(responseData);
			}
		});
		
	//英文名称非法字符验证
	jQuery.validator.addMethod("islocationEnglishName", function(value, element){
		if(value==null||value==undefined||value=="" ){return true};
		var patrn=/[\u4e00-\u9fa5]+/;
		if (patrn.exec(value.replace(/[ ]/g,""))) return false
		return true
	});
	
	//手机11位
	jQuery.validator.addMethod("isMobileNumber", function(value, element){
		if(value==null||value==undefined||value=="" ){return true};
		var patrn=/^1[0-9]\d{9}$/;
		if (!patrn.exec(value.replace(/[ ]/g,""))) return false
		return true
	});

	//在校人数只能是数字且不能为负数
	jQuery.validator.addMethod("isAtlocationHeadcount", function(value, element){
		if(value==null||value==undefined||value=="" ){return true};
		var patrn=/^[0-9]*[1-9][0-9]*$/;
		if (!patrn.exec(value.replace(/[ ]/g,""))) return false
		return true
	});


	if(""!=$("#_imgUrl").val() && typeof($("#_imgUrl").val())!="undefined"){
		$("#img").attr("src","${path }/"+$("#_imgUrl").val());
	};

	$("#maintainForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form) {
		 $(form).ajaxSubmit({
			 async : false,
		     success: function(data){
	             if(!data.id){
	            	 $.messageBox({
						message:data,
						level: "error"
					 });
	             	return;
	             }
		   },
		   error: function(XMLHttpRequest, textStatus, errorThrown){
		      alert("提交错误");
		   }
		  });
		},
		rules: {
			"location.name" :{
				required: true,
			    minlength: 2,
				maxlength: 30,
				exculdeParticalChar :true,
				exsistedName: true
			},
			"location.kind.id":{
				required: false
			},
			"location.address":{
				required: true,
				maxlength: 50,
				exculdeParticalChar :true
			},
			"location.type.id":{
				required: false
			},///英文名称
			"location.telephone":{
				telephone : true
			},
			"location.mobileNumber":{
				digits:true,
				isMobileNumber:true,
				minlength:11
			},
			"location.email":{
		          email:true,
		          maxlength: 30
			},
			"location.fax":{
				maxlength: 15,
				telephone:true
			},
			"location.remark":{
				maxlength: 200
			}
		},
		messages: {
			"location.name" :{
				required: "请输入医院名称!",
				minlength  : $.format("至少需要{0}个字符"),
			    maxlength  : $.format("最多只能输入{0}个字符"),
				exculdeParticalChar : "医院名称不能包含特殊字符",
				exsistedName: "医院名称在该网格下已经存在，请重新输入"
			},
			"location.kind.id":{
				required:"请输入医院性质!"
			},
			"location.address":{
				required: "请输入地址!",
			    maxlength  : $.format("最多只能输入{0}个字符"),
				exculdeParticalChar :"医院地址不能包含特殊字符"
			},
			"location.type.id":{
				required:"请输入医院类型!"
			},
			"location.telephone":{
				telephone:"联系电话不合法，只能输数字和横杠(-)"
			},
			"location.mobileNumber":{
			    digits:"手机只能输入数字字符",
			    isMobileNumber :"手机号码只能输入以1开头的11为数字!",
				minlength:$.format("手机只能输入11位数字字符")
			},
			"location.email":{
		        email:"电子邮箱格式输入有误!",
		        maxlength  : $.format("最多只能输入{0}个字符")
			},
			"location.fax":{
			    maxlength  : $.format("最多只能输入{0}个字符"),
			    telephone : "传真号码不合法!"
			},
			"location.remark":{
			    maxlength  : $.format("最多只能输入{0}个字符")
			}
		}
	
	});
	
});

</script>
