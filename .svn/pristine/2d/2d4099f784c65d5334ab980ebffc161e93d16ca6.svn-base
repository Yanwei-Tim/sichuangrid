<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<jsp:include page="/baseinfo/commonPopulation/jsFunctionInclude.jsp">
	<jsp:param value="NurturesWomen" name="moduleName"/>
</jsp:include>
<%request.setAttribute("searchType",request.getParameter("searchType"));%>
<script type="text/javascript">
	<pop:formatterProperty name="gender" domainName="@com.tianque.domain.property.PropertyTypes@GENDER" />
	<pop:formatterProperty name="career" domainName="@com.tianque.domain.property.PropertyTypes@CAREER" />
	<pop:formatterProperty name="politicalBackground" domainName="@com.tianque.domain.property.PropertyTypes@POLITICAL_BACKGROUND" />
	<pop:formatterProperty name="maritalState" domainName="@com.tianque.domain.property.PropertyTypes@MARITAL_STATUS" />
	<pop:formatterProperty name="faith" domainName="@com.tianque.domain.property.PropertyTypes@FAITH" />
	<pop:formatterProperty name="residenceType" domainName="@com.tianque.domain.property.PropertyTypes@RESIDENCE_TYPE" />
	<pop:formatterProperty name="schooling" domainName="@com.tianque.domain.property.PropertyTypes@SCHOOLING" />
	<pop:formatterProperty name="healthState" domainName="@com.tianque.domain.property.PropertyTypes@HEALTH_STATE" />
	<pop:formatterProperty name="bloodType" domainName="@com.tianque.domain.property.PropertyTypes@BLOOD_TYPE" />
	<pop:formatterProperty name="nation" domainName="@com.tianque.domain.property.PropertyTypes@NATION" />
	<pop:formatterProperty name="currentAddressType" domainName="@com.tianque.domain.property.PropertyTypes@CURRENT_ADDRESS_TYPE"/>
	<pop:formatterProperty name="licenseSituation" domainName="@com.tianque.domain.property.PropertyTypes@LICENSE_SITUATION"/>
	<pop:formatterProperty name="onechildOfCouple" domainName="@com.tianque.domain.property.PropertyTypes@ONE_CHILD_SITUATION"/>
	<pop:formatterProperty name="attentionExtent" domainName="@com.tianque.domain.property.PropertyTypes@ATTENTION_EXTENT" />
	var gridOption = {
		colModel:[
	        {name:"id", index:"id", hidden:true,frozen:true},
	        {name:"encryptId",index:"id",sortable:false,hidden:true,frozen:true},
	    	{name:"organization.id", index:"organization.id", hidden:true,hidedlg:true},
	        {name:"operate", index:"operate",label:"操作",width:50,formatter:operateFormatter,frozen : true},
	        {name:"attentionExtent",index:"attentionExtent",label:"关注程度",sortable:true,width:100,formatter:attentionExtentColor,forzen:true},
	        {name:"name", index:'name', label:'姓名',formatter:nameFont,width:80,frozen:true },
	        {name:'gender',formatter:genderFormatter, label:'性别', align:'center', width:50,hidden:true},
	        {name:'idCardNo',index:'idCardNo', label:'身份证号码',  width:130,frozen:true},
	        {name:"usedName", index:'usedName', label:'曾用名/别名', width:80 ,hidden:true},
	        {name:'currentAddress', sortable:false, label:'常住地址', width:150 },
	        {name:'boyNumber',sortable:false,label:'子女数',align:'center',formatter:childrenFormatter,width:80},
	        {name:'marriageRegistrationTime', sortable:false,label:'离婚时间',width:80},
	        
	        {name:'hasServiceTeamMember',label:'有无服务成员',index:'hasServiceTeamMember',width:100,sortable:false,align:'center',formatter:hasServiceTeamMemberFormatter},
			{name:'lastRecordTime',label:'最新服务时间',index:'hasServiceTeamRecord',sortable:false,align:'center',width:100},
			{name:'sourcesState',index:'sourcesState',label:"数据来源",sortable:false,hidden:true,formatter:sourceStateFormatter,width:80,align:'center'},
	        {name:'updateDate', sortable:false, label:'数据更新时间', width:140},
	        {name:'organization.orgName',sortable:false,index:'organization.orgName',label:'所属网格',width:320,hidden:true},
	        {name:'mobileNumber', sortable:false, label:'联系手机', width:80,hidden:true},
	        {name:'telephone', sortable:false, label:'固定电话', width:80,hidden:true},
	        {name:"birthday", index:'birthday', label:'出生日期', width:80 ,hidden:true},
	        {name:"nation",sortable:false,label:"民族",formatter:nationFormatter,width:80, hidden:true },
	        {name:"politicalBackground",sortable:false,label:"政治面貌",formatter:politicalBackgroundFormatter,width:80,hidden:true},	        
	        {name:'career', sortable:false,formatter:careerFormatter,label:'职业', width:80,hidden:true},
	        {name:'workUnit', sortable:false, label:'工作单位或就读学校', width:100, hidden:true },
	        {name:"stature",sortable:false,label:"身高(cm)",width:80, hidden:true },
	        {name:"bloodType",sortable:false,label:"血型",formatter:bloodTypeFormatter,width:80, hidden:true },
	        {name:"faith",sortable:false,label:"宗教信仰",formatter:faithFormatter,width:80, hidden:true },
	        {name:"email",sortable:false,label:"电子邮箱",width:80, hidden:true },
	        {name:'province',index:'province',sortable:false,label:'户籍地',formatter:householdRegisterFormatter,width:150,hidden:true},
	        {name:'nativePlaceAddress', sortable:false, label:'户籍地详址', width:150,hidden:true},
	        {name:"schooling",sortable:false,label:"育妇文化程度",formatter:schoolingFormatter,width:80,hidden:true},
	        {name:"maritalState",sortable:false,label:"育妇婚姻状况",formatter:maritalStateFormatter,width:80,hidden:true},
	        {name:'otherAddress', sortable:false, label:'临时居所', width:150,hidden:true},
	        {name:'firstMarriageTime', sortable:false,label:'初婚时间',width:80,hidden:true},
	        {name:'licenseSituation',sortable:false,label:'领证情况',formatter:licenseSituationFormatter,width:80,hidden:true},
	        {name:'marriageOrDivorceTime',sortable:false,label:'最近再婚时间',width:80,hidden:true},
	        {name:'fertilityServicesNo',sortable:false,label:'生育服务证',width:80,hidden:true},
	        {name:'licenseTime',sortable:false,label:'领证时间',width:80,hidden:true},
	        {name:'contraceptiveMethod',sortable:false,label:'避孕方法',width:80,hidden:true},
	        {name:'contraceptiveTime',sortable:false,label:'避孕起始时间',width:80,hidden:true},
	        {name:'onechildOfCouple',sortable:false,label:'夫妻双方独生子女情况',formatter:onechildOfCoupleFormatter,width:80,hidden:true},
	        {name:'manIdcardNo',sortable:false,label:'男方身份证号码',width:80,hidden:true},
	        {name:'manName',sortable:false,label:'男方姓名',width:80,hidden:true},
	        {name:'manMaritalState',sortable:false,label:'男方婚姻状况',formatter:maritalStateFormatter,width:80,hidden:true},
	        {name:'manFirstMarriageTime',sortable:false,label:'男方初婚时间',width:80,hidden:true},
	        {name:'manMobileNumber',sortable:false,label:'男方联系手机',width:80,hidden:true},
	        {name:'manTelephone',sortable:false,label:'男方固定电话',width:80,hidden:true},
	        {name:'manBirthday',sortable:false,label:'男方出生日期',width:80,hidden:true},
	        {name:'manNation',sortable:false,label:'男方民族',formatter:nationFormatter,width:80,hidden:true},
	        {name:'manPoliticalBackground',sortable:false,label:'男方政治面貌',formatter:politicalBackgroundFormatter,width:80,hidden:true},
	        {name:'manSchooling',sortable:false,label:'男方文化程度',formatter:schoolingFormatter,width:80,hidden:true},
	        {name:'manCareer',sortable:false,label:'男方职业',formatter:careerFormatter,width:80,hidden:true},
	        {name:'manWorkUnit',sortable:false,label:'男方工作单位或就读学校',width:80,hidden:true},
	        {name:'manProvince',index:'manProvince',sortable:false,label:'男方户籍地',formatter:manNativePlaceFormatter,width:150,hidden:true},
	        {name:'manNativeplaceAddress', sortable:false, label:'男方户籍地详址', width:150,hidden:true},
	        {name:'manCurrentAddress',sortable:false,label:'男方常住地址',width:80,hidden:true},
	        {name:'death',sortable:false,hidden:true,hidedlg:true,width:80},
	        {name:'isEmphasis',sortable:false,hidden:true,hidedlg:true,width:80}
    	]
	};
	var dialogWidth=800;
	var dialogHeight=640;
	function manNativePlaceFormatter(el, options, rowData){
		var str = "";
		if(rowData.manProvince != null)
			str += rowData.manProvince;
		if(rowData.manCity != null)
			str += rowData.manCity;
		if(rowData.manDistrict != null)
			str += rowData.manDistrict;
		return str;
	}

	function childrenFormatter(el, options, rowData){
		var str =0;
		str=rowData.boyNumber+rowData.girlNumber;
		return str;
	}

	function operateFormatter(el,options,rowData){
		return "<pop:JugePermissionTag ename='viewNurturesWomen'><a href='javascript:;' onclick='viewDialog(event,"+rowData.id+");'><U><font color='#6633FF'>查看</font></U></a></pop:JugePermissionTag>";
	}



	function isEmphasisFormatter(){
		var idCollection = new Array();
		idCollection=$("#nurturesWomenList").getDataIDs();
			for(var i=0;i<idCollection.length;i++){
				var ent =  $("#nurturesWomenList").getRowData(idCollection[i]);
				if(ent.isEmphasis==1||ent.isEmphasis=='1'){
					$("#"+idCollection[i]+"").css('color','#778899');
				}
		}
	}


	
</script>
<jsp:include page="/baseinfo/commonPopulation/commonStatisticPopulationList.jsp">
	<jsp:param value="NurturesWomen" name="moduleName"/>
	<jsp:param value="孕妇" name="moduleCName"/>
	<jsp:param value="${searchType}" name="searchType"/>
	<jsp:param value="服务人员" name="supervisorPerson" />
</jsp:include>
<div style="display:none;">
	<input type="hidden" id="_populationType_" name="population.populationType" value='<s:property value="@com.tianque.core.util.BaseInfoTables@NURTURESWOMEN_KEY"/>'/>
		<input type="hidden" id="_supervisorName_" 									  
	value='<s:property value="@com.tianque.service.util.ServiceTeamMemberOrRecord@getMemberAlias(@com.tianque.core.util.BaseInfoTables@NURTURESWOMEN_KEY)" escape="false"/>'/>
	<input type="hidden" id="_superviceRecordName_" 
	value='<s:property value="@com.tianque.service.util.ServiceTeamMemberOrRecord@getRecordAlias(@com.tianque.core.util.BaseInfoTables@NURTURESWOMEN_KEY)" escape="false"/>'/>
</div>