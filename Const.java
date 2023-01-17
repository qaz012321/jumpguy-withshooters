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
    
    public static final int STAGE1WIDTH = 5000;
    
    // new Platform(x, y, w, h, color);
    public static final Platform[] PLATFORMS1 = {
//    new Platform(0,0,Const.STAGE1WIDTH,0,Color.PINK),
        new Platform(0,Const.GROUND,800,40,Color.GREEN),
        new Platform(860,450,100,20,Color.GREEN),
        new Platform(1100,400,100,20,Color.GREEN),
        new Platform(1340,350,100,20,Color.GREEN),
        new Platform(1580,300,100,20,Color.GREEN),
        new Platform(1820,250,500,20,Color.GREEN),
//    new Platform(1000,Const.GROUND,600,40,Color.GREEN),
//    new Platform(1800,Const.GROUND,200,40,Color.GREEN),
//    new Platform(200, 400, 100, 50, Color.BLUE),
//    new Platform(400, 200, 100, 50, Color.BLUE),
//    new Platform(150, 100, 100, 50, Color.BLUE),
//    new Platform(175, 310, 60, 20, Color.BLUE),
//    new Platform(600, 380, 540, 30, Color.BLUE),
//    new Platform(700, 230, 50, 50, Color.RED),
//    new Platform(800, 230, 50, 50, Color.ORANGE),
//    new Platform(900, 230, 50, 50, Color.YELLOW),
//    new Platform(1000, 230, 83, 50, Color.GREEN),
//    new Platform(1500, 200, 300, 40, Color.PINK),
//    new Platform(300, 500, 40, 10, Color.CYAN),
//    new Platform(390, 450, 40, 10, Color.CYAN),
//    new Platform(480, 400, 80, 10, Color.CYAN),
//    new Platform(850, 340, 40, 40, Color.MAGENTA),
//    new Platform(890, 260, 10, 10, Color.MAGENTA),
//    new Platform(1200, 300, 100, 20, Color.MAGENTA)
    };
    // new Enemy([x], [y], [w], [h], [speed], [direction], new Health([hp]), [damage]);
    public static final Enemy[] ENEMIES1 = {
        new Enemy(700, Const.GROUND-40, 60, 40, 6, "left", new Health(10), 10),
        new Enemy(400, Const.GROUND-20, 100, 20, 8, "right", new Health(100), 5),
        new Enemy(1400, Const.GROUND-30, 30, 30, 6, "left", new Health(30), 10),
        new Enemy(1830, 230, 30, 30, 6, "right", new Health(30), 5),
//    new Enemy(160, 60, 20, 40, 10, "left", new Health(20), 25),
//    new Enemy(1600, 195, 5, 5, 6, "right", new Health(200), 34),
//    new Enemy(300, -200, 100, 10, 5, "right", new Health(80), 10)
    };
    
    private Const(){
    }
}