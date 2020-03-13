--
-- File generated with SQLiteStudio v3.2.1 on vi. mar. 13 20:10:36 2020
--
-- Text encoding used: System
--
PRAGMA foreign_keys = off;
BEGIN TRANSACTION;

-- Table: Constant
CREATE TABLE Constant (
    name     TEXT PRIMARY KEY,
    category TEXT,
    value    REAL
);

INSERT INTO Constant (name, category, value) VALUES ('baseCrewCap', 'crew', 8.0);
INSERT INTO Constant (name, category, value) VALUES ('baseStorableE', 'energy', 20000000000.);
INSERT INTO Constant (name, category, value) VALUES ('baseGeneratedE', 'energy', 500000000.);
INSERT INTO Constant (name, category, value) VALUES ('crewRatioSergeantsPerLieutenant', 'crew', 4.0);
INSERT INTO Constant (name, category, value) VALUES ('crewRatioCrewPerSergeant', 'crew', 10.0);
INSERT INTO Constant (name, category, value) VALUES ('crewRatioCommandersPerGeneral', 'crew', 3.0);
INSERT INTO Constant (name, category, value) VALUES ('crewRatioLieutenantsPerCommander', 'crew', 3.0);
INSERT INTO Constant (name, category, value) VALUES ('baseHyperSpaceReach', 'hyperspace', 2.5);
INSERT INTO Constant (name, category, value) VALUES ('maxFightersSize', 'fighters', 8.0);
INSERT INTO Constant (name, category, value) VALUES ('containersWallThickness', 'effectFormula', 0.5);

-- Table: Crew
CREATE TABLE Crew (
    idx    INTEGER PRIMARY KEY,
    name   TEXT,
    salary REAL
);

INSERT INTO Crew (idx, name, salary) VALUES (1, 'Mechanics', 100.0);
INSERT INTO Crew (idx, name, salary) VALUES (2, 'Engineers', 100.0);
INSERT INTO Crew (idx, name, salary) VALUES (3, 'Miners', 200.0);
INSERT INTO Crew (idx, name, salary) VALUES (4, 'Guners', 200.0);
INSERT INTO Crew (idx, name, salary) VALUES (5, 'Sergeants', 400.0);
INSERT INTO Crew (idx, name, salary) VALUES (6, 'Lieutenants', 1500.0);
INSERT INTO Crew (idx, name, salary) VALUES (7, 'Commanders', 2500.0);
INSERT INTO Crew (idx, name, salary) VALUES (8, 'Generals', 5000.0);
INSERT INTO Crew (idx, name, salary) VALUES (9, 'Captains', 15000.0);
INSERT INTO Crew (idx, name, salary) VALUES (10, 'Pilots', 300.0);
INSERT INTO Crew (idx, name, salary) VALUES (11, 'Security', 300.0);
INSERT INTO Crew (idx, name, salary) VALUES (12, 'Boarders', 1500.0);

-- Table: CrewCommand
CREATE TABLE CrewCommand (
    commander    INTEGER,
    commanded    INTEGER,
    commandRatio REAL,
    PRIMARY KEY (
        commander,
        commanded
    )
);

INSERT INTO CrewCommand (commander, commanded, commandRatio) VALUES (5, 1, 10.0);
INSERT INTO CrewCommand (commander, commanded, commandRatio) VALUES (5, 2, 10.0);
INSERT INTO CrewCommand (commander, commanded, commandRatio) VALUES (6, 5, 4.0);
INSERT INTO CrewCommand (commander, commanded, commandRatio) VALUES (7, 6, 3.0);
INSERT INTO CrewCommand (commander, commanded, commandRatio) VALUES (8, 7, 3.0);
INSERT INTO CrewCommand (commander, commanded, commandRatio) VALUES (5, 12, 10.0);
INSERT INTO CrewCommand (commander, commanded, commandRatio) VALUES (5, 11, 10.0);
INSERT INTO CrewCommand (commander, commanded, commandRatio) VALUES (5, 10, 10.0);
INSERT INTO CrewCommand (commander, commanded, commandRatio) VALUES (5, 4, 10.0);
INSERT INTO CrewCommand (commander, commanded, commandRatio) VALUES (5, 3, 10.0);

-- Table: Effect
CREATE TABLE Effect (
    typeModelIdx INTEGER REFERENCES TypeModel (idx),
    materialIdx  INTEGER REFERENCES Material (idx),
    n            INTEGER,
    value        REAL,
    PRIMARY KEY (
        typeModelIdx,
        materialIdx,
        n
    )
);

INSERT INTO Effect (typeModelIdx, materialIdx, n, value) VALUES (3, 0, 1, 20000.0);
INSERT INTO Effect (typeModelIdx, materialIdx, n, value) VALUES (3, 1, 1, 20000.0);
INSERT INTO Effect (typeModelIdx, materialIdx, n, value) VALUES (3, 2, 1, 20000.0);
INSERT INTO Effect (typeModelIdx, materialIdx, n, value) VALUES (3, 3, 1, 20000.0);
INSERT INTO Effect (typeModelIdx, materialIdx, n, value) VALUES (3, 4, 1, 20000.0);
INSERT INTO Effect (typeModelIdx, materialIdx, n, value) VALUES (3, 5, 1, 20000.0);
INSERT INTO Effect (typeModelIdx, materialIdx, n, value) VALUES (3, 6, 1, 20000.0);
INSERT INTO Effect (typeModelIdx, materialIdx, n, value) VALUES (5, 0, 1, 3.5);
INSERT INTO Effect (typeModelIdx, materialIdx, n, value) VALUES (5, 1, 1, 3.5);
INSERT INTO Effect (typeModelIdx, materialIdx, n, value) VALUES (5, 2, 1, 3.5);
INSERT INTO Effect (typeModelIdx, materialIdx, n, value) VALUES (5, 3, 1, 3.5);
INSERT INTO Effect (typeModelIdx, materialIdx, n, value) VALUES (5, 4, 1, 3.5);
INSERT INTO Effect (typeModelIdx, materialIdx, n, value) VALUES (5, 5, 1, 3.5);
INSERT INTO Effect (typeModelIdx, materialIdx, n, value) VALUES (5, 6, 1, 3.5);
INSERT INTO Effect (typeModelIdx, materialIdx, n, value) VALUES (6, 0, 1, 1.052631579);
INSERT INTO Effect (typeModelIdx, materialIdx, n, value) VALUES (6, 1, 1, 1.052631579);
INSERT INTO Effect (typeModelIdx, materialIdx, n, value) VALUES (6, 2, 1, 1.052631579);
INSERT INTO Effect (typeModelIdx, materialIdx, n, value) VALUES (6, 3, 1, 1.052631579);
INSERT INTO Effect (typeModelIdx, materialIdx, n, value) VALUES (6, 4, 1, 1.052631579);
INSERT INTO Effect (typeModelIdx, materialIdx, n, value) VALUES (6, 5, 1, 1.052631579);
INSERT INTO Effect (typeModelIdx, materialIdx, n, value) VALUES (6, 6, 1, 1.052631579);
INSERT INTO Effect (typeModelIdx, materialIdx, n, value) VALUES (7, 0, 1, 5000.0);
INSERT INTO Effect (typeModelIdx, materialIdx, n, value) VALUES (7, 1, 1, 5000.0);
INSERT INTO Effect (typeModelIdx, materialIdx, n, value) VALUES (7, 2, 1, 5000.0);
INSERT INTO Effect (typeModelIdx, materialIdx, n, value) VALUES (7, 3, 1, 5000.0);
INSERT INTO Effect (typeModelIdx, materialIdx, n, value) VALUES (7, 4, 1, 5000.0);
INSERT INTO Effect (typeModelIdx, materialIdx, n, value) VALUES (7, 5, 1, 5000.0);
INSERT INTO Effect (typeModelIdx, materialIdx, n, value) VALUES (7, 6, 1, 5000.0);
INSERT INTO Effect (typeModelIdx, materialIdx, n, value) VALUES (10, 3, 1, 0.5);
INSERT INTO Effect (typeModelIdx, materialIdx, n, value) VALUES (10, 4, 1, 0.5);
INSERT INTO Effect (typeModelIdx, materialIdx, n, value) VALUES (10, 5, 1, 0.5);
INSERT INTO Effect (typeModelIdx, materialIdx, n, value) VALUES (10, 6, 1, 0.5);
INSERT INTO Effect (typeModelIdx, materialIdx, n, value) VALUES (13, 0, 1, 12500.0);
INSERT INTO Effect (typeModelIdx, materialIdx, n, value) VALUES (13, 1, 1, 12500.0);
INSERT INTO Effect (typeModelIdx, materialIdx, n, value) VALUES (13, 2, 1, 12500.0);
INSERT INTO Effect (typeModelIdx, materialIdx, n, value) VALUES (13, 3, 1, 12500.0);
INSERT INTO Effect (typeModelIdx, materialIdx, n, value) VALUES (13, 4, 1, 12500.0);
INSERT INTO Effect (typeModelIdx, materialIdx, n, value) VALUES (13, 5, 1, 12500.0);
INSERT INTO Effect (typeModelIdx, materialIdx, n, value) VALUES (13, 6, 1, 12500.0);
INSERT INTO Effect (typeModelIdx, materialIdx, n, value) VALUES (15, 0, 1, 54400.0);
INSERT INTO Effect (typeModelIdx, materialIdx, n, value) VALUES (15, 6, 1, 100000.0);
INSERT INTO Effect (typeModelIdx, materialIdx, n, value) VALUES (17, 0, 1, 0.351166);
INSERT INTO Effect (typeModelIdx, materialIdx, n, value) VALUES (17, 1, 1, 0.526749);
INSERT INTO Effect (typeModelIdx, materialIdx, n, value) VALUES (17, 2, 1, 0.790124);
INSERT INTO Effect (typeModelIdx, materialIdx, n, value) VALUES (17, 3, 1, 1.18519);
INSERT INTO Effect (typeModelIdx, materialIdx, n, value) VALUES (17, 4, 1, 1.777777);
INSERT INTO Effect (typeModelIdx, materialIdx, n, value) VALUES (17, 5, 1, 2.666666);
INSERT INTO Effect (typeModelIdx, materialIdx, n, value) VALUES (17, 6, 1, 4.0);
INSERT INTO Effect (typeModelIdx, materialIdx, n, value) VALUES (17, 0, 2, 1.0);
INSERT INTO Effect (typeModelIdx, materialIdx, n, value) VALUES (17, 1, 2, 2.0);
INSERT INTO Effect (typeModelIdx, materialIdx, n, value) VALUES (17, 2, 2, 2.0);
INSERT INTO Effect (typeModelIdx, materialIdx, n, value) VALUES (17, 3, 2, 3.0);
INSERT INTO Effect (typeModelIdx, materialIdx, n, value) VALUES (17, 4, 2, 3.0);
INSERT INTO Effect (typeModelIdx, materialIdx, n, value) VALUES (17, 5, 2, 4.0);
INSERT INTO Effect (typeModelIdx, materialIdx, n, value) VALUES (17, 6, 2, 5.0);
INSERT INTO Effect (typeModelIdx, materialIdx, n, value) VALUES (19, 0, 1, 3.5);
INSERT INTO Effect (typeModelIdx, materialIdx, n, value) VALUES (19, 1, 1, 3.5);
INSERT INTO Effect (typeModelIdx, materialIdx, n, value) VALUES (19, 2, 1, 3.5);
INSERT INTO Effect (typeModelIdx, materialIdx, n, value) VALUES (19, 3, 1, 3.5);
INSERT INTO Effect (typeModelIdx, materialIdx, n, value) VALUES (19, 4, 1, 3.5);
INSERT INTO Effect (typeModelIdx, materialIdx, n, value) VALUES (19, 5, 1, 3.5);
INSERT INTO Effect (typeModelIdx, materialIdx, n, value) VALUES (19, 6, 1, 3.5);
INSERT INTO Effect (typeModelIdx, materialIdx, n, value) VALUES (50, 2, 1, 157.5);
INSERT INTO Effect (typeModelIdx, materialIdx, n, value) VALUES (50, 3, 1, 236.25);
INSERT INTO Effect (typeModelIdx, materialIdx, n, value) VALUES (50, 4, 1, 354.375);
INSERT INTO Effect (typeModelIdx, materialIdx, n, value) VALUES (50, 6, 1, 797.344);
INSERT INTO Effect (typeModelIdx, materialIdx, n, value) VALUES (51, 1, 1, 825185185.0);
INSERT INTO Effect (typeModelIdx, materialIdx, n, value) VALUES (51, 2, 1, 937407407.0);
INSERT INTO Effect (typeModelIdx, materialIdx, n, value) VALUES (51, 3, 1, 1087407407.0);
INSERT INTO Effect (typeModelIdx, materialIdx, n, value) VALUES (51, 4, 1, 1293703704.0);
INSERT INTO Effect (typeModelIdx, materialIdx, n, value) VALUES (51, 5, 1, 1500000000.);
INSERT INTO Effect (typeModelIdx, materialIdx, n, value) VALUES (51, 6, 1, 1760000000.);
INSERT INTO Effect (typeModelIdx, materialIdx, n, value) VALUES (52, 1, 1, 82500000.0);
INSERT INTO Effect (typeModelIdx, materialIdx, n, value) VALUES (52, 2, 1, 93750000.0);
INSERT INTO Effect (typeModelIdx, materialIdx, n, value) VALUES (52, 3, 1, 108750000.0);
INSERT INTO Effect (typeModelIdx, materialIdx, n, value) VALUES (52, 4, 1, 127500000.0);
INSERT INTO Effect (typeModelIdx, materialIdx, n, value) VALUES (52, 5, 1, 150000000.);
INSERT INTO Effect (typeModelIdx, materialIdx, n, value) VALUES (52, 6, 1, 176250000.0);
INSERT INTO Effect (typeModelIdx, materialIdx, n, value) VALUES (53, 1, 1, 4.65);
INSERT INTO Effect (typeModelIdx, materialIdx, n, value) VALUES (53, 2, 1, 4.65);
INSERT INTO Effect (typeModelIdx, materialIdx, n, value) VALUES (53, 3, 1, 4.65);
INSERT INTO Effect (typeModelIdx, materialIdx, n, value) VALUES (53, 4, 1, 4.65);
INSERT INTO Effect (typeModelIdx, materialIdx, n, value) VALUES (53, 6, 1, 4.65);
INSERT INTO Effect (typeModelIdx, materialIdx, n, value) VALUES (54, 3, 1, 4.0);
INSERT INTO Effect (typeModelIdx, materialIdx, n, value) VALUES (54, 4, 1, 4.0);
INSERT INTO Effect (typeModelIdx, materialIdx, n, value) VALUES (54, 6, 1, 4.0);
INSERT INTO Effect (typeModelIdx, materialIdx, n, value) VALUES (55, 2, 1, 1.45597631);
INSERT INTO Effect (typeModelIdx, materialIdx, n, value) VALUES (55, 3, 1, 1.666669836);
INSERT INTO Effect (typeModelIdx, materialIdx, n, value) VALUES (55, 4, 1, 1.907852049);
INSERT INTO Effect (typeModelIdx, materialIdx, n, value) VALUES (55, 6, 1, 2.499968584);
INSERT INTO Effect (typeModelIdx, materialIdx, n, value) VALUES (57, 3, 1, 0.1052631579);
INSERT INTO Effect (typeModelIdx, materialIdx, n, value) VALUES (57, 4, 1, 0.1052631579);
INSERT INTO Effect (typeModelIdx, materialIdx, n, value) VALUES (57, 5, 1, 0.1052631579);
INSERT INTO Effect (typeModelIdx, materialIdx, n, value) VALUES (57, 6, 1, 0.1052631579);
INSERT INTO Effect (typeModelIdx, materialIdx, n, value) VALUES (58, 4, 1, 0.52631579);
INSERT INTO Effect (typeModelIdx, materialIdx, n, value) VALUES (58, 5, 1, 0.52631579);
INSERT INTO Effect (typeModelIdx, materialIdx, n, value) VALUES (58, 6, 1, 0.52631579);
INSERT INTO Effect (typeModelIdx, materialIdx, n, value) VALUES (60, 0, 1, 6000000.);
INSERT INTO Effect (typeModelIdx, materialIdx, n, value) VALUES (60, 1, 1, 6000000.);
INSERT INTO Effect (typeModelIdx, materialIdx, n, value) VALUES (60, 2, 1, 6000000.);
INSERT INTO Effect (typeModelIdx, materialIdx, n, value) VALUES (60, 3, 1, 6000000.);
INSERT INTO Effect (typeModelIdx, materialIdx, n, value) VALUES (60, 4, 1, 6000000.);
INSERT INTO Effect (typeModelIdx, materialIdx, n, value) VALUES (60, 5, 1, 6000000.);
INSERT INTO Effect (typeModelIdx, materialIdx, n, value) VALUES (60, 6, 1, 6000000.);

-- Table: Material
CREATE TABLE Material (
    idx          INTEGER,
    name         TEXT,
    density      REAL,
    durability   REAL,
    creditCost   REAL,
    materialCost REAL,
    PRIMARY KEY (
        idx
    )
);

INSERT INTO Material (idx, name, density, durability, creditCost, materialCost) VALUES (0, 'Iron', 51.0, 4.0, 11.111110747, 5.0);
INSERT INTO Material (idx, name, density, durability, creditCost, materialCost) VALUES (1, 'Titanium', 30.0, 6.0, 15.0, 5.0);
INSERT INTO Material (idx, name, density, durability, creditCost, materialCost) VALUES (2, 'Naonite', 33.0, 9.0, 20.250000358, 5.0);
INSERT INTO Material (idx, name, density, durability, creditCost, materialCost) VALUES (3, 'Trinium', 21.0, 13.5, 27.337501646, 5.0);
INSERT INTO Material (idx, name, density, durability, creditCost, materialCost) VALUES (4, 'Xanion', 27.0, 20.25, 36.905626059, 5.0);
INSERT INTO Material (idx, name, density, durability, creditCost, materialCost) VALUES (5, 'Ogonite', 45.0, 30.375, 49.822597504, 5.0);
INSERT INTO Material (idx, name, density, durability, creditCost, materialCost) VALUES (6, 'Avorion', 36.0, 45.5625, 67.260510922, 5.0);

-- Table: Shape
CREATE TABLE Shape (
    idx            INTEGER PRIMARY KEY,
    name           TEXT,
    cuboidFilledIn REAL,
    symmetricIdx   INTEGER
);

INSERT INTO Shape (idx, name, cuboidFilledIn, symmetricIdx) VALUES (0, 'Cuboid', 1.0, 0);
INSERT INTO Shape (idx, name, cuboidFilledIn, symmetricIdx) VALUES (1, 'Edge', 0.5, 1);
INSERT INTO Shape (idx, name, cuboidFilledIn, symmetricIdx) VALUES (2, 'Corner 1', 0.1666666666, 2);
INSERT INTO Shape (idx, name, cuboidFilledIn, symmetricIdx) VALUES (3, 'Corner 2', 0.8333333333, 3);
INSERT INTO Shape (idx, name, cuboidFilledIn, symmetricIdx) VALUES (4, 'Corner 3', 0.3333333333, 4);
INSERT INTO Shape (idx, name, cuboidFilledIn, symmetricIdx) VALUES (5, 'Twisted Corner 1', 0.25, 6);
INSERT INTO Shape (idx, name, cuboidFilledIn, symmetricIdx) VALUES (6, 'Twisted Corner 2', 0.25, 5);

-- Table: Type
CREATE TABLE Type (
    idx          INTEGER PRIMARY KEY,
    typeModelIdx INTEGER REFERENCES TypeModel (idx),
    shapeIdx     INTEGER
);

INSERT INTO Type (idx, typeModelIdx, shapeIdx) VALUES (1, 1, 0);
INSERT INTO Type (idx, typeModelIdx, shapeIdx) VALUES (2, 2, 0);
INSERT INTO Type (idx, typeModelIdx, shapeIdx) VALUES (3, 3, 0);
INSERT INTO Type (idx, typeModelIdx, shapeIdx) VALUES (4, 4, 0);
INSERT INTO Type (idx, typeModelIdx, shapeIdx) VALUES (5, 5, 0);
INSERT INTO Type (idx, typeModelIdx, shapeIdx) VALUES (6, 6, 0);
INSERT INTO Type (idx, typeModelIdx, shapeIdx) VALUES (7, 7, 0);
INSERT INTO Type (idx, typeModelIdx, shapeIdx) VALUES (8, 8, 0);
INSERT INTO Type (idx, typeModelIdx, shapeIdx) VALUES (9, 9, 0);
INSERT INTO Type (idx, typeModelIdx, shapeIdx) VALUES (10, 10, 0);
INSERT INTO Type (idx, typeModelIdx, shapeIdx) VALUES (11, 11, 0);
INSERT INTO Type (idx, typeModelIdx, shapeIdx) VALUES (12, 12, 0);
INSERT INTO Type (idx, typeModelIdx, shapeIdx) VALUES (13, 13, 0);
INSERT INTO Type (idx, typeModelIdx, shapeIdx) VALUES (14, 14, 0);
INSERT INTO Type (idx, typeModelIdx, shapeIdx) VALUES (15, 15, 0);
INSERT INTO Type (idx, typeModelIdx, shapeIdx) VALUES (16, 16, 0);
INSERT INTO Type (idx, typeModelIdx, shapeIdx) VALUES (17, 17, 0);
INSERT INTO Type (idx, typeModelIdx, shapeIdx) VALUES (18, 18, 0);
INSERT INTO Type (idx, typeModelIdx, shapeIdx) VALUES (19, 19, 0);
INSERT INTO Type (idx, typeModelIdx, shapeIdx) VALUES (20, 20, 0);
INSERT INTO Type (idx, typeModelIdx, shapeIdx) VALUES (21, 20, 0);
INSERT INTO Type (idx, typeModelIdx, shapeIdx) VALUES (50, 50, 0);
INSERT INTO Type (idx, typeModelIdx, shapeIdx) VALUES (51, 51, 0);
INSERT INTO Type (idx, typeModelIdx, shapeIdx) VALUES (52, 52, 0);
INSERT INTO Type (idx, typeModelIdx, shapeIdx) VALUES (53, 53, 0);
INSERT INTO Type (idx, typeModelIdx, shapeIdx) VALUES (54, 54, 0);
INSERT INTO Type (idx, typeModelIdx, shapeIdx) VALUES (55, 55, 0);
INSERT INTO Type (idx, typeModelIdx, shapeIdx) VALUES (56, 56, 0);
INSERT INTO Type (idx, typeModelIdx, shapeIdx) VALUES (57, 57, 0);
INSERT INTO Type (idx, typeModelIdx, shapeIdx) VALUES (58, 58, 0);
INSERT INTO Type (idx, typeModelIdx, shapeIdx) VALUES (60, 60, 0);
INSERT INTO Type (idx, typeModelIdx, shapeIdx) VALUES (61, 61, 0);
INSERT INTO Type (idx, typeModelIdx, shapeIdx) VALUES (100, 2, 1);
INSERT INTO Type (idx, typeModelIdx, shapeIdx) VALUES (101, 2, 2);
INSERT INTO Type (idx, typeModelIdx, shapeIdx) VALUES (102, 2, 3);
INSERT INTO Type (idx, typeModelIdx, shapeIdx) VALUES (103, 2, 4);
INSERT INTO Type (idx, typeModelIdx, shapeIdx) VALUES (104, 8, 1);
INSERT INTO Type (idx, typeModelIdx, shapeIdx) VALUES (105, 8, 2);
INSERT INTO Type (idx, typeModelIdx, shapeIdx) VALUES (106, 8, 3);
INSERT INTO Type (idx, typeModelIdx, shapeIdx) VALUES (107, 8, 4);
INSERT INTO Type (idx, typeModelIdx, shapeIdx) VALUES (108, 2, 5);
INSERT INTO Type (idx, typeModelIdx, shapeIdx) VALUES (109, 2, 6);
INSERT INTO Type (idx, typeModelIdx, shapeIdx) VALUES (110, 8, 5);
INSERT INTO Type (idx, typeModelIdx, shapeIdx) VALUES (111, 8, 6);
INSERT INTO Type (idx, typeModelIdx, shapeIdx) VALUES (150, 150, 0);
INSERT INTO Type (idx, typeModelIdx, shapeIdx) VALUES (151, 150, 1);
INSERT INTO Type (idx, typeModelIdx, shapeIdx) VALUES (152, 150, 2);
INSERT INTO Type (idx, typeModelIdx, shapeIdx) VALUES (153, 150, 3);
INSERT INTO Type (idx, typeModelIdx, shapeIdx) VALUES (154, 150, 4);
INSERT INTO Type (idx, typeModelIdx, shapeIdx) VALUES (155, 150, 5);
INSERT INTO Type (idx, typeModelIdx, shapeIdx) VALUES (156, 150, 6);
INSERT INTO Type (idx, typeModelIdx, shapeIdx) VALUES (170, 170, 0);
INSERT INTO Type (idx, typeModelIdx, shapeIdx) VALUES (171, 170, 1);
INSERT INTO Type (idx, typeModelIdx, shapeIdx) VALUES (172, 170, 2);
INSERT INTO Type (idx, typeModelIdx, shapeIdx) VALUES (173, 170, 3);
INSERT INTO Type (idx, typeModelIdx, shapeIdx) VALUES (174, 170, 4);
INSERT INTO Type (idx, typeModelIdx, shapeIdx) VALUES (175, 170, 5);
INSERT INTO Type (idx, typeModelIdx, shapeIdx) VALUES (176, 170, 6);
INSERT INTO Type (idx, typeModelIdx, shapeIdx) VALUES (180, 180, 0);
INSERT INTO Type (idx, typeModelIdx, shapeIdx) VALUES (181, 180, 1);
INSERT INTO Type (idx, typeModelIdx, shapeIdx) VALUES (182, 180, 2);
INSERT INTO Type (idx, typeModelIdx, shapeIdx) VALUES (183, 180, 3);
INSERT INTO Type (idx, typeModelIdx, shapeIdx) VALUES (184, 180, 4);
INSERT INTO Type (idx, typeModelIdx, shapeIdx) VALUES (185, 4, 1);
INSERT INTO Type (idx, typeModelIdx, shapeIdx) VALUES (186, 4, 2);
INSERT INTO Type (idx, typeModelIdx, shapeIdx) VALUES (187, 4, 3);
INSERT INTO Type (idx, typeModelIdx, shapeIdx) VALUES (188, 4, 4);
INSERT INTO Type (idx, typeModelIdx, shapeIdx) VALUES (190, 190, 0);
INSERT INTO Type (idx, typeModelIdx, shapeIdx) VALUES (191, 190, 1);
INSERT INTO Type (idx, typeModelIdx, shapeIdx) VALUES (192, 190, 2);
INSERT INTO Type (idx, typeModelIdx, shapeIdx) VALUES (193, 190, 3);
INSERT INTO Type (idx, typeModelIdx, shapeIdx) VALUES (194, 190, 4);
INSERT INTO Type (idx, typeModelIdx, shapeIdx) VALUES (195, 180, 5);
INSERT INTO Type (idx, typeModelIdx, shapeIdx) VALUES (196, 180, 6);
INSERT INTO Type (idx, typeModelIdx, shapeIdx) VALUES (197, 4, 5);
INSERT INTO Type (idx, typeModelIdx, shapeIdx) VALUES (198, 4, 6);
INSERT INTO Type (idx, typeModelIdx, shapeIdx) VALUES (199, 190, 5);
INSERT INTO Type (idx, typeModelIdx, shapeIdx) VALUES (200, 190, 6);
INSERT INTO Type (idx, typeModelIdx, shapeIdx) VALUES (510, 510, 0);
INSERT INTO Type (idx, typeModelIdx, shapeIdx) VALUES (511, 510, 1);
INSERT INTO Type (idx, typeModelIdx, shapeIdx) VALUES (512, 510, 2);
INSERT INTO Type (idx, typeModelIdx, shapeIdx) VALUES (513, 510, 3);
INSERT INTO Type (idx, typeModelIdx, shapeIdx) VALUES (514, 510, 4);
INSERT INTO Type (idx, typeModelIdx, shapeIdx) VALUES (515, 510, 5);
INSERT INTO Type (idx, typeModelIdx, shapeIdx) VALUES (516, 510, 6);
INSERT INTO Type (idx, typeModelIdx, shapeIdx) VALUES (520, 520, 0);
INSERT INTO Type (idx, typeModelIdx, shapeIdx) VALUES (521, 520, 1);
INSERT INTO Type (idx, typeModelIdx, shapeIdx) VALUES (522, 520, 2);
INSERT INTO Type (idx, typeModelIdx, shapeIdx) VALUES (523, 520, 3);
INSERT INTO Type (idx, typeModelIdx, shapeIdx) VALUES (524, 520, 4);
INSERT INTO Type (idx, typeModelIdx, shapeIdx) VALUES (525, 520, 5);
INSERT INTO Type (idx, typeModelIdx, shapeIdx) VALUES (526, 520, 6);

-- Table: TypeModel
CREATE TABLE TypeModel (
    idx                INTEGER,
    name               TEXT,
    densityMod         REAL,
    durabilityMod      REAL,
    materialCostMod    REAL,
    mechanics          REAL,
    engineers          REAL,
    process            INTEGER,
    hasVolume          INTEGER,
    collisionReduction REAL,
    comment            TEXT,
    PRIMARY KEY (
        idx
    )
);

INSERT INTO TypeModel (idx, name, densityMod, durabilityMod, materialCostMod, mechanics, engineers, process, hasVolume, collisionReduction, comment) VALUES (1, 'Hull', 1.0, 1.0, 1.0, 0.005, 0.0, 1, 1, 0.1, NULL);
INSERT INTO TypeModel (idx, name, densityMod, durabilityMod, materialCostMod, mechanics, engineers, process, hasVolume, collisionReduction, comment) VALUES (2, 'Blank Hull', 1.0, 1.0, 1.0, 0.005, 0.0, 1, 1, 0.1, NULL);
INSERT INTO TypeModel (idx, name, densityMod, durabilityMod, materialCostMod, mechanics, engineers, process, hasVolume, collisionReduction, comment) VALUES (3, 'Engine', 0.5, 0.5, 2.5, 0.01, 0.0333333333, 1, 1, 0.0, 'Acceleration = vol * meta1 / shipMass || MaxSpeed ~ 292291200 + (74.71271 - 292291200)/(pow(1+pow(totalEnginesVol/4.985828;1,002258);0,0000005119675)) || MaxBoostSpeed = MaxSpeed * 5 ');
INSERT INTO TypeModel (idx, name, densityMod, durabilityMod, materialCostMod, mechanics, engineers, process, hasVolume, collisionReduction, comment) VALUES (4, 'Stone', 4.0, 1.0, 0.003, 0.0, 0.0, 0, 1, 0.35, NULL);
INSERT INTO TypeModel (idx, name, densityMod, durabilityMod, materialCostMod, mechanics, engineers, process, hasVolume, collisionReduction, comment) VALUES (5, 'Cargo Bay', 0.3333333333, 1.0, 2.5, 0.005, 0.0, 1, 1, 0.1, 'CargoHold = meta1 * (lengthX-meta2) * (lengthY-meta2) * (lengthZ-meta2)  || final total is rounded to units');
INSERT INTO TypeModel (idx, name, densityMod, durabilityMod, materialCostMod, mechanics, engineers, process, hasVolume, collisionReduction, comment) VALUES (6, 'Crew Quarters', 0.6666666666, 1.0, 2.25, 0.005, 0.0, 1, 1, 0.1, 'CrewCap = vol * meta1 OR vol * 20/19  || final total is truncated');
INSERT INTO TypeModel (idx, name, densityMod, durabilityMod, materialCostMod, mechanics, engineers, process, hasVolume, collisionReduction, comment) VALUES (7, 'Thruster', 0.5, 0.125, 1.5, 0.01, 0.02, 1, 1, 0.1, 'Acceleration = vol * meta1 / shipMass || Doesn''t provides forward (+z) acceleration');
INSERT INTO TypeModel (idx, name, densityMod, durabilityMod, materialCostMod, mechanics, engineers, process, hasVolume, collisionReduction, comment) VALUES (8, 'Armor', 1.6666666666, 3.75, 1.6, 0.0001666666, 0.0, 1, 1, 0.75, NULL);
INSERT INTO TypeModel (idx, name, densityMod, durabilityMod, materialCostMod, mechanics, engineers, process, hasVolume, collisionReduction, comment) VALUES (9, 'Framework', 0.0006666666, 0.00125, 0.150000006, 0.0000666666, 0.0, 0, 1, 0.0, NULL);
INSERT INTO TypeModel (idx, name, densityMod, durabilityMod, materialCostMod, mechanics, engineers, process, hasVolume, collisionReduction, comment) VALUES (10, 'Hangar', 0.0666666666, 0.15, 4.5, 0.002, 0.0, 1, 1, 0.1, 'MaxFighters = meta1 * (lengthX-meta2) * (lengthY-meta2) * (lengthZ-meta2) || MinFighters = MaxFighters / 8 ||  final totals are truncated');
INSERT INTO TypeModel (idx, name, densityMod, durabilityMod, materialCostMod, mechanics, engineers, process, hasVolume, collisionReduction, comment) VALUES (11, 'Dock', 0.3333333333, 0.5, 4.5, 0.005, 0.0, 1, 1, 0.1, NULL);
INSERT INTO TypeModel (idx, name, densityMod, durabilityMod, materialCostMod, mechanics, engineers, process, hasVolume, collisionReduction, comment) VALUES (12, 'Turret Rotation Lock', 1.0, 0.5, 2.0, 0.005, 0.0, 1, 1, 0.1, NULL);
INSERT INTO TypeModel (idx, name, densityMod, durabilityMod, materialCostMod, mechanics, engineers, process, hasVolume, collisionReduction, comment) VALUES (13, 'Directional Thruster', 0.5, 0.125, 1.5, 0.01, 0.02, 1, 1, 0.0, 'Acceleration = vol * meta1 / shipMass || Doesn''t provides forward (+z) acceleration');
INSERT INTO TypeModel (idx, name, densityMod, durabilityMod, materialCostMod, mechanics, engineers, process, hasVolume, collisionReduction, comment) VALUES (14, 'Gyro Array', 0.1666666666, 0.5, 5.0, 0.01, 0.0, 1, 1, 0.1, NULL);
INSERT INTO TypeModel (idx, name, densityMod, durabilityMod, materialCostMod, mechanics, engineers, process, hasVolume, collisionReduction, comment) VALUES (15, 'Inertia Dampener', 0.5, 0.125, 30.0, 0.01, 0.0, 1, 1, 0.1, 'Deceleration = vol * meta1 / shipMass');
INSERT INTO TypeModel (idx, name, densityMod, durabilityMod, materialCostMod, mechanics, engineers, process, hasVolume, collisionReduction, comment) VALUES (16, 'Flight Recorder', 1.666666666, 2.0, 20.0, 0.0001666666, 0.0, 1, 1, 0.1, NULL);
INSERT INTO TypeModel (idx, name, densityMod, durabilityMod, materialCostMod, mechanics, engineers, process, hasVolume, collisionReduction, comment) VALUES (17, 'Assembly', 1.0, 0.5, 6.0, 0.013333333, 0.0, 1, 1, 0.1, 'Production = vol * meta1');
INSERT INTO TypeModel (idx, name, densityMod, durabilityMod, materialCostMod, mechanics, engineers, process, hasVolume, collisionReduction, comment) VALUES (18, 'Torpedo Launcher', 1.0, 1.0, 4.5, 0.01, 0.0, 1, 1, 0.1, NULL);
INSERT INTO TypeModel (idx, name, densityMod, durabilityMod, materialCostMod, mechanics, engineers, process, hasVolume, collisionReduction, comment) VALUES (19, 'Torpedo Storage', 1.0, 1.0, 4.5, 0.01, 0.0, 1, 1, 0.1, 'Storage = meta1 * (lengthX-meta2) * (lengthY-meta2) * (lengthZ-meta2) ||  final total is rounded to units');
INSERT INTO TypeModel (idx, name, densityMod, durabilityMod, materialCostMod, mechanics, engineers, process, hasVolume, collisionReduction, comment) VALUES (20, 'Turret Base', 1.3333333333, 2.0, 2.0, 0.0033333333, 0.0, 1, 1, 0.25, NULL);
INSERT INTO TypeModel (idx, name, densityMod, durabilityMod, materialCostMod, mechanics, engineers, process, hasVolume, collisionReduction, comment) VALUES (50, 'Shield Generator', 1.3333333333, 0.125, 20.0, 0.01, 0.0, 1, 1, 0.05, 'ShieldGen = vol * meta1 OR vol * 70 * pow( 1,5 ; matIndex ) || ShieldRegeneration = shield / 180');
INSERT INTO TypeModel (idx, name, densityMod, durabilityMod, materialCostMod, mechanics, engineers, process, hasVolume, collisionReduction, comment) VALUES (51, 'Energy Container', 1.0, 0.25, 3.5, 0.01, 0.0, 1, 1, 0.05, 'EnergyCap = vol * meta1');
INSERT INTO TypeModel (idx, name, densityMod, durabilityMod, materialCostMod, mechanics, engineers, process, hasVolume, collisionReduction, comment) VALUES (52, 'Generator', 1.3333333333, 0.125, 10.0, 0.01, 0.0, 1, 1, 0.05, 'EnergyGen = vol * meta1 OR 1875000 * ( 40 + 3 matIndex + pow( matIndex ; 2 ) )');
INSERT INTO TypeModel (idx, name, densityMod, durabilityMod, materialCostMod, mechanics, engineers, process, hasVolume, collisionReduction, comment) VALUES (53, 'Integrity Field Generator', 1.3333333333, 0.125, 5.0, 0.01, 0.0, 1, 1, 0.05, 'Aura range in each axis = axisLength * meta1. The Aura range starts at the center of the block.');
INSERT INTO TypeModel (idx, name, densityMod, durabilityMod, materialCostMod, mechanics, engineers, process, hasVolume, collisionReduction, comment) VALUES (54, 'Computer Core', 1.3333333333, 0.125, 5.0, 0.002, 0.0, 1, 1, 0.05, NULL);
INSERT INTO TypeModel (idx, name, densityMod, durabilityMod, materialCostMod, mechanics, engineers, process, hasVolume, collisionReduction, comment) VALUES (55, 'Hyperspace Core', 1.3333333333, 0.125, 50.0, 0.01, 0.0, 1, 1, 0.05, 'Range = pow(vol;1/3) * meta1 || final total is rounded to decimals');
INSERT INTO TypeModel (idx, name, densityMod, durabilityMod, materialCostMod, mechanics, engineers, process, hasVolume, collisionReduction, comment) VALUES (56, 'Transporter', 1.0, 0.125, 50.0, 0.01, 0.0, 1, 1, 0.05, NULL);
INSERT INTO TypeModel (idx, name, densityMod, durabilityMod, materialCostMod, mechanics, engineers, process, hasVolume, collisionReduction, comment) VALUES (57, 'Academy', 1.3333333333, 0.75, 2.25, 0.01, 0.0, 1, 1, 0.1, 'AcademyCap = vol * meta1 OR vol * 20 / 190');
INSERT INTO TypeModel (idx, name, densityMod, durabilityMod, materialCostMod, mechanics, engineers, process, hasVolume, collisionReduction, comment) VALUES (58, 'Cloning Pods', 1.6666666666, 0.625, 20.0, 0.01, 0.0, 1, 1, 0.05, 'CloningCap = vol * meta1 OR vol * ( (20 / 19 -1)*10 ) ');
INSERT INTO TypeModel (idx, name, densityMod, durabilityMod, materialCostMod, mechanics, engineers, process, hasVolume, collisionReduction, comment) VALUES (60, 'Solar Panel', 0.625, 1.0, 0.2521, 0.005, 0.0, 1, 1, 0.05, 'EnergyGen = surfaceArea * meta1');
INSERT INTO TypeModel (idx, name, densityMod, durabilityMod, materialCostMod, mechanics, engineers, process, hasVolume, collisionReduction, comment) VALUES (61, 'Light', 0.3333333333, 0.0625, 1.0, 0.005, 0.0, 1, 1, 0.1, NULL);
INSERT INTO TypeModel (idx, name, densityMod, durabilityMod, materialCostMod, mechanics, engineers, process, hasVolume, collisionReduction, comment) VALUES (150, 'Glow', 0.3333333333, 0.25, 1.0, 0.0002666666, 0.0, 1, 1, 0.0, NULL);
INSERT INTO TypeModel (idx, name, densityMod, durabilityMod, materialCostMod, mechanics, engineers, process, hasVolume, collisionReduction, comment) VALUES (170, 'Glass', 0.3333333333, 0.25, 0.0000000001, 0.0, 0.0, 0, 1, 0.0, NULL);
INSERT INTO TypeModel (idx, name, densityMod, durabilityMod, materialCostMod, mechanics, engineers, process, hasVolume, collisionReduction, comment) VALUES (180, 'Reflector', 0.3333333333, 0.25, 0.0000000001, 0.0001333333, 0.0, 1, 1, 0.0, NULL);
INSERT INTO TypeModel (idx, name, densityMod, durabilityMod, materialCostMod, mechanics, engineers, process, hasVolume, collisionReduction, comment) VALUES (190, 'Hologram', 0.0, 0.0, 0.0000000001, 0.0, 0.0, 0, 0, 0.0, NULL);
INSERT INTO TypeModel (idx, name, densityMod, durabilityMod, materialCostMod, mechanics, engineers, process, hasVolume, collisionReduction, comment) VALUES (510, 'Rich Stone', 4.0, 1.5, 0.2, 0.0, 0.0, 0, 1, 0.35, NULL);
INSERT INTO TypeModel (idx, name, densityMod, durabilityMod, materialCostMod, mechanics, engineers, process, hasVolume, collisionReduction, comment) VALUES (520, 'Super Rich Stone', 8.3333333333, 8.75, 2.0, 0.005, 0.0, 0, 1, 0.35, NULL);

-- Table: TypeModelByMaterial
CREATE TABLE TypeModelByMaterial (
    typeModelIdx  INTEGER REFERENCES TypeModel (idx),
    materialIdx   INTEGER REFERENCES Material (idx),
    creditCostMod REAL,
    energyReq     REAL,
    PRIMARY KEY (
        typeModelIdx,
        materialIdx
    )
);

INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (1, 0, 1.0, 500000.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (2, 0, 1.0, 500000.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (3, 0, 2.5, 3818125.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (4, 0, 0.0, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (5, 0, 2.5, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (6, 0, 2.25, 5000000.);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (7, 0, 1.5, 4500000.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (8, 0, 0.3333333333, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (9, 0, 0.05, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (11, 0, 4.5, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (12, 0, 2.0, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (13, 0, 1.5, 3750000.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (14, 0, 5.0, 7316875.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (15, 0, 30.0, 54390000.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (16, 0, 20.0, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (17, 0, 50.0, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (18, 0, 4.5, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (19, 0, 4.5, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (20, 0, 2.0, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (60, 0, 4.771000128, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (61, 0, 1.0, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (150, 0, 1.0, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (170, 0, 1.0, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (190, 0, 0.0166666666, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (510, 0, 0.0, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (520, 0, 0.0, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (1, 1, 1.0, 500000.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (2, 1, 1.0, 500000.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (3, 1, 2.5, 3818125.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (4, 1, 0.0, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (5, 1, 2.5, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (6, 1, 2.25, 5000000.);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (7, 1, 1.5, 4295469.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (8, 1, 0.3333333333, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (9, 1, 0.05, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (11, 1, 4.5, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (12, 1, 2.0, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (13, 1, 1.5, 3579531.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (14, 1, 5.0, 7612813.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (16, 1, 20.0, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (17, 1, 50.0, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (18, 1, 4.5, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (19, 1, 4.5, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (20, 1, 2.0, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (51, 1, 3.5, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (52, 1, 40.0, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (53, 1, 21.66666667, 9380000.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (60, 1, 3.604333333, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (61, 1, 1.0, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (150, 1, 1.0, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (170, 1, 1.0, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (190, 1, 0.0166666666, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (510, 1, 0.0, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (520, 1, 0.0, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (1, 2, 1.0, 500000.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (2, 2, 1.0, 500000.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (3, 2, 2.5, 3600000.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (4, 2, 0.0, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (5, 2, 2.5, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (6, 2, 2.25, 5000000.);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (7, 2, 1.5, 4050000.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (9, 2, 0.05, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (11, 2, 4.5, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (12, 2, 2.0, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (13, 2, 1.5, 3375000.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (14, 2, 5.0, 8066719.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (16, 2, 20.0, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (17, 2, 50.0, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (18, 2, 4.5, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (19, 2, 4.5, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (20, 2, 2.0, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (50, 2, 52.09876325, 23437500.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (51, 2, 3.5, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (52, 2, 32.22222083, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (53, 2, 17.34567825, 9380000.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (55, 2, 99.38271203, 25000000.);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (60, 2, 2.740135717, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (61, 2, 1.0, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (150, 2, 1.0, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (170, 2, 1.0, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (180, 2, 1.0, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (190, 2, 0.0166666666, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (510, 2, 0.0, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (520, 2, 0.0, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (1, 3, 1.0, 500000.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (2, 3, 1.0, 500000.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (3, 3, 2.5, 3379375.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (4, 3, 0.0, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (5, 3, 2.5, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (6, 3, 2.25, 5000000.);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (7, 3, 1.5, 3801719.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (8, 3, 0.3333333333, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (9, 3, 0.05, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (10, 3, 4.5, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (11, 3, 4.5, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (12, 3, 2.0, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (13, 3, 1.5, 3168125.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (14, 3, 5.0, 8823750.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (16, 3, 20.0, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (17, 3, 50.0, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (18, 3, 4.5, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (19, 3, 4.5, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (20, 3, 2.0, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (50, 3, 43.77686129, 27187500.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (51, 3, 3.5, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (52, 3, 26.46090404, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (53, 3, 14.14494671, 9380000.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (54, 3, 41.47004759, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (55, 3, 86.57978637, 25000000.);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (57, 3, 1.647388703, 526320.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (60, 3, 2.099989348, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (61, 3, 1.0, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (150, 3, 1.0, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (170, 3, 1.0, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (180, 3, 1.0, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (190, 3, 0.0166666666, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (510, 3, 0.0, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (520, 3, 0.0, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (1, 4, 1.0, 500000.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (2, 4, 1.0, 500000.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (3, 4, 2.5, 3176406.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (4, 4, 0.0, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (5, 4, 2.5, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (6, 4, 2.25, 5000000.);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (7, 4, 1.5, 3573594.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (9, 4, 0.05, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (10, 4, 4.5, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (11, 4, 4.5, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (12, 4, 2.0, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (13, 4, 1.5, 2977969.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (14, 4, 5.0, 10058750.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (16, 4, 20.0, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (17, 4, 50.0, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (18, 4, 4.5, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (19, 4, 4.5, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (20, 4, 2.0, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (50, 4, 37.61248988, 31875000.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (51, 4, 3.5, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (52, 4, 22.19326232, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (53, 4, 11.77403465, 9380000.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (54, 4, 32.09613892, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (55, 4, 77.09613972, 25000000.);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (56, 4, 67.61249081, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (57, 4, 2.25, 526320.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (58, 4, 20.0, 7894700.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (60, 4, 1.6069069858, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (61, 4, 1.0, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (150, 4, 1.0, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (170, 4, 1.0, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (180, 4, 1.0, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (190, 4, 0.0166666666, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (510, 4, 0.0, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (520, 4, 0.0, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (1, 5, 1.0, 500000.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (2, 5, 1.0, 500000.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (3, 5, 2.5, 3000000.);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (4, 5, 0.0, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (5, 5, 2.5, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (6, 5, 2.25, 5000000.);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (7, 5, 1.5, 3375000.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (8, 5, 0.3333333333, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (9, 5, 0.05, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (10, 5, 4.5, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (11, 5, 4.5, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (12, 5, 2.0, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (13, 5, 1.5, 2812500.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (14, 5, 5.0, 12000000.);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (16, 5, 20.0, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (17, 5, 50.0, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (18, 5, 4.5, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (19, 5, 4.5, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (20, 5, 2.0, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (51, 5, 3.5, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (52, 5, 19.03204598, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (57, 5, 2.25, 526320.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (58, 5, 20.0, 7894700.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (60, 5, 1.274560672, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (61, 5, 1.0, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (150, 5, 1.0, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (170, 5, 1.0, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (180, 5, 1.0, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (190, 5, 0.0166666666, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (510, 5, 0.0, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (520, 5, 0.0, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (1, 6, 1.0, 500000.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (2, 6, 1.0, 500000.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (3, 6, 2.5, 2851094.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (4, 6, 0.0, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (5, 6, 2.5, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (6, 6, 2.25, 5000000.);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (7, 6, 1.5, 3207500.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (9, 6, 0.05, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (10, 6, 4.5, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (11, 6, 4.5, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (12, 6, 2.0, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (13, 6, 1.5, 2672813.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (14, 6, 5.0, 14968125.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (15, 6, 30.0, 71276250.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (16, 6, 20.0, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (17, 6, 50.0, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (18, 6, 4.5, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (19, 6, 4.5, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (20, 6, 2.0, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (50, 6, 29.66391705, 44062500.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (51, 6, 3.5, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (52, 6, 16.69040412, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (53, 6, 8.716891179, 9380000.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (54, 6, 19.86756471, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (55, 6, 64.86756467, 25000000.);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (56, 6, 59.66391702, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (57, 6, 2.25, 526320.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (58, 6, 20.0, 7894700.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (60, 6, 1.014378243, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (61, 6, 1.0, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (150, 6, 1.0, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (170, 6, 1.0, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (180, 6, 1.0, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (190, 6, 0.0166666666, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (510, 6, 0.0, 0.0);
INSERT INTO TypeModelByMaterial (typeModelIdx, materialIdx, creditCostMod, energyReq) VALUES (520, 6, 0.0, 0.0);

-- View: AllBlocksView
CREATE VIEW AllBlocksView AS
    SELECT Type.idx,
           TypeModel.name,
           Shape.name shape,
           Material.name AS material,
           round(Material.durability * TypeModel.durabilityMod * Shape.cuboidFilledIn, 4) durability,
           round(Material.density * TypeModel.densityMod * Shape.cuboidFilledIn, 4) AS density,
           round(Material.materialCost * TypeModel.materialCostMod * Shape.cuboidFilledIn, 2) AS materialCost,
           round(Material.creditCost * TypeModelByMaterial.creditCostMod * Shape.cuboidFilledIn, 2) AS creditCost,
           round(TypeModel.mechanics * Shape.cuboidFilledIn, 3) AS mechanics,
           round(TypeModel.engineers * Shape.cuboidFilledIn, 3) AS engineers,
           TypeModel.process AS process,
           round(TypeModel.hasVolume * Shape.cuboidFilledIn, 3) AS volume
      FROM TypeModel
           JOIN
           Type ON TypeModel.idx = Type.typeModelIdx
           JOIN
           TypeModelByMaterial ON TypeModel.idx = TypeModelByMaterial.typeModelIdx
           JOIN
           Shape ON Type.shapeIdx = Shape.idx
           JOIN
           Material ON TypeModelByMaterial.materialIdx = Material.idx
     ORDER BY TypeModel.name,
              Material.idx,
              Shape.name;


-- View: EffectsView
CREATE VIEW EffectsView AS
    SELECT tm.name,
           m.name AS material,
           efs.n,
           efs.value
      FROM Effect efs
           LEFT JOIN
           TypeModel tm ON efs.typeModelIdx = tm.idx
           LEFT JOIN
           Material m ON efs.materialIdx = m.idx
     ORDER BY tm.name,
              n,
              m.idx;


-- View: TypeModelByMaterialView
CREATE VIEW TypeModelByMaterialView AS
    SELECT tm.name,
           m.name AS material,
           round(tbm.creditCostMod, 3) AS credtiCostMod,
           energyReq,
           NULL,
           NULL
      FROM TypeModelByMaterial tbm
           LEFT JOIN
           TypeModel tm ON tbm.typeModelIdx = tm.idx
           LEFT JOIN
           Material m ON tbm.materialIdx = m.idx
     ORDER BY tm.name,
              m.idx;


-- View: TypeModelView
CREATE VIEW TypeModelView AS
    SELECT TypeModel.idx,
           TypeModel.name,
           Material.name AS material,
           round(Material.durability * TypeModel.durabilityMod, 4) durability,
           round(Material.density * TypeModel.densityMod, 4) AS density,
           round( (Material.durability * TypeModel.durabilityMod) / (Material.density * TypeModel.densityMod), 4) AS [dur/dens],
           round(Material.materialCost * TypeModel.materialCostMod, 2) AS materialCost,
           round(Material.creditCost * TypeModelByMaterial.creditCostMod, 2) AS creditCost,
           round(TypeModel.mechanics, 3) AS mechanics,
           round(TypeModel.engineers, 3) AS engineers,
           round(TypeModel.process, 3) AS process,
           round(TypeModel.hasVolume, 3) AS hasVolume
      FROM TypeModel
           JOIN
           Type ON TypeModel.idx = Type.typeModelIdx
           JOIN
           TypeModelByMaterial ON TypeModel.idx = TypeModelByMaterial.typeModelIdx
           JOIN
           Shape ON Type.shapeIdx = Shape.idx
           JOIN
           Material ON TypeModelByMaterial.materialIdx = Material.idx
     WHERE Shape.idx = 0
     ORDER BY TypeModel.name,
              Material.idx;


-- View: TypeView
CREATE VIEW TypeView AS
    SELECT t.idx,
           tm.name,
           s.name AS shape
      FROM Type t
           LEFT JOIN
           TypeModel tm ON t.typeModelIdx = tm.idx
           LEFT JOIN
           Shape s ON t.shapeIdx = s.idx
     ORDER BY t.idx;


COMMIT TRANSACTION;
PRAGMA foreign_keys = on;
