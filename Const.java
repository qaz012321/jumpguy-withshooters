// constant values
// @author Galton, Daniel
// @version Dec 21 2022

import java.awt.*;

public final class Const{
    public static final int WIDTH = 800; // window width
    public static final int WIDTHOFFSET = 18; // to offset jframe's poor pixel calculations
    public static final int HEIGHT = 600; // window height
    public static final int HEIGHTOFFSET = 47; // to offset jframe's poor pixel calculations
    public static final int GROUND = HEIGHT-40; // how high up the ground is from the bottom of the window
  
    public static final int FRAME_PERIOD = 20; // milliseconds between frames
  
    public static final int MOVE_SPEED = 8; // player move speed
    public static final int CHARW = 30;
    public static final int CHARH = 30;
    public static final int STARTX = 30; // starting player X pos
    public static final int STARTY = GROUND - CHARH - 30; // starting player Y pos
    public static final int GRAVITY = (((CHARH+CHARW)/2)/20); // acceleration of gravity
    public static final int JUMP_SPEED = -(17*((CHARH+CHARW)/2)/30); // player jump speed
    public static final int STARTHP = 100;
    public static final int IFRAMES = 10;

    public static final int EMOVESPEED = 5;
  
    public static final int CLIP_SIZE = 100;    
    public static final int BULLET_HEIGHT = 6;
    public static final int BULLET_WIDTH = 10;
    public static final int BULLET_SPEED = 5;     
    public static final int BULLET_DAMAGE = 5;
    public static final int SHOOTDELAY = 35; // how many frames before you can shoot again
    public static final int RELOADBARLENGTH = 50; // length of the yellow ammo refill bar
  
    public static final int STAGE1WIDTH = 2000;
    
    public static final Platform[] PLATFORMS1 = {
    new Platform(0,0,Const.STAGE1WIDTH,0,Color.PINK),
    new Platform(0,Const.GROUND,800,40,Color.GREEN),
    new Platform(1000,Const.GROUND,600,40,Color.GREEN),
    new Platform(1800,Const.GROUND,200,40,Color.GREEN),
    new Platform(200, 400, 100, 50, Color.BLUE),
    new Platform(400, 200, 100, 50, Color.BLUE),
    new Platform(150, 100, 100, 50, Color.BLUE),
    new Platform(175, 310, 60, 20, Color.BLUE),
    new Platform(600, 380, 540, 30, Color.BLUE),
    new Platform(700, 230, 50, 50, Color.RED),
    new Platform(800, 230, 50, 50, Color.ORANGE),
    new Platform(900, 230, 50, 50, Color.YELLOW),
    new Platform(1000, 230, 83, 50, Color.GREEN),
    new Platform(1500, 200, 300, 40, Color.PINK),
    new Platform(300, 500, 40, 10, Color.CYAN),
    new Platform(390, 450, 40, 10, Color.CYAN),
    new Platform(480, 400, 80, 10, Color.CYAN),
    new Platform(850, 340, 40, 40, Color.MAGENTA),
    new Platform(890, 260, 10, 10, Color.MAGENTA),
    new Platform(1200, 300, 100, 20, Color.MAGENTA)
  };
    
    private Const(){
    }
}