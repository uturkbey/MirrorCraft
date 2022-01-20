import java.util.ArrayList;
import java.awt.*;
/**
 * This class represents plane mirror objects which has a position
 * and able to reflect LightRay objects according to physical laws.
 * @author Utku Türkbey
 * @version 30.04.2017(version 1)-6.05.2017(Version 2)
 */
public class PlaneMirror extends OpticalObject
{
   private Vec2 start; // start point of mirror
   private Vec2 end; // end point of mirror
   private double slope; // slope of the mirror
   private ArrayList<LightRay> lights; // array list of light rays of plane mirror
   
   public PlaneMirror( int firstX, int firstY, int secondX, int secondY )
   {
      start = new Vec2( firstX, firstY );
      end = new Vec2( secondX, secondY );
      slope = ( end.getY() - start.getY() ) / ( end.getX() - start.getX() );
      lights = new ArrayList<LightRay>();
   }
   
   /**
    * This method draws the plane mirror as a straight line
    * @param g is the Graphics type object which helps to draw
    * this object
    */
   public void draw ( Graphics g )
   {
      g.setColor( new Color(200, 16, 39));
      g.drawLine( (int)start.getX(), (int)start.getY(), (int)end.getX(), (int)end.getY() );
      g.setColor( Color.cyan );
      g.drawLine( (int)( (start.getX() + end.getX() ) / 2), (int)((start.getY() + end.getY()) / 2), (int)(( (start.getX() + end.getX() ) / 2) + (start.getY() - end.getY()) ), (int)((start.getY() + end.getY()) /2 + end.getX() - start.getX())  );
   }
   
   /**
    * This method checks if a light ray, given as parameter,
    * is intersecting the plane mirror object
    * @param light is the LightRay object to be checked if intersecting
    * @return true if the LightRay object and this PlaneMirror objects are
    * intersecting, false otherwise
    */
   public boolean isIntersecting ( LightRay light )
   {
      
      double t; // the parameter of the parametric equation of light ray
      double xIntersection;  // if there is an intersection the x component of intersection point
      double yIntersection;  // if there is an intersection the y component of intersection point
      
      t = 0;
      xIntersection = 0;
      yIntersection = 0;
      
      // calculates the t, which is the parameter of light ray's parametric equation at the point of intersection,
      // by using both the light ray's and plane mirror's equations
      t = ( ( light.getOriginVec().getX() * slope ) - ( start.getX() * slope ) + ( start.getY() ) - ( light.getOriginVec().getY() ) ) /
         ( ( light.getDirectionVec().getY() ) - ( light.getDirectionVec().getX() * slope ) );
      // checks if t is a valid value for a real intersection
      if ( t <= 0 )
      {
         return false;
      }
      
      
      // calculates the x component of intersection point by substituting t in the parametric equation of ray
      xIntersection = light.getOriginVec().getX() + t * light.getDirectionVec().getX();
      
      // calculates the y component of intersection point by substituting t in the parametric equation of ray
      yIntersection = light.getOriginVec().getY() + t * light.getDirectionVec().getY();
      
      // checks if the intersection point is on the line segment of plane mirror
      if ( xIntersection < Math.min( start.getX(), end.getX() )  || xIntersection > Math.max( start.getX(), end.getX() ) )
      {
         return false;
      }
      else if ( yIntersection < Math.min( start.getY(), end.getY() )  || yIntersection > Math.max( start.getY(), end.getY() ) )
      {
         return false;
      }
      else
      {
         return true;
      }
      
      
   }
   
   /**
    * This method first calculates the angle between the PlaneMirror object
    * and LightRay object which is given as a parameter and then by using this
    * angle and getIntersectionPoint X, Y methods creates a new
    * LightRay object symmetric to the firt LightRay object and adds it to the
    * light ArrayList of this PlaneMirror object
    * @param light is the LightRay coming to the mirror
    */
   public void reflect( LightRay light )
   {
      Vec2 newLightDirection;
      Vec2 normal;
      double c1;
      c1 = 0;
      
      normal = new Vec2( start.getY() - end.getY(), end.getX() - start.getX() );
      normal.normalize();
      
      
      if ( this.isIntersecting( light ) )
      {
         c1 = -1 * ( normal.getX() * light.getDirectionVec().getX() + normal.getY() * light.getDirectionVec().getY() );
         newLightDirection = new Vec2( light.getDirectionVec().getX() + 2 * normal.getX() * c1 , light.getDirectionVec().getY() + 2 * normal.getY() * c1 );
         
         light.setEndX(this.getIntersectionPointX(light));
         light.setEndY(this.getIntersectionPointY(light));
         lights.add( new LightRay( this.getIntersectionPointX(light), this.getIntersectionPointY(light), this.getIntersectionPointX(light) + newLightDirection.getX() * 1000, this.getIntersectionPointY(light) + newLightDirection.getY() * 1000 ) );
      }
      
   }
   
   /**
    * This method calculates and returns the Y component of intersection point
    * of this PlaneMirror object and LightRay object, given as parameter, coming to the mirror
    * @param light is the LightRay coming to the mirror
    * @return 0 if the intersection is invalid or the x component of intersection point
    */
   public double getIntersectionPointX ( LightRay light )
   {
      double t; // the parameter of the parametric equation of light ray
      t = 0; // initializes to 0;
      
      if ( this.isIntersecting( light ) )
      {
         // calculates the t, which is the parameter of light ray's parametric equation at the point of intersection,
         // by using both the light ray's and plane mirror's equations
         t = ( ( light.getOriginVec().getX() * slope ) - ( start.getX() * slope ) + ( start.getY() ) - ( light.getOriginVec().getY() ) ) /
            ( ( light.getDirectionVec().getY() ) - ( light.getDirectionVec().getX() * slope ) );
         
         // calculates and returns the x component of intersection point by substituting t in the parametric equation of ray
         return light.getOriginVec().getX() + t * light.getDirectionVec().getX();
         
      }
      else
      {
         return 0;
         
      }
   }
   
   /**
    *  This method calculates and returns the Y component of intersection point
    * of this PlaneMirror object and LightRay object, given as parameter, coming to the mirror
    * @param light is the LightRay coming to the mirror
    */
   public double getIntersectionPointY ( LightRay light )
   {
      
      double t; // the parameter of the parametric equation of light ray
      t = 0; // initializes to 0;
      
      if ( this.isIntersecting( light ) )
      {
         // calculates the t, which is the parameter of light ray's parametric equation at the point of intersection,
         // by using both the light ray's and plane mirror's equations
         t = ( ( light.getOriginVec().getX() * slope ) - ( start.getX() * slope ) + ( start.getY() ) - ( light.getOriginVec().getY() ) ) /
            ( ( light.getDirectionVec().getY() ) - ( light.getDirectionVec().getX() * slope ) );
         // calculates and returns the y component of intersection point by substituting t in the parametric equation of ray
         return light.getOriginVec().getY() + t * light.getDirectionVec().getY();
      }
      else
      {
         return 0;
      }
      
   }
   
   public double getDistance( LightRay light )
   {  
      if ( this.isIntersecting(light) )
      {
         return Math.sqrt( Math.pow( this.getIntersectionPointX(light) - light.getOriginVec().getX(), 2 ) + Math.pow( this.getIntersectionPointY(light) - light.getOriginVec().getY(), 2 ) );
      }
      else
      {
         return 10000;
      }
   }
   
   public ArrayList<LightRay> getLights()
   {
      return lights;
   }
   
   public LightRay reflectReturner( LightRay light )
   {
      Vec2 newLightDirection;
      Vec2 normal;
      double c1;
      c1 = 0;
      
      normal = new Vec2( start.getY() - end.getY(), end.getX() - start.getX() );
      normal.normalize();
      
      
      if ( this.isIntersecting( light ) )
      {
         c1 = -1 * ( normal.getX() * light.getDirectionVec().getX() + normal.getY() * light.getDirectionVec().getY() );
         newLightDirection = new Vec2( light.getDirectionVec().getX() + 2 * normal.getX() * c1 , light.getDirectionVec().getY() + 2 * normal.getY() * c1 );
         
         light.setEndX(this.getIntersectionPointX(light));
         light.setEndY(this.getIntersectionPointY(light));
         return new LightRay( this.getIntersectionPointX(light), this.getIntersectionPointY(light), this.getIntersectionPointX(light) + newLightDirection.getX() * 1000, this.getIntersectionPointY(light) + newLightDirection.getY() * 1000 ) ;
      }
      else
         return null;
      
   }
   
   
}
