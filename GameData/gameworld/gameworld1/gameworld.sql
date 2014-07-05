BEGIN TRANSACTION;
CREATE TABLE "terrain" (
	`x`	NUMERIC NOT NULL,
	`y`	NUMERIC NOT NULL,
	`heightmap`	TEXT NOT NULL,
	`alphamap`	TEXT NOT NULL,
	`x2`	NUMERIC,
	`y2`	NUMERIC,
	PRIMARY KEY(x,y)
);
INSERT INTO `terrain` VALUES(0,0,'assets/Textures/Terrain/splat/heightmap.png','assets/Textures/Terrain/splat/alphamap.png','-1','-1');
CREATE TABLE `custom_textures` (
	`textureID`	BLOB NOT NULL,
	`texturePath`	TEXT NOT NULL,
	PRIMARY KEY(textureID)
);
INSERT INTO `custom_textures` VALUES('de.gamebasis.gameworld.dirt','assets/Textures/Terrain/splat/dirt.jpg');
INSERT INTO `custom_textures` VALUES('de.gamebasis.gameworld.grass','assets/Textures/Terrain/splat/grass.jpg');
INSERT INTO `custom_textures` VALUES('de.gamebasis.gameworld.road','assets/Textures/Terrain/splat/road.jpg');
;
;
COMMIT;
