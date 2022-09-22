package validation;

public record ValidationResult(ValidationFailureReason validationFailureReason)
{
    public boolean isSuccess()
    {
        return validationFailureReason == ValidationFailureReason.NoFail;
    }
}
