delete 	from househasactualpopulation_bak03 where HOUSEID not in (select oldId from houseinfotemp);
	commit;

delete 	from houseHasImportantPopulation where HOUSEID not in (select oldId from houseinfotemp);
	commit;
	
delete 	from rentalhouse where HOUSEID not in (select oldId from houseinfotemp);
	commit;

--清洗实口人房关联中间表
alter table househasactualpopulation_bak03 ADD (flag number(2));
declare 
cursor cur is select /*+ parallel(x,12) */ rowid,x.* from househasactualpopulation_bak03 x;
counter number(10);
begin
counter:=0;
for rec in cur loop
update househasactualpopulation_bak03 p set HOUSEID=(select newid from houseinfotemp t where rec.HOUSEID=t.oldid )
,flag=1 where p.rowid = rec.rowid;
counter:=counter+1;
if(mod(counter,10000)=0) then
        commit;
  end if;
end loop;
end;


--清洗业务人房关联中间表
alter table houseHasImportantPopulation ADD (flag number(2));
declare 
cursor cur is select /*+ parallel(y,8) */ rowid,y.* from houseHasImportantPopulation y;
counter number(10);
begin
counter:=0;
for rec in cur loop
update houseHasImportantPopulation p set HOUSEID=(select newid from houseinfotemp t where rec.HOUSEID=t.oldid )
,flag=1 where  p.rowid = rec.rowid ;
counter:=counter+1;
if(mod(counter,10000)=0) then
        commit;
  end if;
end loop;
end;


--清洗出租房表
alter table rentalhouse ADD (flag number(2));
declare 
cursor cur is select * from rentalhouse;
counter number(10);
begin
counter:=0;
for rec in cur loop
update rentalhouse p set HOUSEID=(select newid from houseinfotemp t where rec.HOUSEID=t.oldid )
,flag=1 where p.id = rec.id;
counter:=counter+1;
if(mod(counter,10000)=0) then
        commit;
  end if;
end loop;
end;


----以上结果确认无误 删除标记字段
--alter table househasactualpopulation drop (flag);
--alter table houseHasImportantPopulation drop (flag);
--alter table rentalhouse drop (flag);
