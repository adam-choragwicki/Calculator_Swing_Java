package calculator;

import java.util.*;

public class Converter
{
    static String convertToPostfixExpression(String infixExpression)
    {
        StringBuilder resultPostfixExpression = new StringBuilder();
        Deque<Character> operatorsStack = new ArrayDeque<>();

        infixExpression = infixExpression.replace(" ", "");

        /* Simplifies some sequences of operators */
        infixExpression = simplifyInfixExpression(infixExpression);

        infixExpression = infixExpression.replace("-(", "-1*(");

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
                if (scannedCharacter == Operators.additionOperator)
                {
                    /* Operator is the first character in expression, so it is unary operator */
                    if (i == 0)
                    {
                        /* Scanned addition operator is unary operator and can be omitted */
                        continue;
                    }
                }
                else if (scannedCharacter == Operators.subtractionOperator)
                {
                    if (currentOperand.isEmpty())
                    {
                        /* Scanned subtraction operator is unary operator and should be part of next scanned operand */
                        currentOperand.append(scannedCharacter);
                        continue;
                    }
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
                    else if (hasPrecedence(scannedCharacter, operatorsStack.peek()) == Precedence.HIGHER)
                    {
                        operatorsStack.push(scannedCharacter);
                    }
                    else
                    {
                        while (!operatorsStack.isEmpty() && hasPrecedence(scannedCharacter, operatorsStack.peek()) != Precedence.HIGHER)
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
        "*+", "*",
        "/+", "/"
        );

        for (var entry : operatorsSimplificationMapping.entrySet())
        {
            infixExpression = infixExpression.replace(entry.getKey(), entry.getValue());
        }

        return infixExpression;
    }

    private enum Precedence
    {
        LOWER,
        EQUAL,
        HIGHER
    }

    static Precedence hasPrecedence(char operator1, char operator2)
    {
        if (operator1 == operator2)
        {
            return Precedence.EQUAL;
        }

        List<Character> precedenceLevel1 = List.of(Characters.leftParentheses);
        List<Character> precedenceLevel2 = List.of('*', '/');
        List<Character> precedenceLevel3 = List.of('+', '-');

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
            /* Operator 2 has lower precedence */
            else if (precedenceLevel3.contains(operator2))
            {
                return Precedence.HIGHER;
            }
            else
            {
                throw new RuntimeException("Operator2 '%c' does not belong to any precedence level".formatted(operator2));
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
            /* Operator 2 has higher precedence */
            else if (precedenceLevel3.contains(operator2))
            {
                return Precedence.HIGHER;
            }
            else
            {
                throw new RuntimeException("Operator2 '%c' does not belong to any precedence level".formatted(operator2));
            }
        }
        /* Operator1 has precedence level 3 */
        else if (precedenceLevel3.contains(operator1))
        {
            /* Operator 2 has higher precedence */
            if (precedenceLevel1.contains(operator2))
            {
                return Precedence.LOWER;
            }
            /* Operator 2 has higher precedence */
            else if (precedenceLevel2.contains(operator2))
            {
                return Precedence.LOWER;
            }
            /* Same precedence */
            else if (precedenceLevel3.contains(operator2))
            {
                return Precedence.EQUAL;
            }
            else
            {
                throw new RuntimeException("Operator2 '%c' does not belong to any precedence level".formatted(operator2));
            }
        }
        else
        {
            throw new RuntimeException("Operator1 '%c' does not belong to any precedence level".formatted(operator1));
        }
    }
}
