package functions.meta;

import functions.Function;

public class Scale implements Function
{
	private Function base;
	private double scaleX, scaleY;

	public Scale(Function base, double scaleX, double scaleY)
	{
		this.base = base;
		this.scaleX = scaleX;
		this.scaleY = scaleY;
	}

	@Override
	public double getLeftDomainBorder()
	{
		return base.getLeftDomainBorder() * scaleX;
	}

	@Override
	public double getRightDomainBorder()
	{
		return base.getRightDomainBorder() * scaleX;
	}

	@Override
	public double getFunctionValue(double x)
	{
		return base.getFunctionValue(x) * scaleY;
	}
}