import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;

//            playButton.setContentAreaFilled(false);
//            playButton.setEnabled(false);

public class Frame extends JPanel implements ActionListener, KeyListener, MouseListener{
    
    JButton playButton;
    JButton lvlOneButton;
    JButton lvlTwoButton;
    JButton lvlThreeButton;
    JButton backButton;
    GravityAndJumping demo = new GravityAndJumping();
    
    //Music mainTheme = new Music("Hyrule Field.wav");
    
    //Image fadeRectangle = new ImageIcon("black rectangle.png").getImage();
    BufferedImage fadeRectangle = null; 
    Timer timer = new Timer(10, this);
    float alpha = 1f;
    
    Frame(){
//        mainTheme.start();
//        mainTheme.loop();
        
        loadImage();
        timer.start();
        
        playButton = new JButton();
        playButton.setBounds(400, 400, 150, 70);
        playButton.addActionListener(this);
        playButton.addMouseListener(this);
        playButton.setText("Play");
        playButton.setFocusable(false);
        playButton.setFont(new Font("Dialog", Font.BOLD, 40));
        playButton.setForeground(Color.white);
        playButton.setBackground(new Color(255, 115, 115));
        playButton.setBorder(BorderFactory.createLineBorder(Color.white, 5));
        playButton.setVisible(true);
        
        lvlOneButton = new JButton();
        lvlOneButton.setBounds(210, 250, 150, 150);
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
        lvlTwoButton.setBounds(410, 250, 150, 150);
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
        lvlThreeButton.setBounds(610, 250, 150, 150);
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
        this.add(lvlOneButton);
        this.add(lvlTwoButton);
        this.add(lvlThreeButton);
        this.add(backButton);
        
    }
    
    public void loadImage(){
        try {
            fadeRectangle = ImageIO.read(getClass().getClassLoader().getResource("black rectangle.png"));
        } catch (IOException e) {
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent event) {
        alpha += -0.01f;
        repaint();
        if (alpha <= 0) {
            alpha = 0;
            timer.stop();
        }
        if (event.getSource() == playButton) {
            fadeOut();
            playButton.setVisible(false);
            lvlThreeButton.setVisible(true);
            lvlTwoButton.setVisible(true);
            lvlOneButton.setVisible(true);
            backButton.setVisible(true);
        }
        if (event.getSource() == backButton) {
            fadeOut();
            playButton.setVisible(true);
            lvlThreeButton.setVisible(false);
            lvlTwoButton.setVisible(false);
            lvlOneButton.setVisible(false);
            backButton.setVisible(false);
        }
        if (event.getSource() == lvlOneButton) {
            fadeOut();
            lvlThreeButton.setVisible(false);
            lvlTwoButton.setVisible(false);
            lvlOneButton.setVisible(false);
            backButton.setVisible(false);
            //do level one stuff here fjkdsnfkjdsnn
            demo.run();
        }
    }
    
    public void fadeOut(){
        loadImage();
        alpha = 1;
        timer.start();
        
        alpha += -0.01f;
        repaint();
        if (alpha <= 0) {
            alpha = 0;
            timer.stop();
        }
    }
    
    @Override
    public void keyTyped(KeyEvent event) {
    }
    
    @Override
    public void keyPressed(KeyEvent event) {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void keyReleased(KeyEvent event) {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        
        
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        
        
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
        
        
    }
    
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
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2d.drawImage(fadeRectangle, 0, 0, null);
    }
}