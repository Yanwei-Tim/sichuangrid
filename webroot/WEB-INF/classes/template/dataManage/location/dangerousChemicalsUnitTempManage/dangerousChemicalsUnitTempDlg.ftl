<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<div class="container container_24" >
	<div id="dangerousChemicalsUnit" class="container container_24">
		<input type="hidden" name="mode" id="mode" value="${(mode)!}" />
		<input type="hidden" name="location.id" id="locationId" value="${(location.id)!}" />
		<input id="organizationId2"	type="hidden" name="organizationId" value="${(location.organization.id )!}" />
		<input id="organizationId"	type="hidden" name="location.organization.id" value="${(location.organization.id )!}" />
		<@s.if test="#parameters.fromlocationType[0]">
			<span style="font-weight: bold; margin-left:20px; ">危险化学品单位</span>
			<div class='clearLine'>&nbsp;</div>
		</@s.if>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">所属网格：</label>
		</div>
		<div class="grid_20">
   				<input type="text"  id="orgName"  name="location.organization.orgName"  readonly value="${(location.organization.orgName) ! }" style="width: 99%"	class="form-txt" />
   		</div>
		<div class='clearLine'></div>
		<div class="grid_4 lable-right">
		<em class="form-req">*</em>
			<label class="form-lbl">单位名称：</label>
		</div>
		<div class="grid_20">
   				<input type="text"  id="name"  name="location.name"   value="${(location.name)!}" style="width: 99%"  maxlength="50" title="场所名称可以输入字母、数字、和中文"
   				class="form-txt " />
   		</div>
		<div class='clearLine'></div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">单位地址：</label>
		</div>
		<div class="grid_20">
   			<input type="text"  id="address"  name="location.address"   value="${(location.address) ! }" style="width: 99%"  maxlength="50" title="场所地址可以输入由字母、数字、和中文"	class="form-txt" />
   		</div>
		<div class="clearLine"></div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">关注程度：</label>
		</div>
		<div class="grid_8">
			<select name="location.attentionExtent.id" id="location-attentionExtent" class="form-txt"
				<@s.if test='"view".equals(mode)'>disabled='true'</@s.if> >
				<@pop.OptionTag name="@com.tianque.domain.property.PropertyTypes@ATTENTION_EXTENT" defaultValue="${(location.attentionExtent.id) ! }" />
			</select>
		</div>
		<div class="grid_4 lable-right">
   			<label class="form-lb1">负责人：</label>
	   	</div>
	   	<div class="grid_8">
	   		<input type="text" name="location.manager" id="manager" maxlength="20"  value="${(location.manager ) ! }" title="请输入负责人名字、如:张三" class="form-txt"/>
	   	</div>
        <div class="clearLine">&nbsp;</div>
        <div class="grid_4 lable-right">
   			<label class="form-lb1">联系电话：</label>
	   	</div>
	   	<div class="grid_8">
	   		<input type="text" name="location.telephone" id="mobile" maxlength="12"  value="${(location.telephone ) ! }" title="请输入负责人联系方式、如:11111111111"
	   		class='form-txt {telephone:true,messages:{telephone:$.format("手机号码或者固定电话，只能输数字或数字和横杠(-)")}}'
	   		/>
	   	</div>
		<div class="grid_4 lable-right">
            <label class="form-lbl">单位类别：</label>
        </div>
        <div class="grid_8">
            <input type="text" name="location.unitType" id="unitType" class="form-txt" value="${(location.unitType)!}"  maxlength="50"/>
        </div>
        <div class="grid_4 lable-right">
            <label class="form-lbl">货物类别：</label>
        </div>
        <div class="grid_20">
            <input type="text" name="location.commodityType" id="commodityType" class="form-txt" value="${(location.commodityType)!}" maxlength="50"/>
        </div>
        <div class="clearLine">&nbsp;</div>
        <div class="grid_4 lable-right">
			<label class="form-lbl">副本许可范围：</label>
		</div>
		<div class="grid_20">
			<input type="text" name="location.copyScope" class="form-txt" value="${(location.copyScope)!}" maxlength="50"/>
		</div>
         <div class="clearLine">&nbsp;</div>
        <div class="grid_4 lable-right">
			<label class="form-lbl">存储设备：</label>
		</div>
		<div class="grid_20">
			<input type="text" name="location.storageDevice" class="form-txt" value="${(location.storageDevice)!}" maxlength="50"/>
		</div>
        <div class="clearLine"></div>
		<div class='clearLine'></div>
		<div class="grid_4 lable-right">
		   <label class="form-lbl">备注：</label>
		</div>
		<div class="grid_20">
			<textarea rows="3" cols="" maxlength="200" id="location-remark" name="location.remark" class="form-txt" >${(location.remark)!}</textarea>
		</div>

		<div class='clearLine'>&nbsp;</div>
		<input name="isSubmit" id="isSubmit" type="hidden" value="" />
	 </div>
</div>
<script>
jQuery.validator.addMethod("decimal", function(value, element) {
    var decimal = /^-?\d+(\.\d{1,2})?$/;
    if (value==""){
	     return true;
	 }
    if(value>=0){
    	return this.optional(element) || (decimal.test(value));
    }
    return false;
});
$(document).ready(function(){
	$("#maintainForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form){
			$(form).ajaxSubmit({
				success:function(data){
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
		rules:{
			"location.name":{
				required:true,
				exsistedName:true,
				exculdeParticalChar:true,
				minlength:2,
				maxlength:50
			},
			"location.address":{
				exculdeParticalChar:true,
				minlength:2,
				maxlength:50
			},
			"location.manager":{
				exculdeParticalChar:true,
				minlength:2,
				maxlength:20
			},
			"location.remark":{
				maxlength:200
			}
		},
		messages:{
			"location.name":{
				required:"请输入危险化学品单位名称",
				exsistedInternetBarName:"上危险化学品单位名称已存在，请重新输入",
				exculdeParticalChar:"危险化学品单位名称只能输入字母，数字和中文字符",
				minlength:$.format("危险化学品单位名称至少需要输入{0}个字符"),
				maxlength:$.format("危险化学品单位名称最多只能输入{0}个字符")
			},
			"location.address":{
				exculdeParticalChar:"危险化学品单位地址只能输入字母，数字和中文字符",
				minlength:$.format("危险化学品单位地址至少需要输入{0}个字符"),
				maxlength:$.format("危险化学品单位地址最多只能输入{0}个字符")
			},
			"location.manager":{
				exculdeParticalChar:"负责人只能输入字母，数字和中文字符",
				minlength:$.format("负责人至少需要输入{0}个字符"),
				maxlength:$.format("负责人最多只能输入{0}个字符")
			},
			"location.remark":{
				maxlength:$.format("备注最多只能输入{0}个字符")
			}
		}
	});
});
</script>
