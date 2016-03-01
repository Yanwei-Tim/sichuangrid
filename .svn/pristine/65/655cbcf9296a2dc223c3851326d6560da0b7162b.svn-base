<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
.aaaa{width:98%;margin:0 1%;}
.aaaa li{float:left;}
</style>
<input id="lowHouseholds"	type="hidden" name="" value="${population.isLowHouseholds}" />
<input id="_imgUrl" type="hidden" name="population.imgUrl" value="${population.imgUrl}"/>
<body>
<table width="200" class="tablelist">
  <tr>
    <td class="title"><label>所属网格</label></td>
    <td colspan="3" class="content" id="aidNeedPopulationOrgName">${population.organization.orgName}</td>
    <td rowspan="7">
    <c:choose>
	    <c:when test="${null!=population.imgUrl && not empty population.imgUrl}">
	     	<img id="aidNeedImg" name="population.imgUrl" src="" width="148px"/>
	    </c:when>
	    <c:otherwise>
	    	<img id="img" name="population.imgUrl" src="${path }/resource/images/head.png" width="148px"/>
	    </c:otherwise>
	</c:choose>
    </td>
  </tr>
  <tr>
    <td class="title"><label>姓 名</label></td>
    <td class="content"><span>${population.name}</span></td>
    <td class="title"><label>身份证号码</label></td>
    <td class="content"><span>${population.idCardNo}</span></td>
  </tr>
  <tr>
    <td class="title"><label>曾用名/别名</label></td>
    <td class="content"><span>${population.usedName}</span></td>
    <td class="title"><label>性别</label></td>
   <td class="content"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@GENDER" defaultValue="${population.gender.id}" /></span></td>
  </tr>
  <tr>
    <td class="title"><label>民族</label></td>
    <td class="content"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@NATION" defaultValue="${population.nation.id}" /></span></td>
    <td class="title"><label>出生日期</label></td>
    <td class="content"><span><s:date name="population.birthday" format="yyyy-MM-dd" /></span></td>
  </tr>
  <tr>
    <td class="title"><label>联系手机</label></td>
    <td class="content"><span>${population.mobileNumber }</span></td>
    <td class="title"><label>固定电话</label></td>
    <td class="content"><span>${population.telephone }</span></td>
  </tr>
  <tr>
    <td class="title"><label>关注程度</label></td>
    <td class="content" colspan="3"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@ATTENTION_EXTENT" defaultValue="${population.attentionExtent.id}" /></span></td>
  </tr>
  <tr>
    <td class="title"><label>救助原因</label></td>
    <td class="content"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@AIDRREASON" defaultValue="${population.aidReason.id}" /></span></td>
    <td class="title"><label>是否低保户</label></td>
     <td class="content"><span id="lowHouseContext"></span></td>
  </tr>
  <tr>
   <td class="title"><label>领取金额</label></td>
    <td class="content"><span>${population.subsidiesAmount }(元)</span></td>
    <td class="title"><label>领取时间</label></td>
     <td class="content" colspan="2"><span><s:date name="population.subsidiesGetTime " format="yyyy-MM-dd"/></span></td>

  </tr>
  <tr id="showOrHidden2">
    <td class="title"><label>困难证号</label></td>
    <td class="content"><span>${population.difficultCardNo }</span></td>
    <td class="title"><label>困难类型</label></td>
   <td class="content" colspan="2">
   <span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@DIFFICULT_TYPE" defaultValue="${population.difficultType.id}" /></span>
   </td>
  </tr>
  <tr id="showOrHidden3">
    <td class="title"><label>享受起始时间</label></td>
      <td class="content"><span><s:date name="population.subsidiesStartTime" format="yyyy-MM-dd"/>
				</span></td>
    <td class="title"><label>享受人数</label></td>
    <td class="content" colspan="2"><span>${population.subsidiesPopulation }(人)</span></td>
  </tr>
  <tr id="showOrHidden4">
    <td class="title"><label>享受地区</label></td>
    <td class="content"  colspan="4"><span>${population.subsidiesArea }</span></td>
  </tr>
</table>

</body>

<script>
$(document).ready(function(){
	
	if(null!=$("#_imgUrl").val() && $("#_imgUrl").val()!=""){//添加随机数保证IE下的图片查看没问题
		$("#aidNeedImg").attr("src",$("#_imgUrl").val()+"?r="+Math.random());
	}
	
	if($("#lowHouseholds").val()=="true"||$("#lowHouseholds").val()==true){
		$("#showOrHidden1").show();
		$("#showOrHidden2").show();
		$("#showOrHidden3").show();
		$("#showOrHidden4").show();
	}else{
		$("#showOrHidden1").hide();
		$("#showOrHidden2").hide();
		$("#showOrHidden3").hide();
		$("#showOrHidden4").hide();
	}
	lowHouseholdsFormatter();
function lowHouseholdsFormatter(){
	var str = "";
	if($("#lowHouseholds").val()=="true"||$("#lowHouseholds").val()==true)
		str += "是";
	if($("#lowHouseholds").val() =="false"||$("#lowHouseholds").val()==false)
		str += "否";
	$("#lowHouseContext").html(str);
}
	});
</script>
