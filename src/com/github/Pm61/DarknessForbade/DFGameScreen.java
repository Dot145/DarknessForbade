package com.github.Pm61.DarknessForbade;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class DFGameScreen implements Screen {
	final DFGame game;
	
	public static final int SIZE_X = DFStarter.SIZE_X;
	public static final int SIZE_Y = DFStarter.SIZE_Y;
	public static final int WORLD_X_ORIGIN = (int) (-SIZE_X*0.5);
	public static final int WORLD_Y_ORIGIN = (int) (-SIZE_Y*0.5);
	OrthographicCamera camera;
	SpriteBatch batch;
	Texture playerImage;
	Sprite backgroundImage, torchFilter;
	
	Player player;
	BitmapFont font;
	
	public DFGameScreen(final DFGame game){
		this.game = game;
		
		Texture.setEnforcePotImages(false);
		
		batch = new SpriteBatch();
		font = new BitmapFont();
		backgroundImage = new Sprite(new Texture(Gdx.files.internal("media/textures/background.bmp")));
		playerImage = new Texture(Gdx.files.internal("media/textures/playerImage.png"));
		torchFilter = new Sprite(new Texture(Gdx.files.internal("media/textures/torchFilter.png")));
		
		backgroundImage.setOrigin(0,0);
		backgroundImage.setPosition(WORLD_X_ORIGIN, WORLD_Y_ORIGIN);
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, SIZE_X, SIZE_Y);
		
		//create the player.  Set player to center of screen (regardless of save data). 
		player = new Player();
		player.setX(SIZE_X/2);
		player.setY(SIZE_Y/2);
		
	}
	@Override
	public void dispose() {
		batch.dispose();
		playerImage.dispose();
		font.dispose();

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(256, 256, 256, 256);
	    Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		backgroundImage.draw(batch);
		batch.draw(playerImage, (float) player.getX(), (float) player.getY());
		torchFilter.draw(batch);
		batch.end();
		if(Gdx.input.isKeyPressed(Keys.LEFT) && player.getX()>WORLD_X_ORIGIN+(SIZE_X/2)){
			player.setX( player.getX() - (player.getVelocity() * Gdx.graphics.getDeltaTime()));
			camera.translate((float) -player.getVelocity()*Gdx.graphics.getDeltaTime(), 0);
			torchFilter.translate((float) -player.getVelocity()*Gdx.graphics.getDeltaTime(), 0);
			camera.update();
		}
		if(Gdx.input.isKeyPressed(Keys.UP) && player.getY()<WORLD_Y_ORIGIN+backgroundImage.getHeight()-(SIZE_Y/2)){
			player.setY( player.getY() + (player.getVelocity() * Gdx.graphics.getDeltaTime()));
			camera.translate(0,(float) player.getVelocity()*Gdx.graphics.getDeltaTime());
			torchFilter.translate(0,(float) player.getVelocity()*Gdx.graphics.getDeltaTime());
			camera.update();
		}
		if(Gdx.input.isKeyPressed(Keys.RIGHT)&& player.getX()<WORLD_X_ORIGIN+backgroundImage.getWidth()-(SIZE_X/2)){
			player.setX( player.getX() + (player.getVelocity() * Gdx.graphics.getDeltaTime()));
			camera.translate((float) player.getVelocity()*Gdx.graphics.getDeltaTime(), 0);
			torchFilter.translate((float) player.getVelocity()*Gdx.graphics.getDeltaTime(), 0);
			camera.update();
		}
		if(Gdx.input.isKeyPressed(Keys.DOWN) && player.getY()>WORLD_Y_ORIGIN+(SIZE_Y/2)){
			player.setY( player.getY() - (player.getVelocity() * Gdx.graphics.getDeltaTime()));
			camera.translate(0,(float) -player.getVelocity()*Gdx.graphics.getDeltaTime());
			torchFilter.translate(0,(float) -player.getVelocity()*Gdx.graphics.getDeltaTime());
			camera.update();
		}
	}

	@Override
	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

}
