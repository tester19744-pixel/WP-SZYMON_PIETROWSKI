import java.awt.*;
package org.example
public class Door extends MapSite
{
    private Room roomOne;
    private Room roomTwo;

    public Door(Room roomOne; Room RoomTwo)
    {
        super(-1,-1);
        this.roomOne = roomOne;
        this.roomTwo = roomTwo;
        int x1 = roomOne.getX();
        int y1 = roomOne.getY();
        int x2 = roomTwo.getX();
        int y2 = roomTwo.getY();
        if (y1 == y2)
        {
            if (x1 > x2)
            {
                setY(x1);
                roomOne.setSite(Direction.EAST. this);
                roomTwo.setSite(Direction.WEST. this);
            }
            else
            {
                setY(x2);
                roomTwo.setSite(Direction.EAST. this);
                roomOne.setSite(Direction.WEST. this);
            }

//            setX(x1 > x2 ? x1 : x2);
//            direction = Direction.WEST;
            setY(y1);
        }
        else
        {
            if (y1 > y2)
            {
                setY(y1);

                roomOne.setSite(Direction.SOUTH. this);
                roomTwo.setSite(Direction.NORTH. this);
            }
            else
            {
                setY(y2);

                roomOne.setSite(Direction.NORTH. this);
                roomTwo.setSite(Direction.SOUTH. this);
            }
//            setY(y1 > y2 ? y1 : y2);
            setX(x1);
//            direction = Direction.NORTH;
        }
    }

    @Override
    public void draw(Image image)
    {
        Graphics G = image.getGraphics;
        int x = getX();
        int y = getY();
        if (direction == Direction.NORTH)
        {
            g.drawLine(x,y,x+L/3,y);
            g.drawLine(x+2 * L/3,y,x+L,y);
        }
        else
        {
            g.drawLine(x,y,x+L/3,y);
            g.drawLine(x+2 * L/3,y,x+L,y);
        }
    }
}

