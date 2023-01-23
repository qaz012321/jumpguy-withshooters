// character class used to make a player character
// inherits Damageable which inherits GameObject
// Composed of a Health object 
// @author Daniel Liu & Galton Ma
// @version Jan 22 2023 - 1.3.5

import java.awt.*;
import javax.swing.*;

public class Character extends Damageable {
    private int jumpPower;
    private Image ghost = new ImageIcon("ghost idle.png").getImage();
//------------------------------------------------------------------------------    
    Character(int x, int y, int width, int height, int speed, int jp, String direction, Health hp, String type){
        super(x,y,width,height,speed,direction,hp,type);
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
      Graphics2D g2d = (Graphics2D)g;
        
      if (this.getDirection().equals("left")) {
        g2d.drawImage(ghost, this.getX() - cam.anchorX() + 50, this.getY(), -49, 50, null);
      } else if (this.getDirection().equals("right")) {
        g2d.drawImage(ghost, this.getX() - cam.anchorX(), this.getY(), 49, 50, null);
      }
    }
//--------------------
    public void moveLeft(Platform[] platforms){
      if (!this.isDead()) {
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
    }
//--------------------
    public void moveRight(Platform[] platforms){
      if (!this.isDead()) {
        this.setDirection("right");
        this.setX(this.getX() + this.getSpeed());
        if (this.getX() + this.getW() > Const.STAGEWIDTH) {
          this.setX(Const.STAGEWIDTH - this.getW());
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
    }
//--------------------
    public boolean isOnPlatform(Platform platform){
      if (!this.isDead()) {
        if ((this.getX() < platform.getX() + platform.getW())  &&  (this.getX() + this.getW() > platform.getX())) {
          return (this.getY() + this.getH() == platform.getY());
        }
      }
      return false;
    }
//--------------------
  public void jump() {
    if (!this.isDead()) {
      this.setVelY(Const.JUMP_SPEED);
    }
  }
//--------------------
}