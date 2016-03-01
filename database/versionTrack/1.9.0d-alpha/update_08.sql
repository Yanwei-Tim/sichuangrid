--人口历史表新增两个字段 involveTibetCount涉藏人数 involveSinkiangCount涉疆人数
alter table populationStatType_2012_10 add(involveTibetCount NUMBER(10),involveSinkiangCount NUMBER(10));
alter table populationStatType_2012_11 add(involveTibetCount NUMBER(10),involveSinkiangCount NUMBER(10));
alter table populationStatType_2012_12 add(involveTibetCount NUMBER(10),involveSinkiangCount NUMBER(10));
alter table populationStatType_2012_9 add(involveTibetCount NUMBER(10),involveSinkiangCount NUMBER(10));
alter table populationStatType_2013_1 add(involveTibetCount NUMBER(10),involveSinkiangCount NUMBER(10));
alter table populationStatType_2013_2 add(involveTibetCount NUMBER(10),involveSinkiangCount NUMBER(10));
alter table populationStatType_2013_3 add(involveTibetCount NUMBER(10),involveSinkiangCount NUMBER(10));
alter table populationStatType_2013_4 add(involveTibetCount NUMBER(10),involveSinkiangCount NUMBER(10));
alter table populationStatType_2013_5 add(involveTibetCount NUMBER(10),involveSinkiangCount NUMBER(10));
alter table populationStatType_2013_6 add(involveTibetCount NUMBER(10),involveSinkiangCount NUMBER(10));
alter table populationStatType_2013_7 add(involveTibetCount NUMBER(10),involveSinkiangCount NUMBER(10));
alter table populationStatType_2013_8 add(involveTibetCount NUMBER(10),involveSinkiangCount NUMBER(10));
alter table populationStatType_2013_9 add(involveTibetCount NUMBER(10),involveSinkiangCount NUMBER(10));
alter table populationStatType_2013_10 add(involveTibetCount NUMBER(10),involveSinkiangCount NUMBER(10));
alter table populationStatType_2013_11 add(involveTibetCount NUMBER(10),involveSinkiangCount NUMBER(10));
alter table populationStatType_2013_12 add(involveTibetCount NUMBER(10),involveSinkiangCount NUMBER(10));
alter table populationStatType_2014_1 add(involveTibetCount NUMBER(10),involveSinkiangCount NUMBER(10));
alter table populationStatType_2014_2 add(involveTibetCount NUMBER(10),involveSinkiangCount NUMBER(10));
alter table populationStatType_2014_3 add(involveTibetCount NUMBER(10),involveSinkiangCount NUMBER(10));
alter table populationStatType_2014_4 add(involveTibetCount NUMBER(10),involveSinkiangCount NUMBER(10));
alter table populationStatType_2014_5 add(involveTibetCount NUMBER(10),involveSinkiangCount NUMBER(10));
alter table populationStatType_2014_6 add(involveTibetCount NUMBER(10),involveSinkiangCount NUMBER(10));
alter table populationStatType_2014_7 add(involveTibetCount NUMBER(10),involveSinkiangCount NUMBER(10));
alter table populationStatType_2014_8 add(involveTibetCount NUMBER(10),involveSinkiangCount NUMBER(10));
alter table populationStatType_2014_9 add(involveTibetCount NUMBER(10),involveSinkiangCount NUMBER(10));
----修改组织机构成员库成员的所在组织结构名称长度
alter table primaryMembers modify orgname  VARCHAR2(200);
--流动人口新增藏疆标识权限
insert into permissions(ID,CNAME,ENAME,PERMISSIONTYPE,MODULENAME,ENABLE,DESCRIPTION,PARENTID)
values(s_permissions.NEXTVAL,'藏疆标识','tibetSinkiangIdentification',0,'流动人口',1,' ',
(select id from permissions where ename = 'floatingPopulationManagement'));
commit;