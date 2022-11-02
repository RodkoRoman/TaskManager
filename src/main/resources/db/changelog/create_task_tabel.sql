--liquibase formatted sql

--changeset dbms:postgresql
CREATE TABLE IF NOT EXISTS task_manager.task
(
    id                          uuid PRIMARY KEY,
    name                        varchar,
    description                 varchar,
    created_at                  timestamp,
    is_deleted                  boolean
);