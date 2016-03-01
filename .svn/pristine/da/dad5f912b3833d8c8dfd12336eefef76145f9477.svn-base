<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%
	request.setAttribute("memberLabelName",request.getParameter("memberLabelName"));
	request.setAttribute("memberSelectName",request.getParameter("memberSelectName"));
	request.setAttribute("recordLabelName",request.getParameter("recordLabelName"));
	request.setAttribute("recordSelectName",request.getParameter("recordSelectName"));
%>
<div class="grid_5 lable-right">
    <label class="form-lbl">有无${memberLabelName}</label>
</div>
<div class="grid_7">
    <select name="${memberSelectName}" class="form-txt" id="searchHasServiceTeamMember">
        <option value="<s:property value='@com.tianque.core.util.ConstantsProduct$HasOrHavntOrAll@ALL'/>" selected><s:property value='@com.tianque.core.util.ConstantsProduct$HasOrHavntOrAll@ALLSTRING'/></option>
		<option value="<s:property value='@com.tianque.core.util.ConstantsProduct$HasOrHavntOrAll@HAS'/>"><s:property value='@com.tianque.core.util.ConstantsProduct$HasOrHavntOrAll@HASSTRING'/></option> 
		<option value="<s:property value='@com.tianque.core.util.ConstantsProduct$HasOrHavntOrAll@HAVNT'/>"><s:property value='@com.tianque.core.util.ConstantsProduct$HasOrHavntOrAll@HAVNTSTRING'/></option>
    </select>
</div>

<div class="grid_5 lable-right">
    <label class="form-lbl">有无${recordLabelName}</label>
</div>
<div class="grid_7">
    <select name="${recordSelectName}" class="form-txt" id="searchHasServiceRecord">
        <option value="<s:property value='@com.tianque.core.util.ConstantsProduct$HasOrHavntOrAll@ALL'/>" selected><s:property value='@com.tianque.core.util.ConstantsProduct$HasOrHavntOrAll@ALLSTRING'/></option>
		<option value="<s:property value='@com.tianque.core.util.ConstantsProduct$HasOrHavntOrAll@HAS'/>"><s:property value='@com.tianque.core.util.ConstantsProduct$HasOrHavntOrAll@HASSTRING'/></option> 
		<option value="<s:property value='@com.tianque.core.util.ConstantsProduct$HasOrHavntOrAll@HAVNT'/>"><s:property value='@com.tianque.core.util.ConstantsProduct$HasOrHavntOrAll@HAVNTSTRING'/></option>
    </select>
 </div>
