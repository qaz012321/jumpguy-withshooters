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
  // game objects
  Character player = new Character(Const.STARTX, Const.STARTY, Const.CHARW, Const.CHARH, Const.MOVE_SPEED, Const.JUMP_SPEED);
  Ammo ammo = new Ammo();
  Camera cam = new Camera(Const.STARTX);
  // make platforms for levels
  Platform[] platforms1 = {
    new Platform(1,Const.GROUND,Const.STAGE1WIDTH,40,Color.GREEN),
    new Platform(200, 400, 100, 50, Color.BLUE),
    new Platform(400, 200, 100, 50, Color.BLUE),
    new Platform(150, 100, 100, 50, Color.BLUE),
    new Platform(175, 310, 60, 20, Color.BLUE),
    new Platform(600, 380, 540, 30, Color.BLUE),
    new Platform(700, 230, 50, 50, Color.RED),
    new Platform(800, 230, 50, 50, Color.ORANGE),
    new Platform(900, 230, 50, 50, Color.YELLOW),
    new Platform(1000, 230, 83, 50, Color.GREEN),
    new Platform(1500, 200, 300, 40, Color.PINK)
  };
  
//------------------------------------------------------------------------------
  GravityAndJumping(){
    gameWindow = new JFrame("prelim camera");
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

      // so the player moves, we have to check if they're close enough to the left edge or right edge, if they are, move character X instead of all other X's
      // if not close enough to an edge, move all other X's according to how much player would 
      if (level == 1) {
        
        if (leftHeld){player.moveLeft(platforms1);}
        if (rightHeld){player.moveRight(platforms1);}
        if (spaceHeld) {
          ammo.addBullet(player);
        }
        ammo.moveBullets();
      }
      
      player.applyGravity();
      player.moveY(platforms1);
      if (upHeld) {
        for (int p = 0; p < platforms1.length; p++) {
          if (player.isOnPlatform(platforms1[p])) {
            player.jump();
          }
        }
      }
      cam.moveTo(player.getX());
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
          player.draw(cam, g);
          System.out.println(player.getX() + " " + (player.getX() + player.getW()) + " " + cam.anchorX());
          // implement the movement for everything else (based on camera pos) here
          for (int p = 0; p < platforms1.length; p++) {
            platforms1[p].draw(cam, g);
          }
          ammo.drawBullets(cam, g);
          g.setColor(Color.GRAY);
          g.drawLine(platforms1[0].getX(),0,platforms1[0].getX(),Const.HEIGHT);
        }    
    }    
//------------------------------------------------------------------------------
    public static void main(String[] args){
        GravityAndJumping demo = new GravityAndJumping();
        demo.run();
    }
}