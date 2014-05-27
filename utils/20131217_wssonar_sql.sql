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
	ht_down_date date NOT NULL,
	ht_back_online date,
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
	CONSTRAINT us_id_pk PRIMARY KEY (us_id)
)
WITH (OIDS=TRUE);
-- ddl-end --

-- ddl-end --

-- object: ht_id_web_service_fk | type: CONSTRAINT -- 
ALTER TABLE main."History" ADD CONSTRAINT ht_id_web_service_fk FOREIGN KEY (ht_id_web_service)
REFERENCES main."Web_Service" (ws_id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION NOT DEFERRABLE;
-- ddl-end --



