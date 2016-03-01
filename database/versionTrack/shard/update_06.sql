update relatepersons r set r.persontype = 'positiveInfo' where r.persontype='positiveinfo';
update relatepersons r set r.persontype = 'rectificativePerson' where r.persontype='rectify';
update relatepersons r set r.persontype = 'mentalPatient' where r.persontype='mentalpatient';
update relatepersons r set r.persontype = 'idleYouth' where r.persontype='idleyouth';
update relatepersons r set r.persontype = 'superiorVisit' where r.persontype='superiorvisit';
update relatepersons r set r.persontype = 'dangerousGoodsPractitioner' where r.persontype='dangerousgoodspractitioner';
update relatepersons r set r.persontype = 'otherAttentionPersonnel' where r.persontype='otherattention';
commit;
update issuesteps
   set (targetorglevel,targetorgfunctionalorgType) =
       (select orglevel,case when functionalorgType is null then 0 else functionalorgType end from organizations where id = issuesteps.target);
commit;
update  GISTYPEMANAGES g set name='刑释人员' where g.checked=1 and g.innerKey = 'keyPerson' and key='POSITIVEINFO';
commit;
---事件对接表添加来源方式字段
alter table issuejointtemps add( data_source VARCHAR2(32));
