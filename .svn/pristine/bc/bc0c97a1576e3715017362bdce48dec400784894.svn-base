<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="incidentType_tree">
	<div id="incidentType_tree_navigation" style="padding: 10px 0 0 10px;"></div>
</div>
<input id="currentIncidentTypeId" type="hidden" />
<s:iterator value="extTreeData.children" var="child"></s:iterator>
<script>
	function centerLeftHeight(){
		var timer;
		function getHeight(){
			var centerHeight=$(".ui-layout-center").outerHeight();
			$(".incidentType_tree").height(centerHeight-$("#thisCrumbs").height()-30);
		}
		getHeight();
		$(window).resize(function(){
			clearTimeout(timer);
			timer=setTimeout(function(){getHeight()},100)
		})
	}
    centerLeftHeight();
    
	var tree = $("#incidentType_tree_navigation").caseLibarayTree(
		{
			<s:if test='null!=#parameters.incidentTypeTree[0] && !"".equals(#parameters.incidentTypeTree[0])'>
			isRootSelected:false
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

	/*用于incidentType 是否为返回null,以及 按钮 状态, true 修改 ，删除 ,false  null ,新增 */
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
	function currentSelectNodeId(){
		return $.getSelectedNode(tree).attributes.id;
	}
	/*获取父节点的incidentTypeId*/
	function getParentNodeIncidentTypeId(){
		return $.getSelectedNode(tree).parentNode.attributes.id;
	}
	/*获取父节点的字典ID*/
	function getParentNodePropertyDictId(){
		return $.getSelectedNode(tree).parentNode.attributes.propertyDictId;
	}
	
	/*获取第一个子节点*/
	function getfirstChildNodeId(){
		return $.getSelectedNode(tree).firstChild.attributes.id;
		}
	/*获取当前节点的层级*/
	function getcurrentLevel(){
		return $.getSelectedNode(tree).attributes.level;
		}
	/*
	$(document).ready(function(){
		var lastSelectedNode=false;

		function afterChangNode(node){
			
			if(node.attributes.enableAddIncident){
				$("#currentIncidentTypeId").val("");
			}else{
				$("#currentIncidentTypeId").val(node.attributes.id);
				}
			
			if(typeof(onCaseLibraryIncidentTypeChanged) != 'undefined'){
				onCaseLibraryIncidentTypeChanged.call(null,node.attributes.id);
			}
			lastSelectedNode=node;
		}
		$.addClick(tree,afterChangNode);
		<s:if test='null!=#parameters.incidentTypeTree[0] && !"".equals(#parameters.incidentTypeTree[0])'>
		$.setTreeValue(<s:property value="#parameters.incidentTypeTree[0]"/>,tree);
		</s:if>
		

	});
   */


</script>