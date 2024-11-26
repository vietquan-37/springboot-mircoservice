create table if not exists category
(
    id serial  primary key,
    description varchar(255),
    name varchar(255)

);
create table if not exists product(
    id serial primary key ,
    description varchar(255),
    name varchar(255),
    stock  double precision not null,
    price numeric(38,2),
    category_id integer
                 constraint  fk1adafsdgfdgfd references category

);


