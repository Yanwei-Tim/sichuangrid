<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>

<div id="dialog-form" title="卡口" class="container container_24">
	
	<form id="maintainForm" method="post" action="">
		<input type="hidden" name="mode" id="mode" value="${mode}" />
		<input type="hidden" id="id" name="bayonet.id" value="${bayonet.id}"/>
		<input type="hidden" id="organizationId" name="bayonet.organization.id" value="${bayonet.organization.id}"/>
		<input type="hidden" id="orgInternalCode" name="bayonet.orgInternalCode" value="${bayonet.orgInternalCode}"/>
		<input type="hidden" id="gisType" value="<s:property value='#parameters.gisType'/>"/>

		<div class="grid_4 lable-right">
			<em class="form-req">*</em>
 			<label class="form-lb1">所属网格：</label>
 		</div>
 		<div class="grid_18">
 			<input type="text"  id="bayonetOrgName"  name="bayonet.organization.orgName"  readonly  value="${bayonet.organization.orgName}" style="width: 99%"	class="form-txt" />
 		</div>
 		<div class='clearLine'>&nbsp;</div>
		
	 	<div class="grid_4 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">编号：</label>
	 	</div>
		<div class="grid_7">
			<input type="text" name="bayonet.bayonetNo" id="bayonetNo"  maxlength="3" class="form-txt" value="${bayonet.bayonetNo}"/>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">地址：</label>
	 	</div>
		<div class="grid_18">
			<input type="text" name="bayonet.address" id="address"  maxlength="30" class="form-txt" value="${bayonet.address}"/>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
				<label class="form-lbl">经度：</label>
			</div>
			<div class="grid_7">
				<input type="text"  id="bayonet-centerLon" name="bayonet.openLayersMapInfo.centerLon" value="${bayonet.openLayersMapInfo.centerLon}"  readonly class="form-txt"/>
			</div>
			<div class="grid_2 lable-right">
				<label class="form-lbl">纬度：</label>
			</div>
			<div class="grid_6">
				<input type="text" id="bayonet-centerLat" name="bayonet.openLayersMapInfo.centerLat" value="${bayonet.openLayersMapInfo.centerLat}"  readonly class="form-txt"/>
				<input type="hidden" id="bayonet-isTransformat" name="bayonet.openLayersMapInfo.isTransformat" value="${bayonet.openLayersMapInfo.isTransformat}" />
				<input type="hidden" id="bayonet-gisType" name="bayonet.openLayersMapInfo.gisType" value="${bayonet.openLayersMapInfo.gisType}" />
			</div>
			<div class="grid_5">
				<c:if test="${'view'!=mode }">
					<input type="button" value="绑定" class="defaultBtn" id="bindMap"/>
					<c:if test="${'edit'==mode && bayonet.openLayersMapInfo.centerLon != null && bayonet.openLayersMapInfo.centerLat != null }">
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
   		centerLonTwo:'${bayonet.openLayersMapInfo.centerLon2}',
   		centerLatTwo:'${bayonet.openLayersMapInfo.centerLat2}'
    }
    TQ.maintainBayonetDlg(dfop)
}
$.cacheScript({
    url:'/resource/getScript/digitalCity/publicSecurity/bayonetManagement/maintainBayonetDlg.js',
    callback: callback
})
</script>


