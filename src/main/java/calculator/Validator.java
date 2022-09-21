package calculator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator
{
    static boolean validateInfixExpression(final String infixExpression)
    {
        String failReason = validateInfix(infixExpression);

        if (failReason == null)
        {
            return true;
        }
        else
        {
            System.out.println(failReason);

            if(StatusBarManager.isActive())
            {
                printReason(failReason);
            }

            return false;
        }
    }

    private static String validateInfix(final String infixExpression)
    {
        final String operatorsSetRegex = "[%c%c%c%c]".formatted(Operators.subtractionOperator, Operators.additionOperator, Operators.multiplicationOperator, Operators.divisionOperator);

        final String threeOrMoreConsecutiveOperatorsRegex = "%s{3,}".formatted(operatorsSetRegex);

        if (applyRegex(infixExpression, threeOrMoreConsecutiveOperatorsRegex))
        {
            return "Any 3 or more consecutive operators are not allowed";
        }

        final String consecutiveOperatorsRegex = "%s[*/]".formatted(operatorsSetRegex);

        if (applyRegex(infixExpression, consecutiveOperatorsRegex))
        {
            return "Wrong 2 consecutive operators sequence";
        }

        final String twoOrMoreDotsRegex = "\\d+(\\.\\d*){2,}";

        if (applyRegex(infixExpression, twoOrMoreDotsRegex))
        {
            return "Two or more dots in number are not allowed";
        }

        final String wrongOperatorAsTheFirstCharacter = "^[*/]";

        if (applyRegex(infixExpression, wrongOperatorAsTheFirstCharacter))
        {
            return "Wrong operator as the first character of the equation";
        }

        final String operatorAsTheLastCharacter = "%s$".formatted(operatorsSetRegex);

        if (applyRegex(infixExpression, operatorAsTheLastCharacter))
        {
            return "Operator cannot be the last character in an equation";
        }

        final String dotAsTheFirstCharacter = "^\\.";

        if (applyRegex(infixExpression, dotAsTheFirstCharacter))
        {
            return "Dot cannot be the first character in an equation";
        }

        final String dotAsTheLastCharacter = "\\.$";

        if (applyRegex(infixExpression, dotAsTheLastCharacter))
        {
            return "Dot cannot be the last character in an equation";
        }

        final String dotWithoutIntegerPart = "%s\\.\\d+".formatted(operatorsSetRegex);

        if (applyRegex(infixExpression, dotWithoutIntegerPart))
        {
            return "Dot cannot be used without integer part";
        }

        final String dotWithoutFractionalPart = "\\d+\\.%s".formatted(operatorsSetRegex);

        if (applyRegex(infixExpression, dotWithoutFractionalPart))
        {
            return "Dot cannot be used without fractional part";
        }

        final String emptyParentheses = "\\(\\)";

        if (applyRegex(infixExpression, emptyParentheses))
        {
            return "Empty parentheses not allowed";
        }

        final String numberFollowedByLeftParentheses = "\\d\\(";

        if (applyRegex(infixExpression, numberFollowedByLeftParentheses))
        {
            return "Number cannot be immediately followed by parentheses";
        }

        final String rightParenthesesFollowedByNumber = "\\)\\d";

        if (applyRegex(infixExpression, rightParenthesesFollowedByNumber))
        {
            return "Right parentheses cannot be immediately followed by a number";
        }

        if (!checkParenthesesBalance(infixExpression))
        {
            return "Left and right parentheses' count has to be equal";
        }

        return null;
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

    private static void printReason(String reason)
    {
        StatusBarManager.set(reason);
    }
}
