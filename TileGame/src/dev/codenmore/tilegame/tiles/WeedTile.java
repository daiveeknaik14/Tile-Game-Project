package dev.codenmore.tilegame.tiles;

import dev.codenmore.tilegame.gfx.Assets;

public class WeedTile extends Tile {

	public WeedTile(int id) {
		super(Assets.weed, id);
	}
	public boolean isSolid() {
		return true;
	}

}
