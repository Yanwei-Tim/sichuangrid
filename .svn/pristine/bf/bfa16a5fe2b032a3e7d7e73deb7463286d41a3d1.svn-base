<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>

<div class="container container_24" >
	<div id="internetBar" class="container container_24">
			<input type="hidden" name="mode" id="mode" value="${(mode)!}" />
			<input type="hidden" name="location.id" id="locationId" value="${(location.id)!}" />
			<input id="organizationId2"	type="hidden" name="organizationId" value="${(location.organization.id )!}" />
			<input id="organizationId"	type="hidden" name="location.organization.id" value="${(location.organization.id )!}" />
			<input id="ajaxUrl" type="hidden" name="ajaxUrl" value="<@s.property value="@com.tianque.plugin.dataManage.util.BaseInfoActionDefine@getAction(ajaxUrl)"/>" />
			<@s.if test="#parameters.fromlocationType[0]">
				<span style="font-weight: bold; margin-left:20px; ">网吧</span>
				<div class='clearLine'>&nbsp;</div>
			</@s.if>
			<div class="grid_4 lable-right">
			   	<em class="form-req">*</em>
	   			<label class="form-lb1">所属网格：</label>
   			</div>
   			<div class="grid_20">
   				<input type="text"  id="orgName"  name="location.organization.orgName"  readonly value="${(location.organization.orgName) ! }" style="width: 99%"	class="form-txt" />
   			</div>
   			<div class='clearLine'>&nbsp;</div>
   			<div class="grid_4 lable-right">
			   	<em class="form-req">*</em>
	   			<label class="form-lb1">场所名称：</label>
   			</div>
   			<div class="grid_20">
   				<input type="text"  id="name"  name="location.name"   value="${(location.name)!}" style="width: 99%"  maxlength="50" title="场所名称可以输入字母、数字、和中文"
   				class="form-txt"  />
   			</div>
   			<div class='clearLine'>&nbsp;</div>
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
	   		<div class='clearLine'>&nbsp;</div>
   			<div class="grid_4 lable-right">
   				<label class="form-lb1">联系电话：</label>
	   		</div>
	   		<div class="grid_8">
	   			<input type="text" name="location.mobile" id="mobile" maxlength="12"  value="${(location.mobile ) ! }" title="请输入负责人联系方式、如:11111111111"
	   			class='form-txt {telephone:true,messages:{telephone:$.format("手机号码或者固定电话，只能输数字或数字和横杠(-)")}}'
	   			 />
	   		</div>
   			<div class="grid_4 lable-right">
   				<label class="form-lb1">网吧编号：</label>
	   		</div>
	   		<div class="grid_8">
	   			<input type="text" name="location.barCode" id="barCode" maxlength="20"  value="${(location.barCode ) ! }" title="请输入网吧编号" class=" form-txt" />
	   		</div>
	   		<div class='clearLine'>&nbsp;</div>
	   		<div class="grid_4 lable-right">
	   			<label class="form-lb1">场所地址：</label>
   			</div>
   			<div class="grid_20">
   				<input type="text"  id="address"  name="location.address"   value="${(location.address) ! }" style="width: 99%"  maxlength="50" title="场所地址可以输入由字母、数字、和中文"	class="form-txt" />
   			</div>
			<div class='clearLine'>&nbsp;</div>
   				<div class="grid_5 lable-right">
	   			<label class="form-lb1">宽带接入服务商：</label>
   			</div>
   			<div class="grid_19">
   				<input type="text"  id="netAccessProviders"  name="location.netAccessProviders"   value="${(location.netAccessProviders) ! }" style="width: 99%"  maxlength="50" title="请输入宽带接入服务商的名称"
   				class='form-txt {exculdeParticalChar:true,messages:{exculdeParticalChar:"不能输入非法字符"}}' />
   			</div>
   			<div class='clearLine'>&nbsp;</div>
   			<div class="grid_5 lable-right">
	   			<label class="form-lb1">现使用IP：</label>
   			</div>
   			<div class="grid_19">
   				<input type="text"  id="useIP"  name="location.useIP"   value="${(location.useIP) ! }" style="width: 99%"  maxlength="50" title="请输入目前在使用的ip地址，多个ip用逗号','隔开"
   				class='form-txt {exculdeParticalChar:true,messages:{exculdeParticalChar:"不能输入非法字符"}}' />
   			</div>
   			<div class='clearLine'>&nbsp;</div>
	   		<div class="grid_4 lable-right">
   				<label class="form-lb1">接入方法：</label>
	   		</div>
	   		<div class="grid_6">
	   			<input type="text" name="location.internetAccessType" id="internetAccessType" maxlength="20"  value="${(location.internetAccessType ) ! }" title="请输入接入方式" class=" form-txt" />
	   		</div>
   			<div class="grid_6 lable-right">
   				<label class="form-lb1">网络文化经营许可证号：</label>
	   		</div>
	   		<div class="grid_8">
	   			<input type="text" name="location.netCultureLicenceNo" id="netCultureLicenceNo" maxlength="20"  value="${(location.netCultureLicenceNo ) ! }" title="请输入网络文化经营许可证号" class=" form-txt" />
	   		</div>
	   		<div class='clearLine'>&nbsp;</div>
	   		<div class="grid_4 lable-right">
   				<label class="form-lb1">电脑台数：</label>
	   		</div>
   			
   		
	   		<div class="grid_6">
	   			<input type="text" name="location.totalComputerNum" id="totalComputerNum" maxlength="10"  value="${(location.totalComputerNum ) ! }" title="请输入电脑台数" class=" form-txt" />
	   		</div>
   			<div class="grid_6 lable-right">
   				<label class="form-lb1">所在地派出所名称：</label>
	   		</div>
	   		<div class="grid_8">
	   			<input type="text" name="location.stationPolice" id="stationPolice" maxlength="20"  value="${(location.stationPolice ) ! }" title="请输入所在地派出所名称"
	   			class='form-txt {exculdeParticalChar:true,messages:{exculdeParticalChar:"单位名称不能输入非法字符"}}'/>
	   		</div>
	   		<div class='clearLine'>&nbsp;</div>

	   		<div class="grid_4 lable-right">
   				<label class="form-lb1">营业面积：</label>
	   		</div>
	   		<div class="grid_5">
	   		<input type="text" name="location.operationArea" id="operationArea" maxlength="10"  value="${(location.operationArea ) ! }" title="请输入营业面积"
				class="form-txt {decimal:true,range:[1,9999999.99],messages:{decimal:'请输入非负数 ，保留两位位小数点',range:'营业面积只能输入1到9999999.99之间的数'}}" />
	   		</div><div class="grid_1">
	 		  <label class="form-lbl">&nbsp;m<sup>2</sup></label>
			</div>
   			<div class="grid_6 lable-right">
   				<label class="form-lb1">网络安全审核意见书号：</label>
	   		</div>
	   		<div class="grid_8">
	   			<input type="text" name="location.netSecurityAuditNo" id="netSecurityAuditNo" maxlength="20"  value="${(location.netSecurityAuditNo ) ! }" title="请输入网络安全审核意见书号" class=" form-txt" />
	   		</div>
	   		<div class='clearLine'>&nbsp;</div>
	   		<div class="grid_4 lable-right">
   				<label class="form-lb1">临时上网卡数：</label>
	   		</div>
	   		<div class="grid_6">
	   			<input type="text" name="location.tempNetCardNum" id="tempNetCardNum" maxlength="10"  value="${(location.tempNetCardNum ) ! }" title="请输入临时上网卡数量" class=" form-txt" />
	   		</div>
   			<div class="grid_6 lable-right">
   				<label class="form-lb1">消防审核意见书号：</label>
	   		</div>
	   		<div class="grid_8">
	   			<input type="text" name="location.fireAuditDocumentNo" id="fireAuditDocumentNo" maxlength="20"  value="${(location.fireAuditDocumentNo ) ! }" title="请输入消防审核审核意见书号" class=" form-txt" />
	   		</div>
	   		<div class='clearLine'>&nbsp;</div>
			<div class="grid_4 lable-right">
   				<label class="form-lb1">备注：</label>
	   		</div>
	   		<div class="grid_20">
   				<textarea rows="4" maxlength="200"  name="location.remark" id="remark" class="form-txt" style="width: 99%">${(location.remark ) ! }</textarea>
   			</div>	
   			
  	</div>
</div>
<script type="text/javascript">
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
			"location.tempNetCardNum":{
				positiveInteger:true
			},
			"location.totalComputerNum":{
				positiveInteger:true
			},
			"location.remark":{
				maxlength:200
			}
		},
		messages:{
			"location.name":{
				required:"请输入网吧名称",
				exsistedName:"网吧名称已存在，请重新输入",
				exculdeParticalChar:"网吧名称只能输入字母，数字和中文字符",
				minlength:$.format("网吧名称至少需要输入{0}个字符"),
				maxlength:$.format("网吧名称最多只能输入{0}个字符")
			},
			"location.address":{
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

</script>
