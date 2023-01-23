// main!!
// @author Daniel Liu & Galton Ma
// @version Jan 22 2023 - 1.3.5

import javax.swing.*;

class Main {
  public static void main(String[] args){
    System.out.println("Hello world!");
    JFrame frame = new JFrame("1234");
    Frame a = new Frame();
    frame.add(a);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(800, 600);
    frame.setVisible(true);
    frame.setResizable(false);
    while (true) {
      System.out.print("");
      if (!a.isVisible()) {
        frame.dispose();
        GravityAndJumping demo = new GravityAndJumping(a.level());
        demo.run();
      }
    }
  }
}