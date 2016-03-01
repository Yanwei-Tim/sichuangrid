<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/> 
<form action="" method="post" id="maintainForm">
	<@pop.token />
<div class="container container_24">
	<input type="hidden" name="mode" id="mode" value="${(mode)!}" />
	<input type="hidden" name="familyInfo.organization.id" id="orgId" value="${(familyInfo.organization.id)!}"/>
	<input type="hidden" name="organizationId" id="organizationId" value="${(organizationId)!}"/>
	<input type="hidden" name="familyInfo.id" id="familyId" value="${(id)!}" />
	<input type="hidden" id="centerLon" name="familyInfo.centerLon"  value="${(familyInfo.centerLon)!}">
	<input type="hidden" id="centerLat" name="familyInfo.centerLat"  value="${(familyInfo.centerLat)!}">
	<div class="grid_4 lable-right">
		<em class="form-req">*</em>
		<label class="form-lb1">所属网格：</label>
	</div>
	<div class="grid_20">
		<input type="text"  id="orgName"  name="familyInfo.organization.orgName"   value="${(familyInfo.organization.orgName)!}" readonly  class="form-txt" />
	</div>
	<div class='clearLine'>&nbsp;</div>
	
	<div class="grid_4 lable-right">
		<em class="form-req">*&nbsp;</em>
		<label class="form-lbl">所属分组：</label>
	</div>
	<div class="grid_12">
		<@s.if test="teamId!=null">
			<@s.iterator value="teamList" id="team">
				<input type="hidden" name="familyInfo.teamId" value="${team.id}"/>
				<input type="text" name="familyInfo.teamName" value="${team.teamName}" class="form-txt" readonly/>
			</@s.iterator>
		</@s.if>
		<@s.else>
		<select name="familyInfo.teamId" id="familyTeam" <@s.if test='"view".equals(mode)'>disabled</@s.if> class="form-txt" style="height: 22px;width:137px;" >
			<option value="">请选择</option>
			<@s.iterator value="teamList" id="teams">
				<option value="${(teams.id)!}">${(teams.teamName)!}</option>
			</@s.iterator>
		</select>
		</@s.else>
	</div>
	<div class='clearLine'>&nbsp;</div>
	
	<div class="grid_4 lable-right">
		<em class="form-req">*&nbsp;</em>
		<label class="form-lbl">用户姓名：</label>
	</div>
	<div class="grid_4">
		<input type="text" name="familyInfo.name" maxlength="20" id="name" value="${(familyInfo.name)!}" <@s.if test='"view".equals(mode)'>readonly</@s.if> class="form-txt" style="width: 99%"/>
	</div>
	<div class="grid_4 lable-right">
		<em class="form-req">*&nbsp;</em>
		<label class="form-lbl">求助电话：</label>
	</div>
	<div class="grid_4">
		<input type="text" name="familyInfo.helpLine" maxlength="11" id="helpLine" value="${(familyInfo.helpLine)!}" <@s.if test='"view".equals(mode)'>readonly</@s.if> class="form-txt" style="width: 99%" />
	</div>
	<div class="grid_4 lable-right">
		<em class="form-req">*&nbsp;</em>
		<label class="form-lbl">证件号码：</label>
	</div>
	<div class="grid_4">
		<input type="text" name="familyInfo.certificateNumber" maxlength="20" id="certificateNumber" value="${(familyInfo.certificateNumber)!}" style="width:99%" <@s.if test='"view".equals(mode)'>readonly</@s.if> class="form-txt"/>
	</div>
	<div class="grid_4 lable-right">
		<em class="form-req">*&nbsp;</em>
		<label class="form-lbl">所属接警中心：</label>
	</div>
	<div class="grid_8">
		<select name="familyInfo.alarmCenter.id" id ="familyInfo-alarmCenter-id"  <@s.if test='"view".equals(mode)'>disabled</@s.if> class="form-select">
			<@pop.OptionTag name="@com.tianque.domain.property.PropertyTypes@ALARMCENTER" defaultValue="${(familyInfo.alarmCenter.id) ! }" />
		</select>
	</div>
	<div class="grid_4 lable-right">
		<em class="form-req">*&nbsp;</em>
		<label class="form-lbl">用户地址：</label>
	</div>
	<div class="grid_8">
		<input type="text" name="familyInfo.familyAddress" id="familyAddress" maxlength="30" value="${(familyInfo.familyAddress) ! }" <@s.if test='"view".equals(mode)'>readonly</@s.if> class="form-txt" />
	</div>
	<div class='clearLine'>&nbsp;</div>
	<div class="grid_4 lable-right">
		<em class="form-req">*&nbsp;</em>
		<label class="form-lbl">用户状态：</label>
	</div>
	<div class="grid_8">
		<select name="familyInfo.familyState" id="familyState" <@s.if test='"view".equals(mode)'>disabled</@s.if> class="form-select">
			<option value="">请选择</option>
			<option value="0">在线</option>
			<option value="1">不在线</option>
		</select>
	</div>
	<div class="grid_4 lable-right">
		<em class="form-req">*&nbsp;</em>
		<label class="form-lbl">警务室：</label>
	</div>
	<div class="grid_8">
		<select name="familyInfo.policeRoom.id" id="familyInfo-policeRoom-id" <@s.if test='"view".equals(mode)'>disabled</@s.if> class="form-select">
			<@pop.OptionTag name="@com.tianque.domain.property.PropertyTypes@POLICEROOM" defaultValue="${(familyInfo.policeRoom.id)!}" />
		</select>
	</div>
	<div class='clearLine'>&nbsp;</div>
	
	<div class="grid_4 lable-right">
		<label class="form-lbl">短信接收号码：</label>
	</div>
	<div class="grid_12">
		<input type="text" id="SMSNumber" maxlength="200" name="familyInfo.SMSNumber" value="${(familyInfo.SMSNumber) ! }" <@s.if test='"view".equals(mode)'>readonly</@s.if> class="form-txt" />	
	</div>
	<div class="grid_8">
		<span style="color:red">号码与号码之间用“ ; ”隔开</span>
	</div>
	<div class='clearLine'>&nbsp;</div>
	<div class="grid_4 lable-right">
		<label class="form-lbl">语音接收号码：</label>
	</div>
	<div class="grid_12">
		<input type="text" id="voiceNumber" maxlength="200" name="familyInfo.voiceNumber" value="${(familyInfo.voiceNumber) ! }" <@s.if test='"view".equals(mode)'>readonly</@s.if> class="form-txt" />
	</div>
	<div class="grid_8">
		<span style="color:red">号码与号码之间用“ ; ”隔开</span>
	</div>
	<div class='clearLine'>&nbsp;</div>
	
	<div class="grid_4 lable-right">
		<label class="form-lbl">告警接警设置：</label>
	</div>
	<div class="grid_8">
		<input type="checkbox"id="isInformOther" name="familyInfo.isInformOther"  value="1"  <@s.if test='familyInfo.isInformOther.equals(1L)'> checked="checked" </@s.if> <@s.if test='"view".equals(mode)'>disabled</@s.if>/>通知同组其他用户&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="checkbox" id="isReceiveOtherAlarm" name="familyInfo.isReceiveOtherAlarm" value="1" <@s.if test='familyInfo.isReceiveOtherAlarm.equals(1L)'> checked="checked" </@s.if> <@s.if test='"view".equals(mode)'>disabled</@s.if> />接收同组其他用户告警
	</div>
	<div class='clearLine'>&nbsp;</div>
	
	<div class="grid_4 lable-right">
		<label class="form-lbl">呼叫设置：</label>
	</div>
	<div class="grid_8">
		<input type="checkbox"id="isCallOther" name="familyInfo.isCallOther" value="1" <@s.if test='familyInfo.isCallOther.equals(1L)'> checked="checked" </@s.if> <@s.if test='"view".equals(mode)'>disabled</@s.if> />呼叫同组其他用户&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="checkbox" id="isReceiveOtherCall" name="familyInfo.isReceiveOtherCall"  value="1" <@s.if test='familyInfo.isReceiveOtherCall.equals(1L)'> checked="checked" </@s.if> <@s.if test='"view".equals(mode)'>disabled</@s.if> />接收同组其他用户呼叫
	</div>
	<div class='clearLine'>&nbsp;</div>
	<@s.if test='!"view".equals(mode)'>
	<div class="clearfix">
		<div class="grid_4 lable-right"></div>
		<div class="grid_20 heightAuto" >
			<div>&nbsp;</div>
			<div id="familyMap" style="width: 600px; height: 170px;overflow-y: hidden;overflow-x: hidden;border:1px solid #7F9DB9">
			</div>
		<div>
		<input type="button" value="绑定" class="defaultBtn" id="bindMap" <@s.if test='"view".equals(mode)'>disabled</@s.if>/>
		<input type="button" class="defaultBtn" value="清除" id="cancelBind" <@s.if test='"view".equals(mode)'>disabled</@s.if>/>
	</div>
	</@s.if>
</div>	
</form>

<script type="text/javascript">
$(document).ready(function(){
	<@s.if test='"edit".equals(mode)||"view".equals(mode)'>
		$("#familyTeam option").each(function(){
			var thisTeam = "${familyInfo.teamId}";
			if($(this).val()==thisTeam){
				$(this).attr("selected",true);
			}
		});
		
		$("#familyState option").each(function(){
			var thisState = "${familyInfo.familyState}";
			if($(this).val()==thisState){
				$(this).attr("selected",true);
			}
		});
	</@s.if>	

	<@s.if test='"add".equals(mode)'>
		$("#maintainForm").attr("action","${path}/tenHouseholdsJoint/familyCondition/addFamilyInfo.action");
	</@s.if>
	<@s.if test='"edit".equals(mode)'>
		$("#maintainForm").attr("action","${path}/tenHouseholdsJoint/familyCondition/updateFamilyInfo.action");
	</@s.if>
	
	loadOpenLayersMap();
	
	$("#maintainForm").formValidate({
		promptPosition:"bottomLeft",
		submitHandler: function(form){
			 $(form).ajaxSubmit({
					success : function(data) {
						if (!data.id) {
							$.messageBox({message : data,level : "error"});
							return;
						}
						if("add"=="${mode}"){
							$.messageBox({message:"用户新增成功！"});
						}
						if("edit"=="${mode}"){
							$.messageBox({message:"用户信息修改成功！"});
						}
						<@s.if test='"familyInfo".equals(operateSource)'>
						$("#familyInfoDialog").dialog('close');
						$("#familyInfoList").trigger("reloadGrid");
						</@s.if>
						<@s.else>
						$("#manageFamilyInfoDialog").dialog("close");
						$("#manageFamilyInfoList").trigger("reloadGrid");
						</@s.else>
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						alert("提交错误");
					}
			 });
		},
		
		rules:{
			"familyInfo.name":{
				required: true,
				minlength: 2,
				maxlength: 20,
				exculdeParticalChar:true
			},
			"familyInfo.helpLine":{
				required: true,
				mobile:true,
				checkHelpLine:true
			},
			"familyInfo.certificateNumber":{
				required: true,
				minlength: 2,
				maxlength: 20
			},
			"familyInfo.familyAddress":{
				required: true,
				minlength: 2,
				maxlength: 30
			},
			"familyInfo.teamId":{
				required:true
			},
			"familyInfo.familyState":{
				required:true
			},
			"familyInfo.alarmCenter.id":{
				required:true
			},
			"familyInfo.policeRoom.id":{
				required:true
			}
		},
		messages:{
			"familyInfo.name":{
				required: "请输入用户姓名",
				minlength  : $.format("至少需要{0}个字符"),
	    		maxlength  : $.format("最多只能输入{0}个字符"),
	    		exculdeParticalChar:'不能输入非法字符'
			},
			"familyInfo.helpLine":{
				required: '请输入求助电话',
				mobile:'手机号码输入只能是以1开头的11位数字',
				checkHelpLine:'该求助电话已存在'
			},
			"familyInfo.certificateNumber":{
				required: "请输入证件号码",
				minlength  : $.format("至少需要{0}个字符"),
	    		maxlength  : $.format("最多只能输入{0}个字符")
			},
			"familyInfo.familyAddress":{
				required: "请输入用户地址",
				minlength  : $.format("至少需要{0}个字符"),
	    		maxlength  : $.format("最多只能输入{0}个字符")
			},
			"familyInfo.teamId":{
				required:"请选择所属分组"
			},
			"familyInfo.familyState":{
				required:"请选择用户状态"
			},
			"familyInfo.alarmCenter.id":{
				required: "请选择接警中心"
			},	
			"familyInfo.policeRoom.id":{
				required:"请选择警务处"
			}
		
		}
	});

	
	$("#bindMap").click(function(){
			$("#issueOpenLayersMapDialog").createDialog({
				width:800,
				height:600,
				title:"地图绑定",
				url:"${path}/openLayersMap/product_3.0/gisBindIssueForFamily.jsp?flag=1&currentOrgId="+$("#organizationId").val(),
				buttons:{
					"关闭" : function(){
			        	$("#issueOpenLayersMapDialog").dialog("close");
			   		}
				},
				shouldEmptyHtml:false
			});
	});
	
	
	
	$("#cancelBind").click(function(){
		$("#centerLon").val("");
		$("#centerLat").val("");
		$("#zoom").val("");
		$("#map").TqMap("clearMarkers");
	});
	
});


	
function loadOpenLayersMap(){
	$("#familyMap").load("${path}/openLayersMap/product_3.0/gisIssue.jsp?currentOrgId="+$("#organizationId").val()+"&lon="+$("#centerLon").val()+"&lat="+$("#centerLat").val());
}

jQuery.validator.addMethod("checkHelpLine", function(value, element){
		var flag=true;
		$.ajax({
			async:false,
			url:"${path}/tenHouseholdsJoint/familyCondition/checkHelpLine.action",
			data:{
				"familyInfo.helpLine" : function(){ return $('#helpLine').val()},
				"familyInfo.id" :function(){ return $('#familyId').val()}
			},
			success:function(responseData){
				flag = responseData;
			}
		});
		return flag;
	});
	
</script>
