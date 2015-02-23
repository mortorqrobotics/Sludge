package org.team1515.sludge;



import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import org.team1515.entities.Bullet;
import org.team1515.entities.Direction;
import org.team1515.entities.Entity;
import org.team1515.screens.GameScreen;
import org.team1515.screens.MainMenuScreen;
import org.team1515.screens.StoreScreen;

import java.util.Random;


public class Main extends Game {


	public int totalMoney;
	public SpriteBatch batch;
	public GameScreen gameScreen;
	public MainMenuScreen mainMenuScreen;
	public StoreScreen storeScreen;

	@Override
	public void create () {
		batch = new SpriteBatch();
		gameScreen = new GameScreen(this);
		mainMenuScreen = new MainMenuScreen(this);
		storeScreen = new StoreScreen(this);
		setScreen(mainMenuScreen);
		totalMoney = 0;
	}

	@Override
	public void render () {
		super.render();
	}
}
