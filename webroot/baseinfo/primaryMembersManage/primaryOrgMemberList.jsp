<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style type="text/css">
	  .edit-cell{background: none !important;}
</style>
	<div style="width: 100%;margin-top:6px">
		<input type="hidden" value="${primaryMemberVo.isHaveJob}" id="isHaveJob0"/>
		<table id="primaryMember${primaryMemberVo.isHaveJob}List"></table>
		<div id="primaryMember${primaryMemberVo.isHaveJob}ListPager"></div>
	</div>

<script type="text/javascript">
<pop:formatterProperty name="gender" domainName="@com.tianque.domain.property.PropertyTypes@GENDER" />

//验证排列序号是否是数字
function seqIsNumber(value, colname) {
		var reg=/^[0-9]+$/;
       if (value!=""&& value!=null&& typeof(value)!="undefined"&& !reg.test(value))
          return [false,"值错误：请输入纯数字"];
       else 
          return [true,""];
       }
       
$(document).ready(function(){
	$("#primaryMember${primaryMemberVo.isHaveJob}List").jqGridFunction({
		datatype: "local",
		cellEdit: true,
		cellsubmit: "clientArray",
		colNames:['id','relevantId','orgId',
		          <c:if test="${primaryMemberVo.isOperator!='view'}">
		  			'操作',
		  			</c:if>
		  		'姓名','性别','身份证号','年龄','手机','电话','所在组织','所属区域(网格)','排列序号'],
		colModel:[
			{name:"id",index:"id",key:true,sortable:true,hidden:true},
			{name:"relevantId",index:"relevantId",hidedlg:true,sortable:true,hidden:true},
			{name:"organization.id",index:"organization.id",sortable:false,hidden:true},
			<c:if test="${primaryMemberVo.isOperator!='view'}"> 
			{name:"operation",index:"id",sortable:false,formatter:operateMemberFormatter,width:120,align:"center"},
			</c:if>
			{name:'name',index:"name",width:100,frozen:true,sortable:true,formatter:viewMemberFormatter},
			{name:'gender',index:'gender',formatter:genderFormatter,width:80,sortable:true,align:"center"},
			{name:'idcardNo',index:"idcardNo",width:150,frozen:true,sortable:true},
			{name:'years',index:"years",width:50,frozen:true,sortable:true,align:"center"},
			{name:'mobile',index:"mobile",width:100,frozen:true,sortable:true},
			{name:'telephone',index:'telephone',sortable:true,width:110,align:"center"},
			{name:'primaryOrgName',index:'primaryOrgName',width:80,formatter:primaryOrgNameFormatter,sortable:false,align:"center"},
			{name:"org.orgName",index:"orgName",sortable:true,width:270},
			{name:'seq',index:'seq',sortable:true,width:150,align:"center",editable:true,editrules:{custom:true, custom_func:seqIsNumber}}
		],
		height:390,
		showColModelButton:false,
		<pop:JugePermissionTag ename="viewPrimaryMembers">
		ondblClickRow:viewPrimaryMember,
		</pop:JugePermissionTag>
		beforeSubmitCell:function(rowid, cellname, value, iRow, iCol){
			$(".edit-cell.ui-state-highlight").attr("class","");
			var rowData=  $("#primaryMember${primaryMemberVo.isHaveJob}List").getRowData(rowid);
			$.ajax({
				url:"${path}/primaryOrg/primaryMembersManage/setMemberSeq.action",
				data:{
					"seq":value,
					"id":rowData.relevantId
				},
				success:function(data){
					$("#primaryMember${primaryMemberVo.isHaveJob}List").trigger("reloadGrid");
				}
			}); 
		},
		afterEditCell:function(rowid, cellname, value, iRow, iCol){
			$(".edit-cell.ui-state-highlight").attr("class","");
		}
	});
	//查看成员详细信息
	function viewMemberFormatter(el,options,rowData){
		return "<a href='javascript:;' <pop:JugePermissionTag ename='viewPrimaryMembers'> onclick='viewPrimaryMember("+rowData.id+")' </pop:JugePermissionTag>   >"+rowData.name+"</a>";
	}
	//显示列表数据
	$("#primaryMember${primaryMemberVo.isHaveJob}List").setGridParam({
		url:"${path}/primaryOrg/primaryMembersManage/findPrimaryMembersByOrg.action",
		sortname:"pm.id",
		sortorder:"asc",
		datatype: "json",
		page:1
	});
	//快速搜索
	$("#primaryMember${primaryMemberVo.isHaveJob}List").setPostData({
		"primaryMemberVo.org.id":'${primaryMemberVo.org.id}',
		"primaryMemberVo.isHaveJob":'${primaryMemberVo.isHaveJob}',
		"primaryMemberVo.primaryOrgId":'${primaryMemberVo.primaryOrgId}',
		"primaryMemberVo.primaryOrgName":'${primaryMemberVo.primaryOrgName}',
		"primaryMemberVo.isFourLevelPlatform":'${primaryMemberVo.isFourLevelPlatform}',
		"primaryMemberVo.fastSearchKeyWords":$("#searchText").val()!="请输入姓名"?$("#searchText").val():''
   	});
	$("#primaryMember${primaryMemberVo.isHaveJob}List").trigger("reloadGrid");
	$("#primaryMember${primaryMemberVo.isHaveJob}List").jqGrid('setFrozenColumns');
});

$("#fastSearchButton").click(function(){
	var conditions = $("#searchText").val();
	if(conditions == '请输入姓名') {
		conditions = '';
	}
	//显示列表数据
	$("#primaryMember${primaryMemberVo.isHaveJob}List").setGridParam({
		url:"${path}/primaryOrg/primaryMembersManage/findPrimaryMembersByOrg.action",
		sortname:"pm.id",
		sortorder:"asc",
		datatype: "json",
		page:1
	});
	$("#primaryMember${primaryMemberVo.isHaveJob}List").setPostData({
		"primaryMemberVo.org.id":'${primaryMemberVo.org.id}',
		"primaryMemberVo.isHaveJob":'${primaryMemberVo.isHaveJob}',
		"primaryMemberVo.primaryOrgId":'${primaryMemberVo.primaryOrgId}',
		"primaryMemberVo.primaryOrgName":'${primaryMemberVo.primaryOrgName}',
		"primaryMemberVo.isFourLevelPlatform":'${primaryMemberVo.isFourLevelPlatform}',
		"primaryMemberVo.fastSearchKeyWords":$("#searchText").val()!="请输入姓名"?$("#searchText").val():''
   	});
	$("#primaryMember${primaryMemberVo.isHaveJob}List").trigger("reloadGrid");
	$("#primaryMember${primaryMemberVo.isHaveJob}List").jqGrid('setFrozenColumns');
	
}
)

//操作
function operateMemberFormatter(el,options,rowData){
	var havajobStr = "", _updateStr = "",isHaveJob="";
	if(${primaryMemberVo.isHaveJob}=="0"){
		havajobStr="卸任";
		isHaveJob=1;
		_updateStr = "<pop:JugePermissionTag ename='updatePrimaryMembers'><a id='update${primaryMemberVo.isHaveJob}'  href='javascript:void(0)' onclick='updateMemberOperator("+rowData.id+")'><span>修改</span></a> </pop:JugePermissionTag>| ";
	}else{
		havajobStr="任职";
		isHaveJob=0;
	}
	return _updateStr+" <pop:JugePermissionTag ename='deletePrimaryMembers'><a id='delete${primaryMemberVo.isHaveJob}'  href='javascript:void(0)'  onclick='deleteMemberOperator("+rowData.id+")'><span>删除</span></a></pop:JugePermissionTag>| <pop:JugePermissionTag ename='holdPrimaryMembers'> <a href='javascript:;' onclick='havajobMemberOperator(event,"+rowData.id+","+isHaveJob+",\""+havajobStr+"\")'><span>"+havajobStr+"</span></a> </pop:JugePermissionTag>";
}

function havajobMemberOperator(event,selectedIds,isHaveJob,havajobStr){
	$.confirm({
		title:"确认"+havajobStr,
		message:"确定要"+havajobStr+"吗?",
		okFunc: function(){
			$.ajax({
				url:'${path}/primaryOrg/primaryMembersManage/havajobPrimaryMember.action?id='+selectedIds+'&isHaveJob='+isHaveJob,
				success:function(data){
					if(data.id){
					    $.messageBox({message:"成功"+havajobStr+"该成员!"});
						$("#primaryMember${primaryMemberVo.isHaveJob}List").trigger("reloadGrid");
					}else{
						$.messageBox({message:data,level: "error"});
					}
				}
			});
		}
	});
}

function primaryOrgNameFormatter(el,options,rowData){
	var selectedId='${primaryMemberVo.primaryOrgId }';
	var primaryOrg;
	if('${primaryMemberVo.isFourLevelPlatform}'==1){
		primaryOrg=$("#fourlevelplatformList").getRowData(selectedId);
	    return primaryOrg.name;
	}else{
	  primaryOrg=$("#primaryOrgList").getRowData(selectedId);
		return primaryOrg.detailName;
	}
}

//查看成员基本信息
function viewPrimaryMember(selectId){
	$("#primaryMembersDialog${primaryMemberVo.isHaveJob}").createDialog({
		title:"查看成员基本信息",
		width:680,
		height:550,
		url:'${path}/primaryOrg/primaryMembersManage/dispatch.action?mode=view&id='+selectId+"&internalId="+internalId+"&primaryOrgDetailName=${primaryOrgDetailName}&primaryMembers.isHaveJob="+$("#isHaveJob0").val(),
				buttons: {
			"关闭" : function(){
	        $(this).dialog("close");
	  		 }
		}
	});
}

//修改成员信息
function updateMemberOperator(selectedId){
	var detailName ="${primaryOrgDetailName}";
	$("#primaryMembersDialog${primaryMemberVo.isHaveJob}").createDialog({
		title:'修改成员信息',
		width:680,
		height:550,
		url:'${path}/primaryOrg/primaryMembersManage/dispatch.action?mode=edit&id='+selectedId+'&internalId='+internalId+'&primaryOrgDetailName='+encodeURIComponent(detailName)+'&primaryMembers.isHaveJob='+$("#isHaveJob0").val()+'&isFourLevelPlatform='+${primaryMemberVo.isFourLevelPlatform},
		buttons: {
			"保存" : function(event){
	   			$("#primaryMembersForm").submit();
   			},
   			"关闭" : function(){
		        	$(this).dialog("close");
				}
		}
	});
}

//删除成员
function deleteMemberOperator(selectedId){
	$.confirm({
		title:"确认删除",
		message:"确定要删除吗?一经删除将无法恢复!",
		okFunc: function(){
			$.ajax({
				url:'${path}/primaryOrg/primaryMembersManage/deletePrimaryMembersOrgType.action?primaryMembersOrgType.primaryMembersId='+selectedId+'&primaryMembersOrgType.primaryOrgId='+${primaryMemberVo.primaryOrgId}+'&primaryMembersOrgType.isFourLevelPlatform='+${primaryMemberVo.isFourLevelPlatform},
				success:function(data){
					if(data!=true && data!="true"){
						$(this).dialog("close");
					}else{
					$.messageBox({level:'warn',message:"成功删除成员"});	
					$("#primaryMember${primaryMemberVo.isHaveJob}List").trigger("reloadGrid");
					}
				}
			});
		}
	});
}
</script>