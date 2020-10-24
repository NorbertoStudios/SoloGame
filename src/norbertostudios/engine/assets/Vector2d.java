package norbertostudios.engine.assets;////

////    Created     12/2/19, 12:46 PM
////    By:         Norberto Studios
////    
public class Vector2d
{
    private double x;
    private double y;

    // The Zero vector
    public Vector2d()
    {
        x = 0;
        y = 0;
    }

    public Vector2d(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    public Vector2d add(Vector2d other)
    {
        return new Vector2d(x + other.getX(), y + other.getY());
    }
    public Vector2d sub(Vector2d other)
    {
        return new Vector2d(x - other.getX(), y - other.getY());
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
