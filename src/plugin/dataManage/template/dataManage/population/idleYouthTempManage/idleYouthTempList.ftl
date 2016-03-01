<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<@s.include value="${path}/baseinfo/commonPopulation/jsFunctionInclude.jsp">
	<@s.param name="moduleName">IdleYouthTemp</@s.param>
</@s.include>
<script type="text/javascript">
function stayInSchoolFormatter(el, options, rowData){
	if(true==rowData.stayInSchool){
		return "是";
	}else{
		return "否";
	}
}
var gridOptionPrivate = {
		colModel:[
			{name:'stayInSchool',label:'是否在校住宿',sortable:true, width:80, align:'center', hidden:true, formatter:stayInSchoolFormatter},
			{name:'schoolName',label:'学校名称',sortable:true, width:180, hidden:true},
			{name:'workCondition', label:'工作情况', width:180, hidden:true}
		]
	};

var dialogWidth=800;
var dialogHeight=640;
var importStartRow = 6;
</script>
<#assign  moduleName="IdleYouthTemp"/>
<#assign  moduleCName="重点青少年"/>
<#include "${path}/dataManage/population/common/countrymen/dataPublicList.ftl"/>