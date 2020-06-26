DROP TABLE Account CASCADE CONSTRAINTS;
DROP TABLE Customer CASCADE CONSTRAINTS;
DROP SEQUENCE hibernate_sequence;
CREATE SEQUENCE hibernate_sequence start with 1006 increment by  1;

create table Customer (
  customer_id number(10) not null,
  emailid varchar2(20),
  name varchar2(20),
  date_of_birth date, 
  CONSTRAINT customer_pk PRIMARY KEY (customer_id)
);

INSERT INTO Customer VALUES (1001,'steven@infy.com', 'Steven Martin', SYSDATE-7476);
INSERT INTO Customer VALUES (1002,'kevin@infy.com', 'Kevin Nelson', SYSDATE-11374);
INSERT INTO Customer VALUES (1003,'john@infy.com', 'John Matric', SYSDATE-12344);
INSERT INTO Customer VALUES (1004,'chan@infy.com', 'Chan Mathew', SYSDATE-10344);
INSERT INTO Customer VALUES (1005,'jill@infy.com', 'Jill Mathew', SYSDATE-11374);

CREATE TABLE Account (
  ac_id number(10) not null,
  ac_number varchar2(20),
  creation_date date,
  balance number(10),
  cust_id number(10),
  CONSTRAINT acc_pk PRIMARY KEY (ac_id),
  CONSTRAINT fk_ac_cust FOREIGN KEY (cust_id) REFERENCES Customer
);



INSERT INTO Account VALUES(12346, '6642160005012193',SYSDATE-3400,20000,1001);
INSERT INTO Account VALUES(12347, '6642160005012194',SYSDATE-4560,30000,1001);
INSERT INTO Account VALUES(12348, '6642160005012195',SYSDATE-1160,20000,1001);
INSERT INTO Account VALUES(12349, '6642160005012196',SYSDATE-5660,20000,1002);
INSERT INTO Account VALUES(12350, '6642160005012197',SYSDATE-5640,20000,1003);
INSERT INTO Account VALUES(12351, '6642160005012198',SYSDATE-5620,20000,1003);



commit;


select * from Account;
select * from Customer;
