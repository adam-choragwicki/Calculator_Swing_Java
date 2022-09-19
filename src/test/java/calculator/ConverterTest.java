package calculator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ConverterTest
{
    @Test
    void convertToPostfixExpression1()
    {
        infixExpression = "2 + 5";
        expectedPostfixExpression = "2 5 +";

        assertEquals(expectedPostfixExpression, Converter.convertToPostfixExpression(infixExpression));
    }

    @Test
    void convertToPostfixExpression2()
    {
        infixExpression = "2 + 5 * 3";
        expectedPostfixExpression = "2 5 3 * +";

        assertEquals(expectedPostfixExpression, Converter.convertToPostfixExpression(infixExpression));
    }

    @Test
    void convertToPostfixExpression3()
    {
        infixExpression = "2 + 5 * 3 - 4";
        expectedPostfixExpression = "2 5 3 * + 4 -";

        assertEquals(expectedPostfixExpression, Converter.convertToPostfixExpression(infixExpression));
    }

    @Test
    void convertToPostfixExpression4()
    {
        infixExpression = "2 + 5 * 3 - 4 / 8";
        expectedPostfixExpression = "2 5 3 * + 4 8 / -";

        assertEquals(expectedPostfixExpression, Converter.convertToPostfixExpression(infixExpression));
    }

    @Test
    void convertToPostfixExpression5()
    {
        infixExpression = "2 * 5";
        expectedPostfixExpression = "2 5 *";

        assertEquals(expectedPostfixExpression, Converter.convertToPostfixExpression(infixExpression));
    }

    @Test
    void convertToPostfixExpression6()
    {
        infixExpression = "2 * 5 + 3";
        expectedPostfixExpression = "2 5 * 3 +";

        assertEquals(expectedPostfixExpression, Converter.convertToPostfixExpression(infixExpression));
    }

    @Test
    void convertToPostfixExpression7()
    {
        infixExpression = "2 * 5 + 3 - 4";
        expectedPostfixExpression = "2 5 * 3 + 4 -";

        assertEquals(expectedPostfixExpression, Converter.convertToPostfixExpression(infixExpression));
    }

    @Test
    void convertToPostfixExpression8()
    {
        infixExpression = "2 * 5 + 3 - 4 / 8";
        expectedPostfixExpression = "2 5 * 3 + 4 8 / -";

        assertEquals(expectedPostfixExpression, Converter.convertToPostfixExpression(infixExpression));
    }

    @Test
    @DisplayName("Unary operator +")
    void convertToPostfixExpression9()
    {
        infixExpression = "+2 - +3";
        expectedPostfixExpression = "2 3 -";

        assertEquals(expectedPostfixExpression, Converter.convertToPostfixExpression(infixExpression));

        infixExpression = "2 - +3";
        expectedPostfixExpression = "2 3 -";

        assertEquals(expectedPostfixExpression, Converter.convertToPostfixExpression(infixExpression));
    }

    @Test
    @DisplayName("Unary operator -")
    void convertToPostfixExpression10()
    {
        infixExpression = "-2 + -3";
        expectedPostfixExpression = "-2 3 -";

        assertEquals(expectedPostfixExpression, Converter.convertToPostfixExpression(infixExpression));

        infixExpression = "2 + -3";
        expectedPostfixExpression = "2 3 -";

        assertEquals(expectedPostfixExpression, Converter.convertToPostfixExpression(infixExpression));
    }

    @Test
    @DisplayName("Single parentheses")
    void convertToPostfixExpression11()
    {
        infixExpression = "(2 + 3)";
        expectedPostfixExpression = "2 3 +";

        assertEquals(expectedPostfixExpression, Converter.convertToPostfixExpression(infixExpression));

        infixExpression = "(-2 + 3)";
        expectedPostfixExpression = "-2 3 +";

        assertEquals(expectedPostfixExpression, Converter.convertToPostfixExpression(infixExpression));

        infixExpression = "(2 + -3)";
        expectedPostfixExpression = "2 3 -";

        assertEquals(expectedPostfixExpression, Converter.convertToPostfixExpression(infixExpression));

        infixExpression = "(-2 + -3)";
        expectedPostfixExpression = "-2 3 -";

        assertEquals(expectedPostfixExpression, Converter.convertToPostfixExpression(infixExpression));

        infixExpression = "4 * (2 + 3) / 5";
        expectedPostfixExpression = "4 2 3 + * 5 /";

        assertEquals(expectedPostfixExpression, Converter.convertToPostfixExpression(infixExpression));

        infixExpression = "-4 * (-2 + -3) / -5";
        expectedPostfixExpression = "-4 -2 3 - * -5 /";

        assertEquals(expectedPostfixExpression, Converter.convertToPostfixExpression(infixExpression));
    }

    @Test
    @DisplayName("Multiple parentheses")
    void convertToPostfixExpression12()
    {
        infixExpression = "(2 + 3) * (4 - 5)";
        expectedPostfixExpression = "2 3 + 4 5 - *";

        assertEquals(expectedPostfixExpression, Converter.convertToPostfixExpression(infixExpression));
    }

    @Test
    @DisplayName("Unary '+' before parentheses")
    void convertToPostfixExpression13()
    {
//        infixExpression = "+(2 + 3)";
//        expectedPostfixExpression = "2 3 +";
//
//        assertEquals(expectedPostfixExpression, Converter.convertToPostfixExpression(infixExpression));
//
//        infixExpression = "(2 + 3) + (5 * 1)";
//        expectedPostfixExpression = "2 3 + 5 1 *";
//
//        assertEquals(expectedPostfixExpression, Converter.convertToPostfixExpression(infixExpression));
//
//        assertEquals("10", Evaluator.evaluate(expectedPostfixExpression));
    }

    @Test
    @DisplayName("Negate parentheses")
    void convertToPostfixExpression14()
    {
        infixExpression = "-(2 + 3)";
        expectedPostfixExpression = "-1 2 3 + *";

        assertEquals(expectedPostfixExpression, Converter.convertToPostfixExpression(infixExpression));

        infixExpression = "-(2 + 3) * -(4 - 5)";
        expectedPostfixExpression = "-1 2 3 + * -1 * 4 5 - *";

        assertEquals(expectedPostfixExpression, Converter.convertToPostfixExpression(infixExpression));

        infixExpression = "-(2 + 3) * -(4 - 5)";
        expectedPostfixExpression = "-1 2 3 + * -1 * 4 5 - *";

        assertEquals(expectedPostfixExpression, Converter.convertToPostfixExpression(infixExpression));
    }

    private String infixExpression;
    private String expectedPostfixExpression;
}
