insert into users (id,dni,password,full_name,status,phone,email)
values ('c9f1d6e1-ff48-4639-8ef5-28a4fb26c7b0','34404216','$2a$10$jGKDuk9g/q6dF0mbAwnhbOvvzC2seU9piA8RTFbfeJhlTghZe/3ZC','Pablo Ocanto','ENABLED','1136319469','pomalianni@gmail.com')
ON CONFLICT DO NOTHING;


insert into users (id,dni,password,full_name,status,phone,email)
values ('f86c56a2-3997-44de-bdef-459a93cba755','27803204','$2a$10$jGKDuk9g/q6dF0mbAwnhbOvvzC2seU9piA8RTFbfeJhlTghZe/3ZC','Carlos Laffite','ENABLED','2216216295','cnlaffitte@gmail.com')
ON CONFLICT DO NOTHING;

insert into users (id,dni,password,full_name,status,phone,email)
values ('6c8b9b1c-0442-4156-88c6-47ad5d3fce70','93479822','$2a$10$jGKDuk9g/q6dF0mbAwnhbOvvzC2seU9piA8RTFbfeJhlTghZe/3ZC','Enerique Neyra','ENABLED','1140377287','bsastranstour@hotmail.com')
ON CONFLICT DO NOTHING;



insert into roles (id,name)
values ('daeaf640-bbca-4d3f-93ca-8f29126d5233','ROL_USER')
ON CONFLICT DO NOTHING;

insert into roles (id,name)
values ('415ccece-73da-437f-b40d-f1ad21e17cb6','ROL_DRIVER')
ON CONFLICT DO NOTHING;

insert into roles (id,name)
values ('803745ce-2411-4b12-ba3f-04951ad58d77','ROL_ADMIN')
ON CONFLICT DO NOTHING;


insert into user_role (user_id,role_id)
values ('c9f1d6e1-ff48-4639-8ef5-28a4fb26c7b0','daeaf640-bbca-4d3f-93ca-8f29126d5233')
ON CONFLICT DO NOTHING;

insert into user_role (user_id,role_id)
values ('f86c56a2-3997-44de-bdef-459a93cba755','daeaf640-bbca-4d3f-93ca-8f29126d5233')
ON CONFLICT DO NOTHING;

insert into user_role (user_id,role_id)
values ('6c8b9b1c-0442-4156-88c6-47ad5d3fce70','803745ce-2411-4b12-ba3f-04951ad58d77')
ON CONFLICT DO NOTHING;


