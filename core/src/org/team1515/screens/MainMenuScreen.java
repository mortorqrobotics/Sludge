package org.team1515.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import org.team1515.entities.ButtonCustom;
import org.team1515.sludge.Main;

/**
 * Created by azadeh2 on 2/6/2015.
 */
public class MainMenuScreen implements Screen {

    Main game;

    String title;
    BitmapFont font;
    Sprite background;
    ButtonCustom playButton;
    ButtonCustom storeButton;
    Texture titleImg;






    public MainMenuScreen(Main game) {
        this.game = game;
        title = "Sludge";
        font = new BitmapFont();
        playButton = new ButtonCustom(new Rectangle(Gdx.graphics.getWidth()/2 - 110, Gdx.graphics.getHeight()/2 - 32, 225, 75), new Texture("playButton1.png"));
        storeButton = new ButtonCustom(new Rectangle(Gdx.graphics.getWidth()/2 - 60, Gdx.graphics.getHeight()/2 - 125, 120, 50), new Texture("storeButton1.jpg"));
        background = new Sprite(new Texture("backSludge7.jpg"));
        titleImg = new Texture("sludgeTitle2.png");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if(playButton.isPressed) {
            game.setScreen(game.gameScreen);

        }
        if(Gdx.input.isKeyPressed(Input.Keys.NUM_1)) {
            game.setScreen(game.mainMenuScreen);

        }
        if(storeButton.isPressed) {
            game.setScreen(game.storeScreen);

        }

        Gdx.gl.glClearColor(.1f, .9f, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        game.batch.draw(background.getTexture(), -10, -10, Gdx.graphics.getWidth()+20, Gdx.graphics.getHeight()+20);
        game.batch.draw(titleImg, Gdx.graphics.getWidth()/2 - 150, Gdx.graphics.getHeight()-110, 300, 70);


        storeButton.render(game.batch);
        playButton.render(game.batch);
        //font.draw(game.batch, title, Gdx.graphics.getWidth()/2 - 110, Gdx.graphics.getHeight()-30);




        game.batch.end();
        font.setScale(5);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
