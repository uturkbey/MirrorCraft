import java.awt.*;
/**
 * This class represents light ray object which has a start position
 * and direction
 * @author Utku Türkbey
 * @version 8.05.2017
 */


public class  LightRay 
{
   
   private Vec2 origin; // start point vector
   private Vec2 direction; // unit direction vector
   private Vec2 end; // dynamic end point vector
   
   public LightRay(double startX, double startY, double endX, double endY) 
   {
      origin = new Vec2(startX, startY); //initializes origin vector according to start points
      end = new Vec2(endX, endY); //initializes end vector according to end points
      
      double magnitude; // a double type variable to keep the initial magnitude of ray to calculate direction vec2
      // calculates the magnitude
      magnitude = Math.sqrt(Math.pow(startX - endX, 2) + Math.pow(startY - endY, 2));
      // initializes unit direction vec using magnitude
      direction = new Vec2((endX - startX) / magnitude, (endY - startY) / magnitude);
      
   }
   
   /**
    *
    *
    */
   public Vec2 getOriginVec() 
   {
      return origin;
   }
   
   /**
    *
    */
   public Vec2 getDirectionVec()
   {
      return direction;
   }
   
   public  Vec2 getEndVec() 
   {
      return end; 
   }
   
   /**
    *
    */
   public void setEndX(double x) 
   {
      end.setX(x);
   }
   
   public void setEndY(double y) {
      end.setY(y);
   }
   
   public void limitRay(int width, int length) 
   {
      double tOne;
      double interOneX;
      double interOneY;
      double tTwo;
      double interTwoX;
      double interTwoY;
      double tThree;
      double interThreeX;
      double interThreeY;
      double tFour;
      double interFourX;
      double interFourY;
      
      tOne = (-1 * origin.getX()) / direction.getX();
      interOneX = 0;
      interOneY = origin.getY() + direction.getY() * tOne;
      if (tOne > 0 && interOneY <= (double) length && interOneY >= 0) 
      {
         this.setEndX(interOneX);
         this.setEndY(interOneY);
      }
      
      tTwo = ((double) length - origin.getY()) / direction.getY();
      interTwoX = origin.getX() + direction.getX() * tTwo;
      interTwoY = (double) length;
      if (tTwo > 0 && interTwoX > 0 && interTwoX <= (double) width) 
      {
         this.setEndX(interTwoX);
         this.setEndY(interTwoY);
      }
      
      tThree = ((double) width - origin.getX()) / direction.getX();
      interThreeX = (double) width;
      interThreeY = origin.getY() + direction.getY() * tThree;
      if (tThree > 0 && interThreeY <= (double) length && interThreeY >= 0)
      {
         this.setEndX(interThreeX);
         this.setEndY(interThreeY);
      }
      
      tFour = (-1 * origin.getY()) / direction.getY();
      interFourX = origin.getX() + direction.getX() * tFour;
      interFourY = 0;
      if (tFour > 0 && interFourX > 0 && interFourX < (double) width)
      {
         this.setEndX(interFourX);
         this.setEndY(interFourY);
      }
      
   }
   
   /**
    * This method draws the light ray
    */
   public void draw(Graphics g) 
   {
      g.setColor(new Color(219, 213, 122));
      g.drawLine((int) origin.getX(), (int) origin.getY(), (int) end.getX(), (int) end.getY());
   }
   
}

