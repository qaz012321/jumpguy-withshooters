import java.awt.Graphics;

public class Ammo{
    final int CARTRIDGE_SIZE = 50;    
    private Bullet[] bullets;
    
    Ammo(){
        this.bullets = new Bullet[CARTRIDGE_SIZE];
    }
//------------------------------------------------------------------------------      
    public void drawBullets(Graphics g){
        for (int i=0; i<this.bullets.length; i++){
            if (this.bullets[i] != null){
                this.bullets[i].draw(g);
            }
        }
    }
    public void moveBullets(){
        for (int i=0; i<this.bullets.length; i++){
            if (this.bullets[i] != null){
                this.bullets[i].move();
                if (this.bullets[i].getX() > Const.WIDTH || this.bullets[i].getX() < 0){
                    this.removeBullet(i);
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