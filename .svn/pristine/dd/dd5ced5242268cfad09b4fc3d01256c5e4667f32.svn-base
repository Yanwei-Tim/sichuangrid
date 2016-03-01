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
    

--奖励评分管理
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '奖励评分管理', 'weChatScoreManagement', 1, '微信中心', 1, (select id from permissions where 
ename='weChatCenterManagement'), '', '', '', 6, '');

--用户满意度
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '用户满意度', 'weChatEvaluationIssueHandleManagement', 1, '奖励评分管理', 1, (select id from permissions where 
ename='weChatScoreManagement'), '', '/wechat/evaluationIssueHandle/evaluationIssueHandleList.jsp', '', 0, '');

--高级搜索
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '高级搜索', 'searchEvaluationIssueHandle', 0, '用户满意度', 1, (select id from permissions where ename='weChatEvaluationIssueHandleManagement'), '', '', '', 1, '');

commit;
















