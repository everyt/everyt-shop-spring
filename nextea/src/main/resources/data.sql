insert into roles (role_type) values ('ROLE_USER');
insert into roles (role_type) values ('ROLE_ADMIN');

insert into users (email, nickname, password, activated) values ('admin@test', 'admin', '1234', 1);
insert into users (email, nickname, password, activated) values ('user@test', 'user', '1234', 1);

insert into user_role (user_id, role_type) values (1, 'ROLE_ADMIN');
insert into user_role (user_id, role_type) values (2, 'ROLE_USER');