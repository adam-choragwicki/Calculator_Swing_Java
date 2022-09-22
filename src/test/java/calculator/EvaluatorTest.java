package calculator;

import evaluation.Evaluator;
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
    @DisplayName("Integer operations")
    void evaluate2()
    {
        postfixExpression = "2 5 +";
        expectedEvaluationResult = "7";

        assertEquals(expectedEvaluationResult, Evaluator.evaluate(postfixExpression));

        postfixExpression = "2 5 -";
        expectedEvaluationResult = "-3";

        assertEquals(expectedEvaluationResult, Evaluator.evaluate(postfixExpression));

        postfixExpression = "2 5 *";
        expectedEvaluationResult = "10";

        assertEquals(expectedEvaluationResult, Evaluator.evaluate(postfixExpression));

        postfixExpression = "2 5 /";
        expectedEvaluationResult = "0.4";

        assertEquals(expectedEvaluationResult, Evaluator.evaluate(postfixExpression));
    }

    @Test
    @DisplayName("Float operations")
    void evaluate3()
    {
        postfixExpression = "2.123 5.456 +";
        expectedEvaluationResult = "7.579";

        assertEquals(expectedEvaluationResult, Evaluator.evaluate(postfixExpression));

        postfixExpression = "2.123 5.456 -";
        expectedEvaluationResult = "-3.333";

        assertEquals(expectedEvaluationResult, Evaluator.evaluate(postfixExpression));

        postfixExpression = "2.123 5.456 *";
        expectedEvaluationResult = "11.583088";

        assertEquals(expectedEvaluationResult, Evaluator.evaluate(postfixExpression));

        postfixExpression = "2.123 5.456 /";
        expectedEvaluationResult = "0.3891129032";

        assertEquals(expectedEvaluationResult, Evaluator.evaluate(postfixExpression));
    }

    @Test
    @DisplayName("Same precedence operations")
    void evaluate4()
    {
        postfixExpression = "2 5 + 3 -";
        expectedEvaluationResult = "4";

        assertEquals(expectedEvaluationResult, Evaluator.evaluate(postfixExpression));

        postfixExpression = "2 5 * 3 *";
        expectedEvaluationResult = "30";

        assertEquals(expectedEvaluationResult, Evaluator.evaluate(postfixExpression));
    }

    @Test
    @DisplayName("Mixed precedence operations")
    void evaluate5()
    {
        postfixExpression = "2 5 3 * +";
        expectedEvaluationResult = "17";

        assertEquals(expectedEvaluationResult, Evaluator.evaluate(postfixExpression));

        postfixExpression = "2 5 * 3 +";
        expectedEvaluationResult = "13";

        assertEquals(expectedEvaluationResult, Evaluator.evaluate(postfixExpression));

        postfixExpression = "2 5 3 * + 4 +";
        expectedEvaluationResult = "21";

        assertEquals(expectedEvaluationResult, Evaluator.evaluate(postfixExpression));

        postfixExpression = "2 5 * 3 4 * +";
        expectedEvaluationResult = "22";

        assertEquals(expectedEvaluationResult, Evaluator.evaluate(postfixExpression));

        postfixExpression = "2 5 * 1 4 / - 5 +";
        expectedEvaluationResult = "14.75";

        assertEquals(expectedEvaluationResult, Evaluator.evaluate(postfixExpression));
    }

    @Test
    @DisplayName("Unary '+'")
    void evaluate6()
    {
        postfixExpression = "2 +5 -";
        expectedEvaluationResult = "-3";

        assertEquals(expectedEvaluationResult, Evaluator.evaluate(postfixExpression));
    }

    @Test
    @DisplayName("Unary '-'")
    void evaluate7()
    {
        postfixExpression = "2 -7 +";
        expectedEvaluationResult = "-5";

        assertEquals(expectedEvaluationResult, Evaluator.evaluate(postfixExpression));
    }

    private String postfixExpression;
    private String expectedEvaluationResult;
}
