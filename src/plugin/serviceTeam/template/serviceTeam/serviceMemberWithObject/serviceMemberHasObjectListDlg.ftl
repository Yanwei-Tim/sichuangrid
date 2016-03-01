<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<@s.include value="${path}/baseinfo/commonPopulation/jsFunctionInclude.jsp">
	<@s.param name="moduleName">ServiceRecord</@s.param>
</@s.include>

<div class="container container_24">

<form id="" method="post">
	<input type="hidden" id="memberIds"  value="${(serviceTeamMemberVo.memberIds)!}"/>
	<input type="hidden" id="memberName"  value="${(serviceTeamMemberVo.name)!}"/>
	<div class="grid_7">
		<select name="serviceObjectVo.objectBigType" id="objectBigType" class="form-select {isSelect:true,message@s.{isSelect:'请选择类型'}}">
			<option value="population">人口</option>
			<option value="location">组织</option>
			<option value="house">房屋</option>
		</select>
	</div>
	<div class='clearLine'></div>
	<div class="grid_10">
		<input type="button"  id='addServiceObject'  value='添加' class="defaultBtn" />
	</div>
	<div class='clearLine'></div>
	<input type="hidden" value="0" name="serviceObjectVo.logOut"/>
</form>

<div class='clearLine'></div>
	<div id="objectSearchDialog"></div>
<div class='clearLine'>&nbsp;</div>
<div style="width: 100%;margin-top: 6px">
	<div id="locationObjectListDiv">
		<table id="locationForMemberAddObjectList"> </table>
		<div id="locationForMemberAddObjectListPager"></div>
	</div>
	<div id="populationObjectListDiv">
		<table id="populationForMemberAddObjectList"> </table>
		<div id="populationForMemberAddObjectListPager"></div>
	</div>
	<div id="houseObjectListDiv">
		<table id="houseForMemberAddObjectList"> </table>
		<div id="houseForMemberAddObjectListPager"></div>
	</div>
</div>
</div>
<script>
$(document).ready(function(){
	<@pop.formatterProperty name="gender" domainName="@com.tianque.domain.property.PropertyTypes@GENDER" />
	<@pop.formatterProperty name="hiddenDangerLevel" domainName="@com.tianque.domain.property.PropertyTypes@HIDDEN_TROUBLE_LEVEL" />
	//房屋的危险程度，颜色显示
	function hiddenDangerLevelColor(el,options,rowData){
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

	$("#populationForMemberAddObjectList").jqGridFunction({
		datatype: "local",
	   	colModel:[
			{name:"objectId",index:"objectId",frozen:true,hidden:true}, 
			{name:"operate",index:'operate',label:'操作',formatter:operateFormatter_ServiceObject,width:100,align:'center',sortable:false},
	      	{name:"name",index:'name',label:'姓名',width:100,sortable:false},
	      	{name:"gender",label:'性别',formatter:genderFormatter,sortable:false,width:60,align:'center'},
	      	{name:'idCardNo',index:'idCardNo',label:'身份证号码',sortable:false,width:180},
	      	{name:'objectTypeCname',label:'详细类型',index:"objectTypeCname",sortable:false, width:180},
	      	{name:'mobileNumber',label:'联系手机',index:"mobileNumber",sortable:false, width:180},
	 	  	{name:'objectType',label:'详细类型',index:"objectType",sortable:false,hidden:true,frozen:true},
	 	  	{name:'organization.orgName',index:'organization.orgName',label:'所属区域(网格)',sortable:false,width:200}
		],
		height:128,
	    rowNum:5,
	    showColModelButton:false,
	    rowList:[5,10,15]
	});

	$("#locationForMemberAddObjectList").jqGridFunction({
		datatype: "local",
	   	colModel:[
			{name:"objectId",index:"objectId",frozen:true,hidden:true},
			{name:"operate",index:'operate',label:'操作',formatter:operateFormatter_ServiceObject,width:100,align:'center',sortable:false},    
	      	{name:"locationName",index:'locationName',label:'场所名称',width:100,sortable:false},
	      	{name:"address",index:'address',label:'场所地址',width:100,sortable:false},
	      	{name:'objectTypeCname',label:'详细类型',index:"objectTypeCname",sortable:false, width:180},
	 	  	{name:'objectType',label:'详细类型',index:"objectType",sortable:false,hidden:true,frozen:true},
	 	  	{name:'organization.orgName',index:'organization.orgName',label:'所属区域(网格)',sortable:false,width:200}
		],
		height:128,
	    rowNum:5,
	    showColModelButton:false,
	    rowList:[5,10,15]
	});
	
	$("#houseForMemberAddObjectList").jqGridFunction({
		datatype: "local",
	   	colModel:[
			{name:"objectId",index:"objectId",frozen:true,hidden:true},
			{name:"operate",index:'operate',label:'操作',formatter:operateFormatter_ServiceObject,width:100,align:'center',sortable:false},
			{name:"hiddenDangerLevel.id",index:'hiddenDangerLevel',label:'隐患等级',formatter :hiddenDangerLevelColor,width:100,sortable:false},
			{name:"rentalPerson",index:'rentalPerson',label:'出租人',width:100,sortable:false},
	      	{name:"houseAddress",index:'houseAddress',label:'房屋地址',width:200,sortable:false},
	      	{name:'objectTypeCname',label:'详细类型',index:"objectTypeCname",sortable:false, width:180},
	 	  	{name:'objectType',label:'详细类型',index:"objectType",sortable:false,hidden:true,frozen:true},
	 	  	{name:'organization.orgName',index:'organization.orgName',label:'所属区域(网格)',sortable:false,width:200}
		],
		height:128,
	    rowNum:5,
	    showColModelButton:false,
	    rowList:[5,10,15]
	});
	divDisplayControl();	
	$("#objectBigType").change(divDisplayControl);
	 
	function divDisplayControl(){
		var bigType=$("#objectBigType").val();
		if(bigType=="location"){
			$("#locationObjectListDiv").show().siblings().hide();
		}else if(bigType=="house"){
			$("#houseObjectListDiv").show().siblings().hide();
		}else{
			$("#populationObjectListDiv").show().siblings().hide();
		}
		getServiceObjectList();
	}
	
	
		$('#addServiceObject').click(function(event){
			openSelectObjects();
		});
		
	function openSelectObjects(){
		$("#objectSearchDialog").createDialog({
			width: 820,
			height: 500,
			title: '服务对象查询',
			url:"${path}/plugin/serviceTeam/serviceObject/dispatch.action?mode=selectObject&from=<@s.property value='@com.tianque.plugin.serviceTeam.serviceObject.controller.ServiceObjectController@FROM_SERVICE_MEMBER'/>&memberIdStrs="+$('#memberIds').val()+"&serviceObjects="+encodeURIComponent($('#memberName').val()),
			buttons: {
				"确认":function(event){
					addServiceObjectFromMember();
					divDisplayControl();
				},
				"关闭":function(){
					$(this).dialog("close");
					divDisplayControl();
				}
			}
		});
	}
});
//删除该成员下的服务对象绑定
function deleteObjectOperator(selectedId){
	var bigType=$("#objectBigType").val();
	var rowData=$("#"+bigType+"ForMemberAddObjectList").getRowData(selectedId);
	$.confirm({
		title:"确认删除",
		message:"确定要删除吗?",
		okFunc: function(){
			$.ajax({
				url:"${path}/plugin/serviceTeam/serviceObject/deleteServiceObjectFromMember.action?serviceMemberWithObject.objectId="+selectedId+"&serviceMemberWithObject.objectType="+rowData.objectType+"&memberIdStrs="+$('#memberIds').val(),
				success:function(data){
					if(data>0){
					    $.messageBox({message:"成功解除与该服务对象的服务关系!"});
					    $("#"+bigType+"ForMemberAddObjectList").trigger("reloadGrid");
					}else{
						$.messageBox({
							message:"解除与该服务对象的服务关系出错!",
							level:"warn"
						});
					}
				}
			});
		}
	});
}
//该成员所有服务对象列表显示（不限所在团队与层级关系）
function getServiceObjectList(){
	var bigType=$("#objectBigType").val();
	$("#"+bigType+"ForMemberAddObjectList").setGridParam({
		url:"${path}/plugin/serviceTeam/serviceObject/findObjectsForServiceTeamMember.action",
		datatype: "json",
		page:1,
		mtype:"post"
	});
	$("#"+bigType+"ForMemberAddObjectList").setPostData({
		"memberIdStrs":$("#memberIds").val(),
		"serviceObjectVo.objectBigType":bigType
	});
	$("#"+bigType+"ForMemberAddObjectList").trigger("reloadGrid"); 
}

</script>