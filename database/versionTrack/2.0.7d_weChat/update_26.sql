alter table exceptionalSituationRecord add hasException char(1) default 0;
alter table HiddenDanger add hasException char(1) default 0;
alter table druggyTask add hasException char(1) default 0;
alter table mentalPatientTask add hasException char(1) default 0;
alter table termerRecord add hasException char(1) default 0;
alter table positiveInfoRecord add hasException char(1) default 0;

comment on column exceptionalSituationRecord.Hasexception is '有无异常';
comment on column HiddenDanger.Hasexception is '有无异常';
comment on column druggyTask.Hasexception is '有无异常';
comment on column mentalPatientTask.Hasexception is '有无异常';
comment on column termerRecord.Hasexception is '有无异常';
comment on column positiveInfoRecord.Hasexception is '有无异常';




--线上任务清单菜单“重症精神病人员”改名“严重精神障碍患者”
update permissions set cname='严重精神障碍患者' where ename='mentalPatientTaskVisitmanagement';
update  permissions p set p.modulename='严重精神障碍患者' where p.parentid in (select id from permissions where ename='mentalPatientTaskVisitmanagement' );