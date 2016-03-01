--删除微信中心所有权限
delete from rolehaspermissions  where rolehaspermissions.permissionid in (select a.id from permissions a start with a.ename='weChatCenterManagement' connect by prior a.id=a.parentid);
delete from permissions a where a.id in (select a.id from permissions a start with a.ename='weChatCenterManagement' connect by prior a.id=a.parentid);

--微信中心
insert into permissions (ID,CNAME,ENAME,PERMISSIONTYPE,MODULENAME,ENABLE,PARENTID,DESCRIPTION,NORMALURL,LEADERURL,INDEXID,GRIDURL)
values      (s_permissions.nextval, '微信中心', 'weChatCenterManagement', 1,' ',1,null,'','','',15,'');

--消息管理
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '消息管理', 'inboxManagement', 1, '微信中心', 1, (select id from permissions where ename='weChatCenterManagement'), '', '', '', 0, '');

--模糊消息
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '模糊消息', 'fuzzyMessageManagement', 1, '消息管理', 1, (select id from permissions where ename='inboxManagement'), '', '/hotModuel/inbox/inboxList.ftl', '', 0, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '高级搜索', 'searchInbox', 0, '模糊消息', 1, (select id from permissions where ename='fuzzyMessageManagement'), '', '', '', 1, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '回复', 'replyInbox', 0, '模糊消息', 1, (select id from permissions where ename='fuzzyMessageManagement'), '', '', '', 1, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '删除', 'deleteInbox', 0, '模糊消息', 1, (select id from permissions where ename='fuzzyMessageManagement'), '', '', '', 1, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '事件受理', 'acceptIssue', 0, '模糊消息', 1, (select id from permissions where ename='fuzzyMessageManagement'), '', '', '', 1, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '有/无效', 'setAvailability', 0, '模糊消息', 1, (select id from permissions where ename='fuzzyMessageManagement'), '', '', '', 1, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '粉丝管理', 'fanManagementByInbox', 0, '模糊消息', 1, (select id from permissions where ename='fuzzyMessageManagement'), '', '', '', 1, '');

--流动人口消息
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '流动人口消息', 'floatingPopulationInboxManagement', 1, '消息管理', 1, (select id from permissions where ename='inboxManagement'), '', '/wechat/preciseInbox/preciseInboxList.jsp?type=floatingPopulationInbox', '', 1, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '高级搜索', 'searchFloatingPopulationInbox', 0, '流动人口消息', 1, (select id from permissions where ename='floatingPopulationInboxManagement'), '', '', '', 1, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '回复', 'replyFloatingPopulationInbox', 0, '流动人口消息', 1, (select id from permissions where ename='floatingPopulationInboxManagement'), '', '', '', 1, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '删除', 'deleteFloatingPopulationInbox', 0, '流动人口消息', 1, (select id from permissions where ename='floatingPopulationInboxManagement'), '', '', '', 1, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '事件受理', 'acceptIssueFloatingPopulationInbox', 0, '流动人口消息', 1, (select id from permissions where ename='floatingPopulationInboxManagement'), '', '', '', 1, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '粉丝管理', 'fanManagementByFloatingPopulationInbox', 0, '流动人口消息', 1, (select id from permissions where ename='floatingPopulationInboxManagement'), '', '', '', 1, '');

--治安隐患消息
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '治安隐患消息', 'hiddenDangerInboxManagement', 1, '消息管理', 1, (select id from permissions where ename='inboxManagement'), '', '/wechat/preciseInbox/preciseInboxList.jsp?type=hiddenDangerInbox', '', 2, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '高级搜索', 'searchHiddenDangerInbox', 0, '治安隐患消息', 1, (select id from permissions where ename='hiddenDangerInboxManagement'), '', '', '', 1, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '回复', 'replyHiddenDangerInbox', 0, '治安隐患消息', 1, (select id from permissions where ename='hiddenDangerInboxManagement'), '', '', '', 1, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '删除', 'deleteHiddenDangerInbox', 0, '治安隐患消息', 1, (select id from permissions where ename='hiddenDangerInboxManagement'), '', '', '', 1, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '事件受理', 'acceptIssueHiddenDangerInbox', 0, '治安隐患消息', 1, (select id from permissions where ename='hiddenDangerInboxManagement'), '', '', '', 1, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '粉丝管理', 'fanManagementByHiddenDangerInbox', 0, '治安隐患消息', 1, (select id from permissions where ename='hiddenDangerInboxManagement'), '', '', '', 1, '');

--综合服务消息
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '综合服务消息', 'comprehensiveInboxManagement', 1, '消息管理', 1, (select id from permissions where ename='inboxManagement'), '', '/wechat/preciseInbox/preciseInboxList.jsp?type=comprehensiveInbox', '', 3, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '高级搜索', 'searchComprehensiveInbox', 0, '综合服务消息', 1, (select id from permissions where ename='comprehensiveInboxManagement'), '', '', '', 1, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '回复', 'replyComprehensiveInbox', 0, '综合服务消息', 1, (select id from permissions where ename='comprehensiveInboxManagement'), '', '', '', 1, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '删除', 'deleteComprehensiveInbox', 0, '综合服务消息', 1, (select id from permissions where ename='comprehensiveInboxManagement'), '', '', '', 1, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '事件受理', 'acceptIssueComprehensiveInbox', 0, '综合服务消息', 1, (select id from permissions where ename='comprehensiveInboxManagement'), '', '', '', 1, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '粉丝管理', 'fanManagementByComprehensiveInbox', 0, '综合服务消息', 1, (select id from permissions where ename='comprehensiveInboxManagement'), '', '', '', 1, '');

--我的事件
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '我的事件', 'myIssueListManagementForWeixin', 1, '微信中心', 1, (select id from permissions where ename='weChatCenterManagement'), '', '/hotModuel/myIssue/weIssueViewTabList.ftl', '', 1, '');


--账号管理
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '账号管理', 'tencentUserManagement', 1, '微信中心', 1, (select id from permissions where ename='weChatCenterManagement'), '', '/hotModuel/tencentUser/tencentUserList.ftl', '', 2, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '新增', 'addTencentUser', 0, '账号管理', 1, (select id from permissions where ename='tencentUserManagement'), '', '', '', 1, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '修改', 'updateTencentUser', 0, '账号管理', 1, (select id from permissions where ename='tencentUserManagement'), '', '', '', 1, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '删除', 'deleteTencentUser', 0, '账号管理', 1, (select id from permissions where ename='tencentUserManagement'), '', '', '', 1, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '被关注自动回复', 'subscribeInfo', 0, '账号管理', 1, (select id from permissions where ename='tencentUserManagement'), '', '', '', 1, '');

--短信账号管理
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '短信账号管理', 'smsAccountManagement', 1, '微信中心', 1, (select id from permissions where ename='weChatCenterManagement'), '', '/wechat/smsAccount/smsAccountList.jsp', '', 3, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '新增', 'addSmsAccount', 0, '短信账号管理', 1, (select id from permissions where ename='smsAccountManagement'), '', '', '', 1, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '修改', 'updateSmsAccount', 0, '短信账号管理', 1, (select id from permissions where ename='smsAccountManagement'), '', '', '', 1, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '删除', 'deleteSmsAccount', 0, '短信账号管理', 1, (select id from permissions where ename='smsAccountManagement'), '', '', '', 1, '');


--后台管理
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '后台管理', 'backManagement', 1, '微信中心', 1, (select id from permissions where ename='weChatCenterManagement'), '', '', '', 4, '');

--素材管理
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '素材管理', 'weChatSourceManagement', 1, '后台管理', 1, (select id from permissions where ename='backManagement'), '', '/wechat/source/sourceList.jsp', '', 0, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '高级搜索', 'searchWeChatSource', 0, '素材管理', 1, (select id from permissions where ename='weChatSourceManagement'), '', '', '', 1, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '新增', 'addSource', 0, '素材管理', 1, (select id from permissions where ename='weChatSourceManagement'), '', '', '', 1, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '修改', 'updateSource', 0, '素材管理', 1, (select id from permissions where ename='weChatSourceManagement'), '', '', '', 1, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '删除', 'deleteSource', 0, '素材管理', 1, (select id from permissions where ename='weChatSourceManagement'), '', '', '', 1, '');


--自动回复
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '自动回复', 'autoRespondManagement', 1, '后台管理', 1, (select id from permissions where ename='backManagement'), '', '/wechat/keyWord/keyWordList.jsp', '', 1, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '高级搜索', 'searchKeyWord', 0, '自动回复', 1, (select id from permissions where ename='autoRespondManagement'), '', '', '', 1, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '新增', 'addKeyWord', 0, '自动回复', 1, (select id from permissions where ename='autoRespondManagement'), '', '', '', 1, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '修改', 'updateKeyWord', 0, '自动回复', 1, (select id from permissions where ename='autoRespondManagement'), '', '', '', 1, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '删除', 'deleteKeyWord', 0, '自动回复', 1, (select id from permissions where ename='autoRespondManagement'), '', '', '', 1, '');

--菜单管理
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '菜单管理', 'weChatMenuManagement', 1, '后台管理', 1, (select id from permissions where ename='backManagement'), '', '/wechat/menu/weChatMenuList.jsp', '', 2, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '高级搜索', 'searchWeChatMenu', 0, '菜单管理', 1, (select id from permissions where ename='weChatMenuManagement'), '', '', '', 1, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '新增菜单', 'addWeChatMenu', 0, '菜单管理', 1, (select id from permissions where ename='weChatMenuManagement'), '', '', '', 1, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '修改菜单', 'updateMenu', 0, '菜单管理', 1, (select id from permissions where ename='weChatMenuManagement'), '', '', '', 1, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '绑定素材', 'updateWeChatMenu', 0, '菜单管理', 1, (select id from permissions where ename='weChatMenuManagement'), '', '', '', 1, '');


--分组管理
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '分组管理', 'weChatGroupManagement', 1, '后台管理', 1, (select id from permissions where ename='backManagement'), '', '/wechat/group/weChatGroupList.jsp', '', 3, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '高级搜索', 'searchWeChatGroup', 0, '分组管理', 1, (select id from permissions where ename='weChatGroupManagement'), '', '', '', 1, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '新增分组', 'addWeChatGroup', 0, '分组管理', 1, (select id from permissions where ename='weChatGroupManagement'), '', '', '', 1, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '修改分组', 'updateWeChatGroup', 0, '分组管理', 1, (select id from permissions where ename='weChatGroupManagement'), '', '', '', 1, '');


--粉丝管理
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '粉丝管理', 'FanManagement', 1, '后台管理', 1, (select id from permissions where ename='backManagement'), '', '/wechat/fan/fanList.jsp', '', 4, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '高级搜索', 'searchFan', 0, '粉丝管理', 1, (select id from permissions where ename='FanManagement'), '', '', '', 1, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '添加昵称', 'updateFan', 0, '粉丝管理', 1, (select id from permissions where ename='FanManagement'), '', '', '', 1, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '移动粉丝', 'removeFan', 0, '粉丝管理', 1, (select id from permissions where ename='FanManagement'), '', '', '', 1, '');


--微信响应管理
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '响应管理', 'weChatResponseManagement', 1, '微信中心', 1, (select id from permissions where 
ename='weChatCenterManagement'), '', '', '', 5, '');

--微信响应
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '微信响应', 'weChatResponse', 1, '响应管理', 1, (select id from permissions where 
ename='weChatResponseManagement'), '', '/wechat/weChatResponse/weChatResponseList.jsp', '', 0, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '群发微信', 'weChatMassSend', 0, '微信响应', 1, (select id from permissions where ename='weChatResponse'), '', '', '', 1, '');

--短信响应
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.NEXTVAL, '短信响应', 'smsResponseManagement', 1, '响应管理', 1, (select id from permissions where 
ename='weChatResponseManagement'), '', '/wechat/smsAccount/smsResponseList.jsp', '', 1, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '群发短信', 'addSmsResponse', 0, '短信响应', 1, (select id from permissions where ename='smsResponseManagement'), '', '', '', 1, '');

commit;








