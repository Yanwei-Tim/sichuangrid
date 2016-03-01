<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%
  String speechType = request.getParameter("speechType");
  if("00".equalsIgnoreCase(speechType)){
	  request.setAttribute("title", "心得体会");
	  request.setAttribute("moduleName", "FeelingExperience");
  }else if("01".equalsIgnoreCase(speechType)){
	  request.setAttribute("title", "服务评价");
	  request.setAttribute("moduleName", "ServeAssess");
  }
  request.setAttribute("speechType", speechType);
%>
<div class="btnbanner btnbannerData">
</div>
<div class="content">
	<div class="ui-corner-all" id="nav">
		<pop:JugePermissionTag ename="add${moduleName}">
			<a id="add" href="javascript:void(0)"><span><strong
					class="ui-ico-xz"></strong>新增</span>
			</a>
		</pop:JugePermissionTag>
			<a id="update" href="javascript:void(0)"><span><strong
					class="ui-ico-xg"></strong>修改</span>
			</a>
			<a id="view" href="javascript:void(0)"><span><strong
					class="ui-ico-cakan"></strong>查看</span>
			</a>
			<a id="delete" href="javascript:void(0)"><span><strong
					class="ui-ico-sc"></strong>删除</span>
			</a>
		<a id="reload" href="javascript:void(0)"><span><strong
				class="ui-ico-refresh"></strong>刷新</span>
		</a>
	</div>

	<div style="clear: both;"></div>
	 <div style="width: 100%;" id="speechList">
		
	</div>
	<div id="${moduleName}Dialog"></div>
</div> 
<script type="text/javascript">

function onOrgChanged(orgId, isgrid){
	$.ajax({
		url:'${path}/baseinfo/speechManage/dispatchOperate.action?mode=list&speechType=${speechType}&orgId='+getCurrentOrgId(),
		success:function(data){
			//var html="";
			//for(var i=0;i<data.length;i++){
			//	html=html+"<ul><li>"+
			//	"<a href='javascript:void(0)' onclick='viewSpeech("+data[i].id+")'>"+data[i].title+"</a>"+
			//  "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>"+data[i].createUserRealName+"</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
			//	"<span>"+data[i].createDate+"</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
			//	"<span><pop:JugePermissionTag ename='delete${moduleName}'><a href='javascript:void(0)' onclick='deleteSpeech("+data[i].id+")'>删除</a></pop:JugePermissionTag></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
			//	"<span><pop:JugePermissionTag ename='update${moduleName}'><a href='javascript:void(0)' onclick='editSpeech("+data[i].id+")'>编辑</a></pop:JugePermissionTag></span></li></ul>";
			//}
			
			$("#speechList").html(data);
	    }
    });
	if(getCurrentOrgId()!= USER_ORG_ID){
		$("#add").buttonDisable();
	}else{
		$("#add").buttonEnable();
	}
	
}
//查看方法
function viewSpeech(speechId){
	$("#${moduleName}Dialog").createDialog({
		width: 800,
		height: 500,
		title:"查看${title}",
		url:"${path}/baseinfo/speechManage/dispatchOperate.action?mode=view&speechId="+speechId,
		buttons:{
	   		"关闭" : function(){
	        	$(this).dialog("close");
	   		}
		}
});
};

//删除方法
function deleteSpeech(speechId){
	$.confirm({
		title:"确认删除",
		message:"确定要删除吗?一经删除将无法恢复",
		okFunc: function(){
			$.ajax({
				url:"${path}/baseinfo/speechManage/deleteSpeech.action?speechId="+speechId,
				success:function(data){
					onOrgChanged(getCurrentOrgId(),isGrid());
				    $.messageBox({message:"已经成功删除该"+title+"信息!"});
			    }
		    });
	   }
 });
};

function editSpeech(speechId){
	$("#${moduleName}Dialog").createDialog({
		width: 800,
		height: 500,
		title:"修改${title}",
		url:"${path}/baseinfo/speechManage/dispatchOperate.action?mode=edit&dailogName=${moduleName}Dialog&speechId="+speechId,
		buttons:{
			"保存" : function(event){
   				$("#mainSpeechForm").submit();
	   		},
	   		"关闭" : function(){
	        	$(this).dialog("close");
	   		}
		}
});
}

$(document).ready(function(){
	
	if (getCurrentOrgId()!=null && getCurrentOrgId()!=""){
		onOrgChanged(getCurrentOrgId(),isGrid());
	}
	$("#add").click(function(event){
		$("#${moduleName}Dialog").createDialog({
				width: 800,
				height: 500,
				title:"编辑${title}",
				url:"${path}/baseinfo/speechManage/dispatchOperate.action?mode=add&dailogName=${moduleName}Dialog&speechType=${speechType}&orgId="+getCurrentOrgId(),
				buttons:{
					"保存" : function(event){
		   				$("#mainSpeechForm").submit();
			   		},
			   		"关闭" : function(){
			        	$(this).dialog("close");
			   		}
				}
		});
	});
	
	$("#reload").click(function(){
		onOrgChanged(getCurrentOrgId(),isGrid());
	});
	
	
	

	

	
});



</script>

