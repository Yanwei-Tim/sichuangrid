---地址信息里添加组织机构相关字段
alter table ADDRESSINFO add ADDORGID NUMBER(10);
alter table ADDRESSINFO add ADDORGCODE VARCHAR2(50);
comment on column ADDRESSINFO.ADDORGID  is '组织机构id';
comment on column ADDRESSINFO.ADDORGCODE  is '组织机构code';

---清洗地址存储过程
create or replace procedure update_addressinfo_org(tablename in varchar2) as
  v_sql varchar2(2000);
begin
  v_sql := 'update ADDRESSINFO t set (addorgid, addorgcode) = (select max(orgid), max(orginternalcode) from ' ||
           tablename ||
           ' t1 where t.id = t1.addressinfoid group by t1.addressinfoid) where  exists (select id from ' ||
           tablename || ' t1 where t.id = t1.addressinfoid)';
  EXECUTE IMMEDIATE v_sql;
  commit;
exception
  WHEN OTHERS THEN
    ROLLBACK;
end update_addressinfo_org;
