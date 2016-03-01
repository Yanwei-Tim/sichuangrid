var colNames = {
	"LETTING_HOUSE" : [ 'id', '房东', '住房编号', '出租房地址' ],
	"SCHOOL" : [ 'id', '学校名称', '校长', '学校地址' ],
	"OTHER_LOCALE" : [ 'id', '联系人', '场所名称', '场所地址' ],
	"ENTERPRISEKEY" : [ 'id', '法人代表', '企业名称', '企业地址' ],
	"SAFETYPRODUCTIONKEY" : [ 'id', '负责人', '安全生产重点企业名称', '安全生产重点企业地址' ],
	"SECURITYKEY" : [ 'id', '负责人', '治安重点企业名称', '治安重点企业地址' ],
	"FIRESAFETYKEY" : [ 'id', '负责人', '消防重点安全名称', '消防重点安全地址' ],
	"RELIGION" : [ 'id', '负责人', '宗教场所名称', '宗教场所地址' ],
	"HOSPITAL" : [ 'id', '医院院长', '医院名称', '医院地址' ],
	"ADMINISTRATIVE_INSTITUTION" : [ 'id', '法人代表', '单位名称', '单位地址' ]
};
var colModel = {
	"LETTING_HOUSE" : [{
		name : "id",
		index : "id",
		hidden : true,
		frozen : true
	}, {
		name : 'name',
		index : "name",
		width : 120,
		frozen : true
	}, {
		name : 'houseCode',
		index : "houseCode",
		width : 70,
		frozen : true
	}, {
		name : "lettingHouseAddr",
		index : 'lettingHouseAddr',
		sortable : false,
		width : 300
	} ],
	"SCHOOL" : [ {
		name : "id",
		index : "id",
		hidden : true,
		frozen : true
	}, {
		name : 'chineseName',
		index : "chineseName",
		width : 120,
		frozen : true
	}, {
		name : 'president',
		index : "president",
		width : 70,
		frozen : true
	}, {
		name : "address",
		index : 'address',
		sortable : false,
		width : 300
	} ],
	"OTHER_LOCALE" : [ {
		name : "id",
		index : "id",
		hidden : true,
		frozen : true
	}, {
		name : 'contactPerson',
		index : "contactPerson",
		width : 120,
		frozen : true
	}, {
		name : 'name',
		index : "name",
		width : 70,
		frozen : true
	}, {
		name : "address",
		index : 'address',
		sortable : false,
		width : 300
	} ],
	"ENTERPRISEKEY" : [ {
		name : "id",
		index : "id",
		hidden : true,
		frozen : true
	}, {
		name : 'legalPerson',
		index : "legalPerson",
		width : 120,
		frozen : true
	}, {
		name : 'name',
		index : "name",
		width : 70,
		frozen : true
	}, {
		name : "address",
		index : 'address',
		sortable : false,
		width : 300
	} ],
	"SAFETYPRODUCTIONKEY" : [ {
		name : "id",
		index : "id",
		hidden : true,
		frozen : true
	}, {
		name : 'legalPerson',
		index : "legalPerson",
		width : 70,
		frozen : true
	}, {
		name : 'name',
		index : "name",
		width : 120,
		frozen : true
	}, {
		name : "address",
		index : 'address',
		sortable : false,
		width : 300
	} ],
	"SECURITYKEY" : [ {
		name : "id",
		index : "id",
		hidden : true,
		frozen : true
	}, {
		name : 'legalPerson',
		index : "legalPerson",
		width : 70,
		frozen : true
	}, {
		name : 'name',
		index : "name",
		width : 120,
		frozen : true
	}, {
		name : "address",
		index : 'address',
		sortable : false,
		width : 300
	} ],
	"FIRESAFETYKEY" : [ {
		name : "id",
		index : "id",
		hidden : true,
		frozen : true
	}, {
		name : 'legalPerson',
		index : "legalPerson",
		width : 70,
		frozen : true
	}, {
		name : 'name',
		index : "name",
		width : 120,
		frozen : true
	}, {
		name : "address",
		index : 'address',
		sortable : false,
		width : 300
	} ],
	"RELIGION" : [ {
		name : "id",
		index : "id",
		hidden : true,
		frozen : true
	}, {
		name : 'dutyPeople',
		index : "dutyPeople",
		width : 100,
		frozen : true
	}, {
		name : 'religiousPlaces',
		index : "religiousPlaces",
		width : 100,
		frozen : true
	}, {
		name : "address",
		index : 'address',
		sortable : false,
		width : 300
	} ],
	"HOSPITAL" : [ {
		name : "id",
		index : "id",
		hidden : true,
		frozen : true
	}, {
		name : 'director',
		index : "director",
		width : 100,
		frozen : true
	}, {
		name : 'hospitalName',
		index : "hospitalName",
		width : 100,
		frozen : true
	}, {
		name : "address",
		index : 'address',
		sortable : false,
		width : 300
	} ],
	"ADMINISTRATIVE_INSTITUTION" : [ {
		name : "id",
		index : "id",
		hidden : true,
		frozen : true
	}, {
		name : 'legalPerson',
		index : "legalPerson",
		width : 100,
		frozen : true
	}, {
		name : 'unitName',
		index : "unitName",
		width : 100,
		frozen : true
	}, {
		name : "unitAddress",
		index : 'unitAddress',
		sortable : false,
		width : 300
	} ]
};
var urls = {
	"LETTING_HOUSE" : "/baseinfo/lettingHouseManage/getUnboundLettingHouseListByOrgId.action",
	"SCHOOL" : "/school/schoolConteroller/getUnboundSchoolListByOrgId.action",
	"OTHER_LOCALE" : "/baseinfo/otherLocaleManage/getUnboundOtherLocaleListByOrgId.action",
	"ENTERPRISEKEY" : "/baseinfo/enterpriseManage/getUnboundEnterpriseListByOrgId.action",
	"SAFETYPRODUCTIONKEY" : "/baseinfo/enterpriseManage/getUnboundEnterpriseListByOrgIdAndType.action",
	"SECURITYKEY" : "/baseinfo/enterpriseManage/getUnboundEnterpriseListByOrgIdAndType.action",
	"FIRESAFETYKEY" : "/baseinfo/fireSafetyEnterpriseManage/getUnboundFireSafetyEnterpriseListByOrgId.action",
	"RELIGION" : "/baseinfo/religion/getUnboundReligionListByOrgId.action",
	"HOSPITAL" : "/baseinfo/hospital/getUnboundHospitalListByOrgId.action",
	"ADMINISTRATIVE_INSTITUTION" : "/baseinfo/administrativeInstitutionManage/getUnboundAdministrativeInstitutionListByOrgId.action"
};

var addPlaceUrls = {
		"LETTING_HOUSE" : "/baseinfo/lettingHouseManage/dispatch.action?mode=add&organizationId=",
		"SCHOOL" : "/school/schoolConteroller/prepareSchoolAction.action?mode=add&orgId=",
		"OTHER_LOCALE" : "/baseinfo/otherLocaleManage/dispatch.action?mode=add&organization.id=",
		"ENTERPRISEKEY" : "/baseinfo/enterpriseManage/dispatchOperate.action?mode=add&ownerOrg.id=",
		"SAFETYPRODUCTIONKEY" : "/baseinfo/enterpriseManage/dispatchOperate.action?mode=add&ownerOrg.id=",
		"SECURITYKEY" : "/baseinfo/enterpriseManage/dispatchOperate.action?mode=add&ownerOrg.id=",
		"FIRESAFETYKEY" : "/baseinfo/fireSafetyEnterpriseManage/dispatchOperate.action?mode=add&ownerOrg.id=",
		"RELIGION" : "/baseinfo/religion/dispatchOperate.action?mode=add&ownerOrg.id=",
		"HOSPITAL" : "/baseinfo/hospital/dispatch.action?mode=add&orgId=",
		"ADMINISTRATIVE_INSTITUTION" : "/baseinfo/administrativeInstitutionManage/dispatch.action?mode=add&ownerOrg.id="
	};
