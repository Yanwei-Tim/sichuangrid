<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>

    <div id="actualHouse" class="container container_24">
		<div id=actualTabs>
			<ul>
				<li><a href="${path}/plugin/dataManage/${tempClassName}Manage/dispatch.action?mode=viewBaseData&id=${houseInfo.id}">基础信息</a> </li>
				<@s.if test='"rentalHouseTempDialog".equals(#parameters.dailogName[0])'>
					<li><a href="${path}/plugin/dataManage/${tempClassName}Manage/dispatch.action?mode=viewRentalData&id=${houseInfo.id}">出租信息</a></li>
				</@s.if>
			</ul>
   		</div>
  </div>
	<script>
	$(function() {
		$("#actualTabs").tabs();
	});
	</script>