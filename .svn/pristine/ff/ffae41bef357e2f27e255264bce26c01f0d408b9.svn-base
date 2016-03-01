<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript">
/**
	参数说明： url: 要捕获行为的目标(必须)
		  close : 是否捕获目前窗体的关闭事件 true:捕获  false,null：不捕获
		  dom   : 要捕获操作时间的dom元素  null: 不捕获   all: 捕获页面所有元素  自定义: jquery获取元素代码
**/
<s:if test="@com.tianque.core.util.GridProperties@IS_OPEN_UBA">
var UBA_WEB_ACCESS_OPERATING_PATH = 'http://10.0.188.88:9999/';
</s:if>
var UBA_OPERATINGTIME_ARRAY = [
{url: '#peopleManageSystem-householdStaffManagement新增户籍人口', close: true, dom: 'all'},
{url: '#peopleManageSystem-householdStaffManagement修改户籍人口', close: true, dom: 'all'},
{url: '#peopleManageSystem-floatingPopulationManagement新增流动人口', close: true, dom: 'all'},
{url: '#peopleManageSystem-floatingPopulationManagement修改流动人口', close: true, dom: 'all'},
{url: '#peopleManageSystem-overseaPersonManagement新增境外人员', close: true, dom: 'all'},
{url: '#peopleManageSystem-overseaPersonManagement修改境外人员', close: true, dom: 'all'},
{url: '#peopleManageSystem-unsettledPopulation新增未落户人口', close: true, dom: 'all'},
{url: '#peopleManageSystem-unsettledPopulation修改未落户人口', close: true, dom: 'all'},
{url: '#peopleManageSystem新增流动人口', close: true, dom: 'all'},
{url: '#peopleManageSystem修改流动人口', close: true, dom: 'all'},
{url: '#peopleManageSystem新增境外人员', close: true, dom: 'all'},
{url: '#peopleManageSystem修改境外人员', close: true, dom: 'all'},
{url: '#peopleManageSystem新增未落户人口', close: true, dom: 'all'},
{url: '#peopleManageSystem修改未落户人口', close: true, dom: 'all'},
{url: '#peopleManageSystem新增户籍人口', close: true, dom: 'all'},
{url: '#peopleManageSystem修改户籍人口', close: true, dom: 'all'},
{url: '#houseManageSystem新增房屋信息', close: true, dom: 'all'},
{url: '#houseManageSystem修改房屋信息', close: true, dom: 'all'},
{url: '#houseManageSystem-actualHouseManagement新增房屋信息', close: true, dom: 'all'},
{url: '#houseManageSystem-actualHouseManagement修改房屋信息', close: true, dom: 'all'},
{url: '#houseManageSystem新增出租房', close: true, dom: 'all'},
{url: '#houseManageSystem修改出租房', close: true, dom: 'all'},
{url: '#houseManageSystem-rentalHouseManagement新增出租房', close: true, dom: 'all'},
{url: '#houseManageSystem-rentalHouseManagement修改出租房', close: true, dom: 'all'},
{url: '#houseManageSystem新增楼宇信息', close: true, dom: 'all'},
{url: '#houseManageSystem修改楼宇信息', close: true, dom: 'all'},
{url: '#houseManageSystem-builddatasManagement新增楼宇信息', close: true, dom: 'all'},
{url: '#houseManageSystem-builddatasManagement修改楼宇信息', close: true, dom: 'all'},
{url: '#newCompanyPlaceManageSystem新增单位场所', close: true, dom: 'all'},
{url: '#newCompanyPlaceManageSystem修改单位场所', close: true, dom: 'all'},
{url: '#newCompanyPlaceManageSystem-newCompanyPlaceManagement新增单位场所', close: true, dom: 'all'},
{url: '#newCompanyPlaceManageSystem-newCompanyPlaceManagement修改单位场所', close: true, dom: 'all'},
{url: '#serviceWork新增事件处理信息', close: true, dom: 'all'},
{url: '#serviceWork办理', close: true, dom: 'all'},
{url: '#serviceWork修改事件处理信息', close: true, dom: 'all'}
], UBA_AJAX_USETIME = {}, uba_doIndex = 0, uba_jquery_page = new Date().getTime(), isClickSave = false;
function UBA_IntValue(num){var MAX_VALUE = 0x7fffffff;var MIN_VALUE = -0x80000000;if(num > MAX_VALUE || num < MIN_VALUE){return num &= 0xFFFFFFFF;}return num;}
function UBA_HashCode(strKey) {var hash = 0;for (var i = 0; strKey != null && i < strKey.length; i++){hash = hash * 31 + strKey.charCodeAt(i);hash = UBA_IntValue(hash);}return hash;}  
var filterUrl = ['uba_accessTime.png', 'uba_operatingTime.png', '/sysadmin/userMessage/findUserMessagesAndProclamation.action', '/sysadmin/userMessage/findUserMessages.action'];
function uba_isSendUbaLogAjaxRequest(url){
	return typeof(UBA_dealNull) == 'function' && typeof(UBA_WEB_ACCESS_OPERATING_PATH) == 'string' && url != null && url.indexOf('.js?') == -1 && filterUrl.indexOf(url.replace(UBA_WEB_ACCESS_OPERATING_PATH,'').substr(0, url.replace(UBA_WEB_ACCESS_OPERATING_PATH,'').indexOf('?'))) == -1;
}
$(document).ajaxSend(function(event, xhr, s){if(uba_isSendUbaLogAjaxRequest(s.url)){UBA_AJAX_USETIME["UBA"+UBA_HashCode(s.url)] = new Date().getTime();}});
$(document).ajaxComplete(function(event, xhr, s){
	if(uba_isSendUbaLogAjaxRequest(s.url)){
		var _ubaModuleName = UBA_dealNull($("#thisCrumbs:visible").text()).replace(new RegExp("(undefined|当前位置|当前层级| |:|：|\t|\n|\r|(\r\n)|(\u0085)|(\u2028)|(\u2029))", "g"), "");
		$.ajax({type: 'POST', url: UBA_WEB_ACCESS_OPERATING_PATH+'uba_accessTime.png?orgName='+ encodeURI(UBA_dealNull($("#CURRENT_SYSTEM_NAME").attr("orgName"))) 
								+ '&userName=' + encodeURI(UBA_dealNull($("#CURRENT_SYSTEM_NAME").attr('user')))
								+ '&modelName=' + encodeURI(_ubaModuleName == '>>' ||　_ubaModuleName == '>>>' || $("#contentDiv").text().length == 0 ? ($("div.workbenchMain").length == 1 ? '首页' : '') : _ubaModuleName)
								+ "&time=" + ( UBA_AJAX_USETIME["UBA"+UBA_HashCode(s.url)] == null ? 0 : (new Date().getTime() - UBA_AJAX_USETIME["UBA"+UBA_HashCode(s.url)]))
								+ '&sysName=' + encodeURI(UBA_dealNull($("#CURRENT_SYSTEM_NAME").val()))
								+ "&doIndex=" + uba_jquery_page + "_" + (++uba_doIndex)
								+ "&url=" + s.url.replace(new RegExp('&', 'g'),'%26'), dataType: 'jsonp'});
		delete UBA_AJAX_USETIME["UBA"+UBA_HashCode(s.url)];
	}
});
function UBA_dealNull(str){return str == null || str == 'undefined' ? "" : str;}
function UBA_ARRAY_HAS_URL(url){for(var i=0;i<UBA_OPERATINGTIME_ARRAY.length;i++){if(UBA_OPERATINGTIME_ARRAY[i].url == url){return true;}}return false;}
function UBA_ARRAY_HAS_CLOSE(url){for(var i=0;i<UBA_OPERATINGTIME_ARRAY.length;i++){if(UBA_OPERATINGTIME_ARRAY[i].url == url && UBA_OPERATINGTIME_ARRAY[i].close === true){return true;}}return false;}
function UBA_ARRAY_GET_DOM(url){for(var i=0;i<UBA_OPERATINGTIME_ARRAY.length;i++){if(UBA_OPERATINGTIME_ARRAY[i].url == url){return UBA_OPERATINGTIME_ARRAY[i].dom;}}return null;}
</script>