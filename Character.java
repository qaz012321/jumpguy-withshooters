import java.awt.Graphics;
import java.awt.Color;

public class Character extends GameObject {
    private int xVel;
    private int yVel;
    private int moveSpeed;
    private int jumpPower;
//------------------------------------------------------------------------------    
    Character(int x, int y, int width, int height, int speed, int jp){
        super(x,y,width,height);
        this.xVel = 0;
        this.yVel = 0; 
        this.moveSpeed = speed;
        this.jumpPower = jp;
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
//------------------------------------------------------------------------------    
    public void draw(Graphics g){
        g.setColor(Color.red);
        g.fillRect(this.getX(), this.getY(), this.getW(), this.getH());
    }
    
    public void moveLeft(Platform[] platforms){
      this.setX(this.getX() - this.moveSpeed);
      if (this.getX() < 0) {
        this.setX(0);
      }
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
    
    public void moveRight(Platform[] platforms){
      this.setX(this.getX() + this.moveSpeed);
      if (this.getX() + this.getW() > Const.WIDTH) {
        this.setX(Const.WIDTH - this.getW());
      }
      for (int p = 0; p < platforms.length; p++) {
        if (platforms[p] != null) {
          // checks if player "entered" a platform && is inside the platform && player top is above bottom of platform && player bottom is below top of platform
          if (  ((this.getX() + this.getW()) > platforms[p].getX())  &&  
              (this.getX() < (platforms[p].getX() + platforms[p].getW()))  &&  
              (this.getY() < (platforms[p].getY() + platforms[p].getH()))  &&  
              ((this.getY() + this.getH()) > platforms[p].getY())  ) 
          {
            this.setX(platforms[p].getX() - this.getW());
          }
        }
      }
    }
    
    public void applyGravity(){
        this.yVel += Const.GRAVITY;
    }
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
    
    public boolean isOnPlatform(Platform platform){
      if ((this.getX() < platform.getX() + platform.getW())  &&  (this.getX() + this.getW() > platform.getX())) {
        return (this.getY() + this.getH() == platform.getY());
      }
      return false;
    }
}