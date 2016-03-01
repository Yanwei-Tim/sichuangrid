--字典项“矛盾纠纷类型”原有选项改变现实顺序
 update issuetypes set internalid=1,indexid=1
 where domainid=(select id from issuetypedomains where domainname='矛盾劝解') and issuetypename='信访问题处理';
 
 update issuetypes set internalid=2,indexid=2
 where domainid=(select id from issuetypedomains where domainname='矛盾劝解') and issuetypename='不稳定因素报告';

update issuetypes set internalid=28,indexid=28,issuetypename='其他',content='其他',simplepinyin='qt',fullpinyin='qita'
 where domainid=(select id from issuetypedomains where domainname='矛盾劝解') and issuetypename='进入大调解信息系统';
 commit;
--字典项“矛盾纠纷类型”添加选项
insert into issuetypes (ID, ORGID, DOMAINID, INTERNALID, PERSONALIZED, ENABLED, INDEXID, ISSUETYPENAME, CONTENT, SIMPLEPINYIN, FULLPINYIN, ORGINTERNALCODE, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_Issuetypes.Nextval, null, (select id from issuetypedomains where domainname='矛盾劝解'), 3, 0, 1, 3, '婚姻家庭', '婚姻家庭', 'hyjt', 'hunyinjiating', null, 'admin', null, sysdate, sysdate);

insert into issuetypes (ID, ORGID, DOMAINID, INTERNALID, PERSONALIZED, ENABLED, INDEXID, ISSUETYPENAME, CONTENT, SIMPLEPINYIN, FULLPINYIN, ORGINTERNALCODE, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_Issuetypes.Nextval, null, (select id from issuetypedomains where domainname='矛盾劝解'), 4, 0, 1, 4, '邻里纠纷', '邻里纠纷', 'lljf', 'linlijiufen', null, 'admin', null, sysdate, sysdate);

insert into issuetypes (ID, ORGID, DOMAINID, INTERNALID, PERSONALIZED, ENABLED, INDEXID, ISSUETYPENAME, CONTENT, SIMPLEPINYIN, FULLPINYIN, ORGINTERNALCODE, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_Issuetypes.Nextval, null, (select id from issuetypedomains where domainname='矛盾劝解'), 5, 0, 1, 5, '环境生态', '环境生态', 'hjst', 'huanjingshengtai', null, 'admin', null, sysdate, sysdate);

insert into issuetypes (ID, ORGID, DOMAINID, INTERNALID, PERSONALIZED, ENABLED, INDEXID, ISSUETYPENAME, CONTENT, SIMPLEPINYIN, FULLPINYIN, ORGINTERNALCODE, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_Issuetypes.Nextval, null, (select id from issuetypedomains where domainname='矛盾劝解'), 6, 0, 1, 6, '建筑工程', '建筑工程', 'jzgc', 'jianzhugongcheng', null, 'admin', null, sysdate, sysdate);

insert into issuetypes (ID, ORGID, DOMAINID, INTERNALID, PERSONALIZED, ENABLED, INDEXID, ISSUETYPENAME, CONTENT, SIMPLEPINYIN, FULLPINYIN, ORGINTERNALCODE, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_Issuetypes.Nextval, null, (select id from issuetypedomains where domainname='矛盾劝解'), 7, 0, 1, 7, '物业管理', '物业管理', 'wygl', 'wuyeguanli', null, 'admin', null, sysdate, sysdate);

insert into issuetypes (ID, ORGID, DOMAINID, INTERNALID, PERSONALIZED, ENABLED, INDEXID, ISSUETYPENAME, CONTENT, SIMPLEPINYIN, FULLPINYIN, ORGINTERNALCODE, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_Issuetypes.Nextval, null, (select id from issuetypedomains where domainname='矛盾劝解'), 8, 0, 1, 8, '企业改制', '企业改制', 'qygz', 'qiyegaizhi', null, 'admin', null, sysdate, sysdate);

insert into issuetypes (ID, ORGID, DOMAINID, INTERNALID, PERSONALIZED, ENABLED, INDEXID, ISSUETYPENAME, CONTENT, SIMPLEPINYIN, FULLPINYIN, ORGINTERNALCODE, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_Issuetypes.Nextval, null, (select id from issuetypedomains where domainname='矛盾劝解'), 9, 0, 1, 9, '司法活动', '司法活动', 'sfhd', 'sifahuodong', null, 'admin', null, sysdate, sysdate);

insert into issuetypes (ID, ORGID, DOMAINID, INTERNALID, PERSONALIZED, ENABLED, INDEXID, ISSUETYPENAME, CONTENT, SIMPLEPINYIN, FULLPINYIN, ORGINTERNALCODE, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_Issuetypes.Nextval, null, (select id from issuetypedomains where domainname='矛盾劝解'), 10, 0, 1, 10, '干部作风', '干部作风', 'gbzf', 'ganbuzuofeng', null, 'admin', null, sysdate, sysdate);

insert into issuetypes (ID, ORGID, DOMAINID, INTERNALID, PERSONALIZED, ENABLED, INDEXID, ISSUETYPENAME, CONTENT, SIMPLEPINYIN, FULLPINYIN, ORGINTERNALCODE, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_Issuetypes.Nextval, null, (select id from issuetypedomains where domainname='矛盾劝解'), 11, 0, 1, 11, '劳资纠纷', '劳资纠纷', 'lzjf', 'laozijiufen', null, 'admin', null, sysdate, sysdate);

insert into issuetypes (ID, ORGID, DOMAINID, INTERNALID, PERSONALIZED, ENABLED, INDEXID, ISSUETYPENAME, CONTENT, SIMPLEPINYIN, FULLPINYIN, ORGINTERNALCODE, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_Issuetypes.Nextval, null, (select id from issuetypedomains where domainname='矛盾劝解'), 12, 0, 1, 12, '征地拆迁', '征地拆迁', 'zdcq', 'zhengdichaiqian', null, 'admin', null, sysdate, sysdate);

insert into issuetypes (ID, ORGID, DOMAINID, INTERNALID, PERSONALIZED, ENABLED, INDEXID, ISSUETYPENAME, CONTENT, SIMPLEPINYIN, FULLPINYIN, ORGINTERNALCODE, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_Issuetypes.Nextval, null, (select id from issuetypedomains where domainname='矛盾劝解'), 13, 0, 1, 13, '军人安置', '军人安置', 'jraz', 'junrenanzhi', null, 'admin', null, sysdate, sysdate);

insert into issuetypes (ID, ORGID, DOMAINID, INTERNALID, PERSONALIZED, ENABLED, INDEXID, ISSUETYPENAME, CONTENT, SIMPLEPINYIN, FULLPINYIN, ORGINTERNALCODE, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_Issuetypes.Nextval, null, (select id from issuetypedomains where domainname='矛盾劝解'), 14, 0, 1, 14, '房屋和宅基地', '房屋和宅基地', 'fwhzjd', 'fangwuhezhaijidi', null, 'admin', null, sysdate, sysdate);

insert into issuetypes (ID, ORGID, DOMAINID, INTERNALID, PERSONALIZED, ENABLED, INDEXID, ISSUETYPENAME, CONTENT, SIMPLEPINYIN, FULLPINYIN, ORGINTERNALCODE, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_Issuetypes.Nextval, null, (select id from issuetypedomains where domainname='矛盾劝解'), 15, 0, 1, 15, '农村“三资”', '农村“三资”', 'ncsz', 'nongcunsanzi', null, 'admin', null, sysdate, sysdate);

insert into issuetypes (ID, ORGID, DOMAINID, INTERNALID, PERSONALIZED, ENABLED, INDEXID, ISSUETYPENAME, CONTENT, SIMPLEPINYIN, FULLPINYIN, ORGINTERNALCODE, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_Issuetypes.Nextval, null, (select id from issuetypedomains where domainname='矛盾劝解'), 16, 0, 1, 16, '农民负担', '农民负担', 'nmfd', 'nongminfudan', null, 'admin', null, sysdate, sysdate);

insert into issuetypes (ID, ORGID, DOMAINID, INTERNALID, PERSONALIZED, ENABLED, INDEXID, ISSUETYPENAME, CONTENT, SIMPLEPINYIN, FULLPINYIN, ORGINTERNALCODE, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_Issuetypes.Nextval, null, (select id from issuetypedomains where domainname='矛盾劝解'), 17, 0, 1, 17, '院校问题', '院校问题', 'yxwt', 'yuanxiaowenti', null, 'admin', null, sysdate, sysdate);

insert into issuetypes (ID, ORGID, DOMAINID, INTERNALID, PERSONALIZED, ENABLED, INDEXID, ISSUETYPENAME, CONTENT, SIMPLEPINYIN, FULLPINYIN, ORGINTERNALCODE, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_Issuetypes.Nextval, null, (select id from issuetypedomains where domainname='矛盾劝解'), 18, 0, 1, 18, '民族宗教', '民族宗教', 'mzzj', 'minzuzongjiao', null, 'admin', null, sysdate, sysdate);

insert into issuetypes (ID, ORGID, DOMAINID, INTERNALID, PERSONALIZED, ENABLED, INDEXID, ISSUETYPENAME, CONTENT, SIMPLEPINYIN, FULLPINYIN, ORGINTERNALCODE, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_Issuetypes.Nextval, null, (select id from issuetypedomains where domainname='矛盾劝解'), 19, 0, 1, 19, '经济活动', '经济活动', 'jjhd', 'jingjihuodong', null, 'admin', null, sysdate, sysdate);

insert into issuetypes (ID, ORGID, DOMAINID, INTERNALID, PERSONALIZED, ENABLED, INDEXID, ISSUETYPENAME, CONTENT, SIMPLEPINYIN, FULLPINYIN, ORGINTERNALCODE, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_Issuetypes.Nextval, null, (select id from issuetypedomains where domainname='矛盾劝解'), 20, 0, 1, 20, '山林土地', '山林土地', 'sltd', 'shanlintudi', null, 'admin', null, sysdate, sysdate);

insert into issuetypes (ID, ORGID, DOMAINID, INTERNALID, PERSONALIZED, ENABLED, INDEXID, ISSUETYPENAME, CONTENT, SIMPLEPINYIN, FULLPINYIN, ORGINTERNALCODE, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_Issuetypes.Nextval, null, (select id from issuetypedomains where domainname='矛盾劝解'), 21, 0, 1, 21, '医患纠纷', '医患纠纷', 'yhjf', 'yihuanjiufen', null, 'admin', null, sysdate, sysdate);

insert into issuetypes (ID, ORGID, DOMAINID, INTERNALID, PERSONALIZED, ENABLED, INDEXID, ISSUETYPENAME, CONTENT, SIMPLEPINYIN, FULLPINYIN, ORGINTERNALCODE, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_Issuetypes.Nextval, null, (select id from issuetypedomains where domainname='矛盾劝解'), 22, 0, 1, 22, '村（社区）务管理', '村（社区）务管理', 'csqwgl', 'cunshequwuguanli', null, 'admin', null, sysdate, sysdate);

insert into issuetypes (ID, ORGID, DOMAINID, INTERNALID, PERSONALIZED, ENABLED, INDEXID, ISSUETYPENAME, CONTENT, SIMPLEPINYIN, FULLPINYIN, ORGINTERNALCODE, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_Issuetypes.Nextval, null, (select id from issuetypedomains where domainname='矛盾劝解'), 23, 0, 1, 23, '行政执法活动', '行政执法活动', 'xzzfhd', 'xingzhengzhifahuodon', null, 'admin', null, sysdate, sysdate);

insert into issuetypes (ID, ORGID, DOMAINID, INTERNALID, PERSONALIZED, ENABLED, INDEXID, ISSUETYPENAME, CONTENT, SIMPLEPINYIN, FULLPINYIN, ORGINTERNALCODE, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_Issuetypes.Nextval, null, (select id from issuetypedomains where domainname='矛盾劝解'), 24, 0, 1, 24, '海事渔事', '海事渔事', 'hsys', 'haishiyushi', null, 'admin', null, sysdate, sysdate);

insert into issuetypes (ID, ORGID, DOMAINID, INTERNALID, PERSONALIZED, ENABLED, INDEXID, ISSUETYPENAME, CONTENT, SIMPLEPINYIN, FULLPINYIN, ORGINTERNALCODE, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_Issuetypes.Nextval, null, (select id from issuetypedomains where domainname='矛盾劝解'), 25, 0, 1, 25, '交通及生产安全', '交通及生产安全', 'jtjscaq', 'jiaotongjishengchana', null, 'admin', null, sysdate, sysdate);

insert into issuetypes (ID, ORGID, DOMAINID, INTERNALID, PERSONALIZED, ENABLED, INDEXID, ISSUETYPENAME, CONTENT, SIMPLEPINYIN, FULLPINYIN, ORGINTERNALCODE, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_Issuetypes.Nextval, null, (select id from issuetypedomains where domainname='矛盾劝解'), 26, 0, 1, 26, '计划生育', '计划生育', 'jhsy', 'jihuashengyu', null, 'admin', null, sysdate, sysdate);

insert into issuetypes (ID, ORGID, DOMAINID, INTERNALID, PERSONALIZED, ENABLED, INDEXID, ISSUETYPENAME, CONTENT, SIMPLEPINYIN, FULLPINYIN, ORGINTERNALCODE, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (s_Issuetypes.Nextval, null, (select id from issuetypedomains where domainname='矛盾劝解'), 27, 0, 1, 27, '合同履行', '合同履行', 'htlx', 'hetonglvxing', null, 'admin', null, sysdate, sysdate);
commit;