package dev.codenmore.tilegame.tiles;

import dev.codenmore.tilegame.gfx.Assets;

public class WaterTile extends Tile{
	
	public WaterTile(int id) {
		super(Assets.water, id);
	}
	public boolean isSolid(){
		return true;
	}
}
