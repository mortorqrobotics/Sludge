package org.team1515.entities;

/**
 * Created by arvinzadeh on 1/11/15.
 */
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

public class Bullets {
    List<MovableEntity> bullets;
    Sprite sprite;
    Vector2 speed;

    public Bullets(Sprite sprite, Vector2 speed) {
        bullets = new ArrayList<MovableEntity>();
    }

    public void addBullet(Direction direction, float x, float y) {
        MovableEntity bullet = new MovableEntity(sprite, direction, speed);
        bullet.sprite.setPosition(x, y);
        if(direction == Direction.RIGHT || direction == Direction.LEFT) {
            bullet.sprite.rotate90(true);
        }
        bullets.add(bullet);
    }


    public void render(SpriteBatch batch) {
        for(MovableEntity bullet : bullets) {
            if (bullet.direction == Direction.RIGHT){
                bullet.sprite.setPosition(sprite.getX() + 20, sprite.getY());
            }
            else if (bullet.direction == Direction.LEFT){
                bullet.sprite.setPosition(sprite.getX() - 20, sprite.getY());
            }
            else if (bullet.direction == Direction.UP){
                bullet.sprite.setPosition(sprite.getX(), sprite.getY() + 20);
            }
            else if (bullet.direction == Direction.DOWN){
                bullet.sprite.setPosition(sprite.getX(), sprite.getY() - 20);
            }
            bullet.sprite.draw(batch);
        }
    }

}
