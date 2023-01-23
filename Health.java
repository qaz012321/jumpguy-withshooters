// class for the hp of entities (i.e. character, enemy)
// @author Daniel Liu & Galton Ma
// @version Jan 22 2023 - 1.3.5

import java.awt.Graphics;
import java.awt.Color;

public class Health {
  private int hp;
  private int maxHP;

  Health() {
    this.hp = 0;
    this.maxHP = this.hp;
  }
  Health(int hp) {
    this.hp = hp;
    this.maxHP = this.hp;
  }
//------------------------------------------------------------------------------
  public void setHealth(int newhp) {
    this.hp = newhp;
  }
  public int getHealth() {
    return this.hp;
  }
  public void setMaxHealth(int newmaxhp) {
    this.maxHP = newmaxhp;
  }
  public int getMaxHealth() {
    return this.maxHP;
  }
  public void draw(Graphics g){}
  public void draw(Camera cam, Graphics g, int x, int y, int w, int h) {
    g.setColor(Color.RED);
    g.fillRect(x + w/2 - this.maxHP/2 - cam.anchorX(), y - 40, this.maxHP, 20);    
    g.setColor(Color.GREEN);
    g.fillRect(x + w/2 - this.maxHP/2 - cam.anchorX(), y - 40, this.hp, 20); 
  }
}