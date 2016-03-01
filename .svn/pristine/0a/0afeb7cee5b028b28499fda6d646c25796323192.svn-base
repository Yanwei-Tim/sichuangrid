create table redCuffTeamMember(
id number(10) not null,
orgId number(10) not null,
orgCode varchar2(32) not null,
memeberName varchar2(32),
idCardNo varchar2(20),
gender number(10),
birthDate Date,
phoneNumber varchar2(15),
nation number(10),
teamType number(10),
Political number(10),
education number(10),
occupation varchar2(60),
fimalyAddress varchar2(120),
registeredPerson varchar2(30),
registeredDate Date,
remark varchar2(300),
createUser varchar2(32),
createDate Date,
updateUser varchar2(32),
updateDate Date,
constraint PKredCuffTeamMember primary key (ID)
);

comment on table redCuffTeamMember is
'红袖套队伍管理';
comment on column redCuffTeamMember.orgid is
'所属网格ID';
comment on column redCuffTeamMember.orgCode is
'所属网格code';
comment on column redCuffTeamMember.memeberName is
'红袖套成员名称';
comment on column redCuffTeamMember.idCardNo is
'身份证号码';
comment on column redCuffTeamMember.gender is
'性别';
comment on column redCuffTeamMember.birthDate is
'出生日期';
comment on column redCuffTeamMember.phoneNumber is
'联系方式';
comment on column redCuffTeamMember.nation is
'民族';
comment on column redCuffTeamMember.teamType is
'队伍类型';
comment on column redCuffTeamMember.Political is
'政治面貌';
comment on column redCuffTeamMember.education is
'文化程度';
comment on column redCuffTeamMember.occupation is
'职业';
comment on column redCuffTeamMember.fimalyAddress is
'家庭住址';
comment on column redCuffTeamMember.registeredPerson is
'登记人';
comment on column redCuffTeamMember.registeredDate is
'登记时间';
comment on column redCuffTeamMember.remark is
'备注';

create sequence s_redCuffTeamMember
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;