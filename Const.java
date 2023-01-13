// constant values
// @author Galton, Daniel
// @version Dec 21 2022

public final class Const{
    public static final int WIDTH = 800; // window width
    public static final int WIDTHOFFSET = 18; // to offset jframe's poor pixel calculations
    public static final int HEIGHT = 600; // window height
    public static final int HEIGHTOFFSET = 47; // to offset jframe's poor pixel calculations
    public static final int GROUND = HEIGHT-40; // how high up the ground is from the bottom of the window
    public static final int GRAVITY = 1; // acceleration of gravity
    public static final int FRAME_PERIOD = 20; // milliseconds between frames
    public static final int JUMP_SPEED = -14; // player jump speed
    public static final int MOVE_SPEED = 8; // player move speed
    public static final int STARTX = 30; // starting player X pos
    public static final int STARTY = GROUND - 80; // starting player Y pos
    public static final int CHARW = 20;
    public static final int CHARH = 20;
    public static final int CLIP_SIZE = 100;    
    public static final int BULLET_HEIGHT = 6;
    public static final int BULLET_WIDTH = 10;
    public static final int BULLET_SPEED = 5;     
    public static final int STAGE1WIDTH = 2000;
    
    private Const(){
    }
}