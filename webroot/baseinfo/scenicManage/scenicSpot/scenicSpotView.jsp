<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
<input id="IsEmpValue"	type="hidden" name="" value="${location.isEmphasis }" />
<table width="200" class="tablelist">
  <tr>
    <td class="title"><label>所属网格</label></td>
    <td colspan="3" class="content" id="organizationOrgName">${location.organization.orgName}</td>
    <td class="imagesTX" rowspan="6">
    <s:if test='null!=location.imgUrl && !"".equals(location.imgUrl)'>
		<img id="img" name="location.imgUrl" src="${path }${location.imgUrl}" width="148px"/>
	</s:if>
	<s:else>
		<img id="img" name="location.imgUrl" src="${path }/resource/images/locationHead.png" width="148px"/>
	</s:else></td>
  </tr>
  <tr>
    <td class="title"><label>景点名称</label></td>
    <td colspan="3" class="content"><span>${location.name}</span></td>
  </tr>
  <tr>
    <td class="title"><label>景点地址</label></td>
    <td colspan="3" class="content"><span>${location.address}</span></td>
  </tr>
  <tr>
   	<td class="title"><label>景点等级</label></td>
    <td class="content"><span>${location.grade}</span></td>
    <td class="title"><label>景点电话</label></td>
    <td class="content"><span>${location.telephone}</span></td>
  </tr>
  <tr>
    <td class="title"><label>景点负责人</label></td>
    <td class="content"><span>${location.personLiable}</span></td>
     <td class="title"><label>景点负责人电话</label></td>
    <td class="content"><span>${location.personLiableTelephone}</span></td>
  </tr>
  <tr>
   	<td class="title"><label>景点介绍</label></td>
    <td class="content" colspan="3"><span>${location.introduction}</span></td>
  </tr>
  <s:if test='location.isEmphasis==true'>
  <tr>
    <td class="title"><label>注销时间</label></td>
    <td class="content"><span><s:date name="location.logOutTime" format="yyyy-MM-dd" /></span></td>
    <td class="title"><label>注销原因</label></td>
    <td class="content" colspan="2"><span>${location.logOutReason}</span></td>
  </tr>
  </s:if>
  <tr>		
   	 <td class="title"><label>备注</label></td>
   	 <td colspan="4" class="content">${location.remark}</td>	 
  </tr> 
</table>

<script>
$(document).ready(function(){
	isEmpFormatter();
	var img=$("#img");
	var src=img.attr("src");
	if(src){
		img.attr("src",src+"?r="+Math.random());
	} 
	
});

function isEmpFormatter(){
	var str = "";
	if($("#IsEmpValue").val()=="true"||$("#IsEmpValue").val()==true)
		str += "是";
	if($("#IsEmpValue").val() =="false"||$("#IsEmpValue").val()==false)
		str += "否";
	$("#tagIsEmp").html(str);
}
</script>
