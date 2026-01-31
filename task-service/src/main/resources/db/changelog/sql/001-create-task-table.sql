--liquibase formatted sql

--changeset yarosl.buykevich:001-create-task-table
create table task(
      id                bigint              not null primary key generated always as identity,
      name              varchar(70)         not null,
      description       text,
      created_at        timestamp           not null,
      updated_at        timestamp
);