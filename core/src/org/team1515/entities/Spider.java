package org.team1515.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Spider {
    List<MovableEntity> spiders;
    Vector2 speed;
    Sprite sprite;
    Random random;
    int spiderFrequency;

    public Spider(Sprite sprite, float scale, Vector2 speed, int spiderFrequency) {
        spiders = new ArrayList<MovableEntity>();
        this.sprite = sprite;
        sprite.setScale(scale);
        this.spiderFrequency = spiderFrequency;
        random = new Random();
    }

    public void addSpider() {
        Direction direction;
        switch(random.nextInt(4)) {
            case 0:
                direction = Direction.UP;
                break;
            case 1:
                direction = Direction.DOWN;
                break;
            case 2:
                direction = Direction.LEFT;
                break;
            case 3:
                direction = Direction.RIGHT;
                break;
            default:
                direction = Direction.UP;
        }
        MovableEntity spider = new MovableEntity(sprite, direction, speed);
        spider.sprite.setPosition(random.nextInt(Gdx.graphics.getWidth()), random.nextInt(Gdx.graphics.getHeight()));
        spiders.add(spider);
    }

    public void render(SpriteBatch batch) {
        //Generate spiders randomly, or when the enter key is pressed
        if (((Gdx.input.isKeyPressed(Input.Keys.ENTER)) && Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) || (random.nextInt(180) < spiderFrequency)) {
            addSpider();
        }

        //Render spiders
        for(Entity spider : spiders) {
            spider.render(batch);
        }
    }
}
