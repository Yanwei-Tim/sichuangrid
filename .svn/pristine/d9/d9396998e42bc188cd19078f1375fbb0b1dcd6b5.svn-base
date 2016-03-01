create index idx_issuelogs_stepid on issuelogs(stepId);

create index idx_issuehastype_domainid on issuehastypes(ISSUETYPEDOMAINID);

create index idx_issuesteps_issue on issuesteps(issue);

create index idx_issueEvaluate_issueid on issueEvaluate(issueid);

create index idx_platformessgfile_pmid on platformMessagesAttachFiles(pmId);

create index idx_moduelClickCounts_userid on moduelClickCounts(userid);

create index idx_permanettaddr_addrname on PermanentAddress(addressname);

create index idx_permanettaddr_parentid on PermanentAddress(parentid);

create index idx_issueapplydelay_orgid on  issueapplydelay(auditorg);

drop index IND_ISSTEP_TARGET ;

create index IND_ISSTEP_TARGET on issuesteps(target,stateCode);

create index idx_issues_historic on issues(historic);

create index idx_organizations_orgname on organizations(orgname);

create index index_baseinfo_birthday on baseinfo (birthday);

----单位场所基础表组织机构id索引
create index idx_companyplacebase_org on companyplacebase(org);
-----单位场所表baseid索引
create index idx_companyplace_baseid on companyplace(baseid);
-----单位场所表英文标识索引
create index idx_classificationen on companyplace(classificationen);
-----单位场所规模企业标识索引
create index idx_companyplace_scaletype on companyplace(scaletype);
-----单位场所业务类型索引
create index idx_iskeytype on companyplacebusiness(iskeytype);

-----线上红牌督办打分时显示加分且打分原因显示null 历史数据处理
create index idx_regradedpoints_regradedorg on regradedpoints(regradedorg);
update regradedpoints r
   set r.content = concat((select o.orgname
                            from organizations o
                           where o.id = r.regradedorg),
                          substr(r.content, 5))
 where r.content like 'null的%';
 commit;
