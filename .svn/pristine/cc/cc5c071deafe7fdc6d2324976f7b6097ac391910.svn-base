--失业原因选项中修改
update propertydicts set displayname = '刑满释放（未成年人除外）', simplepinyin = 'xmsfwcnrcw', fullpinyin = 'xingmanshifangweiche'
       where propertydomainid = (select id from propertydomains where domainname = '失业原因')
       and displayname = '刑满释放';
update propertydicts set displayname = '假释、缓刑、暂予监外外执行、管制、剥夺政治权利等社区矫正人员（未成年人除外）', 
                     simplepinyin = 'jshxzyjwzx', fullpinyin = 'jishihuanxingzanyuji'
       where propertydomainid = (select id from propertydomains where domainname = '失业原因')
       and displayname = '假释、缓刑、暂予监外外执行、管制、剥夺政治权利等社区矫正人员';
update propertydicts set displayname = '劳动教养期满或提前解除劳动教养的（未成年人除外）', 
                     simplepinyin = 'ldjyqmhtqj', fullpinyin = 'laodongjiaoyangqiman'
       where propertydomainid = (select id from propertydomains where domainname = '失业原因')
       and displayname = '劳动教养期满或提前解除劳动教养的';
commit;