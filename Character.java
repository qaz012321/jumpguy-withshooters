// inherits GameObject
import java.awt.Graphics;
import java.awt.Color;
// edit 1133 is working collision with no camera attempts

public class Character extends GameObject {
    private int xVel;
    private int yVel;
    private int moveSpeed;
    private int jumpPower;
    private String direction = "";
    private int internalPos;
    private int internalPosSave;
    private int moveOther;
//------------------------------------------------------------------------------    
    Character(int x, int y, int width, int height, int speed, int jp){
        super(x,y,width,height);
        this.xVel = 0;
        this.yVel = 0; 
        this.moveSpeed = speed;
        this.jumpPower = jp;
        this.direction = "right";
        this.internalPos = 0;
        this.internalPosSave = this.internalPos;
        this.moveOther = 0;
    }
//------------------------------------------------------------------------------
    // getters and setters for x and y velocity
    public void setVelX(int xVel){
        this.xVel = xVel;
    }
    public int getVelX() {
      return this.yVel;
    }
    public void setVelY(int yVel){
        this.yVel = yVel;
    }
    public int getVelY(){
      return this.yVel;
    }
    // getters and setters for the last faced direction
    public void setDirection(String direction) {
      this.direction = direction;
    }
    public String getDirection() {
      return this.direction;
    }
    // getters and setters for Current, New, and Difference Pos's
    public void setInternalPos(int internalPos) {
      this.internalPos = internalPos;
    }
    public void setInternalPosSave(int save) {
      this.internalPosSave = save;
    }
    public void setMoveOther(int moveOther) {
      this.moveOther = moveOther;
    }
    public int getInternalPos() {
      return this.internalPos;
    }
    public int getInternalPosSave() {
      return this.internalPosSave;
    }
    public int getMoveOther() {
      return this.moveOther;
    }
//------------------------------------------------------------------------------    
    public void draw(Graphics g){
        g.setColor(Color.red);
        g.fillRect(this.getX(), this.getY(), this.getW(), this.getH());
        g.setColor(Color.pink);
        if (this.getDirection().equals("left")) {
          g.fillRect(this.getX(), this.getY()+10, 20, 10);
        } else if (this.getDirection().equals("right")) {
          g.fillRect(this.getX()+40, this.getY()+10, 20, 10);
        }
    }
//--------------------
    public void moveLeft(Platform[] platforms){
      this.setDirection("left");
      this.setInternalPosSave(this.getInternalPos());
      if (this.getX() == Const.WIDTH/4) { // if on "centered part" or "middle part" of level
        this.setMoveOther(this.moveSpeed); // move bg + other objects right
        System.out.println(this.getInternalPos() + "  " + this.getMoveOther() + "  " + (this.getInternalPos()-this.getMoveOther()));
        this.setInternalPos(this.getInternalPos()-this.getMoveOther()); // changes player internal position
        System.out.println(this.getInternalPos() + ", " + this.getX());
        if (this.getInternalPos() < Const.LEVEL1_LENGTH*0 + Const.WIDTH/4) { // if camera reached left end
          // get (internal pos) - (player on screen pos while in steady camera mode)
          this.setMoveOther(this.getInternalPos() - Const.WIDTH/4);
          // move player on screen by that much
          this.setX(this.getX() + this.getMoveOther());
          // move other objects right by (step) + (how far left the player just moved) 
          this.setMoveOther(this.getMoveOther() + this.moveSpeed);
        }
        
      } else if (this.getX() < Const.WIDTH/4) { // if on left edge of level
        this.setX(this.getX() - this.moveSpeed);
        this.setMoveOther(0);
        this.setInternalPos(this.getX());
        System.out.println(this.getInternalPos() + ", " + this.getX());
        if (this.getX() < 0) {
          this.setX(0);
        }
        
      } else if (this.getX() > Const.WIDTH/4) { // if on right edge of level
        this.setX(this.getX() - this.moveSpeed);
        this.setMoveOther(0);
        this.setInternalPos(Const.LEVEL1_LENGTH - Const.WIDTH + this.getX());
        System.out.println(this.getInternalPos() + ", " + this.getX());
        // if moved to the left past right edge boundary, then corrects movement to what it should be
        if (this.getX() <= Const.WIDTH/4) {
          System.out.println("move left thing");
          this.setMoveOther(Const.WIDTH/4 - this.getX());
          this.setX(Const.WIDTH/4);
        }
      }

      // if (this.getX() == Const.WIDTH/4) { // adjusts everything else according to how much they should scroll
        for (int p = 0; p < platforms.length; p++) {
          platforms[p].setX(platforms[p].getX() + this.getMoveOther());
        }
      // }

      // checks for collision
      for (int p = 0; p < platforms.length; p++) {
        if (platforms[p] != null) {
          // checks if player "entered" a platform && is inside the platform && player top is above bottom of platform && player bottom is below top of platform
          if (  (this.getX() < platforms[p].getX() + platforms[p].getW())  &&  
              (this.getX() + this.getW() > platforms[p].getX())  &&  
              (this.getY() < platforms[p].getY() + platforms[p].getH())  &&  
              (this.getY() + this.getH() > platforms[p].getY())  ) 
          {
            this.setX(platforms[p].getX() + platforms[p].getW());
          }
        }
      }
    }
//--------------------
    public void moveRight(Platform[] platforms){
      this.setDirection("right");
      this.setInternalPosSave(this.getInternalPos());
      System.out.println(this.getX() + "<-- getX(), constwidth/4 -->" + Const.WIDTH/4);
      if (this.getX() == Const.WIDTH/4) { // if on "centered part" or "middle part" of level
        this.setMoveOther(-this.moveSpeed); // move bg + other objects left
        System.out.println(this.getInternalPos() + "  " + this.getMoveOther() + "  " + (this.getInternalPos()-this.getMoveOther()));
        this.setInternalPos(this.getInternalPos()-this.getMoveOther()); // changes player internal position
        System.out.println(this.getInternalPos() + ", " + this.getX());
        if (this.getInternalPos() > Const.LEVEL1_LENGTH - (3*Const.WIDTH/4)) { // if camera reached right end
          // get (internal pos) - (player on screen pos while in steady camera mode)
          this.setMoveOther(this.getInternalPos() - (Const.LEVEL1_LENGTH - 3*(Const.WIDTH/4)));
          // move player on screen by that much
          this.setX(this.getX() + this.getMoveOther());
          // move other objects left by (step) - (how far right the player just moved) 
          this.setMoveOther(-(this.moveSpeed - this.getMoveOther()));
        }
        System.out.println(this.getX() + " test 1");
        
      } else if (this.getX() > Const.WIDTH/4) { // if on right edge of level
        this.setX(this.getX() + this.moveSpeed);
        this.setMoveOther(0);
        this.setInternalPos(Const.LEVEL1_LENGTH - Const.WIDTH + this.getX());
        System.out.println(this.getInternalPos() + ", " + this.getX());
        if (this.getX() + this.getW() > Const.WIDTH) {
          this.setX(Const.WIDTH - this.getW());
        }
        System.out.println(this.getX() + " test 2");
        
      } else if (this.getX() < Const.WIDTH/4) { // if on left edge of level
        this.setX(this.getX() + this.moveSpeed);
        this.setMoveOther(0);
        System.out.println(this.getInternalPos() + ", " + this.getX() + " test before");
        this.setInternalPos(this.getX());
        System.out.println(this.getInternalPos() + ", " + this.getX() + " test after");
        // if moved to the right past left edge boundary, then corrects movement to what it should be
        if (this.getX() >= Const.WIDTH/4) {
          System.out.println("move right thing");
          this.setMoveOther(Const.WIDTH/4 - this.getX());
          this.setX(Const.WIDTH/4);
        }
        System.out.println(this.getX() + " test 3");
      }
      
      // if (this.getX() == Const.WIDTH/4) { // adjusts all other objects according to how much they should scroll
        for (int p = 0; p < platforms.length; p++) {
          platforms[p].setX(platforms[p].getX() + this.getMoveOther());
        }
      // }
        System.out.println(this.getX() + " test 123.4");

      // checks for collision
      for (int p = 0; p < platforms.length; p++) {
        if (platforms[p] != null) {
          // checks if player "entered" a platform && is inside the platform && player top is above bottom of platform && player bottom is below top of platform
          if (  ((this.getX() + this.getW()) > platforms[p].getX())  &&  
              (this.getX() < (platforms[p].getX() + platforms[p].getW()))  &&  
              (this.getY() < (platforms[p].getY() + platforms[p].getH()))  &&  
              ((this.getY() + this.getH()) > platforms[p].getY())  ) 
          {
            // moves all other (non-player) objects so that the objects can visually be where they're supposed to be when collision happens in the middle of a step
            System.out.println("platform collision: " + platforms[p].getX() + " " + platforms[p].getY());
            this.setMoveOther(this.getInternalPos() - (platforms[p].getX() - platforms[0].getX()));
            for (int pp = 0; pp < platforms.length; pp++) {
              platforms[pp].setX(platforms[pp].getX() - this.getMoveOther());
            }
          }
        }
      }
      System.out.println(this.getX() + " test 4");
    }
//--------------------
    public void applyGravity(){
        this.yVel += Const.GRAVITY;
    }
//--------------------
    public void moveY(Platform[] platforms){
      this.setY(this.getY() + this.yVel);
      for (int p = 0; p < platforms.length; p++) {
        // checks if player "entered" a platform && is inside the platform && player left is left of platform right && player right is right of platform left
        if (   (this.getY() + this.getH() >= platforms[p].getY())  &&  
            (this.getY() < platforms[p].getY() + platforms[p].getH())  &&  
            (this.getX() < platforms[p].getX() + platforms[p].getW())  &&  
            (this.getX() + this.getW() > platforms[p].getX())   ) 
        {
          if (this.yVel >= 0) {
            this.setY(platforms[p].getY() - this.getH());
            this.yVel = 0;
          } else {
            this.setY(platforms[p].getY() + platforms[p].getH());
            this.yVel = 0;
          }
        }
      }
    }
//--------------------
    public boolean isOnPlatform(Platform platform){
      if ((this.getX() < platform.getX() + platform.getW())  &&  (this.getX() + this.getW() > platform.getX())) {
        return (this.getY() + this.getH() == platform.getY());
      }
      return false;
    }
//--------------------
  public void jump() {
    this.setVelY(Const.JUMP_SPEED);
  }
}