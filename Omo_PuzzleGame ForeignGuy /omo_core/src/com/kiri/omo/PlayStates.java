package com.kiri.omo;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.kiri.omo.states.GameStatesManger;
import com.kiri.omo.states.State;
import com.kiri.omo.ui.Tile;

public class PlayStates extends State {

	private Tile[][] tiles;
	private int tileSize;
	private int boardOffset;
	 private final int MAX_FINGER = 2; 

	protected PlayStates(GameStatesManger gsm) {
		super(gsm);

		tiles = new Tile [4][4];
		tileSize = Omo.WIDTH / tiles[0].length;
		boardOffset = (Omo.HEIGHT - (tileSize * tiles.length) / 2);
		
		for (int row = 0; row < tiles.length; row++) {
			for (int col = 0; col <tiles[0].length ; col++) {
				tiles[row][col] = new Tile(
						col * tileSize,
						row * tileSize ,
						tileSize * tileSize,
						tileSize * tileSize
						);
				

			}

		}
	}

	@Override
	public void handleInput() {
		
		
		
	}

	@Override
	public void update(float dt) {
		handleInput();
		
	}

	@Override
	public void render(SpriteBatch sb) {
		sb.setProjectionMatrix(cam.combined);
		sb.begin();
		for (int row = 0; row < tiles.length; row++) {
			for (int col = 0; col <tiles[0].length ; col++) {
				tiles[row][col].render(sb);

			}

		}

		sb.end();
	}
}
