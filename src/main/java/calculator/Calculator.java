package calculator;

import javax.swing.*;
import java.awt.*;

class Calculator extends JFrame
{
    Calculator()
    {
        super("Calculator");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        addComponents();

        setSize(400, 400);
        setMinimumSize(getSize());
        setVisible(true);
    }

    private void addComponents()
    {
        addLabel();
        addButtons();
    }

    private void addLabel()
    {
        labelEquation = new JLabel("0");
        labelEquation.setAlignmentX(Component.RIGHT_ALIGNMENT);
        labelEquation.setFont(Config.labelResultFont);
        add(labelEquation);
    }

    private void addButtons()
    {
        JPanel buttonsPanel = new JPanel(new GridLayout(5, 4, 10, 10));

        buttonsPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);

        CharacterButton buttonLeftParentheses = new CharacterButton(String.valueOf(Config.leftParentheses));
        buttonsPanel.add(buttonLeftParentheses);

        CharacterButton buttonRightParentheses = new CharacterButton(String.valueOf(Config.rightParentheses));
        buttonsPanel.add(buttonRightParentheses);

        ActionButton buttonDelete = new ActionButton(Config.delete, this::deleteLastCharacter);
        buttonsPanel.add(buttonDelete);

        ActionButton buttonClear = new ActionButton(String.valueOf(Config.clear), this::clearResult);
        buttonsPanel.add(buttonClear);

        /* -------------------------------------------------- */

        CharacterButton button7 = new CharacterButton(String.valueOf(Config.num7));
        buttonsPanel.add(button7);

        CharacterButton button8 = new CharacterButton(String.valueOf(Config.num8));
        buttonsPanel.add(button8);

        CharacterButton button9 = new CharacterButton(String.valueOf(Config.num9));
        buttonsPanel.add(button9);

        CharacterButton buttonDivision = new CharacterButton(String.valueOf(Config.divisionOperator));
        buttonsPanel.add(buttonDivision);

        /* -------------------------------------------------- */

        CharacterButton button4 = new CharacterButton(String.valueOf(Config.num4));
        buttonsPanel.add(button4);

        CharacterButton button5 = new CharacterButton(String.valueOf(Config.num5));
        buttonsPanel.add(button5);

        CharacterButton button6 = new CharacterButton(String.valueOf(Config.num6));
        buttonsPanel.add(button6);

        CharacterButton buttonMultiplication = new CharacterButton(String.valueOf(Config.multiplicationOperator));
        buttonsPanel.add(buttonMultiplication);

        /* -------------------------------------------------- */

        CharacterButton button1 = new CharacterButton(String.valueOf(Config.num1));
        buttonsPanel.add(button1);

        CharacterButton button2 = new CharacterButton(String.valueOf(Config.num2));
        buttonsPanel.add(button2);

        CharacterButton button3 = new CharacterButton(String.valueOf(Config.num3));
        buttonsPanel.add(button3);

        CharacterButton buttonSubtraction = new CharacterButton(String.valueOf(Config.subtractionOperator));
        buttonsPanel.add(buttonSubtraction);

        /* -------------------------------------------------- */

        CharacterButton buttonDot = new CharacterButton(String.valueOf(Config.dot));
        buttonsPanel.add(buttonDot);

        CharacterButton button0 = new CharacterButton(String.valueOf(Config.num0));
        buttonsPanel.add(button0);

        ActionButton buttonEquals = new ActionButton(String.valueOf(Config.equals), this::evaluate);
        buttonsPanel.add(buttonEquals);

        CharacterButton buttonAddition = new CharacterButton(String.valueOf(Config.additionOperator));
        buttonsPanel.add(buttonAddition);

        /* -------------------------------------------------- */

        add(buttonsPanel);
    }

    private void deleteLastCharacter()
    {
        String currentEquation = labelEquation.getText();

        if (!currentEquation.isEmpty())
        {
            if (currentEquation.length() == 1)
            {
                clearResult();
            }
            else
            {
                labelEquation.setText(currentEquation.substring(0, currentEquation.length() - 1));
            }
        }
    }

    public static void appendCharacterToLabelEquation(final char character)
    {
        if (labelEquation.getText().equals("0"))
        {
            labelEquation.setText(String.valueOf(character));
        }
        else
        {
            labelEquation.setText(labelEquation.getText() + character);
        }
    }

    private void clearResult()
    {
        labelEquation.setText("0");
        labelEquation.setForeground(Color.black);
    }

    private void convertToPostfix()
    {
        String currentEquation = labelEquation.getText();

        if (!currentEquation.isEmpty())
        {
            labelEquation.setText(Converter.convertToPostfixExpression(currentEquation));
        }
    }

    private void evaluate()
    {
        String currentEquation = labelEquation.getText();

        if (Validator.validateInfixExpression(currentEquation))
        {
            labelEquation.setForeground(Color.black);

            String postfixExpression = Converter.convertToPostfixExpression(currentEquation);
            labelEquation.setText(Evaluator.evaluate(postfixExpression));
        }
        else
        {
            labelEquation.setForeground(Color.red);
        }
    }

    private static JLabel labelEquation;
}
