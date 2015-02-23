package org.team1515.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.graphics.g2d.Sprite;
import org.team1515.entities.ButtonCustom;
import org.team1515.sludge.Main;


/**
 * Created by azadeh2 on 2/6/2015.
 */
public class StoreScreen implements Screen {

    boolean hasSpeed;
    int carDurability;
    String title;
    BitmapFont font;
    BitmapFont fontMoney;
    BitmapFont fontRegular;
    Main game;
    int totalMoney;
    String totalMoneyLabel;
    int startingLives;
    String livesPackTier2Label;
    String livesPackTierStatus;
    Vector2 ammoPackTier;//20-10
    String multiplierPackTierStatus;
    int multiplierAmount;
    boolean hasLaser;
    ButtonCustom buyBreaks;
    Rectangle buyButtonShape;
    Texture buyButtonTexture;
    ButtonCustom buyDurability;
    ButtonCustom buyMultiplier;
    ButtonCustom buyLives;
    ButtonCustom buyAmmo;
    ButtonCustom buyLaser;
    Sprite backgroundStore;
    Texture storeTitleImg;


    //ADD Default bullet doesn't go through all spider, infinity bullet does!
    //ADD Lives tier packs!
    //ADD Speed upgrade!
    //ADD Ability to manually spawn in spiders upgrade
    //ADD in game, spiders move faster as game moves on
    //ADD car durability
    //ADD probability for live gains in game upgrade
    //GET natural moving code from other computer
    //ADD upgrades that affect spider movement (turning, speed, spawning)
    //ADD in game, RARE money drops for quick money boosts and excitement!
    //ADD upgrade to increase rare money drop probability
    //ADD score multiplier upgrade!!!!!! (Double money)
    //ADD ability to buy robot parts


    //Ammo Tier Pack 2 - $300
    //Lives Tier Pack 2 - $300
    String ammoPackTier2Label;
    String ammoPackTierStatus;
    String laserBulletStatus;
    String speedPackLabel;
    String speedPackStatus;
    String carDurabilityTier2Label;
    String carDurabilityTierStatus;
    int purchaseReload;
    ButtonCustom mainMenuButton;
    ButtonCustom playButton;



    public StoreScreen(Main game){
        totalMoney = 0;
        this.game = game;
        title = "Store";
        font = new BitmapFont();
        fontMoney = new BitmapFont();
        totalMoneyLabel = "You have: $";
        ammoPackTier = new Vector2(20, 10); //20-10
        startingLives = 5;
        fontRegular = new BitmapFont();
        //ammoPackTier2Label = "Ammo Pack: "+ ammoPackTierStatus+" - Start with 30 bullets and get 15 per ammo pack!";
        ammoPackTierStatus = "Tier 2 ($300 A) - Start with 30 bullets, 15 per ammo pack!";
        //livesPackTier2Label = "Lives Pack: "+ livesPackTierStatus +" - Start with 7 lives instead of 5!";
       // speedPackLabel = "Speed Pack ("+ speedPackStatus +") - Press SHIFT to enable faster speeds!";
        speedPackStatus = "($3000 S) - Press SHIFT to stop your car completely!";
        livesPackTierStatus = "Tier 2 ($300 L) - Start with 7 lives instead of 5!";
        //carDurabilityTier2Label = "Car Durability: " + carDurabilityTierStatus + " - 30% chance a spider won't take a life!";
        carDurabilityTierStatus = "Tier 2 ($500 D) - 30% chance a spider won't take a life!";
        multiplierPackTierStatus = "Tier 2 ($2000 F) - Receive $2 for every 1 point scored!";
        laserBulletStatus = "($3000 G) - A bullet that passes though all spiders, killing them! ";
        hasSpeed = false;
        buyButtonTexture = new Texture("buyButton1.png");
        backgroundStore = new Sprite(new Texture("backStore1.png"));
        buyBreaks = new ButtonCustom(new Rectangle(5, Gdx.graphics.getHeight() - 190, 50, 19), buyButtonTexture);
        buyDurability = new ButtonCustom(new Rectangle(5, Gdx.graphics.getHeight() - 230, 50, 19), buyButtonTexture); ;
        buyMultiplier = new ButtonCustom(new Rectangle(5, Gdx.graphics.getHeight() - 350, 50, 19), buyButtonTexture);;
        buyLives = new ButtonCustom(new Rectangle(5, Gdx.graphics.getHeight() - 270, 50, 19), buyButtonTexture);;
        buyAmmo = new ButtonCustom(new Rectangle(5, Gdx.graphics.getHeight() - 310, 50, 19), buyButtonTexture);;
        buyLaser = new ButtonCustom(new Rectangle(5, Gdx.graphics.getHeight() - 390, 50, 19), buyButtonTexture);;
        purchaseReload = 180;
        carDurability = 100;
        multiplierAmount = 1;
        storeTitleImg = new Texture("storeTitle1.png");
        mainMenuButton = new ButtonCustom(new Rectangle(5, Gdx.graphics.getHeight() - 110, 100, 100), new Texture("menuButton2.png"));
        playButton = new ButtonCustom(new Rectangle(Gdx.graphics.getWidth() - 180, Gdx.graphics.getHeight() - 70, 180, 60), new Texture("playButton1.png"));




    }
    public void setMoney(int x){
        totalMoney = x;
    }
    public int getMoney(){
        return totalMoney;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if (purchaseReload > 0){
            purchaseReload--;
        }

        if(playButton.isPressed) {
            game.setScreen(game.gameScreen);
            pause();
        }
        if(mainMenuButton.isPressed) {
            game.setScreen(game.mainMenuScreen);

        }
        if(Gdx.input.isKeyPressed(Input.Keys.NUM_2)) {
            game.setScreen(game.storeScreen);

        }



        if(buyAmmo.isPressed&&(totalMoney >= 300)&&ammoPackTier.x == 20) {
            totalMoney -=300;
            ammoPackTier.x = 30;
            ammoPackTier.y = 15;
            ammoPackTierStatus = "Tier 3 ($600 Z) - Start with 40 bullets, 20 per ammo pack!";
            purchaseReload = 180;
        }
        if(buyAmmo.isPressed&&(totalMoney >= 600)&&ammoPackTier.x == 30&& purchaseReload == 0) {
            totalMoney -=600;
            ammoPackTier.x = 40;
            ammoPackTier.y = 20;
            ammoPackTierStatus = "Tier 4 (Coming Soon!)";

        }



        if(buyLives.isPressed&&(totalMoney >= 300)&&startingLives == 5) {
            totalMoney -=300;
            startingLives = 7;
            livesPackTierStatus = "Tier 3 ($1000 M) - Start with 10 lives instead of 7!";
            purchaseReload = 180;
        }
        if(buyLives.isPressed&&(totalMoney >= 1000)&&startingLives == 7&&purchaseReload == 0) {
            totalMoney -=1000;
            startingLives = 10;
            livesPackTierStatus = "Tier 4 (Coming Soon!)";
        }





        if(buyDurability.isPressed&&(totalMoney >= 500)&&carDurability==100) {
            totalMoney -=500;
            carDurability = 70;
            carDurabilityTierStatus = "Tier 3 ($1000 C) - 50% chance a spider won't take a life!";
            purchaseReload = 180;
        }
        if(buyDurability.isPressed&&(totalMoney >= 1000)&&carDurability==70&&purchaseReload == 0) {
            totalMoney -=1000;
            carDurability = 50;
            carDurabilityTierStatus = "Tier 4 (Coming Soon!)";
        }




        if(buyMultiplier.isPressed&&(totalMoney >= 2000)&&multiplierAmount==1) {
            totalMoney -=2000;
            multiplierAmount = 2;
            multiplierPackTierStatus = "Tier 3 (Coming Soon!)";
            purchaseReload = 180;
        }



        if(buyLaser.isPressed&&(totalMoney >= 3000)&&!hasLaser) {
            totalMoney -=3000;
            hasLaser = true;
            laserBulletStatus = "(You have this item!)";
        }




        if (buyBreaks.isPressed&&(totalMoney >= 500)&&!hasSpeed){
            totalMoney -=500;
            hasSpeed = true;
            speedPackStatus = "(You have this item!)";
        }






        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_0)&&Gdx.input.isKeyPressed(Input.Keys.NUM_0)){

            totalMoney+=300;
        }







        Gdx.gl.glClearColor(0, .75f, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();

        game.batch.draw(backgroundStore.getTexture(), 0 , 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        game.batch.draw(storeTitleImg, Gdx.graphics.getWidth()/2 - 150, Gdx.graphics.getHeight()-110, 300, 70);


        buyBreaks.render(game.batch);
        buyAmmo.render(game.batch);
        buyDurability.render(game.batch);
        buyLives.render(game.batch);
        buyMultiplier.render(game.batch);
        buyLaser.render(game.batch);
        mainMenuButton.render(game.batch);
        playButton.render(game.batch);

        //font.draw(game.batch, title, Gdx.graphics.getWidth()/2 - 80, Gdx.graphics.getHeight()-30);
        fontMoney.draw(game.batch, totalMoneyLabel + totalMoney, Gdx.graphics.getWidth()/2 - 95, Gdx.graphics.getHeight()-100);
        fontRegular.draw(game.batch, "Ammo Pack: "+ ammoPackTierStatus, 70, Gdx.graphics.getHeight()-290 );
        fontRegular.draw(game.batch, "Lives Pack: "+ livesPackTierStatus, 70, Gdx.graphics.getHeight()-250 );
        fontRegular.draw(game.batch, "Break Pads "+ speedPackStatus +"", 70, Gdx.graphics.getHeight()-170 );
        fontRegular.draw(game.batch, "Car Durability: " + carDurabilityTierStatus, 70, Gdx.graphics.getHeight()-210 );
        fontRegular.draw(game.batch, "Score Multiplier: " + multiplierPackTierStatus, 70, Gdx.graphics.getHeight()-330 );
        fontRegular.draw(game.batch, "Laser Bullets " + laserBulletStatus, 70, Gdx.graphics.getHeight()-370 );
        //game.batch.draw(buyButtonTexture, 50, 50, 50, 19);



        game.batch.end();

        font.setScale(5);
        fontMoney.setScale(2);
        fontRegular.setScale(1.5f);

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
