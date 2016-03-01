--三本台账审问类型大类增加其他属性
insert into propertydicts(ID,PROPERTYDOMAINID,INTERNALID,DISPLAYSEQ,DISPLAYNAME,SIMPLEPINYIN,FULLPINYIN,CREATEUSER,UPDATEUSER,CREATEDATE,UPDATEDATE)
values(s_propertydicts.nextval,(select id from propertydomains p where domainname = '中江稳定台账涉稳类别'),15,16,'其他','qt','qita','admin',
   '',sysdate,null);
   
--三本台账困难涉稳问题类型增加其他内容选项
insert into propertydicts(ID,PROPERTYDOMAINID,INTERNALID,DISPLAYSEQ,DISPLAYNAME,SIMPLEPINYIN,FULLPINYIN,CREATEUSER,UPDATEUSER,CREATEDATE,UPDATEDATE)
values(s_propertydicts.nextval,(select id from propertydomains p where domainname = '中江稳定台账涉稳问题类别'),15,112,'其他内容','qtnr','qitaneirong','admin',
   '',sysdate,null);
   
--三本台账困难类型家庭成员增加技能特长字段
ALTER TABLE ledgerpoorpeoplemember ADD (skillsSpeciality varchar2(60));

--三本台账困难增加涉稳类型备注字段
ALTER TABLE ledgersteadywork ADD (otherRemark varchar2(60));