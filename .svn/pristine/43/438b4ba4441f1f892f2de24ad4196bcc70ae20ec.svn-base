<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
<input id="_imgUrl" type="hidden" name="population.imgUrl" value="${population.imgUrl}"/>
<table width="200" class="tablelist">
  <tr>
    <td class="title"><label>所属网格</label></td>
    <td colspan="3" class="content" id="landscapingOrgName">${location.organization.orgName}</td>
     <td class="imagesTX" rowspan="7">
    <s:if test='null!=population.imgUrl && !"".equals(population.imgUrl)'>
		<img id="landscapingImg" name="population.imgUrl" src="" width="148px" />
	</s:if>
	<s:else>
		<img id="img" name="population.imgUrl" src="${path }/resource/images/head.png" width="148px"/>
	</s:else></td>
  </tr>
  <tr>
    <td class="title"><label>部件编号</label></td>
    <td class="content"><span>${location.objNum}</span></td>
    <td class="title"><label>部件名称</label></td>
    <td class="content"><span>${location.objName}</span></td>
  </tr>

   <tr>
 	<td class="title"><label>所在街道</label></td>
    <td class="content"><span>${location.street}</span></td>
    <td class="title"><label>所在社区</label></td>
    <td class="content"><span>${location.community}</span></td>
  </tr>
  <tr>
 	<td class="title"><label>主管部门代码</label></td>
    <td class="content"><span>${location.deptCode}</span></td>
    <td class="title"><label>主管部门名称</label></td>
    <td class="content"><span>${location.deptName}</span></td>
  </tr>
  <tr>
  	<td class="title"><label>权属部门代码</label></td>
    <td class="content"><span>${location.ownershipUnitCode}</span></td>
    <td class="title"><label>权属部门名称</label></td>
    <td class="content"><span>${location.ownershipUnitName}</span></td>
    
  </tr>
 <tr>
	 <td class="title"><label>养护单位代码</label></td>
    <td class="content"><span>${location.maintenanceUnitCode}</span></td>
    <td class="title"><label>养护部门名称</label></td>
    <td class="content"><span>${location.maintenanceUnitName}</span></td>
    
  </tr>
  <tr>
    <td class="title"><label>初始时间</label></td>
    <td class="content"><span><s:date name="location.startDate" format="yyyy-MM-dd"/></span></td>
    <td class="title"><label>变更时间</label></td>
    <td class="content"><span><s:date name="location.changeDate" format="yyyy-MM-dd"/></span></td>
  </tr>   
  <tr>
    <td class="title"><label>数据来源</label></td>
    <td class="content"><span>${location.dateSource}</span></td>
    <td class="title"><label>现场调查权属</label></td>
    <td class="content"><span>${location.researchOwnership}</span></td>
  </tr>
  <tr>
    <td class="title"><label>专业部门确认权属</label></td>
    <td class="content"><span>${location.verifyOwnership}</span></td>
    <td class="title"><label>案件资料确认权属</label></td>
    <td class="content"><span>${location.caseMaterialOwnership}</span></td>
  </tr>
  <tr>
    <td class="title"><label>其他来源权属</label></td>
    <td class="content"><span>${location.otherSourceOwnership}</span></td>
    <td class="title"><label>位置描述</label></td>
    <td class="content"><span>${location.objPosition}</span></td>
  </tr>
   <tr>
    <td class="title"><label>状态</label></td>
    <td class="content"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@STATE" 
    	defaultValue="${location.state.id}" /></span></td>
    <td class="title"><label>备注</label></td>
    <td class="content"  colspan="4"><span><pre>${location.remark}</pre></span></td>
  </tr>

   </table>
   <script type="text/javascript">
	$(document).ready(
			function() {
				if (null != $("#_imgUrl").val() && $("#_imgUrl").val() != "") {//添加随机数保证IE下的图片查看没问题
					$("#landscapingImg").attr("src",
							$("#_imgUrl").val() + "?r=" + Math.random());
				}
	});
</script>