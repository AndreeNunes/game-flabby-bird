package com.andrez.flappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.Random;

import javax.xml.soap.Text;

public class Game extends ApplicationAdapter {

	private SpriteBatch batch;
	private Texture[] birds;
	private Texture background;
	private Texture lowBarrel;
	private Texture upBarrel;
	private Texture gameOver;

	private ShapeRenderer shapeRenderer;
	private Circle circleBird;
	private Rectangle rectangleUp;
	private Rectangle rectangleDown;

	private float widthDevice;
	private float heightDevice;
	private float variation = 0;
	private float gravity = 0;
	private float initialPositionBird = 0;
	private float positionPipeHorizontal;
	private float positionPipeVertical;
	private float spaceBetweenPipes;
	private Random random;
	BitmapFont pointText;
	BitmapFont textRestart;
	BitmapFont textBestPoint;
	private int point = 0;
	private boolean passedPipe = false;

	private int stateGame = 0;

	@Override
	public void create () {
		startTexture();
		startObjects();
	}

	@Override
	public void render () {
		validateStateGame();
		validatePoint();
		paintTexture();
		detectarColisoes();
	}

	private void paintTexture(){
		batch.begin();

		batch.draw(background, 0, 0, widthDevice, heightDevice);
		batch.draw(birds[(int) variation], widthDevice / 5, initialPositionBird);
		batch.draw(lowBarrel, positionPipeHorizontal, (heightDevice / 2 - lowBarrel.getHeight() - spaceBetweenPipes / 2 + positionPipeVertical));
		batch.draw(upBarrel, positionPipeHorizontal, (heightDevice / 2  + spaceBetweenPipes / 2 + positionPipeVertical));
		pointText.draw(batch, String.valueOf(point), widthDevice / 2, heightDevice - 110);

		if(stateGame == 2){
			batch.draw(gameOver, widthDevice / 2 - gameOver.getWidth() / 2, heightDevice / 2);
			textRestart.draw(batch, "Toque para reiniciar", widthDevice / 2 - 140, heightDevice / 2 -  gameOver.getHeight() / 2);
			textBestPoint.draw(batch, "Seu record é: 0 pontos", widthDevice / 2 - 140, heightDevice / 2 -  gameOver.getHeight());
		}

		batch.end();
	}

	private void validateStateGame(){

		if(stateGame == 0){
			if(Gdx.input.justTouched()){
				gravity = -16;
				stateGame = 1;
			};

		} else if (stateGame == 1){
			if(Gdx.input.justTouched()) gravity = -16;

			positionPipeHorizontal -= Gdx.graphics.getDeltaTime() * 400;
			if(positionPipeHorizontal < -lowBarrel.getWidth()){
				positionPipeHorizontal = widthDevice;
				positionPipeVertical = random.nextInt(1200) - 400;
				passedPipe = false;
			}

			if(initialPositionBird >= 0 || gravity < 0) initialPositionBird = initialPositionBird - gravity;

			gravity++;

		} else if (stateGame == 2){

		}



	}

	private void detectarColisoes(){
		circleBird.set(widthDevice / 5 + birds[0].getWidth() / 2, initialPositionBird + birds[0].getHeight() / 2, birds[0].getWidth() / 2);
		rectangleDown.set(positionPipeHorizontal, (heightDevice / 2 - lowBarrel.getHeight() - spaceBetweenPipes / 2 + positionPipeVertical), lowBarrel.getWidth(), lowBarrel.getHeight());
		rectangleUp.set(positionPipeHorizontal, (heightDevice / 2  + spaceBetweenPipes / 2 + positionPipeVertical), upBarrel.getWidth(), upBarrel.getHeight());

		boolean collisionPipeUp = Intersector.overlaps(circleBird, rectangleUp);
		boolean collisionPipeDown = Intersector.overlaps(circleBird, rectangleDown);

		if(collisionPipeUp || collisionPipeDown){
			stateGame = 2;
		}

		/*shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		shapeRenderer.setColor(Color.RED);
		shapeRenderer.circle(widthDevice / 5 + birds[0].getWidth() / 2, initialPositionBird + birds[0].getHeight() / 2, birds[0].getWidth() / 2);


		// upBarrel, positionPipeHorizontal, (heightDevice / 2  + spaceBetweenPipes / 2 + positionPipeVertical)
		shapeRenderer.rect(positionPipeHorizontal, (heightDevice / 2 - lowBarrel.getHeight() - spaceBetweenPipes / 2 + positionPipeVertical), lowBarrel.getWidth(), lowBarrel.getHeight());

		shapeRenderer.rect(positionPipeHorizontal, (heightDevice / 2  + spaceBetweenPipes / 2 + positionPipeVertical), upBarrel.getWidth(), upBarrel.getHeight());

		shapeRenderer.end();*/
	}

	private void validatePoint(){
		if(positionPipeHorizontal < 90){
			if(!passedPipe){
				point++;
				passedPipe = true;
			}
		}

		variation += Gdx.graphics.getDeltaTime() * 10;

		if(variation > 3) variation = 0;
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

		gameOver = new Texture("game_over.png");
	}

	private void startObjects(){
		random = new Random();

		widthDevice = Gdx.graphics.getWidth();
		heightDevice = Gdx.graphics.getHeight();
		initialPositionBird = heightDevice / 2;
		positionPipeHorizontal = widthDevice;

		spaceBetweenPipes = 230;

		pointText = new BitmapFont();
		pointText.setColor(Color.WHITE);
		pointText.getData().setScale(10);

		shapeRenderer = new ShapeRenderer();
		circleBird = new Circle();
		rectangleUp = new Rectangle();
		rectangleDown = new Rectangle();

		textRestart = new BitmapFont();
		textRestart.setColor(Color.GREEN);
		textRestart.getData().setScale(2);

		textBestPoint = new BitmapFont();
		textBestPoint.setColor(Color.RED);
		textBestPoint.getData().setScale(2);
	}
	
	@Override
	public void dispose () {
	}
}
