import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * This class extends JPanel and creates a panel for tools.
 */

public class ToolPanel extends JPanel
{
    //   ArrayList<JRadioButton> buttons ;
    JRadioButton rayButton;
    JRadioButton mirrorButton;
    JRadioButton concaveMirrorButton;
    JRadioButton convexMirroButton;
    JRadioButton prismButton;
    JRadioButton deleteButton;
    JButton deleteAllButton;
    JButton resetButton;
    JButton backToMainMenu;
    ButtonGroup bG = new ButtonGroup();
    JPanel toolSectionCreaterbottom;
    int x;
    JLabel infoText;


    public ToolPanel()
    {

        toolSectionCreaterbottom = new JPanel();
        toolSectionCreaterbottom.setBackground(Color.DARK_GRAY);
        add(toolSectionCreaterbottom, BorderLayout.SOUTH);

        infoText = new JLabel();


        x = 0;
        setBackground(Color.DARK_GRAY);
        setPreferredSize(new Dimension(3000, 55));
        setFont(new Font("Arial", Font.BOLD, 16));
        setLayout(new FlowLayout());

//      buttons = new ArrayList<JRadioButton>();

        // This part creates the ray button and ads it to others.

        rayButton = new JRadioButton("Ray");
        rayButton.setBackground(Color.DARK_GRAY);
        rayButton.setForeground(Color.WHITE);
        rayButton.setFocusPainted(false);
//      buttons.add(rayButton);
        bG.add(rayButton);
        add(rayButton);
        rayButton.addActionListener(new rayButtonListener());

        // this part creates the mirrorbutton

        mirrorButton = new JRadioButton("Mirror");
        mirrorButton.setBackground(Color.DARK_GRAY);
        mirrorButton.setForeground(Color.WHITE);
        mirrorButton.setFocusPainted(false);
//      buttons.add(mirrorButton);
        bG.add(mirrorButton);
        add(mirrorButton);
        mirrorButton.addActionListener(new mirrorButtonListener());

        // this part creates the concaveMirrorButton

        concaveMirrorButton = new JRadioButton("circularMirror");
        concaveMirrorButton.setBackground(Color.DARK_GRAY);
        concaveMirrorButton.setForeground(Color.WHITE);
        concaveMirrorButton.setFocusPainted(false);
//      buttons.add(concaveMirrorButton);
        bG.add(concaveMirrorButton);
        add(concaveMirrorButton);
        concaveMirrorButton.addActionListener(new concaveMirrorButtonListener());

        // this part creates the convexMirrorButton

        convexMirroButton = new JRadioButton("convexMirror");
        convexMirroButton.setBackground(Color.DARK_GRAY);
        convexMirroButton.setForeground(Color.WHITE);
        convexMirroButton.setFocusPainted(false);
//      buttons.add(convexMirroButton);
       // bG.add(convexMirroButton);
       // add(convexMirroButton);
        convexMirroButton.addActionListener(new convexMirrorButtonListener());

        // This part creates the prismButton

        prismButton = new JRadioButton("Lens");
        prismButton.setBackground(Color.DARK_GRAY);
        prismButton.setForeground(Color.WHITE);
        prismButton.setFocusPainted(false);
//      buttons.add(prismButton);
        bG.add(prismButton);
        add(prismButton);
        prismButton.addActionListener(new LensButtonListener());

        // This part creates the deleteButton

        deleteButton = new JRadioButton("Delete");
        deleteButton.setBackground(Color.DARK_GRAY);
        deleteButton.setForeground(Color.WHITE);
        rayButton.setFocusPainted(false);
//      buttons.add(deleteButton);
        bG.add(deleteButton);
        add(deleteButton);
        deleteButton.addActionListener(new deleteButtonListener());

        // This part creates the deleteAllButton


        deleteAllButton = new JButton("Delete All");
        //deleteAllButton.setBackground(Color.DARK_GRAY);
        //deleteAllButton.setForeground(Color.WHITE);
        deleteAllButton.setFocusPainted(false);
//      buttons.add(deleteAllButton);
        bG.add(deleteAllButton);
        add(deleteAllButton);
        deleteAllButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                JFrame simulFrame = new JFrame("Simulation Mode");
                JPanel simulPanel = new JPanel();
                simulPanel = new SimulationPanel();
                simulFrame.add(simulPanel);
                simulFrame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
                simulFrame.setUndecorated(true);
                simulFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                simulFrame.setVisible(true);
            }
        });
        
        //this part creates return to menu button
        
        backToMainMenu = new JButton("Back to Main Menu");
        backToMainMenu.setFocusPainted(false);
        bG.add(backToMainMenu);
        add(backToMainMenu);
        backToMainMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                 MainMenu returnMenu = new MainMenu();
                 JFrame mainMenu = new JFrame();
                 mainMenu.add(returnMenu);
                 mainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                 mainMenu.setVisible(true);
            }
        });


        add(infoText);


    }

    private class rayButtonListener implements ActionListener
    {
        private rayButtonListener() {
        }

        public void actionPerformed(ActionEvent event) {

            x = 1;
            //infoText.setText("the chosed button : Ray ");


        }
    }

    private class mirrorButtonListener implements ActionListener {
        private mirrorButtonListener() {
        }

        public void actionPerformed(ActionEvent event) {

            x = 2;
            //infoText.setText("the chosed button : mirror ");

        }
    }

    private class concaveMirrorButtonListener implements ActionListener {
        private concaveMirrorButtonListener() {
        }

        public void actionPerformed(ActionEvent event) {

            x = 3;
            //infoText.setText("the chosed button : concaveMirror ");

        }
    }

    private class convexMirrorButtonListener implements ActionListener {
        private convexMirrorButtonListener() {
        }

        public void actionPerformed(ActionEvent event) {

            x = 4;
            //infoText.setText("the chosed button : convexMirror ");

        }
    }

    private class LensButtonListener implements ActionListener {
        private LensButtonListener() {
        }

        public void actionPerformed(ActionEvent event) {
            x = 4;
            //infoText.setText("the chosed button :Lens ");

        }
    }

    private class deleteButtonListener implements ActionListener {
        private deleteButtonListener() {
        }

        public void actionPerformed(ActionEvent event) {

            x = 6;
           // infoText.setText("the chosed button : delete ");
            // this will delete the selected object from the other panel
            // there will be a arraylist where every object taht is added to the other panle will also be added to the arraylist and as we did in the lab04 we wll have a selectable class that each object will extend and if the object is selected(it will be selected by clicking on them ) after selecting the object if one press the delete button it will delete the object from arraylist and after repiainting the object will be gone
        }
    }

    private class deleteAllButtonListener implements ActionListener {
        private deleteAllButtonListener() {
        }

        public void actionPerformed(ActionEvent event) {

            x = 7;
            //infoText.setText("the chosed button : deleteAll");
            // this will delete everything from the other pane

        }
    }

    private class resetButtonListener implements ActionListener {
        private resetButtonListener() {
        }

        public void actionPerformed(ActionEvent event) {

            x = 8;
            //infoText.setText("the chosed button : reset ");

        }
    }

    public int getNumber() {
        return x;
    }
}