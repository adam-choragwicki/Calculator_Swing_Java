package calculator;

import java.util.List;

public class OperatorPrecedenceManager
{
    static boolean hasPrecedence(char operator1, char operator2)
    {
        if (operator1 == operator2)
        {
            return false;
        }

        /* Operator1 has precedence level 1 */
        if (precedenceLevel1.contains(operator1))
        {
            /* Same precedence */
            if (precedenceLevel1.contains(operator2))
            {
                return false;
            }
            /* Operator 2 has lower precedence */
            else if (precedenceLevel2.contains(operator2))
            {
                return true;
            }
            /* Operator 2 has lower precedence */
            else if (precedenceLevel3.contains(operator2))
            {
                return true;
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
                return false;
            }
            /* Same precedence */
            else if (precedenceLevel2.contains(operator2))
            {
                return false;
            }
            /* Operator 2 has higher precedence */
            else if (precedenceLevel3.contains(operator2))
            {
                return true;
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
                return false;
            }
            /* Operator 2 has higher precedence */
            else if (precedenceLevel2.contains(operator2))
            {
                return false;
            }
            /* Same precedence */
            else if (precedenceLevel3.contains(operator2))
            {
                return false;
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

    static final List<Character> precedenceLevel1 = List.of(Characters.leftParentheses);
    static final List<Character> precedenceLevel2 = List.of(Operators.multiplicationOperator, Operators.divisionOperator);
    static final List<Character> precedenceLevel3 = List.of(Operators.additionOperator, Operators.subtractionOperator);
}
