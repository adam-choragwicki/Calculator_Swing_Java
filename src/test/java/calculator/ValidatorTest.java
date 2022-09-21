package calculator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

abstract class ValidatorTest
{
    protected String infixExpression;
}

class ValidatorPositiveTest extends ValidatorTest
{
    @Test
    @DisplayName("Single number")
    void validateInfixExpression1()
    {
        infixExpression = "123";
        assertTrue(Validator.validateInfixExpression(infixExpression).isSuccess());

        infixExpression = "123.456";
        assertTrue(Validator.validateInfixExpression(infixExpression).isSuccess());
    }

    @Test
    @DisplayName("Simple expression")
    void validateInfixExpression2()
    {
        infixExpression = "123+456";
        assertTrue(Validator.validateInfixExpression(infixExpression).isSuccess());

        infixExpression = "123.456+789";
        assertTrue(Validator.validateInfixExpression(infixExpression).isSuccess());

        infixExpression = "123.456+789.012";
        assertTrue(Validator.validateInfixExpression(infixExpression).isSuccess());

        infixExpression = "123.456+456.789-123.456";
        assertTrue(Validator.validateInfixExpression(infixExpression).isSuccess());

        infixExpression = "123.456+456.789-123.456*532.123";
        assertTrue(Validator.validateInfixExpression(infixExpression).isSuccess());

        infixExpression = "123.456+456.789-123.456/532.123*15";
        assertTrue(Validator.validateInfixExpression(infixExpression).isSuccess());

        infixExpression = "0.123456+456-123/0.123*15";
        assertTrue(Validator.validateInfixExpression(infixExpression).isSuccess());

        infixExpression = "0.123456+456.789-123.456/0.123*15";
        assertTrue(Validator.validateInfixExpression(infixExpression).isSuccess());
    }

    @Test
    @DisplayName("Acceptable sequence of 2 consecutive operators")
    void validateInfixExpression3()
    {
        infixExpression = "123++456";
        assertTrue(Validator.validateInfixExpression(infixExpression).isSuccess());

        infixExpression = "123+-456";
        assertTrue(Validator.validateInfixExpression(infixExpression).isSuccess());

        infixExpression = "123-+456";
        assertTrue(Validator.validateInfixExpression(infixExpression).isSuccess());

        infixExpression = "123--456";
        assertTrue(Validator.validateInfixExpression(infixExpression).isSuccess());

        infixExpression = "123*+456";
        assertTrue(Validator.validateInfixExpression(infixExpression).isSuccess());

        infixExpression = "123*-456";
        assertTrue(Validator.validateInfixExpression(infixExpression).isSuccess());

        infixExpression = "123/+456";
        assertTrue(Validator.validateInfixExpression(infixExpression).isSuccess());

        infixExpression = "123/-456";
        assertTrue(Validator.validateInfixExpression(infixExpression).isSuccess());
    }

    @Test
    @DisplayName("Unary '+' as the first character")
    void validateInfixExpression4()
    {
        infixExpression = "+123*456";
        assertTrue(Validator.validateInfixExpression(infixExpression).isSuccess());
    }

    @Test
    @DisplayName("Unary '-' as the first character")
    void validateInfixExpression5()
    {
        infixExpression = "-123*456";
        assertTrue(Validator.validateInfixExpression(infixExpression).isSuccess());
    }

    @Test
    @DisplayName("Balanced parentheses")
    void validateInfixExpression6()
    {
        infixExpression = "((5+3))";
        assertTrue(Validator.validateInfixExpression(infixExpression).isSuccess());

        infixExpression = "((5-2)+3)";
        assertTrue(Validator.validateInfixExpression(infixExpression).isSuccess());
    }
}

class ValidatorNegativeTest extends ValidatorTest
{
    @Test
    @DisplayName("3 operators next to each other")
    void validateInfixExpression1()
    {
        infixExpression = "123+-*456";
        assertFalse(Validator.validateInfixExpression(infixExpression).isSuccess());
        assertEquals(FailReason.AnyThreeOrMoreConsecutiveOperators, Validator.validateInfixExpression(infixExpression).getFailReason());
    }

    @Test
    @DisplayName("Wrong sequence of 2 consecutive operators")
    void validateInfixExpression2()
    {
        infixExpression = "123+*456";
        assertFalse(Validator.validateInfixExpression(infixExpression).isSuccess());
        assertEquals(FailReason.WrongTwoConsecutiveOperators, Validator.validateInfixExpression(infixExpression).getFailReason());

        infixExpression = "123+/456";
        assertFalse(Validator.validateInfixExpression(infixExpression).isSuccess());
        assertEquals(FailReason.WrongTwoConsecutiveOperators, Validator.validateInfixExpression(infixExpression).getFailReason());

        infixExpression = "123-*456";
        assertFalse(Validator.validateInfixExpression(infixExpression).isSuccess());
        assertEquals(FailReason.WrongTwoConsecutiveOperators, Validator.validateInfixExpression(infixExpression).getFailReason());

        infixExpression = "123-/456";
        assertFalse(Validator.validateInfixExpression(infixExpression).isSuccess());
        assertEquals(FailReason.WrongTwoConsecutiveOperators, Validator.validateInfixExpression(infixExpression).getFailReason());

        infixExpression = "123**456";
        assertFalse(Validator.validateInfixExpression(infixExpression).isSuccess());
        assertEquals(FailReason.WrongTwoConsecutiveOperators, Validator.validateInfixExpression(infixExpression).getFailReason());

        infixExpression = "123*/456";
        assertFalse(Validator.validateInfixExpression(infixExpression).isSuccess());
        assertEquals(FailReason.WrongTwoConsecutiveOperators, Validator.validateInfixExpression(infixExpression).getFailReason());

        infixExpression = "123/*456";
        assertFalse(Validator.validateInfixExpression(infixExpression).isSuccess());
        assertEquals(FailReason.WrongTwoConsecutiveOperators, Validator.validateInfixExpression(infixExpression).getFailReason());

        infixExpression = "123//456";
        assertFalse(Validator.validateInfixExpression(infixExpression).isSuccess());
        assertEquals(FailReason.WrongTwoConsecutiveOperators, Validator.validateInfixExpression(infixExpression).getFailReason());
    }

    @Test
    @DisplayName("Two or more dots in a number")
    void validateInfixExpression3()
    {
        infixExpression = "123*4.5.6";
        assertFalse(Validator.validateInfixExpression(infixExpression).isSuccess());
        assertEquals(FailReason.TwoOrMoreDotsInNumber, Validator.validateInfixExpression(infixExpression).getFailReason());

        infixExpression = "123*4...5.6";
        assertFalse(Validator.validateInfixExpression(infixExpression).isSuccess());
        assertEquals(FailReason.TwoOrMoreDotsInNumber, Validator.validateInfixExpression(infixExpression).getFailReason());

        infixExpression = "123*0...5...6";
        assertFalse(Validator.validateInfixExpression(infixExpression).isSuccess());
        assertEquals(FailReason.TwoOrMoreDotsInNumber, Validator.validateInfixExpression(infixExpression).getFailReason());
    }

    @Test
    @DisplayName("Wrong operator as the first character of an expression")
    void validateInfixExpression4()
    {
        infixExpression = "*456";
        assertFalse(Validator.validateInfixExpression(infixExpression).isSuccess());
        assertFalse(Validator.validateInfixExpression(infixExpression).isSuccess());
        assertEquals(FailReason.WrongOperatorAsTheFirstCharacter, Validator.validateInfixExpression(infixExpression).getFailReason());

        infixExpression = "/456";
        assertFalse(Validator.validateInfixExpression(infixExpression).isSuccess());
        assertEquals(FailReason.WrongOperatorAsTheFirstCharacter, Validator.validateInfixExpression(infixExpression).getFailReason());
    }

    @Test
    @DisplayName("Any operator as the last character of an expression")
    void validateInfixExpression5()
    {
        infixExpression = "123+";
        assertFalse(Validator.validateInfixExpression(infixExpression).isSuccess());
        assertEquals(FailReason.OperatorIsTheLastCharacterInExpression, Validator.validateInfixExpression(infixExpression).getFailReason());

        infixExpression = "123.456+";
        assertFalse(Validator.validateInfixExpression(infixExpression).isSuccess());
        assertEquals(FailReason.OperatorIsTheLastCharacterInExpression, Validator.validateInfixExpression(infixExpression).getFailReason());

        infixExpression = "123.456+456.789-";
        assertFalse(Validator.validateInfixExpression(infixExpression).isSuccess());
        assertEquals(FailReason.OperatorIsTheLastCharacterInExpression, Validator.validateInfixExpression(infixExpression).getFailReason());

        infixExpression = "123.456+456.789-123.456+";
        assertFalse(Validator.validateInfixExpression(infixExpression).isSuccess());
        assertEquals(FailReason.OperatorIsTheLastCharacterInExpression, Validator.validateInfixExpression(infixExpression).getFailReason());

        infixExpression = "0.123+";
        assertFalse(Validator.validateInfixExpression(infixExpression).isSuccess());
        assertEquals(FailReason.OperatorIsTheLastCharacterInExpression, Validator.validateInfixExpression(infixExpression).getFailReason());
    }

    @Test
    @DisplayName("Dot as the first character")
    void validateInfixExpression6()
    {
        infixExpression = ".";
        assertFalse(Validator.validateInfixExpression(infixExpression).isSuccess());
        assertEquals(FailReason.DotIsTheFirstCharacterInExpression, Validator.validateInfixExpression(infixExpression).getFailReason());

        infixExpression = ".123";
        assertFalse(Validator.validateInfixExpression(infixExpression).isSuccess());
        assertEquals(FailReason.DotIsTheFirstCharacterInExpression, Validator.validateInfixExpression(infixExpression).getFailReason());

        infixExpression = ".+123";
        assertFalse(Validator.validateInfixExpression(infixExpression).isSuccess());
        assertEquals(FailReason.DotIsTheFirstCharacterInExpression, Validator.validateInfixExpression(infixExpression).getFailReason());
    }

    @Test
    @DisplayName("Dot as the last character of an expression")
    void validateInfixExpression7()
    {
        infixExpression = "123*456.";
        assertFalse(Validator.validateInfixExpression(infixExpression).isSuccess());
        assertEquals(FailReason.DotIsTheLastCharacterInExpression, Validator.validateInfixExpression(infixExpression).getFailReason());
    }

    @Test
    @DisplayName("Dot without integer part")
    void validateInfixExpression8()
    {
        infixExpression = "5+.123";
        assertFalse(Validator.validateInfixExpression(infixExpression).isSuccess());
        assertEquals(FailReason.DotWithoutIntegerPart, Validator.validateInfixExpression(infixExpression).getFailReason());

        infixExpression = "5+.123+7";
        assertFalse(Validator.validateInfixExpression(infixExpression).isSuccess());
        assertEquals(FailReason.DotWithoutIntegerPart, Validator.validateInfixExpression(infixExpression).getFailReason());
    }

    @Test
    @DisplayName("Dot without fractional part")
    void validateInfixExpression9()
    {
        infixExpression = "123+456.+7";
        assertFalse(Validator.validateInfixExpression(infixExpression).isSuccess());
        assertEquals(FailReason.DotWithoutFractionalPart, Validator.validateInfixExpression(infixExpression).getFailReason());

        infixExpression = "456.+123";
        assertFalse(Validator.validateInfixExpression(infixExpression).isSuccess());
        assertEquals(FailReason.DotWithoutFractionalPart, Validator.validateInfixExpression(infixExpression).getFailReason());
    }

    @Test
    @DisplayName("Empty parentheses")
    void validateInfixExpression10()
    {
        infixExpression = "()";
        assertFalse(Validator.validateInfixExpression(infixExpression).isSuccess());
        assertEquals(FailReason.EmptyParentheses, Validator.validateInfixExpression(infixExpression).getFailReason());

        infixExpression = "5*()";
        assertFalse(Validator.validateInfixExpression(infixExpression).isSuccess());
        assertEquals(FailReason.EmptyParentheses, Validator.validateInfixExpression(infixExpression).getFailReason());

        infixExpression = "()*5";
        assertFalse(Validator.validateInfixExpression(infixExpression).isSuccess());
        assertEquals(FailReason.EmptyParentheses, Validator.validateInfixExpression(infixExpression).getFailReason());
    }

    @Test
    @DisplayName("Left parentheses preceded by a number")
    void validateInfixExpression11()
    {
        infixExpression = "4(5)";
        assertFalse(Validator.validateInfixExpression(infixExpression).isSuccess());
        assertEquals(FailReason.LeftParenthesesPrecededByNumber, Validator.validateInfixExpression(infixExpression).getFailReason());

        infixExpression = "123+5(3)";
        assertFalse(Validator.validateInfixExpression(infixExpression).isSuccess());
        assertEquals(FailReason.LeftParenthesesPrecededByNumber, Validator.validateInfixExpression(infixExpression).getFailReason());
    }

    @Test
    @DisplayName("Right parentheses followed by a number")
    void validateInfixExpression12()
    {
        infixExpression = "(5)4";
        assertFalse(Validator.validateInfixExpression(infixExpression).isSuccess());
        assertEquals(FailReason.RightParenthesesFollowedByNumber, Validator.validateInfixExpression(infixExpression).getFailReason());

        infixExpression = "4+(5)6";
        assertFalse(Validator.validateInfixExpression(infixExpression).isSuccess());
        assertEquals(FailReason.RightParenthesesFollowedByNumber, Validator.validateInfixExpression(infixExpression).getFailReason());
    }

    @Test
    @DisplayName("Unbalanced parentheses")
    void validateInfixExpression13()
    {
        infixExpression = "(5+3))";
        assertFalse(Validator.validateInfixExpression(infixExpression).isSuccess());
        assertEquals(FailReason.UnbalancedParentheses, Validator.validateInfixExpression(infixExpression).getFailReason());

        infixExpression = "((5+3)";
        assertFalse(Validator.validateInfixExpression(infixExpression).isSuccess());
        assertEquals(FailReason.UnbalancedParentheses, Validator.validateInfixExpression(infixExpression).getFailReason());
    }
}
