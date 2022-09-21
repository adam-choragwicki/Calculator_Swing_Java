package calculator;

import java.awt.*;
import java.util.List;

class Characters
{
    static final char leftParentheses = '(';
    static final char rightParentheses = ')';

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

    static final List<Character> availableCharacters = List.of(num0, num1, num2, num3, num4, num5, num6, num7, num8, num9, dot);
}

class Operators
{
    static final char additionOperator = '+';
    static final char subtractionOperator = '-';
    static final char multiplicationOperator = '*';
    static final char divisionOperator = '/';

    static final List<Character> availableOperators = List.of(additionOperator,subtractionOperator,multiplicationOperator,divisionOperator);
}

class Actions
{
    static final char equals = '=';
    static final char clear = 'C';
    static final String delete = "Del";
}

public class Config
{
    static final String emptyResultContent = "0";
    static final Font labelEquationFont = new Font("", Font.BOLD, 40);
    static final Font buttonFont = new Font("", Font.BOLD, 20);
    static final Font statusBarFont = new Font("", Font.BOLD, 12);

    public static final int windowWidth = 450;
    public static final int windowHeight = 400;
}
