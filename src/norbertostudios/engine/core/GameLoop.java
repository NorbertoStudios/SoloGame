package norbertostudios.engine.core;////

import norbertostudios.Debugging;
import norbertostudios.engine.MainCamera;
import norbertostudios.engine.Tiles.BgroundTileMap;
import norbertostudios.engine.Tiles.ForegroundTileMap;
import norbertostudios.engine.Tiles.GroundTileMap;
import norbertostudios.engine.assets.Assets;
import norbertostudios.engine.assets.Vector2d;
import norbertostudios.engine.gameObjects.Player;
import norbertostudios.engine.gameObjects.enemy.GreenSlime;
import norbertostudios.engine.hud.HUD;
import norbertostudios.engine.hud.Message;
import norbertostudios.engine.input.InputManager;
import norbertostudios.engine.util.Constants;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

////    Created     12/1/19, 12:29 PM
////    By:         Norberto Studios
////    
public class GameLoop extends GameBase {

    private InputManager inputManager;

    private GameAction moveLeft;
    private GameAction moveRight;
    private GameAction jump;
    private GameAction exit;

    private GroundTileMap groundTileMap;
    private ForegroundTileMap foregroundTileMap;
    private BgroundTileMap bGroundTileMap;

    private HUD hud;
    public int score = 0;

    public static Player player;
    public static GreenSlime slime;

    private Debugging debugging;


    private ArrayList<Message> messages = new ArrayList<Message>();


    public ArrayList<Message> getMessages() {
        return messages;
    }


    @Override
    protected void gameInit() {

        //  Assets.init(); // Init the game assets
        bGroundTileMap = Assets.bGroundTileMap;
        groundTileMap = Assets.groundTileMap;
        foregroundTileMap = Assets.foregroundTileMap;

        initInput(); // init the game input

        screen.getFullScreenWindow().getGraphicsConfiguration();


        hud = new HUD();

        player = new Player(Assets.player,
                new Vector2d(100, -50), new Vector2d());

        slime = new GreenSlime(Assets.enemiesHashMap.get(0), new Vector2d(1620,580), new Vector2d());
        //slime = new GreenSlime(Assets.enemiesHashMap.get(0), new Vector2d(1942,901), new Vector2d());

//        debugging = new Debugging();

//        messages.add(new Message(new Vector2d(Constants.WIDTH/2,Constants.HEIGHT/2), false,
//                "Start", Color.WHITE,true,Assets.fontBig,this));
    }

    @Override
    protected void gameUpdate(long elapsedTime) {
        bGroundTileMap.update();
        groundTileMap.update();
        foregroundTileMap.update();

        checkInput();
        player.update(elapsedTime);
        slime.update(elapsedTime);

//        debugging.debugUpdate();
        checkCollisionWithPlayer();
    }


    public void checkCollisionWithPlayer()
    {
//

        if(player.bottomRecCollision().intersects(slime.topRecCollision()) )
        {
            System.out.println("_________COLLISION WITH ENEMY on top");
            System.out.println("___ ENEMY Dead____");
            slime.setVelocity(new Vector2d());
            slime.walking = 0;
            hud.addScore(40, slime.getCenter(),this);
            slime.setDeadState(true);

            player.jump();
            // disable the image
           // slime = new GreenSlime(Assets.enemiesHashMap.get(0), new Vector2d(-1620,-580), new Vector2d());

        }

        if((player.rightRecCollision().intersects(slime.leftRecCollision())
                || player.leftRecCollision().intersects(slime.rightRecCollision()))
                && !slime.deadState)
        {
            System.out.println("________----------- Player Dead ___-----");
            playerDead();
        }
    }



    @Override
    protected void gameRender(Graphics2D g) {
//        // System.out.println("Rendering!!");
//
//        for (int i = 0; i < 3; i++) {

        g.drawImage(Assets.background, -MainCamera.x, 0, 2048, 2048, null);
//        }

        bGroundTileMap.draw(g);
        groundTileMap.draw(g);
        foregroundTileMap.draw(g);
        slime.draw(g);
        player.draw(g);


        for (int i = 0; i < messages.size(); i++) {
            messages.get(i).draw(g);
        }

        hud.drawScore(g);
        hud.drawLives(g);

//        debugging.debugDraw(g);

    }

    private void initInput() {
        moveLeft = new GameAction("moveLeft");
        moveRight = new GameAction("moveRight");
        jump = new GameAction("jump", Constants.DETECT_INITIAL_PRESS_ONLY);
        exit = new GameAction("exit", Constants.DETECT_INITIAL_PRESS_ONLY);

        inputManager = new InputManager(screen.getFullScreenWindow());

        inputManager.mapToKey(moveLeft, KeyEvent.VK_LEFT);
        inputManager.mapToKey(moveRight, KeyEvent.VK_RIGHT);
        inputManager.mapToKey(jump, KeyEvent.VK_SPACE);
        inputManager.mapToKey(exit, KeyEvent.VK_ESCAPE);

    }

    private void checkInput() {
        if (exit.isPressed()) {
            System.out.println("I Have Pressed " + exit.getName());
            stop();
        }

        if (player.isAlive()) {
            float velocityX = 0;

            if (velocityX == 0.0) {
                player.idle();
                player.walking = 0;
            }

            if (moveLeft.isPressed()) {

                player.walking = -1;

                if (MainCamera.offsetMinX <= player.getPosition().getX()) {
                    velocityX -= player.getMaxSpeed();
                } else {
                    if (MainCamera.x == 0) {
                        velocityX -= player.getMaxSpeed();
                    } else {

                        MainCamera.moveLeft(2);
                    }
                }
                //System.out.println("Im Walking left");
                player.walk();

            }
            if (moveRight.isPressed()) {

                player.walking = 1;

                if (MainCamera.offsetMaxX >= player.getPosition().getX()) {
                    velocityX += player.getMaxSpeed();

                } else {
                    MainCamera.moveRight(2);
                }
                // System.out.println("Im Walking Right");
                player.walk();

            }
            if (jump.isPressed()) {
                System.out.println("Im Jumping");
//            player.jumpAnim();
                player.setOnGround(true);
                player.jump();

            }
            player.setVelocity(new Vector2d(velocityX, player.getVelocity().getY()));

        }

        // Dead Player
        if (player.getPosition().getY() > Constants.HEIGHT + 100) {

            playerDead();
        }
    }


    public void playerDead()
    {
        hud.delLives();
        if(hud.getLives() <= 0)
        {
            stop();
        }

        player.deadAndRespawn();
        player = new Player(Assets.player,
                new Vector2d(100, -50), new Vector2d());
    }
}
