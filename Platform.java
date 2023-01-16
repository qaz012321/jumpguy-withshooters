// platforms
// inherits GameObject

import java.awt.Color;
import java.awt.Graphics;

public class Platform extends GameObject {
  private Color colour;
  
  // constructors
  Platform(){
    super();
    this.colour = new Color(0,0,0,0); //r,g,b,transparency
  }
  Platform(int x, int y, int w, int h, Color c) {
    super(x,y,w,h);
    this.colour = c;
  }
  
  // setters
  public void setC(Color newC) {
    this.colour = newC;
  }
  // getters
  public Color getC() {
    return this.colour;
  }
  
  public void draw(Camera cam, Graphics g){
    if (cam.onScreen(this.getX(), this.getW())){
      g.setColor(this.colour);
      g.fillRect(this.getX() - cam.anchorX(), this.getY(), this.getW(), this.getH());
    }
  }
  public void draw(Graphics g){}
}