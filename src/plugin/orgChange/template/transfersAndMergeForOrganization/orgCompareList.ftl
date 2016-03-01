<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<style>
	.ui-dialog .ui-dialog-content{overflow:hidden;}
	.tree-left{border-right: 1px solid #D6D6D6;}
	.tree-left,.tree-right{display:inline-block;*display:inline;*zoom:1;width:49%;height:500px;overflow:auto;}
</style>

<div class="tree-left">
	<div class="orgTree">
		<div id="orgTreeCompare">
		</div>
	</div>
</div>
<div class="tree-right">
	<div class="orgTree">
		<div id="orgAllTreeCompare">
		</div>
	</div>
</div>


<div class="center-right">
</div>
<script type="text/javascript" >
var rootOrgId=USER_ORG_ID;
var compareTree;
var compareAllTree;

$(function(){
	compareTree=$("#orgTreeCompare").initTree({rootId:rootOrgId,allOrg:true});
	compareAllTree=$("#orgAllTreeCompare").initAllOrgTree({rootId:rootOrgId,allOrg:true,url:"/sysadmin/orgManage/loadAllOrgTree.action",nodeUrl:"/sysadmin/orgManage/ajaxAllOrgsForExtTree.action"});
});	

</script>
