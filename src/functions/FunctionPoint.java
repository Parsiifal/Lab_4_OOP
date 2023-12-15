package functions;

import java.io.Serializable;

public class FunctionPoint implements Serializable
{
    private double x;
    private double y;

    public FunctionPoint()
    {
        x = 0;
        y = 0;
    }

    public FunctionPoint(double X, double Y)
    {
        x = X;
        y = Y;
    }

    public FunctionPoint(FunctionPoint point)
    {
        x = point.x;
        y = point.y;
    }

    public double GetX()
    {
        return x;
    }

    public double GetY()
    {
        return y;
    }

    public void SetX(double X)
    {
        x = X;
    }

    public void SetY(double Y)
    {
        y = Y;
    }
}