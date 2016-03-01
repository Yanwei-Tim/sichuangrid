<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<@s.include value="${path}/baseinfo/commonPopulation/jsFunctionInclude.jsp">
	<@s.param name="moduleName">PositiveInfoTemp</@s.param>
</@s.include>
<script type="text/javascript">
<@pop.formatterProperty name="agoProfession" domainName="职业" />
<@pop.formatterProperty name="positiveInfoType" domainName="@com.tianque.domain.property.PropertyTypes@POSITIVEINFO" />


function rendIsRepeat(el, options, rowData){
	if(1==rowData.isRepeat){
		return "是";
	}else{
		return "否";
	}
}

var dialogWidth=800;
var dialogHeight=650;
var gridOptionPrivate = {
  	colModel:[
		{name:'imprisonmentDate',label:'原判刑期',index:'imprisonmentDate',width:150,hidden:true,sortable:true},
		{name:'isRepeat',index:'isRepeat',label:'是否累犯',hidden:true,formatter : rendIsRepeat,align:"center",width:90,sortable:true},
		{name:'releaseOrBackDate',index:'releaseOrBackDate',label:'解教（刑释）日期',align:"center",width:140,sortable:true},
		{name:"positiveInfoType",label:'刑释解教类型',index:'positiveInfoType',align:"center",formatter:positiveInfoTypeFormatter,hidden:true,sortable:true},
		{name:'resettlementDate',index:'resettlementDate',label:'安置日期',align:"center",width:90,hidden:true,sortable:true},
		{name:'caseReason',label:'案由',index:'caseReason',sortable:true,width:120,hidden:true},
		{name:'laborEduAddress',label:'劳教(服刑)场所',index:'laborEduAddress',sortable:true,width:130,hidden:true},
		{name:'noResettlementReason',label:'未安置原因',index:'noResettlementReason',width:150,sortable:true,hidden:true},
		//{name:'agoProfession',label:'原职业',sortable:true,formatter : agoProfessionFormatter,width:80,hidden:true},
		{name:'nativePoliceStation',label:'户籍派出所',sortable:true,index:'nativePoliceStation',width:120,hidden:true},
		{name:"isExpired",index:'isExpired',label:'是否过期',align:"center",hidden:true,hidedlg:true,width:90,sortable:true}
  	]
}
</script>
<#assign  moduleName="PositiveInfoTemp"/>
<#assign  moduleCName="刑释人员"/>
<#include "${path}/dataManage/population/common/countrymen/dataPublicList.ftl"/>
