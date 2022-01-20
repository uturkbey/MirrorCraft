import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Created by BUGRA on 13.5.2017.
 */
public class ChallengePanel extends JPanel {

    simulationClickListener clicker;

    int xPosition;
    int yPosition;
    int xSecondPosition;
    int ySecondPosition;
    int xThirdPosition;
    int yThirdPosition;
    int cycler;

    ToolPanelFCM  trialtoolPanel;
    JPanel toolSectionCreaterRight;
    JPanel toolSectionCreaterLeft;
    JPanel toolSectionCreaterSouth;

    OpticalObjectContainer objectContainer;
    ArrayList<LightRay> lights;
    ArrayList<LightRay> lightsOfObjects;
    LightRay currentLight;
    LightRay currentLight2;
    OpticalObject currentObject;
    OpticalObject currentObject2;

    boolean isFirstAdded;
    boolean reflectionHappened;


    //this will be the object that will circle thorugh each situatin if the draw method is invoked and this  is the object that will be entered to the arraylist

    public ChallengePanel () {

        setBackground(Color.DARK_GRAY);
        setPreferredSize(new Dimension(this.getWidth(), this.getHeight()));
        setFont(new Font("Arial", Font.BOLD, 16));
//      setLayout(new FlowLayout());
        clicker = new simulationClickListener();
        addMouseListener(clicker);
        trialtoolPanel = new ToolPanelFCM ();
        add(trialtoolPanel, BorderLayout.NORTH);
        objectContainer = new OpticalObjectContainer();
        lights = new ArrayList<LightRay>();
        lightsOfObjects = new ArrayList<LightRay>();
        cycler = 1;

       // toolSectionCreaterRight = new JPanel();
       // toolSectionCreaterLeft = new JPanel();
       // toolSectionCreaterSouth = new JPanel();

       // toolSectionCreaterRight.setBackground(Color.DARK_GRAY);
       // toolSectionCreaterLeft.setBackground(Color.DARK_GRAY);
       // toolSectionCreaterSouth.setBackground(Color.DARK_GRAY);

        reflectionHappened = false;


//      add(toolSectionCreaterRight,BorderLayout.LINE_END);
//      add(toolSectionCreaterLeft ,BorderLayout.LINE_START);
//      add(toolSectionCreaterSouth , BorderLayout.PAGE_END);


    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (objectContainer.size() == 0) {
            for (int i = 0; i < lights.size(); i++) {
                lights.get(i).limitRay(getWidth(), getHeight());
            }
        }

        reflectionHappened = false;

        for (int i = 0; i < lights.size(); i++) {
            if (objectContainer.getCloser(lights.get(i)) != null)
            {


                currentObject = objectContainer.getCloser(lights.get(i));
                currentObject.reflect(lights.get(i));
                currentObject2 = currentObject;
                reflectionHappened = true;
            }
        }

        // while (reflectionHappened == true)
        // {}
        if(reflectionHappened) {
            System.out.println("reflection happened ");
            reflectionHappened = false;
            for (int i = 0; i < objectContainer.size(); i++) {
                lightsOfObjects = objectContainer.get(i).getLights();

                for (int j = 0; j < lightsOfObjects.size(); j++) {

                    if (objectContainer.getCloser(lightsOfObjects.get(j)) != null && (objectContainer.getCloser(lightsOfObjects.get(j)) != currentObject2))  {
                        System.out.println("reflection happened  and inner loop");
                        currentObject = objectContainer.getCloser(lightsOfObjects.get(j));
                        currentObject.reflect(lightsOfObjects.get(j)) ;
                        //lights.add(currentObject.reflectReturner(lightsOfObjects.get(j)));
                        reflectionHappened = true;
                    }
                    else
                        reflectionHappened = false;

                }
            }
        }



        for (int i = 0; i < lights.size(); i++)
        {
            (lights.get(i)).draw(g);
        }

        for (int i = 0; i < objectContainer.size(); i++)
        {
            objectContainer.get(i).draw(g);
        }

        for (int i = 0; i < objectContainer.size(); i++)
        {

            lightsOfObjects = objectContainer.get(i).getLights();
            if (lightsOfObjects.size() > 0)
            {
                for (int j = 0; j < lightsOfObjects.size(); j++)
                {
                    lightsOfObjects.get(j).draw(g);
                }
            }
        }

        for (int i = 0; i < objectContainer.size(); i++) {
            lightsOfObjects = objectContainer.get(i).getLights();
            lightsOfObjects.clear();
        }


    }


    private class simulationClickListener extends MouseAdapter {


        private boolean isFirstClick = true;

        public void mousePressed(MouseEvent event) {
            // for taking two points each after each
            if (trialtoolPanel.getNumber() == 1 || trialtoolPanel.getNumber() == 2 || trialtoolPanel.getNumber() == 5 ) {
                if (isFirstClick) {
                    // System.out.println("it is in the first click");

                    xPosition = event.getX();
                    yPosition = event.getY();
                    isFirstClick = false;
                } else if (!isFirstClick) {
                    //System.out.println("it is in the second click");
                    xSecondPosition = event.getX();
                    ySecondPosition = event.getY();
                    isFirstClick = true;

                    if (trialtoolPanel.getNumber() == 1)
                    {
                        currentLight = new LightRay(xPosition, yPosition, xSecondPosition, ySecondPosition);
                        isFirstAdded = true;
                        currentLight.limitRay(getWidth(), getHeight());
                        lights.add(currentLight);
                        repaint();

                    }

                    if (trialtoolPanel.getNumber() == 2)
                    {
                        currentObject = new PlaneMirror(xPosition, yPosition, xSecondPosition, ySecondPosition);
                        isFirstAdded = false;
                        objectContainer.add(currentObject);
                        repaint();

                    }
                    if (trialtoolPanel.getNumber() == 5)
                    {
                        currentObject = new LinearObstacle(xPosition, yPosition, xSecondPosition, ySecondPosition);
                        isFirstAdded = false;
                        objectContainer.add(currentObject);
                        repaint();

                    }

//
//            else if(trialtoolPanel.getNumber == 5)
//            {
//               currentObject = new OpticalObject( xPosition, yPosition , xSecondPosition, ySecondPosition)
//                  objects.add(currentObject);
//               (Lens)currentObject .drawOpticalObject ( Graphics g ); wiil draw the lens to the plane
//            }
//
//
//
                }
            }
// for taking three points after and after
            if (trialtoolPanel.getNumber() == 3 ) {
                if (cycler == 1) {
                    xPosition = event.getX();
                    yPosition = event.getY();
                    cycler = 2;
                } else if (cycler == 2) {

                    xSecondPosition = event.getX();
                    ySecondPosition = event.getY();
                    cycler = 3;
                } else if (cycler == 3) {

                    xThirdPosition = event.getX();
                    yThirdPosition = event.getY();

                    if (trialtoolPanel.getNumber() == 3) {
                        System.out.println(xThirdPosition + " " + yThirdPosition);
                        currentObject = new CurvedMirror( xPosition, yPosition , xSecondPosition, ySecondPosition, xThirdPosition, yThirdPosition);
                        objectContainer.add(currentObject);
                        repaint();

                    }

                    cycler = 1;
                }
            }

            if (trialtoolPanel.getNumber() == 4) {
                if (isFirstAdded) {
                    if (lights.size() > 0) {
                        lights.remove(lights.get(lights.size() - 1));
                        repaint();
                    }

                    if (lights.size() == 0) {
                        isFirstAdded = false;
                    }
                } else if (!isFirstAdded) {
                    if (objectContainer.size() > 0) {
                        objectContainer.remove(objectContainer.get(objectContainer.size() - 1));
                        repaint();
                    }

                    if (objectContainer.size() == 0) {
                        isFirstAdded = true;
                    }
                }
            }

            if (trialtoolPanel.getNumber() == 7) {
                objectContainer.clear();
                lights.clear();
                repaint();

            }


            // in here after finding the position it will first check if there is any object in the position if there is it will make its selectable value true so it iwll be used to delete the item
            // if there is not than it will set the positions that will be later used to add the object to the poin
            // since the code is not finished yet we comment this part after the part of the codes and objects finished than it will be usable

        }
    }
}


