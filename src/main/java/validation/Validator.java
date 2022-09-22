package validation;

import config.Operators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator
{
    public static ValidationResult validateInfixExpression(final String infixExpression)
    {
        ValidationFailureReason validationFailureReason = executeValidation(infixExpression);
        ValidationResult validationResult = new ValidationResult(validationFailureReason);

        if(!validationResult.isSuccess())
        {
            System.out.println(validationFailureReason);
        }

        return validationResult;
    }

    private static ValidationFailureReason executeValidation(final String infixExpression)
    {
        final String operatorsSetRegex = "[%c%c%c%c]".formatted(Operators.subtractionOperator, Operators.additionOperator, Operators.multiplicationOperator, Operators.divisionOperator);

        final String threeOrMoreConsecutiveOperatorsRegex = "%s{3,}".formatted(operatorsSetRegex);

        if (applyRegex(infixExpression, threeOrMoreConsecutiveOperatorsRegex))
        {
            return ValidationFailureReason.AnyThreeOrMoreConsecutiveOperators;
        }

        final String consecutiveOperatorsRegex = "%s[*/]".formatted(operatorsSetRegex);

        if (applyRegex(infixExpression, consecutiveOperatorsRegex))
        {
            return ValidationFailureReason.WrongTwoConsecutiveOperators;
        }

        final String twoOrMoreDotsRegex = "\\d+(\\.\\d*){2,}";

        if (applyRegex(infixExpression, twoOrMoreDotsRegex))
        {
            return ValidationFailureReason.TwoOrMoreDotsInNumber;
        }

        final String wrongOperatorAsTheFirstCharacterRegex = "^[*/]";

        if (applyRegex(infixExpression, wrongOperatorAsTheFirstCharacterRegex))
        {
            return ValidationFailureReason.WrongOperatorAsTheFirstCharacter;
        }

        final String operatorAsTheLastCharacterRegex = "%s$".formatted(operatorsSetRegex);

        if (applyRegex(infixExpression, operatorAsTheLastCharacterRegex))
        {
            return ValidationFailureReason.OperatorIsTheLastCharacterInExpression;
        }

        final String dotAsTheFirstCharacterRegex = "^\\.";

        if (applyRegex(infixExpression, dotAsTheFirstCharacterRegex))
        {
            return ValidationFailureReason.DotIsTheFirstCharacterInExpression;
        }

        final String dotAsTheLastCharacterRegex = "\\.$";

        if (applyRegex(infixExpression, dotAsTheLastCharacterRegex))
        {
            return ValidationFailureReason.DotIsTheLastCharacterInExpression;
        }

        final String dotWithoutIntegerPartRegex = "%s\\.\\d+".formatted(operatorsSetRegex);

        if (applyRegex(infixExpression, dotWithoutIntegerPartRegex))
        {
            return ValidationFailureReason.DotWithoutIntegerPart;
        }

        final String dotWithoutFractionalPartRegex = "\\d+\\.%s".formatted(operatorsSetRegex);

        if (applyRegex(infixExpression, dotWithoutFractionalPartRegex))
        {
            return ValidationFailureReason.DotWithoutFractionalPart;
        }

        final String emptyParenthesesRegex = "\\(\\)";

        if (applyRegex(infixExpression, emptyParenthesesRegex))
        {
            return ValidationFailureReason.EmptyParentheses;
        }

        final String numberFollowedByLeftParenthesesRegex = "\\d\\(";

        if (applyRegex(infixExpression, numberFollowedByLeftParenthesesRegex))
        {
            return ValidationFailureReason.LeftParenthesesPrecededByNumber;
        }

        final String rightParenthesesFollowedByNumberRegex = "\\)\\d";

        if (applyRegex(infixExpression, rightParenthesesFollowedByNumberRegex))
        {
            return ValidationFailureReason.RightParenthesesFollowedByNumber;
        }

        if (!checkParenthesesBalance(infixExpression))
        {
            return ValidationFailureReason.UnbalancedParentheses;
        }

        return ValidationFailureReason.NoFail;
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
