<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div class="btnbanner btnbannerData">
	<div class="ui-widget autosearch">
	 <input type="text" value="请输入标题信息" id="searchByCondition" maxlength="18"
			class="searchtxt" onblur="value=(this.value=='')?'请输入标题信息':this.value;"
	onfocus="value=(this.value=='请输入标题信息')?'':this.value;" />
		<button id="refreshOrgTree1"
			class="ui-icon ui-icon-refresh searchbtnico"></button>
	</div>
	<a href="javascript:;" id="searchByConditionButton"><span>检索</span></a>
</div>

<div class="content">
	<div class="ui-corner-all" id="nav">
		<pop:JugePermissionTag ename="addPartyConstructionFiles">
			<a id="add" href="javascript:void(0)"><span><strong
					class="ui-ico-xz"></strong>新增</span>
			</a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="updatePartyConstructionFiles">
			<a id="update" href="javascript:void(0)"><span><strong
					class="ui-ico-xg"></strong>修改</span>
			</a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="viewPartyConstructionFiles">
			<a id="view" href="javascript:void(0)"><span><strong
					class="ui-ico-cakan"></strong>查看</span>
			</a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="deletePartyConstructionFiles">
			<a id="delete" href="javascript:void(0)"><span><strong
					class="ui-ico-sc"></strong>删除</span>
			</a>
		</pop:JugePermissionTag>
		<a id="reload" href="javascript:void(0)"><span><strong
				class="ui-ico-refresh"></strong>刷新</span>
		</a>
	</div>
	<div style="clear: both;"></div>
	<div style="width: 100%;" id="partyConstructionFilesList" >
		<s:iterator var="partyConstructionFiles"  value="list">
		  ${partyConstructionFiles.title}
		</s:iterator>
	</div>
	<div id="partyConstructionFilesDialog"></div>
</div>

<div style="display: none;">
	<pop:JugePermissionTag ename="partyConstructionFiles">
		<span id="title"><s:property value="#request.name" />
		</span>
	</pop:JugePermissionTag>
</div>
<script type="text/javascript">
var dialogWidth=800;
var dialogHeight=400;
var leibie=new Array(3);
leibie[0]=["党建制度","定期会议制度  ","平等协商议事制度","双重组织生活记录","意见建议收集反馈制度","成员单位党组织联系社区制度"];
leibie[1]=["党建计划","年度工作活动计划","每月工作活动计划","会议计划"];
leibie[2]=["考核评审","考核评估评分标准","单位参与审核标准"];
leibie[3]=["会议文件","会议意见征求稿","会议议程和内容"];
var title=$("#title").html();
function onOrgChanged(orgId, isgrid){
	if(getCurrentOrgId()!= USER_ORG_ID){
		$("#add").buttonDisable();
	}else{
		$("#add").buttonEnable();
	}
	$.ajax({
		url:'${path}/baseinfo/partyConstructionFilesManage/findpartyConstructionFiles.action',
		data:{"organizationId":orgId},		
		success:function(data){
			var html="";
			for(var i=0;i<data.length;i++){
				html=html+
				"<ul >"+
				"<li id='"+data[i].id+"'> <table style='font-size:20px;font-family: 宋体'><tr><td width=50% style='font-size:20px;font-family: 宋体'>&middot;"+
				"<pop:JugePermissionTag ename='viewPartyConstructionFiles'>"+
				"<a href='javascript:void(0)' onclick='viewPartyConstructionFilesInfo("+data[i].id+")'>"+data[i].title+"</a></pop:JugePermissionTag></td>"+
				"<td width=20% style='font-size:20px;font-family: 宋体'><span>("+data[i].createDate+")</span></td>"+
				"<td width=10% style='font-size:20px;font-family: 宋体'><pop:JugePermissionTag ename='updatePartyConstructionFiles'>"+
				"<a id='update' href='javascript:void(0)' onclick='update("+data[i].id+")' >"+
				"<span><strong class='ui-ico-sc'></strong>修改</span></a></pop:JugePermissionTag></td>"+
				"<td width=10% style='font-size:20px;font-family: 宋体'><pop:JugePermissionTag ename='deletePartyConstructionFiles'>"+
				"<a id='delete' href='javascript:void(0)' onclick='del("+data[i].id+")' >"+
				"<span><strong class='ui-ico-sc'></strong>删除</span></a></pop:JugePermissionTag></td></tr></table>"+"</li></ul>";
			}
			$("#partyConstructionFilesList").html(html);
			

	    }
    });
}

$(document).ready(function(){
	
	
	$("#update").buttonDisable();
	$("#view").buttonDisable();
	$("#delete").buttonDisable();
	
	$("#add").click(function(event){
		if($(this).attr("disabled")=="disabled"){
			return;
		}
		if (getCurrentOrgId()==null||getCurrentOrgId()==""){
			$.notice({level:"warn", message:"请先选择一个部门"});
		}else{
		$("#partyConstructionFilesDialog").createDialog({
			width:dialogWidth,
			height:dialogHeight,
			title:'新增'+title+'信息',
			url:'${path}/baseinfo/partyConstructionFilesManage/dispatchOperate.action?mode=add&dailogName=partyConstructionFilesDialog&organizationId='+getCurrentOrgId(),
			buttons: {
					"保存" : function(event){
			   			$("#maintainForm").submit();
			   			
		   				},
			   		"关闭" : function(){
			        	$(this).dialog("close");
			   		}
			}
		});
		}
	  });
	
	
	


	if (getCurrentOrgId()!=null && getCurrentOrgId()!=""){
		onOrgChanged(getCurrentOrgId(),isGrid());
	}	

	$("#refreshOrgTree1").click(function(){
		$("#searchByCondition").attr("value","");
	});
	$("#reload").click(function(){
		onOrgChanged(getCurrentOrgId(),isGrid());
	});
	function search(orgId){
		var condition = $("#searchByCondition").val();
		$.ajax({
			url:'${path}/baseinfo/partyConstructionFilesManage/searchPartyConstructionFiles.action',
			data:{"organizationId":orgId,"partyConstructionFiles.title":condition},		
			success:function(data){
				var html="";
				for(var i=0;i<data.length;i++){
					html=html+
					"<ul >"+
					"<li id='"+data[i].id+"'> <table style='font-size:20px;font-family: 宋体'><tr><td width=50% style='font-size:20px;font-family: 宋体'>&middot;"+
					"<pop:JugePermissionTag ename='viewPartyConstructionFiles'>"+
					"<a href='javascript:void(0)' onclick='viewPartyConstructionFilesInfo("+data[i].id+")'>"+data[i].title+"</a></pop:JugePermissionTag></td>"+
					"<td width=20% style='font-size:20px;font-family: 宋体'><span>("+data[i].createDate+")</span></td>"+
					"<td width=10% style='font-size:20px;font-family: 宋体'><pop:JugePermissionTag ename='updatePartyConstructionFiles'>"+
					"<a id='update' href='javascript:void(0)' onclick='update("+data[i].id+")' >"+
					"<span><strong class='ui-ico-sc'></strong>修改</span></a></pop:JugePermissionTag></td>"+
					"<td width=10% style='font-size:20px;font-family: 宋体'><pop:JugePermissionTag ename='deletePartyConstructionFiles'>"+
					"<a id='delete' href='javascript:void(0)' onclick='del("+data[i].id+")' >"+
					"<span><strong class='ui-ico-sc'></strong>删除</span></a></pop:JugePermissionTag></td></tr></table>"+"</li></ul>";
				}
				$("#partyConstructionFilesList").html(html);
				
			}
		});
		
	}
	$("#searchByConditionButton").click(function(){
		search(getCurrentOrgId());
		
	});
});



function viewPartyConstructionFilesInfo(id){
	if(id==null){
 		return;
	}
	$("#partyConstructionFilesDialog").createDialog({
		width: dialogWidth,
		height: 300,
		title:'查看'+title+'信息',
		modal : true,
		url:'${path}/baseinfo/partyConstructionFilesManage/dispatchOperate.action?mode=view&partyConstructionFiles.id='+id,
		buttons: {
		   "关闭" : function(){
		        $(this).dialog("close");
		   }
		}
	});
}

function update(id){
	$("#partyConstructionFilesDialog").createDialog({
		width:dialogWidth,
		height:dialogHeight,
		title:"修改"+title+"信息",
		url:'${path}/baseinfo/partyConstructionFilesManage/dispatchOperate.action?mode=edit&partyConstructionFiles.id='+id,
		buttons: {
			"保存" : function(event){
		      $("#maintainForm").submit();
		    
		   },
		   "关闭" : function(){
		        $(this).dialog("close");
		   }
		}
	});
}


function del(id){
	$.confirm({
		title:"确认删除",
		message:"确定要删除吗?一经删除将无法恢复",
		okFunc: function(){
			$.ajax({
				url:"${path}/baseinfo/partyConstructionFilesManage/deletePartyConstructionFiles.action?ids="+id,
				success:function(data){
					var datas=data.split(",");
				    $.messageBox({message:"已经成功删除该"+title+"信息!"});
				    onOrgChanged(getCurrentOrgId(),isGrid());
			    }
		    });
	   }
 });
}


</script>