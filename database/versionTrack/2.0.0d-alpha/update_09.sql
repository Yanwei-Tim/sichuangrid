insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '短信提示语管理', 'smsMessageMarkManagement', 1, '短信管理', 1, (select id from permissions where ename='OASmsManagementSystem'), '', '/hotModuel/interaction/smsMessageMark/smsMessageMarkList.jsp', '/hotModuel/interaction/smsMessageMark/smsMessageMarkList.jsp', 1, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '添加', 'addSmsMessageMark', 0, '短信提示语管理', 1, (select id from permissions where ename='smsMessageMarkManagement'), '', '', '', 0, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '查看', 'viewSmsMessageMark', 0, '短信提示语管理', 1, (select id from permissions where ename='smsMessageMarkManagement'), '', '', '', 1, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '修改', 'updateSmsMessageMark', 0, '短信提示语管理', 1, (select id from permissions where ename='smsMessageMarkManagement'), '', '', '', 2, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '删除', 'deleteSmsMessageMark', 0, '短信提示语管理', 1, (select id from permissions where ename='smsMessageMarkManagement'), '', '', '', 3, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '短信交流', 'smsExchangeManagement', 1, '互动交流', 1, (select id from permissions where ename='interactionManagement'), '', '', '', 1, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '收件箱', 'smsInboxManagement', 1, '短信交流', 1, (select id from permissions where ename='smsExchangeManagement'), '', '/hotModuel/interaction/sms/inbox/smsReceivedBoxList.jsp', '/hotModuel/interaction/sms/inbox/smsReceivedBoxList.jsp', 1, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '发件箱', 'smsOutboxManagement', 1, '短信交流', 1, (select id from permissions where ename='smsExchangeManagement'), '', '/hotModuel/interaction/sms/outbox/smsSendList.jsp', '/hotModuel/interaction/sms/outbox/smsSendList.jsp', 2, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '查看', 'viewSmsInbox', 0, '收件箱', 1, (select id from permissions where ename='smsInboxManagement'), '', '', '', 0, '');


insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '处理', 'updateSmsInbox', 0, '收件箱', 1, (select id from permissions where ename='smsInboxManagement'), '', '', '', 1, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '删除', 'deleteSmsInbox', 0, '收件箱', 1, (select id from permissions where ename='smsInboxManagement'), '', '', '', 2, '');


insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '查询', 'searchSmsInbox', 0, '收件箱', 1, (select id from permissions where ename='smsInboxManagement'), '', '', '', 3, '');



insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '查看', 'viewSmsOutbox', 0, '发件箱', 1, (select id from permissions where ename='smsOutboxManagement'), '', '', '', 0, '');


insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '新短信', 'addSmsOutbox', 0, '发件箱', 1, (select id from permissions where ename='smsOutboxManagement'), '', '', '', 1, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '删除', 'deleteSmsOutbox', 0, '发件箱', 1, (select id from permissions where ename='smsOutboxManagement'), '', '', '', 2, '');


insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '查询', 'searchSmsOutbox', 0, '发件箱', 1, (select id from permissions where ename='smsOutboxManagement'), '', '', '', 3, '');