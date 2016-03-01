<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>

<table width="200" class="tablelist">
  <tr>
    <td class="title"><label>所属网格33</label></td>
    <td colspan="3" class="content" id="orgName">${location.organization.orgName}</td>
    <td class="imagesTX" rowspan="7">
    <input id="_imgUrl" type="hidden" name="location.imgUrl" value="${location.imgUrl}"/>
    <s:if test='null!=location.imgUrl && !"".equals(location.imgUrl)'>
		<img id="loImg" name="location.imgUrl" src="${path }${location.imgUrl}" width="148px"/>
	</s:if>
	<s:else>
		<img id="img" name="location.imgUrl" src="${resource_path }/resource/images/head.png" width="148px"/>
	</s:else></td>
  </tr>
  <tr>
  	<td class="title"><label>场所名称</label></td>
  	<td colspan="3" class="content"><span>${location.name}</span></td>
  </tr>
  <tr>
  	<td class="title"><label>场所位置</label></td>
  	<td colspan="3" class="content"><span>${location.address}</span></td>
  </tr>
  <tr>
    <td class="title"><label>经纬度</label></td>
    <td class="content" colspan="3"><span>${location.longAititude}</span></td>
  </tr>
  <tr>
    <td class="title"><label>场地类型</label></td>
    <td class="content"><span>${location.siteType}</span></td>
    <td class="title"><label>面积(㎡)</label></td>
   <td class="content"><span>${location.area}</span></td>
  </tr>
  <tr>
    <td class="title"><label>可容纳人数</label></td>
    <td class="content"><span>${location.fullPersonNum}</span></td>
    <td class="title"><label>周边社区/村数量</label></td>
    <td class="content"><span>${location.aroundVillageNum}</span></td>
  </tr>
  <tr>
    <td class="title"><label>周边人口</label></td>
    <td class="content"><span>${location.aroundPersonNum }</span></td>
    <td class="title"><label>标识</label></td>
    <td class="content"  ><span>${location.identificationNum }</span></td>
  </tr>
  <tr>
    <td class="title"><label>图形符号</label></td>
    <td class="content"><span>${location.imageSymbolNum }</span></td>
    <td class="title"><label>指示</label></td>
    <td class="content"><span>${location.pointNum }</span></td>
  </tr>
  <tr>
    <td class="title"><label>周边道路情况</label></td>
    <td class="content"><span>${location.aroundRoadCondition}</span></td>
    <td class="title"><label>功能完善</label></td>
    <td class="content"><span>${location.functionComplete }</span></td>
  </tr>
  <tr>
  	 <td class="title"><label>注销时间</label></td>
	 <td class="content"><span id=""><s:date name="location.logOutTime" format="yyyy-MM-dd"/></span></td>	
	 <td class="title"><label>注销原因</label></td>
	 <td colspan="2" class="content"><span id="">${location.logOutReason}</span></td>
  </tr>
   <tr>		
   	 <td class="title"><label>备注 </label></td>
	 <td colspan="4" class="content">${location.remark}</td>	
  </tr> 
</table>
<script>
$(document).ready(function(){
	if(null!=$("#_imgUrl").val() && $("#_imgUrl").val()!=""){//添加随机数保证IE下的图片查看没问题
		$("#loImg").attr("src",$("#_imgUrl").val()+"?r="+Math.random());
	}
});
</script>