--修正gis地图中的名称显示问题 更正'户籍人口'为'常住人口'
update gisTypeManages set name = '户籍人口' where name = '常住人口' and tablename = 'HOUSEHOLDSTAFFS';

--20131008 通知通报菜单转移到互动交流系统中
insert into permissions(ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, parentId, DESCRIPTION, NORMALURL, LEADERURL, INDEXID)
  values (s_permissions.nextval, '通知通报', 'basePublicNoticeManagement', 1, '通知通报', 1, (select id from permissions where ename='interactionManagement'), '', '', '', null);
update permissions set parentid=(select id from permissions where ename='basePublicNoticeManagement') where id=(select id from permissions where ename='publicNoticeManagement');
commit;

-- 通知通报新增落款字段
alter table PUBLICNOTICE add publicnoticeinscribe varchar2(200);
-- Add comments to the columns 
comment on column PUBLICNOTICE.publicnoticeinscribe
  is '落款';
