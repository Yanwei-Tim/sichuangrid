--见义勇为的数据字典项
insert into propertydomains (ID, DOMAINNAME, SYSTEMSENSITIVE, SYSTEMRESTRICT)
values (s_propertydomains.NEXTVAL, '参保情况（大类）', 0, null);

insert into propertydomains (ID, DOMAINNAME, SYSTEMSENSITIVE, SYSTEMRESTRICT)
values (s_propertydomains.NEXTVAL, '参保情况（子类）', 0, null);

insert into propertydomains (ID, DOMAINNAME, SYSTEMSENSITIVE, SYSTEMRESTRICT)
values (s_propertydomains.NEXTVAL, '伤残等级', 0, null);

insert into propertydomains (ID, DOMAINNAME, SYSTEMSENSITIVE, SYSTEMRESTRICT)
values (s_propertydomains.NEXTVAL, '是否牺牲', 0, null);

insert into propertydomains (ID, DOMAINNAME, SYSTEMSENSITIVE, SYSTEMRESTRICT)
values (s_propertydomains.NEXTVAL, '见义勇为奖项', 0, null);

insert into propertydomains (ID, DOMAINNAME, SYSTEMSENSITIVE, SYSTEMRESTRICT)
values (s_propertydomains.NEXTVAL, '见义勇为困难类型', 0, null);


insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval,(select id from propertydomains p where domainname='参保情况（大类）'), 0, 1, '养老保险', 'ylbx', 'yanglaobaoxian', 'admin', null, sysdate, null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval,(select id from propertydomains p where domainname='参保情况（大类）'), 1, 2, '医疗保险', 'ylbx', 'yiliaobaoxian', 'admin', null, sysdate, null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval,(select id from propertydomains p where domainname='参保情况（大类）'), 2, 3, '其他', 'qt', 'qita', 'admin', null, sysdate, null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval,(select id from propertydomains p where domainname='参保情况（子类）'), 0, 1, '养老保险', 'ylbx', 'yanglaobaoxian', 'admin', null, sysdate, null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval,(select id from propertydomains p where domainname='参保情况（子类）'), 0, 2, '新农保', 'xnb', 'xinnongbao', 'admin', null, sysdate, null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval,(select id from propertydomains p where domainname='参保情况（子类）'), 1, 3, '医保', 'yb', 'yibao', 'admin', null, sysdate, null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval,(select id from propertydomains p where domainname='参保情况（子类）'), 1, 4, '新农合', 'xnh', 'xinnonghe', 'admin', null, sysdate, null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='伤残等级'), 0, 1, '一级', 'yj', 'yiji', 'admin', null, sysdate, null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval,(select id from propertydomains p where domainname='伤残等级'), 0, 2, '二级', 'ej', 'erji', 'admin', null, sysdate, null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval,(select id from propertydomains p where domainname='伤残等级'), 0, 3, '三级', 'sj', 'sanji', 'admin', null, sysdate, null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval,(select id from propertydomains p where domainname='伤残等级'), 0, 4, '四级', 'sj', 'siji', 'admin', null, sysdate, null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval,(select id from propertydomains p where domainname='伤残等级'), 0, 5, '五级', 'wj', 'wuji', 'admin', null, sysdate, null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval,(select id from propertydomains p where domainname='伤残等级'), 0, 6, '六级', 'lj', 'liuji', 'admin', null, sysdate, null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval,(select id from propertydomains p where domainname='伤残等级'), 0, 7, '七级', 'qj', 'qiji', 'admin', null, sysdate, null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval,(select id from propertydomains p where domainname='伤残等级'), 0, 8, '八级', 'bj', 'baji', 'admin', null, sysdate, null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval,(select id from propertydomains p where domainname='伤残等级'), 0, 9, '九级', 'jj', 'jiuji', 'admin', null, sysdate, null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval,(select id from propertydomains p where domainname='伤残等级'), 0, 10, '十级', 'sj', 'shiji', 'admin', null, sysdate, null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval,(select id from propertydomains p where domainname='是否牺牲'), 0, 1, '是', 's', 'shi', 'admin', null, sysdate, null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval,(select id from propertydomains p where domainname='是否牺牲'), 0, 2, '否', 'f', 'fou', 'admin', null, sysdate, null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval,(select id from propertydomains p where domainname='见义勇为奖项'), 0, 1, '国家级', 'gjj', 'guojiaji', 'admin', null, sysdate, null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval,(select id from propertydomains p where domainname='见义勇为奖项'), 0, 2, '省级', 'sj', 'shengji', 'admin', null, sysdate, null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval,(select id from propertydomains p where domainname='见义勇为奖项'), 0, 3, '市级', 'sj', 'shiji', 'admin', null, sysdate, null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval,(select id from propertydomains p where domainname='见义勇为奖项'), 0, 4, '县级', 'xj', 'xianji', 'admin', null, sysdate, null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval,(select id from propertydomains p where domainname='见义勇为奖项'), 0, 5, '其他', 'qt', 'qita', 'admin', null, sysdate, null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval,(select id from propertydomains p where domainname='见义勇为困难类型'), 0, 1, '住房', 'zf', 'zhufang', 'admin', null, sysdate, null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval,(select id from propertydomains p where domainname='见义勇为困难类型'), 0, 2, '就业', 'jy', 'jiuye', 'admin', null, sysdate, null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval,(select id from propertydomains p where domainname='见义勇为困难类型'), 0, 3, '教育', 'jy', 'jiaoyu', 'admin', null, sysdate, null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval,(select id from propertydomains p where domainname='见义勇为困难类型'), 0, 4, '基本生活', 'jbsh', 'jibenshenghuo', 'admin', null, sysdate, null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval,(select id from propertydomains p where domainname='见义勇为困难类型'), 0, 5, '医疗', 'yl', 'yiliao', 'admin', null, sysdate, null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval,(select id from propertydomains p where domainname='见义勇为困难类型'), 0, 6, '其他', 'qt', 'qita', 'admin', null, sysdate, null);

commit;