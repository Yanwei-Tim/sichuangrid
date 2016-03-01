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


<s:if     
	test="internalId==@com.tianque.domain.property.BasicOrgType@PERMARY_ORGANIZATION">
	<pop:formatterProperty name="dutyId" domainName="@com.tianque.domain.property.PropertyTypes@COMPOSITEDUTY" />
</s:if>
<s:elseif
	test="internalId==@com.tianque.domain.property.BasicOrgType@PERMARY_PARTY">
	<pop:formatterProperty name="dutyId" domainName="@com.tianque.domain.property.PropertyTypes@PARTYDUTY" />
</s:elseif>
<s:elseif
	test="internalId==@com.tianque.domain.property.BasicOrgType@AUTONOMY_ORG">
	<pop:formatterProperty name="dutyId" domainName="@com.tianque.domain.property.PropertyTypes@UTONOMYDUTY" />
</s:elseif>
<s:elseif
	test="internalId==@com.tianque.domain.property.BasicOrgType@MASS_TREAT_TEAM">
	<pop:formatterProperty name="dutyId" domainName="@com.tianque.domain.property.PropertyTypes@MASSESDUTY" />
</s:elseif>
<s:elseif
	test="internalId==@com.tianque.domain.property.BasicOrgType@VOLUNTARY_TEAM">
	<pop:formatterProperty name="dutyId" domainName="@com.tianque.domain.property.PropertyTypes@POSTULANTDUTY" />
</s:elseif>
<s:elseif
	test="internalId==@com.tianque.domain.property.BasicOrgType@LEADER_GROUP">
	<pop:formatterProperty name="dutyId" domainName="@com.tianque.domain.property.PropertyTypes@LEADERGROUPDUTY" />
</s:elseif>
<s:elseif
	test="internalId==@com.tianque.domain.property.BasicOrgType@OTHER">
	<pop:formatterProperty name="dutyId" domainName="@com.tianque.domain.property.PropertyTypes@LEADERGROUPDUTY" />
</s:elseif>
<s:elseif
	test="internalId==@com.tianque.domain.property.BasicOrgType@BASICLEVEL_PARTY">
	<pop:formatterProperty name="dutyId" domainName="@com.tianque.domain.property.PropertyTypes@BASICLEVELPARTYDUTY" />
</s:elseif>
<s:elseif
	test="internalId==@com.tianque.domain.property.BasicOrgType@DEPARTMENT_PARTY">
	<pop:formatterProperty name="dutyId" domainName="@com.tianque.domain.property.PropertyTypes@DEPARTMENTPARTYDUTY" />
</s:elseif>
<s:elseif
	test="internalId==@com.tianque.domain.property.BasicOrgType@GOVERNMENT_DEPARTMENT">
	<pop:formatterProperty name="dutyId" domainName="@com.tianque.domain.property.PropertyTypes@GOVERNMENTDEPARTMENTDUTY" />
</s:elseif>
<s:elseif
	test="internalId==@com.tianque.domain.property.BasicOrgType@MASS_ORGANIZATION">
	<pop:formatterProperty name="dutyId" domainName="@com.tianque.domain.property.PropertyTypes@MASSORGMANAGEMENTDUTY" />
</s:elseif>

//验证排列序号是否是数字
function memberSeqIsNumber(value, colname) {
		var reg=/^[0-9]+$/;
       if (value!=""&& value!=null&& typeof(value)!="undefined"&& !reg.test(value))
          return [false,"值错误：请输入纯数字"];
       else 
          return [true,""];
       }

$(document).ready(function(){
	$("#primaryOrgMember${primaryOrgMemberVo.isHaveJob}List").jqGridFunction({
		datatype: "local",
		cellEdit: true,
		cellurl:"${path}/baseinfo/primaryOrgMemberManage/setSeq.action",
		//cellsubmit: "remote", 
		cellsubmit: "clientArray",
	   	colModel:[
	        {name:"id",index:"id",sortable:false,hidden:true},
	        {name:"org.id",index:"org.id",sortable:false,hidden:true},
	        {name:"operation",index:"id",label:"操作",sortable:false,formatter:operateMemberFormatter,width:120,align:"center"},
	        {name:'name',index:"name",label:'姓名',sortable:false,width:80,align:"center"},
	        {name:'gender',index:'gender',label:'性别',sortable:true,formatter:genderFormatter,width:40,align:"center"},
	        {name:'dutyId',index:'dutyId',label:'职务',sortable:true,formatter:dutyIdFormatter,width:80,align:"center"},
	        //{name:'position',index:'position',label:'职位',sortable:true,width:100,align:"center"},
	        //{name:'year',index:'year',label:'出生年份',sortable:true,width:60,align:"center"},
	        {name:'idcardNo',index:'idcardNo',label:'身份证号码',sortable:true,width:140,align:"center"},
	        {name:'mobile',index:'mobile',label:'手机',sortable:false,width:90,align:"center"},
	        {name:'telephone',index:'telephone',label:'电话',sortable:false,width:100,align:"center"},
	        {name:'seq',index:'seq',label:'排列序号',width:200,align:"center",editable:true, editrules:{custom:true, custom_func:memberSeqIsNumber}},
	        {name:'remark',index:'remark',label:'备注',sortable:false,width:195}
		],
		height:390,
		showColModelButton:false,
		beforeSubmitCell:function(rowid, cellname, value, iRow, iCol){
			$(".edit-cell.ui-state-highlight").attr("class","");
				/* var reg=/^[0-9]+$/; 
				 if(!reg.test(value) || !value){   
					 $.messageBox({
			    		message:"请输入正整数",
			    		level: "error"
			    	 });
				    $("#primaryOrgMember${primaryOrgMemberVo.isHaveJob}List").trigger("reloadGrid");
				    return false;
		         } */
			
			//给jqgrid 从新设置cellurl 值  
           // $("#primaryOrgList").setGridParam({cellurl:$editUrl});  
           // return false ;  
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
	
	//设置grid单元格可编辑
	//$("#primaryOrgMember${primaryOrgMemberVo.isHaveJob}List").setGridParam({cellEdit:true});
	
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
		"primaryOrgMemberVo.fastSearchKeyWords":$("#searchText").val()!="请输入姓名或手机号"?$("#searchText").val():''
   	});
	$("#primaryOrgMember${primaryOrgMemberVo.isHaveJob}List").trigger("reloadGrid");
	$("#primaryOrgMember${primaryOrgMemberVo.isHaveJob}List").jqGrid('setFrozenColumns');
});

function operateMemberFormatter(el,options,rowData){
	var havajobStr = "", _updateStr = "";
	if(${primaryOrgMemberVo.isHaveJob}=="0"){
		havajobStr="卸任";
		_updateStr = "<pop:JugePermissionTag ename='editPrimaryOrgMember${param.name==null?\'\':param.name}'> <a href='javascript:;' onclick='updateMemberOperator(event,"+rowData.id+","+rowData.org.id+");'><span>修改</span></a> | ";
	}else{
		havajobStr="任职";
	}
	return _updateStr+"</pop:JugePermissionTag> <pop:JugePermissionTag ename='deletePrimaryOrgMember${param.name==null?\'\':param.name}'> <a href='javascript:;' onclick='deleteMemberOperator(event,"+rowData.id+")'><span>删除</span></a> | </pop:JugePermissionTag> <pop:JugePermissionTag ename='holdPrimaryOrgMember${param.name==null?\'\':param.name}'> <a href='javascript:;' onclick='havajobMemberOperator(event,"+rowData.id+",${primaryOrgMemberVo.isHaveJob},\""+havajobStr+"\")'><span>"+havajobStr+"</span></a> </pop:JugePermissionTag>";
}

var name='${param.name==null?'':param.name}';

function updateMemberOperator(event,selectedIds,orgid){
		$("#editMember${primaryOrgMemberVo.isHaveJob}Dialog").createDialog({
			width:550,
			height:350,
			title:"修改组织成员", 
			url:"${path}/baseinfo/primaryOrgMemberManage/dispatch.action?mode=edit&primaryOrgMember.id="+selectedIds+"&primaryOrgMemberVo.org.id="+orgid+"&internalId="+internalId+"&primaryOrgMemberVo.isHaveJob=${primaryOrgMemberVo.isHaveJob}&name="+name,
			buttons: {
					"保存并关闭" : function(event){
			   			$("#serviceTeamMemberForm").submit();
						$("#isSubmit").val("true");
						//$(this).dialog("close");
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