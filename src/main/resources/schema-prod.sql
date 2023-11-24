

--CREATE ROLE appAdmin
--LOGIN
--PASSWORD 'Postgr@s321!';

create SCHEMA IF NOT EXIST transtour AUTHORIZATION appAdmin;

create sequence transtours.travel_id_seq  increment by 1 start with 0;

create index users_dni_idx on transtours.users USING btree (dni);
create index travels_traveldid_idx on transtours.travels USING btree (travel_id);
create index travels_company_idx on transtours.travels USING btree (company);
create index travels_status_idx on transtours.travels USING btree (status);



create TABLE transtours.users (
	user_id uuid PRIMARY KEY,
	dni VARCHAR ( 50 ) UNIQUE NOT NULL,
	password VARCHAR ( 50 ) NOT NULL,
	full_name VARCHAR ( 200 ) NOT NULL,
	email VARCHAR ( 100 ) UNIQUE NOT NULL,
	phone VARCHAR ( 20 )  NOT NULL,
	token_id VARCHAR ( 20 )  NOT NULL,
    last_login TIMESTAMP
);

create TABLE transtours.roles (
    role_id uuid PRIMARY KEY,
	name VARCHAR ( 50 ) UNIQUE NOT NULL,
);


create TABLE transtours.user_role (
    user_id uuid NOT NULL ,
    role_id uuid ,
	PRIMARY KEY (user_id,role_id),
);

create TABLE transtours.firebase_token  (
    id uuid NOT NULL ,
    token varchar(1000) NOT NULL ,
    device_type varchar(30) NOT NULL ,
    user_id uuid NOT NULL ,
	PRIMARY KEY (id),
	FOREIGN KEY (user_id) references  users(user_id)
);

create TABLE transtours.session_tokens  (
    id uuid NOT NULL ,
    token varchar(1000) NOT NULL ,
    token_type varchar(30) NOT NULL ,
    revoked boolean NOT NULL,
    expired boolean NOT NULL,
    user_id uuid NOT NULL ,
	PRIMARY KEY (id),
	FOREIGN KEY (user_id) references  users(user_id)
)


create TABLE transtours.travels (
	travel_id uuid PRIMARY KEY,
	car_driver VARCHAR ( 15 )  NOT NULL,
	company VARCHAR ( 50 ) NOT NULL,
	status VARCHAR ( 10 ) NOT NULL,
    payload jsonb NOT NULL,
	created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    created_by VARCHAR ( 50 ) NOT NULL,
);