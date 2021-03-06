INSERT INTO MEASURE (MEASURE_TYPE, NAME, PARENT_ID) VALUES (0, 'Schwefelung', null);
INSERT INTO MEASURE (MEASURE_TYPE, NAME, PARENT_ID) VALUES (0, 'Anreicherung', null);
INSERT INTO MEASURE (MEASURE_TYPE, NAME, PARENT_ID) VALUES (0, 'Schönung', null);
INSERT INTO MEASURE (MEASURE_TYPE, NAME, PARENT_ID) VALUES (0, 'Eiweisschönung', (SELECT ID FROM MEASURE WHERE NAME = 'Schönung'));
INSERT INTO MEASURE (MEASURE_TYPE, NAME, PARENT_ID) VALUES (0, 'Entsäuerung', null);
INSERT INTO MEASURE (MEASURE_TYPE, NAME, PARENT_ID) VALUES (1, 'Umfüllen', null);
INSERT INTO MEASURE (MEASURE_TYPE, NAME, PARENT_ID) VALUES (0, 'Vorfiltration', null);
INSERT INTO MEASURE (MEASURE_TYPE, NAME, PARENT_ID) VALUES (0, 'Flaschenfüllung', null);

INSERT INTO CONTAINER_TYPE (NAME) VALUES ('Kunststoff');
INSERT INTO CONTAINER_TYPE (NAME) VALUES ('Edelstahl');
INSERT INTO CONTAINER_TYPE (NAME) VALUES ('Holz');

INSERT INTO LOCATION (NAME) VALUES ('Droß');
INSERT INTO LOCATION (NAME) VALUES ('Stratzing');

INSERT INTO CONTAINER (DESIGNATION, CONTAINER_TYPE_ID, LOCATION_ID, CAPACITY, IS_ALWAYS_FULL_CONTAINER)
VALUES (
'Fass 1', (SELECT ID FROM CONTAINER_TYPE WHERE NAME = 'Kunststoff'),
(SELECT ID FROM LOCATION WHERE NAME = 'Stratzing'),
10000,
false
);

INSERT INTO CONTAINER (DESIGNATION, CONTAINER_TYPE_ID, LOCATION_ID, CAPACITY, IS_ALWAYS_FULL_CONTAINER)
VALUES (
'kleines Fass 1', (SELECT ID FROM CONTAINER_TYPE WHERE NAME = 'Kunststoff'),
(SELECT ID FROM LOCATION WHERE NAME = 'Droß'),
300,
false
);

INSERT INTO CONTAINER (DESIGNATION, CONTAINER_TYPE_ID, LOCATION_ID, CAPACITY, IS_ALWAYS_FULL_CONTAINER)
VALUES (
'Rotwein 1', (SELECT ID FROM CONTAINER_TYPE WHERE NAME = 'Edelstahl'),
(SELECT ID FROM LOCATION WHERE NAME = 'Droß'),
2000,
false
);

INSERT INTO CONTAINER (DESIGNATION, CONTAINER_TYPE_ID, LOCATION_ID, CAPACITY, IS_ALWAYS_FULL_CONTAINER)
VALUES (
'Tank 1', (SELECT ID FROM CONTAINER_TYPE WHERE NAME = 'Edelstahl'),
(SELECT ID FROM LOCATION WHERE NAME = 'Droß'),
3200,
false
);

INSERT INTO CONTAINER (DESIGNATION, CONTAINER_TYPE_ID, LOCATION_ID, CAPACITY, IS_ALWAYS_FULL_CONTAINER)
VALUES (
'Fass 2', (SELECT ID FROM CONTAINER_TYPE WHERE NAME = 'Kunststoff'),
(SELECT ID FROM LOCATION WHERE NAME = 'Stratzing'),
10000,
false
);

INSERT INTO CONTAINER (DESIGNATION, CONTAINER_TYPE_ID, LOCATION_ID, CAPACITY, IS_ALWAYS_FULL_CONTAINER)
VALUES (
'kleines Fass 2', (SELECT ID FROM CONTAINER_TYPE WHERE NAME = 'Kunststoff'),
(SELECT ID FROM LOCATION WHERE NAME = 'Droß'),
300,
false
);

INSERT INTO CONTAINER (DESIGNATION, CONTAINER_TYPE_ID, LOCATION_ID, CAPACITY, IS_ALWAYS_FULL_CONTAINER)
VALUES (
'Rotwein 2', (SELECT ID FROM CONTAINER_TYPE WHERE NAME = 'Edelstahl'),
(SELECT ID FROM LOCATION WHERE NAME = 'Droß'),
2000,
false
);

INSERT INTO CONTAINER (DESIGNATION, CONTAINER_TYPE_ID, LOCATION_ID, CAPACITY, IS_ALWAYS_FULL_CONTAINER)
VALUES (
'Tank unten 2', (SELECT ID FROM CONTAINER_TYPE WHERE NAME = 'Edelstahl'),
(SELECT ID FROM LOCATION WHERE NAME = 'Droß'),
1000,
false
);

INSERT INTO CONTAINER (DESIGNATION, CONTAINER_TYPE_ID, LOCATION_ID, CAPACITY, IS_ALWAYS_FULL_CONTAINER)
VALUES (
'Tank mitte 2', (SELECT ID FROM CONTAINER_TYPE WHERE NAME = 'Edelstahl'),
(SELECT ID FROM LOCATION WHERE NAME = 'Droß'),
1000,
false
);

INSERT INTO CONTAINER (DESIGNATION, CONTAINER_TYPE_ID, LOCATION_ID, CAPACITY, IS_ALWAYS_FULL_CONTAINER)
VALUES (
'Tank oben 2', (SELECT ID FROM CONTAINER_TYPE WHERE NAME = 'Edelstahl'),
(SELECT ID FROM LOCATION WHERE NAME = 'Droß'),
1000,
false
);

INSERT INTO CONTAINER (DESIGNATION, CONTAINER_TYPE_ID, LOCATION_ID, CAPACITY, IS_ALWAYS_FULL_CONTAINER)
VALUES (
'Fass 3', (SELECT ID FROM CONTAINER_TYPE WHERE NAME = 'Kunststoff'),
(SELECT ID FROM LOCATION WHERE NAME = 'Stratzing'),
10000,
false
);

INSERT INTO CONTAINER (DESIGNATION, CONTAINER_TYPE_ID, LOCATION_ID, CAPACITY, IS_ALWAYS_FULL_CONTAINER)
VALUES (
'Holzfass 3', (SELECT ID FROM CONTAINER_TYPE WHERE NAME = 'Holz'),
(SELECT ID FROM LOCATION WHERE NAME = 'Stratzing'),
4380,
false
);

INSERT INTO CONTAINER (DESIGNATION, CONTAINER_TYPE_ID, LOCATION_ID, CAPACITY, IS_ALWAYS_FULL_CONTAINER)
VALUES (
'kleines Fass 3', (SELECT ID FROM CONTAINER_TYPE WHERE NAME = 'Kunststoff'),
(SELECT ID FROM LOCATION WHERE NAME = 'Droß'),
300,
false
);

INSERT INTO CONTAINER (DESIGNATION, CONTAINER_TYPE_ID, LOCATION_ID, CAPACITY, IS_ALWAYS_FULL_CONTAINER)
VALUES (
'Rotwein 3', (SELECT ID FROM CONTAINER_TYPE WHERE NAME = 'Edelstahl'),
(SELECT ID FROM LOCATION WHERE NAME = 'Droß'),
2000,
false
);

INSERT INTO CONTAINER (DESIGNATION, CONTAINER_TYPE_ID, LOCATION_ID, CAPACITY, IS_ALWAYS_FULL_CONTAINER)
VALUES (
'Tank unten 3', (SELECT ID FROM CONTAINER_TYPE WHERE NAME = 'Edelstahl'),
(SELECT ID FROM LOCATION WHERE NAME = 'Droß'),
2000,
false
);

INSERT INTO CONTAINER (DESIGNATION, CONTAINER_TYPE_ID, LOCATION_ID, CAPACITY, IS_ALWAYS_FULL_CONTAINER)
VALUES (
'Tank oben 3', (SELECT ID FROM CONTAINER_TYPE WHERE NAME = 'Edelstahl'),
(SELECT ID FROM LOCATION WHERE NAME = 'Droß'),
2000,
false
);

INSERT INTO CONTAINER (DESIGNATION, CONTAINER_TYPE_ID, LOCATION_ID, CAPACITY, IS_ALWAYS_FULL_CONTAINER)
VALUES (
'Fass 4', (SELECT ID FROM CONTAINER_TYPE WHERE NAME = 'Kunststoff'),
(SELECT ID FROM LOCATION WHERE NAME = 'Stratzing'),
4000,
false
);

INSERT INTO CONTAINER (DESIGNATION, CONTAINER_TYPE_ID, LOCATION_ID, CAPACITY, IS_ALWAYS_FULL_CONTAINER)
VALUES (
'kleines Fass 4', (SELECT ID FROM CONTAINER_TYPE WHERE NAME = 'Kunststoff'),
(SELECT ID FROM LOCATION WHERE NAME = 'Droß'),
200,
false
);

INSERT INTO CONTAINER (DESIGNATION, CONTAINER_TYPE_ID, LOCATION_ID, CAPACITY, IS_ALWAYS_FULL_CONTAINER)
VALUES (
'Rotwein 4', (SELECT ID FROM CONTAINER_TYPE WHERE NAME = 'Edelstahl'),
(SELECT ID FROM LOCATION WHERE NAME = 'Droß'),
2000,
false
);

INSERT INTO CONTAINER (DESIGNATION, CONTAINER_TYPE_ID, LOCATION_ID, CAPACITY, IS_ALWAYS_FULL_CONTAINER)
VALUES (
'Tank unten 4', (SELECT ID FROM CONTAINER_TYPE WHERE NAME = 'Edelstahl'),
(SELECT ID FROM LOCATION WHERE NAME = 'Droß'),
2000,
false
);

INSERT INTO CONTAINER (DESIGNATION, CONTAINER_TYPE_ID, LOCATION_ID, CAPACITY, IS_ALWAYS_FULL_CONTAINER)
VALUES (
'Tank oben 4', (SELECT ID FROM CONTAINER_TYPE WHERE NAME = 'Edelstahl'),
(SELECT ID FROM LOCATION WHERE NAME = 'Droß'),
2000,
false
);

INSERT INTO CONTAINER (DESIGNATION, CONTAINER_TYPE_ID, LOCATION_ID, CAPACITY, IS_ALWAYS_FULL_CONTAINER)
VALUES (
'Fass 5', (SELECT ID FROM CONTAINER_TYPE WHERE NAME = 'Kunststoff'),
(SELECT ID FROM LOCATION WHERE NAME = 'Stratzing'),
4000,
false
);

INSERT INTO CONTAINER (DESIGNATION, CONTAINER_TYPE_ID, LOCATION_ID, CAPACITY, IS_ALWAYS_FULL_CONTAINER)
VALUES (
'kleines Fass 5', (SELECT ID FROM CONTAINER_TYPE WHERE NAME = 'Kunststoff'),
(SELECT ID FROM LOCATION WHERE NAME = 'Droß'),
200,
false
);

INSERT INTO CONTAINER (DESIGNATION, CONTAINER_TYPE_ID, LOCATION_ID, CAPACITY, IS_ALWAYS_FULL_CONTAINER)
VALUES (
'Rotwein 5', (SELECT ID FROM CONTAINER_TYPE WHERE NAME = 'Edelstahl'),
(SELECT ID FROM LOCATION WHERE NAME = 'Droß'),
2000,
false
);

INSERT INTO CONTAINER (DESIGNATION, CONTAINER_TYPE_ID, LOCATION_ID, CAPACITY, IS_ALWAYS_FULL_CONTAINER)
VALUES (
'Tank 5', (SELECT ID FROM CONTAINER_TYPE WHERE NAME = 'Edelstahl'),
(SELECT ID FROM LOCATION WHERE NAME = 'Droß'),
4000,
false
);

INSERT INTO CONTAINER (DESIGNATION, CONTAINER_TYPE_ID, LOCATION_ID, CAPACITY, IS_ALWAYS_FULL_CONTAINER)
VALUES (
'Fass 6', (SELECT ID FROM CONTAINER_TYPE WHERE NAME = 'Kunststoff'),
(SELECT ID FROM LOCATION WHERE NAME = 'Stratzing'),
1500,
false
);

INSERT INTO CONTAINER (DESIGNATION, CONTAINER_TYPE_ID, LOCATION_ID, CAPACITY, IS_ALWAYS_FULL_CONTAINER)
VALUES (
'kleines Fass 6', (SELECT ID FROM CONTAINER_TYPE WHERE NAME = 'Kunststoff'),
(SELECT ID FROM LOCATION WHERE NAME = 'Droß'),
100,
false
);

INSERT INTO CONTAINER (DESIGNATION, CONTAINER_TYPE_ID, LOCATION_ID, CAPACITY, IS_ALWAYS_FULL_CONTAINER)
VALUES (
'Rotwein 6', (SELECT ID FROM CONTAINER_TYPE WHERE NAME = 'Edelstahl'),
(SELECT ID FROM LOCATION WHERE NAME = 'Droß'),
2000,
false
);

INSERT INTO CONTAINER (DESIGNATION, CONTAINER_TYPE_ID, LOCATION_ID, CAPACITY, IS_ALWAYS_FULL_CONTAINER)
VALUES (
'Tank 6', (SELECT ID FROM CONTAINER_TYPE WHERE NAME = 'Edelstahl'),
(SELECT ID FROM LOCATION WHERE NAME = 'Droß'),
8000,
false
);

INSERT INTO CONTAINER (DESIGNATION, CONTAINER_TYPE_ID, LOCATION_ID, CAPACITY, IS_ALWAYS_FULL_CONTAINER)
VALUES (
'Fass 7', (SELECT ID FROM CONTAINER_TYPE WHERE NAME = 'Kunststoff'),
(SELECT ID FROM LOCATION WHERE NAME = 'Stratzing'),
1500,
false
);

INSERT INTO CONTAINER (DESIGNATION, CONTAINER_TYPE_ID, LOCATION_ID, CAPACITY, IS_ALWAYS_FULL_CONTAINER)
VALUES (
'Rotwein 7', (SELECT ID FROM CONTAINER_TYPE WHERE NAME = 'Edelstahl'),
(SELECT ID FROM LOCATION WHERE NAME = 'Droß'),
2000,
false
);

INSERT INTO CONTAINER (DESIGNATION, CONTAINER_TYPE_ID, LOCATION_ID, CAPACITY, IS_ALWAYS_FULL_CONTAINER)
VALUES (
'Tank 7', (SELECT ID FROM CONTAINER_TYPE WHERE NAME = 'Edelstahl'),
(SELECT ID FROM LOCATION WHERE NAME = 'Droß'),
3970,
false
);

INSERT INTO CONTAINER (DESIGNATION, CONTAINER_TYPE_ID, LOCATION_ID, CAPACITY, IS_ALWAYS_FULL_CONTAINER)
VALUES (
'Faß 8', (SELECT ID FROM CONTAINER_TYPE WHERE NAME = 'Kunststoff'),
(SELECT ID FROM LOCATION WHERE NAME = 'Stratzing'),
4380,
false
);

INSERT INTO CONTAINER (DESIGNATION, CONTAINER_TYPE_ID, LOCATION_ID, CAPACITY, IS_ALWAYS_FULL_CONTAINER)
VALUES (
'Tank 8', (SELECT ID FROM CONTAINER_TYPE WHERE NAME = 'Edelstahl'),
(SELECT ID FROM LOCATION WHERE NAME = 'Droß'),
3970,
false
);

INSERT INTO CONTAINER (DESIGNATION, CONTAINER_TYPE_ID, LOCATION_ID, CAPACITY, IS_ALWAYS_FULL_CONTAINER)
VALUES (
'Tank 9', (SELECT ID FROM CONTAINER_TYPE WHERE NAME = 'Edelstahl'),
(SELECT ID FROM LOCATION WHERE NAME = 'Droß'),
3650,
false
);

INSERT INTO CONTAINER (DESIGNATION, CONTAINER_TYPE_ID, LOCATION_ID, CAPACITY, IS_ALWAYS_FULL_CONTAINER)
VALUES (
'Tank 10', (SELECT ID FROM CONTAINER_TYPE WHERE NAME = 'Edelstahl'),
(SELECT ID FROM LOCATION WHERE NAME = 'Droß'),
3650,
false
);

INSERT INTO CONTAINER (DESIGNATION, CONTAINER_TYPE_ID, LOCATION_ID, CAPACITY, IS_ALWAYS_FULL_CONTAINER)
VALUES (
'Tank 11', (SELECT ID FROM CONTAINER_TYPE WHERE NAME = 'Edelstahl'),
(SELECT ID FROM LOCATION WHERE NAME = 'Droß'),
3650,
false
);

INSERT INTO CONTAINER (DESIGNATION, CONTAINER_TYPE_ID, LOCATION_ID, CAPACITY, IS_ALWAYS_FULL_CONTAINER)
VALUES (
'Tank unten 12', (SELECT ID FROM CONTAINER_TYPE WHERE NAME = 'Edelstahl'),
(SELECT ID FROM LOCATION WHERE NAME = 'Droß'),
1600,
false
);

INSERT INTO CONTAINER (DESIGNATION, CONTAINER_TYPE_ID, LOCATION_ID, CAPACITY, IS_ALWAYS_FULL_CONTAINER)
VALUES (
'Tank oben 12', (SELECT ID FROM CONTAINER_TYPE WHERE NAME = 'Edelstahl'),
(SELECT ID FROM LOCATION WHERE NAME = 'Droß'),
1500,
false
);

INSERT INTO CONTAINER (DESIGNATION, CONTAINER_TYPE_ID, LOCATION_ID, CAPACITY, IS_ALWAYS_FULL_CONTAINER)
VALUES (
'Tank 13 Maische', (SELECT ID FROM CONTAINER_TYPE WHERE NAME = 'Edelstahl'),
(SELECT ID FROM LOCATION WHERE NAME = 'Droß'),
2450,
false
);

INSERT INTO CONTAINER (DESIGNATION, CONTAINER_TYPE_ID, LOCATION_ID, CAPACITY, IS_ALWAYS_FULL_CONTAINER)
VALUES (
'Tank 14 Maische', (SELECT ID FROM CONTAINER_TYPE WHERE NAME = 'Edelstahl'),
(SELECT ID FROM LOCATION WHERE NAME = 'Droß'),
8400,
false
);

INSERT INTO CONTAINER (DESIGNATION, CONTAINER_TYPE_ID, LOCATION_ID, CAPACITY, IS_ALWAYS_FULL_CONTAINER)
VALUES (
'Tank 15 Maische', (SELECT ID FROM CONTAINER_TYPE WHERE NAME = 'Edelstahl'),
(SELECT ID FROM LOCATION WHERE NAME = 'Droß'),
8400,
false
);

INSERT INTO CONTAINER (DESIGNATION, CONTAINER_TYPE_ID, LOCATION_ID, CAPACITY, IS_ALWAYS_FULL_CONTAINER)
VALUES (
'Tank 16', (SELECT ID FROM CONTAINER_TYPE WHERE NAME = 'Edelstahl'),
(SELECT ID FROM LOCATION WHERE NAME = 'Droß'),
2000,
false
);

INSERT INTO CONTAINER (DESIGNATION, CONTAINER_TYPE_ID, LOCATION_ID, CAPACITY, IS_ALWAYS_FULL_CONTAINER)
VALUES (
'Tank 17', (SELECT ID FROM CONTAINER_TYPE WHERE NAME = 'Edelstahl'),
(SELECT ID FROM LOCATION WHERE NAME = 'Droß'),
2000,
false
);

INSERT INTO CONTAINER (DESIGNATION, CONTAINER_TYPE_ID, LOCATION_ID, CAPACITY, IS_ALWAYS_FULL_CONTAINER)
VALUES (
'Tank 18', (SELECT ID FROM CONTAINER_TYPE WHERE NAME = 'Edelstahl'),
(SELECT ID FROM LOCATION WHERE NAME = 'Droß'),
2000,
false
);

INSERT INTO CONTAINER (DESIGNATION, CONTAINER_TYPE_ID, LOCATION_ID, CAPACITY, IS_ALWAYS_FULL_CONTAINER)
VALUES (
'Tank 19', (SELECT ID FROM CONTAINER_TYPE WHERE NAME = 'Edelstahl'),
(SELECT ID FROM LOCATION WHERE NAME = 'Droß'),
2000,
false
);

INSERT INTO CONTAINER (DESIGNATION, CONTAINER_TYPE_ID, LOCATION_ID, CAPACITY, IS_ALWAYS_FULL_CONTAINER)
VALUES (
'Tank 20', (SELECT ID FROM CONTAINER_TYPE WHERE NAME = 'Edelstahl'),
(SELECT ID FROM LOCATION WHERE NAME = 'Droß'),
2000,
false
);

INSERT INTO CONTAINER (DESIGNATION, CONTAINER_TYPE_ID, LOCATION_ID, CAPACITY, IS_ALWAYS_FULL_CONTAINER)
VALUES (
'Tank 21', (SELECT ID FROM CONTAINER_TYPE WHERE NAME = 'Edelstahl'),
(SELECT ID FROM LOCATION WHERE NAME = 'Droß'),
1040,
false
);

INSERT INTO CONTAINER (DESIGNATION, CONTAINER_TYPE_ID, LOCATION_ID, CAPACITY, IS_ALWAYS_FULL_CONTAINER)
VALUES (
'Tank 22', (SELECT ID FROM CONTAINER_TYPE WHERE NAME = 'Edelstahl'),
(SELECT ID FROM LOCATION WHERE NAME = 'Droß'),
1040,
false
);

INSERT INTO CONTAINER (DESIGNATION, CONTAINER_TYPE_ID, LOCATION_ID, CAPACITY, IS_ALWAYS_FULL_CONTAINER)
VALUES (
'Tank 23 Immervoll', (SELECT ID FROM CONTAINER_TYPE WHERE NAME = 'Edelstahl'),
(SELECT ID FROM LOCATION WHERE NAME = 'Droß'),
1800,
true
);

INSERT INTO CONTAINER (DESIGNATION, CONTAINER_TYPE_ID, LOCATION_ID, CAPACITY, IS_ALWAYS_FULL_CONTAINER)
VALUES (
'Tank 24 Immervoll', (SELECT ID FROM CONTAINER_TYPE WHERE NAME = 'Edelstahl'),
(SELECT ID FROM LOCATION WHERE NAME = 'Droß'),
1500,
true
);

INSERT INTO CONTAINER (DESIGNATION, CONTAINER_TYPE_ID, LOCATION_ID, CAPACITY, IS_ALWAYS_FULL_CONTAINER)
VALUES (
'Tank 25 Immervoll', (SELECT ID FROM CONTAINER_TYPE WHERE NAME = 'Edelstahl'),
(SELECT ID FROM LOCATION WHERE NAME = 'Droß'),
1500,
true
);

INSERT INTO CONTAINER (DESIGNATION, CONTAINER_TYPE_ID, LOCATION_ID, CAPACITY, IS_ALWAYS_FULL_CONTAINER)
VALUES (
'Tank 26 Immervoll', (SELECT ID FROM CONTAINER_TYPE WHERE NAME = 'Edelstahl'),
(SELECT ID FROM LOCATION WHERE NAME = 'Droß'),
1500,
true
);

INSERT INTO CONTAINER (DESIGNATION, CONTAINER_TYPE_ID, LOCATION_ID, CAPACITY, IS_ALWAYS_FULL_CONTAINER)
VALUES (
'Tank 27 Immervoll', (SELECT ID FROM CONTAINER_TYPE WHERE NAME = 'Edelstahl'),
(SELECT ID FROM LOCATION WHERE NAME = 'Droß'),
1500,
true
);

INSERT INTO CONTAINER (DESIGNATION, CONTAINER_TYPE_ID, LOCATION_ID, CAPACITY, IS_ALWAYS_FULL_CONTAINER)
VALUES (
'Tank 28 Immervoll', (SELECT ID FROM CONTAINER_TYPE WHERE NAME = 'Edelstahl'),
(SELECT ID FROM LOCATION WHERE NAME = 'Droß'),
1000,
true
);

INSERT INTO CONTAINER (DESIGNATION, CONTAINER_TYPE_ID, LOCATION_ID, CAPACITY, IS_ALWAYS_FULL_CONTAINER)
VALUES (
'Tank 29 Immervoll', (SELECT ID FROM CONTAINER_TYPE WHERE NAME = 'Edelstahl'),
(SELECT ID FROM LOCATION WHERE NAME = 'Droß'),
720,
true
);

INSERT INTO CONTAINER (DESIGNATION, CONTAINER_TYPE_ID, LOCATION_ID, CAPACITY, IS_ALWAYS_FULL_CONTAINER)
VALUES (
'Tank 30 Immervoll', (SELECT ID FROM CONTAINER_TYPE WHERE NAME = 'Edelstahl'),
(SELECT ID FROM LOCATION WHERE NAME = 'Droß'),
800,
false
);

INSERT INTO CONTAINER (DESIGNATION, CONTAINER_TYPE_ID, LOCATION_ID, CAPACITY, IS_ALWAYS_FULL_CONTAINER)
VALUES (
'Tank 31', (SELECT ID FROM CONTAINER_TYPE WHERE NAME = 'Kunststoff'),
(SELECT ID FROM LOCATION WHERE NAME = 'Droß'),
700,
false
);

INSERT INTO CONTAINER (DESIGNATION, CONTAINER_TYPE_ID, LOCATION_ID, CAPACITY, IS_ALWAYS_FULL_CONTAINER)
VALUES (
'Tank 32', (SELECT ID FROM CONTAINER_TYPE WHERE NAME = 'Edelstahl'),
(SELECT ID FROM LOCATION WHERE NAME = 'Droß'),
320,
false
);

INSERT INTO CONTAINER (DESIGNATION, CONTAINER_TYPE_ID, LOCATION_ID, CAPACITY, IS_ALWAYS_FULL_CONTAINER)
VALUES (
'Tank 33', (SELECT ID FROM CONTAINER_TYPE WHERE NAME = 'Edelstahl'),
(SELECT ID FROM LOCATION WHERE NAME = 'Droß'),
500,
false
);

INSERT INTO CONTAINER (DESIGNATION, CONTAINER_TYPE_ID, LOCATION_ID, CAPACITY, IS_ALWAYS_FULL_CONTAINER)
VALUES (
'Tank 34', (SELECT ID FROM CONTAINER_TYPE WHERE NAME = 'Edelstahl'),
(SELECT ID FROM LOCATION WHERE NAME = 'Droß'),
750,
false
);