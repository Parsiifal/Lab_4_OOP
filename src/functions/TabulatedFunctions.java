package functions;
import java.io.*;

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

//		if (function.getLeftDomainBorder() < leftX || function.getRightDomainBorder() > rightX)
//		{
//			throw new IllegalArgumentException("Область определения функции находится за границами для табулирования");
//		}

		return new ArrayTabulatedFunction(leftX, rightX, pointsCount, function);
	}

	public static void outputTabulatedFunction(TabulatedFunction function, OutputStream out) throws IOException
	{
		DataOutputStream datOutStr = new DataOutputStream(out);
		try
		{
			datOutStr.writeInt(function.getPointsCount());
			for (int i = 0; i < function.getPointsCount(); i++)
			{
				datOutStr.writeDouble(function.getPointX(i));
				datOutStr.writeDouble(function.getPointY(i));
			}
		}
		finally
		{
			datOutStr.close();
		}
	}

	public static TabulatedFunction inputTabulatedFunction(InputStream in) throws IOException
	{
		DataInputStream datInStr = new DataInputStream(in);
		try
		{
			int pointsCount = datInStr.readInt();
			double[] xValues = new double[pointsCount];
			double[] yValues = new double[pointsCount];

			for (int i = 0; i < pointsCount; i++)
			{
				xValues[i] = datInStr.readDouble();
				yValues[i] = datInStr.readDouble();
			}
			return new ArrayTabulatedFunction(xValues, yValues);
		}
		finally
		{
			datInStr.close();
		}
	}

	public static void writeTabulatedFunction(TabulatedFunction function, Writer out) throws IOException
	{
		BufferedWriter buffWriter = new BufferedWriter(out);
		try
		{
			buffWriter.write(Integer.toString(function.getPointsCount()));
			buffWriter.write("\n");
			for (int i = 0; i < function.getPointsCount(); i++)
			{
				buffWriter.write(Double.toString(function.getPointX(i)));
				buffWriter.write(" ");
				buffWriter.write(Double.toString(function.getPointY(i)));
				buffWriter.write("\n");
			}
		}
		finally
		{
			buffWriter.close();
		}
	}

	public static TabulatedFunction readTabulatedFunction(Reader in) throws IOException
	{
		StreamTokenizer token = new StreamTokenizer(in);
		token.parseNumbers();
		token.nextToken();
		int pointsCount = (int) token.nval;

		double[] xValues = new double[pointsCount];
		double[] yValues = new double[pointsCount];

		for (int i = 0; i < pointsCount; i++)
		{
			token.nextToken();
			xValues[i] = token.nval;
			token.nextToken();
			yValues[i] = token.nval;
		}
		return new ArrayTabulatedFunction(xValues, yValues);
	}

}