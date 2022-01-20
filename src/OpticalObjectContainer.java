import java.util.*;
/**
 * This class simulates an objects container
 * @authot Utku Türkbey
 * @version 11.05.2017
 */

public class OpticalObjectContainer
{
   // property
   ArrayList<OpticalObject> opticalObjects;
   
   // constructor
   public OpticalObjectContainer()
   {
      opticalObjects = new  ArrayList<OpticalObject>();
   }
   
   // get item method
   public OpticalObject get( int i )
   {
      return opticalObjects.get( i );
   }
   
   // get size method
   public int size()
   {
      return opticalObjects.size();
   }
   
   // add method
   public void add( OpticalObject object )
   {
      opticalObjects.add( object );
   }
   
   // remove method
   public void remove ( OpticalObject object )
   {
      opticalObjects.remove(object);
   }
   
   // clear method
   public void clear()
   {
      opticalObjects.clear();
   }
   
   // helper method to find the closer object to the light given as parameter
   public OpticalObject getCloser( LightRay light )
   {
      OpticalObject closer;
      
      if ( opticalObjects.size() == 0 )
      {
         return null;
      }
      
      closer = opticalObjects.get(0);
      
      for ( int i = 0; i < opticalObjects.size(); i++ )
      {
         if( opticalObjects.get(i).getDistance(light) != 10000 && opticalObjects.get(i).getDistance(light) < closer.getDistance(light) )
         {
            closer = opticalObjects.get(i);
         }
      }
      
      if ( closer.getDistance(light) == 10000 )
      {
         return null;
      }
      
      return closer;
      
   }


}