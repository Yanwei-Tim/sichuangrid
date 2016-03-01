<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="/PopGrid-taglib" prefix="pop" %>
<%@ include file="/includes/baseInclude.jsp"%>
<s:if test='#parameters.id[0] == null || #parameters.id[0] == ""'>未获取到实有单位ID。</s:if>
<s:else>
<div class="content">
	<div class="ui-corner-all" id="nav">
		<div class="btnbanner btnbannerData">
			<div class="ui-widget autosearch">
				<input class="basic-input" type="text" value="请输入姓名" name="searchPractitionersText" id="searchPractitionersText" maxlength="18" class="searchtxt"
					 style="width:180px;" onblur="value=(this.value=='')?'请输入姓名':this.value;" onfocus="value=(this.value=='请输入姓名')?'':this.value;" />
				<button id="refreshSearchPractitionersKey" class="ui-icon ui-icon-refresh searchbtnico"></button>
			</div>
			<a href="javascript:;" id="fastSearchPractitionersButton"><span>搜索</span></a>
			<a href="javascript:;" id="addPractitioners"><span><strong class="ui-ico-xz"></strong>新增</span></a>
			<a href="javascript:;" id="delPractitioners"><span><strong class="ui-ico-xz"></strong>删除</span></a>
			<a href="javascript:;" id="refreshPractitioners"><span><strong class="ui-ico-xz"></strong>刷新</span></a>
		</div>
	</div>
</div>
<div style="width: 100%;">
	<table id="practitionersList"></table>
	<div id="practitionersListPager"></div>
</div>
<div id="practitionersDialog"></div>
<script type="text/javascript">
<pop:formatterProperty name="gender" domainName="@com.tianque.domain.property.PropertyTypes@GENDER" />
$(function(){
	$("#practitionersList").jqGridFunction({
		mtype:'post',
		height: 390,
		datatype: "local",
		multiselect:true,
		colModel:[
	        {name:"id",index:"id",hidden:true},
	        {name:"name", label:'姓名',width:200,sortable:false,align:'center' },
		    {name:"gender",label:'性别',width:100,sortable:false,formatter: genderFormatter,align:'center'},
		    {name:"idCardNo",sortable:false,label:"身份证号码",align:'center',width:320}
		]
	});
	function searchPractitioners(){
		$("#practitionersList").setGridParam({
			url:"${path}/baseinfo/actualCompanyManage/findActualCompanysPractitioners.action",
			datatype: "json",
			page:1,
			mtype:"post"
		});
		$("#practitionersList").setPostData({"location.id": '<s:property value="#parameters.id[0]"/>', 'location.corporateRepresentative': $("#searchPractitionersText").val() == '请输入姓名' ? '' : $("#searchPractitionersText").val()});
	    $("#practitionersList").trigger("reloadGrid");
	}
	searchPractitioners();
	$("#refreshPractitioners").click(function (){$("#searchPractitionersText").val('请输入姓名');searchPractitioners();});
	$("#fastSearchPractitionersButton").click(function (){searchPractitioners();});
	$("#refreshSearchPractitionersKey").click(function (){$("#searchPractitionersText").val('请输入姓名');});
	$("#delPractitioners").click(function (){
		$.confirm({
			title:"确认删除",
			message:"确定要删除吗?",
			okFunc: function(){
				$.post('${path}/baseinfo/actualCompanyManage/delPersonForPractitionerst.action?locationIds='+$("#practitionersList").jqGrid("getGridParam", "selarrrow")+'&location.id=<s:property value="#parameters.id[0]"/>', function (data){$.messageBox({ message: "删除成功！"});searchPractitioners();});
			}
		});
	});
	$("#addPractitioners").click(function (){
		$("#practitionersDialog").createDialog({
			width: 600,
			height: 586,
			title:"新增从业人员",
			url:'${path}/baseinfo/actualCompanyManage/addPersonForPractitionerst.action?location.id=<s:property value="#parameters.id[0]"/>',
			buttons: {
		   		"保存" : function(event){
		   			$("#practitionersMaintainForm").submit();
		   		},
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
	});
});
</script></s:else>