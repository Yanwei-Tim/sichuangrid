<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<style type="text/css">
#load {
	width: 800px;
	height: 600px;
	text-align: center;
}
</style>
<div  title="请选择部门">
	<input name="selectOrgName"  id="claimOrgSelector"  type="text" class="form-txt">
	</div>
<form id="maintainClaimForm" method="post" action="${path}/plugin/dataManage/${tempClassName}Manage/claimTempByIds.action">
	<@pop.token />
	<input id="targetOrgId" name="targetOrgId" type="hidden" value="" />
	<input id="orgId" value="" type="hidden" />
	<input name="tempIds" id="tempIds" value="${(tempIds)!}" type="hidden"/>
	<input type="hidden" id="claimMethod"  value=${(from)!} />
</form >
<script type="text/javascript" >
<@pop.formatterProperty name="temp.gender" domainName="@com.tianque.domain.property.PropertyTypes@GENDER" />
<@pop.formatterProperty name="temp.certificateType" domainName="@com.tianque.domain.property.PropertyTypes@CERTIFICATE_TYPE" />

	if ("FROM_STEP"==$("#claimMethod").val()){
		$("#maintainClaimForm").attr("action","${path}/plugin/dataManage/${tempClassName}Manage/stepClaimTempByIds.action");
	}
var orgSelector;
function closeDialog(){
	var selectOrgId=$.getSelectedNode(orgSelector).attributes.id;
	if (selectOrgId!=null){
		$("#targetOrgId").val(selectOrgId);
	}
}
$(document).ready(function(){
	orgSelector=$("#claimOrgSelector").treeSelect({
			inputName:"targetOrgId",
			loadCom:function(){
					$.setTreeValue(USER_ORG_ID,orgSelector); 
				
			}
		});
	$.addClick(orgSelector,closeDialog);
	
 if ("FROM_STEP"==$("#claimMethod").val()){
		orgSelector.on('expand',function(node){
			if(node.attributes.orgLevelInternalId==<@s.property value="@com.tianque.domain.property.OrganizationLevel@TOWN"/>){
				 if (node.hasChildNodes()) {                 //是否有子节点
			   		node.eachChild(function(child) { 
				 	  child.leaf=true;
				  	});
				}
	 		}
	 
	   });
 }
	
	
	$("#maintainClaimForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form) {
			if($("#targetOrgId").val()==null||$("#targetOrgId").val()==undefined||$("#targetOrgId").val()==""){
				$.messageBox({
					message:"请选择组织机构",
					level: "error"
				 });
				return;
			}else{
				if( "FROM_CLAIM_TO_ORG"==$("#claimMethod").val()){
						$.ajax({
						async:false,
						url:"${path}/sysadmin/orgManage/isGridOrganization.action",
						data:{
							"organization.id":$("#targetOrgId").val()
						},
						success:function(responseData){
							if((!responseData) && ("${tempClassName}"!="newSocietyOrganizationsTemp")){
								$.messageBox({
									message:"数据请认领到网格",
									level: "error"
								 });
								 return;
							}else{
								formAjaxSubmit(form);
							}
						}
					});
				}else{
					$.ajax({
						async:false,
						url:"${path}/sysadmin/orgManage/ajaxOrganization.action",
						data:{
							"organization.id":$("#targetOrgId").val()
						},
						success:function(responseData){
							if(responseData){
								if(responseData.orgLevel.internalId >= <@s.property value="@com.tianque.domain.property.OrganizationLevel@DISTRICT"/>){
									$.messageBox({
										message:"请选择区以下层级",
										level: "error"
									 });
								 		return;
									}else{
										formAjaxSubmitForStepClaim(form);
									}
							}
						}
					});
					
					
				}
			}
		}
	});
});

function formAjaxSubmitForStepClaim(form){
    $(form).ajaxSubmit({
        success: function(data){
        		$.messageBox({message:"分布认领成功！"});
               	$("#${tempClassName}List").trigger("reloadGrid");
				$("#${tempClassName}Dialog").dialog("close");
 	   },
     error: function(data){
 		 $.messageBox({
				message:data,
				level: "error"
			 });
     	   }
      });
}

function locationTypeFormatter(el, options, rowData){
	return $("#moduleCName").val();
}

<#macro columns bigType>
	 <#if bigType=="population">
    	{name:"temp.name", index:"name",label:"姓名", sortable:false,width:100 },
    	<#if tempClassName=="overseaPersonnelTemp">
			{name:"temp.englishName",index:"englishName",width:150,sortable:false,label:"英文名"},
			{name:"temp.certificateType", index:"certificateType", width:150, sortable:false, label:"证件类型",formatter:certificateTypeFormatter},
			{name:"temp.certificateNo", index:"certificateNo", width:150, sortable:false, label:"证件号码"},
		<#else>
			{name:"temp.idCardNo", index:"idCardNo", width:150, sortable:false, label:"身份证号码"},
		</#if>
		{name:"temp.gender",index:"gender",width:100,sortable:false,label:"性别",formatter:genderFormatter,align:"center"},
    <#elseif bigType=="location">
    	{name:"temp.name", index:"name",label:"名称", sortable:false,width:100 },
    	{name:"temp.locationType",index:"locationType",label:"类别",sortable:false,width:100,formatter:locationTypeFormatter},
    <#elseif bigType=="dustbin">
		//{name:"temp.partType", index:"partType",label:"部件类型",formatter:partTypeFormatter,width:100},
		//{name:"temp.partName", index:"partName",label:"部件名称",formatter:partNameFormatter,width:100},
	 <#elseif bigType=="builddatas">
	 
    <#else>
    	{name:"temp.houseCode", index:"houseCode",label:"房屋编号", sortable:false,width:100 },
    </#if>
    <#if bigType=="population">
		{name:"temp.otherAddress", index:"address", width:150, sortable:false, label:"地址"<#if tempClassName=="overseaPersonnelTemp">,hidden:true</#if>}
	<#elseif bigType=="dustbin">
    	{name:"temp.dustbinCode", index:"dustbinCode",label:"部件标识码",width:100,frozen:true}
    <#elseif bigType=="builddatas">
    	{name:"temp.buildingname", index:"buildingname",label:"楼宇名称",width:100,frozen:true}
    <#else>
		{name:"temp.address", index:"address", width:150, sortable:false, label:"地址"}
    </#if>
</#macro>

function formAjaxSubmit(form){
    $(form).ajaxSubmit({
        success: function(data){
                if(data){
                	if(!data.successList && !data.errorList){
	                	$.messageBox({
							message:data,
							level: "error"
						 });
	                 	return;
                	}
             	 	claimOrgIdForGlobal = data.orgId;
             	 	//在最开始的list页面存储要认领到的目标网格的id
             	 	$("#targetClaimOrgId").val(data.orgId);
               	 	$("#${tempClassName}List").trigger("reloadGrid");
					$("#${tempClassName}Dialog").dialog("close");
					$("#resultListDialog").show();
					$("#resultListDialog").dialog({
						width: 828,
						height: 600,
						title:'认领结果',
						close:function(){
							$("#successMsg").clearGridData();
							$("#claimErrorMsg").clearGridData();
							claimOrgIdForGlobal = 0;
  						},
						buttons: {
							"关闭" : function(){
								$("#resultListDialog").dialog("close");
							}
						}
					});
 					$("#claimTabs").tabFunction();
					$("#successMsg").jqGridFunction({
	                    datatype: "local",
	                    autowidth:false,
	                	width:786,
	                    colModel:[
	                        {name:"temp.id", index:"id", hidden:true},
	                       <@columns bigType=bigType/>
	                    ]
	                });
	                $("#claimErrorMsg").jqGridFunction({
	                	datatype: "local",
	                	autowidth:false,
	                	width:786,
	                    colModel:[
	                        {name:"temp.id", index:"id", hidden:true},
	                        {name:"claimState.errorInfo", index:"claimState.errorInfo",label:"错误类型", sortable:false, width:80},
	                       <@columns bigType=bigType/>,
						    {name:"claimState.errorType", sortable:false,index:"claimState.errorType",label:"操作",width:120, formatter:errorTypeFormatter,align:"center"}
	                    ]
   	             });
		   reloadClaimResultList(data);
           }
 	   },
     error: function(data){
 		 $.messageBox({
				message:data,
				level: "error"
			 });
     	   }
      });
}
function reloadClaimResultList(data){
		     if(data.successList){
			   	    $.each(data.successList,function(i, n){
						$("#successMsg").addRowData(n.temp.id,n);
					});
			    	$.each(data.errorList,function(i, n){
			   	        $("#claimErrorMsg").addRowData(n.temp.id,n);
		   	        });
				 }
						

	       	     if($("#claimErrorMsg").getGridParam("records") > 0){
	      
	          	    $("#claimTabs").tabFunction({selected:1});
	          	 }else{
	          	    $("#claimTabs").tabFunction({selected:0}); 
	             }
}


</script>