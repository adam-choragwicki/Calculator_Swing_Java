package conversion;

import config.Characters;
import config.Operators;

import java.util.*;

public class Converter
{
    public static String convertToPostfixExpression(String infixExpression)
    {
        StringBuilder resultPostfixExpression = new StringBuilder();
        Deque<Character> operatorsStack = new ArrayDeque<>();

        infixExpression = infixExpression.replace(" ", "");

        /* Simplifies some sequences of operators */
        infixExpression = simplifyInfixExpression(infixExpression);

        infixExpression = infixExpression.replace("-(", "-1%c(".formatted(Operators.multiplicationOperator));

        StringBuilder currentOperand = new StringBuilder();

        for (int i = 0; i < infixExpression.length(); i++)
        {
            final char scannedCharacter = infixExpression.charAt(i);

            /* Digit or dot */
            if (Characters.availableCharacters.contains(scannedCharacter))
            {
                currentOperand.append(scannedCharacter);
            }
            /* Operator */
            else if (Operators.availableOperators.contains(scannedCharacter))
            {
                if (scannedCharacter == Operators.additionOperator && isUnaryOperator(infixExpression, i))
                {
                    /* Scanned addition operator is unary operator and can be omitted */
                    continue;
                }
                else if (scannedCharacter == Operators.subtractionOperator && isUnaryOperator(infixExpression, i))
                {
                    /* Scanned subtraction operator is unary operator and should be part of next scanned operand */
                    currentOperand.append(scannedCharacter);
                    continue;
                }

                resultPostfixExpression.append(' ').append(currentOperand);
                currentOperand.setLength(0);

                if (operatorsStack.isEmpty())
                {
                    operatorsStack.push(scannedCharacter);
                }
                else
                {
                    if (operatorsStack.peek() == '(')
                    {
                        operatorsStack.push(scannedCharacter);
                    }
                    else if (OperatorPrecedenceManager.hasPrecedence(scannedCharacter, operatorsStack.peek()))
                    {
                        operatorsStack.push(scannedCharacter);
                    }
                    else
                    {
                        while (!operatorsStack.isEmpty() && !OperatorPrecedenceManager.hasPrecedence(scannedCharacter, operatorsStack.peek()))
                        {
                            char topOperator = operatorsStack.pop();

                            if (topOperator != Characters.leftParentheses)
                            {
                                resultPostfixExpression.append(' ').append(topOperator);
                            }
                        }

                        operatorsStack.push(scannedCharacter);
                    }
                }
            }
            else if (scannedCharacter == Characters.leftParentheses)
            {
                if (!currentOperand.isEmpty())
                {
                    resultPostfixExpression.append(' ').append(currentOperand);
                    currentOperand.setLength(0);
                }

                operatorsStack.push(scannedCharacter);
            }
            else if (scannedCharacter == Characters.rightParentheses)
            {
                resultPostfixExpression.append(' ').append(currentOperand);
                currentOperand.setLength(0);

                while (!operatorsStack.isEmpty())
                {
                    char character = operatorsStack.pop();

                    if (character == '(')
                    {
                        break;
                    }
                    else
                    {
                        resultPostfixExpression.append(' ').append(character);
                    }
                }
            }
            else
            {
                throw new RuntimeException(String.format("Infix expression contains unsupported character '%c'", scannedCharacter));
            }
        }

        resultPostfixExpression.append(' ').append(currentOperand);

        while (!operatorsStack.isEmpty())
        {
            resultPostfixExpression.append(' ').append(operatorsStack.pop());
        }

        /* Remove doubles spaces */
        return String.valueOf(resultPostfixExpression).trim().replace("  ", " ");
    }

    private static String simplifyInfixExpression(String infixExpression)
    {
        Map<String, String> operatorsSimplificationMapping = Map.of(
        "++", "+",
        "+-", "-",
        "-+", "-",
        "--", "+",
        "%c+".formatted(Operators.multiplicationOperator), "%c".formatted(Operators.multiplicationOperator),
        "%c+".formatted(Operators.divisionOperator), "%c".formatted(Operators.divisionOperator)
        );

        for (var entry : operatorsSimplificationMapping.entrySet())
        {
            infixExpression = infixExpression.replace(entry.getKey(), entry.getValue());
        }

        return infixExpression;
    }

    private static boolean isUnaryOperator(String infixExpression, int i)
    {
        /* Operator is the first character in expression, so it is unary operator */
        if (i == 0)
        {
            return true;
        }
        else
        {
            char previousCharacter = infixExpression.charAt(i - 1);
            return (previousCharacter == Characters.leftParentheses) || (previousCharacter == Operators.multiplicationOperator) || (previousCharacter == Operators.divisionOperator);
        }
    }
}
