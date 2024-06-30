create table pacientes(
    id bigint not null auto_increment,
        nombre varchar(100) not null,
        email varchar(100) not null unique,
        DNI int not null unique,
        calle varchar(100) not null,
        numero int not null,
        manzana varchar(100) not null,
        departamento varchar(100) not null,
        provincia varchar(100) not null,
        telefono varchar(100) not null,
        activo tinyint not null,

    primary key(id)

);