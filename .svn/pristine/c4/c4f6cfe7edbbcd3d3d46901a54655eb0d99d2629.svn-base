--事件模块（对于四支队伍新增 类型ID，队伍名称（ID）标识）
alter table issuesteps add fourTeamsTypeID number(10);
comment on column issuesteps.fourTeamsTypeID is '四支队伍id（parentID）';
commit;
alter table issuesteps add fourTeams NUMBER(10);
comment on column issuesteps.fourTeams is '四支队伍名称(存储为队伍的ID)';
commit;
--事件模块（对于四支队伍新增 队伍名称（ID）标识）
alter table issuelogs add fourTeamsName varchar2(60);
comment on column issuelogs.fourTeamsName is '四支队伍名称';
commit;