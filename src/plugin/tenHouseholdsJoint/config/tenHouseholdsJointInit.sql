create sequence s_MESSAGE
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;

CREATE TABLE MESSAGE 
(
   ID                   NUMBER(10),
   TEXT                 VARCHAR2(500),
   MESSAGETYPE          NUMBER(10),
   SENDADDRESS          VARCHAR2(20),
   REQUESTIDENTIFIER    VARCHAR2(32),
   CREATEUSER           VARCHAR2(32),
   CREATEDATE           DATE,
   UPDATEUSER           VARCHAR2(32),
   UPDATEDATE           DATE,
   constraint PKmessage primary key (ID)
);
COMMENT ON TABLE MESSAGE
  is '短信信息表';
COMMENT ON COLUMN MESSAGE.TEXT IS
'消息内容';
COMMENT ON COLUMN MESSAGE.MESSAGETYPE IS
'消息类型：0预警、1通知';
COMMENT ON COLUMN MESSAGE.SENDADDRESS IS
'发送地址';
COMMENT ON COLUMN MESSAGE.REQUESTIDENTIFIER IS
'标识一个特定的短消息发送请求， 最大长度 32 位；编码格式：SAG 设备号（6 位）＋预留位"0"+厂商内部编码';

create sequence s_ReceiveMsgInfo
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;

CREATE TABLE RECEIVEMSGINFO
(
   ID                   NUMBER(10),
   MESSAGEID            NUMBER(10),
   ORGID                NUMBER(10),
   ORGCODE              VARCHAR2(32),
   USERID               NUMBER(10),
   TEAMID               NUMBER(10),
   SENDDATE             DATE,
   ISDEAL               NUMBER(1)                default 0,
   ISREPORT             NUMBER(1)              default 0,
   CREATEUSER           VARCHAR2(32),
   CREATEDATE           DATE,
   UPDATEUSER           VARCHAR2(32),
   UPDATEDATE           DATE,
   constraint PKreceivemsginfo primary key (ID)
);
COMMENT ON TABLE RECEIVEMSGINFO
  is '收件箱信息表';
COMMENT ON COLUMN RECEIVEMSGINFO.MESSAGEID IS '消息ID';
COMMENT ON COLUMN RECEIVEMSGINFO.USERID IS '目标用户ID';
COMMENT ON COLUMN RECEIVEMSGINFO.TEAMID IS '目标用户所在组ID';
COMMENT ON COLUMN RECEIVEMSGINFO.SENDDATE IS '发送时间';
COMMENT ON COLUMN RECEIVEMSGINFO.ISDEAL IS '是否受理';
COMMENT ON COLUMN RECEIVEMSGINFO.ISREPORT IS '是否通知其它组员';

create sequence s_sendMsgInfo
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;

CREATE TABLE SENDMSGINFO
(
   ID                   NUMBER(10),
   MESSAGEID            NUMBER(10),
   ORGID                NUMBER(10),
   ORGCODE              VARCHAR2(32),
   USERID               NUMBER(10),
   TEAMID               NUMBER(10),
   SENDDATE             DATE,
   ISREPORT               number(1)                 default 0,
   CREATEUSER           VARCHAR2(32),
   CREATEDATE           DATE,
   UPDATEUSER           VARCHAR2(32),
   UPDATEDATE           DATE,
   constraint PKsendmsginfo primary key (ID)
);
COMMENT ON TABLE SENDMSGINFO
  is '发件箱信息表';
COMMENT ON COLUMN SENDMSGINFO.MESSAGEID IS '消息ID';
COMMENT ON COLUMN SENDMSGINFO.USERID IS '目标用户ID';
COMMENT ON COLUMN SENDMSGINFO.TEAMID IS '目标用户所在组ID';
COMMENT ON COLUMN SENDMSGINFO.SENDDATE IS '发送时间';
COMMENT ON COLUMN SENDMSGINFO.ISREPORT IS '是否通知到';

create sequence s_FamilyTeam
minvalue 1
maxvalue 999999999999999
start with 1
increment by 1;

create table FamilyTeam 
(
   ID                      NUMBER(10)           not null,
   TEAMCODE                VARCHAR2(60)         not null,
   TEAMNAME                VARCHAR2(60)         not null,
   HOUSEHOLDS              NUMBER(10)           not null,
   HOUSEMASTER             VARCHAR2(60)         not null,
   HOUSEMASTCERTIFICATENO  VARCHAR2(60)         not null,
   ALARMCENTER             NUMBER(10)           not null,
   ORGID                   NUMBER(10)	    	not null,
   ORGINTERNALCODE         VARCHAR2(50),
   CREATEUSER              VARCHAR2(32)         not null,
   UPDATEUSER              VARCHAR2(32),
   CREATEDATE              DATE                 not null,
   UPDATEDATE              DATE,
   constraint PKFAMILYTEAM primary key (ID)
);
  
comment on table FamilyTeam is
'十户分组表';
comment on column FamilyTeam.ID
  is '十户分组表ID';
comment on column FamilyTeam.TEAMCODE
  is '分组编码';
comment on column FamilyTeam.TEAMNAME
  is '分组名称';
comment on column FamilyTeam.HOUSEHOLDS
  is '户数';
comment on column FamilyTeam.HOUSEMASTER
  is '联户长';
comment on column FamilyTeam.HOUSEMASTCERTIFICATENO
  is '联户长证件号';
comment on column FamilyTeam.ALARMCENTER
  is '接警中心';
comment on column FamilyTeam.ORGID
  is '组织机构ID';
comment on column FamilyTeam.ORGINTERNALCODE
  is '组织机构内置编号';
comment on column FamilyTeam.CREATEUSER
  is '创建用户';
comment on column FamilyTeam.UPDATEUSER
  is '修改用户';
comment on column FamilyTeam.CREATEDATE
  is '创建时间';
comment on column FamilyTeam.UPDATEDATE
  is '修改时间';
  



create sequence s_familyInfo
minvalue 1
maxvalue 999999999999999
start with 1
increment by 1;
/*==============================================================*/
/* Table: familyInfo   十户联防家庭表                                        */
/*==============================================================*/
create table familyInfo(
        id                    NUMBER(10)                      not null,
        name                  VARCHAR2(150)                  not null,
        orgId                 NUMBER(10)                     not null,
        orgInternalCode       VARCHAR2(32)       not null,
        teamId                NUMBER(10)                     not null,
        teamName        VARCHAR2(60)         not null,
        helpLine              VARCHAR2(33)                   not null,
        certificateNumber     VARCHAR2(60)                   not null,
        alarmCenter           NUMBER(10)                     not null,       
        familyAddress         VARCHAR2(90)                   not null,
        familyState           NUMBER(10)                     not null,
        policeRoom            NUMBER(10)                     not null,     
        SMSNumber             VARCHAR2(600),
        voiceNumber           VARCHAR2(600),
        isInformOther         NUMBER(1),
        isReceiveOtherAlarm   NUMBER(1),
        isCallOther           NUMBER(1),
        isReceiveOtherCall    NUMBER(1),
        logOut                NUMBER(1)   default 0,
        centerLon             VARCHAR2(32),
        centerLat             VARCHAR2(32),
        centerLon2            VARCHAR2(32),
        centerLat2            VARCHAR2(32),
        CREATEUSER            VARCHAR2(32)                   not null,
        UPDATEUSER            VARCHAR2(32),
        CREATEDATE            DATE                           not null,
        UPDATEDATE            DATE,
        constraint pkFamilyInfo primary key (id)      
);
comment on table familyInfo
  is '十户联防家庭表';
  comment on column familyInfo.id
  is '用户编号';
comment on column familyInfo.name
  is '用户姓名';
  comment on column familyInfo.orgId
  is '所属网格';
  comment on column familyInfo.orgInternalCode
  is '所属网格code';
  comment on column familyInfo.teamId
  is '所属分组';
  comment on column familyInfo.helpLine
  is '求助电话';
  comment on column familyInfo.certificateNumber
  is '用户证件号码';
  comment on column familyInfo.alarmCenter
  is '接警中心';
  comment on column familyInfo.familyAddress
  is '用户地址';
  comment on column familyInfo.familyState
  is '用户状态';
  comment on column familyInfo.policeRoom
  is '警务室';
  comment on column familyInfo.SMSNumber
  is '短息接收号码';
  comment on column familyInfo.voiceNumber
  is '语音接收号码';
  comment on column familyInfo.isInformOther
  is '是否通知同组其他用户';
  comment on column familyInfo.isReceiveOtherAlarm
  is '是否接收同组其他用户告警';
  comment on column familyInfo.isCallOther
  is '是否呼叫同组其他用户';
  comment on column familyInfo.isReceiveOtherCall
  is '是否接收同组其他用户呼叫';
  comment on column familyInfo.logOut
  is '是否注销';
    comment on column familyInfo.centerLon
  is '热区X轴';
    comment on column familyInfo.centerLat
  is '热区Y轴';
    comment on column familyInfo.centerLon2
  is '热区X轴2';
    comment on column familyInfo.centerLat2
  is '热区Y轴2';
    comment on column familyInfo.CREATEUSER
  is '创建用户';
    comment on column familyInfo.UPDATEUSER
  is '修改用户';
    comment on column familyInfo.CREATEDATE
  is '修改时间';
    comment on column familyInfo.UPDATEDATE
  is '创建时间';

