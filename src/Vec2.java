/**
 * This class represents a vector
 * @author Utku Türkbey
 * @version 6.05.2017
 */
public class Vec2
{
   
   // properties
   private double x;
   private double y;
   
   // constructor
   public Vec2( double x, double y )
   {
      this.x = x;
      this.y = y;
      
   }
   
   // get methods
   public double getX()
   {
      return x;
   }
   
   public double getY()
   {
      return y;
   }
   
   // set methods
   public void setX( double newX )
   {
      x = newX;
   }
   
   public void setY( double newY )
   {
      y = newY;
   }
   
   //normilizing method
   public void normalize()
   {
      double magnitude = Math.sqrt( x * x + y * y );
      y = y / magnitude;
      x = x / magnitude;
   }
   
   //finding magnitude of vec method
   public double magnitude()
   {
      return Math.sqrt( x * x + y * y );
   }
   
}