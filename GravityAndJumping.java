// main java file that runs jpanel and jframe

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GravityAndJumping {
  JFrame gameWindow;
  GamePanel gamePanel;   
  MyKeyListener keyListener; 
  
  boolean leftHeld, rightHeld, downHeld, upHeld, spaceHeld, escpressed;
    
  // game variables  
  int level = 1;
  int shootDelay = Const.SHOOTDELAY;
  int iFrameCount = Const.IFRAMES; // every 10 frames they are allowed to take damage again
  // game objects
  Character player = new Character(Const.STARTX, Const.STARTY, Const.CHARW, Const.CHARH, Const.MOVE_SPEED, Const.JUMP_SPEED, "right", new Health(Const.STARTHP));
  Ammo ammo = new Ammo();
  Camera cam = new Camera(Const.STARTX);
  // make platforms for levels
  Platform[] platforms1 = Const.PLATFORMS1;
  Enemy[] enemies1 = {
    new Enemy(700, Const.GROUND-40, 60, 40, 6, "left", new Health(10), 10),
    new Enemy(400, Const.GROUND-20, 100, 20, 8, "right", new Health(100), 5),
    new Enemy(1400, Const.GROUND-30, 30, 30, 6, "left", new Health(30), 10),
    new Enemy(160, 60, 20, 40, 10, "left", new Health(20), 25),
    new Enemy(1600, 195, 5, 5, 6, "right", new Health(200), 34),
    new Enemy(300, -200, 100, 10, 5, "right", new Health(80), 10)
  };
  
//------------------------------------------------------------------------------
  GravityAndJumping(){
    gameWindow = new JFrame("hallucinations");
    gameWindow.setSize(Const.WIDTH,Const.HEIGHT);
    gameWindow.setMinimumSize(new Dimension(Const.WIDTH
                                              +Const.WIDTHOFFSET
                                              , Const.HEIGHT
                                              +Const.HEIGHTOFFSET
                                           ));
    gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    gameWindow.setResizable(false);
    
    gamePanel = new GamePanel();
    gameWindow.add(gamePanel); 
    
    keyListener = new MyKeyListener();
    gamePanel.addKeyListener(keyListener);
    
    gameWindow.setVisible(true);    
  }
//------------------------------------------------------------------------------  
  //main game loop
  public void run(){
    while (true) {
      gameWindow.repaint();
      try {Thread.sleep(Const.FRAME_PERIOD);} catch(Exception e){}
      
      if (level == 1) {
        // increase frame counters for shooting and iframes 
        shootDelay++;
        iFrameCount++;
        // move player left or right based on key presses
        if (leftHeld){player.moveLeft(platforms1);}
        if (rightHeld){player.moveRight(platforms1);}
        // move enemies left or right based on what platform edge or boundary they reached last
        for (Enemy enemy: enemies1) {
          if (enemy != null) {
            enemy.move(platforms1);
          }
        }
        // reduce player hp if they collide with an enemy
        for (Enemy enemy: enemies1) {
          if (enemy != null) {
            if (iFrameCount >= Const.IFRAMES && player.getHP() > 0 && player.collidesWith(enemy)) {
              iFrameCount = 0;
              if (player.getHP()-enemy.getDamage() < 0) {
                player.setHP(0);
              } else {
                player.setHP(player.getHP()-enemy.getDamage());
              }
            }
          }
        }
        // shoot a bullet from player if their ammo reload is done
        if (spaceHeld && shootDelay >= Const.SHOOTDELAY) {
          shootDelay = 0;
          ammo.addBullet(player);
        }
        // move all bullets and check for bullet collision with platforms or enemies
        ammo.moveBullets(platforms1, enemies1);
        // remove all dead enemies from game
        for (int enemy = 0; enemy < enemies1.length; enemy++) {
          if (enemies1[enemy] != null) {
            if (enemies1[enemy].getHP() <= 0) {
              enemies1[enemy] = null;
            }
          }
        }
        // change the % that the ammo reload bar has reached
        if (shootDelay/Const.SHOOTDELAY >= 1) {
          ammo.setReloadBarLength(50);
        } else {
          ammo.setReloadBarLength(50*shootDelay/Const.SHOOTDELAY);
        }
        // apply gravity and jumping to player
        player.applyGravity();
        player.moveY(platforms1);
        if (upHeld) {
          for (int p = 0; p < platforms1.length; p++) {
            if (player.isOnPlatform(platforms1[p])) {
              player.jump();
            }
          }
        }
        // apply gravity to enemies
        for (Enemy enemy: enemies1) {
          
        }
        // change where the camera is looking at
        cam.moveTo(player.getX());
      }
    }
  }
//------------------------------------------------------------------------------  
    //act upon key events
    public class MyKeyListener implements KeyListener{
        public void keyPressed(KeyEvent e){
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_A){
                leftHeld = true;
            }
            if (key == KeyEvent.VK_D){
                rightHeld = true;
            } 
            if (key == KeyEvent.VK_W){
                upHeld = true;
            }
            if (key == KeyEvent.VK_SPACE){
               spaceHeld = true;
            } 
            if (key == KeyEvent.VK_ESCAPE){
              System.exit(0);
            }
            if (key == KeyEvent.VK_P){
              System.out.println("x: " + player.getX() + " y: " + player.getY());
            }
        }
        public void keyReleased(KeyEvent e){ 
          int key = e.getKeyCode();
            if (key == KeyEvent.VK_A){
                leftHeld = false;
            } 
            if (key == KeyEvent.VK_D){
                rightHeld = false;
            } 
            if (key == KeyEvent.VK_W){
                upHeld = false;
            }
            if (key == KeyEvent.VK_SPACE){
                spaceHeld = false;
            } 
        }   
        public void keyTyped(KeyEvent e){
        }           
    }    
//------------------------------------------------------------------------------
    //draw everything
    public class GamePanel extends JPanel{
        GamePanel(){
            setFocusable(true);
            requestFocusInWindow();
        }
        
        @Override
        public void paintComponent(Graphics g){ 
          super.paintComponent(g); //required
          // draw player
          player.draw(cam, g);
          // draw enemies
          for (Enemy enemy: enemies1) {
            if (enemy != null) {
              enemy.draw(cam, g);
            }
          }
          // draw platforms
          for (int p = 0; p < platforms1.length; p++) {
            platforms1[p].draw(cam, g);
          }
          // draw enemies' hp bars
          for (Enemy enemy: enemies1) {
            if (enemy != null) {
              enemy.HP().draw(cam, g, enemy.getX(), enemy.getY(), enemy.getW(), enemy.getH());
            }
          }
          // draw player hp bar
          player.HP().draw(cam, g, player.getX(), player.getY(), player.getW(), player.getH());
          // draw bullets
          ammo.drawBullets(cam, g);
          // draw ammo refill bar
          ammo.draw(g);
          // draw line x=0
          g.setColor(Color.GRAY);
          g.drawLine(platforms1[0].getX(),0,platforms1[0].getX(),Const.HEIGHT);
        }    
    }    
//------------------------------------------------------------------------------
    public static void main(String[] args){
        System.out.println("Hello world!");
        GravityAndJumping demo = new GravityAndJumping();
        demo.run();
    }
}