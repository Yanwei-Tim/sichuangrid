<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<@s.include value="${path}/baseinfo/commonPopulation/jsFunctionInclude.jsp">
	<@s.param name="moduleName">OverseaPersonnelTemp</@s.param>
</@s.include>
<script type="text/javascript">
<@pop.formatterProperty name="gender" domainName="@com.tianque.domain.property.PropertyTypes@GENDER" />
<@pop.formatterProperty name="certificateType" domainName="@com.tianque.domain.property.PropertyTypes@CERTIFICATE_TYPE" />
<@pop.formatterProperty name="profession" domainName="@com.tianque.domain.property.PropertyTypes@PROFESSION_TYPE" />
	function englishNameFont(el,options,rowData){
		if(null == rowData.englishName) {
			return "&nbsp;&nbsp;"
		}else if(rowData.death == true || rowData.death == "true"){
			return '<a href="javascript:;" <@pop.JugePermissionTag ename="importViewOverseaPersonnelTemp"> onclick="viewDialog(event,'+rowData.id+')" </@pop.JugePermissionTag> ><font color="red">'+rowData.englishName+'</font></a>';
		}else if(rowData.logOut==1||rowData.logOut=='1'){
			return '<a href="javascript:;"  <@pop.JugePermissionTag ename="importViewOverseaPersonnelTemp"> onclick="viewDialog(event,'+rowData.id+')" </@pop.JugePermissionTag> ><font color="#778899">'+rowData.englishName+'</font></a>';
		}
		return '<a href="javascript:;" <@pop.JugePermissionTag ename="importViewOverseaPersonnelTemp"> onclick="viewDialog(event,'+rowData.id+')" </@pop.JugePermissionTag>   ><font color="#6633FF">'+rowData.englishName+'</font></a>';
	}

var gridOption = {
	colModel:[
		 {name:"id",index:"id",hidden:true,sortable:false,frozen:true},
	        {name:"操作",index:'id',width:90,formatter:operateFormatterClaim,sortable:false,align:"center"},
	        {name:'organization.orgName',index:'orgInternalCode',label:'所属网格',width:150,sortable:false,hidden:true},
	        {name:"englishName",index:'englishName',label:'英文名',width:80,formatter:englishNameFont,sortable:true},
	        {name:"gender",label:'性别',width:50,align:'center',sortable:true,formatter : genderFormatter },
	        {name:'certificateType',label:'证件种类',width:100,sortable:true,formatter : certificateTypeFormatter},
	        {name:'certificateNo',label:'证件号码',sortable:true,width:160},
	        {name:'name',label:'姓名',width:80,sortable:true,hidden:true},
	        //{name:'currentAddress',label:'常住地址',sortable:true,width:200},
	        {name:"nationality",label:'国籍',sortable:true,width:120},
	        {name:'birthday', sortable:true, label:'出生日期',align:'center', width:90,hidden:true},
	   		{name:'telephone',label:'固定电话',sortable:true,align:'center', width:120,hidden:true},
	   		{name:'mobileNumber',label:'联系手机',index:"mobileNumber",align:'center',sortable:true, width:100,hidden:true},
	   		{name:'workUnit',label:'工作单位或就读学校',sortable:true, width:200,hidden:true},
      		{name:'arrivalTime',label:'抵达时间',sortable:true,align:'center', width:90,hidden:true},
      		{name:'leaveTime',label:'离开时间',sortable:true,align:'center', width:90,hidden:true},
      		{name:'profession',label:'职业',sortable:true, width:80,hidden:true,align:'center',formatter : professionFormatter},
	        {name:"logOut",sortable:false,hidden:true,hidedlg:true,width:100}
	]
};
var dialogWidth=800;
var dialogHeight=640;
</script>
<#assign  moduleName="OverseaPersonnelTemp"/>
<#assign  moduleCName="境外人员"/>
<#assign  bigType="population"/>
<#assign  importStartRow=6 />
<#include "${path}/dataManage/common/publicFrame.ftl"/>