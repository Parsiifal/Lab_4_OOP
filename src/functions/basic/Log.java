package functions.basic;

import functions.Function;

public class Log implements Function
{
	private final double base;

	public Log(double footing)
	{
		if (footing <= 0 || footing == 1)
		{
			throw new IllegalArgumentException("Основание должно быть больше 0 и не равно 1!");
		}
		base = footing;
	}

	@Override
	public double getLeftDomainBorder()
	{
		return 0; // неопределен для x <= 0
	}

	@Override
	public double getRightDomainBorder()
	{
		return Double.POSITIVE_INFINITY;
	}

	@Override
	public double getFunctionValue(double x)
	{
		return Math.log(x) / Math.log(base);
	}
}