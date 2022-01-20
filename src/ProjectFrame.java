import java.awt.*;
import java.awt.event.*;
import java.util.Iterator;
import javax.swing.*;
import java.util.*;

public class ProjectFrame  extends JFrame
{
    SimulationPanel trial ;
    JPanel  sectionCreaterNorth ;
    JPanel sectionCreaterRight;
    JPanel sectionCreaterLeft ;
    JPanel sectionCreaterSouth;

    public ProjectFrame()
    {
        trial = new SimulationPanel();
//      setLayout(new BorderLayout(5,5));
        setBackground(Color.gray);



        sectionCreaterNorth   = new JPanel();
        sectionCreaterRight  = new JPanel();
        sectionCreaterLeft  = new JPanel();
        sectionCreaterSouth  = new JPanel();

        sectionCreaterNorth.setBackground(Color.DARK_GRAY);
        sectionCreaterRight.setBackground(Color.DARK_GRAY);
        sectionCreaterLeft.setBackground(Color.DARK_GRAY);
        sectionCreaterSouth.setBackground(Color.DARK_GRAY);

//      sectionCreaterNorth.setPreferredSize(new Dimension(3000,2 ));
//      sectionCreaterSouth.setPreferredSize(new Dimension(3000,2 ));
//      sectionCreaterRight.setPreferredSize(new Dimension(2,1996 ));
//      sectionCreaterLeft.setPreferredSize(new Dimension(2,1996 ));

        add(trial,BorderLayout.CENTER);
        add(sectionCreaterNorth,BorderLayout.PAGE_START);
        add(sectionCreaterSouth,BorderLayout.PAGE_END);
        add(sectionCreaterRight,BorderLayout.LINE_END);
        add(sectionCreaterLeft,BorderLayout.LINE_START);


        setPreferredSize(new Dimension(3000,2000));
    }
}