/*abstract class damageable (just for common methods/attributes
 * between Character and Enemy that are too specific for GameObject
 * and too specific for bullets)
 */
// inherits GameObject
// @author Daniel Liu & Galton Ma
// @version Jan 22 2023 - 1.3.5

import java.awt.Graphics;
import java.awt.Color;

abstract class Damageable extends GameObject {
  private int yVel;
  private int moveSpeed;
  private String direction = "";
  private Health hp;
  private boolean dead;
  private String type = "";
//------------------------------------------------------------------------------    
  Damageable(){
    super();
    this.yVel = 0;
    this.moveSpeed = 0;
    this.direction = "";
    this.hp = null;
    this.dead = true;
    this.type = "";
  }
  Damageable(int x, int y, int width, int height, int speed, String direction, Health hp, String type){
    super(x,y,width,height);
    this.yVel = 0;
    this.moveSpeed = speed;
    this.direction = direction;
    this.hp = hp;
    this.dead = false;
    this.type = type;
  }
//------------------------------------------------------------------------------    
  public void setVelY(int yVel){
    this.yVel = yVel;
  }
  public int getVelY(){
    return this.yVel;
  }
  public void setSpeed(int newspeed) {
    this.moveSpeed = newspeed;
  }
  public int getSpeed() {
    return this.moveSpeed;
  }
  public void setDirection(String direction) {
    this.direction = direction;
  }
  public String getDirection() {
    return this.direction;
  }
  public int getHP() {
    return this.hp.getHealth(); // calls getHealth from Health class
  }
  public void setHP(int newhp) {
    this.hp.setHealth(newhp); // calls setHealth from Health class
  }
  public void setMaxHP(int newmaxhp) {
    this.hp.setMaxHealth(newmaxhp);
  }
  public int getMaxHP() {
    return this.hp.getMaxHealth();
  }
  public boolean isDead() {
    if (this.getHP() <= 0) {
      this.dead = true;
    } else {
      this.dead = false;
    }
    return this.dead;
  }
  public String getType(){
    return this.type;
  }
//------------------------------------------------------------------------------
  public Health HP() {
    return this.hp; // just returns the Health object
  }
//--------------------
  public void applyGravity(){
    if (!this.isDead()) {
      this.yVel += Const.GRAVITY;
    }
  }
//--------------------
  public void moveY(Platform[] platforms){
    if (!this.isDead()) {
      this.setY(this.getY() + this.yVel);
      for (int p = 0; p < platforms.length; p++) {
        // checks if player "entered" a platform && is inside the platform && player left is left of platform right && player right is right of platform left
        if (this.collidesWith(platforms[p])) {
          if (this.yVel >= 0) { // if player is going down (falling)
            this.setY(platforms[p].getY() - this.getH());
            this.yVel = 0;
          } else { // if player is going up (just jumped)
            this.setY(platforms[p].getY() + platforms[p].getH());
            this.yVel = 0;
          }
        }
      }
    }
  }
//--------------------
  public void moveY(){
    this.setY(this.getY() + this.yVel);
  }
//------------------------------------------------------------------------------    
    // mandatory draw method so that we don't forget to draw the game object
    abstract void draw(Graphics g);
}