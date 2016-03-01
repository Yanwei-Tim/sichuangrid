-- 任务清单的报表索引

create index idx_propaganda_org_cdate on propagandaAndVerification(orginternalCode,createDate);
create index idx_workingSituation_org_cdate on workingSituation(orginternalCode,createDate);
create index idx_exceptSRecord_org_cdate on exceptionalSituationRecord(orgcode,createDate);
create index idx_hiddenDanger_org_cdate on hiddenDanger(orginternalCode,createDate);
create index idx_termerRecord_org_cdate on termerRecord(orgcode,createDate);
create index idx_posiRecord_org_cdate on positiveInfoRecord(orgcode,createDate);
create index idx_druggyTask_org_cdate on druggyTask(orginternalCode,createDate);
create index idx_mentalTask_org_cdate on mentalPatientTask(orginternalCode,createDate);