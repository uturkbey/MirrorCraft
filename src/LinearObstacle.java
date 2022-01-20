/**
 * Created by BUGRA on 12.5.2017.
 */
import java.awt.*;
public class LinearObstacle extends PlaneMirror{

    private Vec2 start; // start point of mirror
    private Vec2 end; // end point of mirror
    private double slope; // slope of the mirror

    public LinearObstacle(int firstX, int firstY, int secondX, int secondY)
    {
        super(firstX,firstY,secondX,secondY);
        start = new Vec2( firstX, firstY );
        end = new Vec2( secondX, secondY );
        slope = ( end.getY() - start.getY() ) / ( end.getX() - start.getX() );
    }

    @Override
    public void reflect( LightRay light )
    {
        if ( this.isIntersecting( light ) )
        {
            light.setEndX(this.getIntersectionPointX(light));
            light.setEndY(this.getIntersectionPointY(light));

        }
    }

    public void draw ( Graphics g )
    {
        g.setColor( new Color(151, 22, 200));
        g.drawLine( (int)start.getX(), (int)start.getY(), (int)end.getX(), (int)end.getY() );
       // g.setColor( new  );
       // g.drawLine( (int)( (start.getX() + end.getX() ) / 2), (int)((start.getY() + end.getY()) / 2), (int)(( (start.getX() + end.getX() ) / 2) + (start.getY() - end.getY()) ), (int)((start.getY() + end.getY()) /2 + end.getX() - start.getX())  );
    }
    public LightRay  positionChanger(LightRay light )
    {
        if ( this.isIntersecting( light ) )
        {
            light.setEndX(this.getIntersectionPointX(light));
            light.setEndY(this.getIntersectionPointY(light));
            return light;
        }
        return null;

    }





}
