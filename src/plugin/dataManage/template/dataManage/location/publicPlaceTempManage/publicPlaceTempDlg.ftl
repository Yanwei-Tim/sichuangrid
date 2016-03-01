<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>

<div class="container container_24" >
	<div id="publicPlace" class="container container_24">
			<div id="perUuid"></div>
			<input type="hidden" name="mode" id="mode" value="${(mode)!}" />
			<input type="hidden" name="location.id" id="locationId" value="${(location.id)!}" />
			<input id="organizationId"	type="hidden" name="location.organization.id" value="${(location.organization.id)! }" />
			<input id="ajaxUrl" type="hidden" name="ajaxUrl" value="${(ajaxUrl)!}">
			<@s.if test="#parameters.fromlocationType[0]">
				<span style="font-weight: bold; margin-left:20px; ">公共场所</span>
				<div class='clearLine'>&nbsp;</div>
			</@s.if>
			<div class="grid_4 lable-right">
			   	<em class="form-req">*</em>
	   			<label class="form-lb1">所属网格：</label>
   			</div>
   			<div class="grid_20">
   				<input type="text"  id="orgName"  name="location.organization.orgName"  readonly value="${(location.organization.orgName)!}" style="width: 99%"	class="form-txt" />
   			</div>
   			<div class='clearLine'>&nbsp;</div>
   			<div class="grid_4 lable-right">
			   	<em class="form-req">*</em>
	   			<label class="form-lb1">场所名称：</label>
   			</div>
   			<div class="grid_20">
   				<input type="text"  id="name"  name="location.name"   value="${(location.name)!}" style="width: 99%"  maxlength="50" title="请输入由字母、数字、和中文组成的场所名称"	class="form-txt" />
   			</div>
   			<div class="grid_4 lable-right">
			   	<em class="form-req">*</em>
	   			<label class="form-lb1">场所地址：</label>
   			</div>
   			<div class="grid_20">
   				<input type="text"  id="address"  name="location.address"   value="${(location.address)!}" style="width: 99%"  maxlength="50" title="请输入由字母、数字、和中文组成的场所地址"	 class="form-txt"  />
   			</div>
   			<div class='clearLine'>&nbsp;</div>
   			<div class="grid_4 lable-right">
				<label class="form-lbl">关注程度：</label>
			</div>
			<div class="grid_8">
				<select name="location.attentionExtent.id" id="location-attentionExtent" class="form-txt"
					<@s.if test='"view".equals(mode)'>disabled='true'</@s.if> >
					<@pop.OptionTag name="@com.tianque.domain.property.PropertyTypes@ATTENTION_EXTENT" defaultValue="${(location.attentionExtent.id)!}" />
				</select>
			</div>
			<div class='clearLine'>&nbsp;</div>
   			<div class="grid_4 lable-right">
   				<label class="form-lb1">负责人：</label>
	   		</div>
	   		<div class="grid_8">
	   			<input type="text" name="location.manager" id="manager" maxlength="20"  value="${(location.manager)! }" title="请输入负责人名字、如:张三" class=" form-txt" />
	   		</div>
   			<div class="grid_4 lable-right">
   				<label class="form-lb1">备案登记号码：</label>
	   		</div>
	   		<div class="grid_8">
	   			<input type="text" name="location.registrationNumber" id="registrationNumber" maxlength="20"  value="${(location.registrationNumber)! }" title="请输入备案号码"  class=" form-txt" />
	   		</div>
   			<div class='clearLine'>&nbsp;</div>

   			<div class="grid_4 lable-right">
   				<label class="form-lb1">开业时间：</label>
	   		</div>
	   		<div class="grid_8">
			<input type="text" name="location.openingDate" id="openingDate" class="form-txt" readonly="readonly" value="<@s.date name="location.openingDate" format="yyyy-MM-dd"/>"/>
			</div>
   			<div class="grid_4 lable-right">
	   			<label class="form-lb1">领取执照时间：</label>
   			</div>
   			<div class="grid_8">
				<input type="text" name="location.licenseDate" id="licenseDate" class="form-txt" readonly="readonly" value="<@s.date name="location.licenseDate" format="yyyy-MM-dd"/>"/>
			</div>
   			<div class='clearLine'>&nbsp;</div>
   			 <div class="grid_4 lable-right">
   				<label class="form-lb1">场所等级：</label>
	   		</div>
	   		<div class="grid_8">
	   			<input type="text" name="location.placeLevel" id="placeLevel" maxlength="20"  value="${(location.placeLevel)! }" title="请输入场所等级" class=" form-txt" />
	   		</div>
   			 <div class="grid_4 lable-right">
   				<label class="form-lb1">公共场所类别：</label>
	   		</div>
	   		<div class="grid_8">
	   			<input type="text" name="location.category" id="category" maxlength="20"  value="${(location.category)! }" title="请输入公共场所类别" class=" form-txt" />
	   		</div>
   			<div class='clearLine'>&nbsp;</div>
   			<div class='clearLine'>&nbsp;</div>

   			<div class="grid_4 lable-right">
   				<label class="form-lb1">总面积：</label>
	   		</div>
	   		<div class="grid_7">
	   			<input type="text" name="location.totalArea" id="totalArea" maxlength="10"  value="${(location.totalArea)! }" 
	   			class="form-txt {decimal:true,range:[1,9999999.99],messages:{decimal:'请输入非负数 ，保留两位位小数点',range:'总面积只能输入1到9999999.99之间的数'}}" />
	   		</div>
	   		<div class="grid_1">
	 		  <label class="form-lbl">&nbsp;m<sup>2</sup></label>
			</div>
   			<div class="grid_4 lable-right">
   				<label class="form-lb1">营业面积：</label>
	   		</div>
	   		<div class="grid_7">
	   			<input type="text" name="location.operationArea" id="operationArea" maxlength="10"  value="${(location.operationArea)! }" 
	   			class="form-txt {decimal:true,range:[1,9999999.99],messages:{decimal:'请输入非负数 ，保留两位位小数点',range:'营业面积只能输入1到9999999.99之间的数'}}" />
	   		</div>
	   		<div class="grid_1">
	 		  <label class="form-lbl">&nbsp;m<sup>2</sup></label>
			</div>
   			<div class='clearLine'>&nbsp;</div>
	   		<div class="grid_4 lable-right">
   				<label class="form-lb1">包间数：</label>
	   		</div>
	   		<div class="grid_7">
	   			<input type="text" name="location.privateRoomCount" id="privateRoomCount" maxlength="10"  value="${(location.privateRoomCount)! }" title="请输入包间数" class=" form-txt" />
	   		</div>
	   		<div class="grid_1">
	 		  <label class="form-lbl">&nbsp;间</label>
			</div>
   			<div class="grid_4 lable-right">
   				<label class="form-lb1">核定人数：</label>
	   		</div>
	   		<div class="grid_7">
	   			<input type="text" name="location.vouchedPeopleCount" id="vouchedPeopleCount" maxlength="10"  value="${(location.vouchedPeopleCount)! }" title="请输入核定人数"  class=" form-txt" />
	   		</div>
	   		<div class="grid_1">
	 		  <label class="form-lbl">&nbsp;人</label>
			</div>
   			<div class='clearLine'>&nbsp;</div>
  			<div class="grid_4 lable-right">
   				<label class="form-lb1">通道口：</label>
	   		</div>
	   		<div class="grid_8">
	   			<input type="text" name="location.passageway" id="passageway" maxlength="20"  value="${(location.passageway)! }" title="请描述通道口" class=" form-txt" />
	   		</div>
	   		<div class="grid_4 lable-right">
   				<label class="form-lb1">小件寄存处：</label>
	   		</div>
	   		<div class="grid_8">
			   <select name="location.cloakroom.id" id="location.cloakroom" class="form-txt" >
			   		<@pop.OptionTag name="@com.tianque.domain.property.PropertyTypes@PUBLICPLACE_CLOAKROOM" defaultValue="${(location.cloakroom.id)!}" />
				</select>
			</div>
   			<div class="grid_4 lable-right">
   				<label class="form-lb1">内部结构：</label>
	   		</div>
	   		<div class="grid_8">
	   			<input type="text" name="location.innerStructure" id="innerStructure" maxlength="20"  value="${(location.innerStructure)! }" title="请输入描述内部结构" class=" form-txt" />
	   		</div>
	   		<div class="grid_4 lable-right">
   				<label class="form-lb1">建筑物结构：</label>
	   		</div>
	   		<div class="grid_8">
	   			<input type="text" name="location.buildingStructure" id="buildingStructure" maxlength="20"  value="${(location.buildingStructure)! }" title="请输入建筑物结构类型、如砖混，水泥等" class=" form-txt" />
	   		</div>
   			<div class='clearLine'>&nbsp;</div>

   			 <div class="grid_4 lable-right">
   				<label class="form-lb1">周围环境：</label>
	   		</div>
	   		<div class="grid_20">
   				<input name="location.surrounding" id="surrounding" 
   				class="form-txt" maxlength="150" style="width: 99%" value= "${(location.surrounding)! }" />
   			</div>

			<div class="grid_4 lable-right">
   				<label class="form-lb1">备注：</label>
	   		</div>
	   		<div class="grid_20">
   				<textarea rows="4" maxlength="200" name="location.remark" id="remark" class="form-txt" style="width: 99%">${(location.remark)! }</textarea>
   			</div>
   		    <input name="isSubmit" id="isSubmit" type="hidden" value="" />
  	</div>
</div>

<script type="text/javascript">
$(document).ready(function(){
$('#licenseDate').datePicker({
	yearRange: '1900:2060',
	dateFormat:'yy-mm-dd',
	maxDate:'+0y'
});
$('#openingDate').datePicker({
	yearRange: '1900:2060',
	dateFormat:'yy-mm-dd'
	//maxDate:'+0y'
});
jQuery.validator.addMethod("isOpeningDatebigger", function(value, element){
	var value = $('#openingDate').val();
	var licenseDate =$('#licenseDate').val();
	if(value==null||value==undefined||value==""){return true}
	if(value < licenseDate){
       return false;
	}else {
		return true;
	}
});

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
			"location.name":{
				required:true,
				exsistedName:true,
				exculdeParticalChar:true,
				minlength:2,
				maxlength:50
			},
			"location.address":{
				required:true,
				exculdeParticalChar:true,
				minlength:2,
				maxlength:50
			},
			"location.openingDate":{
				isOpeningDatebigger:true
			},
			"location.manager":{
				exculdeParticalChar:true,
				minlength:2,
				maxlength:10
			},
			"location.registrationNumber":{
				positiveInteger:true,
				maxlength:30
			},
			"location.totalArea":{
				posNumWiPot:true,
				maxlength:10
			},
			"location.operationArea":{
				posNumWiPot:true,
				maxlength:10
			},
			"location.vouchedPeopleCount":{
				positiveInteger:true,
				maxlength:10
			},
			"location.privateRoomCount":{
				positiveInteger:true,
				maxlength:10
			},
			"location.surrounding":{
				maxlength:150
			},
			"location.remark":{
				maxlength:200
				}
		},
		messages:{
			"location.name":{
				required:"请输入公共场所名称",
				exsistedName:"公共场所名称已存在，请重新输入",
				exculdeParticalChar:"公共场所名称只能输入字母，数字和中文字符",
				minlength:$.format("公共场所名称至少需要输入{0}个字符"),
				maxlength:$.format("公共场所名称最多只能输入{0}个字符")
				},
			"location.address":{
					required:"请输入公共场所地址",
					exculdeParticalChar:"公共场所地址只能输入字母，数字和中文字符",
					minlength:$.format("公共场所地址至少需要输入{0}个字符"),
					maxlength:$.format("公共场所地址最多只能输入{0}个字符")
			},
			"location.openingDate":{
				isOpeningDatebigger:$.format("开业时间应该大于领取执照时间，请重新输入")
			},
			"location.manager":{
				exculdeParticalChar:"负责人只能输入字母，数字和中文字符",
				minlength:$.format("负责人至少需要输入{0}个字符"),
				maxlength:$.format("负责人最多只能输入{0}个字符")
			},
			"location.registrationNumber":{
				positiveInteger:$.format("备案登记号码只能输入正整数"),
				maxlength:$.format("备案登记号码最多只能输入{0}个字符")
			},
			"location.totalArea":{
				posNumWiPot:$.format("总面积只能输入正数，且小数点最多精确到两位"),
				maxlength:$.format("总面积最多只能输入{0}个字符")
			},
			"location.operationArea":{
				posNumWiPot:$.format("营业面积只能输入正数，且小数点最多精确到两位"),
				maxlength:$.format("营业积最多只能输入{0}个字符")
			},
			"location.vouchedPeopleCount":{
				positiveInteger:$.format("核定人数只能输入正整数"),
				maxlength:$.format("核定人数最多只能输入{0}个字符")
			},
			"location.privateRoomCount":{
				positiveInteger:$.format("包间数只能输入正整数"),
				maxlength:$.format("包间数最多只能输入{0}个字符")
			},
			"location.surrounding":{
				maxlength:$.format("周围环境最多只能输入{0}个字符")
			},
			"location.remark":{
				maxlength:$.format("备注最多只能输入{0}个字符")
			}
		}
	});
	});
</script>