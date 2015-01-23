package org.team1515.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Car extends MovableEntity {

    Bullets bullets;
    int rotation;

    public Car(Sprite sprite, float scale, Direction direction, Sprite bulletSprite, Vector2 speed) {
        super(sprite, direction, speed, scale);
        sprite.setPosition(0, 0);
        bullets = new Bullets(bulletSprite, new Vector2(2, 2));
        rotation = 0;
    }

    public float getX() {
        return sprite.getX();
    }

    public float getY() {
        return sprite.getY();
    }

    public float getWidth() {
        return sprite.getBoundingRectangle().getWidth();
    }

    public float getHeight() {
        return sprite.getBoundingRectangle().getHeight();
    }

    public Vector2 getCenter() {
        return new Vector2(getX() + getWidth() / 2, getY() + getHeight() / 2);
    }

    public void render(SpriteBatch batch) {
        if(Gdx.input.isKeyPressed(Input.Keys.W)) {
            if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                direction = Direction.UP_LEFT;
                sprite.setX(sprite.getX() - speed.x);
                sprite.setRotation(45);
            } else if(Gdx.input.isKeyPressed(Input.Keys.D)) {
                direction = Direction.UP_RIGHT;
                sprite.setX(sprite.getX() + speed.x);
                sprite.setRotation(315);
            } else {
                direction = Direction.UP;
                sprite.setRotation(0);
            }
            sprite.setY(sprite.getY() + speed.y);
        } else if(Gdx.input.isKeyPressed(Input.Keys.S)) {
            if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                direction = Direction.DOWN_LEFT;
                sprite.setX(sprite.getX() - speed.x);
                sprite.setRotation(135);
            } else if(Gdx.input.isKeyPressed(Input.Keys.D)) {
                direction = Direction.DOWN_LEFT;
                sprite.setX(sprite.getX() + speed.x);
                sprite.setRotation(225);
            } else {
                direction = Direction.DOWN;
                sprite.setRotation(180);
            }
            sprite.setY(sprite.getY() - speed.y);
        } else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            direction = Direction.RIGHT;
            sprite.setX(sprite.getX() - speed.x);
            sprite.setRotation(90);
        } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            direction = Direction.LEFT;
            sprite.setX(sprite.getX() + speed.x);
            sprite.setRotation(270);
        }

        super.render(batch);
    }
}
