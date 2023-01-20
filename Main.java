// main!!

class Main {
  public static void main(String[] args) {
    System.out.println("Hello world!");
    // System.out.println(Const.GRAVITY + " " + Const.JUMP_SPEED);
    GravityAndJumping demo = new GravityAndJumping();
//    Frame demo = new Frame();
    demo.run();
  }
}