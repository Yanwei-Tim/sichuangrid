<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
if(ThreadVariable.getUser()!=null){
	request.setAttribute("NAME",ThreadVariable.getUser().getName());
}
request.setAttribute("aspirationType",com.tianque.domain.property.PropertyTypes.ASPIRATIONTYPE);
%>
<s:action namespace="/sysadmin/propertyManage" name="findPropertyDictByDomainName" var="aspirationTypes">
	<s:param name="propertyDomain.domainName">${aspirationType}</s:param>
</s:action>
<style type="text/css">
#steadyWorkDialog{overflow-y:auto !important;}
.addPeople{
    margin: 10px 0 5px;
    padding: 5px 0;
    position:relative;
    border:1px dashed #d2d2d2;
    background-color:#fff;
    overflow:hidden;
}
.addPeople .removeList,
.addPeople .addList{
    position:absolute;
    right:0;top:0;
    padding: 0 8px;
    width: 1em;
    font-size:14px;
    color:#666;
    cursor:pointer;
    height:100px;
    background-color:#ececec;
}
.addPeople .removeList .icon,
.addPeople .addList .icon{
    display: block;
    font-family: microsoft yahei;
    font-size: 18px;
    font-weight: bolder;
    text-align: center;
}

.suqiuList{overflow:hidden;margin-bottom:10px;}
.suqiuList li{float:left;min-width:120px;}
.suqiuList li input{vertical-align:middle;margin-right:3px;}

.yujingList{overflow:hidden;margin-bottom:10px;}
.yujingList li{
    float:left;
    min-width:310px;
}
.yujingList i{
    display:inline-block;*display:inline;*zoom:1;
    margin-right: 3px;
    width:18px;height:15px;
    vertical-align:middle;
    background: url('/resource/images/jingshi.png') 0 0 no-repeat;
}
.yujingList .icon_blue  {background-position:0px 0px;}
.yujingList .icon_yellow{background-position:0px -20px;}
.yujingList .icon_orange{background-position:0px -40px;}
.yujingList .icon_red   {background-position:0px -60px;}

.twin_row{
    margin-right: 5px;
    padding-top: 5px;
    display: inline;
    float: right;
    width: 80px;
    line-height: 1.4em;
}


</style>

<form action="" id='maintainForm' method="post">
	<pop:token />
	<input type="hidden" name="mode" id="mode" value="${mode}" />
	<input type="hidden" id="organizationId" name="steadyWork.organization.id" value="${steadyWork.organization.id}"/>
	<input type="hidden" id="orgType" name="steadyWork.organization.orgType.id" value="${steadyWork.organization.orgType.id}"/>
	<input type="hidden" id="id" name="steadyWork.id" value="${steadyWork.id}"/>
	<input type="hidden" id="orgInternalCode" name="steadyWork.orgInternalCode" value="${steadyWork.orgInternalCode}"/>
	<input type="hidden" id="occurOrgId" name="steadyWork.occurOrg.id" value="${steadyWork.occurOrg.id}" />
	<input type="hidden" id="occurOrgInternalCode" name="steadyWork.occurOrgInternalCode" value="${steadyWork.occurOrgInternalCode}" />
	
	
	<div class="container container_24">
	    <div class="grid_2 lable-right">
	    	<em class="form-req">*</em>编号：
	    </div>
	    <div class="grid_8">
	    	<input type="text" name="steadyWork.serialNumber"  id="serialNumber" maxlength="15" value="${steadyWork.serialNumber}" class='form-txt' readonly="readonly"/>
		</div>
	    <div class="grid_6 lable-right">
	    	<em class="form-req">*</em>建表类型：
	    </div>
	    <div class="grid_7">
			<select name="steadyWork.createTableType.id" id="createTableType" class='form-txt' <s:if test='"view".equals(mode)'>disabled</s:if>>
		   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@CREATE_TABLE_TYPE"  defaultValue="${steadyWork.createTableType.id}" />
			</select>
	    </div>
	    <div class="clearLine">&nbsp;</div>
	    
	    <div class="grid_2 lable-right">
	    	网格号：
	    </div>
	    <div class="grid_8">
			<input type="text" name="steadyWork.gridNo" id="gridNo"  maxlength="20" value="${steadyWork.gridNo}" class='form-txt' />
	    </div>
	    <div class="grid_6 lable-right">
	    	<em class="form-req">*</em>所属网格：
	    </div>
	    <div class="grid_7">
	    	<input type="text" id="peopleOccurOrgSelector" name="selectOrgName" value="" class="form-txt" />
	    </div>
	    <div class="clearLine">&nbsp;</div>
	    
	    <div class="grid_2 lable-right">登记单位：</div>
	    <div class="grid_8">
			<input type="text" id="steadyWorkOrgName" name="steadyWork.bookingUnit" readonly value="${steadyWork.bookingUnit}"	class='form-txt' />
	    </div>
	    <div class="grid_6 lable-right">
	    	<em class="form-req">*</em>登记人：
	    </div>
	    <div class="grid_7">
			<input type="text" name="steadyWork.registrant" id="registrant" maxlength="20" value="${steadyWork.registrant}" class='form-txt'/>
	    </div>
	    <div class="clearLine">&nbsp;</div>
	</div>
	<s:if test="@com.tianque.core.util.DialogMode@EDIT_MODE.equalsIgnoreCase(mode)">
		<c:forEach items="${steadyWork.peopleInfos }" var="peopleInfo">
	<div class="container container_24 addPeople">
	
			<div class="grid_3 lable-right"><em class="form-req">*</em>身份证号：</div>
		    <div class="grid_6">
				<input type="text" name="peopleInfoVo.idCardNoTemp" class="idCardNo form-txt"  maxlength="18" value='${peopleInfo.idCardNo }'/>	    
		    </div>
		     <div class="grid_2 lable-right"><em class="form-req">*</em>姓名：</div>
	    <div class="grid_4">
			<input type="text" name="peopleInfoVo.nameTemp" id="name" maxlength="20" value='${peopleInfo.name }'
				class='form-txt'/>	    
	    </div>
	    <div class="grid_3 lable-right">性别：</div>
	    <div class="grid_3">
			<select name="peopleInfoVo.genderTemp" id="gender" class='form-txt' <s:if test='"view".equals(mode)'>disabled</s:if>>
		   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@GENDER" defaultValue="${peopleInfo.gender.id}" />
			</select>
		</div>
		<div class="clearLine">&nbsp;</div>
	    <div class="grid_3 lable-right">联系手机：</div>
	    <div class="grid_6">
			<input type="text" name="peopleInfoVo.mobileNumberTemp" id="mobileNumber"  maxlength="11"  value='${peopleInfo.mobileNumber }' title="请输入11位以1开头的联系手机  例如：13988888888" 
				class='form-txt {mobile:true,messages:{mobile:"手机号码输入只能是以1开头的11位数字"}}' />	    
		</div>
	    <div class="grid_2 lable-right">出生年月：</div>
	    <div class="grid_4 birthdayDiv">
			<input type="text" name="peopleInfoVo.birthDayTemp" class="birthDay form-txt" maxlength="32" disabled value='<fmt:formatDate value="${peopleInfo.birthDay}" type="date" pattern="yyyy-MM-dd" />' />	    
		</div>
	    <div class="grid_3 lable-right">是否党员：</div>
	    <div class="grid_3">
			<select id="isPartyMember" name="peopleInfoVo.isPartyMemberTemp" class="form-txt" <s:if test='"view".equals(mode)'>disabled</s:if>>
 				<option value="true" <c:if test="${peopleInfo.isPartyMember }">selected</c:if>>是</option>
 				<option value="false" <c:if test="!${peopleInfo.isPartyMember}">selected</c:if>>否</option>
	 		</select>	    
	    </div>
	    <div class="clearLine">&nbsp;</div>
	    <div class="grid_3 lable-right">常住地址：</div>
	    <div class="grid_12">
			<input type="text" name="peopleInfoVo.addressTemp" id="permanentAddress"  maxlength="50"  value='${peopleInfo.permanentAddress }'
				class="form-txt {validatorNativePlaceAddress:true, messages:{validatorNativePlaceAddress:'常住地址不能输入特殊字符'}}"/>	    
	    </div>
	    <div class="grid_3 lable-right">职业或身份：</div>
	    <div class="grid_3">
			<select name="peopleInfoVo.positionTemp" id="position" class='form-txt' <s:if test='"view".equals(mode)'>disabled</s:if>>
		   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@POSITION_OR_STATUS" defaultValue='${peopleInfo.position.id}'/>
			</select>	    
	    </div>
	    <div class="addList"><span class="icon">+</span>新增此列</div>
	    <div class="removeList" style="display:none"><span class="icon">-</span>删除此列</div>
	      <div class="clearLine">&nbsp;</div>
	</div>
		   </c:forEach>
	   </s:if>
	   <s:else>
		<div class="container container_24 addPeople">
	   		<div class="grid_3 lable-right"><em class="form-req">*</em>身份证号：</div>
		    <div class="grid_6">
				<input type="text" name="peopleInfoVo.idCardNoTemp" class="idCardNo form-txt"  maxlength="18" value='<s:property value="idCardNo"/>'/>	    
		    </div>
		     <div class="grid_2 lable-right"><em class="form-req">*</em>姓名：</div>
	    <div class="grid_4">
			<input type="text" name="peopleInfoVo.nameTemp" id="name" maxlength="20" value="${steadyWork.name}"
				class="form-txt {validatorNativePlaceAddress:true, messages:{validatorNativePlaceAddress:'姓名不能输入特殊字符'}}"/>	    
	    </div>
	    <div class="grid_3 lable-right">性别：</div>
	    <div class="grid_3">
			<select name="peopleInfoVo.genderTemp" id="gender" class='form-txt' <s:if test='"view".equals(mode)'>disabled</s:if>>
		   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@GENDER" defaultValue="${steadyWork.gender.id}" />
			</select>	    
		</div>
		<div class="clearLine">&nbsp;</div>
	    <div class="grid_3 lable-right">联系手机：</div>
	    <div class="grid_6">
			<input type="text" name="peopleInfoVo.mobileNumberTemp" id="mobileNumber"  maxlength="11"  value="${steadyWork.mobileNumber}" title="请输入11位以1开头的联系手机  例如：13988888888" 
				class='form-txt {mobile:true,messages:{mobile:"手机号码输入只能是以1开头的11位数字"}}' />	    
		</div>
	    <div class="grid_2 lable-right">出生年月：</div>
	    <div class="grid_4 birthdayDiv" >
			<input type="text" name="peopleInfoVo.birthDayTemp" class="birthDay form-txt" maxlength="32" disabled value='<s:date name="steadyWork.birthDay" format="yyyy-MM-dd" />' />	    
		</div>
	    <div class="grid_3 lable-right">是否党员：</div>
	    <div class="grid_3">
			<select id="isPartyMember" name="peopleInfoVo.isPartyMemberTemp" class="form-txt" <s:if test='"view".equals(mode)'>disabled</s:if>>
 				<option value="true" <s:if test="steadyWork.isPartyMember">selected</s:if>>是</option>
 				<option value="false" <s:if test="!steadyWork.isPartyMember">selected</s:if>>否</option>
	 		</select>	    
	    </div>
	    <div class="clearLine">&nbsp;</div>
	    <div class="grid_3 lable-right">常住地址：</div>
	    <div class="grid_12">
			<input type="text" name="peopleInfoVo.addressTemp" id="permanentAddress"  maxlength="50"  value="${steadyWork.permanentAddress}"
				class="form-txt {validatorNativePlaceAddress:true, messages:{validatorNativePlaceAddress:'常住地址不能输入特殊字符'}}"/>	    
	    </div>
	    <div class="grid_3 lable-right">职业或身份：</div>
	    <div class="grid_3">
			<select name="peopleInfoVo.positionTemp" id="position" class='form-txt' <s:if test='"view".equals(mode)'>disabled</s:if>>
		   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@POSITION_OR_STATUS" defaultValue="${steadyWork.position.id}" />
			</select>	    
	    </div>
	    <div class="addList"><span class="icon">+</span>新增此列</div>
	    <div class="removeList" style="display:none"><span class="icon">-</span>删除此列</div>
	    </div>
	    <div class="clearLine">&nbsp;</div>
	   </s:else>
	<div id="addPeoplePos"></div>
	<div id="addPeopleData" style="display:none">
        <div class="container container_24 addPeople">
            <div class="grid_3 lable-right"><em class="form-req">*</em>身份证号：</div>
            <div class="grid_6">
                <input type="text" name="peopleInfoVo.idCardNoTemp" class="idCardNo form-txt"  maxlength="18" value=""/>	    
            </div>
            <div class="grid_2 lable-right"><em class="form-req">*</em>姓名：</div>
            <div class="grid_4">
                <input type="text" name="peopleInfoVo.nameTemp" id="name" maxlength="20" value=""
                    class="form-txt"/>	    
            </div>
            <div class="grid_3 lable-right">性别：</div>
            <div class="grid_3">
            <select name="peopleInfoVo.genderTemp" id="gender" class="form-txt">
		   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@GENDER" defaultValue="" />
			</select>
            </div>
            <div class="clearLine">&nbsp;</div>
            <div class="grid_3 lable-right">联系手机：</div>
            <div class="grid_6">
                <input type="text" name="peopleInfoVo.mobileNumberTemp" id="mobileNumber"  maxlength="11"  value="" title="请输入11位以1开头的联系手机  例如：13988888888" 
                    class='form-txt {mobile:true,messages:{mobile:"手机号码输入只能是以1开头的11位数字"}}' />	    
            </div>
            <div class="grid_2 lable-right">出生年月：</div>
            <div class="grid_4 birthdayDiv">
                <input type="text" name="peopleInfoVo.birthDayTemp" class="birthDay form-txt" maxlength="32" readonly value='<s:date name="steadyWork.birthDayTemp" format="yyyy-MM-dd" />' />	    
            </div>
            <div class="grid_3 lable-right">是否党员：</div>
            <div class="grid_3">
                <select id="isPartyMember" name="peopleInfoVo.isPartyMemberTemp" class="form-txt" <s:if test='"view".equals(mode)'>disabled</s:if>>
                    <option value="true">是</option>
                    <option value="false">否</option>
                </select>	    
            </div>
            <div class="clearLine">&nbsp;</div>
            <div class="grid_3 lable-right">常住地址：</div>
            <div class="grid_12">
                <input type="text" name="peopleInfoVo.addressTemp" id="permanentAddress"  maxlength="50"  value=""
                    class="form-txt {validatorNativePlaceAddress:true, messages:{validatorNativePlaceAddress:'常住地址不能输入特殊字符'}}"/>	    
            </div>
            <div class="grid_3 lable-right">职业或身份：</div>
            <div class="grid_3">
                <select name="peopleInfoVo.positionTemp" id="position" class="form-txt">
                   <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@POSITION_OR_STATUS" defaultValue="" />
                </select>
            </div>
            <div class="addList"><span class="icon">+</span>新增此列</div>
            <div class="removeList" style="display:none"><span class="icon">-</span>删除此列</div>
            <div class="clearLine">&nbsp;</div>
        </div>
    </div>
	
	<div class="container container_24">
	    <div class="grid_3 lable-right">涉稳群体人数：</div>
	    <div class="grid_7">
	    	<input type="text" name="steadyWork.involvingSteadyNum" maxlength="6"  id="involvingSteadyNum" value="${steadyWork.involvingSteadyNum}" class="form-txt">
	    </div>
	    <div class="grid_6 lable-right">涉稳人群类别：</div>
	    <div class="grid_7">
	    	<select name="steadyWork.involvingSteadyType.id" id="involvingSteadyType" class='form-txt' <s:if test='"view".equals(mode)'>disabled</s:if>>
		   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@INVOLVING_STEADY_TYPE" defaultValue="${steadyWork.involvingSteadyType.id}" />
			</select>	    
	    	
	    </div>
	    <div class="grid_3 lable-right">
	    	<em class="form-req">*</em>涉稳事项：
	    </div>
	    <div class="grid_21 heightAuto">
	    	<textarea name="steadyWork.involvingSteadyInfo" id="involvingSteadyInfo"  maxlength="60" class="form-txt">${steadyWork.involvingSteadyInfo}</textarea>
	    </div>
	    <div class="clearLine">&nbsp;</div>
	    <div class="grid_3 lable-right">历次涉稳情况：</div>
	    <div class="grid_21 heightAuto">
	    	<textarea name="steadyWork.previousSteadyInfo" id="previousSteadyInfo" maxlength="200" class="form-txt">${steadyWork.previousSteadyInfo}</textarea>
	    </div>
	    <div class="clearLine">&nbsp;</div>
	    <div class="grid_3 lable-right">历次化解情况：</div>
	    <div class="grid_21 heightAuto">
	    	<textarea name="steadyWork.previousResolveInfo" id="previousResolveInfo" maxlength="200" class="form-txt">${steadyWork.previousResolveInfo}</textarea>
	    </div>
	    <div class="clearLine">&nbsp;</div>
	    <div class="grid_3 lable-right">稳控责任单位：</div>
	    <div class="grid_8">
	    	<input type="text" name="steadyWork.steadyUnit" id="steadyUnit"  maxlength="60" value="${steadyWork.steadyUnit}" class="form-txt">
	    </div>
	    <div class="grid_3 lable-right">稳控责任人：</div>
	    <div class="grid_8">
	    	<input type="text" name="steadyWork.steadyUser" id="steadyUser" maxlength="10" value="${steadyWork.steadyUser}" class="form-txt">
	    </div>
	    <div class="grid_3 lable-right">化解责任部门：</div>
	    <div class="grid_8">
	    	<input type="text" name="steadyWork.resolveUnit" id="resolveUnit" maxlength="60" value="${steadyWork.resolveUnit}" class="form-txt">
	    </div>
	    <div class="grid_3 lable-right">化解责任人：</div>
	    <div class="grid_8">
	    	<input type="text" name="steadyWork.resolveUser" id="resolveUser" maxlength="10" value="${steadyWork.resolveUser}" class="form-txt">
	    </div>
	    <div class="grid_3 lable-right">
	    	<em class="form-req">*</em>诉求类别：
	    </div>
	    <div class="grid_20 heightAuto">
	        <ul class="suqiuList">
		        <c:forEach items="${aspirationTypes.propertyDicts}" var="dict">
			  		<li><label><input type="checkbox" name="steadyWork.aspirationType" value="${dict.id}">${dict.displayName }</label></li>
			  	</c:forEach>
	        	<!--<li><label><input type="checkbox" name="steadyWork.aspirationType" value="0">党纪政纪</label></li>
	        	<li><label><input type="checkbox" name="steadyWork.aspirationType" value="1">组织人事</label></li>
	        	<li><label><input type="checkbox" name="steadyWork.aspirationType" value="2">涉法涉诉</label></li>
	        	<li><label><input type="checkbox" name="steadyWork.aspirationType" value="3">土地、林权</label></li>
	        	<li><label><input type="checkbox" name="steadyWork.aspirationType" value="4">征地拆迁</label></li>
	        	<li><label><input type="checkbox" name="steadyWork.aspirationType" value="5">水利电力</label></li>
	        	<li><label><input type="checkbox" name="steadyWork.aspirationType" value="6">环保</label></li>
	        	<li><label><input type="checkbox" name="steadyWork.aspirationType" value="7">扶贫济困</label></li>
	        	<li><label><input type="checkbox" name="steadyWork.aspirationType" value="8">惠农政策及村(社区)政务、财务</label></li>
	        	<li><label><input type="checkbox" name="steadyWork.aspirationType" value="9">人口与医疗卫生</label></li>
	        	<li><label><input type="checkbox" name="steadyWork.aspirationType" value="10">劳动保障</label></li>
	        	<li><label><input type="checkbox" name="steadyWork.aspirationType" value="11">交通运输</label></li>
	        	<li><label><input type="checkbox" name="steadyWork.aspirationType" value="12">城建</label></li>
	        	<li><label><input type="checkbox" name="steadyWork.aspirationType" value="13">安全生产</label></li>
	        	<li><label><input type="checkbox" name="steadyWork.aspirationType" value="14">旅游</label></li>
	        	<li><label><input type="checkbox" name="steadyWork.aspirationType" value="15">教育</label></li>
	        	<li><label><input type="checkbox" name="steadyWork.aspirationType" value="16">企业改制</label></li>
	        	<li><label><input type="checkbox" name="steadyWork.aspirationType" value="17">移民</label></li>
	        	<li><label><input type="checkbox" name="steadyWork.aspirationType" value="18">涉军</label></li>
	        	<li><label><input type="checkbox" name="steadyWork.aspirationType" value="19">其他</label></li>
	        --></ul>
	        <input type="hidden" id="aspirationType"  value="${steadyWork.aspirationType}" /> 
	    </div>
	
	    <div class="grid_3 lable-right"><span class="twin_row">涉稳人员或群<br/>体稳定现状：</span></div>
	    <div class="grid_21 heightAuto">
	        <textarea name="steadyWork.steadyInfo" id="steadyInfo" maxlength="200" class="form-txt">${steadyWork.steadyInfo}</textarea>
	    </div><div class="grid_3 lable-right"><em class="form-req">*</em>预警级别：</div><div class="grid_21 heightAuto">
	        <ul class="yujingList">
	        	<li><label><input type="radio" name="steadyWork.warningType" value="0"><i class="icon_blue"></i>1-2人且事态在可控范围</label></li>
	        	<li><label><input type="radio" name="steadyWork.warningType" value="1"><i class="icon_yellow"></i>涉及3-9人且事态恶化的可能性</label></li>
	        	<li><label><input type="radio" name="steadyWork.warningType" value="2"><i class="icon_orange"></i>涉及10-30人且事态有扩大趋势</label></li>
	        	<li><label><input type="radio" name="steadyWork.warningType" value="3"><i class="icon_red"></i>涉及30人以上且事态还有扩大趋势的及涉及人数虽较少，但如不及时采取措施事态将恶化</label></li>
	        </ul>
	        <input type="hidden" class="warningType"  value="${steadyWork.warningType}" />
	    </div>
	    
	    <div class="grid_3 lable-right">服务联系人：</div>
	    <div class="grid_6">
	    	<input type="text" name="steadyWork.serverContractor" id="serverContractor"  maxlength="20" class="form-txt" value="${steadyWork.serverContractor}"/>
	    </div>
	    <div class="grid_5 lable-right">服务联系人单位：</div>
	    <div class="grid_9">
	    	<input type="text" name="steadyWork.serverUnit" id="serverUnit"  maxlength="50" class="form-txt" value="${steadyWork.serverUnit}"/>
	    </div>
	    <div class="clearLine">&nbsp;</div>
	    <div class="grid_3 lable-right">服务职务：</div>
	    <div class="grid_6">
	    	<input type="text" name="steadyWork.serverJob" id="serverJob"  maxlength="20" class="form-txt" value="${steadyWork.serverJob}"/>
	    </div>
	    <div class="grid_5 lable-right">联系电话：</div>
	    <div class="grid_9">
	    	<input type="text" name="steadyWork.serverTelephone" id="serverTelephone"  maxlength="13"  value="${steadyWork.serverTelephone}"
				class='form-txt {telephone:true,messages:{telephone:$.format("固定电话不合法，只能输数字和横杠(-)")}}' title="请输入由数字和-组成的联系电话,例如：0577-88888888" />
	    </div>
	</div>
</form>

<script type="text/javascript">
$(function(){
    function addPeople(){
        var _ = $('#maintainForm'),
            box = $('#addPeoplePos'),
            tem = $('#addPeopleData').find('.addPeople').clone();

            tem.find('.addList').hide().next().show();
            
        $('#maintainForm').on('click','.addList',function(){

            if(box.html() === ''){
                box.append(tem.clone())

                box.find('.birthDay').datePicker({
                    yearRange: '1900:2030',
                    dateFormat: 'yy-mm-dd',
                    maxDate:'+0d'
                });
            }

        }).on('click','.removeList',function(){
            $(this).closest('.addPeople').remove()
            //tem = _.find('.addPeople').clone();
            //tem.find('.addList').hide().next().show();
        })
    }
    addPeople();
    // 初始发生化网格 
	initOccurOrgSelector();
	
    
    $('#maintainForm').on('blur','.idCardNo',function(){
        var _ = $(this);
        var parentBox = _.closest('.addPeople');
        var txt = getBirthDayTextFromIdCard( _.val() )
        resetBirthdayField(parentBox , txt)
    })
    
    var firstBox = $('.addPeople').eq(0);
    var firstBirthDay = firstBox.find(".birthDay").val()
    
	resetBirthdayField( firstBox , firstBirthDay);
	
    // console.log( $(".birthDay")[0]).value )
	
	
	jQuery.validator.addMethod("validatorNativePlaceAddress", validatorSpecialWord);
	function validatorSpecialWord(value,element){
		if(value==null||value==undefined||value==""){return true}
		var pattern = new RegExp("[`~!@%#$^&*()=|{}':;',　\\[\\]<>/? \\.；：%……+￥（）【】‘”“'。，、？ ！]");
		return this.optional(element)||!pattern.test(value); 
	}
	
	var serialNumberData;
	jQuery.validator.addMethod("exsistedSerialNumber", function(value, element){
		var value=$('#serialNumber').val();
		if(value==null||value==undefined||value==""){return true}
		$.ajax({
			async: false ,
			url:"${path}/account/steadyWorkManage/hasDuplicateSerialNumber.action",
		   	data:{
				"steadyWork.serialNumber":$('#serialNumber').val(),
				"steadyWork.id":$('#id').val()
	        },
			success:function(responseData){
				serialNumberData = responseData;
			}
		});
		if(!(serialNumberData==null||serialNumberData=="")){
			return false;
		}
		return true;
	});

	
	$("#maintainForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form) {
	        $(form).ajaxSubmit({
				success: function(data){
		        	if(!data.id){
						$.messageBox({
							message:data,
							level: "error"
						});
						return;
					}
		        	if(data.organization){
						data["organization.orgName"]=data.organization.orgName;
					}
					<s:if test='"edit".equals(mode)'>
				    	$.messageBox({message:"稳定台账表修改成功!"});
				    	$("#steadyWorkDialog").dialog("close");
				    	loadSteadyWorkData(USER_ORG_ID);
					</s:if>
					<s:if test='"add".equals(mode)'>
						$.messageBox({message:"稳定台账表新增成功!"});
						$("#steadyWorkDialog").dialog("close");
						loadSteadyWorkData(USER_ORG_ID);
					</s:if>
	      	   	},
	      	   	error: function(XMLHttpRequest, textStatus, errorThrown){
	      	    	alert("提交数据时发生错误");
	      	   	}
	      	});
		},
		rules:{
			"steadyWork.serialNumber":{
				required:true,
				exsistedSerialNumber:true
			},
			"steadyWork.createTableType.id":{
				required:true
			},
			"selectOrgName":{
				required:true,
				orgLevelLessEqual:function(){
						return [getOccurOrgId(),<s:property value="@com.tianque.domain.property.OrganizationLevel@GRID"/>];
					}
			},
			"steadyWork.registrant":{
				required:true,
				exculdeParticalChar:true,
				minlength:2,
				maxlength:20
			},
			"steadyWork.involvingSteadyInfo":{
				required:true,
				minlength:2,
				maxlength:60
			},
			"steadyWork.involvingSteadyNum":{
				digits:true,
				range:[1,999999]
			},
			"steadyWork.aspirationType":{
				required:true
			},
			"steadyWork.warningType":{
				required:true
			},
			"peopleInfoVo.idCardNoTemp":{
				required:true,
				idCard:true
			},
			"peopleInfoVo.nameTemp":{
				required:true,
				exculdeParticalChar:true,
				minlength:2,
				maxlength:20
			},
			"steadyWork.serverContractor":{
				exculdeParticalChar:true,
				minlength:2,
				maxlength:20
			},
			"steadyWork.serverJob":{
				exculdeParticalChar:true,
				minlength:2,
				maxlength:20
			},
			"steadyWork.serverUnit":{
				exculdeParticalChar:true,
				minlength:2,
				maxlength:20
			}
		},
		messages:{
			"steadyWork.serialNumber":{
				required:"请输入编号",
				exsistedSerialNumber:function(){
					return serialNumberData;
				}
			},
			"steadyWork.createTableType.id":{
				required:"请选择建表类型"
			},
			"selectOrgName":{
				required:"请选择发生网格",
				orgLevelLessEqual:"发生网格只能选择网格级别"
			},
			"steadyWork.registrant":{
				required:"请输入登记人姓名",
				exculdeParticalChar:"不能输入非法字符",
				minlength:$.format("登记人姓名至少需要输入{0}个字符"),
				maxlength:$.format("登记人姓名最多需要输入{0}个字符")
			},
			"steadyWork.involvingSteadyInfo":{
				required:"请输入涉稳事项",
				minlength:$.format("涉稳事项至少需要输入{0}个字符"),
				maxlength:$.format("涉稳事项最多需要输入{0}个字符")
			},
			"steadyWork.involvingSteadyNum":{
				digits:"涉稳群体人数只能输入1到999999之间的整数",
				range:$.format("涉稳群体人数只能输入{0}到{1}之间的整数")
			},
			"steadyWork.aspirationType":{
				required:"请选择诉求类别"
			},
			"steadyWork.warningType":{
				required:"请选择预警级别"
			},
			"peopleInfoVo.idCardNoTemp":{
				required:"请输入身份证号",
				idCard:"请输入一个合法的身份证号码"
			},
			"peopleInfoVo.nameTemp":{
				required:"请输入姓名",
				exculdeParticalChar:"不能输入非法字符",
				minlength:$.format("姓名至少需要输入{0}个字符"),
				maxlength:$.format("姓名最多需要输入{0}个字符")
			},
			"steadyWork.serverContractor":{
				exculdeParticalChar:"不能输入非法字符",
				minlength:$.format("服务联系人至少需要输入{0}个字符"),
				maxlength:$.format("服务联系人最多需要输入{0}个字符")
			},
			"steadyWork.serverJob":{
				exculdeParticalChar:"不能输入非法字符",
				minlength:$.format("服务职务至少需要输入{0}个字符"),
				maxlength:$.format("服务职务最多需要输入{0}个字符")
			},
			"steadyWork.serverUnit":{
				exculdeParticalChar:"不能输入非法字符",
				minlength:$.format("服务联系人单位至少需要输入{0}个字符"),
				maxlength:$.format("服务联系人单位最多需要输入{0}个字符")
			}
		},
		ignore:':hidden'
	});
	<s:if test='"add".equals(mode)'>
	    $("#maintainForm").attr("action","${path}/account/steadyWorkManage/addSteadyWork.action");
	</s:if>
	<s:if test='"edit".equals(mode)'>
	    $("#maintainForm").attr("action","${path}/account/steadyWorkManage/updateSteadyWork.action");
	</s:if>
	<s:if test='"add".equals(mode)'>
		$("#registrant").val('${NAME}');
		$("#createTableType option").eq(1).attr("selected", true);
		$.ajax({
			async: false,
			url: "${path}/sysadmin/orgManage/getOrgRelativePath.action",
			data:{
				"organization.id":$("#organizationId").val()
			},
			success:function(responseData){
				$("#steadyWorkOrgName").val(responseData);
				
			}
		});
	</s:if>
    
	<s:if test='"edit".equals(mode)'>
		if($(".warningType").val()!=null){
			$("input[name='steadyWork.warningType'][value='"+$(".warningType").val()+"']").attr("checked",true);
		}
		if($("#aspirationType").val()!=null){
			var ids = $("#aspirationType").val().split(", ");//双引号之间是一个空格以及逗号 ;
			for(var i=0;i<ids.length;i++){
				$("input[name='steadyWork.aspirationType'][value='"+ids[i]+"']").attr("checked",true);
			}
			
　　	}
	</s:if>

    //根据身份证得到出生日期
    function getBirthDayTextFromIdCard(idCard){
        if(idCard!=null&&idCard.length==18){
            idCard=idCard.substring(6,14);
            if(idCard.substring(4,6)<=0||idCard.substring(4,6)>12){
                return "";
            }else if(idCard.substring(6,8)<=0||idCard.substring(6,8)>31){
                return "";
            }else{
                return idCard.substring(0,4)+"-"+idCard.substring(4,6)+"-"+idCard.substring(6,8);
            }
        }else if(idCard!=null&&idCard.length==15){
            idCard=idCard.substring(6,12);
            if(idCard.substring(2,4)<=0||idCard.substring(2,4)>12){
                return "";
            }else if(idCard.substring(4,6)<=0||idCard.substring(4,6)>31){
                return "";
            }else{
                return "19"+idCard.substring(0,2)+"-"+idCard.substring(2,4)+"-"+idCard.substring(4,6);
            }
        }
        return "";
    }

    function resetBirthdayField(obj , txt){
    
        if (txt!="" && obj.find('.idCardNo').val()!=""){
            obj.find(".birthdayDiv").html("<input type='text' name='peopleInfoVo.birthDayTemp' class='birthDay form-txt' value='"+txt+"' disabled/>");
        }else{
            obj.find('.birthDay').datePicker({
                yearRange: '1900:2030',
                dateFormat: 'yy-mm-dd',
                maxDate:'+0d'
            });
        }
    }

    function isNull(){
        if($("#peopleOccurOrgSelector").val()==null||$("#peopleOccurOrgSelector").val()==""){
            $.notice({
                title:"提示",
                message:"请先选择所属网格！",
                width: 300
            });
            return false;
            }
        return true;
    }

    function initOccurOrgSelector(){
            var tree=$("#peopleOccurOrgSelector").treeSelect({
                inputName:"steadyWork.occurOrg.id",
                loadCom:function(){
                    if(peopleEditing()){
                        $.setTreeValue(getDefaultOccurOrg(),tree); 
                    }
                }
            });
            peopleTree=tree;
    }

    function peopleEditing(){
        return <s:property value='"edit".equals(mode)'/>;
    }

    function getDefaultOccurOrg(){
        <s:if test="null!=steadyWork.occurOrg && null!=steadyWork.occurOrg.id">
            return "${steadyWork.occurOrg.id}";
        </s:if>
        <s:else>
            return -1;
        </s:else>
    }

    function  getOccurOrgId(){
        return $("#occurOrgId").val();
    }
});

</script>