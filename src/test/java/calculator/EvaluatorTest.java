package calculator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EvaluatorTest
{
    @Test
    @DisplayName("Nothing to evaluate")
    void evaluate1()
    {
        postfixExpression = "2";
        expectedEvaluationResult = "2";

        assertEquals(expectedEvaluationResult, Evaluator.evaluate(postfixExpression));

        postfixExpression = "-5";
        expectedEvaluationResult = "-5";

        assertEquals(expectedEvaluationResult, Evaluator.evaluate(postfixExpression));
    }

    @Test
    @DisplayName("Integer addition 2 + 5")
    void evaluate2()
    {
        postfixExpression = "2 5 +";
        expectedEvaluationResult = "7";

        assertEquals(expectedEvaluationResult, Evaluator.evaluate(postfixExpression));
    }

    @Test
    @DisplayName("Integer subtraction 2 - 5")
    void evaluate3()
    {
        postfixExpression = "2 5 -";
        expectedEvaluationResult = "-3";

        assertEquals(expectedEvaluationResult, Evaluator.evaluate(postfixExpression));
    }

    @Test
    @DisplayName("Integer multiplication 2 * 5")
    void evaluate4()
    {
        postfixExpression = "2 5 *";
        expectedEvaluationResult = "10";

        assertEquals(expectedEvaluationResult, Evaluator.evaluate(postfixExpression));
    }

    @Test
    @DisplayName("Integer division 2 / 5")
    void evaluate5()
    {
        postfixExpression = "2 5 /";
        expectedEvaluationResult = "0.4";

        assertEquals(expectedEvaluationResult, Evaluator.evaluate(postfixExpression));
    }

    @Test
    @DisplayName("Float addition 2.123 + 5.456")
    void evaluate6()
    {
        postfixExpression = "2.123 5.456 +";
        expectedEvaluationResult = "7.579000000000001";

        assertEquals(expectedEvaluationResult, Evaluator.evaluate(postfixExpression));
    }

    @Test
    @DisplayName("Float subtraction 2.123 - 5.456")
    void evaluate7()
    {
        postfixExpression = "2.123 5.456 -";
        expectedEvaluationResult = "-3.333";

        assertEquals(expectedEvaluationResult, Evaluator.evaluate(postfixExpression));
    }

    @Test
    @DisplayName("Float multiplication 2.123 * 5.456")
    void evaluate8()
    {
        postfixExpression = "2.123 5.456 *";
        expectedEvaluationResult = "11.583088000000002";

        assertEquals(expectedEvaluationResult, Evaluator.evaluate(postfixExpression));
    }

    @Test
    @DisplayName("Float division 2.123 / 5.456")
    void evaluate9()
    {
        postfixExpression = "2.123 5.456 /";
        expectedEvaluationResult = "0.38911290322580644";

        assertEquals(expectedEvaluationResult, Evaluator.evaluate(postfixExpression));
    }

    @Test
    @DisplayName("Same precedence operations Integer + Integer - Integer 2 + 5 - 3")
    void evaluate10()
    {
        postfixExpression = "2 5 + 3 -";
        expectedEvaluationResult = "4";

        assertEquals(expectedEvaluationResult, Evaluator.evaluate(postfixExpression));
    }

    @Test
    @DisplayName("Same precedence operations Integer * Integer * Integer 2 * 5 * 3")
    void evaluate11()
    {
        postfixExpression = "2 5 * 3 *";
        expectedEvaluationResult = "30";

        assertEquals(expectedEvaluationResult, Evaluator.evaluate(postfixExpression));
    }

    @Test
    @DisplayName("Mixed precedence operations Integer + Integer * Integer 2 + 5 * 3")
    void evaluate12()
    {
        postfixExpression = "2 5 3 * +";
        expectedEvaluationResult = "17";

        assertEquals(expectedEvaluationResult, Evaluator.evaluate(postfixExpression));
    }

    @Test
    @DisplayName("Mixed precedence operations Integer * Integer + Integer 2 * 5 + 3")
    void evaluate13()
    {
        postfixExpression = "2 5 * 3 +";
        expectedEvaluationResult = "13";

        assertEquals(expectedEvaluationResult, Evaluator.evaluate(postfixExpression));
    }

    @Test
    @DisplayName("Mixed precedence operations Integer + Integer * Integer + Integer 2 + 5 * 3 + 4")
    void evaluate14()
    {
        postfixExpression = "2 5 3 * + 4 +";
        expectedEvaluationResult = "21";

        assertEquals(expectedEvaluationResult, Evaluator.evaluate(postfixExpression));
    }

    @Test
    @DisplayName("Mixed precedence operations Integer * Integer + Integer * Integer 2 * 5 + 3 * 4")
    void evaluate15()
    {
        postfixExpression = "2 5 * 3 4 * +";
        expectedEvaluationResult = "22";

        assertEquals(expectedEvaluationResult, Evaluator.evaluate(postfixExpression));
    }

    @Test
    @DisplayName("Mixed precedence operations Integer * Integer - Integer / Integer + Integer 2 * 5 - 1 / 4 + 5")
    void evaluate16()
    {
        postfixExpression = "2 5 * 1 4 / - 5 +";
        expectedEvaluationResult = "14.75";

        assertEquals(expectedEvaluationResult, Evaluator.evaluate(postfixExpression));
    }

    @Test
    @DisplayName("Unary operator +")
    void evaluate17()
    {
        postfixExpression = "2 +5 -";
        expectedEvaluationResult = "-3";

        assertEquals(expectedEvaluationResult, Evaluator.evaluate(postfixExpression));
    }

    @Test
    @DisplayName("Unary operator -")
    void evaluate18()
    {
        postfixExpression = "2 -7 +";
        expectedEvaluationResult = "-5";

        assertEquals(expectedEvaluationResult, Evaluator.evaluate(postfixExpression));
    }

    private String postfixExpression;
    private String expectedEvaluationResult;
}
