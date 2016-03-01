<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="/PopGrid-taglib" prefix="pop" %>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
	.imgUrl{
		width: 200px;
		height:170px;
		padding: 10px;
	}
	.param{
		float:left;
		margin-top: 15px;
	}
	.button{
		 margin-left:31%;
	}
</style>
<div class="ui-corner-all" id="nav">
	<a  href="javascript:;" id="addPhotos"><span><strong class="ui-ico-xz"></strong>新增图片</span></a>
</div>
<ul>
<s:iterator value="basicCase.caseImageUploads" var="caseImageUpload">
<li class="param">
	<input type="hidden" value="${caseImageUpload.id }" id="imgId"/>
	<c:choose>
	    <c:when test="${caseImageUpload.imgUrl == null }">
	     	<img src="${resource_path}/resource/images/nopic.jpg" />
	    </c:when>
	    <c:otherwise>
	    	<img src="${caseImageUpload.imgUrl}"  id="imgUrl${caseImageUpload.id }"  class="imgUrl"/>
	    </c:otherwise>
	</c:choose>
	<div class="button">
		<a href="javascript:;" id="editPhotos_${caseImageUpload.id }" class="editPhotos">修改</a>
		&nbsp;&nbsp;
		<a href="javascript:;" id="deletePhotos_${caseImageUpload.id }" class="deletePhotos">删除</a>
	</div>
	
</li>
<script type="text/javascript">
$(function(){
	var imgId = ${caseImageUpload.id};
	//$("#addButton").hide();
		$("#editPhotos_${caseImageUpload.id }").click(function(event){
			$("#basicCaseDialog").createDialog({
				width: 470,
				height: 230,
				title:"编辑图片",
				url:"${path}/partyBuilding/baseInfos/maintainPhotos.jsp?imgId="+imgId+"&orgId="+getCurrentOrgId()+"&type=second"+"&baseInfoType=${baseInfoType}",
				buttons:{
					"保存" : function(event){
		   				$("#maintainForm").submit();
			   		},
			   		"关闭" : function(){
			        	$(this).dialog("close");
			   		}
				}
			});
		});
		
		$("#deletePhotos_${caseImageUpload.id }").click(function(event){
			var imgUrl = $("#imgUrl${caseImageUpload.id }").attr("src");
	 		var url = "${path}/caseImageUploadManage/deletePhotosAndOrgById.action?caseImageUpload.id="+${caseImageUpload.id}+"&caseImageUpload.imgUrl="+imgUrl+"&organization.id="+getCurrentOrgId();
			$.confirm({
				title:"确认删除",
				message:"你确定要清除此图片吗？",
				okFunc: function() {
					$.ajax({
		 				url:url,
						success:function(data){
							if (data == true) {
								imgId = "";
								//$(".leftAccordion .cur").click();
								$.messageBox({message:"已经成功清除该图片！"});
								$("#zoomin").click();
	 						    return true;
							}else{
								$.messageBox({message:"清除该图片失败！"});
		 						return false;		
							}
						    
						}
					});
				}
			});
			
		});
		
});
</script>
</s:iterator>
</ul>
<script type="text/javascript">
$(function(){
	$("#addPhotos").click(function(event){
 		$("#basicCaseDialog").createDialog({
 				width: 470,
 				height: 230,
 				title:"编辑图片",
 				url:"${path}/partyBuilding/baseInfos/maintainPhotos.jsp?orgId="+getCurrentOrgId()+"&type=second"+"&baseInfoType=${baseInfoType}",
 				buttons:{
 					"保存" : function(event){
 		   				$("#maintainForm").submit();
 			   		},
 			   		"关闭" : function(){
 			        	$(this).dialog("close");
 			   		}
 				}
 		});
	});
});
</script>