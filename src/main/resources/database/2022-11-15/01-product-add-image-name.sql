--liquibase formatted sql
--changeset rkulig:2
alter table product add image varchar(128) after currency;