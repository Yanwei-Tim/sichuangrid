 /*==============================================================*/
/* Sequence: s_aidsPopulations         艾滋病人员序列                          */
/*==============================================================*/
create sequence s_aidsPopulations
 increment by 1
 start with 1
 minvalue 1
 cache 20
 maxvalue 9999999999;
 /*==============================================================*/
/* Table: aidsPopulations              艾滋病人员信息表                     */
/*==============================================================*/
create table AIDSPOPULATIONS 
(
   ID                   NUMBER(10)           not null,
   BASEINFOID           NUMBER(10),
   ADDRESSINFOID        NUMBER(10),
   ORGID                NUMBER(10)           not null,
   ORGINTERNALCODE      VARCHAR2(50),
   ADDRESSNO            VARCHAR2(60),
   INFECTWAY            NUMBER(10),
   VIOLATIONSOFTHELAW   NUMBER(10),
   CRIMETYPE            NUMBER(10),
   RECEIVEDORGANIZATION VARCHAR2(60),
   RECEIVEDLEVEL        NUMBER(10),
   HELPCIRCUMSTANCES    NUMBER(10),
   ACTUALPOPULATIONTYPE VARCHAR2(50),
   SOURCESSTATE         NUMBER(1)            default 1,
   ATTENTIONEXTENT      NUMBER(10),
   ISEMPHASIS           NUMBER(1)            default 0,
   ISEMPHASISREASON     VARCHAR2(300),
   ISEMPHASISDATE       DATE,
   CREATEUSER           VARCHAR2(32)         not null,
   UPDATEUSER           VARCHAR2(32),
   CREATEDATE           DATE                 not null,
   UPDATEDATE           DATE,
   constraint PKAIDSPOPULATIONS primary key (ID),
   constraint fkAidsPopulationsOrg foreign key (orgId)
         references organizations (id)  
);

comment on table AIDSPOPULATIONS is
'艾滋病人员';

comment on column AIDSPOPULATIONS.ID is
'ID';

comment on column AIDSPOPULATIONS.ORGID is
'所属网格id';

comment on column AIDSPOPULATIONS.ORGINTERNALCODE is
'所属网格code';

comment on column AIDSPOPULATIONS.ADDRESSNO is
'地址编号';

comment on column AIDSPOPULATIONS.INFECTWAY is
'感染途径';

comment on column AIDSPOPULATIONS.VIOLATIONSOFTHELAW is
'违法情况';

comment on column AIDSPOPULATIONS.CRIMETYPE is
'犯罪类型';

comment on column AIDSPOPULATIONS.RECEIVEDORGANIZATION is
'收治机构';

comment on column AIDSPOPULATIONS.RECEIVEDLEVEL is
'收治机构所属层级';

comment on column AIDSPOPULATIONS.HELPCIRCUMSTANCES is
'帮扶情况';

comment on column AIDSPOPULATIONS.ACTUALPOPULATIONTYPE is
'实口类型';

comment on column AIDSPOPULATIONS.SOURCESSTATE is
'数据来源：1.录入；2.认领；3.已核实';

comment on column AIDSPOPULATIONS.ATTENTIONEXTENT is
'关注程度';

comment on column AIDSPOPULATIONS.ISEMPHASIS is
'是否关注';

comment on column AIDSPOPULATIONS.ISEMPHASISREASON is
'关注原因';

comment on column AIDSPOPULATIONS.ISEMPHASISDATE is
'关注日期';

comment on column AIDSPOPULATIONS.CREATEUSER is
'创建用户';

comment on column AIDSPOPULATIONS.UPDATEUSER is
'修改用户';

comment on column AIDSPOPULATIONS.CREATEDATE is
'创建日期';

comment on column AIDSPOPULATIONS.UPDATEDATE is
'修改时间';

/*==============================================================*/
/* index: index_Ai_OrgCodeAndIsemphasis                           */
/*==============================================================*/
create index index_Ai_OrgCodeAndIsemphasis on AIDSPOPULATIONS (ORGINTERNALCODE, ISEMPHASIS);
create index IDX_AIDSPOPULATION_BASEINFOID on AIDSPOPULATIONS (BASEINFOID);
create index IDX_AIDSPOPULATION_ADSID on AIDSPOPULATIONS (ADDRESSINFOID);