var settings = {
	async : {
		enable : true,
		url : PATH+"/baseInfo/gridConfigTaskManage/getAuthorizedOraganiztionTreeNodelist.action?organization.id="+$("#orgId").val()+"&mode="+$("#mode").val()+"&type="+$("#type").val(),
		autoParam : ["id=pid"],
		otherParam : ["checkedIds",$("#" + $("#orgIdFieldId").val()).val()]
	},
	callback : {
		beforeCheck : zTreeBeforeCheck,
		onAsyncSuccess : zTreeOnAsyncSuccess,
		onAsyncError : zTreeOnAsyncError,
		onCheck : zTreeOnCheck
	},
	check : {
		enable : true,
		chkboxType : { "Y": "s", "N": "p" }
	},
	data : {
		simpleData : {
			enable : true
		}
	},
	view : {
		selectedMulti : true
	}
};

function zTreeOnCheck(event, treeId, treeNode) {
	var orgNameFieldId = $("#orgNameFieldId").val();
	if (orgNameFieldId == null) {
		return;
	}
	var orgIdFieldId = $("#orgIdFieldId").val();
	if (orgIdFieldId == null) {
		return;
	}
	var strname = "";
	var strid = "";
	var treeObj = $.fn.zTree.getZTreeObj("ulOrgTreeNodeList");
	var nodes = treeObj.getCheckedNodes(true);
	for (var i = 0; i < nodes.length; i++) {
		var node = nodes[i];
		if (i == 0) {
			strname = node.name;
			strid = node.id
		} else {
			strname += "," + node.name;
			strid += "," + node.id;
		}
	}
	$("#" + orgNameFieldId).val(strname);
	$("#" + orgIdFieldId).val(strid);
}
//选中节点前判断如果没选中的话展开所有节点,如果选中了的话取消所有子节点的选中
function zTreeBeforeCheck(treeId, treeNode) {
	var treeObj = $.fn.zTree.getZTreeObj("ulOrgTreeNodeList");
	if(treeNode.checked==false){
		treeObj.expandNode(treeNode, true, false, false);
		//选中所有父节点
		checkParentNodes(treeNode);
	}else{
		//取消节点的所有子节点的选中
		cancelAllChildSelected(treeNode,treeObj);
	}
}
//选中所有父节点
function checkParentNodes(treeNode){
	var parentNode=treeNode.getParentNode();
	if(parentNode!=undefined&&parentNode!=null){
		parentNode.checked=true;
		checkParentNodes(parentNode);
	}
}

//取消节点的所有子节点的选中
function cancelAllChildSelected(treeNode,treeObj){
	var childs=treeNode.children;
	if(childs!=undefined&&childs!=null&&childs.length>0){
		for(var i=0;i<childs.length;i++){
			treeObj.checkNode(childs[i], false, false); 
			cancelAllChildSelected(childs[i],treeObj);
		}
	}
}
function zTreeOnAsyncSuccess(event, treeId, treeNode, msg) {
	var treeObj = $.fn.zTree.getZTreeObj("ulOrgTreeNodeList");
	if (treeNode != null && treeNode.checked) {
		var nodes = treeObj.getNodesByParam("pId", treeNode.id, null);
		for (var i = 0; i < nodes.length; i++) {
			treeObj.checkNode(nodes[i], true, false, true);
		}
	}
	if($("#mode").val()=='view'){
		nodes = treeObj.transformToArray(treeObj.getNodes());
		for(var i=0;i<nodes.length;i++){
			treeObj.setChkDisabled(nodes[i], true, false, false);
		}
	}
	if($("#mode").val()=="update"){
		zTreeOnCheck(null,null,null);
	}
}

function zTreeOnAsyncError() {
	alert("数据获取出错，请检查链路！");
}
