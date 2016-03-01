<%@page contentType="text/html;charset=utf-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%
  String speechType = request.getParameter("speechType");
  if("00".equalsIgnoreCase(speechType)){
	  request.setAttribute("title", "心得体会");
	  request.setAttribute("moduleName", "FeelingExperience");
  }else if("01".equalsIgnoreCase(speechType)){
	  request.setAttribute("title", "社会评价");
	  request.setAttribute("moduleName", "ServeAssess");
  }
  request.setAttribute("speechType", speechType);
  
%>
<%request.setAttribute("organizationId",request.getParameter("organizationId"));%>
<pop:JugePermissionTag ename="add${moduleName}Page">
<a href="javascript:void(0)" id="addSpeech">新增</a>
</pop:JugePermissionTag>
<div class="book_c_04">
	<input type="hidden" id="total" value="${total}"/>
	<ul class="party_list">
		<s:iterator value="speechList" var="obj">
            		<li>
                        <a href="${path}/partyBuilding/article.jsp?organizationId=${organizationId}&model=speech&speechType=${speechType}&objId=<s:property value='id'/>" >${obj.title}</a>
                        <span><s:date name="#obj.createDate" format="yyyy-MM-dd"/></span>
				        <div class="btn_list" id="btn_list">
				        	<pop:JugePermissionTag ename='update${moduleName}Page'>
							<a id="edit" href="javascript:editSpeech(${id })" class="edit">修改</a>
							</pop:JugePermissionTag>
							<pop:JugePermissionTag ename='delete${moduleName}Page'>
							<a id="delete" href="javascript:deleteSpeech(${id })" class="delete">删除</a>
							</pop:JugePermissionTag>
						</div>
                    </li>
		</s:iterator>
      </ul>
</div>
<div id="${moduleName}Dialog"></div>
<script type="text/javascript">
if($('#currentOrgId').val()!= USER_ORG_ID){
	$("#addSpeech").hide();
	$("#btn_list").hide();
}else{
	$("#addSpeech").show();
	$("#btn_list").show();
}

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

//删除方法
function deleteSpeech(speechId){
	$.confirm({
		title:"确认删除",
		message:"确定要删除吗?一经删除将无法恢复",
		okFunc: function(){
			$.ajax({
				url:"${path}/baseinfo/speechManage/deleteSpeech.action?speechId="+speechId,
				success:function(data){
					document.location.reload();
					onOrgChanged(getCurrentOrgId(),isGrid());
				    $.messageBox({message:"已经成功删除该"+title+"信息!"});
			    }
		    });
	   }
 });
};

$(document).ready(function(){
	$("#addSpeech").click(function(event){
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
});

$(".btn_list").hide();
$(".party_list li").hover(function(){
	$(this).children(".btn_list").show();
},function(){
	$(".btn_list").hide();
})
</script>



