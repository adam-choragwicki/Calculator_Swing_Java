package config;

import java.util.List;

public class Operators
{
    public static final char additionOperator = '+';
    public static final char subtractionOperator = '-';
    public static final char multiplicationOperator = '*';
    public static final char divisionOperator = '/';

    public static final List<Character> availableOperators = List.of(additionOperator,subtractionOperator,multiplicationOperator,divisionOperator);
}
