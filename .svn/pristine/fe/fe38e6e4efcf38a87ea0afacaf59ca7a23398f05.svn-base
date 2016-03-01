<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="/includes/baseInclude.jsp" %>
<style>
td {text-align: center;}
</style>
<body>
<table width="300" class="tablelist">
   <tr>
	     <td class="title"><label>文件标题</label></td>
	     <td class="title" style="width:100px;"><label>收件人名称</label></td>
	     <td class="title"><label>收件人类型</label></td>
	     <td class="title"><label>是否签收</label></td>
	     <td class="title"><label>签收人</label></td>
	     <td class="title"><label>签收回执</label></td>
	     <td class="title"><label>签收时间</label></td>
  </tr>
  <c:forEach items="${reminderList}" var="vo"> 
   <tr>
 	 	 <td class=""><label name="title">${vo.documentTitle }</label></td>
	     <td class="" style="width:100px;"><label>${vo.userName }</label></td>
	     <td class=""><label name="posttype">${vo.receviesType }</label></td>
	     <td class=""><label name="isSign">${vo.isSign }</label></td>
	     <td class=""><label>${vo.sinerUserName }</label></td>
	     <td class=""><label>${vo.signContent }</label></td>
	     <td class=""><label><fmt:formatDate value="${vo.signDate }" type="date" pattern="yyyy-MM-dd" /></label></td>
  </tr>
  </c:forEach>
</table>

</body>
<script>
$(document).ready(function(){
	subTitle();
	addRemander();
	getPosttype();
	$("#receiveDocumentsList").trigger("reloadGrid");
});



function getPosttype(){
	$("label[name='posttype']").each(function(){
		var posttype = $(this).html();
		if(posttype==0 || posttype=="0"){
			 $(this).html("主送");
		}else{
			$(this).html("抄送");
		}
});
}

function subTitle(){
	$("label[name='title']").each(function(){
		var title = $(this).html();
		if(title.length>5){
			title = title.substring(1,5)+"...";
		}
		 $(this).html(title);
});
}

function addRemander(){
	$("label[name='isSign']").each(function(){
		var sign = $(this).html();
		if(sign==0 || sign=="0"){
			 $(this).html("未签收");
		}else{
			$(this).html("已签收");
		}
// 		 <a href='' style='text-decoration: underline;color: red;'>(催收)</a>
		
});
}



</script>
