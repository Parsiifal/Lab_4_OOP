package functions;

public class InappropriateFunctionPointException extends Exception
{
    public InappropriateFunctionPointException()
    {
        super("Попытка добавления или изменения точки функции несоответствующим образом.");
    }
}