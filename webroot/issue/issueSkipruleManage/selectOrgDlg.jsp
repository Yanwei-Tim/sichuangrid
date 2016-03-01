<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>

<div id="dialog-form" title="规则" class="container container_24">
		<div id="evaluate_tree_navigation" style="padding:10px 0 0 10px;"></div>
</div>
<script type="text/javascript">
$(document).ready(function(){
	var rootOrgId = "<s:property value='@com.tianque.core.util.ThreadVariable@getUser().getOrganization().getId()'/>";
	<c:if test='${parameters.rootOrgId[0]!=null && ""!=parameters.rootOrgId[0]}'>
		rootOrgId = "${parameters.rootOrgId[0]}";
	</c:if>
	var tree = $("#evaluate_tree_navigation").initTree({
		rootId:rootOrgId,
		url:'/sysadmin/orgManage/firstLoadExtTreeDataWithCheckedBox.action',
		nodeUrl:'/sysadmin/orgManage/ajaxOrgsForExtTreeWithCheckedBox.action'
	});
	
	tree.on('checkchange', function(node, flag) {
		var orgIds="",orgNames="";
		tree.getRootNode().cascade(function(node){
			if(node.attributes.checked){
				if(!orgIds){
					orgIds = node.id;
					orgNames = node.text;
				}else{
					orgIds += ","+node.id;
					orgNames += ","+node.text;
				}
			}
		});
		if(flag){
			$("#selectOrgNameHid").val(orgNames);
			$("#selectOrgIdHid").val(orgIds);
		}
// 获取所有子节点
//         node.cascade(function(node) {
//             node.attributes.checked = flag;
//             node.ui.checkbox.checked = flag;
// //            node.ui.toggleCheck(flag);
//             return true;
//         });
//         // 获取所有父节点
//         var pNode = node.parentNode;
//         for (; pNode.id != "root"; pNode = pNode.parentNode) {
//             if (flag || tree.getChecked(id, node.parentNode) == "") {
//                   pNode.ui.checkbox.checked = flag;
// //                pNode.ui.toggleCheck(flag);
// //                pNode.ui.checkbox.toggleCheck(flag);
//                 pNode.attributes.checked = flag;
//             }
//         }
    });
});

</script>
