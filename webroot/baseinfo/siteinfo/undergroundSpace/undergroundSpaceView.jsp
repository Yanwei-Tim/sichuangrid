<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
<input id="_imgUrl" type="hidden" name="population.imgUrl" value="${population.imgUrl}"/>
<table width="200" class="tablelist">
  <tr>
    <td class="title"><label>所属网格</label></td>
    <td colspan="3" class="content" id="dangerousChemicalsUnitOrgName">${location.organization.orgName}</td>
     <td class="imagesTX" rowspan="7">
    <s:if test='null!=population.imgUrl && !"".equals(population.imgUrl)'>
		<img id="dangerousChemicalsImg" name="population.imgUrl" src="" width="148px"/>
	</s:if>
	<s:else>
		<img id="img" name="population.imgUrl" src="${path }/resource/images/head.png" width="148px"/>
	</s:else></td>
  </tr>
  <tr>
    <td class="title"><label>单位名称</label></td>
    <td colspan="3" class="content"><span>${location.unitName}</span></td>
  </tr>
  <tr>
    <td class="title"><label>单位地址</label></td>
    <td colspan="3" class="content"><span>${location.unitAddress}</span></td>
  </tr>
  <tr>
    <td class="title"><label>负责人</label></td>
    <td class="content"><span>${location.superintendent}</span></td>
    <td class="title"><label>联系电话</label></td>
    <td class="content"><span>${location.telephone }</span></td>
  </tr>
  <tr>
    <td class="title"><label>单位类别</label></td>
    <td class="content"><span>${location.unitType}</span></td>
    <td class="title"><label>货物类别</label></td>
    <td class="content"><span>${location.commodityType}</span></td>
  </tr>
   <tr>
    <td class="title"><label>是否注销</label></td>
    <td class="content"><span><s:if test='true==location.isEmphasis'>正常</s:if><s:else>已注销</s:else> </span></td>
     <td class="title"><label>注销时间</label></td>
    <td class="content"><span><s:date name="location.logOutTime" format="yyyy-MM-dd" /></span></td>
  </tr>
   <tr>
    <td class="title"><label>副本许可范围</label></td>
    <td colspan="3" class="content"><span>${location.copyScope}</span></td>
  </tr>
  <tr>
    <td class="title"><label>存储设备</label></td>
    <td  colspan="4" class="content"><span>${location.storageDevice}</span></td>
  </tr>
 
  <tr>
    <td class="title"><label>注销原因</label></td>
    <td colspan="4" class="content"><span>${location.logOutReason}</span></td>
  </tr>
  
  <tr>
    <td class="title"><label>备注</label></td>
    <td colspan="4" class="content"><span><pre>${location.remark}</pre></span></td>
  </tr>
   </table>
   <script type="text/javascript">
	$(document).ready(
			function() {
				if (null != $("#_imgUrl").val() && $("#_imgUrl").val() != "") {//添加随机数保证IE下的图片查看没问题
					$("#dangerousChemicalsImg").attr("src",
							$("#_imgUrl").val() + "?r=" + Math.random());
				}
	});
</script>