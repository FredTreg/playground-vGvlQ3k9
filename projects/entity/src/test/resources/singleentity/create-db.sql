CREATE TABLE USERS (
 ID bigint GENERATED BY DEFAULT AS IDENTITY (start with 1),
 BDATE timestamp,
 MAIL varchar(255),
 FIRST_NAME varchar(255) not null,
 LAST_NAME varchar(255),
 MSTATUS varchar(255),
 primary key (ID)
 );