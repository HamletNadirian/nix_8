create table employees
(
    id         bigint auto_increment
        primary key,
    created    datetime(6)  null,
    updated    datetime(6)  null,
    visible    bit          null,
    first_name varchar(255) not null,
    last_name  varchar(255) not null
);

create table departments
(
    id              bigint auto_increment
        primary key,
    created         datetime(6)  null,
    updated         datetime(6)  null,
    visible         bit          null,
    department_name varchar(255) not null,
    location        text         null,
    budget          int          not null
);

create table employee_department
(
    employee_id   bigint not null,
    department_id bigint not null,
    primary key (employee_id, department_id),
    foreign key (employee_id) references employees (id) on delete cascade,
    foreign key (department_id) references departments (id)
);
