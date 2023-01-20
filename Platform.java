// platforms
// inherits GameObject

import java.io.*;
import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import javax.imageio.*;

public class Platform extends GameObject {
  private Color colour;
  BufferedImage grass;
  int grassCount = 0;
  
  // constructors
  Platform(){
    super();
    this.colour = new Color(0,0,0,0); //r,g,b,transparency

  }
  Platform(int x, int y, int w, int h, Color c) {
    super(x,y,w,h);
    this.colour = c;
    try {
        grass = ImageIO.read(new File("platformblock.png"));
    } catch (Exception e) {System.out.println("Image not found");}
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
      
//      g.setColor(this.colour);
//      g.fillRect(this.getX() - cam.anchorX(), this.getY(), this.getW(), this.getH());
      for (int grassCount = this.getX() - cam.anchorX(); grassCount < this.getW() + this.getX() - cam.anchorX(); grassCount += 25) {
          g.drawImage(grass, grassCount, this.getY(), 25, 25, null);
      }
    }
  }
  public void draw(Graphics g){}
}