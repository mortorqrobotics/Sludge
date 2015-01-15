package com.arvin.robotics.sludge;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import java.awt.*;
import java.util.Random;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;


public class Main extends Game {

	SpriteBatch batch;
	Texture img;
	Texture bullet;
	Texture car1R;
	Texture car1L;
	Texture car1U;
	Texture spiderImg;
	Texture ammoImg;
	int score;
	BitmapFont font;
	Texture car1D;
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
	Bullet bullet2;
	Entity entity1;
	Random rand;
	int randomBetween;

	Direction dirBullet;
	Direction dirEntity;
	Vector2 rlWH;
	Vector2 udWH;
	int margin;



	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		bullet = new Texture("badlogic.jpg");
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
		entity1 = new Entity();
		bullet2 = new Bullet();

		margin = 80;
		barrier1 = new Rectangle(-2500, -2500, 3, 5000);
		car1R = new Texture("car1R.png");
		car1L = new Texture("car1L.png");
		car1U = new Texture("car1U.png");;
		car1D = new Texture("car1D.png");
		ammoImg = new Texture("ammo1.png");
		font = new BitmapFont(false);
		scoreLabel = ("Score: ");
		score = 0;
		fontLives = new BitmapFont(false);
		livesLabel = ("Lives: ");
		lives = 0;
		spiderImg = new Texture("spider1.png");
		ammo = 20;
		fontAmmo = new BitmapFont(false);
		ammoLabel = ("Ammo: ");
		spiderFrequency = 3;
	}

	@Override


	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		bullet2.render(batch);
		entity1.render(batch);

		if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_RIGHT) && Gdx.input.isKeyJustPressed(Input.Keys.SHIFT_RIGHT)) {
			if (speed.x == 4 && !speedChanged) {
				speed.x *= 3;
				speed.y *= 3;
				speedChanged = true;
			} else if (speed.x != 4 && speedChanged) {
				speed.x = 4;
				speed.y = 4;
				speedChanged = false;
			}
		}
		if (speed.x == 4) {
			//System.out.println(4);
		}
		if (speed.x != 4) {
			//System.out.println("not 4");
		}

		if(!(rect.getX() <= 0 + margin)) {
			if(Gdx.input.isKeyPressed(Input.Keys.A)) {
				rect.setX(rect.getX() - speed.x);
				dirBullet = Direction.LEFT;
				//img = new Texture("car1L.png");
				//rect.setSize(rlWH.x, rlWH.y);
			}
		}
		else{
			if(Gdx.input.isKeyPressed(Input.Keys.A)) {
				//barrier1.setX(barrier1.getX() + speed.x);
				for (Entity.EntityBody body : entity1.entities) {
					body.rectangle.setX(body.rectangle.getX() + speed.x);
					dir = Direction.LEFT;

				}
				//img = new Texture("car1L.png");
				//rect.setSize(rlWH.x, rlWH.y);
			}
		}
		if (!(rect.getX() + rect.getWidth() >= Gdx.graphics.getWidth()-margin)) {
			if(Gdx.input.isKeyPressed(Input.Keys.D)) {
				rect.setX(rect.getX() + speed.x);
				dir = Direction.RIGHT;
				//img = new Texture("car1R.png");
				//rect.setSize(rlWH.x, rlWH.y);
			}
		}
		else{
			if(Gdx.input.isKeyPressed(Input.Keys.D)) {

				for (Entity.EntityBody body : entity1.entities) {
					body.rectangle.setX(body.rectangle.getX() - speed.x);
					dir = Direction.RIGHT;

				}
				//img = new Texture("car1R.png");
				//rect.setSize(rlWH.x, rlWH.y);
			}
		}
		if(!(rect.getY() <= 0+margin)) {
			if(Gdx.input.isKeyPressed(Input.Keys.S)) {
				rect.setY(rect.getY() - speed.y);
				dir = Direction.DOWN;
				//img = new Texture("car1D.png");
				//rect.setSize(udWH.x, udWH.y);
			}
		}
		else{
			if(Gdx.input.isKeyPressed(Input.Keys.S)) {
				for (Entity.EntityBody body : entity1.entities) {
					body.rectangle.setY(body.rectangle.getY() + speed.y);
					dir = Direction.DOWN;

				}
				//img = new Texture("car1D.png");
				//rect.setSize(udWH.x, udWH.y);
			}
		}
		if (!(rect.getY() + rect.getHeight() >= Gdx.graphics.getHeight()-margin)) {
			if(Gdx.input.isKeyPressed(Input.Keys.W)) {
				rect.setY(rect.getY() + speed.y);
				dir = Direction.UP;
				//img = new Texture("car1U.png");
				//rect.setSize(udWH.x, udWH.y);
			}
		}
		else{
			if(Gdx.input.isKeyPressed(Input.Keys.W)) {
				for (Entity.EntityBody body : entity1.entities) {
					body.rectangle.setY(body.rectangle.getY() - speed.y);
					dir = Direction.UP;

				}
				//img = new Texture("car1U.png");
				//rect.setSize(udWH.x, udWH.y);
			}
		}
		if ((Gdx.input.isKeyPressed(Input.Keys.SPACE))&&Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
			//didShoot = true;
			//bullet2.addBullet(rect.getX() + rect.getWidth()/2, rect.getY());
			lives = 5;
			ammo = 20;
			score = 0;
			//RESTART
		}
		if (ammo != 0) {
			if ((Gdx.input.isKeyPressed(Input.Keys.RIGHT)) && Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
				bullet2.addBullet(rect.getX(), rect.getY() + rlWH.y / 2, 50, 5, 1);
				ammo--;
			} else if ((Gdx.input.isKeyPressed(Input.Keys.LEFT)) && Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
				bullet2.addBullet(rect.getX(), rect.getY() + rlWH.y / 2, 50, 5, 2);
				ammo--;
			} else if ((Gdx.input.isKeyPressed(Input.Keys.UP)) && Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
				bullet2.addBullet(rect.getX() + udWH.x / 2, rect.getY(), 5, 50, 3);
				ammo--;
			} else if ((Gdx.input.isKeyPressed(Input.Keys.DOWN)) && Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
				bullet2.addBullet(rect.getX() + udWH.x / 2, rect.getY(), 5, 50, 4);
				ammo--;
			}
		}
		if ((Gdx.input.isKeyPressed(Input.Keys.ENTER)) && Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
			entity1.addEntity(spiderImg, new Rectangle(random(Gdx.graphics.getWidth()), random(Gdx.graphics.getHeight()), 40, 40), random(4)+1, "spider");
		}
		if (random(180) < spiderFrequency){
			//4
			entity1.addEntity(spiderImg, new Rectangle(random(Gdx.graphics.getWidth()), random(Gdx.graphics.getHeight()), 40, 40), random(4)+1, "spider");

		}
		if (random(720) < 1){
			entity1.addEntity(ammoImg, new Rectangle(random(Gdx.graphics.getWidth()), random(Gdx.graphics.getHeight()), 30, 30), random(4)+1, "ammo");
			//Add in addEntity, "type", type of entity. "ammo" or "spider
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
						//entity1.removeEntity(body);
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

		/*if (didShoot == true){
			//rectBullet.swaetY(rectBullet.getY() + bulletSpeed.y);
			if (rectBullet.getY() + rectBullet.getHeight() >= Gdx.graphics.getHeight()){
				didShoot = false;
			}
		}
		else{
			rectBullet.setX(rect.getX() + rect.getWidth()/2);
			rectBullet.setY(rect.getY());
		}*/
		//System.out.println(rectBullet.getX());
		//System.out.println(rectBullet.getY());
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
		//batch.draw(img, barrier1.getX(), barrier1.getY(), barrier1.getWidth(), barrier1.getHeight());
		//batch.draw(bullet, rectBullet.getX(), rectBullet.getY(), rectBullet.getWidth(), rectBullet.getHeight());
		//batch.draw(img, rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
		font.draw(batch, livesLabel + lives, 10, 50);
		font.draw(batch, ammoLabel + ammo, 10, 80);
		font.draw(batch, scoreLabel + score, 10, 20);
		batch.end();
	}
	public int random(int between){
		rand = new Random();
		randomBetween = rand.nextInt(between);
		//System.out.println(randomBetween);

		return randomBetween;
	}


}
