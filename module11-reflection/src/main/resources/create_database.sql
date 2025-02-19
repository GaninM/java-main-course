create table if not exists users
(
    id   bigserial primary key,
    name varchar not null,
    age  integer not null
);

insert into users (name, age)
values ('Alice', 22);
insert into users (name, age)
values ('John', 40);
insert into users (name, age)
values ('Bob', 13);
insert into users (name, age)
values ('Franko', 34);
