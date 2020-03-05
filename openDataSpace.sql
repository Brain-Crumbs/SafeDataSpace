drop database if exists safedatabase;

CREATE database safedatabase;
use safedatabase;

DROP table if exists prodTiers;
create table prodTiers(
tierID int primary key,
maxDownload int not null,
maxUpload int not null,
maxSize int not null,
price decimal(6,2),
pricePerGB decimal(5,2));

DROP TABLE IF EXISTS contract;
CREATE TABLE contract (
contractID int primary key auto_increment,
 tierID int not null,
 name varChar(20) not null,
 amountUsed bigint not null,
 isActive boolean not null,
 foreign key (tierID) references prodTiers(tierID));
 
 drop table if exists files;
 create table files(fileID int primary key auto_increment,
 contractID int not null,
 size bigint not null,
 foreign key (contractID) references contract(contractID));

update prodTiers
set pricePerGB= 0.50
where tierID=1;
select * from contract;
select * from prodTiers;
select * from files;

insert into prodTiers values
(1, 25, 50, 100, 50.99, 0.50),
(2, 50, 100, 1000, 99.99, 0.99),
(3, 100, 200, 10000, 149.99, 0.25);

insert into contract values
(contractID, 1, "Zach", 0, true),
(contractID, 2, "Cole", 0, true),
(contractID, 3, "Tom", 0, false);
