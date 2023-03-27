insert into users (id,dni,password,full_name,status,phone,email)
VALUES ('c9f1d6e1-ff48-4639-8ef5-28a4fb26c7b0','34404216','12434','Pablo Ocanto','ENABLED','1136319469','pomalianni@gmail.com')
ON CONFLICT DO NOTHING;


insert into users (id,dni,password,full_name,status,phone,email)
VALUES ('f86c56a2-3997-44de-bdef-459a93cba755','27803204','12434','Carlos Laffite','ENABLED','222','cnlaffitte@gmail.com')
ON CONFLICT DO NOTHING;


insert into roles (id,name)
VALUES ('daeaf640-bbca-4d3f-93ca-8f29126d5233','USER')
ON CONFLICT DO NOTHING;

insert into roles (id,name)
VALUES ('415ccece-73da-437f-b40d-f1ad21e17cb6','DRIVER')
ON CONFLICT DO NOTHING;

insert into roles (id,name)
VALUES ('803745ce-2411-4b12-ba3f-04951ad58d77','ADMIN')
ON CONFLICT DO NOTHING;


insert into user_role (user_id,role_id)
VALUES ('c9f1d6e1-ff48-4639-8ef5-28a4fb26c7b0','daeaf640-bbca-4d3f-93ca-8f29126d5233')
ON CONFLICT DO NOTHING;

insert into user_role (user_id,role_id)
VALUES ('f86c56a2-3997-44de-bdef-459a93cba755','daeaf640-bbca-4d3f-93ca-8f29126d5233')
ON CONFLICT DO NOTHING;


