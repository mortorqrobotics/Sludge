package org.team1515.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arvin on 1/11/15.
 */
public class Entity {
    public List<EntityBody> entities;
    Vector2 entitySpeed;
    public Entity(){
        entities = new ArrayList<EntityBody>();
        entitySpeed = new Vector2(1,1);
    }

    public void removeEntity(EntityBody body){
        entities.remove(body);
        //System.out.println("Removed!");
    }

    public void addEntity(Texture texture, Rectangle rectangle, int direction, String type){
        entities.add(new EntityBody(texture, rectangle, direction, type));
    }



    public void startMoving(){
        //May not be used
    }

    public void render(SpriteBatch batch){
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



    }

    public class EntityBody{
        public Texture texture;
        public Rectangle rectangle;
        public int direction;
        public String type;

        public EntityBody(Texture texture, Rectangle rectangle, int direction, String type){
            this.rectangle = rectangle;
            this.texture = texture;
            this.direction = direction;
            this.type = type;
        }


    }

}
