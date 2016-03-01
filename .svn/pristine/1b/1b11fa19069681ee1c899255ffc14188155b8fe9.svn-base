<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<@s.include value="${path}/baseinfo/commonPopulation/jsFunctionInclude.jsp">
	<@s.param name="moduleName">${parameters.moduleName[1]!}</@s.param>
</@s.include>
<script type="text/javascript">                 
<@pop.formatterProperty name="type" domainName="@com.tianque.domain.property.PropertyTypes@COMPANY_PLACE_TYPE" />
<@pop.formatterProperty name="attentionextent" domainName="@com.tianque.domain.property.PropertyTypes@ATTENTION_EXTENT" />

function isRiskEnterprise(el, options, rowData){
	if(true == rowData.riskEnterprise){
		return "是";
	}else{
		return "否";
	}
}
var gridOption = {
		colModel:[
          {name:"id",index:'id',sortable:false,hidden:true},
		  {name:"operator", index:'id',label:'操作',formatter:operateFormatterClaim,width:80,frozen:true,sortable:false,align:'center' },
      	  {name:"organization.orgName",index:'orgInternalCode',label:'所属网格',sortable:false,width:150, hidden:true},
		  {name:"attentionextent",index:"attentionextent",label:"关注程度",sortable:true,width:100,formatter:attentionextentColor,frozen:true},
      	  {name:"name-Font",index:'name',label:'名称',frozen:true,sortable:true,formatter:nameFontClaim,width: 200},
      	  {name:"name",index:'name',label:'名称',frozen:true,sortable:true,hidden:true,width:150},
      	  {name:"address",index:'address',sortable:true,label:'地址',width:220},
      	  {name:"type",index:'type',label:'类型',hidden:true,sortable:true,width:90,formatter:typeFormatter},
		  
      	  {name:"userName",index:'userName',label:'负责人',sortable:true,hidden:true,width:120},
      	  {name:"mobileNumber",index:'mobileNumber',label:'联系手机',hidden:true,sortable:true,width:110,align:'center'},
      	  {name:"telePhone",index:'telePhone',label:'联系固话',hidden:true,sortable:true,width:140,align:'center'},
      	  {name:"isEmphasis",label:'是否关注',sortable:false,hidden:true,hidedlg:true,width:100,align:'center'}
		 ]
};
var dialogWidth=800;
var dialogHeight=600;
</script>

<#assign moduleName="${parameters.moduleName[1]!}"/>
<#assign module="${parameters.module[0]!}"/>
<#assign bigType="location"/>
<#if "NewConstructionPlaceTemp"==moduleName>
  <#assign  importStartRow=6/>
<#else>
  <#assign  importStartRow=5/>
</#if>
<#assign isNew=1/>
<#include "${path}/dataManage/common/publicFrameNew.ftl"/>