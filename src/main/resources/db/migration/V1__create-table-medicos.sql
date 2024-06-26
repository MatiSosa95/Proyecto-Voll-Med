create table medicos(
    id bigint not null auto_increment,
    nombre varchar(100) not null,
    email varchar(100) not null unique,
    DNI int not null unique,
    especialidad varchar(100) not null,
    calle varchar(100) not null,
    numero int not null,
    manzana varchar(100) not null,
    departamento varchar(100) not null,
    provincia varchar(100) not null,

    primary key(id)

);