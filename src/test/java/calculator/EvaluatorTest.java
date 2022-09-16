package calculator;

import evaluation.EvaluationResult;
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

        evaluationResult = Evaluator.evaluate(postfixExpression);
        assertTrue(evaluationResult.isSuccess());
        assertEquals(expectedEvaluationResult, evaluationResult.toString());

        postfixExpression = "-5";
        expectedEvaluationResult = "-5";

        evaluationResult = Evaluator.evaluate(postfixExpression);
        assertTrue(evaluationResult.isSuccess());
        assertEquals(expectedEvaluationResult, evaluationResult.toString());
    }

    @Test
    @DisplayName("Integer operations")
    void evaluate2()
    {
        postfixExpression = "2 5 +";
        expectedEvaluationResult = "7";

        evaluationResult = Evaluator.evaluate(postfixExpression);
        assertTrue(evaluationResult.isSuccess());
        assertEquals(expectedEvaluationResult, evaluationResult.toString());

        postfixExpression = "2 5 -";
        expectedEvaluationResult = "-3";

        evaluationResult = Evaluator.evaluate(postfixExpression);
        assertTrue(evaluationResult.isSuccess());
        assertEquals(expectedEvaluationResult, evaluationResult.toString());

        postfixExpression = "2 5 \u00D7";
        expectedEvaluationResult = "10";

        evaluationResult = Evaluator.evaluate(postfixExpression);
        assertTrue(evaluationResult.isSuccess());
        assertEquals(expectedEvaluationResult, evaluationResult.toString());

        postfixExpression = "2 5 \u00F7";
        expectedEvaluationResult = "0.4";

        evaluationResult = Evaluator.evaluate(postfixExpression);
        assertTrue(evaluationResult.isSuccess());
        assertEquals(expectedEvaluationResult, evaluationResult.toString());
    }

    @Test
    @DisplayName("Float operations")
    void evaluate3()
    {
        postfixExpression = "2.123 5.456 +";
        expectedEvaluationResult = "7.579";

        evaluationResult = Evaluator.evaluate(postfixExpression);
        assertTrue(evaluationResult.isSuccess());
        assertEquals(expectedEvaluationResult, evaluationResult.toString());

        postfixExpression = "2.123 5.456 -";
        expectedEvaluationResult = "-3.333";

        evaluationResult = Evaluator.evaluate(postfixExpression);
        assertTrue(evaluationResult.isSuccess());
        assertEquals(expectedEvaluationResult, evaluationResult.toString());

        postfixExpression = "2.123 5.456 \u00D7";
        expectedEvaluationResult = "11.583088";

        evaluationResult = Evaluator.evaluate(postfixExpression);
        assertTrue(evaluationResult.isSuccess());
        assertEquals(expectedEvaluationResult, evaluationResult.toString());

        postfixExpression = "2.123 5.456 \u00F7";
        expectedEvaluationResult = "0.3891129032";

        evaluationResult = Evaluator.evaluate(postfixExpression);
        assertTrue(evaluationResult.isSuccess());
        assertEquals(expectedEvaluationResult, evaluationResult.toString());
    }

    @Test
    @DisplayName("Same precedence operations")
    void evaluate4()
    {
        postfixExpression = "2 5 + 3 -";
        expectedEvaluationResult = "4";

        evaluationResult = Evaluator.evaluate(postfixExpression);
        assertTrue(evaluationResult.isSuccess());
        assertEquals(expectedEvaluationResult, evaluationResult.toString());

        postfixExpression = "2 5 \u00D7 3 \u00D7";
        expectedEvaluationResult = "30";

        evaluationResult = Evaluator.evaluate(postfixExpression);
        assertTrue(evaluationResult.isSuccess());
        assertEquals(expectedEvaluationResult, evaluationResult.toString());
    }

    @Test
    @DisplayName("Mixed precedence operations")
    void evaluate5()
    {
        postfixExpression = "2 5 3 \u00D7 +";
        expectedEvaluationResult = "17";

        evaluationResult = Evaluator.evaluate(postfixExpression);
        assertTrue(evaluationResult.isSuccess());
        assertEquals(expectedEvaluationResult, evaluationResult.toString());

        postfixExpression = "2 5 \u00D7 3 +";
        expectedEvaluationResult = "13";

        evaluationResult = Evaluator.evaluate(postfixExpression);
        assertTrue(evaluationResult.isSuccess());
        assertEquals(expectedEvaluationResult, evaluationResult.toString());

        postfixExpression = "2 5 3 \u00D7 + 4 +";
        expectedEvaluationResult = "21";

        evaluationResult = Evaluator.evaluate(postfixExpression);
        assertTrue(evaluationResult.isSuccess());
        assertEquals(expectedEvaluationResult, evaluationResult.toString());

        postfixExpression = "2 5 \u00D7 3 4 \u00D7 +";
        expectedEvaluationResult = "22";

        evaluationResult = Evaluator.evaluate(postfixExpression);
        assertTrue(evaluationResult.isSuccess());
        assertEquals(expectedEvaluationResult, evaluationResult.toString());

        postfixExpression = "2 5 \u00D7 1 4 \u00F7 - 5 +";
        expectedEvaluationResult = "14.75";

        evaluationResult = Evaluator.evaluate(postfixExpression);
        assertTrue(evaluationResult.isSuccess());
        assertEquals(expectedEvaluationResult, evaluationResult.toString());
    }

    @Test
    @DisplayName("Unary '+'")
    void evaluate6()
    {
        postfixExpression = "2 +5 -";
        expectedEvaluationResult = "-3";

        evaluationResult = Evaluator.evaluate(postfixExpression);
        assertTrue(evaluationResult.isSuccess());
        assertEquals(expectedEvaluationResult, evaluationResult.toString());
    }

    @Test
    @DisplayName("Unary '-'")
    void evaluate7()
    {
        postfixExpression = "2 -7 +";
        expectedEvaluationResult = "-5";

        evaluationResult = Evaluator.evaluate(postfixExpression);
        assertTrue(evaluationResult.isSuccess());
        assertEquals(expectedEvaluationResult, evaluationResult.toString());
    }

    @Test
    @DisplayName("Division by zero")
    void evaluate8()
    {
        postfixExpression = "2 0 \u00F7";
        expectedEvaluationResult = null;

        evaluationResult = Evaluator.evaluate(postfixExpression);
        assertFalse(evaluationResult.isSuccess());
        assertEquals(expectedEvaluationResult, evaluationResult.toString());
    }

    private String postfixExpression;
    private String expectedEvaluationResult;
    private EvaluationResult evaluationResult;
}
