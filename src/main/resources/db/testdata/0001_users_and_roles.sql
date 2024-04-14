insert into
    users (first_name, last_name, email, password, area, avatar)
values
    ('Arkadiusz', 'Pucek', 'admin@example.com', '{noop}Adminpass12!', 'Warszawa', '/images/avatars/Monkey.png'),
    ('Krzysztof', 'Frąckowiak', 'user@example.com', '{noop}Userpass12!', 'Poznań','/images/avatars/Fox.png'),
    ('Łukasz', 'Jurkowski','editor@example.com', '{noop}Editorpass12!', 'Katowice','/images/avatars/wilk2.png');

insert into
    user_role (name, description)
values
    ('Country Manager', 'full permissions'),
    ('Sales Engineer', 'basic rights'),
    ('Sales Manager', 'basic permissions + ability to manage content'),
    ('Designer', 'basic permissions + ability to manage content'),
    ('Warehouse Manager', 'basic permissions + ability to manage content'),
    ('Assembly Manager', 'basic permissions + ability to manage content');

insert into
    user_roles (user_id, role_id)
values
    (1, 2),
    (2, 2),
    (3, 2);