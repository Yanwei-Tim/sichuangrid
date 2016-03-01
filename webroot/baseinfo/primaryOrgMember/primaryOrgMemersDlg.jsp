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
		<pop:JugePermissionTag ename="addPromaryOrgMember${param.name==null?'':param.name}">
			<a id="addMember" href="javascript:;"><span>新增</span></a>
		</pop:JugePermissionTag>
		<input id="isHaveJob0" value="0" type="hidden"/>
	</div>
<div id="addMemberDialog"></div>

	<div id=tabs>
		<ul>
			<li id="liHava"><a id="hava"  href='${path}/baseinfo/primaryOrgMemberManage/dispatch.action?mode=primaryOrgMemberList&dailogName=primaryOrgDialog&internalId=${param.internalId}&primaryOrgMemberVo.primaryOrgId=${primaryOrgMemberVo.primaryOrgId}&primaryOrgMemberVo.isHaveJob=0&name=${param.name}'>现有成员</a></li>
			<li><a id="nothava" href='${path}/baseinfo/primaryOrgMemberManage/dispatch.action?mode=primaryOrgMemberList&dailogName=primaryOrgDialog&internalId=${param.internalId}&primaryOrgMemberVo.primaryOrgId=${primaryOrgMemberVo.primaryOrgId}&primaryOrgMemberVo.isHaveJob=1&name=${param.name}'>卸任成员</a></li>
		</ul>
	</div>
</div>
 <%-- 
<script>
	$(function() {
		$( "#tabs").tabs({cache:false,beforeLoad:function(ui){
			$("#viewPrimaryOrgMemer").remove();
		}});
	});
</script>--%>
<script type="text/javascript">
var name='${param.name==null?'':param.name}';
$("#refreshSearchKey").click(function(){$("#searchText").attr("value","");});
var  internalId=${param.internalId};
	$(function() {
		$( "#tabs").tabs({cache:false});
		//$( "#tabs").tabs();
		
		$("#addMember").click(function (event){
			$("#addMemberDialog").createDialog({
				width:550,
				height:350,
				title:"新增组织成员", 
				url:"${path}/baseinfo/primaryOrgMemberManage/dispatch.action?mode=add&internalId="+internalId+"&primaryOrgMemberVo.primaryOrgId=${primaryOrgMemberVo.primaryOrgId}&primaryOrgMemberVo.org.id=${primaryOrgMemberVo.org.id}&primaryOrgMemberVo.isHaveJob=0&name="+name,
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
	
			//var appendUrl="&primaryOrgMemberVo.fastSearchKeyWords="+conditions;
			
			//$("#hava").attr("href", $("#hava").attr("href")+appendUrl);
			//$("#nothava").attr("href", $("#nothava").attr("href")+appendUrl);
			
			
			//$("#fastSearchKeyWords0").val(conditions);
			//$("#fastSearchKeyWords1").val(conditions);
			//alert("11"+$("#fastSearchKeyWords0").val())
			//$("#primaryOrgMember0List").trigger("reloadGrid");
			
			
			if($("#isHaveJob0").val()==1||$("#isHaveJob0").val()=="1"){
				$("#primaryOrgMember1List").setPostData({
					"primaryOrgMemberVo.primaryOrgId":$("#primaryOrgId0").val(),
					"primaryOrgMemberVo.isHaveJob":$("#isHaveJob0").val(),
					"primaryOrgMemberVo.fastSearchKeyWords":conditions
			   	});
				$("#primaryOrgMember1List").trigger("reloadGrid");
			}else{
				$("#primaryOrgMember0List").setPostData({
					"primaryOrgMemberVo.primaryOrgId":$("#primaryOrgId0").val(),
					"primaryOrgMemberVo.isHaveJob":$("#isHaveJob0").val(),
					"primaryOrgMemberVo.fastSearchKeyWords":conditions
			   	});
				$("#primaryOrgMember0List").trigger("reloadGrid");
			}
			
		}
	)
</script>