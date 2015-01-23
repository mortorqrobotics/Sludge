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
import org.team1515.entities.*;

import java.util.Random;

/**
 * Created by azadeh2 on 1/17/2015.
 */
public class GameScreen2 {
/*    SpriteBatch batch;
    Spider spider;
    Car car;
    OrthographicCamera camera;

    Texture car1R;
    Texture car1L;
    Texture car1U;
    Texture spiderImg;
    Texture ammoImg;
    Texture car1D;

    int score;
    BitmapFont font;
    int spiderFrequency;
    int ammo;
    BitmapFont fontAmmo;
    String ammoLabel;
    String scoreLabel;
    BitmapFont fontLives;
    String livesLabel;
    int lives;
    Rectangle rect;
    Rectangle rectBullet;
    Rectangle barrier1;
    Vector2 speed;
    Vector2 bulletNewLoc;
    Vector2 bulletSpeed;
    boolean speedChanged;
    boolean didShoot;
    Vector2 bulletOldLoc;

    Random rand;
    int randomBetween;

    Direction dirBullet;
    Vector2 rlWH;
    Vector2 udWH;
    int margin;

    @Override
    public void show() {
        batch = new SpriteBatch();
        spider = new Spider(new Sprite(new Texture("spider.png")));
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Texture carTexture = new Texture("car.png");
        Texture bulletTexture = new Texture("badlogic.jpg");
        car = new Car(new Sprite(new Texture("car.png"), carTexture.getWidth(), carTexture.getHeight()), Direction.UP, new Sprite(bulletTexture, 5, 50), new Vector2(2, 2));

        rlWH = new Vector2(50, 30);
        udWH = new Vector2(30, 50);
        rect = new Rectangle(50, 50, 60, 100);
        rectBullet = new Rectangle(rect.getX(), rect.getY(), 5, 25);
        speed = new Vector2(4, 4);
        bulletNewLoc = new Vector2(rect.getX(), rect.getY());
        //bulletOldLoc = new Vector2(-50, -50);
        bulletSpeed = new Vector2(0, 20);
        speedChanged = false;
        didShoot = false;

        margin = 80;
        car1R = new Texture("car1R.png");
        car1L = new Texture("car1L.png");
        car1U = new Texture("car.png");;
        car1D = new Texture("car1D.png");
        ammoImg = new Texture("ammo.png");
        font = new BitmapFont(false);
        scoreLabel = ("Score: ");
        score = 0;
        fontLives = new BitmapFont(false);
        livesLabel = ("Lives: ");
        lives = 0;
        spiderImg = new Texture("spider.png");
        ammo = 20;
        fontAmmo = new BitmapFont(false);
        ammoLabel = ("Ammo: ");
        spiderFrequency = 3;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.position.set(car.getX(), Gdx.graphics.getHeight() / 2, 0);
        camera.update();

        batch.begin();

        //If player !goes past left wall, and if the player presses A, then move only move player across the screen
        if(!(rect.getX() <= 0 + margin)) {
            if(Gdx.input.isKeyPressed(Input.Keys.A)) {
                rect.setX(rect.getX() - speed.x);
                dirBullet = Direction.LEFT;
            }
        }
        else{
            if(Gdx.input.isKeyPressed(Input.Keys.A)) {
                for (Entity body : entity1.entities) {
                    body.rectangle.setX(body.rectangle.getX() + speed.x);
                    dirBullet = Direction.LEFT;

                }
            }
        }
        if (!(rect.getX() + rect.getWidth() >= Gdx.graphics.getWidth()-margin)) {
            if(Gdx.input.isKeyPressed(Input.Keys.D)) {
                rect.setX(rect.getX() + speed.x);
                dirBullet = Direction.RIGHT;
            }
        }
        else{
            if(Gdx.input.isKeyPressed(Input.Keys.D)) {

                for (Entity.EntityBody body : entity1.entities) {
                    body.rectangle.setX(body.rectangle.getX() - speed.x);
                    dirBullet = Direction.RIGHT;

                }
            }
        }
        if(!(rect.getY() <= 0+margin)) {
            if(Gdx.input.isKeyPressed(Input.Keys.S)) {
                rect.setY(rect.getY() - speed.y);
                dirBullet = Direction.DOWN;
            }
        }
        else{
            if(Gdx.input.isKeyPressed(Input.Keys.S)) {
                for (Entity.EntityBody body : entity1.entities) {
                    body.rectangle.setY(body.rectangle.getY() + speed.y);
                    dirBullet = Direction.DOWN;

                }
            }
        }
        if (!(rect.getY() + rect.getHeight() >= Gdx.graphics.getHeight()-margin)) {
            if(Gdx.input.isKeyPressed(Input.Keys.W)) {
                rect.setY(rect.getY() + speed.y);
                dirBullet = Direction.UP;
            }
        }
        else{
            if(Gdx.input.isKeyPressed(Input.Keys.W)) {
                for (Entity.EntityBody body : entity1.entities) {
                    body.rectangle.setY(body.rectangle.getY() - speed.y);
                    dirBullet = Direction.UP;

                }
            }
        }
        if ((Gdx.input.isKeyPressed(Input.Keys.SPACE))&&Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            lives = 5;
            ammo = 20;
            score = 0;
            //RESTART
        }
        if (ammo != 0) {
            if ((Gdx.input.isKeyPressed(Input.Keys.RIGHT)) && Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
                bullets.addBullet(Direction.RIGHT, rect.getX(), rect.getY());
                ammo--;
            } else if ((Gdx.input.isKeyPressed(Input.Keys.LEFT)) && Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
                bullets.addBullet(Direction.LEFT, rect.getX(), rect.getY());
                ammo--;
            } else if ((Gdx.input.isKeyPressed(Input.Keys.UP)) && Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
                bullets.addBullet(Direction.UP, rect.getX(), rect.getY());
                ammo--;
            } else if ((Gdx.input.isKeyPressed(Input.Keys.DOWN)) && Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
                bullets.addBullet(Direction.DOWN, rect.getX(), rect.getY());
                ammo--;
            }
        }



        for (Entity.EntityBody body: entity1.entities){
            if (body.type.equals("ammo")){
                if (body.rectangle.overlaps(rect)&&(body.rectangle.getHeight() > 0)){
                    ammo+=10;
                    body.rectangle.setSize(0);
                }

            }
            for (Bullet.Projectile bullet: bullet2.bullets) {
                if (body.type.equals("spider")) {
                    if (body.rectangle.overlaps(bullet.rectangle)) {
                        body.rectangle.setSize(0);
                        score++;
                    }
                    if ((body.rectangle.overlaps(rect)) && (body.rectangle.getHeight() > 0)) {
                        body.rectangle.setSize(0);
                        if (lives > 0) {
                            lives--;
                        }

                    }
                }
            }

            //If any bullet intersects any entity, entity is hidden
            //Find how to remove
        }
        if (lives <= 0){
            //score = 0;
            ammo = 0;
            //spiderFrequency = 0;
            entity1.entities.clear();
            bullet2.bullets.clear();
        }
        if ((score < 10)&&(score > 0)) {
            spiderFrequency = 2;
        }
        else if (score < 25) {
            spiderFrequency = 3;
        }
        else if (score < 75) {
            spiderFrequency = 4;
        }
        else if (score < 150) {
            spiderFrequency = 5;
        }
        else if (score < 250) {
            spiderFrequency = 6;
        }
        else if (score < 500) {
            spiderFrequency = 7;
        }
        else{
            spiderFrequency = 8;
        }


        if (dirBullet == Direction.RIGHT){
            batch.draw(car1R, rect.getX(), rect.getY(), rlWH.x, rlWH.y);
        }
        else if(dirBullet == Direction.LEFT){
            batch.draw(car1L, rect.getX(), rect.getY(), rlWH.x, rlWH.y);
        }
        else if(dirBullet == Direction.UP){
            batch.draw(car1U, rect.getX(), rect.getY(), udWH.x, udWH.y);
        }
        else if(dirBullet == Direction.DOWN){
            batch.draw(car1D, rect.getX(), rect.getY(), udWH.x, udWH.y);
        }

        //TODO: stick this somewhere
//        if (random(720) < 1) {
//            addSpider(ammoImg, new Rectangle(random(Gdx.graphics.getWidth()), random(Gdx.graphics.getHeight()), 30, 30), random(4)+1, "ammo");
//            //Add in addEntity, "type", type of entity. "ammo" or "spider
//        }

        font.draw(batch, livesLabel + lives, 10, 50);
        font.draw(batch, ammoLabel + ammo, 10, 80);
        font.draw(batch, scoreLabel + score, 10, 20);
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
    */
}
