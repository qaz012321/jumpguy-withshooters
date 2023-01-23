// enemy class
// inherits Damageable which inherits GameObject
// @author Daniel Liu & Galton Ma
// @version Jan 22 2023 - 1.3.5

import javax.swing.*;
import java.awt.*;

public class Enemy extends Damageable {
  private int contactDamage;
  private Image slimeEnemy = new ImageIcon("SlimeEnemy.png").getImage();
  private Image goblinEnemy = new ImageIcon("GoblinEnemy.png").getImage();
  private Image treeEnemy = new ImageIcon("TreeEnemy.png").getImage();
  private Image shieldEnemy = new ImageIcon("ShieldEnemy.png").getImage();
//------------------------------------------------------------------------------
  Enemy(int x, int y, int w, int h, int speed, String direction, Health hp, int cd, String type) {
    super(x, y, w, h, speed, direction, hp, type);
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
    Graphics2D g2d = (Graphics2D)g;
//    g.setColor(Color.BLACK);
//    g.fillRect(this.getX() - cam.anchorX(), this.getY(), this.getW(), this.getH());
//    g.setColor(Color.GRAY);
    if (this.getDirection().equals("left")) {
      //g.fillRect(this.getX() - cam.anchorX(), this.getY()+this.getH()/5, this.getW()/3, this.getH()/5);
        if (this.getType().equals("slime")){
          g2d.drawImage(slimeEnemy, this.getX() - cam.anchorX() + this.getW(), this.getY(), -this.getW(), this.getH(), null);
        }
        if (this.getType().equals("goblin")){
          g2d.drawImage(goblinEnemy, this.getX() - cam.anchorX() + this.getW(), this.getY(), -this.getW(), this.getH(), null);
        }
        if (this.getType().equals("tree")){
          g2d.drawImage(treeEnemy, this.getX() - cam.anchorX() + this.getW(), this.getY(), -this.getW(), this.getH(), null);
        }
        if (this.getType().equals("shield")){
          g2d.drawImage(shieldEnemy, this.getX() - cam.anchorX() + this.getW(), this.getY(), -this.getW(), this.getH(), null);
        }
    } else if (this.getDirection().equals("right")) {
      //g.fillRect(this.getX()+(2*this.getW()/3) - cam.anchorX() , this.getY()+this.getH()/5, this.getW()/3, this.getH()/5);
        if (this.getType().equals("slime")){
          g2d.drawImage(slimeEnemy, this.getX() - cam.anchorX(), this.getY(), this.getW(), this.getH(), null);
        }
        if (this.getType().equals("goblin")){
          g2d.drawImage(goblinEnemy, this.getX() - cam.anchorX(), this.getY(), this.getW(), this.getH(), null);
        }
        if (this.getType().equals("tree")){
          g2d.drawImage(treeEnemy, this.getX() - cam.anchorX(), this.getY(), this.getW(), this.getH(), null);
        }
        if (this.getType().equals("shield")){
          g2d.drawImage(shieldEnemy, this.getX() - cam.anchorX(), this.getY(), this.getW(), this.getH(), null);
        }
    }
  }
//--------------------
  public void move(Platform[] platforms){
    for (Platform p: platforms) {
      if (this.isCompletelyOnPlatform(p)) {
        // if facing left (platforms)
        if (this.getDirection().equals("left")) {
          if (this.getX() - this.getSpeed() < p.getX()) {
            this.setX(p.getX());
            this.setDirection("right");
          } else {
            this.setX(this.getX() - this.getSpeed());
          }
        // if facing right (platforms)
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
    } else if (this.getX() + this.getW() > Const.STAGEWIDTH) { // assumes platforms[0] will always be length of the current stage
      this.setX(Const.STAGEWIDTH-this.getW());
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