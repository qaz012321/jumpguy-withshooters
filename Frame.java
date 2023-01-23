// frame for buttons and stuff
// inherits JPanel and implements the interfaces: ActionListener, KeyListener, MouseListener
// @author Daniel Liu & Galton Ma
// @version Jan 22 2023 - 1.3.5

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;

//            playButton.setContentAreaFilled(false);
//            playButton.setEnabled(false);

public class Frame extends JPanel implements ActionListener, KeyListener, MouseListener{
  
    int level = 0;
    
    JButton playButton;
    JButton instructionButton;
    JButton lvlOneButton;
    JButton lvlTwoButton;
    JButton lvlThreeButton;
    JButton backButton;
    
    //Music mainTheme = new Music("Hyrule Field.wav");
    
    BufferedImage instructions = null;
    boolean onInstructions = false;
    
    BufferedImage home = null;
    boolean onHome = true;
    
    BufferedImage levels = null;
    boolean onLevels = false;
    
    Frame(){
        
        loadImage();
        
        playButton = new JButton();
        playButton.setBounds(310, 320, 150, 70);
        playButton.addActionListener(this);
        playButton.addMouseListener(this);
        playButton.setText("Play");
        playButton.setFocusable(false);
        playButton.setFont(new Font("Dialog", Font.BOLD, 40));
        playButton.setForeground(Color.white);
        playButton.setBackground(new Color(255, 115, 115));
        playButton.setBorder(BorderFactory.createLineBorder(Color.white, 5));
        playButton.setVisible(true);
        
        instructionButton = new JButton();
        instructionButton.setBounds(310, 400, 150, 70);
        instructionButton.addActionListener(this);
        instructionButton.addMouseListener(this);
        instructionButton.setText("Instructions");
        instructionButton.setFocusable(false);
        instructionButton.setFont(new Font("Dialog", Font.BOLD, 20));
        instructionButton.setForeground(Color.white);
        instructionButton.setBackground(new Color(255, 115, 115));
        instructionButton.setBorder(BorderFactory.createLineBorder(Color.white, 5));
        instructionButton.setVisible(true);
        
        lvlOneButton = new JButton();
        lvlOneButton.setBounds(110, 250, 150, 150);
        lvlOneButton.addActionListener(this);
        lvlOneButton.addMouseListener(this);
        lvlOneButton.setText("1");
        lvlOneButton.setFocusable(false);
        lvlOneButton.setFont(new Font("Dialog", Font.BOLD, 80));
        lvlOneButton.setForeground(Color.white);
        lvlOneButton.setBackground(new Color(255, 115, 115));
        lvlOneButton.setBorder(BorderFactory.createLineBorder(Color.white, 5));
        lvlOneButton.setVisible(false);
        
        lvlTwoButton = new JButton();
        lvlTwoButton.setBounds(310, 250, 150, 150);
        lvlTwoButton.addActionListener(this);
        lvlTwoButton.addMouseListener(this);
        lvlTwoButton.setText("2");
        lvlTwoButton.setFocusable(false);
        lvlTwoButton.setFont(new Font("Dialog", Font.BOLD, 80));
        lvlTwoButton.setForeground(Color.white);
        lvlTwoButton.setBackground(new Color(255, 115, 115));
        lvlTwoButton.setBorder(BorderFactory.createLineBorder(Color.white, 5));
        lvlTwoButton.setVisible(false);
        
        lvlThreeButton = new JButton();
        lvlThreeButton.setBounds(510, 250, 150, 150);
        lvlThreeButton.addActionListener(this);
        lvlThreeButton.addMouseListener(this);
        lvlThreeButton.setText("3");
        lvlThreeButton.setFocusable(false);
        lvlThreeButton.setFont(new Font("Dialog", Font.BOLD, 80));
        lvlThreeButton.setForeground(Color.white);
        lvlThreeButton.setBackground(new Color(255, 115, 115));
        lvlThreeButton.setBorder(BorderFactory.createLineBorder(Color.white, 5));
        lvlThreeButton.setVisible(false);
        
        backButton = new JButton();
        backButton.setBounds(10, 10, 150, 70);
        backButton.addActionListener(this);
        backButton.addMouseListener(this);
        backButton.setText("Back");
        backButton.setFocusable(false);
        backButton.setFont(new Font("Dialog", Font.BOLD, 40));
        backButton.setForeground(Color.white);
        backButton.setBackground(new Color(255, 115, 115));
        backButton.setBorder(BorderFactory.createLineBorder(Color.white, 5));
        backButton.setVisible(false);
        
        this.setLayout(null);
        this.addMouseListener(this);
        this.addKeyListener(this);
        this.add(playButton);
        this.add(instructionButton);
        this.add(lvlOneButton);
        this.add(lvlTwoButton);
        this.add(lvlThreeButton);
        this.add(backButton);
        
    }
    
    public void loadImage(){
        try {
            instructions = ImageIO.read(getClass().getClassLoader().getResource("instructions.png"));
            home = ImageIO.read(getClass().getClassLoader().getResource("home screen.png"));
            levels = ImageIO.read(getClass().getClassLoader().getResource("level screen.png"));
        } catch (IOException e) {
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == playButton) {
            onHome = false;
            onLevels = true;
            playButton.setVisible(false);
            instructionButton.setVisible(false);
            lvlThreeButton.setVisible(true);
            lvlTwoButton.setVisible(true);
            lvlOneButton.setVisible(true);
            backButton.setVisible(true);
        }
        if (event.getSource() == instructionButton) {
            onHome = false;
            onInstructions = true;
            playButton.setVisible(false);
            instructionButton.setVisible(false);
            backButton.setVisible(true);
        }
        if (event.getSource() == backButton) {
            onHome = true;
            onInstructions = false;
            onLevels = false;
            playButton.setVisible(true);
            instructionButton.setVisible(true);
            lvlThreeButton.setVisible(false);
            lvlTwoButton.setVisible(false);
            lvlOneButton.setVisible(false);
            backButton.setVisible(false);
        }
        if (event.getSource() == lvlOneButton) {
            lvlThreeButton.setVisible(false);
            lvlTwoButton.setVisible(false);
            lvlOneButton.setVisible(false);
            backButton.setVisible(false);
            this.setVisible(false);
            this.level = 1;
        }
        if (event.getSource() == lvlTwoButton) {
            lvlThreeButton.setVisible(false);
            lvlTwoButton.setVisible(false);
            lvlOneButton.setVisible(false);
            backButton.setVisible(false);
            this.setVisible(false);
            this.level = 2;
        }
        if (event.getSource() == lvlThreeButton) {
            lvlThreeButton.setVisible(false);
            lvlTwoButton.setVisible(false);
            lvlOneButton.setVisible(false);
            backButton.setVisible(false);
            this.setVisible(false);
            this.level = 3;
        }
    }
    
    public int level() {
      return this.level;
    }
    
    @Override
    public void keyTyped(KeyEvent event) {}
    
    @Override
    public void keyPressed(KeyEvent event) {}
    
    @Override
    public void keyReleased(KeyEvent event) {}
    
    @Override
    public void mouseClicked(MouseEvent e) {}
    
    @Override
    public void mousePressed(MouseEvent e) {}
    
    @Override
    public void mouseReleased(MouseEvent e) {}
    
    @Override
    //makes button color lighter when hovering over
        public void mouseEntered(MouseEvent e) {
        if (e.getSource() == playButton){
            playButton.setBackground(new Color(253, 214, 214));
        }
        if (e.getSource() == lvlOneButton){
            lvlOneButton.setBackground(new Color(253, 214, 214));
        }
        if (e.getSource() == lvlTwoButton){
            lvlTwoButton.setBackground(new Color(253, 214, 214));
        }
        if (e.getSource() == lvlThreeButton){
            lvlThreeButton.setBackground(new Color(253, 214, 214));
        }
        if (e.getSource() == backButton){
            backButton.setBackground(new Color(253, 214, 214));
        }
        if (e.getSource() == instructionButton){
            instructionButton.setBackground(new Color(253, 214, 214));
        }
        
    }
    
    @Override
    //returns button color to original when not hovering
        public void mouseExited(MouseEvent e) {
        if (e.getSource() == playButton){
            playButton.setBackground(new Color(255, 115, 115));
        }
        if (e.getSource() == lvlOneButton){
            lvlOneButton.setBackground(new Color(255, 115, 115));
        }
        if (e.getSource() == lvlTwoButton){
            lvlTwoButton.setBackground(new Color(255, 115, 115));
        }
        if (e.getSource() == lvlThreeButton){
            lvlThreeButton.setBackground(new Color(255, 115, 115));
        }
        if (e.getSource() == backButton){
            backButton.setBackground(new Color(255, 115, 115));
        }
        if (e.getSource() == instructionButton){
            instructionButton.setBackground(new Color(255, 115, 115));
        }
    }
    
    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        
        if (onHome){
           g2d.drawImage(home, 0, 0, this.getWidth(), this.getHeight(), null);
        }

        else if (onInstructions){
            g2d.drawImage(instructions, 0, 0, this.getWidth(), this.getHeight(), null);
        }
        
        else if (onLevels){
            g2d.drawImage(levels, 0, 0, this.getWidth(), this.getHeight(), null);
        }
        super.paintComponents(g);
        super.repaint();
    }
}