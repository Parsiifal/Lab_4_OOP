package functions;

public class FunctionPointIndexOutOfBoundsException extends IndexOutOfBoundsException
{
    public FunctionPointIndexOutOfBoundsException()
    {
        super("Индекс точки функции находится за пределами допустимого диапазона.");
    }
}