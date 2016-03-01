--食药工商建表
--政策法规宣传序列
create sequence s_PolicyPropaganda
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;

/*==============================================================*/
/* Table: PolicyPropaganda     政策法规宣传                                */
/*==============================================================*/
create table PolicyPropaganda 
(
   id                   number(10)                     not null,
   inputTime           date,
   address              VARCHAR2(150),
   Category             number(10),
   Details              varchar2(300),
   remake               varchar2(900),
   isSign               number(1) default 0,
   isreply              number(1) default 0,
   signDate             date,
   signpeople           varchar2(30),
   signContent          varchar2(900),
   telephone			varchar2(12),
   createDate          date,
   createUser          varchar2(30),
   updateDate          date,
   updateUser          varchar2(30),
   ORGID                NUMBER(10) not null,
   ORGINTERNALCODE      VARCHAR2(50),
   constraint PK_PolicyPropaganda primary key   (id)
);

comment on table PolicyPropaganda is 
'政策法规宣传';

comment on column PolicyPropaganda.id is 
'编号';

comment on column PolicyPropaganda.inputTime is 
'录入时间';

comment on column PolicyPropaganda.address is 
'地址';

comment on column PolicyPropaganda.Category is 
'类别';

comment on column PolicyPropaganda.Details is 
'详细情况';

comment on column PolicyPropaganda.remake is 
'备注';

comment on column PolicyPropaganda.isSign is 
'是否签收 0：未签收 1：已签收';

comment on column PolicyPropaganda.isreply is 
'是否回复0：未回复1：已回复';

comment on column PolicyPropaganda.signDate is 
'签收时间';

comment on column PolicyPropaganda.signpeople is 
'签收人';

comment on column PolicyPropaganda.signContent is 
'签收意见';

--食品安全协管序列
create sequence s_foodsafty
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;

/*==============================================================*/
/* Table: foodsafty      食品安全协管                                      */
/*==============================================================*/
create table foodsafty 
(
   id                   number(10)                     not null,
   inputTime           date,
   address              VARCHAR2(150),
   Category             number(10),
   Details              varchar2(300),
   Declaration          number(10),
   personnel            varchar2(300),
   remake               varchar2(900),
   isSign               number(1) default 0,
   isreply              number(1) default 0,
   signDate             date,
   signpeople           varchar2(30),
   signContent          varchar2(900),
   telephone			varchar2(12),
   createDate          date,
   createUser          varchar2(30),
   updateDate          date,
   updateUser          varchar2(30),
   ORGID                NUMBER(10) not null,
   ORGINTERNALCODE      VARCHAR2(50),   
   constraint PK_foodsafty primary key   (id)
);

comment on table foodsafty is 
'食品安全协管';

comment on column foodsafty.id is 
'编号';

comment on column foodsafty.inputTime is 
'录入时间';

comment on column foodsafty.address is 
'地点';

comment on column foodsafty.Category is 
'类别';

comment on column foodsafty.Details is 
'情况描述';

comment on column foodsafty.Declaration is 
'5桌以上申报';

comment on column foodsafty.personnel is 
'参与人员';

comment on column foodsafty.remake is 
'备注';

comment on column foodsafty.isSign is 
'是否签收 0：未签收 1：已签收';

comment on column foodsafty.isreply is 
'是否回复0：未回复1：已回复';

comment on column foodsafty.signDate is 
'签收时间';

comment on column foodsafty.signpeople is 
'签收人';

comment on column foodsafty.signContent is 
'签收意见';

--药品安全协管序列
create sequence s_drugssafty
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;

/*==============================================================*/
/* Table: drugssafty        药品安全协管                                   */
/*==============================================================*/
create table drugssafty 
(
   id                   number(10)                     not null,
   inputTime           date,
   address              VARCHAR2(150),
   Category             number(10),
   Details              varchar2(300),
   personnel            varchar2(300),
   remake               varchar2(900),
   isSign               number(1) default 0,
   isreply              number(1) default 0,
   signDate             date,
   signpeople           varchar2(30),
   signContent          varchar2(900),
   telephone			varchar2(12),
   createDate          date,
   createUser          varchar2(30),
   updateDate          date,
   updateUser          varchar2(30),
   ORGID                NUMBER(10) not null,
   ORGINTERNALCODE      VARCHAR2(50),   
   constraint PK_drugssafty primary key   (id)
);

comment on table drugssafty is 
'药品安全协管';

comment on column drugssafty.id is 
'编号';

comment on column drugssafty.inputTime is 
'录入时间';

comment on column drugssafty.address is 
'地点';

comment on column drugssafty.Category is 
'类别';

comment on column drugssafty.Details is 
'情况描述';

comment on column drugssafty.personnel is 
'参与人员';

comment on column drugssafty.remake is 
'备注';

comment on column drugssafty.isSign is 
'是否签收 0：未签收 1：已签收';

comment on column drugssafty.isreply is 
'是否回复0：未回复1：已回复';

comment on column drugssafty.signDate is 
'签收时间';

comment on column drugssafty.signpeople is 
'签收人';

comment on column drugssafty.signContent is 
'签收意见';


--工商行政管理协管序列
create sequence s_Businessmanage
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;

/*==============================================================*/
/* Table: Businessmanage        工商行政管理协管                               */
/*==============================================================*/
create table Businessmanage 
(
   id                   number(10)                     not null,
   inputTime           date,
   address              VARCHAR2(150),
   Category             number(10),
   Details              varchar2(300),
   personnel            varchar2(300),
   remake               varchar2(900),
   isSign               number(1) default 0,
   isreply              number(1) default 0,
   signDate             date,
   signpeople           varchar2(30),
   signContent          varchar2(900),
   telephone			varchar2(12),
   createDate          date,
   createUser          varchar2(30),
   updateDate          date,
   updateUser          varchar2(30),
   ORGID                NUMBER(10) not null,
   ORGINTERNALCODE      VARCHAR2(50),   
   constraint PK_Businessmanage primary key   (id)
);

comment on table Businessmanage is 
'工商行政管理协管';

comment on column Businessmanage.id is 
'编号';

comment on column Businessmanage.inputTime is 
'录入时间';

comment on column Businessmanage.address is 
'地点';

comment on column Businessmanage.Category is 
'类别';

comment on column Businessmanage.Details is 
'情况描述';

comment on column Businessmanage.personnel is 
'参与人员';

comment on column Businessmanage.remake is 
'备注';

comment on column Businessmanage.isSign is 
'是否签收 0：未签收 1：已签收';

comment on column Businessmanage.isreply is 
'是否回复0：未回复1：已回复';

comment on column Businessmanage.signDate is 
'签收时间';

comment on column Businessmanage.signpeople is 
'签收人';

comment on column Businessmanage.signContent is 
'签收意见';

--打击非法传销协管序列
create sequence s_pyramidSalesManage
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;

/*==============================================================*/
/* Table: pyramidSalesManage        打击非法传销协管                          */
/*==============================================================*/
create table pyramidSalesManage 
(
   id                   number(10)                     not null,
   inputTime           date,
   address              VARCHAR2(150),
   Category             number(10),
   pyramidCategory     number(10),
   Details              varchar2(300),
   personnel            varchar2(300),
   remake               varchar2(900),
   isSign               number(1) default 0,
   isreply              number(1) default 0,
   signDate             date,
   signpeople           varchar2(30),
   signContent          varchar2(900),
   telephone			varchar2(12),
   createDate          date,
   createUser          varchar2(30),
   updateDate          date,
   updateUser          varchar2(30),
   ORGID                NUMBER(10) not null,
   ORGINTERNALCODE      VARCHAR2(50),   
   constraint PK_pyramidSalesManage primary key (id)
);

comment on table pyramidSalesManage is 
'打击非法传销协管';

comment on column pyramidSalesManage.id is 
'编号';

comment on column pyramidSalesManage.inputTime is 
'录入时间';

comment on column pyramidSalesManage.address is 
'地点';

comment on column pyramidSalesManage.Category is 
'类别';

comment on column pyramidSalesManage.pyramidCategory is 
'传销类别';

comment on column pyramidSalesManage.Details is 
'情况描述';

comment on column pyramidSalesManage.personnel is 
'参与人员';

comment on column pyramidSalesManage.remake is 
'备注';

comment on column pyramidSalesManage.isSign is 
'是否签收 0：未签收 1：已签收';

comment on column pyramidSalesManage.isreply is 
'是否回复0：未回复1：已回复';

comment on column pyramidSalesManage.signDate is 
'签收时间';

comment on column pyramidSalesManage.signpeople is 
'签收人';

comment on column pyramidSalesManage.signContent is 
'签收意见';


--查处取缔无证无照经营协管序列
create sequence s_unlicensedManage
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;

/*==============================================================*/
/* Table: unlicensedManage       查处取缔无证无照经营协管                              */
/*==============================================================*/
create table unlicensedManage 
(
   id                   number(10)                     not null,
   inputTime           date,
   address              VARCHAR2(150),
   Category             number(10),
   Details              varchar2(300),
   personnel            varchar2(300),
   remake               varchar2(900),
   isSign               number(1) default 0,
   isreply              number(1) default 0,
   signDate             date,
   signpeople           varchar2(30),
   signContent          varchar2(900),
   telephone			varchar2(12),
   createDate          date,
   createUser          varchar2(30),
   updateDate          date,
   updateUser          varchar2(30),
   ORGID                NUMBER(10) not null,
   ORGINTERNALCODE      VARCHAR2(50),   
   constraint PK_unlicensedManage primary key   (id)
);

comment on table unlicensedManage is 
'查处取缔无证无照经营协管';

comment on column unlicensedManage.id is 
'编号';

comment on column unlicensedManage.inputTime is 
'录入时间';

comment on column unlicensedManage.address is 
'地点';

comment on column unlicensedManage.Category is 
'类别';

comment on column unlicensedManage.personnel is 
'参与人员';

comment on column unlicensedManage.Details is 
'情况描述';

comment on column unlicensedManage.remake is 
'备注';

comment on column unlicensedManage.isSign is 
'是否签收 0：未签收 1：已签收';

comment on column unlicensedManage.isreply is 
'是否回复0：未回复1：已回复';

comment on column unlicensedManage.signDate is 
'签收时间';

comment on column unlicensedManage.signpeople is 
'签收人';

comment on column unlicensedManage.signContent is 
'签收意见';

--其它情况序列
create sequence s_otherSituationManage
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;

/*==============================================================*/
/* Table: otherSituationManage     其它情况                           */
/*==============================================================*/
create table otherSituationManage 
(
   id                   number(10)                     not null,
   inputTime           date,
   address              VARCHAR2(150),
   Details              varchar2(300),
   remake               varchar2(900),
   isSign               number(1) default 0,
   isreply              number(1) default 0,
   signDate             date,
   signpeople           varchar2(30),
   signContent          varchar2(900),
   telephone			varchar2(12),
   createDate          date,
   createUser          varchar2(30),
   updateDate          date,
   updateUser          varchar2(30),
   ORGID                NUMBER(10) not null,
   ORGINTERNALCODE      VARCHAR2(50),   
   constraint PK_otherSituationManage primary key   (id)
);

comment on table otherSituationManage is 
'其它情况';

comment on column otherSituationManage.id is 
'编号';

comment on column otherSituationManage.inputTime is 
'录入时间';

comment on column otherSituationManage.address is 
'地点';

comment on column otherSituationManage.Details is 
'详细情况';

comment on column otherSituationManage.remake is 
'备注';

comment on column otherSituationManage.isSign is 
'是否签收 0：未签收 1：已签收';

comment on column otherSituationManage.isreply is 
'是否回复0：未回复1：已回复';

comment on column otherSituationManage.signDate is 
'签收时间';

comment on column otherSituationManage.signpeople is 
'签收人';

comment on column otherSituationManage.signContent is 
'签收意见';

--回复序列
create sequence s_reply
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;

/*==============================================================*/
/* Table: reply            回复                                     */
/*==============================================================*/
create table reply 
(
   id                   number(10)                     not null,
   replyDate            date,
   replypeople          varchar2(30),
   replyContent         varchar2(900),
   serviceId           number(10),
   serviceType         number(2),
   constraint PK_REPLY primary key   (id)
);

comment on table reply is 
'回复';

comment on column reply.id is 
'编号';

comment on column reply.replyDate is 
'回复时间';

comment on column reply.replypeople is 
'回复人';

comment on column reply.replyContent is 
'处理情况';

comment on column reply.serviceId is 
'回复关联id';

comment on column reply.serviceType is 
'食药工商类型';
commit;

--回复附件序列
create sequence s_replyAttch
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;

/*==============================================================*/
/* Table: replyAttch       回复附件                                    */
/*==============================================================*/
create table replyAttch 
(
   id                   number(10)                     not null,
   name                 VARCHAR2(150),
   path                 VARCHAR2(350),
   replyId             number(10),
   serviceType         number(2),
   constraint PK_replyAttch primary key   (id)
);

comment on table replyAttch is 
'回复附件';

comment on column replyAttch.id is 
'编号';

comment on column replyAttch.name is 
'附件名称';

comment on column replyAttch.path is 
'附件路径';

comment on column replyAttch.replyId is 
'关联的回复id';

comment on column replyAttch.serviceType is 
'食药工商类型';

--回复附件序列
create sequence s_ServiceListAttch
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;

/*==============================================================*/
/* Table: ServiceListAttch        食药工商附件                            */
/*==============================================================*/
create table ServiceListAttch 
(
   id                   number(10)                     not null,
   name                 VARCHAR2(150),
   path                 VARCHAR2(350),
   serviceId           number(10),
   serviceType         number(2),
   constraint PK_ServiceListAttch primary key   (id)
);

comment on table ServiceListAttch is 
'食药工商附件';

comment on column ServiceListAttch.id is 
'编号';

comment on column ServiceListAttch.name is 
'附件名称';

comment on column ServiceListAttch.path is 
'附件路径';

comment on column ServiceListAttch.serviceId is 
'回复关联id';

comment on column ServiceListAttch.serviceType is 
'食药工商类型';
commit;
