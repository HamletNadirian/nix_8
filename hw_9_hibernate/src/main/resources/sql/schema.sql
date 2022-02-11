create table employees
(
    id        int auto_increment
        primary key,
    created   datetime(6)  null,
    updated   datetime(6)  null,
    firstName varchar(255) null,
    lastName  varchar(255) null
);
create table departments
(
    id       int auto_increment
        primary key,
    created  datetime(6)  null,
    updated  datetime(6)  null,
    location varchar(255) null,
    name     varchar(255) null,
    budget   int          null

);
create table departments_employees
(
    department_id int not null,
    employee_id   int not null,
    primary key (department_id, employee_id),
        foreign key (department_id) references departments (id),
        foreign key (employee_id) references employees (id)
);