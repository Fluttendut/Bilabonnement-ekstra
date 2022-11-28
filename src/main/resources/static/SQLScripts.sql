create database Bilabonnement;
use Bilabonnement;

create table Cars (
    id int unique AUTO_INCREMENT primary key not null,
    serialnumber int unique not null,
    type varchar(50) not null ,
    price int not null ,
    damaged binary not null ,
    available binary not null
    );

create table User (
    id int auto_increment not null primary key,
    username varchar(50) unique not null ,
    password varchar(50) not null
);

create table rentee (
    name varchar(50) not null ,
    email varchar(50) unique not null ,
    cpr int unique not null ,
    address varchar(50) not null
);

