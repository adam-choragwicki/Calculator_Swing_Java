package calculator;

import static org.junit.jupiter.api.Assertions.*;

import conversion.Converter;
import evaluation.Evaluator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ConverterTest
{
    @Test
    @DisplayName("Simple expression")
    void convertToPostfixExpression1()
    {
        infixExpression = "2 + 5";
        expectedPostfixExpression = "2 5 +";
        expectedEvaluationResult = "7";

        assertEquals(expectedPostfixExpression, Converter.convertToPostfixExpression(infixExpression));
        assertEquals(expectedEvaluationResult, Evaluator.evaluate(Converter.convertToPostfixExpression(infixExpression)));

        infixExpression = "2 + 5 \u00D7 3";
        expectedPostfixExpression = "2 5 3 \u00D7 +";
        expectedEvaluationResult = "17";

        assertEquals(expectedPostfixExpression, Converter.convertToPostfixExpression(infixExpression));
        assertEquals(expectedEvaluationResult, Evaluator.evaluate(Converter.convertToPostfixExpression(infixExpression)));

        infixExpression = "2 + 5 \u00D7 3 - 4";
        expectedPostfixExpression = "2 5 3 \u00D7 + 4 -";
        expectedEvaluationResult = "13";

        assertEquals(expectedPostfixExpression, Converter.convertToPostfixExpression(infixExpression));
        assertEquals(expectedEvaluationResult, Evaluator.evaluate(Converter.convertToPostfixExpression(infixExpression)));

        infixExpression = "2 + 5 \u00D7 3 - 4 \u00F7 8";
        expectedPostfixExpression = "2 5 3 \u00D7 + 4 8 \u00F7 -";
        expectedEvaluationResult = "16.5";

        assertEquals(expectedPostfixExpression, Converter.convertToPostfixExpression(infixExpression));
        assertEquals(expectedEvaluationResult, Evaluator.evaluate(Converter.convertToPostfixExpression(infixExpression)));

        infixExpression = "2 \u00D7 5";
        expectedPostfixExpression = "2 5 \u00D7";
        expectedEvaluationResult = "10";

        assertEquals(expectedPostfixExpression, Converter.convertToPostfixExpression(infixExpression));
        assertEquals(expectedEvaluationResult, Evaluator.evaluate(Converter.convertToPostfixExpression(infixExpression)));

        infixExpression = "2 \u00D7 5 + 3";
        expectedPostfixExpression = "2 5 \u00D7 3 +";
        expectedEvaluationResult = "13";

        assertEquals(expectedPostfixExpression, Converter.convertToPostfixExpression(infixExpression));
        assertEquals(expectedEvaluationResult, Evaluator.evaluate(Converter.convertToPostfixExpression(infixExpression)));

        infixExpression = "2 \u00D7 5 + 3 - 4";
        expectedPostfixExpression = "2 5 \u00D7 3 + 4 -";
        expectedEvaluationResult = "9";

        assertEquals(expectedPostfixExpression, Converter.convertToPostfixExpression(infixExpression));
        assertEquals(expectedEvaluationResult, Evaluator.evaluate(Converter.convertToPostfixExpression(infixExpression)));

        infixExpression = "2 \u00D7 5 + 3 - 4 \u00F7 8";
        expectedPostfixExpression = "2 5 \u00D7 3 + 4 8 \u00F7 -";
        expectedEvaluationResult = "12.5";

        assertEquals(expectedPostfixExpression, Converter.convertToPostfixExpression(infixExpression));
        assertEquals(expectedEvaluationResult, Evaluator.evaluate(Converter.convertToPostfixExpression(infixExpression)));
    }

    @Test
    @DisplayName("Unary operator +")
    void convertToPostfixExpression2()
    {
        infixExpression = "+2 - +3";
        expectedPostfixExpression = "2 3 -";
        expectedEvaluationResult = "-1";

        assertEquals(expectedPostfixExpression, Converter.convertToPostfixExpression(infixExpression));
        assertEquals(expectedEvaluationResult, Evaluator.evaluate(Converter.convertToPostfixExpression(infixExpression)));

        infixExpression = "2 - +3";
        expectedPostfixExpression = "2 3 -";
        expectedEvaluationResult = "-1";

        assertEquals(expectedPostfixExpression, Converter.convertToPostfixExpression(infixExpression));
        assertEquals(expectedEvaluationResult, Evaluator.evaluate(Converter.convertToPostfixExpression(infixExpression)));
    }

    @Test
    @DisplayName("Unary operator -")
    void convertToPostfixExpression3()
    {
        infixExpression = "-2 + -3";
        expectedPostfixExpression = "-2 3 -";
        expectedEvaluationResult = "-5";

        assertEquals(expectedPostfixExpression, Converter.convertToPostfixExpression(infixExpression));
        assertEquals(expectedEvaluationResult, Evaluator.evaluate(Converter.convertToPostfixExpression(infixExpression)));

        infixExpression = "2 + -3";
        expectedPostfixExpression = "2 3 -";
        expectedEvaluationResult = "-1";

        assertEquals(expectedPostfixExpression, Converter.convertToPostfixExpression(infixExpression));
        assertEquals(expectedEvaluationResult, Evaluator.evaluate(Converter.convertToPostfixExpression(infixExpression)));
    }

    @Test
    @DisplayName("Single parentheses")
    void convertToPostfixExpression4()
    {
        infixExpression = "(2 + 3)";
        expectedPostfixExpression = "2 3 +";
        expectedEvaluationResult = "5";

        assertEquals(expectedPostfixExpression, Converter.convertToPostfixExpression(infixExpression));
        assertEquals(expectedEvaluationResult, Evaluator.evaluate(Converter.convertToPostfixExpression(infixExpression)));

        infixExpression = "(-2 + 3)";
        expectedPostfixExpression = "-2 3 +";
        expectedEvaluationResult = "1";

        assertEquals(expectedPostfixExpression, Converter.convertToPostfixExpression(infixExpression));
        assertEquals(expectedEvaluationResult, Evaluator.evaluate(Converter.convertToPostfixExpression(infixExpression)));

        infixExpression = "(2 + -3)";
        expectedPostfixExpression = "2 3 -";
        expectedEvaluationResult = "-1";

        assertEquals(expectedPostfixExpression, Converter.convertToPostfixExpression(infixExpression));
        assertEquals(expectedEvaluationResult, Evaluator.evaluate(Converter.convertToPostfixExpression(infixExpression)));

        infixExpression = "(-2 + -3)";
        expectedPostfixExpression = "-2 3 -";
        expectedEvaluationResult = "-5";

        assertEquals(expectedPostfixExpression, Converter.convertToPostfixExpression(infixExpression));
        assertEquals(expectedEvaluationResult, Evaluator.evaluate(Converter.convertToPostfixExpression(infixExpression)));

        infixExpression = "4 \u00D7 (2 + 3) \u00F7 5";
        expectedPostfixExpression = "4 2 3 + \u00D7 5 \u00F7";
        expectedEvaluationResult = "4";

        assertEquals(expectedPostfixExpression, Converter.convertToPostfixExpression(infixExpression));
        assertEquals(expectedEvaluationResult, Evaluator.evaluate(Converter.convertToPostfixExpression(infixExpression)));

        infixExpression = "-4 \u00D7 (-2 + -3) \u00F7 -5";
        expectedPostfixExpression = "-4 -2 3 - \u00D7 -5 \u00F7";
        expectedEvaluationResult = "-4";

        assertEquals(expectedPostfixExpression, Converter.convertToPostfixExpression(infixExpression));
        assertEquals(expectedEvaluationResult, Evaluator.evaluate(Converter.convertToPostfixExpression(infixExpression)));

        infixExpression = "5 \u00D7 (3 + 2)";
        expectedPostfixExpression = "5 3 2 + \u00D7";
        expectedEvaluationResult = "25";

        assertEquals(expectedPostfixExpression, Converter.convertToPostfixExpression(infixExpression));
        assertEquals(expectedEvaluationResult, Evaluator.evaluate(Converter.convertToPostfixExpression(infixExpression)));
    }

    @Test
    @DisplayName("Multiple parentheses")
    void convertToPostfixExpression5()
    {
        infixExpression = "(2 + 3) \u00D7 (4 - 5)";
        expectedPostfixExpression = "2 3 + 4 5 - \u00D7";
        expectedEvaluationResult = "-5";

        assertEquals(expectedPostfixExpression, Converter.convertToPostfixExpression(infixExpression));
        assertEquals(expectedEvaluationResult, Evaluator.evaluate(Converter.convertToPostfixExpression(infixExpression)));
    }

    @Test
    @DisplayName("'+' before parentheses")
    void convertToPostfixExpression6()
    {
        infixExpression = "5 + (3 \u00D7 2)";
        expectedPostfixExpression = "5 3 2 \u00D7 +";
        expectedEvaluationResult = "11";

        assertEquals(expectedPostfixExpression, Converter.convertToPostfixExpression(infixExpression));
        assertEquals(expectedEvaluationResult, Evaluator.evaluate(Converter.convertToPostfixExpression(infixExpression)));

        infixExpression = "+(3 + 2)";
        expectedPostfixExpression = "3 2 +";
        expectedEvaluationResult = "5";

        assertEquals(expectedPostfixExpression, Converter.convertToPostfixExpression(infixExpression));
        assertEquals(expectedEvaluationResult, Evaluator.evaluate(Converter.convertToPostfixExpression(infixExpression)));

        infixExpression = "(2 + 3) + (5 \u00D7 1)";
        expectedPostfixExpression = "2 3 + 5 1 \u00D7 +";
        expectedEvaluationResult = "10";

        assertEquals(expectedPostfixExpression, Converter.convertToPostfixExpression(infixExpression));
        assertEquals(expectedEvaluationResult, Evaluator.evaluate(Converter.convertToPostfixExpression(infixExpression)));
    }

    @Test
    @DisplayName("Nested parentheses")
    void convertToPostfixExpression7()
    {
        infixExpression = "5 \u00D7 ((3 + 2) \u00D7 4)";
        expectedPostfixExpression = "5 3 2 + 4 \u00D7 \u00D7";
        expectedEvaluationResult = "100";

        assertEquals(expectedPostfixExpression, Converter.convertToPostfixExpression(infixExpression));
        assertEquals(expectedEvaluationResult, Evaluator.evaluate(Converter.convertToPostfixExpression(infixExpression)));

        infixExpression = "-5 \u00D7 -(-(3 + 2) \u00D7 -4)";
        expectedPostfixExpression = "-5 -1 \u00D7 -1 3 2 + \u00D7 \u00D7 -4 \u00D7";
        expectedEvaluationResult = "100";

        assertEquals(expectedPostfixExpression, Converter.convertToPostfixExpression(infixExpression));
        assertEquals(expectedEvaluationResult, Evaluator.evaluate(Converter.convertToPostfixExpression(infixExpression)));
    }

    @Test
    @DisplayName("Negate parentheses")
    void convertToPostfixExpression8()
    {
        infixExpression = "-(2 + 3)";
        expectedPostfixExpression = "-1 2 3 + \u00D7";
        expectedEvaluationResult = "-5";

        assertEquals(expectedPostfixExpression, Converter.convertToPostfixExpression(infixExpression));
        assertEquals(expectedEvaluationResult, Evaluator.evaluate(Converter.convertToPostfixExpression(infixExpression)));

        infixExpression = "-(2 + 3) \u00D7 -(4 - 5)";
        expectedPostfixExpression = "-1 2 3 + \u00D7 -1 \u00D7 4 5 - \u00D7";
        expectedEvaluationResult = "-5";

        assertEquals(expectedPostfixExpression, Converter.convertToPostfixExpression(infixExpression));
        assertEquals(expectedEvaluationResult, Evaluator.evaluate(Converter.convertToPostfixExpression(infixExpression)));

        infixExpression = "-(4 - 5) + -(5 + 10)";
        expectedPostfixExpression = "-1 4 5 - \u00D7 1 5 10 + \u00D7 -";
        expectedEvaluationResult = "-14";

        assertEquals(expectedPostfixExpression, Converter.convertToPostfixExpression(infixExpression));
        assertEquals(expectedEvaluationResult, Evaluator.evaluate(Converter.convertToPostfixExpression(infixExpression)));

        infixExpression = "-(2 + 3) \u00D7 -(4 - 5) + -(5 + 10)";
        expectedPostfixExpression = "-1 2 3 + \u00D7 -1 \u00D7 4 5 - \u00D7 1 5 10 + \u00D7 -";
        expectedEvaluationResult = "-20";

        assertEquals(expectedPostfixExpression, Converter.convertToPostfixExpression(infixExpression));
        assertEquals(expectedEvaluationResult, Evaluator.evaluate(Converter.convertToPostfixExpression(infixExpression)));
    }

    private String infixExpression;
    private String expectedPostfixExpression;
    private String expectedEvaluationResult;
}
