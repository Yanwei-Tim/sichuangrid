---新增是否pc端接收，是否手机端接收
alter table proclamations add pcusable number(1); 
comment on column proclamations.pcusable is '否pc端接收';
alter table proclamations add mobileusable number(1); 
comment on column proclamations.mobileusable is '是否手机端接收';
commit;