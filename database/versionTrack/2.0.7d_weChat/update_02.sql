create sequence S_accountReportData
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;

CREATE TABLE accountReportData (
    id number(10) not null,
	  orgId   number(10),
    PEOPLEASPIRATION_TOTALS  NUMBER(10),
    PEOPLEASPIRATION_WATER  NUMBER(10),
    PEOPLEASPIRATION_TRAFFIC  NUMBER(10),
    PEOPLEASPIRATION_ENERGY  NUMBER(10),
    PEOPLEASPIRATION_EDUCATION  NUMBER(10),
    PEOPLEASPIRATION_SCIENCE  NUMBER(10),
    PEOPLEASPIRATION_MEDICAL  NUMBER(10),
    PEOPLEASPIRATION_LABOR  NUMBER(10),
    PEOPLEASPIRATION_ENVIRONMENT  NUMBER(10),
    PEOPLEASPIRATION_TOWN  NUMBER(10),
    PEOPLEASPIRATION_AGRICULTURE  NUMBER(10),
    PEOPLEASPIRATION_OTHER  NUMBER(10),
    POORPEOPLE_TOTALS  NUMBER(10),
    POORPEOPLE_HOUSE  NUMBER(10),
    POORPEOPLE_LIFE  NUMBER(10),
    POORPEOPLE_MEDICAL  NUMBER(10),
    POORPEOPLE_EMPLOYMENT  NUMBER(10),
    POORPEOPLE_SCHOOL  NUMBER(10),
    POORPEOPLE_OTHER  NUMBER(10),
    STEATY_TOTALS  NUMBER(10),
    STEATY_INVOLVING_LAW  NUMBER(10),
    STEATY_SOIL_WATER  NUMBER(10),
    STEATY_GOVERNMENT_FINANCE  NUMBER(10),
    STEATY_CIVIL_AFFAIRS  NUMBER(10),
    STEATY_POPULATION_TREATMENT  NUMBER(10),
    STEATY_LABOR_SECURITY  NUMBER(10),
    STEATY_TRANSPORTATION  NUMBER(10),
    STEATY_URBAN_CONSTRUCTION  NUMBER(10),
    STEATY_PARTY  NUMBER(10),
    STEATY_EDUCATION  NUMBER(10),
    STEATY_ENTERPRISE  NUMBER(10),
    STEATY_ENVIRONMENTAL_SCIENCE  NUMBER(10),
    STEATY_ORGANIZATION  NUMBER(10),
    STEATY_OTHER  NUMBER(10),
    STEATY_PEOPLE  NUMBER(10),
    STEATY_OTHER_PEOPLE  NUMBER(10),
    createUser           VARCHAR2(32),
  	updateUser           VARCHAR2(32),
    createDate           DATE,
    updateDate           DATE,
	  constraint PKAccountReportData primary key (id));
    comment on table accountReportData  is '三本台账报表数据';
    comment on column accountReportData.id  is '报表数据ID';
    comment on column accountReportData.orgId  is '组织机构ID';
    comment on column accountReportData.PEOPLEASPIRATION_TOTALS  is '民生台账合计数量';
    comment on column accountReportData.PEOPLEASPIRATION_WATER  is '民生水利合计';
    comment on column accountReportData.PEOPLEASPIRATION_TRAFFIC  is '民生交通合计';
    comment on column accountReportData.PEOPLEASPIRATION_ENERGY  is '民生能源合计';
    comment on column accountReportData.PEOPLEASPIRATION_EDUCATION  is '民生教育合计';
    comment on column accountReportData.PEOPLEASPIRATION_SCIENCE  is '民生科技文体合计';
    comment on column accountReportData.PEOPLEASPIRATION_MEDICAL  is '民生医疗卫生合计';
    comment on column accountReportData.PEOPLEASPIRATION_LABOR  is '民生劳动与社会保障合计';
    comment on column accountReportData.PEOPLEASPIRATION_ENVIRONMENT  is '民生环境保护合计';
    comment on column accountReportData.PEOPLEASPIRATION_TOWN  is '民生城乡规划建设与管理合计';
    comment on column accountReportData.PEOPLEASPIRATION_AGRICULTURE  is '民生农业合计';
    comment on column accountReportData.PEOPLEASPIRATION_OTHER  is '民生其他合计';
    comment on column accountReportData.POORPEOPLE_TOTALS  is '困难台账合计';
    comment on column accountReportData.POORPEOPLE_HOUSE  is '困难住房合计';
    comment on column accountReportData.POORPEOPLE_LIFE  is '困难生活合计';
    comment on column accountReportData.POORPEOPLE_MEDICAL  is '困难医疗合计';
    comment on column accountReportData.POORPEOPLE_EMPLOYMENT  is '困难就业合计';
    comment on column accountReportData.POORPEOPLE_SCHOOL  is '困难就学合计';
    comment on column accountReportData.POORPEOPLE_OTHER  is '困难其他合计';
    comment on column accountReportData.STEATY_TOTALS  is '稳定台账合计';
    comment on column accountReportData.STEATY_INVOLVING_LAW  is '稳定-涉法涉诉';
    comment on column accountReportData.STEATY_SOIL_WATER  is '稳定-林水土';
    comment on column accountReportData.STEATY_GOVERNMENT_FINANCE  is '稳定-惠农政策及村（社区）政务财务';
    comment on column accountReportData.STEATY_CIVIL_AFFAIRS  is '稳定-民政问题';
    comment on column accountReportData.STEATY_POPULATION_TREATMENT  is '稳定-人口与医疗卫生';
    comment on column accountReportData.STEATY_LABOR_SECURITY  is '稳定-劳动保障';
    comment on column accountReportData.STEATY_TRANSPORTATION  is '稳定-交通运输';
    comment on column accountReportData.STEATY_URBAN_CONSTRUCTION  is '稳定-城建及综合执法';
    comment on column accountReportData.STEATY_PARTY  is '稳定-党纪政纪';
    comment on column accountReportData.STEATY_EDUCATION  is '稳定-教育';
    comment on column accountReportData.STEATY_ENTERPRISE  is '稳定-企业改制';
    comment on column accountReportData.STEATY_ENVIRONMENTAL_SCIENCE  is '稳定-环境保护';
    comment on column accountReportData.STEATY_ORGANIZATION  is '稳定-组织人事';
    comment on column accountReportData.STEATY_OTHER  is '稳定-其他类';
    comment on column accountReportData.STEATY_PEOPLE  is '稳定-重点人员';
    comment on column accountReportData.STEATY_OTHER_PEOPLE  is '稳定-其他';
commit;