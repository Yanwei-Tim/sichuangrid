<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<div id="schoolInfo" class="container container_24">
 <div id="perUuid"></div> 
	<input id="name" type="hidden" name="name" value="${(name)!}"> 
	<input type="hidden" id="mode" name="mode" value="${(mode)!}" />
	<input type="hidden" name="location.organization.orgInternalCode" value="<@s.property value='location.organization.orgInternalCode'/>" /> 
	<input type="hidden" name="location.orgInternalCode" value="<@s.property value='location.orgInternalCode'/>" /> 
	<input type="hidden" name="location.organization.id" id="locationOrganizationId" value="<@s.property value='location.organization.id'/>" />
	<input id="organizationId2"	type="hidden" name="organizationId" value="${(location.organization.id )!}" />
	<input type="hidden" id="locationId" name="location.id" value="${(location.id)!}" /> 
	<input type="hidden" name="location.createUser" value="${(location.createUser)!}" />
	<input id="ajaxUrl" type="hidden" name="ajaxUrl" value="${(ajaxUrl)!}">
	<input name="isSubmit" id="isSubmit" type="hidden" value="">
	<input type="hidden" name="location.createDate" value='<@s.date name="location.createDate" format="yyyy-MM-dd HH:mm:ss"/>' />

<div class='clearLine'>&nbsp;</div>
<div class="grid_4 lable-right">
	<em class="form-req">*&nbsp;</em>
	<label class="form-lbl">所属网格：</label>
</div>
<div class="grid_19">
	<input type="text" name="location.organization.orgName" id="schoolOrgName" value="${(location.organization.orgName) ! }" readonly class="form-txt"   style="width: 99%"/>
</div>
<div class="grid_1"></div>
<div class='clearLine'>&nbsp;</div>

<div class="grid_4 lable-right">
	<em class="form-req">*&nbsp;</em>
	<label class="form-lbl">学校名称：</label>
</div>
<div class="grid_19">
	<input type="text" id="name" maxlength="30" name="location.name" value="${(location.name) ! }" class="form-txt" style="width: 99%"/>
</div>
<div class="grid_4 lable-right">
	<em class="form-req">*&nbsp;</em>
	<label class="form-lbl">学校地址：</label>
</div>
<div class="grid_19">
	<input type="text" name="location.address" maxlength="50" id="locationAddress" value="${(location.address) ! }" class="form-txt" style="width: 99%"/>
</div>
<div class='clearLine'>&nbsp;</div>
<div class="grid_4 lable-right">
	<em class="form-req">*&nbsp;</em>
	<label class="form-lbl">学校性质：</label>
</div>
<div class="grid_7">
	<select name="location.kind.id" id ="location-kind-id" class="form-select">
		<@pop.OptionTag name="@com.tianque.domain.property.PropertyTypes@SCHOOL_PROPERTY" defaultValue="${(location.kind.id) ! }" />
	</select>
</div>
<div class="grid_5 lable-right">
	<em class="form-req">*&nbsp;</em>
	<label class="form-lbl">校长：</label>
</div>
<div class="grid_7">
	<input type="text" name="location.manager" id="location-manager" maxlength="20" value="${(location.manager) ! }"  class="form-txt"  />
</div>

<div class='clearLine'>&nbsp;</div>
<div class="grid_4 lable-right">
	<em class="form-req">*&nbsp;</em>
	<label class="form-lbl">学校类型：</label>
</div>
<div class="grid_7">
	<select name="location.type.id" id="location-type-id" class="form-select" title="托管机构:在正常8小时以外从事学生托管的机构" >
		<@pop.OptionTag name="@com.tianque.domain.property.PropertyTypes@SCHOOL_TYPE" defaultValue="${(location.type.id) ! }" />
	</select>
</div>
<div class="grid_5 lable-right">
	<label class="form-lbl">关注程度：</label>
</div>
<div class="grid_7">
	<select name="location.attentionExtent.id" id="location-attentionExtent" class="form-txt"
		<@s.if test='"view".equals(mode)'>disabled='true'</@s.if> >
		<@pop.OptionTag name="@com.tianque.domain.property.PropertyTypes@ATTENTION_EXTENT" defaultValue="${(location.attentionExtent.id) ! }" />
	</select>
</div>
<div class='clearLine'>&nbsp;</div>
<div class="grid_4 lable-right">
	<label class="form-lbl">英文名称：</label>
</div>
<div class="grid_7">
	<input type="text" id="schoolEnglishName" maxlength="30" name="location.englishName" value="${(location.englishName) ! }" class="form-txt"/>
</div>
<div class="grid_5 lable-right">
	<label class="form-lbl">学校网址：</label>
</div>
<div class="grid_7">
	<input type="text" name="location.webSite" maxlength="30" style="ime-mode: disabled" id="schoolWebSite"
		value="${(location.webSite) ! }" class="form-txt  dialogtip" title="请输入以http://开头的学校网址!例:http://www.xxx.com">
</div>
<div class='clearLine'>&nbsp;</div>

<div class="grid_4 lable-right">
	<label class="form-lbl">法制副校长：</label>
</div>
<div class="grid_7">
	<input type="text" name="location.vicePresident" id ="location-vicePresident" maxlength="20" value="${(location.vicePresident) ! }" class="form-txt">
</div>
<div class="grid_5 lable-right">
	<label class="form-lbl">在校人数：</label>
</div>
<div class="grid_7">
	<input type="text" name="location.atSchoolHeadcount" maxlength="5" id ="location-atSchoolHeadcount" value="${(location.atSchoolHeadcount) ! }" class="form-txt"  />
</div>
<div class='clearLine'>&nbsp;</div>

<div class="grid_4 lable-right">
	<label class="form-lbl">联系电话：</label>
</div>
<div class="grid_7">
	<input type="text" name="location.telephone" maxlength="20" id="location-telephone"
	value="${(location.telephone) ! }" class="form-txt  dialogtip" title="请输入由数字和-组成的联系电话!例如：0577-88888888"/>
</div>
<div class="grid_5 lable-right">
	<label class="form-lbl">联系手机：</label>
</div>
<div class="grid_7">
	<input type="text" name="location.mobileNumber" maxlength="11" id = "location-mobileNumber"
	value="${(location.mobileNumber) ! }" class="form-txt  dialogtip" title="请输入11位以1开头的手机号码!例如：13988888888" />
</div>
<div class='clearLine'>&nbsp;</div>
<div class="grid_4 lable-right">
	<label class="form-lbl">传真：</label>
</div>
<div class="grid_7">
	<input type="text" id="schoolFax" maxlength="20" name="location.fax" value='${(location.fax)!}'
	class="form-txt  dialogtip"  title="请输入由数字和-组成的传真号码!例如：0577-88888888" />
</div>
<div class="grid_5 lable-right">
	<label class="form-lbl">电子邮箱：</label>
</div>
<div class="grid_7">
<input type="text" name="location.email" id="location-email" maxlength="30" style="ime-mode: disabled" value="${(location.email) ! }" class="form-txt  dialogtip"
	title="电子邮箱是通过网络电子邮局为网络客户提供的网络交流电子信息空间。电子邮箱具有存储和收发电子信息的功能，是因特网中最重要的信息交流工具 例如：youname@youcompany.com" />
</div>
<div class="grid_1"></div>
<div class='clearLine'>&nbsp;</div>

<div class="grid_4 lable-right">
	<label class="form-lbl">周边情况：</label>
</div>
<div class="grid_19">
	<textarea name="location.remark" maxlength="200" id ="location-remark" class="form-txt" rows="5" style="width: 99%">${(location.remark)!}</textarea>
</div>
<div class='clearLine'>&nbsp;</div>
</div>

<script type="text/javascript">
$(document).ready(function(){
//手机11位
jQuery.validator.addMethod("isMobileNumber", function(value, element){
	if(value==null||value==undefined||value=="" ){return true};
	var patrn=/^1[0-9]\d{9}$/;
	if (!patrn.exec(value.replace(/[ ]/g,""))) return false
	return true
});

//在校人数只能是数字且不能为负数
jQuery.validator.addMethod("isAtSchoolHeadcount", function(value, element){
	if(value==null||value==undefined||value=="" ){return true};
	var patrn=/^[0-9]*[1-9][0-9]*$/;
	if (!patrn.exec(value.replace(/[ ]/g,""))) return false
	return true
});



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
		required: true
	},
	"location.address":{
		required: true,
		maxlength: 50,
		exculdeParticalChar :true
	},
	"location.type.id":{
		required: true
	},///英文名称
	"location.englishName":{
		 maxlength: 30,
		 notChinese:true
	},
	"location.personLiableTelephone":{
		telephone : true
	},
	"location.personLiableMobileNumber":{
		digits:true,
		isMobileNumber:true,
		minlength:11
	},
	"location.email":{
          email:true,
          maxlength: 30
	},
	"location.manager":{
		required: true,
		minlength: 2,
		maxlength: 20,
		exculdeParticalChar :true
	},
	"location.contactName":{
		minlength: 2,
		maxlength: 20,
		exculdeParticalChar :true
	},
	"location.fax":{
		maxlength: 15,
		telephone:true
	},
	"location.remark":{
		maxlength: 200
	},
	"location.webSite":{
		maxlength: 30,
		url:true
	},
	"location.vicePresident":{
		minlength: 2,
		maxlength: 20,
		exculdeParticalChar :true
	},
	"location.atSchoolHeadcount":{
		isAtSchoolHeadcount : true
	}
},
messages: {
	"location.name" :{
		required: "请输入学校名称!",
		minlength  : $.format("至少需要{0}个字符"),
	    maxlength  : $.format("最多只能输入{0}个字符"),
		exculdeParticalChar : "学校名称不能包含特殊字符",
		exsistedName: "学校名称在该网格下已经存在，请重新输入"
	},
	"location.kind.id":{
		required:"请输入学校性质!"
	},
	"location.address":{
		required: "请输入学校地址!",
	    maxlength  : $.format("最多只能输入{0}个字符"),
		exculdeParticalChar :"学校地址不能包含特殊字符"
	},
	"location.type.id":{
		required:"请输入学校类型!"
	},
	"location.englishName":{
	    maxlength  : $.format("最多只能输入{0}个字符"),
	    notChinese:"英文名不能输入中文!"
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
	"location.manager":{
		required: "请输入校长!",
		minlength  : $.format("至少需要{0}个字符"),
	    maxlength  : $.format("最多只能输入{0}个字符"),
		exculdeParticalChar :"校长不能包含特殊字符"
	},
	"location.contactName":{
		minlength  : $.format("至少需要{0}个字符"),
	    maxlength  : $.format("最多只能输入{0}个字符"),
		exculdeParticalChar :"联系人不能包含特殊字符"
	},
	"location.fax":{
	    maxlength  : $.format("最多只能输入{0}个字符"),
	    telephone : "传真号码不合法!"
	},
	"location.remark":{
	    maxlength  : $.format("最多只能输入{0}个字符")
	},
	"location.webSite":{
	    maxlength  : $.format("最多只能输入{0}个字符"),
	    url : "网址输入有误!"
	},
	"location.vicePresident":{
		minlength  : $.format("至少需要{0}个字符"),
		maxlength  : $.format("最多只能输入{0}个字符"),
		exculdeParticalChar :"法制副校长输入不能包含特殊字符"
	},
	"location.atSchoolHeadcount":{
		isAtSchoolHeadcount :"在校人数只能输入整数数字!"
	}
}
});
});

</script>
