public class Camera{
    private int x = Const.WIDTH/2;
    Camera(int x){
        if (this.x < x){
            this.x = x;
        }
    }
    
    public void moveTo(int playerX){
        if (Const.WIDTH/2 < playerX && playerX < Const.STAGE1WIDTH - Const.WIDTH/2){
            this.x = playerX;
        }
        if (Const.WIDTH/2 > playerX) {
            this.x = Const.WIDTH/2;
        }
        if (playerX > Const.STAGE1WIDTH - Const.WIDTH/2) {
            this.x = Const.STAGE1WIDTH - Const.WIDTH/2;
        }
    }

    public int anchorX(){
        return this.x - Const.WIDTH/2;
    }
    public boolean onScreen(int x, int width){
        return (this.x - Const.WIDTH/2 - width < x && x < this.x + Const.WIDTH/2 + width);
    }
}