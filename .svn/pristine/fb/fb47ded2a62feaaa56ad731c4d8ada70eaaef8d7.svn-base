<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<@s.include value="${path}/baseinfo/commonPopulation/jsFunctionInclude.jsp">
	<@s.param name="moduleName">PublicPlaceTemp</@s.param>
	<@s.param name="moduleNameTemp">PublicPlaceTemp</@s.param>
</@s.include>

<script type="text/javascript">
<@pop.formatterProperty name="cloakroom" domainName="@com.tianque.domain.property.PropertyTypes@PUBLICPLACE_CLOAKROOM" />
<@pop.formatterProperty name="attentionExtent" domainName="@com.tianque.domain.property.PropertyTypes@ATTENTION_EXTENT" />
	var gridOption={
			colModel:[
	          {name:"id", index:"id",sortable:false,hidden:true, frozen :true},
	      	  {name:"operator", index:'id',label:'操作',formatter:operateFormatterClaim,width:80,frozen:true,sortable:false,align:'center' },
	          {name:"organization.orgName", index:"orgInternalCode", width:200,label:"所属网格",sortable:false,hidden:true},
	      	  {name:"attentionExtent",index:"attentionExtent",label:"关注程度",sortable:true,width:100,formatter:attentionExtentColor,frozen:true},
	          //{name:"placeName-Font",index:"placeName",label:"场所名称",width:200,sortable:true,frozen:true,formatter:nameFontClaim},
	          {name:"name",index:"name",label:"场所名称",width:100,sortable:true,frozen:true,formatter:nameFontClaim},
	          {name:"address",index:"address",label:"场所地址",width:200,sortable:true,frozen:true,formatter:placeAddressColorFormatter},
	          {name:"manager",index:"manager",label:"负责人",sortable:true,width:100,hidden:false},
	          {name:"category",index:"category",label:"公共场所类别",sortable:true,width:100},
	          {name:"registrationNumber",index:"registrationNumber",label:"备案登记号码",sortable:true,width:100,hidden:true},
	          {name:"openingDate",index:"licenseDate",label:"开业时间",sortable:true,width:100,align:'center'},
	          {name:"licenseDate",index:"licenseDate",label:"领取执照时间",width:100,sortable:true,hidden:true,align:'center'},
	          {name:"placeLevel",index:"placeLevel",label:"场所等级",sortable:true,width:100,hidden:true},
	          {name:"buildingStructure",index:"buildingStructure",label:"建筑物结构",sortable:true,width:100,hidden:true},
	          {name:"totalArea",index:"totalArea",label:"总面积(<font size='2'>m</font><font size='1'><sup>2</sup></font>)",sortable:true,width:100,hidden:true},
	          {name:"operationArea",index:"operationArea",label:"营业面积(<font size='2'>m</font><font size='1'><sup>2</sup></font>)",sortable:true,width:110,hidden:true},
	          {name:"cloakroom",index:"cloakroom",formatter:cloakroomFormatter,label:"小件寄存处",sortable:true,width:100,hidden:true,align:'center'},
	          {name:"vouchedPeopleCount",index:"vouchedPeopleCount",label:"核定人数(人)",sortable:true,width:100,hidden:true},
	          {name:"privateRoomCount",index:"privateRoomCount",label:"包间数(间)",sortable:true,width:100,hidden:true},
	          {name:"surrounding",index:"surrounding",label:"周围环境",sortable:true,width:100,hidden:true},
	          {name:"passageway",index:"passageway",label:"通道口",sortable:true,width:100,hidden:true},
	          {name:"innerStructure",index:"innerStructure",label:"内部结构",sortable:true,width:100,hidden:true},
	          {name:"remark",index:"remark",label:"备注",sortable:true,width:200,hidden:true}
				]
			};
	var dialogWidth=800;
	var dialogHeight=600;

	function placeAddressColorFormatter(el,options,rowData){
		if(rowData.isEmphasis==true||rowData.isEmphasis=='true'){
			return "<font color='#778899'>"+rowData.address+"</font>";
		}
		return "<font color='#000'>"+rowData.address+"</font>";
	}
	function getLocationName(rowData){
		return rowData.name;
	}
</script>
<#assign moduleName="PublicPlaceTemp"/>
<#assign bigType="location"/>
<#assign  importStartRow=6/>
<#assign moduleCName="公共场所"/>
<#assign isNew=1/>
<#include "${path}/dataManage/common/publicFrame.ftl"/>