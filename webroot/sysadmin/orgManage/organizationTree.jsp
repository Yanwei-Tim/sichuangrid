<%@ page language="java" pageEncoding="UTF-8"%>
<jsp:include page="/includes/baseInclude.jsp" />
<style>
ul#refreshOrgTree {margin: 0; padding: 0;}
ul#refreshOrgTree li {margin: 2px; position: relative; padding: 4px 0; cursor: pointer; float: left;  list-style: none;}
ul#refreshOrgTree span.ui-icon {float: left; margin: 0 4px;}
</style>
<div id="treePanel" >
	<div id="refreshOrgTreeDiv">
		<div style="float: left;"><input name="" type="text" id="org-tree-autocomplete"/></div>
		<div style="float: left;">
			<ul id="refreshOrgTree" class="ui-widget ui-helper-clearfix"> 
				<li class="ui-state-default ui-corner-all" title="重置树"><span class="ui-icon ui-icon-refresh"></span></li>
			</ul>
		</div>
	</div>
	<div style="clear: both;">
		<div id="orgTree"></div>
	</div>
</div>
<script type="text/javascript">



</script>

