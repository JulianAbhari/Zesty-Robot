package com.Julian.game.level.tiles;

import com.Julian.game.Game;
import com.Julian.game.entities.Player;
import com.Julian.game.level.Level;

public class OpenedDoorTile extends BasicTriggerTile {

	String levelPath;

	public OpenedDoorTile(int id, int x, int y, int tileColor, int levelImageColor, String levelPath) {
		super(id, x, y, tileColor, levelImageColor);
		this.levelPath = levelPath;
	}

	public void doAction() {

		Game.levels.get(Game.currentLevelIndex).removeEntity(Game.player);

		boolean matchFound = false;

		for (int i = 0; i < Game.levels.size(); i += 1) {
			System.out.println(Game.levels.get(i).getLevelStringPath());
			// Instead of adding a level that already exists, this goes through the total
			// levels array to see if a level that is trying to be created already exists.
			// If a level already exists then the currentLevelIndex will be the index that
			// the match was found in.
			if (Game.levels.get(i).getLevelStringPath() == levelPath) {
				Game.currentLevelIndex = i;
				matchFound = true;
			}
		}

		if (!matchFound) {
			Game.currentLevelIndex = Game.levels.size();
			Game.levels.add(new Level(levelPath));
		}

		Game.player = new Player(Game.levels.get(Game.currentLevelIndex),
				(Game.levels.get(Game.currentLevelIndex).width / 2) * 8,
				(Game.levels.get(Game.currentLevelIndex).height - 2) * 8, Game.input);
		Game.levels.get(Game.currentLevelIndex).addEntity(Game.player);

	}

}
