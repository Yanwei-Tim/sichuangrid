<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>

<div id="dialog-form" title="天网" class="container container_24">
	
	<form id="maintainForm" method="post" action="">
		<input type="hidden" name="mode" id="mode" value="${mode}" />
		<input type="hidden" id="id" name="skynet.id" value="${skynet.id}"/>
		<input type="hidden" id="organizationId" name="skynet.organization.id" value="${skynet.organization.id}"/>
		<input type="hidden" id="orgInternalCode" name="skynet.orgInternalCode" value="${skynet.orgInternalCode}"/>
		<input type="hidden" id="gisType" value="<s:property value='#parameters.gisType'/>"/>

		<div class="grid_4 lable-right">
			<em class="form-req">*</em>
 			<label class="form-lb1">所属网格：</label>
 		</div>
 		<div class="grid_18">
 			<input type="text"  id="skynetOrgName"  name="skynet.organization.orgName"  readonly  value="${skynet.organization.orgName}" style="width: 99%"	class="form-txt" />
 		</div>
 		<div class='clearLine'>&nbsp;</div>
		
	 	<div class="grid_4 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">编号：</label>
	 	</div>
		<div class="grid_7">
			<input type="text" name="skynet.skynetNo" id="skynetNo"  maxlength="3" class="form-txt" value="${skynet.skynetNo}"/>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">地址：</label>
	 	</div>
		<div class="grid_18">
			<input type="text" name="skynet.address" id="address"  maxlength="30" class="form-txt" value="${skynet.address}"/>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
				<label class="form-lbl">经度：</label>
			</div>
			<div class="grid_7">
				<input type="text"  id="skynet-centerLon" name="skynet.openLayersMapInfo.centerLon" value="${skynet.openLayersMapInfo.centerLon}"  readonly class="form-txt"/>
			</div>
			<div class="grid_2 lable-right">
				<label class="form-lbl">纬度：</label>
			</div>
			<div class="grid_6">
				<input type="text" id="skynet-centerLat" name="skynet.openLayersMapInfo.centerLat" value="${skynet.openLayersMapInfo.centerLat}"  readonly class="form-txt"/>
				<input type="hidden" id="skynet-isTransformat" name="skynet.openLayersMapInfo.isTransformat" value="${skynet.openLayersMapInfo.isTransformat}" />
				<input type="hidden" id="skynet-gisType" name="skynet.openLayersMapInfo.gisType" value="${skynet.openLayersMapInfo.gisType}" />
			</div>
			<div class="grid_5">
				<c:if test="${'view'!=mode }">
					<input type="button" value="绑定" class="defaultBtn" id="bindMap"/>
					<c:if test="${'edit'==(mode) && skynet.openLayersMapInfo.centerLon != null && skynet.openLayersMapInfo.centerLat != null }">
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
   		centerLonTwo:'${skynet.openLayersMapInfo.centerLon2}',
   		centerLatTwo:'${skynet.openLayersMapInfo.centerLat2}'
    }
    TQ.maintainSkynetDlg(dfop)
}
$.cacheScript({
    url:'/resource/getScript/digitalCity/publicSecurity/skynetManagement/maintainSkynetDlg.js',
    callback: callback
})
</script>


