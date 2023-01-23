// constant values
// @author Daniel Liu & Galton Ma
// @version Jan 22 2023 - 1.3.5

import java.awt.*;

public final class Const{
    
    public static final int WIDTH = 800; // window width
    public static final int WIDTHOFFSET = 18; // to offset jframe's poor pixel calculations
    public static final int HEIGHT = 600; // window height
    public static final int HEIGHTOFFSET = 47; // to offset jframe's poor pixel calculations
    public static final int GROUND = HEIGHT-40; // how high up the ground is from the bottom of the window
    
    public static final int FRAME_PERIOD = 20; // milliseconds between frames
    
    public static final int MOVE_SPEED = 8; // player move speed
    public static final int CHARW = 50;
    public static final int CHARH = 50;
    public static final int STARTX = 30+4300; // starting player X pos
    public static final int STARTY = GROUND - 300;//-40; // starting player Y pos
    public static final int GRAVITY = 1; // acceleration of gravity
    public static final int JUMP_SPEED = -17; // player jump speed
    public static final int STARTHP = 100;
    public static final int IFRAMES = 10;
    
    public static final int TEXTSIZE = 30;
    public static final Font TEXTFONT = new Font("Arial", Font.BOLD, TEXTSIZE);
    
    public static final int CLIP_SIZE = 100;    
    public static final int BULLET_HEIGHT = 6;
    public static final int BULLET_WIDTH = 10;
    public static final int BULLET_SPEED = 5;     
    public static final int BULLET_DAMAGE = 5;
    public static final int SHOOTDELAY = 35; // how many frames before you can shoot again
    public static final int RELOADBARLENGTH = 50; // length of the yellow ammo refill bar
    
    public static final int STAGETIME = 80; // 80 seconds to complete each level
    public static final int STAGEWIDTH = 8500;
    
    // new Platform(x, y, w, h, color);
    public static final Platform[] PLATFORMS1 = {
        new Platform(0,450,100,25,Color.GREEN),
        new Platform(0,Const.GROUND,800,20,Color.GREEN),
        new Platform(850,450,100,20,Color.GREEN),
        new Platform(1100,400,100,20,Color.GREEN),
        new Platform(1350,350,100,20,Color.GREEN),
        new Platform(1575,300,100,20,Color.GREEN),
        new Platform(1825,250,500,20,Color.GREEN),
        new Platform(2500,300,100,20,Color.GREEN),
        new Platform(2800,250,500,20,Color.GREEN),
        new Platform(3600,300,100,20,Color.GREEN),
        new Platform(3850,350,100,20,Color.GREEN),
        new Platform(4075,400,100,20,Color.GREEN),
        new Platform(4325,450,100,20,Color.GREEN),
        new Platform(4550,Const.GROUND,800,20,Color.GREEN),
        new Platform(5600,Const.GROUND,800,20,Color.GREEN),
        new Platform(6600,Const.GROUND,200,20,Color.GREEN),
        new Platform(7000,Const.GROUND,200,20,Color.GREEN),
        new Platform(7400,Const.GROUND,200,20,Color.GREEN),
        new Platform(7800,Const.GROUND,800,20,Color.GREEN)
    };
    public static final Platform[] PLATFORMS2 = {
        new Platform(0,450,100,25,Color.GREEN),
        new Platform(0,Const.GROUND,800,20,Color.GREEN),
        new Platform(860,450,100,20,Color.GREEN),
        new Platform(1100,500,100,20,Color.GREEN),
        new Platform(1340,450,100,20,Color.GREEN),
        new Platform(1580,500,100,20,Color.GREEN),
        new Platform(1820,450,100,20,Color.GREEN),
        new Platform(2060,450,700,20,Color.GREEN),
        new Platform(2830,Const.GROUND,825,20,Color.GREEN),
        new Platform(2830,450,825,20,Color.GREEN),
        new Platform(3800,450,200,20,Color.GREEN),
        new Platform(4100,Const.GROUND,900,20,Color.GREEN),
        new Platform(4100,450,900,20,Color.GREEN),
        new Platform(5200,500,100,20,Color.GREEN),
        new Platform(5400,450,100,20,Color.GREEN),
        new Platform(5600,500,100,20,Color.GREEN),
        new Platform(5800,450,100,20,Color.GREEN),
        new Platform(6000,Const.GROUND,800,20,Color.GREEN),
        new Platform(7128,440,100,20,Color.GREEN),
        new Platform(7328,440,100,20,Color.GREEN),
        new Platform(7528,440,100,20,Color.GREEN),
        new Platform(7000,Const.GROUND,1000,20,Color.GREEN),
        new Platform(7975,-15,25,600,Color.GREEN),
    };
    public static final Platform[] PLATFORMS3 = {
        new Platform(0,450,100,25,Color.GREEN),
        new Platform(0,Const.GROUND,300,20,Color.GREEN), // starting
        new Platform(300,440,50,20,Color.GREEN), // steps
        new Platform(350,320,50,20,Color.GREEN),
        new Platform(400,200,50,20,Color.GREEN),
        new Platform(500,80,25,150,Color.GREEN), // wall up1
        new Platform(500,230,1000,25,Color.GREEN), // area up1
        new Platform(500,410,25,150,Color.GREEN), // wall down1
        new Platform(500,Const.GROUND,1000,20,Color.GREEN), // area down1
        new Platform(1475,410,25,150,Color.GREEN), // wall down1
        new Platform(1575,200,50,20,Color.GREEN), // steps
        new Platform(1650,320,50,20,Color.GREEN),
        new Platform(1750,410,25,150,Color.GREEN), // wall down2
        new Platform(1750,Const.GROUND,1000,20,Color.GREEN), // area down2
        new Platform(1750,80,25,150,Color.GREEN), // wall up2
        new Platform(1750,230,1000,20,Color.GREEN), // area up2
        new Platform(2725,80,25,150,Color.GREEN), // wall up2
        new Platform(2775,440,50,20,Color.GREEN), // steps
        new Platform(2850,320,50,20,Color.GREEN),
        new Platform(2925,205,50,20,Color.GREEN),
        new Platform(3000,410,25,150,Color.GREEN), // wall down3
        new Platform(3000,Const.GROUND,1000,20,Color.GREEN), // area down3
        new Platform(3450,535,25,20,Color.GREEN), // block down3
        new Platform(3450,205,25,20,Color.GREEN), // block up3
        new Platform(3000,80,25,150,Color.GREEN), // wall up3
        new Platform(3000,230,1000,20,Color.GREEN), // area up3
        new Platform(3975,80,25,150,Color.GREEN), // wall up3
        new Platform(4075,440,50,20,Color.GREEN), // steps
        new Platform(4175,320,50,20,Color.GREEN),
        new Platform(4275,205,50,20,Color.GREEN),
        new Platform(4300,410,70,25,Color.GREEN), // down4 extension
        new Platform(4375,410,25,150,Color.GREEN), // wall down4
        new Platform(4375,Const.GROUND,1625,20,Color.GREEN), // area down4
        new Platform(4375,80,25,150,Color.GREEN), // wall up4
        new Platform(4375,230,1625,20,Color.GREEN), // area up4
        new Platform(4800,535,25,25,Color.GREEN),
        new Platform(4825,405,1175,25,Color.GREEN), //area mid4
        new Platform(5975,255,25,75,Color.GREEN), // wall mid4
        new Platform(4950,330,25,25,Color.GREEN),
        new Platform(5150,330,25,25,Color.GREEN),
        new Platform(5350,330,25,25,Color.GREEN),
        new Platform(5550,330,25,25,Color.GREEN),
        new Platform(5750,330,25,25,Color.GREEN),
        new Platform(5975,80,25,150,Color.GREEN), // wall up4
        new Platform(6100,500,250,25,Color.GREEN),
        new Platform(6500,400,250,25,Color.GREEN),
        new Platform(6900,300,250,25,Color.GREEN),
        new Platform(7300,200,250,25,Color.GREEN),
        new Platform(7700,150,250,25,Color.GREEN),
        new Platform(8100,150,200,25,Color.GREEN),
        new Platform(8300,150,25,400,Color.GREEN), // ending hole
        new Platform(8475,150,25,400,Color.GREEN),
        new Platform(8300,550,200,25,Color.GREEN),
    };
    
    // new Enemy([x], [y], [w], [h], [speed], [direction], new Health([hp]), [damage]);
    public static final Enemy[] ENEMIES1 = {
        new Enemy(700, Const.GROUND-40, 40, 40, 6, "left", new Health(30), 10, "goblin"), // 40 by 40
        new Enemy(2130, 150, 70, 70, 7, "right", new Health(100), 10, "slime"), // 70 by 70
        new Enemy(3038, 150, 70, 70, 7, "right", new Health(100), 10, "slime"),
        new Enemy(4870, 400, 50, 50, 15, "right", new Health(30), 8, "goblin"),
        new Enemy(5878, 400, 50, 50, 15, "left", new Health(30), 8, "goblin"),
        new Enemy(6694, 400, 20, 20, 3, "left", new Health(10), 3, "goblin"),
        new Enemy(7086, 400, 20, 20, 3, "right", new Health(10), 3, "goblin"),
        new Enemy(7500, 400, 20, 20, 3, "left", new Health(10), 3, "goblin"),
        new Enemy(8000, 400, 80, 90, 1, "left", new Health(20), 100, "tree"), // 80 by 90
    };
    public static final Enemy[] ENEMIES2 = {
        new Enemy(110, 0, 30, 30, 4, "left", new Health(5), 2, "goblin"),
        new Enemy(198, 0, 30, 30, 4, "left", new Health(5), 2, "goblin"),
        new Enemy(286, 0, 30, 30, 4, "left", new Health(5), 2, "goblin"),
        new Enemy(390, 0, 30, 30, 4, "left", new Health(5), 2, "goblin"),
        new Enemy(494, 0, 30, 30, 4, "left", new Health(5), 2, "goblin"),
        new Enemy(582, 0, 30, 30, 4, "left", new Health(5), 2, "goblin"),
        new Enemy(662, 0, 30, 30, 4, "left", new Health(5), 2, "goblin"),
        new Enemy(2206, 350, 40, 100, 0, "left", new Health(100), 10, "shield"),
        new Enemy(2406, 350, 40, 100, 0, "left", new Health(100), 10, "shield"), // 40 by 100
        new Enemy(2606, 350, 40, 100, 0, "left", new Health(100), 10, "shield"),
        new Enemy(3150, 100, 200, 200, 30, "right", new Health(400), 100, "slime"),
        new Enemy(3008, 520, 30, 30, 40, "left", new Health(10), 3, "goblin"),
        new Enemy(3224, 520, 30, 30, 40, "left", new Health(10), 3, "goblin"),
        new Enemy(3424, 520, 30, 30, 40, "left", new Health(10), 3, "goblin"),
        new Enemy(4454, 100, 200, 200, 30, "right", new Health(400), 3, "slime"),
        new Enemy(4270, 520, 30, 30, 40, "left", new Health(10), 100, "goblin"),
        new Enemy(4478, 520, 30, 30, 40, "left", new Health(10), 100, "goblin"),
        new Enemy(4718, 520, 30, 30, 40, "left", new Health(10), 100, "goblin"),
        new Enemy(6064, 0, 30, 30, 4, "left", new Health(5), 5, "goblin"),
        new Enemy(6160, 0, 30, 30, 4, "left", new Health(5), 5, "goblin"),
        new Enemy(6272, 0, 30, 30, 4, "left", new Health(5), 5, "goblin"),
        new Enemy(6376, 0, 30, 30, 4, "left", new Health(5), 5, "goblin"),
        new Enemy(6480, 0, 30, 30, 4, "left", new Health(5), 5, "goblin"),
        new Enemy(6584, 0, 30, 30, 4, "left", new Health(5), 5, "goblin"),
        new Enemy(6688, 0, 30, 30, 4, "left", new Health(5), 5, "goblin"),
        new Enemy(7128, 430, 40, 80, 0, "right", new Health(100), 10, "shield"),
        new Enemy(7328, 130, 40, 80, 0, "left", new Health(100), 10, "shield"),
        new Enemy(7528, 430, 40, 80, 0, "right", new Health(100), 10, "shield"),
    };
    public static final Enemy[] ENEMIES3 = {
        new Enemy(700, 40, 40, 40, 6, "left", new Health(30), 10, "goblin"), // 40 by 40
        new Enemy(700, 440, 70, 70, 7, "left", new Health(100), 20, "slime"), // 70 by 70
        new Enemy(1100, 440, 70, 70, 7, "left", new Health(100), 20, "slime"),
        new Enemy(1400, 440, 70, 70, 7, "left", new Health(100), 20, "slime"),
        new Enemy(2000, 40, 70, 70, 7, "left", new Health(100), 20, "slime"),
        new Enemy(2200, 40, 30, 30, 10, "left", new Health(100), 10, "goblin"),
        new Enemy(2400, 40, 70, 70, 7, "left", new Health(100), 20, "slime"),
        new Enemy(2000, 440, 40, 40, 6, "left", new Health(30), 10, "goblin"),
        new Enemy(2250, 460, 40, 100, 0, "left", new Health(100), 30, "shield"),
        new Enemy(2450, 460, 40, 100, 0, "left", new Health(100), 30, "shield"), // 40 by 100
        new Enemy(2650, 460, 40, 100, 0, "left", new Health(100), 30, "shield"),
        new Enemy(3475, 40, 10, 10, 3, "left", new Health(100), 5, "goblin"),
        new Enemy(3505, 40, 10, 10, 3, "left", new Health(100), 5, "goblin"),
        new Enemy(3535, 40, 10, 10, 3, "left", new Health(100), 5, "goblin"),
        new Enemy(3565, 40, 10, 10, 3, "left", new Health(100), 5, "goblin"),
        new Enemy(3595, 40, 10, 10, 3, "left", new Health(100), 5, "goblin"),
        new Enemy(3625, 40, 10, 10, 3, "left", new Health(100), 5, "goblin"),
        new Enemy(3555, 40, 10, 10, 3, "left", new Health(100), 5, "goblin"),
        new Enemy(3685, 40, 10, 10, 3, "left", new Health(100), 5, "goblin"),
        new Enemy(3715, 40, 10, 10, 3, "left", new Health(100), 5, "goblin"),
        new Enemy(3745, 40, 10, 10, 3, "left", new Health(100), 5, "goblin"),
        new Enemy(3775, 40, 10, 10, 3, "left", new Health(100), 5, "goblin"),
        new Enemy(3805, 40, 10, 10, 3, "left", new Health(100), 5, "goblin"),
        new Enemy(3835, 40, 10, 10, 3, "left", new Health(100), 5, "goblin"),
        new Enemy(3865, 40, 10, 10, 3, "left", new Health(100), 5, "goblin"),
        new Enemy(3895, 40, 10, 10, 3, "left", new Health(100), 5, "goblin"),
        new Enemy(3925, 40, 10, 10, 3, "left", new Health(100), 5, "goblin"),
        new Enemy(3955, 40, 10, 10, 3, "left", new Health(100), 5, "goblin"),
        new Enemy(3965, 40, 10, 10, 3, "right", new Health(100), 5, "goblin"),
        new Enemy(3935, 40, 10, 10, 3, "right", new Health(100), 5, "goblin"),
        new Enemy(3905, 40, 10, 10, 3, "right", new Health(100), 5, "goblin"),
        new Enemy(3875, 40, 10, 10, 3, "right", new Health(100), 5, "goblin"),
        new Enemy(3845, 40, 10, 10, 3, "right", new Health(100), 5, "goblin"),
        new Enemy(3815, 40, 10, 10, 3, "right", new Health(100), 5, "goblin"),
        new Enemy(3785, 40, 10, 10, 3, "right", new Health(100), 5, "goblin"),
        new Enemy(3755, 40, 10, 10, 3, "right", new Health(100), 5, "goblin"),
        new Enemy(3725, 40, 10, 10, 3, "right", new Health(100), 5, "goblin"),
        new Enemy(3695, 40, 10, 10, 3, "right", new Health(100), 5, "goblin"),
        new Enemy(3665, 40, 10, 10, 3, "right", new Health(100), 5, "goblin"),
        new Enemy(3635, 40, 10, 10, 3, "right", new Health(100), 5, "goblin"),
        new Enemy(3605, 40, 10, 10, 3, "right", new Health(100), 5, "goblin"),
        new Enemy(3575, 40, 10, 10, 3, "right", new Health(100), 5, "goblin"),
        new Enemy(3545, 40, 10, 10, 3, "right", new Health(100), 5, "goblin"),
        new Enemy(3515, 40, 10, 10, 3, "right", new Health(100), 5, "goblin"),
        new Enemy(3705, 450, 60, 60, 7, "right", new Health(100), 5, "slime"),
        new Enemy(4800, 40, 80, 90, 1, "left", new Health(100), 20, "tree"), // 80 by 90
        new Enemy(4850, 345, 25, 60, 0, "left", new Health(50), 20, "shield"), //mid
        new Enemy(4950, 270, 25, 60, 0, "left", new Health(50), 20, "shield"),
        new Enemy(5050, 345, 25, 60, 0, "left", new Health(50), 20, "shield"),
        new Enemy(5150, 270, 25, 60, 0, "left", new Health(50), 20, "shield"),
        new Enemy(5250, 345, 25, 60, 0, "left", new Health(50), 20, "shield"),
        new Enemy(5350, 270, 25, 60, 0, "left", new Health(50), 20, "shield"),
        new Enemy(5450, 345, 25, 60, 0, "left", new Health(50), 20, "shield"),
        new Enemy(5550, 270, 25, 60, 0, "left", new Health(50), 20, "shield"),
        new Enemy(5650, 345, 25, 60, 0, "left", new Health(50), 20, "shield"),
        new Enemy(5750, 270, 25, 60, 0, "left", new Health(50), 20, "shield"),
        new Enemy(5850, 345, 25, 60, 0, "left", new Health(50), 20, "shield"),
        new Enemy(4825, 500, 20, 50, 0, "left", new Health(50), 30, "shield"), //bot
        new Enemy(4925, 500, 20, 50, 0, "left", new Health(50), 30, "shield"),
        new Enemy(5025, 500, 20, 50, 0, "left", new Health(50), 30, "shield"),
        new Enemy(5125, 500, 20, 50, 0, "left", new Health(50), 30, "shield"),
        new Enemy(5225, 500, 20, 50, 0, "left", new Health(50), 30, "shield"),
        new Enemy(5325, 500, 20, 50, 0, "left", new Health(50), 30, "shield"),
        new Enemy(5425, 500, 20, 50, 0, "left", new Health(50), 30, "shield"),
        new Enemy(5525, 500, 20, 50, 0, "left", new Health(50), 30, "shield"),
        new Enemy(5625, 500, 20, 50, 0, "left", new Health(50), 30, "shield"),
        new Enemy(5725, 500, 20, 50, 0, "left", new Health(50), 30, "shield"),
        new Enemy(5825, 500, 20, 50, 0, "left", new Health(50), 30, "shield"),
        new Enemy(5925, 500, 20, 50, 0, "left", new Health(50), 30, "shield"),
        new Enemy(6215, 410, 80, 90, 4, "left", new Health(100), 20, "tree"), // on platforms
        new Enemy(6600, 310, 80, 90, 6, "left", new Health(100), 20, "tree"),
        new Enemy(7000, 210, 80, 90, 8, "left", new Health(100), 20, "tree"),
        new Enemy(7350, 110, 80, 90, 10, "left", new Health(100), 20, "tree"),
        new Enemy(7800, 40, 80, 90, 12, "left", new Health(100), 20, "tree"),
    };
    
    // new Platform(x, y, w, h, color);
    public static final Platform[] ENDPOINTS = {
      new Platform(8300,Const.GROUND-70,50,70,Color.BLUE),
      new Platform(7800,Const.GROUND-70,50,70,Color.BLUE),
      new Platform(8375,480,50,70,Color.BLUE),
    };
    
    private Const(){
    }
//------------------------------------------------------------------------------
}