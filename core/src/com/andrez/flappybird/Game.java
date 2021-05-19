package com.andrez.flappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import javax.xml.soap.Text;

public class Game extends ApplicationAdapter {

	private SpriteBatch batch;
	private Texture bird;
	private Texture background;
	private int moveX = 0;

	@Override
	public void create () {
		batch = new SpriteBatch();
		bird = new Texture("passaro1.png");
		background = new Texture("fundo.png");
	}

	@Override
	public void render () {
		batch.begin();

		batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batch.draw(bird, moveX, 500);
		moveX++;
		batch.end();
	}
	
	@Override
	public void dispose () {
	}
}
