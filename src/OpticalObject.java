import java.awt.*;
import java.util.*;

/**
 * This abstract class represents optical objects which has a position
 * and able to reflect LightRay objects according to physical laws.
 * @author Utku Türkbey
 * @version 6.05.2017
 */
public abstract class OpticalObject 
{
    public abstract void     draw( Graphics g );
    public abstract boolean  isIntersecting ( LightRay light );
    public abstract void     reflect( LightRay light );
    public abstract LightRay reflectReturner( LightRay light );
    public abstract double   getIntersectionPointX ( LightRay light );
    public abstract double   getIntersectionPointY ( LightRay light );
    public abstract double   getDistance( LightRay light );
    public abstract ArrayList<LightRay> getLights();
    
}