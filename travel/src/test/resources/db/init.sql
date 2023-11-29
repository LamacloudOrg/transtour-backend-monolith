CREATE ROLE userDB
LOGIN
PASSWORD 'Postgr@s321!';


create SCHEMA IF NOT EXISTS transtour;

GRANT SELECT ON ALL TABLES IN SCHEMA transtour TO userDB;
GRANT USAGE, SELECT ON ALL SEQUENCES IN SCHEMA transtour to userDB;

create sequence IF NOT EXISTS travel_id_seq  increment by 1 start with 1;

create sequence IF NOT EXISTS hibernate_sequence  increment by 1 start with 1;



drop table IF EXISTS  transtour.travels;

create TABLE transtour.travels (
	travel_id INTEGER PRIMARY KEY,
	car_driver VARCHAR ( 15 )  NOT NULL,
	company VARCHAR ( 50 ) NOT NULL,
	status VARCHAR ( 10 ) NOT NULL,
    payload text NOT NULL,
	created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP,
    created_by VARCHAR ( 50 ) NOT NULL,
    modified_by VARCHAR ( 50 )

);

drop table IF EXISTS  transtour.revinfo;
drop table IF EXISTS  revinfo;

create table revinfo (
    rev INTEGER PRIMARY KEY,
    revtstmp bigint
);

drop table IF EXISTS  transtour.travels_aud ;

create table transtour.travels_aud (
    travel_id INTEGER,
    rev                INTEGER , -- The version number of the entity.
    revend             INTEGER , -- The version of the next version number after entity gets updated.
    revtype            INTEGER, -- The type of the revision.
    revend_tstmp       TIMESTAMP, -- The timestamp of the next version number after entity gets updated.
    car_driver varchar(255),
    car_driver_mod boolean,
    company varchar(255),
    company_mod boolean,
    created_at timestamp,
    created_at_mod boolean,
    updated_at timestamp,
    updated_at_mod boolean,
    created_by varchar(255),
    created_by_mod boolean,
    modified_by varchar(255),
    modified_by_mod boolean,
    payload varchar(100000),
    payload_mod boolean,
    status varchar(255),
    status_mod boolean,
    primary key (travel_id, rev)
);



create index travels_traveldid_idx on transtour.travels USING btree (travel_id);
create index travels_company_idx on transtour.travels USING btree (company);
create index travels_status_idx on transtour.travels USING btree (status);

