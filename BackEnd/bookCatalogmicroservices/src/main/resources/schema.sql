DROP TABLE IF EXISTS BOOK CASCADE;
DROP TABLE IF EXISTS CATEGORY CASCADE;
DROP TABLE IF EXISTS BOOKCATEGORY CASCADE;
DROP TABLE IF EXISTS USER CASCADE;
DROP TABLE IF EXISTS REVIEW CASCADE;
DROP TABLE IF EXISTS LIKES CASCADE;


CREATE TABLE BOOK ("ISBN" VARCHAR2(26) PRIMARY KEY ,"TITLE" VARCHAR2(128),"AUTHORFIRST" VARCHAR2(26),"AUTHORLAST" VARCHAR2(26),"DESCRIPTION" VARCHAR2(4000),"COVERIMAGE" VARCHAR2(256),"PAGES" NUMBER(38,0),"PUBLISHER" VARCHAR2(128),"PUBLISHEDYEAR" VARCHAR2(4));

CREATE TABLE CATEGORY ("CATEGORYID" VARCHAR2(2) PRIMARY KEY , "CATEGORYTYPE" VARCHAR2(26));

CREATE TABLE BOOKCATEGORY ("ISBN" VARCHAR2(26), "CATEGORYID" VARCHAR2(2), FOREIGN KEY ("ISBN") REFERENCES BOOK("ISBN") ON DELETE CASCADE ON UPDATE CASCADE, FOREIGN KEY ("CATEGORYID") REFERENCES CATEGORY ("CATEGORYID") ON DELETE CASCADE ON UPDATE CASCADE);

CREATE TABLE REVIEW ("UUID" VARCHAR2(36) PRIMARY KEY , "USERNAME" VARCHAR2(50), "ISBN" VARCHAR2(26), "TITLE" VARCHAR2(60), "BODY" VARCHAR2(3000), "RATING" VARCHAR2(1), "TIMESTAMP" DATE, FOREIGN KEY ("ISBN") REFERENCES BOOK ("ISBN") ON DELETE CASCADE ON UPDATE CASCADE);

CREATE TABLE LIKES ("UUID" VARCHAR2(36), "USERNAME" VARCHAR2(50), FOREIGN KEY ("UUID") REFERENCES REVIEW ("UUID") ON DELETE CASCADE ON UPDATE CASCADE);
