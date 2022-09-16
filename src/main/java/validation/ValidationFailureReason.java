package validation;

public enum ValidationFailureReason
{
    AnyThreeOrMoreConsecutiveOperators("Any 3 or more consecutive operators are not allowed"),
    WrongTwoConsecutiveOperators("Wrong 2 consecutive operators sequence"),
    TwoOrMoreDotsInNumber("Two or more dots in number are not allowed"),
    WrongOperatorAsTheFirstCharacter("Wrong operator as the first character of the equation"),
    OperatorIsTheLastCharacterInExpression("Operator cannot be the last character in an equation"),
    DotIsTheFirstCharacterInExpression("Dot cannot be the first character in an equation"),
    DotIsTheLastCharacterInExpression("Dot cannot be the last character in an equation"),
    DotWithoutIntegerPart("Dot cannot be used without integer part"),
    DotWithoutFractionalPart("Dot cannot be used without fractional part"),
    EmptyParentheses("Empty parentheses not allowed"),
    LeftParenthesesPrecededByNumber("Number cannot be immediately followed by parentheses"),
    RightParenthesesFollowedByNumber("Right parentheses cannot be immediately followed by a number"),
    UnbalancedParentheses("Left and right parentheses' count has to be equal"),
    NoFail("");

    ValidationFailureReason(String reasonString)
    {
        this.reasonString = reasonString;
    }

    @Override
    public String toString()
    {
        return reasonString;
    }

    private final String reasonString;
}
