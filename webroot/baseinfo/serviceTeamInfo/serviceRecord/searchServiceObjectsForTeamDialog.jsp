<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<div class="container container_24">
	<div class="btnbanner btnbannerData">
		<div class="ui-widget autosearch">
	    	<select name="conditonSelected" id="conditonSelected">
	       		<option value="idCardNo" selected="selected">身份证号码</option>
	       		<option value="name">姓名</option>
	    	</select>
	    	<input type="text" value="请输入<s:text name="list.search.button.name"/>条件" name="searchText" id="searchText" maxlength="18" class="searchtxt" onblur="value=(this.value=='')?'请输入<s:text name="list.search.button.name"/>条件':this.value;" onfocus="value=(this.value=='请输入<s:text name="list.search.button.name"/>条件')?'':this.value;"/>
			<button id="refreshSearchKey" class="ui-icon ui-icon-refresh searchbtnico"></button>
		</div>
		<a href="javascript:;" id="fastSearchButton"><span><s:text name="list.search.button.name"/></span></a>
	</div>

	<div style="width: 100%;margin-top:6px">
		<table id="serviceObjectList"> </table>
		<div id="serviceObjectListPager"></div>
	</div>
</div>

<script type="text/javascript">
<pop:formatterProperty name="gender" domainName="@com.tianque.domain.property.PropertyTypes@GENDER" />
$(document).ready(function(){
	$("#serviceObjectList").jqGridFunction({
		datatype: "local",
		colNames:['sid','populationId','populationType','姓名','所属网格','性别','身份证号码','人员类型','服务者','服务记录','最新接受服务时间'],
	   	colModel:[
			{name:"sid",index:"sid",hidden:true,key:true},
	        {name:"populationId",hidden:true},
		    {name:'populationType',hidden:true},
	        {name:"name",width:100,sortable:false},
	        {name:"organization.orgName",width:120,sortable:false},
		    {name:'gender',formatter:genderFormatter,sortable:false,width:70},
		    {name:'idCardNo',width:100,sortable:false},
		    {name:'attentionPopulationTypeCname',width:70,sortable:false},
		    {name:'servicer',sortable:false,hidden:true},
		    {name:'serviceRecord',sortable:false,hidden:true},
	        {name:'lastestServiceRecordDate',sortable:false,hidden:true}
		],
		height:400,
	    multiselect:true
	});
	
	setData({"searchServiceObjectVo.serviceTeamId":$("#_teamId_").val()});
	
	$("#fastSearchButton").click(function(){
		var condition = $("#searchText").val();
		var teamId = $("#_teamId_").val();
		if(condition == '请输入<s:text name="list.search.button.name"/>条件') return;
		var initParam;
		if("idCardNo"==$("#conditonSelected").val()){
			initParam = {
				"searchServiceObjectVo.serviceTeamId": teamId,
				"searchServiceObjectVo.idCardNumber":condition
			};
		}else{
			initParam = {
				"searchServiceObjectVo.serviceTeamId": teamId,
				"searchServiceObjectVo.name":condition
			}
		}
		setData(initParam);	
	});
	
	function setData(initParam) {
		$("#serviceObjectList").setGridParam({
			url:'${path}/baseinfo/serviceTeam/searchServiceObject/searchServiceObjectsForTeam.action',
			datatype: "json",
			page:1
		});
		$("#serviceObjectList").setPostData(initParam);
		$("#serviceObjectList").trigger("reloadGrid");
	}
})
</script>