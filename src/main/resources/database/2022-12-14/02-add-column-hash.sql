--liquibase formatted sql
--changeset rkulig:23
alter table users add hash varchar(120);
--changeset rkulig:24
alter table users add hash_date datetime;