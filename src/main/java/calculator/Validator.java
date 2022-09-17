package calculator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator
{
    static boolean validateInfixExpression(final String infixExpression)
    {
        final String operatorsSetRegex = "[%c%c%c%c]".formatted(Config.subtractionOperator, Config.additionOperator, Config.multiplicationOperator, Config.divisionOperator);

        final String singleNumberRegex = "^\\d+(\\.\\d+)?$";

        Pattern pattern1 = Pattern.compile(singleNumberRegex);
        Matcher matcher1 = pattern1.matcher(infixExpression);

        if (matcher1.find())
        {
            return true;
        }

        final String multipleExpressionsRegex = "^\\d+(\\.\\d+)?(%s\\d+(\\.\\d+)?)+$".formatted(operatorsSetRegex);

        Pattern pattern2 = Pattern.compile(multipleExpressionsRegex);
        Matcher matcher2 = pattern2.matcher(infixExpression);

        if (matcher2.find())
        {
            return true;
        }

        return false;
    }
}
