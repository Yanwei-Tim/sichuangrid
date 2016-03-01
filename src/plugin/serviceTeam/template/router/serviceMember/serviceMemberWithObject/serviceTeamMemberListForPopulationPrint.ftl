<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<input type="hidden" name="module" id="module" value="${(module)! }" /> 
<input type="hidden" name="id" id="objectId" value="${(id)! }" /> 

		<table id="serviceTeamMemberList"></table>

<script type="text/javascript">


$(document).ready(function(){
<@pop.formatterProperty name="gender" domainName="@com.tianque.domain.property.PropertyTypes@GENDER" />
<@pop.formatterProperty name="position" domainName="@com.tianque.plugin.serviceTeam.util.Constants$ServiceTeamPositionPropertyTypes@TEAMPOSITION" />

	//服务成员列表
	$("#serviceTeamMemberList").jqGridFunction({
		mtype:'post',
		datatype: "local",
		colModel:[
		  {name:"id",index:"id",width:50,hidden:true}, 
		  {name:"teamId",index:"teamId",hidden:true},
	      {name:"memberId",index:"memberId",width:50,hidden:true},
	      {name:"memberName",index:'memberName',label:'姓名',sortable:false,width:80},
	      {name:"gender",label:'性别',sortable:false,formatter:genderFormatter,width:70,align:"center"},
	      {name:"position",label:'职务(身份)',sortable:false,formatter:positionOrRelationFormatter,width:120},
		  {name:'teamName',label:'所在团队',sortable:false,width:130},
	      {name:'mobile',label:'联系手机',sortable:false, width:110,align:"center"},
	      {name:"idCardNo",index:'idCardNo',label:'身份证号码',sortable:false,width:130,align:"center"},
	      {name:"homePhone",index:'homePhone',label:'固定电话',sortable:false,width:100,align:"center"},
	      {name:"remark",index:'remark',label:'备注',sortable:false,width:200},
	      {name:'onDuty',label:'是否在职',formatter:onDutyFormatter,sortable:false, width:80,hidden:true},
	      {name:'teamMember',label:'是否为团队成员',sortable:false, width:80,hidden:true}
	      
		],
		showColModelButton:false,
		
		rowNum:Math.pow(2,31)-1,
		//width:755,
		shrinkToFit:true,
		//autowidth:false,
		height:'auto',
		
		sortname:'id'
	});
	$("#serviceTeamMemberList").jqGrid('setFrozenColumns');
	loadTeamMembers();
	

    //$("#serviceTeamMemberList").setGridWidth(dialogWidth-245);  

	

	//加载服务成员
	function loadTeamMembers() {
	//var personData = $("#"+$('#module').val()+"List").getRowData($("#objectId").val());		
		var listParam = null;
		
			listParam = {
				'serviceMemberVo.objectType':$('#module').val(),
				'serviceMemberVo.objectId':$("#objectId").val()
			};
	
		loadData(listParam);
	}
	//加载服务成员数据
	function loadData(listParam) {
		$("#serviceTeamMemberList").setGridParam({
			url:'${path}/plugin/serviceTeam/router/routerManage/findServiceMembersByServiceMemberVo.action',
			datatype: "json",
			page:1
		});
		$("#serviceTeamMemberList").setPostData(listParam);
		$("#serviceTeamMemberList").trigger("reloadGrid");
	}
	//是否在职
	function onDutyFormatter(el,options,rowData){
		if(rowData.onDuty==0){
			return "否";
		}else{
			return "是";
		}
	}
	
 function positionOrRelationFormatter(el,options,rowData){
		if(rowData.teamMember==1){
		    if(!(rowData.position+""=="undefined")){
		    return positionData[rowData.position.id];	
		    }else{
		      return  "";	
		    }
		}
		if(rowData.teamMember==0){
			return rowData.relation;
		}
	}
	
});
</script>