<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div class="btnbanner btnbannerData">
</div>
<div class="content">
	<div class="ui-corner-all" id="nav">
		<pop:JugePermissionTag ename="addNews">
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
	<div style="width: 100%;" id="newsList" />
	<div id="newsDialog"></div>
</div> 
<script type="text/javascript">

function onOrgChanged(orgId, isgrid){
	$.ajax({
		url:'${path}/baseinfo/newsManage/dispatchOperate.action?mode=list&orgId='+getCurrentOrgId(),
		success:function(data){
			var html="";
			for(var i=0;i<data.length;i++){
				html=html+"<ul><li>"+
				"<a href='javascript:void(0)' onclick='viewNews("+data[i].id+")'>"+data[i].title+"</a>"+
				"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>"+data[i].createUserRealName+"</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
				"<span>"+data[i].createDate+"</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
				"<span><pop:JugePermissionTag ename='deleteNews'><a href='javascript:void(0)' onclick='deleteNews("+data[i].id+")'>删除</a></pop:JugePermissionTag></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
				"<span><pop:JugePermissionTag ename='updateNews'><a href='javascript:void(0)' onclick='editNews("+data[i].id+")'>编辑</a></pop:JugePermissionTag></span></li></ul>";
			}
			
			$("#newsList").html(html);
	    }
    });
	if(getCurrentOrgId()!= USER_ORG_ID){
		$("#add").buttonDisable();
	}else{
		$("#add").buttonEnable();
	}
	
}
//查看方法
function viewNews(newsId){
	$("#newsDialog").createDialog({
		width: 800,
		height: 500,
		title:"查看新闻",
		url:"${path}/baseinfo/newsManage/dispatchOperate.action?mode=view&newsId="+newsId,
		buttons:{
	   		"关闭" : function(){
	        	$(this).dialog("close");
	   		}
		}
});
};

//删除方法
function deleteNews(newsId){
	$.confirm({
		title:"确认删除",
		message:"确定要删除吗?一经删除将无法恢复",
		okFunc: function(){
			$.ajax({
				url:"${path}/baseinfo/newsManage/deleteNews.action?newsId="+newsId,
				success:function(data){
					onOrgChanged(getCurrentOrgId(),isGrid());
				    $.messageBox({message:"已经成功删除该"+title+"信息!"});
			    }
		    });
	   }
 });
};

function editNews(newsId){
	$("#newsDialog").createDialog({
		width: 800,
		height: 500,
		title:"修改${title}",
		url:"${path}/baseinfo/newsManage/dispatchOperate.action?mode=edit&dailogName=newsDialog&newsId="+newsId,
		buttons:{
			"保存" : function(event){
   				$("#mainNewsForm").submit();
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
		$("#newsDialog").createDialog({
				width: 800,
				height: 500,
				title:"编辑新闻",
				url:"${path}/baseinfo/newsManage/dispatchOperate.action?mode=add&dailogName=newsDialog&orgId="+getCurrentOrgId(),
				buttons:{
					"保存" : function(event){
		   				$("#mainNewsForm").submit();
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

