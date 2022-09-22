package evaluation;

import config.Operators;

import java.util.ArrayDeque;
import java.util.Deque;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Evaluator
{
    public static String evaluate(final String postfixExpression)
    {
        Deque<String> operandsStack = new ArrayDeque<>();
        String[] tokens = postfixExpression.split(" ");

        for (String token : tokens)
        {
            /* Operator */
            if (token.length() == 1 && Operators.availableOperators.contains(token.charAt(0)))
            {
                char operator = token.charAt(0);

                if (operandsStack.size() >= 2)
                {
                    /* First popped operand is the second operand */
                    String operand2String = operandsStack.pop();
                    String operand1String = operandsStack.pop();

                    BigDecimal operand1 = new BigDecimal(operand1String);
                    BigDecimal operand2 = new BigDecimal(operand2String);

                    BigDecimal result = switch (operator)
                    {
                        case Operators.additionOperator -> operand1.add(operand2);
                        case Operators.subtractionOperator -> operand1.subtract(operand2);
                        case Operators.multiplicationOperator -> operand1.multiply(operand2);
                        case Operators.divisionOperator -> operand1.divide(operand2, 10, RoundingMode.DOWN);
                        default -> throw new RuntimeException("Unsupported operator " + operator);
                    };

                    operandsStack.push(result.stripTrailingZeros().toPlainString());
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
            return operandsStack.pop();
        }
        else
        {
            throw new RuntimeException("Operands stack size after end of evaluation should be 1");
        }
    }
}
