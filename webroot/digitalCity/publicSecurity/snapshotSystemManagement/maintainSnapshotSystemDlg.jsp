<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>

<div id="dialog-form" title="抓拍系统" class="container container_24">
	
	<form id="maintainForm" method="post" action="">
		<input type="hidden" name="mode" id="mode" value="${mode}" />
		<input type="hidden" id="id" name="snapshotSystem.id" value="${snapshotSystem.id}"/>
		<input type="hidden" id="organizationId" name="snapshotSystem.organization.id" value="${snapshotSystem.organization.id}"/>
		<input type="hidden" id="orgInternalCode" name="snapshotSystem.orgInternalCode" value="${snapshotSystem.orgInternalCode}"/>
		<input type="hidden" id="gisType" value="<s:property value='#parameters.gisType'/>"/>

		<div class="grid_4 lable-right">
			<em class="form-req">*</em>
 			<label class="form-lb1">所属网格：</label>
 		</div>
 		<div class="grid_18">
 			<input type="text"  id="snapshotSystemOrgName"  name="snapshotSystem.organization.orgName"  readonly  value="${snapshotSystem.organization.orgName}" style="width: 99%"	class="form-txt" />
 		</div>
 		<div class='clearLine'>&nbsp;</div>
		
	 	<div class="grid_4 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">编号：</label>
	 	</div>
		<div class="grid_7">
			<input type="text" name="snapshotSystem.snapshotNo" id="snapshotNo"  maxlength="3" class="form-txt" value="${snapshotSystem.snapshotNo}"/>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">地址：</label>
	 	</div>
		<div class="grid_18">
			<input type="text" name="snapshotSystem.address" id="address"  maxlength="30" class="form-txt" value="${snapshotSystem.address}"/>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
				<label class="form-lbl">经度：</label>
			</div>
			<div class="grid_7">
				<input type="text"  id="snapshotSystem-centerLon" name="snapshotSystem.openLayersMapInfo.centerLon" value="${snapshotSystem.openLayersMapInfo.centerLon}"  readonly class="form-txt"/>
			</div>
			<div class="grid_2 lable-right">
				<label class="form-lbl">纬度：</label>
			</div>
			<div class="grid_6">
				<input type="text" id="snapshotSystem-centerLat" name="snapshotSystem.openLayersMapInfo.centerLat" value="${snapshotSystem.openLayersMapInfo.centerLat}"  readonly class="form-txt"/>
				<input type="hidden" id="snapshotSystem-isTransformat" name="snapshotSystem.openLayersMapInfo.isTransformat" value="${snapshotSystem.openLayersMapInfo.isTransformat}" />
				<input type="hidden" id="snapshotSystem-gisType" name="snapshotSystem.openLayersMapInfo.gisType" value="${snapshotSystem.openLayersMapInfo.gisType}" />
			</div>
			<div class="grid_5">
				<c:if test="${'view'!=mode }">
					<input type="button" value="绑定" class="defaultBtn" id="bindMap"/>
					<c:if test="${'edit'==mode && snapshotSystem.openLayersMapInfo.centerLon != null && snapshotSystem.openLayersMapInfo.centerLat != null }">
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
   		centerLonTwo:'${snapshotSystem.openLayersMapInfo.centerLon2}',
   		centerLatTwo:'${snapshotSystem.openLayersMapInfo.centerLat2}'
    }
    TQ.maintainSnapshotSystemDlg(dfop)
}
$.cacheScript({
    url:'/resource/getScript/digitalCity/publicSecurity/snapshotSystemManagement/maintainSnapshotSystemDlg.js',
    callback: callback
})
</script>


