package org.team1515.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import org.team1515.entities.Car;
import org.team1515.entities.Direction;
import org.team1515.entities.Entity;
import org.team1515.entities.Spider;

import java.util.Random;

/**
 * Created by azadeh2 on 1/17/2015.
 */
public class GameScreen implements Screen {
    SpriteBatch batch;
    Spider spider;
    Car car;
    OrthographicCamera camera;

    @Override
    public void show() {
        batch = new SpriteBatch();
        spider = new Spider(new Sprite(new Texture("spider.png")), 0.15f, new Vector2(2, 2), 3);
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        car = new Car(new Sprite(new Texture("car.png")), 0.15f, Direction.UP, new Sprite(new Texture("badlogic.jpg")), new Vector2(4, 4));
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //TODO: get position in center of screen more accurate
        camera.position.set(car.getCenter().x + Gdx.graphics.getWidth() / 6, Gdx.graphics.getHeight() / 2 + car.getCenter().y, 0);
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();

        spider.render(batch);
        car.render(batch);

        batch.end();
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
