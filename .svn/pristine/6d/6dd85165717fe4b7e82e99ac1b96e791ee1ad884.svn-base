<%@page contentType="text/html;charset=utf-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%request.setAttribute("organizationId",request.getParameter("organizationId"));%>
<input type="hidden"   id="modelName" value="党建新闻"/>
<pop:JugePermissionTag ename="addNewsPage">
<a href="javascript:void(0)" id="addNews">新增</a>
</pop:JugePermissionTag>
<div class="book_c_04">
	<ul class="party_list">
<s:iterator value="newsList" var="obj">
					
            		<li>
                        <a href="${path}/partyBuilding/article.jsp?organizationId=${organizationId}&model=news&objId=<s:property value='id'/>" target="_blank">${obj.title}</a>
                        <span><s:date name="#obj.createDate" format="yyyy-MM-dd"/></span>
                        <div class="btn_list" id="btn_list">
                        	<pop:JugePermissionTag ename='updateNewsPage'>
							<a id="edit" href="javascript:editNews(${id })" class="edit">修改</a>
							</pop:JugePermissionTag>
							<pop:JugePermissionTag ename='deleteNewsPage'>
							<a id="delete" href="javascript:deleteNews(${id })" class="delete">删除</a>
							</pop:JugePermissionTag>
						</div>
                    </li>
</s:iterator>
      </ul>
</div>
<div id="newsDialog"></div>
<script type="text/javascript">

$(".btn_list").hide();
$(".party_list li").hover(function(){
	$(this).children(".btn_list").show();
},function(){
	$(".btn_list").hide();
})

if($('#currentOrgId').val()!= USER_ORG_ID){
	$("#addNews").hide();
	$("#btn_list").hide();
}else{
	$("#addNews").show();
	$("#btn_list").show();
}

//删除方法
function deleteNews(newsId){
	$.confirm({
		title:"确认删除",
		message:"确定要删除吗?一经删除将无法恢复",
		okFunc: function(){
			$.ajax({
				url:"${path}/baseinfo/newsManage/deleteNews.action?newsId="+newsId,
				success:function(data){
					document.location.reload();
				    $.messageBox({message:"已经成功删除该"+title+"信息!"});
			    }
		    });
	   }
 });
}


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
	$("#addNews").click(function(event){
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
	
});

</script>
