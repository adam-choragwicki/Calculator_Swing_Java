package evaluation;

public class EvaluationResult
{
    EvaluationResult(String resultString, boolean success)
    {
        this.resultString = resultString;
        this.success = success;
    }

    public boolean isSuccess()
    {
        return success;
    }

    @Override
    public String toString()
    {
        return resultString;
    }

    private final boolean success;
    private final String resultString;
}
