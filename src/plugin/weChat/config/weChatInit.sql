--微信素材附件序列
CREATE SEQUENCE S_wechatSource_Attachment
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9999999999999
    NOCYCLE
    CACHE 20
    NOORDER;
--微信素材附件表 
CREATE TABLE wechatSource_Attachment(
    attachmentId              NUMBER(10, 0)    NOT NULL,
   
    fileSize                    NUMBER(10, 0)    ,
    extFileName                 VARCHAR2(200)    ,
    fileName                    VARCHAR2(500)    ,
    fileActualUrl               VARCHAR2(500)    ,
    
    weChatUserId         VARCHAR2(50)     ,
    sourceId             NUMBER(10, 0)    ,
    
    createUser    			VARCHAR2(32)       not null,
    updateUser    			VARCHAR2(32),
    createDate      		DATE             not null,
    updateDate      		DATE,
    CONSTRAINT PK_wechatSource_Attachment PRIMARY KEY (attachmentId)
);
COMMENT ON TABLE wechatSource_Attachment IS '微信素材附件表 ';
COMMENT ON COLUMN wechatSource_Attachment.attachmentId IS '主键id';
COMMENT ON COLUMN wechatSource_Attachment.weChatUserId IS '服务号';
COMMENT ON COLUMN wechatSource_Attachment.sourceId IS '素材id';
COMMENT ON COLUMN wechatSource_Attachment.fileSize IS '文件大小';
COMMENT ON COLUMN wechatSource_Attachment.extFileName IS '后缀名';
COMMENT ON COLUMN wechatSource_Attachment.fileName IS '文件名';
COMMENT ON COLUMN wechatSource_Attachment.fileActualUrl IS '文件路径';
    

--微信菜单序列
CREATE SEQUENCE S_wechat_menu
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9999999999999
    NOCYCLE
    CACHE 20
    NOORDER;
    
--微信菜单表 
CREATE TABLE wechat_menu(
    menu_ID              NUMBER(10, 0)    NOT NULL,
    menuName                 VARCHAR2(100)    ,
    menuKey              VARCHAR2(100)    ,
    menuType             VARCHAR2(20)     ,
    WECHATUSERID         VARCHAR2(50)     ,
    source_description   VARCHAR2(200)     ,
    source_type          NUMBER(10, 0)    ,
    sourceID              VARCHAR2(200)    ,
    isLeaf               NUMBER(10, 0)    ,
    parentId             NUMBER(10, 0)    ,
    url                  VARCHAR2(500)    ,
    rank                 NUMBER(10, 0)    ,
    createUser    			VARCHAR2(32)       not null,
    updateUser    			VARCHAR2(32),
    createDate      		DATE             not null,
    updateDate      		DATE,
    CONSTRAINT PK_wechat_menu PRIMARY KEY (menu_ID)
);
COMMENT ON TABLE wechat_menu IS '微信菜单表';
COMMENT ON COLUMN wechat_menu.menu_ID IS '主键id';
COMMENT ON COLUMN wechat_menu.menuName IS '菜单名称';
COMMENT ON COLUMN wechat_menu.menuKey IS '菜单key';
COMMENT ON COLUMN wechat_menu.menuType IS '菜单类型 1:click 2:view';
COMMENT ON COLUMN wechat_menu.WECHATUSERID IS '服务号';
COMMENT ON COLUMN wechat_menu.sourceID IS '素材id';
COMMENT ON COLUMN wechat_menu.source_description IS '素材描述';
COMMENT ON COLUMN wechat_menu.source_type IS '素材类型';
COMMENT ON COLUMN wechat_menu.isLeaf IS '是不是叶子结点 1：是 2：不是';
COMMENT ON COLUMN wechat_menu.parentId IS '父结点id';
COMMENT ON COLUMN wechat_menu.url IS '链接地址';
COMMENT ON COLUMN wechat_menu.rank IS '层级 1：一级菜单 2：二级菜单';


--关键字序列
CREATE SEQUENCE S_KEYWORD
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9999999999999
    NOCYCLE
    CACHE 20
    NOORDER;
--关键字表 
CREATE TABLE KEYWORD(
    KEYWORD_ID        NUMBER(10, 0)    NOT NULL,
    KEY_NAME   VARCHAR2(100)    ,
    KEY_REMARK  VARCHAR2(100)    ,
    SOURCE_Id        VARCHAR2(200)     ,
    WECHATUSERID   VARCHAR2(50)     ,
    source_description   VARCHAR2(200)     ,
    source_type  NUMBER(10, 0)    ,
    ORG_ID             NUMBER(10, 0),
    ORGINTERNALCODE   VARCHAR2(50),
    
    createUser    			VARCHAR2(32)      not null,
    updateUser    			VARCHAR2(32),
    createDate      		DATE             not null,
    updateDate      		DATE,
    CONSTRAINT PK_KEYWORD PRIMARY KEY (KEYWORD_ID)
);
COMMENT ON TABLE KEYWORD IS '关键字表';
COMMENT ON COLUMN KEYWORD.KEYWORD_ID IS '主键id';
COMMENT ON COLUMN KEYWORD.KEY_NAME IS '关键字名称';
COMMENT ON COLUMN KEYWORD.KEY_REMARK IS '关键字描述';
COMMENT ON COLUMN KEYWORD.SOURCE_Id IS '素材id';
COMMENT ON COLUMN KEYWORD.WECHATUSERID IS '服务号';
COMMENT ON COLUMN KEYWORD.source_description IS '素材描述';
COMMENT ON COLUMN KEYWORD.source_type IS '素材类型';
COMMENT ON COLUMN KEYWORD.ORG_ID IS '所属组织部门';

--素材表序列
CREATE SEQUENCE S_wechat_source
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9999999999999
    NOCYCLE
    CACHE 20
    NOORDER;
    
--素材表
CREATE TABLE wechat_source(
    source_id        NUMBER(10, 0)    NOT NULL,
    path             VARCHAR2(200)    ,
    content          VARCHAR2(2000)    ,
    title             VARCHAR2(500)    ,
    description      VARCHAR2(2000)     ,
    picUrl           VARCHAR2(500)     ,
    url              VARCHAR2(500)     ,
    source_type      NUMBER(10, 0)     ,
    weChatUserId     VARCHAR2(50)     ,
    source_description      VARCHAR2(200)     ,
    ORG_ID             NUMBER(10, 0),
    ORGINTERNALCODE   VARCHAR2(50),
    createUser    			VARCHAR2(32)      not null,
    updateUser    			VARCHAR2(32),
    createDate      		DATE             not null,
    updateDate      		DATE,
    CONSTRAINT PK_wechat_source PRIMARY KEY (source_id)
);

COMMENT ON TABLE wechat_source IS '素材表';
COMMENT ON COLUMN wechat_source.path IS '图片路径';
COMMENT ON COLUMN wechat_source.content IS '文本内容';
COMMENT ON COLUMN wechat_source.picUrl IS '图文图片地址';
COMMENT ON COLUMN wechat_source.url IS '链接地址';
COMMENT ON COLUMN wechat_source.description IS '图文描述';
COMMENT ON COLUMN wechat_source.source_type IS '素材类型';
COMMENT ON COLUMN wechat_source.weChatUserId IS '服务号';
COMMENT ON COLUMN wechat_source.source_description IS '素材描述';
COMMENT ON COLUMN wechat_source.ORG_ID IS '所属组织部门';




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
    AVAILABILITY    VARCHAR2(20)     ,
    IS_KEYWORD_MSG  NUMBER(10, 0)     ,
    SERVICE_ID      VARCHAR2(500)      ,
    LAT             VARCHAR2(50)       ,
    LNG             VARCHAR2(50)  ,
    FORWARDINGSTATE NUMBER(1)		default 1,
    ORGINTERNALCODE VARCHAR2(50),
    ISREAD          NUMBER(10, 0),
    UPDATEUSER      VARCHAR2(32),
    UPDATEDATE      DATE,
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
COMMENT ON COLUMN INBOX.AVAILABILITY IS '有效或是无效 1：有效 0：无效';
COMMENT ON COLUMN INBOX.IS_KEYWORD_MSG IS '1：关键字消息 2：非关键字消息';
COMMENT ON COLUMN INBOX.SERVICE_ID IS '事件服务单号';
COMMENT ON COLUMN INBOX.LAT IS '纬度值';
COMMENT ON COLUMN INBOX.LNG IS '经度值';
COMMENT ON COLUMN INBOX.forwardingState IS '转发状态（未转发:1 已转发:2）';
COMMENT ON COLUMN INBOX.ORGINTERNALCODE is '所属组织编号';
COMMENT ON COLUMN INBOX.ISREAD IS '阅读状态(0为未读,1为已读)';

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
  PRECISE_INBOX_ID                   NUMBER(10),
  CONTENT                            VARCHAR2(3000),
  RECEIVE_USER                       VARCHAR2(50),
  FROM_USER_NAME                     VARCHAR2(100),
  IS_ISSUE                           NUMBER(10),
  SERVICE_ID                         VARCHAR2(500), 
  CREATE_DATE                        DATE,
  WECHAT_USER_ID     VARCHAR2(50),
  CONSTRAINT PK_REPLY_MESSAGE PRIMARY KEY (REPLY_MESSAGE_ID)
);

COMMENT ON TABLE REPLY_MESSAGE
  IS '收件箱回复记录';
COMMENT ON COLUMN REPLY_MESSAGE.REPLY_MESSAGE_ID
  IS '回复记录ID';
COMMENT ON COLUMN REPLY_MESSAGE.INBOX_ID
  IS '模糊消息ID';
COMMENT ON COLUMN REPLY_MESSAGE.PRECISE_INBOX_ID
  IS '精确消息ID';
COMMENT ON COLUMN REPLY_MESSAGE.CONTENT
  IS '回复内容';
COMMENT ON COLUMN REPLY_MESSAGE.CREATE_DATE
  IS '回复时间';
COMMENT ON COLUMN REPLY_MESSAGE.WECHAT_USER_ID IS '公众账号id（微信端）';

COMMENT ON COLUMN REPLY_MESSAGE.FROM_USER_NAME IS '用户openId';
COMMENT ON COLUMN REPLY_MESSAGE.IS_ISSUE IS '是否转为事件(0为否,1为是)';
COMMENT ON COLUMN REPLY_MESSAGE.SERVICE_ID is '事件服务单号';

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
    fan_id   	           NUMBER(10, 0)    NOT NULL,
    open_id                VARCHAR2(50),
    name                   VARCHAR2(200),
    group_id   	           NUMBER(10, 0)    NOT NULL,
    wechat_user_id         VARCHAR2(50),
    nick_name              VARCHAR2(60),
    createUser    		   VARCHAR2(32)       not null,
    updateUser    		   VARCHAR2(32),
    createDate      	   DATE             not null,
    updateDate      	   DATE,
    CONSTRAINT pk_fan PRIMARY KEY (fan_id)
);
comment on table fan is '微信粉丝信息表 ';
comment on column fan.fan_id is '粉丝信息id';
comment on column fan.open_id is 'openid';
comment on column fan.name is '粉丝微信昵称';  
comment on column fan.group_id is '群组id（微信方）'; 
comment on column fan.wechat_user_id is '公众账号id（微信端）';  
comment on column fan.nick_name is '昵称';


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
    count_fan           NUMBER(10, 0),
    createUser    			VARCHAR2(32)       not null,
    updateUser    			VARCHAR2(32),
    createDate      		DATE             not null,
    updateDate      		DATE,
    CONSTRAINT PK_WECHAT_GROUP PRIMARY KEY (WECHAT_GROUP_ID)
);
COMMENT ON TABLE WECHAT_GROUP IS '微信群组表 ';
COMMENT ON COLUMN WECHAT_GROUP.WECHAT_GROUP_ID IS '群组id';
COMMENT ON COLUMN WECHAT_GROUP.NAME IS '群组名';
COMMENT ON COLUMN WECHAT_GROUP.WECHAT_USER_ID IS '公众账号id（微信端）';  
COMMENT ON COLUMN WECHAT_GROUP.GROUP_ID IS '群组id（微信端）';
COMMENT ON COLUMN WECHAT_GROUP.count_fan IS '群组内的粉丝数';

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
    
    source_description   VARCHAR2(200)     ,
    source_type          NUMBER(10, 0)    ,
    source_ID              VARCHAR2(200)     ,
    is_append_KeyWord             NUMBER(10, 0),
    
    CONSTRAINT PK1 PRIMARY KEY (TENCENT_USER_ID)
);
COMMENT ON TABLE TENCENT_USER IS '微信公众号信息表';
COMMENT ON COLUMN TENCENT_USER.NAME IS '名称';
COMMENT ON COLUMN TENCENT_USER.ORG_ID IS '所属组织部门';
COMMENT ON COLUMN TENCENT_USER.ORGINTERNALCODE IS '所属组织编号';
COMMENT ON COLUMN TENCENT_USER.APP_ID IS '应用号';
COMMENT ON COLUMN TENCENT_USER.APP_SECRET IS '应用秘钥';

COMMENT ON COLUMN TENCENT_USER.source_description IS '素材描述';
COMMENT ON COLUMN TENCENT_USER.source_type IS '素材类型';
COMMENT ON COLUMN TENCENT_USER.source_ID IS '素材id';
COMMENT ON COLUMN TENCENT_USER.is_append_KeyWord IS '是否追加关键字  1：追加 2：不追加';

--微信红包
--以下为表结构及序列
--微信红包配置表序列
CREATE SEQUENCE s_RedEnvelope
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9999999999999
    NOCYCLE
    CACHE 20
    NOORDER;
--微信红包发放记录表序列
CREATE SEQUENCE s_REDENVELOPESPAYMENTRECORDS
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9999999999999
    NOCYCLE
    CACHE 20
    NOORDER;
	
-- 创建微信红包发放记录表
create table REDENVELOPESPAYMENTRECORDS
(
  ID              NUMBER(10) not null,
  SIGN            VARCHAR2(64),
  MCH_BILLNO      VARCHAR2(64),
  MCH_ID          VARCHAR2(64),
  WXAPPID         VARCHAR2(64),
  RE_OPENID       VARCHAR2(64),
  TOTAL_AMOUNT    NUMBER(8),
  SEND_TIME       VARCHAR2(64),
  SEND_LISTID     VARCHAR2(64),
  DETAIL_ID       VARCHAR2(64),
  STATUS          VARCHAR2(32),
  SEND_TYPE       VARCHAR2(64),
  HB_TYPE         VARCHAR2(64),
  TOTAL_NUM       NUMBER(8),
  REASON          VARCHAR2(64),
  REFUND_TIME     VARCHAR2(64),
  REFUND_AMOUNT   NUMBER(8),
  WISHING         VARCHAR2(256),
  REMARK          VARCHAR2(512),
  ACT_NAME        VARCHAR2(64),
  OPENID          VARCHAR2(64),
  AMOUNT          NUMBER(8),
  RCV_TIME        VARCHAR2(64),
  CREATEUSER      VARCHAR2(32) not null,
  UPDATEUSER      VARCHAR2(32),
  UPDATEDATE      DATE,
  CREATEDATE      DATE not null,
  ORGINTERNALCODE VARCHAR2(64),
  ORGID           NUMBER(10),
  APIKEY          VARCHAR2(64),
  FANNAME         VARCHAR2(200),
  constraint pkREDENVELOPESPAYMENTRECORDS primary key(id)
);
comment on table REDENVELOPESPAYMENTRECORDS
  is '微信红包发放记录表';
-- Add comments to the columns 
comment on column REDENVELOPESPAYMENTRECORDS.ID
  is '主键id';
comment on column REDENVELOPESPAYMENTRECORDS.SIGN
  is '签名';
comment on column REDENVELOPESPAYMENTRECORDS.MCH_BILLNO
  is '商户订单号';
comment on column REDENVELOPESPAYMENTRECORDS.MCH_ID
  is '微信支付分配的商户号';
comment on column REDENVELOPESPAYMENTRECORDS.WXAPPID
  is '公众账号appid';
comment on column REDENVELOPESPAYMENTRECORDS.RE_OPENID
  is '接受收红包的用户 用户在wxappid下的openid';
comment on column REDENVELOPESPAYMENTRECORDS.TOTAL_AMOUNT
  is '付款金额，单位分';
comment on column REDENVELOPESPAYMENTRECORDS.SEND_TIME
  is '红包发送时间';
comment on column REDENVELOPESPAYMENTRECORDS.SEND_LISTID
  is '红包订单的微信单号';
comment on column REDENVELOPESPAYMENTRECORDS.DETAIL_ID
  is '使用API发放现金红包时返回的红包单号';
comment on column REDENVELOPESPAYMENTRECORDS.STATUS
  is '红包状态 
';
comment on column REDENVELOPESPAYMENTRECORDS.SEND_TYPE
  is 'API:通过API接口发放 
UPLOAD:通过上传文件方式发放 
ACTIVITY:通过活动方式发放';
comment on column REDENVELOPESPAYMENTRECORDS.HB_TYPE
  is 'GROUP:裂变红包 
NORMAL:普通红包 
';
comment on column REDENVELOPESPAYMENTRECORDS.TOTAL_NUM
  is '红包个数';
comment on column REDENVELOPESPAYMENTRECORDS.REASON
  is '发送失败原因';
comment on column REDENVELOPESPAYMENTRECORDS.REFUND_TIME
  is '红包退款时间';
comment on column REDENVELOPESPAYMENTRECORDS.REFUND_AMOUNT
  is '红包退款金额';
comment on column REDENVELOPESPAYMENTRECORDS.WISHING
  is '祝福语';
comment on column REDENVELOPESPAYMENTRECORDS.REMARK
  is '活动描述，低版本微信可见';
comment on column REDENVELOPESPAYMENTRECORDS.ACT_NAME
  is '红包的活动名称';
comment on column REDENVELOPESPAYMENTRECORDS.OPENID
  is '领取红包的openid';
comment on column REDENVELOPESPAYMENTRECORDS.AMOUNT
  is '领取金额 ';
comment on column REDENVELOPESPAYMENTRECORDS.RCV_TIME
  is '领取红包的时间';
comment on column REDENVELOPESPAYMENTRECORDS.CREATEUSER
  is '创建用户';
comment on column REDENVELOPESPAYMENTRECORDS.UPDATEUSER
  is '修改用户';
comment on column REDENVELOPESPAYMENTRECORDS.UPDATEDATE
  is '修改时间';
comment on column REDENVELOPESPAYMENTRECORDS.CREATEDATE
  is '创建日期';
comment on column REDENVELOPESPAYMENTRECORDS.ORGINTERNALCODE
  is '所属网格编码';
comment on column REDENVELOPESPAYMENTRECORDS.ORGID
  is '所属网格id';
comment on column REDENVELOPESPAYMENTRECORDS.APIKEY
  is '微信支付商户号API密钥';
comment on column REDENVELOPESPAYMENTRECORDS.FANNAME
  is '发送对象粉丝昵称';

-- 创建微信红包配置表
create table REDENVELOPE
(
  ID                   NUMBER(10) not null,
  REDENVELOPETYPE      NUMBER(1),
  MIN_VALUE            NUMBER(8),
  MAX_VALUE            NUMBER(8),
  SINGLEENVELOPE_VALUE NUMBER(8),
  ACT_NAME             VARCHAR2(64),
  SEND_NAME            VARCHAR2(64),
  REMARK               VARCHAR2(512),
  WISHING              VARCHAR2(256),
  SHARE_CONTENT        VARCHAR2(512),
  SHARE_URL            VARCHAR2(256),
  ORGINTERNALCODE      VARCHAR2(64),
  ORGID                NUMBER(10),
  CREATEUSER           VARCHAR2(32) not null,
  UPDATEUSER           VARCHAR2(32),
  UPDATEDATE           DATE,
  CREATEDATE           DATE not null,
  MCH_ID               VARCHAR2(64),
  WXAPPID              VARCHAR2(64),
  WECHATUSERID         VARCHAR2(64),
  NICK_NAME            VARCHAR2(64),
  APIKEY               VARCHAR2(64),
  constraint pkREDENVELOPE primary key(id)
);
comment on table REDENVELOPE
  is '微信红包配置表';
comment on column REDENVELOPE.ID
  is '主键id';
comment on column REDENVELOPE.REDENVELOPETYPE
  is '红包类型：1表是随机红包 2表示固定红包';
comment on column REDENVELOPE.MIN_VALUE
  is '最小红包金额';
comment on column REDENVELOPE.MAX_VALUE
  is '最大红包金额';
comment on column REDENVELOPE.SINGLEENVELOPE_VALUE
  is '单个红包金额';
comment on column REDENVELOPE.ACT_NAME
  is '活动名称';
comment on column REDENVELOPE.SEND_NAME
  is '商户名称';
comment on column REDENVELOPE.REMARK
  is '备注';
comment on column REDENVELOPE.WISHING
  is '红包祝福语';
comment on column REDENVELOPE.SHARE_CONTENT
  is '分享文案';
comment on column REDENVELOPE.SHARE_URL
  is '分享链接';
comment on column REDENVELOPE.ORGINTERNALCODE
  is '所属网格编码';
comment on column REDENVELOPE.ORGID
  is '所属网格id';
comment on column REDENVELOPE.CREATEUSER
  is '创建用户';
comment on column REDENVELOPE.UPDATEUSER
  is '修改用户';
comment on column REDENVELOPE.UPDATEDATE
  is '修改时间';
comment on column REDENVELOPE.CREATEDATE
  is '创建日期';
comment on column REDENVELOPE.MCH_ID
  is '商户号';
comment on column REDENVELOPE.WXAPPID
  is '公众账号appid 
';
comment on column REDENVELOPE.WECHATUSERID
  is '公众账号微信连接号';
comment on column REDENVELOPE.NICK_NAME
  is '提供方名称';
comment on column REDENVELOPE.APIKEY
  is '微信支付商户号API密钥';

--消息模板记录表(消息模板管理)
create table TEMPLATEMESSAGERECORD
(
  ID               NUMBER(10) not null,
  TEMPLATEID       VARCHAR2(256),
  TITLE            VARCHAR2(256),
  PRIMARYINDUSTRY  VARCHAR2(256),
  TWOSTAGEINDUSTRY VARCHAR2(256),
  TEMPLATENUM      VARCHAR2(64),
  WECHATUSERID     VARCHAR2(64),
  ORGINTERNALCODE  VARCHAR2(64),
  ORGID            NUMBER(10),
  CREATEUSER       VARCHAR2(32) not null,
  UPDATEUSER       VARCHAR2(32),
  UPDATEDATE       DATE,
  CREATEDATE       DATE not null,
  constraint pkTEMPLATEMESSAGERECORD primary key(id)
);
comment on table TEMPLATEMESSAGERECORD
  is '消息模板记录表';
comment on column TEMPLATEMESSAGERECORD.ID
  is '主键';
comment on column TEMPLATEMESSAGERECORD.TEMPLATEID
  is '微信服务器模板id（不同微信服务号的相同模板编号是不同的）';
comment on column TEMPLATEMESSAGERECORD.TITLE
  is '标题';
comment on column TEMPLATEMESSAGERECORD.PRIMARYINDUSTRY
  is '一级行业';
comment on column TEMPLATEMESSAGERECORD.TWOSTAGEINDUSTRY
  is '二级行业';
comment on column TEMPLATEMESSAGERECORD.TEMPLATENUM
  is '模板编号（相同模板微信是统一的，即模板编号相同）';
comment on column TEMPLATEMESSAGERECORD.WECHATUSERID
  is '微信服务号';
comment on column TEMPLATEMESSAGERECORD.ORGINTERNALCODE
  is '所属网格编码';
comment on column TEMPLATEMESSAGERECORD.ORGID
  is '所属网格id';
comment on column TEMPLATEMESSAGERECORD.CREATEUSER
  is '创建用户';
comment on column TEMPLATEMESSAGERECORD.UPDATEUSER
  is '修改用户';
comment on column TEMPLATEMESSAGERECORD.UPDATEDATE
  is '修改时间';
comment on column TEMPLATEMESSAGERECORD.CREATEDATE
  is '创建日期';
--消息模板管理序列
CREATE SEQUENCE s_TEMPLATEMESSAGERECORD
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9999999999999
    NOCYCLE
    CACHE 20
    NOORDER;

   /*精确消息*/
create table preciseInbox (
		
    id                           NUMBER(10)           not null,
    toUserName                   VARCHAR2(100),
    groupId                      NUMBER(10),
    groupName                    VARCHAR2(100),
    fromUserName                 VARCHAR2(100),
    msgType                      VARCHAR2(20),
    msgId                        NUMBER(30),
    orgId                        NUMBER(10),
    issueId                      NUMBER(10),
    dealState                    NUMBER(10),
    availability                 NUMBER(10),
    serviceId                    VARCHAR2(200),
    lng                          VARCHAR2(50),
    lat                          VARCHAR2(50), 
    forwardingState              NUMBER(1),
    orgInternalCode              VARCHAR2(50),    
    issueTypeDomainId            NUMBER(10),
    issueTypeId                  NUMBER(10),
    issueName                    VARCHAR2(64), 
    occurLocation                VARCHAR2(500), 
    reportPeopleName             VARCHAR2(256),
    reportPeoplePhoneNumber      NUMBER(11),
    reportPeopleTelephone        VARCHAR2(100), 
    issueScale                   NUMBER(10),
    relatePeopleCount            NUMBER(10),
    profile                      VARCHAR2(1000),
    inboxType                    NUMBER(10),
    exceptionType                NUMBER(10),
    isRead                       NUMBER(10),
    
    createUser                   VARCHAR2(100),
    updateUser                   VARCHAR2(32),
    createDate                   DATE                   not null,
    updateDate                   DATE,
    constraint pkPreciseInbox primary key(id)
);

comment on column preciseInbox.id is 'id';
comment on column preciseInbox.toUserName is '微信公众号';
comment on column preciseInbox.groupId is '群组id';
comment on column preciseInbox.groupName is '群组名称';
comment on column preciseInbox.fromUserName is '信息来源微信号';
comment on column preciseInbox.msgType is '信息类型';
comment on column preciseInbox.msgId is '信息id（在微信服务器处存储的id）';
comment on column preciseInbox.orgId is '所属组织部门';
comment on column preciseInbox.issueId is '转为事件id';
comment on column preciseInbox.dealState is '事件处理状态';
comment on column preciseInbox.availability is '有效或是无效 1：有效 0：无效';
comment on column preciseInbox.serviceId is '事件服务单号';
comment on column preciseInbox.lng is '纬度值';
comment on column preciseInbox.lat is '经度值';
comment on column preciseInbox.forwardingState is '转发状态（未转发:1 已转发:2）';
comment on column preciseInbox.orgInternalCode is '所属组织编号';
comment on column preciseInbox.issueTypeDomainId is '事件大类';
comment on column preciseInbox.issueTypeId is '事件小类';
comment on column preciseInbox.issueName is '事件名称';
comment on column preciseInbox.occurLocation is '发生地点';
comment on column preciseInbox.reportPeopleName is '上报人姓名';
comment on column preciseInbox.reportPeoplePhoneNumber is '上报人手机号码';
comment on column preciseInbox.reportPeopleTelephone is '上报人固定电话';
comment on column preciseInbox.issueScale is '事件规模';
comment on column preciseInbox.relatePeopleCount is '涉及人数';
comment on column preciseInbox.profile is '简述';
comment on column preciseInbox.inboxType is '消息类型';
comment on column preciseInbox.exceptionType is '异常类型';
comment on column preciseinbox.isRead is '阅读状态(0为未读,1为已读)';

comment on column preciseInbox.createUser is '创建人';
comment on column preciseInbox.updateUser is '修改人';
comment on column preciseInbox.createDate is '创建时间';
comment on column preciseInbox.updateDate is '修改时间';

--精确消息序列
CREATE SEQUENCE s_preciseInbox
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9999999999999
    NOCYCLE
    CACHE 20
    NOORDER;
    
    --微信评价事件处理
CREATE TABLE wechat_evaluation_issue(
    id                          NUMBER(10, 0)    not null,
    orgId                       NUMBER(10)      not null,
    orgCode                     VARCHAR2(32)    not null,
    toUserName                  VARCHAR2(200),
    openId                      VARCHAR2(200),
    serialNumber                VARCHAR2(200),
    scorePerson                 VARCHAR2(50),
    scoreStarNumber             NUMBER(10, 0),
    content                     VARCHAR2(1000),
    issueName                   VARCHAR2(200),
    
    closeCaseDate               DATE,
    createUser                  VARCHAR2(100),
    updateUser                  VARCHAR2(32),
    createDate                  DATE                   not null,
    updateDate                  DATE,
    constraint pkwechat_evaluation_issue primary key(id)
);
comment on column wechat_evaluation_issue.id is 'id';
comment on column wechat_evaluation_issue.serialNumber is '事件的服务单号';
comment on column wechat_evaluation_issue.toUserName is '微信公众号';
comment on column wechat_evaluation_issue.openId is '评价人openId';
comment on column wechat_evaluation_issue.scorePerson is '评分人';
comment on column wechat_evaluation_issue.scoreStarNumber is '评分星数';
comment on column wechat_evaluation_issue.content is '评分内容';
comment on column wechat_evaluation_issue.issueName is '事件名称';

comment on column wechat_evaluation_issue.closeCaseDate is '结案时间 ';
comment on column wechat_evaluation_issue.createUser is '创建人';
comment on column wechat_evaluation_issue.updateUser is '修改人';
comment on column wechat_evaluation_issue.createDate is '创建时间';
comment on column wechat_evaluation_issue.updateDate is '修改时间';
    

--微信评分事件序列
CREATE SEQUENCE s_wechat_evaluation_issue
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9999999999999
    NOCYCLE
    CACHE 20
    NOORDER;
    
--微信消息语音提示
create table wechat_inbox_voice_prompt(
    id                          NUMBER(10, 0)    not null,
    orgId                       NUMBER(10)      not null,
    orgCode                     VARCHAR2(32)    not null,
    toUserName                  VARCHAR2(200),
    voicePromptStatus           NUMBER(10)    not null,
    voiceUrl                    VARCHAR2(200),
    displayName                 VARCHAR2(20),
    createUser                  VARCHAR2(100),
    updateUser                  VARCHAR2(32),
    createDate                  DATE                   not null,
    updateDate                  DATE,
    constraint wechat_inbox_voice_prompt primary key(id)
);
comment on column wechat_inbox_voice_prompt.id is 'id';
comment on column wechat_inbox_voice_prompt.toUserName is '微信公众号';
comment on column wechat_inbox_voice_prompt.voicePromptStatus is '微信消息语音提示状态ID(属性字典ID )';
comment on column wechat_inbox_voice_prompt.voiceUrl is '语音路径';
comment on column wechat_inbox_voice_prompt.displayName is '微信消息语音提示状态名称(属性字典名称)';
    
comment on column wechat_inbox_voice_prompt.createUser is '创建人';
comment on column wechat_inbox_voice_prompt.updateUser is '修改人';
comment on column wechat_inbox_voice_prompt.createDate is '创建时间';
comment on column wechat_inbox_voice_prompt.updateDate is '修改时间';


--微信消息语音提示序列
CREATE SEQUENCE s_wechat_inbox_voice_prompt
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9999999999999
    NOCYCLE
    CACHE 20
    NOORDER;
);