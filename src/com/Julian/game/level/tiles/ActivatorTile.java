package com.Julian.game.level.tiles;

import com.Julian.game.Game;

public class ActivatorTile extends BasicInteractiveTile {

	int levelIndexOfDoor;
	Tile oldTile;
	Tile newBottomTile;
	
	public ActivatorTile(int id, int x, int y, int tileColor, int levelImageColor, int levelIndexOfDoor, Tile oldTile, Tile newBottomTile) {
		super(id, x, y, tileColor, levelImageColor);
		this.solid = true;
		this.oldTile = oldTile;
		this.newBottomTile = newBottomTile;
		this.levelIndexOfDoor = levelIndexOfDoor;
	}

	public void doAction() {
		Game.levels.get(levelIndexOfDoor).alterTile(oldTile.xLevel, oldTile.yLevel - 1, Tile.DOOR_TOP_OPENED);
		Tile.DOOR_TOP_OPENED.setXLevel(oldTile.xLevel);
		Tile.DOOR_TOP_OPENED.setYLevel(oldTile.yLevel - 1);
		Game.levels.get(levelIndexOfDoor).alterTile(oldTile.xLevel, oldTile.yLevel, newBottomTile);
		newBottomTile.setXLevel(oldTile.xLevel);
		newBottomTile.setYLevel(oldTile.yLevel);
	}
	
}
