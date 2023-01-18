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
    
    public static final int STAGE1WIDTH = 6800;
    
    // new Platform(x, y, w, h, color);
    public static final Platform[] PLATFORMS1 = {
//    new Platform(0,0,Const.STAGE1WIDTH,0,Color.PINK),
        new Platform(0,Const.GROUND,800,40,Color.GREEN),
        new Platform(860,450,100,20,Color.GREEN),
        new Platform(1100,400,100,20,Color.GREEN),
        new Platform(1340,350,100,20,Color.GREEN),
        new Platform(1580,300,100,20,Color.GREEN),
        new Platform(1820,250,500,20,Color.GREEN),
        new Platform(2500,300,100,20,Color.GREEN),
        new Platform(2800,250,500,20,Color.GREEN),
        new Platform(3600,300,100,20,Color.GREEN),
        new Platform(3840,350,100,20,Color.GREEN),
        new Platform(4080,400,100,20,Color.GREEN),
        new Platform(4320,450,100,20,Color.GREEN),
        new Platform(4560,Const.GROUND,800,40,Color.GREEN),
        new Platform(5600,Const.GROUND,800,40,Color.GREEN),
        new Platform(6600,Const.GROUND,200,40,Color.GREEN),
        new Platform(7000,Const.GROUND,200,40,Color.GREEN),
        new Platform(7400,Const.GROUND,200,40,Color.GREEN),
        new Platform(7800,Const.GROUND,800,40,Color.GREEN)
    };
    public static final Platform[] PLATFORMS2 = {
        new Platform(0,Const.GROUND,800,40,Color.GREEN),
        new Platform(860,450,100,20,Color.GREEN),
        new Platform(1100,500,100,20,Color.GREEN),
        new Platform(1340,450,100,20,Color.GREEN),
        new Platform(1580,500,100,20,Color.GREEN),
        new Platform(1820,450,100,20,Color.GREEN),
        new Platform(2060,450,700,20,Color.GREEN),
        new Platform(2860,Const.GROUND,850,40,Color.GREEN),
        new Platform(2830,450,830,20,Color.GREEN),
        new Platform(3800,450,200,20,Color.GREEN),
        new Platform(4100,Const.GROUND,900,40,Color.GREEN),
        new Platform(4100,450,900,20,Color.GREEN),
        new Platform(5200,500,100,20,Color.GREEN),
        new Platform(5400,450,100,20,Color.GREEN),
        new Platform(5600,500,100,20,Color.GREEN),
        new Platform(5800,450,100,20,Color.GREEN),
        new Platform(6000,Const.GROUND,800,40,Color.GREEN),
    };
    // new Enemy([x], [y], [w], [h], [speed], [direction], new Health([hp]), [damage]);
    public static final Enemy[] ENEMIES1 = {
        new Enemy(700, Const.GROUND-40, 60, 40, 6, "left", new Health(10), 10),
        new Enemy(400, Const.GROUND-20, 100, 20, 8, "right", new Health(100), 5),
        new Enemy(1400, Const.GROUND-30, 30, 30, 6, "left", new Health(30), 10),
        new Enemy(2130, 150, 70, 70, 7, "right", new Health(100), 10),
        new Enemy(3038, 150, 70, 70, 7, "right", new Health(100), 10),
        new Enemy(4870, 400, 50, 50, 15, "right", new Health(30), 8),
        new Enemy(5878, 400, 50, 50, 15, "left", new Health(30), 8),
        new Enemy(6694, 400, 20, 20, 3, "left", new Health(10), 3),
        new Enemy(7086, 400, 20, 20, 3, "right", new Health(10), 3),
        new Enemy(7500, 400, 20, 20, 3, "left", new Health(10), 3),
        new Enemy(8000, 400, 40, 80, 1, "left", new Health(20), 100)
    };
    public static final Enemy[] ENEMIES2 = {
        new Enemy(110, 0, 30, 30, 4, "left", new Health(5), 1),
        new Enemy(198, 0, 30, 30, 4, "left", new Health(5), 1),
        new Enemy(286, 0, 30, 30, 4, "left", new Health(5), 1),
        new Enemy(390, 0, 30, 30, 4, "left", new Health(5), 1),
        new Enemy(494, 0, 30, 30, 4, "left", new Health(5), 1),
        new Enemy(582, 0, 30, 30, 4, "left", new Health(5), 1),
        new Enemy(662, 0, 30, 30, 4, "left", new Health(5), 1),
        new Enemy(2206, 350, 40, 100, 0, "left", new Health(100), 10),
        new Enemy(2406, 350, 40, 100, 0, "left", new Health(100), 10),
        new Enemy(2606, 350, 40, 100, 0, "left", new Health(100), 10),
        new Enemy(3150, 100, 200, 200, 30, "right", new Health(400), 100),
        new Enemy(3008, 520, 30, 30, 40, "left", new Health(10), 1),
        new Enemy(3224, 520, 30, 30, 40, "left", new Health(10), 1),
        new Enemy(3424, 520, 30, 30, 40, "left", new Health(10), 1),
        new Enemy(4454, 100, 200, 200, 30, "right", new Health(400), 1),
        new Enemy(4270, 520, 30, 30, 40, "left", new Health(10), 100),
        new Enemy(4478, 520, 30, 30, 40, "left", new Health(10), 100),
        new Enemy(4718, 520, 30, 30, 40, "left", new Health(10), 100),
        new Enemy(6064, 0, 30, 30, 4, "left", new Health(5), 1),
        new Enemy(6160, 0, 30, 30, 4, "left", new Health(5), 1),
        new Enemy(6272, 0, 30, 30, 4, "left", new Health(5), 1),
        new Enemy(6376, 0, 30, 30, 4, "left", new Health(5), 1),
        new Enemy(6480, 0, 30, 30, 4, "left", new Health(5), 1),
        new Enemy(6584, 0, 30, 30, 4, "left", new Health(5), 1),
        new Enemy(6688, 0, 30, 30, 4, "left", new Health(5), 1),
    };
    
    private Const(){
    }
}