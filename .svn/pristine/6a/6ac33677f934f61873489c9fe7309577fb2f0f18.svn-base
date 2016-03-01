--残疾人 类别  等级 更新
delete from propertydicts where propertydomainid=(select id from propertydomains where domainname = '残疾类型') and displayname='其他';
commit;
update propertydicts set displayname = '言语残疾', fullpinyin = 'yanyucanji' where propertydomainid=(select id from propertydomains where domainname = '残疾类型') and displayname='语言残疾';
commit;

update propertydicts set displayname = '智力残疾一级', simplepinyin = 'zlcjyj', fullpinyin = 'zhilicanjiyiji' where displayname = '一级智残' and propertydomainid = (select id from propertydomains where domainname='残疾状况');
update propertydicts set displayname = '智力残疾二级', simplepinyin = 'zlcjej', fullpinyin = 'zhilicanjierji' where displayname = '二级智残' and propertydomainid = (select id from propertydomains where domainname='残疾状况');
update propertydicts set displayname = '智力残疾三级', simplepinyin = 'zlcjsj', fullpinyin = 'zhilicanjisanji' where displayname = '三级智残' and propertydomainid = (select id from propertydomains where domainname='残疾状况');
update propertydicts set displayname = '智力残疾四级', simplepinyin = 'zlcjsj', fullpinyin = 'zhilicanjisiji' where displayname = '四级智残' and propertydomainid = (select id from propertydomains where domainname='残疾状况');
update propertydicts set displayname = '肢体残疾一级', simplepinyin = 'ztcjyj', fullpinyin = 'zhiticanjiyiji' where displayname = '一级肢残' and propertydomainid = (select id from propertydomains where domainname='残疾状况');
update propertydicts set displayname = '肢体残疾二级', simplepinyin = 'ztcjej', fullpinyin = 'zhiticanjierji' where displayname = '二级肢残' and propertydomainid = (select id from propertydomains where domainname='残疾状况');
update propertydicts set displayname = '肢体残疾三级', simplepinyin = 'ztcjsj', fullpinyin = 'zhiticanjisanji' where displayname = '三级肢残' and propertydomainid = (select id from propertydomains where domainname='残疾状况');
update propertydicts set displayname = '肢体残疾四级', simplepinyin = 'ztcjsj', fullpinyin = 'zhiticanjisiji' where displayname = '四级肢残' and propertydomainid = (select id from propertydomains where domainname='残疾状况');
update propertydicts set displayname = '精神残疾一级', simplepinyin = 'jscjyj', fullpinyin = 'jingshencanjiyiji' where displayname = '一级精残' and propertydomainid = (select id from propertydomains where domainname='残疾状况');
update propertydicts set displayname = '精神残疾二级', simplepinyin = 'jscjej', fullpinyin = 'jingshencanjierji' where displayname = '二级精残' and propertydomainid = (select id from propertydomains where domainname='残疾状况');
update propertydicts set displayname = '精神残疾三级', simplepinyin = 'jscjsj', fullpinyin = 'jingshencanjisanji' where displayname = '三级精残' and propertydomainid = (select id from propertydomains where domainname='残疾状况');
update propertydicts set displayname = '精神残疾四级', simplepinyin = 'jscjsj', fullpinyin = 'jingshencanjisiji' where displayname = '四级精残' and propertydomainid = (select id from propertydomains where domainname='残疾状况');
update propertydicts set displayname = '听力残疾一级', simplepinyin = 'tlcjyj', fullpinyin = 'tinglicanjiyiji' where displayname = '一级聋' and propertydomainid = (select id from propertydomains where domainname='残疾状况');
update propertydicts set displayname = '听力残疾二级', simplepinyin = 'tlcjej', fullpinyin = 'tinglicanjierji' where displayname = '二级聋' and propertydomainid = (select id from propertydomains where domainname='残疾状况');
update propertydicts set displayname = '听力残疾三级', simplepinyin = 'tlcjsj', fullpinyin = 'tinglicanjisanji' where displayname = '三级聋' and propertydomainid = (select id from propertydomains where domainname='残疾状况');
update propertydicts set displayname = '听力残疾四级', simplepinyin = 'tlcjsj', fullpinyin = 'tinglicanjisiji' where displayname = '一级重听' and propertydomainid = (select id from propertydomains where domainname='残疾状况');
update propertydicts set displayname = '言语残疾一级', simplepinyin = 'yycjyj', fullpinyin = 'yanyucanjiyiji' where displayname = '失语' and propertydomainid = (select id from propertydomains where domainname='残疾状况');
update propertydicts set displayname = '言语残疾二级', simplepinyin = 'yycjej', fullpinyin = 'yanyucanjierji' where displayname = '失音' and propertydomainid = (select id from propertydomains where domainname='残疾状况');
update propertydicts set displayname = '言语残疾三级', simplepinyin = 'yycjsj', fullpinyin = 'yanyucanjisanji' where displayname = '二级重听' and propertydomainid = (select id from propertydomains where domainname='残疾状况');
update propertydicts set displayname = '言语残疾四级', simplepinyin = 'yycjsj', fullpinyin = 'yanyucanjisiji' where displayname = '低视' and propertydomainid = (select id from propertydomains where domainname='残疾状况');
update propertydicts set displayname = '视力残疾一级', simplepinyin = 'slcjyj', fullpinyin = 'shilicanjiyiji' where displayname = '盲' and propertydomainid = (select id from propertydomains where domainname='残疾状况');
update propertydicts set displayname = '视力残疾二级', simplepinyin = 'slcjej', fullpinyin = 'shilicanjierji' where displayname = '未知' and propertydomainid = (select id from propertydomains where domainname='残疾状况');
update propertydicts set displayname = '视力残疾三级', simplepinyin = 'slcjsj', fullpinyin = 'shilicanjisanji' where displayname = '其他' and propertydomainid = (select id from propertydomains where domainname='残疾状况');
commit;

insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='残疾状况'),0,24,'视力残疾四级','slcjsj','shilicanjisiji','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='残疾状况'),0,25,'多重残疾一级','dccjyj','duochongcanjiyiji','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='残疾状况'),0,26,'多重残疾二级','dccjej','duochongcanjierji','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='残疾状况'),0,27,'多重残疾三级','dccjsj','duochongcanjisanji','admin',sysdate);
insert into propertydicts (id,propertydomainid,internalid,displayseq,displayname,simplepinyin,fullpinyin,createuser,createdate)
	values (s_propertyDicts.Nextval,(select id from propertydomains where domainname='残疾状况'),0,28,'多重残疾四级','dccjsj','duochongcanjisiji','admin',sysdate);
commit;


update propertydicts set displayseq = '21' where displayname = '言语残疾三级' and propertydomainid = (select id from propertydomains where domainname='残疾状况');
update propertydicts set displayseq = '22' where displayname = '言语残疾四级' and propertydomainid = (select id from propertydomains where domainname='残疾状况');
update propertydicts set displayseq = '19' where displayname = '言语残疾一级' and propertydomainid = (select id from propertydomains where domainname='残疾状况');
update propertydicts set displayseq = '20' where displayname = '言语残疾二级' and propertydomainid = (select id from propertydomains where domainname='残疾状况');
update propertydicts set displayseq = '1' where displayname = '视力残疾一级' and propertydomainid = (select id from propertydomains where domainname='残疾状况');
update propertydicts set displayseq = '2' where displayname = '视力残疾二级' and propertydomainid = (select id from propertydomains where domainname='残疾状况');
commit;

--政治面貌 新增 中国少年先锋队 字典项
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, 
       DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='政治面貌'), 0, 15, 
       '中国少年先锋队', 'zgsnxfd', 'zhongguoshaonianxian', 'admin', '', sysdate, null);
commit;




insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, 
       parentId, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.nextval, '楼宇信息', 'builddatasTemp', 1, '楼宇信息', 1, 
       (select id from permissions where ename='actualHouseDataManagement'), '', 
       '/hotModuel/sysadmin/menuManage/getTabByParentEname.action?ename=builddatasTemp', '', 0);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, 
       PARENTID, DESCRIPTION, NORMALURL, LEADERURL,INDEXID)
values (s_permissions.nextVal, '楼宇信息导入', 'builddatasTempImport', 1, '楼宇信息', 1, 
       (select id from permissions where ename = 'builddatasTemp'), '',
       '/hotModuel/dataManage/location/builddatasTempManage/builddatasTempList.ftl', '', 0);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, 
       PARENTID, DESCRIPTION, NORMALURL, LEADERURL,INDEXID)
values (s_permissions.nextVal, '搜索', 'importSearchBuilddatasTemp', 0, '楼宇信息导入', 1, 
       (select id from permissions where ename = 'builddatasTempImport'), ' ','', '', 0);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, 
       PARENTID, DESCRIPTION, NORMALURL, LEADERURL,INDEXID)
values (s_permissions.nextVal, '高级搜索', 'importAdvancedSearchBuilddatasTemp', 0, '楼宇信息导入', 1, 
       (select id from permissions where ename = 'builddatasTempImport'), ' ','', '', 1);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, 
       PARENTID, DESCRIPTION, NORMALURL, LEADERURL,INDEXID)
values (s_permissions.nextVal, '导入', 'importBuilddatasTemp', 0, '楼宇信息导入', 1, 
       (select id from permissions where ename = 'builddatasTempImport'), ' ','', '', 2);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, 
       PARENTID, DESCRIPTION, NORMALURL, LEADERURL,INDEXID)
values (s_permissions.nextVal, '查看', 'importViewBuilddatasTemp', 0, '楼宇信息导入', 1, 
       (select id from permissions where ename = 'builddatasTempImport'), ' ','', '', 3);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, 
       PARENTID, DESCRIPTION, NORMALURL, LEADERURL,INDEXID)
values (s_permissions.nextVal, '批量删除', 'importDeleteBuilddatasTemp', 0, '楼宇信息导入', 1, 
       (select id from permissions where ename = 'builddatasTempImport'), ' ','', '', 4);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, 
       PARENTID, DESCRIPTION, NORMALURL, LEADERURL,INDEXID)
values (s_permissions.nextVal, '楼宇信息认领', 'builddatasTempclaim', 1, '楼宇信息', 1, 
       (select id from permissions where ename = 'builddatasTemp'), '',
       '/hotModuel/dataManage/location/builddatasTempManage/builddatasTempList.ftl?mode=claimList', '', 1);

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, 
       PARENTID, DESCRIPTION, NORMALURL, LEADERURL,INDEXID)
values (s_permissions.nextVal, '搜索', 'claimSearchBuilddatasTemp', 0, '楼宇信息认领', 1, 
       (select id from permissions where ename = 'builddatasTempclaim'), ' ','', '', 0);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, 
       PARENTID, DESCRIPTION, NORMALURL, LEADERURL,INDEXID)
values (s_permissions.nextVal, '高级搜索', 'claimAdvancedSearchBuilddatasTemp', 0, '楼宇信息认领', 1, 
       (select id from permissions where ename = 'builddatasTempclaim'), ' ','', '', 1);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, 
       PARENTID, DESCRIPTION, NORMALURL, LEADERURL,INDEXID)
values (s_permissions.nextVal, '查看', 'claimViewBuilddatasTemp', 0, '楼宇信息认领', 1, 
       (select id from permissions where ename = 'builddatasTempclaim'), ' ','', '', 2);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, 
       PARENTID, DESCRIPTION, NORMALURL, LEADERURL,INDEXID)
values (s_permissions.nextVal, '修改', 'claimUpdateBuilddatasTemp', 0, '楼宇信息认领', 1, 
       (select id from permissions where ename = 'builddatasTempclaim'), ' ','', '', 3);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, 
       PARENTID, DESCRIPTION, NORMALURL, LEADERURL,INDEXID)
values (s_permissions.nextVal, '认领', 'claimBuilddatasTemp', 0, '楼宇信息认领', 1, 
       (select id from permissions where ename = 'builddatasTempclaim'), ' ','', '', 4);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, 
       PARENTID, DESCRIPTION, NORMALURL, LEADERURL,INDEXID)
values (s_permissions.nextVal, '分步认领', 'stepClaimBuilddatasTemp', 0, '楼宇信息认领', 1, 
       (select id from permissions where ename = 'builddatasTempclaim'), ' ','', '', 5);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, 
       PARENTID, DESCRIPTION, NORMALURL, LEADERURL,INDEXID)
values (s_permissions.nextVal, '撤销认领', 'unDoClaimBuilddatasTemp', 0, '楼宇信息认领', 1, 
       (select id from permissions where ename = 'builddatasTempclaim'), ' ','', '', 6);
       
 -----事件处理查看动态列表权限
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, 
       PARENTID, DESCRIPTION, NORMALURL, LEADERURL,INDEXID)
values (s_permissions.nextVal, '查看动态流程图', 'viewProcessJurisdictionsNeed', 0, '待办事项', 1, 
       (select id from permissions where ename = 'unDoJurisdictionsIssueListManagement'), ' ','', '', 11);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, 
       PARENTID, DESCRIPTION, NORMALURL, LEADERURL,INDEXID)
values (s_permissions.nextVal, '查看动态流程图', 'viewProcessJurisdictionsDone', 0, '已办结事项', 1, 
       (select id from permissions where ename = 'doneJurisdictionsIssueListManagement'), ' ','', '', 2);
commit;

--配套设施类型
insert into propertydomains(id,domainname)
  values(s_propertydomains.NEXTVAL,'配套设施类型');
commit;
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, 
       DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='配套设施类型'), 0, 1, 
       '景区餐馆', 'jqcg', 'jingqucanguan', 'admin', '', sysdate, null);
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, 
       DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='配套设施类型'), 1, 2, 
       '景区宾馆', 'jqbg', 'jingqubinguan', 'admin', '', sysdate, null);
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, 
       DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='配套设施类型'), 2, 3, 
       '景区购物点', 'jqgwd', 'jingqugouwudian', 'admin', '', sysdate, null);
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, 
       DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='配套设施类型'), 3, 4, 
       '娱乐设施', 'ylss', 'yulesheshi', 'admin', '', sysdate, null);
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, 
       DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='配套设施类型'), 4, 5, 
       '自驾车宿营地', 'zjcsyd', 'zijiachesuyingdi', 'admin', '', sysdate, null);
commit;
--景点交通类型
insert into propertydomains(id,domainname)
  values(s_propertydomains.NEXTVAL,'景点交通类型');
commit;
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, 
       DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='景点交通类型'), 0, 1, 
       '公交', 'gj', 'gongjiao', 'admin', '', sysdate, null);
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, 
       DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='景点交通类型'), 1, 2, 
       '自行车', 'zxc', 'zixingche', 'admin', '', sysdate, null);
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, 
       DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='景点交通类型'), 2, 3, 
       '游船', 'yc', 'youchuan', 'admin', '', sysdate, null);
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, 
       DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='景点交通类型'), 3, 4, 
       '其他', 'qt', 'qita', 'admin', '', sysdate, null);
commit;


--投诉表扬类型
insert into propertydomains(id,domainname)
  values(s_propertydomains.NEXTVAL,'投诉表扬类型');
commit;
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, 
       DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='投诉表扬类型'), 0, 1, 
       '咨询', 'zx', 'zixun', 'admin', '', sysdate, null);
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, 
       DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='投诉表扬类型'), 1, 2, 
       '表扬', 'by', 'biaoyang', 'admin', '', sysdate, null);
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, 
       DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='投诉表扬类型'), 2, 3, 
       '投诉', 'ts', 'tousu', 'admin', '', sysdate, null);
       
commit;


insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, 
       PARENTID, DESCRIPTION, NORMALURL, LEADERURL,INDEXID)
values (s_permissions.nextVal, '景区管理', 'scenicManagement', 1, '基础信息', 1, 
       (select id from permissions where ename = 'orgLocationManageSystem'), ' ','', '', 1);
commit;
insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,indexId)
    VALUES(s_permissions.NEXTVAL,'旅游景点','scenicSpotManagement',1,'景区管理',(select id from permissions where ename='scenicManagement'),1,'/hotModuel/baseinfo/scenicManage/scenicSpot/scenicSpotList.jsp',0);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID,INDEXID)
    values (s_permissions.NEXTVAL,'新增', 'addScenicSpot', 0, '旅游景点', 1, ' ', (select id from permissions where ename='scenicSpotManagement'),1);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID,INDEXID)
    values (s_permissions.NEXTVAL,'修改', 'updateScenicSpot', 0, '旅游景点', 1, ' ', (select id from permissions where ename='scenicSpotManagement'),2);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID,INDEXID)
    values (s_permissions.NEXTVAL,'查看', 'viewScenicSpot', 0, '旅游景点', 1, ' ', (select id from permissions where ename='scenicSpotManagement'),3);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID,INDEXID)
    values (s_permissions.NEXTVAL,'删除', 'deleteScenicSpot', 0, '旅游景点', 1, ' ', (select id from permissions where ename='scenicSpotManagement'),4);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID,INDEXID)
    values (s_permissions.NEXTVAL,'查询', 'searchScenicSpot', 0, '旅游景点', 1, ' ', (select id from permissions where ename='scenicSpotManagement'),5);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID,INDEXID)
    values (s_permissions.NEXTVAL,'取消关注', 'cancelAttendedScenicSpot', 0, '旅游景点', 1, ' ', (select id from permissions where ename='scenicSpotManagement'),8);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID,INDEXID)
    values (s_permissions.NEXTVAL,'重新关注', 'attendedScenicSpot', 0, '旅游景点', 1, ' ', (select id from permissions where ename='scenicSpotManagement'),9);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID,INDEXID)
    values (s_permissions.NEXTVAL,'投诉/表扬', 'praiseScenicSpot', 0, '旅游景点', 1, ' ', (select id from permissions where ename='scenicSpotManagement'),10);
commit;
insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,indexId)
    VALUES(s_permissions.NEXTVAL,'配套设施','scenicEquipmentManagement',1,'景区管理',(select id from permissions where ename='scenicManagement'),1,'/hotModuel/baseinfo/scenicManage/scenicEquipment/scenicEquipmentList.jsp',1);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID,INDEXID)
    values (s_permissions.NEXTVAL,'新增', 'addScenicEquipment', 0, '配套设施', 1, ' ', (select id from permissions where ename='scenicEquipmentManagement'),1);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID,INDEXID)
    values (s_permissions.NEXTVAL,'修改', 'updateScenicEquipment', 0, '配套设施', 1, ' ', (select id from permissions where ename='scenicEquipmentManagement'),2);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID,INDEXID)
    values (s_permissions.NEXTVAL,'查看', 'viewScenicEquipment', 0, '配套设施', 1, ' ', (select id from permissions where ename='scenicEquipmentManagement'),3);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID,INDEXID)
    values (s_permissions.NEXTVAL,'删除', 'deleteScenicEquipment', 0, '配套设施', 1, ' ', (select id from permissions where ename='scenicEquipmentManagement'),4);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID,INDEXID)
    values (s_permissions.NEXTVAL,'查询', 'searchScenicEquipment', 0, '配套设施', 1, ' ', (select id from permissions where ename='scenicEquipmentManagement'),5);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID,INDEXID)
    values (s_permissions.NEXTVAL,'取消关注', 'cancelAttendedScenicEquipment', 0, '配套设施', 1, ' ', (select id from permissions where ename='scenicEquipmentManagement'),8);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID,INDEXID)
    values (s_permissions.NEXTVAL,'重新关注', 'attendedScenicEquipment', 0, '配套设施', 1, ' ', (select id from permissions where ename='scenicEquipmentManagement'),9);
commit;
insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,indexId)
    VALUES(s_permissions.NEXTVAL,'景区交通','scenicTrafficManagement',1,'景区管理',(select id from permissions where ename='scenicManagement'),1,'/hotModuel/baseinfo/scenicManage/scenicTraffic/scenicTrafficList.jsp',2);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID,INDEXID)
    values (s_permissions.NEXTVAL,'新增', 'addScenicTraffic', 0, '景区交通', 1, ' ', (select id from permissions where ename='scenicTrafficManagement'),1);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID,INDEXID)
    values (s_permissions.NEXTVAL,'修改', 'updateScenicTraffic', 0, '景区交通', 1, ' ', (select id from permissions where ename='scenicTrafficManagement'),2);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID,INDEXID)
    values (s_permissions.NEXTVAL,'查看', 'viewScenicTraffic', 0, '景区交通', 1, ' ', (select id from permissions where ename='scenicTrafficManagement'),3);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID,INDEXID)
    values (s_permissions.NEXTVAL,'删除', 'deleteScenicTraffic', 0, '景区交通', 1, ' ', (select id from permissions where ename='scenicTrafficManagement'),4);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID,INDEXID)
    values (s_permissions.NEXTVAL,'查询', 'searchScenicTraffic', 0, '景区交通', 1, ' ', (select id from permissions where ename='scenicTrafficManagement'),5);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID,INDEXID)
    values (s_permissions.NEXTVAL,'取消关注', 'cancelAttendedScenicTraffic', 0, '景区交通', 1, ' ', (select id from permissions where ename='scenicTrafficManagement'),8);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID,INDEXID)
    values (s_permissions.NEXTVAL,'重新关注', 'attendedScenicTraffic', 0, '景区交通', 1, ' ', (select id from permissions where ename='scenicTrafficManagement'),9);
commit;