--liquibase formatted sql

--changeset yarosl.buykevich:001-create-user-table
create table "user" (
    id              uuid                primary key,
    username        varchar(100)        not null,
    password        varchar(100)        not null,
    role_id         bigserial           not null
);