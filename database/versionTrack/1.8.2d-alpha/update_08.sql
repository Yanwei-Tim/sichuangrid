insert into EARLYWARNING (ID, NAME, REMINDTIME, DESCRIBE, CREATEUSER, CREATEDATE)
values (S_earlyWarning.Nextval,'platformMessageIndividual',10, '平台消息个人删除天数', 'admin', sysdate);
 
insert into EARLYWARNING (ID, NAME, REMINDTIME, DESCRIBE, CREATEUSER, CREATEDATE)
values (S_earlyWarning.Nextval,'platformMessageDepartment',10, '平台消息部门以及系统消息删除天数', 'admin', sysdate);
commit;

--更改KEYPLACES中负责人的长度
alter table keyplaces modify(CHARGEPERSON varchar2(60));