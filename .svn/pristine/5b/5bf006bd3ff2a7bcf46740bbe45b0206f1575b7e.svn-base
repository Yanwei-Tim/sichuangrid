update permissions p set p.normalurl='/statAnalyse/issueManage/issueStatList.jsp'  where ename='issueDealStatReport';
---执行前先查询一下是不是一条数据

-----处理中国层级的那条待办事件
update issuesteps set statecode=1000 where target=(select id from organizations where orgname='中国') and statecode<=500;

delete propertydicts where displayname='北改办';

  --删除党员报到和志愿者岗位关联表的外键约束--
 alter table memberHasVolunteerJobs drop constraint fkMemberHasVolunteerJobs;
 
 --添加党员报到和志愿者岗位关联表的外键约束--
 alter table memberHasVolunteerJobs
   add constraint fkMemberHasVolunteerJobs foreign key (volunteerJobsId)
      references volunteerJobs (id);

commit;

drop table IssueTypeStanals;