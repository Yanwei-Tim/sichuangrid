<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>

<div id="search-condition-form" title="事件处理状态统计查询"
	class="container container_24">
	<form id="maintainForm" method="post" action="">
		<div>
			<div class="grid_4 lable-right">
				<label class="form-lbl">时间：</label>
			</div>
			<div class="grid_8 form-left">
				<select name="year" id="year">
					<option value="0"></option>
					<@s.iterator begin="minYear" end="maxYear" var="newMinYear">
						<option value="${newMinYear }">${newMinYear }</option>
					</@s.iterator>
				</select>年 <select name="month" id="month">
					<option value="0"></option>
					<@s.iterator begin="1" end="12" var="newMinMonth">
						<option value="${newMinMonth }">${newMinMonth }</option>
					</@s.iterator>
				</select>月
			</div>
			<div class='clearLine'></div>
		</div>
	</form>
</div>