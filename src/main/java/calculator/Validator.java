package calculator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator
{
    static boolean validateInfixExpression(final String infixExpression)
    {
        final String operatorsSetRegex = "[%c%c%c%c]".formatted(Operators.subtractionOperator, Operators.additionOperator, Operators.multiplicationOperator, Operators.divisionOperator);

        final String threeOrMoreConsecutiveOperatorsRegex = "%s{3,}".formatted(operatorsSetRegex);

        if (applyRegex(infixExpression, threeOrMoreConsecutiveOperatorsRegex))
        {
            System.out.println("Any 3 or more consecutive operators are not allowed");
            return false;
        }

        final String consecutiveOperatorsRegex = "%s[*/]".formatted(operatorsSetRegex);

        if (applyRegex(infixExpression, consecutiveOperatorsRegex))
        {
            System.out.println("Wrong 2 consecutive operators sequence");
            return false;
        }

        final String twoOrMoreDotsRegex = "\\d+(\\.\\d*){2,}";

        if (applyRegex(infixExpression, twoOrMoreDotsRegex))
        {
            System.out.println("Two or more dots in number are not allowed");
            return false;
        }

        final String wrongOperatorAsTheFirstCharacter = "^[*/]";

        if (applyRegex(infixExpression, wrongOperatorAsTheFirstCharacter))
        {
            System.out.println("Wrong operator as the first character of the equation");
            return false;
        }

        final String operatorAsTheLastCharacter = "%s$".formatted(operatorsSetRegex);

        if (applyRegex(infixExpression, operatorAsTheLastCharacter))
        {
            System.out.println("Operator cannot be the last character in an equation");
            return false;
        }

        final String dotAsTheFirstCharacter = "^\\.";

        if (applyRegex(infixExpression, dotAsTheFirstCharacter))
        {
            System.out.println("Dot cannot be the first character in an equation");
            return false;
        }

        final String dotAsTheLastCharacter = "\\.$";

        if (applyRegex(infixExpression, dotAsTheLastCharacter))
        {
            System.out.println("Dot cannot be the last character in an equation");
            return false;
        }

        final String dotWithoutIntegerPart = "%s\\.\\d+".formatted(operatorsSetRegex);

        if (applyRegex(infixExpression, dotWithoutIntegerPart))
        {
            System.out.println("Dot cannot be used without integer part");
            return false;
        }

        final String dotWithoutFractionalPart = "\\d+\\.%s".formatted(operatorsSetRegex);

        if (applyRegex(infixExpression, dotWithoutFractionalPart))
        {
            System.out.println("Dot cannot be used without fractional part");
            return false;
        }

        return true;
    }

    static boolean applyRegex(final String expression, final String regex)
    {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(expression);

        return matcher.find();
    }
}
