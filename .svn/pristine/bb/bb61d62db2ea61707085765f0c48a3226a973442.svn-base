<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div id="incidentType_tree" style="padding:0 0 0 10px;"></div>
<input id="currentIncidentTypeId"  type="hidden"/>
<s:iterator value="extTreeData.children" var="child"></s:iterator>
<script>
	var incidentTypeTree = $("#incidentType_tree").plaitSchemeTree(
		{
			<s:if test='null!=#parameters.incidentTypeTree[0] && !"".equals(#parameters.incidentTypeTree[0])'>
			,isRootSelected:false
			</s:if>
		}
	);
	/*获取字典名称*/
	function getIncidentText(){
		return  $.getSelectedNode(incidentTypeTree).attributes.text;
	}
	/*获取字典ID*/
	function getPropertyDictId(){
		return  $.getSelectedNode(incidentTypeTree).attributes.propertyDictId;
	}

	/*用于incidentType 是否为返回null,以及 按钮 状态, true 修改 ，删除 ,false  null ,新增 */
	function enableAddIncident(){
		return  $.getSelectedNode(incidentTypeTree).attributes.enableAddIncident;
	}
	/*是否叶子节点*/
	function isNodeleaf(){
		return $.getSelectedNode(incidentTypeTree).attributes.leaf;
	}
	function currentSelectNode(){
		return $.getSelectedNode(incidentTypeTree);
	}
	/*获取父节点的incidentTypeId*/
	function getParentNodeIncidentTypeId(){
		return $.getSelectedNode(incidentTypeTree).parentNode.attributes.id;
	}
	/*获取第一个子节点*/
	function getfirstChildNodeId(){
		return $.getSelectedNode(incidentTypeTree).firstChild.attributes.id;
		}
	/*获取当前节点的层级*/
	function getcurrentLevel(){
		return $.getSelectedNode(incidentTypeTree).attributes.level;
		}
	
	$(document).ready(function(){
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
			$.ajax({
				url:"/incident/plaitScheme/index.jsp",
				success:function(data){
					$(".center-right").html(data);
				}
			})
		}
		$.addClick(incidentTypeTree,afterChangNode);
		<s:if test='null!=#parameters.incidentTypeTree[0] && !"".equals(#parameters.incidentTypeTree[0])'>
		$.setTreeValue(<s:property value="#parameters.incidentTypeTree[0]"/>,incidentTypeTree);
		</s:if>

	});



</script>