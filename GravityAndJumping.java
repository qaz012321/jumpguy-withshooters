// main java file that runs jpanel and jframe

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GravityAndJumping {
  JFrame gameWindow;
  GamePanel gamePanel;   
  MyKeyListener keyListener; 
  MyMouseListener mouseListener;
  
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
  Enemy[] enemies1 = Const.ENEMIES1;
  
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
            if (enemy != null) {
                enemy.applyGravity();
                enemy.moveY(platforms1);
            }
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
              System.out.println("px: " + player.getX() + " py: " + player.getY());
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
    // mouse inputs
    public class MyMouseListener implements MouseListener{
        public void mouseClicked(MouseEvent e){   // moves the box at the mouse location
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