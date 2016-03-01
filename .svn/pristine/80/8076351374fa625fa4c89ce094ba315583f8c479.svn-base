<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %> 
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<jsp:include page="/includes/baseInclude.jsp" />

<div class="container container_24">
	<form id="maintainForm" method="post" action="${path}/baseinfo/villageProfile/updateOrAddVillageProfile.action?mode=baseVillageProfile">
		<input type="hidden" name="villageProfile.id" value="${villageProfile.id }" />
		<input type="hidden" name="villageProfile.organization.id" value="${villageProfile.organization.id}" />
		<div id="aa">
		   <input type="hidden" name="hide" id="hide" value="${villageProfile.gridNum}"/>
		</div>
		<div class="grid_6 lable-right">
			<label>责任区：</label>
		</div>
		<div class="grid_5" id="oogg">
			<label id="orgName">${villageProfile.organization.orgName}</label>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_6 lable-right">
			<label class="form-lbl">面积：</label>
		</div>
		<div class="grid_4">
			<input type="text" name="villageProfile.acreage" value="${villageProfile.acreageStr}" id="acreage" maxlength="9" class='form-txt {number:true,min:0,max:999999999,messages:{number: "面积输入数字",min: "面积需要输入正数",max: "面积最大输入999999999"}}' />
		</div>
		<div class="grid_1">㎡</div>
		<div class="grid_6 lable-right">
			<label class="form-lbl">户数：</label>
		</div>
		<div class="grid_4">
			<input type="text" name="villageProfile.doors" value="${villageProfile.doors}" id="doors" maxlength="9" class='form-txt {digits:true,min:0,max:999999999,messages:{digits: "户数输入正整数",min: "户数输入正数",max: "户数最大输入999999999"}}' />
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_6 lable-right">
			<label>网格居民数：</label>
		</div>
		<div class="grid_5">
			<input type="text" name="villageProfile.villagers" value="${villageProfile.villagers}" id="villagers" maxlength="9" class='form-txt {digits:true,min:0,max:999999999,messages:{digits: "网格人数输入正整数",min: "网格人数输入正数",max: "网格人数最大输入999999999"}}' />
		</div>
		<div class="grid_6 lable-right">
			<label>网格党员数：</label>
		</div>
		<div class="grid_5">
			<input type="text" name="villageProfile.villageRingsters" value="${villageProfile.villageRingsters}" id="villageRingsters" maxlength="9" class='form-txt {digits:true,min:0,max:999999999,villageRingstersLessthanVillagers:true,messages:{digits: "网格党员数输入正整数",min: "网格党员数输入正数",max: "网格党员数最大输入999999999",villageRingstersLessthanVillagers:"党员数不能大于村民数"}}' />
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_6 lable-right">
			<label>网格代表数：</label>
		</div>
		<div class="grid_5">
			<input type="text" name="villageProfile.villageDelegate" value="${villageProfile.villageDelegate}" id="villageDelegate" maxlength="9" class='form-txt {digits:true,min:0,max:999999999,villageDelegateLessthanVillagers:true,messages:{digits: "网格代表数输入正整数",min: "网格代表数输入正数",max: "网格代表数最大输入999999999",villageDelegateLessthanVillagers:"村民代表数不能大于村民数"}}' />
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_6 lable-right">
			<label>信息员A岗：</label>
		</div>
		<div class="grid_5">
			<input type="text" name="villageProfile.informationPersonA" value="${villageProfile.informationPersonA}" id="informationPersonA" maxlength="10" class='form-txt {minlength:2,maxlength:10,exculdeParticalChar:true,messages:{minlength:$.format("信息员A岗至少需要输入{0}个字符"),maxlength:$.format("信息员A岗最多需要输入{0}个字符"),exculdeParticalChar:"不能输入非法字符"}}' />
		</div>
		<div class="grid_6 lable-right">
			<label>联系电话：</label>
		</div>
		<div class="grid_5">
			<input type="text" name="villageProfile.informationPersonAPhone" value="${villageProfile.informationPersonAPhone}" id="informationPersonAPhone" maxlength="13" class='form-txt {telephone:true,messages:{telephone:"信息员A岗联系电话不合法，只能输数字和横杠(-)"}}' />
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_6 lable-right">
			<label>信息员B岗：</label>
		</div>
		<div class="grid_5">
			<input type="text" name="villageProfile.informationPersonB" value="${villageProfile.informationPersonB}" id="informationPersonB" maxlength="10" class='form-txt {minlength:2,maxlength:10,exculdeParticalChar:true,messages:{minlength:$.format("信息员B岗至少需要输入{0}个字符"),maxlength:$.format("信息员B岗最多需要输入{0}个字符"),exculdeParticalChar:"不能输入非法字符"}}' />
		</div>
		<div class="grid_6 lable-right">
			<label>联系电话：</label>
		</div>
		<div class="grid_5">
			<input type="text" name="villageProfile.informationPersonBPhone" value="${villageProfile.informationPersonBPhone}" id="informationPersonBPhone" maxlength="13" class='form-txt {telephone:true,messages:{telephone:$.format("信息员B岗联系电话不合法，只能输数字和横杠(-)")}}' />
		</div>
		<div class='clearLine'>&nbsp;</div>
	</form>
</div>

<script type="text/javascript">
$(document).ready(function(){
	if(!isGrid){
		if($('#hide').val()=="null"){
			$("#aa").html("<div class='grid_6 lable-right' id='orgNo'><label>网格数：</label></div></div><div class='grid_5'><input name='villageProfile.gridNum' value='' id='gridNum'  maxlength='9' class='form-txt' /></div>");
		}else{
			$("#aa").html("<div class='grid_6 lable-right' id='orgNo'><label>网格数：</label></div></div><div class='grid_5'><input name='villageProfile.gridNum' value='"+$('#hide').val()+"' id='gridNum'  maxlength='9' class='form-txt' /></div>");
		}
	}
	$("#maintainForm").formValidate({
		submitHandler: function(form) {
			$(form).ajaxSubmit({
				success: function(data){
					if(!data.id){
						$.messageBox({message:data,level: "error"});
						return;
					}else{
						$("#orgName_label").empty().append(data.organization.orgName);
						$("#acreage_label").empty().append(data.acreage);
						$("#gridNum_label").empty().append(data.gridNum );
						$("#doors_label").empty().append(data.doors );
						$("#villagers_label").empty().append(data.villagers);
						$("#villageRingsters_label").empty().append(data.villageRingsters );
						$("#villageDelegate_label").empty().append(data.villageDelegate );
						$("#hamletEconomyinclude_label").empty().append(data.hamletEconomyinclude);
						$("#individualAverageInclude_label").empty().append(data.individualAverageInclude );
						$("#villageCollectivityAsset_label").empty().append(data.villageCollectivityAsset );
						$("#interzoneLeading_label").empty().append(data.interzoneLeading );
						$("#department_label").empty().append(data.department );
						$("#villageBuildupSecretary_label").empty().append(data.villageBuildupSecretary);
						$("#buildupSecretaryPhone_label").empty().append(data.buildupSecretaryPhone );
						$("#villageDirector_label").empty().append(data.villageDirector );
						$("#villageDirectorPhone_label").empty().append(data.villageDirectorPhone);
						$("#informationPersonA_label").empty().append(data.informationPersonA );
						$("#informationPersonAPhone_label").empty().append(data.informationPersonAPhone);
						$("#informationPersonB_label").empty().append(data.informationPersonB);
						$("#informationPersonBPhone_label").empty().append(data.informationPersonBPhone);
						$.messageBox({message:"已成功编辑网格基本信息"});
					 	$("#villageProfileDialog").dialog("close");
					}
				},
				error: function(XMLHttpRequest, textStatus, errorThrown){
					alert("提交错误");
				}
			});
		},
		rules:{
		},
		messages:{
		}
	});
	jQuery.validator.addMethod("villageRingstersLessthanVillagers", function(value, element){
		if($("#villagers").val() != null 
				&& $("#villageRingsters").val() != null 
				&& $("#villagers").val() != "" 
				&& $("#villageRingsters").val() != "" 
				&& parseInt($("#villageRingsters").val()) > parseInt($("#villagers").val())){
			return false;
		}
		return true;
	});
	jQuery.validator.addMethod("villageDelegateLessthanVillagers", function(value, element){
		if($("#villagers").val() != null 
				&& $("#villageDelegate").val() != null 
				&& $("#villagers").val() != "" 
				&& $("#villageDelegate").val() != "" 
				&& parseInt($("#villageDelegate").val()) > parseInt($("#villagers").val())){
			return false;
		}
		return true;
	});
	jQuery.validator.addMethod("individualAverageInclude", function(value, element){
		if($("#individualAverageInclude").val()>99){
			return false;
		}
		return true;
	});
});
</script>