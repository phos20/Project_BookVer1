CREATE USER BOOKSTORE IDENTIFIED BY BOOKSSTORE;
GRANT CONNECT, RESOURCE, CREATE VIEW, CREATE SEQUENCE TO BOOKSTORE;


-- USER TABLE ���� --
CREATE TABLE USERLIST(
USER_ID VARCHAR2(30) PRIMARY KEY, -- ����� ID
USER_PWD VARCHAR2(30) NOT NULL,  -- ����� ��й�ȣ
USER_NAME VARCHAR2(30), -- ����� �̸� 
USER_PHONE VARCHAR2(20) NOT NULL, -- ����� �ڵ�����ȣ
USER_POINT NUMBER(6) NOT NULL, -- ����� ����Ʈ
REG_DATE DATE, -- ����� ������ 
USER_TOTAL NUMBER(10), -- ����� �� ���ݾ� 
GRADE VARCHAR2(9) default 'bronze' REFERENCES USERGRADE(GRADE) -- ����� ��� 
); 
 
INSERT INTO USERLIST VALUES ('K','1111','������', '010-1111-1111', 0, SYSDATE,0,'������'); 
INSERT INTO USERLIST VALUES ('C','1111','�ַ���', '010-1111-1111', 10000, SYSDATE, 0, DEFAULT);
INSERT INTO USERLIST VALUES ('P','1111','�ڱ��', '010-1111-1111', 10000, SYSDATE, 0, DEFAULT);
INSERT INTO USERLIST VALUES ('L','1111','�̰���', '010-1111-1111', 50000, SYSDATE, 0, DEFAULT);
INSERT INTO USERLIST VALUES ('L2','1111','��â��', '010-1111-1111', 50000, SYSDATE, 0, DEFAULT);

delete from userlist where user_id ='L';

COMMIT;

SELECT * FROM USERLIST;
drop table USERLIST;



-- USERGRADE TABLE ��� -- 
CREATE TABLE USERGRADE(
GRADE VARCHAR(20) PRIMARY KEY, -- ���
LOWAMOUNT NUMBER(10), -- �ּұݾ�
HIGHAMOUNT NUMBER(10) -- �ִ�ݾ� 
);

insert into usergrade(grade) values('������');
insert into usergrade values('bronze', 0, 50000);
insert into usergrade values('silver', 50001, 100000);
insert into usergrade values('gold', 100001, 1000000);

select * from usergrade;
drop table USERGRADE;



-- BOOKS TABLE ���� -- 
CREATE TABLE BOOKS(
BOOKS_ID VARCHAR2(20) PRIMARY KEY, -- ���� �ڵ�
BOOKS_NAME VARCHAR2(50) NOT NULL, -- ���� ����
BOOKS_WRITER VARCHAR2(20) NOT NULL, -- ���� ����
BOOKS_PUBLISHER VARCHAR2(30) NOT NULL, -- ���� ���ǻ�
BOOKS_PUB_DATE VARCHAR2(20) NOT NULL, -- ���� ������
BOOKS_GENRE VARCHAR2(20) NOT NULL,  -- ���� �帣
BOOKS_PRICE NUMBER(12) NOT NULL, -- ���� ����
STOCK NUMBER(3), -- �������
REG_DATE VARCHAR2(20) NOT NULL -- ���� ����� 
);

INSERT INTO BOOKS VALUES('A01', '�����', '�������丮', '�������ǻ�', '2019.12.02', '��ȭ', 7000, 10, SYSDATE);
INSERT INTO BOOKS VALUES('A02','���ð��', '��â��', '����', '2019.05.10', '�Ҽ�', 6000, 10, SYSDATE);
INSERT INTO BOOKS VALUES('A03','�ҳ���', 'Ȳ����', '����', '2017.04.12', '�Ҽ�', 7000, 10, SYSDATE);
INSERT INTO BOOKS VALUES('A04','�����', '������', '���а�������', '2005.04.18', '�Ҽ�', 7000, 10, SYSDATE);

SELECT * FROM BOOKS;
DROP TABLE BOOKS;

COMMIT;



-- ORDERS TABLE �ֹ� -- 
CREATE TABLE ORDERS(
ORDER_ID NUMBER(5) PRIMARY KEY, -- �ֹ� �ڵ� 
ORDER_DATE DATE NOT NULL, -- �ֹ��� 
USER_ID VARCHAR2(30) NOT NULL REFERENCES USERLIST(USER_ID) ON DELETE CASCADE, -- ����� ID
ADDRESS VARCHAR2(100) NOT NULL, -- �ּ� 
TOTAL_AMOUNT NUMBER(20) NOT NULL -- �ѱݾ� 
);

DROP SEQUENCE ORDER_ID_SEQ;
CREATE SEQUENCE ORDER_ID_SEQ NOCACHE;

INSERT INTO ORDERS VALUES(ORDER_ID_SEQ.NEXTVAL, SYSDATE, 'C', '����', 20000);
INSERT INTO ORDERS VALUES(ORDER_ID_SEQ.NEXTVAL, SYSDATE, 'P', '����', 7000);

select * from orders;



-- ORDER_LINE TABLE �ֹ��� --
CREATE TABLE ORDER_LINE(
ORDER_LINE_ID NUMBER(5) PRIMARY KEY,
ORDER_ID NUMBER(5) NOT NULL REFERENCES ORDERS(ORDER_ID),
BOOKS_ID VARCHAR2(20) NOT NULL REFERENCES BOOKS(BOOKS_ID),
UNIT_PRICE NUMBER(12) NOT NULL,
QTY NUMBER(3) NOT NULL,
AMOUNT NUMBER(10) NOT NULL
);

DROP SEQUENCE ORDER_LINE_ID_SEQ;
CREATE SEQUENCE ORDER_LINE_ID_SEQ  NOCACHE;

INSERT INTO ORDER_LINE VALUES(ORDER_LINE_ID_SEQ.NEXTVAL, 1, 'A01', 7000, 2, 14000);
INSERT INTO ORDER_LINE VALUES(ORDER_LINE_ID_SEQ.NEXTVAL, 1, 'A02', 6000, 1, 6000);
INSERT INTO ORDER_LINE VALUES(ORDER_LINE_ID_SEQ.NEXTVAL, 2, 'A01', 7000, 2, 14000);

update books set stock = stock-3 where books_id = 'A01';
update books set stock = stock-1 where books_id = 'A02';

SELECT * FROM ORDER_LINE;
DROP TABLE ORDER_LINE;





-- REGISTER BOOK TABLE ������� --
CREATE TABLE REGBOOK(
REG_NO NUMBER(3) PRIMARY KEY, -- ������� ��ȣ
REG_NAME VARCHAR2(50) NOT NULL, -- ������� ����
REG_WRITER VARCHAR2(30) NOT NULL, -- ������� ����
REG_PUBLISHER VARCHAR2(30) NOT NULL, -- ������� ���ǻ�
USER_ID VARCHAR2(30) REFERENCES USERLIST(USER_ID) ON DELETE CASCADE, -- ����� ID 
REG_DATE DATE DEFAULT SYSDATE -- ������� ����� 
);

insert into REGBOOK values(REG_NO_SEQ.NEXTVAL, '�Ƹ��', '�տ���', 'â��', 'L', SYSDATE);
insert into REGBOOK values(REG_NO_SEQ.NEXTVAL, '���ǼӼ�', '���ȣ', '����������Ͻ�', 'L', SYSDATE);

DROP SEQUENCE REG_NO_SEQ;
CREATE SEQUENCE REG_NO_SEQ  NOCACHE;



-- ��ٱ��� ���̺� -- 
create table cart(
cart_no number(3) primary key,
user_id varchar2(30) references userlist(user_id) on delete cascade,
books_id varchar2(20) references books(books_id) on delete cascade,
quantity number(2),
reg_date date default sysdate
);

create sequence cart_no_seq;
drop sequence cart_no_seq;
drop table cart;

select * from cart;

insert into cart values(cart_no_seq.nextval, 'L', 'A01', 2, sysdate);

select books_id, books_name, books_writer, books_price, quantity, cart.reg_date from books inner join cart using(books_id);



SELECT*FROM USERLIST;
SELECT*FROM USERGRADE;
SELECT*FROM BOOKS;
SELECT*FROM ORDERS;
SELECT*FROM ORDER_LINE;
SELECT*FROM REGBOOK;
SELECT*FROM CART;

COMMIT;
rollback;

DROP TABLE USERLIST;
DROP TABLE GRADE;
DROP TABLE BOOKS;
DROP TABLE REGBOOK;
DROP TABLE ORDERS;
DROP TABLE ORDER_LINE;
DROP TABLE CART;

