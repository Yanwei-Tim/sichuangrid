<%@ page language="java" contentType="text/html; charset=UTF-8"pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div  class="container container_24">
	<input id="orgId" type="hidden" name="orgId"  />
	<input id="areaorgId" type="hidden" name="areaorgId" />
	<input id="roleOrgId" type="hidden" name="roleOrgId" />
	<div id="personSelectBox">
		<div class="personSelectMenus" id="personSelectMenus">
			<ul>
				<li><a href="javascript:;"><span class="icon-zn"></span>站内联系人</a></li>
				<li><a href="javascript:;"><span class="icon-my"></span>我的群组</a></li>
				<li><a href="javascript:;"><span class="icon-cj"></span>按层级发送</a></li>
			</ul>
		</div>
		<%-- 站内联系人 --%>
		<div class="personSelectPanel">
			<div class="grid_24 departmentBox">
				<div class="grid_8 lable-right">
					<label>部门：</label>
				</div>
	  			<div class="grid_16">
					<input type="text" id="workContactsOrgTree" class="form-txt" />
    			</div>
    		</div>
    		<div class='clearLine'>&nbsp;</div>
			<div id="workContactsList">
				<ul id="workContactsList_ul" class="orgLevelBox"></ul>
			</div>
		</div>
		<%-- 我的群组 --%>
		<div class="personSelectPanel" >
		<c:choose>
			<c:when test="${fn:length(myGroups)>0}">
				<div>
					<div id="myGroup" style="cursor: pointer;margin-top: 5px;">
						<span class="ui-icon ui-icon-triangle-1-s" style="float: left;"></span>我的群组(<s:property value="myGroups.size()" />)
					</div>
					<ul class="orgLevelBox" id="myGroupList">
						<c:forEach items="${myGroups }" var="myGroup">
							<li >
								<label><input type="checkbox" id="workContact_${myGroup.id}" value="${myGroup.id}" userName="${myGroup.userName }" name="${myGroup.name}">${myGroup.name}</label>
							</li>
						</c:forEach>
					</ul>
				</div>
			</c:when>
			<c:otherwise>
				<font color="red">暂无群组</font>
			</c:otherwise>
			</c:choose>
		</div>
		<%-- 按层级发送 --%>
		<div class="personSelectPanel" id="orgLevelContacters">
			<div class="grid_24">
				<div class="grid_8 lable-right">
					<label>部门：</label>
				</div>
			  	<div class="grid_16">
					<input type="text" id="areaOrgTree" class="form-txt" />
		    	</div>
		    </div>
		    <div class='clearLine'>&nbsp;</div>
		    <ul class="orgLevelMenu">
		    	<li class="cur"><a href="javascript:;">行政部门</a></li>
		    	<li><a href="javascript:;">职能部门</a></li>
		    </ul>
		    <div class="orgLevelCttBox">
		    	<ul class="orgLevelBox" id="orgAll">
			    	<li>
			    		<label>
			    			<input type="checkbox"   id="selectAllLevelAdministrativeOrg" />全部
			    		</label>
			    	</li>
					<li>
						<label>
							<input type="checkbox"  value="省" id='<s:property value="@com.tianque.domain.property.OrganizationLevel@PROVINCE"/>_<s:property value="@com.tianque.domain.property.OrganizationType@ADMINISTRATIVE_REGION"/>' class="org" isHide="0"/>省级
						</label>
					</li>
					<li>
						<label>
							<input type="checkbox"  value="市" id='<s:property value="@com.tianque.domain.property.OrganizationLevel@CITY"/>_<s:property value="@com.tianque.domain.property.OrganizationType@ADMINISTRATIVE_REGION"/>' class="org" isHide="0"/>市级
						</label>
					</li>
					<li>
						<label>
							<input type="checkbox"  value="区县" id='<s:property value="@com.tianque.domain.property.OrganizationLevel@DISTRICT"/>_<s:property value="@com.tianque.domain.property.OrganizationType@ADMINISTRATIVE_REGION"/>' class="org" isHide="0"/>区县级
						</label>
					</li>
			    	<li>
			    		<label>
			    			<input type="checkbox"  value="乡镇街道" id='<s:property value="@com.tianque.domain.property.OrganizationLevel@TOWN"/>_<s:property value="@com.tianque.domain.property.OrganizationType@ADMINISTRATIVE_REGION"/>' class="org" isHide="0"/>乡镇街道级
			    		</label>
			    	</li>
					<li>
						<label>
							<input type="checkbox"  value="村社区" id='<s:property value="@com.tianque.domain.property.OrganizationLevel@VILLAGE"/>_<s:property value="@com.tianque.domain.property.OrganizationType@ADMINISTRATIVE_REGION"/>' class="org" isHide="0"/>村社区级
						</label>
					</li>
					<li>
						<label>
							<input type="checkbox"  value="网格" id='<s:property value="@com.tianque.domain.property.OrganizationLevel@GRID"/>_<s:property value="@com.tianque.domain.property.OrganizationType@ADMINISTRATIVE_REGION"/>' class="org" isHide="0"/>网格层级
						</label>
					</li>
			    </ul>
		    	<ul class="orgLevelBox" style="display:none;" id="funcOrgAll">
			    	<li>
			    		<label>
			    			<input type="checkbox"   id="selectAllLevelFunctionalOrg" />全部
			    		</label>
			    	</li>
					<li>
						<label>
							<input type="checkbox"  value="省级职能部门" id='<s:property value="@com.tianque.domain.property.OrganizationLevel@PROVINCE"/>_<s:property value="@com.tianque.domain.property.OrganizationType@FUNCTIONAL_ORG"/>' class="org" isHide="0"/>省级职能部门
						</label>
					</li>
					<li>
						<label>
							<input type="checkbox"  value="市级职能部门" id='<s:property value="@com.tianque.domain.property.OrganizationLevel@CITY"/>_<s:property value="@com.tianque.domain.property.OrganizationType@FUNCTIONAL_ORG"/>' class="org" isHide="0"/>市级职能部门
						</label>
					</li>
					<li>
						<label>
							<input type="checkbox"   value="区县级职能部门" id='<s:property value="@com.tianque.domain.property.OrganizationLevel@DISTRICT"/>_<s:property value="@com.tianque.domain.property.OrganizationType@FUNCTIONAL_ORG"/>' class="org" isHide="0"/>区县级职能部门
						</label>
					</li>
			    	<li>
			    		<label>
							<input type="checkbox"   value="乡镇街道级职能部门" id='<s:property value="@com.tianque.domain.property.OrganizationLevel@TOWN"/>_<s:property value="@com.tianque.domain.property.OrganizationType@FUNCTIONAL_ORG"/>' class="org" isHide="0"/>乡镇街道级职能部门
						</label>
			    	</li>
			    </ul>
		    </div>
		</div>
		<div class="personSelectedBox">
			<div id="orgLevelContactersList" class="orgLevelContactersList" >
				<h3>已选择对象：</h3><ul></ul>
			</div>
		</div>
	</div>
</div>
<div id="viewMemberListDlg"></div>
<script type="text/javascript">
var provienceId;
$(document).ready(function(){
	provienceId = getprovienceOrgId();
});
/**
 * 通过当前用户的ID去查询该用户省级网格ID
 */
function getprovienceOrgId(){
		$.ajax({
			url:"${path}/sysadmin/orgManage/getOrgProvinceByOrgId.action",
			async:false,
			success:function(data){
				rootId = eval(data);
			}
		});
	return rootId;
}
 
/****************************************站内联系人***************************************************/
 //站内联系人组织树
var orgTree=$("#workContactsOrgTree").treeSelect({
	width:100,
	allOrg:false,
	rootId:provienceId,
	inputName:"orgId",
	showFunctionalOrg:true,
	isFuncDep:true,
	changeFun:function(){return getWorkContactsData($("#orgId").val());}
});
//当站内来联系人的部门改变是加载该部门下的联系人
function getWorkContactsData(orgId){
	$.ajax({
		async:false,
		url:"${path}/interactive/outboxPlatformMessageManage/findWorkContacts.action",
		data:{"orgId":orgId},
		success:function(data){
			showWorkContactsList(data);
		}
	});
};
//显示站内联系人列表
function showWorkContactsList(data){
	$("#workContactsList>ul").empty();
	var userReciverIds = $("#userReceiversCopy").val().split(",");
	if((data!=null && data.length > 0) || (userReciverIds!='' && userReciverIds.length > 0) ){
		if(data!=null && data.length > 0){
			$("#workContactsList>ul").append(
					'<li><label><input type="checkbox"  id="selecAllWorkContacts" />全选</label></li>'
				);
		}
		if(data!=null && data.length!=0){
		for(var i=0;i<data.length;i++){
			$("#workContactsList>ul").append(
				'<li>' +
					'<label><input type="checkbox" userName="'+data[i].userName+'" name="'+data[i].name+'" value="'+data[i].id+'" id="workContact_'+data[i].id+'" />'+ 
					data[i].name+'   '+data[i].userName+
					'</label>'+
				'</li>'
			);
			var exist=$.inArray(data[i].id+'',userReciverIds);
			if(exist>=0){
				//判断该联系人是否已经被选择/如果是就将其选中
				$('#workContact_'+data[i].id).attr("checked",true);
				//移除已经当前部门下的已经能选中的 ,剩下的就是非当前部门下的要被选中的
				userReciverIds.remove(data[i].id);
				//判断该联系人是否已加到 已选择对象中去 
				if($("#orgLevelContactersList ul li[id='"+data[i].id+"']").length==0){
					$("#orgLevelContactersList ul").prepend('<li id="'+data[i].id+'">'+data[i].name+'    '+data[i].userName+'<a href="javascript:;" class="delete">删除</a></li>');
				}
			}
		}
		}
		//把非当前部门下但已经选择的联系人也添加到已选择对象中去
		for(var j = 0 ; j < userReciverIds.length ; j++){
			var id = userReciverIds[j];
			if(id !=''&& id != '-1' && id !='-2' && id.lastIndexOf('-levelList')==-1 && id.lastIndexOf('-roleList')==-1 && $("#orgLevelContactersList ul li[id='"+id+"']").length==0){
				var name = $("#userReceiversCopy").data(id);
				if(name==undefined){
					//通过输入框输入的
					$("#holder_userReceiversCopy .bit-box").each(function(){
						if($(this).data('data')==id){
							name = $(this).text();
						}
					});
				}
				$("#orgLevelContactersList ul").prepend('<li id="'+id+'">'+name+'<a href="javascript:;" class="delete">删除</a></li>');
			}
		}
		isSelectAllWorkContacts();
	}
}
//点击站内联系人的checkbox
function clickWorkcontactOrGroupCheckbox(checkbox) {
	if (checkbox.attr("checked")) {
		isSelectAllWorkContacts();
		$("#orgLevelContactersList ul").prepend('<li id="'+checkbox.val()+'">'+checkbox.attr("name")+'    '+checkbox.attr("userName")+'<a href="javascript:;" class="delete">删除</a></li>');
		$("#userReceiversCopy").data(checkbox.val(),checkbox.attr("name"));
		$("#userReceiversCopy").setPersonnelCompleteVal({
			value : checkbox.val(),
			label : checkbox.attr("name"),
			desc : ""
		});
	}else{
		var cval = checkbox.val();
		$("#selecAllWorkContacts").attr("checked",false);
		$("#userReceiversCopy").removeData(cval);
		$("#userReceiversCopy").removePersonnelCompeleteValue(cval);
		$('#orgLevelContactersList').find("#"+cval).remove();
	}
}
//点击我的群组的checkbox
function clickWorkcontacterCheckbox(checkbox){
	if(checkbox.attr("checked")){
		var id = checkbox.attr("value");
	    $("#viewMemberListDlg").createDialog({
			width: 500,
			height: 300,
			title:'选择群组成员',
			url:'${path}/contact/myGroupManage/findMyGroupHasContactersToSelectPersion.action?myGroup.id='+id+'&myGroup.belongClass=workContact&isCopySelect=true',
			buttons: {
				"确定" : function(event){
					var checkID = getCheckboxValue();
					if(checkID!=null && checkID.length!=0){
						$("#workContact_"+id).attr("checked",true);
					}
					$(this).dialog("close");
			   }
			}
			
		});
	}
}
//选中全部站内联系人
function selectAllWorkContacts(selectAllCheckbox){
	var isChecked=selectAllCheckbox.attr("checked");
	$("#workContactsList_ul li input[id!='"+selectAllCheckbox.attr("id")+"']").each(function(){
		if(isChecked){
			if($(this).attr("checked")==undefined){
				 $(this).attr("checked",true);
				 clickWorkcontactOrGroupCheckbox($(this));
			}
		}else{
			if($(this).attr("checked")){
				 $(this).attr("checked",false);
				 clickWorkcontactOrGroupCheckbox($(this));
			}
		}
	});
}
//判断站内联系人的全选按钮是否应该被选中
function isSelectAllWorkContacts(){
	var isChecked=true;
	$("#workContactsList_ul li:gt(0) input").each(function(){
		if($(this).attr("checked")==undefined){
			isChecked=false;
			return;
		}
	});
	$("#selecAllWorkContacts").attr("checked",isChecked);
}


/****************************************按层级发送***************************************************/
//按层级发送组织树
var areaTree=$("#areaOrgTree").treeSelect({
	width:100,
	nodeUrl:"/sysadmin/orgManage/ajaxOrgsForExtTree.action?directlySupervisor=true",
	allOrg:false,
	rootId:provienceId,
	inputName:"areaorgId",
	showFunctionalOrg:true,
	directlySupervisor:true,
	changeFun:function(){ return setOrgLevelCheckboxState($.getSelectedNode(areaTree).attributes.orgLevelInternalId);}
});
//当层级部门改变时设置按各层级checkbox状态
function setOrgLevelCheckboxState(level){
	userOrgName = '<s:property value="@com.tianque.core.util.ThreadVariable@getUser().getOrganization().getOrgName()"/>';
	userLeavel = '<s:property value="@com.tianque.core.util.ThreadVariable@getUser().getOrganization().getOrgLevel().getInternalId()"/>';
    var orgLevelCheckboxs = $(".org");
    //先全部取消层级的选中状态
    orgLevelCheckboxs.attr('checked',false);
    //取消层级全选按钮的选中状态
	$("#selectAllLevelAdministrativeOrg,#selectAllLevelFunctionalOrg").attr("checked",false);
	//已选择层级部门收件人的id
	var orgReceiversCopyArray = $('#orgReceiversCopy').val().split(',');
    for(var i = 0 ; i < orgLevelCheckboxs.length ; i++ ){
        var orgLevel = orgLevelCheckboxs[i].id.split('_')[0];
		if(level>userLeavel){
			if(orgLevel==level){
				$("#"+orgLevelCheckboxs[i].id).parent().show();
				$("#"+orgLevelCheckboxs[i].id).attr("isHide","0");
			}else{
				$("#"+orgLevelCheckboxs[i].id).parent().hide();
				$("#"+orgLevelCheckboxs[i].id).attr("isHide","1");
			}
		}else{
			if(orgLevel<=userLeavel && orgLevel<=level){
				$("#"+orgLevelCheckboxs[i].id).parent().show();
				$("#"+orgLevelCheckboxs[i].id).attr("isHide","0");
			}else{
				$("#"+orgLevelCheckboxs[i].id).parent().hide();
				$("#"+orgLevelCheckboxs[i].id).attr("isHide","1");
			}
		}
    	var id = $('#areaorgId').val()+'_'+orgLevelCheckboxs[i].id;
    	//如果已选择层级部门收件人id中包含该层级就选中这个checkbox
    	if(orgReceiversCopyArray.contains(id)){
    		orgLevelCheckboxs[i].checked=true;
        }
    }
    isSelectedAllOrgLevel(level);
    //对非当前层级选中的层级联系人遍历 并添加到已选择对象中去
    for(var j = 0 ; j < orgReceiversCopyArray.length; j++){

    	var id = orgReceiversCopyArray[j];
        if(id==null||id==''){
        	continue;
        }
        var explainId = id.split("_");
        var orgId = explainId[0];
        var checklevel = explainId[1];//截取得到选择的行政部门层级 50表示省级 40市级.....
        var orgType = explainId[2];
		var organizanition = getOrganizanition(orgId);//根据当前选择行政部门的层级查询组织机构信息
		var orgName = "";
        if(id!='' && $("#orgLevelContactersList>ul li[id='"+id+"']").length==0){
        	var nameAndLevel= $("#orgReceiversCopy").data(id);
        	if(checklevel >= organizanition.orgLevel.internalId){
        		if(orgType==0){
        			orgName = organizanition.orgName;
        		}else{
        			orgName = organizanition.orgName + "职能部门";
        		}
        	}else{
    			if(nameAndLevel=='' || nameAndLevel==undefined){
    				orgName = organizanition.orgName+"层级下行政部门";
    			}else{
    				orgName = organizanition.orgName+"层级下全部"+nameAndLevel[1];
    			}
    		}
        	$("#orgLevelContactersList > ul").prepend(
    				'<li key="orgLevel" id="'+id+'">' +
    						'<label>'+orgName+'</label>'+
    						'<a href="javascript:;"  class="delete">删除</a>'+
    				'</li>');
        }
    }
}

//点击层级的checkbox  level:组织机构树所选层级的等级
function clickOrgLevelCheckbox(checkbox,level){
 		userOrgName = $("#areaOrgTree").val();
		//所选部门id_orgLevel_orgType
	   	var id = $('#areaorgId').val()+'_'+checkbox.attr("id") ;
	    var explainId = id.split("_");
	    var orgId = explainId[0];
		var checklevel = explainId[1];//截取得到选择的行政部门层级 50表示省级 40市级.....
		var organizanition = getOrganizanition(orgId);
		var orgName = "";
		var isDepartment = checkbox.val();
		var addDep = 0;
		if(isDepartment.indexOf("职能")>=0){
			addDep=1;
		}
		if(checklevel >= level){
			if(addDep==0){
				orgName = organizanition.orgName;
			}else{
				orgName = organizanition.orgName+"职能部门";
			}
		}else{
			orgName = userOrgName+"层级下全部"+checkbox.val();
		}
		if(checkbox.attr("checked")){
			isSelectedAllOrgLevel(level);
			if($("#orgLevelContactersList>ul li[id='"+id+"']").length==0){
				$("#orgLevelContactersList > ul").prepend(
						'<li key="orgLevel" id="'+id+'">' +
								'<label>'+orgName+'</label>'+
								'<a href="javascript:;"  class="delete">删除</a>'+
						'</li>');
			}
		   var orgReceiversCopyArray = $('#orgReceiversCopy').val().split(',');
		   if(!orgReceiversCopyArray.contains(id)){
			   $("#orgReceiversCopy").val($("#orgReceiversCopy").val()+','+id);
		   }
		   $("#userReceiversCopy").setPersonnelCompleteVal({
				value : checkbox.val()+"-levelList",
				label : orgName,
				desc : ""
		   });
		   $("#orgReceiversCopy").data(id,[$("#areaOrgTree").val(),checkbox.val()]);
		}else{
			$("#orgReceiversCopy").val($("#orgReceiversCopy").val().replace(','+id , ''));
			var orgType = id.split("_")[2];
			if(orgType == <s:property value="@com.tianque.domain.property.OrganizationType@ADMINISTRATIVE_REGION"/> ){
				$("#selectAllLevelAdministrativeOrg").attr("checked",false);
			}else if(orgType == <s:property value="@com.tianque.domain.property.OrganizationType@FUNCTIONAL_ORG"/>){
				$("#selectAllLevelFunctionalOrg").attr("checked",false);
			}
			$("#"+id).remove();
			$("#userReceiversCopy").removePersonnelCompeleteValue(checkbox.val()+"-levelList");
			$("#orgReceiversCopy").removeData(id);
		}
}

function getOrganizanition(orgId){
	var organizanition = "";
	$.ajax({
		async:false,
		url:'${path}/sysadmin/orgManage/getFullOrgById.action?organization.id='+orgId,
		success:function(data){
			if(data){
				organizanition = eval(data);
			}
		}
	});
	return organizanition;
}

//判断层级全选按钮是否要选中
function isSelectedAllOrgLevel(level){
	 var administrativeOrgIsChecked=true;
	 var functionalOrgIsChecked=true;
	 $("#orgAll .org").each(function(){
		var isHide = $(this).attr("isHide");
		 var thisLevel = $(this).attr("id").split('_')[0];
		 var orgType = $(this).attr("id").split('_')[1];
		 if(isHide=="0"){
			 if($(this).attr("checked")==undefined && orgType == <s:property value="@com.tianque.domain.property.OrganizationType@ADMINISTRATIVE_REGION"/> ){
				 administrativeOrgIsChecked=false;
			 }
		 }
		
	});
	 $("#funcOrgAll .org").each(function(){
		var isHide = $(this).attr("isHide");
		 var thisLevel = $(this).attr("id").split('_')[0];
		 var orgType = $(this).attr("id").split('_')[1];
		 if(isHide=="0"){
			 if($(this).attr("checked")==undefined && orgType == <s:property value="@com.tianque.domain.property.OrganizationType@FUNCTIONAL_ORG"/>){
				 functionalOrgIsChecked=false;
			 }
		 }
		
	});
	$("#selectAllLevelAdministrativeOrg").attr("checked",administrativeOrgIsChecked);
	$("#selectAllLevelFunctionalOrg").attr("checked",functionalOrgIsChecked);
}

/****************************************按岗位发送***************************************************/

//判断全选是否选中
function isSelectAllRole(){
	 var isChecked=true;
	 $("#roleList li input[id!='selecAllRole']").each(function(){
		if(!$(this).attr('checked')){
			 isChecked=false;
			 return ;
		}
	});
	$("#selecAllRole").attr('checked',isChecked);
}


/**************************************************************************************************************/
$(function(){
	//加载选项卡
	$("#personSelectMenus li").click(function(){
		var thisIndex=$(this).index();
		$(".personSelectPanel").eq(thisIndex).show().siblings(".personSelectPanel").hide();
		$(this).addClass("cur").siblings().removeClass("cur");
	}).eq(0).click();

	//遍历我的群组当该群组已经被选择 就选择该群组
	var userReciverIds = $("#userReceiversCopy").val().split(",");
	$("#myGroupList li label input").each(function(){
		var exist=$.inArray($(this).val(),userReciverIds);
		if(exist>=0){
			$(this).attr("checked",true);
			if($("#orgLevelContactersList ul li[id='"+$(this).val()+"']").length==0){
				$("#orgLevelContactersList ul").prepend('<li id="'+$(this).val()+'">'+$(this).attr("name")+'<a href="javascript:;" class="delete">删除</a></li>');
			}
		}
	});
	//加载rootOrg下的联系人
	orgTree.getLoader().on("load" , function(){
		if($("#orgId").val() == ""){
			getWorkContactsData(orgTree.root.firstChild.id);
		}else{
			getWorkContactsData($("#orgId").val());
		}
	});
	//加载rootOrg下的已选层级
	areaTree.getLoader().on("load" , function(){
		if($.getSelectedNode(areaTree) == null){
			setOrgLevelCheckboxState(areaTree.root.firstChild.attributes.orgLevelInternalId);
		}else{
			setOrgLevelCheckboxState($.getSelectedNode(areaTree).attributes.orgLevelInternalId);			
		}
	});

	//全选站内联系人
	$("#workContactsList_ul").delegate("#selecAllWorkContacts","click",function(){
		selectAllWorkContacts($(this));
	});
	//选择单个站内联系人
	$("#workContactsList_ul").delegate("li input[id!='selecAllWorkContacts']","click",function(){
		clickWorkcontactOrGroupCheckbox($(this));
	});
	//从已选择对象中删除已选择的收件人
	$("#orgLevelContactersList").delegate("a","click",function(){
		var id =$(this).parent().attr("id");
		var checkboxId = $(this).parent().attr("parentId");
		var key = $(this).parent().attr("key");
		var checkbox = null ;
		var parentCheckbox = null ;
		if(key=='orgLevel'){
			var orgId = id.split("_")[0];
			var orgLevel = id.split("_")[1];
			var orgType = id.split("_")[2];
			if(orgId==$("#areaorgId").val()){
				checkbox = $("#"+orgLevel+"_"+orgType);
				checkbox.attr("checked",false);
				var level=$.getSelectedNode(areaTree).attributes.orgLevelInternalId;
				clickOrgLevelCheckbox(checkbox,level);
			}else{
				$("#orgReceiversCopy").val($("#orgReceiversCopy").val().replace(','+id , ''));
			}
		}else if(key=='role'){
			var roleId = id.split("_")[0];
			var orgId = id.split("_")[1];
			var roleScope = id.split("_")[2];
			if(orgId==$("#roleOrgId").val() && roleScope == $("#roleScope").val()){
				checkbox = $("#role_"+roleId);
				checkbox.attr("checked",false);
				clickRoleCheckbox(checkbox);
			}else{
				$("#roleReceiversCopy").val($("#roleReceiversCopy").val().replace(','+id , ''));
			}
		}else if(key=="myGroup"){
			checkbox = $("#workContact_"+id);//群组下联系人的ID
			parentCheckbox = $("#workContact_"+checkboxId);//checkboxId群组的ID
			if(checkbox.length==0){
				$("#userReceiversCopy").removePersonnelCompeleteValue(id);
			}else{
				checkbox.attr("checked",false);
				clickWorkcontactOrGroupCheckbox(checkbox);
			}
			chechBoxSelect(checkboxId,parentCheckbox);
		}else{
			checkbox = $("#workContact_"+id);
			//当该站内联系人不在当前所选择部门下显示时,直接从收件人栏里删除
			if(checkbox.length==0){
				$("#userReceiversCopy").removePersonnelCompeleteValue(id);
			}else{
				checkbox.attr("checked",false);
				clickWorkcontactOrGroupCheckbox(checkbox);
			}
		}
		$(this).parent().remove();
	});
	
	function chechBoxSelect(checkboxId,parentCheckbox){
		if($("#orgLevelContactersList li.group").length==0){
			parentCheckbox.attr("checked",false);
		}else{
			var isNoBox = 0;
			$("#orgLevelContactersList li.group").each(function(){
				if(checkboxId==$(this).attr("parentId")){
					isNoBox=(isNoBox-0)+1;
					return;
				}
			}); 
			
			if(isNoBox==1){
				parentCheckbox.attr("checked",false);
			}else{
				parentCheckbox.attr("checked",true);
			}
		}
	}
	
	//显示或隐藏群组
	$("#myGroup").click(function(event){
		var uiSpan=$(this).find("span:first");
		if(uiSpan.hasClass("ui-icon-triangle-1-s")){
			uiSpan.removeClass("ui-icon-triangle-1-s").addClass("ui-icon-triangle-1-e");
			$(this).parent().find("ul").hide();
		}else{
			uiSpan.removeClass("ui-icon-triangle-1-e").addClass("ui-icon-triangle-1-s");
			$(this).parent().find("ul").show();
		}
	});
	//选择群组
	$("#myGroupList li input").click(function(){
		clickWorkcontacterCheckbox($(this));
	});

	//按层级发送 行政和职能部门切换
	$(".orgLevelMenu li").click(function(){
		$(this).addClass("cur").siblings().removeClass("cur");
		$(".orgLevelCttBox").find(".orgLevelBox").eq($(this).index()).show().siblings().hide();
	})
	
	//按层级发送的全选
	$("#selectAllLevelAdministrativeOrg,#selectAllLevelFunctionalOrg").click(function(){
		//所选部门的层级
		var level=$.getSelectedNode(areaTree).attributes.orgLevelInternalId;
		var isChecked=$(this).attr("checked");
		//遍历部门层级
		 $(this).closest(".orgLevelBox").find(".org").each(function(){
			 var thisLevel = $(this).attr("id").split('_')[0];
			var isHide = $(this).attr("isHide");
				 if(isHide=="0"){
				   if(isChecked){
				     if($(this).attr("checked")!="checked"){
				     	 $(this).attr("checked",true);
				     	 clickOrgLevelCheckbox($(this),level);
				     }
				   }else{
					   if($(this).attr("checked")){
					     	 $(this).attr("checked",false);
					     	 clickOrgLevelCheckbox($(this),level);
					    }
					}
				   
				}
		    });
		});
		
	 //选择单个部门层级
	 $(".org").click(function(){
		 clickOrgLevelCheckbox($(this),$.getSelectedNode(areaTree).attributes.orgLevelInternalId);
	  });

	
	  
});

</script>
