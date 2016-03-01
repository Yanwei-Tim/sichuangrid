--在吸毒人员、重症精神病人员、社区服刑人员、刑释人员模块添加字段：帮扶人员
alter table druggytask add helpPeople varchar2(300);
alter table mentalpatienttask add helpPeople varchar2(300);
alter table termerrecord add helpPeople varchar2(300);
alter table positiveinforecord add helpPeople varchar2(300);

comment on column druggytask.helpPeople is '帮扶人员';
comment on column mentalpatienttask.helpPeople is '帮扶人员';
comment on column termerrecord.helpPeople is '帮扶人员';
comment on column positiveinforecord.helpPeople is '帮扶人员';


--任务清单每个模块(异常情形报告和发现治安隐患除外)都加上身份证、电话号码字段
alter table propagandaandverification add idcard varchar2(18);
alter table propagandaandverification add phone varchar2(15);
alter table workingsituation add idcard varchar2(18);
alter table workingsituation add phone varchar2(15);
alter table druggytask add idcard varchar2(18);
alter table druggytask add phone varchar2(15);
alter table mentalpatienttask add idcard varchar2(18);
alter table mentalpatienttask add phone varchar2(15);
alter table termerrecord add idcard varchar2(18);
alter table termerrecord add phone varchar2(15);
alter table positiveinforecord add idcard varchar2(18);
alter table positiveinforecord add phone varchar2(15);

comment on column propagandaandverification.idcard is '身份证号码';
comment on column propagandaandverification.phone is '电话号码';
comment on column workingsituation.idcard is '身份证号码';
comment on column workingsituation.phone is '电话号码';
comment on column druggytask.idcard is '身份证号码';
comment on column druggytask.phone is '电话号码';
comment on column mentalpatienttask.idcard is '身份证号码';
comment on column mentalpatienttask.phone is '电话号码';
comment on column termerrecord.idcard is '身份证号码';
comment on column termerrecord.phone is '电话号码';
comment on column positiveinforecord.idcard is '身份证号码';
comment on column positiveinforecord.phone is '电话号码';