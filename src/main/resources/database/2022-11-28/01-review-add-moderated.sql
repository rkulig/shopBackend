--liquibase formatted sql
--changeset rkulig:9
alter table review add moderated boolean default false;