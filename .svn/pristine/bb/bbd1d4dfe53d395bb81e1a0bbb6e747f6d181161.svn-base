<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<script type="text/javascript">


  $(function(){
	
		jQuery("#incidentPlanStepList").jqGridFunction({
		datatype: "local",
		sortname:"seqindex",
	 	colNames:['id','顺序','处置内容'],
	 	colModel:[
	 	         {name:"id",index:"id",hidden:true,frozen:true},
	 	         {name:"seqIndex",index:"seqindex",width:50,hidden:true,frozen:true},
	 	         {name:"content",index:"content",width:500,frozen:true,sortable:true}
		 	 	],
	   	multiselect:true,
	   	altclass:true,
	   	height:130
	   });
	  jQuery("#incidentPlanStepList").jqGrid('setFrozenColumns');
	  loadGridDate();

	  $("#addPlanStep").click(function(){
		
		  $("#editincidentPlanStep").createDialog({
	   			width: 500,
	   			height:320,
	   			title:'处置步骤',
	   			modal : true,
	   			url:"${path}/incident/incidentDealPlans/initIncidentPlanStep.action?mode=add&incidentPlanStep.incidentDealPlan.id="+$("#hiddenIncidentDealPlanId").val(),
	   			buttons: {
	   			  "确定" : function(event){
   			          $("#planStepForm").submit();
				    },
	   			   "关闭" : function(){
	   			        $(this).dialog("close");
	   			   }
	   			}
	   		});
	  });

	  $("#incidentPlanStepList").click(function(){
		 	var selectedIds = $("#incidentPlanStepList").jqGrid("getGridParam", "selarrrow");
			if(selectedIds.length>1){
				 $("#upPlanStep").buttonDisable();
				 $("#topPlanStep").buttonDisable();
				 $("#downPlanStep").buttonDisable();
				 $("#bottomPlanStep").buttonDisable();
				 return;
			 }else{
					$("#upPlanStep").buttonEnable();
				    $("#topPlanStep").buttonEnable();
				    $("#downPlanStep").buttonEnable();
					$("#bottomPlanStep").buttonEnable();
			 }
			var selectedId = $("#incidentPlanStepList").getGridParam("selrow");
			var selectRow =selectRowFunction(selectedId);
			var rowindexNum =selectRow.rowIndex;
			var rowTotalNum=this.rows.length;
	        if(rowindexNum==1){
	        	 $("#upPlanStep").buttonDisable(); 
	        	 $("#topPlanStep").buttonDisable();
	        	 $("#downPlanStep").buttonEnable();
				 $("#bottomPlanStep").buttonEnable();
		     }
		     if(rowindexNum+1==rowTotalNum){
		    	 $("#upPlanStep").buttonEnable(); 
	        	 $("#topPlanStep").buttonEnable();
		    	 $("#downPlanStep").buttonDisable();
				 $("#bottomPlanStep").buttonDisable();	
			 } 
				
	  });
		$("#deletePlanStep").click(function(){
			var selectedIds = $("#incidentPlanStepList").jqGrid("getGridParam", "selarrrow");
			if(selectedIds.length==0){
				$.messageBox({level:"warn",
					message:'请选择一条记录后再删除！'});
				return ;
			}
			
			var str="确定要删除吗?一经删除将无法恢复";
				$.confirm({
						title:"确认删除",
						message:str,
						okFunc: function() {
					     $.ajax({
							data:{"idListStr":selectedIds.toString()},
							url:"${path}/incident/incidentDealPlans/deleteIncidentPlanStep.action",
							success:function(data){
						
								if(data=="success"){
									 for(var i = selectedIds.length-1 ; i >-1 ; i--){
										$("#incidentPlanStepList").delRowData(selectedIds[i]);
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

	  $("#updatePlanStep").click(function(){
		  var selectId = getSelectedIdLast();
		  if(selectId==null){
			  $.messageBox({level:"warn",
					message:'请选择一条记录后再修改！'});
				return ;
		  }
	    $("#editincidentPlanStep").createDialog({
	   			width: 500,
	   			height:320,
	   			title:'处置步骤',
	   			modal : true,
	   			url:"${path}/incident/incidentDealPlans/initIncidentPlanStep.action?mode=edit&incidentPlanStep.incidentDealPlan.id="+$("#hiddenIncidentDealPlanId").val()+"&incidentPlanStep.id="+selectId,
	   			buttons: {
	   			  "确定" : function(event){
	   			     $("#planStepForm").submit();
				    },
	   			   "关闭" : function(){
	   			        $(this).dialog("close");
	   			   }
	   			}
	   		});
	  });
	  $("#topPlanStep").click(function(){
			var selectedId = $("#incidentPlanStepList").getGridParam("selrow");
			if(selectedId==null)return;
			var selectRow=selectRowFunction(selectedId);
			 $("#upPlanStep").buttonDisable();
			 $("#topPlanStep").buttonDisable();
			 $("#downPlanStep").buttonEnable();
			 $("#bottomPlanStep").buttonEnable();
			 var rowData= $("#incidentPlanStepList").jqGrid("getRowData", selectedId); 
			 $("#incidentPlanStepList").jqGrid('delRowData', selectedId);
			$("#incidentPlanStepList").jqGrid("addRowData",selectedId, {"id":selectedId,"seqIndex":rowData.seqIndex,"content":rowData.content}, "first");
			$("#incidentPlanStepList").setSelection(selectedId);
	   });
	
	   
	  $("#upPlanStep").click(function(){
			var selectedId = $("#incidentPlanStepList").getGridParam("selrow");
			if(selectedId==null){
				 return;
			}
			var trList=$("#incidentPlanStepList tr");
			var selectRow=selectRowFunction(selectedId);
			if(selectRow.rowIndex==1)return;
			if(selectRow.rowIndex-1==1){
				 $("#upPlanStep").buttonDisable();
				 $("#topPlanStep").buttonDisable();
				 $("#downPlanStep").buttonEnable();
				 $("#bottomPlanStep").buttonEnable();
			 }else{
			
				 $("#downPlanStep").buttonEnable();
				 $("#bottomPlanStep").buttonEnable();
			  }
	         var rowData= $("#incidentPlanStepList").jqGrid("getRowData", selectedId); 
	     	 var prevRow=trList[selectRow.rowIndex-1];
	     	 var prevRowData= $("#incidentPlanStepList").jqGrid("getRowData", prevRow.id); 
	     	$("#incidentPlanStepList").jqGrid('delRowData', selectedId);
			$("#incidentPlanStepList").jqGrid("addRowData",selectedId, {"id":selectedId,"seqIndex":rowData.seqIndex,"content":rowData.content},"before",prevRowData.id);
			$("#incidentPlanStepList").setSelection(selectedId);
	   });

	  $("#downPlanStep").click(function(){
			var selectedId = $("#incidentPlanStepList").getGridParam("selrow");
			if(selectedId==null){return;}
			var trList=$("#incidentPlanStepList tr");
			var rowTotalNum=trList.length;
			var selectRow=selectRowFunction(selectedId);
			if(selectRow.rowIndex+1==rowTotalNum-1){
				 $("#downPlanStep").buttonDisable();
				 $("#bottomPlanStep").buttonDisable();
				 $("#upPlanStep").buttonEnable();
				 $("#topPlanStep").buttonEnable();
			 }else{
				 $("#upPlanStep").buttonEnable();
				 $("#topPlanStep").buttonEnable();
			 }
			var rowData= $("#incidentPlanStepList").jqGrid("getRowData", selectedId); 
			var afterRow =trList[selectRow.rowIndex+1];
			var afterRowData= $("#incidentPlanStepList").jqGrid("getRowData", afterRow.id); 
			$("#incidentPlanStepList").jqGrid('delRowData', selectedId);
			$("#incidentPlanStepList").jqGrid("addRowData",selectedId, {"id":selectedId,"seqIndex":rowData.seqIndex,"content":rowData.content}, "after",afterRowData.id);
			$("#incidentPlanStepList").setSelection(selectedId);
	 });
	  $("#bottomPlanStep").click(function(){
			var selectedId = $("#incidentPlanStepList").getGridParam("selrow");
			if(selectedId==null)return;
			var selectRow=selectRowFunction(selectedId);
			 $("#downPlanStep").buttonDisable();
			 $("#bottomPlanStep").buttonDisable();
			 $("#upPlanStep").buttonEnable();
			 $("#topPlanStep").buttonEnable();
			 var rowData= $("#incidentPlanStepList").jqGrid("getRowData", selectedId); 
			 $("#incidentPlanStepList").jqGrid('delRowData', selectedId);
			$("#incidentPlanStepList").jqGrid("addRowData",selectedId, {"id":selectedId,"seqIndex":rowData.seqIndex,"content":rowData.content}, "last");
			$("#incidentPlanStepList").setSelection(selectedId);
	   });
	   //通过父类From editIncidentDeal.jsp 传出  上移 下移 .. 排序的顺序
	  $("#incidentPlanStepTab").mouseleave(function() {
		  getSeqIndexListStr();
	  });
  });

  function selectRowFunction(selectedId){
		var trList=$("#incidentPlanStepList tr");
		for(var i=0;i<trList.length;i++){
			if(trList[i].id==selectedId){
				selectRow=trList[i];
	           break;
		    }
		}
		return selectRow;
	}


  function  loadGridDate(){
	  var initParam = {"incidentPlanStep.incidentDealPlan.id":$("#hiddenIncidentDealPlanId").val()};
	  $("#incidentPlanStepList").setGridParam({
			url:'${path}/incident/incidentDealPlans/findIncidentPlanSteps.action',
			datatype: "json",
			page:1
		});
		$("#incidentPlanStepList").setPostData(initParam);
		$("#incidentPlanStepList").trigger("reloadGrid");
	}
  function getSelectedIdLast(){
		var selectedId;
		var selectedIds = $("#incidentPlanStepList").jqGrid("getGridParam", "selarrrow");
		for(var i=0;i<selectedIds.length;i++){
			selectedId = selectedIds[i];
		}
		return selectedId;
	}

   function getSeqIndexListStr(){
	   var trList=$("#incidentPlanStepList tr");
		  var  seqIndexArray =new Array();
		  for(var i=0;i<trList.length;i++){
			  if(trList[i].id!=null && trList[i].id!=""){
				  seqIndexArray.push(trList[i].id);
			  }
		  }
	   $("#seqIndexListStrId").val("");
	   $("#seqIndexListStrId").val(seqIndexArray.toString());
   }

</script>
<div style="padding-left:10px;padding-right:10px" id="incidentPlanStepTab">
    <div class="ui-corner-all" id="nav">
     	<a id="addPlanStep" href="javascript:void(0)"><span><strong
					class="ui-ico-xz"></strong>添加</span></a>
		<a id="updatePlanStep" href="javascript:void(0)"><span><strong
					class="ui-ico-xg"></strong>修改</span></a>		
		<a id="deletePlanStep" href="javascript:void(0)"><span><strong
					class="ui-ico-sc"></strong>删除</span></a>
		<a id="topPlanStep" href="javascript:void(0)"><span><strong
					class="ui-ico-top"></strong>到顶</span></a>
		<a id="upPlanStep" href="javascript:void(0)"><span><strong
					class="ui-ico-sy"></strong>上移</span></a>
		<a id="downPlanStep" href="javascript:void(0)"><span><strong
					class="ui-ico-xy"></strong>下移</span></a>
	    <a id="bottomPlanStep" href="javascript:void(0)"><span><strong
					class="ui-ico-foot"></strong>到底</span></a>
    </div>
	<div>
		<table id="incidentPlanStepList"></table>
	</div>
    <div id="editincidentPlanStep"></div>
</div>
