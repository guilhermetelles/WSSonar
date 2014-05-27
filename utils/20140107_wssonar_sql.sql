/* Database generated with pgModeler (PostgreSQL Database Modeler).
  Project Site: pgmodeler.com.br
  Model Author: --- */

SET check_function_bodies = false;
-- ddl-end --


/* Database creation must be done outside an multicommand file.
   These commands were put in this file only for convenience.

-- object: "WSSonar" | type: DATABASE -- 
CREATE DATABASE "WSSonar"
	ENCODING = 'UTF8'
;
-- ddl-end --

*/

-- object: main | type: SCHEMA -- 
DROP SCHEMA main CASCADE;
CREATE SCHEMA main;
-- ddl-end --

-- object: main."Web_Service" | type: TABLE -- 
CREATE TABLE main."Web_Service"(
	ws_id integer NOT NULL,
	ws_name varchar(40) NOT NULL,
	ws_description varchar(100),
	ws_status boolean NOT NULL,
	CONSTRAINT ws_id_pk PRIMARY KEY (ws_id)
)
WITH (OIDS=TRUE);
-- ddl-end --

-- ddl-end --

-- object: main."History" | type: TABLE -- 
CREATE TABLE main."History"(
	ht_id integer NOT NULL,
	ht_id_web_service integer NOT NULL,
	ht_down_date timestamp NOT NULL,
	ht_back_online timestamp,
	ht_error_result varchar(250),
	CONSTRAINT ht_id_pk PRIMARY KEY (ht_id)
)
WITH (OIDS=TRUE);
-- ddl-end --

-- ddl-end --

-- object: main."User" | type: TABLE -- 
CREATE TABLE main."User"(
	us_id integer NOT NULL,
	us_name varchar(70) NOT NULL,
	us_username varchar(20) NOT NULL,
	us_password varchar(255) NOT NULL,
	us_email varchar(100) NOT NULL,
	CONSTRAINT us_id_pk PRIMARY KEY (us_id),
	CONSTRAINT us_username_uq UNIQUE (us_username)
)
WITH (OIDS=TRUE);
-- ddl-end --

-- ddl-end --

-- object: ht_id_web_service_fk | type: CONSTRAINT -- 
ALTER TABLE main."History" ADD CONSTRAINT ht_id_web_service_fk FOREIGN KEY (ht_id_web_service)
REFERENCES main."Web_Service" (ws_id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION NOT DEFERRABLE;
-- ddl-end --

-- INSERT --

-- Web Service --
INSERT INTO main."Web_Service" (ws_id, ws_name, ws_description, ws_status) VALUES (1, 'Web Service de Compra', 'Web Service de compra de seguro.', true);

INSERT INTO main."Web_Service" (ws_id, ws_name, ws_description, ws_status) VALUES (2, 'Web Service de Cotação', 'Web Service que realiza cotações.', true);	

INSERT INTO main."Web_Service" (ws_id, ws_name, ws_description, ws_status) VALUES (3, 'Web Service de Contato', 'Web Service usado para requisitar contato.', true);
	
-- History --
/**
INSERT INTO main."History" (ht_id, ht_id_web_service, ht_down_date, ht_back_online, ht_error_result) VALUES (1, 1, '2014-01-01 02:44:21', '2014-01-01 03:22:01', 'Error!');
INSERT INTO main."History" (ht_id, ht_id_web_service, ht_down_date, ht_back_online, ht_error_result) VALUES (2, 1, '2014-01-01 04:44:21', '2014-01-01 05:52:01', 'Error!');
INSERT INTO main."History" (ht_id, ht_id_web_service, ht_down_date, ht_back_online, ht_error_result) VALUES (3, 1, '2014-01-01 06:44:21', '2014-01-01 07:22:01', 'Error!');
INSERT INTO main."History" (ht_id, ht_id_web_service, ht_down_date, ht_back_online, ht_error_result) VALUES (4, 1, '2014-01-01 08:44:21', '2014-01-01 09:22:01', 'Error!');
INSERT INTO main."History" (ht_id, ht_id_web_service, ht_down_date, ht_back_online, ht_error_result) VALUES (5, 1, '2014-01-01 10:44:21', '2014-01-01 11:22:01', 'Error!');
INSERT INTO main."History" (ht_id, ht_id_web_service, ht_down_date, ht_back_online, ht_error_result) VALUES (6, 1, '2014-01-01 12:44:21', '2014-01-01 13:22:01', 'Error!');
INSERT INTO main."History" (ht_id, ht_id_web_service, ht_down_date, ht_back_online, ht_error_result) VALUES (7, 1, '2014-01-01 14:44:21', '2014-01-01 15:22:01', 'Error!');
INSERT INTO main."History" (ht_id, ht_id_web_service, ht_down_date, ht_back_online, ht_error_result) VALUES (8, 1, '2014-01-02 12:44:21', '2014-01-02 17:44:21', 'Error!');
INSERT INTO main."History" (ht_id, ht_id_web_service, ht_down_date, ht_back_online, ht_error_result) VALUES (9, 1, '2014-01-03 02:44:21', '2014-01-03 08:22:01', 'Error!');
INSERT INTO main."History" (ht_id, ht_id_web_service, ht_down_date, ht_back_online, ht_error_result) VALUES (10, 1, '2014-01-04 02:44:21', '2014-01-04 13:22:01', 'Error!');
INSERT INTO main."History" (ht_id, ht_id_web_service, ht_down_date, ht_back_online, ht_error_result) VALUES (11, 1, '2014-01-05 02:44:21', '2014-01-05 10:22:01', 'Error!');
INSERT INTO main."History" (ht_id, ht_id_web_service, ht_down_date, ht_back_online, ht_error_result) VALUES (12, 1, '2014-01-06 02:44:21', '2014-01-06 15:22:01', 'Error!');
INSERT INTO main."History" (ht_id, ht_id_web_service, ht_down_date, ht_back_online, ht_error_result) VALUES (13, 1, '2014-01-07 02:44:21', '2014-01-07 18:22:01', 'Error!');
INSERT INTO main."History" (ht_id, ht_id_web_service, ht_down_date, ht_back_online, ht_error_result) VALUES (14, 1, '2014-01-08 02:44:21', '2014-01-08 03:22:01', 'Error!');
INSERT INTO main."History" (ht_id, ht_id_web_service, ht_down_date, ht_back_online, ht_error_result) VALUES (15, 1, '2014-01-09 02:44:21', '2014-01-09 07:22:01', 'Error!');
INSERT INTO main."History" (ht_id, ht_id_web_service, ht_down_date, ht_error_result) VALUES (16, 1, '2014-01-09 18:44:21','Error again!');

INSERT INTO main."History" (ht_id, ht_id_web_service, ht_down_date, ht_back_online, ht_error_result) VALUES (17, 2, '2014-01-06 09:44:21', '2014-01-07 11:14:21', 'SAF Error!');
INSERT INTO main."History" (ht_id, ht_id_web_service, ht_down_date, ht_back_online, ht_error_result) VALUES (18, 2, '2014-01-07 12:22:21', '2014-01-07 17:04:21', 'SAF Error!');
INSERT INTO main."History" (ht_id, ht_id_web_service, ht_down_date, ht_back_online, ht_error_result) VALUES (19, 2, '2014-01-07 18:44:21', '2014-01-07 19:14:21', 'SAF Error!');
INSERT INTO main."History" (ht_id, ht_id_web_service, ht_down_date, ht_back_online, ht_error_result) VALUES (20, 2, '2014-01-07 20:22:21', '2014-01-07 22:04:21', 'SAF Error!');
INSERT INTO main."History" (ht_id, ht_id_web_service, ht_down_date, ht_error_result) VALUES (21, 2, '2014-01-08 12:44:21','SAF Error again!'); 
*/
-- User --
INSERT INTO main."User" (us_id, us_name, us_username, us_password, us_email) VALUES (1, 'Natalia Vaz', 'natalia', '$2a$10$9iq0e1tJSr/Gtr2vbl/t8uJGZikhIDh.Otxw45Np2X63Ex8JON8LO', 'natalia.esteves@cardif.com.br');

