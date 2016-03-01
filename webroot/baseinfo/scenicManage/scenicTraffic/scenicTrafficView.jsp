<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
<input id="IsEmpValue"	type="hidden" name="" value="${scenicTraffic.isEmphasis }" />
<table width="200" class="tablelist">
  <tr>
    <td class="title"><label>所属网格</label></td>
    <td colspan="3" class="content" id="organizationOrgName">${scenicTraffic.organization.orgName}</td>
    <td class="imagesTX" rowspan="6">
    <s:if test='null!=scenicTraffic.imgUrl && !"".equals(scenicTraffic.imgUrl)'>
		<img id="img" name="scenicTraffic.imgUrl" src="${path }${scenicTraffic.imgUrl}" width="148px"/>
	</s:if>
	<s:else>
		<img id="img" name="scenicTraffic.imgUrl" src="${path }/resource/images/locationHead.png" width="148px"/>
	</s:else></td>
  </tr>
  <tr>
    <td class="title"><label>起点</label></td>
    <td colspan="3" class="content"><span>${scenicTraffic.startAddress}</span></td>
  </tr>
  <tr>
    <td class="title"><label>终点</label></td>
    <td colspan="3" class="content"><span>${scenicTraffic.endAddress}</span></td>
  </tr>
  <tr>
    <td class="title"><label>类型</label></td>
    <td class="content"><span><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@SCENICTRAFFIC_TYPES" defaultValue="${scenicTraffic.trafficType.id}"/></span></span></td>
     <td class="title"><label>联系电话</label></td>
    <td class="content"><span>${scenicTraffic.phone}</span></td>
  </tr>
  <tr>
   	<td class="title"><label>线路名称</label></td>
    <td class="content" colspan="3"><span>${scenicTraffic.trafficName}</span></td>
  </tr>
  <tr>
  	<td class="title"><label>负责单位</label></td>
    <td class="content" colspan="3"><span>${scenicTraffic.managerUnit}</span></td>
  </tr>
  <tr>
    <td class="title"><label>负责人</label></td>
    <td class="content"><span>${scenicTraffic.managerPeople}</span></td>
     <td class="title"><label>负责人电话</label></td>
    <td class="content" colspan="2"><span>${scenicTraffic.managerPeoplePhone}</span></td>
  </tr>
   <tr>		
   	 <td class="title"><label>最早班车时间</label></td>
   	 <td class="content"><span>${scenicTraffic.firstBusTime}</span></td>	 
   	 <td class="title"><label>最晚班车时间</label></td>
   	 <td  class="content" colspan="2"><span>${scenicTraffic.lastBusTime}</span></td>	 
  </tr> 
   <tr>		
   	 <td class="title"><label>周边景区</label></td>
	 <td colspan="4" class="content">${scenicTraffic.aroundScenic}</td>
  </tr> 
  <tr>		
   	 <td class="title"><label>备注</label></td>
   	 <td colspan="4" class="content">${scenicTraffic.remark}</td>	 
  </tr> 
</table>

<script>
$(document).ready(function(){
	isEmpFormatter();
	//fateson add
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
