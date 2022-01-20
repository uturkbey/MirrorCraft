import java.util.*;
import java.awt.*;

public class CurvedMirror extends OpticalObject
{
   // PROPERTIES
   double radius;
   Vec2 center;
   double degreeStart;
   double degreeFinish;
   ArrayList<LightRay> lights;
   
   // CONSTRUCTORS
   public CurvedMirror (int x1, int y1, int x2, int y2, int x3, int y3)
   {
      double radius2 = Math.sqrt( Math.pow( x2 - x1, 2 ) + Math.pow( y2 - y1, 2 ) );
      double radius3 = Math.sqrt( Math.pow( x3 - x1, 2 ) + Math.pow( y3 - y1, 2 ) );
      radius = radius2;     // or radius3 depends on preference of point order
      center = new Vec2 (x1, y1);
      degreeStart = giveDegree (x2, y2, radius2);
      degreeFinish = giveDegree (x3, y3, radius3);
      lights = new ArrayList<LightRay>();
   }
   
   
   
   
   
   
   // METHODS
   /**
    * This method tries wether it intersect or not
    * @param  light is tried object
    * @return boolean true or false
    */
   public boolean isIntersecting ( LightRay light )
   {
      
      double t1, t2;    // two prameters of intersection
      double deltaA = Math.pow (light.getDirectionVec().getX(), 2) + Math.pow( light.getDirectionVec().getY(), 2);
      double deltaB = 2 * ( light.getOriginVec().getX() * light.getDirectionVec().getX() + light.getOriginVec().getY() * light.getDirectionVec().getY() - center.getX() * light.getDirectionVec().getX() - center.getY() * light.getDirectionVec().getY() );
      double deltaC = Math.pow(center.getX(), 2) + Math.pow(center.getY(), 2) + Math.pow(light.getOriginVec().getX(), 2) + Math.pow(light.getOriginVec().getY(), 2) - Math.pow (radius, 2) - 2 * (center.getX() * light.getOriginVec().getX() + center.getY() * light.getOriginVec().getY() );
      double deltaD = deltaB * deltaB - 4 * deltaA * deltaC;
      t1 = ( -1 * deltaB - Math.sqrt (deltaD) ) / (2 * deltaA);
      t2 = ( -1 * deltaB + Math.sqrt (deltaD) ) / (2 * deltaA);
      
      if ( correctT (t1, t2, light) > 0 )    // t can be zero, but then light starts from point on mirror, which is not what we will do in simulation
      {
         return true;
      }
      
      return false;
   }
   
   
   /**
    * This method returns x coordinate of first correct intersection
    * @param  light is tried object
    * @return double x point
    */
   public double getIntersectionPointX (LightRay light)
   {
      double t1 = 0;
      double t2 = 0;
      
      if ( this.isIntersecting (light) )
      {
         System.out.println("IS INTERSECTING");
         double deltaA = Math.pow (light.getDirectionVec().getX(), 2) + Math.pow( light.getDirectionVec().getY(), 2);
         double deltaB = 2 * ( light.getOriginVec().getX() * light.getDirectionVec().getX() + light.getOriginVec().getY() * light.getDirectionVec().getY() - center.getX() * light.getDirectionVec().getX() - center.getY() * light.getDirectionVec().getY() );
         double deltaC = Math.pow(center.getX(), 2) + Math.pow(center.getY(), 2) + Math.pow(light.getOriginVec().getX(), 2) + Math.pow(light.getOriginVec().getY(), 2) - Math.pow (radius, 2) - 2 * (center.getX() * light.getOriginVec().getX() + center.getY() * light.getOriginVec().getY() );
         double deltaD = deltaB * deltaB - 4 * deltaA * deltaC;
         t1 = ( -1 * deltaB - Math.sqrt (deltaD) ) / (2 * deltaA);
         t2 = ( -1 * deltaB + Math.sqrt (deltaD) ) / (2 * deltaA);
         return light.getOriginVec().getX() + correctT (t1, t2, light) * light.getDirectionVec().getX();
      }
      else
      {
         System.out.println("IS NOT NOT NOT NOT INTERSECTING");
         return -10000;
      }
   }
   
   
   /**
    * This method returns y coordinate of first correct intersection
    * @param  light is tried object
    * @return double y point
    */
   public double getIntersectionPointY (LightRay light)
   {
      double t1 = 0;
      double t2 = 0;
      
      if ( this.isIntersecting (light) )
      {
         System.out.println("IS INTERSECTING");
         double deltaA = Math.pow (light.getDirectionVec().getX(), 2) + Math.pow( light.getDirectionVec().getY(), 2);
         double deltaB = 2 * ( light.getOriginVec().getX() * light.getDirectionVec().getX() + light.getOriginVec().getY() * light.getDirectionVec().getY() - center.getX() * light.getDirectionVec().getX() - center.getY() * light.getDirectionVec().getY() );
         double deltaC = Math.pow(center.getX(), 2) + Math.pow(center.getY(), 2) + Math.pow(light.getOriginVec().getX(), 2) + Math.pow(light.getOriginVec().getY(), 2) - Math.pow (radius, 2) - 2 * (center.getX() * light.getOriginVec().getX() + center.getY() * light.getOriginVec().getY() );
         double deltaD = deltaB * deltaB - 4 * deltaA * deltaC;
         t1 = ( -1 * deltaB - Math.sqrt (deltaD) ) / (2 * deltaA);
         t2 = ( -1 * deltaB + Math.sqrt (deltaD) ) / (2 * deltaA);
         return light.getOriginVec().getY() + correctT (t1, t2, light) * light.getDirectionVec().getY();
      }
      else
      {
         System.out.println("IS NOT NOT NOT NOT INTERSECTING");
         return -10000;
      }
   }
   
   
   /**
    * This method reflectr light and creates
    * new light using intersection information
    * @param  light is coming light
    * @return new reflected LightRay
    */
   public void reflect (LightRay light)
   {
      Vec2 newLightDirection;
      Vec2 normal;
      double c1;
      
      normal = new Vec2( this.getIntersectionPointX (light) - center.getX(), this.getIntersectionPointY (light) - center.getY() );
      normal.normalize();
      c1 = 0;
      
      if ( this.isIntersecting (light) )
      {
         System.out.println ("REFLECTING");
         c1 = -1 * ( normal.getX() * light.getDirectionVec().getX() + normal.getY() * light.getDirectionVec().getY() );
         newLightDirection = new Vec2 (light.getDirectionVec().getX() + 2 * normal.getX() * c1, light.getDirectionVec().getY() + 2 * normal.getY() * c1);
         light.setEndX( this.getIntersectionPointX (light) );
         light.setEndY( this.getIntersectionPointY (light) );
         lights.add(new LightRay( this.getIntersectionPointX(light), this.getIntersectionPointY(light),this.getIntersectionPointX(light) + 1000 * newLightDirection.getX(), this.getIntersectionPointY(light) + 1000 * newLightDirection.getY() ));
      }
   }
   
   
   /**
    * This method crucial to find out correct intersection
    * @param  light is tried object, t1 and t2 is solutions to be tried
    * @return double t which is correct
    */
   private double correctT (double t1, double t2, LightRay light)
   {
      double intersectionX1 = light.getOriginVec().getX() + t1 * light.getDirectionVec().getX();
      double intersectionY1 = light.getOriginVec().getY() + t1 * light.getDirectionVec().getY();
      double intersectionX2 = light.getOriginVec().getX() + t2 * light.getDirectionVec().getX();
      double intersectionY2 = light.getOriginVec().getY() + t2 * light.getDirectionVec().getY();
      double radius1 = Math.sqrt( Math.pow( intersectionX1 - center.getX(), 2 ) + Math.pow( intersectionY1 - center.getY(), 2 ) );
      double radius2 = Math.sqrt( Math.pow( intersectionX2 - center.getX(), 2 ) + Math.pow( intersectionY2 - center.getY(), 2 ) ); 
      double degree1 = giveDegree (intersectionX1, intersectionY1, radius1);
      double degree2 = giveDegree (intersectionX2, intersectionY2, radius2);
      
      if ( isBetween (degree1) && isBetween (degree2) )
      {
         return Math.min (t1, t2);              // first intersection happens faster and t should be minimum :D
      }
      else if ( isBetween (degree1) )
      {
         return t1;
      }
      else if ( isBetween (degree2) )
      {
         return t2;
      }
      else
      {
         return -10000;    // very big negatife number, to assure that intersection point is negative, meaning that it is not intersecting
      }
   }
   
   
   /**
    * This method returns true if degree is between other two degrees
    * @param  degree
    * @return boolean true or false
    */
   private boolean isBetween (double degree)
   {
      if ( degree > degreeStart && degree < degreeFinish )
      {
         return true;
      }
      
      return false;
   }
   
   
   /**
    * This method returns degree from giving point and center by calclations
    * @param  x, double y points
    * @return double degree
    */
   private double giveDegree (double x, double y, double radius)
   {
      if ( y == center.getY() )     // special cases, not needed actually, but if we place it perfectly, there always little possibility
      {
         if ( x > center.getX() )
         {
            return 0;
         }
         else
         {
            return 180;
         }
      }
      
      if ( x == center.getX() )      // special cases, not needed actually, but if we place it perfectly, there always little possibility
      {
         if ( y > center.getY() )
         {
            return 90;
         }
         else
         {
            return 270;
         }
      }
      
      if ( y > center.getY() && x > center.getX() )
      {
         return Math.toDegrees( Math.asin( ( y - center.getY() ) / radius ) );
      }
      else if ( y > center.getY() && x < center.getX() )
      {
         return 180 - Math.toDegrees( Math.asin( ( y - center.getY() ) / radius ) );
      }
      else if ( y < center.getY() && x < center.getX() )
      {
         return 180 - Math.toDegrees( Math.asin( ( y - center.getY() ) / radius ) );
      }
      else
      {
         return 360 + Math.toDegrees( Math.asin( ( y - center.getY() ) / radius ) );
      }
   }
   
   
   /**
    * This method draws circle arc
    * @param Graphics g
    */
   public void draw (Graphics g)
   {
      g.setColor( new Color (200, 133, 193) );
      double degreeStartTemp = degreeStart;
      degreeStart = 360 - degreeStart;
      double degreeFinishTemp = degreeFinish;
      degreeFinish = 360 - degreeFinish; 
      
      if ( degreeStart < degreeFinish )
      {
         g.drawArc( (int)(center.getX() - radius), (int)(center.getY() - radius), 2 * (int)radius, 2 * (int)radius, (int)(degreeStart), (int)(degreeFinish - degreeStart) );
      }
      if ( degreeStart > degreeFinish )
      {
         g.drawArc( (int)(center.getX() - radius), (int)(center.getY() - radius), 2 * (int)radius, 2 * (int)radius, (int)(degreeStart), (int)(360 + degreeFinish - degreeStart) );
      }
      
      degreeStart = degreeStartTemp;
      degreeFinish = degreeFinishTemp;
   }
   
   
   
   
   
   public double   getDistance( LightRay light ){
      return 0;
   }
   
   @Override
   public ArrayList<LightRay> getLights() {
      return lights;
   }
   
   public LightRay     reflectReturner( LightRay light ){
      return null;
   }
   
   
   public void clearLights()
   {};
   
   
   
}