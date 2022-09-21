package calculator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

enum FailReason
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

    FailReason(String reasonString)
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

class ValidationResult
{
    ValidationResult(FailReason failReason)
    {
        this.failReason = failReason;
    }

    public boolean isSuccess()
    {
        return failReason == FailReason.NoFail;
    }


    public FailReason getFailReason()
    {
        return failReason;
    }

    private final FailReason failReason;
}

public class Validator
{
    static ValidationResult validateInfixExpression(final String infixExpression)
    {
        FailReason failReason = executeValidation(infixExpression);
        ValidationResult validationResult = new ValidationResult(failReason);

        if(!validationResult.isSuccess())
        {
            System.out.println(failReason);
        }

        return validationResult;
    }

    private static FailReason executeValidation(final String infixExpression)
    {
        final String operatorsSetRegex = "[%c%c%c%c]".formatted(Operators.subtractionOperator, Operators.additionOperator, Operators.multiplicationOperator, Operators.divisionOperator);

        final String threeOrMoreConsecutiveOperatorsRegex = "%s{3,}".formatted(operatorsSetRegex);

        if (applyRegex(infixExpression, threeOrMoreConsecutiveOperatorsRegex))
        {
            return FailReason.AnyThreeOrMoreConsecutiveOperators;
        }

        final String consecutiveOperatorsRegex = "%s[*/]".formatted(operatorsSetRegex);

        if (applyRegex(infixExpression, consecutiveOperatorsRegex))
        {
            return FailReason.WrongTwoConsecutiveOperators;
        }

        final String twoOrMoreDotsRegex = "\\d+(\\.\\d*){2,}";

        if (applyRegex(infixExpression, twoOrMoreDotsRegex))
        {
            return FailReason.TwoOrMoreDotsInNumber;
        }

        final String wrongOperatorAsTheFirstCharacterRegex = "^[*/]";

        if (applyRegex(infixExpression, wrongOperatorAsTheFirstCharacterRegex))
        {
            return FailReason.WrongOperatorAsTheFirstCharacter;
        }

        final String operatorAsTheLastCharacterRegex = "%s$".formatted(operatorsSetRegex);

        if (applyRegex(infixExpression, operatorAsTheLastCharacterRegex))
        {
            return FailReason.OperatorIsTheLastCharacterInExpression;
        }

        final String dotAsTheFirstCharacterRegex = "^\\.";

        if (applyRegex(infixExpression, dotAsTheFirstCharacterRegex))
        {
            return FailReason.DotIsTheFirstCharacterInExpression;
        }

        final String dotAsTheLastCharacterRegex = "\\.$";

        if (applyRegex(infixExpression, dotAsTheLastCharacterRegex))
        {
            return FailReason.DotIsTheLastCharacterInExpression;
        }

        final String dotWithoutIntegerPartRegex = "%s\\.\\d+".formatted(operatorsSetRegex);

        if (applyRegex(infixExpression, dotWithoutIntegerPartRegex))
        {
            return FailReason.DotWithoutIntegerPart;
        }

        final String dotWithoutFractionalPartRegex = "\\d+\\.%s".formatted(operatorsSetRegex);

        if (applyRegex(infixExpression, dotWithoutFractionalPartRegex))
        {
            return FailReason.DotWithoutFractionalPart;
        }

        final String emptyParenthesesRegex = "\\(\\)";

        if (applyRegex(infixExpression, emptyParenthesesRegex))
        {
            return FailReason.EmptyParentheses;
        }

        final String numberFollowedByLeftParenthesesRegex = "\\d\\(";

        if (applyRegex(infixExpression, numberFollowedByLeftParenthesesRegex))
        {
            return FailReason.LeftParenthesesPrecededByNumber;
        }

        final String rightParenthesesFollowedByNumberRegex = "\\)\\d";

        if (applyRegex(infixExpression, rightParenthesesFollowedByNumberRegex))
        {
            return FailReason.RightParenthesesFollowedByNumber;
        }

        if (!checkParenthesesBalance(infixExpression))
        {
            return FailReason.UnbalancedParentheses;
        }

        return FailReason.NoFail;
    }

    private static boolean checkParenthesesBalance(final String infixExpression)
    {
        long leftParenthesesCount = infixExpression.chars().filter(character -> character == '(').count();
        long rightParenthesesCount = infixExpression.codePoints().filter(character -> character == ')').count();

        return leftParenthesesCount == rightParenthesesCount;
    }

    private static boolean applyRegex(final String expression, final String regex)
    {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(expression);

        return matcher.find();
    }
}
