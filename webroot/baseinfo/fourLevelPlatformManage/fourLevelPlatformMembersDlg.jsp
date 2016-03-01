<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
<div id="viewPrimaryOrgMemer" class="container container_24">

	<div class="btnbanner btnbannerData">
		<div class="ui-widget autosearch">
		    <input type="text" value="请输入姓名或手机号" name="searchText" id="searchText" 
		    maxlength="18" class="searchtxt" onblur="value=(this.value=='')?'请输入姓名或手机号':this.value;"
		    onfocus="value=(this.value=='请输入姓名或手机号')?'':this.value;"/>
			<button id="refreshSearchKey" class="ui-icon ui-icon-refresh searchbtnico"></button>
		</div>
		<a id="fastSearchButton" href="javascript:;"><span>检索</span></a>
		<pop:JugePermissionTag ename="addPromaryOrgMemberFourLevelPlatform">
			<a id="addMember" href="javascript:;"><span>新增</span></a>
		</pop:JugePermissionTag>
		<input id="isHaveJob0" value="0" type="hidden"/>
	</div>
<div id="addMemberDialog"></div>

	<div id="tabs">
		<ul>
			<li id="liHava"><a id="hava"  href='${path}/baseinfo/primaryOrgMemberManage/dispatch.action?mode=platformMembersList&primaryOrgMemberVo.primaryOrgId=${param.primaryOrgId}&primaryOrgMemberVo.isHaveJob=0&primaryOrgMemberVo.isFourLevelPlatform=1'>现有成员</a></li>
			<li><a id="nothava" href='${path}/baseinfo/primaryOrgMemberManage/dispatch.action?mode=platformMembersList&primaryOrgMemberVo.primaryOrgId=${param.primaryOrgId}&primaryOrgMemberVo.isHaveJob=1&primaryOrgMemberVo.isFourLevelPlatform=1'>卸任成员</a></li>
		</ul>
	</div>
</div>
<script type="text/javascript">
$("#refreshSearchKey").click(function(){$("#searchText").attr("value","");});
	$(function() {
		$("#tabs").tabs({cache:false});
		//$( "#tabs").tabs();
		
		$("#addMember").click(function (event){
			$("#addMemberDialog").createDialog({
				width:550,
				height:350,
				title:"新增组织成员", 
				url:"${path}/baseinfo/primaryOrgMemberManage/dispatch.action?mode=add&primaryOrgMemberVo.primaryOrgId=${param.primaryOrgId}&primaryOrgMemberVo.org.id=${param.orgId}&primaryOrgMemberVo.isHaveJob=0&primaryOrgMemberVo.isFourLevelPlatform=1",
				buttons: {
						"保存并继续" : function(event){
				   			$("#serviceTeamMemberForm").submit();
				   			$("#isSubmit").val("false");
						},
						
						"保存并关闭" : function(event){
							$("#serviceTeamMemberForm").submit();
				   			$("#isSubmit").val("true");
						},
						"关闭" : function(event){
				   			 $("#addMemberDialog").dialog('close');
						}
					}
			});
		});
		
	});
	
	$("#hava").click(function (event){
		$("#isHaveJob0").val("0");
	});
	$("#nothava").click(function (event){
		$("#isHaveJob0").val("1");
	});
	
	$("#fastSearchButton").click(function(){
			if($("#isHaveJob0").val()==1||$("#isHaveJob0").val()=="1"){
				$("#nothava").click();
			}else{
				$("#hava").click();
			}
			var conditions = $("#searchText").val();
			if(conditions == '请输入姓名或手机号') {
				conditions = '';
			}
	
			
			if($("#isHaveJob0").val()==1||$("#isHaveJob0").val()=="1"){
				$("#primaryOrgMember1List").setPostData({
					"primaryOrgMemberVo.primaryOrgId":$("#primaryOrgId0").val(),
					"primaryOrgMemberVo.isHaveJob":$("#isHaveJob0").val(),
					"primaryOrgMemberVo.fastSearchKeyWords":conditions,
					"primaryOrgMemberVo.isFourLevelPlatform":1
			   	});
				$("#primaryOrgMember1List").trigger("reloadGrid");
			}else{
				$("#primaryOrgMember0List").setPostData({
					"primaryOrgMemberVo.primaryOrgId":$("#primaryOrgId0").val(),
					"primaryOrgMemberVo.isHaveJob":$("#isHaveJob0").val(),
					"primaryOrgMemberVo.fastSearchKeyWords":conditions,
					"primaryOrgMemberVo.isFourLevelPlatform":1
			   	});
				$("#primaryOrgMember0List").trigger("reloadGrid");
			}
			
		}
	)
</script>