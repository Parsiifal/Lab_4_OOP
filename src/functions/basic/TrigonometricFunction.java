package functions.basic;

import functions.Function;

public class TrigonometricFunction implements Function
{
	@Override
	public double getLeftDomainBorder()
	{
		return Double.NEGATIVE_INFINITY;
	}

	@Override
	public double getRightDomainBorder()
	{
		return Double.POSITIVE_INFINITY;
	}

	@Override
	public double getFunctionValue(double x)
	{
		// The base class implementation does not provide a meaningful value for trigonometric functions
		throw new UnsupportedOperationException("Класс тригонометрических функций не считает значения функций");
	}
}