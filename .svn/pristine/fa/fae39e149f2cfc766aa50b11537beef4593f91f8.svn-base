<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ include file="/baseinfo/serviceTeamManage/typeFormatter.jsp" %>
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

	<div class='clearLine'></div>
	<div style="width: 100%;margin-top:6px">
		<table id="teamMemberList"> </table>
		<div id="teamMemberListPager"></div>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function(){
	
	$("#teamMemberList").jqGridFunction({
		datatype: "local",
		colNames:['memberId','teamId','职务','姓名','性别','身份证号码','联系手机','组织名称','组织类别'],
	   	colModel:[
	        {name:'memberId',index:'memberId',hidden:true},
	        {name:'teamId',index:'teamId',hidden:true, width:10},
	        {name:'memberPosition',sortable:true,width:70},
	        {name:'memberName',sortable:false, width:80},
	        {name:'memberGender',sortable:true, width:30},
	   		{name:'memberIdCardNo',sortable:false, width:100},
      		{name:'mobile',sortable:false, width:90},
      		{name:'teamName',sortable:false, width:120},
      		{name:'teamClazz',sortable:false,hidden:true,width:100}
		],
		sortName:'memberId',
		height:400,
	    multiselect:true
	});
	
	setData({"searchServiceTeamMemberVo.teamId":$("#_teamId_").val()});
	
	$("#fastSearchButton").click(function(){
		var condition = $("#searchText").val();
		var teamId = $("#_teamId_").val();
		if(condition == '请输入<s:text name="list.search.button.name"/>条件') return;
		var initParam;
		if("idCardNo"==$("#conditonSelected").val()){
			initParam = {
				"searchServiceTeamMemberVo.teamId":teamId,
				"searchServiceTeamMemberVo.idCard":condition
			};
		}else{
			initParam = {
				"searchServiceTeamMemberVo.teamId":teamId,
				"searchServiceTeamMemberVo.name":condition
			}
		}
		setData(initParam);
	});
	
	function setData(initParam){
		$("#teamMemberList").setGridParam({
			url:"${path}//baseinfo/serviceTeamMemberManage/pageTeamMembersBySearchServiceTeamMemberVo.action",
			datatype: "json",
			page:1
		});
		$("#teamMemberList").setPostData(initParam);
		$("#teamMemberList").trigger("reloadGrid");
		
	}
})
</script>