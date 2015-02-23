package org.team1515.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.List;
import java.util.ArrayList;



/**
 * Created by arvin on 2/15/15.
 */
public class Floor {

    public List<Tile> tiles;
    public Direction direction;

    public Floor(){

        //this.direction = direction;
        tiles = new ArrayList<Tile>();

    }
    public void render(SpriteBatch batch){

        if (direction == Direction.DOWN){


        }
        else if (direction == Direction.UP){


        }
        else if (direction == Direction.RIGHT){


        }
        else if (direction == Direction.LEFT){


        }
        for (Tile tile : tiles) {
            batch.draw(tile.sprite.getTexture(),tile.sprite.getX(), tile.sprite.getY(), tile.width, tile.height);
        }

    }
    public void addTile(float x, float y, float w, float h){
       tiles.add(new Tile(x, y, w, h));

    }
    public void removeTile(Tile tileToRemove){
        tiles.remove(tileToRemove);
    }

    public class Tile{

        public Sprite sprite;
        public Texture gameBack;
        public float width;
        public float height;

        public Tile(float x, float y, float w, float h){
            gameBack = new Texture("grassTile3.jpg");
            this.sprite = new Sprite(gameBack);
            this.sprite.setPosition(x, y);
            this.width = w;
            this.height = h;
            this.sprite.setSize(w, h);
        }



    }


}
