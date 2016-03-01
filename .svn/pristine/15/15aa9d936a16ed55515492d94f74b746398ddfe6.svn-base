--修改菜单中文名
update permissions set modulename = '异常情况报告' where parentid = (select id from permissions where ename  = 'exceptionalSituationRecordManage');
update permissions set cname = '异常情况报告' where ename  = 'exceptionalSituationRecordManage';

commit;