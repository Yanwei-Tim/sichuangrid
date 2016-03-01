--研判分析 重点青少年-年龄段区分图 年龄段分类：0-6岁 6-18岁 18-25岁  25-35岁 
update propertydicts set displayname='0~6岁', simplepinyin = '06syx', fullpinyin = '06suiyixia'
where displayname = '10岁以下' and propertydomainid = (select id from propertydomains where domainname='重点青少年年龄区间');
update propertydicts set displayname='6~18岁', simplepinyin = '618s', fullpinyin = '628sui'
where displayname = '10~16岁' and propertydomainid = (select id from propertydomains where domainname='重点青少年年龄区间');
update propertydicts set displayname='18~25岁', simplepinyin = '1825s', fullpinyin = '1825sui'
where displayname = '16~20岁' and propertydomainid = (select id from propertydomains where domainname='重点青少年年龄区间');
update propertydicts set displayname='25~35岁', simplepinyin = '2535s', fullpinyin = '2535sui'
where displayname = '20岁以上' and propertydomainid = (select id from propertydomains where domainname='重点青少年年龄区间');
commit;