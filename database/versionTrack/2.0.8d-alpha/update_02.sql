
--素材类型数据字典修改
update propertydicts set internalid = '1' where displayname = '文本' and propertydomainid = (select id from propertyDomains where domainName= '微信');
update propertydicts set internalid = '2' where displayname = '图片' and propertydomainid = (select id from propertyDomains where domainName= '微信');
update propertydicts set internalid = '3' where displayname = '图文' and propertydomainid = (select id from propertyDomains where domainName= '微信');
update propertydicts set internalid = '4' where displayname = '语音' and propertydomainid = (select id from propertyDomains where domainName= '微信');

commit;


















