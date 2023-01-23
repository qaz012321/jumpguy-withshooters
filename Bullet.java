// explains direction of bullet and how to draw
// inherits GameObject
// @author Daniel Liu & Galton Ma
// @version Jan 22 2023 - 1.3.5

import javax.swing.*;
import java.awt.*;

public class Bullet extends GameObject{
    private int bulletVx;
    private Image bulletPic = new ImageIcon("bullet.png").getImage();
    private String direction = "";
    
    public Bullet(int x, int y, String dir){
        super(x, y, Const.BULLET_WIDTH, Const.BULLET_HEIGHT);
        this.direction = dir;
        if (dir.equals("left")) {
          this.bulletVx = -Const.BULLET_SPEED;
        } else if (dir.equals("right")) {
          this.setX(x+Const.CHARW-Const.BULLET_WIDTH);
          this.bulletVx = Const.BULLET_SPEED;
        }
    }    
//------------------------------------------------------------------------------    
    @Override
    public void draw(Graphics g){}
    
    public void draw(Camera cam, Graphics g){
      Graphics2D g2d = (Graphics2D)g;
      if (cam.onScreen(this.getX(), this.getW())) {
        if (this.direction.equals("left")) {
          g2d.drawImage(bulletPic, this.getX() - cam.anchorX() + this.getW(), this.getY(), -this.getW(), this.getH(), null);
        } else if (this.direction.equals("right")) {
          g2d.drawImage(bulletPic, this.getX() - cam.anchorX() + this.getW(), this.getY(), this.getW(), this.getH(), null);
        }
      }
    }    
//------------------------------------------------------------------------------    
    public void move(){
        this.setX(this.getX() + this.bulletVx);
    }    
}