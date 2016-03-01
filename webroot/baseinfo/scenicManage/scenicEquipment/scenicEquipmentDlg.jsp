<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<jsp:include page="/includes/baseInclude.jsp" />
<style type='text/css'>
.ui-widget-content #searchSpot{padding:4px 6px;}
</style>

<div class="container container_24" >
	<div id="publicPlace" class="container container_24">
		<form id="maintainForm" method="post" action="${path}/baseinfo/scenicEquipmentManage/saveScenicEquipment.action"  >
			<div id="perUuid"></div>
			<s:if test='"add".equals(mode)'>
				<pop:token/>
			</s:if>
			<input type="hidden" name="mode" id="mode" value="${mode}" />
			<input type="hidden" name="modeType" id="modeType" value="${modeType}" />
			<input type="hidden" name="location.id" id="locationId" value="${location.id}" />
			<input id="organizationId"	type="hidden" name="location.organization.id" value="${location.organization.id }" />
			<input id="_imgUrl" name="location.imgUrl" type="hidden" value="${location.imgUrl}"/>
			<s:if test="#parameters.fromlocationType[0]">
				<span style="font-weight: bold; margin-left:20px; ">配套设施</span>
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
	   			<label class="form-lb1">名称：</label>
   			</div>
   			<div class="grid_20">
   				<input type="text"  id="equipName"  name="location.equipName"   value="${location.equipName}" style="width: 99%"  maxlength="50" title="名称可以输入字母、数字、和中文"
   				class="form-txt " />
   			</div>
   			<div class='clearLine'>&nbsp;</div>
   			<div class="grid_4 lable-right">
	   			<label class="form-lb1">地址：</label>
   			</div>
   			<div class="grid_20">
   				<input type="text"  id="equipAddress"  name="location.equipAddress"   value="${location.equipAddress}" style="width: 99%"  maxlength="50" title="地址可以输入字母、数字、和中文"
   				class="form-txt " />
   			</div>
   			<div class='clearLine'>&nbsp;</div>
   			<div class="grid_4 lable-right">
   				<em class="form-req">*</em>
				<label class="form-lbl">类型：</label>
			</div>
			<div class="grid_8">
				<select name="location.equipType.id" id="location-equipType" class="form-txt">
					<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@SCENICEQUIP_TYPES" defaultValue="${location.equipType.id}" />
				</select>
			</div>
			<div class="grid_4 lable-right">
   				<label class="form-lb1">联系电话：</label>
	   		</div>
	   		<div class="grid_8">
	   			<input type="text" name="location.mobile" id="mobile" maxlength="15"  value="${location.mobile }" title="请输入联系电话、如:11111111111"
	   			class='form-txt {telephone:true,messages:{telephone:$.format("手机号码或者固定电话，只能输数字或数字和横杠(-)")}}' />
	   		</div>
   			<div class="grid_4 lable-right">
   				<label class="form-lb1">负责人：</label>
	   		</div>
	   		<div class="grid_8">
	   			<input type="text" name="location.manager" id="manager" maxlength="20"  value="${location.manager }" title="请输入负责人、如:张三" class="form-txt"/>
	   		</div>
   			<div class="grid_4 lable-right">
   				<label class="form-lb1">负责人电话：</label>
	   		</div>
	   		<div class="grid_8">
	   			<input type="text" name="location.managerMobile" id="managerMobile" maxlength="15"  value="${location.managerMobile }" title="请输入负责人联系电话、如:11111111111"
	   			class='form-txt {telephone:true,messages:{telephone:$.format("手机号码或者固定电话，只能输数字或数字和横杠(-)")}}'	 />
	   		</div>
   			<div class='clearLine'>&nbsp;</div>
   			<div class="grid_4 lable-right">
   				<label class="form-lb1">周边景点：</label>
	   		</div>
	   		<div class="grid_20" style="height:145px">
	   			<input type="hidden" id="aroundScenicIds" name="location.aroundScenicIds" value="${location.aroundScenicIds}"/>
   				<textarea rows="6"   name="location.aroundScenic" id="aroundScenic"   class="form-txt" style="width: 90%" readonly="readonly">${location.aroundScenic }</textarea>
   				<div style="float: right;padding-top:4px;">
   					<a id="searchSpot" class="button" href="javascript:void(0)"><span><strong class="ui-ico-dc"></strong>查询</span></a>
   				</div>
   			</div>
   			 <div class="clearLine"></div>
			<div class="grid_4 lable-right">
   				<label class="form-lb1">备注：</label>
	   		</div>
	   		<div class="grid_20">
   				<textarea rows="6"   name="location.remark" id="remark" class="form-txt" style="width: 99%">${location.remark }</textarea>
   			</div>
   		    <input name="isSubmit" id="isSubmit" type="hidden" value="" />
		</form>
  	</div>
</div>
<div id="searchScenicSpotListDialog"></div>
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
			"location.equipName":{
				required:true,
				exculdeParticalChar:true,
				minlength:2,
				maxlength:50
			},
			"location.equipAddress":{
				exculdeParticalChar:true,
				minlength:2,
				maxlength:50
			},
			"location.manager":{
				exculdeParticalChar:true,
				minlength:2,
				maxlength:20
			},
			"location.mobile":{
				required:false,
				telephone:true,
				minlength:7,
				maxlength:15
			},
			"location.managerMobile":{
					required:false,
					telephone:true,
					minlength:7,
					maxlength:15
			},
			"location.aroundScenic":{
				maxlength:300
			},
			"location.equipType.id":{
				required:true
			},
			"location.remark":{
				maxlength:300
			}
		},
		messages:{
			"location.equipName":{
				required:"请输入名称",
				exculdeParticalChar:"名称只能输入字母，数字和中文字符",
				minlength:$.format("名称至少需要输入{0}个字符"),
				maxlength:$.format("名称最多只能输入{0}个字符")
			},
			"location.equipAddress":{
				exculdeParticalChar:"地址只能输入字母，数字和中文字符",
				minlength:$.format("地址至少需要输入{0}个字符"),
				maxlength:$.format("地址最多只能输入{0}个字符")
			},
			"location.manager":{
				exculdeParticalChar:"负责人只能输入字母，数字和中文字符",
				minlength:$.format("负责人至少需要输入{0}个字符"),
				maxlength:$.format("负责人最多只能输入{0}个字符")
			},
			"location.equipType.id":{
				required:"请输入类型"
			},
			"location.mobile":{
				required:"请输入联系电话",
				telephone:$.format("联系电话只能输数字和横杠(-)"),
				minlength:$.format("联系电话最少需要输入{0}个字符"),
				maxlength:$.format("联系电话最多需要输入{0}个字符")
			},
			"location.managerMobile":{
				required:"请输入负责人电话",
				telephone:$.format("负责人电话只能输数字和横杠(-)"),
				minlength:$.format("负责人电话最少需要输入{0}个字符"),
				maxlength:$.format("负责人电话最多需要输入{0}个字符")
			},
			"location.aroundScenic":{
				maxlength:$.format("周边景点最多只能输入{0}个字符")
			},
			"location.remark":{
				maxlength:$.format("备注最多只能输入{0}个字符")
			}
		}
	});
	$("#searchSpot").click(function(event){
		var organizationId=$("#organizationId").val();
		if(organizationId==""||organizationId==null){
			return;
		}
		$("#searchScenicSpotListDialog").createDialog({
			width:500,
			height:500,
			title:'旅游景点列表',
			url:'/baseinfo/scenicSpotManage/dispatchOperate.action?dailogName=searchScenicSpotListDialog&organizationId='+organizationId+'&mode=allSearch',
			buttons: {
				"确定":function(){
					saveScenicSpotNames();
					$(this).dialog("close");
				},
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			},
			close: function(event, ui) {$("#${lowerCaseModuleName}List").trigger("reloadGrid");}
		});
	});
	function saveScenicSpotNames(){
		var selectedSpotName="";
		var id="";
		var spotIds = $("#searchScenicSpotList").jqGrid("getGridParam", "selarrrow");
		for(var i=0;i<spotIds.length;i++){
			var rowData = $("#searchScenicSpotList").getRowData(spotIds[i]);
			if(rowData.name!=""&&rowData.name!=null){
				selectedSpotName+=";"+rowData.name;
			}
				id+=" "+rowData.id;
			
		}
// 		if(selectedSpotName==""){
// 			return
// 		}else{
// 			selectedSpotName=selectedSpotName.substr(1);
// 		}
		selectedSpotName=selectedSpotName.substr(1);
		var aroundScenic = $("#aroundScenic").val();
		//$("#aroundScenic").val(aroundScenic+" " + selectedSpotName);
		$("#aroundScenic").val(selectedSpotName);
		$("#aroundScenicIds").val(id.substr(1));
		
	}
});


</script>