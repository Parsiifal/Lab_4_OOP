package functions;

public class TabulatedFunctions
{
	private TabulatedFunctions() {} // Предотвращаем создание объектов этого класса извне

	public static TabulatedFunction tabulate(Function function, double leftX, double rightX, int pointsCount)
	{
		if (leftX >= rightX || pointsCount < 2)
		{
			throw new IllegalArgumentException("Некорректные параметры");
		}

		double step = (rightX - leftX) / (pointsCount - 1);

		for (int i = 1; i < pointsCount; i++)
		{
			double x = leftX + i * step;
			if (x < function.getLeftDomainBorder() || x > function.getRightDomainBorder())
			{
				throw new IllegalArgumentException("Диапазон находится за пределами области");
			}
		}

		if (function.getLeftDomainBorder() < leftX || function.getRightDomainBorder() > rightX)
		{
			throw new IllegalArgumentException("Область определения функции находится за границами для табулирования");
		}

		return new ArrayTabulatedFunction(leftX, rightX, pointsCount, function);
	}
}