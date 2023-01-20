// main java file that runs jpanel and jframe

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GravityAndJumping {
    JFrame gameWindow;
    GamePanel gamePanel;   
    MyKeyListener keyListener; 
    MyMouseListener mouseListener;
    Image bg = new ImageIcon("level 1 background.png").getImage();
    
    boolean leftHeld, rightHeld, downHeld, upHeld, spaceHeld, escpressed;
    
    // game variables  
    int framePeriod = Const.FRAME_PERIOD;
    int level = 1;
    int shootDelay = Const.SHOOTDELAY;
    int iFrameCount = Const.IFRAMES; // every 10 frames they are allowed to take damage again
    long startTime = System.currentTimeMillis();
    long currentTime = System.currentTimeMillis();
    long elapsedTime = currentTime - startTime;
    int timeLeftInSeconds = Const.STAGETIME-((int)elapsedTime/1000);
    // game objects
    Character player = new Character(Const.STARTX, Const.STARTY, Const.CHARW, Const.CHARH, Const.MOVE_SPEED, Const.JUMP_SPEED, "right", new Health(Const.STARTHP), "ghostplayer");
    Ammo ammo = new Ammo();
    Camera cam = new Camera(Const.STARTX);
    // make platforms for levels
    Platform[] platforms1 = Const.PLATFORMS1.clone();
    Enemy[] enemies1 = Const.ENEMIES1.clone();
    Platform[] platforms2 = Const.PLATFORMS2.clone();
    Enemy[] enemies2 = Const.ENEMIES2.clone();
    Platform[] endpoints = Const.ENDPOINTS.clone();
    
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
        
        mouseListener = new MyMouseListener();
        gamePanel.addMouseListener(mouseListener);
        
        gameWindow.setVisible(true);    
    }
//------------------------------------------------------------------------------  
    //main game loop
    public void run(){
      while (level == 1) {
        gameWindow.repaint();
        try {Thread.sleep(framePeriod);} catch(Exception e){}
        framePeriod = Const.FRAME_PERIOD;
        
        if (!player.isDead()) { // if player is NOT dead
          //-------------------------------------------- PER FRAME --------------------------------------------
          // update time
          currentTime = System.currentTimeMillis();
          elapsedTime = currentTime - startTime;
          timeLeftInSeconds = Const.STAGETIME-((int)elapsedTime/1000);
          
          // increase frame counters for shooting and iframes 
          shootDelay++;
          iFrameCount++;
          
          //-------------------------------------------- MOVE --------------------------------------------
          // move player left or right based on key presses
          if (leftHeld){player.moveLeft(platforms1);}
          if (rightHeld){player.moveRight(platforms1);}
          // move enemies left or right based on what platform edge or boundary they reached last
          for (Enemy enemy: enemies1) {
            if (enemy != null) {
              enemy.move(platforms1);
            }
          }
          
          //-------------------------------------------- REDUCE HP --------------------------------------------
          // reduce player hp if they collide with an enemy
          for (Enemy enemy: enemies1) {
            if (enemy != null) {
              if (iFrameCount >= Const.IFRAMES && player.getHP() > 0 && player.collidesWith(enemy)) {
                iFrameCount = 0;
                if (player.getHP()-enemy.getDamage() <= 0) {
                  player.setHP(0);
                } else {
                  player.setHP(player.getHP()-enemy.getDamage());
                }
              }
            }
          }
          // reduce player hp if they fall through the bottom of the screen
          if (player.getY() > Const.HEIGHT+50) {
            player.setHP(player.getHP()-20);
            if (player.getHP() <= 0) {
              player.setHP(0);
            } else {
              player.setX(Const.STARTX);
              player.setY(-100);
            }
          }
          // set player hp to 0 if they run out of time
          if (timeLeftInSeconds < 0) {
            player.setHP(0);
          }
          // checks if player is dead; if yes, then execute death sequence
          if (player.isDead()) {
            framePeriod = 1000;
            player.setVelY(-12);
          }
          
          //-------------------------------------------- BULLETS --------------------------------------------
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
              if (enemies1[enemy].isDead()) {
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
          
          //-------------------------------------------- GRAVITY --------------------------------------------
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
            if (enemy != null) {
              enemy.applyGravity();
              enemy.moveY(platforms1);
            }
          }
          
          //-------------------------------------------- OTHER --------------------------------------------
          // change where the camera is looking at
          cam.moveTo(player.getX());
          // if player reached end point of stage
          if (player.collidesWith(endpoints[0])) {
            level = 2;
            player.setX(Const.STARTX);
            player.setY(Const.STARTY);
            for (int b = 0; b<ammo.getMag().length; b++) {
              ammo.removeBullet(b);
            }
            startTime = System.currentTimeMillis();
            currentTime = System.currentTimeMillis();
            elapsedTime = currentTime - startTime;
            timeLeftInSeconds = Const.STAGETIME-((int)elapsedTime/1000);
          }
          
        } else { // else (player is dead)
          // make the ghost more and more transparent
          if (true) {
            gameWindow.repaint();
            try {Thread.sleep(framePeriod);} catch(Exception e){}
            framePeriod = Const.FRAME_PERIOD;
          }
        }
      }
      
      while (level == 2) {
        gameWindow.repaint();
        try {Thread.sleep(framePeriod);} catch(Exception e){}
        framePeriod = Const.FRAME_PERIOD;
        
        if (!player.isDead()) {
          //-------------------------------------------- PER FRAME --------------------------------------------
          // update time
          currentTime = System.currentTimeMillis();
          elapsedTime = currentTime - startTime;
          timeLeftInSeconds = Const.STAGETIME-((int)elapsedTime/1000);
          // increase frame counters for shooting and iframes 
          shootDelay++;
          iFrameCount++;
          
          //-------------------------------------------- MOVE --------------------------------------------
          // move player left or right based on key presses
          if (leftHeld){player.moveLeft(platforms2);}
          if (rightHeld){player.moveRight(platforms2);}
          // move enemies left or right based on what platform edge or boundary they reached last
          for (Enemy enemy: enemies2) {
            if (enemy != null) {
              enemy.move(platforms2);
            }
          }
          
          //-------------------------------------------- REDUCE HP --------------------------------------------
          // reduce player hp if they collide with an enemy
          for (Enemy enemy: enemies2) {
            if (enemy != null) {
              if (iFrameCount >= Const.IFRAMES && player.getHP() > 0 && player.collidesWith(enemy)) {
                iFrameCount = 0;
                if (player.getHP()-enemy.getDamage() <= 0) {
                  player.setHP(0);
                  framePeriod = 1000;
                  player.setVelY(-12);
                  player.isDead();
                } else {
                  player.setHP(player.getHP()-enemy.getDamage());
                }
              }
            }
          }
          // reduce player hp if they fall through the bottom of the screen
          if (player.getY() > Const.HEIGHT) {
            player.setHP(player.getHP()-20);
            if (player.getHP() <= 0) {
              framePeriod = 1000;
              player.setVelY(-12);
              player.isDead();
            } else {
              player.setX(Const.STARTX);
              player.setY(-100);
            }
          }
          // set player hp to 0 if they run out of time
          if (timeLeftInSeconds < 0) {
            framePeriod = 1000;
            player.setVelY(-12);
            player.isDead();
          }
          
          //-------------------------------------------- BULLETS --------------------------------------------
          // shoot a bullet from player if their ammo reload is done
          if (spaceHeld && shootDelay >= Const.SHOOTDELAY) {
            shootDelay = 0;
            ammo.addBullet(player);
          }
          // move all bullets and check for bullet collision with platforms or enemies
          ammo.moveBullets(platforms2, enemies2);
          // remove all dead enemies from game
          for (int enemy = 0; enemy < enemies2.length; enemy++) {
            if (enemies2[enemy] != null) {
              if (enemies2[enemy].isDead()) {
                enemies2[enemy] = null;
              }
            }
          }
          // change the % that the ammo reload bar has reached
          if (shootDelay/Const.SHOOTDELAY >= 1) {
            ammo.setReloadBarLength(50);
          } else {
            ammo.setReloadBarLength(50*shootDelay/Const.SHOOTDELAY);
          }
          
          //-------------------------------------------- GRAVITY --------------------------------------------
          // apply gravity and jumping to player
          player.applyGravity();
          player.moveY(platforms2);
          if (upHeld) {
            for (int p = 0; p < platforms2.length; p++) {
              if (player.isOnPlatform(platforms2[p])) {
                player.jump();
              }
            }
          }
          // apply gravity to enemies
          for (Enemy enemy: enemies2) {
            if (enemy != null) {
              enemy.applyGravity();
              enemy.moveY(platforms2);
            }
          }
          
          //-------------------------------------------- OTHER --------------------------------------------
          // change where the camera is looking at
          cam.moveTo(player.getX());
          // if player reached end point of stage
          
        } else { // else (player is dead)
          // make the ghost more and more transparent
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
                System.out.println("px: " + player.getX() + " py: " + player.getY());
            }
            if (key == KeyEvent.VK_R){
              restartLevel();
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
    public void restartLevel() {
      player.setX(Const.STARTX);
      player.setY(Const.STARTY);
      player.setVelY(0);
      player.setHP(Const.STARTHP);
      for (int b = 0; b<ammo.getMag().length; b++) {
        ammo.removeBullet(b);
      }
      startTime = System.currentTimeMillis();
      if (level == 1) {
        platforms1 = Const.PLATFORMS1.clone();
        enemies1 = Const.ENEMIES1.clone();
      } else if (level == 2) {
        platforms2 = Const.PLATFORMS2.clone();
        enemies2 = Const.ENEMIES2.clone();
      }
      // else if (level == 3) {
      //   platforms3 = Const.PLATFORMS3;
      //   enemies3 = Const.ENEMIES3;
      // }
    }
//------------------------------------------------------------------------------
    // mouse inputs
    public class MyMouseListener implements MouseListener{
        public void mouseClicked(MouseEvent e){   // gets the mouse coordinates
            int mouseX = e.getX();
            int mouseY = e.getY();
            System.out.println(mouseX + " " + mouseY);
        }
        public void mousePressed(MouseEvent e){   // MUST be implemented even if not used!
        }
        public void mouseReleased(MouseEvent e){  // MUST be implemented even if not used!
        }
        public void mouseEntered(MouseEvent e){   // MUST be implemented even if not used!
        }
        public void mouseExited(MouseEvent e){    // MUST be implemented even if not used!
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
        public void paintComponent(Graphics g) {
            super.paintComponent(g); //required
            Graphics2D g2d = (Graphics2D)g;
            // level 1
            if (level == 1) {
              if (!player.isDead()) {
                // draw background
                g2d.drawImage(bg, 0, 0, Const.WIDTH, Const.HEIGHT, null);
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
                // draw text
                g.setColor(Color.BLACK);
                g.setFont(Const.TEXTFONT);
                g.drawString("Time:", Const.WIDTH-100, 40);
                g.drawString(Integer.toString(timeLeftInSeconds), Const.WIDTH-87, 80);
                // draw line x=0
                g.setColor(Color.GRAY);
                g.drawLine(platforms1[0].getX(),0,platforms1[0].getX(),Const.HEIGHT);
              } else {
                g.setColor(Color.BLACK);
                g.fillRect(0,0,Const.WIDTH, Const.HEIGHT);
                g2d.drawImage(bg, 0, 0, Const.WIDTH, Const.HEIGHT, null);
                g.setColor(Color.WHITE);
                g.setFont(Const.TEXTFONT);
                g.drawString("You died!", Const.WIDTH/2 - 80, Const.HEIGHT/2-40);
                g.drawString("Press R to restart", Const.WIDTH/2-145, Const.HEIGHT/2);
                g.drawString("Press ESC to quit", Const.WIDTH/2-142, Const.HEIGHT/2+40);
              }
            }
            if (level == 2) {
              if (!player.isDead()) {
                // draw background
                g2d.drawImage(bg, 0, 0, Const.WIDTH, Const.HEIGHT, null);
                // draw player
                player.draw(cam, g);
                // draw enemies
                for (Enemy enemy: enemies2) {
                  if (enemy != null) {
                    enemy.draw(cam, g);
                  }
                }
                // draw platforms
                for (int p = 0; p < platforms2.length; p++) {
                  platforms2[p].draw(cam, g);
                }
                // draw enemies' hp bars
                for (Enemy enemy: enemies2) {
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
                // draw text
                g.setColor(Color.BLACK);
                g.setFont(Const.TEXTFONT);
                g.drawString("Time:", Const.WIDTH-100, 40);
                g.drawString(Integer.toString(timeLeftInSeconds), Const.WIDTH-87, 80);
                // draw line x=0
                g.setColor(Color.GRAY);
                g.drawLine(platforms2[0].getX(),0,platforms1[0].getX(),Const.HEIGHT);
              } else {
                g.setColor(Color.BLACK);
                g.fillRect(0,0,Const.WIDTH, Const.HEIGHT);
//                g2d.drawImage(bg, 0, 0, Const.WIDTH, Const.HEIGHT, null);
                g.setColor(Color.WHITE);
                g.setFont(Const.TEXTFONT);
                g.drawString("You died!", Const.WIDTH/2 - 80, Const.HEIGHT/2-40);
                g.drawString("Press R to restart", Const.WIDTH/2-145, Const.HEIGHT/2);
                g.drawString("Press ESC to quit", Const.WIDTH/2-142, Const.HEIGHT/2+40);
              }
            }
        }
    }
//------------------------------------------------------------------------------
    public static void main(String[] args){
        System.out.println("Hello world!");
        GravityAndJumping demo = new GravityAndJumping();
        demo.run();
    }
}