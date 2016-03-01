<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div id="incidentypeTree-top">
<h1 class="tree-title"><span></span>预案类型</h1></div>
<div id="incidentTypeTree-select" style="overflow: auto;_width:160px;">
	<div id="incidentType_tree_navigation" style="padding:0 0 0 10px;"></div>
</div>
<input id="currentIncidentTypeId"  type="hidden"/>
<s:iterator value="extTreeData.children" var="child"></s:iterator>
<script>
	var tree = $("#incidentType_tree_navigation").incidentTypeTree(
		{
			<s:if test='null!=#parameters.incidentTypeTree[0] && !"".equals(#parameters.incidentTypeTree[0])'>
			,isRootSelected:false
			</s:if>
		}
	);
	/*获取字典名称*/
	function getIncidentText(){
		return  $.getSelectedNode(tree).attributes.text;
	}
	/*获取字典ID*/
	function getPropertyDictId(){
		return  $.getSelectedNode(tree).attributes.propertyDictId;
	}

	/*用于incidentType 是否为返回null,以及 按钮 状态, false 修改 ，删除 ,true  null ,新增 */
	function enableAddIncident(){
		return  $.getSelectedNode(tree).attributes.enableAddIncident;
	}
	/*是否叶子节点*/
	function isNodeleaf(){
		return $.getSelectedNode(tree).attributes.leaf;
	}
	function currentSelectNode(){
		return $.getSelectedNode(tree);
	}
	/*获取父节点的incidentTypeId*/
	function getParentNodeIncidentTypeId(){
		return $.getSelectedNode(tree).parentNode.attributes.id;
	}
	/*获取第一个子节点*/
	function getfirstChildNodeId(){
		return $.getSelectedNode(tree).firstChild.attributes.id;
		}
	/*获取当前节点的层级*/
	function getcurrentLevel(){
		return $.getSelectedNode(tree).attributes.level;
		}
	function  fillViewIncidentTypeDates(resultDate){
		$("#viewTypeName").append(resultDate.name);
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
	$(document).ready(function(){
		$("#incidentTypeTree-select").height($(".ui-layout-center").outerHeight()-65);
		var lastSelectedNode=false;

		function afterChangNode(node){
			if(node.attributes.enableAddIncident){
				$("#currentIncidentTypeId").val("");
			}else{
				$("#currentIncidentTypeId").val(node.attributes.id);
			}
			
			if(typeof(onIncidentTypeChanged) != 'undefined'){
				onIncidentTypeChanged.call(null,node.attributes.id);
			}
			lastSelectedNode=node;
			if($("#currentIncidentTypeId").val()!=""){
				$.ajax({
					url:"/incident/systemManage/viewIncidentType.action?IncidentTypeId="+$("#currentIncidentTypeId").val(),
					success:function(data){
						fillViewIncidentTypeDates(data);
					}
				})
			}
		}
		$.addClick(tree,afterChangNode);
		<s:if test='null!=#parameters.incidentTypeTree[0] && !"".equals(#parameters.incidentTypeTree[0])'>
		$.setTreeValue(<s:property value="#parameters.incidentTypeTree[0]"/>,tree);
		</s:if>

	});



</script>