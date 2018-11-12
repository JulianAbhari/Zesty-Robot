
package com.Julian.game.level.tiles;

import com.Julian.game.gfx.Colors;
import com.Julian.game.gfx.Screen;
import com.Julian.game.level.Level;

public abstract class Tile {

	// 256 is the maximum amount of tiles that can be in the board
	public static final Tile[] tiles = new Tile[256];

	public static final Tile VOID = new BasicSolidTile(0, 0, 0, Colors.get(000, -1, -1, -1), 0xFF000000);
	public static final Tile STONE = new BasicSolidTile(1, 1, 0, Colors.get(-1, 222, -1, -1), 0xFF555555);
	public static final Tile GRASS = new BasicTile(2, 2, 0, Colors.get(-1, 131, 141, -1), 0xFF00FF00);
	public static final Tile WATER = new AnimatedTile(3, new int[][] { { 0, 5 }, { 1, 5 }, { 2, 5 }, { 1, 5 } },
			Colors.get(-1, 004, 115, -1), 0xFF0000FF, 1000);
	public static final Tile STONE_GROUND = new BasicTile(4, 1, 0, Colors.get(-1, 122, -1, -1), 0xFF335555);
	
	// Reusable Door tiles
	public static final Tile DOOR_TOP = new BasicSolidTile(5, 4, 0, Colors.get(222, 111, 333, 555), 0xFF313131);
	public static final Tile DOOR_TOP_OPENED = new BasicTile(6, 6, 0, Colors.get(222, 111, 333, 555), 0xFFB0B0B0);
	
	// Overworld Door and Activator Tiles
	public static final Tile OD_BOTTOM = new BasicSolidTile(7, 3, 0, Colors.get(222, 111, 333, 555), 0xFF2c2c2c);
	public static final Tile OD_BOTTOM_OPENED = new OpenedDoorTile(8, 5, 0, Colors.get(000, 111, 333, 555), 0xFFA0A0A0, "/Levels/Level_1.0.png");
	public static final Tile OD_ACTIVATOR = new ActivatorTile(9, 7, 0, Colors.get(000, 000, 333, 035), 0xFF2a7f9f, 0, OD_BOTTOM, OD_BOTTOM_OPENED);
	
	// Level 1
	public static final Tile L1_BOTTOM_1 = new BasicSolidTile(10, 3, 0, Colors.get(222, 111, 333, 555), 0xFF212121);
	public static final Tile L1_BOTTOM_OPENED_1 = new OpenedDoorTile(11, 5, 0, Colors.get(000, 111, 333, 555), 0xFFC0C0C0, "/Levels/Level_2.0.png");
	public static final Tile L1_ACTIVATOR_1 = new ActivatorTile(12, 7, 0, Colors.get(000, 000, 333, 035), 0xFF397b8c, 1, L1_BOTTOM_1, L1_BOTTOM_OPENED_1);
	public static final Tile L1_BOTTOM_2 = new BasicSolidTile(13, 3, 0, Colors.get(222, 111, 333, 555), 0xFF111111);
	public static final Tile L1_BOTTOM_OPENED_2 = new OpenedDoorTile(14, 5, 0, Colors.get(000, 111, 333, 555), 0xFF, "/Levels/Level_1.1.png");
	public static final Tile L1_ACTIVATOR_2 = new ActivatorTile(15, 7, 0, Colors.get(000, 000, 333, 035), 0xFF193461, 1, L1_BOTTOM_2, L1_BOTTOM_OPENED_2);  
	
	// Level 2
	public static final Tile SKULL = new BasicSolidTile(16, 8, 0, Colors.get(122, -1, -1, 555), 0xFFddd5e8);
	public static final Tile POLE_TOP = new BasicSolidTile(17, 9, 0, Colors.get(122, 111, 402, 455), 0xFF7830db);
	public static final Tile POLE_BOTTOM = new BasicSolidTile(18, 10, 0, Colors.get(122, 111, 402, 434), 0xFF4b3b61);
	public static final Tile NECK_SQUEEZER = new BasicSolidTile(19, 11, 0, Colors.get(122, 322, 444, 555), 0xFF634419);
	public static final Tile L2_BOTTOM_1 = new BasicSolidTile(20, 3, 0, Colors.get(222, 111, 333, 555), 0xFF454545);
	public static final Tile L2_BOTTOM_OPENED_1 = new OpenedDoorTile(21, 5, 0, Colors.get(000, 111, 333, 555), 0xFF, "/Levels/Level_2.2.png");
	public static final Tile L2_BOTTOM_2 = new BasicSolidTile(22, 3, 0, Colors.get(222, 111, 333, 555), 0xFF424242);
	public static final Tile L2_BOTTOM_OPENED_2 = new OpenedDoorTile(23, 5, 0, Colors.get(000, 111, 333, 555), 0xFF, "/Levels/Level_3.0.png");
	public static final Tile L2_BOTTOM_3 = new BasicSolidTile(24, 3, 0, Colors.get(222, 111, 333, 555), 0xFF404040);
	public static final Tile L2_BOTTOM_OPENED_3 = new OpenedDoorTile(25, 5, 0, Colors.get(000, 111, 333, 555), 0xFF, "/Levels/Level_2.1.png");
	public static final Tile L2_DOUBLE_ACTIVATOR_1 = new DoubleActivator(26, 7, 0, Colors.get(000, 000, 333, 312), 0xFF8e1616, 3, new Tile[] {L2_BOTTOM_1, L2_BOTTOM_3, L2_BOTTOM_2}, new Tile[] {L2_BOTTOM_OPENED_1, L2_BOTTOM_OPENED_3, L2_BOTTOM_OPENED_2}, 2);	
	public static final Tile DBLE_ACT_DEMO = new BasicTile(28, 7, 0, Colors.get(000, 000, 333, 312), 0xFF5f335a);
	
	// Level 3
	//public static final Tile L3_DOUBLE_ACTIVATOR_1 = new DoubleActivator(29, 7, 0, Colors.get(000, 000, 333, 312), 0xFF, 6, )
	
	protected byte id;
	// Collision detection
	protected boolean solid;
	// Light
	protected boolean emitter;
	// Interactive
	protected boolean interactive;
	// Trigger Tile
	protected boolean trigger;
	// This is the color ID for image to tile.
	private int levelImageColor;
	// This is the xCoordinate of the tile within the level
	protected int xLevel;
	// This is the yCoordinate of the tile within the level
	protected int yLevel;

	public Tile(int id, boolean isSolid, boolean isEmitter, boolean isInteractive, boolean isTrigger, int levelImageColor) {
		this.id = (byte) id;
		if (tiles[id] != null) {
			throw new RuntimeException("Duplicate tile id on " + id);
		}
		this.solid = isSolid;
		this.emitter = isEmitter;
		this.interactive = isInteractive;
		this.trigger = isTrigger;
		this.levelImageColor = levelImageColor;
		tiles[id] = this;
	}

	public byte getId() {
		return id;
	}

	public boolean isSolid() {
		return solid;
	}

	public boolean isEmitter() {
		return emitter;
	}

	public boolean isInteractive() {
		return interactive;
	}
	
	public boolean isTrigger() {
		return trigger;
	}

	public int getLevelImageColor() {
		return levelImageColor;
	}
	
	public int getXLevel() {
		return xLevel;
	}
	
	public void setXLevel(int xLevel) {
		this.xLevel = xLevel;
	}
	
	public int getYLevel() {
		return yLevel;
	}

	public void setYLevel(int yLevel) {
		this.yLevel = yLevel;
	}

	public abstract void tick();

	public abstract void render(Screen screen, Level level, int x, int y, boolean flipX, boolean flipY);

}