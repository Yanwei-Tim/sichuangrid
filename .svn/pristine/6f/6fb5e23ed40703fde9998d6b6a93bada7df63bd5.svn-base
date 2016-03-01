--ALTER TABLE BUILDLAYOUT drop constraint fkBuildLayout;
ALTER TABLE BUILDLAYOUT RENAME  TO BUILDLAYOUT_OLD;
ALTER TABLE BUILDLAYOUT_OLD drop constraint pkBuildLayout;
ALTER TABLE BUILDLAYOUT_OLD add constraint pkBuildLayout_Old primary key(id);

create table BUILDLAYOUT(
   id                               NUMBER(10)                      not null,
   buildId		      		     VARCHAR2(32)					not null,
   orgId                          NUMBER(10)                      not null,
   orgInternalCode         VARCHAR2(32)                    not null,
   layoutInfo                  CLOB,
   totalRow				     NUMBER(10)                      not null,
   totalCol			             NUMBER(10)						not null,
   createUser                  VARCHAR2(32)                    not null,
   updateUser                VARCHAR2(32),
   updateDate                DATE,
   createDate                 DATE                            not null,
   constraint pkBuildLayout primary key (id)
);
comment on table BUILDLAYOUT is
'楼宇布局表';
comment on column BUILDLAYOUT.id is
'主键';
comment on column BUILDLAYOUT.buildId is
'楼宇ID';
comment on column BUILDLAYOUT.layoutInfo is
'布局信息';
comment on column BUILDLAYOUT.totalRow is
'总布局行数';
comment on column BUILDLAYOUT.totalCol is
'总布局列数';

--insert into BUILDLAYOUT 
--   	select s_buildlayout.nextval,buildId,orgId,orginternalcode,'' layoutinfo,totalrow,totalcolumn,'admin',null,sysdate,sysdate
--   	from (select buildId ,orgId,orginternalcode,totalrow,totalcolumn from BUILDLAYOUT_OLD 
--    				group by buildId ,orgId,orginternalcode,totalrow,totalcolumn);

insert into BUILDLAYOUT 
   	select s_buildlayout.nextval,t.buildId,orgId,orginternalcode,'['||t.layoutinfo_s||']' layoutinfo,totalrow,totalcolumn,'admin',null,sysdate,sysdate
   	from (
         with ItemAttribute  as (
         	select layoutrow,layoutcolumn,buildId,'{"row":'||layoutrow||',"col":'||layoutcolumn||',"colSpan":'||layoutcolspan||',"rowSpan":1,"housePropertyId":'||housepropertyid||',"room":"'||room||'"}' layoutinfo
            from BUILDLAYOUT_OLD
        )
        ,ItemAttributeGrouped as (
         	select xmlagg(xmlparse(content layoutinfo||',' wellformed) order by layoutrow,layoutcolumn ).getclobval() layoutinfo_s ,buildId
            from ItemAttribute
            group by buildId
         )
         select * from ItemAttributeGrouped   
     ) t,(
     	select buildId ,orgId,orginternalcode,totalrow,totalcolumn from BUILDLAYOUT_OLD 
    	group by buildId ,orgId,orginternalcode,totalrow,totalcolumn
     ) i where t.buildid = i.buildId;
    				

commit;