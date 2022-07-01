create table if not exists url (
    id serial primary key not null,
    name text,
    shortcut text,
    calls int,
    unique (name, shortcut)
);

create table if not exists site (
    id serial primary key not null,
    name varchar(50),
    unique (name)
);

create table if not exists persons (
  id serial primary key not null,
  login text,
  password text,
  site_id int references site(id),
  unique (login)
);


