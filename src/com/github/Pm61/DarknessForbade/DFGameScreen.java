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
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;


public class DFGameScreen implements Screen {
	final DFGame game;
	
	public static final int SIZE_X = DFStarter.SIZE_X;
	public static final int SIZE_Y = DFStarter.SIZE_Y;
	public static final int WORLD_X_ORIGIN = (int) (-SIZE_X*0.5);
	public static final int WORLD_Y_ORIGIN = (int) (-SIZE_Y*0.5);
	OrthographicCamera camera;
	SpriteBatch batch;
	Texture playerImage, skeletonImage;
	Sprite backgroundImage, torchFilter;
	
	Player player;
	Rectangle playerHitbox;
	
	BitmapFont font;
	
	public DFGameScreen(final DFGame game){
		this.game = game;
		
		Texture.setEnforcePotImages(false);
		
		batch = new SpriteBatch();
		font = new BitmapFont();
		backgroundImage = new Sprite(new Texture(Gdx.files.internal("media/textures/background.bmp")));
		playerImage = new Texture(Gdx.files.internal("media/textures/playerImage.png"));
		torchFilter = new Sprite(new Texture(Gdx.files.internal("media/textures/EncounterFilter.png")));
		
		backgroundImage.setOrigin(0,0);
		backgroundImage.setPosition(WORLD_X_ORIGIN, WORLD_Y_ORIGIN);
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, SIZE_X, SIZE_Y);
		
		//create the player.  Set player to center of screen (regardless of save data). 
		player = new Player();
		player.setX(SIZE_X/2);
		player.setY(SIZE_Y/2);
		
		playerHitbox = new Rectangle();
		playerHitbox.x = (float) player.getX();
		playerHitbox.y = (float) player.getY();
		playerHitbox.width = playerImage.getWidth();
		playerHitbox.height = playerImage.getHeight();
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
		player.setXvelocity(0);
		player.setYvelocity(0);
		if(Gdx.input.isKeyPressed(Keys.A) && !Gdx.input.isKeyPressed(Keys.D) && player.getX()>WORLD_X_ORIGIN){
			player.setXvelocity(-player.getVelocity());
			player.setX( player.getX() + (player.getXvelocity() * Gdx.graphics.getDeltaTime()));
		}
		if(Gdx.input.isKeyPressed(Keys.W) && !Gdx.input.isKeyPressed(Keys.S) && player.getY()<WORLD_Y_ORIGIN+backgroundImage.getHeight()-playerImage.getHeight()){
			player.setYvelocity(player.getVelocity());
			player.setY( player.getY() + (player.getYvelocity() * Gdx.graphics.getDeltaTime()));
		}
		if(Gdx.input.isKeyPressed(Keys.D) && !Gdx.input.isKeyPressed(Keys.A) && player.getX()<WORLD_X_ORIGIN+backgroundImage.getWidth()-playerImage.getWidth()){
			player.setXvelocity(player.getVelocity());
			player.setX( player.getX() + (player.getXvelocity() * Gdx.graphics.getDeltaTime()));
		}
		if(Gdx.input.isKeyPressed(Keys.S) && !Gdx.input.isKeyPressed(Keys.W) && player.getY()>WORLD_Y_ORIGIN){
			player.setYvelocity(-player.getVelocity());
			player.setY( player.getY() + (player.getYvelocity() * Gdx.graphics.getDeltaTime()));
		}
		if(player.getX()>WORLD_X_ORIGIN+(SIZE_X/2) && player.getX()<WORLD_X_ORIGIN+backgroundImage.getWidth()-(SIZE_X/2)){
			camera.translate((float) player.getXvelocity()*Gdx.graphics.getDeltaTime(),0);
			torchFilter.translate((float) player.getXvelocity()*Gdx.graphics.getDeltaTime(),0);
		}
		if(player.getY()>WORLD_Y_ORIGIN+(SIZE_Y/2) && player.getY()<WORLD_Y_ORIGIN+backgroundImage.getHeight()-(SIZE_Y/2)){
			camera.translate(0,(float) player.getYvelocity()*Gdx.graphics.getDeltaTime());
			torchFilter.translate(0,(float) player.getYvelocity()*Gdx.graphics.getDeltaTime());
		}
		camera.update();
		playerHitbox.setX((float) player.getX());
		playerHitbox.setY((float) player.getY());
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
