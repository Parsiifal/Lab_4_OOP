package functions;

public class ArrayTabulatedFunction implements TabulatedFunction
{
	private FunctionPoint[] points;
	private int numberofpoints = 0;

	public ArrayTabulatedFunction(double[] xValues, double[] yValues)
	{
		points = new FunctionPoint[xValues.length];
		numberofpoints = xValues.length;

		for (int i = 0; i < xValues.length; i++)
		{
			points[i] = new FunctionPoint(xValues[i], yValues[i]);
		}
	}

	public ArrayTabulatedFunction(double leftX, double rightX, int pointsCount, Function function)
	{
		if (leftX >= rightX || pointsCount < 2)
		{
			throw new IllegalArgumentException("Некорректные параметры конструктора!");
		}

		double step = (rightX - leftX) / (pointsCount - 1);
		double[] values = new double[pointsCount];

		for (int i = 0; i < pointsCount; i++)
		{
			double x = leftX + i * step;
			values[i] = function.getFunctionValue(x);
		}

		points = new FunctionPoint[pointsCount];

		for (int i = 0; i < pointsCount; i++)
		{
			double x = leftX + i * step;
			points[i] = new FunctionPoint(x, values[i]);
		}

		numberofpoints = pointsCount;
	}

	public ArrayTabulatedFunction(FunctionPoint[] values)
	{
		if (values == null || values.length < 2)
		{
			throw new IllegalArgumentException("Некорректные параметры конструктора!");
		}

		for (int i = 1; i < values.length; i++)
		{
			if (values[i].GetX() <= values[i - 1].GetX())
			{
				throw new IllegalArgumentException("Точки должны быть упорядочены по значению абсциссы!");
			}
		}

		points = new FunctionPoint[values.length];

		for (int i = 0; i < values.length; i++)
		{
			points[i] = new FunctionPoint(values[i]);
		}

		numberofpoints = values.length;
	}


	public ArrayTabulatedFunction(double leftX, double rightX, int pointsCount)
	{
		if (leftX >= rightX || pointsCount < 2)
		{
			throw new IllegalArgumentException("Некорректные параметры конструктора!");
		}

		double step = (rightX - leftX) / (pointsCount - 1);
		points = new FunctionPoint[pointsCount];

		for (int i = 0; i < pointsCount; i++)
		{
			double x = leftX + i * step;
			points[i] = new FunctionPoint(x, 0);
		}

		numberofpoints = pointsCount;
	}

	public ArrayTabulatedFunction(double leftX, double rightX, double[] values)
	{
		if (leftX >= rightX || values.length < 2)
		{
			throw new IllegalArgumentException("Некорректные параметры конструктора!");
		}

		double step = (rightX - leftX) / (values.length - 1);
		points = new FunctionPoint[values.length];

		for (int i = 0; i < values.length; i++)
		{
			double x = leftX + i * step;
			points[i] = new FunctionPoint(x, values[i]);
		}

		numberofpoints = values.length;
	}

	public double getLeftDomainBorder()
	{
		return points[0].GetX();
	}

	public double getRightDomainBorder()
	{
		return points[points.length - 1].GetX();
	}

	public double getFunctionValue(double x)
	{
		if (x < getLeftDomainBorder() || x > getRightDomainBorder())
		{
			throw new FunctionPointIndexOutOfBoundsException();
		}

		for (int i = 1; i < points.length; i++)
		{
			if (x <= points[i].GetX())
			{
				double x1 = points[i - 1].GetX();
				double y1 = points[i - 1].GetY();
				double x2 = points[i].GetX();
				double y2 = points[i].GetY();
				return y1 + (x - x1) * (y2 - y1) / (x2 - x1);
			}
		}

		return Double.NaN;
	}

	public int getPointsCount()
	{
		return numberofpoints;
	}

	public FunctionPoint getPoint(int index)
	{
		if (index < 0 || index >= points.length)
		{
			throw new FunctionPointIndexOutOfBoundsException();
		}
		return points[index];
	}

	public void setPoint(int index, FunctionPoint point) throws InappropriateFunctionPointException
	{
		if (index < 0 || index >= points.length)
		{
			throw new FunctionPointIndexOutOfBoundsException();
		}
		if (index > 0 && point.GetX() <= points[index - 1].GetX())
		{
			throw new InappropriateFunctionPointException();
		}
		if (index < points.length - 1 && point.GetX() >= points[index + 1].GetX())
		{
			throw new InappropriateFunctionPointException();
		}

		points[index] = point;
	}

	public double getPointX(int index)
	{
		if (index < 0 || index >= points.length)
		{
			throw new FunctionPointIndexOutOfBoundsException();
		}

		return points[index].GetX();
	}

	public void setPointX(int index, double x) throws InappropriateFunctionPointException
	{
		if (index < 0 || index >= points.length)
		{
			throw new FunctionPointIndexOutOfBoundsException();
		}
		if (index > 0 && x <= points[index - 1].GetX())
		{
			throw new InappropriateFunctionPointException();
		}
		if (index < points.length - 1 && x >= points[index + 1].GetX())
		{
			throw new InappropriateFunctionPointException();
		}

		points[index].SetX(x);
	}

	public double getPointY(int index)
	{
		if (index < 0 || index >= points.length)
		{
			throw new FunctionPointIndexOutOfBoundsException();
		}

		return points[index].GetY();
	}

	public void setPointY(int index, double y)
	{
		if (index < 0 || index >= points.length) {
			throw new FunctionPointIndexOutOfBoundsException();
		}

		points[index].SetY(y);
	}

	public void deletePoint(int index)
	{
		if (index < 0 || index >= points.length)
		{
			throw new FunctionPointIndexOutOfBoundsException();
		}
		if (points.length < 3)
		{
			throw new IllegalStateException("Невозможно удалить точку, так как количество точек менее трех!");
		}
		for (int i = index; i < numberofpoints - 1; i++)
		{
			points[i] = points[i + 1];
		}

		numberofpoints--;

	}

	public void addPoint(FunctionPoint point) throws InappropriateFunctionPointException
	{
		int indexToInsert = 0;
		for (int i = 0; i < numberofpoints; i++)
		{
			if (point.GetX() < points[i].GetX())
			{
				indexToInsert = i;
				break;
			}
		}

		if (indexToInsert > 0 && points[indexToInsert - 1].GetX() == point.GetX())
		{
			throw new InappropriateFunctionPointException();
		}

		if (numberofpoints == points.length)
		{
			FunctionPoint[] newPoints = new FunctionPoint[points.length + 5];
			System.arraycopy(points, 0, newPoints, 0, indexToInsert);
			newPoints[indexToInsert] = point;
			System.arraycopy(points, indexToInsert, newPoints, indexToInsert + 1, points.length - indexToInsert); // копируем остальные точки
			points = newPoints;
		}
		else
		{
			for (int i = numberofpoints - 1; i >= indexToInsert; i--)
			{
				points[i + 1] = points[i];
			}
			points[indexToInsert] = point;
		}

		numberofpoints++;

	}

}