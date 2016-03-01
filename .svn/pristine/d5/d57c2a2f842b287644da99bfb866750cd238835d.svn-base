<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>

<table class="tablelist">
  <tr>
    <td class="title"><label>所属网格</label></td>
    <td colspan="3" class="content" id="orgName">${location.organization.orgName}</td>
    <td class="imagesTX" rowspan="7">
    <input id="_imgUrl" type="hidden" name="location.imgUrl" value="${location.imgUrl}"/>
	    <s:if test='null!=location.imgUrl && !"".equals(location.imgUrl)'>
			<img id="locaImg" name="location.imgUrl" src="${path }${location.imgUrl}" width="148px"/>
		</s:if>
		<s:else>
			<img id="img" name="location.imgUrl" src="${path }/resource/images/locationHead.png" width="148px"/>
		</s:else>
	</td>
  </tr>
  <tr>
  	<td class="title"><label>单位名称</label></td>
  	<td colspan="3" class="content"><span>${location.companyName}</span></td>
  </tr>
  <tr>
  	<td class="title"><label>单位地址</label></td>
  	<td colspan="3" class="content"><span>${location.companyAddress}</span></td>
  </tr>
  <tr>
    <td class="title"><label>单位类别</label></td>
    <td class="content"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@ACTUALCOMPANY_COMPANYTYPE" defaultValue="${location.companyType.id}" /></span></td>
    <td class="title"><label>是否重点单位</label></td>
    <td class="content"><span><s:if test="location.isKey">是</s:if><s:else>否</s:else></span></td>
  </tr>
  <tr>
    <td class="title"><label>法人代表</label></td>
    <td class="content"><span>${location.corporateRepresentative}</span></td>
    <td class="title"><label>身份证号码</label></td>
   <td class="content"><span>${location.idCardNo}</span></td>
  </tr>
  <tr>
    <td class="title"><label>单位电话</label></td>
    <td class="content"><span>${location.telephone}</span></td>
    <td class="title"><label>营业执照号码</label></td>
    <td class="content"><span>${location.businessLicenseNo }</span></td>
  </tr>
  <tr>
    <td class="title"><label>单位传真</label></td>
    <td class="content"><span>${location.facsimile}</span></td>
    <td class="title"><label>组织机构代码</label></td>
    <td class="content"><span>${location.orgCode }</span></td>
  </tr>
  <tr>
    <td class="title"><label>经济性质</label></td>
    <td class="content"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@ACTUALCOMPANY_ECONOMICNATURE" defaultValue="${location.economicNature.id}" /></span></td>
  	<td class="title"><label>注册资本(万元)</label></td>
    <td class="content" colspan="2"><span>${location.registeredCapital }</span></td>
  </tr>
  <tr>
     <td class="title"><label>有效期至</label></td>
	 <td class="content"><span id=""><s:date name="location.expiryDate" format="yyyy-MM-dd" /></span></td>		
	 <td class="title"><label>注册日期 </label></td>
	 <td colspan="2" class="content"><span id=""><s:date name="location.registrationDate" format="yyyy-MM-dd" /></span></td>				 
  </tr> 
  <tr>
    <td class="title"><label>经营范围</label></td>
    <td class="content" colspan="4"><span>${location.businessScope}</span></td>
  </tr>
  <tr>
    <td class="title"><label>注册地址</label></td>
    <td class="content" colspan="4"><span>${location.registrationAddress }</span></td>
  </tr>
  <tr>
    <td class="title"><label>从业人数 </label></td>
    <td class="content"><span>${location.employeesNum }</span></td>
    <td class="title"><label>主管部门</label></td>
    <td class="content" colspan="2"><span>${location.competentDepartment }</span></td>
  </tr>
  <tr>
   	 <td class="title"><label>管理级别</label></td>
	 <td class="content" ><span id="" ><pop:PropertyDictViewTag  name="@com.tianque.domain.property.PropertyTypes@ACTUALCOMPANY_SUPERVISORYLEVEL" defaultValue="${location.supervisoryLevel.id}" /></span></td>
     <td class="title"><label>管理部门</label></td>
	 <td  colspan="2" class="content"><span id="">${location.supervisoryDepartment}</span></td>	 
  </tr> 
  <tr>		
   	 <td class="title"><label>消防等级</label></td>
	 <td class="content"><span id=""><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@ACTUALCOMPANY_FIREFIGHTINGLEVEL" defaultValue="${location.fireFightingLevel.id}" /></span></td>	
	 <td class="title"><label>治安负责人</label></td>
	 <td colspan="2" class="content"><span id="">${location.securityChief}</span></td>	 
  </tr> 
  <tr>
  	 <td class="title"><label>取消关注时间</label></td>
	 <td class="content"><span id=""><s:date name="location.logOutTime" format="yyyy-MM-dd"/></span></td>	
	 <td class="title"><label>取消关注原因</label></td>
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
		$("#locaImg").attr("src",$("#_imgUrl").val()+"?r="+Math.random());
	}
	//fateson add
	$(function (){
		var img=$("#img");
		var src=img.attr("src");
		if(src){
			img.attr("src",src+"?r="+Math.random());
		} 
	});
});
</script>