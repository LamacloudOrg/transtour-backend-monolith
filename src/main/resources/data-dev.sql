

--TOKEN DRIVER INIT--
insert into transtour.firebase_token (id,token)
values ('5ae9cbf2-dd01-4dca-b07d-bf7b94636f14','emZg8DrXT3WyvowokBhGmd:APA91bElq5MXUPLnTqkNPaztmX4FSY2xkajYYkE83dyCratyrvcgkvqGzDCodtSr3fOZ5a5_ZoR2y_l5196HS_q4to_yYMXK_tFFdYwPH3qZgEsEOv6k4AT0KYBJb0Vc1oCxcCxOZEfP')
ON CONFLICT DO NOTHING;

insert into transtour.firebase_token (id,token)
values ('35efa2f3-282d-42f8-bf05-4d77333d89d1','sessionTokens')
ON CONFLICT DO NOTHING;

insert into transtour.firebase_token (id,token)
values ('fb0911ef-670c-4070-a2cf-b93fe2759377','sessionTokens')
ON CONFLICT DO NOTHING;
-- TOKEN DRIVER  FIN--

--USER INIT--

insert into transtour.users (user_id,dni,password,full_name,status,phone,email,token_id)
values ('c9f1d6e1-ff48-4639-8ef5-28a4fb26c7b0','34404216','$2a$10$jGKDuk9g/q6dF0mbAwnhbOvvzC2seU9piA8RTFbfeJhlTghZe/3ZC','Pablo Ocanto','ENABLED','1136319469','pomalianni@gmail.com','5ae9cbf2-dd01-4dca-b07d-bf7b94636f14')
ON CONFLICT DO NOTHING;


insert into transtour.users (user_id,dni,password,full_name,status,phone,email,token_id)
values ('f86c56a2-3997-44de-bdef-459a93cba755','27803204','$2a$10$jGKDuk9g/q6dF0mbAwnhbOvvzC2seU9piA8RTFbfeJhlTghZe/3ZC','Carlos Laffite','ENABLED','2216216295','cnlaffitte@gmail.com','35efa2f3-282d-42f8-bf05-4d77333d89d1')
ON CONFLICT DO NOTHING;

insert into transtour.users (user_id,dni,password,full_name,status,phone,email)
values ('6c8b9b1c-0442-4156-88c6-47ad5d3fce70','93479822','$2a$10$jGKDuk9g/q6dF0mbAwnhbOvvzC2seU9piA8RTFbfeJhlTghZe/3ZC','Enerique Neyra','ENABLED','1140377287','bsastranstour@hotmail.com')
ON CONFLICT DO NOTHING;

--USER FIN--

-- DRIVERS INIT --
insert into transtour.users (user_id,dni,password,full_name,status,phone,email)
values ('f9d9a50a-cd99-4450-8602-cf7c00d1db11','23132456','$2a$10$jGKDuk9g/q6dF0mbAwnhbOvvzC2seU9piA8RTFbfeJhlTghZe/3ZC','Pedro Picapiedra','ENABLED','1140377287','bsastranstour@hotmail.com')
ON CONFLICT DO NOTHING;

insert into transtour.users (user_id,dni,password,full_name,status,phone,email)
values ('f7e7adbc-db32-457f-bb7c-39a1792a5501','23132457','$2a$10$jGKDuk9g/q6dF0mbAwnhbOvvzC2seU9piA8RTFbfeJhlTghZe/3ZC','Dave Navarro','ENABLED','1140377287','bsastranstour@hotmail.com')
ON CONFLICT DO NOTHING;

insert into transtour.users (user_id,dni,password,full_name,status,phone,email)
values ('edd55fec-b58a-4b57-8b9e-e62b7ab7d6c5','23132458','$2a$10$jGKDuk9g/q6dF0mbAwnhbOvvzC2seU9piA8RTFbfeJhlTghZe/3ZC','Sargento Garcia','ENABLED','1140377287','bsastranstour@hotmail.com')
ON CONFLICT DO NOTHING;




-- DRIVERS FIN --

insert into transtour.roles (role_id,name)
values ('daeaf640-bbca-4d3f-93ca-8f29126d5233','ROL_USER')
ON CONFLICT DO NOTHING;

insert into transtour.roles (role_id,name)
values ('415ccece-73da-437f-b40d-f1ad21e17cb6','ROL_DRIVER')
ON CONFLICT DO NOTHING;

insert into transtour.roles (role_id,name)
values ('803745ce-2411-4b12-ba3f-04951ad58d77','ROL_ADMIN')
ON CONFLICT DO NOTHING;


insert into transtour.user_role (user_id,role_id)
values ('c9f1d6e1-ff48-4639-8ef5-28a4fb26c7b0','daeaf640-bbca-4d3f-93ca-8f29126d5233')
ON CONFLICT DO NOTHING;

insert into transtour.user_role (user_id,role_id)
values ('c9f1d6e1-ff48-4639-8ef5-28a4fb26c7b0','415ccece-73da-437f-b40d-f1ad21e17cb6')
ON CONFLICT DO NOTHING;


insert into transtour.user_role (user_id,role_id)
values ('6c8b9b1c-0442-4156-88c6-47ad5d3fce70','803745ce-2411-4b12-ba3f-04951ad58d77')
ON CONFLICT DO NOTHING;


-- DRIVERS ROL INIT --
insert into transtour.user_role (user_id,role_id)
values ('f9d9a50a-cd99-4450-8602-cf7c00d1db11','415ccece-73da-437f-b40d-f1ad21e17cb6')
ON CONFLICT DO NOTHING;

insert into transtour.user_role (user_id,role_id)
values ('f7e7adbc-db32-457f-bb7c-39a1792a5501','415ccece-73da-437f-b40d-f1ad21e17cb6')
ON CONFLICT DO NOTHING;

insert into transtour.user_role (user_id,role_id)
values ('edd55fec-b58a-4b57-8b9e-e62b7ab7d6c5','415ccece-73da-437f-b40d-f1ad21e17cb6')
ON CONFLICT DO NOTHING;

insert into transtour.user_role(user_id,role_id)
values ('f86c56a2-3997-44de-bdef-459a93cba755','415ccece-73da-437f-b40d-f1ad21e17cb6')
ON CONFLICT DO NOTHING;

insert into transtour.user_role (user_id,role_id)
values ('f86c56a2-3997-44de-bdef-459a93cba755','daeaf640-bbca-4d3f-93ca-8f29126d5233')
ON CONFLICT DO NOTHING;


--insert into cars (id,patent,brand,model,release_year)
--values ('fb0911ef-670c-4070-a2cf-b93fe2759377','5eeba015-b59e-4805-bf62-a9ed4ebe7349','AFZ 989','Toyota','Corola','2023-04-01')
--ON CONFLICT DO NOTHING;

--insert into cars (id,patent,brand,model,release_year)
--values ('3e08c173-a838-46bd-b90a-d98715d5b0bf','LUC 284','Renault','Fluence','2012-12-12')
--ON CONFLICT DO NOTHING;

--insert into user_car (user_id,car_id)
--values ('edd55fec-b58a-4b57-8b9e-e62b7ab7d6c5','5eeba015-b59e-4805-bf62-a9ed4ebe7349')
--ON CONFLICT DO NOTHING;

--insert into user_car (user_id,car_id)
--values ('edd55fec-b58a-4b57-8b9e-e62b7ab7d6c5','3e08c173-a838-46bd-b90a-d98715d5b0bf')
--ON CONFLICT DO NOTHING;


-- DRIVERS ROL FIN --

-- COMPANY INI --
insert into transtour.company (company_id, full_name, nick_name, cuit, email, phone, whiting_time_amount, disposition_time_amount)
values ('8d57e979-5964-4caa-a3ec-d3dc78ee498b', 'Tarjeta Naranja SRL.', 'Naranja X', 208887773, 'naranjax@naranja.com.ar', 2216216295, 3500, 2500)
ON CONFLICT DO NOTHING;

insert into transtour.company (company_id, full_name, nick_name, cuit, email, phone, whiting_time_amount, disposition_time_amount)
values ('5e0c9c68-7461-40c0-8daa-7f42ca8af700', 'Toyota SRL.', 'Toyota', 208887773, 'info@toyota.com.ar', 2216216295, 3500, 2500)
ON CONFLICT DO NOTHING;

insert into transtour.company (company_id, full_name, nick_name, cuit, email, phone, whiting_time_amount, disposition_time_amount)
values ('266526c8-a0dd-47a8-a73d-1d61a831ffe4', 'Mercado Libre SRL.', 'Meli', 208887773, 'info@mercadolibre.com.ar', 2216216295, 3500, 2500)
ON CONFLICT DO NOTHING;
-- COMPANY FIN --
