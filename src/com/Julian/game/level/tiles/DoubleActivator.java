package com.Julian.game.level.tiles;

import com.Julian.game.Game;

public class DoubleActivator extends BasicInteractiveTile {

	int levelIndexOfDoor;
	Tile[] oldTiles;
	Tile[] newTiles;
	int firstStateTileEnd;
	public static boolean currentState = false;

	public DoubleActivator(int id, int x, int y, int tileColor, int levelImageColor, int levelIndexOfDoor,
			Tile[] oldTiles, Tile[] newTiles, int firstStateTileEnd) {
		super(id, x, y, tileColor, levelImageColor);
		this.levelIndexOfDoor = levelIndexOfDoor;
		this.solid = true;
		this.oldTiles = oldTiles;
		this.newTiles = newTiles;
		this.firstStateTileEnd = firstStateTileEnd;
	}

	public void doAction() {
		if (currentState) {
			// This changes the tiles that should be changed into the new Tiles
			for (int i = 0; i < firstStateTileEnd; i += 1) {
				Game.levels.get(levelIndexOfDoor).alterTile(oldTiles[i].xLevel, oldTiles[i].yLevel - 1,
						Tile.DOOR_TOP_OPENED);
				Tile.DOOR_TOP_OPENED.setXLevel(oldTiles[i].xLevel);
				Tile.DOOR_TOP_OPENED.setYLevel(oldTiles[i].yLevel - 1);
				Game.levels.get(levelIndexOfDoor).alterTile(oldTiles[i].xLevel, oldTiles[i].yLevel, newTiles[i]);
				newTiles[i].setXLevel(oldTiles[i].xLevel);
				newTiles[i].setYLevel(oldTiles[i].yLevel);
			}
			// This changes the tiles that shouldn't be changed back into their old tiles
			for (int i = firstStateTileEnd; i < oldTiles.length; i += 1) {
				Game.levels.get(levelIndexOfDoor).alterTile(oldTiles[i].xLevel, oldTiles[i].yLevel - 1, Tile.DOOR_TOP);
				Game.levels.get(levelIndexOfDoor).alterTile(oldTiles[i].xLevel, oldTiles[i].yLevel, oldTiles[i]);
			}

		}
		if (!currentState) {
			// This changes the tiles that should be changed into the new Tiles
			for (int i = firstStateTileEnd; i < oldTiles.length; i += 1) {
				Game.levels.get(levelIndexOfDoor).alterTile(oldTiles[i].xLevel, oldTiles[i].yLevel - 1,
						Tile.DOOR_TOP_OPENED);
				Tile.DOOR_TOP_OPENED.setXLevel(oldTiles[i].xLevel);
				Tile.DOOR_TOP_OPENED.setYLevel(oldTiles[i].yLevel - 1);
				Game.levels.get(levelIndexOfDoor).alterTile(oldTiles[i].xLevel, oldTiles[i].yLevel, newTiles[i]);
				newTiles[i].setXLevel(oldTiles[i].xLevel);
				newTiles[i].setYLevel(oldTiles[i].yLevel);
			}
			// This changes the tiles that shouldn't be changed back into their old tiles
			for (int i = 0; i < firstStateTileEnd; i += 1) {
				Game.levels.get(levelIndexOfDoor).alterTile(oldTiles[i].xLevel, oldTiles[i].yLevel - 1, Tile.DOOR_TOP);
				Game.levels.get(levelIndexOfDoor).alterTile(oldTiles[i].xLevel, oldTiles[i].yLevel, oldTiles[i]);
			}
		}
	}

}
