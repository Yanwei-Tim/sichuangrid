--户口号家庭编号字段长度增加
alter table householdstaffs_5116 modify accountnumber VARCHAR2(210);
alter table householdstaffs_5101 modify accountnumber VARCHAR2(210);
alter table householdstaffs_5103 modify accountnumber VARCHAR2(210);
alter table householdstaffs_5104 modify accountnumber VARCHAR2(210);
alter table householdstaffs_5106 modify accountnumber VARCHAR2(210);
alter table householdstaffs_5107 modify accountnumber VARCHAR2(210);
alter table householdstaffs_5108 modify accountnumber VARCHAR2(210);
alter table householdstaffs_5109 modify accountnumber VARCHAR2(210);
alter table householdstaffs_5110 modify accountnumber VARCHAR2(210);
alter table householdstaffs_5111 modify accountnumber VARCHAR2(210);
alter table householdstaffs_5113 modify accountnumber VARCHAR2(210);
alter table householdstaffs_5114 modify accountnumber VARCHAR2(210);
alter table householdstaffs_5115 modify accountnumber VARCHAR2(210);
alter table householdstaffs_5117 modify accountnumber VARCHAR2(210);
alter table householdstaffs_5118 modify accountnumber VARCHAR2(210);
alter table householdstaffs_5119 modify accountnumber VARCHAR2(210);
alter table householdstaffs_5120 modify accountnumber VARCHAR2(210);
alter table householdstaffs_5132 modify accountnumber VARCHAR2(210);
alter table householdstaffs_5133 modify accountnumber VARCHAR2(210);
alter table householdstaffs_5134 modify accountnumber VARCHAR2(210);
alter table householdstaffs_3301 modify accountnumber VARCHAR2(210);
alter table householdstaffs_5105 modify accountnumber VARCHAR2(210);

alter table censusregisterfamilys modify accountnumber VARCHAR2(210);
alter table groupfamily modify familyaccount VARCHAR2(210);
--修改非公有制经济组织名称长度
alter table newEconomicOrganizations modify name VARCHAR2(210);
--修改单位场所名称长度
alter table companyplacebase modify name NVARCHAR2(210);
--修改单位场所名称长度
alter table SCENICSPOTS modify name VARCHAR2(150);
---修改数据管理非公有制经济组织名称长度
alter table dm_neweconoorg_temp modify name VARCHAR2(210);
--修改房屋信息表的产权人证件号码的长度
alter table houseInfo modify certificateNumbe VARCHAR2(60);

--服务成员索引
create index idx_servicememobj_objid on serviceMemberHasObject(objectId);

--待验证被督办后的事件，验证通过后，该事件的督办的状态设置为未督办
update issuesteps set superviselevel=-1 where id in(select isu.id from issuesteps isu where isu.statecode=1000);
commit;

--将原有的基本信息统计报表(特殊人群概况)更改名称
update permissions set cname = '各市(州)重点数据情况统计' where ename = 'specialCrowdReports';
--删除基本信息统计报表权限
delete from ROLEHASPERMISSIONS r where r.permissionid = (
select id from permissions where ename = 'baseSituationStatement'
);
delete from permissions where ename = 'baseSituationStatement';

--用户覆盖率报表更名
update permissions set cname = '网格化服务管理工作情况' where ename = 'userActivateReport';

--更改育龄妇女中 “孕育证发证单位” 的长度
alter table nurtureswomen modify Maternitycardunit VARCHAR2(150);

commit;