package norbertostudios.math;////

////    Created     10/30/19, 11:03 PM
////    By:         Norberto Studios
////    
public class Vector2D
{
    private double x;
    private double y;

    // Zero Vector
    public Vector2D()
    {
        x = 0;
        y = 0;
    }

    public Vector2D(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    public Vector2D add(Vector2D v)
    {
        return new Vector2D(x + v.getX(), y + v.getY());
    }
    public Vector2D sub(Vector2D v)
    {
        return new Vector2D(x - v.getX(), y - v.getY());
    }

    public Vector2D scale(double value)
    {
        return new Vector2D(x * value,y * value);
    }

    public Vector2D limit(double value)
    {
        if(getMagnitude() > value) {
            return this.normalize().scale(value);
        }
        return this;
    }

    public Vector2D normalize() // vector unitario 1
    {
        double magnitude = getMagnitude();
        return new Vector2D(x/magnitude, y/magnitude);
    }

    public double getMagnitude()
    {
        return Math.sqrt(x*x + y*y);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
}
