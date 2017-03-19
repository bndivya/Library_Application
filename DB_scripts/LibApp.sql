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
   id number(20) not null,
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
select *  from Library_books;

drop sequence LibApp_user_seq;
drop trigger LibApp_user_trig;
delete from LIBAPP_USER_DETAILS;
dROP TABLE LIBAPP_USER_DETAILS;

insert into library_books(id, title, author, edition, publisher, availableunits) values(1, 'The Da Vinci Code', 'Dan Brown', '1990', 'Penguin Books', 10);
insert into library_books(id, title, author, edition, publisher, availableunits) values(2, 'One Night at the Call Center', 'Chethan Bhagat', '1990', '', 10);
insert into library_books(id, title, author, edition, publisher, availableunits) values(3, 'How to Kill a Mocking Bird', 'Unknown', '2000', '', 10);
insert into library_books(id, title, author, edition, publisher, availableunits) values(4, 'Harry Potter Part 1', 'J K Rowling', '1990', '', 5);
insert into library_books(id, title, author, edition, publisher, availableunits) values(5, 'Julius Ceasar', 'Shakespeare', '1800', '', 4);
insert into library_books(id, title, author, edition, publisher, availableunits) values(6, 'Computer Networks', 'Forouzan', '2012', 'Pearson', 2);
insert into library_books(id, title, author, edition, publisher, availableunits) values(7, 'Master Minds', 'Gordon Korman', '1990', '', 10);
insert into library_books(id, title, author, edition, publisher, availableunits) values(8, 'The Stars Shine Down', 'Sidney Sheldon', '1990', '', 10);
insert into library_books(id, title, author, edition, publisher, availableunits) values(9, 'The Alchemist', 'Paulo Coelho', '1990', 'Harper Torch', 10);
insert into library_books(id, title, author, edition, publisher, availableunits) values(10, 'Ignited Minds', 'A P J Abdul Kalam', '1970', '', 9);