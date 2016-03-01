<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/includes/baseInclude.jsp" />

<div class="center-left">
		<ul id="propertiesDomainYear">
			<s:iterator value="gisModuleVoList" id="moduleVo">
		    	<li><a href="javascript:void(0)" class="daily" name="<s:property value="id"/>"><s:property value="moduleName"/></a><span></span></li>
			</s:iterator>
		</ul>
</div>

<div class="center-right" id="dailyTrunkDirectory"></div>
<div id="dailyTrunkDirectoryDailog"></div>
<div id="dailySubDirectoryMaintanceDialog"></div>
<div id="moduleDialog"></div>
<div id="iconDialog"></div>
<div id="columnSelectDialog"></div>

<script type="text/javascript">
	$(document).ready(function(){
		$.dialogLoading("close");
		initMenu();
		$("#propertiesDomainYear").find("a").each(function(){//设置字符长度
			var str=$(this).html();
			$(this).html(str.substring(0,5));
		});
		if($("#propertiesDomainYear li a").length>0){
	 		$("#propertiesDomainYear li a:first").click();
	 	}else{
	 		getGisModuleById(0);
	 	}
	});
	

function getGisModuleById(id){
	$("#dailyTrunkDirectory").load("${path}/gis/twoDimensionMapModuleManage/getGisModuleVoById.action?gisModuleVo.id="+id);
}

function initMenu(){
	$("#propertiesDomainYear li a").bind("click", function(){
		$("#propertiesDomainYear li").removeClass("select");
		getGisModuleById($(this).attr("name"));
		$(this).parent().addClass("select");
	});
}
</script>