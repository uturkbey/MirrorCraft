
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MainMenu extends JFrame implements ActionListener{
  
 public MainMenu() {
  
    JFrame menuFrame = new JFrame("Main Menu");
    
    
    //menuFrame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
    menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    menuFrame.setSize(1920, 1080);
    //menuFrame.setLayout(null);
    
    JPanel mainPanel = new JPanel();
    JButton simulButton = new JButton("Simulation Mode");
    JButton chalButton = new JButton("Challenge Mode");
    JButton infoButton = new JButton("Info");
    JButton exitButton = new JButton("Exit");
    
    /*
    simulButton.setSize(360, 180);
    chalButton.setSize(360, 180);
    infoButton.setSize(360, 180);
   
    simulButton.setLocation(740, 180);
    chalButton.setLocation(740, 450);
    infoButton.setLocation(740, 720);
    */
    
    
    menuFrame.setContentPane(new JLabel(new ImageIcon("lensmenu.jpg")));

    menuFrame.setLayout(new GridBagLayout());
    //GridBagConstraints gbc = new GridBagConstraints();
    //gbc.gridwidth = GridBagConstraints.REMAINDER;
    //gbc.fill = GridBagConstraints.HORIZONTAL;
    
    
    simulButton.setPreferredSize(new Dimension(235, 170));
    chalButton.setPreferredSize(new Dimension(235, 170));
    infoButton.setPreferredSize(new Dimension(235, 170));
    exitButton.setPreferredSize(new Dimension(170, 170));
    /*
    chalButton.setPreferredSize(new Dimension(menuFrame.getWidth()/10, menuFrame.getHeight()/10));
    infoButton.setPreferredSize(new Dimension(menuFrame.getWidth()/10, menuFrame.getHeight()/10));
    simulButton.setOpaque(false);
    simulButton.setContentAreaFilled(false);
    //simulButton.setBorderPainted(false);
    chalButton.setOpaque(false);
    chalButton.setContentAreaFilled(false);
    //chalButton.setBorderPainted(false);
    infoButton.setOpaque(false);
    infoButton.setContentAreaFilled(false);
    //infoButton.setBorderPainted(false);
    menuFrame.add(simulButton, gbc);
    menuFrame.add(chalButton, gbc);
    menuFrame.add(infoButton, gbc);
    */
    
    menuFrame.add(simulButton);
    menuFrame.add(chalButton);
    menuFrame.add(infoButton);
    menuFrame.add(exitButton);
    
    //menuFrame.add(simulButton), constraints);
    
    simulButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                JFrame simulFrame = new JFrame("Simulation Mode");
                JPanel simulPanel = new JPanel();
                simulPanel = new SimulationPanel();
                simulFrame.add(simulPanel);
                simulFrame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
                simulFrame.setUndecorated(true);
                simulFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                simulFrame.setVisible(true);
                menuFrame.dispose();
            }
        });
    
    infoButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
            JOptionPane optionPane = new JOptionPane("Is this what you need?", JOptionPane.QUESTION_MESSAGE,JOptionPane.YES_NO_OPTION);
            JDialog dialog = optionPane.createDialog("Dialog");
            dialog.setVisible(true);
        }
    });
    
    chalButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
         JFrame chalFrame = new JFrame("Simulation Mode");
            JPanel chalPanel = new JPanel();
            chalPanel = new ChallengePanel();
            chalFrame.add(chalPanel);
            chalFrame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
            chalFrame.setUndecorated(true);
            chalFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            chalFrame.setVisible(true);
            menuFrame.dispose();
        }
    });
    
    exitButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
            System.exit(0);
        }
    });
    
    mainPanel.add(simulButton);
    mainPanel.add(chalButton);
    mainPanel.add(infoButton);
    mainPanel.add(exitButton);
    menuFrame.add(mainPanel);
    //menuFrame.setUndecorated(true);
    menuFrame.setVisible(true); 
  }

  public void actionPerformed(ActionEvent e) {   
  }
  
  public static void main(String args[]) {
    new MainMenu();
  } 
}