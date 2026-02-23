package calculator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import validation.ValidationFailureReason;
import validation.ValidationResult;
import validation.Validator;

import static org.junit.jupiter.api.Assertions.*;

abstract class ValidatorTest
{
    protected String infixExpression;
    protected ValidationResult validationResult;
}

class ValidatorPositiveTest extends ValidatorTest
{
    @Test
    @DisplayName("Single number")
    void validateInfixExpression1()
    {
        infixExpression = "123";
        validationResult = Validator.validateInfixExpression(infixExpression);
        assertTrue(validationResult.isSuccess());

        infixExpression = "123.456";
        validationResult = Validator.validateInfixExpression(infixExpression);
        assertTrue(validationResult.isSuccess());
    }

    @Test
    @DisplayName("Simple expression")
    void validateInfixExpression2()
    {
        infixExpression = "123+456";
        validationResult = Validator.validateInfixExpression(infixExpression);
        assertTrue(validationResult.isSuccess());

        infixExpression = "123.456+789";
        validationResult = Validator.validateInfixExpression(infixExpression);
        assertTrue(validationResult.isSuccess());

        infixExpression = "123.456+789.012";
        validationResult = Validator.validateInfixExpression(infixExpression);
        assertTrue(validationResult.isSuccess());

        infixExpression = "123.456+456.789-123.456";
        validationResult = Validator.validateInfixExpression(infixExpression);
        assertTrue(validationResult.isSuccess());

        infixExpression = "123.456+456.789-123.456\u00D7532.123";
        validationResult = Validator.validateInfixExpression(infixExpression);
        assertTrue(validationResult.isSuccess());

        infixExpression = "123.456+456.789-123.456\u00F7532.123\u00D715";
        validationResult = Validator.validateInfixExpression(infixExpression);
        assertTrue(validationResult.isSuccess());

        infixExpression = "0.123456+456-123\u00F70.123\u00D715";
        validationResult = Validator.validateInfixExpression(infixExpression);
        assertTrue(validationResult.isSuccess());

        infixExpression = "0.123456+456.789-123.456\u00F70.123\u00D715";
        validationResult = Validator.validateInfixExpression(infixExpression);
        assertTrue(validationResult.isSuccess());
    }

    @Test
    @DisplayName("Acceptable sequence of 2 consecutive operators")
    void validateInfixExpression3()
    {
        infixExpression = "123++456";
        validationResult = Validator.validateInfixExpression(infixExpression);
        assertTrue(validationResult.isSuccess());

        infixExpression = "123+-456";
        validationResult = Validator.validateInfixExpression(infixExpression);
        assertTrue(validationResult.isSuccess());

        infixExpression = "123-+456";
        validationResult = Validator.validateInfixExpression(infixExpression);
        assertTrue(validationResult.isSuccess());

        infixExpression = "123--456";
        validationResult = Validator.validateInfixExpression(infixExpression);
        assertTrue(validationResult.isSuccess());

        infixExpression = "123\u00D7+456";
        validationResult = Validator.validateInfixExpression(infixExpression);
        assertTrue(validationResult.isSuccess());

        infixExpression = "123\u00D7-456";
        validationResult = Validator.validateInfixExpression(infixExpression);
        assertTrue(validationResult.isSuccess());

        infixExpression = "123\u00F7+456";
        validationResult = Validator.validateInfixExpression(infixExpression);
        assertTrue(validationResult.isSuccess());

        infixExpression = "123\u00F7-456";
        validationResult = Validator.validateInfixExpression(infixExpression);
        assertTrue(validationResult.isSuccess());
    }

    @Test
    @DisplayName("Unary '+' as the first character")
    void validateInfixExpression4()
    {
        infixExpression = "+123\u00D7456";
        validationResult = Validator.validateInfixExpression(infixExpression);
        assertTrue(validationResult.isSuccess());
    }

    @Test
    @DisplayName("Unary '-' as the first character")
    void validateInfixExpression5()
    {
        infixExpression = "-123\u00D7456";
        validationResult = Validator.validateInfixExpression(infixExpression);
        assertTrue(validationResult.isSuccess());
    }

    @Test
    @DisplayName("Balanced parentheses")
    void validateInfixExpression6()
    {
        infixExpression = "((5+3))";
        validationResult = Validator.validateInfixExpression(infixExpression);
        assertTrue(validationResult.isSuccess());

        infixExpression = "((5-2)+3)";
        validationResult = Validator.validateInfixExpression(infixExpression);
        assertTrue(validationResult.isSuccess());
    }
}

class ValidatorNegativeTest extends ValidatorTest
{
    @Test
    @DisplayName("3 operators next to each other")
    void validateInfixExpression1()
    {
        infixExpression = "123+-\u00D7456";
        validationResult = Validator.validateInfixExpression(infixExpression);
        assertFalse(validationResult.isSuccess());
        assertEquals(ValidationFailureReason.AnyThreeOrMoreConsecutiveOperators, validationResult.validationFailureReason());
    }

    @Test
    @DisplayName("Wrong sequence of 2 consecutive operators")
    void validateInfixExpression2()
    {
        infixExpression = "123+\u00D7456";
        validationResult = Validator.validateInfixExpression(infixExpression);
        assertFalse(validationResult.isSuccess());
        assertEquals(ValidationFailureReason.WrongTwoConsecutiveOperators, validationResult.validationFailureReason());

        infixExpression = "123+\u00F7456";
        validationResult = Validator.validateInfixExpression(infixExpression);
        assertFalse(validationResult.isSuccess());
        assertEquals(ValidationFailureReason.WrongTwoConsecutiveOperators, validationResult.validationFailureReason());

        infixExpression = "123-\u00D7456";
        validationResult = Validator.validateInfixExpression(infixExpression);
        assertFalse(validationResult.isSuccess());
        assertEquals(ValidationFailureReason.WrongTwoConsecutiveOperators, validationResult.validationFailureReason());

        infixExpression = "123-\u00F7456";
        validationResult = Validator.validateInfixExpression(infixExpression);
        assertFalse(validationResult.isSuccess());
        assertEquals(ValidationFailureReason.WrongTwoConsecutiveOperators, validationResult.validationFailureReason());

        infixExpression = "123\u00D7\u00D7456";
        validationResult = Validator.validateInfixExpression(infixExpression);
        assertFalse(validationResult.isSuccess());
        assertEquals(ValidationFailureReason.WrongTwoConsecutiveOperators, validationResult.validationFailureReason());

        infixExpression = "123\u00D7\u00F7456";
        validationResult = Validator.validateInfixExpression(infixExpression);
        assertFalse(validationResult.isSuccess());
        assertEquals(ValidationFailureReason.WrongTwoConsecutiveOperators, validationResult.validationFailureReason());

        infixExpression = "123\u00F7\u00D7456";
        validationResult = Validator.validateInfixExpression(infixExpression);
        assertFalse(validationResult.isSuccess());
        assertEquals(ValidationFailureReason.WrongTwoConsecutiveOperators, validationResult.validationFailureReason());

        infixExpression = "123\u00F7\u00F7456";
        validationResult = Validator.validateInfixExpression(infixExpression);
        assertFalse(validationResult.isSuccess());
        assertEquals(ValidationFailureReason.WrongTwoConsecutiveOperators, validationResult.validationFailureReason());
    }

    @Test
    @DisplayName("Two or more dots in a number")
    void validateInfixExpression3()
    {
        infixExpression = "123\u00D74.5.6";
        validationResult = Validator.validateInfixExpression(infixExpression);
        assertFalse(validationResult.isSuccess());
        assertEquals(ValidationFailureReason.TwoOrMoreDotsInNumber, validationResult.validationFailureReason());

        infixExpression = "123\u00D74...5.6";
        validationResult = Validator.validateInfixExpression(infixExpression);
        assertFalse(validationResult.isSuccess());
        assertEquals(ValidationFailureReason.TwoOrMoreDotsInNumber, validationResult.validationFailureReason());

        infixExpression = "123\u00D70...5...6";
        validationResult = Validator.validateInfixExpression(infixExpression);
        assertFalse(validationResult.isSuccess());
        assertEquals(ValidationFailureReason.TwoOrMoreDotsInNumber, validationResult.validationFailureReason());
    }

    @Test
    @DisplayName("Wrong operator as the first character of an expression")
    void validateInfixExpression4()
    {
        infixExpression = "\u00D7456";
        validationResult = Validator.validateInfixExpression(infixExpression);
        assertFalse(validationResult.isSuccess());
        assertEquals(ValidationFailureReason.WrongOperatorAsTheFirstCharacter, validationResult.validationFailureReason());

        infixExpression = "\u00F7456";
        validationResult = Validator.validateInfixExpression(infixExpression);
        assertFalse(validationResult.isSuccess());
        assertEquals(ValidationFailureReason.WrongOperatorAsTheFirstCharacter, validationResult.validationFailureReason());
    }

    @Test
    @DisplayName("Any operator as the last character of an expression")
    void validateInfixExpression5()
    {
        infixExpression = "123+";
        validationResult = Validator.validateInfixExpression(infixExpression);
        assertFalse(validationResult.isSuccess());
        assertEquals(ValidationFailureReason.OperatorIsTheLastCharacterInExpression, validationResult.validationFailureReason());

        infixExpression = "123.456+";
        validationResult = Validator.validateInfixExpression(infixExpression);
        assertFalse(validationResult.isSuccess());
        assertEquals(ValidationFailureReason.OperatorIsTheLastCharacterInExpression, validationResult.validationFailureReason());

        infixExpression = "123.456+456.789-";
        validationResult = Validator.validateInfixExpression(infixExpression);
        assertFalse(validationResult.isSuccess());
        assertEquals(ValidationFailureReason.OperatorIsTheLastCharacterInExpression, validationResult.validationFailureReason());

        infixExpression = "123.456+456.789-123.456+";
        validationResult = Validator.validateInfixExpression(infixExpression);
        assertFalse(validationResult.isSuccess());
        assertEquals(ValidationFailureReason.OperatorIsTheLastCharacterInExpression, validationResult.validationFailureReason());

        infixExpression = "0.123+";
        validationResult = Validator.validateInfixExpression(infixExpression);
        assertFalse(validationResult.isSuccess());
        assertEquals(ValidationFailureReason.OperatorIsTheLastCharacterInExpression, validationResult.validationFailureReason());
    }

    @Test
    @DisplayName("Dot as the first character")
    void validateInfixExpression6()
    {
        infixExpression = ".";
        validationResult = Validator.validateInfixExpression(infixExpression);
        assertFalse(validationResult.isSuccess());
        assertEquals(ValidationFailureReason.DotIsTheFirstCharacterInExpression, validationResult.validationFailureReason());

        infixExpression = ".123";
        validationResult = Validator.validateInfixExpression(infixExpression);
        assertFalse(validationResult.isSuccess());
        assertEquals(ValidationFailureReason.DotIsTheFirstCharacterInExpression, validationResult.validationFailureReason());

        infixExpression = ".+123";
        validationResult = Validator.validateInfixExpression(infixExpression);
        assertFalse(validationResult.isSuccess());
        assertEquals(ValidationFailureReason.DotIsTheFirstCharacterInExpression, validationResult.validationFailureReason());
    }

    @Test
    @DisplayName("Dot as the last character of an expression")
    void validateInfixExpression7()
    {
        infixExpression = "123\u00D7456.";
        validationResult = Validator.validateInfixExpression(infixExpression);
        assertFalse(validationResult.isSuccess());
        assertEquals(ValidationFailureReason.DotIsTheLastCharacterInExpression, validationResult.validationFailureReason());
    }

    @Test
    @DisplayName("Dot without integer part")
    void validateInfixExpression8()
    {
        infixExpression = "5+.123";
        validationResult = Validator.validateInfixExpression(infixExpression);
        assertFalse(validationResult.isSuccess());
        assertEquals(ValidationFailureReason.DotWithoutIntegerPart, validationResult.validationFailureReason());

        infixExpression = "5+.123+7";
        validationResult = Validator.validateInfixExpression(infixExpression);
        assertFalse(validationResult.isSuccess());
        assertEquals(ValidationFailureReason.DotWithoutIntegerPart, validationResult.validationFailureReason());

        infixExpression = "(.123)";
        validationResult = Validator.validateInfixExpression(infixExpression);
        assertFalse(validationResult.isSuccess());
        assertEquals(ValidationFailureReason.DotWithoutIntegerPart, validationResult.validationFailureReason());

        infixExpression = "5+(.123)";
        validationResult = Validator.validateInfixExpression(infixExpression);
        assertFalse(validationResult.isSuccess());
        assertEquals(ValidationFailureReason.DotWithoutIntegerPart, validationResult.validationFailureReason());
    }

    @Test
    @DisplayName("Dot without fractional part")
    void validateInfixExpression9()
    {
        infixExpression = "123+456.+7";
        validationResult = Validator.validateInfixExpression(infixExpression);
        assertFalse(validationResult.isSuccess());
        assertEquals(ValidationFailureReason.DotWithoutFractionalPart, validationResult.validationFailureReason());

        infixExpression = "456.+123";
        validationResult = Validator.validateInfixExpression(infixExpression);
        assertFalse(validationResult.isSuccess());
        assertEquals(ValidationFailureReason.DotWithoutFractionalPart, validationResult.validationFailureReason());

        infixExpression = "123.)";
        validationResult = Validator.validateInfixExpression(infixExpression);
        assertFalse(validationResult.isSuccess());
        assertEquals(ValidationFailureReason.DotWithoutFractionalPart, validationResult.validationFailureReason());

        infixExpression = "(123.)";
        validationResult = Validator.validateInfixExpression(infixExpression);
        assertFalse(validationResult.isSuccess());
        assertEquals(ValidationFailureReason.DotWithoutFractionalPart, validationResult.validationFailureReason());
    }

    @Test
    @DisplayName("Empty parentheses")
    void validateInfixExpression10()
    {
        infixExpression = "()";
        validationResult = Validator.validateInfixExpression(infixExpression);
        assertFalse(validationResult.isSuccess());
        assertEquals(ValidationFailureReason.EmptyParentheses, validationResult.validationFailureReason());

        infixExpression = "5\u00D7()";
        validationResult = Validator.validateInfixExpression(infixExpression);
        assertFalse(validationResult.isSuccess());
        assertEquals(ValidationFailureReason.EmptyParentheses, validationResult.validationFailureReason());

        infixExpression = "()\u00D75";
        validationResult = Validator.validateInfixExpression(infixExpression);
        assertFalse(validationResult.isSuccess());
        assertEquals(ValidationFailureReason.EmptyParentheses, validationResult.validationFailureReason());
    }

    @Test
    @DisplayName("Left parentheses preceded by a number")
    void validateInfixExpression11()
    {
        infixExpression = "4(5)";
        validationResult = Validator.validateInfixExpression(infixExpression);
        assertFalse(validationResult.isSuccess());
        assertEquals(ValidationFailureReason.LeftParenthesesPrecededByNumber, validationResult.validationFailureReason());

        infixExpression = "123+5(3)";
        validationResult = Validator.validateInfixExpression(infixExpression);
        assertFalse(validationResult.isSuccess());
        assertEquals(ValidationFailureReason.LeftParenthesesPrecededByNumber, validationResult.validationFailureReason());
    }

    @Test
    @DisplayName("Right parentheses followed by a number")
    void validateInfixExpression12()
    {
        infixExpression = "(5)4";
        validationResult = Validator.validateInfixExpression(infixExpression);
        assertFalse(validationResult.isSuccess());
        assertEquals(ValidationFailureReason.RightParenthesesFollowedByNumber, validationResult.validationFailureReason());

        infixExpression = "4+(5)6";
        validationResult = Validator.validateInfixExpression(infixExpression);
        assertFalse(validationResult.isSuccess());
        assertEquals(ValidationFailureReason.RightParenthesesFollowedByNumber, validationResult.validationFailureReason());
    }

    @Test
    @DisplayName("Unbalanced parentheses")
    void validateInfixExpression13()
    {
        infixExpression = "(5+3))";
        validationResult = Validator.validateInfixExpression(infixExpression);
        assertFalse(validationResult.isSuccess());
        assertEquals(ValidationFailureReason.UnbalancedParentheses, validationResult.validationFailureReason());

        infixExpression = "((5+3)";
        validationResult = Validator.validateInfixExpression(infixExpression);
        assertFalse(validationResult.isSuccess());
        assertEquals(ValidationFailureReason.UnbalancedParentheses, validationResult.validationFailureReason());
    }

    @Test
    @DisplayName("Wrong parentheses order")
    void validateInfixExpression14()
    {
        infixExpression = ")(";
        validationResult = Validator.validateInfixExpression(infixExpression);
        assertFalse(validationResult.isSuccess());
        assertEquals(ValidationFailureReason.UnbalancedParentheses, validationResult.validationFailureReason());

        infixExpression = ")(5+3)";
        validationResult = Validator.validateInfixExpression(infixExpression);
        assertFalse(validationResult.isSuccess());
        assertEquals(ValidationFailureReason.UnbalancedParentheses, validationResult.validationFailureReason());

        infixExpression = "5+3)(";
        validationResult = Validator.validateInfixExpression(infixExpression);
        assertFalse(validationResult.isSuccess());
        assertEquals(ValidationFailureReason.UnbalancedParentheses, validationResult.validationFailureReason());

        infixExpression = "(5+3))(";
        validationResult = Validator.validateInfixExpression(infixExpression);
        assertFalse(validationResult.isSuccess());
        assertEquals(ValidationFailureReason.UnbalancedParentheses, validationResult.validationFailureReason());
    }
}
