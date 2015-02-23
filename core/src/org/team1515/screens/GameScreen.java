package org.team1515.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import org.team1515.entities.*;
import org.team1515.sludge.Main;
import org.team1515.entities.Floor.*;

import java.util.Random;

/**
 * Created by azadeh2 on 1/30/2015.
 */
public class GameScreen implements Screen {
    Main game;
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
    int spiderValue;
    String totalMoneyLabel;
    int ammo;
    BitmapFont fontAmmo;
    String ammoLabel;
    String scoreLabel;
    BitmapFont fontLives;
    String partsLabel;
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
    double dx;
    double storedDx;
    double storedDy;
    double dy;
    double accel;
    double deaccel;
    int robotParts;
    Texture gearImg;
    Texture oilImg;
    Texture motorImg;
    Double friction;
    Direction dir;
    Direction dirEntity;
    Vector2 rlWH;
    Vector2 udWH;
    int margin;
    int spiderTurnFrequency;
    boolean partElig;
    boolean[] hasParts;
    ButtonCustom mainMenuButton;
    ButtonCustom storeButton;
    Floor floor;
    boolean canSpawnRight;
    boolean canSpawnUp;
    boolean canSpawnDown;
    boolean canSpawnLeft;
    SpawnDir spawnDir;
    float scrolledX;
    float scrolledY;
    int picX;
    int picY;
    int manageX;
    int manageY;

    public GameScreen(Main game) {
        this.game = game;

        img = new Texture("badlogic.jpg");
        bullet = new Texture("badlogic.jpg");
        rlWH = new Vector2(50, 30);
        udWH = new Vector2(30, 50);
        rect = new Rectangle(200, 200, 60, 100);
        rectBullet = new Rectangle(rect.getX(), rect.getY(), 5, 25);
        speed = new Vector2(4, 4);
        bulletNewLoc = new Vector2(rect.getX(), rect.getY());
        //bulletOldLoc = new Vector2(-50, -50);
        bulletSpeed = new Vector2(0, 20);
        speedChanged = false;
        didShoot = false;
        entity1 = new Entity();
        bullet2 = new Bullet();
        totalMoneyLabel = "Total Money: $";
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
        spiderValue = 1;
        spiderTurnFrequency = 2;
        dx = 0;
        dy = 0;
        accel = 0.05;
        deaccel = 0.2;
        robotParts = 0;
        motorImg =  new Texture("motor1.png");
        oilImg =  new Texture("oil1.png");
        gearImg = new Texture("gear1.png");
        partElig = true;
        friction = 0.02;
        partsLabel = ("Robot Parts: ");
        hasParts  = new boolean[6];
        mainMenuButton = new ButtonCustom(new Rectangle(5, Gdx.graphics.getHeight() - 110, 100, 100), new Texture("menuButton2.png"));
        storeButton = new ButtonCustom(new Rectangle(Gdx.graphics.getWidth() - 180, 0, 180, 60), new Texture("storeButton1.jpg"));
        floor = new Floor();
        floor.addTile(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        floor.addTile(0-Gdx.graphics.getWidth()-20, 0, Gdx.graphics.getWidth()+20, Gdx.graphics.getHeight());
        floor.addTile(0+Gdx.graphics.getWidth(), 0, Gdx.graphics.getWidth()+20, Gdx.graphics.getHeight());
        floor.addTile(0, 0+Gdx.graphics.getHeight(), Gdx.graphics.getWidth()+20, Gdx.graphics.getHeight()+20);
        floor.addTile(0, 0-Gdx.graphics.getHeight(), Gdx.graphics.getWidth()+20, Gdx.graphics.getHeight()+20);
        canSpawnDown = true;
        canSpawnUp = true;
        canSpawnLeft = true;
        canSpawnRight = true;
        spawnDir = SpawnDir.NONE;
        scrolledX = 0;
        scrolledY = 0;
        picX = (int)(scrolledX / Gdx.graphics.getWidth());
        picY = (int)(scrolledY / Gdx.graphics.getHeight());
        manageX = -1;
        manageY = -1;


    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {


        Gdx.gl.glClearColor(.5f, .5f, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        floor.render(game.batch);
        bullet2.render(game.batch);
        entity1.render(game.batch);
        mainMenuButton.render(game.batch);
        storeButton.render(game.batch);








        /*picX = scrolledX % Gdx.graphics.getWidth();
        picY = scrolledY % Gdx.graphics.getHeight();*/












        picX = (int)(scrolledX / Gdx.graphics.getWidth());
        picY = (int)(scrolledY / Gdx.graphics.getHeight());

        if (picX > manageX||(scrolledX < 20 && scrolledX > 0 && rect.getX()+rect.getWidth()>Gdx.graphics.getWidth()-margin)){
            floor.addTile(Gdx.graphics.getWidth()-20, 0, Gdx.graphics.getWidth()+20, Gdx.graphics.getHeight());

            manageX++;
            for (int i = 0; i < floor.tiles.size(); i++) {
                if (floor.tiles.get(i).sprite.getX() + floor.tiles.get(i).width < 0) {
                    floor.tiles.remove(floor.tiles.get(i));
                }
            }
        }else if(picX < manageX||(scrolledX > -20 && scrolledX < 0 && rect.getX()<margin)){
            floor.addTile(0 - Gdx.graphics.getWidth()-20, 0, Gdx.graphics.getWidth()+20, Gdx.graphics.getHeight());
            manageX--;

            for (int i = 0; i < floor.tiles.size(); i++) {
                if (floor.tiles.get(i).sprite.getX() > Gdx.graphics.getWidth()) {
                    floor.tiles.remove(floor.tiles.get(i));
                }
            }
        }

        if (picY > manageY||(scrolledY < 20 && scrolledY > 0 && rect.getY()+rect.getHeight()>Gdx.graphics.getHeight()-margin)){
            floor.addTile(0, Gdx.graphics.getHeight()-20, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()+20);
            manageY++;
            for (int i = 0; i < floor.tiles.size(); i++) {
                if (floor.tiles.get(i).sprite.getY() + floor.tiles.get(i).height < 0) {
                    floor.tiles.remove(floor.tiles.get(i));
                }
            }
        }else if (picY < manageY||(scrolledY > -20 && scrolledY < 0 && rect.getY()<margin)){
            floor.addTile(0, 0 - Gdx.graphics.getHeight()-20, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()+20);
            manageY--;
            for (int i = 0; i < floor.tiles.size(); i++) {
                if (floor.tiles.get(i).sprite.getY() > Gdx.graphics.getHeight()) {
                    floor.tiles.remove(floor.tiles.get(i));
                }
            }
        }

















        if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_RIGHT) && Gdx.input.isKeyJustPressed(Input.Keys.SHIFT_RIGHT) && game.storeScreen.hasSpeed == true) {
            /*if (speed.x == 4 && !speedChanged) {
                speed.x *= 3;
                speed.y *= 3;
                speedChanged = true;
            } else if (speed.x != 4 && speedChanged) {
                speed.x = 4;
                speed.y = 4;
                speedChanged = false;
            }*/
            //BREAK
            dx = 0;
            dy = 0;
            storedDx = 0;
            storedDy = 0;

        }
        if (speed.x == 4) {
            //System.out.println(4);
        }
        if (speed.x != 4) {
            //System.out.println("not 4");
        }


        rect.setX((float) (rect.getX() + dx));
        rect.setY((float) (rect.getY() + dy));
        storedDx = dx != 0 ? dx : storedDx;
        storedDy = dy != 0 ? dy : storedDy;

        //storedDx = dx != 0 ? dx : storedDx;
        /*
        accel = Math.abs(dx) > 5 || Math.abs(storedDx) > 5  ? 0.01 : 0.03;
        accel = Math.abs(dy) > 5 || Math.abs(storedDy) > 5 ? 0.01 : 0.03;
        if (accel == 0.01){
            System.out.println("WORKS 0.01");
        }
        else{
            System.out.println("NO WORKS 0.03");
        }
        */
        //SLOWS ACCEL AFTER A SPEED OF 5


        dx = dx > 15 ? 15 : dx;
        dy = dy > 15 ? 15 : dy;
        storedDx = storedDx > 15 ? 15 : storedDx;
        storedDy = storedDy > 15 ? 15 : storedDy;
        //FORCES MAX SPEED OF 15
        dx = dx < -15 ? -15 : dx;
        dy = dy < -15 ? -15 : dy;
        storedDx = storedDx < -15 ? -15 : storedDx;
        storedDy = storedDy < -15 ? -15 : storedDy;
        //BETTER WAY TO DO THIS?



        //System.out.println(storedDx);
        //System.out.println("DX: " + dx);
        //System.out.println("DXS: " + storedDy);
        System.out.println(""+ picX  +" "+ manageX);










        if (rect.getY() > Gdx.graphics.getHeight() - margin){

            for (Entity.EntityBody body : entity1.entities) {
                body.rectangle.setY((float) (body.rectangle.getY() - storedDy));
                //Don't know why it's so much faster

            }
            dy = 0;

            //floor.direction = Direction.UP;

            scrolledY+=storedDy;

            /*game.batch.draw(new Texture("grassTile3.jpg"), (float)picX, (float)picY);
            game.batch.draw(new Texture("grassTile3.jpg"), (float)picX - Gdx.graphics.getWidth(), (float)picY);
            game.batch.draw(new Texture("grassTile3.jpg"), (float)picX, (float)picY - Gdx.graphics.getHeight());
            game.batch.draw(new Texture("grassTile3.jpg"), (float)picX - Gdx.graphics.getWidth(), (float)picY-Gdx.graphics.getHeight());
            */

            if (canSpawnUp){
                //floor.addTile(0, Gdx.graphics.getHeight(), Gdx.graphics.getWidth(), Gdx.graphics.getHeight()+60);
                canSpawnUp = false;
                //canSpawnRight = true;
            }

            /*for (Tile tile: floor.tiles){
                tile.sprite.setPosition((float) (tile.sprite.getX()), (float) (tile.sprite.getY()-storedDy));
                if (tile.sprite.getY() + tile.height < 0){
                    floor.tiles.remove(tile);
                    canSpawnUp = true;
                }
            }*/
            for (int i = 0; i < floor.tiles.size(); i++){
                floor.tiles.get(i).sprite.setPosition((float) (floor.tiles.get(i).sprite.getX()), (float) (floor.tiles.get(i).sprite.getY()-storedDy));



            }




        }
        if (rect.getY() < margin){

            for (Entity.EntityBody body : entity1.entities) {
                body.rectangle.setY((float) (body.rectangle.getY() - storedDy));
                //Don't know why it's so much faster

            }
            dy = 0;

            scrolledY+=storedDy;




            if (canSpawnDown){
                //floor.addTile(0, 0 - Gdx.graphics.getHeight() - 60, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()+60);
                canSpawnDown = false;
                //canSpawnRight = true;
                canSpawnLeft = true;
            }

            for (int i = 0; i < floor.tiles.size(); i++){
                floor.tiles.get(i).sprite.setPosition((float) (floor.tiles.get(i).sprite.getX()), (float) (floor.tiles.get(i).sprite.getY()-storedDy));


            }




        }
        if (rect.getX() > Gdx.graphics.getWidth() - margin){

            for (Entity.EntityBody body : entity1.entities) {
                body.rectangle.setX((float)(body.rectangle.getX() - storedDx));
                //Don't know why it's so much faster

            }
            dx = 0;


            scrolledX+=storedDx;

            if (canSpawnRight){
                //floor.addTile(Gdx.graphics.getWidth(), 0, Gdx.graphics.getWidth()+30, Gdx.graphics.getHeight());
                //canSpawnRight = false;
                canSpawnUp = true;
            }

            /*for (Tile tile: floor.tiles){
                tile.sprite.setPosition((float) (tile.sprite.getX()-storedDx), (float) (tile.sprite.getY()));
                if (tile.sprite.getX()+tile.sprite.getWidth() < 0){
                    floor.tiles.remove(tile);
                    canSpawnRight = true;
                }
            }*/
            for (int i = 0; i < floor.tiles.size(); i++){
                floor.tiles.get(i).sprite.setPosition((float) (floor.tiles.get(i).sprite.getX()-storedDx), (float) (floor.tiles.get(i).sprite.getY()));

            }





        }
        if (rect.getX() < margin){
            for (Entity.EntityBody body : entity1.entities) {
                body.rectangle.setX((float)(body.rectangle.getX() - storedDx));


            }
            dx = 0;


            scrolledX+=storedDx;

            for (int i = 0; i < floor.tiles.size(); i++){
                floor.tiles.get(i).sprite.setPosition((float) (floor.tiles.get(i).sprite.getX() - storedDx), (float) (floor.tiles.get(i).sprite.getY()));


            }




        }
















        if(!(rect.getX() <= 0 + margin)) {
            if(Gdx.input.isKeyPressed(Input.Keys.A)) {
                //rect.setX(rect.getX() - speed.x);
                //dx = dx == 0 ? storedDx : dx;
                //dx -= dx < 0 ? accel : deaccel;
                dir = Direction.LEFT;
                rect.setSize(rlWH.x, rlWH.y);
                //img = new Texture("car1L.png");
                //rect.setSize(rlWH.x, rlWH.y);
                if (storedDx > dx){
                    storedDx-=deaccel;
                }else{
                    dx -= dx < 0 ? accel : deaccel;
                }
            }
            else {
                for (int i = 0; i < friction / 0.01 && dx < 0; i++) {
                    dx += 0.01;
                    if (dir == Direction.LEFT && dx > 0) {
                        dx = 0;
                    }
                }



                /*if (dir == Direction.LEFT && dx > 0){
                    dx = 0;
                }
                else{
                    dx+=0.05;
                }*/


            }
        }
        else{
            if(Gdx.input.isKeyPressed(Input.Keys.A)) {
                //barrier1.setX(barrier1.getX() + speed.x);
                //for (Entity.EntityBody body : entity1.entities) {
                    //body.rectangle.setX(body.rectangle.getX() + speed.x);
                    dir = Direction.LEFT;
                    rect.setSize(rlWH.x, rlWH.y);
                    storedDx -= storedDx < 0 ? accel : deaccel;

                //}
                //img = new Texture("car1L.png");
                //rect.setSize(rlWH.x, rlWH.y);
            }
            else {
                for (int i = 0; i < friction / 0.01 && storedDx < 0; i++) {
                    storedDx += dir == Direction.LEFT ? 0.01 : 0;
                    if (dir == Direction.LEFT && storedDx > 0) {
                        storedDx = 0;
                    }
                }
            }
        }
        if (!(rect.getX() + rect.getWidth() >= Gdx.graphics.getWidth()-margin)) {
            if(Gdx.input.isKeyPressed(Input.Keys.D)) {
                //rect.setX(rect.getX() + speed.x);

                //dx = storedDx > 0 ? storedDx : dx;

                //storedDx += storedDx < 0 ? accel : deaccel;

                if (storedDx < dx){ //<
                    storedDx+=deaccel;
                }else{
                    dx += dx > 0 ? accel : deaccel;
                }



                dir = Direction.RIGHT;
                rect.setSize(rlWH.x, rlWH.y);
                //img = new Texture("car1R.png");
                //rect.setSize(rlWH.x, rlWH.y);
            }
            else {
                for (int i = 0; i < friction / 0.01 && dx > 0; i++) {
                    dx -= 0.01;
                    if (dir == Direction.RIGHT && dx < 0) {
                        dx = 0;
                    }
                }
            }
        }
        else{
            if(Gdx.input.isKeyPressed(Input.Keys.D)) {
                dir = Direction.RIGHT;
                rect.setSize(rlWH.x, rlWH.y);
                storedDx += storedDx < 0 ? accel : deaccel;
            }
            else {
                for (int i = 0; i < friction / 0.01 && storedDx > 0; i++) {
                    storedDx -= 0.01;
                    if (dir == Direction.RIGHT && storedDx < 0) {
                        storedDx = 0;
                    }
                }
            }

        }
        if(!(rect.getY() <= 0+margin)) {
            if(Gdx.input.isKeyPressed(Input.Keys.S)) {
                //rect.setY(rect.getY() - speed.y);
                //dy -= dy < 0 ? accel : deaccel;
                dir = Direction.DOWN;
                rect.setSize(udWH.x, udWH.y);

                if (storedDy > dy){
                    storedDy-=deaccel;
                }else{
                    dy -= dy < 0 ? accel : deaccel;
                }

                //img = new Texture("car1D.png");
                //rect.setSize(udWH.x, udWH.y);
            }
            else {
                for (int i = 0; i < friction / 0.01 && dy < 0; i++) {
                    dy += dir == Direction.DOWN ? 0.01 : 0;
                    if (dir == Direction.DOWN && dy > 0) {
                        dy = 0;
                    }
                }
            }



        }
        else{
            if(Gdx.input.isKeyPressed(Input.Keys.S)) {
                //for (Entity.EntityBody body : entity1.entities) {
                    //body.rectangle.setY(body.rectangle.getY() + speed.y);
                    dir = Direction.DOWN;
                    rect.setSize(udWH.x, udWH.y);
                    storedDy -= storedDy < 0 ? accel : deaccel;



                //}
                //img = new Texture("car1D.png");
                //rect.setSize(udWH.x, udWH.y);
            }
            else {
                for (int i = 0; i < friction / 0.01 && storedDy < 0; i++) {
                    storedDy += dir == Direction.DOWN ? 0.01 : 0;
                    if (dir == Direction.DOWN && storedDy > 0) {
                        storedDy = 0;
                    }
                }
            }
        }
        if (!(rect.getY() + rect.getHeight() >= Gdx.graphics.getHeight()-margin)) {
            if(Gdx.input.isKeyPressed(Input.Keys.W)) {
                //rect.setY(rect.getY() + speed.y);
                //dy += dy < 0 ? accel : deaccel;
                dir = Direction.UP;
                rect.setSize(udWH.x, udWH.y);

                if (storedDy < dy){
                    storedDy+=deaccel;
                } else {
                    dy += dy > 0 ? accel : deaccel;
                }

                //img = new Texture("car1U.png");
                //rect.setSize(udWH.x, udWH.y);
            }
            else {
                for (int i = 0; i < friction / 0.01 && dy > 0; i++) {
                    dy -= 0.01;
                    if (dir == Direction.UP && dy < 0) {
                        dy = 0;
                    }
                }
            }

        }
        else{
            if(Gdx.input.isKeyPressed(Input.Keys.W)) {
                //for (Entity.EntityBody body : entity1.entities) {
                    //body.rectangle.setY(body.rectangle.getY() - speed.y);
                    dir = Direction.UP;
                    rect.setSize(udWH.x, udWH.y);
                    storedDy += storedDy < 0 ? accel : deaccel;


                //}
                //img = new Texture("car1U.png");
                //rect.setSize(udWH.x, udWH.y);
            }
            else {
                for (int i = 0; i < friction / 0.01 && storedDy > 0; i++) {
                    storedDy -= dir == Direction.UP ? 0.01 : 0;
                    if (dir == Direction.UP && storedDy < 0) {
                        storedDy = 0;
                    }
                }
            }
        }


        /* if score is between 0 and 50 and robotParts == 1 and a random number 1000 == 1{
               spawn entity robotpart with texture gear
        }



         */















        if ((Gdx.input.isKeyPressed(Input.Keys.SPACE))&&Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            //didShoot = true;
            //bullet2.addBullet(rect.getX() + rect.getWidth()/2, rect.getY());
            lives = game.storeScreen.startingLives;
            ammo = (int)game.storeScreen.ammoPackTier.x;
            game.storeScreen.setMoney(game.storeScreen.getMoney() + (score*game.storeScreen.multiplierAmount));
            score = 0;
            entity1.entities.clear();
            bullet2.bullets.clear();
            dx = 0;
            dy = 0;
            storedDx = 0;
            storedDy = 0;
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
            //Add in addEntity, "type", type of entity. "ammo" or "spider"
        }

        //System.out.println("" + hasParts[0] + hasParts[1] + hasParts[2] + "\n");




        //0 50 50 100 100 200
        //ROBOT PARTS 3/5
        if (score > 0 && score < 50 && !hasParts[0] && random(2000) < 1 && partElig){
            //Gear 0
            entity1.addEntity(gearImg, new Rectangle(random(Gdx.graphics.getWidth()), random(Gdx.graphics.getHeight()), 30, 30), random(4)+1, "part");
            partElig = false;
        }
        else if(score > 50 && score < 100 && !hasParts[1] && random(2000) < 1 && partElig){
            //Motor 1
            entity1.addEntity(motorImg, new Rectangle(random(Gdx.graphics.getWidth()), random(Gdx.graphics.getHeight()), 30, 23), random(4)+1, "part");
            partElig = false;


        }
        else if(score > 100 && score < 200 && !hasParts[2] && random(2000) < 1 && partElig){
            //Oil 2
            entity1.addEntity(oilImg, new Rectangle(random(Gdx.graphics.getWidth()), random(Gdx.graphics.getHeight()), 20, 32), random(4)+1, "part");
            partElig = false;


        }






        for (Entity.EntityBody body: entity1.entities){
            if (body.type.equals("spider")){
                if (random(300)<spiderTurnFrequency){
                    body.direction = random(4) + 1;
                }

            }
            if (body.type.equals("part")){
                if (body.rectangle.overlaps(rect) && body.rectangle.getHeight() > 0){
                    robotParts++;
                    body.rectangle.setSize(0);
                    partElig = true;
                    hasParts[0] = body.texture.equals(gearImg) || hasParts[0];
                    hasParts[1] = body.texture.equals(motorImg) || hasParts[1];
                    hasParts[2] = body.texture.equals(oilImg) || hasParts[2];
                }
            }
        }

        for (Entity.EntityBody body: entity1.entities){
            if (body.type.equals("ammo")){
                if (body.rectangle.overlaps(rect)&&(body.rectangle.getHeight() > 0)&&score > 0){
                    ammo+=(int)game.storeScreen.ammoPackTier.y;
                    body.rectangle.setSize(0);
                }

            }
            for (Bullet.Projectile bullet: bullet2.bullets) {
                if (body.type.equals("spider")) {
                    if (body.rectangle.overlaps(bullet.rectangle) && bullet.rectangle.getHeight()>0 && body.rectangle.getHeight()>0) {
                        if (game.storeScreen.hasLaser == false){
                            bullet.rectangle.setSize(0);
                            body.rectangle.setSize(0);
                        }
                        else{
                            body.rectangle.setSize(0);
                        }

                        //entity1.removeEntity(body);
                        score+=spiderValue;
                        //Infinity bullet or not?
                    }
                    if ((body.rectangle.overlaps(rect)) && (body.rectangle.getHeight() > 0)) {
                        System.out.println("overlaps");
                        body.rectangle.setSize(0);
                        if (lives > 0) {
                            if (random(100)+1 < game.storeScreen.carDurability) {
                                lives--;
                            }
                        }

                    }
                }
            }

            //If any bullet intersects any entity, entity is hidden
            //Find how to remove
        }
//        System.out.println(spiderValue);
        if (lives <= 0){
            //score = 0;
            ammo = 0;
            //spiderFrequency = 0;
            entity1.entities.clear();
            bullet2.bullets.clear();
        }
        if ((score < 10)&&(score >= 0)) {
            spiderFrequency = 4;
            spiderValue = 1;
        }
        else if (score < 25) {
            spiderFrequency = 5;
            spiderValue = 3;
        }
        else if (score < 75) {
            spiderFrequency = 6;
            spiderValue = 5;
        }
        else if (score < 150) {
            spiderFrequency = 7;
            spiderValue = 6;
        }
        else if (score < 250) {
            spiderFrequency = 8;
            spiderValue = 6;

        }
        else if (score < 500) {
            spiderFrequency = 9;
            spiderValue = 8;

        }
        else{
            spiderFrequency = 10;
            spiderValue = 9;

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
        if (rect.getWidth() == rlWH.x) {
            System.out.println("true1");
        }
        if (rect.getWidth() == udWH.x) {
            System.out.println("true2");
        }
        if (dir == Direction.RIGHT){
            game.batch.draw(car1R, rect.getX(), rect.getY(), rlWH.x, rlWH.y);
        }
        else if(dir == Direction.LEFT){
            game.batch.draw(car1L, rect.getX(), rect.getY(), rlWH.x, rlWH.y);
        }
        else if(dir == Direction.UP){
            game.batch.draw(car1U, rect.getX(), rect.getY(), udWH.x, udWH.y);
        }
        else if(dir == Direction.DOWN){
            game.batch.draw(car1D, rect.getX(), rect.getY(), udWH.x, udWH.y);
        }

        //batch.draw(img, barrier1.getX(), barrier1.getY(), barrier1.getWidth(), barrier1.getHeight());
        //batch.draw(bullet, rectBullet.getX(), rectBullet.getY(), rectBullet.getWidth(), rectBullet.getHeight());
        //batch.draw(img, rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
        font.draw(game.batch, totalMoneyLabel + game.storeScreen.getMoney(), 10, 110);
        font.draw(game.batch, livesLabel + lives, 10, 50);
        font.draw(game.batch, ammoLabel + ammo, 10, 80);
        font.draw(game.batch, scoreLabel + score, 10, 20);
        font.draw(game.batch, partsLabel + robotParts + "/3", 10, 140);
        game.batch.end();

        if(mainMenuButton.isPressed) {
            game.setScreen(game.mainMenuScreen);

        }
        if(storeButton.isPressed) {
            game.setScreen(game.storeScreen);

        }
    }
    public int random(int between){

        rand = new Random();
        randomBetween = rand.nextInt(between);
        //System.out.println(randomBetween);

        return randomBetween;
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {
        System.out.println("paused");
    }

    @Override
    public void resume() {
        System.out.println("resumed");
    }

    @Override
    public void hide() {
        System.out.println("hidden");
    }

    @Override
    public void dispose() {

    }
}



