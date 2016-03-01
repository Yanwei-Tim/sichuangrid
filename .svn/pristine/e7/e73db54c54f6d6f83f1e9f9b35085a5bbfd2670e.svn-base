--事件类型越级规则设置--

create sequence S_ISSUE_SKIPRULE
minvalue 1
maxvalue 99999999999
start with 1
increment by 1
cache 20;

create table ISSUE_SKIPRULE
(
  ID                NUMBER(10) not null,
  ORG_ID             NUMBER(10) not null,
  ORG_INTERNAL_CODE   VARCHAR2(32),
  ISSUE_TYPE_ID       NUMBER(10) not null,
  ISSUE_TYPE_DOMAIN_ID NUMBER(10) not null,
  ISSUE_URGENT_LEVEL  NUMBER(10) not null,
  SUBMIT_LEVEL       NUMBER(10) not null,
  CC_ORG_IDS          VARCHAR2(512),
  MESSAGE_FLAG       VARCHAR2(1) default 0,
  CREATEUSER        VARCHAR2(32) not null,
  UPDATEUSER        VARCHAR2(32),
  CREATEDATE        DATE not null,
  UPDATEDATE        DATE,
  constraint PK_ISSUESKIPRULE primary key (ID),
  constraint UQ_ISSUESKIPRULE unique (ORG_ID, ISSUE_TYPE_DOMAIN_ID, ISSUE_TYPE_ID, ISSUE_URGENT_LEVEL)
);
comment on table ISSUE_SKIPRULE
  is '事件类型越级规则设置';

comment on column ISSUE_SKIPRULE.ORG_ID
  is '所属网格';
comment on column ISSUE_SKIPRULE.ORG_INTERNAL_CODE
  is '所属网格编号';
comment on column ISSUE_SKIPRULE.ISSUE_TYPE_ID
  is '事件所属类型(小类)id';
comment on column ISSUE_SKIPRULE.ISSUE_TYPE_DOMAIN_ID
  is '事件所属类型(大类)id';
comment on column ISSUE_SKIPRULE.ISSUE_URGENT_LEVEL
  is '审核流程(重大紧急等级)';
comment on column ISSUE_SKIPRULE.SUBMIT_LEVEL
  is '上报层级';
comment on column ISSUE_SKIPRULE.CC_ORG_IDS
  is '抄送单位id数组';
comment on column ISSUE_SKIPRULE.MESSAGE_FLAG
  is '是否短信提醒(0否1是)';



--短信提醒人表--

create sequence S_ISSUE_MESSAGE_REMIND
minvalue 1
maxvalue 99999999999
start with 1
increment by 1
cache 20;

create table ISSUE_MESSAGE_REMIND
(
  ID                NUMBER(10) not null,
  CONTACTS_NAME     VARCHAR2(64) not null,
  CONTACTS_MOBILE   VARCHAR2(11) not null,
  ISSUE_SKIPRULE_ID NUMBER(10) not null,
  CREATEUSER        VARCHAR2(32) not null,
  UPDATEUSER        VARCHAR2(32),
  CREATEDATE        DATE not null,
  UPDATEDATE        DATE,
  constraint PK_ISSUE_MESSAGE_REMIND primary key (ID)
);
comment on table ISSUE_MESSAGE_REMIND
  is '事件类型越级短信提醒';
comment on column ISSUE_MESSAGE_REMIND.CONTACTS_NAME
  is '联系人姓名';
comment on column ISSUE_MESSAGE_REMIND.CONTACTS_MOBILE
  is '联系手机';
comment on column ISSUE_MESSAGE_REMIND.ISSUE_SKIPRULE_ID
  is '事件类型越级规则ID';


--重大紧急等级字典项增加--
insert into propertydomains (ID, DOMAINNAME, SYSTEMSENSITIVE, SYSTEMRESTRICT)
values (s_propertydomains.nextVal, '事件重大紧急等级', 1, '[{"displayName":"低","identify":1},{"displayName":"中","identify":2}
                                                      ,{"displayName":"高","identify":3}]');

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextVal, (select id from propertydomains where domainname='事件重大紧急等级'), 0, 1, '低', 'd', 'di', 
       'admin', '', sysdate, null);
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextVal, (select id from propertydomains where domainname='事件重大紧急等级'), 1, 2, '中', 'z', 'zhong', 
       'admin', '', sysdate, null);
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextVal, (select id from propertydomains where domainname='事件重大紧急等级'), 2, 3, '高', 'g', 'gao', 
       'admin', '', sysdate, null);


--权限菜单--

insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderUrl,indexId)
    VALUES(s_permissions.NEXTVAL,'审核规则','issueRuleManage',1,'考核评估',(select id from permissions where ename='evaluateManagement'),1,'',
	'',(select max(indexId)+1 from permissions where parentId = (select id from permissions where ename='evaluateManagement')));

insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,normalUrl,leaderUrl,indexId)
    VALUES(s_permissions.NEXTVAL,'越级规则','issueSkipruleManagement',1,'审核规则',(select id from permissions where ename='issueRuleManage'),1,'/issue/issueSkipruleManage/issueSkipruleList.jsp',
	'',0);

insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,indexId)
    VALUES(s_permissions.NEXTVAL,'新增','addIssueSkiprule',0,'越级规则',(select id from permissions where ename='issueSkipruleManagement'),1,0);

insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,indexId)
    VALUES(s_permissions.NEXTVAL,'修改','updateIssueSkiprule',0,'越级规则',(select id from permissions where ename='issueSkipruleManagement'),1,1);
 
insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,indexId)
    VALUES(s_permissions.NEXTVAL,'删除','deleteIssueSkiprule',0,'越级规则',(select id from permissions where ename='issueSkipruleManagement'),1,2);

insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,indexId)
    VALUES(s_permissions.NEXTVAL,'查询','findIssueSkiprule',0,'越级规则',(select id from permissions where ename='issueSkipruleManagement'),1,3);

insert into permissions (id,cname,ename,permissionType,moduleName,parentId,enable,indexId)
    VALUES(s_permissions.NEXTVAL,'查看','viewIssueSkiprule',0,'越级规则',(select id from permissions where ename='issueSkipruleManagement'),1,4);

commit;