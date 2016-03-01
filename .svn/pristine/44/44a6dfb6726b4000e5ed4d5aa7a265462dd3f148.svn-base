  	insert into accountsteps_xichang(id,accountid,accounttype,targetorgid,targetorgcode,isfinish,createuser,createdate)
       select s_accountsteps_xichang.NEXTVAL,t.*  from (select id as accountid,'PEOPLEASPIRATION' as accounttype,
       orgid as targetorgid,ORGINTERNALCODE as targetorgcode,0,'admin',sysdate from peopleaspirations) t;
       
        insert into accountsteps_xichang(id,accountid,accounttype,targetorgid,targetorgcode,isfinish,createuser,createdate)
       select s_accountsteps_xichang.NEXTVAL,t.*  from (select id as accountid,'POORPEOPLE' as accounttype,
       orgid as targetorgid,ORGINTERNALCODE as targetorgcode,0,'admin',sysdate from poorpeoples) t;
      
      insert into accountsteps_xichang(id,accountid,accounttype,targetorgid,targetorgcode,isfinish,createuser,createdate) 
       select s_accountsteps_xichang.NEXTVAL,t.*  from (select id as accountid,'STEADYWORK' as accounttype,
       orgid as targetorgid,ORGINTERNALCODE as targetorgcode,0,'admin',sysdate from steadyworks) t;
       
       commit;