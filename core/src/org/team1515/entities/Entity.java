package org.team1515.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arvin on 1/11/15.
 */
public class Entity {
    Sprite sprite;



    public Entity(Sprite sprite){
        this.sprite = sprite;
    }

    public void render(SpriteBatch batch) {
        sprite.draw(batch);
    }
        /*
        for (EntityBody body : entities){
            if (body.direction == 1){
                body.rectangle.setX(body.rectangle.getX()+entitySpeed.x);
                //RIGHT
            }else if(body.direction == 2){
                body.rectangle.setX(body.rectangle.getX()-entitySpeed.x);
                //LEFT
            }else if(body.direction == 3){
                body.rectangle.setY(body.rectangle.getY()+entitySpeed.y);
                //UP
            }else{
                body.rectangle.setY(body.rectangle.getY()-entitySpeed.y);
                //DOWN
            }
            batch.draw(body.texture, body.rectangle.getX(), body.rectangle.getY(), body.rectangle.getWidth(), body.rectangle.getHeight());
            //System.out.println("Running");


        }
        */
}
