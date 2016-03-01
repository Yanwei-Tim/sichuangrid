--为手机端登录增加两个参数 mobileVersion mobileInnerVersion

ALTER TABLE users ADD (mobileVersion varchar2(32));
COMMENT ON COLUMN users.mobileVersion IS '手机端 版本号';

ALTER TABLE users ADD (mobileInnerVersion varchar2(32));
COMMENT ON COLUMN users.mobileInnerVersion IS '手机端 内部版本号';

--系统管理 用户管理    用户列表    手机库    在线用户

alter table users add isgps number(1) default 0;
COMMENT ON COLUMN users.isgps IS '是否GPS';
alter table users add isFourTeams number(1) default 0;
COMMENT ON COLUMN users.isFourTeams IS '是否四支队伍';
alter table usersessions add clientMode number(1) default 0;
COMMENT ON COLUMN usersessions.clientMode IS '客户端登陆类型';

--开启关闭GPS 四支队伍权限

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '开启GPS', 'openGps', 0, '手机账号库', 1, (select id from permissions where ename='mobileUserManageMent'), '', '', '', 10);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '关闭GPS', 'closeGps', 0, '手机账号库', 1, (select id from permissions where ename='mobileUserManageMent'), '', '', '', 11);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '开启四支队伍', 'openFourTeams', 0, '手机账号库', 1, (select id from permissions where ename='mobileUserManageMent'), '', '', '', 12);
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.NEXTVAL, '关闭四支队伍', 'closeFourTeams', 0, '手机账号库', 1, (select id from permissions where ename='mobileUserManageMent'), '', '', '', 13);
commit;

--优化
--第一步，需要同步id
--业务表中增加字段
ALTER TABLE companyplacebusiness ADD companyplaceid number(10);
COMMENT ON COLUMN companyplacebusiness.companyplaceid IS '单位场所表（companyplace）ID';
--创建索引
create index IDX_BUSSINESS_COMPANYPLACEID on companyplacebusiness (companyplaceid);
--更新数据把关系表中的ID转移进来(该语句执行后若添加新数据后就不可再执行，否则新数据的companyplaceid会被置空)
update companyplacebusiness c  set c.companyplaceid = (select cb.baseid  from companyplacebusinessrelation cb where cb.businessid = c.id );
commit;
