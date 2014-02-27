package com.github.Pm61.DarknessForbade;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;


public class DFGameScreen implements Screen {
	final DFGame game;
	
	public static final int SIZE_X = DFStarter.SIZE_X;
	public static final int SIZE_Y = DFStarter.SIZE_Y;
	public static final int WORLD_SIZE_X = DFStarter.WORLD_SIZE_X;
	public static final int WORLD_SIZE_Y = DFStarter.WORLD_SIZE_Y;
	float unitScale = 1f;
	OrthogonalTiledMapRenderer renderer;
	OrthographicCamera camera;
	SpriteBatch batch;
	Texture playerImage, skeletonImage;
	Sprite torchFilter;
	TiledMap map;
	TiledMapTileLayer baseLayer, pathLayer, collisionLayer;
	
	Player player;
	
	BitmapFont font;
	
	public DFGameScreen(final DFGame game){
		this.game = game;
		
		Texture.setEnforcePotImages(false);
		
		batch = new SpriteBatch();
		font = new BitmapFont();

		playerImage = new Texture(Gdx.files.internal("media/textures/playerImage.png"));
		torchFilter = new Sprite(new Texture(Gdx.files.internal("media/textures/torchFilter.png")));
		
		torchFilter.setOrigin(torchFilter.getWidth(),torchFilter.getHeight());
		
		map = new TmxMapLoader().load("media/maps/map.tmx");
		
		renderer = new OrthogonalTiledMapRenderer(map,unitScale);
		baseLayer = (TiledMapTileLayer)map.getLayers().get(0);
		pathLayer = (TiledMapTileLayer)map.getLayers().get(1);
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, SIZE_X, SIZE_Y);
		camera.position.x = WORLD_SIZE_X/2;
		camera.position.y = WORLD_SIZE_Y/2;
		
		//create the player.  Set player to center of screen (regardless of save data). 
		player = new Player();
		player.setX(WORLD_SIZE_X/2);
		player.setY(WORLD_SIZE_Y/2);
		
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
		torchFilter.setX((float) (player.getX()-SIZE_X*1.30));
		torchFilter.setY((float) (player.getY()-SIZE_Y*1.30));
		renderer.setView(camera);
		renderer.render();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(playerImage, (float) player.getX(), (float) player.getY());
		if(player.isTorch()==true) torchFilter.draw(batch);
		batch.end();
		player.setXvelocity(0);
		player.setYvelocity(0);
		if(Gdx.input.isKeyPressed(Keys.A) && !Gdx.input.isKeyPressed(Keys.D) && player.getX()>0){
			player.setXvelocity(-player.getSpeed());
			player.setX( player.getX() + (player.getXvelocity() * Gdx.graphics.getDeltaTime()));
		}
		if(Gdx.input.isKeyPressed(Keys.W) && !Gdx.input.isKeyPressed(Keys.S) && player.getY()<WORLD_SIZE_Y-playerImage.getHeight()){
			player.setYvelocity(player.getSpeed());
			player.setY( player.getY() + (player.getYvelocity() * Gdx.graphics.getDeltaTime()));
		}
		if(Gdx.input.isKeyPressed(Keys.D) && !Gdx.input.isKeyPressed(Keys.A) && player.getX()<WORLD_SIZE_X-playerImage.getWidth()){
			player.setXvelocity(player.getSpeed());
			player.setX( player.getX() + (player.getXvelocity() * Gdx.graphics.getDeltaTime()));
		}
		if(Gdx.input.isKeyPressed(Keys.S) && !Gdx.input.isKeyPressed(Keys.W) && player.getY()>0){
			player.setYvelocity(-player.getSpeed());
			player.setY( player.getY() + (player.getYvelocity() * Gdx.graphics.getDeltaTime()));
		}
		if(Gdx.input.justTouched()){
			player.setTorch(!player.isTorch());
		}
		if(player.getX()>(SIZE_X/2) && player.getX()<WORLD_SIZE_X-(SIZE_X/2)){
			camera.translate((float) player.getXvelocity()*Gdx.graphics.getDeltaTime(),0);
		}
		if(player.getY()>(SIZE_Y/2) && player.getY()<WORLD_SIZE_Y-(SIZE_Y/2)){
			camera.translate(0,(float) player.getYvelocity()*Gdx.graphics.getDeltaTime());
		}
		camera.update();
		
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
