import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GravityAndJumping {
  JFrame gameWindow;
  GamePanel gamePanel;   
  MyKeyListener keyListener; 
  
  boolean leftHeld, rightHeld, downHeld, upHeld, spaceHeld, escpressed;
    
  // game objects
  Character player = new Character(Const.STARTX, Const.STARTY, 60, 60, Const.MOVE_SPEED, Const.JUMP_SPEED);
  // make platforms for levels
  Platform[] platforms = {
    new Platform(0,Const.GROUND,Const.WIDTH,40,Color.GREEN),
    new Platform(200, 400, 100, 50, Color.BLUE),
    new Platform(400, 200, 100, 50, Color.BLUE),
    new Platform(150, 100, 100, 50, Color.BLUE),
    new Platform(175, 310, 60, 20, Color.BLUE)
  };
  
//------------------------------------------------------------------------------
  GravityAndJumping(){
    gameWindow = new JFrame("Game Window");
    gameWindow.setSize(Const.WIDTH,Const.HEIGHT);
    gameWindow.setMinimumSize(new Dimension(Const.WIDTH+Const.WIDTHOFFSET, Const.HEIGHT+Const.HEIGHTOFFSET));
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
      
      if (leftHeld){player.moveLeft(platforms);}
      if (rightHeld){player.moveRight(platforms);}
      player.applyGravity();
      player.moveY(platforms);
      if (upHeld) {
        for (int p = 0; p < platforms.length; p++) {
          if (player.isOnPlatform(platforms[p])) {
            player.setVelY(Const.JUMP_SPEED);
          }
        }
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
            if (key == KeyEvent.VK_ESCAPE){
                System.exit(0);
            }
        }
        public void keyReleased(KeyEvent e){ 
          int key = e.getKeyCode();
            if (key == KeyEvent.VK_A){
                leftHeld = false;
            } else if (key == KeyEvent.VK_D){
                rightHeld = false;
            } else if ((key == KeyEvent.VK_W)){
                upHeld = false;
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
          player.draw(g);
          for (int p = 0; p < platforms.length; p++) {
            platforms[p].draw(g);
          }
        }    
    }    
//------------------------------------------------------------------------------
    public static void main(String[] args){
        GravityAndJumping demo = new GravityAndJumping();
        demo.run();
    }
}