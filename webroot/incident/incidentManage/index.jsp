<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<jsp:include page="/includes/baseInclude.jsp" />

<div class="content" >
	<div class="ui-corner-all" id="nav">
		<pop:JugePermissionTag ename="addIncidentType">
			<a id="add" href="javascript:;"><span>新增</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="updateIncidentType">
			<a id="update" href="javascript:void(0)"><span>修改</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="deleteIncidentType">
			<a id="delete" href="javascript:void(0)"><span>删除</span></a>
		</pop:JugePermissionTag>
	</div>
</div>

<div style="clear: both;"></div>
<div id="incidentTypeDialog"></div>
<div class="system_management">
	<s:include value="/incident/incidentManage/viewIncidentType.jsp"></s:include>
</div>


<script type="text/javascript">
var dialogWidth= 700;
var dialogHeight= 600;

	
var timer;
function getHeight(){
	var bodyHeight=document.documentElement.clientHeight;
	var mainHeight=$(".system_management").height(bodyHeight-215);
	var mainHeight=$(".system_baseGrade .baseGradeMain").height(bodyHeight-375);
}
getHeight();
$(window).resize(function(){
	clearTimeout(timer);
	timer=setTimeout(function(){getHeight()},100);
})

	function onIncidentTypeChanged(){
		cleanViewIncidentTypeDates();
		if(getcurrentLevel()==2){
			viewIncidentType();
		}
	}

	
	//查看 
	function viewIncidentType(){
		$.ajax({
			async: false,
			url:"${path}/incident/systemManage/viewIncidentType.action?IncidentTypeId="+ifGetIncidnetId(),
			success:function(resultDate){
				fillViewIncidentTypeDates(resultDate);
			},
			 error: function(XMLHttpRequest, textStatus, errorThrown){
		 	      alert("提交数据时发生错误");
		 	 }
		});
	}

	function ifGetIncidnetId(){
		var incidnetId;
		if(getCurrentIncidentId()){
			incidnetId=getCurrentIncidentId();
		}else{
			incidnetId=$.getSelectedNode(tree).id;
		}
		return incidnetId;
	}

		$("#add").click( function(){
			if(getCurrentIncidentId()!=""){
				 $.messageBox({message:"请选择一个预案大类",level: "info"});
				return;
	        }
			$("#incidentTypeDialog").createDialog({
				width:dialogWidth,
				height:dialogHeight,
				title:"新增事件类型",
				url:"${path}/incident/systemManage/dispatchOperate.action?mode=add",
				buttons: {
				"保存" : function(event){
					$("#maintainForm").submit();
					},
	   			"关闭" : function(){
	        	$(this).dialog("close");
	   			}
				}
			});
			});
	
		$("#update").click( function(){
			if(getCurrentIncidentId()=="" || getCurrentIncidentId() ==null){
			   $.messageBox({message:"请选择一个预案小类",level: "info"});
			   return
			}
			$("#incidentTypeDialog").createDialog({
				width:dialogWidth,
				height:dialogHeight,
				title:"修改事件类型",
				url:"${path}/incident/systemManage/dispatchOperate.action?mode=edit&IncidentTypeId="+getCurrentIncidentId(),
				buttons: {
				"保存" :function(event){
					$("#maintainForm").submit();
					},
	   			"关闭" :function(){
	        	$(this).dialog("close");
	   			}
				}
			});
			});


			$("#delete").click(function(){
				if(getCurrentIncidentId()=="" || getCurrentIncidentId() ==null){
					$.messageBox({message:"请选择一个预案小类",level: "info"});
					return;
				}
				$.confirm({
					message:"确定要删除吗?一经删除将无法恢复",
					okFunc: function() {
						$.ajax({
							url:"${path}/incident/systemManage/deleteIncidentType.action?incidentTypeId="+ getCurrentIncidentId(),
							success:function(data){
								cleanViewIncidentTypeDates();
								$.removeNode(tree,data);
								$.messageBox({message:"已经成功删除该事件类型信息!"});
							}
						});
					}
				});
			});	

	
			function initButtonState(){
				$("#add").buttonEnable();
				$("#delete").buttonDisable();
				$("#update").buttonDisable();
			}

			function  fillViewIncidentTypeDates(resultDate){
				$("#viewTypeName").html(resultDate.name);
				$("#viewTypeDescription").html(resultDate.description);
				$("#viewTypeDegreed").attr("checked",resultDate.degreed);
				if(resultDate.degreed){
					$("#viewTyperule_0").html((resultDate.degreeRule[0].rule).replace(/\n/g,"<br />"));
					$("#viewTyperule_1").html((resultDate.degreeRule[1].rule).replace(/\n/g,"<br />"));
					$("#viewTyperule_2").html((resultDate.degreeRule[2].rule).replace(/\n/g,"<br />"));
					$("#viewTyperule_3").html((resultDate.degreeRule[3].rule).replace(/\n/g,"<br />"));
				}
				//alert($("#viewTyperule_3").html());
				
			}
			function  cleanViewIncidentTypeDates(){
				$("#viewTypeName").text("");
				$("#viewTypeDescription").text("");
				$("#viewTypeDegreed").attr("checked",false);
				$("#viewTyperule_0").text("");
				$("#viewTyperule_1").text("");
				$("#viewTyperule_2").text("");
				$("#viewTyperule_3").text("");
			}

			/*  预警树接口	
			function onIncidentTypeChanged(){
				alert("当前节点对象：" +currentSelectNode());
				alert("当前名称： "+ getIncidentText());
				alert("当前字典Id:" +  getPropertyDictId());
				alert("是否叶子节点：" + isNodeleaf());
				alert("当前事件类型Id:" +  getCurrentIncidentId());
				alert("是否可以新增："+enableAddIncident());
				alert("当前节点的父节点Id: "+getParentNodeIncidentTypeId());
				alert("当前节点层级："+ getcurrentLevel());
				alert("获取当前的节点的第一个子节点："+getcurrentLevel());
			} */



</script>