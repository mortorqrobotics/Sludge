package org.team1515.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by arvin on 2/7/15.
 */
public class ButtonCustom {
    Rectangle rectangle;
    Texture texture;
    public boolean isPressed;
    private boolean wasPressed;


    public ButtonCustom(Rectangle rectangle, Texture texture) {
        this.rectangle = rectangle;
        this.texture = texture;
        wasPressed = false;
    }

    public void render(SpriteBatch batch) {
        if(Gdx.input.isTouched() && Gdx.input.getX() > rectangle.getX() && Gdx.input.getX() < rectangle.getX() + rectangle.getWidth() && Gdx.graphics.getHeight() - Gdx.input.getY() > rectangle.getY() && Gdx.graphics.getHeight() - Gdx.input.getY() < rectangle.getY() + rectangle.getHeight()) {
            if(!wasPressed) {
                isPressed = true;
                wasPressed = true;
            } else {
                isPressed = false;
            }
        } else {
            isPressed = false;
            wasPressed = false;
        }


        if(isPressed)System.out.println(true);
        batch.draw(texture, rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
    }

}
