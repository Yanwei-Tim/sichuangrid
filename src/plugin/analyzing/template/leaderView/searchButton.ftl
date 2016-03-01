<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>

<div class="btnbanner btnbannerData" >	
	<@s.include value="/common/orgSelectedComponent.jsp"/>
	<div class="ui-widget autosearch">
		<input class="basic-input" type="text" value="请输入姓名或身份证号码" id="searchByCondition" maxlength="18" class="searchtxt" />
		<button id="refreshOrgTree1" class="ui-icon ui-icon-refresh searchbtnico"></button>
	</div>
    <span class="lineBetween"></span>
	<div class="btnlist">
		<a class="cur" href="javascript:;" id="searchByIdcardA"><span>搜索</span></a>
		<a href="javascript:;" id="searchHighA"><span>高级搜索</span></a>
	</div>
</div>

<script type="text/javascript">
$(function(){
	$.showTxtValue('#searchByCondition');
})
</script>