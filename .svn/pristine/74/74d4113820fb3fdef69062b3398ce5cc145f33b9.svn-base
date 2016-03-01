<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
<input id="_imgUrl" type="hidden" name="population.imgUrl" value="${population.imgUrl}"/>
<table width="200" class="tablelist">
  <tr>
    <td class="title"><label>所属网格</label></td>
    <td colspan="3" class="content" id="druggyPopulationOrgName">${population.organization.orgName}</td>
    <td rowspan="7">
    <s:if test='null!=population.imgUrl && !"".equals(population.imgUrl)'>
		<img id="druggyPImg" name="population.imgUrl" src="" width="148px"/>
	</s:if>
	<s:else>
		<img id="img" name="population.imgUrl" src="${resource_path }/resource/images/head.png" width="148px"/>
	</s:else></td>
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
   	  <td class="title"><label>伤残等级</label></td>
	 <td  class="content"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@DISABLE_GRADE" defaultValue="${population.disableGradeType.id}"/></span></td>
      <td class="title"><label>是否牺牲 </label></td>
	 <td class="content"    ><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@IS_SACRIFICE" defaultValue="${population.sacrificeType.id}"/></span></td>
  </tr>
  <tr>
   	  <td class="title"><label>参保情况</label></td>
	 <td  class="content"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@INSURE_SITUATION" defaultValue="${population.insureSituationType.id}"/></span><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@INSURE_SITUATION_SUB" defaultValue="${population.insureSituationSmallType.id}"/></span></td>
      <td class="title"><label>困难类型 </label></td>
	 <td class="content"    ><span id=""><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@DIFFICULT_TYPE_GOOD_SAMARITAN" defaultValue="${population.difficultType.id}"/></span></td>
  </tr>
   <tr>
   	  <td class="title"><label>见义勇为奖项</label></td>
	 <td  colspan="4" class="content"><span>${population.awardYear}年<pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@AWARD" defaultValue="${population.awardType.id}"/></span></td>
  </tr>
  <tr>
      <td class="title"><label>行为发生地 </label></td>
	 <td colspan="4" class="content"><span id="">${population.actOccurred}</span></td>
  </tr>
  <tr>
   	  <td class="title"><label>确认单位</label></td>
	 <td  colspan="4" class="content"><span>${population.confirmUnit}</span></td>
  </tr>
  <tr>
      <td class="title"><label>主要见义勇为事迹</label></td>
	 <td colspan="4" class="content"><span id="">${population.heroicDeeds}</span></td>
  </tr>
   <tr>
    <td class="title"><label>附件</label>
    	<select id="personAttachFiles" name="personAttachFiles" multiple="multiple" style="width:200px;display:none"></select>
    </td>
    <td colspan="4"  class="content" id="custom-queue_proKey"></td>
  </tr>
</table>

<script>
$(document).ready(function(){
	adanonFormatter();
	
	if(null!=$("#_imgUrl").val() && $("#_imgUrl").val()!=""){//添加随机数保证IE下的图片查看没问题
		$("#druggyPImg").attr("src",$("#_imgUrl").val()+"?r="+Math.random());
		//$(".shadow").show();
	}
	fillFile();
});

function adanonFormatter(){
	var str = "";
	if($("#adanonValue").val()=="true"||$("#adanonValue").val()==true)
		str += "是";
	if($("#adanonValue").val() =="false"||$("#adanonValue").val()==false)
		str += "否";
	$("#tagAdanon").html(str);
}

function fillFile(){
     <s:if test="personAttachFileList !=null && personAttachFileList.size>0">
		<s:iterator value="personAttachFileList">
	        $("#custom-queue_proKey").addUploadFileValue({
		        id:"<s:property value='id'/>",
		        filename:"<s:property value='fileName'/>",
		    	link:"${path}/baseinfo/goodSamaritanManage/downloadPersonAttachFile.action?populationAttachFile.id=<s:property value='id'/>",
		        showCloseButton:false
	        });
	        $("<option>").attr("id","<s:property value='id'/>").val("<s:property value='id'/>,<s:property value='fileName' escape='false'/>").attr("selected",true).appendTo($("#personAttachFiles"));
	        </s:iterator>
  </s:if>
}

</script>
