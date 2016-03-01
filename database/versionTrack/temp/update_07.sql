--修改模糊消息连接地址
update permissions set normalUrl='/wechat/fuzzyInbox/fuzzyInboxList.jsp' where ename  = 'fuzzyMessageManagement';

--添加接入权限
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '接入', 'accessCustomerInboxs', 0, '模糊消息', 1, (select id from permissions where ename='fuzzyMessageManagement'), '', '', '', 1, '');

alter table inbox add(updateUser  VARCHAR2(32));
comment on column inbox.updateUser is '修改人';

alter table inbox add(updateDate  DATE);
comment on column inbox.updateDate is '修改时间';

alter table inbox add(isRead  NUMBER(10));
comment on column inbox.isRead is '阅读状态(0为未读,1为已读)';

--精确消息表(流动人口消息、治安隐患消息、综合服务消息公共表),添加是否阅读字段
alter table preciseinbox add(isRead  NUMBER(10));
comment on column preciseinbox.isRead is '阅读状态(0为未读,1为已读)';

--用户openId
alter table reply_message add(from_user_name  varchar2(100));
comment on column reply_message.from_user_name is '用户openId';

--是否转为事件
alter table reply_message add(is_issue  number(10));
comment on column reply_message.is_issue is '是否转为事件(0为否,1为是)';

--事件服务单号
alter table reply_message add(service_id  varchar2(500));
comment on column reply_message.service_id is '事件服务单号';

--创建人
alter table reply_message add(create_user  varchar2(100));
comment on column reply_message.create_user is '创建人';
	
			
--微信消息语音提示管理
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '提示语音管理', 'wechatInboxVoicePromptManagement', 1, '后台管理', 1, (select id from permissions where ename='backManagement'), '', '/wechat/wechatInboxVoicePrompt/wechatInboxVoicePromptList.jsp', '', 5, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '修改', 'updateWechatInboxVoicePrompt', 0, '提示语音管理', 1, (select id from permissions where ename='wechatInboxVoicePromptManagement'), '', '', '', 1, '');

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

--微信消息语音提示状态数据字典
insert into propertydomains(id,domainname) values(s_propertydomains.NEXTVAL,'微信消息语音提示状态');
  
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='微信消息语音提示状态'), 1, 1, '关闭', 'gb', 'guanbi', 'admin', '', sysdate, null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='微信消息语音提示状态'), 2, 2, '开启', 'kq', 'kaiqi', 'admin', '', sysdate, null);