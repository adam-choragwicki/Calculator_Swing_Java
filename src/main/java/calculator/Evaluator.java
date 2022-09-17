package calculator;

import java.util.ArrayDeque;
import java.util.Deque;

public class Evaluator
{
    static String evaluate(String postfixExpression)
    {
        Deque<String> operandsStack = new ArrayDeque<>();
        StringBuilder currentOperand = new StringBuilder();

        for (int i = 0; i < postfixExpression.length(); ++i)
        {
            final char scannedCharacter = postfixExpression.charAt(i);

            if (Character.isDigit(scannedCharacter) || scannedCharacter == '.')
            {
                currentOperand.append(scannedCharacter);
            }
            /* Space */
            else if (scannedCharacter == ' ')
            {
                if (currentOperand.length() > 0)
                {
                    operandsStack.push(currentOperand.toString());
                    currentOperand.setLength(0);
                }
            }
            /* Operator */
            else if (Config.availableOperators.contains(scannedCharacter))
            {
                if (operandsStack.size() >= 2)
                {
                    /* First popped operand is the second operand */
                    String operand2String = operandsStack.pop();
                    String operand1String = operandsStack.pop();

                    double operand1 = Double.parseDouble(operand1String);
                    double operand2 = Double.parseDouble(operand2String);

                    double result;

                    if (scannedCharacter == Config.additionOperator)
                    {
                        result = operand1 + operand2;
                    }
                    else if (scannedCharacter == Config.subtractionOperator)
                    {
                        result = operand1 - operand2;
                    }
                    else if (scannedCharacter == Config.multiplicationOperator)
                    {
                        result = operand1 * operand2;
                    }
                    else if (scannedCharacter == Config.divisionOperator)
                    {
                        result = operand1 / operand2;
                    }
                    else
                    {
                        throw new RuntimeException("Unsupported operator " + scannedCharacter);
                    }

                    operandsStack.push(String.valueOf(result));
                }
                else
                {
                    throw new RuntimeException("Operand stack contains less than 2 operands");
                }
            }
            else
            {
                throw new RuntimeException("Postfix expression under evaluation contains unsupported character '%c'".formatted(scannedCharacter));
            }
        }

        if (!currentOperand.isEmpty())
        {
            operandsStack.push(currentOperand.toString());
            currentOperand.setLength(0);
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
