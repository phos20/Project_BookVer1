CREATE USER BOOKSTORE IDENTIFIED BY BOOKSSTORE;
GRANT CONNECT, RESOURCE, CREATE VIEW, CREATE SEQUENCE TO BOOKSTORE;


-- USER TABLE 유저 --
CREATE TABLE USERLIST(
USER_ID VARCHAR2(30) PRIMARY KEY, -- 사용자 ID
USER_PWD VARCHAR2(30) NOT NULL,  -- 사용자 비밀번호
USER_NAME VARCHAR2(30), -- 사용자 이름 
USER_PHONE VARCHAR2(20) NOT NULL, -- 사용자 핸드폰번호
USER_POINT NUMBER(6) NOT NULL, -- 사용자 포인트
REG_DATE DATE, -- 사용자 가입일 
USER_TOTAL NUMBER(10), -- 사용자 총 사용금액 
GRADE VARCHAR2(9) default 'bronze' REFERENCES USERGRADE(GRADE) -- 사용자 등급 
); 
 
INSERT INTO USERLIST VALUES ('K','1111','김현민', '010-1111-1111', 0, SYSDATE,0,'관리자'); 
INSERT INTO USERLIST VALUES ('C','1111','최량훈', '010-1111-1111', 10000, SYSDATE, 0, DEFAULT);
INSERT INTO USERLIST VALUES ('P','1111','박기랑', '010-1111-1111', 10000, SYSDATE, 0, DEFAULT);
INSERT INTO USERLIST VALUES ('L','1111','이경은', '010-1111-1111', 50000, SYSDATE, 0, DEFAULT);
INSERT INTO USERLIST VALUES ('L2','1111','이창균', '010-1111-1111', 50000, SYSDATE, 0, DEFAULT);

COMMIT;

SELECT * FROM USERLIST;
drop table USERLIST;



-- USERGRADE TABLE 등급 -- 
CREATE TABLE USERGRADE(
GRADE VARCHAR(20) PRIMARY KEY, -- 등급
LOWAMOUNT NUMBER(10), -- 최소금액
HIGHAMOUNT NUMBER(10) -- 최대금액 
);

insert into usergrade(grade) values('관리자');
insert into usergrade values('bronze', 0, 50000);
insert into usergrade values('silver', 50001, 100000);
insert into usergrade values('gold', 100001, 1000000);

select * from usergrade;
drop table USERGRADE;



-- BOOKS TABLE 도서 -- 
CREATE TABLE BOOKS(
BOOKS_ID VARCHAR2(20) PRIMARY KEY, -- 도서 코드
BOOKS_NAME VARCHAR2(50) NOT NULL, -- 도서 제목
BOOKS_WRITER VARCHAR2(20) NOT NULL, -- 도서 저자
BOOKS_PUBLISHER VARCHAR2(30) NOT NULL, -- 도서 출판사
BOOKS_PUB_DATE VARCHAR2(20) NOT NULL, -- 도서 출판일
BOOKS_GENRE VARCHAR2(20) NOT NULL,  -- 도서 장르
BOOKS_PRICE NUMBER(12) NOT NULL, -- 도서 가격
STOCK NUMBER(3), -- 도서재고
REG_DATE VARCHAR2(20) NOT NULL -- 도서 등록일 
);

INSERT INTO BOOKS VALUES('A01', '어린왕자', '생텍쥐페리', '문예출판사', '2019.12.02', '동화', 7000, 10, SYSDATE);
INSERT INTO BOOKS VALUES('A02','가시고기', '조창인', '산지', '2019.05.10', '소설', 6000, 10, SYSDATE);
INSERT INTO BOOKS VALUES('A03','소나기', '황순원', '새움', '2017.04.12', '소설', 7000, 10, SYSDATE);
INSERT INTO BOOKS VALUES('A04','동백꽃', '김유정', '문학과지성사', '2005.04.18', '소설', 7000, 10, SYSDATE);

SELECT * FROM BOOKS;
DROP TABLE BOOKS;

COMMIT;



-- ORDERS TABLE 주문 -- 
CREATE TABLE ORDERS(
ORDER_ID NUMBER(5) PRIMARY KEY, -- 주문 코드 
ORDER_DATE DATE NOT NULL, -- 주문일 
USER_ID VARCHAR2(30) NOT NULL REFERENCES USERLIST(USER_ID), -- 사용자 ID
GRADE VARCHAR2(9) REFERENCES USERGRADE(GRADE), -- 사용자 등급 
ADDRESS VARCHAR2(100) NOT NULL, -- 주소 
TOTAL_AMOUNT NUMBER(20) NOT NULL -- 총금액 
);

DROP SEQUENCE ORDER_ID_SEQ;
CREATE SEQUENCE ORDER_ID_SEQ NOCACHE;

INSERT INTO ORDERS VALUES(ORDER_ID_SEQ.NEXTVAL, SYSDATE, 'C', 'bronze', '서울', 20000);
INSERT INTO ORDERS VALUES(ORDER_ID_SEQ.NEXTVAL, SYSDATE, 'P', 'bronze', '성남', 7000);

select * from orders;



-- ORDER_LINE TABLE 주문상세 --
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

SELECT * FROM ORDER_LINE;
DROP TABLE ORDER_LINE;



-- REGISTER BOOK TABLE 희망도서 --
CREATE TABLE REGBOOK(
REG_NO NUMBER(3) PRIMARY KEY, -- 희망도서 번호
REG_NAME VARCHAR2(50) NOT NULL, -- 희망도서 제목
REG_WRITER VARCHAR2(30) NOT NULL, -- 희망도서 저자
REG_PUBLISHER VARCHAR2(30) NOT NULL, -- 희망도서 출판사
USER_ID VARCHAR2(30) REFERENCES USERLIST(USER_ID), -- 사용자 ID 
REG_DATE DATE DEFAULT SYSDATE -- 희망도서 등록일 
);

insert into REGBOOK values(REG_NO_SEQ.NEXTVAL, '아몬드', '손원평', '창비', 'L', SYSDATE);

DROP SEQUENCE REG_NO_SEQ;
CREATE SEQUENCE REG_NO_SEQ  NOCACHE;


SELECT*FROM USERLIST;
SELECT*FROM USERGRADE;
SELECT*FROM BOOKS;
SELECT*FROM ORDERS;
SELECT*FROM ORDER_LINE;
SELECT*FROM REGBOOK;

COMMIT;
rollback;

DROP TABLE USERLIST;
DROP TABLE GRADE;
DROP TABLE BOOKS;
DROP TABLE REGBOOK;
DROP TABLE ORDERS;
DROP TABLE ORDER_LINE;
