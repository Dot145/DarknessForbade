package com.github.Pm61.DarknessForbade;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class DFGame extends Game{
	SpriteBatch batch;
    BitmapFont font;
    Preferences userprefs;
	@Override
	public void create() {
    	userprefs = Gdx.app.getPreferences("User Preferences");
		batch = new SpriteBatch();
		font = new BitmapFont();
		font.setColor(Color.GRAY);
		this.setScreen(new DFMainMenu(this));	
	}
	
	@Override
	public void dispose() {
		batch.dispose();
        font.dispose();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

}
