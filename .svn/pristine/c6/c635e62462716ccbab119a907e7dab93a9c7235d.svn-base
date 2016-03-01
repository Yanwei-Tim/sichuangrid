<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<@s.include value="${path}/baseinfo/commonPopulation/jsFunctionInclude.jsp">
	<@s.param name="moduleName">ElderlyPeopleTemp</@s.param>
</@s.include>

<script type="text/javascript">
<@pop.formatterProperty name="maritalState" domainName="@com.tianque.domain.property.PropertyTypes@MARITAL_STATUS" />
<@pop.formatterProperty name="healthState" domainName="@com.tianque.domain.property.PropertyTypes@HEALTH_STATE" />
<@pop.formatterProperty name="peopleType" domainName="@com.tianque.domain.property.PropertyTypes@SPECIAL_PERSON" />
<@pop.formatterProperty name="currentStatus" domainName="@com.tianque.domain.property.PropertyTypes@CURRENT_STATUS" />
<@pop.formatterProperty name="liveStatus" domainName="@com.tianque.domain.property.PropertyTypes@LIVE_STATUS" />
<@pop.formatterProperty name="spouseStatus" domainName="@com.tianque.domain.property.PropertyTypes@SPOUSE_STATUS" />
<@pop.formatterProperty name="incomeSource" domainName="@com.tianque.domain.property.PropertyTypes@INCOME_SOURCE" />



var gridOptionPrivate = {
	colModel:[
        {name:'elderPeopleId', sortable:true, label:'老年人证号',hidden:true, width:100},
        {name:'peopleType',formatter:peopleTypeFormatter, sortable:true, label:'人员类型', align:'center', width:100,hidden:false},
        {name:'currentStatus',formatter:currentStatusFormatter, sortable:true, label:'目前状况', width:100,hidden:true},
        
        {name:'liveStatus', formatter:liveStatusFormatter, sortable:true, label:'居住情况', width:100,hidden:true},
        {name:'spouseStatus',formatter:spouseStatusFormatter,  sortable:true, label:'配偶情况', width:80,hidden:true},
        {name:'incomeSource',formatter:incomeSourceFormatter,  sortable:true, label:'经济来源',width:80,hidden:true},
        {name:'enterWorkDate', sortable:true, label:'参加工作日期', align:'center', width:100,hidden:true},
        {name:'retireUnitAndDuty', sortable:true, label:'离退休单位', width:100,hidden:true},
        
        {name:'retireDate', sortable:true, label:'离退休日期', align:'center', hidden:true, width:100},
        {name:'zhiwu', sortable:true, label:'单位职务', width:100,hidden:true},
        {name:'pension', sortable:true, label:'退休金(元)',hidden:true, width:100}
	]
};
var dialogWidth=800;
var dialogHeight=640;
var importStartRow = 6;
</script>
<#assign  moduleName="ElderlyPeopleTemp"/>
<#assign  moduleCName="老年人"/>
<#include "${path}/dataManage/population/common/countrymen/dataPublicList.ftl"/>
