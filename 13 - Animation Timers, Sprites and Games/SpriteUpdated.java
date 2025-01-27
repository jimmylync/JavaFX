import javafx.scene.image.*;
import javafx.scene.canvas.*;
import javafx.geometry.*;

/*
 * Store data and methods
 * for movable images on screen in a game.
 */
public class SpriteUpdated
{
    public double x;
    public double y;
    public double width;
    public double height;
    public Image  pic;
    
    
    // Automatic movement values in each direction
    // Not getting initialized in the constructor. Values actually getting
     // set inside the game class
    public double distanceX, distanceY;
    
    
    
    // Constructor
    // Interesting notation
    // Don't have to like come up with alternate names for things just to
     // pass into a function
    // I guess you could always do x and y as parameters and then do like
     // this.x = x
    public SpriteUpdated(double _x, double _y, double _width, double _height, String fileName)
    {
        x = _x;
        y = _y;
        width = _width;
        height = _height;
        pic = new Image(fileName, width, height, false, true);
    }
    
    // move the sprite by changing the x and y coordinates
    public void move(double dx, double dy)
    {
        x = x + dx;
        y = y + dy;
    }
    
    // draw the image on a canvas using a graphics context
    public void draw(GraphicsContext context)
    {
        // I think this is always going to be a rectangle but idk
            // what about a png
        // Actually, I think our yarn image is not a rectangle
        // I guess still a rectangle, just with some of it being transparent?
        
        // Drawing using the x and y (position) instance variables
        context.drawImage( pic, x, y );
    }
    
    // check if this sprite overlaps with another sprite (boundary rectangles)
    // What we're really doing is checking if two rectangles with the same
     // width and height of the corresponding sprites intersect when at the
      // same location as those sprites
    public boolean overlaps(SpriteUpdated other)
    {
        // Using a new thing, Rectangle2D
            // Can look up in documentation
        // Has methods like intersects(), contains(), etc.
        // represents the boundary of this sprite
        Rectangle2D rect1 = new Rectangle2D(x,y, width,height);
        
        // boundary of other sprite
        Rectangle2D rect2 = new Rectangle2D(other.x, other.y, other.width, other.height);
        
        // check if they overlap
        boolean overlap = rect1.intersects( rect2 );
        
        // return the result
        return overlap;
    }
    
    
    
    // If the object passes beyond boundary of the canvas (600, 600), then
     // set coordinates so it appears on the other side
    public void wrap()
    {
        // Right side of the canvas
        if (x > 600)
        {
            x = -width;
        }
        
        // Left side of the canvas
        if (x < -width)
        {
            x = 600;
        }
        
        // Bottom edge of the canvas
        if (y > 600)
        {
            y = -height;
        }
        
        // Top edge of canvas
        if (y < -height)
        {
            y = 600;
        }
        
        
    }
    
    
    
    
    
    
    
}