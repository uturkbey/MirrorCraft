import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by BUGRA on 13.5.2017.
 */
public class ToolPanelFCM  extends JPanel{

    JRadioButton rayButton;
    JRadioButton mirrorButton;
    JRadioButton concaveMirrorButton;
    JRadioButton deleteButton;
    JRadioButton LineerObstacleButton;
    //JButton deleteAllButton;
    JButton menuButton;
    JButton previousLevel;
    JButton nextLevel;
    ButtonGroup bG = new ButtonGroup();
    int x;
    JLabel infoText;


    public ToolPanelFCM ()
    {
        //toolSectionCreaterbottom = new JPanel();
        //toolSectionCreaterbottom.setBackground(Color.DARK_GRAY);
        //add(toolSectionCreaterbottom, BorderLayout.SOUTH);

        infoText = new JLabel();


        x = 0;
        setBackground(Color.DARK_GRAY);
        setPreferredSize(new Dimension(3000, 55));
        setFont(new Font("Arial", Font.BOLD, 16));
        setLayout(new FlowLayout());
        
        previousLevel = new JButton("Previous Level");
        previousLevel.setFocusPainted(false);
        bG.add(previousLevel);
        add(previousLevel);
        
        nextLevel = new JButton("Next Level");
        nextLevel.setFocusPainted(false);
        bG.add(nextLevel);
        add(nextLevel);

        rayButton = new JRadioButton("Ray");
        rayButton.setBackground(Color.DARK_GRAY);
        rayButton.setForeground(Color.WHITE);
        rayButton.setFocusPainted(false);
//      buttons.add(rayButton);
        bG.add(rayButton);
        add(rayButton);
        rayButton.addActionListener(new rayButtonListener());

        mirrorButton = new JRadioButton("mirror");
        mirrorButton.setBackground(Color.DARK_GRAY);
        mirrorButton.setForeground(Color.WHITE);
        mirrorButton.setFocusPainted(false);
//      buttons.add(mirrorButton);
        bG.add(mirrorButton);
        add(mirrorButton);
        mirrorButton.addActionListener(new mirrorButtonListener());


        concaveMirrorButton = new JRadioButton("circularMirror");
        concaveMirrorButton.setBackground(Color.DARK_GRAY);
        concaveMirrorButton.setForeground(Color.WHITE);
        concaveMirrorButton.setFocusPainted(false);
//      buttons.add(concaveMirrorButton);
        bG.add(concaveMirrorButton);
        add(concaveMirrorButton);
        concaveMirrorButton.addActionListener(new concaveMirrorButtonListener());


        deleteButton = new JRadioButton("Delete");
        deleteButton.setBackground(Color.DARK_GRAY);
        deleteButton.setForeground(Color.WHITE);
        rayButton.setFocusPainted(false);
//      buttons.add(deleteButton);
        bG.add(deleteButton);
        add(deleteButton);
        deleteButton.addActionListener(new deleteButtonListener());

        LineerObstacleButton = new JRadioButton("LineerObstacle");
        LineerObstacleButton.setBackground(Color.DARK_GRAY);
        LineerObstacleButton.setForeground(Color.WHITE);
        LineerObstacleButton.setFocusPainted(false);
//      buttons.add(resetButton);
        bG.add(LineerObstacleButton);
        add(LineerObstacleButton);
        LineerObstacleButton.addActionListener(new LineerObstacleListener());
        
        
        /*
        deleteAllButton = new JButton("Delete All");
        deleteAllButton.setBackground(Color.DARK_GRAY);
        deleteButton.setForeground(Color.WHITE);
        rayButton.setFocusPainted(false);
        buttons.add(deleteButton);
        bG.add(deleteAllButton);
        add(deleteAllButton);
        deleteAllButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                JFrame chalFrame = new JFrame("Simulation Mode");
                JPanel chalPanel = new JPanel();
                chalPanel = new ChallengePanel();
                chalFrame.add(chalPanel);
                chalFrame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
                chalFrame.setUndecorated(true);
                chalFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                chalFrame.setVisible(true);
            }
        }); */
        
        menuButton = new JButton("Back to Main Menu");
        menuButton.setFocusPainted(false);
        bG.add(menuButton);
        add(menuButton);
        menuButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                 MainMenu returnMenu = new MainMenu();
                 JFrame mainMenu = new JFrame();
                 mainMenu.add(returnMenu);
                 mainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                 mainMenu.setVisible(true);
            }
        });


    }

    private class rayButtonListener implements ActionListener
    {
        private rayButtonListener() {
        }

        public void actionPerformed(ActionEvent event) {

            x = 1;
            infoText.setText("the chosed button : Ray ");


        }
    }

    private class mirrorButtonListener implements ActionListener {
        private mirrorButtonListener() {
        }

        public void actionPerformed(ActionEvent event) {

            x = 2;
            infoText.setText("the chosed button : mirror ");

        }
    }

    private class concaveMirrorButtonListener implements ActionListener {
        private concaveMirrorButtonListener() {
        }

        public void actionPerformed(ActionEvent event) {

            x = 3;
            infoText.setText("the chosed button : concaveMirror ");

        }
    }

    private class deleteButtonListener implements ActionListener {
        private deleteButtonListener() {
        }

        public void actionPerformed(ActionEvent event) {

            x = 4;
            // infoText.setText("the chosen button : delete ");
            // this will delete the selected object from the other panel
            // there will be a Arraylist where every object that is added to the other panel will also be added to the arraylist and as we did in the lab04 we wll have a selectable class that each object will extend and if the object is selected(it will be selected by clicking on them ) after selecting the object if one press the delete button it will delete the object from arraylist and after repiainting the object will be gone
        }
    }

    private class LineerObstacleListener implements ActionListener {
        private LineerObstacleListener() {
        }

        public void actionPerformed(ActionEvent event) {

            x = 5;
            infoText.setText("the chosed button : obstacle ");

        }
    }

    public int getNumber() {
        return x;
    }
}
