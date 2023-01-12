// inherits GameObject
import java.awt.Graphics;
import java.awt.Color;

public class Bullet extends GameObject{
    private int bulletVx;
    
    public Bullet(int x, int y, String dir){
        super(x, y, Const.BULLET_WIDTH, Const.BULLET_HEIGHT);
        if (dir.equals("left")) {
          this.bulletVx = -Const.BULLET_SPEED;
        } else if (dir.equals("right")) {
          this.setX(x+Const.CHARW-Const.BULLET_WIDTH);
          this.bulletVx = Const.BULLET_SPEED;
        }
    }    
//------------------------------------------------------------------------------    
    @Override
    public void draw(Graphics g){
        g.setColor(Color.BLACK);
        g.fillRect(getX(), getY(), getW(), getH());    
    }    
//------------------------------------------------------------------------------    
    public void move(){
        this.setX(this.getX() + this.bulletVx);
    }    
}