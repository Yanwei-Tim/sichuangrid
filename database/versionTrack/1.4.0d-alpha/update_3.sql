--楼宇布局room字段的类型修改--
alter table BuildLayout modify room VARCHAR2(150);

--四川党建成员表
create sequence S_party_members
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;

create table party_members
(
  id number(10) primary key,
  idCardNo varchar2(60),
  name VARCHAR2(60),
  gender number(10),
  nation number(10),
  birthday date,
  schooling number(10),
  degree varchar2(60),
  specialPosition varchar2(100),
  telephone VARCHAR2(80),
  mobileNumber VARCHAR2(50),
  province VARCHAR2(30),
  city VARCHAR2(30),
  district VARCHAR2(30),
  nativePlaceAddress VARCHAR2(150),
  belongOrg number(10),
  partyPosition number(10),
  joinPartyDate date,
  career varchar2(300),
  endDate date,
  rewardAndPunishment varchar2(1000),
  accedingSituation varchar2(1000),
  joinPartyBranchDate date,
  democracy varchar2(1000),
  isHandicap number(10),
  isOld number(10),
  isOversea number(10),
  reportOrganization number(10),
  IMGURL VARCHAR2(300),
  createdate date not null,
  createuser VARCHAR2(32) not null,
  updatedate date,
  updateuser VARCHAR2(32)
);
comment on table party_members                      is '四川党建成员表';
comment on column party_members.id                  is '四川党建成员表id';
comment on column party_members.idCardNo            is '身份证号码';
comment on column party_members.name                is '姓名';
comment on column party_members.gender              is '性别';
comment on column party_members.nation              is '民族';
comment on column party_members.birthday            is '出生日期';
comment on column party_members.schooling           is '学历';
comment on column party_members.degree              is '学位';
comment on column party_members.specialPosition     is '专业技术职务';
comment on column party_members.telephone           is '联系电话';
comment on column party_members.mobileNumber        is '联系手机';
comment on column party_members.province            is '省';
comment on column party_members.city                is '市';
comment on column party_members.district            is '县';
comment on column party_members.nativePlaceAddress  is '现居地址';
comment on column party_members.belongOrg           is '所属部门';
comment on column party_members.partyPosition       is '党内职务';
comment on column party_members.joinPartyDate       is '入党日期';
comment on column party_members.career              is '单位、职务或职业';
comment on column party_members.endDate             is '党费交纳至';
comment on column party_members.rewardAndPunishment is '奖惩情况';
comment on column party_members.accedingSituation   is '参加组织生活情况';
comment on column party_members.joinPartyBranchDate is '进入当前党支部日期';
comment on column party_members.democracy           is '民主评议情况';
comment on column party_members.isHandicap          is '是否困难党员';
comment on column party_members.isOld               is '是否建国前老党员';
comment on column party_members.isOversea           is '是否出国出境';
comment on column party_members.reportOrganization  is '双报到至指定组织机构';
comment on column party_members.IMGURL              is '图片链接地址';
comment on column party_members.createDate          is '创建时间';
comment on column party_members.createUser          is '创建人';
comment on column party_members.updateDate          is '修改时间';
comment on column party_members.updateUser          is '修改人';
--成员组织关联表
create sequence S_member_associate_partyorg
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;

create table member_associate_partyorg
(
  id number(10) primary key,
  orgId number(10),
  orgInternalCode varchar2(32),
  memberId number(10),
  partyOrgType number(10),
  logOut number(1) default 0,
  partyOrg varchar2(200),
  createDate date not null,
  createUser varchar2(32) not null,
  updateDate date,
  updateUser varchar2(32)
);
comment on table member_associate_partyorg is '成员组织关联表';
comment on column member_associate_partyorg.id is '成员组织关联表id';
comment on column member_associate_partyorg.orgId is '组织机构id';
comment on column member_associate_partyorg.orgInternalCode is '组织机构内置编码';
comment on column member_associate_partyorg.memberId is '成员表id';
comment on column member_associate_partyorg.partyOrgType is '党组织类别';
comment on column member_associate_partyorg.logOut is '是否注销';
comment on column member_associate_partyorg.partyOrg is '所在党支部';
comment on column member_associate_partyorg.createDate is '创建时间';
comment on column member_associate_partyorg.createUser is '创建人';
comment on column member_associate_partyorg.updateDate is '修改时间';
comment on column member_associate_partyorg.updateUser is '修改人';

--删除党内职务
delete from propertydicts
 where id in (select pt.id
                from propertydicts pt
               where pt.propertydomainid =
                     (select pd.id
                        from propertydomains pd
                       where pd.domainname = '党内职务'));
delete from propertydomains
 where id = (select pd.id
               from propertydomains pd
              where pd.domainname = '党内职务');
commit;
--新增党内职务数据字典
insert into propertydomains (ID, DOMAINNAME, SYSTEMSENSITIVE, SYSTEMRESTRICT)
values (s_propertydomains.nextVal, '党内职务', 1, '[{"displayName":"书记","identify":0},{"displayName":"副书记","identify":1}
                                                      ,{"displayName":"组织委员","identify":2},{"displayName":"宣传委员","identify":3}
													  ,{"displayName":"纪检委员","identify":4},{"displayName":"青年委员","identify":5}
													  ,{"displayName":"其他","identify":6}]');

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextVal, (select id from propertydomains where domainname='党内职务'), 0, 1, '书记', 'sj', 'shuji', 
       'admin', '', sysdate, null);
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextVal, (select id from propertydomains where domainname='党内职务'), 1, 2, '副书记', 'fsj', 'fushuji', 
       'admin', '', sysdate, null);
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextVal, (select id from propertydomains where domainname='党内职务'), 2, 3, '组织委员', 'zzwy', 'zuzhiweiyuan', 
       'admin', '', sysdate, null);
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextVal, (select id from propertydomains where domainname='党内职务'), 3, 4, '宣传委员', 'xcwy', 'xuanchuanweiyuan', 
       'admin', '', sysdate, null);
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextVal, (select id from propertydomains where domainname='党内职务'), 4, 5, '纪检委员', 'jjwy', 'jijianweiyuan', 
       'admin', '', sysdate, null);
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextVal, (select id from propertydomains where domainname='党内职务'), 5, 6, '青年委员', 'qnwy', 'qingnianweiyuan', 
       'admin', '', sysdate, null);
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextVal, (select id from propertydomains where domainname='党内职务'), 6, 7, '其他', 'qt', 'qita', 
       'admin', '', sysdate, null);
commit;

--所属部门数据字典
insert into propertydomains (ID, DOMAINNAME, SYSTEMSENSITIVE, SYSTEMRESTRICT)
values (s_propertydomains.nextVal, '所属部门', 0, null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, CREATEDATE)
values (s_propertydicts.nextVal, (select id from propertydomains where domainname='所属部门'), 0, 1, '区委办', 'qwb', 'quweiban', 'admin', sysdate);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, CREATEDATE)
values (s_propertydicts.nextVal, (select id from propertydomains where domainname='所属部门'), 1, 2, '人大办', 'rdb', 'rendaban', 'admin', sysdate);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, CREATEDATE)
values (s_propertydicts.nextVal, (select id from propertydomains where domainname='所属部门'), 2, 3, '政府办', 'zfb', 'zhengfuban', 'admin', sysdate);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, CREATEDATE)
values (s_propertydicts.nextVal, (select id from propertydomains where domainname='所属部门'), 3, 4, '政协办', 'zxb', 'zhengxieban', 'admin', sysdate);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, CREATEDATE)
values (s_propertydicts.nextVal, (select id from propertydomains where domainname='所属部门'), 4, 5, '纪委(监察局)', 'jwjcj', 'jiweijianchaju', 'admin', sysdate);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, CREATEDATE)
values (s_propertydicts.nextVal, (select id from propertydomains where domainname='所属部门'), 5, 6, '组织部(老干局)', 'zzblgj', 'zuzhibulaoganju', 'admin', sysdate);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, CREATEDATE)
values (s_propertydicts.nextVal, (select id from propertydomains where domainname='所属部门'), 6, 7, '宣传部', 'xcb', 'xuanchuanbu', 'admin', sysdate);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, CREATEDATE)
values (s_propertydicts.nextVal, (select id from propertydomains where domainname='所属部门'), 7, 8, '统战部', 'tzb', 'tongzhanbu', 'admin', sysdate);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, CREATEDATE)
values (s_propertydicts.nextVal, (select id from propertydomains where domainname='所属部门'), 8, 9, '政法委', 'zfw', 'zhengfawei', 'admin', sysdate);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, CREATEDATE)
values (s_propertydicts.nextVal, (select id from propertydomains where domainname='所属部门'), 9, 10, '编办', 'bb', 'bianban', 'admin', sysdate);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, CREATEDATE)
values (s_propertydicts.nextVal, (select id from propertydomains where domainname='所属部门'), 10, 11, '机关工委', 'jggw', 'jiguangongwei', 'admin', sysdate);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, CREATEDATE)
values (s_propertydicts.nextVal, (select id from propertydomains where domainname='所属部门'), 11, 12, '信访和群众工作局', 'xfhqzgzj', 'xinfanghequnzhonggon', 'admin', sysdate);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, CREATEDATE)
values (s_propertydicts.nextVal, (select id from propertydomains where domainname='所属部门'), 12, 13, '党校', 'dx', 'dangxiao', 'admin', sysdate);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, CREATEDATE)
values (s_propertydicts.nextVal, (select id from propertydomains where domainname='所属部门'), 13, 14, '规服办', 'gfb', 'guifuban', 'admin', sysdate);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, CREATEDATE)
values (s_propertydicts.nextVal, (select id from propertydomains where domainname='所属部门'), 14, 15, '法院', 'fy', 'fayuan', 'admin', sysdate);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, CREATEDATE)
values (s_propertydicts.nextVal, (select id from propertydomains where domainname='所属部门'), 15, 16, '检察院', 'jcy', 'jianchayuan', 'admin', sysdate);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, CREATEDATE)
values (s_propertydicts.nextVal, (select id from propertydomains where domainname='所属部门'), 16, 17, '发改局', 'fgj', 'fagaiju', 'admin', sysdate);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, CREATEDATE)
values (s_propertydicts.nextVal, (select id from propertydomains where domainname='所属部门'), 17, 18, '物价局', 'wjj', 'wujiaju', 'admin', sysdate);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, CREATEDATE)
values (s_propertydicts.nextVal, (select id from propertydomains where domainname='所属部门'), 18, 19, '教育局', 'jyj', 'jiaoyuju', 'admin', sysdate);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, CREATEDATE)
values (s_propertydicts.nextVal, (select id from propertydomains where domainname='所属部门'), 19, 20, '经科局', 'jkj', 'jingkeju', 'admin', sysdate);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, CREATEDATE)
values (s_propertydicts.nextVal, (select id from propertydomains where domainname='所属部门'), 20, 21, '民政局', 'mzj', 'minzhengju', 'admin', sysdate);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, CREATEDATE)
values (s_propertydicts.nextVal, (select id from propertydomains where domainname='所属部门'), 21, 22, '司法局', 'sfj', 'sifaju', 'admin', sysdate);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, CREATEDATE)
values (s_propertydicts.nextVal, (select id from propertydomains where domainname='所属部门'), 22, 23, '财政局', 'czj', 'caizhengju', 'admin', sysdate);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, CREATEDATE)
values (s_propertydicts.nextVal, (select id from propertydomains where domainname='所属部门'), 23, 24, '人社局', 'rsj', 'rensheju', 'admin', sysdate);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, CREATEDATE)
values (s_propertydicts.nextVal, (select id from propertydomains where domainname='所属部门'), 24, 25, '环保局', 'hbj', 'huanbaoju', 'admin', sysdate);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, CREATEDATE)
values (s_propertydicts.nextVal, (select id from propertydomains where domainname='所属部门'), 25, 26, '建设局', 'jsj', 'jiansheju', 'admin', sysdate);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, CREATEDATE)
values (s_propertydicts.nextVal, (select id from propertydomains where domainname='所属部门'), 26, 27, '房管局', 'fgj', 'fangguanju', 'admin', sysdate);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, CREATEDATE)
values (s_propertydicts.nextVal, (select id from propertydomains where domainname='所属部门'), 27, 28, '城管局', 'cgj', 'chengguanju', 'admin', sysdate);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, CREATEDATE)
values (s_propertydicts.nextVal, (select id from propertydomains where domainname='所属部门'), 28, 29, '交通市政局', 'jtszj', 'jiaotongshizhengju', 'admin', sysdate);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, CREATEDATE)
values (s_propertydicts.nextVal, (select id from propertydomains where domainname='所属部门'), 29, 30, '统筹局', 'tcj', 'tongchouju', 'admin', sysdate);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, CREATEDATE)
values (s_propertydicts.nextVal, (select id from propertydomains where domainname='所属部门'), 30, 31, '商务局', 'swj', 'shangwuju', 'admin', sysdate);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, CREATEDATE)
values (s_propertydicts.nextVal, (select id from propertydomains where domainname='所属部门'), 31, 32, '投促局', 'tcj', 'toucuju', 'admin', sysdate);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, CREATEDATE)
values (s_propertydicts.nextVal, (select id from propertydomains where domainname='所属部门'), 32, 33, '旅体局', 'ltj', 'lvtiju', 'admin', sysdate);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, CREATEDATE)
values (s_propertydicts.nextVal, (select id from propertydomains where domainname='所属部门'), 33, 34, '卫生局', 'wsj', 'weishengju', 'admin', sysdate);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, CREATEDATE)
values (s_propertydicts.nextVal, (select id from propertydomains where domainname='所属部门'), 34, 35, '人口计生局', 'rkjsj', 'renkoujishengju', 'admin', sysdate);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, CREATEDATE)
values (s_propertydicts.nextVal, (select id from propertydomains where domainname='所属部门'), 35, 36, '审计局', 'sjj', 'shenjiju', 'admin', sysdate);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, CREATEDATE)
values (s_propertydicts.nextVal, (select id from propertydomains where domainname='所属部门'), 36, 37, '文广新局', 'wgxj', 'wenguangxinju', 'admin', sysdate);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, CREATEDATE)
values (s_propertydicts.nextVal, (select id from propertydomains where domainname='所属部门'), 37, 38, '安监局', 'ajj', 'anjianju', 'admin', sysdate);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, CREATEDATE)
values (s_propertydicts.nextVal, (select id from propertydomains where domainname='所属部门'), 38, 39, '统计局', 'tjj', 'tongjiju', 'admin', sysdate);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, CREATEDATE)
values (s_propertydicts.nextVal, (select id from propertydomains where domainname='所属部门'), 39, 40, '公安分局', 'gafj', 'gonganfenju', 'admin', sysdate);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, CREATEDATE)
values (s_propertydicts.nextVal, (select id from propertydomains where domainname='所属部门'), 40, 41, '工商局', 'gsj', 'gongshangju', 'admin', sysdate);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, CREATEDATE)
values (s_propertydicts.nextVal, (select id from propertydomains where domainname='所属部门'), 41, 42, '质监局', 'zjj', 'zhijianju', 'admin', sysdate);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, CREATEDATE)
values (s_propertydicts.nextVal, (select id from propertydomains where domainname='所属部门'), 42, 43, '食药监局', 'syjj', 'shiyaojianju', 'admin', sysdate);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, CREATEDATE)
values (s_propertydicts.nextVal, (select id from propertydomains where domainname='所属部门'), 43, 44, '国税局', 'gsj', 'guoshuiju', 'admin', sysdate);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, CREATEDATE)
values (s_propertydicts.nextVal, (select id from propertydomains where domainname='所属部门'), 44, 45, '地税局', 'dsj', 'dishuiju', 'admin', sysdate);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, CREATEDATE)
values (s_propertydicts.nextVal, (select id from propertydomains where domainname='所属部门'), 45, 46, '国土分局', 'gtfj', 'guotufenju', 'admin', sysdate);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, CREATEDATE)
values (s_propertydicts.nextVal, (select id from propertydomains where domainname='所属部门'), 46, 47, '机关事务局', 'jgswj', 'jiguanshiwuju', 'admin', sysdate);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, CREATEDATE)
values (s_propertydicts.nextVal, (select id from propertydomains where domainname='所属部门'), 47, 48, '档案局', 'daj', 'danganju', 'admin', sysdate);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, CREATEDATE)
values (s_propertydicts.nextVal, (select id from propertydomains where domainname='所属部门'), 48, 49, '统建办', 'tjb', 'tongjianban', 'admin', sysdate);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, CREATEDATE)
values (s_propertydicts.nextVal, (select id from propertydomains where domainname='所属部门'), 49, 50, '危改办', 'wgb', 'weigaiban', 'admin', sysdate);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, CREATEDATE)
values (s_propertydicts.nextVal, (select id from propertydomains where domainname='所属部门'), 50, 51, '新客站商旅城管委会', 'xkzslcgwh', 'xinkezhanshanglvchen', 'admin', sysdate);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, CREATEDATE)
values (s_propertydicts.nextVal, (select id from propertydomains where domainname='所属部门'), 51, 52, '龙潭总部经济城管委会', 'ltzbjjcgwh', 'longtanzongbujingjic', 'admin', sysdate);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, CREATEDATE)
values (s_propertydicts.nextVal, (select id from propertydomains where domainname='所属部门'), 52, 53, '北湖管委会', 'bhgwh', 'beihuguanweihui', 'admin', sysdate);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, CREATEDATE)
values (s_propertydicts.nextVal, (select id from propertydomains where domainname='所属部门'), 53, 54, '北改办', 'bgb', 'beigaiban', 'admin', sysdate);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, CREATEDATE)
values (s_propertydicts.nextVal, (select id from propertydomains where domainname='所属部门'), 54, 55, '建设路商圈推进办', 'jslsqtjb', 'jianshelushangquantu', 'admin', sysdate);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, CREATEDATE)
values (s_propertydicts.nextVal, (select id from propertydomains where domainname='所属部门'), 55, 56, '昭觉寺推进办', 'zjstjb', 'zhaojuesituijinban', 'admin', sysdate);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, CREATEDATE)
values (s_propertydicts.nextVal, (select id from propertydomains where domainname='所属部门'), 56, 57, '工商联', 'gsl', 'gongshanglian', 'admin', sysdate);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, CREATEDATE)
values (s_propertydicts.nextVal, (select id from propertydomains where domainname='所属部门'), 57, 58, '总工会', 'zgh', 'zonggonghui', 'admin', sysdate);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, CREATEDATE)
values (s_propertydicts.nextVal, (select id from propertydomains where domainname='所属部门'), 58, 59, '妇联', 'fl', 'fulian', 'admin', sysdate);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, CREATEDATE)
values (s_propertydicts.nextVal, (select id from propertydomains where domainname='所属部门'), 59, 60, '团区委', 'tqw', 'tuanquwei', 'admin', sysdate);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, CREATEDATE)
values (s_propertydicts.nextVal, (select id from propertydomains where domainname='所属部门'), 60, 61, '残联', 'cl', 'canlian', 'admin', sysdate);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, CREATEDATE)
values (s_propertydicts.nextVal, (select id from propertydomains where domainname='所属部门'), 61, 62, '红十字会', 'hszh', 'hongshizihui', 'admin', sysdate);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, CREATEDATE)
values (s_propertydicts.nextVal, (select id from propertydomains where domainname='所属部门'), 62, 63, '科协', 'kx', 'kexie', 'admin', sysdate);

create sequence S_TOWNPARTYORG
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;
  
--  街道社区党组织表
create table town_party_org
(
  id                  NUMBER(10) not null,
  orgId               NUMBER(10) not null,
  orgInternalCode     VARCHAR2(32),
  name                VARCHAR2(60) not null,
  type                NUMBER(10) not null,
  foundDate           DATE,
  secretary           VARCHAR2(60),
  idCardNo            VARCHAR2(18),
  telephone           VARCHAR2(20),
  mobileNumber        VARCHAR2(20),
  address             VARCHAR2(90),
  CREATEUSER          VARCHAR2(60) not null,
  UPDATEUSER          VARCHAR2(60),
  CREATEDATE          DATE not null,
  UPDATEDATE          DATE,
  constraint town_party_org primary key (ID)
);
-- Add comments to the table 
comment on table town_party_org
  is '街道社区党组织表';
-- Add comments to the columns 
comment on column town_party_org.id
  is '主键ID';
comment on column town_party_org.name
  is '党组织名称';
comment on column town_party_org.type
  is '党组织类型';
comment on column town_party_org.foundDate
  is '成立时间';
comment on column town_party_org.secretary
  is '党支部书记';
comment on column town_party_org.idCardNo
  is '身份证号码';
comment on column town_party_org.mobileNumber 
  is '联系手机';
comment on column town_party_org.telephone
  is '联系电话';
comment on column town_party_org.address
  is '党组织地址';

create sequence S_NEWPARTYORG
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;
  
--  两新组织党组织表
create table new_party_org
(
  id                  NUMBER(10) not null,
  orgId               NUMBER(10) not null,
  orgInternalCode     VARCHAR2(32),
  name                VARCHAR2(60) not null,
  type                NUMBER(10) not null,
  address             VARCHAR2(90),
  remark              VARCHAR2(600),
  CREATEUSER          VARCHAR2(60) not null,
  UPDATEUSER          VARCHAR2(60),
  CREATEDATE          DATE not null,
  UPDATEDATE          DATE,
  constraint new_party_org primary key (ID)
);
-- Add comments to the table 
comment on table new_party_org
  is '两新组织党组织表';
-- Add comments to the columns 
comment on column new_party_org.id
  is '主键ID';
comment on column new_party_org.name
  is '组织名称';
comment on column new_party_org.type
  is '党组织类别';
comment on column new_party_org.address
  is '地址';
comment on column new_party_org.remark
  is '备注';
  
create sequence S_PARTYORGMEMBER
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;
  
--  党组织支部成员表
create table party_org_member
(
  id                  NUMBER(10) not null,
  orgId               NUMBER(10) not null,
  orgInternalCode     VARCHAR2(32),
  partyOrgId          NUMBER(10),
  name                VARCHAR2(60) not null,
  telephone           VARCHAR2(20),
  mobileNumber        VARCHAR2(20),
  CREATEUSER          VARCHAR2(60) not null,
  UPDATEUSER          VARCHAR2(60),
  CREATEDATE          DATE not null,
  UPDATEDATE          DATE,
  constraint party_org_member primary key (ID)
);
-- Add comments to the table 
comment on table party_org_member
  is '党组织支部成员表';
-- Add comments to the columns 
comment on column party_org_member.id
  is '主键ID';
comment on column party_org_member.partyOrgId
  is '党组织表id';
comment on column party_org_member.name
  is '姓名';
comment on column party_org_member.mobileNumber 
  is '联系手机';
comment on column party_org_member.telephone
  is '联系电话';
  
--党组织类型
insert into propertydomains (ID, DOMAINNAME, SYSTEMSENSITIVE, SYSTEMRESTRICT)
values (s_propertydomains.nextVal, '党组织类型', 0, null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, CREATEDATE)
values (s_propertydicts.nextVal, (select id from propertydomains where domainname='党组织类型'), 0, 1, '党委', 'dw', 'dangwei', 'admin', sysdate);
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, CREATEDATE)
values (s_propertydicts.nextVal, (select id from propertydomains where domainname='党组织类型'), 1, 2, '党总支', 'dzz', 'dangzongzh', 'admin', sysdate);
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, CREATEDATE)
values (s_propertydicts.nextVal, (select id from propertydomains where domainname='党组织类型'), 2, 3, '党支部', 'dzb', 'dangzhibu', 'admin', sysdate);

alter table HANDICAPPEDS modify DISABILITYCARDNO VARCHAR2(90);
alter table DM_HANDICAPPEDS_TEMP modify DISABILITYCARDNO VARCHAR2(90);


insert into propertydomains (ID, DOMAINNAME, SYSTEMSENSITIVE, SYSTEMRESTRICT)
values (s_propertydomains.nextVal, '民主评议情况', 0, '');

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextVal, (select id from propertydomains where domainname='民主评议情况'), 0, 1, '优秀', 'yx', 'youxiu', 
       'admin', '', sysdate, null);
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextVal, (select id from propertydomains where domainname='民主评议情况'), 1, 2, '合格', 'hg', 'hege', 
       'admin', '', sysdate, null);
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextVal, (select id from propertydomains where domainname='民主评议情况'), 2, 3, '不合格', 'bhg', 'buhege', 
       'admin', '', sysdate, null);
       
commit;
