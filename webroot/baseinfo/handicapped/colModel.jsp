<%@	page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript">
	var dialogWidth = 800;
	var dialogHeight = 600;

	//字典的自定义标签
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
	<pop:formatterProperty name="peopleType" domainName="@com.tianque.domain.property.PropertyTypes@SPECIAL_PERSON" />
	<pop:formatterProperty name="currentStatus" domainName="@com.tianque.domain.property.PropertyTypes@CURRENT_STATUS" />
	<pop:formatterProperty name="liveStatus" domainName="@com.tianque.domain.property.PropertyTypes@LIVE_STATUS" />
	<pop:formatterProperty name="spouseStatus" domainName="@com.tianque.domain.property.PropertyTypes@SPOUSE_STATUS" />
	<pop:formatterProperty name="skillProfile" domainName="@com.tianque.domain.property.PropertyTypes@SKILLS_AND_SPECIALITIES" />
	<pop:formatterProperty name="disability" domainName="@com.tianque.domain.property.PropertyTypes@DISABILITY_STATUS" />
	<pop:formatterProperty name="disabilityType" domainName="@com.tianque.domain.property.PropertyTypes@DISABILITY_TYPE" />
	<pop:formatterProperty name="workProfile" domainName="@com.tianque.domain.property.PropertyTypes@LABOUR_CAPACITY" />
	<pop:formatterProperty name="attentionExtent" domainName="@com.tianque.domain.property.PropertyTypes@ATTENTION_EXTENT" />


	function isTreat(el, options, rowData) {
		if (true == rowData.treat) {
			return "是";
		} else {
			return "否";
		}
	}

	
	var gridOption = {
		colModel : [ {
			name : "id",
			index : "id",
			hidden : true,
			sortable:false,
			frozen : true
		}, {
			name : "encryptId",
			index : "id",
			hidden : true,
			sortable:false,
			frozen : true
		}, {
			name : "organization.id",
			index : "organization.id",
			hidden : true,
			sortable:false,
			hidedlg:true
		}, {
			name : "operate",
			index : "operate",
			label : "操作",
			width : 90,
			formatter : operateFormatter,
			frozen : true,
			sortable:false,
			align:"center"
		}, {
			name:"attentionExtent",
			index:"attentionExtent",
			label:"关注程度",
			formatter:attentionExtentColor,
			width:100,
			sortable:false,
			frozen:true
		},{
			name : "name",
			index : 'name',
			label : '姓名',
			formatter : nameFont,
			width : 80,
			sortable:false,
			frozen : true
		},{
			name : 'gender',
			formatter : genderFormatter,
			label : '性别',
			align : 'center',
			sortable:false,
			width : 50
		},{
			name : 'idCardNo',
			index : 'idCardNo',
			label : '身份证号码',
			sortable:false,
			align : 'center',
			width : 160,
			frozen : true
		}, {
			name : 'organization.orgName',
			sortable : false,
			index : 'organization.orgName',
			label : '所属网格',
			hidden : true,
			width : 150
		}, {
			name : 'province',
			index : 'province',
			sortable:false,
			label : '户籍地',
			formatter : nativePlaceFormatter,
			width : 200,
			hidden : true
		}, {
			name : 'nativePlaceAddress',
			sortable:false,
			label : '户籍地详址',
			width : 200,
			hidden : true
		}, {
			name : 'currentAddress',
			sortable:false,
			label : '常住地址',
			width : 200
		},  {
			name : "birthday",
			index : 'birthday',
			label : '出生日期',
			align : 'center',
			sortable:false,
			hidden : true,
			width : 80
		}, {
			name : "usedName",
			index : 'usedName',
			label : '曾用名/别名',
			width : 95,
			sortable:false,
			hidden : true
		}, {
			name : 'mobileNumber',
			sortable:false,
			label : '联系手机',
			width : 100,
			align : 'center',
			hidden : true
		}, {
			name : 'telephone',
			sortable:false,
			label : '固定电话',
			width : 120,
			align : 'center',
			hidden : true
		}, {
			name : "nation",
			sortable:false,
			label : "民族",
			formatter : nationFormatter,
			width : 80,
			hidden : true
		}, {
			name : "politicalBackground",
			sortable:false,
			label : "政治面貌",
			formatter : politicalBackgroundFormatter,
			width : 150,
			hidden : true
		}, {
			name : "schooling",
			sortable:false,
			label : "文化程度",
			formatter : schoolingFormatter,
			width : 80,
			hidden : true
		}, {
			name : 'career',
			sortable:false,
			formatter : careerFormatter,
			label : '职业',
			width : 80,
			hidden : true
		}, {
			name : 'workUnit',
			sortable:false,
			label : '工作单位或就读学校',
			width : 200,
			hidden : true
		}, {
			name : "maritalState",
			sortable:false,
			label : "婚姻状况",
			formatter : maritalStateFormatter,
			width : 80,
			align : 'center',
			hidden : true
		}, {
			name : "stature",
			sortable:false,
			label : "身高(cm)",
			width : 80,
			align : 'center',
			hidden : true
		}, {
			name : "bloodType",
			sortable:false,
			label : "血型",
			formatter : bloodTypeFormatter,
			width : 80,
			hidden : true
		}, {
			name : "faith",
			sortable:false,
			label : "宗教信仰",
			formatter : faithFormatter,
			width : 80,
			hidden : true
		}, {
			name : "email",
			sortable:false,
			label : "电子邮箱",
			width : 150,
			hidden : true
		}, {
			name : 'otherAddress',
			sortable:false,
			label : '临时居所',
			width : 200,
			hidden : true
		}, {
			name : 'death',
			sortable:false,
			hidden : true,
			hidedlg : true,
			width : 80
		}, {
			name : 'isEmphasis',
			sortable:false,
			hidden : true,
			hidedlg : true,
			width : 80
		}, {
			name : "guardianName",
			index : 'guardianName',
			label : '监护人',
			sortable:false,
			width : 80,
			hidden : true
		}, {
			name : 'disabilityCardNo',
			index : 'disabilityCardNo',
			label : '残疾证号',
			hidden : true,
			sortable:false,
			width : 80
		}, {
			name : 'disabilityType',
			label : '残疾类型',
			formatter : disabilityTypeFormatter,
			sortable:false,
			hidden : false,
			width : 80
		}, {
			name : 'disabilityReason',
			index : 'disabilityReason',
			label : '残疾原因',
			sortable:false,
			hidden : false,
			width : 120
		}, {
			name : "disabilityDate",
			index : 'disabilityDate',
			label : '残疾时间',
			sortable:false,
			width : 80,
			align : 'center',
			hidden : true
		},
		
		{name:'hasServiceTeamMember',label:'有无服务成员',index:'hasServiceTeamMember',width:100,sortable:false,align:'center',formatter:hasServiceTeamMemberFormatter},
		{name:'lastRecordTime',label:'最新服务时间',index:'hasServiceTeamRecord',sortable:false,align:'center',width:100},
		{name:'sourcesState',index:'sourcesState',label:"数据来源",sortable:false,hidden:true,formatter:sourceStateFormatter,width:80,align:'center'},
		{
			name : 'updateDate',
			index : 'updateDate',
			label : '数据更新时间',
			width : 160,
			align : 'center',
			sortable:false
		},{
			name : 'disability',
			label : '残疾等级',
			formatter : disabilityFormatter,
			sortable:false,
			hidden : true,
			width : 80
		}, {
			name : 'skillProfile',
			index : 'skillProfile',
			label : '技能特长',
			formatter : skillProfileFormatter,
			sortable:false,
			hidden : true,
			width : 80
		}, {
			name : 'workProfile',
			index : 'workProfile',
			label : '劳动能力',
			formatter : workProfileFormatter,
			sortable:false,
			hidden : true,
			width : 80
		} ]
	}
	function nativePlaceFormatter(el, options, rowData){
		var str = "";
		if(rowData.province != null)
			str += rowData.province;
		if(rowData.city != null)
			str += rowData.city;
		if(rowData.district != null)
			str += rowData.district;
		return str;
	}
</script>
