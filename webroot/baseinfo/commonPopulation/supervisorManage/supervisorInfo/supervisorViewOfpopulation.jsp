<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<div class="content">
	<div style="clear: both;"></div>
	<div style="width: 100%;">
		<table id="viewSupervisorList"> </table>
		<div id="viewSupervisorListPager"></div>
	</div>
</div>

<div style="display: none;">
	<span id="title"></span>
</div>
<script type="text/javascript">
$("#title").val($("#populationType").val());
<pop:formatterProperty name="gender" domainName="@com.tianque.domain.property.PropertyTypes@GENDER" />
var title=$("#title").html();
function onOrgChanged(){
	$("#viewSupervisorList").setGridParam({
		url:'${path}/supervisorManage/supervisorInfoManage/findSupervisorforList.action',
		datatype: "json",
		page:1
	});
	$("#viewSupervisorList").setPostData({
		"population.populationId":$("#populationId").val(),
    	"population.populationType":$("#populationType").val()
   	});
	$("#viewSupervisorList").trigger("reloadGrid");
}
$(document).ready(function(){
	
	$("#viewSupervisorList").jqGridFunction({
		datatype: "local",
		sortname:'memberId',
	   	colModel:[
	        {name:"id",index:"id",hidden:true,hidedlg:true},
	        {name:"memberPosition",label:'职务',width:110},
	        {name:"name",index:'name',label:'姓名',width:80},
	        {name:"gender",index:'gender',label:'性别',width:50,align:'center',formatter:genderFormatter},
	        {name:'idCardNo',index:'idCardNo',label:'身份证号码',width:180},
	   		{name:'mobile',label:'联系手机',sortable:false, width:120},
      		{name:'teamName',label:'管理服务团队名称',sortable:false, width:200}
		],
	    height:360,
	    rowNum:20,
	    showColModelButton:false
	});
	onOrgChanged();
});
</script>

