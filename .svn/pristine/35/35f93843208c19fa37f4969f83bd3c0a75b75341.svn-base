------我的台账的反馈评论表
create sequence S_EVALUATEFEEDBACKS
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;
  
create table EVALUATEFEEDBACKS  (
	   id          		  	  NUMBER(10)       				  not null,
       orgId                  NUMBER(10)                      not null,
       orgInternalCode        VARCHAR2(32)                    not null,
       accountType            VARCHAR2(120)                   not null,
       accountId              NUMBER(10)                      not null,
       feedbacksType          NUMBER(10),
       feedbacksOpinion       NUMBER(10),
       evaluateUser           VARCHAR2(60),
       createUser             VARCHAR2(60) 					   not null,
	   updateUser             VARCHAR2(60),
	   createDate             DATE         					   not null,
	   updateDate             DATE,
	   remark				 VARCHAR2(600),
	   constraint PKevaluateFeedbacks primary key (ID)
       );

comment on table EVALUATEFEEDBACKS
  is '我的台账的反馈评论表 ';
comment on column EVALUATEFEEDBACKS.id
  is '主键ID';
comment on column EVALUATEFEEDBACKS.accountType
  is '评论的类型（我的台账的的种类）';
comment on column EVALUATEFEEDBACKS.accountId
  is '关联的台账的ID';
comment on column EVALUATEFEEDBACKS.feedbacksType
  is '反馈方式';
comment on column EVALUATEFEEDBACKS.feedbacksOpinion
  is '反馈意见';
comment on column EVALUATEFEEDBACKS.evaluateUser
  is '反馈用户';
comment on column EVALUATEFEEDBACKS.createuser
  is '创建用户';
comment on column EVALUATEFEEDBACKS.updateuser
  is '修改用户';
comment on column EVALUATEFEEDBACKS.createdate
  is '创建时间';
comment on column EVALUATEFEEDBACKS.updatedate
  is '修改时间';
  
alter table scenicEquipment modify managerMobile varchar2(15);

alter table scenicEquipment modify mobile varchar2(15);