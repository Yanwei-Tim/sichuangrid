<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ include file="/baseinfo/commonPopulation/jsFunctionInclude.jsp"%>
<script type="text/javascript">
  $(function(){
		jQuery("#incidentDealTeamList").jqGridFunction({
		datatype: "local",
		sortname:"id",
	 	colNames:['id','应急事件预案ID','是否领导小组','名称','牵头部门','参与部门','职责'],
	 	colModel:[
	 	         {name:"id",index:"id",hidden:true,width:30,frozen:true},
	 	         {name:"incidentDealPlan.id",index:"incidenttypeid",width:30,hidden:true,frozen:true},
	 	         {name:"teamType",index:"teamtype",hidden:true,frozen:true},
	 	         {name:"teamName",index:"teamname",width:70,frozen:true,sortable:true},
	 	         {name:"teamLeader",index:"teamleader",width:130,frozen:true,sortable:true},
	 	         {name:"teamMember",index:"teammember",width:150,frozen:true,sortable:true},
	 	         {name:"responsibility",index:"responsibility",width:150,frozen:true,sortable:true}
		 	 	 ],
	   	multiselect:true,
	   	altclass:true,
	   	height:130
	  });
       jQuery("#incidentDealTeamList").jqGrid('setFrozenColumns');
	   loadGridDate(); 

	   $("#updateDealTeam").live('click',function(){
		   var selectId = getSelectedIdLast();
		  if(selectId==null){
					$.messageBox({level:"warn",
						message:'请选择一条记录后再修改！'});
					return ;
			}
		   var param = 'mode=edit&incidentDealTeam2.id='+selectId;
		   $("#editincidentDealTeam").createDialog({
	   			width: 500,
	   			height:350,
	   			title:'处置小组',
	   			modal : true,
	   			url:'${path}/incident/incidentDealPlans/initIncidentDealTeam.action?'+param,
	   			buttons: {
	   			  "确定" : function(event){
	   			     $("#dealTeamForm").submit();
				    },
	   			   "关闭" : function(){
				    	 ajaxInitPageData();
	   			        $(this).dialog("close");
	   			      
	   			   }
	   			}
	   		});
		});

		$("#deleteDealTeam").click(function(){
			var str="确定要删除吗?一经删除将无法恢复";
			var selectedIds = $("#incidentDealTeamList").jqGrid("getGridParam", "selarrrow");
			if(selectedIds.length==0){
				$.messageBox({level:"warn",
					message:'请选择一条记录后再删除！'});
				return ;
			}
				$.confirm({
						title:"确认删除",
						message:str,
						okFunc: function() {
					    $.ajax({
						data:{"incidentDealTeam2.teamType":0,"idListStr":selectedIds.toString()},
						url:"${path}/incident/incidentDealPlans/deleteIncidentDealTeam.action",
						success:function(data){
				
							if(data=="success"){
								 for(var i = selectedIds.length-1 ; i >-1 ; i--){
									$("#incidentDealTeamList").delRowData(selectedIds[i]);
								  }
							      $.messageBox({message:"已经成功删除该信息!"});
							    }else{
								 $.messageBox({message:data,level: "error" });
							    }
						    }
				          });
				      }
			});
		});
	   $("#addDealTeam").click(function(){
		   var param = 'mode=add&addhDealPlanId='+$("#hiddenIncidentDealPlanId").val();
		   $("#editincidentDealTeam").createDialog({
	   			width: 500,
	   			height:350,
	   			title:'处置小组',
	   			modal : true,
	   			url:'/incident/plaitScheme/incidentDealTeamEdit.jsp?'+param,
	   			buttons: {
	   			  "确定" : function(event){
			         $("#dealTeamForm").submit();
				    },
	   			   "关闭" : function(){
	   			        $(this).dialog("close");
	   			       ajaxInitPageData();
	   			   }
	   			}
	   		});
		});
  });
  function getSelectedIdLast(){
		var selectedId;
		var selectedIds = $("#incidentDealTeamList").jqGrid("getGridParam", "selarrrow");
		for(var i=0;i<selectedIds.length;i++){
			selectedId = selectedIds[i];
		}
		return selectedId;
	}
  function  loadGridDate(){
	  var initParam = {"incidentDealTeam.incidentDealPlan.id":$("#hiddenIncidentDealPlanId").val(),"incidentDealTeam.teamType":0};
	  $("#incidentDealTeamList").setGridParam({
			url:'${path}/incident/incidentDealPlans/findIncidentDealTeamsList.action',
			datatype: "json",
			page:1
		});
		$("#incidentDealTeamList").setPostData(initParam);
		$("#incidentDealTeamList").trigger("reloadGrid");
	}
</script>

<div style="padding-left:10px;padding-right:10px" >
   <div class="ui-corner-all" id="nav">
     	<a id="addDealTeam" href="javascript:void(0)"><span><strong
					class="ui-ico-xz"></strong>添加</span></a>
		<a id="updateDealTeam" href="javascript:void(0)"><span><strong
					class="ui-ico-xg"></strong>修改</span></a>		
		<a id="deleteDealTeam" href="javascript:void(0)"><span><strong
					class="ui-ico-sc"></strong>删除</span></a>
   </div>
    <div>
      <table id="incidentDealTeamList"></table>
   </div>
   <div id="editincidentDealTeam"></div>
</div>