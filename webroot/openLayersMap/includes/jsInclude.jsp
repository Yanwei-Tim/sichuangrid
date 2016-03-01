<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<link href="${resource_path}/resource/openLayersMap/css/sysGis.css" rel="stylesheet" />

<script type="text/javascript">
var GisModuleType={},ServiceModeType={},BigModeType={},GisGlobalValueMap={},Properties={},FunctionType={};
(function(){
	if(typeof attentionExtentFormatter == "undefined") window.attentionExtentFormatter=function(){/**特殊人群中点击饼状图显示的列表中，有时候缺少这个方法，特地在地图的jsInclude.jsp中添加了这个方法*/}
	
	/* findStaticInfo({className:"ServiceImplModeType",success:function(data){ServiceModeType = data;}})
	findStaticInfo({className:"BigModeType",success:function(data){BigModeType = data;}})
	findStaticInfo({className:"GisGlobalValueMap",success:function(data){
		GisGlobalValueMap = $.extend(data,{
			getByKey:function(mapName,key){
				var result = GisGlobalValueMap[mapName];
				if(result && key){
					key = key.toUpperCase();
					result = result.toUpperCase();
				}
				var key = result.indexOf("{"+key+"=")>-1? "{"+key+"=" : ", "+key+"=";
				result = result.substring(result.indexOf(key)+key.length);
				result = result.substring(0,result.indexOf(","));
				return result;
			}
		})
	}}) */

	Properties = {/**属性文件*/
		/**组织机构层级*/
		ORG_LEVEL_CITY:"<s:property value='@com.tianque.domain.property.OrganizationLevel@CITY'/>",
		ORG_LEVEL_DISTRICT:"<s:property value='@com.tianque.domain.property.OrganizationLevel@DISTRICT'/>",
		ORG_LEVEL_TOWN:"<s:property value='@com.tianque.domain.property.OrganizationLevel@TOWN'/>",
		ORG_LEVEL_VILLAGE:"<s:property value='@com.tianque.domain.property.OrganizationLevel@VILLAGE'/>",
		ORG_LEVEL_GRID:"<s:property value='@com.tianque.domain.property.OrganizationLevel@GRID'/>",
		/**与用户相关的独有信息，例如组织机构*/
		USER_ORG_ID:"<s:property value='@com.tianque.core.util.ThreadVariable@getUser().getOrganization().getId()'/>",
		USER_ORG_DEPARTMENTNO:"<s:property value='@com.tianque.core.util.ThreadVariable@getUser().getOrganization().getDepartmentNo()'/>"
	}

	FunctionType = {
		REFINESEARCH:"<s:property value='@com.tianque.openLayersMap.util.FunctionType@REFINESEARCH' />"
	}
	
	function findStaticInfo(option){
		var dfop = $.extend({
			async:true,
			className:"",
			success:function(){}
		},option)
		$.ajax({
			async:dfop.async,
			url:"${path}/gisUtilManage/findStaticProperties.action?className="+dfop.className,
			success:dfop.success
		})
	}
})()
</script>