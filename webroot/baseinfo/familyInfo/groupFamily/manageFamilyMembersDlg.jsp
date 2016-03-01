<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
<div id="manageDialog-form" title="维护成员" class="container container_24">
    <form id="manageFamilyMembers-form" action="">
    <pop:token />
          <input type="hidden" id="groupFamilyId" name="groupFamilyId" value="${groupFamily.encryptId }">
         
    	 <div class="grid_3 lable-right">
         	<label class="form-lbl">家庭编号：</label>
    	 </div>
    	 <div class="grid_5 lable-lef" >
    	     <input type="text" class="form-txt" name="groupFamily.familyAccount" id="groupFamily.familyAccount" value="${groupFamily.familyAccount }" readonly>
    	 </div>
    	 <div class="grid_3 lable-right">
         	<label class="form-lbl">家长姓名：</label>
    	 </div>
    	 <div class="grid_5 lable-lef" >
    	     <input type="text" class="form-txt" name="groupFamily.familyMaster" id="groupFamily.familyMaster" value="${groupFamily.familyMaster }" readonly>
    	 </div>
    	 <div class="grid_3 lable-right">
         	<label class="form-lbl">家长证件号：</label>
    	 </div>
    	 <div class="grid_5 lable-lef" >
    	     <input type="text" class="form-txt" name="groupFamily.masterCardNo" id="groupFamily.masterCardNo" value="${groupFamily.masterCardNo }" readonly>
    	 </div>
    	 <div class='clearLine'>&nbsp;</div>
    	  <br/>
    	<div class="grid_3 lable-left">
         	<label class="form-lbl"><b>添加家庭成员</b></label>
    	 </div>
    	 <div class="grid_2 lable-right">
         	<label class="form-lbl">证件号：</label>
    	 </div>
    	 <div class="grid_5 lable-right">
         	<input id="idCardNo" class="form-txt" maxlength="18" name="idCardNo">
    	 </div>
    	 <div class="grid_4 lable-right">
         	<label class="form-lbl">与家长的关系：</label>
    	 </div>
    	 <div class="grid_4 lable-right">
         	<select name="familyRelationId" 
				id="familyRelationId" class="form-select" >
				<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@RELATION_SHIP_WITH_MASTER"  exceptInternalIds="@com.tianque.domain.property.RelationShipWithHead@HEADER"/>
			</select>
    	 </div>
    	 <div class="grid_1"></div>
    	 <div class="grid_5 lable-left">
         	<input id="addMember" type="button" class="defaultBtn"  value="添加"/>
    	 </div>
    	 <div class='clearLine'>&nbsp;</div>
    	 <div class="grid_24 lable-left">
         	<label class="form-lbl"><b>现有家庭成员</b></label>
    	 </div>
    	 <div class='clearLine'>&nbsp;</div>
    	 <div id="groupFamilyMembersList" style="width:100%">
			<table id="existGroupFamilyMembersList"></table>
			<div id="existGroupFamilyMembersListPager"></div>
		</div>
		<div class='clearLine'>&nbsp;</div>
		
    	 
    </form>
</div> 
<script type="text/javascript">
<pop:formatterProperty name="population.gender" domainName="@com.tianque.domain.property.PropertyTypes@GENDER" />

$("#groupFamilyMembersList").delegate(".deleteGroupFamilyMember","click",function(){
	var populationId = $(this).attr("populatId");
	var populationType = $(this).attr("populatType");
	$.confirm({
		title:"确认删除家庭成员",
		message:"确认要删除家庭成员吗?",
		okFunc: function(){
			$.ajax({
				async:false,
				url:'${path}/baseinfo/familyInfo/deleteGroupFamilyMember.action?populationId='+populationId+'&populationType='+populationType+'&groupFamilyId='+$("#groupFamilyId").val(),
				type:'GET',
				dataType:'json',
				success : function(data){
					if(data==null||data==""){
							$.messageBox({message:"删除家庭成员成功！"});
							$("#existGroupFamilyMembersList").trigger("reloadGrid");
					}else{
							$.messageBox({message:data,level:"error"});
					}
				},
				error : function(){
					$.messageBox({
						message : "加载失败，请刷新页面！",
						level : "error"
					});
				}
			});
		}
	});
	$("#groupFamilyList").trigger("reloadGrid");
});
 $("#groupFamilyMembersList").delegate(".viewPopulationInfo","click",function(){
	var memberId = $(this).attr("memberId");
	var actionUrl;
	var pid = memberId.substr(0, memberId.indexOf("_"));
	var type = memberId.substr(memberId.indexOf("_")+1);
	if(type == '<s:property value="@com.tianque.service.util.PopulationType@HOUSEHOLD_STAFF"/>'){
		actionUrl = '${path}/baseinfo/householdStaff/dispath.action?mode=view&population.id='+pid;
	}else if(type == '<s:property value="@com.tianque.service.util.PopulationType@FLOATING_POPULATION"/>'){
		actionUrl = '${path}/baseinfo/floatingPopulationManage/dispath.action?mode=view&population.id='+pid;
	}else if(type == '<s:property value="@com.tianque.service.util.PopulationType@UNSETTLED_POPULATION"/>'){
		actionUrl = '${path}/baseinfo/unsettledPopulationManage/dispatchOperate.action?mode=view&unsettledPopulation.id='+pid;
	}else if(type == '<s:property value="@com.tianque.service.util.PopulationType@OVERSEA_STAFF"/>') {
		actionUrl = '${path}/baseinfo/overseaPersonnelManage/dispatch.action?mode=view&isHiddenPersonnelTrack=1&overseaPersonnel.id='+pid;
	}else return;
	$("#groupFamilyPopulationDialog").createDialog({
		width: 800,
		height: 600,
		title:'查看家庭成员信息',
		modal : true,
		url:actionUrl,
		buttons: {
		   "关闭" : function(){
		        $(this).dialog("close");
		   }
		}
	});
}); 


$(document).ready(function(){
	
	function nameFont(el,options,rowData){
		return '<a href="javascript:void(0)" class="viewPopulationInfo" memberId="'+rowData.id+'"><span>'+rowData.population.name+'</span></a>';
	}
	function operateFormatter(el, options, rowData){
		return '<a href="javascript:void(0)" class="deleteGroupFamilyMember" populatId="'+rowData.populationIdEncryptId+'" populatType="'+rowData.populationType+'" ><U><font color="#6633FF">删除</font></U></a>';
	}
	 jQuery("#existGroupFamilyMembersList").jqGridFunction({
			datatype: "local",
		 	colNames:['id','populationIdEncryptId','populationId','populationType','姓名','性别','证件号','与家长关系','户籍地址/国籍','常住地址','操作'],
		 	height:200,
		   	colModel:[
			   {name:'id',index:'id',frozen:true,hidden:true,key:true},
			   {name:'populationIdEncryptId',index:'populationIdEncryptId',frozen:true,hidden:true,key:true},
			   {name:'populationId',index:'populationId',frozen:true,hidden:true,key:true},
			   {name:'populationType',index:'populationType',frozen:true,hidden:true,key:true},
	   	       {name:'population.name',width:80,formatter:nameFont,sortable:false},
	   	       {name:'population.gender',formatter:genderFormatter,width:40,sortable:false},
		       {name:'population.idCardNo',width:120,sortable:false},
		       {name:"familyRelation.displayName",width:80,sortable:false},
		       {name:'population.nativeLocation',width:150,sortable:false},
		       {name:'population.currentAddress',width:200,sortable:false},
		       {name:'operation',width:70,formatter:operateFormatter,align:"center",frozen:true,sortable:false}
		   	],
		   	showColModelButton:false
		   	
	});
	 jQuery("#existGroupFamilyMembersList").jqGrid('setFrozenColumns');
	 var initParam = {
				"groupFamilyId": $("#groupFamilyId").val()
			};
			$("#existGroupFamilyMembersList").setGridParam({
				url:'${path}/baseinfo/familyInfo/findFamilyMembersByFamilyIdNoMasterAndIncludeRelation.action',
				datatype: "json",
				page:1
			});
			$("#existGroupFamilyMembersList").setPostData(initParam);
			$("#existGroupFamilyMembersList").trigger("reloadGrid");
    
    	function addFamilyMember(data,flag){
    		 $.ajax({
					url:'${path}/baseinfo/familyInfo/addFamilyMember.action?familyRelationId='+$("#familyRelationId").val()+'&groupFamilyId='+$("#groupFamilyId").val()+'&savePeople.populationId='+data.populationId+'&savePeople.populationType='+data.populationType+'&whetherExistOtherFamily='+flag,
					type:'get',
					dataType:'json',
					success : function(data){
						if(data==null||data==""){
							$.messageBox({message:"新增家庭成员成功！"});
							$("#existGroupFamilyMembersList").trigger("reloadGrid");
							$("#idCardNo").val("");
							$("#familyRelationId option[value='']").attr("selected", true);
						}else{
							$.messageBox({message:data,level:"error"});
						}
					},
					error : function(){
						$.messageBox({
							message : "加载失败，请刷新页面！",
							level : "error"
						});
					}
				});
    	}
    	$("#addMember").click(function(){
    		var idCardNo=$("#idCardNo").val();
    		if( idCardNo==null ||idCardNo==""){
    			$.messageBox({message:"证件号码无效！",level:"warn"});
    			return ;
    		}
			 if($("#familyRelationId").val()==""||$("#familyRelationId").val()==null){
					$.messageBox({message:"与家长的关系不能为空！",level:"warn"});
					return ;
				}
			 $.ajax({
					url:'${path}/baseinfo/familyInfo/judgeExistAtOrgCodeOfFamily.action?idCardNo='+$("#idCardNo").val()+'&familyRelationId='+$("#familyRelationId").val()+'&groupFamilyId='+$("#groupFamilyId").val(),
					type:'GET',
					dataType:'json',
					async :true,
					success : function(data){
						if(data != null){
							if(data.populationId != null){
								if(data.name == undefined  || data.name == null ){
									addFamilyMember(data,false);
								}else{
									if(data.name == $("input[name='groupFamily.familyMaster']").val() ){
										$.messageBox({message:"此人已在该家庭存在！",level:"warn"});
										$("#idCardNo").val("");
										$("#familyRelationId option[value='']").attr("selected", true);
									}else{
										$.confirm({
											title:"确认添加",
											message:data.name+"已存在其他家庭，确认添加后会将此人从原家庭中移除，是否确认添加?",
											width: 400,
											okFunc: function(){addFamilyMember(data,true);}
										});
									}
								}
							}else{
								$.messageBox({message:data,level:"error"});
							}
						}else{
							$.messageBox({message:"添加失败！！！",level:"error"});
						}
					},
					error : function(){
						$.messageBox({
							message : "加载失败，请刷新页面！",
							level : "error"
						});
					}
				});
			 $("#groupFamilyList").trigger("reloadGrid");
    	});
    	
     });
</script>