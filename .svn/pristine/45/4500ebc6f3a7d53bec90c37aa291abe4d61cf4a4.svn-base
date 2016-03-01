<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %> 
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<jsp:include page="/includes/baseInclude.jsp" />

<div class="container container_24">
	<form id="maintainForm" method="post" action="${path }/baseinfo/villageProfile/updateOrAddVillageProfile.action?mode=baseVillageProfile">
		<input type="hidden" name="villageProfile.id" value="${villageProfile.id }" />
		<input type="hidden" name="villageProfile.organization.id" value="${villageProfile.organization.id}" />
		<div class="grid_6 lable-right">
			<label>责任区：</label>
		</div>
		<div class="grid_5" id="oogg">
			<label id="orgName">${villageProfile.organization.orgName}</label>
		</div>
		<div id="aa">
		   <input type="hidden" name="hide" id="hide" value="${villageProfile.gridNum}"/>
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		<div class="grid_6 lable-right">
			<label>面积：</label>
		</div>
		<div class="grid_4">
			<input type="text" name="villageProfile.acreage" value="${villageProfile.acreageStr}" id="acreage" maxlength="9" class="form-txt" />
		</div>
		<div class="grid_1">㎡</div>
		<div class="grid_7 lable-right">
			<label>户数：</label>
		</div>
		<div class="grid_5">
			<input type="text" name="villageProfile.doors" value="${villageProfile.doors}" id="doors" maxlength="9" class="form-txt" />
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		<div class="grid_6 lable-right">
			<label>社区(村)人数：</label>
		</div>
		<div class="grid_5">
			<input type="text" name="villageProfile.villagers" value="${villageProfile.villagers}" id="villagers" maxlength="9" class="form-txt" />
		</div>
		<div class="grid_7 lable-right">
			<label>社区(村)党员数：</label>
		</div>
		<div class="grid_5">
			<input type="text" name="villageProfile.villageRingsters" value="${villageProfile.villageRingsters}" id="villageRingsters" maxlength="9" class="form-txt" />
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		<div class="grid_6 lable-right">
			<label>社区(村)代表数：</label>
		</div>
		<div class="grid_5">
			<input type="text" name="villageProfile.villageDelegate" value="${villageProfile.villageDelegate}" id="villageDelegate" maxlength="9" class="form-txt" />
		</div>
		<div class="grid_7 lable-right">
			<label>社区(村)集体经济年收入：</label>
		</div>
		<div class="grid_4">
			<input type="text" name="villageProfile.hamletEconomyinclude" value="${villageProfile.hamletEconomyinclude}" id="hamletEconomyinclude"  title="请输入正数，保留2位小数点!" class="dialogtip form-txt" />
		</div>
		<div class="grid_2">万元</div>
		<div class='clearLine'>&nbsp;</div>
		
		<div class="grid_6 lable-right">
			<label>社区(村)人均年收入：</label>
		</div>
		<div class="grid_3">
			<input type="text" name="villageProfile.individualAverageInclude" value="${villageProfile.individualAverageInclude}" id="individualAverageInclude" title="请输入正数，保留2位小数点!" class="dialogtip form-txt" />
		</div>
		<div class="grid_2">万元</div>
		<div class="grid_7 lable-right">
			<label>社区(村)集体资产：</label>
		</div>
		<div class="grid_4">
			<input type="text" name="villageProfile.villageCollectivityAsset" value="${villageProfile.villageCollectivityAsset}" id="villageCollectivityAsset" title="请输入正数，保留2位小数点!" class="dialogtip form-txt" />
		</div>
		<div class="grid_2">万元</div>
		<div class='clearLine'>&nbsp;</div>
		
		<div class="grid_6 lable-right">
			<label>联系的区级领导：</label>
		</div>
		<div class="grid_5">
			<input type="text" name="villageProfile.interzoneLeading" value="${villageProfile.interzoneLeading}" id="interzoneLeading" maxlength="10" class="form-txt" />
		</div>
		<div class="grid_7 lable-right">
			<label>挂钩的机关部门：</label>
		</div>
		<div class="grid_5">
			<input type="text" name="villageProfile.department" value="${villageProfile.department}" id="department" maxlength="10" class="form-txt" />
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		<div class="grid_6 lable-right">
			<label>社区(村)党委组织书记：</label>
		</div>
		<div class="grid_5">
			<input type="text" name="villageProfile.villageBuildupSecretary" value="${villageProfile.villageBuildupSecretary}" id="villageBuildupSecretary" maxlength="10" class="form-txt" />
		</div>
		<div class="grid_7 lable-right">
			<label>联系电话：</label>
		</div>
		<div class="grid_5">
			<input type="text" name="villageProfile.buildupSecretaryPhone" value="${villageProfile.buildupSecretaryPhone}" id="buildupSecretaryPhone" maxlength="15" class="form-txt" />
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		<div class="grid_6 lable-right">
			<label>社区(村)主任：</label>
		</div>
		<div class="grid_5">
			<input type="text" name="villageProfile.villageDirector" value="${villageProfile.villageDirector}" id="villageDirector" maxlength="10" class="form-txt" />
		</div>
		<div class="grid_7 lable-right">
			<label>联系电话：</label>
		</div>
		<div class="grid_5">
			<input type="text" name="villageProfile.villageDirectorPhone" value="${villageProfile.villageDirectorPhone}" id="villageDirectorPhone" maxlength="15" class="form-txt" />
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		<div class="grid_6 lable-right">
			<label>信息员A岗：</label>
		</div>
		<div class="grid_5">
			<input type="text" name="villageProfile.informationPersonA" value="${villageProfile.informationPersonA}" id="informationPersonA" maxlength="10" class="form-txt" />
		</div>
		<div class="grid_7 lable-right">
			<label>联系电话：</label>
		</div>
		<div class="grid_5">
			<input type="text" name="villageProfile.informationPersonAPhone" value="${villageProfile.informationPersonAPhone}" id="informationPersonAPhone" maxlength="15" class="form-txt" />
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		<div class="grid_6 lable-right">
			<label>信息员B岗：</label>
		</div>
		<div class="grid_5">
			<input type="text" name="villageProfile.informationPersonB" value="${villageProfile.informationPersonB}" id="informationPersonB" maxlength="10" class="form-txt" />
		</div>
		<div class="grid_7 lable-right">
			<label>联系电话：</label>
		</div>
		<div class="grid_5">
			<input type="text" name="villageProfile.informationPersonBPhone" value="${villageProfile.informationPersonBPhone}" id="informationPersonBPhone" maxlength="15" class="form-txt" />
		</div>
		<div class='clearLine'>&nbsp;</div>
	</form>
</div>

<script type="text/javascript">
$(document).ready(function(){
	/*不需要截取小数点 注释
	if($("#individualAverageInclude").val().indexOf('.') != -1){
		$("#individualAverageInclude").val($("#individualAverageInclude").val().substr(0, $("#individualAverageInclude").val().indexOf('.')));
	}
	if($("#hamletEconomyinclude").val().indexOf('.') != -1){
		$("#hamletEconomyinclude").val($("#hamletEconomyinclude").val().substr(0, $("#hamletEconomyinclude").val().indexOf('.')));
	}
	if($("#villageCollectivityAsset").val().indexOf('.') != -1){
		$("#villageCollectivityAsset").val($("#villageCollectivityAsset").val().substr(0, $("#villageCollectivityAsset").val().indexOf('.')));
	}
	*/
	if(!isGrid()){
		if("${villageProfile.gridNum}"=="null" || "${villageProfile.gridNum}" == ""){
			$("#aa").html("<div class='grid_7 lable-right' id='orgNo'><label>网格数：</label></div></div><div class='grid_5'><input name='villageProfile.gridNum' value='' id='gridNum'  maxlength='9' class='form-txt' /></div>");
		}else{
			$("#aa").html("<div class='grid_7 lable-right' id='orgNo'><label>网格数：</label></div></div><div class='grid_5'><input name='villageProfile.gridNum' value='${villageProfile.gridNum}' id='gridNum'  maxlength='9' class='form-txt' /></div>");
		}
	}
   
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

	$("#maintainForm").formValidate({
		promptPosition: "bottomLeft", 
		submitHandler: function(form){
		$(form).ajaxSubmit({
			success:function(data){
				if(!data.id){
					$.messageBox({
						message: data,
						level: "error"
					});
					return;
				}
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
			});
		},
		rules:{
			"villageProfile.acreage":{
				number:true,
				min:0,
				max:999999999
			},
			"villageProfile.doors":{
				digits:true,
				min:0,
				max:999999999
			},
			"villageProfile.gridNum":{
				digits:true,
				min:0,
				max:999999999
			},
			"villageProfile.villagers":{
				digits:true,
				min:0,
				max:999999999
			},
			"villageProfile.villageRingsters":{
				digits:true,
				min:0,
				max:999999999,
				villageRingstersLessthanVillagers:true
			},
			"villageProfile.villageDelegate":{
				digits:true,
				min:0,
				max:999999999,
				villageDelegateLessthanVillagers:true
			},
			"villageProfile.hamletEconomyinclude":{
				posNumWiPot:true,
				min:0,
				max:999999
			},
			"villageProfile.individualAverageInclude":{
				posNumWiPot:true,
				min:0,
				max:99
			},
			"villageProfile.villageCollectivityAsset":{
				posNumWiPot:true,
				min:0,
				max:999999
			},
			"villageProfile.interzoneLeading":{
				exculdeParticalChar:true,
				minlength:2,
				maxlength:10
			},
			"villageProfile.department":{
				exculdeParticalChar:true,
				maxlength:10
			},
			"villageProfile.villageBuildupSecretary":{
				exculdeParticalChar:true,
				minlength:2,
				maxlength:10
			},
			"villageProfile.buildupSecretaryPhone":{
				telephone:true
			},
			"villageProfile.villageDirector":{
				exculdeParticalChar:true,
				minlength:2,
				maxlength:10
			},
			"villageProfile.villageDirectorPhone":{
				telephone:true
			},
			"villageProfile.informationPersonA":{
				exculdeParticalChar:true,
				minlength:2,
				maxlength:10
			},
			"villageProfile.informationPersonAPhone":{
				telephone:true
			},
			"villageProfile.informationPersonB":{
				exculdeParticalChar:true,
				minlength:2,
				maxlength:10
			},
			"villageProfile.informationPersonBPhone":{
				telephone:true
			}
		},
		messages:{
			"villageProfile.acreage":{
				number: "面积请输入数字",
				min: "面积需要输入正数",
				max: "面积最大输入999999999"
			},
			"villageProfile.gridNum":{
				digits: "网格数请输入正整数",
				min: "网格数请输入正数",
				max: "网格数最大输入999999"
			},
			"villageProfile.doors":{
				digits: "户数请输入正整数",
				min: "户数请输入正数",
				max: "户数最大输入999999999"
			},
			"villageProfile.villagers":{
				digits: "村民数请输入正整数",
				min: "村民数请输入正数",
				max: "村民数最大输入999999999"
			},
			"villageProfile.villageRingsters":{
				digits: "村民党员数请输入正整数",
				min: "村民党员数请输入正数",
				max: "村民党员数最大输入999999999",
				villageRingstersLessthanVillagers:"党员数不能大于村民数"
			},
			"villageProfile.villageDelegate":{
				digits: "村民代表数请输入正整数",
				min: "村民代表数请输入正数",
				max: "村民代表数最大输入999999999",
				villageDelegateLessthanVillagers:"村民代表数不能大于村民数"
			},
			"villageProfile.hamletEconomyinclude":{
				posNumWiPot: "村集体经济年收入请输入正整数,保留2位小数点",
				min: "村集体经济年收入请输入正数",
				max: "村集体经济年收入最大输入999999"
			},
			"villageProfile.individualAverageInclude":{
				posNumWiPot: "农民平均收入请输入正整数,保留2位小数点",
				min: "农民平均收入请输入正数",
				max: "农民平均收入最大输入99"
			},
			"villageProfile.villageCollectivityAsset":{
				posNumWiPot: "村集体资产请输入正整数,保留2位小数点",
				min: "村集体资产请输入正数",
				max: "村集体资产最大输入999999"
			},
			"villageProfile.interzoneLeading":{
				exculdeParticalChar:"不能输入非法字符",
				minlength:$.format("联系的区级领导至少需要输入{0}个字符"),
				maxlength:$.format("联系的区级领导最多需要输入{0}个字符")
			},
			"villageProfile.department":{
				exculdeParticalChar:"不能输入非法字符",
				maxlength:$.format("挂钩的机关部门最多需要输入{0}个字符")
			},
			"villageProfile.villageBuildupSecretary":{
				exculdeParticalChar:"不能输入非法字符",
				minlength:$.format("村党组织书记至少需要输入{0}个字符"),
				maxlength:$.format("村党组织书记最多需要输入{0}个字符")
			},
			"villageProfile.buildupSecretaryPhone":{
				telephone:$.format("村党组织书记联系电话不合法，只能输数字和横杠(-)")
			},
			"villageProfile.villageDirector":{
				exculdeParticalChar:"不能输入非法字符",
				minlength:$.format("村委会主任至少需要输入{0}个字符"),
				maxlength:$.format("村委会主任最多需要输入{0}个字符")
			},
			"villageProfile.villageDirectorPhone":{
				telephone:$.format("村委会主任联系电话不合法，只能输数字和横杠(-)")
			},
			"villageProfile.informationPersonA":{
				exculdeParticalChar:"不能输入非法字符",
				minlength:$.format("信息员A岗至少需要输入{0}个字符"),
				maxlength:$.format("信息员A岗最多需要输入{0}个字符")
			},
			"villageProfile.informationPersonAPhone":{
				telephone:$.format("信息员A岗联系电话不合法，只能输数字和横杠(-)")
			},
			"villageProfile.informationPersonB":{
				exculdeParticalChar:"不能输入非法字符",
				minlength:$.format("信息员B岗至少需要输入{0}个字符"),
				maxlength:$.format("信息员B岗最多需要输入{0}个字符")
			},
			"villageProfile.informationPersonBPhone":{
				telephone:$.format("信息员B岗联系电话不合法，只能输数字和横杠(-)")
			}
		}
	});
	
});
</script>

