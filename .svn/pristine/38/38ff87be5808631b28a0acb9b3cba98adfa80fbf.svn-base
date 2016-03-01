<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ include file="/baseinfo/serviceTeamManage/typeFormatter.jsp"%>
<div class="container container_24">
	<form id="maintainSupervisorForm" method="post">
	<pop:token />
 		<input name="population.orgInternalCode" type="hidden" id="orgInternalCode" value="${population.orgInternalCode}"/>
 		<input name="population.populationId" type="hidden" id="populationId" value="${population.populationId}"/>
		<input id="populationType" type="hidden" name="population.populationType" value="${population.populationType}" />
	
	<div class="grid_4 lable-right">
		<label class="form-lbl">组织大类：</label>
	</div>

	<div class="grid_7">
		<select id="teamClazz" name="population.teamClazz.id" class="form-select" >
	   		<pop:PropertyDictReleationSelectTag name="@com.tianque.domain.property.PropertyTypes@TEAMCLAZZ" defaultValue="${serviceManageTeam.teamClazz.id}" 
	   		reletionId="teamTypeId" reletionName="@com.tianque.domain.property.PropertyTypes@BASICORGTYPE" id="teamClazz" />
		</select>
    </div>

	<div class="grid_4 lable-right">
		<label class="form-lbl">组织类别：</label>
	</div>
	<div class="grid_7">
		<select id="teamTypeId" name="population.teamType.id" class="form-select" ></select>
    </div>
	<div class='clearLine'></div>
	
	<div class="grid_4 lable-right">
		<label class="form-lb1">组织名称：</label>
	</div>
	<div class="grid_19">
		<input type="text"	id="teamName" maxlength="100" name="population.teamName" style="width: 97%"	value="${serviceManageTeam.teamName}" title="请输入组织名称"
		class='form-txt {required:true,maxlength:100,messages:{required:"请输入组织名称" , maxlength:$.format("组织名称最多输入{0}个字符")}}' />
	</div>
	
	<div class="grid_4 lable-right">
		<label class="form-lb1">姓名：</label>
	</div>
	<div class="grid_19">
		<input type="text" id="populationName" maxlength="20" name="population.name" style="width: 97%" value="${population.name}" title="请输入组织姓名"
		class='form-txt {required:true,exculdeParticalChar:true,minlength:2,maxlength:20,messages:{required:"请输入姓名",exculdeParticalChar:"不能输入非法字符",minlength:$.format("姓名至少需要输入{0}个字符"),maxlength:$.format("姓名最多需要输入{0}个字符")}}' />
	</div>
	</form>

	<div class='clearLine'></div>
	<div style="margin-left: 425px">
		<input type="button"  id='superSearch'  value='查询' style="width: 50px;height: 28px;"/>
		<input type="button"  id='superReload'  value='刷新' style="width: 50px;height: 28px;margin-left: 8px;"/>
	</div>
	
	<div class='clearLine'></div>
	<div style="width: 100%;margin-top: 6px">
			<table id="supervisorList"> </table>
			<div id="supervisorListPager"></div>
	</div>
</div>

<script>
<pop:formatterProperty name="teamClazz" domainName="@com.tianque.domain.property.PropertyTypes@TEAMCLAZZ" />
<pop:formatterProperty name="gender"   domainName="@com.tianque.domain.property.PropertyTypes@GENDER" />
$(document).ready(function(){
	$("#superSearch").click(function(){
	   	var data = $("#maintainSupervisorForm").serializeObject();
		$("#supervisorList").setGridParam({
		    url:"${path}/supervisorManage/supervisorInfoManage/searchSupervisor.action",
			datatype: "json",
			page:1,
			mtype:"post"
		});
		$("#supervisorList").setPostData(data);
	    $("#supervisorList").trigger("reloadGrid");
	});
	$("#superReload").click(function(){
		 $("#teamClazz").val("");
		 $("#teamTypeId").html("");
		 $("#teamName").val("");
		 $("#populationName").val("");
		 $("#supervisorList").clearGridData();
	});
	
	$("#supervisorList").jqGridFunction({
		datatype: "local",
	   	colModel:[
	      {name:"memberId",index:"memberId",hidden:true},        
	      {name:"memberPosition",label:'职务',sortable:false,width:90},
	      {name:"name",index:'name',label:'姓名',sortable:false,width:80},
	      {name:"gender",label:'性别',align:'center',sortable:false,formatter:genderFormatter,width:50},
	      {name:'idCardNo',label:'身份证号码',sortable:false,width:170},
		  {name:'teamClazz',label:'管理服务团队类别',sortable:false,formatter:teamClazzFormatter,width:120},
		  {name:'teamName',label:'管理服务团队名称',sortable:false,width:200},
	      {name:'organization.orgName',label:'所属网格',sortable:false, width:150},
	 	  {name:'mobile',label:'联系手机',index:"mobileNumber",sortable:false,width:110},
		  {name:'teamId',label:'组织id',hidden:true,hidedlg:true},
		  {name:'populationType',label:'人员类型',hidden:true,hidedlg:true}
		],
		height:125,
		sortName:'memberId',
	    multiselect:true,
	    rowNum:5,
	    rowList:[5,10,15,20,25,30],
	    showColModelButton:false
	});
});
</script>