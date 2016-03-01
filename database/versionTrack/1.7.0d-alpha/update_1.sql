alter table  party_members_enroll add logoutStatus  NUMBER(10) default 0;
comment on column party_members_enroll.logoutStatus  is '是否注销';


insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'注销', 'logoutMemberEnroll', 0, '党员报到情况', 1, ' ', (select id from permissions where ename='partyMemberRegistrationSituationManagement'));
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, DESCRIPTION, PARENTID)
values (s_permissions.NEXTVAL,'取消注销', 'unLogoutMemberEnroll', 0, '党员报到情况', 1, ' ', (select id from permissions where ename='partyMemberRegistrationSituationManagement'));

---为是否注销状态增加默认值--
update party_members_enroll t set t.logoutStatus = 0 where t.logoutStatus is null;

commit;


