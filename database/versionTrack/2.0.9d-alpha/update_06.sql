--正式库中 网格员表gridTeamMember中 有一条数据的一个字段userid数据丢失
update gridTeamMember set  userid=(select id from users where username='18180539510@cdsg') 
	where idcardno='510121198907282266' and memebername ='孙金燕';
	
commit;
