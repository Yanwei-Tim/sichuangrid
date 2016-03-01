<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<style type="text/css">
	  .edit-cell{background: 0 !important;}
</style>
<div class="container container_24" id="viewPrimaryOrgMemer${primaryOrgMemberVo.isHaveJob}">
	<input id="primaryOrgId${primaryOrgMemberVo.isHaveJob}" name="primaryOrgId${primaryOrgMemberVo.isHaveJob}" type="hidden" value="${primaryOrgMemberVo.primaryOrgId}"/>
	<input id="isHaveJob${primaryOrgMemberVo.isHaveJob}" name="isHaveJob${primaryOrgMemberVo.isHaveJob}" type="hidden" value="${primaryOrgMemberVo.isHaveJob}"/>
	<div class='clearLine'></div>
	<div style="width: 100%;margin-top:6px">
		<table id="primaryOrgMember${primaryOrgMemberVo.isHaveJob}List"> </table>
		<div id="primaryOrgMember${primaryOrgMemberVo.isHaveJob}ListPager"></div>
	</div>
</div>

<div id="editMember${primaryOrgMemberVo.isHaveJob}Dialog"></div>
<script type="text/javascript">
<pop:formatterProperty name="gender" domainName="@com.tianque.domain.property.PropertyTypes@GENDER" />
<pop:formatterProperty name="dutyId" domainName="@com.tianque.domain.property.PropertyTypes@PLATFORMDUTY" />

$(document).ready(function(){
	$("#primaryOrgMember${primaryOrgMemberVo.isHaveJob}List").jqGridFunction({
		datatype: "local",
		cellEdit: true,
		cellurl:"${path}/baseinfo/primaryOrgManage/setSeq.action",
		cellsubmit: "clientArray",
	   	colModel:[
	        {name:"id",index:"id",sortable:false,hidden:true},
	        {name:"org.id",index:"org.id",sortable:false,hidden:true},
	        <s:if test="primaryMemberVo.isOperator!='view'"> 
	        {name:"operation",index:"id",label:"操作",sortable:false,formatter:operateMemberFormatter,width:120,align:"center"},
	        </s:if>
	        {name:'name',index:"name",label:'姓名',sortable:true,width:80,align:"center"},
	        {name:'gender',index:'gender',label:'性别',sortable:true,formatter:genderFormatter,width:40,align:"center"},
 	        {name:'dutyId',index:'dutyId',label:'职务',sortable:true,formatter:dutyIdFormatter,width:80,align:"center"},
	        //{name:'position',index:'position',label:'职位',sortable:true,width:100,align:"center"},
	        //{name:'year',index:'year',label:'出生年份',sortable:true,width:60,align:"center"},
	        {name:'idcardNo',index:'idcardNo',label:'身份证号码',sortable:true,width:140,align:"center"},
	        {name:'mobile',index:'mobile',label:'手机',sortable:true,width:90,align:"center"},
	        {name:'telephone',index:'telephone',label:'电话',sortable:true,width:100,align:"center"},
	        {name:'seq',index:'seq',label:'排列序号',width:200,align:"center",editable:true,editrules:{number:true}},
	        {name:'remark',index:'remark',label:'备注',sortable:false,width:195}
		],
		height:390,
		showColModelButton:false,
		beforeSubmitCell:function(rowid, cellname, value, iRow, iCol){
			$(".edit-cell.ui-state-highlight").attr("class","");
			$.ajax({
				url:"${path}/baseinfo/primaryOrgMemberManage/setSeq.action",
				data:{
					"seq":value,
					"id":rowid
				},
				success:function(data){
					$("#primaryOrgMember${primaryOrgMemberVo.isHaveJob}List").trigger("reloadGrid");
				}
			}); 
		},
		afterEditCell:function(rowid, cellname, value, iRow, iCol){
			$(".edit-cell.ui-state-highlight").attr("class","");
		}
	});
	
	
	$("#primaryOrgMember${primaryOrgMemberVo.isHaveJob}List").setGridParam({
		url:"${path}/baseinfo/primaryOrgMemberManage/findPrimaryOrgMembers.action",
		sortname:"dutyId",
		sortorder:"asc",
		datatype: "json",
		page:1
	});
	$("#primaryOrgMember${primaryOrgMemberVo.isHaveJob}List").setPostData({
		"primaryOrgMemberVo.primaryOrgId":$("#primaryOrgId${primaryOrgMemberVo.isHaveJob}").val(),
		"primaryOrgMemberVo.isHaveJob":$("#isHaveJob${primaryOrgMemberVo.isHaveJob}").val(),
		"primaryOrgMemberVo.isFourLevelPlatform":1,
		"primaryOrgMemberVo.fastSearchKeyWords":$("#searchText").val()!="请输入姓名或手机号"?$("#searchText").val():''
   	});
	$("#primaryOrgMember${primaryOrgMemberVo.isHaveJob}List").trigger("reloadGrid");
	$("#primaryOrgMember${primaryOrgMemberVo.isHaveJob}List").jqGrid('setFrozenColumns');
});

function operateMemberFormatter(el,options,rowData){
	var havajobStr = "", _updateStr = "";
	if(${primaryOrgMemberVo.isHaveJob}=="0"){
		havajobStr="卸任";
		_updateStr = "<pop:JugePermissionTag ename='editPrimaryOrgMemberFourLevelPlatform'> <a href='javascript:;' onclick='updateMemberOperator(event,"+rowData.id+","+rowData.org.id+");'><span>修改</span></a> | ";
	}else{
		havajobStr="任职";
	}
	return _updateStr+"</pop:JugePermissionTag> <pop:JugePermissionTag ename='deletePrimaryOrgMemberFourLevelPlatform'> <a href='javascript:;' onclick='deleteMemberOperator(event,"+rowData.id+")'><span>删除</span></a> | </pop:JugePermissionTag> <pop:JugePermissionTag ename='holdPrimaryOrgMemberFourLevelPlatform'> <a href='javascript:;' onclick='havajobMemberOperator(event,"+rowData.id+",${primaryOrgMemberVo.isHaveJob},\""+havajobStr+"\")'><span>"+havajobStr+"</span></a> </pop:JugePermissionTag>";
}


function updateMemberOperator(event,selectedIds,orgid){
		$("#editMember${primaryOrgMemberVo.isHaveJob}Dialog").createDialog({
			width:550,
			height:350,
			title:"修改组织成员", 
			url:"${path}/baseinfo/primaryOrgMemberManage/dispatch.action?mode=edit&primaryOrgMember.id="+selectedIds+"&primaryOrgMemberVo.org.id="+orgid+"&primaryOrgMemberVo.isHaveJob=${primaryOrgMemberVo.isHaveJob}",
			buttons: {
					"保存并关闭" : function(event){
			   			$("#serviceTeamMemberForm").submit();
						$("#isSubmit").val("true");
					},
					"关闭" : function(event){
						$(this).dialog("close");
					}
				}
		});
}


function deleteMemberOperator(event,selectedIds){
	$.confirm({
		title:"确认删除",
		message:"确定要删除吗?",
		okFunc: function(){
			$.ajax({
				url:'${path}/baseinfo/primaryOrgMemberManage/deletePrimaryOrgMember.action?mode=delete&selectedIds='+selectedIds,
				success:function(data){
					if(data>0){
					    $.messageBox({message:"成功删除该成员!"});
						$("#primaryOrgMember${primaryOrgMemberVo.isHaveJob}List").trigger("reloadGrid");
					}else{
						$.messageBox({message:data,level: "error"});
					}
				}
			});
		}
	});

}


function havajobMemberOperator(event,selectedIds,isHaveJob,havajobStr){
	$.confirm({
		title:"确认"+havajobStr,
		message:"确定要"+havajobStr+"吗?",
		okFunc: function(){
			$.ajax({
				url:'${path}/baseinfo/primaryOrgMemberManage/havajobPrimaryOrgMember.action?selectedIds='+selectedIds+'&primaryOrgMember.isHaveJob='+isHaveJob,
				success:function(data){
					if(data>0){
					    $.messageBox({message:"成功"+havajobStr+"该成员!"});
						$("#primaryOrgMember${primaryOrgMemberVo.isHaveJob}List").trigger("reloadGrid");
					}else{
						$.messageBox({message:data,level: "error"});
					}
				}
			});
		}
	});
}
</script>