create table LIBAPP_USER_DETAILS (
   id NUMBER(20) NOT NULL ,
   username VARCHAR2(20),
   email VARCHAR2(20) not null,
   password VARCHAR2(20) not null,
   isactive char(1),
   Firstname VARCHAR2(50) not null,
   lastname VARCHAR2(50),
   phonenumber VARCHAR2(20),
   Interests varchar(150)
);

CREATE SEQUENCE LibApp_user_seq START WITH 1 
  INCREMENT BY 1 
  MINVALUE 1 ;

CREATE OR REPLACE TRIGGER LibApp_user_trig 
BEFORE INSERT ON LIBAPP_USER_DETAILS 
FOR EACH ROW
BEGIN
  SELECT LibApp_user_seq.NEXTVAL
  INTO   :new.id
  FROM   dual;
END;
/


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


select 'drop sequence '||sequence_name||';'from user_sequences;
select * from LIBAPP_USER_DETAILS;
select * from Library_books;

drop sequence LibApp_user_seq;
drop trigger LibApp_user_trig;
delete from LIBAPP_USER_DETAILS;
dROP TABLE LIBAPP_USER_DETAILS;