--队伍成员表新增字段MEMBERDEPARTEMENT
alter table fourteammembers add(MEMBERDEPARTEMENT	VARCHAR2(300));
alter table fourteammembers add(serviceName varchar2(90));
--四支队伍表新增字段competentDepartment(主管部门)serviceArea(服务范围) 服务网格(serviceNmae)
alter table fourteams add(competentDepartment  number(10));
alter table fourteams add(serviceArea   varchar2(300));
commit;

--四支队伍主管部门字典项
insert into propertydomains(id,domainname)
  values(s_propertydomains.NEXTVAL,'主管部门');
commit;
insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='主管部门'), 0, 1, '县政务服务中心', 'xzwzx', 'xianzhengwuzhongxin', 'admin', '', sysdate, null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='主管部门'), 0, 2, '县综治办', 'xzzb', 'xianzongzhiban', 'admin', '', sysdate, null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='主管部门'), 0, 3, '县委组织部', 'xwzzb', 'xianweizuzhibu', 'admin', '', sysdate, null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_propertydicts.nextval, (select id from propertydomains p where domainname='主管部门'), 0, 4, '县委宣传部', 'xzxcb', 'xianweixuanchuanbu', 'admin', '', sysdate, null);

--成员表职务和特长字段增加长度
alter table fourteammembers modify SPECIALTY    varchar2(60);
alter table fourteammembers modify DUTY   varchar2(100);
commit;

