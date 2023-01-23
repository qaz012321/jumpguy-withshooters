// main java file that runs jpanel and jframe and stuff
// @author Daniel Liu & Galton Ma
// @version Jan 22 2023 - 1.3.5

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GravityAndJumping {
    JFrame gameWindow;
    JFrame frame;
    GamePanel gamePanel;   
    MyKeyListener keyListener; 
    MyMouseListener mouseListener;
    Image bg = new ImageIcon("level 1 background.png").getImage();
    int bgx1 = Const.STARTX;
    int bgx2 = Const.STARTX-Const.WIDTH;
    
    boolean leftHeld, rightHeld, downHeld, upHeld, spaceHeld, escpressed;
    
    // game variables  
    int framePeriod = Const.FRAME_PERIOD;
    int level; // unassigned value for level
    int shootDelay = Const.SHOOTDELAY;
    int iFrameCount = Const.IFRAMES; // every 10 frames they are allowed to take damage again
    long startTime = System.currentTimeMillis();
    long currentTime = System.currentTimeMillis();
    long elapsedTime = currentTime - startTime;
    int timeLeftInSeconds = Const.STAGETIME-((int)elapsedTime/1000);
    int deathCounter = 0; // counts death for decreasing max timer time
    // game objects
    Character player = new Character(Const.STARTX, Const.STARTY, Const.CHARW, Const.CHARH, 
                                     Const.MOVE_SPEED, Const.JUMP_SPEED, "right", new Health(Const.STARTHP), "ghostplayer");
    Ammo ammo = new Ammo();
    Camera cam = new Camera(Const.STARTX);
    // make platforms for levels
    Platform[] platforms1;
    Enemy[] enemies1;
    Platform[] platforms2;
    Enemy[] enemies2;
    Platform[] platforms3;
    Enemy[] enemies3;
    Platform[] endpoints;
    
//------------------------------------------------------------------------------
    GravityAndJumping(int lvl){
        // level is dependent on button pressed
        level = lvl;
        // make platforms for levels
        platforms1 = Const.PLATFORMS1.clone();
        enemies1 = Const.ENEMIES1.clone();
        platforms2 = Const.PLATFORMS2.clone();
        enemies2 = Const.ENEMIES2.clone();
        platforms3 = Const.PLATFORMS3.clone();
        enemies3 = Const.ENEMIES3.clone();
        endpoints = Const.ENDPOINTS.clone();
        
        gameWindow = new JFrame("hallucinations");
        gameWindow.setSize(Const.WIDTH,Const.HEIGHT);
        gameWindow.setMinimumSize(new Dimension(Const.WIDTH
                                                  +Const.WIDTHOFFSET
                                                  , Const.HEIGHT
                                                  +Const.HEIGHTOFFSET
                                               ));
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.setResizable(false);
        
        gameWindow.add(new Frame());
        
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
      //---------------------//---------------------// LEVEL 1 //---------------------//---------------------//
      while (level == 1) {
        gameWindow.repaint();
        try {Thread.sleep(framePeriod);} catch(Exception e){}
        framePeriod = Const.FRAME_PERIOD;
        
        if (!player.isDead()) { // if player is NOT dead
          //-------------------------------------------- PER FRAME --------------------------------------------
          // update time
          currentTime = System.currentTimeMillis();
          elapsedTime = currentTime - startTime;
          if (Const.STAGETIME-5*deathCounter < 50) {
            timeLeftInSeconds = (50)-((int)elapsedTime/1000);
          } else {
            timeLeftInSeconds = (Const.STAGETIME-5*deathCounter)-((int)elapsedTime/1000);
          }
          // increase frame counters for shooting and iframes 
          shootDelay++;
          iFrameCount++;
          
          //-------------------------------------------- MOVE --------------------------------------------
          // move player left or right based on key presses
          if (leftHeld){player.moveLeft(platforms1);}
          if (rightHeld){player.moveRight(platforms1);}
          // replace background if moving right or moving left (scrolling bg)
          if (bgx1-cam.anchorX() <= 0) {
            bgx2 = bgx1 + Const.WIDTH;
          }
          if (bgx2-cam.anchorX() <= 0) {
            bgx1 = bgx2 + Const.WIDTH;
          }
          if (bgx1-cam.anchorX() >= Const.WIDTH) {
            bgx1 = bgx2 - Const.WIDTH;
          }
          if (bgx2-cam.anchorX() >= Const.WIDTH) {
            bgx2 = bgx1 - Const.WIDTH;
          }
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
            player.setHP(player.getHP()-10);
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
            player.setMaxHP(player.getHP());
            for (int b = 0; b<ammo.getMag().length; b++) {
              ammo.removeBullet(b);
            }
            startTime = System.currentTimeMillis();
            currentTime = System.currentTimeMillis();
            elapsedTime = currentTime - startTime;
            timeLeftInSeconds = Const.STAGETIME-((int)elapsedTime/1000);
          }
          
        }
      }
      
      //---------------------//---------------------// LEVEL 2 //---------------------//---------------------//
      while (level == 2) {
        gameWindow.repaint();
        try {Thread.sleep(framePeriod);} catch(Exception e){}
        framePeriod = Const.FRAME_PERIOD;
        
        if (!player.isDead()) {
          //-------------------------------------------- PER FRAME --------------------------------------------
          // update time
          currentTime = System.currentTimeMillis();
          elapsedTime = currentTime - startTime;
          if (Const.STAGETIME-5*deathCounter < 50) {
            timeLeftInSeconds = (50)-((int)elapsedTime/1000);
          } else {
            timeLeftInSeconds = (Const.STAGETIME-5*deathCounter)-((int)elapsedTime/1000);
          }
          // increase frame counters for shooting and iframes 
          shootDelay++;
          iFrameCount++;
          
          //-------------------------------------------- MOVE --------------------------------------------
          // move player left or right based on key presses
          if (leftHeld){player.moveLeft(platforms2);}
          if (rightHeld){player.moveRight(platforms2);}
          // replace background if moving right or moving left (scrolling bg)
          if (bgx1-cam.anchorX() <= 0) {
            bgx2 = bgx1 + Const.WIDTH;
          }
          if (bgx2-cam.anchorX() <= 0) {
            bgx1 = bgx2 + Const.WIDTH;
          }
          if (bgx1-cam.anchorX() >= Const.WIDTH) {
            bgx1 = bgx2 - Const.WIDTH;
          }
          if (bgx2-cam.anchorX() >= Const.WIDTH) {
            bgx2 = bgx1 - Const.WIDTH;
          }
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
                } else {
                  player.setHP(player.getHP()-enemy.getDamage());
                }
              }
            }
          }
          // reduce player hp if they fall through the bottom of the screen
          if (player.getY() > Const.HEIGHT) {
            player.setHP(player.getHP()-10);
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
          if (player.collidesWith(endpoints[1])) {
            level = 3;
            player.setX(Const.STARTX);
            player.setY(Const.STARTY);
            player.setMaxHP(player.getHP());
            for (int b = 0; b<ammo.getMag().length; b++) {
              ammo.removeBullet(b);
            }
            startTime = System.currentTimeMillis();
            currentTime = System.currentTimeMillis();
            elapsedTime = currentTime - startTime;
            timeLeftInSeconds = Const.STAGETIME-((int)elapsedTime/1000);
          }
        }
      }
      
      //---------------------//---------------------// LEVEL 3 //---------------------//---------------------//
      while (level == 3) {
        gameWindow.repaint();
        try {Thread.sleep(framePeriod);} catch(Exception e){}
        framePeriod = Const.FRAME_PERIOD;
        
        if (!player.isDead()) {
          //-------------------------------------------- PER FRAME --------------------------------------------
          // update time
          currentTime = System.currentTimeMillis();
          elapsedTime = currentTime - startTime;
          if (Const.STAGETIME-5*deathCounter < 50) {
            timeLeftInSeconds = (50)-((int)elapsedTime/1000);
          } else {
            timeLeftInSeconds = (Const.STAGETIME-5*deathCounter)-((int)elapsedTime/1000);
          }
          // increase frame counters for shooting and iframes 
          shootDelay++;
          iFrameCount++;
          
          //-------------------------------------------- MOVE --------------------------------------------
          // move player left or right based on key presses
          if (leftHeld) {player.moveLeft(platforms3);}
          if (rightHeld) {player.moveRight(platforms3);}
          // replace background if moving right or moving left (scrolling bg)
          if (bgx1-cam.anchorX() <= 0) {
            bgx2 = bgx1 + Const.WIDTH;
          }
          if (bgx2-cam.anchorX() <= 0) {
            bgx1 = bgx2 + Const.WIDTH;
          }
          if (bgx1-cam.anchorX() >= Const.WIDTH) {
            bgx1 = bgx2 - Const.WIDTH;
          }
          if (bgx2-cam.anchorX() >= Const.WIDTH) {
            bgx2 = bgx1 - Const.WIDTH;
          }
          // move enemies left or right based on what platform edge or boundary they reached last
          for (Enemy enemy: enemies3) {
            if (enemy != null) {
              enemy.move(platforms3);
            }
          }
          
          //-------------------------------------------- REDUCE HP --------------------------------------------
          // reduce player hp if they collide with an enemy
          for (Enemy enemy: enemies3) {
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
          if (player.getY() > Const.HEIGHT) {
            player.setHP(player.getHP()-10);
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
          
          //-------------------------------------------- BULLETS --------------------------------------------
          // shoot a bullet from player if their ammo reload is done
          if (spaceHeld && shootDelay >= Const.SHOOTDELAY) {
            shootDelay = 0;
            ammo.addBullet(player);
          }
          // move all bullets and check for bullet collision with platforms or enemies
          ammo.moveBullets(platforms3, enemies3);
          // remove all dead enemies from game
          for (int enemy = 0; enemy < enemies3.length; enemy++) {
            if (enemies3[enemy] != null) {
              if (enemies3[enemy].isDead()) {
                enemies3[enemy] = null;
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
          player.moveY(platforms3);
          if (upHeld) {
            for (int p = 0; p < platforms3.length; p++) {
              if (player.isOnPlatform(platforms3[p])) {
                player.jump();
              }
            }
          }
          // apply gravity to enemies
          for (Enemy enemy: enemies3) {
            if (enemy != null) {
              enemy.applyGravity();
              enemy.moveY(platforms3);
            }
          }
          
          //-------------------------------------------- OTHER --------------------------------------------
          // change where the camera is looking at
          cam.moveTo(player.getX());
          // if player reached end point of stage
          if (player.collidesWith(endpoints[2])) {
            level = 10;
          }
        }
      }
      
      //---------------------//---------------------// WIN SCREEN //---------------------//---------------------//
      while (level == 10) {
        gameWindow.repaint();
        try {Thread.sleep(framePeriod);} catch(Exception e){}
        framePeriod = Const.FRAME_PERIOD;
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
      player.setHP(player.getMaxHP());
      for (int b = 0; b<ammo.getMag().length; b++) {
        ammo.removeBullet(b);
      }
      startTime = System.currentTimeMillis();
      deathCounter = deathCounter + 1;
      if (level == 1) {
        platforms1 = Const.PLATFORMS1.clone();
        enemies1 = Const.ENEMIES1.clone();
      } else if (level == 2) {
        platforms2 = Const.PLATFORMS2.clone();
        enemies2 = Const.ENEMIES2.clone();
      } else if (level == 3) {
         platforms3 = Const.PLATFORMS3;
         enemies3 = Const.ENEMIES3;
       }
    }
//------------------------------------------------------------------------------
    // mouse inputs
    public class MyMouseListener implements MouseListener{
        public void mouseClicked(MouseEvent e){
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
                g2d.drawImage(bg, bgx1-cam.anchorX(), 0, Const.WIDTH, Const.HEIGHT, null);
                g2d.drawImage(bg, bgx2-cam.anchorX(), 0, Const.WIDTH, Const.HEIGHT, null);
                // draw door
                endpoints[0].draw(cam, g);
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
              } else {
                g.setColor(Color.BLACK);
                g.fillRect(0,0,Const.WIDTH, Const.HEIGHT);
//                g2d.drawImage(bg, 0-cam.anchorX(), 0, Const.WIDTH, Const.HEIGHT, null);
//                g2d.drawImage(bg, Const.WIDTH-cam.anchorX(), 0, Const.WIDTH, Const.HEIGHT, null);
                g.setColor(Color.WHITE);
                g.setFont(Const.TEXTFONT);
                g.drawString("You died!", Const.WIDTH/2 - 80, Const.HEIGHT/2-40);
                g.drawString("Press R to restart", Const.WIDTH/2-145, Const.HEIGHT/2);
                g.drawString("Press ESC to quit", Const.WIDTH/2-142, Const.HEIGHT/2+40);
              }
            }
            // level 2
            if (level == 2) {
              if (!player.isDead()) {
                // draw background
                g2d.drawImage(bg, bgx1-cam.anchorX(), 0, Const.WIDTH, Const.HEIGHT, null);
                g2d.drawImage(bg, bgx2-cam.anchorX(), 0, Const.WIDTH, Const.HEIGHT, null);
                // draw door
                endpoints[1].draw(cam, g);
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
              } else {
                g.setColor(Color.BLACK);
                g.fillRect(0,0,Const.WIDTH, Const.HEIGHT);
                g.setColor(Color.WHITE);
                g.setFont(Const.TEXTFONT);
                g.drawString("You died!", Const.WIDTH/2 - 80, Const.HEIGHT/2-40);
                g.drawString("Press R to restart", Const.WIDTH/2-145, Const.HEIGHT/2);
                g.drawString("Press ESC to quit", Const.WIDTH/2-142, Const.HEIGHT/2+40);
              }
            }
            // level 3
            if (level == 3) {
              if (!player.isDead()) {
                // draw background
                g2d.drawImage(bg, bgx1-cam.anchorX(), 0, Const.WIDTH, Const.HEIGHT, null);
                g2d.drawImage(bg, bgx2-cam.anchorX(), 0, Const.WIDTH, Const.HEIGHT, null);
                // draw door
                endpoints[2].draw(cam, g);
                // draw player
                player.draw(cam, g);
                // draw enemies
                for (Enemy enemy: enemies3) {
                  if (enemy != null) {
                    enemy.draw(cam, g);
                  }
                }
                // draw platforms
                for (int p = 0; p < platforms3.length; p++) {
                  platforms3[p].draw(cam, g);
                }
                // draw enemies' hp bars
                for (Enemy enemy: enemies3) {
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
              } else {
                g.setColor(Color.BLACK);
                g.fillRect(0,0,Const.WIDTH, Const.HEIGHT);
                g.setColor(Color.WHITE);
                g.setFont(Const.TEXTFONT);
                g.drawString("You died!", Const.WIDTH/2 - 80, Const.HEIGHT/2-40);
                g.drawString("Press R to restart", Const.WIDTH/2-145, Const.HEIGHT/2);
                g.drawString("Press ESC to quit", Const.WIDTH/2-142, Const.HEIGHT/2+40);
              }
            }
            if (level == 10) {
              g.setColor(Color.BLACK);
              g.fillRect(0,0,Const.WIDTH, Const.HEIGHT);
//                g2d.drawImage(bg, 0, 0, Const.WIDTH, Const.HEIGHT, null);
              g.setColor(Color.WHITE);
              g.setFont(Const.TEXTFONT);
              g.drawString("You've escaped!", Const.WIDTH/2 - 120, Const.HEIGHT/2-10);
            }
        }
    }
//------------------------------------------------------------------------------
}