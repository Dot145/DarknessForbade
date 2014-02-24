package com.github.Pm61.DarknessForbade;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class DFMainMenu implements Screen{
	final DFGame game;
	private Stage stage;

	public static final int SIZE_X = DFStarter.SIZE_X;
	public static final int SIZE_Y = DFStarter.SIZE_Y;
	
	OrthographicCamera camera;
	Skin skin;
	
	public DFMainMenu(final DFGame game){
		this.game = game;
		
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		
		skin = new Skin();
		
		Pixmap pixmap = new Pixmap(1, 1, Format.RGBA8888);
		pixmap.setColor(Color.WHITE);
		pixmap.fill();
		skin.add("white", new Texture(pixmap));
		
		skin.add("default", new BitmapFont());
		
		Table table = new Table();
		table.setFillParent(true);
		stage.addActor(table);
		
		TextButtonStyle style = new TextButtonStyle();
		style.up = skin.newDrawable("white", Color.DARK_GRAY);
		style.down = skin.newDrawable("white", Color.GRAY);
		style.font = skin.getFont("default");
		skin.add("default", style);
		
		final TextButton button = new TextButton("Start Game", skin);
		table.add(button);
		
		button.addListener(new ChangeListener() {
			@Override
			public void changed (ChangeEvent event, Actor actor) {
				game.setScreen(new DFGameScreen(game));
			}
			
			});
		table.add(new Image(skin.newDrawable("white"))).size(64);
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false,SIZE_X,SIZE_Y);
	}
	@Override
	public void dispose() {
		stage.dispose();
		skin.dispose();
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

		Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
        
        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.end();
		
	}
	@Override
	public void resize(int width, int height) {
		stage.setViewport(width, height, true);
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
