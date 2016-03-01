insert into IssueAnalysis
      (id,
       year,
       month,
       orgid,
       orgCode,
       issueTypeId,
       issueTypeDomainId,
       issueSum,
       createuser,
       createdate)
      select S_IssueAnalysis.NEXTVAL, t.*
        from (select to_char(to_date('2013-09-30 22:15:00','yyyy-MM-dd HH24:mi:ss'), 'yyyy') as year,
                     to_char(to_date('2013-09-30 22:15:00','yyyy-MM-dd HH24:mi:ss'), 'mm') as month,
                     i.createorg,
                     i.createorginternalcode,
                     it.issuetypeid,
                     it.issuetypedomainid,
                     count(0) as issueSum,
                     'admin',
                     to_date('2013-09-30 22:15:00','yyyy-MM-dd HH24:mi:ss')
                from issues i
                left join issuehastypes it on i.id = it.issueid
               where i.createdate between add_months(last_day(to_date('2013-09-30 22:15:00','yyyy-MM-dd HH24:mi:ss')), -1) and
                     add_months(last_day(to_date('2013-09-30 22:15:00','yyyy-MM-dd HH24:mi:ss')) + 1, 0)
               group by i.createorg,
                        i.createorginternalcode,
                        it.issuetypeid,
                        it.issuetypedomainid) t;
                        
                        
 insert into IssueAnalysis
      (id,
       year,
       month,
       orgid,
       orgCode,
       issueTypeId,
       issueTypeDomainId,
       issueSum,
       createuser,
       createdate)
      select S_IssueAnalysis.NEXTVAL, t.*
        from (select to_char(to_date('2013-10-31 22:15:00','yyyy-MM-dd HH24:mi:ss'), 'yyyy') as year,
                     to_char(to_date('2013-10-31 22:15:00','yyyy-MM-dd HH24:mi:ss'), 'mm') as month,
                     i.createorg,
                     i.createorginternalcode,
                     it.issuetypeid,
                     it.issuetypedomainid,
                     count(0) as issueSum,
                     'admin',
                     to_date('2013-10-31 22:15:00','yyyy-MM-dd HH24:mi:ss')
                from issues i
                left join issuehastypes it on i.id = it.issueid
               where i.createdate between add_months(last_day(to_date('2013-10-31 22:15:00','yyyy-MM-dd HH24:mi:ss')), -1) and
                     add_months(last_day(to_date('2013-10-31 22:15:00','yyyy-MM-dd HH24:mi:ss')) + 1, 0)
               group by i.createorg,
                        i.createorginternalcode,
                        it.issuetypeid,
                        it.issuetypedomainid) t;
                     
 insert into IssueAnalysis
      (id,
       year,
       month,
       orgid,
       orgCode,
       issueTypeId,
       issueTypeDomainId,
       issueSum,
       createuser,
       createdate)
      select S_IssueAnalysis.NEXTVAL, t.*
        from (select to_char(to_date('2013-11-30 22:15:00','yyyy-MM-dd HH24:mi:ss'), 'yyyy') as year,
                     to_char(to_date('2013-11-30 22:15:00','yyyy-MM-dd HH24:mi:ss'), 'mm') as month,
                     i.createorg,
                     i.createorginternalcode,
                     it.issuetypeid,
                     it.issuetypedomainid,
                     count(0) as issueSum,
                     'admin',
                     to_date('2013-11-30 22:15:00','yyyy-MM-dd HH24:mi:ss')
                from issues i
                left join issuehastypes it on i.id = it.issueid
               where i.createdate between add_months(last_day(to_date('2013-11-30 22:15:00','yyyy-MM-dd HH24:mi:ss')), -1) and
                     add_months(last_day(to_date('2013-11-30 22:15:00','yyyy-MM-dd HH24:mi:ss')) + 1, 0)
               group by i.createorg,
                        i.createorginternalcode,
                        it.issuetypeid,
                        it.issuetypedomainid) t;
                        
                        
 insert into IssueAnalysis
      (id,
       year,
       month,
       orgid,
       orgCode,
       issueTypeId,
       issueTypeDomainId,
       issueSum,
       createuser,
       createdate)
      select S_IssueAnalysis.NEXTVAL, t.*
        from (select to_char(to_date('2013-12-31 22:15:00','yyyy-MM-dd HH24:mi:ss'), 'yyyy') as year,
                     to_char(to_date('2013-12-31 22:15:00','yyyy-MM-dd HH24:mi:ss'), 'mm') as month,
                     i.createorg,
                     i.createorginternalcode,
                     it.issuetypeid,
                     it.issuetypedomainid,
                     count(0) as issueSum,
                     'admin',
                     to_date('2013-12-31 22:15:00','yyyy-MM-dd HH24:mi:ss')
                from issues i
                left join issuehastypes it on i.id = it.issueid
               where i.createdate between add_months(last_day(to_date('2013-12-31 22:15:00','yyyy-MM-dd HH24:mi:ss')), -1) and
                     add_months(last_day(to_date('2013-12-31 22:15:00','yyyy-MM-dd HH24:mi:ss')) + 1, 0)
               group by i.createorg,
                        i.createorginternalcode,
                        it.issuetypeid,
                        it.issuetypedomainid) t;
 commit;