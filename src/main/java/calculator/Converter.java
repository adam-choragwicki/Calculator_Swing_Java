package calculator;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class Converter
{
    static String convertToPostfixExpression(String infixExpression)
    {
        StringBuilder result = new StringBuilder();
        Deque<Character> operatorsStack = new ArrayDeque<>();

        infixExpression = infixExpression.replace(" ", "");

        StringBuilder currentOperand = new StringBuilder();

        for (int i = 0; i < infixExpression.length(); i++)
        {
            final char scannedCharacter = infixExpression.charAt(i);

            /* Digit or dot */
            if (Config.availableCharacters.contains(scannedCharacter))
            {
                currentOperand.append(scannedCharacter);
            }
            /* Operator */
            else if (Config.availableOperators.contains(scannedCharacter))
            {
                result.append(' ').append(currentOperand);
                currentOperand.setLength(0);

                if (operatorsStack.isEmpty())
                {
                    operatorsStack.push(scannedCharacter);
                }
                else
                {
                    if (hasPrecedence(scannedCharacter, operatorsStack.peek()) == Precedence.HIGHER)
                    {
                        operatorsStack.push(scannedCharacter);
                    }
                    else
                    {
                        while (!operatorsStack.isEmpty() && hasPrecedence(scannedCharacter, operatorsStack.peek()) != Precedence.HIGHER)
                        {
                            result.append(' ').append(operatorsStack.pop());
                        }

                        operatorsStack.push(scannedCharacter);
                    }
                }
            }
            else
            {
                throw new RuntimeException(String.format("Infix expression contains unsupported character '%c'", scannedCharacter));
            }
        }

        result.append(' ').append(currentOperand);

        while (!operatorsStack.isEmpty())
        {
            result.append(' ').append(operatorsStack.pop());
        }

        /* Remove doubles spaces */
        return String.valueOf(result).trim().replace("  ", " ");
    }

    private enum Precedence
    {
        LOWER,
        EQUAL,
        HIGHER
    }

    private static Precedence hasPrecedence(char operator1, char operator2)
    {
        if (operator1 == operator2)
        {
            return Precedence.EQUAL;
        }

        List<Character> precedenceLevel1 = List.of(Config.multiplicationOperator, Config.divisionOperator);
        List<Character> precedenceLevel2 = List.of(Config.additionOperator, Config.subtractionOperator);

        /* Operator1 has precedence level 1 */
        if (precedenceLevel1.contains(operator1))
        {
            /* Same precedence */
            if (precedenceLevel1.contains(operator2))
            {
                return Precedence.EQUAL;
            }
            /* Operator 2 has lower precedence */
            else if (precedenceLevel2.contains(operator2))
            {
                return Precedence.HIGHER;
            }
            else
            {
                throw new RuntimeException("Operator2 " + operator2 + " does not belong to any precedence level");
            }
        }
        /* Operator1 has precedence level 2 */
        else if (precedenceLevel2.contains(operator1))
        {
            /* Operator 2 has lower precedence */
            if (precedenceLevel1.contains(operator2))
            {
                return Precedence.LOWER;
            }
            /* Same precedence */
            else if (precedenceLevel2.contains(operator2))
            {
                return Precedence.EQUAL;
            }
            else
            {
                throw new RuntimeException("Operator2 " + operator2 + " does not belong to any precedence level");
            }
        }
        else
        {
            throw new RuntimeException("Operator1 " + operator1 + " does not belong to any precedence level");
        }
    }
}
