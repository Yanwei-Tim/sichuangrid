--地点从20改到50个字即150个字符--
 
alter table propagandaandverification modify(address varchar2(150));

alter table hiddendanger modify(address varchar2(150));

alter table mentalPatientTask modify(place varchar2(150));
alter table druggyTask modify(place varchar2(150));