<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<@s.include value="${path}/baseinfo/commonPopulation/jsFunctionInclude.jsp">
	<@s.param name="moduleName">ServiceRecord</@s.param>
</@s.include>

<div class="container container_24">
<form id="searchServiceObjectForm" method="post">
	<@pop.token />	
	<div class="grid_4 lable-right">
		<label class="form-lbl">所属区域：</label>
	</div>	
	<div class="grid_20">
		<input name="serviceObjectVo.organization.id" type="hidden" id="service_object_orgId" value=""/>
		<input name="serviceObjectVo.organization.orgInternalCode" type="hidden" id="serviceObjectVo_org_orgInternalCode" value=""/>
		<input type="text" id="serviceObjectVoSearchOrgId" name="serviceObjectVoSearchOrgId" value="" class="form-txt"/>
    </div>
	
	<div class="grid_4 lable-right">
		<label class="form-lbl">对象类型：</label>
	</div>
	<div class="grid_8">
		<select name="serviceObjectVo.objectBigType" id="populationBigType" class="form-select {isSelect:true,message:{isSelect:'请选择类型'}}" <@s.if test='"view".equals(mode)'>disabled="disabled"</@s.if>>
			<@pop.JugePermissionTag ename="locationInformation">
				<option value="IMPORTANTPLACE">重点场所</option>
			</@pop.JugePermissionTag>
			<@pop.JugePermissionTag ename="civiAdministrationManagement">
				<option value="CIVIL">关怀对象</option>
			</@pop.JugePermissionTag>
			<@pop.JugePermissionTag ename="twoNewOrganizationManagement">
				<option value="doubleNew">两新组织</option>
			</@pop.JugePermissionTag>
			<@pop.JugePermissionTag ename="actualCompanyManagement">
				<option value="actualCompany">实有单位</option>
			</@pop.JugePermissionTag>
			<@pop.JugePermissionTag ename="peopleInformation">
				<option value="IMPORTANTPERSONNEL">特殊人群</option>
			</@pop.JugePermissionTag>
			<@pop.JugePermissionTag ename="enterprise_top_Management">
				<option value="ENTERPRISE">企业</option>
			</@pop.JugePermissionTag>
			<@pop.JugePermissionTag ename="rentalHouseManagement">
				<option value="ACTUALHOUSE">房屋信息</option>
			</@pop.JugePermissionTag>
			<@pop.JugePermissionTag ename="unemployedPeopleManagement">
				<option value="UNEMPLOY">失业人员</option>
			</@pop.JugePermissionTag>
			<@pop.JugePermissionTag ename="nurturesWomen">
				<option value="BIRTH">育龄妇女</option>
			</@pop.JugePermissionTag>
		</select>
	</div>
	<div class="grid_4 lable-right">
		<label class="form-lbl">对象子类型：</label>
	</div>
	<#include "serviceObjectTypeDlg.ftl"/>
	<div class='clearLine'></div>
	<div class="searchInfoBox" id="populationInfoDiv">
		<div class="grid_4 lable-right"><label class="form-lb1">姓名：</label></div>
		<div class="grid_8"><input type="text"
			id="name" maxlength="10"
			name="serviceObjectVo.name" style="width: 97%"
			title="请输入姓名"
			class='form-txt {maxlength:10,messages:{maxlength:$.format("姓名最多输入{0}个字符")}}' />
		</div>
		<div class="grid_4 lable-right"><label class="form-lb1">身份证号码：</label></div>
		<div class="grid_8"><input type="text"
			id="idCardNumber" maxlength="18"
			name="serviceObjectVo.idCardNo" style="width: 97%"
			title="请输入身份证号码"
			class='form-txt {exculdeParticalChar:true,minlength:2,maxlength:18,messages:{exculdeParticalChar:"不能输入非法字符",minlength:$.format("身份证号码至少需要输入{0}个字符"),maxlength:$.format("身份证号码最多需要输入{0}个字符")}}' />
		</div>
		<div class='clearLine'></div>
	</div>
	<div class="searchInfoBox" id="locationInfoDiv">
		<div class="grid_4 lable-right"><label class="form-lb1">场所名称：</label></div>
		<div class="grid_8"><input type="text"
			id="name" maxlength="10"
			name="serviceObjectVo.locationName" style="width: 97%"
			title="请输入场所名称"
			class='form-txt {maxlength:10,messages:{maxlength:$.format("场所名称最多输入{0}个字符")}}' />
		</div>
		<div class='clearLine'></div>
	</div>
	<div class="searchInfoBox" id="houseInfoDiv">
		<div class="grid_4 lable-right"><label class="form-lb1">房屋地址：</label></div>
		<div class="grid_8"><input type="text"
			id="name" maxlength="10"
			name="serviceObjectVo.houseAddress" style="width: 97%"
			title="请输入房屋地址"
			class='form-txt {maxlength:10,messages:{maxlength:$.format("房屋地址最多输入{0}个字符")}}' />
		</div>
		<div class='clearLine'></div>
	</div>
	<div class="grid_4 lable-right">
		<label class="form-lb1">
			<input type="button"  id='searchServiceObject'  value='查询' class="defaultBtn" />
		</label>
	</div>
	<div class='clearLine'></div>
	<input type="hidden" value="0" name="serviceObjectVo.logOut"/>
	
</form>

<div class='clearLine'></div>
<div style="width: 100%;margin-top: 6px">
	<div id="locationListDiv">
		<table id="LocationForMemberAddObjectList"> </table>
		<div id="LocationForMemberAddObjectListPager"></div>
	</div>
	<div id="populationListDiv">
		<table id="PopulationForMemberAddObjectList"> </table>
		<div id="PopulationForMemberAddObjectListPager"></div>
	</div>
	<div id="houseListDiv">
		<table id="HouseForMemberAddObjectList"> </table>
		<div id="HouseForMemberAddObjectListPager"></div>
	</div>
	
</div>
<div class='clearLine'></div>
<div id="serviceMemberInTeam" style="display:none">
		<div class="grid_2 lable-left">
			<label class="form-lb1">服务人员：</label>
		</div>
		<div class="grid_5 lable-left">
			<span><b>${(serviceObjects)!}</b></span>
		</div>
		<div class='clearLine'></div>
		<div class="grid_2 lable-left">
			<span> 所在团队：</span>
		</div>
		<div class="grid_6 lable-left">
			<select  name="" id="serviceMemberInTeamSelect" class="form-select {isSelect:true,message:{isSelect:'请选择所在团体'}}">
		</select>
		</div>
	</div>
</div>
<script>
$("#organizationId").val(getCurrentOrgId());
var tree=$("#serviceObjectVoSearchOrgId").treeSelect({
			inputName:"serviceObjectVo.organization.id",
			inputCodeName:"serviceObjectVo.organization.orgInternalCode",
			rootId:USER_ORG_ID
		});
		
$(document).ready(function(){

	<@pop.formatterProperty name="gender" domainName="@com.tianque.domain.property.PropertyTypes@GENDER" />
	<@pop.formatterProperty name="hiddenDangerLevel" domainName="@com.tianque.domain.property.PropertyTypes@HIDDEN_TROUBLE_LEVEL" />
	var hiddenDangerLevelColor = function(el,options,rowData){
		var displayName=hiddenDangerLevelFormatter(el,options,rowData);
		if(displayName=='undefined'||displayName=='')
			return '';
		else if(displayName=='严重')
			return '<span>严重：<span style="color:#ff0000;">█████</span></span>';
		else if(displayName=='一般')
			return '<span>一般：<span style="color:#ffcc00;">███</span></span>';
		else if(displayName=='安全')
			return '<span>安全：<span style="color:#99cc00;">█</span></span>';
		else
			return '';
	}
	

	$("#PopulationForMemberAddObjectList").jqGridFunction({
		datatype: "local",
	   	colModel:[
			{name:"objectId",index:"objectId",frozen:true,hidden:true},    
	      	{name:"name",index:'name',label:'姓名',width:100,sortable:false},
	      	{name:"gender",label:'性别',formatter:genderFormatter,sortable:false,width:60,align:'center'},
	      	{name:'idCardNo',index:'idCardNo',label:'身份证号码',sortable:false,width:180},
	      	{name:'objectTypeCname',label:'详细类型',index:"objectTypeCname",sortable:false, width:180},
	      	{name:'mobileNumber',label:'联系手机',index:"mobileNumber",sortable:false, width:180},
	 	  	{name:'objectType',label:'详细类型',index:"objectType",sortable:false,hidden:true,frozen:true},
	 	  	{name:'organization.orgName',index:'organization.orgName',label:'所属区域(网格)',sortable:false,width:350}
		],
		height:128,
	    multiselect:true,
	    rowNum:5,
	    showColModelButton:false,
	    rowList:[5,10,15]
	});


	$("#LocationForMemberAddObjectList").jqGridFunction({
		datatype: "local",
	   	colModel:[
			{name:"objectId",index:"objectId",frozen:true,hidden:true},    
	      	{name:"locationName",index:'locationName',label:'场所名',width:100,sortable:false},
	      	{name:"address",index:'address',label:'场所地址',width:100,sortable:false},
	      	{name:'objectTypeCname',label:'详细类型',index:"objectTypeCname",sortable:false, width:180},
	 	  	{name:'objectType',label:'详细类型',index:"objectType",sortable:false,hidden:true,frozen:true},
	 	  	{name:'organization.orgName',index:'organization.orgName',label:'所属区域(网格)',sortable:false,width:350}
		],
		height:128,
	    multiselect:true,
	    rowNum:5,
	    showColModelButton:false,
	    rowList:[5,10,15]
	});
	
	$("#HouseForMemberAddObjectList").jqGridFunction({
		datatype: "local",
	   	colModel:[
			{name:"objectId",index:"objectId",frozen:true,hidden:true},    
			{name:"hiddenDangerLevel.id",index:'hiddenDangerLevel',label:'隐患等级',formatter :hiddenDangerLevelColor,width:100,sortable:false},
			{name:"rentalPerson",index:'rentalPerson',label:'出租人',width:100,sortable:false},
	      	{name:"houseAddress",index:'houseAddress',label:'房屋地址',width:200,sortable:false},
	      	{name:'objectTypeCname',label:'详细类型',index:"objectTypeCname",sortable:false, width:180},
	 	  	{name:'objectType',label:'详细类型',index:"objectType",sortable:false,hidden:true,frozen:true},
	 	  	{name:'organization.orgName',index:'organization.orgName',label:'所属区域(网格)',sortable:false,width:350}
		],
		height:128,
	    multiselect:true,
	    rowNum:5,
	    showColModelButton:false,
	    rowList:[5,10,15]
	});
	divDisplayControl();
	
	$("#populationBigType").change(divDisplayControl);
	 
	function divDisplayControl(){
		var bigType=$("#populationBigType").val();
		$("#populationType").html($("#"+bigType).html());
		if(bigType=="IMPORTANTPLACE" || bigType=="actualCompany" || bigType=="doubleNew" || bigType=="ENTERPRISE"){
			locationDivShow();
		}else if(bigType=="ACTUALHOUSE"){
			houseDivShow();
		}else{
			populationDivShow();
		}
	}
	
	
	//绑定查询事件
	$("#searchServiceObject").click(function(){
		var orgVal = $('#service_object_orgId').val();
		if(orgLevelLessEqual(orgVal,<@s.property value="@com.tianque.domain.property.OrganizationLevel@CITY"/>) == false){
			$.messageBox({
				message:'所属区域不能选择市层级以上级别',
				level:'error'
			})
		}else{
		   	var data=$("#searchServiceObjectForm").serializeArray();
		   	var dataJson={};
			for(i=0;i<data.length;i++){
				if (dataJson[data[i].name]) {
					dataJson[data[i].name]=dataJson[data[i].name]+","+data[i].value;
				} else {
					dataJson[data[i].name] = data[i].value;
				}
			}
			var bigType=$("#populationBigType").val();
			if(bigType=="IMPORTANTPLACE" || bigType=="actualCompany" || bigType=="doubleNew" || bigType=="ENTERPRISE"){
				locationDivShow();
				$("#LocationForMemberAddObjectList").setGridParam({
				    url:"${path}/plugin/serviceTeam/serviceObject/findPopulationsForServiceRecord.action",
					datatype: "json",
					page:1,
					mtype:"post"
				});
				$("#LocationForMemberAddObjectList").setPostData(dataJson);
			    $("#LocationForMemberAddObjectList").trigger("reloadGrid");
			}else if(bigType=="ACTUALHOUSE"){
				houseDivShow();
				$("#HouseForMemberAddObjectList").setGridParam({
				    url:"${path}/plugin/serviceTeam/serviceObject/findPopulationsForServiceRecord.action",
					datatype: "json",
					page:1,
					mtype:"post"
				});
				$("#HouseForMemberAddObjectList").setPostData(dataJson);
			    $("#HouseForMemberAddObjectList").trigger("reloadGrid");
			}else{
				populationDivShow();
				$("#PopulationForMemberAddObjectList").setGridParam({
				    url:"${path}/plugin/serviceTeam/serviceObject/findPopulationsForServiceRecord.action",
					datatype: "json",
					page:1,
					mtype:"post"
				});
				$("#PopulationForMemberAddObjectList").setPostData(dataJson);
			    $("#PopulationForMemberAddObjectList").trigger("reloadGrid");
			}
		}
	});
	
	if("${(from)!}"=="<@s.property value='@com.tianque.plugin.serviceTeam.serviceObject.controller.ServiceObjectController@FROM_SERVICE_MEMBER'/>"){
		$("#serviceMemberInTeam").show();
		getMemberInTeamOption();
	}
	function getMemberInTeamOption(){
			$.ajax({
			async: false,
			url: "${path}/plugin/serviceTeam/serviceObject/getMemberInTeam.action",
			data:{
				"memberIdStrs":'${(memberIdStrs)!}'
			},
			success:function(list){
				for(var j = 0 ;j<=list.length;j++){
					var map = list[j];
					for(var i in map){ 
						$("#serviceMemberInTeamSelect").append("<option value='"+i+"'>"+map[i]+"</option>");
					}
				}
			}
		});
	}
});

function populationDivShow()
{
	$("#populationListDiv").show().siblings().hide();
	$("#populationInfoDiv").show().siblings(".searchInfoBox").hide();
}
function locationDivShow()
{
	$("#locationListDiv").show().siblings().hide();
	$("#locationInfoDiv").show().siblings(".searchInfoBox").hide();
}
function houseDivShow()
{
	$("#houseListDiv").show().siblings().hide();
	$("#houseInfoDiv").show().siblings(".searchInfoBox").hide();
}
function getSelectIds(){
	var bigType=$("#populationBigType").val();
	var selectedIds="";
	if(bigType=="IMPORTANTPLACE" || bigType=="actualCompany" || bigType=="doubleNew" || bigType=="ENTERPRISE"){
		selectedIds = $("#LocationForMemberAddObjectList").jqGrid("getGridParam", "selarrrow");
	}else if(bigType=="ACTUALHOUSE"){
		selectedIds = $("#HouseForMemberAddObjectList").jqGrid("getGridParam", "selarrrow");
	}else{
		 selectedIds = $("#PopulationForMemberAddObjectList").jqGrid("getGridParam", "selarrrow");
	}
	return selectedIds;
}

function addServiceObjectForMember(){
	var bigType=$("#populationBigType").val();
	var selectedIds = getSelectIds();
	if(selectedIds=="" || selectedIds==null){
		$.messageBox({message:"没有选择对象！",level: "error"});
		return;
	}
	if(""!="${(objectType)!}" && $("#populationType").val()!="${(objectType)!}"){	
		var str="与之前选择的对象类型不同，新类型数据将覆盖原先存在的数据，是否继续";
		var returnData;
		$.confirm({
			title:"确认类型",
			message:str,
			okFunc: function() {							
				$("#serviceObjects").clearPersonnelComplete();
				var data=new Array();
				for(var i=0;i<selectedIds.length;i++){
					if(bigType=="IMPORTANTPLACE" || bigType=="actualCompany" || bigType=="doubleNew" || bigType=="ENTERPRISE"){
						$("#objectBigType").val("notPopulation");
						var val={"id":selectedIds[i],
								"name":$("#LocationForMemberAddObjectList").getRowData(selectedIds[i]).locationName,
								"type":$("#populationType").val()
								};
					}else if(bigType=="ACTUALHOUSE"){
						$("#objectBigType").val("notPopulation");
						var val={"id":selectedIds[i],
								"name":$("#HouseForMemberAddObjectList").getRowData(selectedIds[i]).houseAddress,
								"type":$("#populationType").val()
								};
					}else{
						$("#objectBigType").val("");
						$("#recordType").val("");	
						$("input[name='serviceRecord.recordType']").attr("checked",false);
						var val={"id":selectedIds[i],
								"name":$("#PopulationForMemberAddObjectList").getRowData(selectedIds[i]).name,
								"type":$("#populationType").val()
								};
					}
					$("#serviceObjects").setPersonnelCompleteVal({value:val.id+"-"+val.name+"-"+val.type,label:val.name,desc:""}); 
					
				}
				recordTypeDivControl();
				$("#objectType").val($("#populationType").val());
				$("#objectSearchDialog").dialog("close");
			}
		});
	}else{
		if("${(serviceObjects)!}"==""){
			var data=new Array();
			for(var i=0;i<selectedIds.length;i++){
				if(bigType=="IMPORTANTPLACE" || bigType=="actualCompany" || bigType=="doubleNew" || bigType=="ENTERPRISE"){
					$("#objectBigType").val("notPopulation");
					var val={"id":selectedIds[i],
							"name":$("#LocationForMemberAddObjectList").getRowData(selectedIds[i]).locationName,
							"type":$("#populationType").val()
							};
				}else if(bigType=="ACTUALHOUSE"){
					$("#objectBigType").val("notPopulation");
					var val={"id":selectedIds[i],
							"name":$("#HouseForMemberAddObjectList").getRowData(selectedIds[i]).houseAddress,
							"type":$("#populationType").val()
							};
				}else{
					$("#objectBigType").val("");
					$("#recordType").val("");	
					$("input[name='serviceRecord.recordType']").attr("checked",false);
					var val={"id":selectedIds[i],
							"name":$("#PopulationForMemberAddObjectList").getRowData(selectedIds[i]).name,
							"type":$("#populationType").val()
							};
				}
				$("#serviceObjects").setPersonnelCompleteVal({value:val.id+"-"+val.name+"-"+val.type,label:val.name,desc:""}); 
			}
			recordTypeDivControl();
			$("#objectType").val($("#populationType").val());
			$("#objectSearchDialog").dialog("close");
		}else{
			var objects="${(serviceObjects)!}".split(",");
			var objectIds=objects;
			var data=new Array();
			//先获取原先存在的所有对象
			for(var i=0;i<objectIds.length;i++)
			{
				data[i]={"id":objects[i].split("-")[0],"name":objects[i].split("-")[1],"type":objects[i].split("-")[2]};
				objectIds[i]=objectIds[i].split("-")[0];
			}
			//遍历选中的行数据，与之前重复的不进行添加
			for(var j=0;j<selectedIds.length;j++){
				var isDuplicate=true;
				for(var k=0;k<objectIds.length;k++)
				{
					if(objectIds[k]==selectedIds[j]){
						isDuplicate=false
						break;			
					}	
				}
				if(isDuplicate){
					if(bigType=="IMPORTANTPLACE" || bigType=="actualCompany" || bigType=="doubleNew" || bigType=="ENTERPRISE"){
						$("#objectBigType").val("notPopulation");
						var val={"id":selectedIds[j],
								"name":$("#LocationForMemberAddObjectList").getRowData(selectedIds[j]).locationName,
								"type":$("#populationType").val()
								};
					}else if(bigType=="ACTUALHOUSE"){
						$("#objectBigType").val("notPopulation");
						var val={"id":selectedIds[j],
								"name":$("#HouseForMemberAddObjectList").getRowData(selectedIds[j]).houseAddress,
								"type":$("#populationType").val()
								};
					}else{
						$("#objectBigType").val("");
						$("#recordType").val("");	
						$("input[name='serviceRecord.recordType']").attr("checked",false);
						var val={"id":selectedIds[j],
								"name":$("#PopulationForMemberAddObjectList").getRowData(selectedIds[j]).name,
								"type":$("#populationType").val()
								};	
					}
					recordTypeDivControl();
					$("#serviceObjects").setPersonnelCompleteVal({value:val.id+"-"+val.name+"-"+val.type,label:val.name,desc:""}); 
				}
			}
			$("#objectSearchDialog").dialog("close");
		}
	}
}
function recordTypeDivControl(){
	if($("#objectBigType").val()=="" && $("#recordType").val()==""){
		$("#recordTypeDiv").hide();
	}else{
		$("#recordTypeDiv").show();
	}
}

//服务成员角度绑定服务对象
function addServiceObjectFromMember(){
			var selectedIds = getSelectIds();
			if(selectedIds=="" || selectedIds==null){
				$.messageBox({message:"没有选择对象！",level: "error"});
				return;
			}
			$.ajax({
			async: false,
			url: "${path}/plugin/serviceTeam/serviceObject/addServiceObjectFromMember.action",
			data:{
				"memberIdAndTeamId":$("#serviceMemberInTeamSelect").val(),
				"objectType":$("#populationType").val(),
				"objectIdsAndNames":getSelectIdsAndNames()
				
			},
			success:function(data){
					$.messageBox({message:"为服务成员添加服务对象成功！"});
					$("#objectSearchDialog").dialog("close");
			}
		});
}


function getSelectIdsAndNames(){
var selectedIds = getSelectIds();

var bigType=$("#populationBigType").val();
	if(bigType=="IMPORTANTPLACE" || bigType=="actualCompany" || bigType=="doubleNew" || bigType=="ENTERPRISE"){
		if(null !=selectedIds && selectedIds.length>0){
			var str="";
			for(var i=0;i<selectedIds.length;i++){
			str += selectedIds[i]+"-"+$("#LocationForMemberAddObjectList").getRowData(selectedIds[i]).locationName+",";
			}
		}
		
	}else if(bigType=="ACTUALHOUSE"){
		if(null !=selectedIds && selectedIds.length>0){
			var str="";
			for(var i=0;i<selectedIds.length;i++){
			str += selectedIds[i]+"-"+$("#HouseForMemberAddObjectList").getRowData(selectedIds[i]).houseAddress+",";
			}
		}
	}else{
		if(null !=selectedIds && selectedIds.length>0){
			var str="";
			for(var i=0;i<selectedIds.length;i++){
			str += selectedIds[i]+"-"+$("#PopulationForMemberAddObjectList").getRowData(selectedIds[i]).name+",";
			}
		}
	}
	return str;
}

function orgLevelLessEqual(orgId,level){
	var bol = false;
	$.ajax({
		async:false,
		url:PATH+"/tools/org/levelCompare.action",
		data:{
			"orgId":orgId,
			"orgLevel":level
		},
		success:function(responseData){
			bol = responseData<=0;
		}
	});
	return bol;
}
</script>