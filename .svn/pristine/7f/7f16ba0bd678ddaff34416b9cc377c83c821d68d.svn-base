<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>

<div id="dialog-form" class="container container_24">
	<form id="searchSchoolForm">
        <div class='clearLine'>&nbsp;</div> 
        <div class="grid_5 lable-right">
	     	<label class="form-lbl">学校名称：</label>
	  	</div>
	  	<div class="grid_7">
	    	<input type="text" id="schoolChineseName" name="school.chineseName"  maxlength="20"  class="form-txt" />
	  	</div>
	  	<div class="grid_5 lable-right">
	  		<label class="form-lbl">学校性质：</label> 
	  	</div>
	    <div class="grid_7">
<%-- 	  	  <s:action name="findPropertyDictByDomainName" namespace="/sysadmin/propertyManage" var="getKindProperties"> --%>
<%-- 	            <s:param name="propertyDomain.domainName"  value="@com.tianque.domain.property.PropertyTypes@SCHOOL_PROPERTY" /> --%>
<%--        	 </s:action> --%>
<%--  		 <s:select list="#getKindProperties.propertyDicts" id="schoolKindId"  name="school.kind.id" listKey="id" value="school.kind.id" listValue="displayName" emptyOption="true" cssClass="form-txt" /> --%>
	   	<select id="conditionKindId" name="school.kind.id" class="form-txt" >
				<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@SCHOOL_PROPERTY" />
		</select>
	   
	   </div>
	   <div class='clearLine'>&nbsp;</div>
	   	<div class="grid_5 lable-right">
		  	<label class="form-lbl">学校地址：</label> 
		</div>
		<div class="grid_7">
	  	     <input type="text"  id="schoolAddress"  name="school.address"  class="form-txt" maxlength="50"/>
  	    </div>
	  	 <div class="grid_5 lable-right">
	     	<label class="form-lbl">学校类型：</label>
	  	</div>
	  	<div class="grid_7">
<%-- 	   	 <s:action name="findPropertyDictByDomainName" namespace="/sysadmin/propertyManage" var="getSchoolTypeProperties"> --%>
<%-- 	            <s:param name="propertyDomain.domainName"  value="@com.tianque.domain.property.PropertyTypes@SCHOOL_TYPE" /> --%>
<%--        	</s:action> --%>
<%--  		<s:select list="#getSchoolTypeProperties.propertyDicts"  id="schoolTypeId"  name="school.type.id" listKey="id" value="school.type.id" listValue="displayName" emptyOption="true" cssClass="form-txt" /> --%>
  	    <select id="conditionTypeId" name="school.type.id" class="form-txt" >
				<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@SCHOOL_TYPE" />
		</select>
  	   </div>
	  	<div class='clearLine'>&nbsp;</div> 
	  	
	   <div class="grid_5 lable-right">
	     	<label class="form-lbl">英文名称：</label>
	  	</div>
	  	<div class="grid_7">
	  		<input type="text"  id="schoolEnglishName"  name="school.englishName"  class="form-txt" maxlength="30" />
  	   </div>
		<div class="grid_5 lable-right">
		  	<label class="form-lbl">学校网址：</label> 
		</div>
		<div class="grid_7">
	  	     <input type="text"  id="schoolWebSite"  name="school.webSite"  class="form-txt" maxlength="30" />
  	    </div>
	  	<div class='clearLine'>&nbsp;</div> 
	  	
	  	<div class="grid_5 lable-right">
	     	<label class="form-lbl">校长：</label>
	  	</div>
	  	<div class="grid_7">
	  		<input type="text"  id="schoolPresident"  name="school.president"  class="form-txt" maxlength="20" />
  	   </div>
		<div class="grid_5 lable-right">
		  	<label class="form-lbl">传真：</label> 
		</div>
		<div class="grid_7">
	  	     <input type="text"  id="schoolFax"  name="school.fax"  class="form-txt" maxlength="15"/>
  	    </div>
	  	<div class='clearLine'>&nbsp;</div> 
	  	
	  	<div class="grid_5 lable-right">
	     	<label class="form-lbl">电子邮箱：</label>
	  	</div>
	  	<div class="grid_7">
	  		<input type="text"  id="schoolEmail"  name="school.email"  class="form-txt" maxlength="30"/>
  	   </div>
		<div class="grid_5 lable-right">
		  	<label class="form-lbl">法制副校长：</label> 
		</div>
		<div class="grid_7">
	  	     <input type="text"  id="schoolPersonLiable"  name="school.personLiable"  class="form-txt" maxlength="20"/>
  	    </div>
	  	<div class='clearLine'>&nbsp;</div> 
	  	
	  	<div class="grid_5 lable-right">
	     	<label class="form-lbl">联系电话：</label>
	  	</div>
	  	<div class="grid_7">
	  		<input type="text"  id="schoolPersonLiableTelephone"  name="school.personLiableTelephone"  class="form-txt" maxlength="15" />
  	   </div>
		<div class="grid_5 lable-right">
		  	<label class="form-lbl">联系手机：</label> 
		</div>
		<div class="grid_7">
	  	     <input type="text"  id="schoolPersonLiableMobileNumber"  name="school.personLiableMobileNumber"  class="form-txt" maxlength="11" />
  	    </div>
	  	<div class='clearLine'>&nbsp;</div> 
	  	
	 	<div class="grid_5 lable-right">
	     	<label class="form-lbl">在校人数：</label>
	  	</div>
	  	<div class="grid_3">
	  		<input type="text"  id="schoolMinAtSchoolHeadcount"  name="school.minAtSchoolHeadcount"  class="form-txt" maxlength="5" />
  	   </div>
  	   <div class="grid_1 lable-right">&nbsp;&nbsp;到&nbsp;&nbsp;</div>
  	   <div class="grid_3">
	  		<input type="text"  id="schoolMaxAtSchoolHeadcount"  name="school.maxAtSchoolHeadcount"  class="form-txt" maxlength="5"/>
  	   </div>

		<div class="grid_5 lable-right">
  			<label class="form-lb1">关注状态：</label>
	   	</div>
	   	<div class="grid_7">
			<select id="isLock" name="school.isLogOut" class="form-txt">
				<option value="-1" selected="selected">全部</option>
				<option value="0">现在关注</option>
				<option value="1">曾经关注</option>
			</select>
		</div>
	  	<div class='clearLine'>&nbsp;</div> 
		<jsp:include page="/baseinfo/common/commonHasOrHavntForSearch.jsp">
			<jsp:param value="治安负责人：" name="memberLabelName"/>
			<jsp:param value="school.hasServiceTeamMember" name="memberSelectName"/>
			<jsp:param value="巡场记录：" name="recordLabelName"/>
			<jsp:param value="school.hasServiceRecord" name="recordSelectName"/>
		</jsp:include>
	</form>
</div>
<script type="text/javascript">
$(document).ready(function(){
	var placeName = $("#searchByPlaceName").val();
	if(placeName=="请输入学校名称"){
		$("#schoolChineseName").val("");
	}else{
		$("#schoolChineseName").val(placeName);
	}
});
</script>