/*四级平台移动只日常办公系统*/
update permissions p set p.cname ='四级体系建设',p.modulename='日常办公',p.parentid=(select id from permissions p where p.ename ='workingManageSystem') where p.cname ='四级平台';
commit;
update permissions p set p.modulename='四级体系建设' where p.modulename ='四级平台';
commit;


--四级平台添加管理部门字段 只针对县级
ALTER TABLE fourLevelPlatform ADD (supervisorydepartment   VARCHAR2(90));

--户籍地调整添加部分区县
insert into PERMANENTADDRESS  (addressno, addressname, parentid, addresslevel)
values('500116','江津区','500100','3');
insert into PERMANENTADDRESS  (addressno, addressname, parentid, addresslevel)
values('511907','经开区','513700','3');
insert into PERMANENTADDRESS  (addressno, addressname, parentid, addresslevel)
values('510825','经济开发区','510800','3');
insert into PERMANENTADDRESS  (addressno, addressname, parentid, addresslevel)
values('510323','高新区','510300','3');
insert into PERMANENTADDRESS  (addressno, addressname, parentid, addresslevel)
values('659001','石河子市','659000','3');
commit;

--修改育龄妇女中孕育证编号的长度
alter table nurtureswomen modify MATERNITYCARDNO  VARCHAR2(90);
alter table DM_NURTURESWOMEN_TEMP modify MATERNITYCARDNO  VARCHAR2(90);
---修改楼宇信息联系电话长度
alter table BUILDDATAS modify PHONE  VARCHAR2(30);