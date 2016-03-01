create sequence S_excelimportlog
minvalue 1
maxvalue 99999999999999999999
start with 1
increment by 1
cache 20;
create table excelimportlog  (
   ID                   NUMBER         not null,
   uuid                VARCHAR2(36)  not null,
   createUser       VARCHAR2(32) 	,
   fileName          VARCHAR2(100),
   fileType          VARCHAR2(50),
   status               number default 1,
   importDataNum        number,
   currentDealNum       number,
   errorCountNum        number,
   createDate           DATE                            not null,
   updateDate           DATE,
   constraint PK_EXCELIMPORTLOG primary key (ID)
);
comment on table excelimportlog is
'excel 导入日志';

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, 
       parentId, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.nextval, '导入日志系统', 'excelImportLogSystem', 1, ' ', 1, '', '', '', '',2);
       
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, 
       parentId, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
values (s_permissions.nextval, '导入日志', 'excelImportLogManagement', 1, '导入日志系统', 1, 
       (select id from permissions where ename='excelImportLogSystem'), '', '', 
       'baseinfo/excelimportlog/excelImportLogList.jsp', 
       (select max(indexid) from permissions where modulename = '导入日志系统')+1);


commit;