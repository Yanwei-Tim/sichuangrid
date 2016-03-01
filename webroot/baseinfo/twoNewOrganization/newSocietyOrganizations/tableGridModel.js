
var gridOption = {
	colModel:[
		{name:"id",index:'id',sortable:false,hidden:true},
		{name:"encryptId",index:'id',sortable:false,hidden:true,frozen:true},
    	{name:"operator", index:'id',label:'操作',formatter:operatorFormatter,width:65,frozen:true,sortable:false,align:'center' },
 		{name:"name",index:'name',label:"组织名称",sortable:true,formatter:nameFont,width:200},
 		{name:"address",index:'address',sortable:true,label:"住所",width:200},
 		{name:"legalPerson",index:'legalPerson',label:"法定代表人",sortable:true,width:120},
		{name:"type",index:'type',label:"组织类别",formatter:typeFormatter,sortable:true,width:120},
		//{name:'subType',index:'subType',label:'组织子类别',width:120,sortable:true,formatter:subTypeFormatter},
		{name:"mainResponsibilities",label:"主要职责",width:100},
		{name:"validityStartDate",index:'validityStartDate',label:"有效期开始日期",align:'center',sortable:true,width:120},
		{name:"validityEndDate",index:'validityEndDate',label:"有效期结束日期",align:'center',sortable:true,width:120},
		{name:'hasServiceTeamMember', sortable:true, label:'有无治安负责人', width:140,align:'center',formatter:hasServiceTeamMemberFormatter},
		{name:'lastRecordTime', sortable:false, label:'最新巡场时间',align:'center', width:140},
		{name:'updateDate',sortable:true, label:'数据更新时间',align:'center', width:140},
		{name:'organization.orgName',label:"所属区域",align:'center',sortable:false,width:180, hidden:true},
		{name:"legalPersonTelephone",index:'legalPersonTelephone',label:"固定电话",align:'center',sortable:true,width:120,hidden:true},
		{name:"legalPersonMobileNumber",index:'legalPersonMobileNumber',label:"联系手机",align:'center',sortable:true,width:120,hidden:true},
		{name:"isEmphasis",sortable:false,hidden:true,hidedlg:true,width:100},
		{name:"chargeUnit",index:'chargeUnit',label:"业务主管单位",sortable:true,width:120,hidden:true},
		{name:"registrationNumber",index:'registrationNumber',label:"登记证号码",sortable:true,width:120,hidden:true},
		{name:"personNum",index:'personNum',label:"成员数",sortable:true,width:90,hidden:true},
		{name:"partyNum",index:'partyNum',label:"党员人数",sortable:true,width:90,hidden:true},
		{name:"introduction",index:'introduction',label:"简 介",sortable:true,width:120,hidden:true},
		{name:"honor",index:'honor',label:"获得荣誉",sortable:true,width:120,hidden:true},
		{name:'sourcesState',label:"数据来源",sortable:false,hidden:true,formatter:sourceStateFormatter,width:80},
		{name:"remark",sortable:true,label:"备注",hidden:true,width:100}
	]
};