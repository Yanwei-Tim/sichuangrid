<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@ include file="/includes/baseInclude.jsp"%>
<div class="content">
	<div class="ui-corner-all" id="nav">
		<div class="btnbanner btnbannerData">
			<div class="ui-widget autosearch">
				<input class="basic-input" type="text" value="请输入姓名" id="poorPeopleMembersSearchText" maxlength="18" class="searchtxt" style="width: 180px;"
					onblur="value=(this.value=='')?'请输入姓名':this.value;" onfocus="value=(this.value=='请输入姓名')?'':this.value;" />
				<button onclick="$('#poorPeopleMembersSearchText').val('请输入姓名');" class="ui-icon ui-icon-refresh searchbtnico"></button>
			</div>
		</div>
		<a href="javascript:;" id="fastSearchPoorPeopleMembersButton"><span>搜索</span></a>
		    <span class="lineBetween "></span>
			<s:if test='"edit".equals(mode)'>
			    <a id="addPoorPeopleMembers" href="javascript:;"><span>新增</span></a>
	        </s:if>
			<a id="reload" href="javascript:void(0);" onclick="loadPoorPeopleMembersData();$('#poorPeopleMembersSearchText').val('请输入姓名');"><span>刷新</span></a>
	</div>
	<div style="width: 100%;">
		<table id="poorPeopleMembersList"></table>
		<div id="poorPeopleMembersListPager"></div>
	</div>
	<div id="poorPeopleMembersDialog" style="overflow: hidden"></div>
</div>
<script type="text/javascript">
<pop:formatterProperty name="relationShipWithHead" domainName="@com.tianque.domain.property.PropertyTypes@RELATION_SHIP_WITH_HEAD" />
<pop:formatterProperty name="gender" domainName="@com.tianque.domain.property.PropertyTypes@GENDER" />
<pop:formatterProperty name="schooling" domainName="@com.tianque.domain.property.PropertyTypes@SCHOOLING" />
<pop:formatterProperty name="healthState" domainName="@com.tianque.domain.property.PropertyTypes@HEALTH_STATE" />
<pop:formatterProperty name="insuranceType" domainName="@com.tianque.domain.property.PropertyTypes@INSURANCETYPE" />
function loadPoorPeopleMembersData(pData) {
	var _poorPeopleList = $("#poorPeopleMembersList");
	_poorPeopleList.setGridParam({
		url:"${path}/account/poorPeopleMemberManage/getPoorPeopleMemberForList.action",
		datatype: "json",
		page:1
	});
	pData = pData == null ? {} : pData;
	pData['poorPeopleMembers.poorPeople.id'] = '${poorPeopleMembers.poorPeople.id}';
	_poorPeopleList.setPostData(pData);
	_poorPeopleList.trigger("reloadGrid");
}
var dialogMemberWidth = 760, dialogMemberHeight = 280;
function operateFormatter(el, options, rowData){
	var str="<a href='javascript:viewPoorPeopleMembers(\""+rowData.id+"\")'><U><font color='#6633FF'>查看</font></U></a>";
    <s:if test='"edit".equals(mode)'>
        str+="&nbsp;&nbsp;<a href='javascript:updatePoorPeopleMembers(\""+rowData.id+"\")'><U><font color='#6633FF'>修改</font></U></a>";
        str+="&nbsp;&nbsp;<a href='javascript:delPoorPeopleMembers(\""+rowData.id+"\")'><U><font color='#6633FF'>删除</font></U></a>";
    </s:if>
    return str;
}
$(function (){
	$("#poorPeopleMembersList").jqGridFunction({
		datatype: "local",
	   	colModel:[
	        {name:"id",index:"id",hidden:true,sortable:false},
			{name:"操作",index:'id',width:120,formatter:operateFormatter,align:"center",frozen:true,sortable:false},
	        {name:"name",sortable:true,label:'姓名',align:'center',width:100},
      		{name:"relationShipWithHead",label:'与户主关系',align:'center',width:100, formatter: relationShipWithHeadFormatter},
	        {name:'gender',label:'性别',sortable:true,align:'center',width:60, formatter: genderFormatter},
	        {name:'birthday',label:'出生年月',width:100,align:'center', formatter: function (arg0){return arg0 == null ? '' :arg0.toString().replace('T00:00:00', '')}},
	        {name:'schooling',label:'学历',width:100,align:'center', formatter: schoolingFormatter},
	        {name:'healthState',label:'健康状况',width:100,align:'center', formatter: healthStateFormatter},
	        {name:'insuranceType',label:'纳入低保（五保）情况',align:'center',width:140, formatter: insuranceTypeFormatter}
		],
		//multiselect: true,
		ondblClickRow: function (arg0){viewPoorPeopleMembers(arg0);}
	});
	loadPoorPeopleMembersData();
	$("#addPoorPeopleMembers").click(function (){
		$("#poorPeopleMembersDialog").createDialog({
			width: dialogMemberWidth,
			height: dialogMemberHeight,
			title:'新增困难群众台账-家庭成员',
			url:'${path}/account/poorPeopleMemberManage/toAddPoorPeopleMembers.action?poorPeopleMembers.poorPeople.id=${poorPeopleMembers.poorPeople.id}',
			buttons: {
		   		"保存" : function(event){
		   			$("#maintainPoorPeopleMembersForm").submit();
		   		},
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
	});
	$("#fastSearchPoorPeopleMembersButton").click(function (){
		if('请输入姓名' == $("#poorPeopleMembersSearchText").val()){
			$("#poorPeopleMembersSearchText").focus();
			return;
		}
		loadPoorPeopleMembersData({"poorPeopleMembers.name": $("#poorPeopleMembersSearchText").val()});
	});
	
	
});

function updatePoorPeopleMembers(id){
	$("#poorPeopleMembersDialog").createDialog({
		width: dialogMemberWidth,
		height: dialogMemberHeight,
		title:'修改困难群众台账-家庭成员',
		url:'${path}/account/poorPeopleMemberManage/getPoorPeopleMembersByIdForEdit.action?mode=edit&poorPeopleMembers.id=' + id,
		buttons: {
	   		"保存" : function(event){
	   			$("#maintainPoorPeopleMembersForm").submit();
	   		},
	   		"关闭" : function(){
	        	$(this).dialog("close");
	   		}
		}
	});
}

function delPoorPeopleMembers(id){
	$.confirm({
		title:"确认删除",
		message:"确定要删除吗?一经删除将无法恢复",
		okFunc: function() {
			$.ajax({
				url:'${path}/account/poorPeopleMemberManage/deletePoorPeopleMembers.action?ids='+id,
				success:function(data){
				    $.messageBox({message:"已经成功删除该困难群众台账-家庭成员信息!"});
					$("#poorPeopleMembersList").trigger("reloadGrid");
				}
			});
		}
	});
}

function viewPoorPeopleMembers(id){
	$("#poorPeopleMembersDialog").createDialog({
		width: dialogMemberWidth,
		height: dialogMemberHeight,
		title:'查看困难群众台账-家庭成员',
		url:'${path}/account/poorPeopleMemberManage/getPoorPeopleMembersByIdForView.action?poorPeopleMembers.id=' + id,
		buttons: {
	   		"关闭" : function(){
	        	$(this).dialog("close");
	   		}
		}
	});
}
</script>