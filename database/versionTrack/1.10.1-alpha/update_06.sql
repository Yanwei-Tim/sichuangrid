--jms信息表序列
create sequence S_JMSMESSAGE
minvalue 1
maxvalue 9999999999
start with 5
increment by 1
cache 20;

create table JMSMESSAGE
(
  ID              NUMBER(10) not null,
  JMSURL          VARCHAR2(300) not null,
  JMSQUEUE        VARCHAR2(300) not null,
  MESSAGETYPE     VARCHAR2(100) not null,
  ISSENDERMESSAGE NUMBER(1) not null,
  CREATEUSER      VARCHAR2(32) not null,
  UPDATEUSER      VARCHAR2(32),
  CREATEDATE      DATE not null,
  UPDATEDATE      DATE,
  constraint PKJMSMESSAGE primary key (ID)
);
comment on table JMSMESSAGE is
'jms信息表';
comment on column JMSMESSAGE.ID is
'主键id';
comment on column JMSMESSAGE.JMSURL is
'jms的url';
comment on column JMSMESSAGE.JMSQUEUE is
'jms的队列';
comment on column JMSMESSAGE.MESSAGETYPE is
'jms的信息类型';
comment on column JMSMESSAGE.ISSENDERMESSAGE is
'是否启动';
insert into jmsmessage (ID, JMSURL, JMSQUEUE, MESSAGETYPE, ISSENDERMESSAGE, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (1, 'tcp://localhost:61616', 'populationQueue', 'populationJmsType', 0, 'admin', 'admin', sysdate, sysdate);
insert into jmsmessage (ID, JMSURL, JMSQUEUE, MESSAGETYPE, ISSENDERMESSAGE, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (2, 'tcp://localhost:61616', 'hbaseQueue', 'hbaseJmsType', 0, 'admin', 'admin', sysdate, sysdate);
insert into jmsmessage (ID, JMSURL, JMSQUEUE, MESSAGETYPE, ISSENDERMESSAGE, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (3, 'tcp://localhost:61616', 'solrQueue', 'solrJmsType', 0, 'admin', 'admin', sysdate, sysdate);
insert into jmsmessage (ID, JMSURL, JMSQUEUE, MESSAGETYPE, ISSENDERMESSAGE, CREATEUSER, UPDATEUSER, CREATEDATE, UPDATEDATE)
values (4, 'tcp://localhost:61616', 'solrQueue', 'populationSolrSearchJmsType', 0, 'admin', 'admin', sysdate, sysdate);

commit;