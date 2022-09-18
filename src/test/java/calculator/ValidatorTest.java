package calculator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

abstract class ValidatorTest
{
    String infixExpression;
}

class ValidatorPositiveTest extends ValidatorTest
{
    @Test
    @DisplayName("Single Integer")
    void validateInfixExpression1()
    {
        infixExpression = "123";
        assertTrue(Validator.validateInfixExpression(infixExpression));
    }

    @Test
    @DisplayName("Single Float")
    void validateInfixExpression2()
    {
        infixExpression = "123.456";
        assertTrue(Validator.validateInfixExpression(infixExpression));
    }

    @Test
    @DisplayName("Integer + Integer")
    void validateInfixExpression3()
    {
        infixExpression = "123+456";
        assertTrue(Validator.validateInfixExpression(infixExpression));
    }

    @Test
    @DisplayName("Float + Integer")
    void validateInfixExpression4()
    {
        infixExpression = "123.456+789";
        assertTrue(Validator.validateInfixExpression(infixExpression));
    }

    @Test
    @DisplayName("Float + Float")
    void validateInfixExpression5()
    {
        infixExpression = "123.456+789.012";
        assertTrue(Validator.validateInfixExpression(infixExpression));
    }

    @Test
    @DisplayName("Float + Float - Float")
    void validateInfixExpression6()
    {
        infixExpression = "123.456+456.789-123.456";
        assertTrue(Validator.validateInfixExpression(infixExpression));
    }

    @Test
    @DisplayName("Float + Float - Float*Float")
    void validateInfixExpression7()
    {
        infixExpression = "123.456+456.789-123.456*532.123";
        assertTrue(Validator.validateInfixExpression(infixExpression));
    }

    @Test
    @DisplayName("Float + Float - Float / Float")
    void validateInfixExpression8()
    {
        infixExpression = "123.456+456.789-123.456/532.123*15";
        assertTrue(Validator.validateInfixExpression(infixExpression));
    }

    @Test
    @DisplayName("0.Float + Integer - Integer / 0.Float * Integer")
    void validateInfixExpression9()
    {
        infixExpression = "0.123456+456-123/0.123*15";
        assertTrue(Validator.validateInfixExpression(infixExpression));
    }

    @Test
    @DisplayName("0.Float + Float - Float / 0.Float * Integer")
    void validateInfixExpression10()
    {
        infixExpression = "0.123456+456.789-123.456/0.123*15";
        assertTrue(Validator.validateInfixExpression(infixExpression));
    }

    @Test
    @DisplayName("Acceptable sequence of 2 consecutive operators")
    void validateInfixExpression11()
    {
        infixExpression = "123++456";
        assertTrue(Validator.validateInfixExpression(infixExpression));

        infixExpression = "123+-456";
        assertTrue(Validator.validateInfixExpression(infixExpression));

        infixExpression = "123-+456";
        assertTrue(Validator.validateInfixExpression(infixExpression));

        infixExpression = "123--456";
        assertTrue(Validator.validateInfixExpression(infixExpression));

        infixExpression = "123*+456";
        assertTrue(Validator.validateInfixExpression(infixExpression));

        infixExpression = "123*-456";
        assertTrue(Validator.validateInfixExpression(infixExpression));

        infixExpression = "123/+456";
        assertTrue(Validator.validateInfixExpression(infixExpression));

        infixExpression = "123/-456";
        assertTrue(Validator.validateInfixExpression(infixExpression));
    }

    @Test
    @DisplayName("Unary '+' as the first character")
    void validateInfixExpression12()
    {
        infixExpression = "+123*456";
        assertTrue(Validator.validateInfixExpression(infixExpression));
    }

    @Test
    @DisplayName("Unary '-' as the first character")
    void validateInfixExpression13()
    {
        infixExpression = "-123*456";
        assertTrue(Validator.validateInfixExpression(infixExpression));
    }
}

class ValidatorNegativeTest extends ValidatorTest
{
    @Test
    @DisplayName("Integer and operator as the last character")
    void validateInfixExpression1()
    {
        infixExpression = "123+";
        assertFalse(Validator.validateInfixExpression(infixExpression));
    }

    @Test
    @DisplayName("Float and operator as the last character")
    void validateInfixExpression2()
    {
        infixExpression = "123.456+";
        assertFalse(Validator.validateInfixExpression(infixExpression));
    }

    @Test
    @DisplayName("Float + Float and operator as the last character")
    void validateInfixExpression3()
    {
        infixExpression = "123.456+456.789-";
        assertFalse(Validator.validateInfixExpression(infixExpression));
    }

    @Test
    @DisplayName("Float + Float - Float and operator as the last character")
    void validateInfixExpression4()
    {
        infixExpression = "123.456+456.789-123.456+";
        assertFalse(Validator.validateInfixExpression(infixExpression));
    }

    @Test
    @DisplayName("Single dot")
    void validateInfixExpression5()
    {
        infixExpression = ".";
        assertFalse(Validator.validateInfixExpression(infixExpression));
    }

    @Test
    @DisplayName("Dot followed by number")
    void validateInfixExpression6()
    {
        infixExpression = ".123";
        assertFalse(Validator.validateInfixExpression(infixExpression));

        infixExpression = "5+.123";
        assertFalse(Validator.validateInfixExpression(infixExpression));
    }

    @Test
    @DisplayName("Dot followed by number and operator")
    void validateInfixExpression7()
    {
        infixExpression = ".123+";
        assertFalse(Validator.validateInfixExpression(infixExpression));

        infixExpression = "5+.123+";
        assertFalse(Validator.validateInfixExpression(infixExpression));
    }

    @Test
    @DisplayName("0.Float and operator as last character")
    void validateInfixExpression8()
    {
        infixExpression = "0.123+";
        assertFalse(Validator.validateInfixExpression(infixExpression));
    }

    @Test
    @DisplayName("Integer + 0.Float")
    void validateInfixExpression9()
    {
        infixExpression = "123+.456";
        assertFalse(Validator.validateInfixExpression(infixExpression));
    }

    @Test
    @DisplayName("Wrong sequence of 2 consecutive operators")
    void validateInfixExpression10()
    {
        infixExpression = "123+*456";
        assertFalse(Validator.validateInfixExpression(infixExpression));

        infixExpression = "123+/456";
        assertFalse(Validator.validateInfixExpression(infixExpression));

        infixExpression = "123-*456";
        assertFalse(Validator.validateInfixExpression(infixExpression));

        infixExpression = "123-/456";
        assertFalse(Validator.validateInfixExpression(infixExpression));

        infixExpression = "123**456";
        assertFalse(Validator.validateInfixExpression(infixExpression));

        infixExpression = "123*/456";
        assertFalse(Validator.validateInfixExpression(infixExpression));

        infixExpression = "123/*456";
        assertFalse(Validator.validateInfixExpression(infixExpression));

        infixExpression = "123//456";
        assertFalse(Validator.validateInfixExpression(infixExpression));
    }

    @Test
    @DisplayName("3 operators next to each other")
    void validateInfixExpression11()
    {
        infixExpression = "123+-*456";
        assertFalse(Validator.validateInfixExpression(infixExpression));
    }

    @Test
    @DisplayName("Double dot")
    void validateInfixExpression14()
    {
        infixExpression = "123*4.5.6";
        assertFalse(Validator.validateInfixExpression(infixExpression));

        infixExpression = "123*4...5.6";
        assertFalse(Validator.validateInfixExpression(infixExpression));

        infixExpression = "123*0...5...6";
        assertFalse(Validator.validateInfixExpression(infixExpression));
    }

    @Test
    @DisplayName("Dot as the last character")
    void validateInfixExpression15()
    {
        infixExpression = "123*456.";
        assertFalse(Validator.validateInfixExpression(infixExpression));
    }

    @Test
    @DisplayName("Dot without fractional part")
    void validateInfixExpression16()
    {
        infixExpression = "456.+123";
        assertFalse(Validator.validateInfixExpression(infixExpression));
    }

    @Test
    @DisplayName("Multiplication and division operator as the first character")
    void validateInfixExpression17()
    {
        infixExpression = "*456";
        assertFalse(Validator.validateInfixExpression(infixExpression));

        infixExpression = "/456";
        assertFalse(Validator.validateInfixExpression(infixExpression));
    }
}
