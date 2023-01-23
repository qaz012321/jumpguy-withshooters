// explains what to do with each bullet slot in the clip
// @author Daniel Liu & Galton Ma
// @version Jan 22 2023 - 1.3.5

import java.awt.Graphics;
import java.awt.Color;

public class Ammo {
    private Bullet[] bullets;
    private int reloadBarLength;
    
    Ammo(){
        this.bullets = new Bullet[Const.CLIP_SIZE];
        this.reloadBarLength = Const.RELOADBARLENGTH;
    }
    
    public Bullet[] getMag() {
      return this.bullets;
    }
    public int getReloadBarLength() {
      return this.reloadBarLength;
    }
    public void setReloadBarLength(int rbl) {
      this.reloadBarLength = rbl;
    }
//------------------------------------------------------------------------------      
    public void draw(Graphics g) {
      g.setColor(Color.GRAY);
      g.fillRect(20, 20, 15, 50);
      g.setColor(Color.YELLOW);
      g.fillRect(20, 20+50-this.getReloadBarLength(), 15, this.getReloadBarLength());
    }
    public void drawBullets(Camera cam, Graphics g){
        for (int i=0; i<this.bullets.length; i++){
            if (this.bullets[i] != null){
                this.bullets[i].draw(cam, g);
            }
        }
    }
    public void moveBullets(Platform[] platforms, Enemy[] enemies){
        for (int i=0; i<this.bullets.length; i++){
            if (this.bullets[i] != null){
                this.bullets[i].move();
                // check for bullets that have travelled off stage
                if (this.bullets[i].getX() > Const.STAGEWIDTH || this.bullets[i].getX() + this.bullets[i].getW() < 0){
                    this.removeBullet(i);
                } 
                // check for collision with platforms
                for (Platform p: platforms) {
                  if (this.bullets[i] != null) {
                    if (this.bullets[i].collidesWith(p)) {
                      this.removeBullet(i);
                    }
                  }
                }
                // check for collision with enemies and deal damage if so
                for (Enemy e: enemies) {
                  if (this.bullets[i] != null && e != null) {
                    if (this.bullets[i].collidesWith(e)) {
                      this.removeBullet(i);
                      e.setHP(e.getHP() - Const.BULLET_DAMAGE);
                    }
                  }
                }
            }
        }
    }
//------------------------------------------------------------------------------  
    public void addBullet(Character player){
        boolean added = false;
        for (int i=0; i<this.bullets.length; i++){
            if (this.bullets[i] == null && !added){
                int bulletX = player.getX();
                int bulletY = player.getY() + player.getH()/2 - Const.BULLET_HEIGHT;
                this.bullets[i] = new Bullet(bulletX, bulletY, player.getDirection());
                added = true;
            }
        }
    }
    public void removeBullet(int index){
        this.bullets[index] = null;
    }    
}