create table answer (id integer not null, answer varchar(255) not null, username varchar(20), question_id integer, primary key (id));
create table question (id integer not null, question varchar(255), type_ integer default 1, answer1 varchar(255), answer2 varchar(255), answer3 varchar(255), answer4 varchar(255), answer5 varchar(255), answer6 varchar(255), answer7 varchar(255), description varchar(255), servey_id integer, parentQuestion integer unique, childQuestion integer unique, primary key (id));
create table serveys (id integer not null, name varchar(255), description varchar(255), gender varchar(4), beginAge integer, endAge integer, beginDate date, endDate date,checkLogin varchar(6), primary key (id));
create table user (id integer not null, username varchar(20), password varchar(20), age integer, gender varchar(4), registerDate varchar(25), primary key (id));
alter table answer add index FKABCA3FBE2DCE0EEB (question_id), add constraint FKABCA3FBE2DCE0EEB foreign key (question_id) references question (id);
alter table question add index FKBA823BE6A62E2F88 (servey_id), add constraint FKBA823BE6A62E2F88 foreign key (servey_id) references serveys (id);
alter table question add index FKBA823BE617317FD9 (childQuestion), add constraint FKBA823BE617317FD9 foreign key (childQuestion) references question (id);
alter table question add index FKBA823BE6316B2567 (parentQuestion), add constraint FKBA823BE6316B2567 foreign key (parentQuestion) references question (id);
