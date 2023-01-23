// abstract class for all game objects that are interactable by the user 
// GameObject encapsulates Damageable and Platform
// @author Daniel Liu & Galton Ma
// @version Jan 22 2023 - 1.3.5

import java.awt.Graphics;

abstract class GameObject{
    private int x;
    private int y;
    private int w;
    private int h;
//------------------------------------------------------------------------------  
    GameObject(){
        this.x = 0;
        this.y = 0;
        this.w = 0;
        this.h = 0;
    }
    GameObject(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.w = width;
        this.h = height;
    }
//------------------------------------------------------------------------------
    // getters and setters
    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }
    public int getW(){
        return this.w;
    }    
    public int getH(){
        return this.h;
    }    
    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }
    public void setW(int w){
        this.w = w;
    }
    public void setH(int h){
        this.h = h;
    }
//------------------------------------------------------------------------------
    public boolean collidesWith(GameObject object) {
      if (  (this.x < object.getX() + object.getW())  &&  
            (this.x + this.w > object.getX())  &&  
            (this.y < object.getY() + object.getH())  &&  
            (this.y + this.h > object.getY())  ) 
      {
        return true;
      }
      return false;
    }
//------------------------------------------------------------------------------    
    // mandatory draw method so that we don't forget to draw the game object
    abstract void draw(Graphics g);
}