<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.tianque.core.util.ThreadVariable"%>
<%@page import="java.util.Date"%>
<s:action name="getLoginInfo" var="loginAction" executeResult="false" namespace="/sessionManage" />
<s:action name="getFullOrgById" var="getFullOrgById" executeResult="false" namespace="/sysadmin/orgManage" >
	<s:param name="organization.id" value="#loginAction.user.organization.id"></s:param>
</s:action>
<link id="_barCss" rel="stylesheet" type="text/css"/>
<%
	Date accessTime=null;
	if(ThreadVariable.getSession()!=null){
		accessTime=ThreadVariable.getSession().getAccessTime();
	}
%>
<%@page import="com.tianque.core.util.GridProperties"%>
<link rel="shortcut icon" href="${resource_path}/resource/images/favicon.ico" type="image/x-icon"/>
<%-- 
<link href="${resource_path}/resource/css/formgrid.css" rel="stylesheet" type="text/css"/>
<link href="${resource_path}/resource/css/reset.css" rel="stylesheet" type="text/css"/>
<link href="${resource_path}/resource/external/jqGrid/css/ui.jqgrid.css" rel="stylesheet" type="text/css"/>
<link href="${resource_path}/resource/external/poshytip/tip-yellowsimple/tip-yellowsimple.css" rel="stylesheet" type="text/css"/>
<link href="${resource_path}/resource/external/loadmask/jquery.loadmask.css" type="text/css" rel="stylesheet"/>
<link rel="stylesheet" type="text/css" href="${resource_path}/resource/external/ext/css/ext-all.css" />
<link rel="stylesheet" type="text/css" href="${resource_path}/resource/external/contextmenu/css/contextmenu.css" />
<link rel="stylesheet" type="text/css" href="${resource_path}/resource/system/js/imageGallery/css/jquery.image-gallery.min.css" />
<link rel="stylesheet" type="text/css" id="switchSkin" />
<link rel="stylesheet" type="text/css" href="${resource_path}/resource/xichang/css/accountReport.css" />
<link type="text/css" href="${resource_path}/resource/external/imgareaselect/imgareaselect-default.css" rel="stylesheet"/>
<link href="${resource_path}/resource/external/reportList/css/sigmawidgets.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/resource/external/jrac/style.jrac.css" />
 --%>
<s:if test='!"production".equals(@com.tianque.core.util.GlobalValue@environment)'>
	<link rel="stylesheet" type="text/css" href="${resource_path}/resource/css/formgrid.css"/>
	<link rel="stylesheet" type="text/css" href="${resource_path}/resource/external/jqGrid/css/ui.jqgrid.css"/>
	<link rel="stylesheet" type="text/css" href="${resource_path}/resource/external/poshytip/tip-yellowsimple/tip-yellowsimple.css"/>
	<link rel="stylesheet" type="text/css" href="${resource_path}/resource/external/loadmask/jquery.loadmask.css" />
	<link rel="stylesheet" type="text/css" href="${resource_path}/resource/external/ext/css/ext-all.css" />
	<link rel="stylesheet" type="text/css" href="${resource_path}/resource/external/contextmenu/css/contextmenu.css" />
	<link rel="stylesheet" type="text/css" href="${resource_path}/resource/system/js/imageGallery/css/jquery.image-gallery.min.css" />
	<link rel="stylesheet" type="text/css" href="${resource_path}/resource/xichang/css/accountReport.css" />
	<link rel="stylesheet" type="text/css" href="${resource_path}/resource/external/imgareaselect/imgareaselect-default.css"/>
	<link rel="stylesheet" type="text/css" href="${resource_path}/resource/external/reportList/css/sigmawidgets.css" />
	<link rel="stylesheet" type="text/css" href="${resource_path}/resource/external/jrac/style.jrac.css" />
	<link rel="stylesheet" type="text/css" href="${resource_path}/resource/external/jqueryui/default/jquery-ui-1.9.2.custom.min.css" />
	<link rel="stylesheet" type="text/css" href="${resource_path}/resource/system/css/main.css" />
	<link rel="stylesheet" type="text/css" id="switchSkin" />
	
	<script type="text/javascript" src="${resource_path}/resource/external/jquery.min.js"></script>
	<script type="text/javascript" src="${resource_path}/resource/external/jquery.cookie.js"></script>
	<script type="text/javascript" src="${resource_path}/resource/external/jquery.bgiframe.js"></script>
	<script type="text/javascript" src="${resource_path}/resource/external/jqueryui/jquery-ui-1.9.2.custom.min.js"></script>
	<script type="text/javascript">$("#mainProgressbar").progressbar({value: 6});$("#mainProgressbarText").text('正在加载资源文件，已完成 6 %');</script>
	<script type="text/javascript" src="${resource_path}/resource/external/jqueryui/jquery-ui-timepicker-addon.js"></script>
	<script type="text/javascript" src="${resource_path}/resource/external/jqueryui/jquery.ui.datepicker-zh-CN.js"></script>
	<script type="text/javascript">$("#mainProgressbar").progressbar({value: 15});$("#mainProgressbarText").text('正在加载核心文件，已完成 15 %');</script>
	<script type="text/javascript" src="${resource_path}/resource/external/jsValidate/jquery.form.js"></script>
	<script type="text/javascript" src="${resource_path}/resource/external/jsValidate/jquery.metadata.js"></script>
	<script type="text/javascript" src="${resource_path}/resource/external/jsValidate/jquery.validate.min.js"></script>
	<script type="text/javascript" src="${resource_path}/resource/external/jsValidate/additional-methods.min.js"></script>
	<script type="text/javascript" src="${resource_path}/resource/external/jsValidate/messages_cn.js"></script>
	<script type="text/javascript" src="${resource_path}/resource/external/validatePassword/digitalspaghetti.password.js"></script>
	<script type="text/javascript" src="${resource_path}/resource/external/jqGrid/js/grid.locale-cn.js"></script>
	<script type="text/javascript">$("#mainProgressbar").progressbar({value: 23});$("#mainProgressbarText").text('正在加载主要资源，已完成 23 %');</script>
	<script type="text/javascript" src="${resource_path}/resource/external/jqGrid/js/jquery.jqGrid.min.js"></script>
	<script type="text/javascript" src="${resource_path}/resource/external/jqGrid/plugins/grid.setcolumns.js"></script>
	<script type="text/javascript" src="${resource_path}/resource/external/contextmenu/jquery.contextmenu.js"></script>
	<script type="text/javascript" src="${resource_path}/resource/external/poshytip/jquery.poshytip.js"></script>
	<script type="text/javascript" src="${resource_path}/resource/external/xheditor/xheditor-1.1.13-zh-cn.min.js"></script>
	<SCRIPT type="text/javascript" src="${resource_path}/resource/external/selectInPlace/jquery-selectInPlace-0.1.js"></SCRIPT>
	<SCRIPT type="text/javascript" src="${resource_path}/resource/system/js/uploadFile.js"></SCRIPT>
	
	<script type="text/javascript">$("#mainProgressbar").progressbar({value: 30});$("#mainProgressbarText").text('正在加载主要资源，已完成 30 %');</script>
	<script type="text/javascript" src="${resource_path }/resource/external/ext/ext-base.js"></script>
	<script type="text/javascript" src="${resource_path }/resource/external/ext/ext-all.js"></script>
	<script type="text/javascript" src="${resource_path }/resource/external/uploadify/swfobject.js"></script>
	<script type="text/javascript" src="${resource_path }/resource/external/uploadify/jquery.uploadify.v2.1.4.min.js"></script>
	<script type="text/javascript" src="${resource_path}/resource/external/reportList/sigmareport.js"></script>
	<script type="text/javascript" src="${resource_path}/resource/external/reportList/sigmabase.js"></script>
	<script type="text/javascript" src="${resource_path}/resource/external/highcharts/highcharts.js"></script>
	<script type="text/javascript" src="${resource_path}/resource/external/highcharts/exporting.js"></script>
	<script type="text/javascript" src="${resource_path}/resource/external/highcharts/highcharts-3d.js"></script>
	<SCRIPT type="text/javascript" src="${resource_path}/resource/external/imgareaselect/jquery.imgareaselect.pack.js"></SCRIPT>
	<script type="text/javascript" src="${resource_path}/resource/external/jrac/jquery.jrac.js"></script>
	<script type="text/javascript">$("#mainProgressbar").progressbar({value: 40});$("#mainProgressbarText").text('正在加载脚本文件，已完成 40 %');</script>
	<script type="text/javascript" src="${resource_path}/resource/external/paging/pagenav.min.js"></script>
	<script type="text/javascript" src="${resource_path}/resource/external/jquery.raty.js"></script>
	<script type="text/javascript" src="${resource_path}/resource/external/jquery.SuperSlide.js"></script>
	<script type="text/javascript" src="${resource_path}/resource/external/jstorage/jquery.json.min.js"></script>
	<script type="text/javascript" src="${resource_path}/resource/external/jstorage/jstorage.min.js"></script>
	<script type="text/javascript" src="${resource_path}/resource/external/loadmask/jquery.loadmask.min.js"></script>
	<script type="text/javascript" src="${resource_path}/resource/system/js/primaryOrgMemberAutoComplete.js"></script>
	<script type="text/javascript" src="${resource_path}/resource/external/raphael/raphael-min.js"></script>
	<script type="text/javascript" src="${resource_path}/resource/external/template.js"></script>
	
	<!-- 只在本地加载 -->
	<script type="text/javascript">$("#mainProgressbar").progressbar({value: 45});$("#mainProgressbarText").text('正在加载脚本文件，已完成 45 %');</script>
	<script type="text/javascript" src="${resource_path}/resource/system/js/userAutocompele.js"></script>
	<script type="text/javascript" src="${resource_path}/resource/system/js/idCheckUtil.js"></script>
	<script type="text/javascript" src="${resource_path}/resource/system/js/dialog.js"></script>
	<script type="text/javascript" src="${resource_path}/resource/system/js/actualPopulationDialog.js"></script>
	<script type="text/javascript" src="${resource_path}/resource/system/js/tabDialog.js"></script>
	<script type="text/javascript" src="${resource_path}/resource/system/js/formValidate.js"></script>
	<script type="text/javascript" src="${resource_path}/resource/system/js/gridUtil.js"></script>
	<script type="text/javascript" src="${resource_path}/resource/system/js/idCheckUtil.js"></script>
	<script type="text/javascript" src="${resource_path}/resource/system/js/AreaData.js"></script>
	<script type="text/javascript" src="${resource_path}/resource/system/js/threeSelect.js"></script>
	<script type="text/javascript" src="${resource_path}/resource/system/js/propertyDictAutocomplete.js"></script>
	<SCRIPT type="text/javascript" src="${resource_path}/resource/system/js/richText.js"></SCRIPT>
	<SCRIPT type="text/javascript" src="${resource_path}/resource/system/js/personnelComplete.js"></SCRIPT>
	<SCRIPT type="text/javascript" src="${resource_path}/resource/system/js/typeSelect.js"></SCRIPT>
	<SCRIPT type="text/javascript" src="${resource_path}/resource/system/js/announcement.js"></SCRIPT>
	<script type="text/javascript" src="${resource_path }/resource/system/js/orgTreeManage.js"></script>
	<script type="text/javascript" src="${resource_path }/resource/system/js/resourcePoolPreminssinTreeManage.js"></script>
	<script type="text/javascript" src="${resource_path }/resource/system/js/resourcePoolTreeManage.js"></script>
	<script type="text/javascript" src="${resource_path }/resource/system/js/permissionTreeManage.js"></script>
	<script type="text/javascript" src="${resource_path }/resource/system/js/dailydirectoryTreeManage.js"></script>
	<SCRIPT type="text/javascript" src="${resource_path}/resource/system/js/treeSelect.js"></SCRIPT>
	<script type="text/javascript" src="${resource_path }/resource/system/js/issueManage/formatter.js"></script>
	<script type="text/javascript" src="${resource_path }/resource/system/js/issueManage/issueManage.js"></script>
	<script type="text/javascript" src="${resource_path }/resource/system/js/issueManage/supervise.js"></script>
	<script type="text/javascript" src="${resource_path}/resource/system/js/charts.js"></script>
	<script type="text/javascript" src="${resource_path }/resource/system/js/issueManage/urgent.js"></script>
	<script type="text/javascript" src="${resource_path}/resource/system/js/printArea.js"></script>
	<script type="text/javascript" src="${resource_path }/resource/system/js/jqgridMultiCheck.js"></script>
	<script type="text/javascript" src="${resource_path }/resource/system/js/excelDownload.js"></script>
	<script type="text/javascript" src="${resource_path}/resource/system/js/inhabitantAutocomplete.js"></script>
	<script type="text/javascript" src="${resource_path}/resource/system/js/actualPopulationAutocomplete.js"></script>
	<script type="text/javascript" src="${resource_path}/resource/system/js/houseAutoComplete.js"></script>
	<script type="text/javascript" src="${resource_path}/resource/system/js/validationExtend.js"></script>
	<script type="text/javascript" src="${resource_path}/resource/system/js/uuid.js"></script>
	<script type="text/javascript" src="${resource_path}/resource/system/js/pagenav.js"></script>
	<script type="text/javascript" src="${resource_path}/resource/system/js/orgSelect.js"></script>
	<script type="text/javascript" src="${resource_path}/resource/system/js/dynamicTagToElement.js"></script>
	<script type="text/javascript" src="${resource_path}/resource/system/js/raphael.widget.js"></script>
	
	<script type="text/javascript">$("#mainProgressbar").progressbar({value: 60});$("#mainProgressbarText").text('正在加载脚本文件，已完成 60 %');</script>
	<script type="text/javascript" src="${resource_path}/resource/system/js/widget/spin.min.js"></script>
	<script type="text/javascript" src="${resource_path}/resource/system/js/widget/widget.js"></script>
	<script type="text/javascript" src="${resource_path }/resource/system/js/init.js"></script>
	<script type="text/javascript" src="${resource_path }/resource/system/js/incident/init.js"></script>
	<script type="text/javascript" src="${resource_path}/resource/system/js/incident/incidentTypeTreeManager.js"></script>
	<script src="${resource_path }/resource/system/js/component.js" type="text/javascript"></script>
	<%-- <script type="text/javascript" src="${resource_path }/resource/edushiGis/js/tqMap/tqMap.js" ></script> --%>
	<%-- <script type=text/javascript src="${resource_path}/resource/edushiGis/js/tqMap/lib/Map.js"></script> --%>
	<script type="text/javascript">$("#mainProgressbar").progressbar({value: 70});$("#mainProgressbarText").text('正在加载脚本文件，已完成 70 %');</script>
	
	<script type="text/javascript" src="${resource_path}/resource/workBench/js/jquery.SuperSlide.js"></script>
	<script type="text/javascript" src="${resource_path}/resource/workBench/js/workbench.widget.js"></script>
	<script type="text/javascript" src="${resource_path}/resource/workBench/js/peopleLog/peopleLog.js" ></script>
	<script type="text/javascript" src="${resource_path}/resource/workBench/js/statAnalyse/statAnalyse.js" ></script>
	<script type="text/javascript" src="${resource_path}/resource/workBench/js/calendarWidget.js" ></script>
	<script type="text/javascript">$("#mainProgressbar").progressbar({value: 80});$("#mainProgressbarText").text('正在加载脚本文件，已完成 80 %');</script>
	<script type="text/javascript" src="${resource_path}/resource/external/multiselect/jquery.multiselect.min.js"></script>
	<script type="text/javascript" src="${resource_path}/resource/system/js/schoolAutoComplete.js"></script>
	<script type="text/javascript" src="${resource_path}/resource/system/js/imageGallery/js/load-image.js"></script>
	<script type="text/javascript" src="${resource_path}/resource/system/js/imageGallery/js/jquery.image-gallery.min.js"></script>
	<script type="text/javascript">$("#mainProgressbar").progressbar({value: 85});$("#mainProgressbarText").text('正在加载脚本文件，已完成 85 %');</script>
	
	<script type="text/javascript" src="${resource_path}/resource/openLayersMap/js/LoadResource.js"></script>
	<script type="text/javascript" src="${resource_path}/resource/openLayersMap/js/HashMap.js"></script>
	<script type="text/javascript" src="${resource_path}/resource/openLayersMap/js/OpenLayers-1/OpenLayers.js"></script>
	<script type="text/javascript" src="${resource_path}/resource/openLayersMap/js/TQOpenLayers.js"></script>
	<script type="text/javascript" src="${resource_path}/resource/openLayersMap/js/TQMapUtil.js"></script>
	<script type="text/javascript" src="${resource_path}/resource/openLayersMap/js/TQMap/TQLayers.js"></script>
	<script type="text/javascript" src="${resource_path}/resource/openLayersMap/js/TQMap/TQTransformat.js"></script>
	<script type="text/javascript" src="${resource_path}/resource/openLayersMap/js/TQMap.js"></script>
	<script type="text/javascript" src="${resource_path}/resource/openLayersMap/js/gis.widget.js"></script>
	<script type="text/javascript" src="${resource_path}/resource/openLayersMap/js/gisList.js"></script>
	<script type="text/javascript" src="${resource_path}/resource/openLayersMap/js/constant.js"></script>
	<script type="text/javascript" src="${resource_path}/resource/openLayersMap/js/pagenav.min.js"></script>
	<script type="text/javascript" src="${resource_path}/resource/openLayersMap/js/convert.js"></script>
	
	<script type="text/javascript" src="${resource_path}/resource/system/js/primaryMemberComplete.js"></script>
	<script type="text/javascript" src="${resource_path}/resource/system/js/fillGenderByIdCardNo.js"></script>
	<script type="text/javascript" src="${resource_path}/resource/system/js/otherMenu.js"></script>
	<script type="text/javascript" src="${resource_path}/resource/system/js/main.js"></script>
</s:if>
<s:else>
	<link rel="stylesheet" type="text/css" href="${resource_path}/resource/fileMerge/baseCss.css" />
	<link rel="stylesheet" type="text/css" id="switchSkin" />
	<script type="text/javascript" src="${resource_path}/resource/openLayersMap/js/LoadResource.js"></script>
	<script type="text/javascript" src="${resource_path}/resource/openLayersMap/js/HashMap.js"></script>
	
	<script type="text/javascript" src="${resource_path}/resource/fileMini/minStage1.<s:property value="@com.tianque.core.util.GlobalValue@JS_NODE_VERSION"/>.js"></script>
	<script type="text/javascript" src="${resource_path}/resource/external/xheditor/xheditor-1.1.13-zh-cn.min.js"></script>
	<script type="text/javascript" src="${resource_path}/resource/fileMini/minStage2.<s:property value="@com.tianque.core.util.GlobalValue@JS_NODE_VERSION"/>.js"></script>
	<script type="text/javascript" src="${resource_path}/resource/fileMini/minStage3.<s:property value="@com.tianque.core.util.GlobalValue@JS_NODE_VERSION"/>.js"></script>
	<script type="text/javascript" src="${resource_path}/resource/fileMini/minStage4.<s:property value="@com.tianque.core.util.GlobalValue@JS_NODE_VERSION"/>.js"></script>
	<script type="text/javascript" src="${resource_path}/resource/fileMini/minStage5.<s:property value="@com.tianque.core.util.GlobalValue@JS_NODE_VERSION"/>.js"></script>
	<script type="text/javascript" src="${resource_path}/resource/fileMini/minStage6.<s:property value="@com.tianque.core.util.GlobalValue@JS_NODE_VERSION"/>.js"></script> 
	
	<script type="text/javascript" src="${resource_path}/resource/openLayersMap/js/OpenLayers-1/OpenLayers.js"></script>
	<script type="text/javascript" src="${resource_path}/resource/openLayersMap/js/TQOpenLayers.js"></script>
	<script type="text/javascript" src="${resource_path}/resource/openLayersMap/js/TQMapUtil.js"></script>
	<script type="text/javascript" src="${resource_path}/resource/openLayersMap/js/TQMap/TQLayers.js"></script>
	<script type="text/javascript" src="${resource_path}/resource/openLayersMap/js/TQMap/TQTransformat.js"></script>
	<script type="text/javascript" src="${resource_path}/resource/openLayersMap/js/TQMap.js"></script>
</s:else>
<script type="text/javascript" src="${resource_path}/resource/account/business.flow.js"></script>
<script>
<%if(accessTime!=null){%>
var sessionTimeOut=<%=GridProperties.SESSION_TIME_OUT%>;
var accessTime=<%=accessTime.getTime()%>;
var intervalId;
function sendAjaxToValidateSession(){
	$.ajax({
		url : "${path}/sessionManage/getCurrentSession.action" ,
		success : function(data){
			if((new Date() - new Date(accessTime))>sessionTimeOut){
				clearInterval(intervalId);
				//TODO login
				$.createDialog({
					title : "登入",
					url:"${path}/login.jsp",
					close:function(){
						return false;
					}
				});
			}else{
				accessTime=new Date(data.accessTime);
			}
		}
	});
}
//sendAjaxToValidateSession();
//intervalId=setInterval("sendAjaxToValidateSession()",5000);
<%}%>
$("#mainProgressbar").progressbar({value: 98});$("#mainProgressbarText").text('正在加载皮肤资源，已完成 98 %');
/*switch($.jStorage.get("skinColor")){
	case 'blue':
		$("#switchSkin").attr("href","/resource/system/css/switchSkin-blue.css");
    	break;
	case 'green':
		$("#switchSkin").attr("href","/resource/system/css/switchSkin-green.css");
    	break;
	case 'red':
		$("#switchSkin").attr("href","/resource/system/css/switchSkin-red.css");
    	break;
    default:
    	
}
*/
var orglevel = '<s:property value="#getFullOrgById.organization.orgLevel.internalId"/>', _skinColor = 'blue';
switch(orglevel){
case '20':
	_skinColor = 'green';
	break;
case '30':
	_skinColor = 'red';
	break;
default:
	_skinColor = 'blue';
}
$("#switchSkin").attr("href","/resource/system/css/switchSkin-"+_skinColor+".css");
</script>
