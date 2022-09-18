package calculator;

import java.util.List;

public class Config
{
    static final char additionOperator = '+';
    static final char subtractionOperator = '-';
    static final char multiplicationOperator = '*';
    static final char divisionOperator = '/';

    static final char num0 = '0';
    static final char num1 = '1';
    static final char num2 = '2';
    static final char num3 = '3';
    static final char num4 = '4';
    static final char num5 = '5';
    static final char num6 = '6';
    static final char num7 = '7';
    static final char num8 = '8';
    static final char num9 = '9';
    static final char dot = '.';

    static final char equals = '=';
    static final char clear = 'C';
    static final String delete = "Del";

    static List<Character> availableCharacters;
    static List<Character> availableOperators;

    static
    {
        availableOperators = List.of(additionOperator, subtractionOperator, multiplicationOperator, divisionOperator);
        availableCharacters = List.of(num0, num1, num2, num3, num4, num5, num6, num7, num8, num9, dot);
    }
}
