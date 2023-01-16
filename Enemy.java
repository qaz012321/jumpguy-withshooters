// abstract class

import java.awt.Graphics;
import java.awt.Color;

public class Enemy extends Damageable {
  private int contactDamage;
//------------------------------------------------------------------------------
  Enemy(int x, int y, int w, int h, int speed, String direction, Health hp, int cd) {
    super(x, y, w, h, speed, direction, hp);
    this.contactDamage = cd;
  }
//------------------------------------------------------------------------------
  public int getDamage() {
    return this.contactDamage;
  }
  public void setDamage(int newcd) {
    this.contactDamage = newcd;
  }
//------------------------------------------------------------------------------
  public void draw(Graphics g){}
  public void draw(Camera cam, Graphics g){
    g.setColor(Color.BLACK);
    g.fillRect(this.getX() - cam.anchorX(), this.getY(), this.getW(), this.getH());
    g.setColor(Color.GRAY);
    if (this.getDirection().equals("left")) {
      g.fillRect(this.getX() - cam.anchorX(), this.getY()+this.getH()/5, this.getW()/3, this.getH()/5);
    } else if (this.getDirection().equals("right")) {
      g.fillRect(this.getX()+(2*this.getW()/3) - cam.anchorX() , this.getY()+this.getH()/5, this.getW()/3, this.getH()/5);
    }
  }
//--------------------
  public void move(Platform[] platforms){
    for (Platform p: platforms) {
      if (this.isCompletelyOnPlatform(p)) {
        //
        if (this.getDirection().equals("left")) {
          if (this.getX() - this.getSpeed() < p.getX()) {
            this.setX(p.getX());
            this.setDirection("right");
          } else {
            this.setX(this.getX() - this.getSpeed());
          }
        //
        } else if (this.getDirection().equals("right")) {
          if (this.getX() + this.getW() + this.getSpeed() > p.getX() + p.getW()) {
            this.setX(p.getX() + p.getW() - this.getW());
            this.setDirection("left");
          } else {
            this.setX(this.getX() + this.getSpeed());
          }
        //
        } else {
          System.out.println("enemy has no direction???");
        }
        //
      } 
    }
    // left and right stage boundaries
    if (this.getX() < 0) {
      this.setX(0);
      this.setDirection("right");
    } else if (this.getX() + this.getW() > platforms[0].getW()) { // assumes platforms[0] will always be length of the current stage
      this.setX(platforms[0].getW()-this.getW());
      this.setDirection("left");
    }
    for (int p = 0; p < platforms.length; p++) {
      if (platforms[p] != null) {
        // checks if enemy collided with the current platform, then switches direction if so
        if (this.collidesWith(platforms[p])) {
          if (this.getDirection().equals("left")) {
            this.setX(platforms[p].getX() + platforms[p].getW());
            this.setDirection("right");
          } else if (this.getDirection().equals("right")) {
            this.setX(platforms[p].getX() - this.getW());
            this.setDirection("left");
          }
        }
      }
    }
  }
//--------------------
  public boolean isCompletelyOnPlatform(Platform platform){
    if ((this.getX() + this.getW() <= platform.getX() + platform.getW()) && (this.getX() >= platform.getX())) {
      // if all of the enemy is on this specific platform boundary
      // AND if the enemy is direction on top of the platform
      // then return true, else return false
      return (this.getY() + this.getH() == platform.getY());
    }
    return false;
  }
}