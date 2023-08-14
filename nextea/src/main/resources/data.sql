INSERT INTO user_tb (USER_ID, _EMAIL, _NICKNAME, _PASSWORD, _APPENDTIME, _UPDATETIME)
VALUES (1, 'admin', 'admin', '1234', '0000-00-00 00:00:00', '0000-00-00 00:00:00');

insert into authority (authority_name) values ('ROLE_USER');
insert into authority (authority_name) values ('ROLE_ADMIN');

insert into user_authority (user_id, authority_name) values (1, 'ROLE_USER');
insert into user_authority (user_id, authority_name) values (1, 'ROLE_ADMIN');