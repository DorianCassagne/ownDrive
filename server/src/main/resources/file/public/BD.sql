drop table if exists TOURISTE cascade;
drop table if exists MONUMENT cascade;
drop table if exists VISITE cascade;

SET datestyle = "ISO, DMY";
create table TOURISTE(
  nom varchar(30),
  prenom varchar(30),
  age INT,
  paysOrigin varchar(30),
  CONSTRAINT touriste_pk PRIMARY KEY (nom)
);
create table MONUMENT(
  nom varchar(30),
  ville varchar(30),
  pays varchar(30),
  anneeConstruction INT,
  CONSTRAINT monument_pk PRIMARY KEY (nom)
);
create table VISITE(
  nomTouriste varchar(30),
  nomMonument varchar(30),
  dateVISITE date,
  prixEntree INT,
  CONSTRAINT visite_pk PRIMARY KEY (nomTouriste, nomMonument, dateVISITE),
  CONSTRAINT visite_fk foreign key (nomTouriste) references TOURISTE (nom),
  CONSTRAINT visite_fk2 foreign key (nomMonument) references MONUMENT (nom)

);

insert into TOURISTE values('Bonaparte','Napoleon',37,'France');
insert into TOURISTE values('Tepes','Vlad',28,'Roumanie');
insert into TOURISTE values('Legrand','Alexandre',33,'Grece');
insert into TOURISTE values('Deux','Nicolas',41,'Russie');
insert into TOURISTE values('Cesar','Jules',44,'Italie');
insert into TOURISTE values('Darc','Jeanne',18,'France');
insert into TOURISTE values('Magne','Charles',62,'Allemagne');
insert into TOURISTE values('Corcovado','Thais',48,'Bresil');
insert into TOURISTE values('Stote','Harry',65,'Grece');


insert into MONUMENT values('Chateau de Dracula','Bran','Roumanie',1377);
insert into MONUMENT values('Tour Eiffel','Paris','France',1889);
insert into MONUMENT values('Corcovado','Rio','Bresil',1931);
insert into MONUMENT values('Colisee','Rome','Italie',80);
insert into MONUMENT values('Tour de Pise','Pise','Italie',1350);
insert into MONUMENT values('Chateau de Versailles','Versailles','France',null);
insert into MONUMENT values('Cathedrale de la Se','Sao Paolo','Bresil',1954);
insert into MONUMENT values('Maison du Peuple','Bucarest','Roumanie',1984);
insert into MONUMENT values('Musee du CNAM','Paris','France',1794);
insert into MONUMENT values('Notre-Dame','Paris','France',1345);
insert into MONUMENT values('Vatican','Rome','Italie',null);

insert into VISITE values('Bonaparte','Colisee',str_to_date('2011/12/12','%Y/%m/%d'),10);
insert into VISITE values('Tepes','Cathedrale de la Se',str_to_date('2013/05/18','%Y/%m/%d'),0);
insert into VISITE values('Legrand','Tour Eiffel',str_to_date('2011/01/01','%Y/%m/%d'),25);
insert into VISITE values('Cesar','Corcovado',str_to_date('2009/12/25','%Y/%m/%d'),12);
insert into VISITE values('Legrand','Chateau de Dracula',str_to_date('2012/02/14','%Y/%m/%d'),null);
insert into VISITE values('Cesar','Cathedrale de la Se',str_to_date('2009/12/18','%Y/%m/%d'),5);
insert into VISITE values('Bonaparte','Maison du Peuple',str_to_date('2010/09/25','%Y/%m/%d'),20);
insert into VISITE values('Darc','Tour Eiffel',str_to_date('2014/02/02','%Y/%m/%d'),18);
insert into VISITE values('Darc','Musee du CNAM',str_to_date('2013/11/19','%Y/%m/%d'),8);
insert into VISITE values('Darc','Notre-Dame',str_to_date('2014/01/13','%Y/%m/%d'),0);
insert into VISITE values('Cesar','Vatican',str_to_date('2014/02/14','%Y/%m/%d'),30);
insert into VISITE values('Magne','Vatican',str_to_date('2012/06/07','%Y/%m/%d'),25);
insert into VISITE values('Deux','Notre-Dame',str_to_date('2010/12/12','%Y/%m/%d'),0);
insert into VISITE values('Cesar','Chateau de Dracula',CURDATE()-300,15);
insert into VISITE values('Cesar','Tour Eiffel',CURDATE()-20,6);
insert into VISITE values('Cesar','Corcovado',CURDATE()-60,5);
insert into VISITE values('Cesar','Colisee',CURDATE()-400,20);
insert into VISITE values('Cesar','Tour de Pise',CURDATE()-5,10);
insert into VISITE values('Cesar','Chateau de Versailles',CURDATE()-155,25);
insert into VISITE values('Cesar','Cathedrale de la Se',CURDATE()-500,0);
insert into VISITE values('Cesar','Maison du Peuple',CURDATE()-700,5);
insert into VISITE values('Cesar','Musee du CNAM',CURDATE()-2,0);
insert into VISITE values('Cesar','Notre-Dame',CURDATE()-2,0);
insert into VISITE values('Cesar','Vatican',CURDATE()-250,30);
insert into VISITE values('Legrand','Musee du CNAM',CURDATE()-2,5);
insert into VISITE values('Darc','Colisee',CURDATE()-30,15);
insert into VISITE values('Darc','Maison du Peuple',CURDATE()-120,10);
insert into VISITE values('Stote','Notre-Dame',CURDATE()-5,3);
insert into VISITE values('Stote','Musee du CNAM',CURDATE()-10,3);
insert into VISITE values('Stote','Chateau de Versailles',CURDATE()-300,30);
insert into VISITE values('Stote','Chateau de Dracula',CURDATE()-500,5);
insert into VISITE values('Stote','Tour Eiffel',CURDATE()-40,20);
insert into VISITE values('Stote','Corcovado',CURDATE()-600,5);
insert into VISITE values('Stote','Colisee',CURDATE()-55,20);
insert into VISITE values('Stote','Tour de Pise',CURDATE()-65,15);
insert into VISITE values('Stote','Cathedrale de la Se',CURDATE()-700,5);
insert into VISITE values('Stote','Maison du Peuple',CURDATE()-35,5);
insert into VISITE values('Stote','Vatican',CURDATE()-250,20);
insert into VISITE values('Darc','Musee du CNAM',CURDATE()-170,4);

UPDATE VISITE set prixEntree = prixEntree + 2 where nomTOURISTE = 'Cesar'
---- exo 1
select * from MONUMENT;
---- exo 2
select * from TOURISTE where paysOrigin like 'France';
---- exo 3
select nom, ville from MONUMENT where pays like 'Roumanie';
---- exo 4
select distinct * from MONUMENT where anneeConstruction BETWEEN 500 and 1500;
---- exo 5
select distinct nomTouriste from VISITE where prixEntree = 0;
---- exo 6
select nom from MONUMENT where nom like 'Tour%' and nom in (select nomMonument from VISITE where prixEntree >= 20);
---- exo 7
select nom from MONUMENT order by nom;
select nom, anneeConstruction from MONUMENT order by anneeConstruction desc;
---- exo 8
select * from MONUMENT where anneeConstruction is NULL;
---- exo 9
select nom, prenom, age, nomMonument from TOURISTE inner JOIN VISITE on TOURISTE.nom like VISITE.nomTOURISTE;
---- exo 10
select distinct ville from MONUMENT where nom in (select nomMonument from VISITE where prixEntree = 0);
---- exo 11
select paysOrigin from TOURISTE where nom IN (select nomTOURISTE from VISITE where nomMonument IN (select nom from MONUMENT where pays like 'Bresil'));
---- exo 12
select TOURISTE.nom from TOURISTE inner join MONUMENT on TOURISTE.nom like MONUMENT.nom;
---- exo 13
select distinct T1.nom, T1.prenom, T2.nom, T2.prenom from TOURISTE AS T1 LEFT OUTER JOIN TOURISTE AS T2 ON T1.paysOrigin = T2.paysOrigin where T1.nom not like T2.nom;
---- exo 14
select nom, paysOrigin from TOURISTE where nom IN (select nomTOURISTE from VISITE where nomMonument IN (select nom from MONUMENT where pays = paysOrigin));
---- exo 15
select distinct T1.nom, T1.paysOrigin, T2.nom, T2.paysOrigin
from (select * from VISITE natural join TOURISTE) as T1 cross join (select * from VISITE natural join TOURISTE) as T2
where T1.nomTouriste < T2.nomTouriste and T1.nomMonument like T2.nomMonument and T1.dateVisite = T2.dateVisite ;
---- exo 16
select paysOrigin from TOURISTE
union
select pays from MONUMENT;
---- exo 17
select pays from MONUMENT where pays not in (select paysOrigin from TOURISTE);
---- exo 18
select pays from MONUMENT where nom in (select nomMonument from VISITE where prixEntree < 15);
select pays from MONUMENT where nom in (select nomMonument from VISITE) /
-- ---- exo 19
-- select nom, paysOrigin from TOURISTE where paysOrigin not in (select pays from MONUMENT where nom in (select nomMonument from VISITE where TOURISTE.nom like nomTOURISTE));
-- ---- exo 20
-- select distinct TOURISTE.nom, MONUMENT.nom from TOURISTE, MONUMENT where MONUMENT.anneeConstruction > CURDATE()-TOURISTE.age;
-- ---- exo 21
-- select distinct TOURISTE.nom from TOURISTE, MONUMENT where MONUMENT.anneeConstruction < CURDATE()-TOURISTE.age;
-- ---- exo 22
-- select min(age) from TOURISTE;
-- ---- exo 23
-- select distinct nomMonument from VISITE where prixEntree = (select min(prixEntree) from VISITE where EXTRACT(YEAR from dateVisite) = 2010);
-- ---- exo 24
-- select nomTOURISTE, count(nomTOURISTE) from VISITE group by nomTOURISTE;
-- ---- exo 25
-- select avg(prixEntree) from VISITE where nomMonument IN (select nom from MONUMENT where pays like 'Bresil');
-- ---- exo 26
-- select avg(prixEntree) from VISITE;
-- ---- exo 27
-- select nomMonument from VISITE where 10000000 < (select count(nomMonument) from VISITE) group by nomMonument;
-- ---- exo 28
-- select nomMonument, avg(prixEntree) from VISITE group by nomMonument;
-- ---- exo 29
-- select nomMonument, avg(prixEntree) from VISITE group by nomMonument;
-- ---- exo 30
--
-- ---- exo 31
-- Select distinct TOURISTE.nom, MONUMENT.nom, MONUMENT.ville from TOURISTE, MONUMENT where paysOrigin = 'Bresil' and pays = 'Bresil';
-- ---- exo 32 Donner la liste des monuments ayant été visité par tous les touristes français.
-- select nomMonument from (select distinct nomTOURISTE, nomMonument from VISITE where nomTOURISTE in (select nom from TOURISTE where paysOrigin = 'France')) group by nomMonument having count(nomMonument) = (select count() from TOURISTE where paysOrigin = 'France');
-- ---- exo 33
