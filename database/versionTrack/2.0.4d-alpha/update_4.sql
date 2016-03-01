ALTER TABLE workingsituation ADD (policeName varchar2(60));
update propertydicts  set displayseq = 3 where propertydomainid=(select id from propertydomains where domainname='民警带领下工作内容')
 and displayname='其他';
 
 insert into propertydicts(id, propertydomainid, internalid, displayseq, displayname ,simplepinyin, fullpinyin, createuser,createdate)
 values(s_propertydicts.nextval,(select id from propertydomains where domainname='民警带领下工作内容'), 0,2, '宣传四川省流动人口信息登记办法', 'xcldrkglbf', 'xuanchuangliudongrenkouguanli', 'admin', sysdate);
 
 update propertydicts  set displayname = '无守楼护院人员' where propertydomainid=(select id from propertydomains where domainname='治安隐患异常类型')
 and displayname='有无守楼护院人员';
 
alter table hiddendanger modify(exceptionsituation varchar2(1000));
 
alter table Workingsituation modify(remark varchar2(600));

alter table propagandaandverification modify(remark varchar2(600));

alter table hiddendanger modify(remark varchar2(600));

alter table Workingsituation modify(advice varchar2(600));

alter table propagandaandverification modify(advice varchar2(600));

alter table hiddendanger modify(advice varchar2(600));




alter table propagandaandverification modify(address varchar2(120));

alter table hiddendanger modify(address varchar2(120));

 alter table hiddendanger drop(isHasAbnormal);