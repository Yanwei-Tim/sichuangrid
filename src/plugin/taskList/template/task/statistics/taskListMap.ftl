<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>

<div style="margin-top:10px;" id="taskTypeMap">
	任务类别： 
</div>
<div>
<@s.include value="/taskListDemo/taskListMapDemo.jsp" />
</div>