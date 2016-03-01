<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<@s.include value="${path}/baseinfo/commonPopulation/jsFunctionInclude.jsp">
	<@s.param name="moduleName">InternetBarTemp</@s.param>
	<@s.param name="moduleNameTemp">InternetBarTemp</@s.param>
</@s.include>
<script type="text/javascript">
<@pop.formatterProperty name="attentionExtent" domainName="@com.tianque.domain.property.PropertyTypes@ATTENTION_EXTENT" />

	var gridOption={
			colModel:[
	          {name:"id", index:"id",hidden:true,sortable:false, frozen :true},
	      	  {name:"operator", index:'id',label:'操作',formatter:operateFormatterClaim,width:80,frozen:true,sortable:false,align:'center' },
	          {name:"organization.orgName", index:"orgInternalCode", width:200,label:"所属网格",sortable:false,hidden:true},
	      	  {name:"attentionExtent",index:"attentionExtent",label:"关注程度",sortable:true,width:100,formatter:attentionExtentColor,frozen:true},
	          //{name:"placeName-Font",index:"placeName",label:"单位名称",width:200,sortable:true,frozen:true,formatter:nameFont},
	          {name:"name",index:"name",label:"单位名称",width:100,sortable:true,formatter:nameFontClaim},
	          {name:"address",index:"address",label:"单位地址",width:200,sortable:true,frozen:true,formatter:placeAddressColorFormatter},
	          {name:"manager",index:"manager",label:"负责人",sortable:true,width:100,hidden:false},
	          {name:"stationPolice",index:"stationPolice",label:"所在地派出所名称",sortable:true,width:200},
	          {name:"totalComputerNum",index:"totalComputerNum",label:"电脑台数",sortable:true,width:80},
	          
			  //{name:'sourcesState',index:'sourcesState',label:"数据来源",sortable:false,hidden:true,formatter:sourceStateFormatter,width:80,align:'center'},
	          //{name:'updateDate', sortable:true, label:'数据更新时间', width:140},
	          {name:"mobile",index:"mobile",label:"联系电话",width:100,sortable:true,hidden:true},
	          {name:"netCultureLicenceNo",index:"netCultureLicenceNo",label:"网络文化经营许可证号",sortable:true,width:150,hidden:true},
	          {name:"netSecurityAuditNo",index:"netSecurityAuditNo",label:"网络安全审核意见书号",sortable:true,width:150,hidden:true},
	          {name:"fireAuditDocumentNo",index:"fireAuditDocumentNo",label:"消防审核意见书号",sortable:true,width:150,hidden:true},
	          {name:"netAccessProviders",index:"netAccessProviders",label:"宽带接入服务商",sortable:true,width:110,hidden:true},
	          {name:"internetAccessType",index:"internetAccessType",label:"接入方式",sortable:true,width:100,hidden:true},
	          {name:"useIP", index:"useIP", width:200,label:"现使用IP",sortable:true,hidden:true},
	          {name:"barCode", index:"barCode", width:100,label:"网吧编号",sortable:true,hidden:true},
	          {name:"operationArea",index:"operationArea",label:"营业面积(<font size='2'>m</font><font size='1'><sup>2</sup></font>)",sortable:true,width:110,hidden:true},
	          {name:"tempNetCardNum",index:"tempNetCardNum",label:"临时上网卡数量",sortable:true,width:110,hidden:true},
	          //{name:"logOutReason",index:"logOutReason",label:"注销原因",sortable:true,width:100,hidden:true},
	          //{name:"logOutTime",index:"logOutTime",label:"注销时间",sortable:true,width:100,hidden:true,align:'center'},
	          {name:"isEmphasis",sortable:false,label:"是否关注",hidden:true,hidedlg:true,width:100},
	          {name:"remark",index:"remark",label:"备注",sortable:true,width:200,hidden:true}
				]
			};
	var dialogWidth=800;
	var dialogHeight=600;

	function placeAddressColorFormatter(el,options,rowData){
	if(rowData.address !=null && rowData.address !="null"){
		if(rowData.isEmphasis==true||rowData.isEmphasis=='true'){
			return "<font color='#778899'>"+rowData.address+"</font>";
		}
		return "<font color='#000'>"+rowData.address+"</font>";
	}else{
		return "";
	}
	}

</script>
<#assign moduleName="InternetBarTemp"/>
<#assign bigType="location"/>
<#assign  importStartRow=6/>
<#assign moduleCName="网吧"/>
<#assign isNew=1/>
<#include "${path}/dataManage/common/publicFrame.ftl"/>

