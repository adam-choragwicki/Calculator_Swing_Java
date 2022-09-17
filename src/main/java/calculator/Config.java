package calculator;

import java.util.List;

public class Config
{
    static final char additionOperator;
    static final char subtractionOperator;
    static final char multiplicationOperator;
    static final char divisionOperator;
    static List<Character> availableOperators;

    static
    {
        additionOperator = '+';
        subtractionOperator = '-';
        multiplicationOperator = '*';
        divisionOperator = '/';

        availableOperators = List.of(additionOperator,subtractionOperator,multiplicationOperator,divisionOperator);
    }
}
