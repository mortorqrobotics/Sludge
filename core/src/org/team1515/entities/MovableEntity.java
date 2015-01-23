package org.team1515.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by azadeh2 on 1/17/2015.
 */
public class MovableEntity extends Entity {
    Direction direction;
    Vector2 speed;

    public MovableEntity(Sprite sprite, Direction direction, Vector2 speed) {
        super(sprite);
        this.direction = direction;
        this.speed = speed;
    }

    public MovableEntity(Sprite sprite, Direction direction, Vector2 speed, float scale) {
        super(sprite);
        sprite.setScale(scale);
        this.direction = direction;
        this.speed = speed;
    }
}
