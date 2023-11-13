import functions.*;

public class Main
{
	public static void main(String[] args) throws InappropriateFunctionPointException
	{
		FunctionPoint[] values = {
				new FunctionPoint(1, 2),
				new FunctionPoint(2, 4),
				new FunctionPoint(3, 67)
		};
		TabulatedFunction a = new LinkedListTabulatedFunction(values);

		printFunctionValues(a);


	}

	public static void printFunctionValues(TabulatedFunction function) // функция для вывода значений функции
	{
		System.out.println("Значения функции:");

		for (int i = 0; i < function.getPointsCount(); i++)
		{
			double x = function.getPointX(i);
			double y = function.getPointY(i);
			System.out.println("f(" + x + ") = " + y);
		}
		System.out.println();
	}
}
