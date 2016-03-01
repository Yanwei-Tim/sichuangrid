<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
<table class="tablelist">
  <tr>
    <td class="title"><label>所属网格</label></td>
    <td colspan="3" class="content"><label>${school.organization.orgName}</label></td>
    <td class="imagesTX" rowspan="7">
    <input id="_imgUrl" type="hidden" name="school.imgUrl" value="${school.imgUrl}"/>
	    <s:if test='null!=school.imgUrl && !"".equals(school.imgUrl)'>
			<img id="schoolImg" name="school.imgUrl" src="${path }${school.imgUrl}" width="148px"/>
		</s:if>
		<s:else>
			<img id="img" name="school.imgUrl" src="${path }/resource/images/locationHead.png" width="148px"/>
		</s:else></td>
  </tr> 
  <tr>
    <td class="title"><label>学校名称</label></td>
    <td colspan="3" class="content"><span>${school.chineseName}</span></td>
  </tr>
  <tr>
  	<td class="title"><label>学校地址</label></td>
    <td colspan="3" class="content"><span>${school.address}</span></td>
  </tr>
  <tr>
    <td class="title"><label>英文名称</label></td>
    <td class="content" id="schoolEnglishName"><label>${school.englishName}</label></td>
    <td class="title"><label>学校性质</label></td>
    <td class="content"><span><pop:PropertyDictViewTag
				name="@com.tianque.domain.property.PropertyTypes@SCHOOL_PROPERTY"
				defaultValue="${school.kind.id}"/></span></td>
  </tr>
  <tr>
    <td class="title"><label>学校类型</label></td>
  	<td class="content"><span><pop:PropertyDictViewTag
				name="@com.tianque.domain.property.PropertyTypes@SCHOOL_TYPE"
				defaultValue="${school.type.id}"/></span>
	</td>
	<td class="title"><label>关注程度</label></td>
	<td class="content"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@ATTENTION_EXTENT" defaultValue="${school.attentionExtent.id}"/></span></td>
  </tr>
  <tr>
    <td class="title"><label>学校网址</label></td>
    <td class="content"><label>${school.webSite}</label></td>
    <td class="title"><label>在校人数</label></td>
    <td class="content"><label>${school.atSchoolHeadcount}</label></td>
  </tr>
  <tr>
    <td class="title"><label>校长</label></td>
    <td class="content"><label>${school.president}</label></td>
    <td class="title"><label>传真</label></td>
    <td class="content"><span>${school.fax}</span></td>
  </tr>
  <tr>
    <td class="title"><label>电子邮箱</label></td>
    <td class="content"><span>${school.email}</span></td>
    <td class="title"><label>法制副校长</label></td>
    <td class="content" colspan="2"><label>${school.personLiable}</label></td>
  </tr>
  <tr>
    <td class="title"><label>联系电话</label></td>
    <td class="content"><label>${school.personLiableTelephone}</label></td>
    <td class="title"><label>联系手机</label></td>
    <td class="content" colspan="2"><label>${school.personLiableMobileNumber}</label></td>
  </tr>
   <s:if test='school.isEmphasis==@com.tianque.service.vo.IsEmphasis@IsNotEmphasis'>
    <td class="title"><label>注销时间</label></td>
    <td class="content"><span><s:date name="school.logOutTime" format="yyyy-MM-dd" /></span></td>
    <td class="title"><label>注销原因</label></td>
    <td class="content" colspan="2"><span>${school.logOutReason}</span></td>
   </s:if>
  <tr>
    <td class="title"><label>周边情况</label></td>
    <td colspan="4" class="content"><span>${school.remark}</span></td>
  </tr>
  
</table>

<script type="text/javascript">
$(function (){
	if(null!=$("#_imgUrl").val() && $("#_imgUrl").val()!=""){//添加随机数保证IE下的图片查看没问题
		$("#schoolImg").attr("src",$("#_imgUrl").val()+"?r="+Math.random());
	}
});
</script>
