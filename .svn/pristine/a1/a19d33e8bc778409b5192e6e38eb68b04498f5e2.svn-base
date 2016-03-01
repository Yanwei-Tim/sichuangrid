--红袖套队伍管理：注销认证
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, 

NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '注销认证', 'cancellationOfCertification', 0, '红袖套队伍管理', 1, 

(select id from permissions where ename='redCuffTeamManage'), '', '', '', 1, '');

update permissions set cname = '红袖套队伍' where ename  = 'redCuffTeamManage';
update permissions set moduleName = '红袖套队伍' where      ename  = 'addRedCuffMemeber';
update permissions set moduleName = '红袖套队伍' where      ename  = 'updateRedCuffMemeber';
update permissions set moduleName = '红袖套队伍' where      ename  = 'deleteRedCuffMemeber';
update permissions set moduleName = '红袖套队伍' where      ename  = 'viewRedCuffMemeber';
update permissions set moduleName = '红袖套队伍' where      ename  = 'searchRedCuffMemeber';
update permissions set moduleName = '红袖套队伍' where      ename  = 'importRedCuffMemeber';
update permissions set moduleName = '红袖套队伍' where      ename  = 'cancellationOfCertification';


alter table redCuffTeamMember add(telephone  VARCHAR2(100));
comment on column redCuffTeamMember.telephone is '固定电话';

alter table redCuffTeamMember add(exitRedCuffTeam  number(10));
comment on column redCuffTeamMember.exitRedCuffTeam is '是否退出红袖套';

commit;