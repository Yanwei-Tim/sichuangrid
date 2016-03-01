<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<jsp:include page="/baseinfo/commonPopulation/jsFunctionInclude.jsp"/>
<%
	String isCommissionOrganization = request.getParameter("isCommissionOrganization");
	request.setAttribute("isCommissionOrganization",isCommissionOrganization);
%>
<style type="text/css">
	 .edit-cell{background: none !important;} 
</style>
<div class="content">
	<div class="ui-corner-all" id="nav">
		<div class="btnbanner btnbannerData">
		<jsp:include page="/common/orgSelectedComponent.jsp"/>
			<div class="userState" id="fastSearchSelect">
				<select id="displayLevel" name="displayLevel" class="basic-input" >
					<option value="<s:property value="@com.tianque.domain.property.BasicOrgType@ORGSCOPE_SAMEGRADE"/>">仅显示本级</option>
					<option value="<s:property value="@com.tianque.domain.property.BasicOrgType@ORGSCOPE_DIRECTJURISDICTION"/>">直属下辖</option>
					<option value="<s:property value="@com.tianque.domain.property.BasicOrgType@ORGSCOPE_ALLURISDICTION"/>" selected="selected">全部</option>
				</select>
			</div>
		</div>
		<pop:JugePermissionTag ename="search${param.name==null?'PrimaryOrg':param.name }">
		<a href="javascript:;" id="search"><span>高级搜索</span></a>
		</pop:JugePermissionTag> 
		<span class="lineBetween "></span>
		<pop:JugePermissionTag ename="add${param.name==null?'PrimaryOrg':param.name}">
		<a id="add" href="javascript:void(0)"><span><strong class="ui-ico-xz"></strong>新增组织</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="delete${param.name==null?'PrimaryOrg':param.name}">
		<a id="delete" href="javascript:void(0)"><span><strong class="ui-ico-delete"></strong>批量删除</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="export${param.name==null?'PrimaryOrg':param.name}">
		<a id="export" href="javascript:void(0)"><span><strong class="ui-ico-dc"></strong>导出</span></a>
		</pop:JugePermissionTag>		
		<a id="reload" href="javascript:void(0)"><span>刷新</span></a>
		
		<pop:JugePermissionTag ename="maintainPrimaryOrgMembers${param.name==null?'PrimaryOrg':param.name}">
		<a id="maintainPrimaryOrgMembers" href="javascript:void(0)"><span><strong class="ui-ico-sc"></strong>维护成员</span></a>
		</pop:JugePermissionTag>
		<!-- 只有社会志愿者有 -->
		<c:if test="${param.name=='Postulantduty'}"> 
			<pop:JugePermissionTag ename="synchronizePrimaryOrgMembersToMasseduty">
				<a id="synchronizePrimaryOrgMembersToMasseduty" href="javascript:void(0)"><span><strong class="ui-ico-sc"></strong>同步群防群治</span></a>
			</pop:JugePermissionTag>
		</c:if> 
	</div>
	<div style="clear: both;"></div>
	<div style="width: 100%;">
		<table id="primaryOrgList"> </table>
		<div id="primaryOrgListPager"></div>
	</div>
	<div id="primaryOrgDialog"></div>
	<div id="primaryOrgMaintanceDialog"></div>
	<div id="helpPrecordDialog"></div>
	<div id="scanHeaderImage"></div>
</div>
<div style="display: none;">
	<pop:JugePermissionTag ename="primaryOrgManagement">
	<span id="title"><s:property value="#request.name" /></span>
	</pop:JugePermissionTag>
</div>


<script type="text/javascript">
 var teamClazzId;
 var teamTypeName;
 var titleName;
 var internalId;
 var teamTypeDomainName;
 var isCommissionOrganization = '${isCommissionOrganization}';
 <pop:formatterProperty name="teamClazz" domainName="@com.tianque.domain.property.PropertyTypes@TEAMCLAZZ" />
 
 function getkeyByValue(value){
	 var resultId;
	 for(var key in  teamClazzData ){
		 if(teamClazzData[key]==value){
			 resultId=key;
			break;
		 }
	 }
	 return resultId;
 }
 
 var name='${param.name==null?'':param.name}';
 if(name=='BasePartyOrg'){
 	//如果是1  基层党组织
 	teamTypeName="";
 	titleName="基层党组织";
 	internalId=1;
 	teamClazzId=getkeyByValue("基层党组织");
 }
 else if(name=='Autonomy'){
 	//如果是2 基层自治组织
 	teamClazzId=getkeyByValue("基层自治组织");
	titleName="基层自治组织";
 	teamTypeName="BASICORGTYPE";
	internalId=2;
	teamTypeDomainName = '<s:property value="@com.tianque.domain.property.PropertyTypes@BASICORGTYPE" escape="false"/>';
 	<pop:formatterProperty name="teamType" domainName="@com.tianque.domain.property.PropertyTypes@BASICORGTYPE" />
 }else if(name=='Masseduty'){
 	//3	群防群治队伍
 	teamClazzId=getkeyByValue("群防群治队伍");
 	teamTypeName="MASSEDUTY_TYPE";
 	titleName="群防群治队伍";
	internalId=3;
	teamTypeDomainName = '<s:property value="@com.tianque.domain.property.PropertyTypes@MASSEDUTY_TYPE" escape="false"/>';
 	<pop:formatterProperty name="teamType" domainName="@com.tianque.domain.property.PropertyTypes@MASSEDUTY_TYPE" />
 }else if(name=='Postulantduty'){
 	//4	社会志愿者队伍
 	teamClazzId=getkeyByValue("社会志愿者队伍");
 	teamTypeName="POSTULANTDUTY_TYPE";
	titleName="社会志愿者队伍";
	internalId=4;
	teamTypeDomainName = '<s:property value="@com.tianque.domain.property.PropertyTypes@POSTULANTDUTY_TYPE" escape="false"/>';
 	<pop:formatterProperty name="teamType" domainName="@com.tianque.domain.property.PropertyTypes@POSTULANTDUTY_TYPE" />
 }else if(name=='Leadergroup'){
 	//5	专项工作领导小组
	teamClazzId=getkeyByValue("专项工作领导小组");
	teamTypeName="LEADERGROUP_TYPE";
	titleName="专项工作领导小组";
	internalId=5;
	teamTypeDomainName = '<s:property value="@com.tianque.domain.property.PropertyTypes@LEADERGROUP_TYPE" escape="false"/>';
 	<pop:formatterProperty name="teamType" domainName="@com.tianque.domain.property.PropertyTypes@LEADERGROUP_TYPE" />
 }else if(name=='BasicLevelParty'){
 	//6	基层党委
	teamClazzId=getkeyByValue("基层党委");
	teamTypeName="BASICLEVELPARTY_TYPE";
	titleName="基层党委";
	internalId=8;
	teamTypeDomainName = '<s:property value="@com.tianque.domain.property.PropertyTypes@BASICLEVELPARTY_TYPE" escape="false"/>';
 	<pop:formatterProperty name="teamType" domainName="@com.tianque.domain.property.PropertyTypes@BASICLEVELPARTY_TYPE" />
 }else if(name=='DepartmentParty'){
 	//7	部门党委
	teamClazzId=getkeyByValue("部门党委");
	teamTypeName="DEPARTMENTPARTY_TYPE";
	titleName="党委部门";
	internalId=9;
	teamTypeDomainName = '<s:property value="@com.tianque.domain.property.PropertyTypes@DEPARTMENTPARTY_TYPE" escape="false"/>';
 	<pop:formatterProperty name="teamType" domainName="@com.tianque.domain.property.PropertyTypes@DEPARTMENTPARTY_TYPE" />
 }else if(name=='GovernmentDepartment'){
 	//8	政府部门
	teamClazzId=getkeyByValue("政府部门");
	teamTypeName="GOVERNMENTDEPARTMENT_TYPE";
	titleName="政府部门";
	internalId=10;
	teamTypeDomainName = '<s:property value="@com.tianque.domain.property.PropertyTypes@GOVERNMENTDEPARTMENT_TYPE" escape="false"/>';
 	<pop:formatterProperty name="teamType" domainName="@com.tianque.domain.property.PropertyTypes@GOVERNMENTDEPARTMENT_TYPE" />
 }else if(name=='MassOrganization'){
 	//9	群团组织
 	teamTypeName="MASSORGANIZATION_TYPE";
 	titleName="群团组织";
 	internalId=11;
 	teamClazzId=getkeyByValue("群团组织");
 	teamTypeDomainName = '<s:property value="@com.tianque.domain.property.PropertyTypes@MASSORGANIZATION_TYPE" escape="false"/>';
 }else{
 	//10	综治组织
 	teamClazzId=getkeyByValue("综治组织");
 	teamTypeName="PERMARY_ORGANIZATION_TYPE";
	titleName="综治组织";
	internalId=0;
	teamTypeDomainName = '<s:property value="@com.tianque.domain.property.PropertyTypes@MASSORGANIZATION_TYPE" escape="false"/>';
    <pop:formatterProperty name="teamType" domainName="@com.tianque.domain.property.PropertyTypes@PERMARY_ORGANIZATION_TYPE" />
 }
var dialogWidth=850;
var dialogHeight=590;
var isgridBol = false;
var currentOrgId=getCurrentOrgId();
var notRun = new Array();
var run = new Array();

function checkedOrgId(orgID){
	return true;
}

function viewDialog(id){
	var primaryOrg =  $("#primaryOrgList").getRowData(id);
	var encryptId = primaryOrg.encryptId;
	$("#primaryOrgDialog").createDialog({
		width:830,
		height:630,
		title:"查看组织信息",
		url:PATH+'/baseinfo/primaryOrgManage/dispatchByEncrypt.action?mode=view&dailogName=primaryOrgDialog&primaryOrg.id='+encryptId+"&teamTypeName="+teamTypeName+'&primaryMemberVo.primaryOrgId='+primaryOrg.id+'&primaryOrg.org.id='+primaryOrg["org.id"],
		buttons: {
		   "关闭" : function(){
		        $(this).dialog("close");
		   }
		}
	});
}

function updateOperator(event,selectedId){
	var primaryOrg = $("#primaryOrgList").getRowData(selectedId);
	var encryptId = primaryOrg.encryptId;
	if(internalId==3){
		var dialogHeight = 470;
	}else{
		var dialogHeight = 330;
	}
	$("#primaryOrgDialog").createDialog({
		title:'修改组织',
		width: 650,
		height: dialogHeight,
		url:PATH+'/baseinfo/primaryOrgManage/dispatchByEncrypt.action?mode=edit&dailogName=primaryOrgDialog&primaryOrg.id='+encryptId+"&teamTypeName="+teamTypeName+"&isCommissionOrganization="+isCommissionOrganization,
		buttons: {
			"保存并关闭" : function(event){
				$("#primaryOrgForm").submit();
   			},
	   		"关闭" : function(){
	        	$(this).dialog("close");
	   		}
		}
	}); 
}

function deleteOperator(event,selectedIds){
	var selectedIds=deleteOperatorByEncrypt("primaryOrg",selectedIds,"encryptId");
	$.confirm({
		title:"确认删除",
		message:"确定要删除吗?",
		okFunc: function(){
			$.ajax({
				url:PATH+'/baseinfo/primaryOrgManage/deletePrimaryOrg.action',
				type:"post",
				data:{
					"mode":"delete",
					"selectedIds":selectedIds
				},
				success:function(data){
					if(data>0){
					    $.messageBox({message:"成功删除该组织团队!"});
						$("#primaryOrgList").trigger("reloadGrid");
					}else{
						$.messageBox({
							message:"请清空组织成员后再删除该组织!",
							level:"warn"
						});
					}
				}
			});
		}
	});
}

function operateFormatter(el,options,rowData){
	if(name=='Masseduty' && rowData.isSynchronize > 0){
		return "";
	}else{
		return "<pop:JugePermissionTag ename='edit${param.name==null?\'PrimaryOrg\':param.name}'> <a href='javascript:;' onclick='updateOperator(event,"+rowData.id+");'><span>修改</span></a> |</pop:JugePermissionTag> <pop:JugePermissionTag ename='delete${param.name==null?\'PrimaryOrg\':param.name}'>  <a href='javascript:;' onclick='deleteOperator(event,"+rowData.id+")'><span>删除</span></a></pop:JugePermissionTag>";
	}
}

function nameFont(el,options,rowData){
	return "<pop:JugePermissionTag ename='view${param.name==null?\'PrimaryOrg\':param.name}'> <a href='javascript:;' onclick='viewDialog("+rowData.id+");'></pop:JugePermissionTag>"+rowData.detailName+"<pop:JugePermissionTag ename='view${param.name==null?\'PrimaryOrg\':param.name}'></a></pop:JugePermissionTag>";
}

function viewMemberFormatter(el, options, rowData){
	if(rowData.memberNum==0){
		return "0";
	}else{
		return "<pop:JugePermissionTag ename='view${param.name==null?\'PrimaryOrg\':param.name}'> <a href='javascript:;' onclick='viewDialog("+rowData.id+");'><U><font color='#6633FF'>"+rowData.memberNum+"</font></U></a> </pop:JugePermissionTag>";
	}
}
function teamTypeFormatter(el,options,rowData){
	 if(rowData['teamType.id']||(rowData.teamType&&rowData.teamType.id)){
	 if(rowData["teamType.id"]){
	 return teamTypeData[rowData["teamType.id"]];
	 }
	 return teamTypeData[rowData.teamType.id];
	 } else if(rowData['population.teamType.id']||(rowData.population && rowData.population.teamType&&rowData.population.teamType.id)){
	 if(rowData["population.teamType.id"]){
	 return teamTypeData[rowData["population.teamType.id"]];
	 }
	 return teamTypeData[rowData.population.teamType.id];
	 }else{
	 return "&nbsp;"
	 }
} 

function isSynchronizeMassedutyUnformat(el){
	if(el=="是"){
		return	1;
	}else{
		return 0;
	}
}


function isSynchronizeFormatter(el, options, rowData){//teamTypeFormatter
	if(rowData.isSynchronize){
		return	"同步";
	}else{
		return	"录入";
	}
}

function isSynchronizeUnformat(el){
	if(el=="同步"){
		return	1;
	}else{
		return 0;
	}
}
function massedutyTteamTypeFormatter(el, options, rowData){
	if(name=="Masseduty" && rowData.isSynchronize>0){
		return	"<span>平安志愿者</span>";
	}else{
		return teamTypeFormatter(el, options, rowData);
	}
}
function isSynchronizeMassedutyFormatter(el, options, rowData){//teamTypeFormatter
	if(rowData.isSynchronize){
		return	"是";
	}else{
		return	"否";
	}
}

//验证排列序号是否是数字
function seqIsNumber(value, colname) {
		var reg=/^[0-9]+$/;
       if (value!=""&& value!=null&& typeof(value)!="undefined"&& !reg.test(value))
          return [false,"值错误：请输入自然数"];
       else 
          return [true,""];
       }


function callback(){
    var dfop = {
    	name:'${param.name==null?'':param.name}',
    	organizationLevelCity:'<s:property value="@com.tianque.domain.property.OrganizationLevel@CITY"/>',
    	isSynchronizeMShow:'<s:if test="'Masseduty'.equals(#parameters.name[0])">true</s:if>',
    	teamTypeShow:"<s:if test='#parameters.name==null || (!("BasePartyOrg".equals(#parameters.name[0])) && !("MassOrganization".equals(#parameters.name[0])))'>true</s:if>",
    	isSynchronizePShow:"<s:if test="'Postulantduty'.equals(#parameters.name[0])">true</s:if>",
    	hasViewPermission:"<pop:JugePermissionTag ename="view${param.name==null?'PrimaryOrg':param.name}">true</pop:JugePermissionTag>",
    	hasAddPrimaryOrgMember:{
					"保存并关闭" : function(event){
			   			$("#primaryOrgForm").submit();
			   			$("#isSubmit").val("true");
					},
					"保存并继续" : function(event){
			   			$("#primaryOrgForm").submit();
			   			$("#isSubmit").val("false");
					}
					<pop:JugePermissionTag ename="addPrimaryOrgMember">
					,
					"添加成员" : function(event){
						$("#appendMember").val("true");
						$("#primaryOrgForm").submit();
					}
					</pop:JugePermissionTag>
					
			},
			colModel:[
	          	{name:"id",index:"id",sortable:false,hidden:true,frozen:true},
		        {name:"encryptId",index:'id',frozen:true,hidden:true},
		        {name:"operator",index:"id",label:"操作",sortable:false,formatter:operateFormatter,width:80,align:"center"},
		        <s:if test="'Masseduty'.equals(#parameters.name[0])"> 
				{name:'isSynchronize',index:'isSynchronize',label:'数据来源',sortable:true,formatter:isSynchronizeFormatter, unformat:isSynchronizeUnformat,width:120,align:"center"},
				</s:if>
				<s:if test='#parameters.name==null || (!("BasePartyOrg".equals(#parameters.name[0])) && !("MassOrganization".equals(#parameters.name[0])))'>
				{name:'teamType',index:'teamType',label:'组织类型',sortable:true,formatter:massedutyTteamTypeFormatter,width:120,align:"center"},
				<s:if test="'Postulantduty'.equals(#parameters.name[0])"> 
				{name:"isSynchronize",index:"isSynchronize",label:'是否已同步到群防群治',formatter:isSynchronizeMassedutyFormatter,unformat:isSynchronizeMassedutyUnformat,sortable:false,frozen:true,align:"center"},
				</s:if>
				</s:if>
		        {name:'detailName-Font',index:'detailName',label:'组织名称',sortable:true,width:260,align:"center",formatter:nameFont},
		        {name:'detailName',index:'detailName',sortable:false,hidden:true,hidedlg:true},
		        {name:'memberNum',index:'memberNum',label:'成员数',sortable:true,width:60,align:"center",formatter:viewMemberFormatter},
			    {name:'org.orgName',index:'org.orgName',label:'所属区域(网格)',sortable:false,align:"center",width:200},
			  	{name:'seq',index:'seq',label:'排列序号',width:200,sortable:true,align:"center",editable:true,editrules:{custom:true, custom_func:seqIsNumber}/* editrules:{integer:true} */},
		       	{name:'org.id',index:'org.id',label:'组织id',sortable:false,hidden:true,frozen:true,hidedlg:true}
			  ]
    }
    TQ.primaryOrgList(dfop)
}

$.cacheScript({
	url:'/resource/getScript/baseinfo/primaryOrganization/primaryOrgList.js',
    callback: callback
})
</script>