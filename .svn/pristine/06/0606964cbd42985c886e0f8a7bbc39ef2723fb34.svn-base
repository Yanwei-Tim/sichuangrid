--微信响应管理

CREATE SEQUENCE S_weChatResponse
MINVALUE 1
MAXVALUE 9999999999
START WITH 1
INCREMENT BY 1
CACHE 20;


create table weChatResponse(
id number(10) not null,
orgId number(10) not null,
orgCode varchar2(32),
sendType varchar2(100),
wechatUserName varchar2(20),
sourceId number(10),
mediaId varchar2(20),
text varchar2(2000),
msgId varchar2(10),
status varchar(20),
TotalCount number(10),
FilterCount number(10),
SentCount number(10),
ErrorCount number(10),
resultDate date,
userId number(10),
userName varchar2(32),
createUser varchar2(32),
createDate Date,
updateUser varchar2(32),
updateDate Date,
CONSTRAINT pk_weChatResponse PRIMARY KEY (ID)
);
comment on table weChatResponse is '微信响应';
comment on column weChatResponse.sendType is '红袖套分类';
comment on column weChatResponse.wechatUserName is '公众号的微信号';
comment on column weChatResponse.sourceId is '素材id(社管)';
comment on column weChatResponse.mediaId is '素材图文id(微信)';
comment on column weChatResponse.text is '内容';
comment on column weChatResponse.msgId is '微信返回的消息id';
comment on column weChatResponse.status is '群发的状态';
comment on column weChatResponse.TotalCount is 'group_id下粉丝数；或者openid_list中的粉丝数';
comment on column weChatResponse.FilterCount is '过滤（过滤是指特定地区、性别的过滤、用户设置拒收的过滤，用户接收已超4条的过滤）后，准备发送的粉丝数，原则上，FilterCount = SentCount + ErrorCount';
comment on column weChatResponse.SentCount is '发送成功的粉丝数';
comment on column weChatResponse.ErrorCount is '发送失败的粉丝数';
comment on column weChatResponse.resultDate is '发送结果时间';

commit;
