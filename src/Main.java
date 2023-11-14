import functions.*;
import functions.basic.*;
import java.io.*;
import functions.TabulatedFunction;
import functions.TabulatedFunctions;

public class Main
{
	public static void main(String[] args) throws InappropriateFunctionPointException
	{
		// Создание табулированного аналога логарифма
		double leftX = 0.0;
		double rightX = 10.0;
		int pointsCount = 11;
		Log a = new Log(2.72);

		TabulatedFunction logTabulated = TabulatedFunctions.tabulate(a, leftX + 1, rightX + 1, pointsCount);

		// Запись в файл
		try (FileOutputStream fos = new FileOutputStream("logTabulated.txt"))
		{
			TabulatedFunctions.outputTabulatedFunction(logTabulated, fos);
		} catch (IOException e)
		{
			e.printStackTrace();
		}

		// Чтение из файла и сравнение значений
		try (FileInputStream fis = new FileInputStream("logTabulated.txt"))
		{
			TabulatedFunction logTabulatedFromFile = TabulatedFunctions.inputTabulatedFunction(fis);

			double step = 1.0;
			for (double x = leftX; x <= rightX; x += step)
			{
				System.out.println("Original: " + logTabulated.getFunctionValue(x) +
						", Read from file: " + logTabulatedFromFile.getFunctionValue(x));
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		}
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