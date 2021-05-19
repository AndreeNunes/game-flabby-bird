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
	private Texture lowBarrel;
	private Texture upBarrel;

	private float widthDevice;
	private float heightDevice;
	private float variation = 0;
	private float gravity = 0;
	private float initialPositionBird = 0;
	private float positionPipeHorizontal;
	private float spaceBetweenPipes;

	@Override
	public void create () {
		startTexture();
		startObjects();
	}

	@Override
	public void render () {
		validateStateGame();
		paintTexture();
	}

	private void paintTexture(){
		batch.begin();

		batch.draw(background, 0, 0, widthDevice, heightDevice);
		batch.draw(birds[(int) variation], 30, initialPositionBird);
		batch.draw(lowBarrel, positionPipeHorizontal - 100, (heightDevice / 2 - lowBarrel.getHeight() - spaceBetweenPipes / 2));
		batch.draw(upBarrel, positionPipeHorizontal - 100, (heightDevice / 2  + spaceBetweenPipes / 2));

		batch.end();
	}

	private void validateStateGame(){
		if(Gdx.input.justTouched()) gravity = -20;

		if(initialPositionBird >= 0 || gravity < 0) initialPositionBird = initialPositionBird - gravity;

		variation += Gdx.graphics.getDeltaTime() * 10;

		if(variation > 3) variation = 0;

		gravity++;
	}

	private void startTexture(){
		batch = new SpriteBatch();

		birds = new Texture[3];
		birds[0] = new Texture("passaro1.png");
		birds[1] = new Texture("passaro2.png");
		birds[2] = new Texture("passaro3.png");
		background = new Texture("fundo.png");

		lowBarrel = new Texture("cano_baixo_maior.png");
		upBarrel = new Texture("cano_topo_maior.png");
	}

	private void startObjects(){
		widthDevice = Gdx.graphics.getWidth();
		heightDevice = Gdx.graphics.getHeight();
		initialPositionBird = heightDevice / 2;
		positionPipeHorizontal = widthDevice;

		spaceBetweenPipes = 400;
	}
	
	@Override
	public void dispose () {
	}
}
