<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<script type="text/javascript">
var dialogWidth=800;
var dialogHeight=600;
var incidentDealPlanId=null;

function getIncidentTypeId(){
    if(getcurrentLevel()==2){
    	return getCurrentIncidentId();
     }else if(getcurrentLevel()==3){
    	 return getParentNodeIncidentTypeId();
     }
    return null;
    
}
function getDegreeId(){
	var degreeId=null;
	if(getcurrentLevel()==3){
		degreeId=getPropertyDictId();
	}
	return degreeId;
}

function getDegreeRuleListRule(data){
	var degreeRuleList =data.incidentType.degreeRule;
	
	var degree = data.degree;
	var ruleFormat="";
	
    if(degreeRuleList!=null && degree!=null){
   
	    	 for(var i=0;i<degreeRuleList.length;i++){
	        	if(degreeRuleList[i].degree.id==degree.id){
	            	ruleFormat=degreeRuleList[i].rule;            
	            	break;
	          }        
	      }
	}

    $("#incidentTypeDegreeRuleListRule").html(ruleFormat+"<br/>");
	
}
function canCopy(){
	var degreeId=getDegreeId();
	
	var param={"incidentDealPlan.incidentType.id":getIncidentTypeId()};
	if(degreeId!=null){
		param=$.extend(param,{"incidentDealPlan.degree.id":degreeId}); 
	}
	var isCanCopy = false;
    $.ajax({
    	data:param,
    	async:false,
		url:"${path}/incident/incidentDealPlans/getIncidentPlanAndDealTeam.action",
		success:function(data){
			incidentDealPlanId=data.id;
			$("#incidentTypeDescription").html(data.incidentType.description);
			 getDegreeRuleListRule(data);
			loadIncidentDealTeamGridDate(); 
			loadIncidentPlanStepGridDate();
			if(incidentDealPlanId!=null){
				isCanCopy=true;
			}
		}
		});
	return isCanCopy;
			
}
function ajaxInitPageData(){
	
	if(!isNodeleaf()){
		clearPage();
		return;
	 }
	 
	if(getIncidentTypeId()==null)return;
	var degreeId=getDegreeId();
	
	var param={"incidentDealPlan.incidentType.id":getIncidentTypeId()};
	if(degreeId!=null){
		param=$.extend(param,{"incidentDealPlan.degree.id":degreeId}); 
	}
    $.ajax({
	    	data:param,
			url:"${path}/incident/incidentDealPlans/getIncidentPlanAndDealTeam.action",
			success:function(data){
				incidentDealPlanId=data.id;
				 $("#incidentTypeDescription").html(data.incidentType.description);
				 getDegreeRuleListRule(data);
				loadIncidentDealTeamGridDate(); 
				loadIncidentPlanStepGridDate();
				
				
				if(incidentDealPlanId==null){
                    // $("#delete").buttonDisable();
                   //  $("#copy").buttonDisable();
					// $.messageBox({message:"请选择一个事件分级",level: "info"});
				}
				
			
				if(data.incidentType.id){
					
					$("#incidentDealPlanEndRule").html(data.endRule);
	                if(data.dealingTeams!=null && data.dealingTeams.length>0){
					    $("#incidentDealTeamComposition").html(data.dealingTeams[0].composition);
					    $("#incidentDealTeamResponsibility").html(data.dealingTeams[0].responsibility);
					}else{
						$("#incidentDealTeamComposition").html("");
					    $("#incidentDealTeamResponsibility").html("");
					}
				 }else{
					 $.messageBox({message:data,level: "error" });
				 }
			  }
	  });
  }

function onIncidentTypeChanged(){
	/*
	if(isNodeleaf()){
		  $("#delete").buttonEnable();
          $("#copy").buttonEnable();
          $("#edit").buttonEnable();
	}else{
		  $("#delete").buttonDisable();
          $("#copy").buttonDisable();
          $("#edit").buttonDisable();
	} 
	*/
	if(getIncidentTypeId()!=null){
		ajaxInitPageData();
	}
}
$(function(){
	   processHeight();
	   ajaxInitPageData();
	   onIncidentTypeChanged();
	 
	   $("#edit").click(function(){
			 if(!isNodeleaf()){
				 $.messageBox({message:"请选择一个事件分级",level: "warn"});
				 return;
		     }
			 if(getIncidentTypeId()==null){
				 $.messageBox({message:"请选择一个事件分级",level: "warn"});
				 return;
			 }
			  var degreeId=getDegreeId();
			  var param="incidentDealPlan.incidentType.id="+getIncidentTypeId();
			  if(degreeId!=null){
					param +="&incidentDealPlan.degree.id="+degreeId; 
			  }
			
			  if(incidentDealPlanId!=null){
				 param+="&incidentDealPlan.id="+incidentDealPlanId;
			  }
			
	           $("#incidentDealDialog").createDialog({
	   			width: dialogWidth,
	   			height: 300,
	   			position:'center',
	   			title:'编辑预案定制',
	   			modal : true,
	   			url:'${path}/incident/incidentDealPlans/initIncidentPlan.action?'+param,
	   			buttons: {
	   			  "上一步":function(){
			  	    },
	   			  "下一步":function(){
			  	    },
	   			  "保存" : function(event){
	     			  $("#mainNextForm").submit();
				    },
	   			   "关闭" : function(){
				    	ajaxInitPageData();
	   			        $(this).dialog("close");
	   			   }
	   			}
	   		});
	      });
	   jQuery("#incidentdtList").jqGridFunction({
			datatype: "local",
		 	colNames:['应急事件预案ID','是否领导小组','名称','牵头部门','参与部门','职责'],
		 	colModel:[
		 	         {name:"incidentDealPlan.id",index:"incidenttypeid",width:30,hidden:true,frozen:true,sortable:false},
		 	         {name:"teamType",index:"teamtype",hidden:true,frozen:true,sortable:false},
		 	         {name:"teamName",index:"teamname",width:70,frozen:true,sortable:false},
		 	         {name:"teamLeader",index:"teamleader",width:130,frozen:true,sortable:false},
		 	         {name:"teamMember",index:"teammember",width:150,frozen:true,sortable:false},
		 	         {name:"responsibility",index:"responsibility",width:150,frozen:true,sortable:false}
			 	 	 ],
	 	 	height:130,
			autowidth:false,
			width:$(".ui-layout-center").width()-113,
		   	multiselect:false,
		   	altclass:true
		  });
	      jQuery("#incidentdtList").jqGrid('setFrozenColumns');

		  jQuery("#incidentPSList").jqGridFunction({
				datatype: "local",
				sortname:"seqindex",
			 	colNames:['顺序','处置内容'],
			 	colModel:[
			 	         {name:"seqIndex",index:"seqindex",width:50,hidden:true,frozen:true},
			 	         {name:"content",index:"content",width:500,frozen:true,sortable:false}
				 	 	],
		 	 	height:150,
				autowidth:false,
				width:$(".ui-layout-center").width()-113,
			   	multiselect:false,
			   	altclass:true
			   });
		   jQuery("#incidentPSList").jqGrid('setFrozenColumns');


			$("#copy").click(function(){	
				 if(!isNodeleaf()){
					 $.messageBox({message:"请选择一个事件分级",level: "warn"});
					 return;
			     }
				 if(getIncidentTypeId()==null){
					 $.messageBox({message:"请选择一个事件分级",level: "warn"});
					 return;
				 }
				 var flag = canCopy();
				 if(!flag){
					 $.messageBox({message:"尚未编制，无法复制",level: "warn"});
					 return;
				 }
			       $("#incidentDealDialog").createDialog({
			   			width: 280,
			   			height:200,
			   			title:'预案复制',
			   			modal : true,
			   			url:'/incident/plaitScheme/incidentPlanCopy.jsp',
			   			buttons: {
			   			  "确定" : function(event){
			   			    var incidentTypeId=$("#incidentTList").val();
			   			    var degreeId = $("#incidentClass").val();
			
			    	         if(incidentTypeId !=null){
				    	         var param={"incidentDealPlan.incidentType.id":incidentTypeId,"incidentDealPlan.id":incidentDealPlanId};
				    	     
				    	         if(degreeId!=null){
				    	        	param={"incidentDealPlan.incidentType.id":incidentTypeId,"incidentDealPlan.id":incidentDealPlanId,"incidentDealPlan.degree.id":degreeId}
					    	      }
			    	        	  $.ajax({
			  				    	data:param,
			  						url:"${path}/incident/incidentDealPlans/copyIncidentDealPlan.action",
			  						success:function(data){
			  						
				  						if(data=="own"){
				  						  $.messageBox({message:"不能复制本身",level: "error" });
					  					}else if(data == "otherExist"){
					  					  $.messageBox({message:"被复制对象 已经有数据,不能再复制 ,可以删除后在复制",level: "error" });    
						  				 }else if(data == "success"){
						  					 $.messageBox({message:"复制成功"});    
							  			 }else{
							  				 $.messageBox({message:data,level: "error" });
								  		  }
				  					    
			  						   }
			  				        }); 
			    	        		 $(this).dialog("close"); 
				    	       }
			   			   
						    },
			   			   "关闭" : function(){
			   			        $(this).dialog("close");
			   			   }
			   			}
			   		});
			});

			$("#delete").click(function(){
				if(!isNodeleaf()){
					 $.messageBox({message:"请选择一个事件分级",level: "warn"});
					 return;
				};
				if(incidentDealPlanId==null){
					 $.messageBox({message:"无处置信息，无需删除",level: "warn"});
					 return;
			    };
				
				var str="确定要删除吗?一经删除将无法恢复";
				 $.confirm({
					title:"确认删除",
					message:str,
					okFunc: function() {
				     $.ajax({
				    	data:{"incidentDealPlan.id":incidentDealPlanId},
						url:"${path}/incident/incidentDealPlans/deleteIncidentPlan.action",
						success:function(data){
							if(data=="success"){
								  clearPage();
							      $.messageBox({message:"已经成功删除该信息!"});
							 }else{
								 $.messageBox({message:data,level: "error" });
							 }
						    }
				        });
			      }
		        });
		    });
	
});

function  loadIncidentDealTeamGridDate(){
   
	if(!isNodeleaf())return;
	if(incidentDealPlanId==null){
		$("#incidentdtList").clearGridData();
		return;
	}
	  var initParam = {"incidentDealTeam.incidentDealPlan.id":incidentDealPlanId,"incidentDealTeam.teamType":0};
	  $("#incidentdtList").setGridParam({
			url:'${path}/incident/incidentDealPlans/findIncidentDealTeamsList.action',
			datatype: "json",
			page:1
		});
		$("#incidentdtList").setPostData(initParam);
		$("#incidentdtList").trigger("reloadGrid");
}

 function  loadIncidentPlanStepGridDate(){
	  
		if(!isNodeleaf())return;
	
		if(incidentDealPlanId==null){
			$("#incidentPSList").clearGridData();
			return;
		}
		  var initParam = {"incidentPlanStep.incidentDealPlan.id":incidentDealPlanId};
		  $("#incidentPSList").setGridParam({
				url:'${path}/incident/incidentDealPlans/findIncidentPlanSteps.action',
				datatype: "json",
				page:1
			});
			$("#incidentPSList").setPostData(initParam);
			$("#incidentPSList").trigger("reloadGrid");
		}


  function  clearPage(){
	    $("#incidentTypeDescription").empty();
	    $("#incidentTypeDegreeRuleListRule").empty();
	    $("#incidentDealPlanEndRule").empty();
	    $("#incidentDealTeamComposition").empty();
	    $("#incidentDealTeamResponsibility").empty();
		$("#incidentdtList").clearGridData();
		$("#incidentPSList").clearGridData();
	}


   function processHeight(){
		var timer;
		function getHeight(){
			var bodyHeight=document.documentElement.clientHeight;
			var mainHeight=$(".process_project").height(bodyHeight-185);
		}
		getHeight();
		$(window).resize(function(){
			clearTimeout(timer);
			timer=setTimeout(function(){getHeight()},200);
		});
	};

	
</script>

<div class="content nav_content">
	<div class="ui-corner-all" id="nav">
		<pop:JugePermissionTag ename="editIncidentDealPlan">
			<a id="edit" href="javascript:void(0)"><span><strong
					class="ui-ico-xg"></strong> <s:property value="#request.name" /> </span>
			</a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="deleteIncidentDealPlan">
			<a id="delete" href="javascript:void(0)"><span><strong
					class="ui-ico-sc"></strong> <s:property value="#request.name" /> </span>
			</a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="copyIncidentDealPlan">
			<a id="copy" href="javascript:void(0)"><span><strong
					class="ui-ico-cakan"></strong> <s:property value="#request.name" />
			</span> </a>
		</pop:JugePermissionTag>
	</div>
	<div id="incidentDealDialog"></div>
</div>
<div class="process_project">
	<div class="project_explain clearfix">
		<div class="define clearfix">
			<strong class="title">事件定义：</strong>
			<p class="text" id="incidentTypeDescription">
				${incidentDealPlan.incidentType.description}
			</p>
		</div>
		<div class="define clearfix">
			<strong class="title">分级依据：</strong>
			<pre class="text" id="incidentTypeDegreeRuleListRule"></pre>
		</div>
	</div>
	<div class="project_lead">
		<div class="leadContent clearfix">
			<strong class="title">终止条件：</strong>
			<p class="text" id="incidentDealPlanEndRule">
				${incidentDealPlan.endRule}
			</p>
		</div>
		<div class="leadContent clearfix">
			<strong class="title">领导小组组成方式：</strong>
			<p class="text" id="incidentDealTeamComposition">
				${incidentDealTeam.composition}
			</p>
		</div>
		<div class="leadContent clearfix">
			<strong class="title">领导小组职责：</strong>
			<p class="text" id="incidentDealTeamResponsibility">
				${incidentDealTeam.responsibility}
			</p>
		</div>
	</div>
	<div class="project_list">
		<h3 class="title">
			现场处置小组
		</h3>
		<div class="incidentdtList">
			<table id="incidentdtList"></table>
		</div>
	</div>
	<div class="project_list project_intro">
		<h3 class="title">
			处置步骤
		</h3>
		<div class="incidentdtList">
			<table id="incidentPSList"></table>
		</div>
	</div>
</div>

