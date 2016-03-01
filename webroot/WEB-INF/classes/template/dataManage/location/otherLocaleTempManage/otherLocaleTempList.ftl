<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<@s.include value="${path}/baseinfo/commonPopulation/jsFunctionInclude.jsp">
	<@s.param name="moduleName">OtherLocaleTemp</@s.param>
</@s.include>
<script type="text/javascript">
<@pop.formatterProperty name="type" domainName="@com.tianque.domain.property.PropertyTypes@OTHER_LOCALE_TYPE" />
<@pop.formatterProperty name="attentionExtent" domainName="@com.tianque.domain.property.PropertyTypes@ATTENTION_EXTENT" />

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
		  {name:"attentionExtent",index:"attentionExtent",label:"关注程度",sortable:true,width:100,formatter:attentionExtentColor,frozen:true},
      	  {name:"name-Font",index:'name',label:'名称',frozen:true,sortable:true,formatter:nameFontClaim,width: 200},
      	  {name:"name",index:'name',label:'名称',frozen:true,sortable:true,hidden:true,width:150},
      	  {name:"address",index:'address',sortable:true,label:'地址',width:220},
      	  {name:"type",index:'type',label:'场所类型',sortable:true,width:90,formatter:typeFormatter},
		  
      	  {name:"manager",index:'contactPerson',label:'联系人',sortable:true,hidden:true,width:120},
      	  {name:"mobileNumber",index:'mobileNumber',label:'联系手机',hidden:true,sortable:true,width:110,align:'center'},
      	  {name:"telephone",index:'telephone',label:'联系电话',hidden:true,sortable:true,width:140,align:'center'},
      	  {name:"isEmphasis",label:'是否关注',sortable:false,hidden:true,hidedlg:true,width:100,align:'center'}
		 ]
};
var dialogWidth=800;
var dialogHeight=600;
</script>
<#assign moduleName="OtherLocaleTemp" />
<#assign moduleCName="其他场所"/>
<#assign bigType="location"/>
<#assign  importStartRow=5/>
<#assign isNew=1/>
<#include "${path}/dataManage/common/publicFrame.ftl"/>
