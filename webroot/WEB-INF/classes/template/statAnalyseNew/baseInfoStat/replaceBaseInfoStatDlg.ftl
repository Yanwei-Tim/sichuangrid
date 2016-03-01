<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>

<div id="search-condition-form" title="统计分析基础信息重新生成" class="container container_24">
<form id="maintainForm" method="post" action="">
   		<div>
			<div class="grid_8 lable-right">
				<label class="form-lbl">开始时间：</label>
			</div>
			<div class="grid_16 form-left">
		    	<select name="replaceYear" id="replaceYear">
		    		<option value="0" selected="selected"></option>
		    		<@s.iterator begin="minYear" end="maxYear" var="newMinYear">
		    			<option value="${newMinYear }">${newMinYear }</option>
		    		</@s.iterator>
		    	</select>年
		    	<select name="replaceMonth" id="replaceMonth" >
		    		<option value="0" selected="selected"></option>	
		    		<@s.iterator begin="1" end="12" var="newMinMonth">
		    			<option value="${newMinMonth }">${newMinMonth }</option>
		    		</@s.iterator>
		    	</select>月
			</div>
	</div>
  </form>
</div>