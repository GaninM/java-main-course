create table if not exists users
(
    id      bigserial primary key,
    name    varchar(50) not null,
    surname varchar(50) not null,
    mail    varchar(50) not null
);

create table if not exists task
(
    id            bigserial primary key,
    user_id       bigint,
    name          varchar(50) not null,
    description   text,
    creation_date date        not null ,
    dead_line     date        not null
);

ALTER TABLE task
    ADD FOREIGN KEY (user_id) REFERENCES users (id);
