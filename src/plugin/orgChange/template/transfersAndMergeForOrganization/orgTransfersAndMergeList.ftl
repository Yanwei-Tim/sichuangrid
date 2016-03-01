<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<style>
	.center-left #refreshTransfersAndMergeOrgTree{border:0; background:none; position:absolute; top:0; right:0; cursor:pointer; outline:none;width:30px;height:28px;}
</style>
<div class="center-left">
	<div>
		<@s.if test="operate.equals('transfers')">
		所选组织为被迁移组织
		</@s.if>
		<@s.if test="operate.equals('meger')">
		所选组织为被合并组织
		</@s.if>
	</div>
	<div >
		<div class="ui-widget">
			<input id="orgTree_autocomplete" type="text" value=""/>
			<button id="refreshTransfersAndMergeOrgTree" class="ui-icon-refresh"></button>
		</div>
	</div>
	<div class="orgTree">
		<div id="orgTreeTransfersAndMerge">
		</div>
	</div>
</div>

<div class="center-right">
	<div class="content">
		<div id="content-top">
			<div class="ui-corner-all" >
				<@s.if test="operate.equals('transfers')">
				<label >迁移至：</label><span id="orgName-toCity-id">
				</span>
				</@s.if>
				<@s.if test="operate.equals('meger')">
				<label >合并至：</label><span id="orgName-toCity-id">
				</span>
				</@s.if>
			</div>
			<div style="clear:both">
			</div>
			<div class="ui-corner-all" id="nav">
				<a id="addForMerge" href="javascript:;"><span>添加 </span></a>
				<a id="removeForMerge" href="javascript:;"><span>移除</span></a>
			</div>
			
			<div style="clear:both">
			</div>
		</div>
		<div style="width: 100%">
			<table id="orgChildren-dataGrid-TransfersAndMerge">
			</table>
			<div id="orgChildren-dataGrid-TransfersAndMergePager">
			</div>
		</div>
	</div>
</div>
<input type="hidden" id="organizationIdFormerge" value="${(organization.id)!}" />
<input type="hidden" id="operateId" value="${(operate)!}" />
<script type="text/javascript" >
var  orgjosn=<@s.action name="findCityOrgId" var="cityOrganization" executeResult=true namespace="/orgchange/orgChangeInfoManage" >
<@s.param name="organization.id" value="#organization.id"></@s.param>
</@s.action>;
var rootOrgId=orgjosn.id;
//获得市的orgid
var choseOrgId=$("#organizationIdFormerge").val();
var transfersAndMergeTree;
var node;
var valOrgId;
var idstr;

$(function(){
	var centerHeight=$("div.ui-layout-center").outerHeight();
	$(".orgTreeTransfersAndMerge").height(centerHeight-70);
	
	$("#orgChildren-dataGrid-TransfersAndMerge").jqGridFunction({
	   	colNames:['id','部门名称', '部门类型','部门级别','部门联系电话', '备注'],
	   	colModel:[
	   		{name:'id',index:'id',hidden:true,sortable:false},
	   		{name:'orgName',index:'orgName',sortable:true},
	   		{name:'orgType.id',index:'orgType.id',sortable:true,formatter:orgType},
	   		{name:'orgLevel.id',index:'orgLevel.id', sortable:true,formatter:orgLevel},
	   		{name:'contactWay',index:'contactWay', sortable:true},
	   		{name:'remark',index:'remark', sortable:false}
	   	],
	    rowNum:-1,
	    pager:false,
	    shrinkToFit:true,
	    showColModelButton:false,
	    height:$(".ui-layout-west").height()-$(".path").height()-$("#content-top").height()+10
	});
	
		//设置组织机构树最高从市开始
		transfersAndMergeTree=$("#orgTreeTransfersAndMerge").initTree({rootId:rootOrgId,allOrg:true});
	
	
	
	function afterChangNode(nodes){
		node=nodes;
		$("#orgTree_autocomplete").val(nodes.text);
	}
	$.addClick(transfersAndMergeTree,afterChangNode);
	
	//添加操作
	$("#addForMerge").click(function(){
		valOrgId=node.id;
		var orgIdCurrent=$("#organizationIdFormerge").val();
		var operateType=$("#operateId").val()
		
		if(!isAcrossCity(valOrgId,orgIdCurrent)){
			$.messageBox({level:'warn',message:"只能同一市内进行迁移合并操作"});
			return;
		}
	    var levelSameTemp=judgeAddAvailable(orgIdCurrent,valOrgId);
		if(!levelSameTemp){
			if(operateType=="meger"){
				$.messageBox({level:'warn',message:"请选择与目标层级同一级别的节点添加！"});
			}else if(operateType=="transfers"){
				$.messageBox({level:'warn',message:"请选择比目标层级低一层级的节点添加！"});
			}
			return;
		}
		var row=$("#orgChildren-dataGrid-TransfersAndMerge").getRowData();
		 idstr=getrowDataIds(row);
		 //判断类表中是否已包含正要增加的节点
		var temp=judgeVal(row,valOrgId);
		if(!temp){
			$.messageBox({level:'warn',message:"列表中已存在此组织机构！"});
			return ;
		}
		if(operateType=="meger"){
			if( valOrgId == orgIdCurrent ){
				$.messageBox({level:'warn',message:"被合并的对象不能是自己！"});
				return;
			}
		}

		if( operateType=="transfers" ){
			if(checkTransferTargetOrg(orgIdCurrent,valOrgId)){
				$.messageBox({level:'warn',message:"所选部门已经在目标部门直属下级！"});
				return;
			}
		}
		
		$("#orgChildren-dataGrid-TransfersAndMerge").setGridParam({
		url:'${path}/orgchange/orgChangeInfoManage/ajaxOrgPage.action',
		datatype: "json",
		page:1
	});
	$("#orgChildren-dataGrid-TransfersAndMerge").setPostData({
		"orgIds":idstr+valOrgId
	});
	$("#orgChildren-dataGrid-TransfersAndMerge").trigger("reloadGrid");
	});
	
	
	//迁移和合并是否跨市操作
	function isAcrossCity(oldOrgId,newOrgId){
		$.ajax({
			async:false,
			url:"${path}/transferManage/isSameCity.action",
			data:{
				"toOrgId":newOrgId,
				"orgId":oldOrgId
			},
			success:function(responseData){
				bol = responseData;
			}
		});
		return bol;
	}
	
	
	function judgeVal(row,valOrgId){
		for(var i=0;i<row.length;i++){
			if(row[i]["id"]==valOrgId){
				return false;
			}
		}
		return true;
		
	}
	
	//移除操作
	$("#removeForMerge").click(function(){
		var row=$("#orgChildren-dataGrid-TransfersAndMerge").getRowData();
		var selectedId = $("#orgChildren-dataGrid-TransfersAndMerge").getGridParam("selrow");
		if(selectedId==null || selectedId==''){
			$.messageBox({level:'warn',message:"请至少选中一行选择移除！"});
				return;
		}
		removeId(row,selectedId);
		var idStr=getrowDataIds(row);
		
		$("#orgChildren-dataGrid-TransfersAndMerge").setGridParam({
		url:'${path}/orgchange/orgChangeInfoManage/ajaxOrgPage.action',
		datatype: "json",
		page:1
	});
	$("#orgChildren-dataGrid-TransfersAndMerge").setPostData({
		"orgIds":idStr
	});
	$("#orgChildren-dataGrid-TransfersAndMerge").trigger("reloadGrid");
		
	});
	
	//移除列表中的id为selectedId的对象
	function removeId(row,selectedId){
		for (var i=0; i<row.length; i++) {  
          if (row[i]["id"] == selectedId) {  
              row.splice(i, 1);    
          }  
      }
	}
	
	//将列表中所有对象的id转换成以逗号分隔的字符串传入后台
	function getrowDataIds(row){
		if(row.length==0){
			return "";
		}
		var ids="";
		for(var i=0;i<row.length;i++){
			ids+=row[i]["id"]+",";
		}
		return ids;
		
	}
	//迁移和合并组织机构选择时规则不同
	function judgeAddAvailable(orgId,nodeId){
		var levelSameTemp=false;
		var operateType=$("#operateId").val();
			$.ajax({
				async: false,
				url:PATH+"/orgchange/orgChangeInfoManage/judgeIsSameLevel.action?organization.id="+orgId+"&targetOrganization.id="+nodeId+"&operate="+operateType,
				success:function(data){
					if(data==true){
						levelSameTemp=true;
					}else if(data==false){
						levelSameTemp=false;
					}
				}
			});
			return levelSameTemp;
	}
	
	//判断被迁移的组织机构的目标层级是否为当前的父层级
	function checkTransferTargetOrg(orgId,nodeId){
		var currentParentOrg=false;
		var operateType=$("#operateId").val();
			$.ajax({
				async: false,
				url:PATH+"/orgchange/orgChangeInfoManage/equalsTargetOrgCurrentParentOrg.action?organization.id="+orgId+"&targetOrganization.id="+nodeId+"&operate="+operateType,
				success:function(data){
					if(data==true){
						currentParentOrg=true;
					}else if(data==false){
						currentParentOrg=false;
					}else{
						$.messageBox({level:'error',message:data});
						return;
					}
				}
			});
			return currentParentOrg;
	}
	
	
	//填充迁移到的组织机构名称
	$.ajax({
			url: PATH+"/orgchange/orgChangeInfoManage/findOrgToCityRelativeNameByOrgId.action?id="+choseOrgId,
			success:function(responseData){
				$("#orgName-toCity-id").html(responseData);
			}
		});
		
	$("#orgTree_autocomplete").autocomplete({
		source: function(request, response) {
			$.ajax({
				url: PATH+"/sysadmin/orgManage/ajaxFindOrganizationsByOrgName.action",
				data:{"organization.orgName":function(){return request.term;}},
				success: function(data) {
					response($.map(data, function(item) {
						return {
							label: item.orgName+","+stringFormatter(item.contactWay),
							value: item.orgName,
							id: item.id
						}
					}))
				},
				error : function(){
					$.messageBox({
						message:"搜索失败，请重新登入！",
						level:"error"
					});
				}
			})
		},
		select: function(event, ui) {
			$.ajax({
				url:PATH+"/sysadmin/orgManage/ajaxSearchParentNodeIds.action?organization.id="+ui.item.id,
				success:function(data){
					$.searchChild(transfersAndMergeTree,data);
				}
			});
		}
	});
	$("#refreshTransfersAndMergeOrgTree").click(function(){
		$.selectRootNode(transfersAndMergeTree);
	});
});	

</script>
