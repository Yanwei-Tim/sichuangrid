--先删除测试库原有网格表数据
delete from gridTeamMember;

commit;
--将系统中岗位为信息员或农村信息员的角色  加入到网格员表中
insert into gridTeamMember (id,userId,Memebername,Phonenumber,Orgid,orgCode,Createuser,Createdate,Isactivated)
select s_gridTeamMember.nextval,id,name,mobile,organizationid,orginternalCode,createuser,createdate,'0' 
  from users where id in(select userid from userhasroles where roleId in(select id from roles where roleName='信息员' or roleName='农村信息员') );
commit;







