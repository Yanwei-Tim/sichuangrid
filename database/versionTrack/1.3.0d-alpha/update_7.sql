-- Create sequence
create sequence S_supervisionAdministration
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;
 
create table supervisionAdministration(
       id                   number(10)                      not null,
       orgId                number(10)                      not null,
       orgCode              varchar2(32)                    not null,
       content              clob,
       createUser           varchar2(32)                    not null,
       updateUser           varchar2(32),
       createDate           DATE                            not null,
       updateDate           DATE,
       constraint pkSupervisionAdministration primary key (id)
);
comment on table supervisionAdministration is
'日常监督管理制度表';
comment on column supervisionAdministration.id is
'ID';
comment on column supervisionAdministration.orgId is
'组织机构ID';
comment on column supervisionAdministration.orgCode is
'组织机构Code';
comment on column supervisionAdministration.content is
'内容';
comment on column supervisionAdministration.createUser is
'创建人';
comment on column supervisionAdministration.updateUser is
'修改人';
comment on column supervisionAdministration.createDate is
'创建时间';
comment on column supervisionAdministration.updateDate is
'修改时间';

alter table issuerelatedpeople add fixPhone VARCHAR2(15); 

