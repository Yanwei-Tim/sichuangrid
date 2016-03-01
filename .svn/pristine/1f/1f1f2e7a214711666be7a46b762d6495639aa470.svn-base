--去掉未落户户口表 UNSETTLEDPOPULATIONS 中的ORGID与IDCARDID唯一约束
drop index IDX_U_ORGIDANDIDCARDNO;

--创建 通知通报与岗位对应关系表
create table publicNoticeRole(
       noticeId number(10),
       roleId number(10)
);
alter table publicNoticeRole add constraints PK_publicNoticeRole primary key (noticeId, roleId);

--创建 通知通报与用户对应关系表，用户已读通知通报存放。
create table publicNoticeUser(
       noticeId number(10),
       userId number(10),
       readTime Date default sysdate
);
alter table publicNoticeUser add constraints PK_publicNoticeUser primary key (noticeId, userId);

comment on column publicNoticeUser.readTime
  is '读取时间';
  
--创建 通知通报与组织机构对应关系表
create table publicNoticeOrganizations(
       noticeId number(10),
       orgId varchar2(30)
);
alter table publicNoticeOrganizations add constraints PK_publicNoticeOrganizations primary key (noticeId, orgId);
