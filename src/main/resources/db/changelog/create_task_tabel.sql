--liquibase formatted sql

--changeset dbms:postgresql
CREATE TABLE IF NOT EXISTS task_manager.task
(
    id                          uuid PRIMARY KEY,
    name                        varchar,
    description                 varchar,
    is_deleted                  boolean,
    createdAt                   timestamp
);