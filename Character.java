// character class used to make a player character
// inherits GameObject
// Composed of an Ammo object and a Health object 

import java.awt.Graphics;
import java.awt.Color;

public class Character extends Damageable {
    private int jumpPower;
//------------------------------------------------------------------------------    
    Character(int x, int y, int width, int height, int speed, int jp, String direction, Health hp){
        super(x,y,width,height,speed,direction,hp);
        this.jumpPower = jp;
    }
//------------------------------------------------------------------------------
    // getters and setters for x and y velocity
    public void setJumpPower(int newjp){
        this.jumpPower = newjp;
    }
    public int getJumpPower() {
      return this.jumpPower;
    }
//------------------------------------------------------------------------------
    public void draw(Graphics g){}
    public void draw(Camera cam, Graphics g){
      g.setColor(Color.red);
      g.fillRect(this.getX() - cam.anchorX(), this.getY(), this.getW(), this.getH());
      g.setColor(Color.pink);
      if (this.getDirection().equals("left")) {
        g.fillRect(this.getX() - cam.anchorX(), this.getY()+this.getH()/5, this.getW()/3, this.getH()/5);
      } else if (this.getDirection().equals("right")) {
        g.fillRect(this.getX()+(2*this.getW()/3) - cam.anchorX() , this.getY()+this.getH()/5, this.getW()/3, this.getH()/5);
      }
    }
//--------------------
    public void moveLeft(Platform[] platforms){
      this.setDirection("left");
      this.setX(this.getX() - this.getSpeed());
      if (this.getX() < 0) {
        this.setX(0);
      }
      for (int p = 0; p < platforms.length; p++) {
        if (platforms[p] != null) {
          // checks if player "entered" a platform && is inside the platform && player top is above bottom of platform && player bottom is below top of platform
          if (this.collidesWith(platforms[p])) {
            this.setX(platforms[p].getX() + platforms[p].getW());
          }
        }
      }
    }
//--------------------
    public void moveRight(Platform[] platforms){
      this.setDirection("right");
      this.setX(this.getX() + this.getSpeed());
      if (this.getX() + this.getW() > Const.STAGE1WIDTH) {
        this.setX(Const.STAGE1WIDTH - this.getW());
      }
      for (int p = 0; p < platforms.length; p++) {
        if (platforms[p] != null) {
          // checks if player "entered" a platform && is inside the platform && player top is above bottom of platform && player bottom is below top of platform
          if (this.collidesWith(platforms[p])) {
            this.setX(platforms[p].getX() - this.getW());
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