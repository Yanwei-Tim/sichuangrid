<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>

<style>
#tabAInfo table{border-left:1px solid #CCC; border-top:1px solid #CCC; border-collapse:collapse;}
#tabAInfo td{text-align: center;vertical-align:middle;border-right:1px solid #ccc; border-bottom:1px solid #ccc; border-collapse:collapse; word-break:break-all; word-warp:break-word;}
#tabAInfo .ttd1{width: 120px;}
#tabAInfo .ttd2{width: 80px;}
#tabAInfo .td1{width: 120px;}
#tabAInfo .td2{width: 80px;}

#tabAInfo .smsSelectDelete{color:red}
#tabAInfo .smsSelectDelete:hover{color:blue}
</style>

<div class="container container_24">
		<input type="hidden" id="selectType" value="1"/>
		<div id="tabs">
		<ul>
			<li id="li_tabAInfo"><a id="a_tabA" href="#tabAInfo">手机号码</a></li>
			<li id="li_tabBInfo" ><a id="a_tabB" href="${path}/smsUplinkManage/dispatchOperate.action?mode=selectAddressee">通讯录</a></li>
			<li id="li_tabCInfo" ><a id="a_tabC" href="${path}/smsUplinkManage/dispatchOperate.action?mode=sendObjects">复杂对象</a></li>
		</ul>
		
		<div id="tabAInfo" class="container container_24" style="width:99.7%;height:340px;overflow: auto;" >
			
			<div id="templateDiv"  class="inputDiv" style="display:none" >
				<div class="grid_7 lable-right" >
					<label>手机号码：</label>
				</div>
				<div class="grid_15">
				     <input type="text" id="i" onblur="validateInput(this);" maxlength="11" class="form-txt inputMobile"/>
				</div>
				<div class="grid_1">
				     <label class="click"><a href="javascript:;" style="font-size:15px;font-weight:bold" >+</a></label>
				</div>
				<div class='clearLine'>&nbsp;</div>
			</div>
			<div id="inputDiv" class="grid_12">
				<div class="container container_24">
					<div class="inputDiv" >
						<div class="grid_7 lable-right" >
							<label>手机号码：</label>
						</div>
						<div class="grid_15">
						     <input type="text" id="i_1" onblur="validateInput(this);" maxlength="11" class="form-txt inputMobile" />
						</div>
						<div class='clearLine'>&nbsp;</div>
					</div>
					
					<div class="inputDiv" >
						<div class="grid_7 lable-right" >
							<label>手机号码：</label>
						</div>
						<div class="grid_15">
						     <input type="text" id="i_2" onblur="validateInput(this);" maxlength="11" class="form-txt inputMobile" />
						</div>
						<div class='clearLine'>&nbsp;</div>
					</div>
					
					<div class="inputDiv" >
						<div class="grid_7 lable-right" >
							<label>手机号码：</label>
						</div>
						<div class="grid_15">
						     <input type="text" id="i_3" onblur="validateInput(this);" maxlength="11" class="form-txt inputMobile" />
						</div>
						<div class='clearLine'>&nbsp;</div>
					</div>
					
					<div class="inputDiv" >
						<div class="grid_7 lable-right" >
							<label>手机号码：</label>
						</div>
						<div class="grid_15">
						     <input type="text" id="i_4" onblur="validateInput(this);" maxlength="11" class="form-txt inputMobile" />
						</div>
						<div class='clearLine'>&nbsp;</div>
					</div>
					
					<div class="inputDiv" >
						<div class="grid_7 lable-right" >
							<label>手机号码：</label>
						</div>
						<div class="grid_15">
						     <input type="text" id="i_5" onblur="validateInput(this);" maxlength="11" class="form-txt inputMobile" />
						</div>
						<div class='clearLine'>&nbsp;</div>
					</div>
					
					<div class="inputDiv" >
						<div class="grid_7 lable-right" >
							<label>手机号码：</label>
						</div>
						<div class="grid_15">
						     <input type="text" id="i_6" onblur="validateInput(this);" maxlength="11" class="form-txt inputMobile" />
						</div>
						<div class='clearLine'>&nbsp;</div>
					</div>
					
					<div class="inputDiv" >
						<div class="grid_7 lable-right" >
							<label>手机号码：</label>
						</div>
						<div class="grid_15">
						     <input type="text" id="i_7" onblur="validateInput(this);" maxlength="11" class="form-txt inputMobile" />
						</div>
						<div class='clearLine'>&nbsp;</div>
					</div>
					
					<div class="inputDiv" >
						<div class="grid_7 lable-right" >
							<label>手机号码：</label>
						</div>
						<div class="grid_15">
						     <input type="text" id="i_8" onblur="validateInput(this);" maxlength="11" class="form-txt inputMobile" />
						</div>
						<div class='clearLine'>&nbsp;</div>
					</div>
					
					<div class="inputDiv" >
						<div class="grid_7 lable-right" >
							<label>手机号码：</label>
						</div>
						<div class="grid_15">
						     <input type="text" id="i_9" onblur="validateInput(this);" maxlength="11" class="form-txt inputMobile" />
						</div>
						<div class="grid_1">
					     	<label><a href="javascript:;" id="addInput" style="font-size:15px;font-weight:bold">+</a></label>
						</div>
						<div class='clearLine'>&nbsp;</div>
					</div>
				</div>
			</div>
			
			<div class="grid_4" ></div>
			
			<div class="grid_8">
				<div id="tableDiv" style="margin-top:15px;height:280px;overflow: auto;"></div>
			</div>
			
		</div>
	</div>
</div>

<script type="text/javascript">

$(document).ready(function(){
var count = 9;

	$("#tabs").tabFunction({ cache: true });
	initAddresseeHtml();
	$("#addInput").click(function(){
		count = count+1
		$("#i").attr("id","i_"+count);
		addInputDiv($(this));
	});
	
	function addInputDiv(obj){
		obj.unbind().text("—").click(function(){
			$(this).parent().parent().parent().remove();
		});
		var div = $("#templateDiv").clone();
		div.removeAttr("id").css("display","").find("a").click(function(){
			addInputDiv($(this));
		});
		$("#inputDiv").append(div);
	}
	
	$("#a_tabA").click(function(){
		$("#selectType").val("1");
		initAddresseeHtml();
	});
	
	$("#a_tabB").click(function(){
		$("#selectType").val("2");
	});
	
	$("#a_tabC").click(function(){
		$("#selectType").val("3");
	});
	
});

function initAddresseeHtml(){
	var html='<table><tr><td class="ttd1" >已选手机号</td><td class="ttd2" >操作</td></tr>';
	var type = $("#sendObjectType").val(),selectValue="";
	if(type == 1){
		selectValue = $("#selectAddressee").val();
	}
	if(!selectValue){
		var arr = selectValue.split(",");
		for(var i=0;i< 6;i++){
			html += '<tr><td class="td1" >&nbsp;</td><td class="td2" >&nbsp;</td></tr>';
		}
		html += '</table>';
	}else{
		var arr = selectValue.split(",");
		for(var i=0;i< arr.length;i++){
			html += '<tr><td class="td1" >'+arr[i]+'</td><td class="td2" ><div><a href="javascript:;" class="smsSelectDelete" onclick="deleteMobileInput(this);"><span>删除</span></a></div></td></tr>';
		}
		for(i;i< 6;i++){
			html += '<tr><td class="td1" >&nbsp;</td><td class="td2" >&nbsp;</td></tr>';
		}
		html += '</table>';
	}
	$("#tableDiv").html(html);
}

function validateInput(obj){
	if(!obj.value){
		return ;
	}
	var length = obj.value.length;
	var mobile = /^((1[0-9]{2})+\d{8})$/;
	var bol =  (length == 11 && mobile.test(obj.value));
	if(!bol){
		alert("手机号码只能是1开头的11位数字");
		$(obj).focus();
	}
	for(var i=0;i<$('div.grid_15').length;i++){
		if(obj.id != $('div.grid_15:eq('+i+') input').attr("id") && obj.value == $('div.grid_15:eq('+i+') input').val()){
			$.messageBox({level:'warn',message:"当前输入号码重复"});
			obj.select();
			obj.focus();
			return;
		}
	}
}

function deleteMobileInput(obj){
	$(obj).parent().parent().parent().html('<td class="td1" >&nbsp;</td><td class="td2" >&nbsp;</td>');
}

</script>


