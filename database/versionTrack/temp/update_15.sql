alter table sms_Account add(contentSuffix  varchar2(40));
comment on column sms_Account.contentSuffix is '短信内容后缀';

COMMIT;

create table issueSpecialTemporary(
            orgCode varchar2(32),
            issueDealCount number(10),
            agencyOfOpinionCount number(10),
            specialCrowdCount number(10),
            dispose number(1)
);
create index indexIssuespecialtemporary on issuespecialtemporary(orgCode);
commit;