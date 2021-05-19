package com.andrez.flappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import javax.xml.soap.Text;

public class Game extends ApplicationAdapter {

	private SpriteBatch batch;
	private Texture[] birds;
	private Texture background;
	private int moveX = 0;

	private float widthDevice;
	private float heightDevice;
	private float variation = 0;

	@Override
	public void create () {
		batch = new SpriteBatch();
		birds = new Texture[3];
		birds[0] = new Texture("passaro1.png");
		birds[1] = new Texture("passaro2.png");
		birds[2] = new Texture("passaro3.png");
		background = new Texture("fundo.png");

		widthDevice = Gdx.graphics.getWidth();
		heightDevice = Gdx.graphics.getHeight();
	}

	@Override
	public void render () {
		batch.begin();

		if(variation > 3) variation = 0;

		batch.draw(background, 0, 0, widthDevice, heightDevice);
		batch.draw(birds[(int) variation], moveX, heightDevice / 2);

		variation += Gdx.graphics.getDeltaTime() * 10;

		moveX++;
		batch.end();
	}
	
	@Override
	public void dispose () {
	}
}
