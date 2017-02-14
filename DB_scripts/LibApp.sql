create table LOGIN_USER_DETAILS (
   id NUMBER(20) NOT NULL ,
   username VARCHAR2(20),
   email VARCHAR2(20) not null,
   password VARCHAR2(20),
   isactive char(1),
   Firstname VARCHAR2(50) not null,
   lastname VARCHAR2(50) not null,
   phonenumber VARCHAR2(20),
   Interests varchar(150)
);

CREATE SEQUENCE LibApp_user_seq START WITH 1 
  INCREMENT BY 1 
  MINVALUE 1 ;

drop sequence LibApp_user_seq;

select 'drop sequence '||sequence_name||';'from user_sequences;

CREATE OR REPLACE TRIGGER LibApp_user_trig 
BEFORE INSERT ON LOGIN_USER_DETAILS 
FOR EACH ROW
BEGIN
  SELECT LibApp_user_seq.NEXTVAL
  INTO   :new.id
  FROM   dual;
END;
/

drop trigger LibApp_user_trig;



insert into Login_user_details values('Divya','divya@gmail.com','divya','Y','Divya','B N ','1234567890'); 

delete from LOGIN_USER_DETAILS;
select * from LOGIN_USER_DETAILS
drop table LOGIN_USER_DETAILS
SELECT username, password FROM LOGIN_USER_DETAILS where username = 'qwe' and password = 'qwe';

Alter table LOGIN_uSER_Details add Interests varchar(150);
commit;

select * from Library_books;

create table Library_books(
   title VARCHAR2(50),
   author VARCHAR2(20) ,
   edition VARCHAR2(20),
   publisher VARCHAR2(20),
   availableunits varchar2(5)
);



column S new_val inc; 
select LibApp_user_seq.nextval S from dual; 
alter sequence LibApp_user_seq increment by -&inc minvalue 0; 
select LibApp_user_seq.nextval S from dual; 
alter sequence LibApp_user_seq increment by 1; 
select LibApp_user_seq.nextval from dual; 

