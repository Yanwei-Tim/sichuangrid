<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp" />
<style type="text/css">
.container_24{position:relative;}
.redDetail{position:absolute;left:43%;top:55px;width:505px;}
.redDetail .redBox{width:238px;padding:0 5px;border:1px dashed #C9C9C9;background:#F1F1F1; }
.redDetail .title{font-size:17px;font-weight:bold;letter-spacing:15px;text-align:center;padding-bottom:10px;}
.redDetail .redBox .redHd{line-height:24px;}
.redDetail .redBox .redCont{border:1px solid #999;border-radius:5px;background:#fff;margin-bottom:5px;}
.redDetail .redBox .redCont .hd{height:7px;border-bottom:1px solid #999;border-radius:0 0 3px 3px;background:#C7E5FF;margin:0;}
.redDetail .redBox .redCont div{line-height:24px;margin:0 7px;}
.redDetail .redBox .redCont .infor{font-weight:bold;}
.redDetail .redBox .redCont .date{color:#9FA9C2}
.redDetail .redBox .redCont .inforDet{line-height:16px;}
.redDetail .redBox .redCont .inforDet span{color:#049900}
.redDetail .redBox .redCont .line,.redDetail .redBox .redCont .remark,.redDetail .redBox .redCont .wishWords{border-bottom:1px solid #999;color:#9FA9C2;line-height:30px;width:90%;}
.redDetail .redBox .redCont .more a{color:#9FA9C2}
</style>
<div class="container_24">
	<form id="redEnvelopeForm" method="post" action="${path}/redEnvelopeManage/updateRedEnvelope.action">
	<@pop.token />
	<input type="hidden" name="redEnvelope.id" value="${(redEnvelope.id)!''}">
	<div class="grid_3 lable-right">
		<label>选择红包类型：</label>
	</div>
	<div style="clear:both"></div>
	<div class="grid_4 lable-right">
		<em class="form-req">*</em>
		<label>红包类型：</label>
	</div>
	<div class="grid_19">
		<input type="radio" name="redEnvelope.redEnvelopeType" disabled value="<@s.property value='@com.tianque.plugin.weChat.constant.RedEnvelopeConstant@RANDOMENVELOPE'/>" >随机金额红包
		<input type="radio" name="redEnvelope.redEnvelopeType" value="<@s.property value='@com.tianque.plugin.weChat.constant.RedEnvelopeConstant@SINGLEENVELOPE'/>" checked="checked" >固定金额红包
		<em class="form-req">注:(红包发放接口的限制,暂时只支持固定红包)</em>
	</div>
	<#-- 
	<div style="clear:both"></div>	
	<div class="grid_4 lable-right randomEnvelope">
		<em class="form-req">*</em>
		<label>最小金额：</label>
	</div>
	<div class="grid_6 randomEnvelope">
		<input type="text"  name="redEnvelope.min_value" value="${(redEnvelope.min_value)!''}"  class='form-txt {required:true,messages:{required:"请输入最小金额"}}' />
	</div>
	<div class="grid_6 randomEnvelope">元</div>
	<div style="clear:both"></div>	
	<div class="grid_4 lable-right randomEnvelope">
		<em class="form-req">*</em>
		<label>最大金额：</label>
	</div>
	<div class="grid_6 randomEnvelope">
		<input type="text"  name="redEnvelope.max_value" value="${(redEnvelope.max_value)!''}" class='form-txt {required:true,messages:{required:"请输入最大金额"}}' />
	</div>
	<div class="grid_5 randomEnvelope">元</div>
	-->
	<div style="clear:both"></div>	
	<div class="grid_4 lable-right singleEnvelope">
		<em class="form-req">*</em>
		<label>单个红包金额：</label>
	</div>
	<div class="grid_6 singleEnvelope">
		<input type="text" id="singleEnvelope_value_id" maxlength="10" value="${(redEnvelope.singleEnvelope_value)!''}" class="form-txt {required:true,decimal:true,range:[1.00,200],messages:{required:'请输入单个红包金额',decimal:'请输入非负数 ，最多保留两位小数点',range:'金额只能输入1到200之间的数'}}" />
		<input type="hidden" id="singleEnvelope_value_tempId"  name="redEnvelope.singleEnvelope_value" />
	</div>
	<div class="grid_5 singleEnvelope">元</div>
	<div style="clear:both"></div>	
	<div class="grid_3 lable-right">
		<label>配置红包消息：</label>
	</div>
	<div style="clear:both"></div>	
	<div class="grid_4 lable-right">
		<em class="form-req">*</em>
		<label>活动名称：</label>
	</div>
	<div class="grid_6">
		<input type="text" id="redEnvelope_act_name" name="redEnvelope.act_name" value="${(redEnvelope.act_name)!''}" maxlength="10" class='form-txt {required:true,messages:{required:"请输入活动名称"}}' />
	</div>
	<div style="clear:both"></div>	
	<div class="grid_4 lable-right">
		<em class="form-req">*</em>
		<label>商户名称：</label>
	</div>
	<div class="grid_6">
		<input type="hidden"  name="redEnvelope.nick_name" id="nick_name_id"  />
		<input type="text"  name="redEnvelope.send_name" id="send_name_id" value="${(redEnvelope.send_name)!''}" maxlength="10" class='form-txt {required:true,messages:{required:"请输入商户名称"}}' />
	</div>
	<div style="clear:both"></div>
	<div class="grid_4 lable-right">
		<em class="form-req">*</em>
		<label>备注：</label>
	</div>
	<div class="grid_6">
		<input type="text" id="remark" name="redEnvelope.remark" value="${(redEnvelope.remark)!''}" maxlength="15" class='form-txt {required:true,messages:{required:"请输入备注"}}' />
	</div>
	<div style="clear:both"></div>
	<div class="grid_4 lable-right">
		<em class="form-req">*</em>
		<label>祝福语：</label>
	</div>
	<div class="grid_6">
		<input type="text" id="wishWords" name="redEnvelope.wishing" value="${(redEnvelope.wishing)!''}" maxlength="20" class='form-txt {required:true,messages:{required:"请输入祝福语"}}' />
	</div>
	<div style="clear:both"></div>
	<div class="grid_3 lable-right">
		<label>商户平台|微信支付：</label>
	</div>
	<div style="clear:both"></div>
	<div class="grid_4 lable-right">
		<em class="form-req">*</em>
		<label>微信支付商户号：</label>
	</div>
	<div class="grid_6">
		<input type="text"  name="redEnvelope.mch_id" value="${(redEnvelope.mch_id)!''}" maxlength="15" class='form-txt {required:true,messages:{required:"请输入微信支付商户号"}}' />
	</div>
	<em class="form-req">注:请准确填写该项,否则红包将无法发放</em>
	<div style="clear:both"></div>
	<div class="grid_4 lable-right">
		<em class="form-req">*</em>
		<label>微信支付商户号API密钥：</label>
	</div>
	<div class="grid_6">
		<input type="text"  name="redEnvelope.apiKey" value="${(redEnvelope.apiKey)!''}" maxlength="32" class='form-txt {required:true,messages:{required:"请输入微信支付商户号API密钥"}}' />
	</div>
	<em class="form-req">注:请准确填写微信支付商户号API密钥,否则无法调用发放红包接口</em>
	<div style="clear:both"></div>
	<div class="grid_4 lable-right">
		<em class="form-req">*</em>
		<label>微信公众平台appID：</label>
	</div>
	<div class="grid_6">
		<input type="text" id="redEnvelopeWxappid_Id" value="${(redEnvelope.wxappid)!''}" readonly  name="redEnvelope.wxappid" maxlength="20" class='form-txt {required:true,messages:{required:"请选择微信公众平台appID"}}' />
		<input type="hidden" id="weChatUserId_Id"  name="redEnvelope.weChatUserId" value="${(redEnvelope.weChatUserId)!''}" />
	</div>
	<div class="grid_2">
		<input type="button" class="defaultBtn" value="选择" id="choiceWxappid_Id" />
	</div>
	<em class="form-req">注:选择准确的微信公众平台appID</em>
	<div style="clear:both"></div>
		<div class="redDetail" id="redDetail">
		<div class="title">红包预览</div>
		<div class="cf">
			<div class="redBox fl">
				<div class="redHd">用户收到后的消息</div>
				<div class="redCont">
					<div class="hd"></div>
					<div class="infor">你收到了一个红包</div>
					<div class="date">xx月xx日</div>
					<div class="inforDet">你参加<span class="act">${(redEnvelope.act_name)!''}</span>，成功获得<span class="com">${(redEnvelope.send_name)!''}</span>赠送的红包。</div>
					<div class="remark">${(redEnvelope.remark)!''}</div>
					<div class="more"><a href="javascript:;">详情</a></div>
				</div>
			</div>
			<div class="redBox fr">
				<div class="redHd">用户拆开红包后的消息</div>
				<div class="redCont">
					<div class="hd"></div>
					<div class="infor">你领取了一个红包</div>
					<div class="date">xx月xx日</div>
					<div class="inforDet">你成功领取了<span class="com">${(redEnvelope.send_name)!''}</span>发的红包。<br/>红包金额：<label class="price"></label>元</div>
					<div class="wishWords">${(redEnvelope.wishing)!''}</div>
					<div class="more"><a href="javascript:;">详情</a></div>
				</div>
			</div>
		</div>
	</div>
	</form>	
</div>
<div id="redEnvelopeDialog"></div>
<script type="text/javascript">
//首次加载默认是固定红包
$(".randomEnvelope").hide();
singleEnvelopeFormatter();
//金额格式化
function singleEnvelopeFormatter(){
	var val = $("#singleEnvelope_value_id").val();
	if(val != '' && !isNaN(val)){
		$("#singleEnvelope_value_id").val(val/100);
	}
}
	//红包类型切换
	function redEnvelopeTypeChange(){
		var type = $('input:radio[name="redEnvelopeType"]:checked').val();
		if(type==<@s.property value='@com.tianque.plugin.weChat.constant.RedEnvelopeConstant@SINGLEENVELOPE'/>){
			$(".randomEnvelope").hide();
			$(".singleEnvelope").show();
		}else{
			$(".singleEnvelope").hide();
			$(".randomEnvelope").show();
		}
	}
	//选择发送红包的公众账号-对应的微信公众平台appID
	$("#choiceWxappid_Id").click(function(){ 
		$("#redEnvelopeDialog").createDialog({
			width: 820,
			height: 550,
			title:'选择微信公众平台appID',
			url:'${path}/hotModuel/redEnvelopeManagement/searchTencentUserDlg.ftl',
			buttons: {			
			   "确定" : function(){
					fillWxappid();
			   },
			   "关闭" : function(){
					$(this).dialog("close");
			   }
			}
		});
	});
	
	//提交表单-保存
	function submitRedEnvelopeForm(){
		if(${USER_ORG_ID!} != $("#currentOrgId").val()){
			$.messageBox({message:"只能对本级部门创建红包!",level:"warn"});
			return;
		}
		//商户名称和提供方名称，默认一致
		var send_name_val = $("#send_name_id").val();
		$("#nick_name_id").val(send_name_val);
		//将金额转化为单位为分的数值，为了满足微信接口
		var c = 0;
		var val = $("#singleEnvelope_value_id").val();
		if(val != '' && !isNaN(val)){
			c = Math.round((c + (val == '' || isNaN(val) ? 0 : parseFloat(val)))*100); 
			$("#singleEnvelope_value_tempId").val(c);
		}
		$("#redEnvelopeForm").submit();
	}
	
	//表单验证
	$("#redEnvelopeForm").formValidate({
		submitHandler: function(form) {
			$(form).ajaxSubmit({
         		success: function(data){
         			if(data != null && data.id){
						$.messageBox({message:"修改成功"});	
						$("#administrationRedEnvelopeList").trigger("reloadGrid");					
					} else {
						$.messageBox({message:data,level:"error"});
					}
					$("#administrationRedEnvelopeDialog").dialog("close");
	  	   		}
      	  	});
		},
		rules:{
		},
		messages:{
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
	$(function(){
		var getRedInfor = function(){
			var price = $('#singleEnvelope_value_id').val();
			var act = $('#redEnvelope_act_name').val();
			var com = $('#send_name_id').val();
			var remark = $('#remark').val();
			var wishWords = $('#wishWords').val();
			$('.price').text(price);
			$('.act').text(act);
			$('.com').text(com);
			$('.remark').text(remark).addClass('line');
			$('.wishWords').text(wishWords).addClass('line');
		}
		$('input').keyup(function(){
			getRedInfor();
		})
	})
</script>
