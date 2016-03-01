<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>

<div id="dialog-form" title="监控系统" class="container container_24">
	
	<form id="maintainForm" method="post" action="">
		<input type="hidden" name="mode" id="mode" value="${mode}" />
		<input type="hidden" id="id" name="videoSystem.id" value="${videoSystem.id}"/>
		<input type="hidden" id="organizationId" name="videoSystem.organization.id" value="${videoSystem.organization.id}"/>
		<input type="hidden" id="orgInternalCode" name="videoSystem.orgInternalCode" value="${videoSystem.orgInternalCode}"/>
		<input type="hidden" id="gisType" value="<s:property value='#parameters.gisType'/>"/>

		<div class="grid_4 lable-right">
			<em class="form-req">*</em>
 			<label class="form-lb1">所属网格：</label>
 		</div>
 		<div class="grid_18">
 			<input type="text"  id="videoSystemOrgName"  name="videoSystem.organization.orgName"  readonly  value="${videoSystem.organization.orgName}" style="width: 99%"	class="form-txt" />
 		</div>
 		<div class='clearLine'>&nbsp;</div>
		
	 	<div class="grid_4 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">编号：</label>
	 	</div>
		<div class="grid_18">
			<input type="text" name="videoSystem.videoNo" id="videoNo"  maxlength="50" class="form-txt" value="${videoSystem.videoNo}"/>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">地址：</label>
	 	</div>
		<div class="grid_18">
			<input type="text" name="videoSystem.address" id="address"  maxlength="30" class="form-txt" value="${videoSystem.address}"/>
		</div>
		
			<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">网络地址：</label>
	 	</div>
		<div class="grid_18">
			<input type="text" name="videoSystem.url" id="url"  maxlength="30" class="form-txt" value="${videoSystem.url}"/>
		</div>
			<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">账号：</label>
	 	</div>
		<div class="grid_18">
			<input type="text" name="videoSystem.account" id="account"  maxlength="30" class="form-txt" value="${videoSystem.account}"/>
		</div>
		<div class='clearLine'>&nbsp;</div>
			<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">密码：</label>
	 	</div>
		<div class="grid_18">
			<input type="password" name="videoSystem.password" id="password"  maxlength="30" class="form-txt" value="${videoSystem.password}"/>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
				<label class="form-lbl">经度：</label>
			</div>
			<div class="grid_7">
				<input type="text"  id="videoSystem-centerLon" name="videoSystem.openLayersMapInfo.centerLon" value="${videoSystem.openLayersMapInfo.centerLon}"  readonly class="form-txt"/>
			</div>
			<div class="grid_2 lable-right">
				<label class="form-lbl">纬度：</label>
			</div>
			<div class="grid_6">
				<input type="text" id="videoSystem-centerLat" name="videoSystem.openLayersMapInfo.centerLat" value="${videoSystem.openLayersMapInfo.centerLat}"  readonly class="form-txt"/>
				<input type="hidden" id="videoSystem-isTransformat" name="videoSystem.openLayersMapInfo.isTransformat" value="${videoSystem.openLayersMapInfo.isTransformat}" />
				<input type="hidden" id="videoSystem-gisType" name="videoSystem.openLayersMapInfo.gisType" value="${videoSystem.openLayersMapInfo.gisType}" />
			</div>
			<div class="grid_5">
				<c:if test="${'view'!=mode }">
					<input type="button" value="绑定" class="defaultBtn" id="bindMap"/>
					<c:if test="${'edit'==mode && videoSystem.openLayersMapInfo.centerLon != null && videoSystem.openLayersMapInfo.centerLat != null }">
						<input type="button" value="解绑" class="defaultBtn" id="cancelBind"/>
					</c:if>
				</c:if>
			</div>
		<div class='clearLine'>&nbsp;</div>
		
	</form>
	
</div>
<script type="text/javascript">


function callback(){
    var dfop = {
   		centerLonTwo:'${videoSystem.openLayersMapInfo.centerLon2}',
   		centerLatTwo:'${videoSystem.openLayersMapInfo.centerLat2}'
    }
    TQ.maintainVideoSystemDlg(dfop)
}
$.cacheScript({
    url:'/resource/getScript/digitalCity/publicSecurity/videoSystemManagement/maintainVideoSystemDlg.js',
    callback: callback
})
</script>


