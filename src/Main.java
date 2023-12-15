import functions.*;
import functions.basic.*;
import java.io.*;
import functions.TabulatedFunction;
import functions.TabulatedFunctions;

public class Main
{
	public static void main(String[] args) throws InappropriateFunctionPointException
	{
		Tan tan = new Tan();

		TabulatedFunction func = TabulatedFunctions.tabulate(tan, 0, 6.28, 11);

		try
		{
			FileOutputStream fos = new FileOutputStream("file.txt");
			ObjectOutputStream oos = new ObjectOutputStream(fos);

			oos.writeObject(func);
			oos.close();

			FileInputStream fis = new FileInputStream("file.txt");
			ObjectInputStream ois = new ObjectInputStream(fis);

			TabulatedFunction fromfile = (TabulatedFunction) ois.readObject();
			ois.close();

			for(double i = 0; i <= 6.28; i += 0.1)
			{
				if(func.getFunctionValue(i) != fromfile.getFunctionValue(i)) System.out.println("Не равны");
			}

		}
		catch (IOException e)
		{
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
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