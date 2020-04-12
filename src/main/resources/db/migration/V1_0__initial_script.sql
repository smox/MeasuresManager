CREATE SEQUENCE SEQ_SETTING;

CREATE TABLE PUBLIC.SETTING (
	ID BIGINT DEFAULT NEXT VALUE FOR "PUBLIC"."SEQ_SETTING" NOT NULL AUTO_INCREMENT,
	COMPANY_NAME VARCHAR(255) NOT NULL,
	COMPANY_NUMBER VARCHAR(255) NOT NULL,
	CURRENT_YEAR VARCHAR(255),
	DELETED_AT DATE,
	HOME_DIRECTORY VARCHAR(255),
CONSTRAINT CONSTRAINT_A PRIMARY KEY (ID)
);

CREATE SEQUENCE SEQ_WINE;

CREATE TABLE PUBLIC.WINE (
	ID BIGINT DEFAULT NEXT VALUE FOR "PUBLIC"."SEQ_WINE" NOT NULL AUTO_INCREMENT,
	NAME VARCHAR(255) NOT NULL,
"YEAR" VARCHAR(255) NOT NULL,
CONSTRAINT CONSTRAINT_2 PRIMARY KEY (ID)
);

CREATE SEQUENCE SEQ_MEASURE;

CREATE TABLE PUBLIC.MEASURE (
  ID BIGINT DEFAULT NEXT VALUE FOR "PUBLIC"."SEQ_MEASURE" NOT NULL AUTO_INCREMENT,
MEASURE_TYPE INTEGER,
NAME VARCHAR(255) NOT NULL,
PARENT_ID BIGINT,
CONSTRAINT CONSTRAINT_6 PRIMARY KEY (ID),
CONSTRAINT FK_4KRDPPDHEOAGG74JVGFSML1U9 FOREIGN KEY (PARENT_ID) REFERENCES PUBLIC.MEASURE(ID) ON DELETE RESTRICT ON UPDATE RESTRICT
);

CREATE SEQUENCE SEQ_CONTAINER_TYPE;
CREATE TABLE PUBLIC.CONTAINER_TYPE (
	ID BIGINT DEFAULT NEXT VALUE FOR "PUBLIC"."SEQ_CONTAINER_TYPE" NOT NULL AUTO_INCREMENT,
	NAME VARCHAR(100) NOT NULL,
CONSTRAINT CONTAINER_TYPE_PK PRIMARY KEY (ID)
);

CREATE SEQUENCE SEQ_LOCATION;
CREATE TABLE PUBLIC.LOCATION (
	ID BIGINT DEFAULT NEXT VALUE FOR "PUBLIC"."SEQ_LOCATION" NOT NULL AUTO_INCREMENT,
	NAME VARCHAR(100) NOT NULL UNIQUE,
CONSTRAINT LOCATION_PK PRIMARY KEY (ID)
);

CREATE SEQUENCE SEQ_CONTAINER;
CREATE TABLE PUBLIC.CONTAINER (
	ID BIGINT DEFAULT NEXT VALUE FOR "PUBLIC"."SEQ_CONTAINER" NOT NULL AUTO_INCREMENT,
	DESIGNATION VARCHAR(100) NOT NULL UNIQUE,
CONTAINER_TYPE_ID BIGINT,
LOCATION_ID BIGINT,
CAPACITY INTEGER,
CONSTRAINT CONTAINER_PK PRIMARY KEY (ID),
CONSTRAINT CONTAINER_FK FOREIGN KEY (CONTAINER_TYPE_ID) REFERENCES PUBLIC.CONTAINER_TYPE(ID) ON DELETE RESTRICT ON UPDATE CASCADE,
CONSTRAINT CONTAINER_FK_1 FOREIGN KEY (LOCATION_ID) REFERENCES PUBLIC.LOCATION(ID) ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE SEQUENCE SEQ_ENTRY;

CREATE TABLE PUBLIC.ENTRY (
	ID BIGINT DEFAULT NEXT VALUE FOR "PUBLIC"."SEQ_ENTRY" NOT NULL AUTO_INCREMENT,
	ADDITIONAL_INFORMATION VARCHAR(255),
AMOUNT INTEGER NOT NULL,
CONTAINER_ID BIGINT,
CREATEDAT DATE,
REALIZEDAT DATE NOT NULL,
MEASURE_ID BIGINT,
WINE_ID BIGINT,
CONSTRAINT CONSTRAINT_3 PRIMARY KEY (ID),
CONSTRAINT FK_P0O74HUGCM12345RWYT2VVGB3 FOREIGN KEY (CONTAINER_ID) REFERENCES PUBLIC.CONTAINER(ID) ON DELETE RESTRICT ON UPDATE RESTRICT,
CONSTRAINT FK_P0O74HUGCMCP227RWYT2VVGB3 FOREIGN KEY (MEASURE_ID) REFERENCES PUBLIC.MEASURE(ID) ON DELETE RESTRICT ON UPDATE RESTRICT,
CONSTRAINT FK_QEBCX3HQ3DP84WQ56PEWYELO8 FOREIGN KEY (WINE_ID) REFERENCES PUBLIC.WINE(ID) ON DELETE RESTRICT ON UPDATE RESTRICT
);

INSERT INTO SETTING (COMPANY_NAME, COMPANY_NUMBER, CURRENT_YEAR) VALUES ('Winzerhof Schwanzelberger', '1563157', '2020');

