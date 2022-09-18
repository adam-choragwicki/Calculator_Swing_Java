package calculator;

import java.util.ArrayDeque;
import java.util.Deque;

public class Evaluator
{
    static String evaluate(final String postfixExpression)
    {
        Deque<String> operandsStack = new ArrayDeque<>();
        String[] tokens = postfixExpression.split(" ");

        for (String token : tokens)
        {
            /* Operator */
            if (token.length() == 1 && Config.availableOperators.contains(token.charAt(0)))
            {
                char operator = token.charAt(0);

                if (operandsStack.size() >= 2)
                {
                    /* First popped operand is the second operand */
                    String operand2String = operandsStack.pop();
                    String operand1String = operandsStack.pop();

                    double operand1 = Double.parseDouble(operand1String);
                    double operand2 = Double.parseDouble(operand2String);

                    double result = switch (operator)
                    {
                        case Config.additionOperator -> operand1 + operand2;
                        case Config.subtractionOperator -> operand1 - operand2;
                        case Config.multiplicationOperator -> operand1 * operand2;
                        case Config.divisionOperator -> operand1 / operand2;
                        default -> throw new RuntimeException("Unsupported operator " + operator);
                    };

                    operandsStack.push(String.valueOf(result));
                }
                else
                {
                    throw new RuntimeException("Operand stack contains less than 2 operands");
                }
            }
            /* Operand */
            else
            {
                operandsStack.push(token);
            }
        }

        if (operandsStack.size() == 1)
        {
            String result = operandsStack.pop();

            /* Remove trailing .0 */
            if (result.endsWith(".0"))
            {
                return result.substring(0, result.length() - 2);
            }

            return result;
        }
        else
        {
            throw new RuntimeException("Operands stack size after end of evaluation should be 1");
        }
    }
}
