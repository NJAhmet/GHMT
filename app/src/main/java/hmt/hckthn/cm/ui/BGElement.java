package hmt.hckthn.cm.ui;


import java.io.Serializable;

public class BGElement implements Serializable
{
    private String type;
    private int xPosition;
    private int yPosition;
    private int r;
    private int width;
    private int height;
    private String color;

    public String getType()
    {
        return type;
    }
    public int getXPosition()
    {
        return xPosition;
    }
    public int getYPosition()
    {
        return yPosition;
    }
    public int getRadius()
    {
        return r;
    }
    public int getWidth()
    {
        return width;
    }
    public int getHeight()
    {
        return height;
    }
    public String getColor()
    {
        return color;
    }
}
