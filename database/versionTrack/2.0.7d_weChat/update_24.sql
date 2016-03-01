--网格成员表序列
create sequence s_gridTeamMember
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;
--创建网格员表
create table gridTeamMember(
id number(10) not null,
orgId number(10) not null,
orgCode varchar2(32) not null,
memeberName varchar2(32),
idCardNo varchar2(20),
gender number(10),
birthDate Date,
phoneNumber varchar2(15),
positionType number(10),
political number(10),
education number(10),
isActivated number(1),
activatedTime Date,
registeredDate Date,
remark varchar2(300),
userDefinedGridName varchar2(20),
imageUrl varchar2(128),
userId number(10),
createUser varchar2(32),
createDate Date,
updateUser varchar2(32),
updateDate Date,
constraint PKgridTeamMember primary key (ID)
);

comment on table gridTeamMember is
'网格员队伍管理';
comment on column gridTeamMember.orgid is
'所属网格ID';
comment on column gridTeamMember.orgCode is
'所属网格code';
comment on column gridTeamMember.memeberName is
'网格员名称';
comment on column gridTeamMember.idCardNo is
'身份证号码';
comment on column gridTeamMember.gender is
'性别';
comment on column gridTeamMember.birthDate is
'出生日期';
comment on column gridTeamMember.phoneNumber is
'联系方式';
comment on column gridTeamMember.positionType is
'专职/兼职情况';
comment on column gridTeamMember.Political is
'政治面貌';
comment on column gridTeamMember.education is
'文化程度';
comment on column gridTeamMember.isActivated is
'是否激活';
comment on column gridTeamMember.activatedTime is
'激活时间';
comment on column gridTeamMember.registeredDate is
'登记时间';
comment on column gridTeamMember.remark is
'备注';
comment on column gridTeamMember.userDefinedGridName is
'存储系统组织机构中不存在的网格名称';
comment on column gridTeamMember.imageUrl is
'头像地址';









