<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop" %>
<jsp:include page="/includes/baseInclude.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="dialog-form" title="修改个人信息" class="container container_24" style="overflow:hidden;background:#fff;">
	<div class="grid_5 heightAuto uploadImageBox">
		<div class="imgBox">
			<c:choose>
			    <c:when test="${null!=partyworkMembers.imageUrl && not empty partyworkMembers.imageUrl}">
			     	<img id="imgUrl" src="${partyworkMembers.imageUrl}"></img>
			    </c:when>
			    <c:otherwise>
			    	<img id="imgUrl" src="${resource_path}/resource/system/images/avatar.jpg"></img>
			    </c:otherwise>
			</c:choose>
			<div class="shadow" id="deleteHeaderImageBox" style="display: none">
				<div class="bgc"></div>
				<div  id="deleteHeaderImage" class="deleteHeaderImage">删除头像</div>
			</div>
		</div>
		<form id="maintainUrlForm" method="post" action="${path}/imageUpload/uploadImg.action" enctype="multipart/form-data" name="maintainUrlForm">
			<div class="uploadfileBtn" id="uploadfileBtnId" ><span>上传照片</span><input id="uploadImg" type="file" name="header" /></div>
		</form>
	</div>

	<form name="updateDetailsForm" method="post" action="${path }/basicCaseManage/addPartyWorkMembers.action" id="updateDetailsForm">
		<pop:token /> 
		<div class="grid_19 heightAuto rightPanelBox">
			<pop:token />
			<input type="hidden" name="mode" id="mode" value="${mode}" />
			<input type="hidden" value="${partyworkMembers.id}" name="partyworkMembers.id" />
			<input type="hidden" value="${partyworkMembers.orgCode}" name="partyworkMembers.orgCode" id="orgCode"/>
			<input type="hidden" value="${partyworkMembers.organization.id}" name="partyworkMembers.organization.id" id="orgId"/>
			<input id="headerUrl" type="hidden" value="${partyworkMembers.imageUrl}" name="partyworkMembers.imageUrl" />
			<input type="hidden" value="${baseInfoType}" name="partyworkMembers.baseInfoType"/>
			<div class="clearfix">
				<div class="grid_4 lable-right">
				<em class="form-req">*</em>
				<label class="form-label">姓名：</label>
			</div>
			<div class="grid_6">
				<input type="text" class="form-txt" name="partyworkMembers.name" maxlength="20"
					id="name" value="${partyworkMembers.name}"  <c:if test='${mode=="view"}'>readonly</c:if>/>
			</div>
			<div class="grid_1"></div>

			<div class="grid_4 lable-right">
				<em class="form-req">*</em>
				<label class="form-label">性别：</label>
			</div>
			<div class="grid_6">
				 <select name="partyworkMembers.gender.id" id="gender" class='form-txt {required:true,messages:{required:"请选择性别"}}'>
			   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@GENDER" defaultValue="${partyworkMembers.gender.id}" />
			</select>
			</div>
			<div class="grid_3"></div>
			</div>
			<div class="clearline"></div>
			<div class="grid_4 lable-right">
				<em class="form-req">*</em>
				<label class="form-label">职务：</label>
			</div>
			<div class="grid_6">
				<input type="text" class="form-txt" maxlength="30"  <c:if test='${mode=="view"}'>readonly</c:if>
					name="partyworkMembers.duty" id="duty" value="${partyworkMembers.duty}" />
			</div>
			<div class="grid_1"></div>		
			 <div class="grid_4 lable-right">
				&nbsp;
			</div>
			<div class="grid_6">
				&nbsp;
			</div>
			<div class="grid_1"></div>
			<div class="clearline"></div>
			<div class="grid_4 lable-right">
				<em class="form-req">*</em>
				<label class="form-label">简介：</label>
			</div>
			<div class="grid_18 heightAuto">
				<textarea id="introduction" name="partyworkMembers.introduction" maxlength="1000"
					defaultvalue="" class="form-txt" <c:if test='${mode=="view"}'>readonly</c:if> style="height:220px;">${partyworkMembers.introduction}</textarea>
			</div>
		</div>
	</form>
</div>
<div id="scanHeaderImage"></div>
<script type="text/javascript">
function callback(){
    var dfop = {
    	token:"<s:property value='@com.tianque.core.util.FormTokenHelper@TOKEN_NAME'/>=<pop:token ajax='true'/>",
        path:'${path}'
    }
    TQ.partyworkMembersDlg(dfop);
    
}

$.cacheScript({
    url:'/resource/getScript/partyBuilding/baseInfos/partyworkMembersDlg.js',
    callback: callback
})
</script>