create table if not exists student
(
    id      bigserial primary key,
    name    varchar(50) not null,
    surname varchar(50) not null,
    age     smallint    not null
);

insert into student(name, surname, age) VALUES ('John', 'Coffey', 33);
insert into student(name, surname, age) VALUES ('Sarah', 'Connor', 29);
insert into student(name, surname, age) VALUES ('Bilbo', 'Baggins', 35);
insert into student(name, surname, age) VALUES ('Robinson', 'Crusoe', 28);
