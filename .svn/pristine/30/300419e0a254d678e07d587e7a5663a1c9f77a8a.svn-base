 create table HOUSEHOLDSTAFFS_Logs
(
  logid                    NUMBER(10),
  operatorType             VARCHAR2(10),
  ID                       NUMBER(15) not null,
  newId                    NUMBER(15) not null,
  tableName                VARCHAR2(32),
  CREATEDATE               DATE not null
);

alter table HOUSEHOLDSTAFFS_Logs add constraint HOUSEHOLDSTAFFS_Logs_ID primary key (logid);

create sequence S_HOUSEHOLDSTAFFS_Logs
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;  
  
	CREATE OR REPLACE TRIGGER householdstaffs_insert 
       after insert on householdstaffs for each row
       begin
       insert into HOUSEHOLDSTAFFS_Logs(logid,operatorType,ID,newId,tableName,CREATEDATE)
       VALUES(S_HOUSEHOLDSTAFFS_Logs.Nextval,'insert',:new.ID,substr((select o.departmentno from organizations o
       where :new.ORGID = o.id),0,4)||:new.ID,'HouseholdStaffs_'||substr((select o.departmentno from organizations o
       where :new.ORGID = o.id),0,4),sysdate);
    end;
   
   CREATE OR REPLACE TRIGGER householdstaffs_update 
      after update on householdstaffs for each row
      begin
        delete from HOUSEHOLDSTAFFS_Logs where id=:new.id;
        insert into HOUSEHOLDSTAFFS_Logs(logid,operatorType,ID,newId,tableName,CREATEDATE)
        VALUES(S_HOUSEHOLDSTAFFS_Logs.Nextval,'update',:new.ID,substr((select o.departmentno from organizations o
        where :new.ORGID = o.id),0,4)||:new.ID,'HouseholdStaffs_'||substr((select o.departmentno from organizations o
        where :new.ORGID = o.id),0,4),sysdate);
   end;
   
   CREATE OR REPLACE TRIGGER householdstaffs_delete 
      after delete on householdstaffs for each row
      begin
      insert into HOUSEHOLDSTAFFS_Logs(logid,operatorType,ID,newId,tableName,CREATEDATE)
      VALUES(S_HOUSEHOLDSTAFFS_Logs.Nextval,'delete',:old.ID,substr((select o.departmentno from organizations o
            where :old.ORGID = o.id),0,4)||:old.ID,'HouseholdStaffs_'||substr((select o.departmentno from organizations o
            where :old.ORGID = o.id),0,4),sysdate);
    end;
    
    
    