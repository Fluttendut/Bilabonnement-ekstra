create database Bilabonnement;
use Bilabonnement;

create table Cars
(
    id           int unique AUTO_INCREMENT not null,
    serialnumber int primary key           not null,
    type         varchar(50)               not null,
    price        int                       not null,
    damaged      binary                    not null,
    available    binary                    not null


);

create table User
(
    id       int auto_increment not null primary key,
    username varchar(50) unique not null,
    password varchar(50)        not null
);


create table leasing
(
    type         varchar(50) not null,
    price        int         not null,
    startdate    date        not null,
    enddate      date        not null,
    serialnumber int         not null
);

create table accounting
(
  currentLeasedCars varchar(50),
  monthlyIncome     varchar(50),
  annualIncome      varchar(50)
);

