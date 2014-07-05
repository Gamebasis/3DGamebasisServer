BEGIN TRANSACTION;
CREATE TABLE `gamepluginlist` (
	`comID`	BLOB NOT NULL,
	`isLoaded`	INTEGER NOT NULL,
	`configurationPath`	TEXT,
	PRIMARY KEY(comID)
);
CREATE TABLE `app_pluginlist` (
	`comID`	BLOB NOT NULL,
	`required`	INTEGER NOT NULL,
	PRIMARY KEY(comID)
);
CREATE TABLE `gameconnections` (
	`connID`	INTEGER NOT NULL,
	`playerID`	INTEGER NOT NULL,
	PRIMARY KEY(connID)
);
CREATE TABLE `gamesettings` (
	`gamesetting`	BLOB NOT NULL,
	`value`	TEXT,
	PRIMARY KEY(gamesetting)
);
INSERT INTO `gamesettings` VALUES('de.gamebasis.networking.maxClients','-1');
INSERT INTO `gamesettings` VALUES('de.gamebasis.gameworld.filePath','GameData/gameworld/gameworld1');
INSERT INTO `gamesettings` VALUES('de.gamebasis.terrain.allowHeightMapChanges','true');
CREATE TABLE `gamepermissionsystem` (
	`comID`	BLOB NOT NULL,
	`description`	TEXT,
	PRIMARY KEY(comID)
);
INSERT INTO `gamepermissionsystem` VALUES('de.gamebasis.terrain.canChangeHeightMap','Kann die Terrain HeightMap verändern, also das Terrain anheben und absenken.');
;
;
;
;
COMMIT;
