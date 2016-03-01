<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<jsp:include page="/includes/baseInclude.jsp" />
<form id="searchCommentLogForm">
	<input type="hidden" name="userAccountSearchVo.organization.id" value="${organization.id }"/>
	<input type="hidden" name="userAccountSearchVo.searchType" value="${searchType }"/>
	<div class="container container_24">
		<div class="grid_5 lable-right" class="form-txt ">
			行政职能:
		</div>
		<div class="grid_7 " >
	    	  <select name="userAccountSearchVo.orgType.id" class="form-txt">
	                <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@ORGANIZATION_TYPE" />
	            </select>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_5 lable-right" class="form-txt ">
			农村城市:
		</div>
		<div class="grid_7 " >
	    	<select name="userAccountSearchVo.cityVillageType" class="form-txt ">
	    		<option value="0">所有</option>
	    		<option value="1">城市</option>
	    		<option value="2">农村</option>
	    	</select>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_5 lable-right" class="form-txt ">
			账号类别:
		</div>
		<div class="grid_7 " >
	    	<select name="userAccountSearchVo.userAccountType" class="form-txt ">
	    		<option value="0">所有</option>
	    		<option value="1">PC</option>
	    		<option value="2">手机</option>
	    	</select>
		</div>
	<div class='clearLine'>&nbsp;</div>
	    <div class="grid_5 lable-right" class="form-txt ">
			<label class="form-lbl">激活时间：</label>
		</div>
		<div class="grid_7 " >
	    	<input type="text" id="startActivationTime" name="userAccountSearchVo.startActivationTime" id=""
				 readonly class="form-txt"/>
		</div>
		<div class="grid_1 " >----</div>
		<div class="grid_7 " >
	    	<input type="text" id="endActivationTime" name="userAccountSearchVo.endActivationTime" id=""
				 readonly class="form-txt"/>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_5 lable-right" class="form-txt ">
			<label class="form-lbl">最后登录时间：</label>
		</div>
		<div class="grid_7 " >
	    	<input type="text" id="startLastLoginTime" name="userAccountSearchVo.startLastLoginTime" id=""
				 readonly class="form-txt"/>
		</div>
		<div class="grid_1 " >----</div>
		<div class="grid_7 " >
	    	<input type="text" id="endLastLoginTime" name="userAccountSearchVo.endLastLoginTime" id=""
				 readonly class="form-txt"/>
		</div>
	</div>
</form>
<script type="text/javascript">
$(document).ready(function(){
	$('#startActivationTime').datePicker({
		yearRange:'2008:2060',
		dateFormat:'yy-mm-dd',
		maxDate:'%y-%M-#{%d}'
	});

	$('#endActivationTime').datePicker({
		yearRange:'2008:2060',
		dateFormat:'yy-mm-dd',
		maxDate:'%y-%M-#{%d}'
	});

	$('#startLastLoginTime').datePicker({
		yearRange:'2008:2060',
		dateFormat:'yy-mm-dd',
		maxDate:'%y-%M-#{%d}'
	});

	$('#endLastLoginTime').datePicker({
		yearRange:'2008:2060',
		dateFormat:'yy-mm-dd',
		maxDate:'%y-%M-#{%d}'
	});
	  $("#searchCommentLogForm").formValidate({
			promptPosition: "bottomRight",
			submitHandler: function(form){
				var params = $(form).serialize();
				jQuery("#accountLoginDetailsList").setPostData({});
				var url = jQuery("#accountLoginDetailsList").getGridParam("url");
				jQuery("#accountLoginDetailsList").setGridParam({"url":"${path }/sysadmin/userAccountLoginDetailManage/searchUserAccountForPageResult.action?"+params});
				$("#accountLoginDetailsList").trigger("reloadGrid");
			}
		});
});
</script>
