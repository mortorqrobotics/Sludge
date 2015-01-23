package org.team1515.sludge;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.team1515.screens.GameScreen;
import org.team1515.screens.MainMenuScreen;
import org.team1515.screens.StoreScreen;


public class Main extends Game {
	SpriteBatch batch;

	GameScreen gameScreen;
	MainMenuScreen mainMenuScreen;
	StoreScreen storeScreen;

	@Override

	public void create() {
		batch = new SpriteBatch();

		gameScreen = new GameScreen();
		mainMenuScreen = new MainMenuScreen();
		storeScreen = new StoreScreen();

		setScreen(gameScreen);
	}

	@Override
	public void render() {
		super.render();
	}
}
