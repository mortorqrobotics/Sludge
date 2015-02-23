package org.team1515.entities;

/**
 * Created by arvinzadeh on 1/11/15.
 */
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class Bullet {
    public List<Projectile> bullets;

    public Bullet() {
        bullets = new ArrayList<Projectile>();
    }

    public void addBullet(float x, float y, float w, float h, int direction) {
        bullets.add(new Projectile(new Texture("badlogic.jpg"), new Rectangle(x, y, w, h), direction));
        //W5 H50     RL
        //W50  H5    UD
    }


    public void render(SpriteBatch batch) {
        for(Projectile bullet : bullets) {
            if (bullet.direction == 1){
                bullet.rectangle.setX(bullet.rectangle.getX() + 20);
            }
            else if (bullet.direction == 2){
                bullet.rectangle.setX(bullet.rectangle.getX() - 20);
            }
            else if (bullet.direction == 3){
                bullet.rectangle.setY(bullet.rectangle.getY() + 20);
            }
            else if (bullet.direction == 4){
                bullet.rectangle.setY(bullet.rectangle.getY() - 20);
            }
            //bullet.rectangle.setY(bullet.rectangle.getY() + 20);
            batch.draw(bullet.texture, bullet.rectangle.getX(), bullet.rectangle.getY(), bullet.rectangle.getWidth(), bullet.rectangle.getHeight());
            //System.out.println("running");
        }
        //System.out.println("Running");

    }

    public class Projectile {
        Texture texture;
        public Rectangle rectangle;
        int direction;
        public Projectile(Texture texture, Rectangle rectangle, int direction) {
            this.texture = texture;
            this.rectangle = rectangle;
            this.direction = direction;
        }
    }
}
