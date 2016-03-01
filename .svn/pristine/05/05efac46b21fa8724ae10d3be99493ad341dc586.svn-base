--添加微信权限--
insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,indexId)
    VALUES(s_permissions.NEXTVAL,'微信中心','weChatCenterManagement',1,'互动交流系统',(select id from permissions where ename='interactionManagement'),1,'',4);
	
insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,indexId)
    VALUES(s_permissions.NEXTVAL,'消息管理','inboxManagement',1,'微信中心',(select id from permissions where ename='weChatCenterManagement'),1,'/hotModuel/inbox/inboxList.ftl',0);
	
insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
    VALUES(s_permissions.NEXTVAL,'高级搜索','searchInbox',0,'消息管理',(select id from permissions where ename='inboxManagement'),1,'','',0);
	
insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
    VALUES(s_permissions.NEXTVAL,'回复','replyInbox',0,'消息管理',(select id from permissions where ename='inboxManagement'),1,'','',1);
	
insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
    VALUES(s_permissions.NEXTVAL,'删除','deleteInbox',0,'消息管理',(select id from permissions where ename='inboxManagement'),1,'','',2);
	
insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
    VALUES(s_permissions.NEXTVAL,'事件受理','acceptIssue',0,'消息管理',(select id from permissions where ename='inboxManagement'),1,'','',3);
	
insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,indexId)
    VALUES(s_permissions.NEXTVAL,'我的事件','weChatIssueManagement',1,'微信中心',(select id from permissions where ename='weChatCenterManagement'),1,'/hotModuel/weIssue/weIssueViewTabList.ftl',1);
	
insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,indexId)
    VALUES(s_permissions.NEXTVAL,'统计分析','statisticAnalysisManagement',1,'微信中心',(select id from permissions where ename='weChatCenterManagement'),1,'/hotModuel/statisticAnalysis/statisticAnalysisList.ftl',2);
	
insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,indexId)
    VALUES(s_permissions.NEXTVAL,'账号管理','tencentUserManagement',1,'微信中心',(select id from permissions where ename='weChatCenterManagement'),1,'/hotModuel/tencentUser/tencentUserList.ftl',3);
	
insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
    VALUES(s_permissions.NEXTVAL,'新增','addTencentUser',0,'账号管理',(select id from permissions where ename='tencentUserManagement'),1,'','',0);
	
insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
    VALUES(s_permissions.NEXTVAL,'修改','updateTencentUser',0,'账号管理',(select id from permissions where ename='tencentUserManagement'),1,'','',1);
	
insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderurl,indexId)
    VALUES(s_permissions.NEXTVAL,'删除','deleteTencentUser',0,'账号管理',(select id from permissions where ename='tencentUserManagement'),1,'','',2);
    
    
--收件箱序列
CREATE SEQUENCE S_INBOX
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9999999999999
    NOCYCLE
    CACHE 20
    NOORDER;
    
--收件箱主表
CREATE TABLE INBOX(
    INBOX_ID        NUMBER(10, 0)    NOT NULL,
    TO_USER_NAME    VARCHAR2(100)    ,
    GROUP_ID        NUMBER(10, 0)    ,
    FROM_USER_NAME  VARCHAR2(100)    ,
    CREATE_TIME     DATE             ,
    MSG_TYPE        VARCHAR2(20)     ,
    MSG_ID          NUMBER(30, 0)    ,
    CONTENT         VARCHAR2(3000)   ,
    ORG_ID          NUMBER(10,0)     ,
    CREATE_USER     VARCHAR2(200)     ,
    ISSUE_ID        NUMBER(10, 0)    ,
    DEAL_STATE      NUMBER(10, 0)    ,
    CONSTRAINT PK_INBOX_ID PRIMARY KEY (INBOX_ID)
);

COMMENT ON TABLE INBOX IS '微信平台收件箱表';
COMMENT ON COLUMN INBOX.INBOX_ID IS '主键id';
COMMENT ON COLUMN INBOX.TO_USER_NAME IS '微信公众号';
COMMENT ON COLUMN INBOX.GROUP_ID IS '群组';
COMMENT ON COLUMN INBOX.FROM_USER_NAME IS '信息来源微信号';
COMMENT ON COLUMN INBOX.CREATE_TIME IS '信息创建时间（微信服务器处的时间）';
COMMENT ON COLUMN INBOX.MSG_TYPE IS '信息类型';
COMMENT ON COLUMN INBOX.MSG_ID IS '信息id（在微信服务器处存储的id）';
COMMENT ON COLUMN INBOX.CONTENT IS '文本内容';
COMMENT ON COLUMN INBOX.ORG_ID IS '所属组织部门';
COMMENT ON COLUMN INBOX.CREATE_USER IS '创建者（此处为微信粉丝名）';
COMMENT ON COLUMN INBOX.ISSUE_ID IS '转为事件id';
COMMENT ON COLUMN INBOX.DEAL_STATE IS '事件处理状态';

--收件箱附件序列
CREATE SEQUENCE S_INBOXATTACHMENT
INCREMENT BY 1
START WITH 1
MINVALUE 1
CACHE 10
MAXVALUE 9999999999;

--收件箱附件主表
CREATE TABLE INBOXATTACHMENT
(
  INBOX_ATTACHMENT_ID                NUMBER(10)        NOT NULL,
  INBOX_ID       					 NUMBER(10),
  FILE_SIZE       	                 NUMBER(10),
  FILE_NAME       	                 VARCHAR2(150),
  EXT_FILE_NAME                      VARCHAR2(30),
  FILE_ACTUAL_URL  	                 VARCHAR2(250) 		NOT NULL,
  CONSTRAINT PK_INBOX_ATTACHMENT PRIMARY KEY (INBOX_ATTACHMENT_ID)
);

COMMENT ON TABLE INBOXATTACHMENT
  IS '收件箱附件';
COMMENT ON COLUMN INBOXATTACHMENT.INBOX_ATTACHMENT_ID
  IS '收件箱附件ID';
COMMENT ON COLUMN INBOXATTACHMENT.INBOX_ID
  IS '收件箱ID';
COMMENT ON COLUMN INBOXATTACHMENT.FILE_SIZE
  IS '附件大小';
COMMENT ON COLUMN INBOXATTACHMENT.FILE_NAME
  IS '附件名称';
  COMMENT ON COLUMN INBOXATTACHMENT.EXT_FILE_NAME
  IS '附件扩展名';
COMMENT ON COLUMN INBOXATTACHMENT.FILE_ACTUAL_URL
  IS '附件路径';
  
 --收件箱回复记录序列
CREATE SEQUENCE S_REPLY_MESSAGE
INCREMENT BY 1
START WITH 1
MINVALUE 1
CACHE 10
MAXVALUE 9999999999;

--收件箱回复记录主表
CREATE TABLE REPLY_MESSAGE
(
  REPLY_MESSAGE_ID                   NUMBER(10)        NOT NULL,
  INBOX_ID       					 NUMBER(10),
  CONTENT                            VARCHAR2(3000),
  RECEIVE_USER                       VARCHAR2(50),
  CREATE_DATE                        DATE,
  CONSTRAINT PK_REPLY_MESSAGE PRIMARY KEY (REPLY_MESSAGE_ID)
);

COMMENT ON TABLE REPLY_MESSAGE
  IS '收件箱回复记录';
COMMENT ON COLUMN REPLY_MESSAGE.REPLY_MESSAGE_ID
  IS '回复记录ID';
COMMENT ON COLUMN REPLY_MESSAGE.INBOX_ID
  IS '收件箱ID';
COMMENT ON COLUMN REPLY_MESSAGE.CONTENT
  IS '回复内容';
COMMENT ON COLUMN REPLY_MESSAGE.CREATE_DATE
  IS '回复时间';
  
--微信粉丝序列
CREATE SEQUENCE S_FAN
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 99999999999999
    NOCYCLE
    CACHE 20
    ORDER;

--微信粉丝主表
CREATE TABLE FAN(
    FAN_ID   	 NUMBER(10, 0)    NOT NULL,
    OPEN_ID      VARCHAR2(50),
    NAME         VARCHAR2(200),
    GROUP_ID   	 NUMBER(10, 0)    NOT NULL,
    CONSTRAINT PK_FAN PRIMARY KEY (FAN_ID)
);
COMMENT ON TABLE FAN IS '微信粉丝信息表 ';
COMMENT ON COLUMN FAN.FAN_ID IS '粉丝信息id';
COMMENT ON COLUMN FAN.OPEN_ID IS 'openid';
COMMENT ON COLUMN FAN.NAME IS '粉丝微信昵称';  
COMMENT ON COLUMN FAN.GROUP_ID IS '群组id（微信方）'; 


--群组序列
CREATE SEQUENCE S_WECHAT_GROUP
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 99999999999999
    NOCYCLE
    CACHE 20
    ORDER;

--群组主表
CREATE TABLE WECHAT_GROUP(
    WECHAT_GROUP_ID    NUMBER(10, 0)    NOT NULL,
    NAME               VARCHAR2(200),
    WECHAT_USER_ID     VARCHAR2(50),
    GROUP_ID   	       NUMBER(10, 0),
    CONSTRAINT PK_WECHAT_GROUP PRIMARY KEY (WECHAT_GROUP_ID)
);
COMMENT ON TABLE WECHAT_GROUP IS '微信群组表 ';
COMMENT ON COLUMN WECHAT_GROUP.WECHAT_GROUP_ID IS '群组id';
COMMENT ON COLUMN WECHAT_GROUP.NAME IS '群组名';
COMMENT ON COLUMN WECHAT_GROUP.WECHAT_USER_ID IS '公众账号id（微信端）';  
COMMENT ON COLUMN WECHAT_GROUP.GROUP_ID IS '群组id（微信端）';

--微信账号序列 
CREATE SEQUENCE S_TencentUser
START WITH 1
INCREMENT BY 1
MINVALUE 1
MAXVALUE 99999999999999
NOCYCLE
CACHE 20
ORDER;

--微信账号主表
CREATE TABLE TENCENT_USER(
    TENCENT_USER_ID    NUMBER(10, 0)    NOT NULL,
    NAME               VARCHAR2(128),
    CREATE_DEPT        NUMBER(10, 0),
    CREATE_USER        VARCHAR2(50),
    CREATE_DATE        DATE,
    UPDATE_DEPT        NUMBER(10, 0),
    UPDATE_USER        VARCHAR2(50),
    UPDATE_DATE        DATE,
    ORG_ID             NUMBER(10, 0),
    ORGINTERNALCODE   VARCHAR2(50),
    APP_ID                 VARCHAR2(50),
    APP_SECRET         VARCHAR2(50),
    WECHAT_USER_ID     VARCHAR2(50),
    CONSTRAINT PK1 PRIMARY KEY (TENCENT_USER_ID)
);
COMMENT ON TABLE TENCENT_USER IS '微信公众号信息表';
COMMENT ON COLUMN TENCENT_USER.NAME IS '名称';
COMMENT ON COLUMN TENCENT_USER.ORG_ID IS '所属组织部门';
COMMENT ON COLUMN TENCENT_USER.ORGINTERNALCODE IS '所属组织编号';
COMMENT ON COLUMN TENCENT_USER.APP_ID IS '应用号';
COMMENT ON COLUMN TENCENT_USER.APP_SECRET IS '应用秘钥';

commit;