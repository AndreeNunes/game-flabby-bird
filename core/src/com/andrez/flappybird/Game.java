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

	private float gravity = 0;
	private float initialPositionBird = 0;

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

		initialPositionBird = heightDevice / 2;
	}

	@Override
	public void render () {
		batch.begin();

		if(variation > 3) variation = 0;

		if(Gdx.input.justTouched()){
			gravity = -20;
		}

		if(initialPositionBird >= 0 || gravity < 0) initialPositionBird = initialPositionBird - gravity;

		batch.draw(background, 0, 0, widthDevice, heightDevice);
		batch.draw(birds[(int) variation], moveX, initialPositionBird);

		variation += Gdx.graphics.getDeltaTime() * 10;

		gravity++;
		moveX++;
		batch.end();
	}
	
	@Override
	public void dispose () {
	}
}
