<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<jsp:include page="/includes/baseInclude.jsp" />

<div class="container container_24" >
	<div id="publicPlace" class="container container_24">
		<form id="maintainForm" method="post"action="${path}/baseinfo/internetBarManage/saveInternetBar.action"  >
			<div id="perUuid"></div>
			<s:if test='"add".equals(mode)'>
				<pop:token/>
			</s:if>
			<input type="hidden" name="mode" id="mode" value="${mode}" />
			<input type="hidden" name="modeType" id="modeType" value="${modeType}" />
			<input type="hidden" name="location.id" id="locationId" value="${location.id}" />
			<input type="hidden" name="location.gisInfo.buildingId" id="houseInfoBuildingId" value="${location.gisInfo.buildingId}">
			<input type="hidden" name="location.gisInfo.centerX" id="houseInfoCenterX" value="${location.gisInfo.centerX}">
			<input type="hidden" name="location.gisInfo.centerY" id="houseInfoCenterY" value="${location.gisInfo.centerY}">
			<input type="hidden" name="location.placeId" id="placeId" value="${location.placeId}"/>
			<input id="organizationId"	type="hidden" name="location.organization.id" value="${location.organization.id }" />
			<input id="ajaxUrl" type="hidden" name="ajaxUrl" value="<s:property value="@com.tianque.controller.BaseInfoActionDefine@getAction(ajaxUrl)"/>" />
			<input id="_imgUrl" name="location.imgUrl" type="hidden" value="${location.imgUrl}"/>
			<s:if test="#parameters.fromlocationType[0]">
				<span style="font-weight: bold; margin-left:20px; ">网吧</span>
				<div class='clearLine'>&nbsp;</div>
			</s:if>
			<div class="grid_4 lable-right">
			   	<em class="form-req">*</em>
	   			<label class="form-lb1">所属网格：</label>
   			</div>
   			<div class="grid_20">
   				<input type="text"  id="orgName"  name="location.organization.orgName"  readonly value="${location.organization.orgName}" style="width: 99%"	class="form-txt" />
   			</div>
   			<div class='clearLine'>&nbsp;</div>
   			<div class="grid_4 lable-right">
			   	<em class="form-req">*</em>
	   			<label class="form-lb1">场所名称：</label>
   			</div>
   			<div class="grid_20">
   				<input type="text"  id="placeName"  name="location.placeName"   value="${location.placeName}" style="width: 99%"  maxlength="50" title="场所名称可以输入字母、数字、和中文"
   				class="form-txt " />
   			</div>
   			<div class='clearLine'>&nbsp;</div>
   			<div class="grid_4 lable-right">
				<label class="form-lbl">关注程度：</label>
			</div>
			<div class="grid_8">
				<select name="location.attentionExtent.id" id="location-attentionExtent" class="form-txt"
					<s:if test='"view".equals(mode)'>disabled='true'</s:if> >
					<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@ATTENTION_EXTENT" defaultValue="${location.attentionExtent.id}" />
				</select>
			</div>
   			<div class="grid_4 lable-right">
   				<label class="form-lb1">负责人：</label>
	   		</div>
	   		<div class="grid_8">
	   			<input type="text" name="location.manager" id="manager" maxlength="20"  value="${location.manager }" title="请输入负责人名字、如:张三" class="form-txt"/>
	   		</div>
	   		<div class='clearLine'>&nbsp;</div>
   			<div class="grid_4 lable-right">
   				<label class="form-lb1">联系电话：</label><!-- 此处还没改 -->
	   		</div>
	   		<div class="grid_8">
	   			<input type="text" name="location.mobile" id="mobile" maxlength="12"  value="${location.mobile }" title="请输入负责人联系方式、如:11111111111"
	   			class='form-txt {telephone:true,messages:{telephone:$.format("手机号码或者固定电话，只能输数字或数字和横杠(-)")}}'
	   			 />
	   		</div>
   			<div class="grid_4 lable-right">
   				<label class="form-lb1">网吧编号：</label>
	   		</div>
	   		<div class="grid_8">
	   			<input type="text" name="location.barCode" id="barCode" maxlength="20"  value="${location.barCode }" title="请输入网吧编号" class=" form-txt" />
	   		</div>
	   		<div class='clearLine'>&nbsp;</div>
	   		<div class="grid_4 lable-right">
	   			<label class="form-lb1">场所地址：</label>
   			</div>
   			<div class="grid_20">
   				<input type="text"  id="placeAddress"  name="location.placeAddress"   value="${location.placeAddress}" style="width: 99%"  maxlength="50" title="场所地址可以输入由字母、数字、和中文"	class="form-txt" />
   			</div>
   			<div class='clearLine'>&nbsp;</div>
   			<div class="grid_5 lable-right">
	   			<label class="form-lb1">宽带接入服务商：</label>
   			</div>
   			<div class="grid_19">
   				<input type="text"  id="netAccessProviders"  name="location.netAccessProviders"   value="${location.netAccessProviders}" style="width: 99%"  maxlength="50" title="请输入宽带接入服务商的名称"
   				class='form-txt {exculdeParticalChar:true,messages:{exculdeParticalChar:"不能输入非法字符"}}' />
   			</div>
   			<div class='clearLine'>&nbsp;</div>
   			<div class="grid_5 lable-right">
	   			<label class="form-lb1">现使用IP：</label>
   			</div>
   			<div class="grid_19">
   				<input type="text"  id="useIP"  name="location.useIP"   value="${location.useIP}" style="width: 99%"  maxlength="50" title="请输入目前在使用的ip地址，多个ip用逗号','隔开"
   				class='form-txt {ip:true,messages:{ip:"请输入目前在使用的ip地址，多个ip用逗号‘,’隔开"}}' />
   			</div>
   			<div class='clearLine'>&nbsp;</div>
	   		<div class="grid_4 lable-right">
   				<label class="form-lb1">接入方法：</label>
	   		</div>
	   		<div class="grid_6">
	   			<input type="text" name="location.internetAccessType" id="internetAccessType" maxlength="20"  value="${location.internetAccessType }" title="请输入接入方式" class=" form-txt" />
	   		</div>
   			<div class="grid_6 lable-right">
   				<label class="form-lb1">网络文化经营许可证号：</label>
	   		</div>
	   		<div class="grid_8">
	   			<input type="text" name="location.netCultureLicenceNo" id="netCultureLicenceNo" maxlength="20"  value="${location.netCultureLicenceNo }" title="请输入网络文化经营许可证号" class=" form-txt" />
	   		</div>
	   		<div class='clearLine'>&nbsp;</div>
	   		<div class="grid_4 lable-right">
   				<label class="form-lb1">电脑台数：</label>
	   		</div>
	   		<div class="grid_6">
	   			<input type="text" name="location.totalComputerNum" id="totalComputerNum" maxlength="10"  value="${location.totalComputerNum }" title="请输入电脑台数" class=" form-txt" />
	   		</div>
   			<div class="grid_6 lable-right">
   				<label class="form-lb1">所在地派出所名称：</label>
	   		</div>
	   		<div class="grid_8">
	   			<input type="text" name="location.stationPolice" id="stationPolice" maxlength="20"  value="${location.stationPolice }" title="请输入所在地派出所名称"
	   			class='form-txt {exculdeParticalChar:true,messages:{exculdeParticalChar:"单位名称不能输入非法字符"}}'/>
	   		</div>
	   		<div class='clearLine'>&nbsp;</div>

	   		<div class="grid_4 lable-right">
   				<label class="form-lb1">营业面积：</label>
	   		</div>
	   		<div class="grid_5">
	   		<input type="text" name="location.operationArea" id="operationArea" maxlength="10"  value="${location.operationArea }" title="请输入营业面积"
				class="form-txt {decimal:true,range:[1,9999999.99],messages:{decimal:'请输入非负数 ，保留两位位小数点',range:'营业面积只能输入1到9999999.99之间的数'}}" />
	   		</div><div class="grid_1">
	 		  <label class="form-lbl">&nbsp;m<sup>2</sup></label>
			</div>
   			<div class="grid_6 lable-right">
   				<label class="form-lb1">网络安全审核意见书号：</label>
	   		</div>
	   		<div class="grid_8">
	   			<input type="text" name="location.netSecurityAuditNo" id="netSecurityAuditNo" maxlength="20"  value="${location.netSecurityAuditNo }" title="请输入网络安全审核意见书号" class=" form-txt" />
	   		</div>
	   		<div class='clearLine'>&nbsp;</div>
	   		<div class="grid_4 lable-right">
   				<label class="form-lb1">临时上网卡数：</label>
	   		</div>
	   		<div class="grid_6">
	   			<input type="text" name="location.tempNetCardNum" id="tempNetCardNum" maxlength="10"  value="${location.tempNetCardNum }" title="请输入临时上网卡数量" class=" form-txt" />
	   		</div>
   			<div class="grid_6 lable-right">
   				<label class="form-lb1">消防审核意见书号：</label>
	   		</div>
	   		<div class="grid_8">
	   			<input type="text" name="location.fireAuditDocumentNo" id="fireAuditDocumentNo" maxlength="20"  value="${location.fireAuditDocumentNo }" title="请输入消防审核审核意见书号" class=" form-txt" />
	   		</div>
	   		<div class='clearLine'>&nbsp;</div>
			<div class="grid_4 lable-right">
   				<label class="form-lb1">备注：</label>
	   		</div>
	   		<div class="grid_20">
   				<textarea rows="4"   name="location.remark" id="remark" class="form-txt" style="width: 99%">${location.remark }</textarea>
   			</div>
   		    <input name="isSubmit" id="isSubmit" type="hidden" value="" />
		</form>
  	</div>
</div>
<script type="text/javascript">

<s:if test='"add".equals(mode)'>
$("#organizationId").val($("#currentOrgId").val());
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
if(null!=$("#_imgUrl").val() && $("#_imgUrl").val()!=""){
	$("#headerImg").attr("src",$("#_imgUrl").val()+"?r="+Math.random());
	$(".shadow").show();
}
if(""!=$("#_imgUrl").val() && typeof($("#_imgUrl").val())!="undefined"){
	$("#img").attr("src","${path }/"+$("#_imgUrl").val());
};
$(document).ready(function(){
	// 根据房屋编号自动获取实有房屋信息
	function searchHoseInfoByCode(){
		$.ajax({
			async: false ,
			url:"${path}/baseinfo/actualHouseManage/getHouseInfoByHouseCode.action",
		   	data:{
				"searchHouseInfoVo.houseCode":$('#houseCode').val()
	        },
			success:function(data){
				if(null != data) {
					procCurrentAddressType(data);
				} else {
					$("#placeAddress").val("");
				}
			}
		});
	}
	function procCurrentAddressType(data) {
		$("#placeId").val(data.id);
		if(data.addressType.id==741) {
			$("#placeAddress").val(data.community+"小区"+data.block+"幢"+data.unit+"单元"+data.room+"室");
		} else {
			$("#placeAddress").val(data.address);
		}

	}
	$("#houseCode").change(searchHoseInfoByCode);

	$("#houseCode").houseAutoComplete({
		searchLevel:"houseCode",
		select:function(event,ui){
			renderHouseInfoForHouseCode(ui.item);
		}
	});

	function renderHouseInfoForHouseCode(house){
		$("#placeId").val(house.houseId);
		procCurrentAddressType(house);
		$("#houseCode").val(house.houseCode);
	}

	$("#maintainForm").formValidate({
		submitHandler: function(form) {
			$("#_imgUrl").val($("#imgUrl").val());

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
                     $("#<s:property value='#parameters.dailogName[0]'/>").proccessSuccess(data.id,data);
      	   },
      	   error: function(XMLHttpRequest, textStatus, errorThrown){
      	      alert("提交错误");
      	   }
      	  });
	},
		rules:{
			"location.placeName":{
				required:true,
				exsistedInternetBarName:true,
				exculdeParticalChar:true,
				minlength:2,
				maxlength:50
			},
			"location.placeAddress":{
				exculdeParticalChar:true,
				minlength:2,
				maxlength:50
			},
			"location.manager":{
				exculdeParticalChar:true,
				minlength:2,
				maxlength:20
			},
			"location.tempNetCardNum":{
				positiveInteger:true
			},
			"location.totalComputerNum":{
				positiveInteger:true
			},
			"location.remark":{
				maxlength:300
			}

		},
		messages:{
			"location.placeName":{
				required:"请输入网吧名称",
				exsistedInternetBarName:"网吧名称已存在，请重新输入",
				exculdeParticalChar:"网吧名称只能输入字母，数字和中文字符",
				minlength:$.format("网吧名称至少需要输入{0}个字符"),
				maxlength:$.format("网吧名称最多只能输入{0}个字符")
			},
			"location.placeAddress":{
				exculdeParticalChar:"网吧地址只能输入字母，数字和中文字符",
				minlength:$.format("网吧地址至少需要输入{0}个字符"),
				maxlength:$.format("网吧地址最多只能输入{0}个字符")
			},
			"location.manager":{
				exculdeParticalChar:"负责人只能输入字母，数字和中文字符",
				minlength:$.format("负责人至少需要输入{0}个字符"),
				maxlength:$.format("负责人最多只能输入{0}个字符")
			},
			"location.tempNetCardNum":{
				positiveInteger:$.format("临时上网卡数量只能输入正整数")
			},
			"location.totalComputerNum":{
				positiveInteger:$.format("电脑总数量只能输入正整数")
			},
			"location.remark":{
				maxlength:$.format("备注最多只能输入{0}个字符")
			}

		}
	});
});

jQuery.validator.addMethod("exsistedInternetBarName", function(value, element){
	var flag =true;
	if(value==null||value==undefined||value==""){return true}
	$.ajax({
		async: false ,
		url:$('#ajaxUrl').val(),
	   	data:{
			"location.placeName":$('#placeName').val(),
			"organizationId":$('#organizationId').val(),
			"mode":$('#mode').val(),
			"location.id":$('#locationId').val()
        },
		success:function(responseData){
			flag = !eval(responseData);
		}
	});
	return flag;
});

jQuery.validator.addMethod("ip", function(value, element) {
    var ipAddress = /^(\d{1,3}\.\d{1,3}\.\d{1,3}\.\d{1,3},?)+$/;
    // var ip = /^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/;
    if (value==""){
	     return true;
	 }
    if(value!=null && value!=""){
    	return ipAddress.test(value);
    }
    return false;
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

</script>