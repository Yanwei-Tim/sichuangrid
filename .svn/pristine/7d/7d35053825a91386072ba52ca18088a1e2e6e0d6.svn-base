/*==============================================================*/
/* 四肢队伍表                                 */
/*==============================================================*/
create table FOURTEAMS  (
   ID                   NUMBER(10)                      not null,
   ORGID                NUMBER(10),
   ORGCODE				VARCHAR2(32),
   NAMES                VARCHAR2(150)                    not null,
   FULLPINYIN           VARCHAR2(30)                    not null,
   SIMPLEPINYIN         VARCHAR2(10)                    not null,
   INDEXID              NUMBER(10),
   PARENTTEAMID			NUMBER(10),
   SUBTEAMNUMBER        NUMBER(10)          default 0,
   MEMBERNUMBER         NUMBER(10)  default 0,
   TEAMTYPE        VARCHAR2(32),
   COMMENTS        VARCHAR2(300),
   DEPARTEMENTID    NUMBER(10),
   DEPARTEMENTNAME VARCHAR2(32),
   CREATEUSER           VARCHAR2(32)                    not null,
   UPDATEUSER           VARCHAR2(32),
   CREATEDATE           DATE                            not null,
   UPDATEDATE           DATE,
   constraint PKFOURTEAMS primary key (ID)
);

comment on table FOURTEAMS is
'四支队伍表';

comment on column FOURTEAMS.ID is
'父队伍号';

comment on column FOURTEAMS.ORGID is
'网格ID';

comment on column FOURTEAMS.ORGCODE is
'所属网格编号';

comment on column FOURTEAMS.NAMES is
'队伍名';

comment on column FOURTEAMS.FULLPINYIN is
'队伍名全拼';

comment on column FOURTEAMS.SIMPLEPINYIN is
'队伍名简拼';

comment on column FOURTEAMS.INDEXID is
'排序号';

comment on column FOURTEAMS.SUBTEAMNUMBER is
'子队伍数';

comment on column FOURTEAMS.MEMBERNUMBER is
'成员数';

comment on column FOURTEAMS.TEAMTYPE is
'队伍类型';

comment on column FOURTEAMS.COMMENTS is
'备注';

create sequence S_FOURTEAMS
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;
commit;
 /*==============================================================*/
/* 队伍成员表                                    */
/*==============================================================*/
create table FOURTEAMMEMBERS  (
   ID                   NUMBER(10)                      not null,
   ORGID                NUMBER(10),
   ORGCODE        VARCHAR2(32),
   NAMES                 VARCHAR2(32)                    not null,
   FULLPINYIN           VARCHAR2(30)                    not null,
   SIMPLEPINYIN         VARCHAR2(10)                    not null,
   TEAMID        NUMBER(10)                      not null,
   DUTY              VARCHAR2(32),
   GENDER               NUMBER(10)                      default 3 not null,
   BIRTHDAY             DATE,
   MOBILE               VARCHAR2(20),
   TELEPHONE            VARCHAR2(20),
   SPECIALTY            VARCHAR2(20),
   ORGADMINNAME         VARCHAR2(20),
   POLITICS             NUMBER(10),
   ORGADMINTELEPHONE    VARCHAR2(20),
   COMMENTS        VARCHAR2(300),
   CREATEUSER           VARCHAR2(32)                    not null,
   UPDATEUSER           VARCHAR2(32),
   CREATEDATE           DATE                            not null,
   UPDATEDATE           DATE,
   constraint PKFOURTEAMMEMBERS primary key (ID)
);

comment on table FOURTEAMMEMBERS is
'四支队伍成员表';

comment on column FOURTEAMMEMBERS.ORGID is
'网格ID';

comment on column FOURTEAMMEMBERS.ORGCODE is
'所属网格编号';

comment on column FOURTEAMMEMBERS.NAMES is
'成员名';

comment on column FOURTEAMMEMBERS.FULLPINYIN is
'成员名全拼';

comment on column FOURTEAMMEMBERS.SIMPLEPINYIN is
'成员名简拼';

comment on column FOURTEAMMEMBERS.TEAMID is
'队伍号';

comment on column FOURTEAMMEMBERS.DUTY is
'职务';

comment on column FOURTEAMMEMBERS.GENDER is
'性别';

comment on column FOURTEAMMEMBERS.BIRTHDAY is
'出生年月';

comment on column FOURTEAMMEMBERS.MOBILE is
'手机号';

comment on column FOURTEAMMEMBERS.TELEPHONE is
'电话号';

comment on column FOURTEAMMEMBERS.SPECIALTY is
'特长';

comment on column FOURTEAMMEMBERS.ORGADMINNAME is
'网格管理员名';

comment on column FOURTEAMMEMBERS.POLITICS is
'政治面貌';

comment on column FOURTEAMMEMBERS.ORGADMINTELEPHONE is
'网格管理员电话号';

comment on column FOURTEAMMEMBERS.COMMENTS is
'备注';

create sequence S_FOURTEAMMEMBERS
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;
commit;

insert into FOURTEAMS(ID,ORGID,ORGCODE,NAMES,FULLPINYIN,SIMPLEPINYIN,INDEXID,PARENTTEAMID,SUBTEAMNUMBER,MEMBERNUMBER,TEAMTYPE,COMMENTS,CREATEUSER,UPDATEUSER,
CREATEDATE,UPDATEDATE)values(S_FOURTEAMS.NEXTVAL,1,'.','基层便民专业化服务队','jicengbianminzhuanyehuafuwudui','jcbmzyhfwd',1,0,0,0,'convenienceServiceTeam',
'基层便民专业化服务队','admin',null,SYSDATE,null);
   
insert into FOURTEAMS(ID,ORGID,ORGCODE,NAMES,FULLPINYIN,SIMPLEPINYIN,INDEXID,PARENTTEAMID,SUBTEAMNUMBER,MEMBERNUMBER,TEAMTYPE,COMMENTS,CREATEUSER,UPDATEUSER,
CREATEDATE,UPDATEDATE)values(S_FOURTEAMS.NEXTVAL,1,'.','网格员服务队','cunwanggeyuanfuwudui','cwgyfwd',2,0,0,0,'gridServiceTeam', '网格员服务队', 'admin',
null,SYSDATE,null);

insert into FOURTEAMS(ID,ORGID, ORGCODE, NAMES,FULLPINYIN,SIMPLEPINYIN,INDEXID,PARENTTEAMID,SUBTEAMNUMBER,MEMBERNUMBER,TEAMTYPE,COMMENTS,CREATEUSER,UPDATEUSER,
CREATEDATE, UPDATEDATE)values(S_FOURTEAMS.NEXTVAL, 1,'.','党员先锋服务队','dangyuanxianfengfuwudui', 'dyxffwd', 3, 0, 0, 0, 'partyMemberPioneerServiceTeam',
'党员先锋服务队', 'admin', null, SYSDATE, null);

insert into FOURTEAMS(ID, ORGID, ORGCODE, NAMES, FULLPINYIN, SIMPLEPINYIN, INDEXID, PARENTTEAMID, SUBTEAMNUMBER, MEMBERNUMBER, TEAMTYPE, COMMENTS, CREATEUSER,
UPDATEUSER, CREATEDATE, UPDATEDATE)values
(S_FOURTEAMS.NEXTVAL, 1,  '.', '社会志愿者服务队', 'shehuizhiyuanzhefuwudui', 'shzyzfwd', 4, 0, 0, 0, 'communityVolunteerServiceTeam', '社会志愿者服务队',
'admin', null, SYSDATE, null);

commit ;
